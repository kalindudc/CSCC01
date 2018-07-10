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
import java.io.File;
import java.util.List;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class App {

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

    // get the list of do mains along with the depth size and the max number of pages
    Shared.domains = cmd.getOptionValue("seed").split(",");
    int depth = Integer.parseInt(cmd.getOptionValue("depth"));
    int pages = Integer.parseInt(cmd.getOptionValue("pages"));

    /*
    Setup the out put files
     */
    File fetchFile = new File(Constants.FILE_PATH_FETCH);
    File visitFile = new File(Constants.FILE_PATH_VISIT);
    File urlsFile = new File(Constants.FILE_PATH_ALL_URL);

    if (fetchFile.exists()) {
      fetchFile.delete();
    }
    if (visitFile.exists()) {
      visitFile.delete();
    }
    if (urlsFile.exists()) {
      urlsFile.delete();
    }

    /*
    Set up crawler
    */
    CrawlConfig config = new CrawlConfig();
    config.setCrawlStorageFolder(Constants.CRAWLER_STORAGE);
    config.setMaxPagesToFetch(pages);
    config.setPolitenessDelay(1000);
    config.setMaxDepthOfCrawling(depth);

    PageFetcher pageFetcher = new CustomPageFetcher(config);
    RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
    RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
    CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

    /*
    Handle multiple domain names, passed in via command line arguments
     */
    for (String domain : Shared.domains) {
      controller.addSeed(domain);
    }
    controller.start(Crawler.class, Constants.CRAWLER_NUMBER_OF_THREADS);

    List<Object> crawlersLocalData = controller.getCrawlersLocalData();
    for (Object localData : crawlersLocalData) {
      CrawlerStats stats = (CrawlerStats) localData;
      stats.writeUrlsAttempted(urlsFile.getPath());
      stats.writeVisitedUrls(visitFile.getPath());
    }
    ((CustomPageFetcher) pageFetcher).getStats().writeUrlsFetched(fetchFile.getPath());
  }
}
