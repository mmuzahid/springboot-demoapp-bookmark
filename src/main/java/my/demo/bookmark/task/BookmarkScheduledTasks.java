package my.demo.bookmark.task;

import java.util.Date;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import my.demo.bookmark.service.BookmarkService;
@Component
public class BookmarkScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(BookmarkScheduledTasks.class);

	@Autowired
	private BookmarkService bookmarkService;
	
	@Value("${bookmark.clear.till.days}")
	private int clearTillDays;
	
	@Scheduled(fixedRate = 20000)
	@Transactional
	public void deleteOldBookmarks() {
		Date deleteTillDate = new Date(System.currentTimeMillis() - clearTillDays * 24 * 60 * 60 * 1_000);
		log.info("TODO: Delete Old Bookmarks till date: {}", deleteTillDate);
		bookmarkService.deleteBookmarksDateLessThan(deleteTillDate);
	}
	
}
