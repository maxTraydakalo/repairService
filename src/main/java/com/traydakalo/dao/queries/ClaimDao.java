package com.traydakalo.dao.queries;

import com.traydakalo.dao.DaoException;
import com.traydakalo.dao.DataBaseUtility;
import com.traydakalo.dto.ClaimDto;
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
            "insert into claims (name, claim) values (?, ?,)";

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
        claim.setUserAccountId(rs.getLong("user_account_id"));
        claim.setManagerAccountId(rs.getLong("manager_id"));
        claim.setMasterAccountId(rs.getLong("master_id"));
        return claim;
    }

    @Override
    public List<Claim> list() throws DaoException {
        List<Claim> claims = new ArrayList<>();

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_LIST_ORDER_BY_ID);
                ResultSet resultSet = statement.executeQuery();
        ) {
            while (resultSet.next()) {
                claims.add(mapToClaim(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return claims;
    }

    public void saveClaim(Claim claim) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT_INTO_CLAIMS);
        ) {
            statement.setString(1, claim.getName());
            statement.setString(2, claim.getClaim());
            statement.executeQuery();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }




    @Override
    public Claim find(Long id) throws DaoException {
        return null;
    }

    @Override
    public Claim find(String email, String password) throws DaoException {
        return null;
    }

    @Override
    public void create(Claim user) throws IllegalArgumentException, DaoException {

    }

    @Override
    public void update(Claim user) throws IllegalArgumentException, DaoException {

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
