package id.co.uas.service;

import id.co.uas.model.User;
import id.co.uas.DtoResponse;

public interface UserService {
    DtoResponse registerUser(User user);
    DtoResponse getUserByUsernameAndPassword(String username, String password);
    DtoResponse getAllUsers();

    DtoResponse getUserActive();

    DtoResponse saveUser(User user);

    DtoResponse updateUser(User user);

    DtoResponse deleteUser(User user);
    DtoResponse getUserById(Integer userId);
    DtoResponse deleteLayananByusername(String username);

}

