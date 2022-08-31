package payment.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import payment.dto.PaymentReq;
import payment.dto.PaymentRes;
import payment.repository.MemberRepository;
import payment.repository.PaymentRepository;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final MemberRepository memberRepository;

    @Value("${payments.toss.test_client_api_key}")
    private String testClientApiKey;

    @Value("${payments.toss.test_secret_api_key}")
    private String testSecretApiKey;

    @Value("${payments.toss.success_url}")
    private String successCallBackUrl;

    @Value("${payments.toss.fail_url}")
    private String failCallBackUrl;


    @Transactional
    public PaymentRes requestPayments(PaymentReq paymentReq){
        Long amount =paymentReq.getAmount();
        String payType=paymentReq.getPayType().name();
        String customerEmail=paymentReq.getOrderName().name();
    }
}
