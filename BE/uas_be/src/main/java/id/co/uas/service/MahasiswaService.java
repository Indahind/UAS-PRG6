package id.co.uas.service;

import id.co.uas.DtoResponse;
import id.co.uas.model.Mahasiswa;
import id.co.uas.model.User;

public interface MahasiswaService {
    DtoResponse registerMahasiswa(Mahasiswa user);
    DtoResponse getMahasiswaByNIMAndPassword(String nim, String password);
    DtoResponse getAllUsers();
    DtoResponse getNIMDuplikat(String nim);

    DtoResponse getUserActive();

    DtoResponse saveUser(Mahasiswa user);

    DtoResponse updateUser(Mahasiswa user);

    DtoResponse deleteUser(Mahasiswa user);
    DtoResponse getUserById(Integer userId);
    DtoResponse deleteLayananByusername(String username);

}

