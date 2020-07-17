package my.demo.bookmark.service;

import java.util.List;

import org.springframework.data.domain.Page;

import my.demo.bookmark.entity.Tag;

public interface TagService {
	public Tag getTagById(Long id);
	public List<Tag> getTags(Integer page, Integer pageSize, String sortBy);
	public void saveTag(Tag tag);
	public void deleteTagById(Long id);
	public Page<Tag> getTagsPage(Integer page, Integer pageSize, String sortBy);
	void deleteAllTags();
	public List<Tag> getTags();
}
