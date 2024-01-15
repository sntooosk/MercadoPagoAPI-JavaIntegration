package com.api.pago.controller;

import com.api.pago.model.ProdutoModel;
import com.mercadopago.client.preference.*;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.resources.preference.Preference;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import com.mercadopago.exceptions.MPException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pago")
public class PagoController {

    @PostMapping("/create_preference")
    public Preference criarPreferencia(@RequestBody ProdutoModel produtoModel) throws MPException, MPApiException {
        PreferenceClient client = new PreferenceClient();

        List<PreferenceItemRequest> items = new ArrayList<>();
        PreferenceItemRequest item = PreferenceItemRequest.builder()
                .id("1234")
                .title(produtoModel.getNome_produto())
                .description(produtoModel.getDescricao())
                .categoryId("home")
                .quantity(produtoModel.getQuantidade())
                .currencyId("BRL")
                .unitPrice(new BigDecimal(produtoModel.getPreco()))
                .build();
        items.add(item);

        PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                .success("http://localhost:8080/pago/success")
                .pending("http://localhost:8080/pago/pending")
                .failure("http://localhost/pago/failure")
                .build();

        List<PreferencePaymentMethodRequest> excludedPaymentMethods = new ArrayList<>();
        excludedPaymentMethods.add(PreferencePaymentMethodRequest.builder().id("master").build());
        excludedPaymentMethods.add(PreferencePaymentMethodRequest.builder().id("amex").build());

        List<PreferencePaymentTypeRequest> excludedPaymentTypes = new ArrayList<>();
        excludedPaymentTypes.add(PreferencePaymentTypeRequest.builder().id("ticket").build());

        PreferencePaymentMethodsRequest paymentMethods = PreferencePaymentMethodsRequest.builder()
                .excludedPaymentMethods(excludedPaymentMethods)
                .excludedPaymentTypes(excludedPaymentTypes)
                .installments(5)
                .build();

        PreferenceRequest request = PreferenceRequest.builder()
                .items(items)
                .autoReturn("approved")
                .paymentMethods(paymentMethods)
                .backUrls(backUrls)
                .build();

        Preference result = client.create(request);

        return result;
    }

    @PostMapping("/notification")
    public ResponseEntity<String> notificacoes(@RequestParam String data_id, @RequestParam String type) {

        System.out.println("DATA_ID: " + data_id);
        System.out.println("TYPE: " + type);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/pending")
    public ModelAndView pendente(@RequestParam String collection_id,
            @RequestParam String collection_status,
            @RequestParam String payment_id,
            @RequestParam String status,
            @RequestParam String external_reference,
            @RequestParam String payment_type,
            @RequestParam String merchant_order_id,
            @RequestParam String preference_id,
            @RequestParam String site_id,
            @RequestParam String processing_mode,
            @RequestParam String merchant_account_id) {
        ModelAndView mav = new ModelAndView("resposta");
        mav.addObject("estado", "PENDENTE");
        mav.addObject("collection_id", collection_id);
        mav.addObject("collection_status", collection_status);
        mav.addObject("payment_id", payment_id);
        mav.addObject("status", status);
        mav.addObject("external_reference", external_reference);
        mav.addObject("payment_type", payment_type);
        mav.addObject("merchant_order_id", merchant_order_id);
        mav.addObject("preference_id", preference_id);
        mav.addObject("site_id", site_id);
        mav.addObject("processing_mode", processing_mode);
        mav.addObject("merchant_account_id", merchant_account_id);
        return mav;
    }

    @GetMapping("/failure")
    public ModelAndView falha(@RequestParam String collection_id,
            @RequestParam String collection_status,
            @RequestParam String payment_id,
            @RequestParam String status,
            @RequestParam String external_reference,
            @RequestParam String payment_type,
            @RequestParam String merchant_order_id,
            @RequestParam String preference_id,
            @RequestParam String site_id,
            @RequestParam String processing_mode,
            @RequestParam String merchant_account_id) {
        ModelAndView mav = new ModelAndView("resposta");
        mav.addObject("estado", "FALHA");
        mav.addObject("collection_id", collection_id);
        mav.addObject("collection_status", collection_status);
        mav.addObject("payment_id", payment_id);
        mav.addObject("status", status);
        mav.addObject("external_reference", external_reference);
        mav.addObject("payment_type", payment_type);
        mav.addObject("merchant_order_id", merchant_order_id);
        mav.addObject("preference_id", preference_id);
        mav.addObject("site_id", site_id);
        mav.addObject("processing_mode", processing_mode);
        mav.addObject("merchant_account_id", merchant_account_id);
        return mav;
    }

    @GetMapping("/success")
    public ModelAndView sucesso(@RequestParam String collection_id,
            @RequestParam String collection_status,
            @RequestParam String payment_id,
            @RequestParam String status,
            @RequestParam String external_reference,
            @RequestParam String payment_type,
            @RequestParam String merchant_order_id,
            @RequestParam String preference_id,
            @RequestParam String site_id,
            @RequestParam String processing_mode,
            @RequestParam String merchant_account_id) {

        ModelAndView mav = new ModelAndView("resposta");
        mav.addObject("estado", "SUCESSO");
        mav.addObject("collection_id", collection_id);
        mav.addObject("collection_status", collection_status);
        mav.addObject("payment_id", payment_id);
        mav.addObject("status", status);
        mav.addObject("external_reference", external_reference);
        mav.addObject("payment_type", payment_type);
        mav.addObject("merchant_order_id", merchant_order_id);
        mav.addObject("preference_id", preference_id);
        mav.addObject("site_id", site_id);
        mav.addObject("processing_mode", processing_mode);
        mav.addObject("merchant_account_id", merchant_account_id);
        return mav;
    }
}
