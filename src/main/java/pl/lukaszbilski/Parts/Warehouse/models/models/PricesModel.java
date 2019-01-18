package pl.lukaszbilski.Parts.Warehouse.models.models;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "prices")
public class PricesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String unit;
    private String car;
    private String code_Inter_Cars;
    private String price_Inter_Cars;
    private String code_Hart;
    private String price_Hart;
    private String notes;
}
