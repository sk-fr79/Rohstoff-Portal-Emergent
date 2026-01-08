package rohstoff.Echo2BusinessLogic.FIBU;

import java.math.BigDecimal;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_FIBU;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_FIBU_MAHNUNG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MAHNUNG_POS;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.exceptions.myException;

public class RECORD_FIBU_ext extends RECORD_FIBU 
{

	public RECORD_FIBU_ext() throws myException 
	{
		super();
	}

	public RECORD_FIBU_ext(long lID_Unformated, MyConnection Conn)	throws myException 
	{
		super(lID_Unformated, Conn);
	}

	public RECORD_FIBU_ext(long lID_Unformated) throws myException 
	{
		super(lID_Unformated);
	}

	public RECORD_FIBU_ext(MyConnection Conn) throws myException 
	{
		super(Conn);
	}

	public RECORD_FIBU_ext(RECORD_FIBU recordOrig) 
	{
		super(recordOrig);
	}

	public RECORD_FIBU_ext(String c_ID_or_WHEREBLOCK_OR_SQL, MyConnection Conn)	throws myException 
	{
		super(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}

	public RECORD_FIBU_ext(String c_ID_or_WHEREBLOCK_OR_SQL) throws myException 
	{
		super(c_ID_or_WHEREBLOCK_OR_SQL);
	}

	
	
	
	public BigDecimal get__OFFEN_GEGEN_ENDBETRAG_FREMDWAEHRUNG() throws myException 
	{
		BigDecimal bdRueck = this.get_ENDBETRAG_FREMD_WAEHRUNG_bdValue(BigDecimal.ZERO, 2).multiply(this.get_FAKTOR_BUCHUNG_PLUS_MINUS_bdValue(BigDecimal.ZERO));
		
		RECLIST_FIBU  recList = new RECLIST_FIBU("SELECT * FROM "+bibE2.cTO()+".JT_FIBU WHERE NVL(STORNIERT,'N')='N' AND BUCHUNGSBLOCK_NR="+this.get_BUCHUNGSBLOCK_NR_cUF()+
															" AND JT_FIBU.ID_FIBU<>"+this.get_ID_FIBU_cUF());
		
		BigDecimal bdGegenbuchung = new BigDecimal(0);
		if (recList.get_vKeyValues().size()>0)
		{
			for (int i=0;i<recList.get_vKeyValues().size();i++)
			{
				if (recList.get(i).get_BUCHUNGSTYP_cUF().equals(myCONST.BT_DRUCK_RECHNUNG) || 
					recList.get(i).get_BUCHUNGSTYP_cUF().equals(myCONST.BT_DRUCK_GUTSCHRIFT))
				{
					/*
					 * bei buchungsbloecken, die mehrere Rechnungen/Gutschriften beinhalten, kann kein Saldo gedruckt werden
					 */
					bdGegenbuchung = new BigDecimal(0);
					break;
				}
				else
				{
					bdGegenbuchung.add(recList.get(i).get_ENDBETRAG_FREMD_WAEHRUNG_bdValue(BigDecimal.ZERO, 2).multiply(this.get_FAKTOR_BUCHUNG_PLUS_MINUS_bdValue(BigDecimal.ZERO)));
				}
			}
		}
		
		return bdRueck.subtract(bdGegenbuchung);
	}
	
	
	/*
	 * true bei Rechnungen mit Positivem Zahlbetrag oder Gutschriften mit negativem Zahlbetrag
	 */
	public boolean get_bKannGemahntWerden() throws myException
	{
		boolean bRueck = false;
		
		if (this.get_BUCHUNGSTYP_cUF().equals(myCONST.BT_DRUCK_RECHNUNG) && 
			this.get_ZAHLUNGSBETRAG_FREMD_WAEHRUNG_bdValue(BigDecimal.ZERO).compareTo(BigDecimal.ZERO)>0)
		{
			bRueck = true;
		}
		
		
		if (this.get_BUCHUNGSTYP_cUF().equals(myCONST.BT_DRUCK_GUTSCHRIFT) && 
			this.get_ZAHLUNGSBETRAG_FREMD_WAEHRUNG_bdValue(BigDecimal.ZERO).compareTo(BigDecimal.ZERO)<0)
		{
			bRueck = true;
		}
		
		return bRueck;
	}
	
	
	
	/**
	 * liefert bei mahnfaehigen positionen (Positive Rechnungen / negative Gutschriften)
	 * den zahlungsbetrag je nach schalter zuzgl. skonto 
	 * @return
	 * @throws myException
	 */
	public BigDecimal  get_ZahlBetrag_FW_Abhaengig_von_SkontoSchalter() throws myException
	{
		BigDecimal  bdRueck = this.get_ZAHLUNGSBETRAG_FREMD_WAEHRUNG_bdValue(BigDecimal.ZERO).multiply(this.get_FAKTOR_BUCHUNG_PLUS_MINUS_bdValue(BigDecimal.ZERO));
		
		if (this.is_SKONTO_DATUM_UEBERSCHRITTEN_YES())
		{
			bdRueck = bdRueck.add(this.get_SKONTOBETRAG_FREMD_WAEHRUNG_bdValue(BigDecimal.ZERO).multiply(this.get_FAKTOR_BUCHUNG_PLUS_MINUS_bdValue(BigDecimal.ZERO)));
		}
		
		return bdRueck;
	}
	
	
	
//	/*
//	 * der mahnbetrag ist bei teils gezahlten rechnungen der
//	 * Betrag ohne skonto minus der zahlbetrag
//	 */
//	public BigDecimal get_bdMahnbetrag() throws myException
//	{
//		return this.get_RESTBETRAG_FREMD_WAEHRUNG_bdValue(BigDecimal.ZERO);
//	}
//	
//	
	
	/*
	 * der mahnbetrag ist bei teils gezahlten rechnungen der
	 * Betrag ohne skonto minus der zahlbetrag
	 * 
	 * ## aenderung 2012-05-28: es koennen jetzt auch stornierte positionen, d.h. positionen, wo geld raus geht
	 *    in mahnungen verwendet werden. dabei wird in der maske der zahlbetrag als mahnbetrag angezeigt
	 */
	public BigDecimal get_bdMahnbetrag() throws myException
	{
		BigDecimal bdZahlbetrag = this.get_ZAHLUNGSBETRAG_FREMD_WAEHRUNG_bdValue(BigDecimal.ZERO).multiply(this.get_FAKTOR_BUCHUNG_PLUS_MINUS_bdValue(BigDecimal.ZERO));
		
		if (bdZahlbetrag.compareTo(BigDecimal.ZERO)<0)
		{
			return bdZahlbetrag;
		}
		else
		{
			return this.get_RESTBETRAG_FREMD_WAEHRUNG_bdValue(BigDecimal.ZERO);
		}
	}
	

	
	
	public int get_MAXIMALE_MAHNSTUFE() throws myException
	{
		int iRueck = 0;
		
		RECLIST_FIBU_MAHNUNG reclistMahnungen = this.get_DOWN_RECORD_LIST_FIBU_MAHNUNG_id_fibu();
		
		for (int i=0;i<reclistMahnungen.get_vKeyValues().size();i++)
		{
			if (reclistMahnungen.get(i).get_UP_RECORD_MAHNUNG_id_mahnung().get_MAHNSTUFE_lValue(-1l)>iRueck)
			{
				iRueck=reclistMahnungen.get(i).get_UP_RECORD_MAHNUNG_id_mahnung().get_MAHNSTUFE_lValue(-1l).intValue();
			}
		}
		return iRueck;
	}
	
	public int get_MAXIMALE_POS_MAHNSTUFE() throws myException
	{
		int iRueck = 0;
		
		RECLIST_MAHNUNG_POS reclistMahnungen = this.get_DOWN_RECORD_LIST_MAHNUNG_POS_id_fibu();
		
		for (int i=0;i<reclistMahnungen.get_vKeyValues().size();i++)
		{
			if (reclistMahnungen.get(i).get_UP_RECORD_MAHNUNG_id_mahnung().get_MAHNSTUFE_lValue(-1l)>iRueck)
			{
				iRueck=reclistMahnungen.get(i).get_UP_RECORD_MAHNUNG_id_mahnung().get_MAHNSTUFE_lValue(-1l).intValue();
			}
		}
		return iRueck;
	}
	
	
	
	
}
