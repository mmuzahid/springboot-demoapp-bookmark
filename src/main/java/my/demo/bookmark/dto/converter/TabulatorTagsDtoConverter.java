package my.demo.bookmark.dto.converter;

import org.springframework.data.domain.Page;

import my.demo.bookmark.dto.TabulatorTagsDto;
import my.demo.bookmark.entity.Tag;

public class TabulatorTagsDtoConverter {
	
	public static TabulatorTagsDto convertToDto(Page<Tag> page) {
		TabulatorTagsDto TabulatorTagsDto = new TabulatorTagsDto();
		if (page != null) {
			TabulatorTagsDto.setData(page.getContent());
			TabulatorTagsDto.setTotalPage(page.getTotalPages());
		}
		return TabulatorTagsDto;
	}

}
