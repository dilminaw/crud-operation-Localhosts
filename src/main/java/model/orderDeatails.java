package model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString@AllArgsConstructor
@NoArgsConstructor
public class orderDeatails {
    private String id;
    private String itemCode;
    private Integer qty;
    private Double discount;
}
