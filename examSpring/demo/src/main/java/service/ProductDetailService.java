package service;

import entity.ProductDetails;
import entity.Products;
import org.springframework.stereotype.Service;
import repository.ProductDetailRepo;

import java.util.List;
import java.util.Optional;

@Service
public class ProductDetailService {
    private ProductDetailRepo productDetailRepo;
    public Optional<Integer> getQuantityByProductId(int productId) {
        List<ProductDetails> productDetails = productDetailRepo.findByProductId(productId);
        if (!productDetails.isEmpty()) {
            return Optional.of(productDetails.get(0).getQuantity());
        }
        return Optional.empty();
    }
}
