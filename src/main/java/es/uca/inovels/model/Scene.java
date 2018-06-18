/**
 * Interactive Novels: 	Scene.java
 * Represents a single page of an Interactive novel, composed by images, text, etc.
 */
package es.uca.inovels.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

/**
 * @author Francisco Fernández Sobejano
 *
 */

@Entity
public class Scene{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//Novel that contains the scene
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="novel_id", updatable = false, nullable = false)
	private Novel novel;
	
	//Images contained in the scene
	//The first image is the background
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(
			name = "Image_Scene",
			joinColumns = @JoinColumn(name="scene_id"),
			inverseJoinColumns = @JoinColumn(name="image_id")
			)
	private List<Image> images = new ArrayList<>();
	
	@Size(max = 10000)
	private String text;
	
	@Size(max = 20)
	private String answer;
	
	protected Scene() { }
	
	/**
	 * @param novel
	 */
	public Scene(Novel novel) {
		this.novel = novel;
		this.images.add(new Image (Constants.DEFAULT_SCENE, "defaultScene.jpg", "image/jpg", 800, 400));
		this.text = "Default text";
		this.answer = "";
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
	 * @return the images
	 */
	public List<Image> getImages() {
		return images;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * @param answer the answer to set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
}