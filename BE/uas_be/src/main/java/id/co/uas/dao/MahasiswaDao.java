package id.co.uas.dao;

import id.co.uas.model.Mahasiswa;
import id.co.uas.vo.MahasiswaVo;
import id.co.uas.vo.UserVo;

import java.util.List;

public interface MahasiswaDao {

    List<MahasiswaVo> getAllMahasiswas();
    List<MahasiswaVo> getMahasiswaActive();
    MahasiswaVo getMahasiswaByNIM(String nim);

}