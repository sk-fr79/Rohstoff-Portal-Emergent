package panter.gmbh.Echo2.__CONTAINER_ADDONS.MODULSETTINGS.SELECTORS;

import java.util.LinkedHashMap;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
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
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.Replacer.bibReplacer;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;


public class SEL_MASK_ComponentMAP extends E2_ComponentMAP 
{

	public static String    MASK_COMP_HELPBUTTON = 		"MASK_COMP_HELPBUTTON";
	public static String    MASK_COMP_PRUEFBUTTON = 	"MASK_COMP_PRUEFBUTTON";
	
//	private E2_BasicModuleContainer  oCallingModuleContainer = null;
//	
//	private myTempFileAutoDel  		oTempfile = null;  

	
	public SEL_MASK_ComponentMAP(E2_BasicModuleContainer CallingModuleContainer) throws myException
	{
		super(new SEL_MASK_SQLFieldMAP(CallingModuleContainer.get_MODUL_IDENTIFIER()));
		
//		this.oCallingModuleContainer = CallingModuleContainer;
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		

		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_Label(oFM.get_(_DB.SELECTOR$ID_SELECTOR)), 				new MyE2_String("ID-Selektor"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_(_DB.SELECTOR$USER_TEXT),true,700), 	new MyE2_String("Text für die Selektionsauswahl"));
		this.add_Component(new MyE2_DB_TextArea(oFM.get_(_DB.SELECTOR$WHEREBLOCK),700,18), 		new MyE2_String("Definition der Abfrage"));
		
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(_DB.SELECTOR$AKTIV)), 					new MyE2_String("Aktiv"));
		
		this.add_Component(SEL_MASK_ComponentMAP.MASK_COMP_HELPBUTTON, new ownHelpButton(new Extent(700), new Extent(400)), new MyE2_String("Infos"));
		this.add_Component(SEL_MASK_ComponentMAP.MASK_COMP_PRUEFBUTTON, new SEL__PRUEFBUTTON(CallingModuleContainer), new MyE2_String("Infos"));

		
		/*
 		 * definiert die maskeneinstellungen evtl. vor dem bearbeiten
		 */
		this.set_oMAPSettingAgent(new SEL_MASK_MapSettingAgent());
		
		/*
		 * ermoeglicht formatierungen von zusatzinfos in der maske
		 */
		this.set_oSubQueryAgent(new SEL_MASK_FORMATING_Agent());
		
		
		this.get_hmInteractiv_settings_validation().put_(_DB.SELECTOR$WHEREBLOCK, new ownMapSetAndValidator_ValidVerboteneWorte());
		
		this.add_oMAPValidator(new XX_MAP_ValidBeforeSAVE()
		{
			@Override
			public MyE2_MessageVector _doValidation(E2_ComponentMAP oMap, SQLMaskInputMAP oInputMap, String cSTATUS_MASKE) throws myException
			{
				return oMap.get_hmInteractiv_settings_validation().execute_ComponentMAP_ValidBeforeSave_settings(oMap, oInputMap);
			}
		});
	}
	
	
	
	private class ownHelpButton extends MyE2_HelpButtonWithHelpWindow
	{
		
		public ownHelpButton(Extent ExtWidth, Extent ExtHeight)
		{
			super(ExtWidth, ExtHeight);
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
			
			
			//jetzt ein grid mit den moeglichen platzhaltern
			int iBreite[] = {100,200,200};
			MyE2_Grid oGridInfoBlock = new MyE2_Grid(iBreite, MyE2_Grid.STYLE_GRID_DDARK_BORDER_NO_INSETS()) ;
			oGridInfoBlock.add(new MyE2_Label(new MyE2_String("In den Vorgabewerten und Abfragen koennen folgende Platzhalter stehen:",true),new E2_FontBold(-2)),3,new Insets(1,1,1,5));
			
			LinkedHashMap<String, String[]> lhashMap = bibReplacer.get_ListOfReplaceFields(null);
			
			oGridInfoBlock.add(new MyE2_Label(new MyE2_String("Platzhalter"),		new E2_FontBold(-2)),E2_INSETS.I_1_0_1_0);
			oGridInfoBlock.add(new MyE2_Label(new MyE2_String("Momentaner Wert"),	new E2_FontBold(-2)),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_1_0_1_0));
			oGridInfoBlock.add(new MyE2_Label(new MyE2_String("Beschreibung"),		new E2_FontBold(-2)),E2_INSETS.I_1_0_1_0);
			
			for (String cKey: lhashMap.keySet())
			{
				oGridInfoBlock.add(new MyE2_Label(new MyE2_String(cKey),new E2_FontPlain(-2)),E2_INSETS.I_1_0_1_0);
				oGridInfoBlock.add(new MyE2_Label(new MyE2_String(lhashMap.get(cKey)[1]),new E2_FontItalic(-2)),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_1_0_1_0));
				oGridInfoBlock.add(new MyE2_Label(new MyE2_String(lhashMap.get(cKey)[0]),new E2_FontPlain(-2)),E2_INSETS.I_1_0_1_0);
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
				String cWhere_WERT = oMAP.get_cActualDBValueFormated(_DB.SELECTOR$WHEREBLOCK);
				
				String cWhere_INTERPRET = bibReplacer.ReplaceSysvariablesInStrings(cWhere_WERT).toUpperCase();
			
				String cForbiddenWord = bibALL.Check_forbidden_words(cWhere_INTERPRET);
				
				if (S.isFull(cForbiddenWord))
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Sie haben eine Where-Klausel mit dem verbotenen Wort ",true,cForbiddenWord,false, " erfasst !!",true)));
				}
			}
			
			return oMV;
		}
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
