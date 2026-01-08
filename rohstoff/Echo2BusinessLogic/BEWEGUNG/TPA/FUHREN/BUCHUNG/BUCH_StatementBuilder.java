package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.BUCHUNG;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ABZUG_EK;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ABZUG_VK;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT_ABZUG;
import panter.gmbh.indep.BigDecimalFactory;
import panter.gmbh.indep.MyDouble;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.myDataRecordCopySQLString;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.ABZUEGE.BL_CONST_ABZUG;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.PRUEF_RECORD_VPOS_TPA_FUHRE;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.PRUEF_RECORD_VPOS_TPA_FUHRE_ORT;

public class BUCH_StatementBuilder extends MySqlStatementBuilder
{

	/*
	 * wird beim erzeugen des Statementsbuilders auch gefuellt, damit kann mit den gleichen
	 * methoden auch die summe der abrechungsmenge verglichen werden und damit
	 * der status der fuhre errechnet werden 
	 */
	private boolean    bEK = true;
	
	
	//eins der beiden ist immer null
	private PRUEF_RECORD_VPOS_TPA_FUHRE  		recVPOS_TPA_FUHRE = null;
	private PRUEF_RECORD_VPOS_TPA_FUHRE_ORT  	recVPOS_TPA_FUHRE_ORT = null;
	
	private RECORD_ADRESSE   					recAdresse = null; 
	
	
	//schalter, der festlegt, ob es ein BUCH_StatementBuilder, der auf ein eigenes lager zeigt
	private boolean    							bIstEigenesLager = false;
	
	//schalter, der festlegt, ob es ein buchungsstatement ist, das als zahl 0-mengen enthaelt. von diesen positionen wird keine gebucht
	private boolean    							bBuchungMengeIST_Zahl_0 = false;
	
	
	
	public BUCH_StatementBuilder(PRUEF_RECORD_VPOS_TPA_FUHRE  rVPOS_TPA_FUHRE, boolean EK) throws myException
	{
		super();

		this.recVPOS_TPA_FUHRE = rVPOS_TPA_FUHRE;
		this.bEK = EK;
		
		if (EK)
		{
			this.bIstEigenesLager = (this.recVPOS_TPA_FUHRE.get_ID_ADRESSE_START_cUF().equals(bibALL.get_ID_ADRESS_MANDANT()));
			if (this.recVPOS_TPA_FUHRE.get_bdMengeGutschrift()!=null && this.recVPOS_TPA_FUHRE.get_bdMengeGutschrift().compareTo(BigDecimal.ZERO)==0)
			{
				this.bBuchungMengeIST_Zahl_0=true;
			}
		}
		else
		{
			this.bIstEigenesLager = (this.recVPOS_TPA_FUHRE.get_ID_ADRESSE_ZIEL_cUF().equals(bibALL.get_ID_ADRESS_MANDANT()));
			if (this.recVPOS_TPA_FUHRE.get_bdMengeRechnung()!=null && this.recVPOS_TPA_FUHRE.get_bdMengeRechnung().compareTo(BigDecimal.ZERO)==0)
			{
				this.bBuchungMengeIST_Zahl_0=true;
			}
		}
		
		this._baseSettings();
	}

