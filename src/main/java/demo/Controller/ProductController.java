package demo.Controller;

import demo.Model.Product;
import demo.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public List<Product> getProducts(){
        return productService.getProducts();
    }
    @GetMapping("/{prodId}")
    public ResponseEntity<Product> getProductById(@PathVariable int prodId){
        Optional <Product>  product=productService.getProductById(prodId);
        return product.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PostMapping
    public  ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product createdProduct= productService.addProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }
    @PutMapping("/{prodId}")
    public ResponseEntity<Product> updateProduct(@PathVariable int prodId, @RequestBody Product product){
        Product updatedProduct = productService.updateProductById(prodId, product);
        return updatedProduct != null ?ResponseEntity.ok(updatedProduct) : ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{prodId}")
    public  ResponseEntity<Product> deleteProduct(@PathVariable int prodId){
        productService.deleteProductById(prodId);
        return ResponseEntity.noContent().build();
    }
}
