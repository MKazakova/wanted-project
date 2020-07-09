package paris8.wanted.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table
@Data
@EqualsAndHashCode(of= {"id"})
public class Chat implements Serializable {
	protected static final long serialVersionUID = 2406937098267757690L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@JoinColumn(name = "to_id", insertable = false, updatable = false)
	private Post hh;

	@Column(name = "to_id")
	private Long hhId;
	
	@JoinColumn(name = "from_id", insertable = false, updatable = false)
	private Post js;

	@Column(name = "from_id")
	private Long jsId;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Message> messages;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
	public Chat setMessagesReturnChat(List<Message> messages) {
		this.messages = messages;
		return this;
	}

	public Long getHhId() {
		return hhId;
	}

	public void setHhId(Long hhId) {
		this.hhId = hhId;
	}

	public Post getJs() {
		return js;
	}

	public void setJs(Post js) {
		this.js = js;
	}

	public Long getJsId() {
		return jsId;
	}

	public void setJsId(Long jsId) {
		this.jsId = jsId;
	}

	public Post getHh() {
		return hh;
	}

	public void setHh(Post hh) {
		this.hh = hh;
	}

	
}