package com.hamza.gametransactionapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long productId;

    private String name;
    private String description;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<Transaction> transactions;

}
