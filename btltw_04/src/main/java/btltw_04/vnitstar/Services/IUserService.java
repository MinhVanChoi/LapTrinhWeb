package btltw_04.vnitstar.Services;

import btltw_04.vnitstar.Models.UserModel;

public interface IUserService {
	UserModel login(String username, String password);

	UserModel FindByUserName(String username);

	void insert(UserModel user);

	boolean register(String username, String password, String email);

	boolean checkExistEmail(String email);

	boolean checkExistUsername(String username);

	boolean checkExistPhone(String phone);

	boolean updatePassword(String email, String NewPassword);
	
	boolean updateProfile(String fullname, String phone, String images, String username);

}
