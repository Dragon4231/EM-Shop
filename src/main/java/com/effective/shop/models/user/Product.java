package com.effective.shop.models.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;
import java.util.Map;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.InheritanceType.JOINED;

@Entity
@Inheritance(strategy = JOINED)
@Table(name = "products")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(updatable = false, nullable = false)
    protected long id;

    String title;

    String description;

    String organization;

    Double price;

    Integer count;

    @ElementCollection(fetch = LAZY)
    List<String> reviews;

    @ElementCollection(fetch = LAZY)
    List<Integer> estimates;

    @ElementCollection(fetch = LAZY)
    Map<String, String> characteristics;
}
