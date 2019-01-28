package pl.lukaszbilski.Parts.Warehouse.models.models;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "orders")
public class OrdersModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String city;
    private String wholesale;
    private Date date_order;
    private String number_order;
    private String car;
    @Column(name = "part_name")
    private String part;
    @Column(name = "part_code")
    private String partCode;
    private String unit;
    private int quantity;
    private String id_vin;
    @Column(name = "date_assembly")
    private Date assembly;
    private String notes;
}
