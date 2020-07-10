package my.demo.bookmark;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import my.demo.bookmark.controller.TagController;
import my.demo.bookmark.entity.Tag;
import my.demo.bookmark.service.TagService;

@WebMvcTest(TagController.class)
public class TagControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TagService tagService;

	@Test
	@WithMockUser(username="testuser", password="testpassword")
	public void view() throws Exception {
		Tag newTag = new Tag();
		newTag.setId(1l);
		newTag.setName("Test Tag");
		
		Mockito.when(tagService.getTagById(1l)).thenReturn(newTag);
		Mockito.when(tagService.getTagById(2l)).thenThrow(RuntimeException.class);


		mockMvc.perform(get("/tag/1").contentType(MediaType.TEXT_HTML)).andExpect(status().isOk());

		mockMvc.perform(get("/tag/2").contentType(MediaType.TEXT_HTML)).andExpect(status().isBadRequest());

	}

	@Test
	@WithMockUser(username = "testuser", password = "testpassword")
	// @WithMockUser(username = "testuser", password = "testpassword", roles= {"USER"}) // default role is 'USER'
	// @WithMockUser(username = "testuser", password = "testpassword", authorities = {"ROLE_USER"}) // default authority is 'ROLE_USER'
	public void edit() throws Exception {
		Tag newTag = new Tag();
		newTag.setId(1l);
		newTag.setName("Test Tag");

		Mockito.when(tagService.getTagById(1l)).thenReturn(newTag);
		Mockito.when(tagService.getTagById(2l)).thenThrow(RuntimeException.class);

		mockMvc.perform(get("/tag/edit/1").contentType(MediaType.TEXT_HTML)).andExpect(status().isOk());

		mockMvc.perform(get("/tag/edit/2").contentType(MediaType.TEXT_HTML)).andExpect(status().isBadRequest());

	}

	@Test
	@WithMockUser(username="testuser", password="testpassword")
	public void add() throws Exception {
		mockMvc.perform(get("/tag/add").contentType(MediaType.TEXT_HTML)).andExpect(status().isOk());

		mockMvc.perform(get("/tag/add/1").contentType(MediaType.TEXT_HTML)).andExpect(status().isNotFound());

	}

	@Test
	@WithMockUser(username="testuser", password="testpassword")
	public void list() throws Exception {

		mockMvc.perform(get("/tag/list").contentType(MediaType.TEXT_HTML)).andExpect(status().isOk());

	}
	
	@Test
	@WithMockUser(username="testuser", password="testpassword")
	public void home() throws Exception {

		mockMvc.perform(get("/tag/").contentType(MediaType.TEXT_HTML)).andExpect(status().isOk());

	}

	@Test
	@WithMockUser(username="testadmin", password="testpassword", authorities= {"ROLE_ADMIN"})
	public void deleteTag() throws Exception {
		Tag newTag = new Tag();
		newTag.setId(1l);
		newTag.setName("Test Tag");

		Mockito.when(tagService.getTagById(1l)).thenReturn(newTag);
		Mockito.doNothing().when(tagService).deleteTagById(1l);

		mockMvc.perform(delete(new URI("/tag/1"))
					.with(csrf())
					.contentType(MediaType.TEXT_HTML))
				.andExpect(status().isNoContent());
	}
	
}