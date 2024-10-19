package btltweb5.vn.iotstar.dao.impl;

import java.util.List;

import btltweb5.vn.iotstar.configs.JpaConfig;
import btltweb5.vn.iotstar.dao.IVideoDao;
import btltweb5.vn.iotstar.entity.Video;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class VideoDao implements IVideoDao{

	@Override
	public int count() {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "select count(v) from Video v";
		Query query = enma.createQuery(jpql);
		return ((Long)query.getSingleResult()).intValue();
	}

	@Override
	public List<Video> findAll(int page, int pagesize) {
		EntityManager enma = JpaConfig.getEntityManager();
		TypedQuery<Video> query = enma.createNamedQuery("Video.findAll", Video.class);
		query.setFirstResult(page*pagesize);
		query.setMaxResults(pagesize);
		return query.getResultList();
	}

	@Override
	public List<Video> findByVideoname(String vidname) {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "select v from Video v where v.title like: vidname";
		TypedQuery<Video> query = enma.createQuery(jpql, Video.class);
		query.setParameter("vidname","%"+ vidname + "%");
		return query.getResultList();
	}

	@Override
	public List<Video> findAll() {
		EntityManager enma = JpaConfig.getEntityManager();
		TypedQuery<Video> query = enma.createNamedQuery("Video.findAll", Video.class);
		return query.getResultList();
	}

	@Override
	public Video findById(String videoid) {
		EntityManager enma = JpaConfig.getEntityManager();
		Video video = enma.find(Video.class, videoid);
		return video;
	}

	@Override
	public void delete(String videoid) throws Exception {
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			Video video = enma.find(Video.class,videoid);
			if(video != null)
			{
				enma.remove(video);
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
	public void update(Video video) {
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(video);
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
	public void insert(Video video) {
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(video);
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

}