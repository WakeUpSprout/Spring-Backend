package wakeup.sprout.spring.service.user;

import org.springframework.stereotype.Service;
import wakeup.sprout.spring.adapter.user.in.web.dto.request.LoginOrSignupRequest;
import wakeup.sprout.spring.adapter.user.in.web.dto.response.JWTResponse;
import wakeup.sprout.spring.common.excutable.Executable;

@Service
public class LoginOrSignupService implements Executable<LoginOrSignupRequest, JWTResponse> {
    @Override
    public JWTResponse execute(LoginOrSignupRequest request) {

        return null;
    }
}
