package entity;

import jakarta.persistence.*;
import org.hibernate.mapping.Property;

@Entity
@Table(name = "PropertyDetails")
public class PropertyDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int propertyDetailId;

    private String propertyDetailCode;
    private String propertyDetailDetail;
    @ManyToOne
    @JoinColumn(name = "propertyId")
    private Properties properties;
}
