package az.ramazan.ms_order.dao.repository;

import az.ramazan.ms_order.dao.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderEntity,Long> {
}
