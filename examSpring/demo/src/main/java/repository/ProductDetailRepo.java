package repository;

import entity.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductDetailRepo extends JpaRepository<ProductDetails,Integer> {
    List<ProductDetails> findByProductId(int productId);
}
