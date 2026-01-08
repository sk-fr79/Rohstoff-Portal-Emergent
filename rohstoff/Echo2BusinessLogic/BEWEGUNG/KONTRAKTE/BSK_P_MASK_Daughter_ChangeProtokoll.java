package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.ActionAgent_DaughterTable_NEW;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_ButtonMarkForDelete;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_DBC_DaughterTable;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLFieldJoinOutside;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;



public class BSK_P_MASK_Daughter_ChangeProtokoll extends MyE2_DBC_DaughterTable
{
	
	public BSK_P_MASK_Daughter_ChangeProtokoll(	SQLFieldMAP 			fieldMAP, 
												E2_ComponentMAP			ocomponentMAP_from_Mother) throws myException
	{
		super();
		
		/*
		* tochtertabelle fuer das protokoll der preis- und mengenaenderungen
		*/
		Project_SQLFieldMAP    oFieldMapChanges = new Project_SQLFieldMAP("JT_VPOS_KON_AENDERUNGEN",":ID_VPOS_KON:",false);

		this.set_bDaughterIsPassive(true);
		
		oFieldMapChanges.add_SQLField(new SQLFieldJoinOutside("JT_VPOS_KON_AENDERUNGEN",
															"ID_VPOS_KON",
															"ID_VPOS_KON",
															new MyE2_String("ID-VPOS-KON"),
															false,
															bibE2.get_CurrSession(),
															fieldMAP.get_SQLField("ID_VPOS_KON")), 
															false);
		
		oFieldMapChanges.add_ORDER_SEGMENT("ID_VPOS_KON_AENDERUNGEN DESC");
		oFieldMapChanges.initFields();
		
		
		E2_ComponentMAP oMapDruck = new E2_ComponentMAP(oFieldMapChanges);
		MyE2_ButtonMarkForDelete 	oButtonForDel = 	new MyE2_ButtonMarkForDelete();
		MyE2_DB_TextField			oTF_Anzahl = 		new MyE2_DB_TextField(oFieldMapChanges.get_SQLField("ANZAHL"));
		MyE2_DB_TextField			oTF_EPreis = 		new MyE2_DB_TextField(oFieldMapChanges.get_SQLField("EINZELPREIS"));
		MyE2_DB_TextField			oTF_GeaendertAM = 	new MyE2_DB_TextField(oFieldMapChanges.get_SQLField("DATUM_AENDERUNG"));
		MyE2_DB_TextField			oTF_GeaendertUM = 	new MyE2_DB_TextField(oFieldMapChanges.get_SQLField("UHRZEIT_AENDERUNG"));
		MyE2_DB_TextField			oTF_GeaendertVON = 	new MyE2_DB_TextField(oFieldMapChanges.get_SQLField("KUERZEL_AENDERUNG"));
		
		oMapDruck.add_Component("DEL_BUTTON",oButtonForDel,new MyE2_String("?"));
		oMapDruck.add_Component(oTF_Anzahl,new MyE2_String("Anzahl"));
		oMapDruck.add_Component(oTF_EPreis,new MyE2_String("Einzelpreis"));
		oMapDruck.add_Component(oTF_GeaendertAM,new MyE2_String("Geändert am"));
		oMapDruck.add_Component(oTF_GeaendertUM,new MyE2_String("Uhrzeit"));
		oMapDruck.add_Component(oTF_GeaendertVON,new MyE2_String("von"));
		
		/*
		 * neueingabe-button definieren
		 */
		MyE2_Button		oButtonNEW = new MyE2_Button(E2_ResourceIcon.get_RI("new.png"),true);
		oButtonNEW.add_oActionAgent(new ActionAgent_DaughterTable_NEW(this, false));
		oButtonForDel.EXT().set_oCompTitleInList(oButtonNEW);
		this.get_vComponentForDifferentTasks().add(oButtonNEW);
		
		this.set_oContainerExScrollHeight(new Extent(400));
		
		this.INIT_DAUGHTER(	fieldMAP.get_oSQLFieldPKMainTable(),
					ocomponentMAP_from_Mother,
					oMapDruck,
					null);
		
		this.EXT().set_bDisabledFromBasic(true);
	}
	
}
