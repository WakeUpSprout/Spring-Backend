package wakeup.sprout.spring.adapter.user.in.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wakeup.sprout.spring.adapter.user.in.web.dto.request.LoginRequest;
import wakeup.sprout.spring.adapter.user.in.web.dto.response.JWTResponse;
import wakeup.sprout.spring.common.annotation.swagger.ApiErrorResponse;
import wakeup.sprout.spring.common.annotation.swagger.ApiErrorResponses;
import wakeup.sprout.spring.common.exception.GlobalErrorCode;

@Tag(name = "Auth API", description = "인증 관련 API")
@RequiredArgsConstructor
@RestController
@ResponseBody
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Operation(
            summary = "소셜 로그인",
            description = "제공자에 맞춰 소셜 로그인을 진행하고, JWT 토큰을 발급합니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "성공",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = JWTResponse.class)
                            )
                    )
            }
    )
    @ApiErrorResponses({
            @ApiErrorResponse(
                    responseCode = "400",
                    description = "잘못된 요청",
                    errorCodeClass = GlobalErrorCode.class,
                    errorCodes = {"BAD_REQUEST"}
            ),
            @ApiErrorResponse(
                    responseCode = "500",
                    description = "서버 에러",
                    errorCodeClass = GlobalErrorCode.class,
                    errorCodes = {"INTERNAL_SERVER_ERROR", "REDIS_ERROR"}
            )
    })
    @PostMapping(path = "/social")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(new JWTResponse("a", "b"));
    }
}
