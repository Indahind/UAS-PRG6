package id.co.uas.repository;

import id.co.uas.model.CabangModelInd068;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static id.co.uas.constant.CabangConstantInd068.*;

@Repository
public interface CabangRepositoryInd068 extends JpaRepository<CabangModelInd068, Integer> {

    @Query(value = qGetAllCabang, nativeQuery = true)
    List<CabangModelInd068> getAllCabang();

}
