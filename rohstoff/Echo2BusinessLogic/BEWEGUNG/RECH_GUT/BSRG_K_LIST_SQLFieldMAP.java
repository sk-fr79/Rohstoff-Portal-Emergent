package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLConnectorInnerTables;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;

public class BSRG_K_LIST_SQLFieldMAP extends Project_SQLFieldMAP {

	public BSRG_K_LIST_SQLFieldMAP(BS__SETTING SETTING) throws myException 
	{
		super("JT_VKOPF_RG", ":VORGANG_TYP:", false);
		
		
		/*
		 * beschraenkung fuer das Feld VORGANG_TYP
		 */
		this.add_SQLField(new SQLFieldForRestrictTableRange("JT_VKOPF_RG","VORGANG_TYP","VORGANG_TYP",new MyE2_String("Vorgangsart"),bibALL.MakeSql(SETTING.get_cBELEGTYP()),bibE2.get_CurrSession()), false);
		
		// dann den bezug zu JD_USER herstellen, damit das benutzerkuerzel in der liste angezeigt wird
		this.addCompleteTable_FIELDLIST("JD_USER",
					":KUERZEL:",
					true,false,"U_");

		// dann den bezug zu JT_ADRESSE herstellen fuer die selektion zu adressklassen
		this.addCompleteTable_FIELDLIST("JT_ADRESSE",
					":ID_LAND:",
					true,false,"A_");


		// dann ein query-field fuer die Liefnr
		this.add_SQLField(new SQLField(" SUBSTR(  NVL(JT_ADRESSE.LIEF_NR,'--------'),1,8)","A_LIEF_NR_TEIL",new MyE2_String("Lief-Nr"),bibE2.get_CurrSession()), false);


		// dann ein query-field fuer die Liefnr
		this.add_SQLField(new SQLField(" SUBSTR(  NVL(JT_VKOPF_RG.ZAHLUNGSBEDINGUNGEN,  '-----------------------------------------------------------------------'),1,60)","A_ZAHLBED_TEIL",new MyE2_String("Zahlungsbedingungen"),bibE2.get_CurrSession()), false);

		
		//2011-03-02: adress-popup-button mit infobutton
		this.add_SQLField(new SQLField(" JT_VKOPF_RG.ID_ADRESSE",	"INFO_ID_ADRESSE",		new MyE2_String("ID-Adresse"),bibE2.get_CurrSession()),false);
		
		
		//2015-01-15: eine weitere spalte mit der ID
		this.add_SQLField(new SQLField(" JT_VKOPF_RG.ID_VKOPF_RG",	BSRG__CONST.HASH_KEY_ID_VKOPF_RG_2,	 new MyE2_String("ID-Kopf"),bibE2.get_CurrSession()),false);
		
		
		//2015-07-27: neue spalte mit dem leistungsdatum
		this.add_SQLField(new SQLField(	BSRG__CONST.SQLFIELDMAP_FIELDS_RG.LEISTUNGSDAT_MIN._query(),	
										BSRG__CONST.SQLFIELDMAP_FIELDS_RG.LEISTUNGSDAT_MIN._hash(),	 
										BSRG__CONST.SQLFIELDMAP_FIELDS_RG.LEISTUNGSDAT_MIN._descript(),bibE2.get_CurrSession()),false);
		
		
		
		//to_char(&a,'fm9g990d000','NLS_NUMERIC_CHARACTERS = '',.''')
		
		//2011-02-17: neues feld mit gesamtbetrag
//		String cMultiplikator = SETTING.get_oVorgangTableNames().get_cVORGANG_TYP().equals(myCONST.VORGANGSART_RECHNUNG)?"-1":"1";
		
//		String cQuery4Gesamtbetrag = "(SELECT "+
//									" to_char((  "+
//									" SUM( "+
//									" VP.GESAMTPREIS_FW*VP.LAGER_VORZEICHEN-NVL(VP.GESAMTPREIS_ABZUG_FW,0)*VP.LAGER_VORZEICHEN)+ "+
//									" ROUND(SUM((VP.GESAMTPREIS_FW*VP.LAGER_VORZEICHEN- NVL(VP.GESAMTPREIS_ABZUG_FW*VP.LAGER_VORZEICHEN,0))*NVL(VP.STEUERSATZ,0)/100),2) "+
//									" )*"+cMultiplikator+",'fm999g999g990d00','NLS_NUMERIC_CHARACTERS = '',.''')"+
//									" AS GESAMTPREIS_RG "+
//									" FROM "+
//									" JT_VPOS_RG VP LEFT OUTER JOIN JT_VKOPF_RG VK ON (VP.ID_VKOPF_RG=VK.ID_VKOPF_RG) "+
//									" WHERE "+
//									" VK.ID_VKOPF_RG = JT_VKOPF_RG.ID_VKOPF_RG "+
//									" AND "+
//									" VP.POSITION_TYP = 'ARTIKEL' "+
//									" AND "+
//									" NVL(VP.DELETED,'N')='N')";
//	

		//2012-08-28: ersetzen des loklen string durch eine statische methode (fuer anderweitige nutzung)
		String cQuery4Gesamtbetrag = BSRG__CONST.get_SQL_4_RECH_GUT_ENDBETRAG_BRUTTO_FW(SETTING.get_oVorgangTableNames().get_cVORGANG_TYP(), "JT_VKOPF_RG.ID_VKOPF_RG", true);
//		String cQuery4Gesamtbetrag = "(SELECT "+
//									" ROUND((  "+
//									" SUM( "+
//									" VP.GESAMTPREIS_FW*VP.LAGER_VORZEICHEN-NVL(VP.GESAMTPREIS_ABZUG_FW,0)*VP.LAGER_VORZEICHEN)+ "+
//									" ROUND(SUM((VP.GESAMTPREIS_FW*VP.LAGER_VORZEICHEN- NVL(VP.GESAMTPREIS_ABZUG_FW*VP.LAGER_VORZEICHEN,0))*NVL(VP.STEUERSATZ,0)/100),2) "+
//									" )*"+cMultiplikator+",2) "+
//									" AS GESAMTPREIS_RG "+
//									" FROM "+
//									" JT_VPOS_RG VP LEFT OUTER JOIN JT_VKOPF_RG VK ON (VP.ID_VKOPF_RG=VK.ID_VKOPF_RG) "+
//									" WHERE "+
//									" VK.ID_VKOPF_RG = JT_VKOPF_RG.ID_VKOPF_RG "+
//									" AND "+
//									" VP.POSITION_TYP = 'ARTIKEL' "+
//									" AND "+
//									" NVL(VP.DELETED,'N')='N')";

		
		this.add_SQLField(new SQLField(cQuery4Gesamtbetrag, "SUBQUERY__ENDBETRAG_RG", new MyE2_String("Gesamtbetrag"), bibE2.get_CurrSession()), true);
		

		//2012-01-18: keine mahnung
		String cQueryFirmaKeineMahnung = "("+
										  "SELECT NVL(FI.KEINE_MAHNUNGEN,'N') FROM "+bibE2.cTO()+".JT_FIRMENINFO FI WHERE JT_VKOPF_RG.ID_ADRESSE=FI.ID_ADRESSE"+
										  ")";
		this.add_SQLField(new SQLField(cQueryFirmaKeineMahnung, "SUBQUERY__KEINE_MAHNUNGEN", new MyE2_String("Keine Mahnungen bei Firma"), bibE2.get_CurrSession()), true);
		
		
//		//2015-01-19: neues feld: existiert ein originalbeleg  
//		String cSQL_Ausdruck = new ownGeneraterSubQueryOrigMail().get_SQL();
//		this.add_SQLField(new SQLField(cSQL_Ausdruck, BSRG__CONST.HASHKEY_COLUMN_UNSEND_EMAILS, new MyE2_String("Unversendete Originalmails"), bibE2.get_CurrSession()), true);
//		
		
		
		// primaerkey vom user
		this.add_SQLField(new SQLFieldForPrimaryKey("JD_USER","ID_USER","U_ID_USER",new MyE2_String("ID-Benutzer"),bibE2.get_CurrSession(),
				"SELECT "+bibALL.get_TABLEOWNER()+"."+"SEQ_USER"+".NEXTVAL FROM DUAL",true), false);

		// primaerkey von adresse
		this.add_SQLField(new SQLFieldForPrimaryKey("JT_ADRESSE","ID_ADRESSE","A_ID_ADRESSE",new MyE2_String("ID-Adresse"),bibE2.get_CurrSession(),
				"SELECT "+bibALL.get_TABLEOWNER()+"."+"SEQ_ADRESSE"+".NEXTVAL FROM DUAL",true), false);

		this.add_InnerConnector(new SQLConnectorInnerTables("JT_VKOPF_RG.ID_USER=JD_USER.ID_USER (+)"));
		this.add_InnerConnector(new SQLConnectorInnerTables("JT_VKOPF_RG.ID_ADRESSE=JT_ADRESSE.ID_ADRESSE"));

		// reihefolge die neuesten zuerst
		this.clear_ORDER_SEGMENT();
		this.add_ORDER_SEGMENT("JT_VKOPF_RG.ID_VKOPF_RG DESC");

		
		
		this.get_("DELETED").set_cDefaultValueFormated("N");
		this.get_("DELETED").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);

		
		this.initFields();
	}

	
