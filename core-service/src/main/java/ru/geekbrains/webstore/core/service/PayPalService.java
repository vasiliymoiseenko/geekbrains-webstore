package ru.geekbrains.webstore.core.service;

import com.paypal.orders.AddressPortable;
import com.paypal.orders.AmountBreakdown;
import com.paypal.orders.AmountWithBreakdown;
import com.paypal.orders.ApplicationContext;
import com.paypal.orders.Item;
import com.paypal.orders.Money;
import com.paypal.orders.Name;
import com.paypal.orders.OrderRequest;
import com.paypal.orders.PurchaseUnitRequest;
import com.paypal.orders.ShippingDetail;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PayPalService {

  private final OrderService orderService;

  @Transactional
  public OrderRequest createOrderRequest(Long orderId) {
    ru.geekbrains.webstore.core.entity.Order order = orderService.findById(orderId);

    OrderRequest orderRequest = new OrderRequest();
    orderRequest.checkoutPaymentIntent("CAPTURE");

    ApplicationContext applicationContext = new ApplicationContext()
        .brandName("Geekbrains Webstore")
        .landingPage("BILLING")
        .shippingPreference("SET_PROVIDED_ADDRESS");
    orderRequest.applicationContext(applicationContext);

    List<PurchaseUnitRequest> purchaseUnitRequests = new ArrayList<>();
    PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest()
        .referenceId(orderId.toString())
        .description("Geekbrains Webstore Order")
        .amountWithBreakdown(new AmountWithBreakdown().currencyCode("RUB").value(order.getPrice().toString())
            .amountBreakdown(new AmountBreakdown().itemTotal(new Money().currencyCode("RUB").value(order.getPrice().toString()))))
        .items(order.getItems().stream()
            .map(orderItem -> new Item()
                .name(orderItem.getProduct().getTitle())
                .unitAmount(new Money().currencyCode("RUB").value(orderItem.getPricePerProduct().toString()))
                .quantity(orderItem.getQuantity().toString()))
            .collect(Collectors.toList()))
        .shippingDetail(new ShippingDetail().name(new Name().fullName(order.getUsername()))
            .addressPortable(new AddressPortable().addressLine1("123 Townsend St").addressLine2("Floor 6")
                .adminArea2("San Francisco").adminArea1("CA").postalCode("94107").countryCode("US")));
    purchaseUnitRequests.add(purchaseUnitRequest);
    orderRequest.purchaseUnits(purchaseUnitRequests);
    return orderRequest;
  }
}
