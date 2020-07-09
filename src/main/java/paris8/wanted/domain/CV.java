package paris8.wanted.domain;

import java.io.Serializable;
import java.time.Period;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table
@Data
@EqualsAndHashCode(of= {"id"})
public class CV extends Post implements Serializable {
	protected static final long serialVersionUID = 2406937098267757690L;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Education> educations;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Experience> experiences;
	
	private boolean isReadyToMove;
	
	private String name;

	public Set<Education> getEducations() {
		return educations;
	}

	public void setEducations(Set<Education> education) {
		this.educations = education;
		
	}

	public Set<Experience> getExperiences() {
		return experiences;
	}

	private void updateBacPlusLevel() {
		setBacPlusLevel((educations == null||educations.isEmpty())?0:educations.stream().mapToInt(e->e.getBacPlusLevel()).max().getAsInt());
	}


	private void updateMonthsOfExperience() {
		setMonthsOfExperience((experiences == null||experiences.isEmpty())?0:experiences.stream()
				.mapToInt(e->
				{
					Period p = Period.between(e.getStartDate(), e.getEndDate());
					return p.getYears()*12 + p.getMonths()+p.getDays()/28;
					
				}).sum()); 
	}
	
	public void setExperiences(Set<Experience> experience) {
		this.experiences = experience;
	}
	
	public void update() {
		updateBacPlusLevel();
		updateMonthsOfExperience();
	}

	public boolean isReadyToMove() {
		return isReadyToMove;
	}

	public void setReadyToMove(boolean isReadyToMove) {
		this.isReadyToMove = isReadyToMove;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public UserRole getRole() {
		return UserRole.JS;
	}

}
