package my.demo.bookmark.task;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import my.demo.bookmark.service.BookmarkService;
@Component
public class ScheduledBookmarTasks {

	private static final Logger log = LoggerFactory.getLogger(ScheduledBookmarTasks.class);

	@Autowired
	private BookmarkService bookmarkService;
	
	@Scheduled(fixedRate = 5000)
	public void archiveBookmarks() {
		log.info("TODO: Archive Old Bookmarks: The time is now {}", new Date());
	}
	
}