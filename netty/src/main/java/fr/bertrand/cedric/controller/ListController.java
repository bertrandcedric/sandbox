package fr.bertrand.cedric.controller;

import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

public class ListController implements IController {

	@Override
	public byte[] render(String... file) {
		List<String> values = Arrays.asList("toto1", "toto2", "toto3");
		return new Gson().toJson(values).getBytes();
	}

	@Override
	public String getContentType() {
		return APPLICATION_JSON_CHARSET_UTF_8;
	}
}
