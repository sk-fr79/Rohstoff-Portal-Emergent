package rohstoff.Echo2BusinessLogic.FIBU;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FIBU_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{

	public FIBU_LIST_SqlFieldMAP() throws myException 
	{
		super("JT_FIBU", "", false);
		

		this.add_JOIN_Table("JT_ADRESSE", 
				"JT_ADRESSE", 
				SQLFieldMAP.LEFT_OUTER, 
				":NAME1:ORT:", true, "JT_FIBU.ID_ADRESSE=JT_ADRESSE.ID_ADRESSE", "ADR_", null);
		
		
		//2012-02-10: weitere felder in liste einblenden
		this.add_JOIN_Table("JT_FIRMENINFO", 
				"JT_FIRMENINFO", 
				SQLFieldMAP.LEFT_OUTER, 
				":FORDERUNGSVERRECHNUNG:SCHECKDRUCK_JN:", true, "JT_ADRESSE.ID_ADRESSE=JT_FIRMENINFO.ID_ADRESSE", "FI_", null);
		

		this.add_JOIN_Table("JT_VKOPF_RG", 
				"JT_VKOPF_RG", 
				SQLFieldMAP.LEFT_OUTER, 
				":BUCHUNGSNUMMER:DRUCKDATUM:", true, "JT_FIBU.ID_VKOPF_RG=JT_VKOPF_RG.ID_VKOPF_RG", "VKOPF_", null);

		//2015-10-21: Export-ID in der Liste zeigen (Wunsch Fr. Hecktor)
		this.add_JOIN_Table("JT_VKOPF_EXPORT_RG", 
				"JT_VKOPF_EXPORT_RG", 
				SQLFieldMAP.LEFT_OUTER, 
				":ID_EXPORT_LOG:", true, "JT_VKOPF_RG.ID_VKOPF_RG=JT_VKOPF_EXPORT_RG.ID_VKOPF_EXPORT_RG", "EXP_", null);

		//2015-06-01: zahlungsbedingungen
		this.add_JOIN_Table(_DB.ZAHLUNGSBEDINGUNGEN, 
							_DB.ZAHLUNGSBEDINGUNGEN, 
							SQLFieldMAP.LEFT_OUTER, 
							":"+_DB.ZAHLUNGSBEDINGUNGEN$BEZEICHNUNG+":", true, _DB.Z_VKOPF_RG$ID_ZAHLUNGSBEDINGUNGEN+"="+_DB.Z_ZAHLUNGSBEDINGUNGEN$ID_ZAHLUNGSBEDINGUNGEN, "ZB_", null);
		
		
		
		//2012-01-16: selektion nach mahnungs-eintraegen und anzeige der faelligkeiten/ablauf der letzten mahnstufe
		this.add_SQLField(new SQLField(
				"(SELECT MAX(M.DATUM_MAHNUNG+NVL(M.FRIST_IN_TAGEN,0)) FROM "+bibE2.cTO()+".JT_FIBU_MAHNUNG FM  INNER JOIN "+bibE2.cTO()+".JT_MAHNUNG M ON (FM.ID_MAHNUNG=M.ID_MAHNUNG)  WHERE FM.ID_FIBU=JT_FIBU.ID_FIBU)",
				"ABLAUF_MAHNUNG",null,bibE2.get_CurrSession()), true);
	
		this.add_SQLField(new SQLField("NVL(JT_ADRESSE.NAME1,'')||' '||NVL(JT_ADRESSE.ORT,'')","NAME_ORT", new MyE2_String("Adresse"),bibE2.get_CurrSession()),true );
		
		this.add_SQLField(new SQLField("(SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_FIBU FIB WHERE NVL(STORNIERT,'N')='N' AND FIB.BUCHUNGSBLOCK_NR=JT_FIBU.BUCHUNGSBLOCK_NR)",
										"BLOCK_ANZAHL",null,bibE2.get_CurrSession()), true);
		
		//2011-03-23: neue Abfrage wg. skonto-verbuchung

		this.add_SQLField(new SQLField("(SELECT SUM(" +
												" CASE WHEN NVL(FIB.SKONTO_DATUM_UEBERSCHRITTEN,'N')='Y' THEN "+
												 " (NVL(FIB.ZAHLUNGSBETRAG_FREMD_WAEHRUNG,0)+NVL(FIB.SKONTOBETRAG_FREMD_WAEHRUNG,0))*FIB.FAKTOR_BUCHUNG_PLUS_MINUS "+
												 " ELSE "+
												 " (NVL(FIB.ZAHLUNGSBETRAG_FREMD_WAEHRUNG,0)*FIB.FAKTOR_BUCHUNG_PLUS_MINUS) "+
											 " END " +
										") FROM "+
											bibE2.cTO()+".JT_FIBU FIB WHERE NVL(FIB.STORNIERT,'N')='N' AND FIB.BUCHUNGSBLOCK_NR=JT_FIBU.BUCHUNGSBLOCK_NR AND FIB.ID_FIBU<>JT_FIBU.ID_FIBU)",
										"BLOCK_SUMME_GEGENBELEGE",null,bibE2.get_CurrSession()), true);

		
		this.add_SQLField(new SQLField("(SELECT MAX(FIB.ID_FIBU) FROM "+bibE2.cTO()+".JT_FIBU FIB WHERE NVL(STORNIERT,'N')='N' AND FIB.BUCHUNGSBLOCK_NR=JT_FIBU.BUCHUNGSBLOCK_NR)",
										"GROESSTE_ID_BUCHUNGSBLOCK",null,bibE2.get_CurrSession()), true);

		//2011-03-23: neue Abfrage wg. skonto-verbuchung
		this.add_SQLField(new SQLField("(SELECT SUM(" +
										" CASE WHEN NVL(FIB.SKONTO_DATUM_UEBERSCHRITTEN,'N')='Y' THEN "+
											 " (NVL(FIB.ZAHLUNGSBETRAG_FREMD_WAEHRUNG,0)+NVL(FIB.SKONTOBETRAG_FREMD_WAEHRUNG,0))*FIB.FAKTOR_BUCHUNG_PLUS_MINUS "+
											 " ELSE "+
											 " (NVL(FIB.ZAHLUNGSBETRAG_FREMD_WAEHRUNG,0)*FIB.FAKTOR_BUCHUNG_PLUS_MINUS) "+
    									 " END " +
										") FROM "+
										bibE2.cTO()+".JT_FIBU FIB WHERE NVL(FIB.STORNIERT,'N')='N' AND FIB.BUCHUNGSBLOCK_NR=JT_FIBU.BUCHUNGSBLOCK_NR AND " +
										"(FIB.BUCHUNGSTYP='DRUCK_RECHNUNG' OR FIB.BUCHUNGSTYP='DRUCK_GUTSCHRIFT'))",
										"BLOCK_SUMME_BELEGE",null,bibE2.get_CurrSession()), true);

		
		this.add_SQLField(new SQLField("(SELECT SUM(FAKTOR_BUCHUNG_PLUS_MINUS*ZAHLUNGSBETRAG_FREMD_WAEHRUNG) FROM "+
													bibE2.cTO()+".JT_FIBU FIB WHERE NVL(STORNIERT,'N')='N' AND FIB.BUCHUNGSBLOCK_NR=JT_FIBU.BUCHUNGSBLOCK_NR AND " +
															" (NOT (FIB.BUCHUNGSTYP='DRUCK_RECHNUNG' OR FIB.BUCHUNGSTYP='DRUCK_GUTSCHRIFT')))",
										"BLOCK_SUMME_ZAHLUNGEN",null,bibE2.get_CurrSession()), true);
	

		//2011-03-09: zahlungsziel (original) aus den rechnungspositionen holen
		this.add_SQLField(new SQLField("(SELECT MAX(VP.ZAHLUNGSBED_CALC_DATUM) FROM JT_VPOS_RG VP WHERE NVL(VP.DELETED,'N')='N' AND VP.ID_VKOPF_RG=JT_FIBU.ID_VKOPF_RG)",
									"ORIGINAL_ZAHLUNGSZIEL",null,bibE2.get_CurrSession()), true);
		
		
		
		this.add_SQLField(new SQLField("NVL(JT_ADRESSE.NAME1,'')||' '||NVL(JT_ADRESSE.ORT,'')","NAME_ORT", new MyE2_String("Adresse"),bibE2.get_CurrSession()),true );
		

		//neues feld, gemixt aus DRUCKDATUM, wenn es ein Eintrag mit einer ID_VKOPF_RG ist, sonst dem Fibu-buchungsdatum
		this.add_SQLField(new SQLField("(CASE WHEN JT_FIBU.ID_VKOPF_RG IS NULL THEN JT_FIBU.BUCHUNGSDATUM ELSE JT_VKOPF_RG.DRUCKDATUM END)","DRUCK_ODER_BUCHUNGSDATUM", new MyE2_String("Druck_Buchungsdatum"),bibE2.get_CurrSession()),true );

		
		//2011-03-25: leistungszeitraum in Fibu einblenden
		this.add_SQLField(new SQLField("(CASE WHEN JT_FIBU.ID_VKOPF_RG IS NULL THEN NULL ELSE (SELECT MIN(AUSFUEHRUNGSDATUM) FROM "+bibE2.cTO()+".JT_VPOS_RG VP1 WHERE NVL(VP1.DELETED,'N')='N' AND VP1.ID_VKOPF_RG=JT_FIBU.ID_VKOPF_RG) END)","LEISTUNGSDATUM_VON", new MyE2_String("Leistungsdatum ab"),bibE2.get_CurrSession()),true );
		this.add_SQLField(new SQLField("(CASE WHEN JT_FIBU.ID_VKOPF_RG IS NULL THEN NULL ELSE (SELECT MAX(AUSFUEHRUNGSDATUM) FROM "+bibE2.cTO()+".JT_VPOS_RG VP2 WHERE NVL(VP2.DELETED,'N')='N' AND VP2.ID_VKOPF_RG=JT_FIBU.ID_VKOPF_RG) END)","LEISTUNGSDATUM_BIS", new MyE2_String("Leistungsdatum ab"),bibE2.get_CurrSession()),true );
		

		//2012-01-18: keine_mahnungen
		//2012-01-18: keine mahnung
		String cQueryFirmaKeineMahnung = "(" +
											"CASE WHEN "+
											"  (NVL((SELECT NVL(FI.KEINE_MAHNUNGEN,'N') FROM JT_FIRMENINFO FI WHERE JT_FIBU.ID_ADRESSE=FI.ID_ADRESSE),' ')='Y'  OR "+
											"  NVL((SELECT NVL(R.KEINE_MAHNUNGEN,'N') FROM JT_VKOPF_RG R WHERE R.ID_VKOPF_RG=JT_FIBU.ID_VKOPF_RG),' ')='Y') THEN "+
											"'Y' "+
											" ELSE "+
											" 'N' "+
											" END "	+
											")";
		this.add_SQLField(new SQLField(cQueryFirmaKeineMahnung, "SUBQUERY__KEINE_MAHNUNGEN", new MyE2_String("Keine Mahnungen bei Firma oder Rechnung"), bibE2.get_CurrSession()), true);

		
		//statische bedinung: keine vorlauefigen belege anzeigen
		this.add_BEDINGUNG_STATIC("JT_FIBU.VORLAEUFIG='N'");
		
		this.clear_ORDER_SEGMENT();
		this.add_ORDER_SEGMENT(FIBU_CONST.ORDER_BUCHUNGSBLOCK_UP);
		
		this.initFields();
	}
	
}
