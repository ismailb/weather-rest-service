package com.ib.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.ib.exception.ApplicationException;
import com.ib.models.BaseEntity;
import com.ib.models.Location;
import com.ib.models.WeatherCondition;

@Repository
public class WeatherDataDao {

	public Location findLocationByName(String name) {

		if (StringUtils.isBlank(name)) {
			return null;
		}

		Location location = null;

		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ");
		jpql.append(Location.class.getSimpleName());
		jpql.append(" loc WHERE ");
		jpql.append(" UPPER(loc.name) = :name ");

		Query query = getEntityManager().createQuery(jpql.toString(), Location.class);
		query.setParameter("name", name.toUpperCase());

		try {
			location = (Location) query.getSingleResult();
		} catch (NoResultException e) {
			// do nothing
		} catch (Exception e) {
			throw new ApplicationException("Error in geting location %s", name);
		}
		return location;
	}

	public WeatherCondition findWeatherConditionByCode(Class<WeatherCondition> class1, String code) {
		if (StringUtils.isBlank(code)) {
			return null;
		}

		WeatherCondition weatherCondition = null;

		StringBuilder jpql = new StringBuilder();
		jpql.append(" FROM ");
		jpql.append(WeatherCondition.class.getSimpleName());
		jpql.append(" wc WHERE ");
		jpql.append(" wc.code = :code ");

		Query query = getEntityManager().createQuery(jpql.toString(), WeatherCondition.class);
		query.setParameter("code", code);

		try {
			weatherCondition = (WeatherCondition) query.getSingleResult();
		} catch (NoResultException e) {
			// do nothing
		} catch (Exception e) {
			throw new ApplicationException("Error in geting weather condition %s", code);
		}
		return weatherCondition;

	}

	public <T> T find(Class<T> type, String id) {
		return getEntityManager().find(type, id);
	}

	public <T extends BaseEntity> T save(T entity) {
		if (entity.isNew()) {
			getEntityManager().persist(entity);
			return entity;
		} else {
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
