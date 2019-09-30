package com.traydakalo.dao.queries;

import com.traydakalo.dao.DaoException;
import com.traydakalo.dto.ClaimDto;
import com.traydakalo.entity.Claim;

import java.util.List;

/**
 * This interface represents a contract for a Dao for the {@link Claim} model.
 * Note that all methods which returns the {@link Claim} from the DB, will not
 * fill the model with the password, due to security reasons.
 *
 * @author BalusC
 * @link http://balusc.blogspot.com/2008/07/dao-tutorial-data-layer.html
 */
public interface ClaimDaoInterface {

    // Actions ------------------------------------------------------------------------------------

    /**
     * Returns the user from the database matching the given ID, otherwise null.
     * @param id The ID of the user to be returned.
     * @return The user from the database matching the given ID, otherwise null.
     * @throws DaoException If something fails at database level.
     */
     Claim find(Long id) throws DaoException;

    /**
     * Returns the user from the database matching the given email and password, otherwise null.
     * @param email The email of the user to be returned.
     * @param password The password of the user to be returned.
     * @return The user from the database matching the given email and password, otherwise null.
     * @throws DaoException If something fails at database level.
     */
     Claim find(String email, String password) throws DaoException;

    /**
     * Returns a list of all users from the database ordered by user ID. The list is never null and
     * is empty when the database does not contain any user.
     * @return A list of all users from the database ordered by user ID.
     * @throws DaoException If something fails at database level.
     */
     List<Claim> list() throws DaoException;

    /**
     * Create the given user in the database. The user ID must be null, otherwise it will throw
     * IllegalArgumentException. After creating, the Dao will set the obtained ID in the given user.
     * @param user The user to be created in the database.
     * @throws IllegalArgumentException If the user ID is not null.
     * @throws DaoException If something fails at database level.
     */
     void create(Claim user) throws IllegalArgumentException, DaoException;

    /**
     * Update the given user in the database. The user ID must not be null, otherwise it will throw
     * IllegalArgumentException. Note: the password will NOT be updated. Use changePassword() instead.
     * @param user The user to be updated in the database.
     * @throws IllegalArgumentException If the user ID is null.
     * @throws DaoException If something fails at database level.
     */


    /**
     * Delete the given user from the database. After deleting, the Dao will set the ID of the given
     * user to null.
     * @param user The user to be deleted from the database.
     * @throws DaoException If something fails at database level.
     */
     void delete(Claim user) throws DaoException;

    /**
     * Returns true if the given email address exist in the database.
     * @param email The email address which is to be checked in the database.
     * @return True if the given email address exist in the database.
     * @throws DaoException If something fails at database level.
     */
     boolean existEmail(String email) throws DaoException;

    /**
     * Change the password of the given user. The user ID must not be null, otherwise it will throw
     * IllegalArgumentException.
     * @param user The user to change the password for.
     * @throws IllegalArgumentException If the user ID is null.
     * @throws DaoException If something fails at database level.
     */
     void changePassword(Claim user) throws DaoException;

     void saveClaim(String name, String claim, Long id) ;
     void saveClaim(String name, String claim) ;
     List<Claim> findUnmanagedClaims(int limit, int offset);
     Integer getNumberOfUnmanagedClaims ();
     void updateByManager(Claim claim);
}