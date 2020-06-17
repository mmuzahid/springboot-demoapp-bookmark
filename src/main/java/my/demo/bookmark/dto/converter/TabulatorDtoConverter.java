package my.demo.bookmark.dto.converter;

import org.springframework.data.domain.Page;

import my.demo.bookmark.dto.TabulatorDto;

public class TabulatorDtoConverter {
	
	public static <E> TabulatorDto<E> convertToDto(Page<E> page) {
		TabulatorDto<E> dto = new TabulatorDto<E>();
		if (page != null) {
			dto.setData(page.getContent());
			dto.setTotalPage(page.getTotalPages());
		}
		return dto;
	}

}
