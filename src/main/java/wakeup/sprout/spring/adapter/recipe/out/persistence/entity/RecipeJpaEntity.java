package wakeup.sprout.spring.adapter.recipe.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "recipe")
public class RecipeJpaEntity {
    @EmbeddedId
    private RecipeId recipeId;

    @Column(name = "quantity", nullable = false, columnDefinition = "INT UNSIGNED DEFAULT 1")
    private Integer quantity;

    @CreationTimestamp
    @Column(name = "created_time", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdTime;
}
