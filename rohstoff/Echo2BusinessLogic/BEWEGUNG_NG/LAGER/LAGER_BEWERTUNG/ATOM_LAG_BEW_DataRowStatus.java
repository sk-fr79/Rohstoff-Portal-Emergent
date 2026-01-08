package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_BEWERTUNG;

import java.math.BigDecimal;

/**
 * Klasse zum Speichern der Ergebnisse der Statusabfrage.
 * Fall die Summierte Abfrage zum Lagerstatus genommen wird, dann sind die 
 * @author manfred
 *
 */
public class ATOM_LAG_BEW_DataRowStatus {
	private String 		idAdresseLager = null;
	private String 		idArtikelSorte = null;
	private String 		idArtikelHauptsorte = null;
	private BigDecimal	menge_Gesamt = null;
	private BigDecimal  menge_Preise_nicht_Null = null;
	private BigDecimal  menge_Preise_nur_Null = null;
	private BigDecimal  summe_Restwert = null;
	private BigDecimal  avg_Restwert_gesamt = null;
	private BigDecimal  avg_Restwert_Menge_Preise_nicht_Null = null;
	
	private BigDecimal  summe_Restwert_ohne_kosten = null;
	private BigDecimal  avg_Restwert_gesamt_ohne_kosten = null;
	private BigDecimal  avg_Restwert_Menge_Preise_nicht_Null_ohne_kosten = null;	
	private String      idBewegungAtom = null;
	private String      idEinheit = null;
	private String      sEinheit = null;
	
	public String getIdAdresseLager() {
		return idAdresseLager;
	}
	public void setIdAdresseLager(String idAdresseLager) {
		this.idAdresseLager = idAdresseLager;
	}
	public String getIdArtikelSorte() {
		return idArtikelSorte;
	}
	public void setIdArtikelSorte(String idArtikelSorte) {
		this.idArtikelSorte = idArtikelSorte;
	}
	public String getIdArtikelHauptsorte() {
		return idArtikelHauptsorte;
	}
	public void setIdArtikelHauptsorte(String idArtikelHauptsorte) {
		this.idArtikelHauptsorte = idArtikelHauptsorte;
	}
	public BigDecimal getMenge_Gesamt() {
		return menge_Gesamt;
	}
	public void setMenge_Gesamt(BigDecimal menge_Gesamt) {
		this.menge_Gesamt = menge_Gesamt;
	}
	public BigDecimal getMenge_Preise_nicht_Null() {
		return menge_Preise_nicht_Null;
	}
	public void setMenge_Preise_nicht_Null(BigDecimal menge_Preise_nicht_Null) {
		this.menge_Preise_nicht_Null = menge_Preise_nicht_Null;
	}
	public BigDecimal getMenge_Preise_nur_Null() {
		return menge_Preise_nur_Null;
	}
	public void setMenge_Preise_nur_Null(BigDecimal menge_Preise_nur_Null) {
		this.menge_Preise_nur_Null = menge_Preise_nur_Null;
	}

	public BigDecimal getSumme_Restwert() {
		return summe_Restwert;
	}
	public void setSumme_Restwert(BigDecimal summe_Restwert) {
		this.summe_Restwert = summe_Restwert;
	}
	public BigDecimal getAvg_Restwert_gesamt() {
		return avg_Restwert_gesamt;
	}
	public void setAvg_Restwert_gesamt(BigDecimal avg_Restwert_gesamt) {
		this.avg_Restwert_gesamt = avg_Restwert_gesamt;
	}
	public BigDecimal getAvg_Restwert_Menge_Preise_nicht_Null() {
		return avg_Restwert_Menge_Preise_nicht_Null;
	}
	public void setAvg_Restwert_Menge_Preise_nicht_Null(BigDecimal avg_Restwert_Menge_Preise_nicht_Null) {
		this.avg_Restwert_Menge_Preise_nicht_Null = avg_Restwert_Menge_Preise_nicht_Null;
	}

	
	public BigDecimal getSumme_Restwert_Ohne_Kosten() {
		return summe_Restwert_ohne_kosten;
	}
	public void setSumme_Restwert_Ohne_Kosten(BigDecimal summe_Restwert_ohne_kosten) {
		this.summe_Restwert_ohne_kosten = summe_Restwert_ohne_kosten;
	}
	public BigDecimal getAvg_Restwert_gesamt_Ohne_Kosten() {
		return avg_Restwert_gesamt_ohne_kosten;
	}
	public void setAvg_Restwert_gesamt_Ohne_Kosten(BigDecimal avg_Restwert_gesamt_ohne_kosten) {
		this.avg_Restwert_gesamt_ohne_kosten = avg_Restwert_gesamt_ohne_kosten;
	}
	public BigDecimal getAvg_Restwert_Menge_Preise_nicht_Null_Ohne_Kosten() {
		return avg_Restwert_Menge_Preise_nicht_Null_ohne_kosten;
	}
	
	/**
	 * gibt die Kosten der bewerteten Menge zurück
	 * durchschnittliche_kosten = durchschnittlicher_restwert_mit_kosten - durchschnittlicher_restwert_ohne_kosten
	 * @return
	 *
	 * Autor:	 manfred
	 * Erstellt: 23.07.2014
	 *
	 */
	public BigDecimal get_Kosten_Restwert_Menge_Preise_nicht_Null(){
		BigDecimal bdKosten = null;
		if (avg_Restwert_Menge_Preise_nicht_Null != null && avg_Restwert_Menge_Preise_nicht_Null_ohne_kosten != null){
			bdKosten = avg_Restwert_Menge_Preise_nicht_Null.subtract(avg_Restwert_Menge_Preise_nicht_Null_ohne_kosten);
		}
		return bdKosten;
	}
	
	
	public void setAvg_Restwert_Menge_Preise_nicht_Null_Ohne_Kosten(BigDecimal avg_Restwert_Menge_Preise_nicht_Null_ohne_kosten) {
		this.avg_Restwert_Menge_Preise_nicht_Null_ohne_kosten  = avg_Restwert_Menge_Preise_nicht_Null_ohne_kosten;
	}

	
	public String getIdBewegungAtom() {
		return idBewegungAtom;
	}
	public void setIdBewegungAtom(String idBewegungAtom) {
		this.idBewegungAtom = idBewegungAtom;
	}
	
	public String getIdEinheit() {
		return idEinheit;
	}
	public void setIdEinheit(String idEinheit) {
		this.idEinheit = idEinheit;
	}
	
	public String getEinheitKurz() {
		return sEinheit;
	}
	public void setEinheitKurz(String sEinheit) {
		this.sEinheit = sEinheit;
	}
}
