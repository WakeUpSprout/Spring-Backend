package wakeup.sprout.spring.common.util.firebase;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import wakeup.sprout.spring.common.annotation.FirebaseIntegrationTest;
import wakeup.sprout.spring.common.exception.CustomException;
import wakeup.sprout.spring.common.exception.GlobalErrorCode;
import wakeup.sprout.spring.common.util.firebase.dto.File;

import java.util.List;
import java.util.Optional;

@Slf4j
@FirebaseIntegrationTest
public class FileStoreIntegrationTest {
    @Autowired
    private FileStore fileStore;

    private final String FILE_NAME = "test-image.png";
    private final String FILE_CONTENT_TYPE = "image/png";
    private final byte[] FILE_CONTENT = "Test Image Content".getBytes();

    @Test
    @DisplayName("파일 업로드 - 성공")
    void uploadFile_Success() {
        MockMultipartFile multipartFile = new MockMultipartFile(
                "file",
                FILE_NAME,
                FILE_CONTENT_TYPE,
                FILE_CONTENT
        );

        File file = fileStore.upload("test/", multipartFile);
        log.info("Uploaded file info = {}", file);

        Assertions
                .assertThat(file)
                .isNotNull()
                .satisfies(f -> {
                    Assertions.assertThat(f.originalFileName()).isEqualTo(FILE_NAME);
                    Assertions.assertThat(f.extension()).isEqualTo(FILE_CONTENT_TYPE);
                    Assertions.assertThat(f.size()).isEqualTo(multipartFile.getSize());
                    Assertions.assertThat(f.storePath()).startsWith("test/");
                    Assertions.assertThat(f.storeFileName()).isNotEmpty();
                });
    }

    @Test
    @DisplayName("파일 업로드 - 실패 (빈 파일)")
    void uploadFile_EmptyFile_Failure() {
        MockMultipartFile emptyFile = new MockMultipartFile(
                "file",
                "",
                FILE_CONTENT_TYPE,
                new byte[]{}
        );

        Assertions.assertThatThrownBy(() -> fileStore.upload("test/", emptyFile))
                .isInstanceOf(CustomException.class)
                .hasFieldOrPropertyWithValue("errorCode", GlobalErrorCode.BAD_REQUEST_FILE);
    }

    @Test
    @DisplayName("파일 조회 - 성공")
    void getFile_Success() {
        MockMultipartFile multipartFile = new MockMultipartFile(
                "file",
                FILE_NAME,
                FILE_CONTENT_TYPE,
                FILE_CONTENT
        );

        File file = fileStore.upload("test/", multipartFile);
        Assertions.assertThat(file).isNotNull();

        String path = file.storePath();
        log.info("Retrieving file at path = {}", path);

        Assertions.assertThat(path).isNotEmpty();

        Optional<File> retrievedFileOpt = fileStore.get(path);
        Assertions.assertThat(retrievedFileOpt).isPresent();

        File retrievedFile = retrievedFileOpt.get();
        log.info("Retrieved file info = {}", retrievedFile);

        Assertions.assertThat(retrievedFile)
                .isNotNull()
                .satisfies(f -> {
                    Assertions.assertThat(f.extension()).isEqualTo(FILE_CONTENT_TYPE);
                    Assertions.assertThat(f.size()).isEqualTo(multipartFile.getSize());
                    Assertions.assertThat(f.storePath()).isEqualTo(path);
                    Assertions.assertThat(f.storeFileName()).isEqualTo(file.storeFileName());
                });
    }

    @Test
    @DisplayName("파일 조회 - 성공 (파일 없음)")
    void getFile_NotFound_Success() {
        Optional<File> retrievedFileOpt = fileStore.get("non-existent-path/non-existent-file.png");
        Assertions.assertThat(retrievedFileOpt).isEmpty();
    }

    @Test
    @DisplayName("파일 삭제 - 성공")
    void deleteFile_Success() {
        MockMultipartFile multipartFile = new MockMultipartFile(
                "file",
                FILE_NAME,
                FILE_CONTENT_TYPE,
                FILE_CONTENT
        );

        File file = fileStore.upload("test/", multipartFile);
        Assertions.assertThat(file).isNotNull();

        String path = file.storePath();
        log.info("Uploaded file path = {}", path);

        Assertions.assertThat(path).isNotEmpty();

        fileStore.delete(path);

        Optional<File> retrievedFileOpt = fileStore.get(path);
        Assertions.assertThat(retrievedFileOpt).isEmpty();
    }

    @Test
    @DisplayName("파일 삭제 - 성공 (파일 없음)")
    void deleteFile_NotFound_Success() {
        Assertions.assertThatCode(() -> fileStore.delete("non-existent-path/non-existent-file.png"))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("여러 파일 삭제 - 성공")
    void deleteFiles_Success() {
        MockMultipartFile multipartFile1 = new MockMultipartFile(
                "file",
                "test-image1.png",
                "image/png",
                "Test Image Content 1".getBytes()
        );

        MockMultipartFile multipartFile2 = new MockMultipartFile(
                "file",
                "test-image2.png",
                "image/png",
                "Test Image Content 2".getBytes()
        );

        File file1 = fileStore.upload("test/", multipartFile1);
        File file2 = fileStore.upload("test/", multipartFile2);

        Assertions.assertThat(file1).isNotNull();
        Assertions.assertThat(file2).isNotNull();

        String path1 = file1.storePath();
        String path2 = file2.storePath();

        log.info("Uploaded file paths = {}, {}", path1, path2);

        Assertions.assertThat(path1).isNotEmpty();
        Assertions.assertThat(path2).isNotEmpty();

        fileStore.delete(List.of(path1, path2));

        Optional<File> retrievedFileOpt1 = fileStore.get(path1);
        Optional<File> retrievedFileOpt2 = fileStore.get(path2);
        Assertions.assertThat(retrievedFileOpt1).isEmpty();
        Assertions.assertThat(retrievedFileOpt2).isEmpty();
    }
}
