package fr.bertrand.cedric.rest;

import static fr.bertrand.cedric.model.XmlCRCNamespace.VALUE;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_XML;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.jboss.resteasy.annotations.providers.jaxb.json.Mapped;
import org.jboss.resteasy.annotations.providers.jaxb.json.XmlNsMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import fr.bertrand.cedric.model.Offre;
import fr.bertrand.cedric.service.OffreService;

@Path("/offre")
@Service
public class OffreResource {
	private final static Logger logger = LoggerFactory.getLogger(OffreResource.class);

	@Autowired
	private OffreService service;

	@GET
	@Produces({ TEXT_XML, APPLICATION_JSON })
	@Mapped(namespaceMap = { @XmlNsMap(namespace = VALUE, jsonName = "") })
	@Path("/codeVillage/{codeVillage}/codeOccupation/{codeOccupation}/codeVilleDepartDemande/{codeVilleDepartDemande}")
	public List<Offre> get(//
			@PathParam("codeVillage") final String codeVillage, //
			@PathParam("codeOccupation") final int codeOccupation, //
			@PathParam("codeVilleDepartDemande") final String codeVilleDepartDemande) {
		logger.info("get codeVillage = " + codeVillage);
		logger.info("get codeOccupation = " + codeOccupation);
		logger.info("get codeVilleDepartDemande = " + codeVilleDepartDemande);
		return service.find(codeVillage, codeOccupation, codeVilleDepartDemande);
	}

	@GET
	@Produces({ TEXT_XML, APPLICATION_JSON })
	@Mapped(namespaceMap = { @XmlNsMap(namespace = VALUE, jsonName = "") })
	@Path("/promotion/{codePromotion}")
	public List<Offre> getByPromotion(//
			@PathParam("codePromotion") final String codePromotion) {
		logger.info("get codePromotion = " + codePromotion);
		return service.findByPromotion(codePromotion);
	}
}
