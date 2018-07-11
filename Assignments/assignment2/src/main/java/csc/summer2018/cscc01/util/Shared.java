package csc.summer2018.cscc01.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Shared {

  private static Map<String, Integer> contentTypes = new HashMap<String, Integer>();

  private static int fetchAttempted = 0;
  private static int fetchSuccessful = 0;
  private static int fetchAborted = 0;
  private static int fetchFailed = 0;

  private static int fileSizeLessThanOneKb = 0;
  private static int fileSizeOneKbToTenKb = 0;
  private static int fileSizeTenKbToOneHundredKb = 0;
  private static int fileSizeOneHundredKbToOneMb = 0;
  private static int fileSizeLargerThanOneMb = 0;

  private static Map<Integer, Integer> statusCodes = new HashMap<Integer, Integer>();

  private static List<String> seeds = new ArrayList<String>();

  private static int totalUrl = 0;
  private static int uniqueUrlValid = 0;
  private static int uniqueUrlInvalid = 0;

  public static synchronized void setSeeds(List<String> newDomains) {
    seeds = newDomains;
  }

  public static synchronized List<String> getSeeds() {
    return seeds;
  }

  public static synchronized int getFetchAttempted() {
    return fetchAttempted;
  }

  public static synchronized int getFetchSuccessful() {
    return fetchSuccessful;
  }

  public static synchronized int getFetchAborted() {
    return fetchAborted;
  }

  public static synchronized int getFetchFailed() {
    return fetchFailed;
  }

  public static synchronized void resetFetchAttempted() {
    fetchAttempted = 0;
  }

  public static synchronized void resetFetchSuccessful() {
    fetchSuccessful = 0;
  }

  public static synchronized void resetFetchAborted() {
    fetchAborted = 0;
  }

  public static synchronized void resetFetchFailed() {
    fetchFailed = 0;
  }

  public static synchronized void incFetchAttempted() {
    fetchAttempted++;
  }

  public static synchronized void incFetchSuccessful() {
    fetchSuccessful++;
  }

  public static synchronized void incFetchAborted() {
    fetchAborted++;
  }

  public static synchronized void incFetchFailed() {
    fetchFailed++;
  }

  public static synchronized int getTotalUrl() {
    return totalUrl;
  }

  public static synchronized int getUniqueUrlValid() {
    return uniqueUrlValid;
  }

  public static synchronized int getUniqueUrlInvalid() {
    return uniqueUrlInvalid;
  }

  public static synchronized void resetTotalUrl() {
    totalUrl = 0;
  }

  public static synchronized void resetUniqueUrlValid() {
    uniqueUrlValid = 0;
  }

  public static synchronized void resetUniqueUrlInvalid() {
    uniqueUrlInvalid++;
  }

  public static synchronized void incTotalUrl() {
    totalUrl++;
  }

  public static synchronized void incUniqueUrlValid() {
    uniqueUrlValid++;
  }

  public static synchronized void incUniqueUrlInvalid() {
    uniqueUrlInvalid++;
  }

  public static synchronized int getFileSizeLessThanOneKb() {
    return fileSizeLessThanOneKb;
  }

  public static synchronized int getFileSizeOneKbToTenKb() {
    return fileSizeOneKbToTenKb;
  }

  public static synchronized int getFileSizeTenKbToOneHundredKb() {
    return fileSizeTenKbToOneHundredKb;
  }

  public static synchronized int getFileSizeOneHundredKbToOneMb() {
    return fileSizeOneHundredKbToOneMb;
  }

  public static synchronized int getFileSizeLargerThanOneMb() {
    return fileSizeLargerThanOneMb;
  }

  public static synchronized void resetFileSizeLessThanOneKb() {
    fileSizeLessThanOneKb = 0;
  }

  public static synchronized void resetFileSizeOneKbToTenKb() {
    fileSizeOneKbToTenKb = 0;
  }

  public static synchronized void resetFileSizeTenKbToOneHundredKb() {
    fileSizeTenKbToOneHundredKb = 0;
  }

  public static synchronized void resetFileSizeOneHundredKbToOneMb() {
    fileSizeOneHundredKbToOneMb = 0;
  }

  public static synchronized void resetFileSizeLargerThanOneMb() {
    fileSizeLargerThanOneMb = 0;
  }

  public static synchronized void incFileSizeLessThanOneKb() {
    fileSizeLessThanOneKb++;
  }

  public static synchronized void incFileSizeOneKbToTenKb() {
    fileSizeOneKbToTenKb++;
  }

  public static synchronized void incFileSizeTenKbToOneHundredKb() {
    fileSizeTenKbToOneHundredKb++;
  }

  public static synchronized void incFileSizeOneHundredKbToOneMb() {
    fileSizeOneHundredKbToOneMb++;
  }

  public static synchronized void incFileSizeLargerThanOneMb() {
    fileSizeLargerThanOneMb++;
  }

  public static synchronized Map<Integer, Integer> getStatusCodes() {
    return statusCodes;
  }

  public static synchronized void resetStatusCodes() {
    statusCodes = new HashMap<Integer, Integer>();
  }

  public static synchronized void incStatusCodes(Integer key) {
    if (statusCodes.containsKey(key)) {
      statusCodes.put(key, statusCodes.get(key) + 1);
    } else {
      statusCodes.put(key, 0);
    }
  }

  public static synchronized Map<String, Integer> getContentTypes() {
    return contentTypes;
  }

  public static synchronized void resetContentTypes() {
    contentTypes = new HashMap<String, Integer>();
  }

  public static synchronized void incContentTypes(String key) {
    if (contentTypes.containsKey(key)) {
      contentTypes.put(key, contentTypes.get(key) + 1);
    } else {
      contentTypes.put(key, 0);
    }
  }
}
