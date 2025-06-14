package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class order {
    private String orderId;
    private LocalDate orderDate;
    private Integer cusId;
    private List<orderDeatails> orderDeatails;
}
