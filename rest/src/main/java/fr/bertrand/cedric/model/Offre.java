package fr.bertrand.cedric.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(name = "Offre", propOrder = { "codeVillage", //
		"dateDepart", //
		"nombreNuit",//
		"codePaysCommercial",//
		"offreNet",//
		"codeDeviseCommercial",//
		"nombrePersonneAdulte", //
		"dureeTotale", //
		"codeLogementCommercial", //
		"codeOccupation", //
		"codeVilleDepartDemande", //
		"codeVilleIataDepart",//
		"codeVilleIataArrivee",//
		"categorieTarif",//
		"codeInfoDispoOccupation", //
		"typeElargissement", //
		"transportRisqueFinancier", //
		"prixVenteGds", //
		"codeTerminalAeroport", //
		"montant", //
		"montantPromotion", //
		"codePromotion" })
public class Offre {
	@XmlElement(required = true)
	public String codeVillage;
	@XmlElement(required = true)
	public Date dateDepart;
	@XmlElement(required = true)
	public int nombreNuit;
	@XmlElement(required = true)
	public String codePaysCommercial;
	@XmlElement(required = true)
	public boolean offreNet;
	@XmlElement(required = true)
	public String codeDeviseCommercial;
	@XmlElement(required = true)
	public int nombrePersonneAdulte;
	@XmlElement(required = true)
	public int dureeTotale;
	@XmlElement(required = true)
	public String codeLogementCommercial;
	@XmlElement(required = true)
	public int codeOccupation;
	@XmlElement(required = true)
	public String codeVilleDepartDemande;
	@XmlElement(required = true)
	public String codeVilleIataDepart;
	@XmlElement(required = true)
	public String codeVilleIataArrivee;
	@XmlElement()
	public String categorieTarif;
	@XmlElement()
	public int codeInfoDispoOccupation;
	@XmlElement()
	public String typeElargissement;
	@XmlElement()
	public boolean transportRisqueFinancier;
	@XmlElement()
	public BigDecimal prixVenteGds;
	@XmlElement()
	public String codeTerminalAeroport;
	@XmlElement()
	public BigDecimal montant;
	@XmlElement()
	public BigDecimal montantPromotion;
	@XmlElement()
	public String codePromotion;
}
