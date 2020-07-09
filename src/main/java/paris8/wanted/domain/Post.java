package paris8.wanted.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.Data;
import lombok.EqualsAndHashCode;



@Entity
@Table
@Data
@EqualsAndHashCode(of= {"id"})
public class Post implements Serializable {
	protected static final long serialVersionUID = 2406937098267757690L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String position;
	
	private String competences;
	
	private String description;
	
	@JoinColumn(name = "author_id", insertable = false, updatable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private UserProfile author;

	@Column(name = "author_id")
	private Long authorId;
	
	private String country;
	
	private String city;
	
	private String email; 
	
	private Integer bacPlusLevel;
	
	
	private Integer monthsOfExperience;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set <Language> languages;
	
	private Float minSalary;
	
	private boolean showSalary;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getCompetences() {
		return competences;
	}

	public void setCompetences(String competences) {
		this.competences = competences;
	}

	public Set<Language> getLanguages() {
		return languages;
	}

	public void setLanguages(Set<Language> languages) {
		this.languages = languages;
	} 

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getMinSalary() {
		return minSalary;
	}

	public void setMinSalary(Float minSalary) {
		this.minSalary = minSalary;
	}

	public boolean isShowSalary() {
		return showSalary;
	}

	public void setShowSalary(boolean showSalary) {
		this.showSalary = showSalary;
	}

	public Integer getBacPlusLevel() {
		return bacPlusLevel;
	}

	public void setBacPlusLevel(Integer bacPlusLevel) {
		this.bacPlusLevel = bacPlusLevel;
	}
	
	public Integer getMonthsOfExperience() {
		return monthsOfExperience;
	}

	public void setMonthsOfExperience(Integer monthsOfExperience) {
		this.monthsOfExperience = monthsOfExperience;
	}

	public UserProfile getAuthor() {
		return author;
	}

	public void setAuthor(UserProfile author) {
		this.author = author;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public UserRole getRole() {
		return null;
	}
	
}
