package my.demo.bookmark.playground.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service
@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestScopeSettings {
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

	private String settingsLabel;

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpSession getSession() {
		return session;
	}

	public RequestScopeSettings getRequestScopeSettings() {
		return requestScopeSettings;
	}

	public SessionScopeSettings getSessionScopeSettings() {
		return sessionScopeSettings;
	}

	public SingletonScopeSettings getSingletonScopeSettings() {
		return singletonScopeSettings;
	}

	public PrototypeScopeSettings getPrototypeScopeSettings() {
		return prototypeScopeSettings;
	}

	public String getSettingsLabel() {
		return settingsLabel;
	}

	public void setSettingsLabel(String settingsLabel) {
		this.settingsLabel = settingsLabel;
	}
}
