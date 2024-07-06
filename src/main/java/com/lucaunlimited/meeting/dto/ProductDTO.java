package com.lucaunlimited.meeting.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private String name;
    private double price;
    private Integer stock;
}
