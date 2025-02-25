package ee.arohozi.veebipood.controller;

import ee.arohozi.veebipood.entity.Product;
import ee.arohozi.veebipood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @PostMapping("products")
    public List<Product> addProduct(@RequestBody Product product) {
        if (product.getId() != null) {
            throw new RuntimeException("err_product_id_not_null");
        }
        if (product.getPrice() <= 0) {
            throw new RuntimeException("err_product_price_negative");
        }
        productRepository.save(product);
        return productRepository.findAll();
    }

    @DeleteMapping("products/{id}")
    public List<Product> deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return productRepository.findAll();
    }

    @PutMapping("products")
    public List<Product> editProduct(@RequestBody Product product) {
        if (product.getId() == null) {
            throw new RuntimeException("err_product_id_null");
        }
        if (product.getPrice() <= 0) {
            throw new RuntimeException("err_product_price_negative");
        }
        productRepository.save(product);
        return productRepository.findAll();
    }

    @GetMapping("products/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    @PatchMapping("products")
    public List<Product> editProductValue(@RequestParam Long id, @RequestParam String field, @RequestParam String value) {
        if (id == null) {
            throw new RuntimeException("err_product_id_null");
        }
        Product product = productRepository.findById(id).orElseThrow();

        switch (field) {
            case "name" -> product.setName(value);
            case "price" -> {
                if (Double.parseDouble(value) <= 0) {
                    throw new RuntimeException("err_product_price_negative");
                }
                product.setPrice(Double.parseDouble(value));
            }
            case "image" -> product.setImage(value);
            case "active" -> product.setActive(Boolean.parseBoolean(value));
        }
        productRepository.save(product);
        return productRepository.findAll();
    }
}
