package my.demo.bookmark.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import my.demo.bookmark.entity.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
	public void deleteByDateLessThan(Date date);	
}
