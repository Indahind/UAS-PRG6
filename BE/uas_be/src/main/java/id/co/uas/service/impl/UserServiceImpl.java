package id.co.uas.service.impl;

import id.co.uas.dao.UserDao;
import id.co.uas.model.User;
import id.co.uas.repository.UserRepository;
import id.co.uas.DtoResponse;
import id.co.uas.service.UserService;
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
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserDao userDao, UserRepository userRepository) {
        this.userDao = userDao;
        this.userRepository = userRepository;
    }

    public DtoResponse registerUser(User user){
        try {
            String existingEmail = checkUsername(user.getUsername());
            if(user.getUsername().equals(existingEmail)){
                return new DtoResponse(409, existingEmail, "Email Sudah di Gunakan");
            } else {
                User newData = new User();
                newData.setNama(user.getNama());
                newData.setPassword(user.getPassword());
                newData.setJabatan(user.getJabatan());
                userRepository.save(newData);
                return new DtoResponse(200,user,"Sukses Membuat Data");
            }
        }catch (Exception e){
            return new DtoResponse(500,user,"Terjadi Kesalahan saat menambah data " + e.getMessage());
        }
    }

    public DtoResponse getUserByUsernameAndPassword(String username, String password){
        User user = userRepository.findUserByUsernameAndPassword(username, password);
        if(user != null){
            UserVo loginVo = new UserVo(user);
            return new DtoResponse(200, loginVo, "Sukses");
        }else {
            return new DtoResponse(404, null, "Data User tidak di temukan");
        }
    }

    public String checkUsername(String submittedEmail){
        return userRepository.getExistingUsername(submittedEmail);
    }

    public DtoResponse getAllUsers() {
        return this.userDao.getAllUsers() != null ? new DtoResponse(200, this.userDao.getAllUsers()) : new DtoResponse(200, (Object)null, "No data available");
    }

    public DtoResponse getUserActive() {
        return this.userDao.getUserActive() != null ? new DtoResponse(200, this.userDao.getUserActive()) : new DtoResponse(200, (Object)null, "No data available");
    }

    public DtoResponse getUserByUsername(String username) {
        List<UserVo> users = Collections.singletonList(this.userDao.getUserByUsername(username));
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


    public DtoResponse saveUser(User user) {
        try {
            this.userRepository.save(user);
            return new DtoResponse(200, user, "User created successfully");
        } catch (Exception var3) {
            return new DtoResponse(500, user, "Failed to create Uaser");
        }
    }

    public DtoResponse updateUser(User user) {
        try {
            User existingUser = this.userRepository.findUserByUsername(user.getUsername());

            if (existingUser != null) {
                existingUser.setPassword(user.getPassword());
                existingUser.setNama(user.getNama());
                existingUser.setJabatan(user.getJabatan());

                User updatedUser = this.userRepository.save(existingUser);
                return new DtoResponse(200, updatedUser, "User updated successfully");
            } else {
                return new DtoResponse(404, null, "User not found");
            }
        } catch (Exception e) {
            return new DtoResponse(500, user, "Failed to update User");
        }
    }


    public DtoResponse deleteUser(User user) {
        User userData = this.userRepository.findUserByUsername(user.getUsername());

        if (userData != null) {
            try {
                System.out.println(userData.getUsername()); // Cetak username setelah memeriksa null
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
    public DtoResponse deleteLayananByusername(String username) {
        Optional<User> layananOptional =  this.userRepository.findUserByUsernameDel(username);

        if (layananOptional.isPresent()) {
            User userData = layananOptional.get();
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
