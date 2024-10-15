package btltweb5.vn.iotstar.configs;

import btltweb5.vn.iotstar.entity.Category;
import btltweb5.vn.iotstar.entity.Video;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class Test {
	public static void main(String[] args)
	{
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
        Category cate = enma.find(Category.class, 1);
		Video vi = new Video();
		
		// vi
		vi.setTitle("Van2");
		vi.setViews(0);
		vi.setActive(true);
		vi.setCategory(cate);
		vi.setDescription("test1");
		vi.setPoster("https://cdn.pixabay.com/video/2021/08/10/84574-586228759_large.mp4");
		vi.setVideoId("vi2");
		try {
			trans.begin();
			enma.persist(vi);
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
