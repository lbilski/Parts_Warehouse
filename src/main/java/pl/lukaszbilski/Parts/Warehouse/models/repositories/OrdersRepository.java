package pl.lukaszbilski.Parts.Warehouse.models.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.lukaszbilski.Parts.Warehouse.models.models.OrdersModel;

import java.util.List;

public interface OrdersRepository extends CrudRepository<OrdersModel, Integer> {
    List<OrdersModel> findAllByCityLikeAndCarLikeAndPartLike(String city, String car, String part);
    List<OrdersModel> findAllByCityLikeAndCarLikeAndPartLikeAndAssemblyIsNull(String city, String car, String part);
    List<OrdersModel> findAllByCityLikeAndCarLikeAndPartLikeAndAssemblyNotNull(String city, String car, String part);
}
