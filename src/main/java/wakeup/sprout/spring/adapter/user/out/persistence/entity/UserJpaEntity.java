package wakeup.sprout.spring.adapter.user.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import wakeup.sprout.spring.adapter.achievement.out.persistence.entity.AchievementJpaEntity;

import java.time.LocalDateTime;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "app_user")
public class UserJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uuid", nullable = false, columnDefinition = "VARCHAR(36)")
    private String uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "equipped_achievement_id", columnDefinition = "INT UNSIGNED")
    private AchievementJpaEntity equippedAchievement;

    @Column(name = "sns_id", nullable = false, columnDefinition = "VARCHAR(255)")
    private String snsId;

    @Enumerated(EnumType.STRING)
    @Column(name = "provider", nullable = false, columnDefinition = "ENUM('KAKAO','GOOGLE','APPLE')")
    private SnsProvider provider;

    @Column(name = "code", nullable = false, unique = true, columnDefinition = "CHAR(6)")
    private String code;

    @Column(name = "nickname", nullable = false, unique = true, columnDefinition = "VARCHAR(12)")
    private String nickname;

    @Column(name = "coin", nullable = false, columnDefinition = "INT UNSIGNED")
    private Long coin;

    @Column(name = "image_path", nullable = false, columnDefinition = "VARCHAR(255)")
    private String imagePath;

    @Column(name = "is_deleted", nullable = false, columnDefinition = "BOOLEAN")
    private Boolean isDeleted;

    @Column(name = "deleted_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime deletedTime;

    @CreationTimestamp
    @Column(name = "created_time", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdTime;
}
