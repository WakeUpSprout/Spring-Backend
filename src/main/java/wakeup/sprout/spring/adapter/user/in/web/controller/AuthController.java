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
    @PostMapping(path = "/social")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(new JWTResponse("a", "b"));
    }
}
