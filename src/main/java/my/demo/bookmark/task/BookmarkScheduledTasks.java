package my.demo.bookmark.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import my.demo.bookmark.service.BookmarkService;
@Component
public class BookmarkScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(BookmarkScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	private BookmarkService bookmarkService;
	
	@Scheduled(fixedRate = 5000)
	@Transactional
	public void deleteOldBookmarks() {
		log.info("TODO: Archive Old Bookmarks: The time is now {}", dateFormat.format(new Date()));
		Date yesterday = new Date(System.currentTimeMillis() - 24*60*60*1000);
		bookmarkService.deleteBookmarksDateLessThan(yesterday);
	}
	
}