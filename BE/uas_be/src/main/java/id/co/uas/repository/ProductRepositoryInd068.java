package id.co.uas.repository;

import id.co.uas.model.ProductModelInd068;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static id.co.uas.constant.ProductConstantInd068.*;

@Repository
public interface ProductRepositoryInd068 extends JpaRepository<ProductModelInd068, Integer> {

    @Query(value = qGetAllProduct, nativeQuery = true)
    public List<ProductModelInd068> getAllProduct();

}
