package rohstoff.Echo2BusinessLogic.__SPECIALRECORDS;

import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.exceptions.myException;

public class RECORD_VPOS_TPA_FUHRE_ORT_SPEZ extends RECORD_VPOS_TPA_FUHRE_ORT
{
	private Double d0 = new Double(0);

	public RECORD_VPOS_TPA_FUHRE_ORT_SPEZ() throws myException
	{
		super();
	}

	public RECORD_VPOS_TPA_FUHRE_ORT_SPEZ(long lID_Unformated, MyConnection Conn) throws myException
	{
		super(lID_Unformated, Conn);
	}

	public RECORD_VPOS_TPA_FUHRE_ORT_SPEZ(long lID_Unformated) throws myException
	{
		super(lID_Unformated);
	}

	public RECORD_VPOS_TPA_FUHRE_ORT_SPEZ(MyConnection Conn) throws myException
	{
		super(Conn);
	}

	public RECORD_VPOS_TPA_FUHRE_ORT_SPEZ(RECORD_VPOS_TPA_FUHRE_ORT recordOrig)
	{
		super(recordOrig);
	}

	public RECORD_VPOS_TPA_FUHRE_ORT_SPEZ(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn) throws myException
	{
		super(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}

	public RECORD_VPOS_TPA_FUHRE_ORT_SPEZ(String c_ID_or_WHEREBLOCK_OR_SQL) throws myException
	{
		super(c_ID_or_WHEREBLOCK_OR_SQL);
	}

	
	public boolean is_EK() throws myException
	{
		return S.NN(this.get_DEF_QUELLE_ZIEL_cUF()).trim().equals("EK");
	}
	
	public boolean is_VK() throws myException
	{
		return S.NN(this.get_DEF_QUELLE_ZIEL_cUF()).trim().equals("VK");
	}
	
	
	/**
	 * wert der in der Abrechnung zum kontrakt zugeschlagen wird, in der regel die "richtige" (lademenge fuer ek, ablademenge fuer vk) , ausser umgedreht
	 * @return
	 * @throws myException
	 */
	public Double get__MengeZu_Kontrakt_oder_PlanMenge_netto() throws myException
	{
		Double dWert = null;
		
		if (this.is_EK())
		{
			if (this.is_LADEMENGE_GUTSCHRIFT_YES())
			{
				dWert = (this.get_ANTEIL_LADEMENGE_dValue(this.get_ANTEIL_PLANMENGE_dValue(d0)) - this.get_ABZUG_MENGE_dValue(d0));
			}
			else
			{
				dWert = (this.get_ANTEIL_ABLADEMENGE_dValue(this.get_ANTEIL_PLANMENGE_dValue(d0)) - this.get_ABZUG_MENGE_dValue(d0));
			}
		}
		else
		{
			if (this.is_ABLADEMENGE_RECHNUNG_YES())
			{
				dWert = (this.get_ANTEIL_ABLADEMENGE_dValue(this.get_ANTEIL_PLANMENGE_dValue(d0)) - this.get_ABZUG_MENGE_dValue(d0));
			}
			else
			{
				dWert = (this.get_ANTEIL_LADEMENGE_dValue(this.get_ANTEIL_PLANMENGE_dValue(d0)) - this.get_ABZUG_MENGE_dValue(d0));
			}
		}
		return dWert;
	}
	
	
	/**
	 * wert der in der Abrechnung zum kontrakt zugeschlagen wird, in der regel die "richtige" (lademenge fuer ek, ablademenge fuer vk) , ausser umgedreht
	 * @return
	 * @throws myException
	 */
	public Double get__MengeZu_Kontrakt_netto() throws myException
	{
		Double dWert = null;
		
		if (this.is_EK())
		{
			if (this.is_LADEMENGE_GUTSCHRIFT_YES())
			{
				dWert = (this.get_ANTEIL_LADEMENGE_dValue(d0) - this.get_ABZUG_MENGE_dValue(d0));
			}
			else
			{
				dWert = (this.get_ANTEIL_ABLADEMENGE_dValue(d0) - this.get_ABZUG_MENGE_dValue(d0));
			}
		}
		else
		{
			if (this.is_ABLADEMENGE_RECHNUNG_YES())
			{
				dWert = (this.get_ANTEIL_ABLADEMENGE_dValue(d0) - this.get_ABZUG_MENGE_dValue(d0));
			}
			else
			{
				dWert = (this.get_ANTEIL_LADEMENGE_dValue(d0) - this.get_ABZUG_MENGE_dValue(d0));
			}
		}
		return dWert;
	}
	

	
	/**
	 * nettowert der lademenge oder planmenge
	 * @return
	 * @throws myException
	 */
	public Double get__MengeNetto_oder_planmenge() throws myException
	{
		Double dWert = null;
		
		if (this.is_EK())
		{
			//2012-11-15: bug: planmenge vergessen
			dWert = (this.get_ANTEIL_LADEMENGE_dValue(this.get_ANTEIL_PLANMENGE_dValue(d0)) - this.get_ABZUG_MENGE_dValue(d0));
		}
		else
		{
			//2012-11-15: bug: planmenge vergessen
			dWert = (this.get_ANTEIL_ABLADEMENGE_dValue(this.get_ANTEIL_PLANMENGE_dValue(d0)) - this.get_ABZUG_MENGE_dValue(d0));
		}
		return dWert;
	}
	
	
	
	/**
	 * wert der in der Abrechnung zum kontrakt zugeschlagen wird, in der regel die "richtige" (lademenge fuer ek, ablademenge fuer vk) , ausser umgedreht
	 * @return
	 * @throws myException
	 */
	public Double get__Abrechnungs_oder_Planmenge_netto() throws myException
	{
		Double dWert = null;
		
		if (this.is_EK())
		{
			if (this.is_LADEMENGE_GUTSCHRIFT_YES())
			{
				dWert = (this.get_ANTEIL_LADEMENGE_dValue(this.get_ANTEIL_PLANMENGE_dValue(d0)) - this.get_ABZUG_MENGE_dValue(d0));
			}
			else
			{
				dWert = (this.get_ANTEIL_ABLADEMENGE_dValue(this.get_ANTEIL_PLANMENGE_dValue(d0)) - this.get_ABZUG_MENGE_dValue(d0));
			}
		}
		else
		{
			if (this.is_ABLADEMENGE_RECHNUNG_YES())
			{
				dWert = (this.get_ANTEIL_ABLADEMENGE_dValue(this.get_ANTEIL_PLANMENGE_dValue(d0)) - this.get_ABZUG_MENGE_dValue(d0));
			}
			else
			{
				dWert = (this.get_ANTEIL_LADEMENGE_dValue(this.get_ANTEIL_PLANMENGE_dValue(d0)) - this.get_ABZUG_MENGE_dValue(d0));
			}
		}
		return dWert;
	}

	
	/**
	 * 
	 * @return null, wenn kein land, sonst false fuer land gleich mandantenland, sonst true
	 * @throws myException
	 */
	public Boolean get_bZielOrtIstAusland() throws myException {
		Boolean bRueck = null;
		if (this.get_UP_RECORD_ADRESSE_id_adresse_lager()!=null) {
			if (this.get_UP_RECORD_ADRESSE_id_adresse_lager().get_ID_LAND_cUF_NN("").equals(bibALL.get_RECORD_MANDANT().get_ID_LAND_cUF_NN("-"))) {
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
		if (this.get_UP_RECORD_ADRESSE_id_adresse_lager()!=null) {
			if (this.get_UP_RECORD_ADRESSE_id_adresse_lager().get_UP_RECORD_LAND_id_land()!=null) {
				if (this.get_UP_RECORD_ADRESSE_id_adresse_lager().get_UP_RECORD_LAND_id_land().is_INTRASTAT_JN_NO()) {
					bRueck = new Boolean(true);
				} else {
					bRueck = new Boolean(true);
				}
			}
		}
		return bRueck;
	}

	
	public boolean get_bVK_ORT() throws myException {
		return this.get_DEL_DATE_cUF_NN("-").equals("VK");
	}
	
	
}
