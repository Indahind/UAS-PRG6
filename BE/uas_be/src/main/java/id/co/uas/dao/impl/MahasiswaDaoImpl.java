package id.co.uas.dao.impl;

import id.co.uas.dao.MahasiswaDao;
import id.co.uas.dao.UserDao;
import id.co.uas.model.Mahasiswa;
import id.co.uas.model.User;
import id.co.uas.repository.MahasiswaRepository;
import id.co.uas.repository.UserRepository;
import id.co.uas.vo.MahasiswaVo;
import id.co.uas.vo.UserVo;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class MahasiswaDaoImpl implements MahasiswaDao {

    @Autowired
    private MahasiswaRepository userRepository;

    public MahasiswaDaoImpl() {
    }


    @Autowired
    private EntityManager entityManager;

    public List<MahasiswaVo> getAllMahasiswas() {
        Iterable<Mahasiswa> users = this.userRepository.findAll();
        List<MahasiswaVo> userVos = new ArrayList();
        Iterator var3 = users.iterator();

        while(var3.hasNext()) {
            Mahasiswa item = (Mahasiswa) var3.next();
            MahasiswaVo userVo = new MahasiswaVo(item);
            userVos.add(userVo);
        }

        return userVos;
    }

    public List<MahasiswaVo> getMahasiswaActive() {
        Iterable<Mahasiswa> users = this.userRepository.findByStatus(1);
        List<MahasiswaVo> userVos = new ArrayList();
        Iterator var3 = users.iterator();

        while(var3.hasNext()) {
            Mahasiswa item = (Mahasiswa)var3.next();
            MahasiswaVo userVo = new MahasiswaVo(item);
            userVos.add(userVo);
        }

        return userVos;
    }

    @Override
    public MahasiswaVo getMahasiswaByNIM(String nim) {
        List<Object[]> results = userRepository.getUserByUsername(nim);
        MahasiswaVo userVo = null;

        for (Object[] result : results) {
            String username = (String) result[0];
            String password = (String) result[1];
            String nama = (String) result[2];
            String jabatan = (String) result[3];

            userVo = new MahasiswaVo(username, password, nama, jabatan);
        }

        return userVo;
    }

}
