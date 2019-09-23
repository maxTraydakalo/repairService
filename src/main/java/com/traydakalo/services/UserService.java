package com.traydakalo.services;

import com.traydakalo.dao.queries.UserDao;
import com.traydakalo.dto.UserDto;

public class UserService {
    private static UserService userService;
    private UserDao userDao;

    private UserService() {
        userDao = UserDao.getUserDao();
    }

    public static UserService getUserService() {
        if (userService == null) {
            synchronized (UserService.class) {
                if (userService == null) {
                    userService = new UserService();
                }
            }
        }
        return userService;
    }

    //validate->findUser->isCorrect->storeUser
    public UserDto getUser(String login) {
        return new UserDto(userDao.find(login));
    }

    public boolean isCorrect(UserDto userDto, String login, String password) {
        if (userDto.getLogin() == null || userDto.getPassword() == null) {
            return false;
        } else {
            return userDto.getLogin().equals(login) && userDto.getPassword().equals(password);
        }
    }


}
