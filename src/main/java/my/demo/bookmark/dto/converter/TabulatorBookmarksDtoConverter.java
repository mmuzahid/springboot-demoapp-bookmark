package my.demo.bookmark.dto.converter;

import org.springframework.data.domain.Page;

import my.demo.bookmark.dto.TabulatorBookmarksDto;
import my.demo.bookmark.entity.Bookmark;

public class TabulatorBookmarksDtoConverter {
	
	public static TabulatorBookmarksDto convertToDto(Page<Bookmark> page) {
		TabulatorBookmarksDto TabulatorBookmarksDto = new TabulatorBookmarksDto();
		if (page != null) {
			TabulatorBookmarksDto.setData(page.getContent());
			TabulatorBookmarksDto.setTotalPage(page.getTotalPages());
		}
		return TabulatorBookmarksDto;
	}

}
