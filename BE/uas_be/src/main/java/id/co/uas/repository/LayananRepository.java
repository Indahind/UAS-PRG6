package id.co.uas.repository;

import id.co.uas.model.Layanan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static id.co.uas.constant.LayananConstantInd068.qAllDataActive;

@Repository
public interface LayananRepository extends JpaRepository<Layanan, Integer> {
    @Query(value = qAllDataActive, nativeQuery = true)
    List<Layanan> findByStokTersediaGreaterThan(int stok);

    @Query("SELECT l FROM Layanan l WHERE l.IdLayanan = :idLayanan")
    Layanan findByIdLayanan(Integer idLayanan);
}
