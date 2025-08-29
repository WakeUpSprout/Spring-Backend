package wakeup.sprout.spring.adapter.plant.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import wakeup.sprout.spring.adapter.seed.out.persistence.entity.SeedJpaEntity;

import java.time.LocalDateTime;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "plant")
public class PlantJpaEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "plant_id", nullable = false, columnDefinition = "INT UNSIGNED")
    private Long plantId;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "seed_id", nullable = false, columnDefinition = "INT UNSIGNED")
    private SeedJpaEntity seed;

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(50)")
    private String name;

    @Column(name = "level", nullable = false, columnDefinition = "TINYINT CHECK (level >= 1 AND level <= 4)")
    private Integer level;

    @Column(name = "growth_point", nullable = false, columnDefinition = "INT UNSIGNED")
    private Long growthPoint;

    @Column(name = "image_path", nullable = false, columnDefinition = "VARCHAR(255)")
    private String imagePath;

    @CreationTimestamp
    @Column(name = "created_time", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdTime;
}
