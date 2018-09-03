package com.waylau.spring.boot.blog.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 存放Persistent_Token的实体类
 * @author 吴淙
 *
 */
@Entity
@Table(name = "persistent_logins")
public class PersistentLogins {
	@Column(nullable = false) // 映射为字段，值不能为空
	String username;
	@Id
	String series;
	@Column(nullable = false) // 映射为字段，值不能为空
	String token;
	
	@Column(columnDefinition="TIMESTAMP",nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	Date last_used;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getLast_used() {
		return last_used;
	}

	public void setLast_used(Date last_used) {
		this.last_used = last_used;
	} 
	
	
}
