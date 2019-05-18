package pl.lukaszbilski.Parts.Warehouse.models.models;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "services")
public class ServicesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String status;
    private String branch;
    @Column (name = "application_date")
    private Date applicationDate;
    @Column (name = "license_plate")
    private String licensePlate;
    @Column (name = "car_id")
    private String carID;
    private String tasks;
    @Column (name = "finished_tasks")
    private String finishedTasks;
    @Column (name = "next_action_date")
    private Date dateOfNextAction;
    @Column (name = "next_action_task")
    private String nextActionTask;
}
