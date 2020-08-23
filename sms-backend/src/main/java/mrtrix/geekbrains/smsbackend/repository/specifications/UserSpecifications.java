package mrtrix.geekbrains.smsbackend.repository.specifications;


import mrtrix.geekbrains.smsbackend.entity.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecifications {
    public static Specification<User> findByLike(String name) {
        return (Specification<User>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), name);
    }
}
