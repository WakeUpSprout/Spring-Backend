package wakeup.sprout.spring.adapter.alarm.out.persistence.entity;

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
@Table(name = "alarm")
public class AlarmJpaEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "alarm_id", nullable = false, columnDefinition = "INT UNSIGNED")
    private Long alarmId;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "uuid", nullable = false, columnDefinition = "VARCHAR(36)")
    private UserJpaEntity user;

    @Column(name = "repeat_time", columnDefinition = "SMALLINT")
    private Integer repeatTime;

    @Column(name = "repeat_count", columnDefinition = "SMALLINT CHECK (repeat_count >= 1)")
    private Integer repeatCount;

    @Column(name = "is_active", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean isActive;

    @Column(name = "is_deleted", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isDeleted;

    @Column(name = "wake_up_time", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime wakeUpTime;

    @CreationTimestamp
    @Column(name = "created_time", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdTime;
}
