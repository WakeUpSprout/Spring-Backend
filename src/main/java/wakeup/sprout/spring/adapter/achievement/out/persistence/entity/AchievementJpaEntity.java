package wakeup.sprout.spring.adapter.achievement.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "achievement")
public class AchievementJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "achievement_id", nullable = false, columnDefinition = "INT UNSIGNED")
    private Long achievementId;

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(100)")
    private String name;

    @Column(name = "description", nullable = false, columnDefinition = "VARCHAR(100)")
    private String description;

    @Column(name = "image_path", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT '/achievement/default.png'")
    private String imagePath;

    @Column(name = "condition_type", nullable = false, columnDefinition = "VARCHAR(50)" )
    private String conditionType;

    @Column(name = "condition_value", nullable = false, columnDefinition = "SMALLINT")
    private Integer conditionValue;

    @CreationTimestamp
    @Column(name = "created_time", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdTime;
}
