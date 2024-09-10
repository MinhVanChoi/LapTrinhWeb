package van_02.DAO;
import java.util.List;

import van_02.Model.UserModel;

public interface IUserDAO {

	List<UserModel> findall();
	
	UserModel findByID(int id); 
	UserModel findByName(String name);
	UserModel login (String username, String password);
	void insert (UserModel user);

}