	public BUCH_StatementBuilder(PRUEF_RECORD_VPOS_TPA_FUHRE_ORT  rVPOS_TPA_FUHRE_ORT) throws myException
	{
		super();

		this.recVPOS_TPA_FUHRE_ORT = rVPOS_TPA_FUHRE_ORT;
		this.bEK = this.recVPOS_TPA_FUHRE_ORT.get_DEF_QUELLE_ZIEL_cF().equals("EK");
		
		this.bIstEigenesLager = (this.recVPOS_TPA_FUHRE_ORT.get_ID_ADRESSE_cUF().equals(bibALL.get_ID_ADRESS_MANDANT()));
		
		if (this.recVPOS_TPA_FUHRE_ORT.get_bdMenge()!=null && this.recVPOS_TPA_FUHRE_ORT.get_bdMenge().compareTo(BigDecimal.ZERO)==0)
		{
			this.bBuchungMengeIST_Zahl_0=true;
		}
		

		this._baseSettings();
	}

	
	private void _baseSettings() throws myException
	{
		
		//felder, die immer drinne sind
		this.addSQL_Paar("ID_VPOS_RG",			"SEQ_VPOS_RG.NEXTVAL",				false);
		this.addSQL_Paar("POSITION_TYP",		myCONST.VG_POSITION_TYP_ARTIKEL,	true);
		this.addSQL_Paar("POSITIONSNUMMER",		"0",								false);
		this.addSQL_Paar("ID_VKOPF_RG",			"NULL",								false);
		this.addSQL_Paar("GEBUCHT",				"'N'",false);
		this.addSQL_Paar("VORGANG_TYP",			"NULL",false);
		this.addSQL_Paar("ANZAHL_ABZUG",		"0",false);
		this.addSQL_Paar("GESAMTPREIS_ABZUG",	"0",false);
		this.addSQL_Paar("EINZELPREIS_ABZUG",	"0",false);
		this.addSQL_Paar("POSITIONSKLASSE",		"NULL",false);
		this.addSQL_Paar("IST_SONDERPOSITION",	"'N'",								false);
		this.addSQL_Paar("ID_WAEHRUNG_FREMD",	bibALL.get_RECORD_MANDANT().get_ID_WAEHRUNG_VALUE_FOR_SQLSTATEMENT(),false);
		this.addSQL_Paar("WAEHRUNGSKURS",		"1",false);
	
	}

	
	/*
	 * fuellt basisfelder, falls kein kontrakt vorhanden istz
	 */
	public void fill_baseFieldsFromAdress(RECORD_ADRESSE  recKunde) throws myException
	{
		this.recAdresse = recKunde;
		
		//basiswerte vom kunden

		//2011-05-07: zahlungsbedingungen und lieferbedingungen zwischen EK- und VK-Typ unterscheiden
		if (this.bEK)
		{
			this.addSQL_Paar("ID_ZAHLUNGSBEDINGUNGEN",	recKunde.get_ID_ZAHLUNGSBEDINGUNGEN_VALUE_FOR_SQLSTATEMENT());

			if (recKunde.get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen() != null)
			{
				//2012-03-21: falsche zahlungsbedingungstexte (falsch: kurztext wird gezogen)
				//this.addSQL_Paar("ZAHLUNGSBEDINGUNGEN",		recKunde.get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen().get_KURZBEZEICHNUNG_VALUE_FOR_SQLSTATEMENT());
				this.addSQL_Paar("ZAHLUNGSBEDINGUNGEN",		recKunde.get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen().get_BEZEICHNUNG_VALUE_FOR_SQLSTATEMENT());
				this.addSQL_Paar("ZAHLTAGE",				recKunde.get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen().get_ZAHLTAGE_VALUE_FOR_SQLSTATEMENT());
				this.addSQL_Paar("FIXMONAT",				recKunde.get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen().get_FIXMONAT_VALUE_FOR_SQLSTATEMENT());
				this.addSQL_Paar("FIXTAG",					recKunde.get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen().get_FIXTAG_VALUE_FOR_SQLSTATEMENT());
				this.addSQL_Paar("SKONTO_PROZENT",			recKunde.get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen().get_SKONTO_PROZENT_VALUE_FOR_SQLSTATEMENT());
			}

			if (recKunde.get_UP_RECORD_LIEFERBEDINGUNGEN_id_lieferbedingungen() != null)
			{
				this.addSQL_Paar("LIEFERBEDINGUNGEN",		recKunde.get_UP_RECORD_LIEFERBEDINGUNGEN_id_lieferbedingungen().get_KURZBEZEICHNUNG_VALUE_FOR_SQLSTATEMENT());
			}
			
		}
		else
		{
			this.addSQL_Paar("ID_ZAHLUNGSBEDINGUNGEN",	recKunde.get_ID_ZAHLUNGSBEDINGUNGEN_VK_VALUE_FOR_SQLSTATEMENT());

			if (recKunde.get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen_vk() != null)
			{
				//2012-03-21: falsche zahlungsbedingungstexte (falsch: kurztext wird gezogen)
				//this.addSQL_Paar("ZAHLUNGSBEDINGUNGEN",		recKunde.get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen_vk().get_KURZBEZEICHNUNG_VALUE_FOR_SQLSTATEMENT());
				this.addSQL_Paar("ZAHLUNGSBEDINGUNGEN",		recKunde.get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen_vk().get_BEZEICHNUNG_VALUE_FOR_SQLSTATEMENT());
				this.addSQL_Paar("ZAHLTAGE",				recKunde.get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen_vk().get_ZAHLTAGE_VALUE_FOR_SQLSTATEMENT());
				this.addSQL_Paar("FIXMONAT",				recKunde.get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen_vk().get_FIXMONAT_VALUE_FOR_SQLSTATEMENT());
				this.addSQL_Paar("FIXTAG",					recKunde.get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen_vk().get_FIXTAG_VALUE_FOR_SQLSTATEMENT());
				this.addSQL_Paar("SKONTO_PROZENT",			recKunde.get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen_vk().get_SKONTO_PROZENT_VALUE_FOR_SQLSTATEMENT());
			}
			
			if (recKunde.get_UP_RECORD_LIEFERBEDINGUNGEN_id_lieferbedingungen_vk() != null)
			{
				this.addSQL_Paar("LIEFERBEDINGUNGEN",		recKunde.get_UP_RECORD_LIEFERBEDINGUNGEN_id_lieferbedingungen_vk().get_KURZBEZEICHNUNG_VALUE_FOR_SQLSTATEMENT());
			}

		}

		
		
	}
	
	
	
