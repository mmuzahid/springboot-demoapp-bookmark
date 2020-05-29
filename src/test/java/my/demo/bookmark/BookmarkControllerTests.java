package my.demo.bookmark;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import my.demo.bookmark.controller.BookmarkController;
import my.demo.bookmark.entity.Bookmark;
import my.demo.bookmark.service.BookmarkService;

@WebMvcTest(BookmarkController.class)
public class BookmarkControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BookmarkService bookmarkService;

	@Test
	public void view() throws Exception {
		Bookmark newBookmark = new Bookmark();
		newBookmark.setId(1l);
		newBookmark.setDate(new Date());
		newBookmark.setName("Test Bookmark");
		newBookmark.setDescription("Test Desc");
		
		Mockito.when(bookmarkService.getBookmarkById(1l)).thenReturn(newBookmark);
		Mockito.when(bookmarkService.getBookmarkById(2l)).thenThrow(RuntimeException.class);


		mockMvc.perform(get("/bookmark/1").contentType(MediaType.TEXT_HTML)).andExpect(status().isOk());

		mockMvc.perform(get("/bookmark/2").contentType(MediaType.TEXT_HTML)).andExpect(status().isBadRequest());

	}

	@Test
	public void edit() throws Exception {
		Bookmark newBookmark = new Bookmark();
		newBookmark.setId(1l);
		newBookmark.setDate(new Date());
		newBookmark.setName("Test Bookmark");
		newBookmark.setDescription("Test Desc");

		Mockito.when(bookmarkService.getBookmarkById(1l)).thenReturn(newBookmark);
		Mockito.when(bookmarkService.getBookmarkById(2l)).thenThrow(RuntimeException.class);

		mockMvc.perform(get("/bookmark/edit/1").contentType(MediaType.TEXT_HTML)).andExpect(status().isOk());

		mockMvc.perform(get("/bookmark/edit/2").contentType(MediaType.TEXT_HTML)).andExpect(status().isBadRequest());

	}

	@Test
	public void add() throws Exception {

		mockMvc.perform(get("/bookmark/add").contentType(MediaType.TEXT_HTML)).andExpect(status().isOk());

		mockMvc.perform(get("/bookmark/add/1").contentType(MediaType.TEXT_HTML)).andExpect(status().isNotFound());

	}

	@Test
	public void list() throws Exception {

		mockMvc.perform(get("/bookmark/list").contentType(MediaType.TEXT_HTML)).andExpect(status().isOk());

	}
	
	
	@Test
	public void home() throws Exception {

		mockMvc.perform(get("/bookmark/").contentType(MediaType.TEXT_HTML)).andExpect(status().isOk());

	}	
	
}