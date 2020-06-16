package my.demo.bookmark.dto;

import java.util.List;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import my.demo.bookmark.entity.Tag;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class TabulatorTagsDto {
	private int totalPage;
	private List<Tag> data;
}