package wakeup.sprout.spring.adapter.user.in.web.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record JWTResponse(
        @Schema(description = "인가를 위한 Access Token", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
        String accessToken,
        @Schema(description = "Access Token 갱신을 위한 Token", example = "dGhpc0lzQVRlc3RSZWZyZXNoVG9rZW4...")
        String refreshToken
) {
}
