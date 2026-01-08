package rohstoff.Echo2BusinessLogic.__SPECIALRECORDS;

import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._AUSWERTUNGEN.WARENSTROEME.RECORD_VPOS_TPA_FUHRE_EXT;

public class RECORD_VPOS_TPA_FUHRE_SPEZ extends RECORD_VPOS_TPA_FUHRE_EXT
{

	private Double d0 = new Double(0);
	
	public RECORD_VPOS_TPA_FUHRE_SPEZ() throws myException
	{
		super();
	}

	public RECORD_VPOS_TPA_FUHRE_SPEZ(long lIDUnformated, MyConnection Conn) throws myException
	{
		super(lIDUnformated, Conn);
	}

	public RECORD_VPOS_TPA_FUHRE_SPEZ(long lIDUnformated) throws myException
	{
		super(lIDUnformated);
	}

	public RECORD_VPOS_TPA_FUHRE_SPEZ(MyConnection Conn) throws myException
	{
		super(Conn);
	}

	public RECORD_VPOS_TPA_FUHRE_SPEZ(RECORD_VPOS_TPA_FUHRE recordOrig)
	{
		super(recordOrig);
	}

	public RECORD_VPOS_TPA_FUHRE_SPEZ(String cIDOrWHEREBLOCKORSQL, MyConnection Conn) throws myException
	{
		super(cIDOrWHEREBLOCKORSQL, Conn);
	}

	public RECORD_VPOS_TPA_FUHRE_SPEZ(String cIDOrWHEREBLOCKORSQL) throws myException
	{
		super(cIDOrWHEREBLOCKORSQL);
	}


	/**
	 * wert der in der Abrechnung zum ek-kontrakt zugeschlagen wird, in der regel die lademenge, ausser umgedreht
	 * @return
	 * @throws myException
	 */
	public Double get__MengeZu_EK_Kontrakt_oder_PlanMenge_EK_netto() throws myException
	{
		Double dWert = null;
		
		if (this.is_LADEMENGE_GUTSCHRIFT_YES())
		{
			dWert = (this.get_ANTEIL_LADEMENGE_LIEF_dValue(this.get_ANTEIL_PLANMENGE_LIEF_dValue(d0)) - this.get_ABZUG_LADEMENGE_LIEF_dValue(d0));
		}
		else
		{
			dWert = (this.get_ANTEIL_ABLADEMENGE_LIEF_dValue(this.get_ANTEIL_PLANMENGE_LIEF_dValue(d0)) - this.get_ABZUG_LADEMENGE_LIEF_dValue(d0));
		}
		
		return dWert;
	}
	
	
	/**
	 * wert der in der Abrechnung zum vk-kontrakt zugeschlagen wird, in der regel die ablademenge, ausser umgedreht
	 * @return
	 * @throws myException
	 */
	public Double get__MengeZu_VK_Kontrakt_oder_PlanMenge_VK_netto() throws myException
	{
		Double dWert = null;
		
		if (this.is_ABLADEMENGE_RECHNUNG_YES())
		{
			dWert = (this.get_ANTEIL_ABLADEMENGE_ABN_dValue(this.get_ANTEIL_PLANMENGE_ABN_dValue(d0)) - this.get_ABZUG_ABLADEMENGE_ABN_dValue(d0));
		}
		else
		{
			dWert = (this.get_ANTEIL_LADEMENGE_ABN_dValue(this.get_ANTEIL_PLANMENGE_ABN_dValue(d0)) - this.get_ABZUG_ABLADEMENGE_ABN_dValue(d0));
		}
		return dWert;
	}
	

	
	/**
	 * wert der in der Abrechnung zum ek-kontrakt zugeschlagen wird, in der regel die lademenge, ausser umgedreht
	 * @return
	 * @throws myException
	 */
	public Double get__MengeZu_EK_Kontrakt_netto() throws myException
	{
		Double dWert = null;
		
		if (this.is_LADEMENGE_GUTSCHRIFT_YES())
		{
			dWert = (this.get_ANTEIL_LADEMENGE_LIEF_dValue(d0) - this.get_ABZUG_LADEMENGE_LIEF_dValue(d0));
		}
		else
		{
			dWert = (this.get_ANTEIL_ABLADEMENGE_LIEF_dValue(d0) - this.get_ABZUG_LADEMENGE_LIEF_dValue(d0));
		}
		
		return dWert;
	}
	
	
	/**
	 * wert der in der Abrechnung zum vk-kontrakt zugeschlagen wird, in der regel die ablademenge, ausser umgedreht
	 * @return
	 * @throws myException
	 */
	public Double get__MengeZu_VK_Kontrakt_netto() throws myException
	{
		Double dWert = null;
		
		if (this.is_ABLADEMENGE_RECHNUNG_YES())
		{
			dWert = (this.get_ANTEIL_ABLADEMENGE_ABN_dValue(d0) - this.get_ABZUG_ABLADEMENGE_ABN_dValue(d0));
		}
		else
		{
			dWert = (this.get_ANTEIL_LADEMENGE_ABN_dValue(d0) - this.get_ABZUG_ABLADEMENGE_ABN_dValue(d0));
		}
		return dWert;
	}
	

	
	/**
	 * nettowert der lademenge oder planmenge
	 * @return
	 * @throws myException
	 */
	public Double get__LadeMengeNetto_oder_planmenge() throws myException
	{
		//2012-11-15: bug: planmenge vergessen
		//Double dWert = (this.get_ANTEIL_LADEMENGE_LIEF_dValue(d0) - this.get_ABZUG_LADEMENGE_LIEF_dValue(d0));
		Double dWert = (this.get_ANTEIL_LADEMENGE_LIEF_dValue(this.get_ANTEIL_PLANMENGE_LIEF_dValue(d0)) - this.get_ABZUG_LADEMENGE_LIEF_dValue(d0));
		return dWert;
	}
	

