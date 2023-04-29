package com.inditex.challenge.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name="PRODUCT")
public class ProductEntity {
    @Id
    @Column(name = "product_id")
    private Long id;

    @Column(name = "sequence")
    private Long sequence;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "productId")
    private Set<SizeEntity> size;
}
