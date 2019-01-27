package pl.lukaszbilski.Parts.Warehouse.models.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name ="wholesale")
public class WholesaleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
}
