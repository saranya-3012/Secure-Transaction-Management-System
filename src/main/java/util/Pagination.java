package util;

import model.*;
import java.util.*;
import java.sql.*;

public class Pagination {
	
	public List<Transaction> getTransactions(int accountId, int page, int size) throws Exception {
		
		Connection conn = DBConnection.getConnection();

	    int offset = (page - 1) * size;

	    String sql = "SELECT * FROM transactions WHERE from_account=? OR to_account=? ORDER BY date DESC LIMIT ? OFFSET ?";

	    PreparedStatement ps = conn.prepareStatement(sql);
	    ps.setInt(1, accountId);
	    ps.setInt(2, accountId);
	    ps.setInt(3, size);
	    ps.setInt(4, offset);

	    ResultSet rs = ps.executeQuery();
	    
	    List<Transaction> list = new ArrayList<>();

        while (rs.next()) {
            Transaction ts = new Transaction();
            ts.settxnid(rs.getString("user_id"));
            ts.setsender(rs.getString("sender"));
            ts.setreceiver(rs.getString("receiver"));
            ts.setamount(rs.getString("amount"));
            ts.settype(rs.getString("type"));
            ts.setdate(rs.getString("date"));
            
            list.add(ts);
        }
        return list;
	}

}
