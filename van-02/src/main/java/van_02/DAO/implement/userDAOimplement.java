package van_02.DAO.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import van_02.Config.DBConnectMySQL;
import van_02.DAO.IUserDAO;
import van_02.Model.UserModel;

public class userDAOimplement extends DBConnectMySQL implements IUserDAO {

    public Connection conn = null;
    public PreparedStatement ps = null;
    public ResultSet rs = null;

    @Override
    public List<UserModel> findall() {
        String query = "select * from user";
        List<UserModel> list = new ArrayList<>();
        try {
            conn = super.getDatabaseConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new UserModel(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("user_password"),
                        rs.getString("user_email"), rs.getString("user_phone"), rs.getString("user_image")));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserModel findByID(int id) {
        String query = "SELECT * FROM user where user_id = ? ";
        UserModel user = null;
        try {
            conn = super.getDatabaseConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                user = new UserModel(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("user_password"),
                        rs.getString("user_email"), rs.getString("user_phone"), rs.getString("user_image"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (user == null)
            System.out.println("Không tìm thấy ID " + id);
        return user;
    }
   
    @Override
    public void insert(UserModel user) {
        List<UserModel> users = findall();
        String query = "INSERT INTO user (user_name, user_password, user_email, user_phone, user_image) VALUES (?, ?, ?, ?, ?)";
        boolean usernameExists = false;
        for (UserModel existingUser : users) {
            if (existingUser.getName().equals(user.getName())) {
                usernameExists = true;
                break;
            }
        }
        if (usernameExists) {
            System.out.println("Username đã được sử dụng. Vui lòng chọn username khác.");
            return;
        }
        try {
            conn = super.getDatabaseConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPhone());
            ps.setString(5, user.getImage());
            ps.executeUpdate();
            System.out.println("Đã tạo tài khoản thành công");
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Lỗi khi tạo tài khoản");
        }
    }
    
    @Override
    public UserModel login(String username, String password) {
        userDAOimplement userDAOI = new userDAOimplement();
    	UserModel user = userDAOI.findByName(username);
    	if (user != null && password.equals(user.getPassword())) {
    	return user;
    	}
    	return null;
    	}
    public UserModel findByName(String name)
    {
    	String query = "SELECT * FROM user where user_name = ? ";
        UserModel user = null;
        try {
            conn = super.getDatabaseConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new UserModel(
                    rs.getInt("user_id"),
                    rs.getString("user_name"),
                    rs.getString("user_password"),
                    rs.getString("user_email"),
                    rs.getString("user_phone"),
                    rs.getString("user_image")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (user == null)
        {
            System.out.println("Không tìm thấy name " + name);
        }
        
        return user;
    }
    

    public static void main(String[] args) {
        userDAOimplement userDAOI = new userDAOimplement();
        Scanner input = new Scanner(System.in);
        System.out.println("Nhập 1: Đăng nhập");
        System.out.println("Nhập 2: Đăng kí");
        System.out.println("Nhập 3: Tìm thông tin qua ID");
        int number = input.nextInt();
        System.out.println(number);
        if (number == 1) {
            System.out.println("******** Đăng nhập ********");
            System.out.println("Hãy nhập thông tin Username");
            String username_1 = input.next();
            input.nextLine();
            System.out.println("Hãy nhập thông tin: Password ");
            String password_1 = input.nextLine();
            UserModel loggedInUser = userDAOI.login(username_1, password_1);
            System.out.println(loggedInUser);
        } else if (number == 2) {
            System.out.println("******** Đăng kí ********");
            input.nextLine();
            System.out.println("Hãy nhập thông tin: Username ");
            String username = input.nextLine();
            System.out.println("Hãy nhập thông tin: Password ");
            String password_2 = input.nextLine();
            System.out.println("Hãy nhập thông tin: Email ");
            String email = input.nextLine();
            System.out.println("Hãy nhập thông tin: Phone ");
            String phone = input.nextLine();
            System.out.println("Hãy nhập thông tin: Image ");
            String image = input.nextLine();
            UserModel user2 = new UserModel(username, password_2, email, phone, image);
            userDAOI.insert(user2);
        } else if (number == 3) {
            System.out.println("******** Tìm ID ********");
            System.out.println("Nhập vào số ID: ");
            int id = input.nextInt();
            UserModel user3 = userDAOI.findByID(id);
            System.out.println(user3);
        }
        input.close();
    }
}
