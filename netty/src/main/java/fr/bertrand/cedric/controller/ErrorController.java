package fr.bertrand.cedric.controller;

import java.io.IOException;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
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
	public byte[] render(String... file) {
		try {
			StringWriter writer = new StringWriter();
			IOUtils.copy(getClass().getResourceAsStream("/html/error.html"), writer);
			ST template = new ST(writer.getBuffer().toString(), '$', '$');

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
