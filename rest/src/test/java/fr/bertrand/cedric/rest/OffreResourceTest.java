package fr.bertrand.cedric.rest;

import static javax.servlet.http.HttpServletResponse.SC_OK;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.custommonkey.xmlunit.XMLUnit.setIgnoreComments;
import static org.custommonkey.xmlunit.XMLUnit.setIgnoreWhitespace;
import static org.custommonkey.xmlunit.XMLUnit.setNormalizeWhitespace;
import static org.fest.assertions.Assertions.assertThat;
import static org.jboss.resteasy.mock.MockDispatcherFactory.createDispatcher;
import static org.jboss.resteasy.mock.MockHttpRequest.get;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;
import org.custommonkey.xmlunit.Diff;
import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.bertrand.cedric.rest.OffreResource;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:applicationContext-datasource.xml" })
public class OffreResourceTest {
	private final Dispatcher dispatcher = createDispatcher();

	@Autowired
	private OffreResource resource;

	@Before
	public void before() {
		dispatcher.getRegistry().addSingletonResource(resource);

		setIgnoreWhitespace(true);
		setNormalizeWhitespace(true);
		setIgnoreComments(true);
	}

	@Test
	public void testGetXML() throws Exception {
		final MockHttpRequest request = get("/offre/codeVillage/1/codeOccupation/1/codeVilleDepartDemande/ABCDEF");
		final MockHttpResponse response = new MockHttpResponse();
		dispatcher.invoke(request, response);

		assertThat(response.getStatus()).isEqualTo(SC_OK);

		final InputStream expectedInputStream = getClass().getResourceAsStream("data.xml");
		// Z means: "The end of the input but for the final terminator, if any"
		final String expected = new Scanner(expectedInputStream).useDelimiter("\\Z").next();

		String actual = response.getContentAsString();
		final Diff differences = new Diff(expected, actual);

		assertThat(differences.similar()).as(actual).isTrue();
	}

	@Test
	public void testGetJSON() throws Exception {
		final MockHttpRequest request = get("/offre/codeVillage/1/codeOccupation/1/codeVilleDepartDemande/ABCDEF").accept(APPLICATION_JSON);
		final MockHttpResponse response = new MockHttpResponse();
		dispatcher.invoke(request, response);

		assertThat(response.getStatus()).isEqualTo(SC_OK);

		final InputStream expectedInputStream = getClass().getResourceAsStream("data.json");
		StringWriter writer = new StringWriter();
		IOUtils.copy(expectedInputStream, writer);
		assertThat(response.getContentAsString()).isEqualTo(writer.getBuffer().toString());
	}
}
