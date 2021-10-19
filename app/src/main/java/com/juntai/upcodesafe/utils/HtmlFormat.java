package com.juntai.upcodesafe.utils;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlFormat {
    public static String getNewContent(String htmltext) {
        Document doc = Jsoup.parse(htmltext);
        Elements head = doc.getElementsByTag("head");
//        head.get(0).html("<style>*,body,html,div,p,img{border:0;margin:0;padding:0;} </style>");
        head.get(0).html("<link href=\"show_style.css\" type=\"text/css\" rel=\"stylesheet\"/>");
        Elements elements = doc.getElementsByTag("img");
        for (Element element : elements) {
            element.attr("width", "100%").attr("height", "auto");
        }
        return doc.toString();
    }
}
