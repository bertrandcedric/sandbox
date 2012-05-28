package fr.bertrand.cedric.controller;

import org.junit.Test;

import fr.bertrand.cedric.WebTesterHelper;

public class HomeControllerTest extends WebTesterHelper {

	@Test
	public void showHome() {
		beginAt("/home");

		assertTextPresent("Test");
	}
}