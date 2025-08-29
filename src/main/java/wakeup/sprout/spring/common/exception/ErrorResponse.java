package wakeup.sprout.spring.common.exception;


import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import wakeup.sprout.spring.common.util.mapper.CustomTimeStamp;

@Getter
@Builder
public class ErrorResponse {
    private final String timestamp = new CustomTimeStamp().toString();
    private final int status;
    private final String error;
    private final String message;
    private final String code;
    private final String errorCode;

    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ErrorResponse.builder()
                        .status(errorCode.getHttpStatus().value())
                        .errorCode(errorCode.getErrorCode())
                        .error(errorCode.getHttpStatus().name())
                        .code(errorCode.getName())
                        .message(errorCode.getMessage())
                        .build()
                );
    }
}
