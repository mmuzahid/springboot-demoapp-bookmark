package my.demo.bookmark.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import my.demo.bookmark.playground.services.PrototypeScopeSettings;
import my.demo.bookmark.playground.services.RequestScopeSettings;
import my.demo.bookmark.playground.services.SessionScopeSettings;
import my.demo.bookmark.playground.services.SingletonScopeSettings;

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
		return "playgroundHome";
	}

	@GetMapping("/beanScope")
	public String beanScope(Model model) {
		model.addAttribute("requestScopeSettings", requestScopeSettings);
		model.addAttribute("sessionScopeSettings", sessionScopeSettings);
		model.addAttribute("singletonScopeSettings", singletonScopeSettings);
		model.addAttribute("prototypeScopeSettings", prototypeScopeSettings);

		return "playgroundBeanScope";
	}

}
