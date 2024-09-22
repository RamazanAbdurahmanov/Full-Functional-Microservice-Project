package az.ramazan.ms_payment.repository;

import az.ramazan.ms_payment.dao.entity.PaymentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends CrudRepository<PaymentEntity,Long> {
    Optional<PaymentEntity> findByOrderId(Long orderId);
}
