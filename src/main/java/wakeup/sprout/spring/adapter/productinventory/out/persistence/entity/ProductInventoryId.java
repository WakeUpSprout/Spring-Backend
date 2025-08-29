package wakeup.sprout.spring.adapter.productinventory.out.persistence.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import wakeup.sprout.spring.adapter.product.out.persistence.entity.ProductJpaEntity;
import wakeup.sprout.spring.adapter.user.out.persistence.entity.UserJpaEntity;

import java.io.Serializable;

@EqualsAndHashCode
@Embeddable
public class ProductInventoryId implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "uuid", nullable = false, columnDefinition = "VARCHAR(36)")
    private UserJpaEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "product_id", nullable = false, columnDefinition = "INT UNSIGNED")
    private ProductJpaEntity product;
}
