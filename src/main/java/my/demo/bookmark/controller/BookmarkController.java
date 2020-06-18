package my.demo.bookmark.controller;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import my.demo.bookmark.dto.TabulatorBookmarksDto;
import my.demo.bookmark.dto.converter.TabulatorBookmarksDtoConverter;
import my.demo.bookmark.entity.Bookmark;
import my.demo.bookmark.service.BookmarkService;

@Controller
@RequestMapping("/bookmark")
public class BookmarkController {

	private static Logger logger = LoggerFactory.getLogger(BookmarkController.class); 
	
	@Autowired
	private BookmarkService bookmarkService;
	
	@GetMapping(path="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Bookmark viewJson(@PathVariable(name="id") Long id, Model model) {
		return bookmarkService.getBookmarkById(id);
	}
	
	@PostMapping(value = "/")
	public String save(@ModelAttribute("bookmark") @Valid Bookmark bookmark, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "bookmarkForm";
		}

		bookmarkService.saveBookmark(bookmark);
		logger.info("Saved Bookmark : {}", bookmark);
		return "redirect:/bookmark/";
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable(name="id") Long id, Model model) {
		try{
			Bookmark bookmark = bookmarkService.getBookmarkById(id);
			logger.info("Deleting Bookmark: {}", bookmark);
			bookmarkService.deleteBookmarkById(id);
			logger.info("Deleted Bookmark id: {}", id);
			return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch(Exception ex) {
			return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(path="/{id}")
	public String view(@PathVariable(name="id") Long id, Model model) {
		model.addAttribute("bookmark", bookmarkService.getBookmarkById(id));
		return "bookmarkView";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable(name="id") Long id, Model model) {
		Bookmark bookmark = bookmarkService.getBookmarkById(id);
		model.addAttribute("bookmark", bookmark);
		return "bookmarkForm";
	}

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("bookmark", new Bookmark());
		return "bookmarkForm";
	}
	
	@GetMapping("/")
	public String home(Model model) {
		return "bookmarkHome";
	}

	@GetMapping(value = "/list", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public TabulatorBookmarksDto list(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "10") Integer pageSize, @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy) {
	
		Page<Bookmark> bookmarkPage = bookmarkService.getBookmarksPage(page-1, pageSize, sortBy);
		
		return TabulatorBookmarksDtoConverter.convertToDto(bookmarkPage);
	}

}