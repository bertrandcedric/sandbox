package fr.bertrand.cedric.controller;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.stringtemplate.v4.ST;

public class IndexController implements IController {

	@Override
	public byte[] render() {
		try {
			String html = FileUtils.readFileToString(new File("index.html"));
			ST template = new ST(html, '$', '$');

			template.add("data", "essai");

			return template.render().getBytes();
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	public String getContentType() {
		return APPLICATION_HTML_CHARSET_UTF_8;
	}

}
