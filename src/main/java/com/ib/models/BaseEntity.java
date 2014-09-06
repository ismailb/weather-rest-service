package com.ib.models;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDateTime;

/**
 * Base entity contains the audit fields and basic methods needed for any application entity
 * 
 * @author ishmael
 *
 */
@MappedSuperclass
public abstract class BaseEntity {

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CRTE_DATE")
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDT_DATE")
	private Date updatedAt;

	@Version
	@Column(name = "VERSION")
	private Integer version = 1;

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@PrePersist
	@PreUpdate
	void createAndUpdateAtCallback() {

		Date now = LocalDateTime.now().toDate();

		setUpdatedAt(now);
		if (isNew()) {
			setPrimaryId(UUID.randomUUID().toString());
			setCreatedAt(now);
		}
	}

	@Transient
	public abstract String getPrimaryId();

	public abstract void setPrimaryId(String primaryId);

	@Transient
	public boolean isNew() {
		return StringUtils.isBlank(getPrimaryId());
	}

}
