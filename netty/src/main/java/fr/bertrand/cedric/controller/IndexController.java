package fr.bertrand.cedric.controller;

import java.io.IOException;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.stringtemplate.v4.ST;

public class IndexController implements IController {

	@Override
	public byte[] render(String... file) {
		try {
			StringWriter writer = new StringWriter();
			IOUtils.copy(getClass().getResourceAsStream("/html/index.html"), writer);

			ST template = new ST(writer.getBuffer().toString(), '$', '$');
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