	public BigDecimal get_MengenSumme_RG_POS() throws myException
	{

		String cSumme = null;
		if (this.recVPOS_TPA_FUHRE_ORT==null)
		{
			cSumme = bibDB.EinzelAbfrage("SELECT NVL(SUM(ANZAHL),0) FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE " +
					" NVL(DELETED,'N')='N' AND " +
					(this.bEK?" LAGER_VORZEICHEN=1 ":" LAGER_VORZEICHEN=-1 ")+ " AND "+
					" ID_VPOS_TPA_FUHRE_ZUGEORD="+this.recVPOS_TPA_FUHRE.get_ID_VPOS_TPA_FUHRE_cUF()+" AND ID_VPOS_TPA_FUHRE_ORT_ZUGEORD IS NULL");
		}
		else
		{
			cSumme = bibDB.EinzelAbfrage("SELECT NVL(SUM(ANZAHL),0) FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE NVL(DELETED,'N')='N' " +
												" AND ID_VPOS_TPA_FUHRE_ZUGEORD="+this.recVPOS_TPA_FUHRE_ORT.get_ID_VPOS_TPA_FUHRE_cUF()+
												" AND ID_VPOS_TPA_FUHRE_ORT_ZUGEORD="+this.recVPOS_TPA_FUHRE_ORT.get_ID_VPOS_TPA_FUHRE_ORT_cUF());
		}

		MyDouble doSumme = new MyDouble(cSumme,false);
		
		if (doSumme.get_oDouble()==null)
			throw new myException(this,"Sum-Value is not correct !");
		
		
		return BigDecimalFactory.BigDecimal3Round(doSumme.get_oDouble());
	}

	
	/*
	 * pruefung, ob die fuhre (-seite)  bereits gebucht wurde.
	 * Falls alles ok ist, gibt es nur zwei moeglichkeiten:
	 * Die summe der mengen in RG-POS (summe alle buchungen und stornos) ist 0 oder der wert, der gebucht werden muss, alle anderen moeglichkeiten duerfen nicht sein
	 */
	public boolean get_bIstBereitsGebucht() throws myException
	{
		BigDecimal bdMengeZuVerbuchen = 			this.get_bdMengeZurVerbuchung();
		BigDecimal bdMenge_bereits_in_VPOS_RG = 	this.get_MengenSumme_RG_POS();
		BigDecimal bdNull =							new BigDecimal(0);

		//es kann den fall geben, dass ein unbestimmtes statement existiert mit buchungsmenge = null
		if (bdMengeZuVerbuchen==null || bdMengeZuVerbuchen.compareTo(bdNull)==0)
		{
			if (bdMenge_bereits_in_VPOS_RG.compareTo(bdNull)!=0)   //dann darf nichts in den positionen stehen
			{
				throw new myException(this,"Error in booking system: please check all positions of this FUHRE ! (POS1)");
			}
			return false;
		}
		else          //es ist eine Menge!=0 zu verbuchen
		{
			if (bdMenge_bereits_in_VPOS_RG.compareTo(bdMengeZuVerbuchen)==0)        //dann ist die zu buchende summe gleich der summe in den bereits vorhandenen positionen, alles ok
			{
				return true;
			}
			else if (bdMenge_bereits_in_VPOS_RG.compareTo(bdNull)==0)
			{
				return false;
			}
			else   //dann liegt ein fehler vor
			{
				throw new myException(this,"Error in booking system: please check all positions of this FUHRE ! (POS3)");
			}
		}
	}

	
	
