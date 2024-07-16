package id.co.uas.constant;

import id.co.uas.model.Mahasiswa;
import id.co.uas.model.User;

import java.util.List;

public class MahasiswaConstantInd068 {

    public static List<Mahasiswa> mahasiswas;
    public static final String qGetLogin =
            "SELECT * FROM Tabel_Mahasiswa " +
                    "WHERE nim = :nim AND password = :password";
    public static final String qGetUsernameUser =
            "SELECT nim FROM Tabel_Mahasiswa WHERE nim = :nim";
    public static final String qGetUserByUsername =
            "SELECT * FROM Tabel_Mahasiswa WHERE nim = :nim";
    public static final String qGetUserByPassword =
            "SELECT * FROM Tabel_Mahasiswa WHERE password = :password";
    public static final String mNotFound = "User not found";
    public static final String mEmptyData = "No data available";
    public static final String mCreateSuccess = "User created successfully";
    public static final String mCreateFailed = "Failed to create User";
    public static final String mUpdateSuccess = "User updated successfully";
    public static final String mUpdateFailed = "Failed to update User";
    public static final String mDeleteSuccess = "User deleted sucessfully";
    public static final String mDeleteFailed = "Failed to delete User";

}
