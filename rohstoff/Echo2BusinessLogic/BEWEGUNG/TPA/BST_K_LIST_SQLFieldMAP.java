package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLConnectorInnerTables;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;

public class BST_K_LIST_SQLFieldMAP extends Project_SQLFieldMAP {

	public BST_K_LIST_SQLFieldMAP() throws myException 
	{
		super("JT_VKOPF_TPA", ":VORGANG_TYP:", false);
		
		
		/*
		 * beschraenkung fuer das Feld VORGANG_TYP
		 */
		this.add_SQLField(new SQLFieldForRestrictTableRange("JT_VKOPF_TPA","VORGANG_TYP","VORGANG_TYP",new MyE2_String("Vorgangsart"),bibALL.MakeSql(myCONST.VORGANGSART_TRANSPORT),bibE2.get_CurrSession()), false);
		
		// dann den bezug zu JD_USER herstellen, damit das benutzerkuerzel in der liste angezeigt wird
		this.addCompleteTable_FIELDLIST("JD_USER",
					":KUERZEL:",
					true,false,"U_");

		// dann den bezug zu JT_ADRESSE herstellen fuer die selektion zu adressklassen
		this.addCompleteTable_FIELDLIST("JT_ADRESSE",
					":ID_LAND:",
					true,false,"A_");

		// dann ein query-field fuer datum von-bis
		this.add_SQLField(new SQLField(" SUBSTR(  NVL(TO_CHAR(GUELTIG_VON,'DD.MM.YYYY'),'-------'),1,6)||' - '||SUBSTR(  NVL(TO_CHAR(GUELTIG_BIS,'DD.MM.YYYY'),'------'),1,6)","DATUMSBEREICH",new MyE2_String("Gltigkeit"),bibE2.get_CurrSession()), false);

		// dann ein query-field fuer die Liefnr
		this.add_SQLField(new SQLField(" SUBSTR(  NVL(JT_ADRESSE.LIEF_NR,'--------'),1,8)","A_LIEF_NR_TEIL",new MyE2_String("Lief-Nr"),bibE2.get_CurrSession()), false);

		// primaerkey vom user
		this.add_SQLField(new SQLFieldForPrimaryKey("JD_USER","ID_USER","U_ID_USER",new MyE2_String("ID-Benutzer"),bibE2.get_CurrSession(),
				"SELECT "+bibALL.get_TABLEOWNER()+"."+"SEQ_USER"+".NEXTVAL FROM DUAL",true), false);

		// primaerkey von adresse
		this.add_SQLField(new SQLFieldForPrimaryKey("JT_ADRESSE","ID_ADRESSE","A_ID_ADRESSE",new MyE2_String("ID-Adresse"),bibE2.get_CurrSession(),
				"SELECT "+bibALL.get_TABLEOWNER()+"."+"SEQ_ADRESSE"+".NEXTVAL FROM DUAL",true), false);

		//2011-03-02: adress-popup-button mit infobutton
		this.add_SQLField(new SQLField(" JT_VKOPF_TPA.ID_ADRESSE",	"INFO_ID_ADRESSE",		new MyE2_String("ID-Adresse"),bibE2.get_CurrSession()),false);
		
		
		
		this.add_InnerConnector(new SQLConnectorInnerTables("JT_VKOPF_TPA.ID_USER=JD_USER.ID_USER (+)"));
		this.add_InnerConnector(new SQLConnectorInnerTables("JT_VKOPF_TPA.ID_ADRESSE=JT_ADRESSE.ID_ADRESSE"));

		// reihefolge die neuesten zuerst
		this.clear_ORDER_SEGMENT();
		this.add_ORDER_SEGMENT("JT_VKOPF_TPA.ID_VKOPF_TPA DESC");

		this.get_("DELETED").set_cDefaultValueFormated("N");
		this.get_("DELETED").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
		this.initFields();
	}

}
