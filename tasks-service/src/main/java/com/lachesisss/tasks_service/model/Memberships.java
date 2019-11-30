package com.lachesisss.tasks_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Memberships {
	
	@Id
	private String id;

}
