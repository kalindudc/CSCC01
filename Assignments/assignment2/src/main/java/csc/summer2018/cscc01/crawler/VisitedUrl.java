package csc.summer2018.cscc01.crawler;

import csc.summer2018.cscc01.util.Constants;

public class VisitedUrl {

  private String url;
  private double size;
  private int numOfOutlinks;
  private String contentType;

  /**
   * Create a new instance of a VisitedUrl
   *
   * @param url the url
   * @param size the download size in kb
   * @param numOfOutlinks the number of out links
   * @param contentType the content typ eof this endpoint
   */
  public VisitedUrl(String url, double size, int numOfOutlinks, String contentType) {
    this.url = url;
    this.size = size;
    this.numOfOutlinks = numOfOutlinks;
    this.contentType = contentType;
  }

  /**
   * Return the url of this VisitedUrl
   *
   * @return the url of this VisitedUrl
   */
  public String getUrl() {
    return url;
  }

  /**
   * Return the size of this VisitedUrl in kb
   *
   * @return the size of this VisitedUrl in kb
   */
  public double getSize() {
    return size;
  }

  /**
   * Return the number of out links of this VisitedUrl
   *
   * @return the number of out links
   */
  public int getNumOfOutlinks() {
    return numOfOutlinks;
  }

  /**
   * Return the content type of this VisitedUrl
   *
   * @return the content type of this VisitedUrl
   */
  public String getContentType() {
    return contentType;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof VisitedUrl)) {
      return false;
    }

    VisitedUrl newObj = (VisitedUrl) obj;
    return newObj.getUrl().equals(this.url) && newObj.getContentType().equals(this.contentType) &&
        newObj.getNumOfOutlinks() == this.numOfOutlinks && newObj.getSize() == this.size;
  }

  @Override
  public String toString() {
    return url + Constants.FILE_DELIMETER_COMMA + size + Constants.FILE_DELIMETER_COMMA
        + numOfOutlinks + Constants.FILE_DELIMETER_COMMA + contentType;
  }
}
