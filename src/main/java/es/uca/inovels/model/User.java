/**
 * Interactive Novels: User.java
 */
package es.uca.inovels.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * @author Francisco Fernández Sobejano
 *
 */

@Entity
public class User implements UserDetails{

	private static final long serialVersionUID = -1191480646225681093L;

	@JsonIgnore
	@Id
	@GeneratedValue
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@Column(unique = true)
	@Size(min = 3, max = 20)
	private String username;
	
	@JsonIgnore
	@NotNull
	private LocalDate birthdate;
	
	@JsonIgnore
	private String email;
	
	//Owned novels
	@JsonManagedReference
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Novel> novels = new HashSet<Novel>();
	
	//Read novels
	@JsonManagedReference
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<UserNovel> progress = new HashSet<UserNovel>();
	
	@JsonIgnore
	@Size(min = 4, max = 100)
	private String password;
	
	@JsonIgnore
	private String role;
	
	@Column
	@CreationTimestamp
	private LocalDateTime createDateTime;

	protected User() {
	}

	public User(String username, LocalDate birthdate, String email, String role) {
		this.username = username;
		this.birthdate = birthdate;
		this.email = email;
		this.role = role;
	}

	public User(String userName) {
		this(userName, LocalDate.now().minusYears(18), "test@test.com", "ROLE_USER");
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setUsername(String username) {
		this.username= username;
	}
	
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return String.format("%s", username);
	}

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list=new ArrayList<GrantedAuthority>();
		list.add(new SimpleGrantedAuthority(this.getRole()));
		return list;
		
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the ownedNovels
	 */
	public Set<Novel> getNovels() {
		return novels;
	}

	/**
	 * @param ownedNovels the ownedNovels to set
	 */
	public void addNovel(Novel novel) {
		this.novels.add(novel);
	}
	
	public void removeNovel(Novel novel) {
		this.novels.remove(novel);
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the progress
	 */
	public Set<UserNovel> getProgress() {
		return progress;
	}

	/**
	 * @return the createDateTime
	 */
	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}

}