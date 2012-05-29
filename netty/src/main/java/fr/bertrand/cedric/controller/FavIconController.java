package fr.bertrand.cedric.controller;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class FavIconController<T> implements IController {

	@Override
	public byte[] render() {
		try {
			return FileUtils.readFileToByteArray(new File("favicon.png"));
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	public String getContentType() {
		return APPLICATION_HTML_CHARSET_UTF_8;
	}
}
