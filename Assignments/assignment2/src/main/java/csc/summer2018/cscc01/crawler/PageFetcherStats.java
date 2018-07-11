package csc.summer2018.cscc01.crawler;

import csc.summer2018.cscc01.util.Constants;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class PageFetcherStats {

  private Map<String, Integer> urlsFetched;

  public PageFetcherStats() {
    urlsFetched = new HashMap<String, Integer>();
  }

  public boolean hasFetchedUrl(String url) {
    return urlsFetched.containsKey(url);
  }

  public void addUrlFetched(String url, Integer statusCode) {
    if (!hasFetchedUrl(url)) {
      urlsFetched.put(url, statusCode);
    }
  }

  public Integer getFetchedStatusCode(String url) {
    if (hasFetchedUrl(url)) {
      return urlsFetched.get(url);
    }
    return new Integer(-1);
  }

  public void setUrlsFetched(Map<String, Integer> urlsAttempted) {
    this.urlsFetched = urlsAttempted;
  }

  public void writeUrlsFetched(String path) throws IOException {
    FileWriter fileWriter = new FileWriter(path, true);
    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
    PrintWriter out = new PrintWriter(bufferedWriter);

    for (Map.Entry<String, Integer> entry : urlsFetched.entrySet()) {
      out.println(entry.getKey() + Constants.FILE_DELIMETER_COMMA + entry.getValue().toString());
    }

    out.close();
    bufferedWriter.close();
    fileWriter.close();

  }

}