	//2013-01-24: neue variante der pruefung: auch die preispruefung testen
	//alte version
//	public boolean get_bBuchungsangabenSindKomplett() throws myException
//	{
//		boolean bRueck = true;
//
//		//relevante kenngroessen: 
//		BigDecimal  bdMenge = null;
//		boolean     bMengeGeprueft = false;
//		BigDecimal  bdPreis = null;
//		BigDecimal  bdMWST = null;
//		String      cSteuerVermerk = null;
//		
//		if (this.recVPOS_TPA_FUHRE_ORT == null)    //variante buchung der hauptfuhre
//		{
//			if (bEK)
//			{
//				bdMenge = this.recVPOS_TPA_FUHRE.get_ANTEIL_LADEMENGE_LIEF_bdValue(null, 3);
//				if (this.recVPOS_TPA_FUHRE.is_LADEMENGE_GUTSCHRIFT_NO())
//				{
//					bdMenge = this.recVPOS_TPA_FUHRE.get_ANTEIL_ABLADEMENGE_LIEF_bdValue(null, 3);
//				}
//				if (this.recVPOS_TPA_FUHRE.is_PRUEFUNG_LADEMENGE_YES())
//				{
//					bMengeGeprueft = true;
//				}
//				bdPreis = this.recVPOS_TPA_FUHRE.get_EINZELPREIS_EK_bdValue(null,2);
//				bdMWST =  this.recVPOS_TPA_FUHRE.get_STEUERSATZ_EK_bdValue(null,2);
//				cSteuerVermerk = this.recVPOS_TPA_FUHRE.get_EU_STEUER_VERMERK_EK_cUF_NN(null);
//			}
//			else
//			{
//				bdMenge = this.recVPOS_TPA_FUHRE.get_ANTEIL_ABLADEMENGE_ABN_bdValue(null, 3);
//				if (this.recVPOS_TPA_FUHRE.is_ABLADEMENGE_RECHNUNG_NO())
//				{
//					bdMenge = this.recVPOS_TPA_FUHRE.get_ANTEIL_LADEMENGE_ABN_bdValue(null, 3);
//				}
//				
//				
//				if (this.recVPOS_TPA_FUHRE.is_PRUEFUNG_ABLADEMENGE_YES())
//				{
//					bMengeGeprueft = true;
//				}
//				
//				bdPreis = this.recVPOS_TPA_FUHRE.get_EINZELPREIS_VK_bdValue(null,2);
//				bdMWST =  this.recVPOS_TPA_FUHRE.get_STEUERSATZ_VK_bdValue(null,2);
//				cSteuerVermerk = this.recVPOS_TPA_FUHRE.get_EU_STEUER_VERMERK_VK_cUF_NN(null);
//			}
//		}
//		else
//		{
//			if (bEK)
//			{
//				bdMenge = this.recVPOS_TPA_FUHRE_ORT.get_ANTEIL_LADEMENGE_bdValue(null, 3);
//				if (this.recVPOS_TPA_FUHRE_ORT.is_LADEMENGE_GUTSCHRIFT_NO())
//				{
//					bdMenge = this.recVPOS_TPA_FUHRE_ORT.get_ANTEIL_ABLADEMENGE_bdValue(null, 3);
//				}
//			}
//			else
//			{
//				bdMenge = this.recVPOS_TPA_FUHRE_ORT.get_ANTEIL_ABLADEMENGE_bdValue(null, 3);
//				if (this.recVPOS_TPA_FUHRE_ORT.is_ABLADEMENGE_RECHNUNG_NO())
//				{
//					bdMenge = this.recVPOS_TPA_FUHRE_ORT.get_ANTEIL_LADEMENGE_bdValue(null, 3);
//				}
//			}
//			
//			
//			if (this.recVPOS_TPA_FUHRE_ORT.is_PRUEFUNG_MENGE_YES())
//			{
//				bMengeGeprueft = true;
//			}
//			
//			bdPreis = this.recVPOS_TPA_FUHRE_ORT.get_EINZELPREIS_bdValue(null,2);
//			bdMWST =  this.recVPOS_TPA_FUHRE_ORT.get_STEUERSATZ_bdValue(null,2);
//			cSteuerVermerk = this.recVPOS_TPA_FUHRE_ORT.get_EU_STEUER_VERMERK_cUF_NN(null);
//
//		}
//		if (bdMenge == null || bdPreis == null || bdMWST == null || (!bMengeGeprueft) || S.isEmpty(cSteuerVermerk))
//		{
//			bRueck = false;
//		}
//		
//		
//		//2011-03-11: Ladedatum muss bei abrechnung einer vK immer eingetragen sein
//		if (!bEK)
//		{
//			if (this.recVPOS_TPA_FUHRE_ORT == null)    //variante buchung der hauptfuhre
//			{
//				Vector<String> V1 = this.recVPOS_TPA_FUHRE.get_vAlleBeteiligtenLadeDaten_SQL_NOTATION();
//				Vector<String> V2 = this.recVPOS_TPA_FUHRE.get_vAlleBeteiligtenLadeDaten_SQL_NOTATION_NOT_NULL_SORTED();
//				if (V1.size()!=V2.size())
//				{
//					bRueck = false;
//				}
//			}
//			else
//			{
//				Vector<String> V1 = this.recVPOS_TPA_FUHRE_ORT.get_vAlleBeteiligtenLadeDaten_SQL_NOTATION();
//				Vector<String> V2 = this.recVPOS_TPA_FUHRE_ORT.get_vAlleBeteiligtenLadeDaten_SQL_NOTATION_NOT_NULL_SORTED();
//				if (V1.size()!=V2.size())
//				{
//					bRueck = false;
//				}
//			}
//		}
//		
//		
//		
//		return bRueck;
//	}

