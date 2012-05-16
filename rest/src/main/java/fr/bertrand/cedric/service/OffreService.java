package fr.bertrand.cedric.service;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import fr.bertrand.cedric.model.Offre;

@Service
public class OffreService {

	private static final String SCHEMA_DB = "/schema.txt";
	private static final String INSERT_DB = "/insert.txt";

	@Value("${createData}")
	private boolean createData;

	@Value("${insertData}")
	private boolean insertData;

	@Autowired
	private JdbcTemplate template;

	@PostConstruct
	public void insertData() throws IOException {
		if (createData) {
			StringWriter writer = new StringWriter();
			IOUtils.copy(getClass().getResourceAsStream(SCHEMA_DB), writer);
			template.execute(writer.getBuffer().toString());
		}
		if (insertData) {
			StringWriter writer = new StringWriter();
			IOUtils.copy(getClass().getResourceAsStream(INSERT_DB), writer);
			template.batchUpdate(writer.getBuffer().toString().split("\n"));
		}
	}

	public List<Offre> find(final String codeVillage, final int codeOccupation, final String codeVilleDepartDemande) {
		return template.query("SELECT * FROM OFFRE WHERE CODEVILLAGE = ? and CODEOCCUPATION = ? and CODEVILLEDEPARTDEM = ?", //
				new Object[] { codeVillage, codeOccupation, codeVilleDepartDemande },//
				new RowMapper<Offre>() {
					@Override
					public Offre mapRow(ResultSet rs, int rowNum) throws SQLException {
						Offre element = new Offre();
						element.categorieTarif = rs.getString("CATEGORIETARIF");
						element.codeDeviseCommercial = rs.getString("CODEDEVISECOMMERCI");
						element.codeInfoDispoOccupation = rs.getInt("CODEINFODISPOOCCUP");
						element.codeLogementCommercial = rs.getString("CODELOGEMENTCOMMER");
						element.codeOccupation = rs.getInt("CODEOCCUPATION");
						element.codePaysCommercial = rs.getString("CODEPAYSCOMMERCIAL");
						element.codePromotion = rs.getString("CODEPROMOTION");
						element.codeTerminalAeroport = rs.getString("CODETERMINALAEROPO");
						element.codeVillage = rs.getString("CODEVILLAGE");
						element.codeVilleDepartDemande = rs.getString("CODEVILLEDEPARTDEM");
						element.codeVilleIataArrivee = rs.getString("CODEVILLEIATAARRIV");
						element.codeVilleIataDepart = rs.getString("CODEVILLEIATADEPAR");
						element.dateDepart = rs.getDate("DATEDEPART");
						element.dureeTotale = rs.getInt("DUREETOTALE");
						element.montant = rs.getBigDecimal("MONTANT");
						element.montantPromotion = rs.getBigDecimal("MONTANTPROMOTION");
						element.nombreNuit = rs.getInt("NOMBRENUIT");
						element.nombrePersonneAdulte = rs.getInt("NOMBREPERSONNEADUL");
						element.offreNet = rs.getBoolean("OFFRENET");
						element.prixVenteGds = rs.getBigDecimal("PRIXVENTEGDS");
						element.transportRisqueFinancier = rs.getBoolean("TRANSPORTRISQUEFIN");
						element.typeElargissement = rs.getString("TYPEELARGISSEMENT");
						return element;
					}
				});
	}

	public List<Offre> findByPromotion(final String codePromotion) {
		return template.query("SELECT * FROM OFFRE WHERE CODEPROMOTION = ?", //
				new Object[] { codePromotion },//
				new RowMapper<Offre>() {
					@Override
					public Offre mapRow(ResultSet rs, int rowNum) throws SQLException {
						Offre element = new Offre();
						element.categorieTarif = rs.getString("CATEGORIETARIF");
						element.codeDeviseCommercial = rs.getString("CODEDEVISECOMMERCI");
						element.codeInfoDispoOccupation = rs.getInt("CODEINFODISPOOCCUP");
						element.codeLogementCommercial = rs.getString("CODELOGEMENTCOMMER");
						element.codeOccupation = rs.getInt("CODEOCCUPATION");
						element.codePaysCommercial = rs.getString("CODEPAYSCOMMERCIAL");
						element.codePromotion = rs.getString("CODEPROMOTION");
						element.codeTerminalAeroport = rs.getString("CODETERMINALAEROPO");
						element.codeVillage = rs.getString("CODEVILLAGE");
						element.codeVilleDepartDemande = rs.getString("CODEVILLEDEPARTDEM");
						element.codeVilleIataArrivee = rs.getString("CODEVILLEIATAARRIV");
						element.codeVilleIataDepart = rs.getString("CODEVILLEIATADEPAR");
						element.dateDepart = rs.getDate("DATEDEPART");
						element.dureeTotale = rs.getInt("DUREETOTALE");
						element.montant = rs.getBigDecimal("MONTANT");
						element.montantPromotion = rs.getBigDecimal("MONTANTPROMOTION");
						element.nombreNuit = rs.getInt("NOMBRENUIT");
						element.nombrePersonneAdulte = rs.getInt("NOMBREPERSONNEADUL");
						element.offreNet = rs.getBoolean("OFFRENET");
						element.prixVenteGds = rs.getBigDecimal("PRIXVENTEGDS");
						element.transportRisqueFinancier = rs.getBoolean("TRANSPORTRISQUEFIN");
						element.typeElargissement = rs.getString("TYPEELARGISSEMENT");
						return element;
					}
				});
	}
}
