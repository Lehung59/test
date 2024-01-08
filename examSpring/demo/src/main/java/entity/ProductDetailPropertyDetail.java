package entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ProductDetailPropertyDetails")
@Data
public class ProductDetailPropertyDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productDetailPropertyDetailId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productDetailId")
    private ProductDetails productDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "propertyDetailId")
    private PropertyDetails propertyDetails ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private Products products ;
}
