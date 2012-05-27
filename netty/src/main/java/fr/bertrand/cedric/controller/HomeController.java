package fr.bertrand.cedric.controller;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.stringtemplate.v4.ST;

public class HomeController implements IController {

	@Override
	public String render() {
		try {
			String html = FileUtils.readFileToString(new File("index.html"));
			ST template = new ST(html, '$', '$');
			template.add("data", "essai");

			return template.render();
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	public String getContentType() {
		return APPLICATION_HTML_CHARSET_UTF_8;
	}
}
