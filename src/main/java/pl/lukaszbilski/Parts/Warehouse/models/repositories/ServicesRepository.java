package pl.lukaszbilski.Parts.Warehouse.models.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.lukaszbilski.Parts.Warehouse.models.models.ServicesModel;

import java.util.List;


public interface ServicesRepository extends CrudRepository<ServicesModel, Integer> {
    List<ServicesModel> findAllByStatusOrderByDateOfNextAction(String status);
    List<ServicesModel> findAllByCarIDContainingOrLicensePlateContainingOrderByDateOfNextAction(String carID, String licensePlate);
    List<ServicesModel> findAllByStatusLikeAndBranchLikeOrderByDateOfNextAction(String status, String branch);
}
