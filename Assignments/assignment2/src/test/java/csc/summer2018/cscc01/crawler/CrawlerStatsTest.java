package csc.summer2018.cscc01.crawler;

import static org.junit.Assert.*;

import csc.summer2018.cscc01.util.Constants;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CrawlerStatsTest {

  public static final String DOMAIN = "test.com";

  private CrawlerStats stats;
  private Map<String, String> urlsAttempted;
  private List<VisitedUrl> visitStats;

  @Before
  public void setUp() throws Exception {

    if (!new File(Constants.CRAWLER_STORAGE).exists()) new File(Constants.CRAWLER_STORAGE).mkdir();

    stats = new CrawlerStats();
    urlsAttempted = new HashMap<String, String>();
    visitStats = new ArrayList<VisitedUrl>();
    stats.setUrlsAttempted(urlsAttempted);
    stats.setVisitStats(visitStats);
  }

  @After
  public void tearDown() throws Exception {
    stats = null;
  }

  @Test
  public void hasAttemptedUrl() {
    assertFalse(stats.hasAttemptedUrl(DOMAIN + "/test1"));
    urlsAttempted.put(DOMAIN + "/test1", Constants.URL_OK);
    assertTrue(stats.hasAttemptedUrl(DOMAIN + "/test1"));
  }

  @Test
  public void hasVisitedUrl() {
    VisitedUrl toAdd = new VisitedUrl("test.com", 10, 10, "txt");
    assertFalse(stats.hasVisitedUrl(new VisitedUrl("test.com", 10, 10, "txt")));
    visitStats.add(toAdd);
    assertTrue(stats.hasVisitedUrl(new VisitedUrl("test.com", 10, 10, "txt")));
  }

  @Test
  public void addValidUrl() {
    stats.addValidUrl(DOMAIN + "/test1");
    assertEquals(Constants.URL_OK, urlsAttempted.get(DOMAIN + "/test1"));
    stats.addValidUrl(DOMAIN + "/test1");
    assertEquals(Constants.URL_OK, urlsAttempted.get(DOMAIN + "/test1"));
  }

  @Test
  public void addInvalidUrl() {
    stats.addInvalidUrl(DOMAIN + "/test1");
    assertEquals(Constants.URL_NOT_OK, urlsAttempted.get(DOMAIN + "/test1"));
    stats.addInvalidUrl(DOMAIN + "/test1");
    assertEquals(Constants.URL_NOT_OK, urlsAttempted.get(DOMAIN + "/test1"));
  }

  @Test
  public void addVisitedUrl() {
    VisitedUrl toAdd = new VisitedUrl("test.com", 10, 10, "txt");
    stats.addVisitedUrl(toAdd);
    assertTrue(visitStats.contains(new VisitedUrl("test.com", 10, 10, "txt")));
  }

  @Test
  public void writeUrlsAttempted() throws IOException {
    urlsAttempted.put(DOMAIN + "/test1", Constants.URL_OK);
    urlsAttempted.put(DOMAIN + "/test2", Constants.URL_NOT_OK);

    String path = Constants.CRAWLER_STORAGE + "fetch_test" + DOMAIN + ".csv";
    stats.writeUrlsAttempted(path);

    FileReader fileReader = new FileReader(path);
    BufferedReader bufferedReader = new BufferedReader(fileReader);
    String line = bufferedReader.readLine();
    assertNotNull(line);
    assertEquals(DOMAIN + "/test1" + Constants.FILE_DELIMETER_COMMA + Constants.URL_OK, line);
    line = bufferedReader.readLine();
    assertNotNull(line);
    assertEquals(DOMAIN + "/test2" + Constants.FILE_DELIMETER_COMMA + Constants.URL_NOT_OK, line);
    line = bufferedReader.readLine();
    assertNull(line);
    fileReader.close();
    new File(path).delete();
  }


  @Test
  public void writeVisitedUrls() throws IOException {
    VisitedUrl toAdd1 = new VisitedUrl("test1.com", 10, 10, "txt");
    VisitedUrl toAdd2 = new VisitedUrl("test2.com", 20, 20, "json");
    visitStats.add(toAdd1);
    visitStats.add(toAdd2);

    String path = Constants.CRAWLER_STORAGE + "all_test" + DOMAIN + ".csv";
    stats.writeVisitedUrls(path);

    FileReader fileReader = new FileReader(path);
    BufferedReader bufferedReader = new BufferedReader(fileReader);
    String line = bufferedReader.readLine();
    assertNotNull(line);
    assertEquals(toAdd1.toString(), line);
    line = bufferedReader.readLine();
    assertNotNull(line);
    assertEquals(toAdd2.toString(), line);
    line = bufferedReader.readLine();
    assertNull(line);
    fileReader.close();
    new File(path).delete();
  }

}