package pl.lukaszbilski.Parts.Warehouse.models.repositories;


import org.springframework.data.repository.CrudRepository;
import pl.lukaszbilski.Parts.Warehouse.models.models.CompanyBranchModel;

import java.util.List;

public interface CompanyBranchRepository extends CrudRepository<CompanyBranchModel, Integer> {
    List<CompanyBranchModel> findAllByOrderByCity();
}
