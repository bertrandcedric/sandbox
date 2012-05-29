package fr.bertrand.cedric.controller;

import java.io.IOException;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;

import fr.bertrand.cedric.server.HttpServer;

public class ResourceController implements IController {

	@Override
	public byte[] render(String... file) {
		try {
			StringWriter writer = new StringWriter();
			IOUtils.copy(getClass().getResourceAsStream(file[0].replace(HttpServer.RESOURCES, "")), writer);
			return writer.getBuffer().toString().getBytes();
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	public String getContentType() {
		return APPLICATION_HTML_CHARSET_UTF_8;
	}
}
