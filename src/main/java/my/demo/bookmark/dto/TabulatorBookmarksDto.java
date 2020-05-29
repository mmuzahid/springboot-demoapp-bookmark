package my.demo.bookmark.dto;

import java.util.List;

import my.demo.bookmark.entity.Bookmark;

public class TabulatorBookmarksDto {

	private int totalPage;
	private List<Bookmark> data;

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<Bookmark> getData() {
		return data;
	}

	public void setData(List<Bookmark> data) {
		this.data = data;
	}

}