package com.lachesisss.tasks_service.model;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Cards {

	@Id
	private String id;
	private String name;
	private String description;
//	private Badges badges;
	private ArrayList<?> checkItemStates;
	private boolean closed;
	private Date dateLastActivity;
	private Date due;
	private boolean dueComplete;

	private String idChecklists;
	private String idAttachmentCover;

	private String idList;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	public Badges getBadges() {
//		return badges;
//	}
//
//	public void setBadges(Badges badges) {
//		this.badges = badges;
//	}

	public ArrayList<?> getCheckItemStates() {
		return checkItemStates;
	}

	public void setCheckItemStates(ArrayList<?> checkItemStates) {
		this.checkItemStates = checkItemStates;
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public Date getDateLastActivity() {
		return dateLastActivity;
	}

	public void setDateLastActivity(Date dateLastActivity) {
		this.dateLastActivity = dateLastActivity;
	}

	public Date getDue() {
		return due;
	}

	public void setDue(Date due) {
		this.due = due;
	}

	public boolean getDueComplete() {
		return dueComplete;
	}

	public void setDueComplete(boolean dueComplete) {
		this.dueComplete = dueComplete;
	}

	public String getIdChecklists() {
		return idChecklists;
	}

	public void setIdChecklists(String idChecklists) {
		this.idChecklists = idChecklists;
	}

	public String getIdAttachmentCover() {
		return idAttachmentCover;
	}

	public void setIdAttachmentCover(String idAttachmentCover) {
		this.idAttachmentCover = idAttachmentCover;
	}

	public String getIdList() {
		return idList;
	}

	public void setIdList(String idList) {
		this.idList = idList;
	}

	public String getIdBoard() {
		return idBoard;
	}

	public void setIdBoard(String idBoard) {
		this.idBoard = idBoard;
	}

}
