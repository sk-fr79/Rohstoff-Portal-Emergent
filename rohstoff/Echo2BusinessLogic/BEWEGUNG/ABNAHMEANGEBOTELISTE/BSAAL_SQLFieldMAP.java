package rohstoff.Echo2BusinessLogic.BEWEGUNG.ABNAHMEANGEBOTELISTE;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLConnectorInnerTables;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.exceptions.myException;

public class BSAAL_SQLFieldMAP extends Project_SQLFieldMAP {

	public BSAAL_SQLFieldMAP() throws myException 
	{
		super("JT_VPOS_STD", ":ID_VKOPF_STD:BEMERKUNG_INTERN:", false);
		
		
		// dann den komplette kopf dazuladen
		this.addCompleteTable_FIELDLIST("JT_VKOPF_STD",
					":ID_VKOPF_STD:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:BEMERKUNGEN_INTERN:ZAHLUNGSBEDINGUNGEN:LIEFERBEDINGUNGEN:FORMULARTEXT_ANFANG:FORMULARTEXT_ENDE:",
					false,false,"K_");
		
		// primaerkey vom kopf
		this.add_SQLField(new SQLFieldForPrimaryKey("JT_VKOPF_STD","ID_VKOPF_STD","K_ID_VKOPF_STD",new MyE2_String("ID-Kopf"),bibE2.get_CurrSession(),
				"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_VKOPF_STD"+".NEXTVAL FROM DUAL",true), false);

		// dann den wurmfortsatz dazuladen
		this.addCompleteTable_FIELDLIST("JT_VPOS_STD_ANGEBOT",
					":ID_VPOS_STD_ANGEBOT:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:",
					false,false,"G_");
		
		// primaerkey vom wurmfortsatz
		this.add_SQLField(new SQLFieldForPrimaryKey("JT_VPOS_STD_ANGEBOT","ID_VPOS_STD_ANGEBOT","G_ID_VPOS_STD_ANGEBOT",new MyE2_String("ID-VPOS-STD-ANGEBOT"),bibE2.get_CurrSession(),
				"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_VPOS_STD_ANGEBOT"+".NEXTVAL FROM DUAL",true), false);
		

		
		// dann den bezug zu JT_ADRESSE herstellen fuer die selektion zu adressklassen
		this.addCompleteTable_FIELDLIST("JT_ADRESSE",
					":ID_LAND:",
					true,false,"A_");

		// primaerkey von adresse
		this.add_SQLField(new SQLFieldForPrimaryKey("JT_ADRESSE","ID_ADRESSE",BSAAL__CONST.SONDERSPALTEN.A_ID_ADRESSE.name(),new MyE2_String("ID-Adresse"),bibE2.get_CurrSession(),
				"SELECT "+bibALL.get_TABLEOWNER()+"."+"SEQ_ADRESSE"+".NEXTVAL FROM DUAL",true), false);

		
		//NEU_09   USER nach hinten (sonst probleme mit MAXDB 7.5
		// dann den bezug zu JD_USER herstellen, damit das benutzerkuerzel in der liste angezeigt wird
		this.addCompleteTable_FIELDLIST("JD_USER",
					":KUERZEL:",
					true,false,"U_");

		
		
		//NEU_09   USER nach hinten (sonst probleme mit MAXDB 7.5
		// primaerkey vom user
		this.add_SQLField(new SQLFieldForPrimaryKey("JD_USER","ID_USER","U_ID_USER",new MyE2_String("ID-Benutzer"),bibE2.get_CurrSession(),
				"SELECT "+bibALL.get_TABLEOWNER()+"."+"SEQ_USER"+".NEXTVAL FROM DUAL",true),false);

		
		//2015-12-14: neues feld fuer den infobutton (nur formales feld)
		this.add_SQLField(new SQLField("TO_CHAR(JT_VPOS_STD.ID_VPOS_STD)",BSAAL__CONST.H_ID_VPOS_STD,new MyE2_String("ID-VPOS-STD"),bibE2.get_CurrSession()),false);
		
		
		
		this.add_InnerConnector(new SQLConnectorInnerTables("JT_VPOS_STD.ID_VKOPF_STD=JT_VKOPF_STD.ID_VKOPF_STD"));
		this.add_InnerConnector(new SQLConnectorInnerTables("JT_VPOS_STD.ID_VPOS_STD=JT_VPOS_STD_ANGEBOT.ID_VPOS_STD"));
//		this.add_InnerConnector(new SQLConnectorInnerTables("JT_VKOPF_STD.ID_USER=JD_USER.ID_USER (+)"));
		this.add_InnerConnector(new SQLConnectorInnerTables("JT_VKOPF_STD.ID_ADRESSE=JT_ADRESSE.ID_ADRESSE"));
		 //NEU_09 (connect ganz nach hinten)
		this.add_InnerConnector(new SQLConnectorInnerTables("JT_VKOPF_STD.ID_USER=JD_USER.ID_USER (+)"));                       

		
		// dann ein query-field fuer datum von-bis
		this.add_SQLField(new SQLField(" SUBSTR(  NVL(TO_CHAR(JT_VPOS_STD_ANGEBOT.GUELTIG_VON,'DD.MM.YYYY'),'-------'),1,6)||' - '||SUBSTR(  NVL(TO_CHAR(JT_VPOS_STD_ANGEBOT.GUELTIG_BIS,'DD.MM.YYYY'),'------'),1,6)","G_DATUMSBEREICH",new MyE2_String("Gültigkeit"),bibE2.get_CurrSession()), false);

		// dann ein query-field fuer die Liefnr
		this.add_SQLField(new SQLField(" SUBSTR(  NVL(JT_ADRESSE.LIEF_NR,'--------'),1,8)","A_LIEF_NR_TEIL",new MyE2_String("Lief-Nr"),bibE2.get_CurrSession()), false);


		/*
		 * statische bedingungen zufuegen,
		 * nur abnahmeangebote und dort nur artikelpositionen
		 */
		this.add_BEDINGUNG_STATIC("JT_VKOPF_STD.VORGANG_TYP='"+myCONST.VORGANGSART_ABNAHMEANGEBOT+"'");
		this.add_BEDINGUNG_STATIC("JT_VPOS_STD.POSITION_TYP='"+myCONST.VG_POSITION_TYP_ARTIKEL+"'");
		
		
		this.get_("DELETED").set_cDefaultValueFormated("N");
		this.get_("DELETED").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
		// sortierung festlegen
		this.clear_ORDER_SEGMENT();
		this.add_ORDER_SEGMENT("JT_VKOPF_STD.NAME1");
		this.add_ORDER_SEGMENT("JT_VKOPF_STD.ORT");
		this.add_ORDER_SEGMENT("JT_VKOPF_STD.ID_VKOPF_STD");
		this.add_ORDER_SEGMENT("JT_VPOS_STD.ANR1");
		this.add_ORDER_SEGMENT("JT_VPOS_STD.ANR2");
		
		this.initFields();
	}

}
