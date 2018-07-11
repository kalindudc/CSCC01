package csc.summer2018.cscc01;

import csc.summer2018.cscc01.crawler.Crawler;
import csc.summer2018.cscc01.crawler.CrawlerStats;
import csc.summer2018.cscc01.crawler.CustomPageFetcher;
import csc.summer2018.cscc01.util.Constants;
import csc.summer2018.cscc01.util.Shared;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.httpclient.HttpStatus;

public class App {

  /**
   * Set up the app;
   *
   * Reset all shared values
   */
  private static void init() {
    /*
    Reset the shared stats
     */
    Shared.resetFetchAborted();
    Shared.resetFetchAttempted();
    Shared.resetFetchFailed();
    Shared.resetFetchSuccessful();
    Shared.resetTotalUrl();
    Shared.resetUniqueUrlInvalid();
    Shared.resetUniqueUrlValid();
    Shared.resetFileSizeLargerThanOneMb();
    Shared.resetFileSizeLessThanOneKb();
    Shared.resetFileSizeOneHundredKbToOneMb();
    Shared.resetFileSizeOneKbToTenKb();
    Shared.resetFileSizeTenKbToOneHundredKb();
    Shared.resetStatusCodes();
    Shared.resetContentTypes();

    Shared.setSeeds(null);

    /*
    Setup the out put files
     */
    File fetchFile = new File(Constants.FILE_PATH_FETCH);
    File visitFile = new File(Constants.FILE_PATH_VISIT);
    File urlsFile = new File(Constants.FILE_PATH_ALL_URL);
    File reportsFile = new File(Constants.FILE_PATH_REPORT);

    if (fetchFile.exists()) {
      fetchFile.delete();
    }
    if (visitFile.exists()) {
      visitFile.delete();
    }
    if (urlsFile.exists()) {
      urlsFile.delete();
    }
    if (reportsFile.exists()) {
      reportsFile.delete();
    }
  }

  private static void startCrawler(int pages, int depth, boolean generateReq) throws Exception {
    /*
    Set up crawler
    */
    CrawlConfig config = new CrawlConfig();
    config.setCrawlStorageFolder(Constants.CRAWLER_STORAGE);
    config.setIncludeHttpsPages(true);
    config.setMaxPagesToFetch(pages);
    config.setPolitenessDelay(600);
    config.setMaxDepthOfCrawling(depth);

    PageFetcher pageFetcher = new CustomPageFetcher(config);
    RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
    RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
    CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

    /*
    Handle multiple domain names, passed in via command line arguments
     */
    for (String domain : Shared.getSeeds()) {
      controller.addSeed(domain);
    }
    controller.start(Crawler.class, Constants.CRAWLER_NUMBER_OF_THREADS);

    String allUrlsPath = !generateReq ? Constants.FILE_PATH_ALL_URL : Constants.FILE_PATH_ALL_URL_REQ;
    String fetchPath = !generateReq ? Constants.FILE_PATH_FETCH : Constants.FILE_PATH_FETCH_REQ;
    String visitPath = !generateReq ? Constants.FILE_PATH_VISIT : Constants.FILE_PATH_VISIT_REQ;

    List<Object> crawlersLocalData = controller.getCrawlersLocalData();
    for (Object localData : crawlersLocalData) {
      CrawlerStats stats = (CrawlerStats) localData;
      stats.writeUrlsAttempted(allUrlsPath);
      stats.writeVisitedUrls(visitPath);
    }
    ((CustomPageFetcher) pageFetcher).getStats().writeUrlsFetched(fetchPath);
  }

