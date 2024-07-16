package id.co.uas.repository;

import id.co.uas.model.Transaksi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import static id.co.uas.constant.TransaksiConstantInd068.qAllData;

public interface TransaksiRepository extends JpaRepository<Transaksi, String> {
    @Query(value = qAllData, nativeQuery = true)
    List<Transaksi> findAllTransaksi();

    @Query(value = "SELECT TOP 1 IdTransaksi FROM TRPENJUALANSALON ORDER BY IdTransaksi DESC", nativeQuery = true)
    String findLatestId();
    @Query(value = "SELECT * FROM transaksi_kue WHERE MONTH(tr_creadate) = :month AND YEAR(tr_creadate) = :year", nativeQuery = true)
    List<Transaksi> findByMonthAndYear(@Param("month") int month, @Param("year") int year);
}