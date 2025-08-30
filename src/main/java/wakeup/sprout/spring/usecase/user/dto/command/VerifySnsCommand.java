package wakeup.sprout.spring.usecase.user.dto.command;

import wakeup.sprout.spring.adapter.user.in.web.dto.type.SnsProvider;

public record VerifySnsCommand(
        String code,
        SnsProvider provider
) {
}
