package pl.lukaszbilski.Parts.Warehouse.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.lukaszbilski.Parts.Warehouse.models.models.CarsModel;

import java.util.List;

@Repository
public interface CarsRepository extends CrudRepository<CarsModel, Integer> {
    List<CarsModel> findAllByOrderByName();
}
