package pl.lukaszbilski.Parts.Warehouse.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.lukaszbilski.Parts.Warehouse.models.models.PricesModel;

import java.util.List;

@Repository
public interface PricesRepository extends CrudRepository<PricesModel, Integer>{
    List<PricesModel> findAll();
    List<PricesModel> findAllByName(String name);
    List<PricesModel> findAllByCar(String car);
}
