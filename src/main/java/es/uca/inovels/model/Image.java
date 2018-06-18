/**
 * Interactive Novels: Image.java
 * Represents an image that's part of a scene
 */
package es.uca.inovels.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.util.Base64Utils;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	@NotNull
	private String name;
	
	@Lob
	@Basic(fetch = FetchType.EAGER)
	private byte[] src;
	
	@NotNull
	private String extension;
	
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
	
	public Image (byte[] src, int sizeX, int sizeY, int offsetX, int offsetY) {
		this.user = null;
		this.src = src;
		this.name = "defaultimage";
		this.extension = "image/jpeg";
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}
	
	public Image (User user, byte[] src, int sizeX, int sizeY, int offsetX, int offsetY) {
		this.user = user;
		this.name = "defaultimage";
		this.extension = "image/jpeg";
		this.src = src;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}
	
	public Image (String src, String name, String extension, int sizeX, int sizeY) {
		this.user = null;
		this.src = Base64Utils.decodeFromString(src);
		this.name = name;
		this.extension = extension;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.offsetX = 0;
		this.offsetY = 0;
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the src
	 */
	public byte[] getSrc() {
		return src;
	}

	/**
	 * @param src the src to set
	 */
	public void setSrc(byte[] src) {
		this.src = src;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
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
	 * @return the extension
	 */
	public String getExtension() {
		return extension;
	}

	/**
	 * @param extension the extension to set
	 */
	public void setExtension(String extension) {
		this.extension = extension;
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