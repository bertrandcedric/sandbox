package controllers;

import play.api.templates.Html;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.start;

public class Test extends Controller {

	public static Result index() {
		return ok(start.render("Your new application is ready.", new Html("test")));
	}
}