package pl.lukaszbilski.Parts.Warehouse.models.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.lukaszbilski.Parts.Warehouse.models.models.PartsModel;

import java.util.List;

public interface PartsRepository extends CrudRepository<PartsModel, Integer>{
    List<PartsModel> findAllByOrderByName();
}
