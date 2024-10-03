package btltw_04.vnitstar.DAO.Implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;

import btltw_04.vnitstar.Configs.DBConnectMySQL;
import btltw_04.vnitstar.DAO.ICategoryDAO;
import btltw_04.vnitstar.Models.CategoryModel;

public class CategoryDAOImplement implements ICategoryDAO {
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	@Override
	public List<CategoryModel> findAll() {
		String query = "select * from categories";
		List<CategoryModel> list = new ArrayList<>();
		try {
			conn = new DBConnectMySQL().getDatabaseConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next())
			{
				CategoryModel category = new CategoryModel();
				category.setCategoryid(rs.getInt("category_id"));
				category.setCategoryname(rs.getString("category_name"));
				category.setImages(rs.getString("category_images"));
				category.setStatus(rs.getInt("category_status"));
				list.add(category);
			}
			conn.close();
			ps.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	@Override
	public CategoryModel findById(int id) {
			String query = "select * from categories where category_id = ?";
			try {
				conn = new DBConnectMySQL().getDatabaseConnection();
				ps = conn.prepareStatement(query);
				ps.setInt(1,id);
				rs = ps.executeQuery();
				CategoryModel category = new CategoryModel();
				while (rs.next())
				{
					
					category.setCategoryid(rs.getInt("category_id"));
					category.setCategoryname(rs.getString("category_name"));
					category.setImages(rs.getString("category_images"));
					category.setStatus(rs.getInt("category_status"));
					
				}
				conn.close();
				ps.close();
				rs.close();
				return category;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;

		}
	@Override
	public void insert(CategoryModel category) {
		
		String query = "INSERT INTO categories (category_name,category_images, category_status) VALUES ( ?, ? , ?)";
		try {
			conn = new DBConnectMySQL().getDatabaseConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1,category.getCategoryname());
			ps.setString(2,category.getImages());
			ps.setInt(3,category.getStatus());
			
			ps.executeUpdate();
			conn.close();
			ps.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void update(CategoryModel category) {

		String query = "UPDATE categories SET category_name = ?, category_images = ?, category_status = ? WHERE category_id = ?";
		try {
			conn = new DBConnectMySQL().getDatabaseConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1,category.getCategoryname());
			ps.setString(2,category.getImages());
			ps.setInt(3,category.getStatus());
			ps.setInt(4,category.getCategoryid());
			ps.executeUpdate();
			conn.close();
			ps.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
	}

	@Override
	public void delete(int id) {
		String query = "DELETE FROM categories WHERE category_id = ?";;
		try {
			conn = new DBConnectMySQL().getDatabaseConnection();
			ps = conn.prepareStatement(query);
			ps.setInt(1,id);
			ps.executeUpdate();
			conn.close();
			ps.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		
	}

	@Override
	public List<CategoryModel> findName(String keyword) {
		String query = "select * from categories where category_name = ?";
		List<CategoryModel> list = new ArrayList<>();
		try {
			conn = new DBConnectMySQL().getDatabaseConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1,keyword);
			rs = ps.executeQuery();
			CategoryModel category = new CategoryModel();

			while (rs.next())
			{
				category.setCategoryid(rs.getInt("category_id"));
				category.setCategoryname(rs.getString("category_name"));
				category.setImages(rs.getString("category_images"));
				category.setStatus(rs.getInt("category_status"));
				list.add(category);
			}
			conn.close();
			ps.close();
			rs.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void softDelete(CategoryModel category) {
		String query = "UPDATE categories SET category_status = ? WHERE category_id = ?";;
		try {
			conn = new DBConnectMySQL().getDatabaseConnection();
			ps.setInt(1,category.getStatus());
			ps.setInt(2, category.getCategoryid());
			ps.executeUpdate();
			conn.close();
			ps.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
	}
	

}
