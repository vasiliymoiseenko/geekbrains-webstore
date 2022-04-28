package ru.geekbrains.webstore.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import javax.persistence.*;

@Data
@Entity
@Table(name = "orders")
@NoArgsConstructor
@Accessors(chain = true)
public class Order {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "purchase_price")
  private Double purchasePrise;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id")
  private Product product;
}
