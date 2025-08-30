package wakeup.sprout.spring.common.util.firebase;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import wakeup.sprout.spring.common.exception.CustomException;
import wakeup.sprout.spring.common.exception.GlobalErrorCode;
import wakeup.sprout.spring.common.util.firebase.dto.File;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileStore {
    private final Storage storage;
    private final Bucket bucket;

    private String getStoreFileName(String ext) {
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private String getExtension(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }

    /**
     * File을 Firebase Storage에서 가져옵니다.
     *
     * @param path 가져올 파일 경로
     * @return {@link File} 가져온 파일 정보
     */
    public Optional<File> get(String path) {
        log.info("get file: [path = {}]", path);

        Blob file = bucket.get(path);

        if (file == null || !file.exists()) {
            return Optional.empty();
        }

        return Optional.of(File.builder()
                .storeFileName(file.getName().substring(file.getName().lastIndexOf("/") + 1))
                .storePath(file.getName())
                .size(file.getSize())
                .extension(file.getContentType())
                .build());
    }

    /**
     * File을 Firebase Storage에 저장합니다.
     *
     * @param directoryPath 저장할 경로
     * @param file 저장할 파일
     * @return {@link File} 저장된 파일 정보
     */
    public File upload(String directoryPath, MultipartFile file) {
        log.info("store file: [path = {}, name = {}]", directoryPath, file.getOriginalFilename());

        if (file.isEmpty()) {
            log.error("Failed to store empty file because file empty. [path = {}, name = {}]", directoryPath, file.getOriginalFilename());
            throw new CustomException(GlobalErrorCode.BAD_REQUEST_FILE);
        }

        String originalFilename = file.getOriginalFilename();
        String ext = getExtension(originalFilename);
        String storeFileName = getStoreFileName(ext);
        String type = file.getContentType();
        String storeFilePath = directoryPath + storeFileName;
        long size = file.getSize();

        if (bucket.get(storeFileName) != null) {
            log.error("Failed to store file because file already exists. [path = {}, name = {}]", directoryPath, file.getOriginalFilename());
            throw new CustomException(GlobalErrorCode.FIREBASE_UPLOAD_ERROR);
        }

        try {
            bucket.create(storeFilePath, file.getBytes(), type);
        } catch (IOException e) {
            log.error("Failed to store file because can not read file. [path = {}, name = {}]", directoryPath, file.getOriginalFilename());
            throw new CustomException(GlobalErrorCode.FIREBASE_UPLOAD_ERROR);
        } catch (Exception e) {
            log.error("Failed to store file because can not create file. [path = {}, name = {}, reason = {}]", directoryPath, file.getOriginalFilename(), e.getMessage());
            throw new CustomException(GlobalErrorCode.FIREBASE_UPLOAD_ERROR);
        }

        return File.builder()
                .storeFileName(storeFileName)
                .originalFileName(originalFilename)
                .storePath(storeFilePath)
                .size(size)
                .extension(type)
                .build();
    }

    /**
     * File을 삭제합니다. <br />
     * File이 존재하지 않거나, 삭제하지 못했어도 에러를 발생시키지 않습니다.
     *
     * @param storePath 삭제할 파일 경로
     */
    public void delete(String storePath) {
        log.info("delete file: [name = {}]", storePath);

        try {
            Blob file = bucket.get(storePath);

            if (file == null) {
                log.error("Failed to delete file because file not found. [name = {}]", storePath);
                return;
            }

            file.delete();
        } catch (Exception e) {
            log.error("Failed to delete file because can not delete file. [path = {}, reason = {}]", storePath, e.getMessage());
        }
    }

    /**
     * 여러 개의 File을 삭제합니다. <br />
     * File이 존재하지 않거나, 삭제하지 못했어도 에러를 발생시키지 않습니다.
     *
     * @param storePaths 삭제할 파일 경로들
     */
    public void delete(List<String> storePaths) {
        if (storePaths.isEmpty()) {
            return;
        }

        log.info("delete files: [names = {}]", storePaths);

        try {
            List<BlobId> blobIds = storePaths.stream()
                    .map(filePath -> BlobId.of(bucket.getName(), filePath))
                    .toList();

            storage.delete(blobIds);
        } catch (Exception e) {
            log.error("Failed to delete files because can not delete files. [path = {}, reason = {}]", storePaths, e.getMessage());
        }
    }
}
