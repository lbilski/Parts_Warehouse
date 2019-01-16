package pl.lukaszbilski.Parts.Warehouse.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.lukaszbilski.Parts.Warehouse.models.models.CompanyBranchModel;

import java.util.List;

@Repository
public interface CompanyBranchRepository extends CrudRepository<CompanyBranchModel, Integer> {
    List<CompanyBranchModel> findAllByOrderByCity();
}
