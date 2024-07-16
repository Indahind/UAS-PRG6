package id.co.uas.repository;

import id.co.uas.constant.UserConstantInd068;
import id.co.uas.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query(value = "SELECT * FROM Tabel_User u WHERE u.username = :username", nativeQuery = true)
    List<Object[]> getUserByUsername(String username);

    @Query(value = "SELECT * FROM Tabel_User",nativeQuery = true)
    List<User> findByStatus(int status);

    @Query(value = UserConstantInd068.qGetLogin, nativeQuery = true)
    User findUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Query(value = UserConstantInd068.qGetUsernameUser, nativeQuery = true)
    String getExistingUsername(@Param("username") String email);

    @Query(value = UserConstantInd068.qGetUserByUsername, nativeQuery = true)
    User findUserByUsername(@Param("username") String username);

    @Query(value = UserConstantInd068.qGetUserByUsername, nativeQuery = true)
    Optional<User> findUserByUsernameDel(@Param("username") String username);

    @Query(value = UserConstantInd068.qGetUserByPassword, nativeQuery = true)
    User findUserByPassword(@Param("password") String password);

    //boolean existsByUsr_usernameOrUsr_email(String username, String email);

}
