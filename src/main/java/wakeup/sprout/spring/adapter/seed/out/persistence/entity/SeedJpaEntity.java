package wakeup.sprout.spring.adapter.seed.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import wakeup.sprout.spring.adapter.plant.out.persistence.entity.SeedType;

import java.time.LocalDateTime;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "seed")
public class SeedJpaEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "seed_id", nullable = false, columnDefinition = "INT UNSIGNED")
    private Long seedId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, columnDefinition = "ENUM('PLANT','CROPS')")
    private SeedType type;

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(30)")
    private String name;

    @Column(name = "rarity", nullable = false, columnDefinition = "TINYINT CHECK (rarity >= 1 AND rarity <= 4)")
    private Integer rarity;

    @Column(name = "price", nullable = false, columnDefinition = "SMALLINT")
    private Integer price;

    @Column(name = "image_path", nullable = false, columnDefinition = "VARCHAR(255)")
    private String imagePath;

    @CreationTimestamp
    @Column(name = "created_time", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdTime;
}
