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
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
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
	
	private int pageNumber;
	
	//Main image of the scene
	@OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinColumn(name="image_id")
	private Image image;
	
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
		this.image = new Image (Constants.DEFAULT_SCENE, "defaultScene.gif", "image/gif", 800, 1);
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
	 * @param novel the novel to set
	 */
	public void setNovel(Novel novel) {
		this.novel = novel;
	}

	/**
	 * @return the images
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

	/**
	 * @return the pageNumber
	 */
	public int getPageNumber() {
		return pageNumber;
	}

	/**
	 * @param pageNumber the pageNumber to set
	 */
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	
}