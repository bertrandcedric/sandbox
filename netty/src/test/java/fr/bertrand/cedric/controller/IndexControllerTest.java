package fr.bertrand.cedric.controller;

import org.junit.Test;

import fr.bertrand.cedric.WebTesterHelper;

public class IndexControllerTest extends WebTesterHelper {

	@Test
	public void index() {
		beginAt("/");

		assertTextPresent("Test");
	}
}