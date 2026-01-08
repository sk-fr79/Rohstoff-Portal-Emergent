package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG;

import java.math.BigDecimal;

import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class FSI_RECORD_VPOS_TPA_FUHRE_own extends RECORD_VPOS_TPA_FUHRE{

	public RECORD_VPOS_TPA_FUHRE_ORT recORT;
	public String ANR1_2;
	public boolean bEK;
	public boolean isOrt = false;
	private BigDecimal 	 		BD0 = new BigDecimal(0);

	public FSI_RECORD_VPOS_TPA_FUHRE_own(RECORD_VPOS_TPA_FUHRE_ORT rec_ORT, String _ANR1_2, boolean EK) throws myException 
	{
		super(rec_ORT.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre());
		this.recORT = rec_ORT;
		this.ANR1_2 = _ANR1_2;
		this.bEK = EK;
		this.isOrt = true;
	}

	public FSI_RECORD_VPOS_TPA_FUHRE_own(RECORD_VPOS_TPA_FUHRE rec_Fuhre, String _ANR1_2, boolean EK) throws myException 
	{
		super(rec_Fuhre);
		this.ANR1_2 = _ANR1_2;
		this.bEK = EK;
		this.isOrt = false;
	}


	public String get_ARTBEZ1_EK_cF_NN(String cNotNullValue) throws myException
	{
		if (this.recORT==null)
		{
			return super.get_ARTBEZ1_EK_cF_NN(cNotNullValue);
		}
		else
		{
			return this.recORT.get_ARTBEZ1_cF_NN(cNotNullValue);
		}
	}



	public String get_ARTBEZ1_VK_cF_NN(String cNotNullValue) throws myException
	{
		if (this.recORT==null)
		{
			return super.get_ARTBEZ1_VK_cF_NN(cNotNullValue);
		}
		else
		{
			return this.recORT.get_ARTBEZ1_cF_NN(cNotNullValue);
		}
	}




	public BigDecimal get_ANTEIL_PLANMENGE_LIEF_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		if (this.recORT==null)
		{
			return super.get_ANTEIL_PLANMENGE_LIEF_bdValue(bdValueWhenNULL);
		}
		else
		{
			return this.recORT.get_ANTEIL_PLANMENGE_bdValue(bdValueWhenNULL);
		}
	}


	public BigDecimal get_ANTEIL_PLANMENGE_ABN_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		if (this.recORT==null)
		{
			return super.get_ANTEIL_PLANMENGE_ABN_bdValue(bdValueWhenNULL);
		}
		else
		{
			return this.recORT.get_ANTEIL_PLANMENGE_bdValue(bdValueWhenNULL);
		}
	}


	public BigDecimal get__MENGE_FUER_SORT()
	{
		BigDecimal bdRueck = BD0; 
		try
		{
			if (this.bEK)
			{
				bdRueck = this.get_ANTEIL_LADEMENGE_LIEF_bdValue(BD0);
			}
			else
			{
				bdRueck = this.get_ANTEIL_ABLADEMENGE_ABN_bdValue(BD0);
			}
		}
		catch (myException e)
		{
			e.printStackTrace();
		}
		return bdRueck;
	}


	public BigDecimal get__PREIS_FUER_SORT()
	{
		BigDecimal bdRueck = BD0; 
		try
		{
			if (this.bEK)
			{
				if (this.recORT==null)
				{
					bdRueck = this.get_EINZELPREIS_EK_bdValue(BD0);
				}
				else
				{
					bdRueck = this.recORT.get_EINZELPREIS_bdValue(BD0);
				}
			}
			else
			{
				if (this.recORT==null)
				{
					bdRueck = this.get_EINZELPREIS_VK_bdValue(BD0);
				}
				else
				{
					bdRueck = this.recORT.get_EINZELPREIS_bdValue(BD0);
				}
			}
		}
		catch (myException e)
		{
			e.printStackTrace();
		}
		return bdRueck;
	}



	public String get__FIRMA_GEGENSEITE_FUER_SORT()
	{
		String cRueck = ""; 
		try
		{
			if (this.bEK)
			{
				cRueck = this.get_A_NAME1_cUF_NN("");   //bei ek-fuhren wird abnehmer angezeigt
			}
			else
			{
				cRueck = this.get_L_NAME1_cUF_NN("");   //bei vk-fuhren wird lieferant angezeigt
			}
		}
		catch (myException e)
		{
			e.printStackTrace();
		}
		return cRueck;
	}




	public BigDecimal get_ANTEIL_LADEMENGE_LIEF_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		if (this.recORT==null)
		{
			return super.get_ANTEIL_LADEMENGE_LIEF_bdValue(bdValueWhenNULL);
		}
		else
		{
			return this.recORT.get_ANTEIL_LADEMENGE_bdValue(bdValueWhenNULL);
		}
	}


	public BigDecimal get_ANTEIL_ABLADEMENGE_ABN_bdValue(BigDecimal bdValueWhenNULL) throws myException
	{
		if (this.recORT==null)
		{
			return super.get_ANTEIL_ABLADEMENGE_ABN_bdValue(bdValueWhenNULL);
		}
		else
		{
			return this.recORT.get_ANTEIL_ABLADEMENGE_bdValue(bdValueWhenNULL);
		}
	}



	public RECORD_VPOS_RG  recVPOS_RG_UNGELOESCHT_UNSTORNIERT(boolean bEK) throws myException
	{
		RECORD_VPOS_RG  rgRueck = null;

		String cWhereZusatz = " AND LAGER_VORZEICHEN=1 ";

		if (!bEK)
		{
			cWhereZusatz = " AND LAGER_VORZEICHEN=-1 ";
		}

		if (this.recORT==null)
		{
			RECLIST_VPOS_RG recListRG = this.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_zugeord("NVL(JT_VPOS_RG.DELETED,'N')='N' AND JT_VPOS_RG.ID_VPOS_TPA_FUHRE_ORT_ZUGEORD IS NULL"+cWhereZusatz, "JT_VPOS_RG.ID_VPOS_RG", true);

			//den letzten raussuchen
			if (recListRG.get_vKeyValues().size()>0)
			{
				rgRueck = recListRG.get(recListRG.get_vKeyValues().size()-1);

				if (S.isFull(rgRueck.get_ID_VPOS_RG_STORNO_VORGAENGER_cUF_NN("")) || 
						S.isFull(rgRueck.get_ID_VPOS_RG_STORNO_NACHFOLGER_cUF_NN("")))
				{
					rgRueck=null;   //nur den letzten zurueckgeben, wenn er keine storno-merkmale hat
				}
			}

		}
		else
		{
			RECLIST_VPOS_RG recListRG = this.recORT.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_ort_zugeord("NVL(JT_VPOS_RG.DELETED,'N')='N'"+cWhereZusatz, "JT_VPOS_RG.ID_VPOS_RG", true);

			//den letzten raussuchen
			if (recListRG.get_vKeyValues().size()>0)
			{
				rgRueck = recListRG.get(recListRG.get_vKeyValues().size()-1);

				if (S.isFull(rgRueck.get_ID_VPOS_RG_STORNO_VORGAENGER_cUF_NN("")) || 
						S.isFull(rgRueck.get_ID_VPOS_RG_STORNO_NACHFOLGER_cUF_NN("")))
				{
					rgRueck=null;   //nur den letzten zurueckgeben, wenn er keine storno-merkmale hat
				}
			}
		}

		return rgRueck;
	}



	public String get_DATUM_AUFLADEN_cF_NN(String cNotNullValue) throws myException
	{
		if (this.recORT==null)
		{
			return super.get_DATUM_AUFLADEN_cF_NN(cNotNullValue);
		}
		else
		{
			return this.recORT.get_DATUM_LADE_ABLADE_cF_NN(cNotNullValue);
		}
	}

	public String get_DATUM_ABLADEN_cF_NN(String cNotNullValue) throws myException
	{
		if (this.recORT==null)
		{
			return super.get_DATUM_ABLADEN_cF_NN(cNotNullValue);
		}
		else
		{
			return this.recORT.get_DATUM_LADE_ABLADE_cF_NN(cNotNullValue);
		}
	}

	public String get__DATUM_STRING_FUER_LISTE() throws myException
	{
		String cRueck = "";

		if (this.bEK)
		{
			if (this.recORT==null)
			{
				cRueck = super.get_DATUM_AUFLADEN_cF_NN(super.get_DATUM_ABHOLUNG_cF_NN("--")+" *");
			}
			else
			{
				cRueck = this.recORT.get_DATUM_LADE_ABLADE_cF_NN(super.get_DATUM_ABHOLUNG_cF_NN("--")+" *");
			}
		}
		else
		{
			if (this.recORT==null)
			{
				cRueck = super.get_DATUM_ABLADEN_cF_NN(super.get_DATUM_ANLIEFERUNG_cF_NN("--")+" *");
			}
			else
			{
				cRueck = this.recORT.get_DATUM_LADE_ABLADE_cF_NN(super.get_DATUM_ANLIEFERUNG_cF_NN("--")+" *");
			}
		}
		if (cRueck.equals("-- *"))
		{
			cRueck = "-";
		}
		return cRueck;
	}


	public String get__DATUM_STRING_FUER_SORT()
	{
		String cRueck = "";

		try
		{
			if (this.bEK)
			{
				if (this.recORT==null)
				{
					cRueck = super.get_DATUM_AUFLADEN_VALUE_FOR_SQLSTATEMENT();
					if (cRueck.trim().equals("NULL"))
					{
						cRueck = super.get_DATUM_ABHOLUNG_VALUE_FOR_SQLSTATEMENT();
					}
				}
				else
				{
					cRueck = this.recORT.get_DATUM_LADE_ABLADE_VALUE_FOR_SQLSTATEMENT();
					if (cRueck.trim().equals("NULL"))
					{
						cRueck = super.get_DATUM_ABHOLUNG_VALUE_FOR_SQLSTATEMENT();
					}
				}
			}
			else
			{
				if (this.recORT==null)
				{
					cRueck = super.get_DATUM_ABLADEN_VALUE_FOR_SQLSTATEMENT();
					if (cRueck.trim().equals("NULL"))
					{
						cRueck = super.get_DATUM_ANLIEFERUNG_VALUE_FOR_SQLSTATEMENT();
					}
				}
				else
				{
					cRueck = this.recORT.get_DATUM_LADE_ABLADE_VALUE_FOR_SQLSTATEMENT();
					if (cRueck.trim().equals("NULL"))
					{
						cRueck = super.get_DATUM_ANLIEFERUNG_VALUE_FOR_SQLSTATEMENT();
					}
				}
			}
		}
		catch (myException e)
		{
			e.printStackTrace();
		}
		if (cRueck.trim().equals("NULL"))   //ganz nach hinten
		{
			cRueck = "'9999-99-99'";
		}
		return cRueck;
	}

	
	public RECORD_VKOPF_RG get_record_vkopf_rg() throws myException{
		RECLIST_VPOS_RG reclistVpos= null;
		if(isOrt){
			reclistVpos = this.recORT.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_ort_zugeord(
					" NVL(ID_VPOS_RG_STORNO_VORGAENGER,0) = 0 "
						+"AND NVL(ID_VPOS_RG_STORNO_NACHFOLGER,0) = 0 " 
						+"AND NVL(DELETED,'N')='N'"
						,"ID_VPOS_TPA_FUHRE_ORT_ZUGEORD", true);

		}else{
			reclistVpos = this.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_zugeord(
					" NVL(ID_VPOS_RG_STORNO_VORGAENGER,0) = 0"
							+"AND NVL(ID_VPOS_RG_STORNO_NACHFOLGER,0) = 0" 
							+"AND NVL(DELETED,'N')='N'", "ID_VPOS_TPA_FUHRE_ORT_ZUGEORD", true);
		}
		if(reclistVpos.size()==1){
			reclistVpos.get(0).get_UP_RECORD_VKOPF_RG_id_vkopf_rg().get_BUCHUNGSNUMMER_cUF_NN("");
			return reclistVpos.get(0).get_UP_RECORD_VKOPF_RG_id_vkopf_rg();
		}else 
			return null;
	}

	public boolean isbEK() {
		return bEK;
	}
}