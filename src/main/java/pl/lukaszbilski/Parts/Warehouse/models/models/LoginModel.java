package pl.lukaszbilski.Parts.Warehouse.models.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="user")
public class LoginModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    public String login;
    public String password;

}
