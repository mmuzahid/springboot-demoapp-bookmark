package my.demo.bookmark.playground.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import my.demo.bookmark.playground.service.PrototypeScopeSettings;
import my.demo.bookmark.playground.service.RequestScopeSettings;
import my.demo.bookmark.playground.service.SessionScopeSettings;
import my.demo.bookmark.playground.service.SingletonScopeSettings;

@Controller
@RequestMapping("/playground")
public class PlaygroundController {
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpSession session;
	@Autowired
	private RequestScopeSettings requestScopeSettings;
	@Autowired
	private SessionScopeSettings sessionScopeSettings;
	@Autowired
	private SingletonScopeSettings singletonScopeSettings;
	@Autowired
	private PrototypeScopeSettings prototypeScopeSettings;
	@Autowired

	@GetMapping("/")
	public String home() {
		return "playground/playgroundHome";
	}

	@GetMapping("/beanScope")
	public String beanScope(Model model) {
		model.addAttribute("requestScopeSettings", requestScopeSettings);
		model.addAttribute("sessionScopeSettings", sessionScopeSettings);
		model.addAttribute("singletonScopeSettings", singletonScopeSettings);
		model.addAttribute("prototypeScopeSettings", prototypeScopeSettings);

		return "playground/playgroundBeanScope";
	}
	
	@GetMapping("/getNullPointerException")
	public String getNullPointerException(Model model) {
		throw new NullPointerException("This is a NullPointerException");
	}
	
	
	@ExceptionHandler(NullPointerException.class)
	public String handleNpeException(NullPointerException npe, Model model) {
		model.addAttribute("type", npe.getClass());
		model.addAttribute("details", "Exception caught by Controller-based @ExceptionHandler - Message:" + npe.getMessage());
		return "playground/playgroundExceptionHandler";
	}

}
