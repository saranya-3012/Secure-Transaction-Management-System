
package service;

import java.util.*;

public class Authorization {

	// Map(Role, Permissions)
    private static final Map<String, List<String>> Permissions = new HashMap<>();

    // Roles and their Permissions
    static {
        Permissions.put("CUSTOMER", List.of("VIEW_BALANCE"));    // Immutable List
        Permissions.put("TELLER", List.of("DEPOSIT"));
        Permissions.put("MANAGER", List.of("APPROVE_LOAN"));
        Permissions.put("ADMIN", List.of("VIEW_BALANCE", "DEPOSIT", "APPROVE_LOAN", "AUDIT"));
    }

    // Find the user have given Permission or not
    public static boolean hasPermission(String role, String permission) {
    	List<String> permit = Permissions.get(role);

    	if (permit.contains(permission)) {
    	    return true;
    	} 
    	else {
    	    return false;
    	}

    }
}
