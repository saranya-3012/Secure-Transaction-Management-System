package dao;

import model.Admin;
import model.Customer;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class AdminDAO {

    // View User Details
    public Optional<Admin> findByUsername(String username) throws Exception {
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
        }
        return Optional.empty();
    }
}
