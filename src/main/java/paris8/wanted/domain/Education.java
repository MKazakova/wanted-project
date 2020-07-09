package paris8.wanted.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table
@Data
@EqualsAndHashCode(of= {"id"})
public class Education  implements Serializable {
	protected static final long serialVersionUID = 240693709826775590L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	private Integer bacPlusLevel;
	
	@OneToOne
	private Domain domain;
	
	private String title;
	
	private LocalDate finishDate;
	
	private String establishment;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getBacPlusLevel() {
		return bacPlusLevel;
	}

	public void setBacPlusLevel(Integer bacPlusLevel) {
		this.bacPlusLevel = bacPlusLevel;
	}

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(LocalDate finishDate) {
		this.finishDate = finishDate;
	}

	public String getEstablishment() {
		return establishment;
	}

	public void setEstablishment(String establishment) {
		this.establishment = establishment;
	}
	
}
