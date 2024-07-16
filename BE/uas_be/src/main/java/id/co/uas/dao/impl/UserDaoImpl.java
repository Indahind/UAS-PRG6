package id.co.uas.dao.impl;

import id.co.uas.dao.UserDao;
import id.co.uas.model.User;
import id.co.uas.repository.UserRepository;
import id.co.uas.vo.UserVo;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserRepository userRepository;

    public UserDaoImpl() {
    }


    @Autowired
    private EntityManager entityManager;

    public List<UserVo> getAllUsers() {
        Iterable<User> users = this.userRepository.findAll();
        List<UserVo> userVos = new ArrayList();
        Iterator var3 = users.iterator();

        while(var3.hasNext()) {
            User item = (User)var3.next();
            UserVo userVo = new UserVo(item);
            userVos.add(userVo);
        }

        return userVos;
    }


    public List<UserVo> getUserActive() {
        Iterable<User> users = this.userRepository.findByStatus(1);
        List<UserVo> userVos = new ArrayList();
        Iterator var3 = users.iterator();

        while(var3.hasNext()) {
            User item = (User)var3.next();
            UserVo userVo = new UserVo(item);
            userVos.add(userVo);
        }

        return userVos;
    }

    @Override
    public UserVo getUserByUsername(String usrename) {
        List<Object[]> results = userRepository.getUserByUsername(usrename);
        UserVo userVo = null;

        for (Object[] result : results) {
            String username = (String) result[0];
            String password = (String) result[1];
            String nama = (String) result[2];
            String jabatan = (String) result[3];

            userVo = new UserVo(username, password, nama, jabatan);
        }

        return userVo;
    }

}
