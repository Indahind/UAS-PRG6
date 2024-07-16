package id.co.uas.repository;

import id.co.uas.constant.MahasiswaConstantInd068;
import id.co.uas.constant.UserConstantInd068;
import id.co.uas.model.Mahasiswa;
import id.co.uas.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MahasiswaRepository extends JpaRepository<Mahasiswa, String> {
    @Query(value = "SELECT * FROM Tabel_Mahasiswa u WHERE u.nim = :nim", nativeQuery = true)
    List<Object[]> getUserByUsername(String nim);

    @Query(value = "SELECT * FROM Tabel_Mahasiswa",nativeQuery = true)
    List<Mahasiswa> findByStatus(int status);

    @Query(value = MahasiswaConstantInd068.qGetLogin, nativeQuery = true)
    Mahasiswa findUserByUsernameAndPassword(@Param("nim") String nim, @Param("password") String password);

    @Query(value = MahasiswaConstantInd068.qGetUsernameUser, nativeQuery = true)
    String getExistingUsername(@Param("nim") String nim);

    @Query(value = MahasiswaConstantInd068.qGetUserByUsername, nativeQuery = true)
    Mahasiswa findUserByUsername(@Param("nim") String username);

    @Query(value = MahasiswaConstantInd068.qGetUserByUsername, nativeQuery = true)
    Optional<Mahasiswa> findUserByUsernameDel(@Param("nim") String username);

    @Query(value = MahasiswaConstantInd068.qGetUserByPassword, nativeQuery = true)
    Mahasiswa findUserByPassword(@Param("password") String password);

    //boolean existsByUsr_usernameOrUsr_email(String username, String email);

}
