package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.DB_Component_LAND_DROPDOWN;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.ActionAgent_DaughterTable_NEW;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_ButtonMarkForDelete;
import panter.gmbh.Echo2.components.DB.SIMPLE_DAUGHTER.MyE2_DBC_DaughterTable;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAND;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldJoinOutside;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FS_Component_MASK_DAUGHTER_UST_IDS extends MyE2_DBC_DaughterTable
{

	private E2_ComponentMAP 	oMapUSTIDs = null;
	private MyE2_DB_TextArea	oTF_AuslandVertreter = null;
	
	public FS_Component_MASK_DAUGHTER_UST_IDS(	SQLFieldMAP 		fieldMAPMotherTable, 
												E2_ComponentMAP		ocomponentMAP_from_Mother) throws myException
	{
		super();
		
		SQLFieldMAP oSQLFieldMap_UST_IDS = new SQLFieldMAP("JT_ADRESSE_UST_ID",bibE2.get_CurrSession());
		oSQLFieldMap_UST_IDS.addCompleteTable_FIELDLIST("JT_ADRESSE_UST_ID",":ID_ADRESSE_UST_ID:ID_ADRESSE:ID_MANDANT:GEAENDERT_VON:LETZTE_AENDERUNG:",false,true,"");
		oSQLFieldMap_UST_IDS.add_SQLField(new SQLFieldForPrimaryKey("JT_ADRESSE_UST_ID","ID_ADRESSE_UST_ID","ID_ADRESSE_UST_ID",new MyE2_String("ID-UST-ID"),bibE2.get_CurrSession(),
									"SELECT "+bibALL.get_TABLEOWNER()+".SEQ_ADRESSE_UST_ID.NEXTVAL FROM DUAL",true), false);

		oSQLFieldMap_UST_IDS.add_SQLField(new SQLFieldJoinOutside("JT_ADRESSE_UST_ID","ID_ADRESSE","ID_ADRESSE",
											new MyE2_String("ID-Adresse"),false,bibE2.get_CurrSession(),fieldMAPMotherTable.get_SQLField("ID_ADRESSE")), false);

		oSQLFieldMap_UST_IDS.initFields();
		
		
		this.oMapUSTIDs = 								new E2_ComponentMAP(oSQLFieldMap_UST_IDS);
		MyE2_ButtonMarkForDelete 	oButtonForDel = 	new MyE2_ButtonMarkForDelete();
		
		MyE2_DB_TextField			oTF_UST_LKZ = 		new MyE2_DB_TextField(oSQLFieldMap_UST_IDS.get_SQLField("UMSATZSTEUERLKZ"));
		MyE2_DB_TextField			oTF_UST_Id = 		new MyE2_DB_TextField(oSQLFieldMap_UST_IDS.get_SQLField("UMSATZSTEUERID"));
		this.oTF_AuslandVertreter = new MyE2_DB_TextArea(oSQLFieldMap_UST_IDS.get_SQLField("TEXT_AUSLANDSVERTRETUNG"),300,2,null,new E2_FontPlain(-2));
		
		//2014-01-07: land zufuegen
		DB_Component_LAND_DROPDOWN   oDB_Land = new DB_Component_LAND_DROPDOWN(oSQLFieldMap_UST_IDS.get_SQLField(_DB.ADRESSE_UST_ID$ID_LAND), 
																				70, false, new ownActionSetUST_Code());
		
		//2014-01-27: schalter, ob beschraenkt auf ust-id
		MyE2_DB_CheckBox    		oCB_NurRC = new MyE2_DB_CheckBox(oSQLFieldMap_UST_IDS.get_(_DB.ADRESSE_UST_ID$BEGRENZT_AUF_RC_SORTEN));

		//2015-04-21: USt-ID-Check-Button
		MyE2_Button    		UStIdCheckButton = new FS_BT_CheckUStID(	"UMSATZSTEUERLKZ", "UMSATZSTEUERID");
		UStIdCheckButton.EXT().set_oComponentMAP(oMapUSTIDs);

		
		
		oTF_UST_LKZ.set_iWidthPixel(50);
		oTF_UST_Id.set_iWidthPixel(150);

		oTF_UST_LKZ.EXT().set_oColExtent(new Extent(80));
		oTF_UST_Id.EXT().set_oColExtent(new Extent(160));
		
		oTF_UST_LKZ.EXT().set_oStyle_4_ListHeaderComponent(MyE2_Label.STYLE_SMALL_ITALIC());
		oTF_UST_Id.EXT().set_oStyle_4_ListHeaderComponent(MyE2_Label.STYLE_SMALL_ITALIC());
		
		oDB_Land.EXT().set_oColExtent(new Extent(80));
		oDB_Land.EXT().set_oStyle_4_ListHeaderComponent(MyE2_Label.STYLE_SMALL_ITALIC());
		
		oTF_AuslandVertreter.EXT().set_oColExtent(new Extent(310));
		oTF_AuslandVertreter.EXT().set_oStyle_4_ListHeaderComponent(MyE2_Label.STYLE_SMALL_ITALIC());
		
		oTF_UST_LKZ.EXT_DB().set_bIsSortable(false);
		oTF_UST_Id.EXT_DB().set_bIsSortable(false);
		
		oMapUSTIDs.add_Component("DELETE_SELECTOR",oButtonForDel,new MyE2_String("?"));
		oMapUSTIDs.add_Component(oDB_Land,new MyE2_String("LAND"));
		oMapUSTIDs.add_Component(oTF_UST_LKZ,new MyE2_String("UST-LKZ"));
		oMapUSTIDs.add_Component(oTF_UST_Id,new MyE2_String("UST-ID"));
		oMapUSTIDs.add_Component("CHECK_BT", UStIdCheckButton, new MyE2_String("OK?"));
		oMapUSTIDs.add_Component(oCB_NurRC,new MyE2_String("Nur RC?"));
		oMapUSTIDs.add_Component(oTF_AuslandVertreter,new MyE2_String("Text Auslandsvertretung"));
	
		MyE2_Button		oButtonNEW = new MyE2_Button(E2_ResourceIcon.get_RI("new.png"),true);
		
		oButtonNEW.add_oActionAgent(new ActionAgent_DaughterTable_NEW(this, false));
		oButtonForDel.EXT().set_oCompTitleInList(oButtonNEW);
		this.get_vComponentForDifferentTasks().add(oButtonNEW);


		this.set_to_100_percent();
        this.set_oContainerExScrollHeight(new Extent(130));		

		
        
		this.INIT_DAUGHTER(fieldMAPMotherTable.get_oSQLFieldPKMainTable(),
							ocomponentMAP_from_Mother,
							oMapUSTIDs,
							null);
		
		this.get_oNavigationList().hide_NavigationsElements();
	}

	
	public void set_enableAuslandsText(boolean bEnabled) throws myException
	{
		this.oTF_AuslandVertreter.EXT().set_bDisabledFromInteractive(!bEnabled);
		Vector<E2_ComponentMAP> vMaps = this.get_oNavigationList().get_vComponentMAPS();
		
		for(E2_ComponentMAP oMap: vMaps)
		{
			oMap.get__Comp("TEXT_AUSLANDSVERTRETUNG").EXT().set_bDisabledFromInteractive(!bEnabled);
			oMap.get__Comp("TEXT_AUSLANDSVERTRETUNG").set_bEnabled_For_Edit(bEnabled);
		}
	}
	
	
	private class ownActionSetUST_Code extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			if (oExecInfo!=null && oExecInfo.get_MyActionEvent()!=null && oExecInfo.get_MyActionEvent().getSource()!=null) {
				E2_ComponentMAP  oMAP =((MyE2_DB_SelectField) oExecInfo.get_MyActionEvent().getSource()).EXT().get_oComponentMAP(); 
				
				//zuerst alte ust-lkz loeschen
				oMAP.set_cActualDatabaseValueToMask(_DB.ADRESSE_UST_ID$UMSATZSTEUERLKZ, "");
				
				Long lID_Land =  oMAP.get_LActualDBValue(_DB.ADRESSE_UST_ID$ID_LAND, -1l, -1l);
				
				if (lID_Land>0) {
					RECORD_LAND  recLand = new RECORD_LAND(lID_Land);
					if (S.isFull(recLand.get_UST_PRAEFIX_cUF_NN(""))) {
						oMAP.set_cActualDatabaseValueToMask(_DB.ADRESSE_UST_ID$UMSATZSTEUERLKZ, recLand.get_UST_PRAEFIX_cUF_NN(""));
					}
				}
				
			}
			
		}
		
	}
	
}