	//neue version ab 2013-01-24:
	public boolean get_bBuchungsangabenSindKomplett() throws myException
	{
		boolean bRueck = true;

		//relevante kenngroessen: 
		BigDecimal  bdMenge = null;
		boolean     bMengeGeprueft = false;
		boolean     bPreisGeprueft = false;
		BigDecimal  bdPreis = null;
		BigDecimal  bdMWST = null;
		String      cSteuerVermerk = null;
		
		if (this.recVPOS_TPA_FUHRE_ORT == null)    //variante buchung der hauptfuhre
		{
			if (bEK)
			{
				bdMenge = this.recVPOS_TPA_FUHRE.get_ANTEIL_LADEMENGE_LIEF_bdValue(null, 3);
				if (this.recVPOS_TPA_FUHRE.is_LADEMENGE_GUTSCHRIFT_NO())
				{
					bdMenge = this.recVPOS_TPA_FUHRE.get_ANTEIL_ABLADEMENGE_LIEF_bdValue(null, 3);
				}
				if (this.recVPOS_TPA_FUHRE.is_PRUEFUNG_LADEMENGE_YES())
				{
					bMengeGeprueft = true;
				}
				
				//2013-01-24: preispruefung
				if (this.recVPOS_TPA_FUHRE.is_PRUEFUNG_EK_PREIS_YES())
				{
					bPreisGeprueft = true;
				}
				
				
				bdPreis = this.recVPOS_TPA_FUHRE.get_EINZELPREIS_EK_bdValue(null,2);
				bdMWST =  this.recVPOS_TPA_FUHRE.get_STEUERSATZ_EK_bdValue(null,2);
				cSteuerVermerk = this.recVPOS_TPA_FUHRE.get_EU_STEUER_VERMERK_EK_cUF_NN(null);
			}
			else
			{
				bdMenge = this.recVPOS_TPA_FUHRE.get_ANTEIL_ABLADEMENGE_ABN_bdValue(null, 3);
				if (this.recVPOS_TPA_FUHRE.is_ABLADEMENGE_RECHNUNG_NO())
				{
					bdMenge = this.recVPOS_TPA_FUHRE.get_ANTEIL_LADEMENGE_ABN_bdValue(null, 3);
				}
				
				
				if (this.recVPOS_TPA_FUHRE.is_PRUEFUNG_ABLADEMENGE_YES())
				{
					bMengeGeprueft = true;
				}
				
				//2013-01-24: preispruefung
				if (this.recVPOS_TPA_FUHRE.is_PRUEFUNG_VK_PREIS_YES())
				{
					bPreisGeprueft = true;
				}

				
				
				bdPreis = this.recVPOS_TPA_FUHRE.get_EINZELPREIS_VK_bdValue(null,2);
				bdMWST =  this.recVPOS_TPA_FUHRE.get_STEUERSATZ_VK_bdValue(null,2);
				cSteuerVermerk = this.recVPOS_TPA_FUHRE.get_EU_STEUER_VERMERK_VK_cUF_NN(null);
			}
		}
		else
		{
			if (bEK)
			{
				bdMenge = this.recVPOS_TPA_FUHRE_ORT.get_ANTEIL_LADEMENGE_bdValue(null, 3);
				if (this.recVPOS_TPA_FUHRE_ORT.is_LADEMENGE_GUTSCHRIFT_NO())
				{
					bdMenge = this.recVPOS_TPA_FUHRE_ORT.get_ANTEIL_ABLADEMENGE_bdValue(null, 3);
				}
			}
			else
			{
				bdMenge = this.recVPOS_TPA_FUHRE_ORT.get_ANTEIL_ABLADEMENGE_bdValue(null, 3);
				if (this.recVPOS_TPA_FUHRE_ORT.is_ABLADEMENGE_RECHNUNG_NO())
				{
					bdMenge = this.recVPOS_TPA_FUHRE_ORT.get_ANTEIL_LADEMENGE_bdValue(null, 3);
				}
			}
			
			
			if (this.recVPOS_TPA_FUHRE_ORT.is_PRUEFUNG_MENGE_YES())
			{
				bMengeGeprueft = true;
			}

			//2013-01-24: preispruefung
			if (this.recVPOS_TPA_FUHRE_ORT.is_PRUEFUNG_PREIS_YES())
			{
				bPreisGeprueft = true;
			}

			
			bdPreis = this.recVPOS_TPA_FUHRE_ORT.get_EINZELPREIS_bdValue(null,2);
			bdMWST =  this.recVPOS_TPA_FUHRE_ORT.get_STEUERSATZ_bdValue(null,2);
			cSteuerVermerk = this.recVPOS_TPA_FUHRE_ORT.get_EU_STEUER_VERMERK_cUF_NN(null);

		}
		if (bdMenge == null || bdPreis == null || bdMWST == null || (!bMengeGeprueft) || (!bPreisGeprueft) || S.isEmpty(cSteuerVermerk))
		{
			bRueck = false;
		}
		
		
		//2011-03-11: Ladedatum muss bei abrechnung einer vK immer eingetragen sein
		if (!bEK)
		{
			if (this.recVPOS_TPA_FUHRE_ORT == null)    //variante buchung der hauptfuhre
			{
				Vector<String> V1 = this.recVPOS_TPA_FUHRE.get_vAlleBeteiligtenLadeDaten_SQL_NOTATION();
				Vector<String> V2 = this.recVPOS_TPA_FUHRE.get_vAlleBeteiligtenLadeDaten_SQL_NOTATION_NOT_NULL_SORTED();
				if (V1.size()!=V2.size())
				{
					bRueck = false;
				}
			}
			else
			{
				Vector<String> V1 = this.recVPOS_TPA_FUHRE_ORT.get_vAlleBeteiligtenLadeDaten_SQL_NOTATION();
				Vector<String> V2 = this.recVPOS_TPA_FUHRE_ORT.get_vAlleBeteiligtenLadeDaten_SQL_NOTATION_NOT_NULL_SORTED();
				if (V1.size()!=V2.size())
				{
					bRueck = false;
				}
			}
		}
		
		
		
		return bRueck;
	}

	
	
