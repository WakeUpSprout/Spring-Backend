package wakeup.sprout.spring.adapter.collection.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import wakeup.sprout.spring.adapter.food.out.persistence.entity.FoodJpaEntity;
import wakeup.sprout.spring.adapter.plant.out.persistence.entity.PlantJpaEntity;

import java.time.LocalDateTime;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "collection")
public class CollectionJpaEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "collection_id", nullable = false, columnDefinition = "INT UNSIGNED")
    private Long collectionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "food_id", columnDefinition = "INT UNSIGNED")
    private FoodJpaEntity food;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "plant_id", columnDefinition = "INT UNSIGNED")
    private PlantJpaEntity plant;

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(100)")
    private String name;

    @Column(name = "image_path", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT '/collection/default.png'")
    private String imagePath;

    @CreationTimestamp
    @Column(name = "created_time", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdTime;
}
