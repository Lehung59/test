package controller;

import entity.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ProductService;

import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/purchase")
    public ResponseEntity<String> purchaseProduct(@RequestParam int productId, @RequestParam int quantityToPurchase) {
        Optional<Integer> currentQuantityOpt = productService.getQuantityByProductId(productId);
        if (!currentQuantityOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        int currentQuantity = currentQuantityOpt.get();
        if (currentQuantity == 0) {
            return ResponseEntity.ok("Hết hàng");
        } else if (quantityToPurchase > currentQuantity) {
            return ResponseEntity.badRequest().body("Không đủ số lượng, chỉ có " + currentQuantity + " sản phẩm trong kho");
        } else {
            // Xử lý logic mua hàng ở đây nếu số lượng đủ
            productService.purchaseProduct(productId, quantityToPurchase);
            return ResponseEntity.ok("Mua hàng thành công");
        }
    }
    @PutMapping("/update")
    public ResponseEntity<String> updateQuantity(@RequestParam int productId, @RequestParam int newQuantity) {
        Optional<Products> product = productService.getProductById(productId);
        if (!product.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            productService.updateProductQuantity(productId, newQuantity);
            return ResponseEntity.ok("Cập nhập thành công" + productId);
        }
    }
    @GetMapping("/latestVetsion")
    public ResponseEntity<String> getLatestVersion(@RequestParam int productId){
        Optional<Products> product = productService.getProductById(productId);
        if (!product.isPresent()) {
            return ResponseEntity.notFound().build();
        } else{
            return ResponseEntity.ok("Phiên bản mới nhất của" + productId + "là: "+ productService.getProductLatestVersions(productId));
        }
    }


}
