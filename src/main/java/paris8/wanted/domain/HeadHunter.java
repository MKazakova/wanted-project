package paris8.wanted.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table
@Data
@EqualsAndHashCode(of= {"id"})
public class HeadHunter extends UserProfile{
	
	@JsonView(Views.ShortInfo.class)
	private String companyName;
	
	@Column(updatable=false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonView(Views.FullInfo.class)
	private LocalDate creationDate;
	
	@JsonView(Views.ShortInfo.class)
	private String adresse;
	
	@JsonView(Views.ShortInfo.class)
	private String email;
	
	@JsonView(Views.ShortInfo.class)
	private String telephone;
	
	@JsonView(Views.ShortInfo.class)
	private String codePostale;

	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}



	public LocalDate getCreationDate() {
		return creationDate;
	}



	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}



	public String getAdresse() {
		return adresse;
	}



	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getTelephone() {
		return telephone;
	}



	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}



	public String getCodePostale() {
		return codePostale;
	}



	public void setCodePostale(String codePostale) {
		this.codePostale = codePostale;
	}

}

