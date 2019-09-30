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
            "select * from claims where manager_id IS NULL group by id limit ? offset ?";

    private static final String SQL_NUMBER_OF_UNMANAGED_CLAIMS_BY_ID =
            "select count(id) as number_of_unmanaged_claims from claims where manager_id IS NULL";

    private static final String SQL_FIND_BY_ID = "select * from claims where id = ?";

    private static final String SQL_MANAGER_UPDATE_CLAIM = "update claims set manager_id = ?, master_id= ?, price=?, rejection=? WHERE id = ?";



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

    public void saveClaim(String name, String claim, Long id) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(SQL_INSERT_INTO_CLAIMS_WITH_USER_ID)
        ) {
            statement.setString(1, name);
            statement.setString(2, claim);
            statement.setLong(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void saveClaim(String name, String claim) {
        try (
                //can be moved to separate method, maybe should create something like abstract dao
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT_INTO_CLAIMS)
        ) {
            statement.setString(1, name);
            statement.setString(2, claim);
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
                PreparedStatement statement = connection.prepareStatement(SQL_NUMBER_OF_UNMANAGED_CLAIMS_BY_ID);
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
    public Claim find(Long id) {
        Claim claim;
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID);
        ) {
            statement.setLong(1,id);
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
    public void updateByManager(Claim claim) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_MANAGER_UPDATE_CLAIM);
        ) {
            statement.setLong(1, claim.getManagerId());
            statement.setLong(2, claim.getMasterId());
            statement.setLong(3, claim.getPrice());
            statement.setString(4, claim.getRejection());
            statement.setLong(5, claim.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    @Override
    public Claim find(String email, String password) throws DaoException {
        return null;
    }

    @Override
    public void create(Claim user) throws IllegalArgumentException, DaoException {

    }

    @Override
    public void delete(Claim user) throws DaoException {

    }

    @Override
    public boolean existEmail(String email) throws DaoException {
        return false;
    }

    @Override
    public void changePassword(Claim user) throws DaoException {

    }

}
