package payment.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import payment.dto.PaymentReq;
import payment.dto.PaymentRes;
import payment.dto.PaymentResHandlerFailDto;
import payment.dto.SingleResult;
import payment.exception.BussinessException;
import payment.service.PaymentService;
import payment.service.ResponseService;

@Slf4j
@Api(tags = "12. 결제")
@RequestMapping("/v1/api/payment")
@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final ResponseService responseService;
    private final int FAIL = -1;




    @PostMapping
    public SingleResult<PaymentRes> requestPayments(
             @ModelAttribute PaymentReq paymentReq
            ){
        try{
            return responseService.getSingleResult(
                    paymentService.requestPayments(paymentReq)
            );
        } catch (Exception e){
            e.printStackTrace();
            throw new BussinessException(e.getMessage());
        }
    }

    @GetMapping("/success")
    public SingleResult<String> requestFinalPayments(
            @RequestParam String paymentKey,
            @RequestParam String orderId,
            @RequestParam Long amount
    ){
        try {
            System.out.println("paymentKey = " + paymentKey);
            System.out.println("orderId = " + orderId);
            System.out.println("amount = " + amount);

            paymentService.verifyRequest(paymentKey,orderId,amount);
            String result = paymentService.requestFinalPayment(paymentKey, orderId, amount);

            return responseService.getSingleResult(result);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(e.getMessage());
        }
    }


    @GetMapping("/fail")
    public SingleResult<PaymentResHandlerFailDto> requestFail(
            @RequestParam(name = "code") String errorCode,
            @RequestParam(name = "message") String errorMsg,
            @RequestParam(name = "orderId") String orderId
    ){
        try {
            return responseService.getSingleResult(
                    paymentService.requestFail(errorCode,errorMsg,orderId)
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new BussinessException(e.getMessage());
        }

    }

}
