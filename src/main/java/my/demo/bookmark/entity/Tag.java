package my.demo.bookmark.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Tag {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Size(min = 2, max = 20, message = "length of tag must be between 2 and 20")
	private String name;
	private boolean inactive;
}
