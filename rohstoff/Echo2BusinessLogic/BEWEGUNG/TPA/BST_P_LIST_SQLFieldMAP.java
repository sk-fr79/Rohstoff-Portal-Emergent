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

public class BST_P_LIST_SQLFieldMAP extends Project_SQLFieldMAP {

	public BST_P_LIST_SQLFieldMAP() throws myException 
	{
		super("JT_VPOS_TPA", ":ID_VKOPF_TPA:ANZAHL:", false);
		
		
		/*
		 * beschraenkung fuer das Feld VORGANG_TYP
		 */
		this.add_SQLField(new SQLFieldForRestrictTableRange("JT_VPOS_TPA","ID_VKOPF_TPA","ID_VKOPF_TPA",new MyE2_String("ID-VKOPF"),"NULL",bibE2.get_CurrSession()), false);
		this.add_SQLField(new SQLField("ROUND(JT_VPOS_TPA.ANZAHL,1)","ANZAHL",new MyE2_String("Anzahl"),bibE2.get_CurrSession()), false);
		
		
		this.addCompleteTable_FIELDLIST("JT_VPOS_TPA_FUHRE",":L_NAME1:L_ORT:A_NAME1:A_ORT:ANR1_EK:ANR2_EK:ARTBEZ1_EK:",true,false, "F_");
		this.add_SQLField(new SQLFieldForPrimaryKey(	"JT_VPOS_TPA_FUHRE",
														"ID_VPOS_TPA_FUHRE",
														"F_ID_VPOS_TPA_FUHRE",
														new MyE2_String("ID_VPOS_TPA_FUHRE"),
														bibE2.get_CurrSession(),"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_VPOS_TPA_FUHRE.NEXTVAL FROM DUAL",true), false);
		this.add_SQLField(new SQLField("ROUND(JT_VPOS_TPA_FUHRE.MENGE_VORGABE_KO,0)","F_MENGE_VORGABE_KO",new MyE2_String("Menge Vorgabe"),bibE2.get_CurrSession()), false);
		this.add_SQLField(new SQLField("ROUND(JT_VPOS_TPA_FUHRE.MENGE_AUFLADEN_KO,0)","F_MENGE_AUFLADEN_KO",new MyE2_String("Menge Aufladen"),bibE2.get_CurrSession()), false);
		this.add_SQLField(new SQLField("ROUND(JT_VPOS_TPA_FUHRE.MENGE_ABLADEN_KO,0)","F_MENGE_ABLADEN_KO",new MyE2_String("Menge Abladen"),bibE2.get_CurrSession()), false);

		this.add_SQLField(new SQLField("  NVL(JT_VPOS_TPA_FUHRE.L_NAME1,'-')||' '||  NVL(JT_VPOS_TPA_FUHRE.L_ORT,'-')","F_L_NAME_ORT",new MyE2_String("Lieferanten Name/Ort"),bibE2.get_CurrSession()), false);
		this.add_SQLField(new SQLField("  NVL(JT_VPOS_TPA_FUHRE.A_NAME1,'-')||' '||  NVL(JT_VPOS_TPA_FUHRE.A_ORT,'-')","F_A_NAME_ORT",new MyE2_String("Abnehmer Name/Ort"),bibE2.get_CurrSession()), false);

		this.add_SQLField(new SQLField("  NVL(JT_VPOS_TPA_FUHRE.ANR1_EK,'-')||'/'||  NVL(JT_VPOS_TPA_FUHRE.ANR2_EK,'-')||' // '||  NVL(JT_VPOS_TPA_FUHRE.ARTBEZ1_EK,'-')","F_TRANSPORT_ARTIKEL",new MyE2_String("Abnehmer Name/Ort"),bibE2.get_CurrSession()), false);

		this.add_InnerConnector(new SQLConnectorInnerTables("JT_VPOS_TPA.ID_VPOS_TPA=JT_VPOS_TPA_FUHRE.ID_VPOS_TPA"));
		
		
		/*
		 * defaultwerte setzen
		 */
		//this.get_("POSITIONSKLASSE").set_cDefaultValueFormated(myCONST.VG_POSITIONSKLASSE_BELEGPOSITION);
		this.get_("POSITION_TYP").set_cDefaultValueFormated(myCONST.VG_POSITION_TYP_ARTIKEL);
		this.get_("LAGER_VORZEICHEN").set_cDefaultValueFormated("0");
		
		this.get_("WAEHRUNGSKURS").set_cDefaultValueFormated("1");

		
		/*
		 * must-values
		 */
		this.get_("POSITION_TYP").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);

		this.get_("DELETED").set_cDefaultValueFormated("N");
		this.get_("DELETED").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);

		
		// standard-sortierung nach positionsnummer
		this.add_ORDER_SEGMENT("JT_VPOS_TPA.POSITIONSNUMMER");
		
		
		
		this.initFields();
	}

}