	public PRUEF_RECORD_VPOS_TPA_FUHRE get_recVPOS_TPA_FUHRE()
	{
		return recVPOS_TPA_FUHRE;
	}

	public PRUEF_RECORD_VPOS_TPA_FUHRE_ORT get_recVPOS_TPA_FUHRE_ORT()
	{
		return recVPOS_TPA_FUHRE_ORT;
	}



	/*
	 * ein vector, der einen satz von SQL-statements aufbaut, die evtl. vorhandene abzuege aus einer 
	 * BAM dieser fuhre uebernehmen kann
	 */
	public Vector<String>  get_vSQL_AbzugsStack() throws myException
	{
		Vector<String>  vRueck = new Vector<String>();

		HashMap<String,String> hmErsatz = new HashMap<String,String>();
		hmErsatz.put("ID_VPOS_RG","SEQ_VPOS_RG.CURRVAL");
		
		
		// 1. Frage: ist dieses statement aus einer fuhre oder einem fuhrenort gebaut
		if (this.recVPOS_TPA_FUHRE != null)
		{
			if (this.bEK)
			{
				if (this.recVPOS_TPA_FUHRE.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ABZUG_EK_id_vpos_tpa_fuhre().get_vKeyValues().size()>0)
				{
					for (int i=0;i<this.recVPOS_TPA_FUHRE.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ABZUG_EK_id_vpos_tpa_fuhre().get_vKeyValues().size();i++)
					{
						RECORD_VPOS_TPA_FUHRE_ABZUG_EK recAbzug = this.recVPOS_TPA_FUHRE.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ABZUG_EK_id_vpos_tpa_fuhre().get(i);

						
						if (recAbzug.get_ABZUGTYP_cUF().equals(BL_CONST_ABZUG.ABZUGTYP_BETRAG_ABSOLUT)|| 
							recAbzug.get_ABZUGTYP_cUF().equals(BL_CONST_ABZUG.ABZUGTYP_EPREIS_ABSOLUT))
						{
							//dann wird hier der abzug auf fremdwaehrung umgerechnet
							hmErsatz.put("ABZUG","ROUND(ABZUG*"+this.get("WAEHRUNGSKURS")+",2)");
						}
						myDataRecordCopySQLString oCopy = new myDataRecordCopySQLString("JT_VPOS_TPA_FUHRE_ABZUG_EK",
																						"ID_VPOS_TPA_FUHRE_ABZUG_EK",
																						recAbzug.get_ID_VPOS_TPA_FUHRE_ABZUG_EK_cUF(),
																						"JT_VPOS_RG_ABZUG",
																						"ID_VPOS_RG_ABZUG",
																						null,
																						hmErsatz,
																						true);
						vRueck.add(oCopy.get_cINSERT_String());
					}
				}
			}
			else
			{
				if (this.recVPOS_TPA_FUHRE.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ABZUG_VK_id_vpos_tpa_fuhre().get_vKeyValues().size()>0)
				{
					for (int i=0;i<this.recVPOS_TPA_FUHRE.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ABZUG_VK_id_vpos_tpa_fuhre().get_vKeyValues().size();i++)
					{
						RECORD_VPOS_TPA_FUHRE_ABZUG_VK recAbzug = this.recVPOS_TPA_FUHRE.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ABZUG_VK_id_vpos_tpa_fuhre().get(i);

						if (recAbzug.get_ABZUGTYP_cUF().equals(BL_CONST_ABZUG.ABZUGTYP_BETRAG_ABSOLUT)|| 
							recAbzug.get_ABZUGTYP_cUF().equals(BL_CONST_ABZUG.ABZUGTYP_EPREIS_ABSOLUT))
						{
							//dann wird hier der abzug auf fremdwaehrung umgerechnet
							hmErsatz.put("ABZUG","ROUND(ABZUG*"+this.get("WAEHRUNGSKURS")+",2)");
						}
						myDataRecordCopySQLString oCopy = new myDataRecordCopySQLString("JT_VPOS_TPA_FUHRE_ABZUG_VK",
																						"ID_VPOS_TPA_FUHRE_ABZUG_VK",
																						recAbzug.get_ID_VPOS_TPA_FUHRE_ABZUG_VK_cUF(),
																						"JT_VPOS_RG_ABZUG",
																						"ID_VPOS_RG_ABZUG",
																						null,
																						hmErsatz,
																						true);
						vRueck.add(oCopy.get_cINSERT_String());
					}
				}
				
			}
		}
		else
		{
			if (this.recVPOS_TPA_FUHRE_ORT.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_ABZUG_id_vpos_tpa_fuhre_ort().get_vKeyValues().size()>0)
			{
				for (int i=0;i<this.recVPOS_TPA_FUHRE_ORT.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_ABZUG_id_vpos_tpa_fuhre_ort().get_vKeyValues().size();i++)
				{
					RECORD_VPOS_TPA_FUHRE_ORT_ABZUG recAbzug = this.recVPOS_TPA_FUHRE_ORT.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_ABZUG_id_vpos_tpa_fuhre_ort().get(i);

					if (recAbzug.get_ABZUGTYP_cUF().equals(BL_CONST_ABZUG.ABZUGTYP_BETRAG_ABSOLUT)|| 
						recAbzug.get_ABZUGTYP_cUF().equals(BL_CONST_ABZUG.ABZUGTYP_EPREIS_ABSOLUT))
					{
						//dann wird hier der abzug auf fremdwaehrung umgerechnet
						hmErsatz.put("ABZUG","ROUND(ABZUG*"+this.get("WAEHRUNGSKURS")+",2)");
					}
					myDataRecordCopySQLString oCopy = new myDataRecordCopySQLString("JT_VPOS_TPA_FUHRE_ORT_ABZUG",
																					"ID_VPOS_TPA_FUHRE_ORT_ABZUG",
																					recAbzug.get_ID_VPOS_TPA_FUHRE_ORT_ABZUG_cUF(),
																					"JT_VPOS_RG_ABZUG",
																					"ID_VPOS_RG_ABZUG",
																					null,
																					hmErsatz,
																					true);
					vRueck.add(oCopy.get_cINSERT_String());
				}
			}
		}
		
		return vRueck;
	}

	
	
