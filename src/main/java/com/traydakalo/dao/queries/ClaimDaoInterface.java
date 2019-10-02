package com.traydakalo.dao.queries;

import com.traydakalo.dao.DaoException;
import com.traydakalo.dto.ClaimDto;
import com.traydakalo.entity.Claim;

import java.util.List;

/**
 * This interface represents a contract for a Dao for the {@link Claim} model.
 * Note that all methods which returns the {@link Claim} from the DB
 */
public interface ClaimDaoInterface {

    // Actions ------------------------------------------------------------------------------------

    /**
     * Returns the claim from the database matching the given ID, otherwise null.
     *
     * @param id The ID of the claim to be returned.
     * @return The claim from the database matching the given ID, otherwise null.
     * @throws DaoException If something fails at database level.
     */
    Claim findClaim(Long id) throws DaoException;

    /**
     * Returns a list of all claims from the database ordered by claim ID. The list is never null and
     * is empty when the database does not contain any user.
     *
     * @return A list of all claims from the database ordered by user ID.
     * @throws DaoException If something fails at database level.
     */
    List<Claim> list() throws DaoException;

    void saveClaim(String name, String claim, long userId);

    List<Claim> findUnmanagedClaims(int limit, int offset);

    Integer getNumberOfUnmanagedClaims();

    void updateByManager(long managerId, long masterId, long price, String rejection, long id);

    List<Claim> findClaimsOfMaster(long id, int limit, int offset);

    Integer getNumberOfClaimsOfMaster(long id);

    void updateByMaster(long claimId);
}