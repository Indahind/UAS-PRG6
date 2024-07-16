package id.co.uas.vo;

import id.co.uas.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVo {
    private String username;
    private String password;
    private String nama;
    private String jabatan;

    public UserVo(){

    }

    public UserVo(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.nama = user.getNama();
        this.jabatan = user.getJabatan();
    }

    public UserVo(String username, String password, String nama, String jabatan) {
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.jabatan = jabatan;
    }
}
