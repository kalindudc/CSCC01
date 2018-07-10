package csc.summer2018.cscc01.crawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.exceptions.PageBiggerThanMaxSizeException;
import edu.uci.ics.crawler4j.fetcher.PageFetchResult;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.url.WebURL;
import java.io.IOException;

public class CustomPageFetcher extends PageFetcher {

  private PageFetcherStats stats;

  public CustomPageFetcher(CrawlConfig config) {
    super(config);
    stats = new PageFetcherStats();
  }

  @Override
  public PageFetchResult fetchPage(WebURL webUrl)
      throws InterruptedException, PageBiggerThanMaxSizeException, IOException {
    PageFetchResult result = super.fetchPage(webUrl);

    stats.addUrlFetched(result.getFetchedUrl(), result.getStatusCode());

    return result;
  }

  public PageFetcherStats getStats() {
    return stats;
  }

  /**
   * Replace the PageFetcherStats of this PageFetcher
   *
   * @param stats the stats to replace with
   */
  public void setStats(PageFetcherStats stats) {
    this.stats = stats;
  }
}
