package org.soul.web.classifier.db.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="web_page_details")
public class WebPageDetails {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name="url")
	private  String url;
	
	@Column(name="content")
	private String content;
	
	public Integer getId() {
		return id;
	}

	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
