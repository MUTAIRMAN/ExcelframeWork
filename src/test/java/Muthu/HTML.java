package Muthu;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.io.FileHandler;

public class HTML {

	public static void main(String[] args) throws IOException {
		HTML.createHtml("0011", "Login", "Passed");
		HTML.createHtml("0022", "LogOut", "Failed");
		HTML.createHtml("0033", "Payment", "Failed");
		HTML.createHtml("0044", "1Payment", "Failed");
		HTML.createHtml("0055", "2Payment", "Failed");

	}

	public static void createHtml(String s1, String s2, String s3) throws IOException {
		String fileName = "Muthu_.html";
		String projectPath = System.getProperty("user.dir");
		String tempFile = projectPath + File.separator + fileName;
		File file = new File(tempFile);

		if (file.exists()) {
			Document doc = Jsoup.parse(file, "utf-8");
			doc.head().appendElement("title").text("Selenium Test");
			doc.head().appendElement("style")
					.text("table {\r\n" + "  font-family: arial, sans-serif;\r\n" + "  border-collapse: collapse;\r\n"
							+ "  width: 100%;\r\n" + "}\r\n" + "\r\n" + "td, th {\r\n"
							+ "  border: 1px solid #dddddd;\r\n" + "  text-align: left;\r\n" + "  padding: 8px;\r\n"
							+ "}\r\n" + "\r\n" + "th {\r\n" + "  background-color: #dddddd;\r\n" + "}");
			Element table = doc.body().getElementById("muthu");
			table.appendElement("tr").appendElement("td").text(s1).appendElement("td").text(s2).appendElement("td")
					.text(s3);

			OutputStream outputStream = new FileOutputStream(file.getAbsoluteFile());
			Writer writer = new OutputStreamWriter(outputStream);
			writer.write("");
			writer.write(doc.toString());
			writer.close();

		}

		else {
			try {

				Document doc = Jsoup.parse("<html></html>");
				doc.head().appendElement("title").text("Selenium Test");
				doc.head().appendElement("style").text("table {\r\n" + "  font-family: arial, sans-serif;\r\n"
						+ "  border-collapse: collapse;\r\n" + "  width: 100%;\r\n" + "}\r\n" + "\r\n" + "td, th {\r\n"
						+ "  border: 1px solid #dddddd;\r\n" + "  text-align: left;\r\n" + "  padding: 8px;\r\n"
						+ "}\r\n" + "\r\n" + "th {\r\n" + "  background-color: #dddddd;\r\n" + "}");
				Element table = doc.body().appendElement("table").attr("border", "1").attr("bordercolor", "#000000")
						.attr("id", "muthu").attr("bgcolor", "#F5A9BC");
				table.appendElement("tr").appendElement("th").text("TestId").appendElement("th").text("TestName")
						.appendElement("th").text("TestResult");
				// table.appendElement("tr").appendElement("td").text(s1).appendElement("tr").appendElement("th").text("TestName").appendElement("tr").appendElement("th").text("TestResult");
				table.appendElement("tr").appendElement("td").text(s1).appendElement("td").text(s2).appendElement("td")
						.text(s3);

				OutputStream outputStream = new FileOutputStream(file.getAbsoluteFile());
				Writer writer = new OutputStreamWriter(outputStream);
				writer.write(doc.toString());
				writer.close();
				/*
				 * // define a HTML String Builder
				 * 
				 * 
				 * StringBuilder htmlStringBuilder = new StringBuilder(); // append html header
				 * and title
				 * htmlStringBuilder.append("<html><head><title>Selenium Test </title></head>");
				 * // append body htmlStringBuilder.append("<body>"); // append table
				 * htmlStringBuilder.append("<table id=\"muthu\"");//
				 * border=\"1\" bordercolor=\"#000000\"> // append row htmlStringBuilder
				 * .append(
				 * "<tr><td><b>TestId</b></td><td><b>TestName</b></td><td><b>TestResult</b></td></tr>"
				 * ); // append row htmlStringBuilder.append("<tr><td>" + s1 + "</td><td>" + s2
				 * + "</td><td>" + s3 + "</td></tr>");
				 * 
				 * // close html file htmlStringBuilder.append("</table></body></html>"); //
				 * write html string content to a file // if file does exists, then delete and
				 * create a new file // write to file with OutputStreamWriter OutputStream
				 * outputStream = new FileOutputStream(file.getAbsoluteFile()); Writer writer =
				 * new OutputStreamWriter(outputStream);
				 * writer.write(htmlStringBuilder.toString()); writer.close();
				 * 
				 */
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}