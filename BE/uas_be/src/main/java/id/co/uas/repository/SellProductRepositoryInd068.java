package id.co.uas.repository;

import id.co.uas.model.SellProductModelInd068;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import static id.co.uas.constant.SellProductConstantInd068.*;

@Repository
public interface SellProductRepositoryInd068 extends JpaRepository<SellProductModelInd068, String> {

    @Query(value = qGetAllSellProduct, nativeQuery = true)
    List<SellProductModelInd068> getAllSellProduct();

    @Query(value = qGetLastId, nativeQuery = true)
    public String getLastId(@Param("param") String param);

}
