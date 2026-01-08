package panter.gmbh.Echo2.__CONTAINER_ADDONS.TODOS;

import panter.gmbh.Echo2.E2_DropDownSettings;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.Mask.MaskComponentsFAB;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_MaskInfo;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.DB_Component_USER_DROPDOWN;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class TODO_MASK_ComponentMAP extends E2_ComponentMAP 
{

	public TODO_MASK_ComponentMAP() throws myException
	{
		super(new TODO_MASK_SQLFieldMAP());
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		
		String[] cFieldsStandard = {"GENERIERUNGSDATUM","ABLAUFDATUM","ABSCHLUSSDATUM","AUFGABEKURZ","AUFGABENTEXT","ANTWORTKURZ","ANTWORTTEXT","ERLEDIGT"}; 
		String[] cFieldsInfolabs = {"Erstellungsdatum","Ablaufdatum","Abgeschlossen am","Aufgabe (Kurztext)","Aufgabe (Beschreibung)","Antwort (Kurztext)","Antwort (Beschreibung)","Erledigt"}; 

		E2_DropDownSettings oDDWichtigkeit = new E2_DropDownSettings("JT_TODO_WICHTIGKEIT","BESCHREIBUNG","ID_TODO_WICHTIGKEIT","ISTSTANDARD",true);
		
		MaskComponentsFAB.addStandardComponentsToMAP(cFieldsStandard, cFieldsInfolabs, oFM, false, false, this, 400);
		
		this.add_Component(TODO_MASK_BasicModuleContainer.HASHVALUE_TODO_TEILNEHMER,new TODO_MASK_CROSS_TEILNEHMER(oFM), new MyE2_String("Teilnehmerliste"));
		this.add_Component(new DB_Component_USER_DROPDOWN(oFM.get_("ID_USER")), new MyE2_String("Besitzer"));
		this.add_Component(new MyE2_DB_SelectField(oFM.get_("ID_TODO_WICHTIGKEIT"),oDDWichtigkeit.getDD(),false), new MyE2_String("Wichtig ?"));
		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_TODO")), new MyE2_String("ID"));

		((MyE2_DB_TextArea)this.get__Comp("AUFGABENTEXT")).set_iWidthPixel(600);
		((MyE2_DB_TextArea)this.get__Comp("ANTWORTTEXT")).set_iWidthPixel(600);
		((MyE2_DB_TextArea)this.get__Comp("AUFGABENTEXT")).set_iRows(4);
		((MyE2_DB_TextArea)this.get__Comp("ANTWORTTEXT")).set_iRows(4);
		
		((MyE2_DB_TextField)this.get__Comp("AUFGABEKURZ")).set_iWidthPixel(600);
		((MyE2_DB_TextField)this.get__Comp("ANTWORTKURZ")).set_iWidthPixel(600);

		((MyE2_DB_CheckBox)this.get__Comp("ERLEDIGT")).add_oActionAgent(new cbActionAgent());
	
		this.set_oMAPSettingAgent(new TODO_MASK_MapSettingAgent());
		
	}

	
	
	private class cbActionAgent extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			
			try
			{
				MyE2_DB_TextField oDateField = (MyE2_DB_TextField)(new E2_RecursiveSearch_MaskInfo(bibE2.get_LAST_ACTIONEVENT()).get_Component("ABSCHLUSSDATUM"));

				if (((MyE2_DB_CheckBox)bibE2.get_LAST_ACTIONEVENT().getSource()).isSelected())
				{
					if (bibALL.isEmpty(oDateField.getText()))
					{
						oDateField.set_cActualMaskValue(bibALL.get_cDateNOW());
					}
				}
			}
			catch (Exception ex)
			{
				throw new myException(this,": Maskcomponent ABSCHLUSSDATUM was not found !");
				
			}
		}
		
	}
}
