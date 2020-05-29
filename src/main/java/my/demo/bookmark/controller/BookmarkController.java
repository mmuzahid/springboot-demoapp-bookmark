package my.demo.bookmark.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import my.demo.bookmark.dto.TabulatorBookmarksDto;
import my.demo.bookmark.dto.converter.TabulatorBookmarksDtoConverter;
import my.demo.bookmark.entity.Bookmark;
import my.demo.bookmark.service.BookmarkService;

@Controller
@RequestMapping("/bookmark")
public class BookmarkController {

	@Autowired
	private BookmarkService bookmarkService;
	
	@GetMapping(path="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Bookmark viewJson(@PathVariable(name="id") Long id, Model model) {
		return bookmarkService.getBookmarkById(id);
	}
	
	@PostMapping(value = "/")
	public RedirectView save(
			@ModelAttribute("bookmark") Bookmark bookmark) {
		bookmarkService.saveBookmark(bookmark);
		return new RedirectView("/bookmark/");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable(name="id") Long id, Model model) {
		try{
			bookmarkService.deleteBookmarkById(id);
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