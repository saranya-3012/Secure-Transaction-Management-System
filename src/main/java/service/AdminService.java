package service;

import model.Admin;

public class AdminService {

    private final AdminDAO adminDAO = new AdminDAO();

    public void createAdmin(Admin admin) throws Exception {
        adminDAO.register(admin);
    }
}