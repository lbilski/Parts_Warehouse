package pl.lukaszbilski.Parts.Warehouse.models.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.lukaszbilski.Parts.Warehouse.models.models.WholesaleModel;

import java.util.List;


public interface WholesaleRepository extends CrudRepository<WholesaleModel, Integer> {
    List<WholesaleModel> findAll();
}
