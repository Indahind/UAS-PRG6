package id.co.uas.rest;

import id.co.uas.model.User;
import id.co.uas.DtoResponse;
import id.co.uas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/users"})
@CrossOrigin
public class UserRest {
    @Autowired
    private UserService userService;

    public UserRest(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public DtoResponse getSkemas(@RequestParam("username") String username, @RequestParam("password") String password){
        return userService.getUserByUsernameAndPassword(username, password);
    }

//    @PostMapping("/registerUser")
//    public DtoResponse registerUser(@RequestBody User user){
//        return userService.registerUser(user);
//    }
//
//    @PostMapping("/resetPasswordByEmail")

//    public DtoResponse resetPasswordByEmail(@RequestParam("email") String email){
//        return userService.resetPasswordByEmail(email);
//    }
//
//    @PostMapping("/resetPassword")
//    public DtoResponse resetPasswordById(@RequestParam("oldpassword") String oldPassword, @RequestParam("newpassword") String newPassword){
//        return userService.resetPasswordByTempPassword(oldPassword, newPassword);
//    }

    @GetMapping({"/getUsers"})
    public DtoResponse getUsers() {
        return this.userService.getAllUsers();
    }

    @GetMapping("/getUserById/{userId}")
    public DtoResponse getUserById(@PathVariable Integer userId) {
        return userService.getUserById(userId);
    }

    @GetMapping({"/getUserActive"})
    public DtoResponse getUserActive() {
        return this.userService.getUserActive();
    }

    @PostMapping({"/saveUser"})
    public DtoResponse createUser(@RequestBody User user) {
        return this.userService.saveUser(user);
    }

    @PostMapping({"/updateUser"})
    public DtoResponse updateUser(@RequestBody User user) {
        return this.userService.updateUser(user);
    }

    @PostMapping({"/deleteUser"})
    public DtoResponse deleteUser(@RequestBody User user) {
        return this.userService.deleteUser(user);
    }

    @PostMapping("/deleteUser/{username}")
    public DtoResponse deleteLayanans(@PathVariable String username) {
        return this.userService.deleteLayananByusername(username);
    }
}