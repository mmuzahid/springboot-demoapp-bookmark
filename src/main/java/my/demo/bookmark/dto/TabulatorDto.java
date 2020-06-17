package my.demo.bookmark.dto;

import java.util.List;

import lombok.Data;

@Data
public class TabulatorDto<E> {
	private int totalPage;
	private List<E> data;
}