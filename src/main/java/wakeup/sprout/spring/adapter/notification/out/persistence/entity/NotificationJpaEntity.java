package wakeup.sprout.spring.adapter.notification.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import wakeup.sprout.spring.adapter.user.out.persistence.entity.UserJpaEntity;

import java.time.LocalDateTime;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "notification")
public class NotificationJpaEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "notification_id", nullable = false, columnDefinition = "INT UNSIGNED")
    private Long notificationId;

    @Enumerated(EnumType.STRING)
    @Column(name = "target_type", nullable = false, columnDefinition = "ENUM('ALL','INDIVIDUAL')")
    private NotificationTargetType targetType;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "target_id", columnDefinition = "VARCHAR(36)")
    private UserJpaEntity target;

    @Column(name = "title", nullable = false, columnDefinition = "VARCHAR(40)")
    private String title;

    @Column(name = "content", nullable = false, columnDefinition = "VARCHAR(100)")
    private String content;

    @CreationTimestamp
    @Column(name = "created_time", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdTime;
}
