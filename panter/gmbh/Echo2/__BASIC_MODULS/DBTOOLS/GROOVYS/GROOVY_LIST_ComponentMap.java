package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.GROOVYS;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.E2_MessageBoxYesNo;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.basics4project.DB_ENUMS.GROOVYSCRIPT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_GROOVYSCRIPT;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibGroovy;
import panter.gmbh.indep.Replacer.bibReplacer;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.exceptions.myGroovyException;
import panter.gmbh.indep.myVectors.bibVECTOR;

public class GROOVY_LIST_ComponentMap extends E2_ComponentMAP 
{

	
	public GROOVY_LIST_ComponentMap() throws myException
	{
		super(new GROOVY_LIST_SqlFieldMAP());

		
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		
		this.add_Component(GROOVY_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(GROOVY_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));

		//hier kommen die Felder	
		this.add_Component("TEST_BUTTON", new btTestGroovy(), new MyE2_String("Test"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(_DB.GROOVYSCRIPT$ID_GROOVYSCRIPT)), new MyE2_String("ID"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(_DB.GROOVYSCRIPT$NAME_RETURN_VAR)), new MyE2_String("Variable für Rückgabe"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(_DB.GROOVYSCRIPT$USER_TEXT),true), 		new MyE2_String("Benutzeranzeige"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(_DB.GROOVYSCRIPT$SCRIPT),true), 			new MyE2_String("Script"));
		
		

		this.get__Comp(_DB.GROOVYSCRIPT$ID_GROOVYSCRIPT).EXT().set_oColExtent(new Extent(100));
		this.get__Comp(_DB.GROOVYSCRIPT$NAME_RETURN_VAR).EXT().set_oColExtent(new Extent(100));
		this.get__Comp(_DB.GROOVYSCRIPT$USER_TEXT).EXT().set_oColExtent(new Extent(200));
		this.get__Comp(_DB.GROOVYSCRIPT$SCRIPT).EXT().set_oColExtent(new Extent(500));
		

		this.set_oSubQueryAgent(new GROOVY_LIST_FORMATING_Agent());
	}

	
	
	private class btTestGroovy extends MyE2_ButtonInLIST
	{

		public btTestGroovy()
		{
			//super(E2_ResourceIcon.get_RI("sql_button.png"), true);
			super(new MyE2_String("Groovy"));
			
			this.add_oActionAgent(new ownActionAgent());
			this.setToolTipText(new MyE2_String("Groovy-Ausführung simulieren ...").CTrans());
		}
		

		public Object get_Copy(Object objHelp) throws myExceptionCopy
		{
			try
			{
				return new btTestGroovy();
			}
			catch (Exception ex)
			{
				throw new myExceptionCopy("Error Copying SQL-Test-Button");
			}
		}
		
		
		private class ownActionAgent extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				btTestGroovy btTest = (btTestGroovy)oExecInfo.get_MyActionEvent().getSource();
				
