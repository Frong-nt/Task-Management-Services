package com.lachesisss.checklists_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CheckItems {

	@Id
	private String id;
	private String name;
	private boolean state;

	private String idChecklist;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getIdChecklist() {
		return idChecklist;
	}

	public void setIdChecklist(String idChecklist) {
		this.idChecklist = idChecklist;
	}

	public String getId() {
		return id;
	}

}
