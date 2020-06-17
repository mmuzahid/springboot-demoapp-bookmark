package my.demo.bookmark.controller;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import my.demo.bookmark.dto.TabulatorDto;
import my.demo.bookmark.dto.converter.TabulatorDtoConverter;
import my.demo.bookmark.entity.Tag;
import my.demo.bookmark.service.TagService;

@Controller
@RequestMapping("/tag")
public class TagController {

	@Autowired
	private TagService tagService;
	
	@GetMapping(path="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Tag viewJson(@PathVariable(name="id") Long id, Model model) {
		return tagService.getTagById(id);
	}
	
	@PostMapping(value = "/")
	public String save(@ModelAttribute("tag") @Valid Tag tag, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "tagForm";
		}

		tagService.saveTag(tag);
		return "redirect:/tag/";
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable(name="id") Long id, Model model) {
		try{
			tagService.deleteTagById(id);
			return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch(Exception ex) {
			return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(path="/{id}")
	public String view(@PathVariable(name="id") Long id, Model model) {
		model.addAttribute("tag", tagService.getTagById(id));
		return "tagView";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable(name="id") Long id, Model model) {
		Tag tag = tagService.getTagById(id);
		model.addAttribute("tag", tag);
		return "tagForm";
	}

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("tag", new Tag());
		return "tagForm";
	}
	
	@GetMapping("/")
	public String home(Model model) {
		return "tagHome";
	}

	@GetMapping(value = "/list", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public TabulatorDto<Tag> list(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "10") Integer pageSize, @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy) {
	
		Page<Tag> tagPage = tagService.getTagsPage(page-1, pageSize, sortBy);
		return TabulatorDtoConverter.<Tag>convertToDto(tagPage);
	}

}