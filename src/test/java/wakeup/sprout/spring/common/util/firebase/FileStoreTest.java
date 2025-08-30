package wakeup.sprout.spring.common.util.firebase;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import wakeup.sprout.spring.common.exception.CustomException;
import wakeup.sprout.spring.common.exception.GlobalErrorCode;
import wakeup.sprout.spring.common.util.firebase.FileStore;
import wakeup.sprout.spring.common.util.firebase.dto.File;

import java.util.List;
import java.util.Optional;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class FileStoreTest {
    @Mock
    private Storage storage;
    @Mock
    private Bucket bucket;
    @InjectMocks
    private FileStore fileStore;

    @Test
    @DisplayName("파일 조회 - 성공")
    void getFile_Success() {
        String filePath = "images/test-image.png";

        Blob mockBlob = Mockito.mock(Blob.class);
        Mockito
                .when(bucket.get(Mockito.anyString()))
                .thenReturn(mockBlob);

        Mockito
                .when(mockBlob.exists())
                .thenReturn(true);

        Mockito
                .when(mockBlob.getName())
                .thenReturn("test-image.png");

        Mockito
                .when(mockBlob.getContentType())
                .thenReturn("image/png");

        Mockito
                .when(mockBlob.getSize())
                .thenReturn(1024L);

        Optional<File> optionalFile = fileStore.get(filePath);

        Assertions
                .assertThat(optionalFile).isPresent()
                .get()
                .satisfies(file -> {
                    Assertions.assertThat(file.originalFileName()).isEqualTo("test-image.png");
                    Assertions.assertThat(file.extension()).isEqualTo("image/png");
                    Assertions.assertThat(file.size()).isEqualTo(1024L);
                    Assertions.assertThat(file.storePath()).isEqualTo("test-image.png");
                    Assertions.assertThat(file.storeFileName()).isEqualTo("test-image.png");
                });
    }

    @Test
    @DisplayName("파일 조회 - 실패 (파일 없음)")
    void getFile_Fail_FileNotFound() {
        String filePath = "images/non-existent-image.png";

        Mockito
                .when(bucket.get(Mockito.anyString()))
                .thenReturn(null);

        Optional<File> optionalFile = fileStore.get(filePath);

        Assertions
                .assertThat(optionalFile).isNotPresent();
    }

    @Test
    @DisplayName("파일 업로드 - 성공")
    void uploadFile_Success() {
        String dictionaryPath = "images/";

        MockMultipartFile mockFile = new MockMultipartFile(
                "file",
                "test-image.png",
                "image/png",
                "Test Image Content".getBytes()
        );

        Mockito
                .when(bucket.get(Mockito.anyString()))
                .thenReturn(null);

        Mockito
                .when(bucket.create(Mockito.anyString(), Mockito.any(byte[].class), Mockito.anyString()))
                .thenReturn(Mockito.mock(Blob.class));

        File uploadedFile = fileStore.upload(dictionaryPath, mockFile);

        log.info("Uploaded File = {}", uploadedFile);

        Assertions
                .assertThat(uploadedFile).isNotNull()
                .satisfies(file -> {
                    Assertions.assertThat(file.originalFileName()).isEqualTo("test-image.png");
                    Assertions.assertThat(file.extension()).isEqualTo("image/png");
                    Assertions.assertThat(file.size()).isEqualTo(mockFile.getSize());
                    Assertions.assertThat(file.storePath()).startsWith(dictionaryPath);
                    Assertions.assertThat(file.storeFileName()).isNotEmpty();
                });
    }

    @Test
    @DisplayName("파일 업로드 - 실패 (빈 파일)")
    void uploadFile_Fail_EmptyFile() {
        String dictionaryPath = "images/";

        MockMultipartFile mockFile = new MockMultipartFile(
                "file",
                "",
                "image/png",
                new byte[]{}
        );

        Assertions
                .assertThatThrownBy(() -> fileStore.upload(dictionaryPath, mockFile))
                .isInstanceOf(CustomException.class)
                .hasFieldOrPropertyWithValue("errorCode", GlobalErrorCode.BAD_REQUEST_FILE);
    }

    @Test
    @DisplayName("파일 업로드 - 실패 (파일 존재)")
    void uploadFile_Fail_FileExists() {
        String dictionaryPath = "images/";

        MockMultipartFile mockFile = new MockMultipartFile(
                "file",
                "test-image.png",
                "image/png",
                "Test Image Content".getBytes()
        );

        Mockito
                .when(bucket.get(Mockito.anyString()))
                .thenReturn(Mockito.mock(Blob.class));

        Assertions
                .assertThatThrownBy(() -> fileStore.upload(dictionaryPath, mockFile))
                .isInstanceOf(CustomException.class)
                .hasFieldOrPropertyWithValue("errorCode", GlobalErrorCode.FIREBASE_UPLOAD_ERROR);
    }

    @Test
    @DisplayName("파일 삭제 - 성공")
    void deleteFile_Success() {
        String filePath = "images/test-image.png";

        Blob mockBlob = Mockito.mock(Blob.class);
        Mockito
                .when(bucket.get(Mockito.anyString()))
                .thenReturn(mockBlob);

        Assertions
                .assertThatCode(() -> fileStore.delete(filePath))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("파일 삭제 - 성공 (파일 없음)")
    void deleteFile_Fail_FileNotFound() {
        String filePath = "images/non-existent-image.png";

        Mockito
                .when(bucket.get(Mockito.anyString()))
                .thenReturn(null);

        Assertions
                .assertThatCode(() -> fileStore.delete(filePath))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("파일 목록 삭제 - 성공")
    void deleteFiles_Success() {
        String filePath1 = "images/test-image1.png";
        String filePath2 = "images/test-image2.png";

        Mockito
                .when(bucket.getName())
                .thenReturn("mock-bucket");

        Assertions
                .assertThatCode(() -> fileStore.delete(List.of(filePath1, filePath2)))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("파일 목록 삭제 - 성공 (빈 목록)")
    void deleteFiles_Success_EmptyList() {
        Assertions
                .assertThatCode(() -> fileStore.delete(List.of()))
                .doesNotThrowAnyException();
    }
}
