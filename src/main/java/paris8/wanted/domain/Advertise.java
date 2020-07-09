package paris8.wanted.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table
@Data
@EqualsAndHashCode(of= {"id"})
public class Advertise extends Post{
	
	private String companyName;
	
	@OneToOne
	protected Domain workDomain;

	private Float maxSalary;
	
	private String typeContrat;
	
	private LocalDate createDate;
	
	private Double xCoord;
	
	private Double yCoord;

	public Domain getWorkDomain() {
		return workDomain;
	}

	public void setWorkDomain(Domain workDomain) {
		this.workDomain = workDomain;
	}
	
	public Float getMaxSalary() {
		return maxSalary;
	}

	public void setMaxSalary(Float maxSalary) {
		this.maxSalary = maxSalary;
	}

	public String getTypeContrat() {
		return typeContrat;
	}

	public void setTypeContrat(String typeContrat) {
		this.typeContrat = typeContrat;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public UserRole getRole() {
		return UserRole.HH;
	}

	public Double getxCoord() {
		return xCoord;
	}

	public void setxCoord(Double xCoord) {
		this.xCoord = xCoord;
	}

	public Double getyCoord() {
		return yCoord;
	}

	public void setyCoord(Double yCoord) {
		this.yCoord = yCoord;
	}

}
