package ee.arohozi.veebipood.repository;

import ee.arohozi.veebipood.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import org.springframework.data.domain.Pageable; /// !!!!!
import org.springframework.data.domain.Page;
//                                                  SEE MÄÄRAB
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Repository tagastab ainult kas Product, List<Product>
    // on juba sisse kirjutatud:
    // .findAll() ---> SELECT * FROM products
    // .save() ---> INSERT values() INTO products
    // .deleteById() ---> DELETE FROM products WHERE id=
    // .findById()   --> SELECT product FROM products

    // Jpa Buddy (JPA Designer)
    // File --> Settings --> Plugins --> otsingusse Jpa Buddy --> install
    //List<Product> find

    Page<Product> findByCategory_Id(Long id, Pageable pageable);
}