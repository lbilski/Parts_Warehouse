package pl.lukaszbilski.Parts.Warehouse.models.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "cars")
public class CarsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String notes;
}
