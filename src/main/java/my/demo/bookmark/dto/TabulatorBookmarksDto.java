package my.demo.bookmark.dto;

import java.util.List;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import my.demo.bookmark.entity.Bookmark;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class TabulatorBookmarksDto {
	private int totalPage;
	private List<Bookmark> data;
}