	public boolean get_bEK()
	{
		return this.bEK;
	}

	
	
	public BigDecimal get_DifferenzZwischenBuchungsmengeUndWiegemenge() throws myException
	{
		BigDecimal  bdRueck = null;
		
		if (this.recVPOS_TPA_FUHRE_ORT != null)
		{
			if (this.bEK)
			{
				
				if (this.recVPOS_TPA_FUHRE_ORT.is_LADEMENGE_GUTSCHRIFT_YES())  //dann  null
				{
					bdRueck = new BigDecimal(0);
				}
				else
				{
					bdRueck = this.recVPOS_TPA_FUHRE_ORT.get_ANTEIL_LADEMENGE_bdValue(new BigDecimal(0)).subtract(this.recVPOS_TPA_FUHRE_ORT.get_ANTEIL_ABLADEMENGE_bdValue(new BigDecimal(0)));	
				}
			}
			else
			{
				if (this.recVPOS_TPA_FUHRE_ORT.is_ABLADEMENGE_RECHNUNG_YES())  //dann  null
				{
					bdRueck = new BigDecimal(0);
				}
				else
				{
					bdRueck = this.recVPOS_TPA_FUHRE_ORT.get_ANTEIL_ABLADEMENGE_bdValue(new BigDecimal(0)).subtract(this.recVPOS_TPA_FUHRE_ORT.get_ANTEIL_LADEMENGE_bdValue(new BigDecimal(0)));	
				}
			}
		}
		else
		{
			if (this.bEK)
			{
				
				if (this.recVPOS_TPA_FUHRE.is_LADEMENGE_GUTSCHRIFT_YES())  //dann  null
				{
					bdRueck = new BigDecimal(0);
				}
				else
				{
					bdRueck = this.recVPOS_TPA_FUHRE.get_ANTEIL_LADEMENGE_LIEF_bdValue(new BigDecimal(0)).subtract(this.recVPOS_TPA_FUHRE.get_ANTEIL_ABLADEMENGE_LIEF_bdValue(new BigDecimal(0)));	
				}
			}
			else
			{
				if (this.recVPOS_TPA_FUHRE.is_ABLADEMENGE_RECHNUNG_YES())  //dann  null
				{
					bdRueck = new BigDecimal(0);
				}
				else
				{
					bdRueck = this.recVPOS_TPA_FUHRE.get_ANTEIL_ABLADEMENGE_ABN_bdValue(new BigDecimal(0)).subtract(this.recVPOS_TPA_FUHRE.get_ANTEIL_LADEMENGE_ABN_bdValue(new BigDecimal(0)));	
				}
			}
			
		}
		return bdRueck;
	}
	
	
	
	public String get_cNAME1() throws myException
	{
		return this.get_cADRESS_FIELD("NAME1");
	}
	
