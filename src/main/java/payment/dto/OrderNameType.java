package payment.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderNameType {
    PRODUCT_NAME1("토스 머그컵");
    private final String name;
}
