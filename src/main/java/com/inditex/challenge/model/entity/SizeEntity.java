package com.inditex.challenge.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="SIZE")
public class SizeEntity {

    @Id
    @Column(name = "size_id")
    private Long sizeId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "back_soon")
    private boolean backSoon;

    @Column(name = "special")
    private boolean special;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private StockEntity stock;
}
