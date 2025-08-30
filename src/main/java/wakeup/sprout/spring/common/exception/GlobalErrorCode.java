package wakeup.sprout.spring.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum GlobalErrorCode implements ErrorCode {
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "필수사항 또는 형식을 지키지 않은 잘못된 요청입니다.", "VALID_400"),
    BAD_REQUEST_FILE(HttpStatus.BAD_REQUEST, "파일이 존재하지 않습니다.", "FILE_40001"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "예기치 못한 서버 에러입니다.", "SERVER_500"),
    REDIS_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Redis 서버 에러입니다.", "REDIS_50001"),
    FIREBASE_UPLOAD_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Firebase Storage 파일 업로드가 실패하였습니다.","FIREBASE_50010"),
    ;

    private final String name = this.name();
    private final HttpStatus httpStatus;
    private final String message;
    private final String errorCode;
}
