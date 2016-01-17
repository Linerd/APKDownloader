import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class appChinaDownloader {

	static String appUrl = "http://www.appchina.com/category/30/1_1_###_1_0_0_0.html";

	static BufferedWriter bw;

	public static void parse(BufferedWriter bw, String urlHeader) throws InterruptedException {
		int i =1;
		int count = 0;
		int total = 0;
		do {
			count = 0;
			String url = urlHeader.replaceAll("###", i+"");
			try {
				Document doc = Jsoup.connect(url).timeout(5000).get();
				Iterator<Element> ite = doc.select("div[class=app-intro]").select("a[class^=download_app]").iterator();
				while(ite.hasNext()) {
					Element e = ite.next();
					URL downloadURL = new URL(e.attr("href"));
//					String filename = downloadURL.toString().split("fn=")[1].split("&k=")[0];
					System.out.println(downloadURL.toString());
//					System.out.println(filename);
//					ReadableByteChannel rbc = Channels.newChannel(downloadURL.openStream());
//					FileOutputStream fos = new FileOutputStream(filename);
//					fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
					bw.write(downloadURL+"\n");
					count++;
					total++;
//					rbc.close();
//					fos.close();
				}
				Thread.sleep(500);
			} catch (IOException e) {
				e.printStackTrace();
			}
			i++;
		}while(count>0);
		System.out.println("total: "+total);
	}

	public static void main(String[] args) throws InterruptedException {
		try {
			bw = new BufferedWriter(new FileWriter("appChina-app.txt"));
			parse(bw, appUrl);
			bw.close();
//			bw = new BufferedWriter(new FileWriter("game.url"));
//			parse(bw, gameUrlHeader);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
