package csc.summer2018.cscc01.crawler;

import csc.summer2018.cscc01.util.Constants;
import csc.summer2018.cscc01.util.Shared;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import java.util.Set;

public class Crawler extends WebCrawler {

  private CrawlerStats stats;

  public Crawler() {
    super();
    stats = new CrawlerStats();
  }

  @Override
  public boolean shouldVisit(Page referringPage, WebURL url) {
    String href = url.getURL().toLowerCase();
    // ignore any images or css or js files
    boolean result = !Constants.FILTERS.matcher(href).matches();
    Shared.incTotalUrl();
    Shared.incContentTypes(referringPage.getContentType());
    // check if we are still in the correct domain
    if (result) {
      for (String domain : Shared.getSeeds()) {
        if (href.startsWith(domain)) {
          if (!stats.hasAttemptedUrl(href)) {
            stats.addValidUrl(href);
          }
          return true;
        }
      }
    }
    // log stats
    if (!stats.hasAttemptedUrl(href)) {
      stats.addInvalidUrl(href);
    }

    return false;
  }

  @Override
  public void visit(Page page) {
    String url = page.getWebURL().getURL();

    if (page.getParseData() instanceof HtmlParseData) {
      HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
      Set<WebURL> links = htmlParseData.getOutgoingUrls();
      double sizeInKb = ((double) page.getContentData().length) / ((double) Constants.BYTES_TO_KB);

      if (sizeInKb < 1) {
        Shared.incFileSizeLessThanOneKb();
      } else if (sizeInKb < 10) {
        Shared.incFileSizeOneKbToTenKb();
      } else if (sizeInKb < 100) {
        Shared.incFileSizeTenKbToOneHundredKb();
      } else if (sizeInKb < 1024) {
        Shared.incFileSizeOneHundredKbToOneMb();
      } else {
        Shared.incFileSizeLargerThanOneMb();
      }

      VisitedUrl visitedUrl = new VisitedUrl(url,
          page.getContentData().length / Constants.BYTES_TO_KB, links.size(),
          page.getContentType());
      stats.addVisitedUrl(visitedUrl);
    }
  }

  @Override
  public Object getMyLocalData() {
    return stats;
  }

  /**
   * Replace the CrawlerStats of this Crawler
   *
   * @param stats the stats to replace with
   */
  public void setStats(CrawlerStats stats) {
    this.stats = stats;
  }
}
