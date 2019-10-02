package com.traydakalo.dao.queries;


import com.traydakalo.dao.DaoException;
import com.traydakalo.dto.UserDto;
import com.traydakalo.entity.User;

import java.util.List;

public interface UserDaoInterface {
    // Actions ------------------------------------------------------------------------------------

     UserDto find(Long id) throws DaoException;

     User find(String email) throws DaoException;

     List<User> list() throws DaoException;

     void register(String login, String password);

     void addRole(String login, String role);
}
