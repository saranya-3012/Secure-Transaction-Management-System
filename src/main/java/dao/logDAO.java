package dao;

import java.sql.*;

import util.DBConnection;

public class logDAO {
	public void logAction(String action) throws Exception {
		
		Connection conn = DBConnection.getConnection();

	    String sql = "INSERT INTO logs(action) VALUES(?)";

	    PreparedStatement ps = conn.prepareStatement(sql);
	    ps.setString(1, action);
	    ps.executeUpdate();
	}

}
