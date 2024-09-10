package van_02.Model;

import java.io.Serializable;

public class UserModel implements Serializable{
	@SuppressWarnings("serial")
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String password;
	private String email;
	private String phone;
	private String image;

	
	public UserModel()
	{
		
	}
	public UserModel(int id, String name,String password, String email, String phone, String image) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.image = image;

	}
	public UserModel( String name,String password, String email, String phone, String image) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.image = image;

	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "UserModel [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", phone="
				+ phone + ", image=" + image + "]";
	}
	
	
	
	
	
	
	
}

