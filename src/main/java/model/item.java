package model;

import lombok.*;


import java.time.LocalDate;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class item {
    private String id;

    private String name;

    private String category;

    private Double price;

    private Integer quantity;

    private String suplier;

    private String location;

    private LocalDate date;




}
