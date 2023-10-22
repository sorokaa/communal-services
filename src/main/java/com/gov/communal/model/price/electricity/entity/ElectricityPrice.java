package com.gov.communal.model.price.electricity.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table
public class ElectricityPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal price;

    @CreationTimestamp
    private Instant created;

    @UpdateTimestamp
    private Instant updated;

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ElectricityPrice other = (ElectricityPrice) obj;
        return Objects.equals(id, other.getId())
                && Objects.equals(price, other.price)
                && Objects.equals(created, other.created)
                && Objects.equals(updated, other.updated);
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(this.id);
    }
}
