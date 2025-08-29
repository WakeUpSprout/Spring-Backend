package wakeup.sprout.spring.adapter.wakeuplog.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import wakeup.sprout.spring.adapter.alarm.out.persistence.entity.AlarmJpaEntity;
import wakeup.sprout.spring.adapter.user.out.persistence.entity.UserJpaEntity;

import java.time.LocalDateTime;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "wake_up_log")
public class WakeUpLogJpaEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "log_id", nullable = false, columnDefinition = "INT UNSIGNED")
    private Long logId;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "uuid", nullable = false, columnDefinition = "VARCHAR(36)")
    private UserJpaEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "alarm_id", columnDefinition = "INT UNSIGNED")
    private AlarmJpaEntity alarm;

    @Column(name = "wake_up_time", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime wakeUpTime;

    @CreationTimestamp
    @Column(name = "created_time", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdTime;
}
