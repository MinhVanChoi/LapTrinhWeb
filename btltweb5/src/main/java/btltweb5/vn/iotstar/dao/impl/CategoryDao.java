package btltweb5.vn.iotstar.dao.impl;

import java.util.List;

import btltweb5.vn.iotstar.configs.JpaConfig;
import btltweb5.vn.iotstar.dao.ICategoryDao;
import btltweb5.vn.iotstar.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
public class CategoryDao implements ICategoryDao {
	
	@Override
	public void insert(Category category)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(category);
			trans.commit();
		} catch (Exception e)
		{
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}
	
	@Override
	public void update(Category category) 
	{
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(category);
			trans.commit();
		} catch (Exception e)
		{
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}
	
	@Override
	public void delete(int cateid) throws Exception
	{
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			Category category = enma.find(Category.class,cateid);
			if(category != null)
			{
				enma.remove(category);
			} else {
				throw new Exception("Không tìm thấy");
			}		
			trans.commit();
		} catch (Exception e)
		{
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}
	
	@Override
	public Category findById(int cateid)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		Category category = enma.find(Category.class, cateid);
		return category;
	}
	
	@Override
	public List<Category> findAll()
	{
		EntityManager enma = JpaConfig.getEntityManager();
		TypedQuery<Category> query = enma.createNamedQuery("Category.findAll", Category.class);
		return query.getResultList();
	}
	
	@Override
	public List<Category> findByCategoryname(String catname){
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "select c from category c where c.catename like: catname";
		TypedQuery<Category> query = enma.createQuery(jpql, Category.class);
		query.setParameter("catename","%"+ catname + "%");
		return query.getResultList();
	}
	
	@Override
	public List<Category> findAll(int page, int pagesize)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		TypedQuery<Category> query = enma.createNamedQuery("Category.findAll", Category.class);
		query.setFirstResult(page*pagesize);
		query.setMaxResults(pagesize);
		return query.getResultList();
	}
	@Override
	public int count() {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "select count(c) from Category c";
		Query query = enma.createQuery(jpql);
		return ((Long)query.getSingleResult()).intValue();
	}
	
}
