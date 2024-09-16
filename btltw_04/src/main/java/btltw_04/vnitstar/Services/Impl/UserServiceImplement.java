package btltw_04.vnitstar.Services.Impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;

import btltw_04.vnitstar.DAO.IUserDAO;
import btltw_04.vnitstar.DAO.Implement.UserDAOImplement;
import btltw_04.vnitstar.Models.UserModel;
import btltw_04.vnitstar.Services.IUserService;

public class UserServiceImplement implements IUserService{

	IUserDAO userDAO = new UserDAOImplement();
	@Override
	public UserModel login(String username, String password) {
		UserModel user = this.FindByUserName(username);
		if (user != null && password .equals(user.getPassWord())) {
			return user;
		}
		return null;
	}

	@Override
	public UserModel FindByUserName(String username) {
		return userDAO.findByUserName(username);
	}
	
	

	@Override
	public boolean register(String username, String password, String email, String fullname, String phone) {
			if (userDAO.checkExistUsername(username)) {
			return false;
			}
			long millis=System.currentTimeMillis();
			java.sql.Date date=new java.sql.Date(millis);
			userDAO.insert(new UserModel(username, password, email ,fullname, null, 4 ,phone,Date.valueOf(LocalDate.now())));
			return true;
	}
	
		@Override
		public boolean checkExistEmail(String email) {
		return userDAO.checkExistEmail(email);
		}
		public boolean checkExistUsername(String username) {
		return userDAO.checkExistUsername(username);
		}
		@Override
		public boolean checkExistPhone(String phone) {
		return userDAO.checkExistPhone(phone);
		}
		
		@Override
		public void insert(UserModel user) {
			userDAO.insert(user);
		}
		
		public void updatePassword(String email, String NewPassword)
		{
			userDAO.updatePassword(email, NewPassword);
		}
	
	public static void main(String[] agrs)
	{
		IUserService test = new UserServiceImplement();
		test.register("dat", "1", "dat@", "NTD", "113");
	}

	
}
