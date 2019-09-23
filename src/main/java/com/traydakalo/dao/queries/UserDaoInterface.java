package com.traydakalo.dao.queries;


import com.traydakalo.dao.DaoException;
import com.traydakalo.dto.UserDto;
import com.traydakalo.entity.User;

import java.util.List;

public interface UserDaoInterface {
    // Actions ------------------------------------------------------------------------------------

    /**
     * Returns the user from the database matching the given ID, otherwise null.
     * @param id The ID of the user to be returned.
     * @return The user from the database matching the given ID, otherwise null.
     * @throws DaoException If something fails at database level.
     */
    public UserDto find(Long id) throws DaoException;

    /**
     * Returns the user from the database matching the given email and password, otherwise null.
     * @param email The email of the user to be returned.
     * @return The user from the database matching the given email and password, otherwise null.
     * @throws DaoException If something fails at database level.
     */
    public User find(String email) throws DaoException;

    /**
     * Returns a list of all users from the database ordered by user ID. The list is never null and
     * is empty when the database does not contain any user.
     * @return A list of all users from the database ordered by user ID.
     * @throws DaoException If something fails at database level.
     */
    public List<User> list() throws DaoException;


}
