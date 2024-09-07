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
		List<UserModel> list = new ArrayList<UserModel>();
		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new UserModel(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("user_password"),
						rs.getString("user_email"), rs.getString("user_phone"),rs.getString("user_image")));
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	

	@Override
	public UserModel findByID(int id) {
		// TODO Auto-generated method stub
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
			
		}
		if (user == null)
			System.out.println("Không tìm thấy ID "+ id);
		return user;
	}
	
	

	@Override
	public void insert(UserModel user) {
		// TODO Auto-generated method stub
		List<UserModel> users = findall();
	    String query = "INSERT INTO user (user_name, user_password, user_email, user_phone, user_image) VALUES ( ?, ?, ?, ?, ?)";
	    boolean emailExists = false;
	    for (UserModel existingUser : users) {
	        if (existingUser.getEmail().equals(user.getEmail())) {
	            emailExists = true;
	            break; 
	        }
	    }
	    if (emailExists) {
	        System.out.println("Email đã được sử dụng. Vui lòng chọn email khác.");
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
			// TODO: handle exception
			e.printStackTrace();
			System.err.println("Lỗi khi tạo tài khoản");
		}
	}
	
	public void sign_in(String email, String password) {
		List<UserModel> users = findall();
	    boolean loginSuccess = false;
	    for (UserModel user : users) {
	       
	        if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
	            System.out.println("Đăng nhập thành công!");
	            System.out.println("********Thông tin người dùng********");
	            System.out.println("ID: " + user.getId());
	            System.out.println("Name: " + user.getName());
	            System.out.println("Email: " + user.getEmail());
	            System.out.println("Phone: " + user.getPhone());
	            System.out.println("Image: " + user.getImage());
	            loginSuccess = true;
	            break;
	        }
	    }
	    if (!loginSuccess) {
	        System.out.println("Email hoặc mật khẩu không đúng. Vui lòng thử lại.");
	    }
	}
		
	
	
	public static void main(String[] args) {
		
		userDAOimplement userDAOI = new userDAOimplement();
		Scanner input = new Scanner(System.in);
		System.out.println("Nhập 1: Đăng nhập");
		System.out.println("Nhập 2: Đăng kí");
		System.out.println("Nhập 3: Tìm thông tin qua ID");
		List<UserModel> list = userDAOI.findall();
		int number = input.nextInt();
		System.out.println(number);
			if (number == 1)
			{ 
				System.out.println("******** Đăng nhập ********");
				System.out.println("Hãy nhập thông tin Email");
		        String email_1 = input.next();
		        input.nextLine();
		        System.out.println("Hãy nhập thông tin: Password ");
		        String password_1 = input.nextLine();
		        userDAOI.sign_in(email_1,password_1);
				input.close();
			}
			else if (number == 2)
			{
				System.out.println("******** Đăng kí ********");
				input.nextLine();
				System.out.println("Hãy nhập thông tin: Name ");
		        String name = input.nextLine();
		        System.out.println("Hãy nhập thông tin: Password ");
		        String password_2 = input.nextLine();
		        System.out.println("Hãy nhập thông tin: Email ");
		        String email_2 = input.nextLine();
		        System.out.println("Hãy nhập thông tin: Phone ");
		        String phone = input.nextLine();
		        System.out.println("Hãy nhập thông tin: Image ");
		        String image = input.nextLine();
		        input.close();
	            UserModel user2 = new UserModel(name, password_2, email_2, phone, image);
	            userDAOI.insert(user2);
			}
			else if (number == 3)
			{
				System.out.println("******** Tìm ID ********");
				System.out.println("Nhập vào số ID: ");
				int id = input.nextInt();
				System.out.println(id);	
				UserModel user3 = userDAOI.findByID(id);
				System.out.println(user3);
			}	
	}

}
