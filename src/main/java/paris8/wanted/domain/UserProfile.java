package paris8.wanted.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Entity
@Table
@Data
public class UserProfile implements Serializable {
	protected static final long serialVersionUID = 2406937098267757690L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	
	@OneToOne
	protected Domain workDomain;
	protected String city;
	protected String country;
	protected String wantedUserId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Domain getWorkDomains() {
		return workDomain;
	}
	public void setWorkDomains(Domain workDomain) {
		this.workDomain = workDomain;
	}
	public String getWantedUserId() {
		return wantedUserId;
	}
	public void setWantedUserId(String wantedUserId) {
		this.wantedUserId = wantedUserId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Domain getWorkDomain() {
		return workDomain;
	}
	public void setWorkDomain(Domain workDomain) {
		this.workDomain = workDomain;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
}
