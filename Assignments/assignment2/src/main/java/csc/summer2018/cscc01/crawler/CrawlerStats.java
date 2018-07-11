package csc.summer2018.cscc01.crawler;

import csc.summer2018.cscc01.util.Constants;
import csc.summer2018.cscc01.util.Shared;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrawlerStats {

  private Map<String, String> urlsAttempted;
  private List<VisitedUrl> visitStats;

  public CrawlerStats() {
    urlsAttempted = new HashMap<String, String>();
    visitStats = new ArrayList<VisitedUrl>();
  }

  public boolean hasAttemptedUrl(String url) {
    return urlsAttempted.containsKey(url);
  }

  public void addValidUrl(String url) {
    if (!hasAttemptedUrl(url)) {
      Shared.incUniqueUrlValid();
      urlsAttempted.put(url, Constants.URL_OK);
    }
  }

  public void addInvalidUrl(String url) {
    if (!hasAttemptedUrl(url)) {
      Shared.incUniqueUrlInvalid();
      urlsAttempted.put(url, Constants.URL_NOT_OK);
    }
  }

  public void setUrlsAttempted(Map<String, String> urlsAttempted) {
    this.urlsAttempted = urlsAttempted;
  }

  public void setVisitStats(List<VisitedUrl> visitStats) {
    this.visitStats = visitStats;
  }

  public boolean hasVisitedUrl(VisitedUrl newUrl) {
    return visitStats.contains(newUrl);
  }

  public void addVisitedUrl(VisitedUrl newUrl) {
    if (!hasVisitedUrl(newUrl)) {
      visitStats.add(newUrl);
    }
  }

  public void writeUrlsAttempted(String path) throws IOException {
    FileWriter fileWriter = new FileWriter(path, true);
    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
    PrintWriter out = new PrintWriter(bufferedWriter);

    for (Map.Entry<String, String> entry : urlsAttempted.entrySet()) {
      out.println(entry.getKey() + Constants.FILE_DELIMETER_COMMA + entry.getValue());
    }

    out.close();
    bufferedWriter.close();
    fileWriter.close();

  }

  public void writeVisitedUrls(String path) throws IOException {
    FileWriter fileWriter = new FileWriter(path, true);
    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
    PrintWriter out = new PrintWriter(bufferedWriter);

    for (VisitedUrl url : visitStats) {
      out.println(url.getUrl() + Constants.FILE_DELIMETER_COMMA +
          url.getSize() + Constants.FILE_DELIMETER_COMMA +
          url.getNumOfOutlinks() + Constants.FILE_DELIMETER_COMMA +
          url.getContentType());
    }

    out.close();
    bufferedWriter.close();
    fileWriter.close();
  }
}
