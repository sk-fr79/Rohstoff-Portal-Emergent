package panter.gmbh.Echo2.__CONTAINER_ADDONS.CALENDAR;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_SettingAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class CAL_ComponentMAP_MASK_TERMIN_USER extends E2_ComponentMAP 
{
	public static String HASHKEY_GRID_VERTEILER = "";
	
	private CAL_ModuleContainer_MASK           oModuleContainerMASK = null;

	
	public CAL_ComponentMAP_MASK_TERMIN_USER(SQLFieldMAP oSQLFieldMap_Termin, CAL_ModuleContainer_MASK ModuleContainerMASK)  throws myException
	{
		super(new CAL_SQLFieldMAP_TERMIN_USER(oSQLFieldMap_Termin));
		
		this.oModuleContainerMASK = ModuleContainerMASK;
		
		SQLFieldMAP oSqlFM = this.get_oSQLFieldMAP();
		
		this.add_Component( new MyE2_DB_Label(oSqlFM.get_("ID_TERMIN_USER")),new MyE2_String("ID_TERMIN_USER"));
		this.add_Component( new MyE2_DB_Label(oSqlFM.get_("ID_USER")),new MyE2_String("ID_USER"));
		this.add_Component( new MyE2_DB_CheckBox(oSqlFM.get_("IS_OWNER")),new MyE2_String("Ist Besitzer"));
		this.add_Component( new MyE2_DB_TextArea(oSqlFM.get_("LANGTEXT_PRIVATE")),new MyE2_String("Langtext Privat"));
	
		this.add_Component(CAL_ComponentMAP_MASK_TERMIN_USER.HASHKEY_GRID_VERTEILER,new CAL_COMPONENT_GridVerteiler(),new MyE2_String("Verteiler"));

		((MyE2_DB_TextArea)this.get__Comp("LANGTEXT_PRIVATE")).set_iWidthPixel(400);
		((MyE2_DB_TextArea)this.get__Comp("LANGTEXT_PRIVATE")).set_iRows(5);
		
		
		this.set_oMAPSettingAgent(new mapSettingAgent());
	}

	
	public CAL_ModuleContainer_MASK get_oModuleContainerMASK() 
	{
		return oModuleContainerMASK;
	}

	
	
	/*
	 * map-setting-agent verwaltet den verteiler
	 */
	private class mapSettingAgent extends XX_MAP_SettingAgent
	{

		public void __doSettings_BEFORE(E2_ComponentMAP oMap_TERMIN_USER, String STATUS_MASKE) throws myException 
		{
			CAL_ComponentMAP_MASK_TERMIN_USER oThis = CAL_ComponentMAP_MASK_TERMIN_USER.this;
			
			MyE2_Button oButtonDel = oThis.oModuleContainerMASK.get_oButtonDelete();

			// hier wird der verteiler aktiviert
			CAL_COMPONENT_GridVerteiler oVerTeiler = (CAL_COMPONENT_GridVerteiler)oMap_TERMIN_USER.get__Comp(CAL_ComponentMAP_MASK_TERMIN_USER.HASHKEY_GRID_VERTEILER);
			
			if (STATUS_MASKE.equals(E2_ComponentMAP.STATUS_NEW_EMPTY))
				oVerTeiler.build_Grid_4_NEW();
			else
				oVerTeiler.build_Grid_4_Edit(oMap_TERMIN_USER.get_oInternalSQLResultMAP().get_UnFormatedValue("ID_TERMIN_USER"));
			
			
			// falls status edit, muss die maske sicherstellen, dass bei nicht eigenen Terminen alles, bis auf den 
			// privaten text, gesperrt bleibt
			E2_ComponentMAP oMap_TERMIN = (E2_ComponentMAP)oMap_TERMIN_USER.get_E2_vCombinedComponentMAPs().get(0);
			
			oMap_TERMIN.get__Comp("ZEIT_VON").EXT().set_bDisabledFromInteractive(false);
			oMap_TERMIN.get__Comp("ZEIT_BIS").EXT().set_bDisabledFromInteractive(false);
			oMap_TERMIN.get__Comp("KURZTEXT").EXT().set_bDisabledFromInteractive(false);
			oMap_TERMIN.get__Comp("LANGTEXT").EXT().set_bDisabledFromInteractive(false);
			oButtonDel.setVisible(true);                   // DELETE - button ist sichtbar (wenn es ein eigener Termin ist)
			
			if (STATUS_MASKE.equals(E2_ComponentMAP.STATUS_EDIT))
			{
				boolean bEigen = false;
				if (bibALL.null2leer(oMap_TERMIN_USER.get_oInternalSQLResultMAP().get_UnFormatedValue("IS_OWNER")).equals("Y"))
					bEigen = true;
				
				if (!bEigen)
				{
					oMap_TERMIN.get__Comp("ZEIT_VON").EXT().set_bDisabledFromInteractive(true);
					oMap_TERMIN.get__Comp("ZEIT_BIS").EXT().set_bDisabledFromInteractive(true);
					oMap_TERMIN.get__Comp("KURZTEXT").EXT().set_bDisabledFromInteractive(true);
					oMap_TERMIN.get__Comp("LANGTEXT").EXT().set_bDisabledFromInteractive(true);
					oButtonDel.setVisible(false);
				}
				
			}
			
			
		}

		public void __doSettings_AFTER(E2_ComponentMAP oMap, String STATUS_MASKE) throws myException 
		{
			
		}
		
	}
	
	
	
}
