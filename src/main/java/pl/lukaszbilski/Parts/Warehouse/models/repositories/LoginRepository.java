package pl.lukaszbilski.Parts.Warehouse.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.lukaszbilski.Parts.Warehouse.models.models.LoginModel;

import java.util.Optional;

@Repository
public interface LoginRepository extends CrudRepository<LoginModel, Integer>{
    Optional<LoginModel> findByPassword(String password);
}
