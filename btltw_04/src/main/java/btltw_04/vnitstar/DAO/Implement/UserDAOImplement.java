package btltw_04.vnitstar.DAO.Implement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import btltw_04.vnitstar.DAO.IUserDAO;
import btltw_04.vnitstar.Models.UserModel;
import btltw_04.vnitstar.Configs.DBConnectMySQL;

public class UserDAOImplement implements IUserDAO {
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	@Override
	public List<UserModel> findAll() {
		String query = "select * from users";
		List<UserModel> list = new ArrayList<>();
		try {
			conn = DBConnectMySQL.getDatabaseConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new UserModel(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("user_password"),
						rs.getString("user_email"), rs.getString("user_fullname"), rs.getString("user_avatar"),
						rs.getInt("user_role_id"), rs.getString("user_phone"), rs.getDate("user_datecreate")));
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserModel findById(int id) {
		String query = "SELECT * FROM users where user_id = ? ";
		UserModel user = null;
		try {
			conn = DBConnectMySQL.getDatabaseConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				user = new UserModel(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("user_password"),
						rs.getString("user_email"), rs.getString("user_fullname"), rs.getString("user_avatar"),
						rs.getInt("user_role_id"), rs.getString("user_phone"), rs.getDate("user_datecreate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void insert(UserModel user) {
		List<UserModel> users = findAll();
		String query = "insert into users (user_name, user_password, user_email,"
				+ "user_fullname,user_avatar, user_role_id,user_phone,user_datecreate)" + " values (?,?,?,?,?,?,?,?)";
		for (UserModel existingUser : users) {
			if (existingUser.getUserName().equals(user.getUserName())) {
				break;
			}
		}
		try {
			conn = DBConnectMySQL.getDatabaseConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassWord());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getFullName());
			ps.setString(5, user.getAvatar());
			ps.setInt(6, user.getRoleid());
			ps.setString(7, user.getPhone());
			ps.setDate(8, user.getCreatedDate());
			ps.executeUpdate();
			System.out.println("Đã tạo tài khoản thành công");
			System.out.println(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updatePassword(String email, String newPassword) {
		String query = "update users set user_password = ? where user_email= ?";

		try {
			conn = DBConnectMySQL.getDatabaseConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, newPassword);
			ps.setString(2, email);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	@Override
	public UserModel findByUserName(String username) {
		String query = "SELECT * FROM users where user_name = ? ";
		UserModel user = null;
		try {
			conn = DBConnectMySQL.getDatabaseConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, username);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new UserModel(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("user_password"),
						rs.getString("user_email"), rs.getString("user_fullname"), rs.getString("user_avatar"),
						rs.getInt("user_role_id"), rs.getString("user_phone"), rs.getDate("user_datecreate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public boolean checkExistEmail(String email) {
		boolean duplicate = false;
		String query = "select * from users where user_email = ?";
		try {
			conn = DBConnectMySQL.getDatabaseConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if (rs.next()) {
				duplicate = true;
			}
			ps.close();
			conn.close();
		} catch (Exception ex) {
		}
		return duplicate;
	}

	@Override
	public boolean checkExistUsername(String username) {
		boolean duplicate = false;
		String query = "select * from users where user_name = ?";
		try {
			conn = DBConnectMySQL.getDatabaseConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, username);
			rs = ps.executeQuery();
			if (rs.next()) {
				duplicate = true;
			}
			ps.close();
			conn.close();
		} catch (Exception ex) {
		}
		return duplicate;

	}

	@Override
	public boolean checkExistPhone(String phone) {
		boolean duplicate = false;
		String query = "select * from users where user_phone = ?";
		try {
			conn = DBConnectMySQL.getDatabaseConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, phone);
			rs = ps.executeQuery();
			if (rs.next()) {
				duplicate = true;
			}
			ps.close();
			conn.close();
		} catch (Exception ex) {
		}
		return duplicate;
	}
	

	public void updateProfile(String fullname, String phone, String path_image, String username) {
		String query = "update users set user_fullname = ?, user_phone = ?, user_avatar = ? where user_name = ?";

		try {
			conn = DBConnectMySQL.getDatabaseConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, fullname);
			ps.setString(2, phone);
			ps.setString(3, path_image);
			ps.setString(4, username);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static void main(String[] agrs) {
		IUserDAO test = new UserDAOImplement();
		test.updateProfile("Dang Gia Sang", "12313131", "sang,jsp","sang");
	}

}
