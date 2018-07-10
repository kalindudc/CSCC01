package csc.summer2018.cscc01.crawler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import csc.summer2018.cscc01.util.Constants;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PageFetcherStatsTest {

  public static final String DOMAIN = "test.com";

  private PageFetcherStats stats;
  private Map<String, Integer> urlsFetched;

  @Before
  public void setUp() throws Exception {
    stats = new PageFetcherStats();
    urlsFetched = new HashMap<String, Integer>();
    stats.setUrlsFetched(urlsFetched);
  }

  @After
  public void tearDown() throws Exception {
    stats = null;
  }

  @Test
  public void hasAttemptedUrl() {
    assertFalse(stats.hasFetchedUrl(DOMAIN + "/test1"));
    urlsFetched.put(DOMAIN + "/test1", new Integer(200));
    assertTrue(stats.hasFetchedUrl(DOMAIN + "/test1"));
  }

  @Test
  public void addUrlFetched() {
    stats.addUrlFetched(DOMAIN + "/test1", new Integer(200));
    assertEquals(new Integer(200), urlsFetched.get(DOMAIN + "/test1"));
    stats.addUrlFetched(DOMAIN + "/test1", new Integer(200));
    assertEquals(new Integer(200), urlsFetched.get(DOMAIN + "/test1"));
  }

  @Test
  public void writeUrlsAttempted() throws IOException {
    urlsFetched.put(DOMAIN + "/test1", new Integer(200));
    urlsFetched.put(DOMAIN + "/test2", new Integer(400));

    String path = Constants.CRAWLER_STORAGE  + "fetch_" + DOMAIN + ".csv";
    stats.writeUrlsFetched(path);

    FileReader fileReader = new FileReader(path);
    BufferedReader bufferedReader = new BufferedReader(fileReader);
    String line = bufferedReader.readLine();
    assertNotNull(line);
    assertEquals(DOMAIN + "/test1" + Constants.FILE_DELIMETER_COMMA + new Integer(200), line);
    line = bufferedReader.readLine();
    assertNotNull(line);
    assertEquals(DOMAIN + "/test2" + Constants.FILE_DELIMETER_COMMA + new Integer(400), line);
    line = bufferedReader.readLine();
    assertNull(line);
    fileReader.close();
    new File(path).delete();
  }

}