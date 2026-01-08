package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLConnectorInnerTables;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;

public class BSK_P_LIST_SQLFieldMAP extends Project_SQLFieldMAP {

	public BSK_P_LIST_SQLFieldMAP() throws myException 
	{
		super("JT_VPOS_KON", ":ID_VKOPF_KON:"+":"+VPOS_KON.typ_25_to.fn()+":"+":"+VPOS_KON.typ_ladung.fn()+":", false);
		
		
		/*
		 * beschraenkung fuer das Feld VORGANG_TYP
		 */
		this.add_SQLField(new SQLFieldForRestrictTableRange("JT_VPOS_KON","ID_VKOPF_KON","ID_VKOPF_KON",new MyE2_String("ID-VKOPF"),"NULL",bibE2.get_CurrSession()), false);

		
		this.addCompleteTable_FIELDLIST("JT_VPOS_KON_TRAKT",":GUELTIG_VON:GUELTIG_BIS:ABGESCHLOSSEN:",true,false, "K_");
		this.add_SQLField(new SQLFieldForPrimaryKey("JT_VPOS_KON_TRAKT","ID_VPOS_KON_TRAKT","K_ID_VPOS_KON_TRAKT",new MyE2_String("ID_VPOS_KON_TRAKT"),bibE2.get_CurrSession(),"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_VPOS_KON_TRAKT.NEXTVAL FROM DUAL",true), false);
		this.add_InnerConnector(new SQLConnectorInnerTables("JT_VPOS_KON.ID_VPOS_KON=JT_VPOS_KON_TRAKT.ID_VPOS_KON"));
		
		
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
		
		// standard-sortierung nach positionsnummer
		this.add_ORDER_SEGMENT("JT_VPOS_KON.POSITIONSNUMMER");
		
		this.get_("DELETED").set_cDefaultValueFormated("N");
		this.get_("DELETED").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);

		
		this.initFields();
	}

}
