package payment.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import payment.config.DateConfig;
import payment.domain.Payment;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentReq {



    private PayType payType;

    private Long amount;

    private OrderNameType orderName;

    private String customerEmail;

    private String customerName;
    public Payment toEntity() {
        return Payment.builder()
                .orderId(UUID.randomUUID().toString())
                .payType(payType)
                .amount(amount)
                .orderName(orderName)
                .customerEmail(customerEmail)
                .customerName(customerName)
                .paySuccessYn("Y")
                .createDate(new DateConfig().getNowDate())
                .build();
    }
}