	/**
	 * nettowert der lademenge oder planmenge
	 * @return
	 * @throws myException
	 */
	public Double get__AbladeMengeNetto_oder_planmenge() throws myException
	{
		//2012-11-15: bug: planmenge vergessen
		//Double dWert = (this.get_ANTEIL_ABLADEMENGE_ABN_dValue(d0) - this.get_ABZUG_ABLADEMENGE_ABN_dValue(d0));
		Double dWert = (this.get_ANTEIL_ABLADEMENGE_ABN_dValue(this.get_ANTEIL_PLANMENGE_ABN_dValue(d0)) - this.get_ABZUG_ABLADEMENGE_ABN_dValue(d0));
		return dWert;
	}
	

	
	/**
	 * wert der in der Abrechnung zum ek-kontrakt zugeschlagen wird, in der regel die lademenge, ausser umgedreht
	 * @return
	 * @throws myException
	 */
	public Double get__Abrechnungs_oder_Planmenge_Ladeseite_netto() throws myException
	{
		Double dWert = null;
		
		if (this.is_LADEMENGE_GUTSCHRIFT_YES())
		{
			dWert = (this.get_ANTEIL_LADEMENGE_LIEF_dValue(this.get_ANTEIL_PLANMENGE_LIEF_dValue(d0)) - this.get_ABZUG_LADEMENGE_LIEF_dValue(d0));
		}
		else
		{
			dWert = (this.get_ANTEIL_ABLADEMENGE_LIEF_dValue(this.get_ANTEIL_PLANMENGE_LIEF_dValue(d0)) - this.get_ABZUG_LADEMENGE_LIEF_dValue(d0));
		}
		
		return dWert;
	}
	
	
	/**
	 * wert der in der Abrechnung zum vk-kontrakt zugeschlagen wird, in der regel die ablademenge, ausser umgedreht
	 * @return
	 * @throws myException
	 */
	public Double get__Abrechnungs_oder_Planmenge_Abladeseite_netto() throws myException
	{
		Double dWert = null;
		
		if (this.is_ABLADEMENGE_RECHNUNG_YES())
		{
			dWert = (this.get_ANTEIL_ABLADEMENGE_ABN_dValue(this.get_ANTEIL_PLANMENGE_ABN_dValue(d0)) - this.get_ABZUG_ABLADEMENGE_ABN_dValue(d0));
		}
		else
		{
			dWert = (this.get_ANTEIL_LADEMENGE_ABN_dValue(this.get_ANTEIL_PLANMENGE_ABN_dValue(d0)) - this.get_ABZUG_ABLADEMENGE_ABN_dValue(d0));
		}
		return dWert;
	}

	
	
