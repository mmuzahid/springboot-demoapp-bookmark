package my.demo.bookmark.service;

import java.util.List;

import org.springframework.data.domain.Page;

import my.demo.bookmark.entity.Bookmark;

public interface BookmarkService {
	public Bookmark getBookmarkById(Long id);
	public List<Bookmark> getBookmarks(Integer page, Integer pageSize, String sortBy);
	public void saveBookmark(Bookmark bookmark);
	public void deleteBookmarkById(Long id);
	public Page<Bookmark> getBookmarksPage(Integer page, Integer pageSize, String sortBy);
	void deleteAllBookmarks();
}
