package callback.tasks;

import callback.repository.CookiesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ExpireCookiesTask {

  private static final Logger LOG = LoggerFactory.getLogger(ExpireCookiesTask.class);

  @Autowired CookiesRepository cookieRepository;

  @Scheduled(fixedRate = 5000)
  public void reportCurrentTime() {
    var staleCookies = cookieRepository.findStaleCookies();
    LOG.info("Found {} stale cookies", staleCookies.size());
  }
}
