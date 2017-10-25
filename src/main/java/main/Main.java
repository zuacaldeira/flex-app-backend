/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import crawlers.dbCompletion.DBCompletionCrawler;
import services.NewsSourceService;

/**
 *
 * @author zua
 */
public class Main {
    
    public static void main(String... args) {
        DBCompletionCrawler dbCrawler = new DBCompletionCrawler();
        dbCrawler.setSourcesService(new NewsSourceService());
        dbCrawler.crawl();
    }
}
