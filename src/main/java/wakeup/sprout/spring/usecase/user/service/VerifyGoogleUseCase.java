package wakeup.sprout.spring.usecase.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import wakeup.sprout.spring.usecase.user.dto.command.VerifySnsCommand;
import wakeup.sprout.spring.usecase.user.port.in.VerifySnsPortIn;

@Slf4j
@RequiredArgsConstructor
@Component
public class VerifyGoogleUseCase implements VerifySnsPortIn {
    @Override
    public void execute(VerifySnsCommand request) {

    }
}
