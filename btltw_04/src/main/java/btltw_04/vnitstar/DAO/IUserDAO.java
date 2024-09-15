package btltw_04.vnitstar.DAO;

import java.util.List;

import btltw_04.vnitstar.Models.UserModel;

public interface IUserDAO {
	List<UserModel> findAll();
	UserModel findById(int id);
	void insert(UserModel user);
	UserModel findByUserName(String username);
	boolean checkExistEmail(String email);
	boolean checkExistUsername(String username);
	boolean checkExistPhone(String phone);
}
