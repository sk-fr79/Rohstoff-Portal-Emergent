package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;

/**
 * An ExportBuchung is created from a Database result set containing the joined
 * elements of one position on an invoice ("Rechnung") or a credit note
 * ("Gutschrift"), where this position comprises several information (e.g. what
 * is being bought/sold to which price from/to which client etc). This class is
 * to give a nice way of stringing these "transaction atoms" ({@see #toString()}
 * , so one can debug them well, and contains several FIBU logics in it when
 * being created from a database Hashmap (see the {@see #fromDBEntry(HashMap)}
 * method.
 * 
 * @author nils
 * 
 */
public class ExportBuchung_Unused_Code_Nils implements Comparable<ExportBuchung_Unused_Code_Nils> {
	public enum Vorgang {
		EINKAUF, VERKAUF
	}

	private HashMap<String, Object> dbEntry;

	/** Gemeinsame DB-Felder */
	private static final String F_ID_VPOS_RG = "ID_VPOS_RG";
	private static final String F_ID_VKOPF_RG = "ID_VKOPF_RG";

	private static final String F_GESAMTPREIS = "GESAMTPREIS";
	@SuppressWarnings("unused")
	private static final String F_GESAMTPREIS_ABZUG = "GESAMTPREIS_ABZUG"; // OLD
	private static final String F_GPREIS_ABZ_MGE = "GPREIS_ABZ_MGE";
	private static final String F_GPREIS_ABZ_AUF_NETTOMGE = "GPREIS_ABZ_AUF_NETTOMGE";

	private static final String F_GESAMTPREIS_FW = "GESAMTPREIS_FW";
	@SuppressWarnings("unused")
	private static final String F_GESAMTPREIS_ABZUG_FW = "GESAMTPREIS_ABZUG_FW"; // OLD
	private static final String F_GPREIS_ABZ_MGE_FW = "GPREIS_ABZ_MGE_FW";
	private static final String F_GPREIS_ABZ_AUF_NETTOMGE_FW = "GPREIS_ABZ_AUF_NETTOMGE_FW";

	@SuppressWarnings("unused")
	private static final String F_ANZAHL = "ANZAHL"; // Um Stornoposten zu erkennen
	private static final String F_ANZAHL_ABZUG = "ANZAHL_ABZUG";
	private static final String F_ANZAHL_ABZUG_LAGER = "ANZAHL_ABZUG_LAGER";

	@SuppressWarnings("unused")
	private static final String F_WAEHRUNGSKURS = "WAEHRUNGSKURS";

	private static final String F_DRUCKDATUM = "DRUCKDATUM";
	@SuppressWarnings("unused")
	private static final String F_AUSFUEHRUNGSDATUM = "AUSFUEHRUNGSDATUM";

	private static final String F_BUCHUNGSNUMMER = "BUCHUNGSNUMMER";
	private static final String F_ID_ADRESSE = "ID_ADRESSE";
	private static final String F_UMSATZSTEUERLKZ_MANDANT = "UMSATZSTEUERLKZ_MANDANT";
	private static final String F_UMSATZSTEUERLKZ = "UMSATZSTEUERLKZ";
	private static final String F_DIENSTLEISTUNG = "DIENSTLEISTUNG";
	@SuppressWarnings("unused")
	private static final String F_IST_PRODUKT = "IST_PRODUKT";
	private static final String F_PRIVAT = "PRIVAT";
	@SuppressWarnings("unused")
	private static final String F_FIRMA = "FIRMA";
	@SuppressWarnings("unused")
	private static final String F_PRIVAT_MIT_USTID = "PRIVAT_MIT_USTID";
	@SuppressWarnings("unused")
	private static final String F_FIRMA_OHNE_USTID = "FIRMA_OHNE_USTID";

	@SuppressWarnings("unused")
	private static final String F_STEUERSATZ = "STEUERSATZ";
	private static final String F_ANR1 = "ANR1";
	private static final String F_ANR2 = "ANR2";

	private static final String F_EU_STEUER_VERMERK = "EU_STEUER_VERMERK";

