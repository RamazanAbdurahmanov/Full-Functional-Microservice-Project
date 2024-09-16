package az.ramazan.ms_payment.dao.entity;

import az.ramazan.ms_payment.model.enums.PaymentStatus;
import az.ramazan.ms_payment.model.enums.PaymentType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.*;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderId;
    @Enumerated(STRING)
    private PaymentType paymentType;
    private String referenceNumber;
    @Enumerated(STRING)
    private PaymentStatus status;
    private BigDecimal amount;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
