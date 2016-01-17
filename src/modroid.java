import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class modroid {

	static String appUrlHeader = "http://modroid.co.nf/";
	static BufferedWriter bw;

	public static void parse(BufferedWriter bw, String url) throws InterruptedException {
		try {
			Document doc = Jsoup.connect(url).timeout(2000).get();
			Iterator<Element> ite = doc.select("div#appz").select("b").select("a").iterator();
			while(ite.hasNext()) {
				Element e = ite.next();
				System.out.println(e.attr("href"));
				// URL downloadURL = new URL(e.attr("href"));
				//					String filename = downloadURL.toString().split("fn=")[1].split("&k=")[0];
				//					System.out.println(downloadURL.toString());
				//					System.out.println(filename);
				//					ReadableByteChannel rbc = Channels.newChannel(downloadURL.openStream());
				//					FileOutputStream fos = new FileOutputStream(filename);
				//					fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				bw.write(url+e.attr("href")+"\n");
//				count++;
//				total++;
				//					Thread.sleep(1000);
				//					rbc.close();
				//					fos.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		try {
			bw = new BufferedWriter(new FileWriter("modroid.txt"));
			parse(bw, appUrlHeader);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
