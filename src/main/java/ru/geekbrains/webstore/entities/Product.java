package ru.geekbrains.webstore.entities;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.util.List;

@Data
@Entity
@Table(name = "products")
@NoArgsConstructor
@Accessors(chain = true)
public class Product {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "title")
  private String title;

  @Column(name = "price")
  private Double price;

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
  private List<Order> orders;
}
