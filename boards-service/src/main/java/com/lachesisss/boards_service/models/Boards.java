package com.lachesisss.boards_service.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Boards {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private String name;
	private String description;
	private boolean closed;
	private String idOrganization;
	private boolean starred;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public String getIdOrganization() {
		return idOrganization;
	}

	public void setIdOrganization(String idOrganization) {
		this.idOrganization = idOrganization;
	}

	public boolean isStarred() {
		return starred;
	}

	public void setStarred(boolean starred) {
		this.starred = starred;
	}
}
