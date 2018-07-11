package csc.summer2018.cscc01.crawler;

import csc.summer2018.cscc01.util.Shared;
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

    Shared.incFetchAttempted();
    PageFetchResult result = null;

    try {
      result = super.fetchPage(webUrl);
    }
    catch (InterruptedException e) {
      Shared.incFetchFailed();
      throw e;
    }
    catch (PageBiggerThanMaxSizeException e) {
      Shared.incFetchAborted();
      throw e;
    }
    catch (IOException e) {
      Shared.incFetchFailed();
      throw e;
    }

    if (result.getStatusCode() >= 200 && result.getStatusCode() < 300) {
      Shared.incFetchSuccessful();
    } else {
      Shared.incFetchFailed();
    }
    Shared.incStatusCodes(result.getStatusCode());

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