	/**
	 * neue methode, gibt einen info-String zur Fuhre
	 * @throws myException 
	 */
	public String get_cInfoString() throws myException {

		String cRueck = "";
		
		String cRueck1 = "";
		if (this.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek()!=null) {
			cRueck1 += (this.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek().get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cF_NN("<anr1>")+" ");
			cRueck1 += (this.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek().get_ANR2_cUF_NN("<anr2>")+" ");
			cRueck1 += (this.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek().get_ARTBEZ1_cUF_NN("")+" ");
			
			cRueck += ("<"+cRueck1.trim()+">   ");
		}
		
		String cRueck2 = "";
		if (this.get_UP_RECORD_ADRESSE_id_adresse_lager_start()!=null) {
			cRueck += " von ";
			cRueck2 += (this.get_UP_RECORD_ADRESSE_id_adresse_lager_start().get_NAME1_cF_NN("")+" ");
			cRueck2 += (this.get_UP_RECORD_ADRESSE_id_adresse_lager_start().get_ORT_cF_NN("")+" ");
			cRueck += ("   <"+cRueck2.trim()+">   ");
		}
		
		String cRueck3 = "";
		if (this.get_UP_RECORD_ADRESSE_id_adresse_lager_ziel()!=null) {
			cRueck += " nach ";
			cRueck3 += (this.get_UP_RECORD_ADRESSE_id_adresse_lager_ziel().get_NAME1_cF_NN("")+" ");
			cRueck3 += (this.get_UP_RECORD_ADRESSE_id_adresse_lager_ziel().get_ORT_cF_NN("")+" ");
			cRueck += ("   <"+cRueck3.trim()+">");
		}
		
		return cRueck;
	}
	
	
	/**
	 * 
	 * @return null, wenn kein land, sonst false fuer land gleich mandantenland, sonst true
	 * @throws myException
	 */
	public Boolean get_bZielOrtIstAusland() throws myException {
		Boolean bRueck = null;
		if (this.get_UP_RECORD_ADRESSE_id_adresse_lager_ziel()!=null) {
			if (this.get_UP_RECORD_ADRESSE_id_adresse_lager_ziel().get_ID_LAND_cUF_NN("").equals(bibALL.get_RECORD_MANDANT().get_ID_LAND_cUF_NN("-"))) {
				bRueck = new Boolean(false);
			} else {
				bRueck = new Boolean(true);
			}
		}
		return bRueck;
	}
	
	
	/**
	 * 
	 * @return null, wenn kein land, true fuer nicht EU-land, sonst false
	 * @throws myException
	 */
	public Boolean get_bZielOrtIstDrittland() throws myException {
		Boolean bRueck = null;
		if (this.get_UP_RECORD_ADRESSE_id_adresse_lager_ziel()!=null) {
			if (this.get_UP_RECORD_ADRESSE_id_adresse_lager_ziel().get_UP_RECORD_LAND_id_land()!=null) {
				if (this.get_UP_RECORD_ADRESSE_id_adresse_lager_ziel().get_UP_RECORD_LAND_id_land().is_INTRASTAT_JN_NO()) {
					bRueck = new Boolean(true);
				} else {
					bRueck = new Boolean(false);
				}
			}
		}
		return bRueck;
	}
	

	/**
	 * 
	 * @return null, wenn kein land, sonst false fuer land gleich mandantenland, sonst true
	 * @throws myException
	 */
	public Boolean get_bQuelleOrtIstAusland() throws myException {
		Boolean bRueck = null;
		if (this.get_UP_RECORD_ADRESSE_id_adresse_lager_start()!=null) {
			if (this.get_UP_RECORD_ADRESSE_id_adresse_lager_start().get_ID_LAND_cUF_NN("").equals(bibALL.get_RECORD_MANDANT().get_ID_LAND_cUF_NN("-"))) {
				bRueck = new Boolean(false);
			} else {
				bRueck = new Boolean(true);
			}
		}
		return bRueck;
	}
	
	
	/**
	 * 
	 * @return null, wenn kein land, true fuer nicht EU-land, sonst false
	 * @throws myException
	 */
	public Boolean get_bQuelleOrtIstDrittland() throws myException {
		Boolean bRueck = null;
		if (this.get_UP_RECORD_ADRESSE_id_adresse_lager_start()!=null) {
			if (this.get_UP_RECORD_ADRESSE_id_adresse_lager_start().get_UP_RECORD_LAND_id_land()!=null) {
				if (this.get_UP_RECORD_ADRESSE_id_adresse_lager_start().get_UP_RECORD_LAND_id_land().is_INTRASTAT_JN_NO()) {
					bRueck = new Boolean(true);
				} else {
					bRueck = new Boolean(false);
				}
			}
		}
		return bRueck;
	}
	
}
