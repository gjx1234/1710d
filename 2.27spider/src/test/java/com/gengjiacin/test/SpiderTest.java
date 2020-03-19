package com.gengjiacin.test;

import java.io.IOException;

import org.jsoup.nodes.Element;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.gengjiaxin.FileUtilIO;

public class SpiderTest {

	public static void main(String[] args) throws IOException {
		Connection connect = Jsoup.connect("https://news.sohu.com/");
		Document document = connect.get();
		Elements select = document.select(".list16");
		for (Element div : select) {
			Elements as = div.select("a");
			for (Element a : as) {
				String url = a.attr("href");
				String title = a.attr("title");
				System.out.println(title);
				if(!url.startsWith("http")) {
					url="https:"+url;
				}
				System.out.println(url);
				Document document2 = Jsoup.connect(url).get();
				String text = null;
				Elements select2 = document2.select("article");
				for (Element element : select2) {
					text = element.text();
					System.out.println(text);
				}
				FileUtilIO.writeFile("D:\\practice\\"+title+".txt", text, "utf-8");
			}
		}
	}
	
}
