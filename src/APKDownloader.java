import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class APKDownloader {

	static String appUrlHeader = "https://apkpure.com/app?sort=download&page=";
	static String gameUrlHeader = "https://apkpure.com/game?sort=download&page=";
	static BufferedWriter bw;

	public static void parse(BufferedWriter bw, String urlHeader) throws InterruptedException {
		int i =1;
		int count = 0;
		int total = 0;
		do {
			count = 0;
			String url = urlHeader+i;
			try {
				Document doc = Jsoup.connect(url).timeout(2000).get();
				Iterator<Element> ite = doc.select("a:contains(Download APK)").iterator();
				while(ite.hasNext()) {
					Element e = ite.next();
					URL downloadURL = new URL(e.attr("href"));
//					String filename = downloadURL.toString().split("fn=")[1].split("&k=")[0];
//					System.out.println(downloadURL.toString());
//					System.out.println(filename);
//					ReadableByteChannel rbc = Channels.newChannel(downloadURL.openStream());
//					FileOutputStream fos = new FileOutputStream(filename);
//					fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
					bw.write(downloadURL+"\n");
					count++;
					total++;
//					Thread.sleep(1000);
//					rbc.close();
//					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			i++;
		}while(count>0);
		System.out.println("total: "+total);
	}

	public static void main(String[] args) throws InterruptedException {
		try {
			bw = new BufferedWriter(new FileWriter("app.url"));
			parse(bw, appUrlHeader);
			bw.close();
			bw = new BufferedWriter(new FileWriter("game.url"));
			parse(bw, gameUrlHeader);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
