package wakeup.sprout.spring.adapter.user.in.web.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import wakeup.sprout.spring.adapter.user.in.web.dto.type.SnsProvider;
import wakeup.sprout.spring.common.annotation.validation.EnumValue;

public record LoginOrSignupRequest(
        @Schema(description = "소셜 로그인 코드", example = "A1B2C3D4E5F6G7H8I9J0")
        @NotBlank(message = "code는 필수입니다.")
        String code,

        @Schema(description = "소셜 로그인 제공자", example = "GOOGLE")
        @NotBlank(message = "provider는 필수입니다.")
        @EnumValue(enumClass = SnsProvider.class, message = "제공자는 GOOGLE, KAKAO, APPLE 중 하나여야 합니다.")
        String provider
) {
}
