package csc.summer2018.cscc01.crawler;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import csc.summer2018.cscc01.util.Shared;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import java.util.Set;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CrawlerTest {

  private Crawler crawler;
  private CrawlerStats stats;

  @Before
  public void setUp() throws Exception {
    crawler = new Crawler();
    stats = new CrawlerStats();
    crawler.setStats(stats);
    Shared.setSeeds(new String[]{"http://test.com/"});
  }

  @After
  public void tearDown() throws Exception {
    crawler = null;
    stats = null;
    Shared.setSeeds(new String[]{});
  }

  @Test
  public void shouldVisit() {
    String invalidURL = "http://something.test.com/";
    String valid = "http://test.com/something";

    Page referringPage = mock(Page.class);
    WebURL url = mock(WebURL.class);

    when(url.getURL()).thenReturn(invalidURL).thenReturn(valid);

    assertFalse(crawler.shouldVisit(referringPage, url));
    assertTrue(stats.hasAttemptedUrl(invalidURL));
    assertFalse(stats.hasAttemptedUrl(valid));
    assertTrue(crawler.shouldVisit(referringPage, url));
    assertTrue(stats.hasAttemptedUrl(invalidURL));
    assertTrue(stats.hasAttemptedUrl(valid));

  }

  @Test
  public void visit() {
    String valid = "http://test.com/something";

    Page page = mock(Page.class);
    WebURL url = mock(WebURL.class);
    HtmlParseData data = mock(HtmlParseData.class);
    Set<WebURL> outgoing = mock(Set.class);

    when(page.getWebURL()).thenReturn(url);
    when(page.getContentData()).thenReturn(new byte[1024]);
    when(page.getParseData()).thenReturn(data);
    when(page.getContentType()).thenReturn("txt/json");
    when(url.getURL()).thenReturn(valid);
    when(data.getOutgoingUrls()).thenReturn(outgoing);
    when(outgoing.size()).thenReturn(10);

    crawler.visit(page);

    assertTrue(stats.hasVisitedUrl(new VisitedUrl(valid, 1, 10, "txt/json")));
  }

  @Test
  public void getMyLocalData() {
    Object data = crawler.getMyLocalData();

    assertTrue(data instanceof CrawlerStats);
  }
}