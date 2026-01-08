package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLConnectorInnerTables;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;

public class BSK_K_LIST_SQLFieldMAP extends Project_SQLFieldMAP {

	public BSK_K_LIST_SQLFieldMAP(BS__SETTING SETTING) throws myException 
	{
		super("JT_VKOPF_KON", ":VORGANG_TYP:"+VKOPF_KON.ist_fixierung.fn()+":"+VKOPF_KON.typ_25_to.fn()+":"+VKOPF_KON.typ_ladung.fn()+":", false);
		
		
		/*
		 * beschraenkung fuer das Feld VORGANG_TYP
		 */
		this.add_SQLField(new SQLFieldForRestrictTableRange("JT_VKOPF_KON","VORGANG_TYP","VORGANG_TYP",new MyE2_String("Vorgangsart"),bibALL.MakeSql(SETTING.get_cBELEGTYP()),bibE2.get_CurrSession()), false);
		
		/*
		 * nur NICHT-fixierungskontrakte
		 */
		this.add_SQLField(new SQLFieldForRestrictTableRange("JT_VKOPF_KON",VKOPF_KON.ist_fixierung.fn(),VKOPF_KON.ist_fixierung.fn(),new MyE2_String("Vorgangsart"),"'N'",bibE2.get_CurrSession()), false);

		
		// dann den bezug zu JD_USER herstellen, damit das benutzerkuerzel in der liste angezeigt wird
		this.addCompleteTable_FIELDLIST("JD_USER",
					":KUERZEL:",
					true,false,"U_");

		// dann den bezug zu JT_ADRESSE herstellen fuer die selektion zu adressklassen
		this.addCompleteTable_FIELDLIST("JT_ADRESSE",
					":ID_LAND:",
					true,false,"A_");

		// dann ein query-field fuer datum von-bis
		this.add_SQLField(new SQLField(" SUBSTR(  NVL(TO_CHAR(GUELTIG_VON,'DD.MM.YYYY'),'-------'),1,6)||' - '||SUBSTR(  NVL(TO_CHAR(GUELTIG_BIS,'DD.MM.YYYY'),'------'),1,6)","DATUMSBEREICH",new MyE2_String("Gültigkeit"),bibE2.get_CurrSession()), false);

		// dann ein query-field fuer die Liefnr
		this.add_SQLField(new SQLField(" SUBSTR(  NVL(JT_ADRESSE.LIEF_NR,'--------'),1,8)","A_LIEF_NR_TEIL",new MyE2_String("Lief-Nr"),bibE2.get_CurrSession()), false);

		//2011-01-06: haendler/ansprechpartner einblenden
		this.add_SQLField(new SQLField(" (SELECT TRIM(NVL(VORNAME,'')||' '||NVL(NAME1,'-')) FROM "+bibE2.cTO()+".JD_USER WHERE JD_USER.ID_USER=JT_VKOPF_KON.ID_USER_ANSPRECH_INTERN) ","C_HAENDLER",new MyE2_String("Händler"),bibE2.get_CurrSession()), false);

		//2011-01-06: Betreuer (Firma) einblenden
		this.add_SQLField(new SQLField(" (SELECT TRIM(NVL(JD_USER.VORNAME,'')||' '||NVL(JD_USER.NAME1,'-')) FROM "+bibE2.cTO()+".JT_VKOPF_KON KON2" +
				" LEFT OUTER JOIN "+bibE2.cTO()+".JT_ADRESSE ON (KON2.ID_ADRESSE=JT_ADRESSE.ID_ADRESSE) "+
				" LEFT OUTER JOIN "+bibE2.cTO()+".JD_USER ON (JT_ADRESSE.ID_USER=JD_USER.ID_USER) "+
				" WHERE KON2.ID_VKOPF_KON=JT_VKOPF_KON.ID_VKOPF_KON) ","C_BETREUER_FIRMA",new MyE2_String("Betreuer Firma"),bibE2.get_CurrSession()), false);

		
		// primaerkey vom user
		this.add_SQLField(new SQLFieldForPrimaryKey("JD_USER","ID_USER","U_ID_USER",new MyE2_String("ID-Benutzer"),bibE2.get_CurrSession(),
				"SELECT "+bibALL.get_TABLEOWNER()+"."+"SEQ_USER"+".NEXTVAL FROM DUAL",true), false);

		// primaerkey von adresse
		this.add_SQLField(new SQLFieldForPrimaryKey("JT_ADRESSE","ID_ADRESSE","A_ID_ADRESSE",new MyE2_String("ID-Adresse"),bibE2.get_CurrSession(),
				"SELECT "+bibALL.get_TABLEOWNER()+"."+"SEQ_ADRESSE"+".NEXTVAL FROM DUAL",true), false);

		//2011-03-02: adress-popup-button mit infobutton
		this.add_SQLField(new SQLField(" JT_VKOPF_KON.ID_ADRESSE",	"INFO_ID_ADRESSE",		new MyE2_String("ID-Adresse"),bibE2.get_CurrSession()),false);
		
		this.add_InnerConnector(new SQLConnectorInnerTables("JT_VKOPF_KON.ID_USER=JD_USER.ID_USER (+)"));
		this.add_InnerConnector(new SQLConnectorInnerTables("JT_VKOPF_KON.ID_ADRESSE=JT_ADRESSE.ID_ADRESSE"));

		this.get_("DELETED").set_cDefaultValueFormated("N");
		this.get_("DELETED").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
		this.initFields();
	}

}
