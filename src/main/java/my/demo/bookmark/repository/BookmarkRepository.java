package my.demo.bookmark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import my.demo.bookmark.entity.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
}
