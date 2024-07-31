package az.ramazan.products.dao.repository;

import az.ramazan.products.dao.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductEntity,Long> {
}
