package dao;

import model.Admin;
import dbconfiguration.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AdminDAO {

    public Optional<Admin> findByUsername(String username) throws SQLException{
        String sql = "SELECT * FROM Admin WHERE Username=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                        Admin admin = new Admin();
                        admin.setAdminId(rs.getInt("admin_id"));
                        admin.setUsername(rs.getString("username"));
                        admin.setPassword(rs.getString("password"));
                        admin.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime()
                        );
                return Optional.of(admin);
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
