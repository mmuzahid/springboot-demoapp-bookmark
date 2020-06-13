package my.demo.bookmark.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Bookmark {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date date;
	@NotNull
	//hibernative alternative of @Size -  @org.hibernate.validator.constraints.Length(min = 5, max = 50)
	@Size(min = 5, max = 50, message = "length of Name must be between 5 and 50")
	private String name;
	@NotNull
	@Size(min = 5, max = 1000)
	@Column(name="description", columnDefinition="TEXT",length=1024)
	private String description;
}
