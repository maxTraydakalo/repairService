package com.traydakalo.dao.queries;

import com.traydakalo.dao.DaoException;
import com.traydakalo.dao.DataBaseUtility;
import com.traydakalo.entity.Claim;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClaimDao implements ClaimDaoInterface {

    // Constants ----------------------------------------------------------------------------------

    private static final String SQL_LIST_ORDER_BY_ID =
            "SELECT * FROM claims ORDER BY id";

    private static final String SQL_INSERT_INTO_CLAIMS =
            "insert into claims (name, claim) values (?, ?)";

    private static final String SQL_INSERT_INTO_CLAIMS_WITH_USER_ID =
            "insert into claims (name, claim, user_id) values (?, ?, ?)";

    private static final String SQL_LIST_UNMANAGED_CLAIMS_BY_ID =
            "select * from claims where manager_id IS NULL order by id limit ? offset ?";

    private static final String SQL_NUMBER_OF_UNMANAGED_CLAIMS =
            "select count(id) as number_of_unmanaged_claims from claims where manager_id IS NULL";

    private static final String SQL_FIND_BY_ID = "select * from claims where id = ?";

    private static final String SQL_MANAGER_UPDATE_CLAIM =
            "update claims set manager_id = ?, master_id = ?, price = ?, rejection = ? where id = ?";

    private static final String SQL_CLAIMS_OF_MASTER =
            "select * from claims where master_id = ? order by completed, id limit ? offset ?";

    private static final String SQL_NUMBER_OF_CLAIMS_OF_MASTER =
            "select count(id) as number_of_claims_of_master from claims where master_id = ?";

    private static final String SQL_MASTER_UPDATE_CLAIM =
            "update claims set completed = 1 where id = ?";


    // Vars ---------------------------------------------------------------------------------------

    private BasicDataSource dataSource;
    private static ClaimDao claimDao;

    private ClaimDao() {
        dataSource = DataBaseUtility.getDataSource();
    }

    public static ClaimDao getClaimDao() {
        if (claimDao == null) {
            synchronized (ClaimDao.class) {
                if (claimDao == null) {
                    claimDao = new ClaimDao();
                }
            }
        }
        return claimDao;
    }

    private Claim mapToClaim(ResultSet rs) throws SQLException {
        Claim claim = new Claim();
        claim.setId(rs.getLong("id"));
        claim.setName(rs.getString("name"));
        claim.setClaim(rs.getString("claim"));
        claim.setCompleted(rs.getBoolean("completed"));
        claim.setUserId(rs.getLong("user_id"));
        claim.setManagerId(rs.getLong("manager_id"));
        claim.setMasterId(rs.getLong("master_id"));
        claim.setRejection(rs.getString("rejection"));
        claim.setPrice(rs.getLong("price"));
        return claim;
    }

    @Override
    public List<Claim> list() throws DaoException {
        List<Claim> claims = new ArrayList<>();

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_LIST_ORDER_BY_ID);
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                claims.add(mapToClaim(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return claims;
    }

    public void saveClaim(String name, String claim, long userId) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(SQL_INSERT_INTO_CLAIMS_WITH_USER_ID)
        ) {
            statement.setString(1, name);
            statement.setString(2, claim);
            statement.setLong(3, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Claim> findUnmanagedClaims(int limit, int offset) {
        List<Claim> unmanagedClaims = new ArrayList<>();

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_LIST_UNMANAGED_CLAIMS_BY_ID);

        ) {
            statement.setInt(1, limit);
            statement.setInt(2, offset);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                unmanagedClaims.add(mapToClaim(resultSet));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return unmanagedClaims;
    }


    @Override
    public Integer getNumberOfUnmanagedClaims() {
        Integer numberOfUnmanagedClaims;
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_NUMBER_OF_UNMANAGED_CLAIMS);
                ResultSet resultSet = statement.executeQuery();
        ) {
            resultSet.next();
            numberOfUnmanagedClaims = resultSet.getInt("number_of_unmanaged_claims");
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return numberOfUnmanagedClaims;
    }

    @Override
    public Claim findClaim(Long id) {
        Claim claim;
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID);
        ) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            claim = mapToClaim(resultSet);
            resultSet.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return claim;
    }

    @Override
    public void updateByManager(long managerId, long masterId,
                                long price, String rejection, long claimId) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_MANAGER_UPDATE_CLAIM);
        ) {
            statement.setLong(1, managerId);
            statement.setLong(2, masterId);
            statement.setLong(3, price);
            statement.setString(4, rejection);
            statement.setLong(5, claimId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Claim> findClaimsOfMaster(long id, int limit, int offset) {
        List<Claim> claims = new ArrayList<>();

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_CLAIMS_OF_MASTER);
        ) {
            statement.setLong(1, id);
            statement.setInt(2, limit);
            statement.setInt(3, offset);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                claims.add(mapToClaim(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return claims;
    }

    @Override
    public Integer getNumberOfClaimsOfMaster(long id) {
        Integer numberOfClaimsOfMaster;
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_NUMBER_OF_CLAIMS_OF_MASTER);
        ) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            numberOfClaimsOfMaster = resultSet.getInt("number_of_claims_of_master");
            resultSet.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return numberOfClaimsOfMaster;
    }

    @Override
    public void updateByMaster(long claimId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_MASTER_UPDATE_CLAIM);
        ) {
            statement.setLong(1, claimId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


}
