/**
 * UserNovel.java
 * Represents the reader relationship between User and Novel with its progress
 * using the method at https://www.thoughts-on-java.org/many-relationships-additional-properties
 */
package es.uca.inovels.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import es.uca.inovels.repositories.UserNovelRepository;

/**
 * @author Francisco Fernández Sobejano
 *
 */

@Entity
public class UserNovel{
	
	/* We use a numeric id instead of an embedded composite id because the Spring REST API
	 * doesn't fully support embedded IDs.
	 */
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@JsonBackReference(value = "user-progress")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id", updatable = false, nullable = false)
	private User user;
	
	@JsonBackReference(value = "novels-progress")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="novel_id", updatable = false, nullable = false)
	private Novel novel;
	
	//Page represents the progress of the user for a specific novel	
	@Column
	@Min(value = 1)
	private int page;
	
	protected UserNovel() {}
	
	public UserNovel(User user, Novel novel) {
		this.user = user;
		this.novel = novel;
		this.page = 1;
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}
	
	public void turnPage() {
		this.page++;
	}
	
}