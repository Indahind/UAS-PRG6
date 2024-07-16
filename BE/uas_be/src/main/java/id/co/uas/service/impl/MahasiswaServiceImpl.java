package id.co.uas.service.impl;

import id.co.uas.DtoResponse;
import id.co.uas.dao.MahasiswaDao;
import id.co.uas.dao.UserDao;
import id.co.uas.model.Mahasiswa;
import id.co.uas.model.User;
import id.co.uas.repository.MahasiswaRepository;
import id.co.uas.repository.UserRepository;
import id.co.uas.service.MahasiswaService;
import id.co.uas.service.UserService;
import id.co.uas.vo.MahasiswaVo;
import id.co.uas.vo.UserVo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static id.co.uas.constant.LayananConstantInd068.*;


@Service
@Transactional
public class MahasiswaServiceImpl implements MahasiswaService {
    @Autowired
    private MahasiswaDao userDao;
    @Autowired
    private MahasiswaRepository userRepository;

    public MahasiswaServiceImpl(MahasiswaDao userDao, MahasiswaRepository userRepository) {
        this.userDao = userDao;
        this.userRepository = userRepository;
    }

    public DtoResponse registerMahasiswa(Mahasiswa user){
        try {
            String existingEmail = checkUsername(user.getNim());
            if(user.getNim().equals(existingEmail)){
                return new DtoResponse(409, existingEmail, "Error Duplikasi NIM");
            } else {
                Mahasiswa newData = new Mahasiswa();
                newData.setNama(user.getNama());
                newData.setPassword(user.getPassword());
                newData.setProdi(user.getProdi());
                userRepository.save(newData);
                return new DtoResponse(200,user,"Sukses Membuat Data");
            }
        }catch (Exception e){
            return new DtoResponse(500,user,"Terjadi Kesalahan saat menambah data " + e.getMessage());
        }
    }

    public DtoResponse getMahasiswaByNIMAndPassword(String nim, String password){
        Mahasiswa user = userRepository.findUserByUsernameAndPassword(nim, password);
        if(user != null){
            MahasiswaVo loginVo = new MahasiswaVo(user);
            return new DtoResponse(200, loginVo, "Sukses");
        }else {
            return new DtoResponse(404, null, "Data User tidak di temukan");
        }
    }

    public String checkUsername(String submittedEmail){
        return userRepository.getExistingUsername(submittedEmail);
    }

    public DtoResponse getAllUsers() {
        return this.userDao.getAllMahasiswas() != null ? new DtoResponse(200, this.userDao.getAllMahasiswas()) : new DtoResponse(200, (Object)null, "No data available");
    }

    @Override
    public DtoResponse getNIMDuplikat(String nim) {
        List<MahasiswaVo> users = Collections.singletonList(this.userDao.getMahasiswaByNIM(nim));
        if (users != null && !users.isEmpty()) {
            return new DtoResponse(200, users, "Error NIM Duplikasi.");
        } else {
            return new DtoResponse(200, null, "No data available.");
        }
    }

    public DtoResponse getUserActive() {
        return this.userDao.getMahasiswaActive() != null ? new DtoResponse(200, this.userDao.getMahasiswaActive()) : new DtoResponse(200, (Object)null, "No data available");
    }

    public DtoResponse getUserByUsername(String username) {
        List<MahasiswaVo> users = Collections.singletonList(this.userDao.getMahasiswaByNIM(username));
        if (users != null && !users.isEmpty()) {
            return new DtoResponse(200, users, "successfully get user.");
        } else {
            return new DtoResponse(200, null, "No data available.");
        }
    }

//    public DtoResponse saveUser(User user) {
//        try {
//            boolean userExists = this.userDao.findByUsernameOrEmail(user.getUsr_username(), user.getUsr_email());
//            if (userExists) {
//                return new DtoResponse(500, user, "Failed to create User, Username or Email was used");
//            } else {
//                String encryptedPassword = encodePasswordMD5(user.getUsr_password());
//                user.setUsr_password(encryptedPassword);
//                this.userRepository.save(user);
//                return new DtoResponse(200, user, "User created successfully");
//            }
//        } catch (Exception e) {
//            return new DtoResponse(500, user, "Failed to create User due to an internal error");
//        }
//    }


    public DtoResponse saveUser(Mahasiswa user) {
        try {
            this.userRepository.save(user);
            return new DtoResponse(200, user, "User created successfully");
        } catch (Exception var3) {
            return new DtoResponse(500, user, "Failed to create Uaser");
        }
    }

    public DtoResponse updateUser(Mahasiswa user) {
        try {
            Mahasiswa existingUser = this.userRepository.findUserByUsername(user.getNim());

            if (existingUser != null) {
                existingUser.setPassword(user.getPassword());
                existingUser.setNama(user.getNama());
                existingUser.setProdi(user.getProdi());

                Mahasiswa updatedUser = this.userRepository.save(existingUser);
                return new DtoResponse(200, updatedUser, "User updated successfully");
            } else {
                return new DtoResponse(404, null, "User not found");
            }
        } catch (Exception e) {
            return new DtoResponse(500, user, "Failed to update User");
        }
    }


    public DtoResponse deleteUser(Mahasiswa user) {
        Mahasiswa userData = this.userRepository.findUserByUsername(user.getNim());

        if (userData != null) {
            try {
                System.out.println(userData.getNim()); // Cetak username setelah memeriksa null
                this.userRepository.delete(user);
                return new DtoResponse(200, userData, "User deleted successfully");
            } catch (Exception var4) {
                return new DtoResponse(500, userData, "Failed to delete User");
            }
        } else {
            return new DtoResponse(404, null, "User not found");
        }
    }

    @Override
    public DtoResponse getUserById(Integer userId) {
        return null;
    }

    @Override
    public DtoResponse deleteLayananByusername(String nim) {
        Optional<Mahasiswa> layananOptional =  this.userRepository.findUserByUsernameDel(nim);

        if (layananOptional.isPresent()) {
            Mahasiswa userData = layananOptional.get();
            try {
                userRepository.delete(userData);
                return new DtoResponse(200, userData, mDeleteSuccess);
            } catch (Exception e) {
                return new DtoResponse(500, null, mDeleteFailed);
            }
        }
        return new DtoResponse(404, null, mNotFound);
    }
}
