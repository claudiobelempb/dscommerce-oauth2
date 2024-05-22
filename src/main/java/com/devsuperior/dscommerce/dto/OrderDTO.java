package com.devsuperior.dscommerce.dto;

import com.devsuperior.dscommerce.entities.Order;
import com.devsuperior.dscommerce.entities.OrderItem;
import com.devsuperior.dscommerce.entities.OrderStatus;
import jakarta.validation.constraints.NotEmpty;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO {

    private Long id;
    private OrderStatus status;
    private Instant moment;
    private ClientDTO client;
    private PaymentDTO payment;

    @NotEmpty(message = "Deve ter pelo menos uma item")
    private List<OrderItemsDTO> items = new ArrayList<>();

    public OrderDTO() {
    }

    public OrderDTO(Long id, OrderStatus status, Instant moment, ClientDTO client, PaymentDTO payment) {
        this.id = id;
        this.status = status;
        this.moment = moment;
        this.client = client;
        this.payment = payment;
    }

    public OrderDTO(Order entity) {
        id = entity.getId();
        moment = entity.getMoment();
        status = entity.getStatus();
        client = new ClientDTO(entity.getClient());
        payment = (entity.getPayment() == null) ? null : new PaymentDTO(entity.getPayment());
        for (OrderItem dto : entity.getItems()) {
            items.add(new OrderItemsDTO(dto));
        }
    }

    public Long getId() {
        return id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Instant getMoment() {
        return moment;
    }

    public ClientDTO getClient() {
        return client;
    }

    public PaymentDTO getPayment() {
        return payment;
    }

    public List<OrderItemsDTO> getItems() {
        return items;
    }

    public Double getTotal() {
        double sum = 0;
        for (OrderItemsDTO item : items) {
            sum += item.getSubTotal();
        }
        return sum;
    }
}
