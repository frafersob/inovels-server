/**
 * Interactive Novels: Image.java
 * Represents an image that's part of a scene
 */
package es.uca.inovels.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Francisco Fernández Sobejano
 *
 */

@Entity
public class Image{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	@NotEmpty
	private String src;
	
	@NotNull
	@Min(value = 1)
	private int sizeX;
	
	@NotNull
	@Min(value = 1)
	private int sizeY;
	
	@NotNull
	@Min(value = 0)
	private int offsetX;
	
	@NotNull
	@Min(value = 0)
	private int offsetY;
	
	protected Image() {}
	
	public Image (String src, int sizeX, int sizeY, int offsetX, int offsetY) {
		this.user = null;
		this.src = src;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}
	
	public Image (User user, String src, int sizeX, int sizeY, int offsetX, int offsetY) {
		this.user = user;
		this.src = src;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @return the src
	 */
	public String getSrc() {
		return src;
	}

	/**
	 * @param src the src to set
	 */
	public void setSrc(String src) {
		this.src = src;
	}

	/**
	 * @return the sizeX
	 */
	public int getSizeX() {
		return sizeX;
	}

	/**
	 * @param sizeX the sizeX to set
	 */
	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}

	/**
	 * @return the sizeY
	 */
	public int getSizeY() {
		return sizeY;
	}

	/**
	 * @param sizeY the sizeY to set
	 */
	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}

	/**
	 * @return the offsetX
	 */
	public int getOffsetX() {
		return offsetX;
	}

	/**
	 * @param offsetX the offsetX to set
	 */
	public void setOffsetX(int offsetX) {
		this.offsetX = offsetX;
	}

	/**
	 * @return the offsetY
	 */
	public int getOffsetY() {
		return offsetY;
	}

	/**
	 * @param offsetY the offsetY to set
	 */
	public void setOffsetY(int offsetY) {
		this.offsetY = offsetY;
	}
	
}