package com.ib.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.ib.models.BaseEntity;
import com.ib.models.Location;

@Repository
public class WeatherDataDao {

	public Location findLocationByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public <T> T find(Class<T> type, String id)
	{
		return getEntityManager().find(type, id);
	}
	
	public <T extends BaseEntity> T save(T entity) {
		if (entity.isNew())
		{
			getEntityManager().persist(entity);
			return entity;
		}
		else
		{
			return getEntityManager().merge(entity);
		}
	}

	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	
}
