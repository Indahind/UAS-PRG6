package id.co.uas.vo;

import id.co.uas.model.Mahasiswa;
import id.co.uas.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MahasiswaVo {
    private String nim;
    private String password;
    private String nama;
    private String prodi;

    public MahasiswaVo(){

    }

    public MahasiswaVo(Mahasiswa user) {
        this.nim = user.getNim();
        this.password = user.getPassword();
        this.nama = user.getNama();
        this.prodi = user.getProdi();
    }

    public MahasiswaVo(String username, String password, String nama, String jabatan) {
        this.nim = username;
        this.password = password;
        this.nama = nama;
        this.prodi = jabatan;
    }
}