//	private class ownGeneraterSubQueryOrigMail extends GenTERM {
//
//		private String cSQL = "";
//			
//		public ownGeneraterSubQueryOrigMail() throws myException {
//			super();
//			this.
//			AppendTerm("NVL("+_DB.Z_ARCHIVMEDIEN$IST_ORIGINAL+",'N')", 	"=", "Y",false,true).
//			AppendTerm("NVL("+_DB.Z_ARCHIVMEDIEN$MEDIENKENNER+",'-')", 	"=", Archiver.MEDIENKENNER_ORIGBELEG, false,true).
//			AppendTerm("NVL("+_DB.Z_EMAIL_SEND_TARGETS$SEND_OK+",'N')", "=", "N",false,true).
//			AppendTerm(_DB.Z_ARCHIVMEDIEN$TABLENAME, "=", _DB.VKOPF_RG.substring(3),false,true).
//			AppendTerm(_DB.Z_ARCHIVMEDIEN$ID_TABLE, "=",_DB.Z_VKOPF_RG$ID_VKOPF_RG,false,false);
//			
//		
//			GenTERM join1 = new GenTERM();
//			join1.AppendTerm(_DB.Z_EMAIL_SEND$ID_EMAIL_SEND, "=", _DB.Z_EMAIL_SEND_ATTACH$ID_EMAIL_SEND);
//			
//			GenTERM join2 = new GenTERM();
//			join2.AppendTerm(_DB.Z_ARCHIVMEDIEN$ID_ARCHIVMEDIEN, "=", _DB.Z_EMAIL_SEND_ATTACH$ID_ARCHIVMEDIEN);
//			
//			GenTERM join3 = new GenTERM();
//			join3.AppendTerm(_DB.Z_EMAIL_SEND$ID_EMAIL_SEND, "=", _DB.Z_EMAIL_SEND_TARGETS$ID_EMAIL_SEND);
//
//			this.cSQL = "SELECT COUNT(*) FROM "+bibE2.cTO()+"."+_DB.EMAIL_SEND + 
//					" INNER JOIN "+bibE2.cTO()+"."+_DB.EMAIL_SEND_ATTACH + " ON ("+join1.get_TERMS_WITH("AND")+")"+
//					" INNER JOIN "+bibE2.cTO()+"."+_DB.ARCHIVMEDIEN + " ON ("+join2.get_TERMS_WITH("AND")+")"+
//					" INNER JOIN "+bibE2.cTO()+"."+_DB.EMAIL_SEND_TARGETS + " ON ("+join3.get_TERMS_WITH("AND")+")"+
//					" WHERE "+this.get_TERMS_WITH("AND");
//			
//			DEBUG.System_println(this.cSQL);
//			
//		}
//		
//		
//		public String get_SQL() {
//			return "("+this.cSQL+")";
//		}
//		
//		
//		
//	}
	
}
