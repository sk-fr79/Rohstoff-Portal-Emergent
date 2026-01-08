package panter.gmbh.Echo2.__BASIC_MODULS.BENUTZER_VERWALTUNG;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTH_ONLY_ADMIN;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal_NG;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.DB.CROSS_CONNECTOR.MyE2_Grid_With_CheckBoxes;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.DB_ENUMS.BUTTON;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_BUTTON;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_BUTTON_USER;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_BUTTON_USER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BUTTON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USER;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.basics4project.validation.ENUM_VALID_THEME;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class USER__LIST_BT_DEFINIERE_RECHTE_AN_GLOBAL_BUTTONS extends MyE2_Button 
{

	private E2_NavigationList   oNaviList = null;
	
	private String   	 		modulKenner = E2_MODULNAME_ENUM.MODUL.MODUL_KENNER_PROGRAMM_WIDE.get_callKey();
	
	
	public USER__LIST_BT_DEFINIERE_RECHTE_AN_GLOBAL_BUTTONS(E2_NavigationList  NaviList) 
	{
		super(new MyE2_String("Globale Button-Rechte"));
		
		this.oNaviList = NaviList;
		this.setToolTipText(new MyE2_String("Definiert die Berechtigung eines Benutzers an Buttons, die global definiert sind (nicht an Modulen)").CTrans());
		
		this.add_oActionAgent(new ownActionAgent());
		this.add_GlobalValidator(new E2_ButtonAUTH_ONLY_ADMIN());  //2014-02-17: neu: Benutzung fuer alle USER moeglich, aber nur fuer den eigenen Account

	}

	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			USER__LIST_BT_DEFINIERE_RECHTE_AN_GLOBAL_BUTTONS oThis = USER__LIST_BT_DEFINIERE_RECHTE_AN_GLOBAL_BUTTONS.this;
			
			Vector<String> vIDs = oThis.oNaviList.get_vSelectedIDs_Unformated();
			if (vIDs.size()!=1)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte genaue EINEN Benutzer auswählen !!")));
				return;
			}
			
			//jetzt die (evtl. fehlenden Buttonkenner) reinschreiben.
			MyE2_MessageVector mv = new MyE2_MessageVector();
			ENUM_VALIDATION.writeMissungButtons2DB(mv);
			bibMSG.add_MESSAGE(mv);
			
			if (bibMSG.get_bIsOK()) {
				new ownBasicModuleContainer(vIDs.get(0));
			}
			
			
		}
	}
	
	
	private class ownBasicModuleContainer extends E2_BasicModuleContainer	{
		
		private ButtonKennerGrid oGrid4Buttons = null; 
		
		private E2_Grid 			gridBase = new E2_Grid()._w100()._s(1);
			
		//definition der Bereiche fuer global gefuehrte button-validierer
		private RB_selField			selType = new RB_selField()._aaa(new selectActionAgent());    
		
		private String 				cID_USER = null;
		
		public ownBasicModuleContainer(String pID_USER) throws myException  {
			super();
			
			this.cID_USER = pID_USER;
			
			this.add(this.gridBase, E2_INSETS.I(5,5,5,5));
			
			String[][]  themen = ENUM_VALID_THEME.getDDArraySorted(false);
			String[][]  front = {{"Diverse",""}};
			bibARR.add_array_inFront(themen,  front);
			
			this.oGrid4Buttons=new ButtonKennerGrid(modulKenner,"")
													._set_ID_USER(this.cID_USER);

			this.selType._populate(bibARR.add_array_inFront(themen,  front));
			this.selType.setSelectedIndex(0);
			
			this.gridBase ._a(this.selType, new RB_gld()._ins(2, 2, 2, 2))
						  ._a(this.oGrid4Buttons, new RB_gld()._ins(2,2,2,2));

			
			MyE2_Button  oButtonSave = new MyE2_Button(new MyE2_String("Speichern"));

			oButtonSave.add_oActionAgent(new XX_ActionAgent() 
			{
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException 
				{
					ownBasicModuleContainer oThis = ownBasicModuleContainer.this;
					
					Vector<String> vSQL_Save = oThis.oGrid4Buttons.get_SQL_STATEMENTS();
					
					if (vSQL_Save.size()>0)
					{
						bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL_Save, true));
						if (bibMSG.get_bIsOK())
						{
							bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Zahl der Veränderungen: ",true,""+vSQL_Save.size(),false)));
							oThis.CLOSE_AND_DESTROY_POPUPWINDOW(true);
						}
					}
					else
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es gibt nichts zu speichern !!")));
						oThis.CLOSE_AND_DESTROY_POPUPWINDOW(true);
					}
					
				}
			});			
			
			
			this.add(new E2_ComponentGroupHorizontal_NG(oButtonSave, E2_INSETS.I_0_0_0_0),new Insets(5,10,5,0));
			
			RECORD_USER  recUSER = new RECORD_USER(cID_USER);
			
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(600), new Extent(600), 
					new MyE2_String("Definiere Globale Buttons für ",true,recUSER.get___KETTE(bibALL.get_Vector("VORNAME","NAME1")),false));
			
			
		}
		
		
		private class selectActionAgent extends XX_ActionAgent {

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				
				ownBasicModuleContainer oThis = ownBasicModuleContainer.this;
				
				RB_selField  sel = ownBasicModuleContainer.this.selType;
				
				String code = sel.getActualDbVal();
				
				oThis.gridBase.removeAll();
				
				oThis.oGrid4Buttons._clearGrid()._fillGrid(modulKenner,code)._set_ID_USER(oThis.cID_USER);

				oThis.gridBase ._a(sel, new RB_gld()._ins(2, 2, 2, 2))
								._a(oThis.oGrid4Buttons, new RB_gld()._ins(2,2,2,2));
				
			}
			
		}

	}
	
	
	
	public class ButtonKennerGrid extends MyE2_Grid_With_CheckBoxes {
		private String MODULKENNER = null;
		private String ID_USER = null;
		private Vector<MyE2_CheckBox> vCheckBoxesModulButtons = new Vector<MyE2_CheckBox>();
		
		public ButtonKennerGrid(String cModulKenner, String range) throws myException {
			super(4,MyE2_Grid.STYLE_GRID_DDARK_BORDER_W100());
			this._fillGrid(cModulKenner, range);
		}
		
		
		public ButtonKennerGrid _clearGrid() throws myException {
			this.removeAll();
			this.vCheckBoxesModulButtons.clear();
			return this;
		}
		
		
		public ButtonKennerGrid _fillGrid(String cModulKenner, String range) throws myException {
			this.MODULKENNER = cModulKenner;
			RECLIST_BUTTON   relistButtons = null;
			
			if (S.isEmpty(range)) {
				relistButtons = new RECLIST_BUTTON("SELECT * FROM "+bibE2.cTO()+".JD_BUTTON WHERE MODULKENNER="+bibALL.MakeSql(this.MODULKENNER)+" AND "+BUTTON.buttonkenner.fn()+" NOT LIKE '%#%' ORDER BY BUTTONKENNER");
			} else {
				relistButtons = new RECLIST_BUTTON("SELECT * FROM "+bibE2.cTO()+".JD_BUTTON WHERE MODULKENNER="+bibALL.MakeSql(this.MODULKENNER)+" AND "+BUTTON.buttonkenner.fn()+" LIKE '"+range+"#%' ORDER BY BUTTONKENNER");
			}
			
			
			for (int i=0;i<relistButtons.get_vKeyValues().size();i++)
			{
				RECORD_BUTTON  recButton = relistButtons.get(i);
				
				String readableText = ENUM_VALIDATION.getBeschreibung(recButton.get_BUTTONKENNER_cUF());
				
				MyE2_CheckBox oCB = new MyE2_CheckBox(new MyE2_String(readableText,false));
				oCB.setFont(new E2_FontPlain(-2));
				oCB.EXT().set_C_MERKMAL(recButton.get_ID_BUTTON_cUF());
				oCB.setSelected(false);
				
				oCB.EXT().set_O_PLACE_FOR_EVERYTHING(recButton);
				
				this.vCheckBoxesModulButtons.add(oCB);
			}
			
			this.set_vCheckBoxes(this.vCheckBoxesModulButtons);
			this.build_selectorGrid();
			
			return this;
		}
		
		
		
		public ButtonKennerGrid _set_ID_USER(String cID_USER) throws myException	{
			this.ID_USER = cID_USER;
			
			for (int i=0;i<this.vCheckBoxesModulButtons.size();i++)	{
				RECORD_BUTTON  recButton = (RECORD_BUTTON)this.vCheckBoxesModulButtons.get(i).EXT().get_O_PLACE_FOR_EVERYTHING();
				
				RECLIST_BUTTON_USER  recList = recButton.get_DOWN_RECORD_LIST_BUTTON_USER_id_button("JD_BUTTON_USER.ID_USER="+this.ID_USER,null,true); 
				if (recList.get_vKeyValues().size()>1)	{
					throw new myException("Error querying USER-BUTTON setting ...");
				}
				
				if (recList.get_vKeyValues().size()==1)	{
					this.vCheckBoxesModulButtons.get(i).EXT().set_C_MERKMAL2("Y");
					this.vCheckBoxesModulButtons.get(i).setSelected(true);
				} else {
					this.vCheckBoxesModulButtons.get(i).EXT().set_C_MERKMAL2("N");
					this.vCheckBoxesModulButtons.get(i).setSelected(false);
				}
			}
			
			return this;
		}
		
		
		
		public Vector<String> get_SQL_STATEMENTS() throws myException {
			Vector<String> vSQL = new Vector<String>();
			
			if (S.isEmpty(this.ID_USER)) {
				throw new myException("Error: First define USER !!!");
			}
			
			for (int i=0;i<this.vCheckBoxesModulButtons.size();i++) {
				RECORD_BUTTON  recButton = (RECORD_BUTTON)this.vCheckBoxesModulButtons.get(i).EXT().get_O_PLACE_FOR_EVERYTHING();
				MyE2_CheckBox  oCB       = this.vCheckBoxesModulButtons.get(i);

				if (oCB.isSelected() && oCB.EXT().get_C_MERKMAL2().equals("N")) {  //vorher nein, jetzt ja
					RECORDNEW_BUTTON_USER  recNew = new RECORDNEW_BUTTON_USER();
					recNew.set_NEW_VALUE_ID_BUTTON(recButton.get_ID_BUTTON_cUF());
					recNew.set_NEW_VALUE_ID_USER(this.ID_USER);
					
					vSQL.add(recNew.get_InsertSQLStatementWith_Id_Field(false,true));
				} else if ((!oCB.isSelected()) && oCB.EXT().get_C_MERKMAL2().equals("Y")) 	{  //vorher ja, jetzt nein
					RECLIST_BUTTON_USER  recList = recButton.get_DOWN_RECORD_LIST_BUTTON_USER_id_button("JD_BUTTON_USER.ID_USER="+this.ID_USER,null,true); 
					if (recList.get_vKeyValues().size()!=1)	{
						throw new myException("Error querying USER-BUTTON setting ...");
					}
					vSQL.add(recList.get(0).get_DELETE_STATEMENT());
				}
			}	
			return vSQL;
		}
		
		
	}
	
	
}
