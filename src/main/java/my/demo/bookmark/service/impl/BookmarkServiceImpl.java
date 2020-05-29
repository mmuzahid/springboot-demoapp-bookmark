package my.demo.bookmark.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import my.demo.bookmark.entity.Bookmark;
import my.demo.bookmark.repository.BookmarkRepository;
import my.demo.bookmark.service.BookmarkService;

@Service
public class BookmarkServiceImpl implements BookmarkService {

	@Autowired
	private BookmarkRepository bookmarkRepository;
	
	@Override
	public Bookmark getBookmarkById(Long id) {
		Optional<Bookmark> bookmark = bookmarkRepository.findById(id);
        if(bookmark.isPresent()) {
            return bookmark.get();
        } else {
            throw new RuntimeException("No bookmark record exist for given id");
        }
	}

	@Override
	public List<Bookmark> getBookmarks(Integer page, Integer pageSize, String sortBy) {		
		Pageable pageable = PageRequest.of(page.intValue(), pageSize, Sort.by(sortBy));
		Page<Bookmark> bookmarkPage = bookmarkRepository.findAll(pageable);
		return bookmarkPage.getContent();
	}
	
	@Override
	public Page<Bookmark> getBookmarksPage(Integer page, Integer pageSize, String sortBy) {		
		Pageable pageable = PageRequest.of(page.intValue(), pageSize, Sort.by(sortBy));
		Page<Bookmark> bookmarkPage = bookmarkRepository.findAll(pageable);
		return bookmarkPage;
	}

	@Override
	public void saveBookmark(Bookmark bookmark) {
		bookmarkRepository.save(bookmark);
	}

	@Override
	public void deleteBookmarkById(Long id) {
		bookmarkRepository.deleteById(id);
	}
	
	@Override
	public void deleteAllBookmarks() {
		bookmarkRepository.deleteAll();
	}

}