	/** Felder für noch zu exportierende Einträge {@see fromDBEntry()} */
	private static final String F_DEBITOR_NUMMER = "DEBITOR_NUMMER";
	private static final String F_KREDITOR_NUMMER = "KREDITOR_NUMMER";
	private static final String F_LAGER_VORZEICHEN = "LAGER_VORZEICHEN";
	private static final String F_ID_TAX = "ID_TAX";
	private static final String F_ID_ARTIKELGRUPPE = "ID_ARTIKELGRUPPE";
	private static final String F_ID_ARTIKEL = "ID_ARTIKEL";
	private static final String F_WAEHRUNG_FREMD = "WAEHRUNG_FREMD";

	private static final String F_POSITIONSNUMMER = "POSITIONSNUMMER";

	/** Felder für schon exportierte Einträge */
	private static final String F_KONTO = "KONTO";
	private static final String F_GEGENKONTO = "GEGENKONTO";
	// private static final String F_ID_VPOS_PARENT = "ID_VPOS_PARENT";
	private static final String F_SUMME = "SUMME";
	private static final String F_WAEHRUNG = "WAEHRUNG";

	/** Konstultative/fakultative Felder */
	protected Date datum;
	private String buchungsnummer;
	private String konto;
	private String gegenkonto;

	private String anr1;
	private String anr2;

	private BigDecimal position;

	/** Auf den Positions-Gesamtpreis wird später die Steuer addiert */
	private BigDecimal sum;
	/** Aus Debuggingzwecken halten wir noch den alten Nettopreis vor */
	private BigDecimal nettopreis;

	private BigDecimal id_vkopf_rg;
	private BigDecimal id_vpos_rg;
	private BigDecimal id_vpos_parent;

