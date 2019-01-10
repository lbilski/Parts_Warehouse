package pl.lukaszbilski.Parts.Warehouse.models.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.lukaszbilski.Parts.Warehouse.models.models.LoginModel;

import java.util.Optional;


public interface LoginRepository extends CrudRepository<LoginModel, Integer>{
    Optional<LoginModel> findByPassword(String password);
}
