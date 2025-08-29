package wakeup.sprout.spring.adapter.friend.out.persistence.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import wakeup.sprout.spring.adapter.user.out.persistence.entity.UserJpaEntity;

import java.io.Serializable;

@EqualsAndHashCode
@Embeddable
public class FriendId implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "sender_id", nullable = false, columnDefinition = "VARCHAR(36)")
    private UserJpaEntity sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "receiver_id", nullable = false, columnDefinition = "VARCHAR(36)")
    private UserJpaEntity receiver;
}
