package wakeup.sprout.spring.domain.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import wakeup.sprout.spring.common.exception.ErrorCode;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {
    KAKAO_LOGIN_FAILED(HttpStatus.UNAUTHORIZED, "KAKAO 인증에 실패했습니다.", "AUTH_40101"),
    GOOGLE_LOGIN_FAILED(HttpStatus.UNAUTHORIZED, "GOOGLE 인증에 실패했습니다.", "AUTH_40102"),
    APPLE_LOGIN_FAILED(HttpStatus.UNAUTHORIZED, "APPLE 인증에 실패했습니다.", "AUTH_40103"),
    ;

    private final String name = this.name();
    private final HttpStatus httpStatus;
    private final String message;
    private final String errorCode;
}
