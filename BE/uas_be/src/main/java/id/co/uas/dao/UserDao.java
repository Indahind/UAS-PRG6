package id.co.uas.dao;

import id.co.uas.vo.UserVo;

import java.util.List;

public interface UserDao {

    List<UserVo> getAllUsers();
    List<UserVo> getUserActive();
    UserVo getUserByUsername(String nim);

}