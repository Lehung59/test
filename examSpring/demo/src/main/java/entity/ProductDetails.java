package entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "ProductDetails")
@Data
public class ProductDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productDetailId;

    private String productPropertyName;
    private int quantity;
    private float price;
    private float sellPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId")
    private ProductDetails parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductDetails> children;
    @OneToMany(mappedBy = "productDetail", cascade = CascadeType.ALL)
    private Set<ProductDetailPropertyDetail> productDetailPropertyDetails;

}
