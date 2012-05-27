package fr.bertrand.cedric.controller;

public interface IController {

	String APPLICATION_JSON_CHARSET_UTF_8 = "application/json; charset=UTF-8";
	String APPLICATION_HTML_CHARSET_UTF_8 = "text/html; charset=UTF-8";

	String render();

	String getContentType();

}