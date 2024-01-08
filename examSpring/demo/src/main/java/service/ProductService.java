package service;

import entity.ProductDetails;
import entity.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ProductDetailRepo;
import repository.ProductRepo;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private ProductRepo productRepository;
    private ProductDetailRepo productDetailRepo;

    public List<Products> getProductByName(String name) {
        return productRepository.findByProductName(name);
    }
    public Optional<Products> getProductById(int productId) {
        return productRepository.findById(productId);
    }
    public Optional<Integer> getQuantityByProductId(int productId) {
        List<ProductDetails> productDetails = productDetailRepo.findByProductId(productId);
        if (!productDetails.isEmpty()) {
            return Optional.of(productDetails.get(0).getQuantity());
        }
        return Optional.empty();
    }

    public void purchaseProduct(int productId, int quantityToPurchase) {
        Optional<ProductDetails> productDetailOptional = productDetailRepo.findByProductId(productId)
                .stream()
                .findFirst();
        ProductDetails productDetail = productDetailOptional.get();
        productDetail.setQuantity(productDetail.getQuantity() - quantityToPurchase);
        productDetailRepo.save(productDetail);
    }
    public void updateProductQuantity (int productId, int newQuantity){
        Optional<ProductDetails> productDetailOptional = productDetailRepo.findByProductId(productId)
                .stream()
                .findFirst();
        ProductDetails productDetail = productDetailOptional.get();
        productDetail.setQuantity(newQuantity);
        productDetailRepo.save(productDetail);
    }

    public ProductDetails getProductLatestVersions(int productId) {
        List<ProductDetails> productDetails = productDetailRepo.findByProductId(productId);
        Map<Integer, ProductDetails> childToParentMap = new HashMap<>();
        ProductDetails latestVersion = null;

        for (ProductDetails detail : productDetails) {
            childToParentMap.put(detail.getParent().getProductDetailId(), detail);
        }

        for (ProductDetails detail : productDetails) {
            if (!childToParentMap.containsKey(detail.getProductDetailId())) {
                latestVersion = detail;
                break;
            }
        }
        return latestVersion;
    }
}
