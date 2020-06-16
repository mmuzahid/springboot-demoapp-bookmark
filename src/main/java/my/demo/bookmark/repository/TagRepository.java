package my.demo.bookmark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import my.demo.bookmark.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
