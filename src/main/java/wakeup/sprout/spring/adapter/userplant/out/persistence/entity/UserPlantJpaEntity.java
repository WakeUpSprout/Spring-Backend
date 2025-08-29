package wakeup.sprout.spring.adapter.userplant.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import wakeup.sprout.spring.adapter.plant.out.persistence.entity.PlantJpaEntity;
import wakeup.sprout.spring.adapter.user.out.persistence.entity.UserJpaEntity;

import java.time.LocalDateTime;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user_plant")
public class UserPlantJpaEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "user_plant_id", nullable = false, columnDefinition = "INT UNSIGNED")
    private Long userPlantId;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "uuid", nullable = false, columnDefinition = "VARCHAR(36)")
    private UserJpaEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "plant_id", columnDefinition = "INT UNSIGNED")
    private PlantJpaEntity plant;

    @Column(name = "current_point", nullable = false, columnDefinition = "INT UNSIGNED DEFAULT 0")
    private Integer currentPoint;

    @Column(name = "is_fully_grown", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isFullyGrown;

    @Column(name = "is_growing", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean isGrowing;

    @Column(name = "last_watered_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime lastWateredTime;

    @CreationTimestamp
    @Column(name = "created_time", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdTime;
}
