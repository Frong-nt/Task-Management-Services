package com.lachesisss.lists_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Lists {

	@Id
	private String id;
	private String name;
	private boolean closed;
	private String idBoard;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public String getIdBoard() {
		return idBoard;
	}

	public void setIdBoard(String idBoard) {
		this.idBoard = idBoard;
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
