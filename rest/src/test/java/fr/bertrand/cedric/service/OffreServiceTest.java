package fr.bertrand.cedric.service;

import static org.fest.assertions.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.bertrand.cedric.model.Offre;
import fr.bertrand.cedric.service.OffreService;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:applicationContext-datasource.xml" })
public class OffreServiceTest {
	@Autowired
	private OffreService service;

	@Test
	public void find() {
		final List<Offre> actuals = service.find("1     ", 1, "ABCDEF");
		assertThat(actuals).hasSize(1);
		Offre actual = actuals.get(0);
		assertThat(actual).isNotNull();
		assertThat(actual.categorieTarif).isEqualTo("ABCDEF");
		assertThat(actual.codeDeviseCommercial).isEqualTo("YYY");
		assertThat(actual.codeInfoDispoOccupation).isEqualTo(1);
		assertThat(actual.codeLogementCommercial).isEqualTo("ABCDEF");
		assertThat(actual.codeOccupation).isEqualTo(1);
		assertThat(actual.codePaysCommercial).isEqualTo("ZZZ");
		assertThat(actual.codePromotion).isEqualTo("ABCDEF");
		assertThat(actual.codeTerminalAeroport).isEqualTo("ABCDEF");
		assertThat(actual.codeVillage).isEqualTo("1     ");
		assertThat(actual.codeVilleDepartDemande).isEqualTo("ABCDEF");
		assertThat(actual.codeVilleIataArrivee).isEqualTo("ABCDEF");
		assertThat(actual.codeVilleIataDepart).isEqualTo("ABCDEF");
		assertThat(actual.dateDepart).isInstanceOf(Date.class);
		assertThat(actual.dureeTotale).isEqualTo(7);
		assertThat(actual.montant).isEqualTo(new BigDecimal("124"));
		assertThat(actual.montantPromotion).isEqualTo(new BigDecimal("125"));
		assertThat(actual.nombreNuit).isEqualTo(5);
		assertThat(actual.nombrePersonneAdulte).isEqualTo(1);
		assertThat(actual.offreNet).isEqualTo(true);
		assertThat(actual.prixVenteGds).isEqualTo(new BigDecimal("123"));
		assertThat(actual.transportRisqueFinancier).isEqualTo(true);
		assertThat(actual.typeElargissement).isEqualTo("ABCDEFGHIJ");
	}
}
