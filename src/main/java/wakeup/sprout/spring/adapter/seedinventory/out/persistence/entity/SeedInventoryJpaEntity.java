package wakeup.sprout.spring.adapter.seedinventory.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "seed_inventory")
public class SeedInventoryJpaEntity {
    @EmbeddedId
    private SeedInventoryId seedInventoryId;

    @Column(name = "quantity", nullable = false, columnDefinition = "INT UNSIGNED CHECK (quantity >= 0)")
    private Integer quantity;

    @CreationTimestamp
    @Column(name = "created_time", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdTime;
}
