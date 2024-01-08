package entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "Properties")
@Data
public class Properties {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int propertyId;

    private String propertyName;
    private int propertySort;

    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "productId")
    private Products products;

    @OneToMany(mappedBy = "properties", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ProductDetails> productDetails;

}
