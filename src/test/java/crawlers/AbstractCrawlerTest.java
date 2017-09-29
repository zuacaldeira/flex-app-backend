/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawlers;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author zua
 */
public abstract class AbstractCrawlerTest {

    protected abstract FlexNewsCrawler getCrawler();

    @Test
    public abstract void testGetMySource();

    @Test
    public void testGetArticles() {
        System.out.println("getArticles");
        FlexNewsCrawler crawler = getCrawler();
        //assertNotNull(getArticles(crawler));
    }

    @Test
    public void testGetUrl() {
        System.out.println("getUrl");
        FlexNewsCrawler crawler = getCrawler();
        Elements articles = getArticles(crawler);
        if(articles != null) {
            String url = crawler.getUrl(articles.get(0));
            assertNotNull(url);
        }
    }

    @Test
    public void testGetTitle() {
        System.out.println("getTitle");
        FlexNewsCrawler crawler = getCrawler();

        Document document = getArticleDocumentPage(crawler);
        if(document != null) {
            assertNotNull(document);
            String title = crawler.getTitle(document);
            assertNotNull(title);
            assertFalse(title.isEmpty());
        }
    }

    @Test
    public void testGetImageUrl() {
        System.out.println("getImageUrl");
        FlexNewsCrawler crawler = getCrawler();

        Document document = getArticleDocumentPage(crawler);
        if (document != null) {
            assertNotNull(document);
            String image = crawler.getImageUrl(document);
            /*assertNotNull(image);
            assertFalse(image.isEmpty());*/
        }
    }

    @Test
    public void testGetContent() {
        System.out.println("getContent");
        FlexNewsCrawler crawler = getCrawler();
        Document document = getArticleDocumentPage(crawler);
        if (document != null) {
            assertNotNull(document);
            String content = crawler.getContent(document);
            assertNotNull(content);
            assertFalse(content.isEmpty());
        }
    }

    @Test
    public void testGetAuthors() {
        System.out.println("getAuthors");
        FlexNewsCrawler crawler = getCrawler();
        Document document = getArticleDocumentPage(crawler);
        if (document != null) {
            assertNotNull(document);
            String authors = crawler.getAuthorsValue(document);
            assertNotNull(authors);
            assertFalse(authors.isEmpty());
        }

    }

    @Test
    public void testGetTime() {
        System.out.println("getTime");
        FlexNewsCrawler crawler = getCrawler();
        Document document = getArticleDocumentPage(crawler);
        if(document != null) {
            assertNotNull(document);
            String time = crawler.getTimeValue(document);
            assertNotNull(time);
            assertFalse(time.isEmpty());
        }
    }

    @Test
    public void openDocument() {
        Document document = getCrawler().openDocument(getCrawler().getMySource().getUrl());
    }

    @Test
    public void openDocumentFail() {
        Document document = getCrawler().openDocument("");
        assertNull(document);
    }

    @Test
    public void testGetSource() {
        assertEquals(getCrawler().getMySource(), getCrawler().getSource());
    }

    @Test
    public void testImportArticle() {
        Document document = getCrawler().openDocument(getCrawler().getMySource().getUrl());
        if(document != null) {
            Element article = getCrawler().getArticles(document).get(0);
            getCrawler().importArticle(article, getCrawler().getMySource());
        }
    }

    protected Elements getArticles(FlexNewsCrawler crawler) {
        Document document = crawler.openDocument(crawler.getMySource().getUrl());
        if(document != null) {
            return crawler.getArticles(document);
        }
        return null;
    }

    protected Document getArticleDocumentPage(FlexNewsCrawler crawler) {
        Elements articles = getArticles(crawler);
        if(articles != null) {
            assertTrue(articles.size() > 0);
            Element article = articles.get(0);
            assertNotNull(article);
            String url = crawler.getUrl(article);
            assertNotNull(url);
            Document document = crawler.openDocument(url);
            return document;
        }
        return null;
    }

}