	private BigDecimal taxId;
	private BigDecimal articleGroupId;
	private BigDecimal articleId;

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date druckdatum) {
		this.datum = druckdatum;
	}

	public String getBuchungsnummer() {
		return buchungsnummer;
	}

	public void setBuchungsnummer(String buchungsnummer) {
		this.buchungsnummer = buchungsnummer;
	}

	public String getKonto() {
		return konto;
	}

	public void setKonto(String konto) {
		this.konto = konto;
	}

	public String getGegenkonto() {
		return gegenkonto;
	}

	public void setGegenkonto(String gegenkonto) {
		this.gegenkonto = gegenkonto;
	}

	public BigDecimal getGesamtpreis() {
		return sum;
	}

	public String getGesamtpreisAsString() {
		if (this.sum == null)
			return "0.00";
		return String.format("%.2f", this.sum.doubleValue());
	}

	public void setGesamtpreis(BigDecimal gesamtpreis) {
		if (dbEntry != null) {
			this.dbEntry.put(F_GESAMTPREIS, gesamtpreis);
		}
		this.sum = gesamtpreis;
	}

	public BigDecimal getId_vpos_rg() {
		return id_vpos_rg;
	}

	public void setId_vpos_rg(BigDecimal id_vpos_rg) {
		this.id_vpos_rg = id_vpos_rg;
	}

	public BigDecimal getId_vpos_parent() {
		return id_vpos_parent;
	}

	public void setId_vpos_parent(BigDecimal id_vpos_parent) {
		this.id_vpos_parent = id_vpos_parent;
	}

	public BigDecimal getTaxId() {
		return taxId;
	}

	public void setTaxId(BigDecimal taxId) {
		this.taxId = taxId;
	}

	public BigDecimal getArticleGroupId() {
		return articleGroupId;
	}

	public void setArticleGroupId(BigDecimal articleGroupId) {
		this.articleGroupId = articleGroupId;
	}

	public BigDecimal getArticleId() {
		return articleId;
	}

	public void setArticleId(BigDecimal articleId) {
		this.articleId = articleId;
	}

	public BigDecimal getAddressId() {
		return addressId;
	}

	public void setAddressId(BigDecimal addressId) {
		this.addressId = addressId;
	}

	public Vorgang getVorgang() {
		return process;
	}

	public void setVorgang(Vorgang ekvk) {
		if (ekvk == Vorgang.VERKAUF) {
			dbEntry.put(F_LAGER_VORZEICHEN, new BigDecimal(-1));
		} else if (ekvk == Vorgang.EINKAUF) {
			dbEntry.put(F_LAGER_VORZEICHEN, BigDecimal.ONE);
		}
		this.process = ekvk;
	}

	public BigDecimal getIdMandant() {
		return idMandant;
	}

	public void setIdMandant(BigDecimal idMandant) {
		this.idMandant = idMandant;
	}

	public BigDecimal getRuleId() {
		return ruleId;
	}

	public void setRuleId(BigDecimal ruleId) {
		this.ruleId = ruleId;
	}

	public String getDebitor_nummer() {
		return debitor_nummer;
	}

	public void setDebitor_nummer(String debitor_nummer) {
		this.debitor_nummer = debitor_nummer;
	}

	public String getKreditor_nummer() {
		return kreditor_nummer;
	}

	public void setKreditor_nummer(String kreditor_nummer) {
		this.kreditor_nummer = kreditor_nummer;
	}

	private BigDecimal addressId;
	private Vorgang process;
	private BigDecimal idMandant;
	private BigDecimal ruleId;
	private String debitor_nummer;
	private String kreditor_nummer;

	/** Länderpräfix des Mandanten */
	private String quellLand;
	/** Länderpräfix des Kunden */
	private String zielLand;

	private Boolean dienstleistung;
	private Boolean produkt;

	private Boolean firma;
	private Boolean privat;

	private String currency;
	private BigDecimal tax;

	private BigDecimal anzahl;

	private BigDecimal anzahl_abzug;

	private BigDecimal anzahl_abzug_lager;

	private void ensureVirtualTaxId(HashMap<String, Object> entry) {
		// TODO: This is supposed to do nothing at the moment, for that the tax
		// is just checked against the percentage, not a key to a tax in the tax
		// table
		if (this.taxId == null) {
/*			this.taxId = DBUtil.queryForTaxIdBySteuertext(this.idMandant,
					this.tax, getZielLand(),
					(String) entry.get(F_EU_STEUER_VERMERK));
			if (this.taxId != null && bibALL.get_bDebugMode()) {
				DEBUG.System_println("Looked up virtual id_tax (" + this.taxId
						+ ") for " + this.toString(), DEBUG.DEBUG_FLAG_DIVERS1);
			}*/
		}
	}

	/**
	 * Populates the fields from an export run on the original tables
	 * (jt_vpos_rg x jt_vkopf_rg x jd_land x jt_adressse x jt_currency...) and
	 * contains several FIBU logic. This ExportBuchung is later to be written to
	 * an Export set in a file. This Buchung will be given account numbers in
	 * the {@see AccountFinder} class.
	 * 
	 * @param entry
	 * @return
	 */
	public ExportBuchung_Unused_Code_Nils fromDBEntry(HashMap<String, Object> entry) {
		this.dbEntry = entry;

		this.position = (BigDecimal) entry.get(F_POSITIONSNUMMER);
		this.tax = ((BigDecimal) entry.get("STEUERSATZ"));

		this.process = (((BigDecimal) entry.get(F_LAGER_VORZEICHEN)).signum() == -1 ? Vorgang.VERKAUF
				: Vorgang.EINKAUF);
		
		this.taxId = (BigDecimal) entry.get(F_ID_TAX);

		this.anr1 = (String) entry.get(F_ANR1);
		this.anr2 = (String) entry.get(F_ANR2);

		
		this.anzahl = (BigDecimal) entry.get(F_ANZAHL);
		this.anzahl_abzug = (BigDecimal) entry.get(F_ANZAHL_ABZUG);
		this.anzahl_abzug_lager = (BigDecimal) entry.get(F_ANZAHL_ABZUG_LAGER);

		
		this.articleGroupId = (BigDecimal) entry.get(F_ID_ARTIKELGRUPPE);
		this.articleId = (BigDecimal) entry.get(F_ID_ARTIKEL);
		this.addressId = (BigDecimal) entry.get(F_ID_ADRESSE);

		// Preis: GESAMTPREIS - (GPREIS_ABZ_MGE+GPREIS_ABZ_AUF_NETTOMGE)
		BigDecimal preis = ((BigDecimal) entry.get(F_GESAMTPREIS));
		BigDecimal abzugMge = ((BigDecimal) entry.get(F_GPREIS_ABZ_MGE));
		BigDecimal abzugAufNettoMge = ((BigDecimal) entry
				.get(F_GPREIS_ABZ_AUF_NETTOMGE));

		if (abzugMge != null) {
			preis = preis.subtract(abzugMge);
		}
		if (abzugAufNettoMge != null) {
			preis = preis.subtract(abzugAufNettoMge);
		}
		this.sum = preis;
		
		// 2015-12-14 (nils) Prozessanpassung: Ein Einkauf mit negativem Preis ist ein Verkauf, wenn kein Storno
//		if (this.sum.compareTo(BigDecimal.ZERO) < 0 && this.anzahl.compareTo(BigDecimal.ZERO) >= 0) {
//			if (this.process == Vorgang.EINKAUF) {
//				this.process = Vorgang.VERKAUF;
//				this.dbEntry.put(F_LAGER_VORZEICHEN, -1);
//			} else if (this.process == Vorgang.VERKAUF) {
//				this.process = Vorgang.EINKAUF;
//				this.dbEntry.put(F_LAGER_VORZEICHEN, 1);
//			}
//		}

		// TODO: This is essentially the same as Preis. Is there any logics for
		// foreign currencies?
/*		BigDecimal preisFW = ((BigDecimal) entry.get(F_GESAMTPREIS_FW));
		BigDecimal abzugMgeFW = ((BigDecimal) entry.get(F_GPREIS_ABZ_MGE_FW));
		BigDecimal abzugAufNettoMgeFW = ((BigDecimal) entry
				.get(F_GPREIS_ABZ_AUF_NETTOMGE_FW));

		if (abzugMgeFW != null) {
			preisFW = preisFW.subtract(abzugMgeFW);
		}
		if (abzugAufNettoMgeFW != null) {
			preisFW = preisFW.subtract(abzugAufNettoMgeFW);
		}
		this.sum = preisFW;
*/
		// FIBU-Eigenschaft: Es werden immer BRUTTO-Beträge verbucht, daher
		// muss der Gesamtpreis hier angepasst werden
		this.nettopreis = this.sum;
		if (this.tax != null && this.tax.compareTo(BigDecimal.ZERO) > 0) {
			// If tax > 0, calculate factor = (tax / 100) + 1
			BigDecimal factor = this.tax.divide(new BigDecimal(100));
			factor = factor.add(BigDecimal.ONE);

			// Adjust price to gross value
			this.sum = this.sum.multiply(factor);

		}

		this.currency = (String) entry.get(F_WAEHRUNG_FREMD);

		// RE/Gxxxxxx
		this.buchungsnummer = (String) entry.get(F_BUCHUNGSNUMMER);

		// Fibu-Logik: Ist das Leistungsdatum != Druckdatum, verwende
		// buchhalterisch das
		// Leistungsdatum.
		this.datum = (Date) entry.get(F_DRUCKDATUM);

		// Update: 2015-Jun-09: Frau Hecktor möchte immer das Druckdatum
		// Andernfalls ist dies falsch und muss korrigiert werden
		
		/*
		Date datum2 = (Date) entry.get(F_AUSFUEHRUNGSDATUM);
		if (!this.datum.equals(datum2)) {
			this.datum = datum2;
		}*/
		

		// Save this for the case that we need to throw an exception
		this.id_vpos_rg = (BigDecimal) entry.get(F_ID_VPOS_RG);
		this.id_vkopf_rg = (BigDecimal) entry.get(F_ID_VKOPF_RG);
		this.debitor_nummer = (String) entry.get(F_DEBITOR_NUMMER);
		this.kreditor_nummer = (String) entry.get(F_KREDITOR_NUMMER);

		this.setMandantLKZ((String) entry.get(F_UMSATZSTEUERLKZ_MANDANT));
		this.setKundeLKZ((String) entry.get(F_UMSATZSTEUERLKZ));

		// Ab hier: Alles, was keine Dienstleistung ist, ist Produkt
		// (buchhalterisch)
		// TODO: Ist das korrekt so?
		String dl = (String) entry.get(F_DIENSTLEISTUNG);
		if (dl != null && dl.equals("Y")) {
			this.setDienstleistung(Boolean.TRUE);
		} else {
			this.setDienstleistung(Boolean.FALSE);
		}

		// Ab hier: Alles, was keine Firma ist, ist privat
		// TODO: Wie wird zwischen privaten Firmen oder privatpersonen mit UstID
		// umgegangen?
		String pri = (String) entry.get(F_PRIVAT);
		if (pri != null && pri.equals("Y")) {
			this.setPrivat(Boolean.TRUE);
		} else {
			this.setPrivat(Boolean.FALSE);
		}

		this.idMandant = (BigDecimal) entry.get("ID_MANDANT");

		// TODO: This is only for testing and legacy purposes, and NEEDS to be
		// removed once all ID_TAX are accurately present
		ensureVirtualTaxId(entry);
		return this;
	}

	/**
	 * Populates an ExportBuchung from data already written to the export tables
	 * (jt_vpos_export_id x jt_vkopf_export_id x jt_export_log). All decisions
	 * on the findings of the account numbers have already been made. All data
	 * relevant for the Export to CSV is populated here.
	 * 
	 * @param exportedEntry
	 * @return
	 */
	public ExportBuchung_Unused_Code_Nils fromExportedEntry(HashMap<String, Object> exportedEntry) {
		this.dbEntry = exportedEntry;

		this.buchungsnummer = (String) exportedEntry.get(F_BUCHUNGSNUMMER);
		this.konto = (String) exportedEntry.get(F_KONTO);
		this.gegenkonto = (String) exportedEntry.get(F_GEGENKONTO);
		this.sum = (BigDecimal) exportedEntry.get(F_SUMME);
		this.currency = (String) exportedEntry.get(F_WAEHRUNG);
		this.datum = (Date) exportedEntry.get(F_DRUCKDATUM);
		this.id_vpos_rg = (BigDecimal) exportedEntry.get(F_ID_VPOS_RG);
		this.id_vkopf_rg = (BigDecimal) exportedEntry.get(F_ID_VKOPF_RG);

		if (buchungsnummer == null || sum == null || currency == null || datum == null
				| id_vpos_rg == null || id_vkopf_rg == null) {
			throw new NullPointerException(
					"ExportBuchung* has some null values: " + exportedEntry);
		}
		return this;
	}

	public BigDecimal getId_vkopf_rg() {
		return id_vkopf_rg;
	}

	public void setId_vkopf_rg(BigDecimal id_vkopf_rg) {
		this.id_vkopf_rg = id_vkopf_rg;
	}

	/** Gets the Buchungsnummer without 'G'/'RE' (Gutschrift/Rechung) prefix */
	public String getBuchungsnummerNormalized() {
		if (buchungsnummer == null)
			return "";
		if (isGutschrift()) {
			// Gutschrift
			return buchungsnummer.substring(1);
		} else {
			// Rechnung
			return buchungsnummer.substring(2);
		}
	}

	public boolean isGutschrift() {
		return (buchungsnummer != null && buchungsnummer.startsWith("G"));
	}

	public boolean isRechnung() {
		return (buchungsnummer != null && buchungsnummer.startsWith("RE"));
	}

	@Override
	public int compareTo(ExportBuchung_Unused_Code_Nils o) {
		return buchungsnummer.compareTo(o.buchungsnummer);
	}

	public String getQuellLand() {
		return quellLand;
	}

	public void setMandantLKZ(String quellLand) {
		this.quellLand = quellLand;
	}

	public String getZielLand() {
		return zielLand;
	}

	public void setKundeLKZ(String zielLand) {
		this.zielLand = zielLand;
	}

	public Boolean getDienstleistung() {
		return dienstleistung;
	}

	public void setDienstleistung(Boolean dienstleistung) {
		this.dienstleistung = dienstleistung;
	}

	public Boolean getPrivat() {
		return privat;
	}

	public void setPrivat(Boolean privat) {
		this.privat = privat;
	}

	public String getWaehrung() {
		return currency;
	}

	public void setWaehrung(String waehrung) {
		this.currency = waehrung;
	}

	public BigDecimal getPositionsnummer() {
		return position;
	}

	public void setPositionsnummer(BigDecimal positionsnummer) {
		this.position = positionsnummer;
	}

	public HashMap<String, Object> getDBEntry() {
		return this.dbEntry;
	}

	/** This has just debugging purposes and is used in the tests */
	private String kommentar;

	public void appendKommentar(String k) {
		if (kommentar == null) {
			kommentar = k;
		} else {
			kommentar += " " + k;
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (getId_vpos_rg() == null
				|| (this.konto != null && this.gegenkonto != null)) {
			toStringBookedCollection(sb);
		} else {
			toStringVPOS(sb);
		}
		return sb.toString();
	}

	/**
	 * Strings a regular VPOS for nice debugging, so one can see the date, the
	 * booking number, the balance, various signs (EK/VK, PRIVATE/GEWERBLICH,
	 * PRODUKT/DIENSTLEISTUNG) trimmed an nicely.
	 * 
	 * @param sb
	 */
	private void toStringVPOS(StringBuilder sb) {
		sb.append("[ExBu id_vpos_rg: ");
		sb.append(S.padLeft(String.format("%1d", getId_vpos_rg().intValue()), 6));
		sb.append(", id_vkopf_rg: ");
		sb.append(S.padLeft(String.format("%1d", getId_vkopf_rg().intValue()),
				5));
		sb.append(" [");
		sb.append(getBuchungsnummer());
		if (isGutschrift()) {
			sb.append(" ");
		}
		sb.append(" / ");
		if (datum != null) {
			sb.append(datum.toString().substring(0, 10));
			sb.append(" / ");
		}
		sb.append(String.format("%2d", this.position.intValue()));
		sb.append(" / ");
		sb.append(S.padLeft(getGesamtpreisAsString(), 8));
		sb.append(" ");
		sb.append(this.currency);
		sb.append("] {");
		sb.append(Vorgang.EINKAUF == this.getVorgang() ? "EK" : "VK");
		if (this.getDienstleistung()) {
			sb.append(" DL");
		} else {
			sb.append("   ");
		}
		if (this.getPrivat()) {
			sb.append(" PR");
		} else {
			sb.append("   ");
		}
		sb.append(", ");
		if (this.getQuellLand() != null) {
			sb.append(this.getQuellLand());
		} else {
			sb.append("--");
		}
		sb.append("->");
		if (this.getZielLand() != null) {
			sb.append(this.getZielLand());
		} else {
			sb.append("--");
		}
		sb.append(", tax%: ");
		sb.append(S.padLeft(String.format("% 2.0f", this.tax.doubleValue()), 2));
		if (this.getArticleGroupId() != null) {
			sb.append(", artGrp: ");
			sb.append(this.getArticleGroupId());
		}
		if (this.anr1 != null) {
			sb.append(", anr: ");
			sb.append(this.anr1);
			if (this.anr2 != null) {
				sb.append("-");
				sb.append(this.anr2);
			}
		}
		sb.append(", artId: ");
		sb.append(this.getArticleId());
		sb.append(", adrId: ");
		sb.append(this.getAddressId());
		sb.append("}]");
		if (kommentar != null) {
			sb.append(" ");
			sb.append(kommentar);
		}
	}

	/**
	 * Diese Buchung ist evtl schon verschmolzen mit anderen und hat
	 * Kontennummern, das Stringen läuft daher etwas anders.
	 * 
	 * @param sb
	 * @return
	 */
	private void toStringBookedCollection(StringBuilder sb) {
		sb.append("[ExBu* {");
		if (datum != null) {
			sb.append(datum.toString().substring(0, 10));
			sb.append(" / ");
		}
		sb.append(getBuchungsnummer());
		if (isGutschrift()) {
			sb.append(" ");
		}
		sb.append(" / ");
		if (this.position != null) {
			sb.append(String.format("%2d", this.position.intValue()));
		} else {
			sb.append("-");
		}
		sb.append(" / ");
		sb.append(S.padLeft(getGesamtpreisAsString(), 8));
		sb.append(" ");
		sb.append(this.currency);
		sb.append("} => ");
		sb.append(this.konto);
		sb.append(" / ");
		sb.append(this.gegenkonto);
		sb.append(" ]");
		if (kommentar != null) {
			sb.append(" ");
			sb.append(kommentar);
		}
	}

	public String getParentVposId() {
		// TODO Auto-generated method stub
		return "0";
	}
}