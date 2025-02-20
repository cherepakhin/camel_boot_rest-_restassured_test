package ru.perm.v.spring.camel.restassured;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDTO {

    private int id;
    private String name;
    private double price;

}
