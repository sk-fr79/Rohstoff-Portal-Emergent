package rohstoff.Echo2BusinessLogic.LAGER_BEWEGUNG;

import java.math.BigDecimal;

public class LAG_BEW_DataRowStatus {
	private String 		idAdresseLager = null;
	private String 		idArtikelSorte = null;
	private String 		idArtikelHauptsorte = null;
	private BigDecimal	menge_Gesamt = null;
	private BigDecimal  menge_Preise_nicht_Null = null;
	private BigDecimal  menge_Preise_nur_Null = null;
	private BigDecimal  menge_Preise_leer = null;
	private BigDecimal  summe_Restwert = null;
	private BigDecimal  avg_Restwert_gesamt = null;
	private BigDecimal  avg_Restwert_Menge_Preise_nicht_Null = null;
	private BigDecimal  avg_Restwert_Menge_Preise_auch_Null = null;
	
	
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
	public BigDecimal getMenge_Preise_leer() {
		return menge_Preise_leer;
	}
	public void setMenge_Preise_leer(BigDecimal menge_Preise_leer) {
		this.menge_Preise_leer = menge_Preise_leer;
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
	public void setAvg_Restwert_Menge_Preise_nicht_Null(
			BigDecimal avg_Restwert_Menge_Preise_nicht_Null) {
		this.avg_Restwert_Menge_Preise_nicht_Null = avg_Restwert_Menge_Preise_nicht_Null;
	}
	public BigDecimal getAvg_Restwert_Menge_Preise_auch_Null() {
		return avg_Restwert_Menge_Preise_auch_Null;
	}
	public void setAvg_Restwert_Menge_Preise_auch_Null(
			BigDecimal avg_Restwert_Menge_Preise_auch_Null) {
		this.avg_Restwert_Menge_Preise_auch_Null = avg_Restwert_Menge_Preise_auch_Null;
	}
	
	
}
