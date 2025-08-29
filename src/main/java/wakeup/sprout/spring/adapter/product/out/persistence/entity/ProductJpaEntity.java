package wakeup.sprout.spring.adapter.product.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "product")
public class ProductJpaEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false, columnDefinition = "INT UNSIGNED")
    private Long productId;

    @Column(name = "name", nullable = false, unique = true, columnDefinition = "VARCHAR(50)")
    private String name;

    @Column(name = "price", nullable = false, columnDefinition = "SMALLINT UNSIGNED")
    private Integer price;

    @Column(name = "image_path", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT '/product/default.png'")
    private String imagePath;

    @CreationTimestamp
    @Column(name = "created_time", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdTime;
}
