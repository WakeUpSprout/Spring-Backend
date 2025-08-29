package wakeup.sprout.spring.adapter.recipe.out.persistence.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import wakeup.sprout.spring.adapter.food.out.persistence.entity.FoodJpaEntity;
import wakeup.sprout.spring.adapter.product.out.persistence.entity.ProductJpaEntity;

import java.io.Serializable;

@EqualsAndHashCode
@Embeddable
public class RecipeId implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "food_id", nullable = false, columnDefinition = "INT UNSIGNED")
    private FoodJpaEntity food;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "product_id", nullable = false, columnDefinition = "INT UNSIGNED")
    private ProductJpaEntity product;
}
