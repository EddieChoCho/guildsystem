package com.eddie.mock;

import com.eddie.model.User;
import com.eddie.repository.AbstractUserRepository;

import java.util.ArrayList;
import java.util.List;

public class MockUserRepository implements AbstractUserRepository {

    private Long id = new Long(0);
    private List<User> userList;

    public MockUserRepository(){
        userList = new ArrayList<>();
    }

    @Override
    public User save(User user) {
        id++;
        user.setId(id);
        userList.add(user);
        return user;
    }

    @Override
    public User findOne(Long id) {
        User result = null;
        for(User user : userList){
            if(user.getId().equals(id)){
                result = user;
                break;
            }
        }
        return result;
    }

    @Override
    public List<User> findAllByIdIn(List<Long> ids) {
        List<User> result = new ArrayList<>();
        for(User user : userList){
            for(Long id : ids){
                if(user.getId().equals(id)){
                    result.add(user);
                }
            }
        }
        return result;
    }

    @Override
    public User findOneByEmail(String email) {
        User result = null;
        for(User user : userList){
            if(user.getEmail().equals(email)){
                result = user;
                break;
            }
        }
        return result;
    }

    @Override
    public User findOneByEmailAndPassword(String email, String password) {
        User result = null;
        for(User user : userList){
            if(user.getEmail().equals(email) && user.getPassword().equals(password)){
                result = user;
                break;
            }
        }
        return result;
    }

    @Override
    public void delete(User user) {
        userList.remove(user);
    }
}
