package demo.service;

import demo.model.Product;
import demo.repo.ProductRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired ProductRepo productRepo;


    public List<Product> getProducts() {
        return productRepo.findAll();
    }


    public Optional<Product> getProductById(int prodId) {
        return  productRepo.findById(prodId);
    }

    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    public Product updateProductById(int prodId, Product product) {
        if (productRepo.existsById(prodId)){
            product.setId(prodId);
            return productRepo.save(product);
        }
        return null;
    }

    public void deleteProductById(int prodId) {
        productRepo.deleteById(prodId);
    }
}
