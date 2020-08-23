package mrtrix.geekbrains.smsbackend.repository;

import mrtrix.geekbrains.smsbackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    // по продукта по нескольким буквам
    List<Product> findByTitleLike(String title);
}
