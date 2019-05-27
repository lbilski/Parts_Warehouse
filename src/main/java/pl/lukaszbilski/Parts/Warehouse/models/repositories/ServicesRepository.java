package pl.lukaszbilski.Parts.Warehouse.models.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.lukaszbilski.Parts.Warehouse.models.models.ServicesModel;

import java.util.List;


public interface ServicesRepository extends CrudRepository<ServicesModel, Integer> {
    List<ServicesModel> findAllByOrderByDateOfNextAction();
}
