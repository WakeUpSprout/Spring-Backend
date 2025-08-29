package wakeup.sprout.spring.adapter.user.in.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User API", description = "사용자 관련 API")
@RequiredArgsConstructor
@RestController
@ResponseBody
@RequestMapping("/api/v1/users")
public class UserController {

}
