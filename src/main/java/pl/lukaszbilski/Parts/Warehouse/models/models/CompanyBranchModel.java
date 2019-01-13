package pl.lukaszbilski.Parts.Warehouse.models.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "company_branch")
public class CompanyBranchModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String city;
    private String login_Hart;
    private String branch_Hart;
    private String notes_Hart;
    private String login_Inter_Cars;
    private String branch_Inter_Cars;
    private String notes_Inter_Cars;
}
