package com.gov.communal.model.price.gas.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
public class GasPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal price;

    @CreationTimestamp
    private Instant created;

    @UpdateTimestamp
    private Instant updated;

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!GasPrice.class.equals(o.getClass())) {
            return false;
        }
        GasPrice other = (GasPrice) o;
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
