/**
 * Interactive Novels: Novel.java
 * Represents an interactive novel
 */
package es.uca.inovels.model;

import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author Francisco Fernández Sobejano
 *
 */

@Entity
@JsonPropertyOrder({ "id", "name", "description", "user", "image", "scenes" })
public class Novel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	//The name of the novel
	@NotNull
	@Size(min = 3, max = 25)
	private String name;
	
	//The description of the novel
	@Size(max = 200)
	private String description;
	
	//The language of the novel
	@Size(max = 2)
	private String language;
	
	//The age range of the novel
	@Size(max = 5)
	private String agerange;

	//The main image of the novel
	@OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinColumn(name="image_id")
	@NotNull
	private Image image;
	
	//Scenes of the novel
	@OneToMany(mappedBy = "novel", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("pageNumber ASC")
	private List<Scene> scenes;
	
	//Owner of the novel
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user;
	
	@Column
	@CreationTimestamp
	private LocalDateTime createDateTime;

	@Column
	@UpdateTimestamp
	private LocalDateTime updateDateTime;

	protected Novel() { }
	
	public Novel (User user, String name, String description, String language, String agerange) {
		this.user = user;
		this.name = name;
		this.image = new Image (Constants.DEFAULT_NOVEL, "defaultNovel.jpg", "image/jpg", 400, 225);
		this.description = description;
		this.language = language;
		this.agerange = agerange;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the agerange
	 */
	public String getAgerange() {
		return agerange;
	}

	/**
	 * @param agerange the agerange to set
	 */
	public void setAgerange(String agerange) {
		this.agerange = agerange;
	}

	/**
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * @return the scenes
	 */
	public List<Scene> getScenes() {
		return scenes;
	}
	
	public void addScene(Scene scene) {
		this.scenes.add(scene);
	}
	
	public void removeScene(Scene scene) {
		this.scenes.remove(scene);
	}

	/**
	 * @param scenes the scenes to set
	 */
	public void setScenes(List<Scene> scenes) {
		this.scenes = scenes;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @return the createDateTime
	 */
	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}

	/**
	 * @return the updateDateTime
	 */
	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}
	
}