  private static void generateReport(String seed, boolean generateReq) throws IOException {
    StringWriter output = new StringWriter();
    FileWriter fileWriter = new FileWriter(!generateReq ? Constants.FILE_PATH_REPORT : Constants.FILE_PATH_REPORT_REQ, true);
    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
    PrintWriter out = new PrintWriter(bufferedWriter);

    List<Integer> keys = new ArrayList<Integer>(Shared.getStatusCodes().keySet());
    Collections.sort(keys);

    out.println("Name: Kalindu De Costa");
    out.println("UtorID: 1001392619");
    out.println("News site(s) crawled: " + seed);
    out.println("");
    out.println("Fetch Statistics");
    out.println("================");
    out.println("# fetches attempted: " + Shared.getFetchAttempted());
    out.println("# fetches succeeded: " + Shared.getFetchSuccessful());
    out.println("# fetches aborted: " + Shared.getFetchAborted());
    out.println("# fetches failed: " + Shared.getFetchFailed());
    out.println("");
    out.println("Outgoing URLs:");
    out.println("==============");
    out.println("Total URLs extracted: " + Shared.getTotalUrl());
    out.println("# unique URLs extracted: " + (Shared.getUniqueUrlInvalid() + Shared.getUniqueUrlValid()));
    out.println("# unique URLs within News site: " + Shared.getUniqueUrlValid());
    out.println("# unique URLs outside News site: " + Shared.getUniqueUrlInvalid());
    out.println("");
    out.println("Status Codes:");
    out.println("=============");
    for (Integer key: keys) {
      out.println(key + " " + HttpStatus.getStatusText(key) + ": " + Shared.getStatusCodes().get(key));
    }
    out.println("");
    out.println("File Sizes:");
    out.println("===========");
    out.println("< 1KB: " + Shared.getFileSizeLessThanOneKb());
    out.println("1KB ~ <10KB: " + Shared.getFileSizeOneKbToTenKb());
    out.println("10KB ~ <100KB: " + Shared.getFileSizeTenKbToOneHundredKb());
    out.println("100KB ~ <1MB: " + Shared.getFileSizeOneHundredKbToOneMb());
    out.println(">= 1MB: " + Shared.getFileSizeLargerThanOneMb());
    out.println("");
    out.println("Content Types:");
    out.println("==============");
    for (String key: Shared.getContentTypes().keySet()) {
      out.println("" + key + ": " + Shared.getContentTypes().get(key));
    }

    out.close();
    bufferedWriter.close();
    fileWriter.close();
  }

  public static void main(String[] args) throws Exception {

    /*
    Initialize command line options with mandatory flags
     */
    Options options = new Options();

    Option seedOption = new Option("s", "seed", true, "a list of comma seperated websites");
    seedOption.setRequired(true);
    options.addOption(seedOption);

    Option depthOption = new Option("d", "depth", true, "maximum depth for the crawler");
    depthOption.setRequired(true);
    options.addOption(depthOption);

    Option pagesOption = new Option("p", "pages", true, "maximum number of pages to crawl");
    pagesOption.setRequired(true);
    options.addOption(pagesOption);

    Option generateReqOption = new Option("req", "req", false, "generate the required statistic files");
    generateReqOption.setRequired(false);
    options.addOption(generateReqOption);

    CommandLineParser parser = new DefaultParser();
    HelpFormatter formatter = new HelpFormatter();
    CommandLine cmd = null;

    try {
      cmd = parser.parse(options, args);
    } catch (ParseException e) {
      System.out.println(e.getMessage());
      formatter.printHelp("utility-name", options);

      System.exit(1);
    }

    init();

    // get the list of do mains along with the depth size and the max number of pages
    String[] seeds = cmd.getOptionValue("seed").split(",");
    int depth = Integer.parseInt(cmd.getOptionValue("depth"));
    int pages = Integer.parseInt(cmd.getOptionValue("pages"));
    boolean generateReq = cmd.hasOption("req");

    Set<String> seedSet = new HashSet<String>();

    for(String seed: seeds) {
      seedSet.add(seed);
      if (seed.startsWith("http://")) {
        seedSet.add("https://" + seed.substring(7));
      }
      else {
        seedSet.add("http://" + seed.substring(8));
      }
    }
    Shared.setSeeds(new ArrayList<String>(seedSet));

    startCrawler(pages, depth, generateReq);
    generateReport(cmd.getOptionValue("seed"), generateReq);
  }
}