				RECORD_GROOVYSCRIPT_SPECIAL  recScript = new RECORD_GROOVYSCRIPT_SPECIAL(btTest.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
				//String cWert = recScript._get_KeyValuePair()[1];
				
//				if (S.isFull(cWert))
//				{
//					new E2_MessageBoxYesNo(	new MyE2_String("Script-Ergebnis"), 
//											new MyE2_String("OK"), 
//											null, 
//											true,
//											false,
//											bibVECTOR.get_Vector(	new MyE2_String("Inhalt der Variable: ",true),
//																	new MyE2_String("****************************",false),
//																	new MyE2_String(recScript.get_NAME_RETURN_VAR_cUF(),false), 
//																	new MyE2_String(cWert,false),
//																	new MyE2_String("****************************",true)),
//											null);
//				}
//				else
//				{
//					new E2_MessageBoxYesNo(new MyE2_String("Script-Ergebnis: Auswertung ergab einen Fehler !")
//											, new MyE2_String("OK"), null,true,false, null,null);
//					
//				}
				
				new ownBasicModuleContainer(recScript);
				
				
			}
			
		}
		
	}
	
	
	
	private class ownBasicModuleContainer extends E2_BasicModuleContainer {
		private RECORD_GROOVYSCRIPT_SPECIAL  recScript = null;
		
		private RB_TextArea  			t_script = new RB_TextArea(400, 10)._col_back_white()._bord_ddd()._txt("");
		private RB_TextArea  			t_errorMessage = new RB_TextArea(400, 10)._bord_ddd()._col_back(new E2_ColorHelpBackground())._bord_ddd();
		
		private RB_TextField  			t_ausdruck = new RB_TextField(400)._bord_ddd()._txt("");
		private RB_TextField  			t_ergebnis = new RB_TextField(400)._bord_ddd()._txt("");
//		
		private Vector<RB_TextField> 	v_tf_parameters = new Vector<>();

		public ownBasicModuleContainer(RECORD_GROOVYSCRIPT_SPECIAL p_recScript) throws myException {
			super();
			this.recScript = p_recScript;
		
			String scripttext = this.recScript.ufs(GROOVYSCRIPT.script);
			
			
			this.t_script.setText(scripttext);	
			
			this.fill_ausdruck();

			this.t_ergebnis.setText("");
			
			
			E2_Grid  grid = new E2_Grid()._setSize(150,500,80)
										._a(new RB_lab()._t(new MyE2_String("Script")), 			new RB_gld()._left_top()._ins(3))
										._a(this.t_script,											new RB_gld()._left_top()._ins(3))
										._a(new ownBtSave(), 										new RB_gld()._left_top()._ins(3))
										
										._a(new RB_lab()._t(new MyE2_String("Ausdruck im Text")), 	new RB_gld()._left_top()._ins(3))
										._a(this.t_ausdruck,										new RB_gld()._left_top()._ins(3))
										._a()
										
										._a(new RB_lab()._t(new MyE2_String("Test:")), 				new RB_gld()._left_top()._ins(3))
										._a(new ownBtTest(),										new RB_gld()._left_top()._ins(3))
										._a()
										
										._a(new RB_lab()._t(new MyE2_String("Ergebnis:")), 			new RB_gld()._left_top()._ins(3))
										._a(this.t_ergebnis,										new RB_gld()._left_top()._ins(3))
										._a()
										
										._a(new RB_lab()._t(new MyE2_String("Fehler:")), 			new RB_gld()._left_top()._ins(3))
										._a(this.t_errorMessage,									new RB_gld()._left_top()._ins(3))
										._a()
										;

			this.add(grid, E2_INSETS.I(2,2,2,2));
			
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(850), new Extent(500), new MyE2_String("Test des Scripts ..."));
			
		}

		
		private void fill_ausdruck() throws myException {
			String scripttext = this.recScript.ufs(GROOVYSCRIPT.script);

			//nachsehen, ob es parameter gibt
			String parameterlist = "";
			for (int i=0;i<100;i++) {
				if (scripttext.contains("PARAMETER"+i)) {
					parameterlist=":"+("PARAMETER"+i);
				}
			}
			this.t_ausdruck.setText("#GROOVY:"+this.recScript.ufs(GROOVYSCRIPT.name_return_var)+parameterlist+"#");
		}
		
		
		private class ownBtTest extends E2_Button {

			public ownBtTest() {
				super();
				this._t(new MyE2_String("Groovy testen"))._fo_s_plus(4)
					._s_BorderText()
					;
				this.setWidth(new Extent(400));
				this.add_oActionAgent(new ownActionTest());
			}
			
		}
		
		private class ownActionTest extends XX_ActionAgent {

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				ownBasicModuleContainer oThis = ownBasicModuleContainer.this;
				String e="";
				oThis.t_errorMessage._txt("")._col_back_white();
				try {
					e = bibGroovy.replace_groovy_placeholders_with_groovy_results(oThis.t_ausdruck.getText(), oThis.recScript.ufs(GROOVYSCRIPT.name_return_var), oThis.t_script.getText());
				} catch (myGroovyException eg) {
					eg.printStackTrace();
					oThis.t_errorMessage._col_back_alarm()._txt(eg.getLocalizedMessage());
				} catch (Exception e1) {
					e1.printStackTrace();
					oThis.t_errorMessage._col_back_alarm()._txt(e1.getLocalizedMessage());
				}
				oThis.t_ergebnis.setText(e);
			}
			
		}
		
		
		private class ownBtSave extends E2_Button {
			public ownBtSave() {
				super();
				this._t(new MyE2_String("speichern"))._fo_s_plus(2)._fo_bold()
					._s_BorderText()._center()
					;
				this.setWidth(new Extent(100));
				this.add_oActionAgent(new ownActionSave());
			}
		}
		
		
		private class ownActionSave extends XX_ActionAgent {

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				ownBasicModuleContainer oThis = ownBasicModuleContainer.this;
				String e="";
				oThis.recScript.nv(GROOVYSCRIPT.script, oThis.t_script.getText(), bibMSG.MV());
				bibMSG.MV().add_MESSAGE(oThis.recScript.SAVE(true));
				if (bibMSG.get_bIsOK()) {
					oThis.t_errorMessage._txt("")._col_back_white();
					oThis.fill_ausdruck();
					bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Script ist gespeichert !")));
				}
			}
			
		}

	}
	
	
	
	
}