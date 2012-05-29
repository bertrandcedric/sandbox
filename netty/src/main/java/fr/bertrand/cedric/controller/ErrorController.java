package fr.bertrand.cedric.controller;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stringtemplate.v4.ST;

import ch.qos.logback.classic.LoggerContext;

public class ErrorController implements IController {

	private final static Logger LOGGER = ((LoggerContext) LoggerFactory.getILoggerFactory()).getLogger("ROOT");
	private final String msg;

	public ErrorController(String msg) {
		this.msg = msg;
		LOGGER.error(msg);
	}

	@Override
	public byte[] render() {
		try {
			String html = FileUtils.readFileToString(new File("error.html"));
			ST template = new ST(html, '$', '$');

			template.add("error", msg);

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
