package callback.tasks;

import callback.repository.CookiesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ExpireCookiesTask {

  private static final Logger LOG = LoggerFactory.getLogger(ExpireCookiesTask.class);

  @Autowired CookiesRepository cookieRepository;

  @Async
  @Scheduled(cron = "@hourly")
  public void expireCookies() {
    var staleCookies = cookieRepository.findStaleCookies();
    LOG.info("Found [{}] stale cookies.", staleCookies.size());
    if (!staleCookies.isEmpty()) {
      LOG.info("Deleting [{}] stale cookies.", staleCookies.size());
      cookieRepository.deleteAll(staleCookies);
    }
  }
}
