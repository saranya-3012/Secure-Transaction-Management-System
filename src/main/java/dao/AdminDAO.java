package dao;

import model.Admin;
import util.DBConnection;

import java.sql.*;
import java.util.Optional;

public class AdminDAO {

    // View Admin Details
    public Optional<Admin> findByUsername(String username) throws Exception {
        String sql = "SELECT * FROM Admin WHERE username=?";

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
        }
        return Optional.empty();
    }
}