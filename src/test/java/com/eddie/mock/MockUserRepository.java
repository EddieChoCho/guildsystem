package com.eddie.mock;

import com.eddie.model.User;
import com.eddie.repository.AbstractUserRepository;

import java.util.ArrayList;
import java.util.List;

public class MockUserRepository implements AbstractUserRepository {

    private final Long id;
    private Long counter = new Long(0);
    private List<User> userList;

    public MockUserRepository(){
        id = counter;
        userList = new ArrayList<>();
    }

    @Override
    public User save(User user) {
        counter++;
        user.setId(id);
        userList.remove(user);
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
    public void delete(User user) {
        userList.remove(user);
    }
}
