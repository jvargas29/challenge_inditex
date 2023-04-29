package com.inditex.challenge.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="STOCK")
public class StockEntity {
    @Id
    @Column(name = "size_id")
    private Long sizeId;

    @Column(name = "quantity")
    private Integer quantity;

}
