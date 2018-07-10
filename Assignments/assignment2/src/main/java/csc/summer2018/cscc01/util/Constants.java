package csc.summer2018.cscc01.util;

import java.io.File;
import java.util.regex.Pattern;

public class Constants {

  public static final Pattern FILTERS = Pattern
      .compile(".*(\\.(css|js|bmp|gif|jpe?g|png|tiff?|mid|mp2|mp3|mp4|wav|avi|mov|mpeg|ram|m4v|pdf"
          + "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

  public static final String CRAWLER_STORAGE = "crawler-data" + File.separator;
  public static final int CRAWLER_NUMBER_OF_THREADS = 7;

  public static final String FILE_DELIMETER_COMMA = ",";
  public static final String FILE_NEWLINE_SEPARATOR = "\n";
  public static final String FILE_EXTENTION_CSV = ".csv";
  public static final String FILE_EXTENTION_TXT = ".txt";

  public static final String FILE_PATH_FETCH = "fetch" + FILE_EXTENTION_CSV;
  public static final String FILE_PATH_VISIT = "visit" + FILE_EXTENTION_CSV;
  public static final String FILE_PATH_ALL_URL = "urls" + FILE_EXTENTION_CSV;
  public static final String FILE_PATH_REPORT = "fileCrawlReport" + FILE_EXTENTION_TXT;

  public static final String FILE_PATH_FETCH_REQ = "fetchRequired" + FILE_EXTENTION_CSV;
  public static final String FILE_PATH_VISIT_REQ = "visitRequired" + FILE_EXTENTION_CSV;
  public static final String FILE_PATH_ALL_URL_REQ = "urlsRequired" + FILE_EXTENTION_CSV;
  public static final String FILE_PATH_REPORT_REQ = "CrawlReportVisit" + FILE_EXTENTION_TXT;

  public static final String URL_OK = "OK";
  public static final String URL_NOT_OK = "N_OK";

  public static final int BYTES_TO_KB = 1024;
}