	public String get_cORT() throws myException
	{
		return this.get_cADRESS_FIELD("ORT");
	}

	
	public String get_NAME_ORT() throws myException
	{
		return this.get_cNAME1()+" - "+this.get_cORT();
	}
	
	
	public String get_cArtbez() throws myException
	{
		String cRueck = "";
		if (this.recVPOS_TPA_FUHRE_ORT!=null)
		{
			cRueck = this.recVPOS_TPA_FUHRE_ORT.get_ARTBEZ1_cUF_NN("-")+"   ("+this.recVPOS_TPA_FUHRE_ORT.get_ANR1_cUF_NN("-")+"-"+this.recVPOS_TPA_FUHRE_ORT.get_ANR2_cUF_NN("-")+")";
		}
		else
		{
			if (this.bEK)
			{
				cRueck = this.recVPOS_TPA_FUHRE.get_ARTBEZ1_EK_cUF_NN("-")+"   ("+this.recVPOS_TPA_FUHRE.get_ANR1_EK_cUF_NN("-")+"-"+this.recVPOS_TPA_FUHRE.get_ANR2_EK_cUF_NN("-")+")";
			}
			else
			{
				cRueck = this.recVPOS_TPA_FUHRE.get_ARTBEZ1_VK_cUF_NN("-")+"   ("+this.recVPOS_TPA_FUHRE.get_ANR1_VK_cUF_NN("-")+"-"+this.recVPOS_TPA_FUHRE.get_ANR2_VK_cUF_NN("-")+")";
			}
		}
		return cRueck;
	}
	

	
	public String get_cID_FUHRE() throws myException
	{
		String cRueck = "";
		if (this.recVPOS_TPA_FUHRE_ORT!=null)
		{
			cRueck = this.recVPOS_TPA_FUHRE_ORT.get_ID_VPOS_TPA_FUHRE_cUF();
		}
		else
		{
			cRueck = this.recVPOS_TPA_FUHRE.get_ID_VPOS_TPA_FUHRE_cUF();
		}
		return cRueck;
	}

	
	
	private String get_cADRESS_FIELD(String cFIELDNAME) throws myException 
	{
		if (this.recAdresse!=null)
		{
			return this.recAdresse.get_UnFormatedValue(cFIELDNAME, "");
		}
		else
		{
			if (this.recVPOS_TPA_FUHRE_ORT != null)
			{
				return this.recVPOS_TPA_FUHRE_ORT.get_UP_RECORD_ADRESSE_id_adresse().get_UnFormatedValue(cFIELDNAME, "");
			}
			else
			{
				if (this.bEK)
				{
					return this.recVPOS_TPA_FUHRE.get_UP_RECORD_ADRESSE_id_adresse_start().get_UnFormatedValue(cFIELDNAME, "");
				}
				else
				{
					return this.recVPOS_TPA_FUHRE.get_UP_RECORD_ADRESSE_id_adresse_ziel().get_UnFormatedValue(cFIELDNAME, "");
				}
			}
		}
	}

	
	public RECORD_ADRESSE get_recAdresse()
	{
		return recAdresse;
	}
	

	/*
	 * 2011-02-08: zahlungsbedingung-EK- und VK unterscheiden
	 */
	public MyE2_MessageVector  get_CheckAdresseZahlungsbedingung(boolean bEK) throws myException
	{
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
		if (this.recAdresse == null)
		{
			throw new myException(this,"RECORD_ADRESSE is not created yet !");
		}
		
		if (bEK)
		{
			if (S.isEmpty(this.recAdresse.get_ID_ZAHLUNGSBEDINGUNGEN_cUF_NN("")))
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(
						new MyE2_String("Keine EK-Zahlungsbedingungen in Adress-Stammsatz für Lieferant: ",true,
								this.get_INFO_BLOCK_Fuer_Adresse_Sorte_id(),false)));
			}
		}
		else
		{
			if (S.isEmpty(this.recAdresse.get_ID_ZAHLUNGSBEDINGUNGEN_VK_cUF_NN("")))
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(
						new MyE2_String("Keine VK-Zahlungsbedingungen in Adress-Stammsatz für Abnehmer: ",true,
								this.get_INFO_BLOCK_Fuer_Adresse_Sorte_id(),false)));
			}
		}
		
		return oMV;
	}
	
	
	
	public String get_INFO_BLOCK_Fuer_Adresse_Sorte_id() throws myException
	{
		return ( this.get_NAME_ORT()+" -> "+this.get_cArtbez()+"  -> ID:"+this.get_cID_FUHRE());        //hilfsinfo
	}
	
	
	public BigDecimal get_bdMengeZurVerbuchung() throws myException 
	{
		if (this.recVPOS_TPA_FUHRE_ORT != null)
		{
			return this.recVPOS_TPA_FUHRE_ORT.get_bdMengeZurVerbuchung();
		}
		else
		{
			return this.recVPOS_TPA_FUHRE.get_bdMengeZurVerbuchung(this.bEK);
		}
	}

	
	
	public boolean get_bIstEigenesLager()
	{
		return bIstEigenesLager;
	}

	public boolean get_bBuchung_Menge_IST_Zahl_0()
	{
		return bBuchungMengeIST_Zahl_0;
	}

	
	
}
