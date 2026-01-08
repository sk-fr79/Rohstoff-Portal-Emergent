package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.GROOVYS;

import java.util.HashMap;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_ValidBeforeSAVE;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_MAP_Set_And_Valid;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_HelpButtonWithHelpWindow;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibGroovy;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;


public class GROOVY_MASK_ComponentMAP extends E2_ComponentMAP 
{

	public static String    HASHKEY_HELP_BUTTON = "HASHKEY_HELP_BUTTON";
	
	
	public GROOVY_MASK_ComponentMAP() throws myException
	{
		super(new GROOVY_MASK_SQLFieldMAP());
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		

		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_Label(oFM.get_(_DB.GROOVYSCRIPT$ID_GROOVYSCRIPT)), new MyE2_String("ID"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_(_DB.GROOVYSCRIPT$NAME_RETURN_VAR),true,300), new MyE2_String("Variable für Rückgabe"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_(_DB.GROOVYSCRIPT$USER_TEXT),true,700), new MyE2_String("Benutzeranzeige"));
		this.add_Component(new MyE2_DB_TextArea(oFM.get_(_DB.GROOVYSCRIPT$SCRIPT),700,18), new MyE2_String("Script"));
		
		this.add_Component(GROOVY_MASK_ComponentMAP.HASHKEY_HELP_BUTTON, new ownHelpButton(), new MyE2_String("Hilfe und Infos zur Programmierung"));
		
		/*
 		 * definiert die maskeneinstellungen evtl. vor dem bearbeiten
		 */
		this.set_oMAPSettingAgent(new GROOVY_MASK_MapSettingAgent());
		
		/*
		 * ermoeglicht formatierungen von zusatzinfos in der maske
		 */
		this.set_oSubQueryAgent(new GROOVY_MASK_FORMATING_Agent());
		
		
		this.get_hmInteractiv_settings_validation().put_(_DB.GROOVYSCRIPT$SCRIPT, new ownMapSetAndValidator_ValidVerboteneWorte());
		
		this.add_oMAPValidator(new XX_MAP_ValidBeforeSAVE()
		{
			@Override
			public MyE2_MessageVector _doValidation(E2_ComponentMAP oMap, SQLMaskInputMAP oInputMap, String cSTATUS_MASKE) throws myException
			{
				return oMap.get_hmInteractiv_settings_validation().execute_ComponentMAP_ValidBeforeSave_settings(oMap,oInputMap);
			}
		});

		
	}
	
	
	private class ownHelpButton extends MyE2_HelpButtonWithHelpWindow
	{

		public ownHelpButton()
		{
			super(new Extent(600), new Extent(300));
		}

		@Override
		public E2_BasicModuleContainer createOwnBasicModuleContainer() throws myException
		{
			return new ownBasicContainer();
		}
		private class ownBasicContainer extends E2_BasicModuleContainer
		{
		}

		@Override
		public void fillInternalColumn(MyE2_Column oColumn) throws myException
		{
			oColumn.removeAll();
			
			int iBreite[] = {300,300};
			
			MyE2_Grid oGridInfoBlock = new MyE2_Grid(iBreite, MyE2_Grid.STYLE_GRID_DDARK_BORDER_NO_INSETS()) ;
			oGridInfoBlock.add(new MyE2_Label(new MyE2_String("Den Scripten stehen folgende Variable zur Verfügung:",true),new E2_FontBold(-2)),2,E2_INSETS.I_1_0_1_0);
			
			HashMap<String, MyE2_String> hmVars  = bibGroovy.get_HashMap_ParameterUebergaben();
			
			oGridInfoBlock.add(new MyE2_Label(new MyE2_String("Name"),				new E2_FontBold(-2)),E2_INSETS.I_1_0_1_0);
			oGridInfoBlock.add(new MyE2_Label(new MyE2_String("Beschreibung"),		new E2_FontBold(-2)),E2_INSETS.I_1_0_1_0);
			
			for (String cKey: hmVars.keySet())
			{
				oGridInfoBlock.add(new MyE2_Label(new MyE2_String(cKey),new E2_FontPlain(-2)),E2_INSETS.I_1_0_1_0);
				oGridInfoBlock.add(new MyE2_Label(hmVars.get(cKey),new E2_FontItalic(-2)),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_1_0_1_0));
			}

			oColumn.add(oGridInfoBlock);

		}
		
	}
	
	
	
	//einen map-set-and-validator, um verbotene where-Eintraege zu verhindern (stichwort: sql-injection)
	private class ownMapSetAndValidator_ValidVerboteneWorte extends XX_MAP_Set_And_Valid
	{

		@Override
		public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_COPY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
		{
			return this.pruefe(oMAP, ActionType, oExecInfo);
		}

		@Override
		public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_EMPTY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
		{
			return this.pruefe(oMAP, ActionType, oExecInfo);
		}
		

		@Override
		public MyE2_MessageVector make_InteractiveSettings_STATUS_EDIT(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
		{
			return this.pruefe(oMAP, ActionType, oExecInfo);
		}

		@Override
		public MyE2_MessageVector make_InteractiveSettings_STATUS_VIEW(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
		{
			return new MyE2_MessageVector();			
		}

		
		@Override
		public MyE2_MessageVector make_InteractiveSettings_STATUS_UNDEFINED(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
		{
			return new MyE2_MessageVector();
		}

		
		private MyE2_MessageVector pruefe(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo) throws myException
		{
			MyE2_MessageVector  oMV = new MyE2_MessageVector();
			
			if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_VALID_ACTION)
			{
				String cWhere_WERT = oMAP.get_cActualDBValueFormated(_DB.GROOVYSCRIPT$SCRIPT);
				String cForbiddenWord1 = bibALL.Check_forbidden_words(cWhere_WERT);
				
				//jetzt noch die interpretierte version versuchen (falls interpretierung geht)
				String cForbiddenWord2 = ""; 
				try
				{
					String cVariable = 	oMAP.get_cActualDBValueFormated(_DB.GROOVYSCRIPT$NAME_RETURN_VAR);
					String cScript = 	oMAP.get_cActualDBValueFormated(_DB.GROOVYSCRIPT$SCRIPT);
					String cWhere_INTERPRET = bibGroovy.interpretGroovySingleStringReturn(cScript, null, cVariable);
					cForbiddenWord1 = bibALL.Check_forbidden_words(cWhere_INTERPRET);
				}
				catch (Exception ex)
				{
					cForbiddenWord2 = "";
				}
				
				if (S.isFull(cForbiddenWord1) || S.isFull(cForbiddenWord2))
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Sie haben eine Where-Klausel mit dem verbotenen Wort ",true,S.NN(cForbiddenWord1)+"/"+S.NN(cForbiddenWord2),false, " erfasst !!",true)));
				}
			}
			
			return oMV;
		}
		
	}

	
	
}
