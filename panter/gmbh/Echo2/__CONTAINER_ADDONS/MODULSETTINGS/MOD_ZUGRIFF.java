package panter.gmbh.Echo2.__CONTAINER_ADDONS.MODULSETTINGS;

import java.util.Vector;

import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorMaskHighlight;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message_OT;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.DB.CROSS_CONNECTOR.MyE2_Grid_With_CheckBoxes;
import panter.gmbh.Echo2.components.DB.CROSS_CONNECTOR.XX_SelectAgentForCheckboxesVisible;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;



/*
 * verwaltung, welcher benutzer auf welche buttons zugriff hat
 */
public class MOD_ZUGRIFF extends MyE2_Column
{
	
	private String 						cMODULKENNER = null; 
	
	private MyDBToolBox					oDB = bibALL.get_myDBToolBox();

	private SelectUser					oSelectUsers = null;
	private MyE2_Grid_With_CheckBoxes	oGridForButtons = null;
	private MyE2_Grid_With_CheckBoxes 	oGridForUsers	= null;
	private String[][]					cButtons = null;;    // enthaelt die vermerkten buttons dieses moduls
	private String[][]					cUsers = null;       // enthalt usernamen + ids aller mandanten-user


	private ownCheckBoxToggleInaktivUsers  oSelectAktivInaktivUsers = new ownCheckBoxToggleInaktivUsers();

	
	protected void finalize()
	{
		bibALL.destroy_myDBToolBox(oDB);
	}
	

	
	public MOD_ZUGRIFF(	String  cModuleKenner ) throws myException
	{
		super();
		
		this.cMODULKENNER = cModuleKenner;
		if (bibALL.isEmpty(this.cMODULKENNER))
			throw new myException("MOD_REPORTS:Constuctor: Modul has no MODUL_KENNER !!!");
			
		
		
		try
		{
			
			this.cButtons = oDB.EinzelAbfrageInArray("SELECT BUTTONKENNER,ID_BUTTON FROM "+bibE2.cTO()+
																				".JD_BUTTON WHERE MODULKENNER="+bibALL.MakeSql(MOD_ZUGRIFF.this.cMODULKENNER)+" ORDER BY BUTTONKENNER");
			
			//this.cUsers =  oDB.EinzelAbfrageInArray("SELECT   NVL(VORNAME,'')||' '||NVL(NAME1,NVL(NAME,'-'))||' ('|| NVL(KUERZEL,'-')||')',ID_USER, NVL(AKTIV,'N') FROM "+bibE2.cTO()+
			// 							".JD_USER WHERE ID_MANDANT="+bibALL.get_ID_MANDANT()+" ORDER BY NAME");

			//sortierung und darstellung an sonstige dropdowns anpassen
			this.cUsers =  oDB.EinzelAbfrageInArray("SELECT   NVL(NAME1,NVL(NAME,'-'))||', '||NVL(VORNAME,'')||' ('|| NVL(KUERZEL,'-')||')',ID_USER, NVL(AKTIV,'N') FROM "+bibE2.cTO()+
			 							".JD_USER WHERE ID_MANDANT="+bibALL.get_ID_MANDANT()+" ORDER BY 1");

			
			
			/*
			 * jetzt die check-box-vectoren und ihre grids bauen
			 */
			Vector<MyE2_CheckBox> vCheckBoxesUsers = new Vector<MyE2_CheckBox>();
			
			for (int i=0;i<this.cUsers.length;i++)
			{
				MyE2_CheckBox oCB = new MyE2_CheckBox(new MyE2_String(this.cUsers[i][0]+(this.cUsers[i][2].equals("N")?" *":""),false));
				oCB.setFont(new E2_FontPlain(-2));
				oCB.EXT().set_C_MERKMAL(this.cUsers[i][1]);
				oCB.EXT().set_C_MERKMAL2(this.cUsers[i][2]);
				oCB.setSelected(false);
				oCB.add_oActionAgent(new ownActionAgentCheckBoxUsers());
				vCheckBoxesUsers.add(oCB);
			}
			this.oGridForUsers = new MyE2_Grid_With_CheckBoxes(4);
			this.oGridForUsers.set_vCheckBoxes(vCheckBoxesUsers);
			this.oGridForUsers.add_AddOnComponentsInFront(this.oSelectAktivInaktivUsers);
			this.oGridForUsers.set_oAgentToSelektAnzeige(new SelectAgent());
			this.oGridForUsers.build_selectorGrid();
			
			
			
			// modul-buttons
			Vector<MyE2_CheckBox> vCheckBoxesModulButtons = new Vector<MyE2_CheckBox>();
			for (int i=0;i<this.cButtons.length;i++)
			{
				MyE2_CheckBox oCB = new MyE2_CheckBox(new MyE2_String(this.cButtons[i][0],false));
				oCB.setFont(new E2_FontPlain(-2));
				oCB.EXT().set_C_MERKMAL(this.cButtons[i][1]);
				oCB.setSelected(false);
				oCB.EXT().set_C_MERKMAL2("N");
				vCheckBoxesModulButtons.add(oCB);
			}
			this.oGridForButtons = new MyE2_Grid_With_CheckBoxes(3);
			this.oGridForButtons.set_vCheckBoxes(vCheckBoxesModulButtons);
			this.oGridForButtons.build_selectorGrid();
			
			
			this.oSelectUsers = new SelectUser();
			
			
			MyE2_String cHelp = new MyE2_String("Erlaubte Buttons des Gültigkeitsbereichs: ");
			cHelp.addUnTranslated(this.cMODULKENNER);
			
			MyE2_Button buttonSave = new MyE2_Button(new MyE2_String("Speichern"));
			buttonSave.add_oActionAgent(new ownActionAgentSaveMask());
			buttonSave.setBackground(new E2_ColorHelpBackground());
			buttonSave.setFont(new E2_FontBold());

			MyE2_Button buttonToggle = new MyE2_Button(new MyE2_String("Einzeluser/Gruppenmodus"));
			buttonToggle.add_oActionAgent(new ownActionAgentToggle());
			buttonToggle.setBackground(new E2_ColorHelpBackground());
			buttonToggle.setFont(new E2_FontBold());
			
			this.add(new E2_ComponentGroupHorizontal(0,new MyE2_Label(cHelp), new Insets(0,2,25,2)), new Insets(5,5,25,10));
			this.add(new E2_ComponentGroupHorizontal(0,new MyE2_Label(new MyE2_String("Benutzer:  ")),this.oSelectUsers,this.oGridForUsers, null), new Insets(5,5,5,10));
			this.add(new E2_ComponentGroupHorizontal(0,new MyE2_Label(new MyE2_String("Berechtigt: ")),this.oGridForButtons, null), new Insets(5,5,5,10));
			this.add(new E2_ComponentGroupHorizontal(0,buttonSave,buttonToggle,new Insets(0,2,25,2)), new Insets(5,5,5,10));
			
			/*
			 * starteinstellung: selektor der einzelbenutzer ist visible
			 */
			this.oGridForUsers.setVisible(false);
			
		}
		catch (myException ex)
		{
			bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
		}

		
	}


	private class ownCheckBoxToggleInaktivUsers extends MyE2_CheckBox
	{
	
		public ownCheckBoxToggleInaktivUsers() throws myException
		{
			super(new MyE2_String("Inaktive"));
			this.add_oActionAgent(new ownActionToogleInaktivUsers());
			GridLayoutData oGL = new GridLayoutData();
			oGL.setBackground(new E2_ColorMaskHighlight());
			this.setLayoutData(oGL);
		}

		//schalter fuer das ein/ausschalten von inaktiven benutzern
		private class ownActionToogleInaktivUsers extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				MOD_ZUGRIFF.this.oGridForUsers.build_selectorGrid();
			}
		}

	} 

	
	private class SelectAgent extends XX_SelectAgentForCheckboxesVisible
	{
		@Override
		public boolean get_Visible(MyE2_CheckBox ocb)
		{
			if (MOD_ZUGRIFF.this.oSelectAktivInaktivUsers.isSelected())
			{
				return true;
			}
			return ocb.EXT().get_C_MERKMAL2().equals("Y");
		}

		@Override
		public Object get_Copy(Object objHelp) throws myExceptionCopy
		{
			return null;
		}
	
	}
	
	
	
	/*
	 * schaltet von der single-user-betriebsart in die mehrfach-user-betriebsart um
	 */
	private class ownActionAgentToggle extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo)
		{
			if (MOD_ZUGRIFF.this.oSelectUsers.isVisible())
			{
				MOD_ZUGRIFF.this.oSelectUsers.setVisible(false);
				MOD_ZUGRIFF.this.oGridForUsers.setVisible(true);
			}
			else
			{
				MOD_ZUGRIFF.this.oSelectUsers.setVisible(true);
				MOD_ZUGRIFF.this.oGridForUsers.setVisible(false);
			}
			
			/*
			 * alle selektionen weg 
			 */
			for (int i=0;i<MOD_ZUGRIFF.this.oGridForButtons.get_vCheckBoxes().size();i++)
			{
				MyE2_CheckBox oCB = (MyE2_CheckBox)MOD_ZUGRIFF.this.oGridForButtons.get_vCheckBoxes().get(i);
				oCB.EXT().set_C_MERKMAL2("N");
				oCB.setSelected(false);
			}

			/*
			 * alle selektionen weg 
			 */
			for (int i=0;i<MOD_ZUGRIFF.this.oGridForUsers.get_vCheckBoxes().size();i++)
			{
				MOD_ZUGRIFF.this.oGridForUsers.get_vCheckBoxes().get(i).setSelected(false);
			}
			
			MOD_ZUGRIFF.this.oSelectUsers.setSelectedIndex(0);
			
		}
		
	}
	
	
	
	/*
	 * select-field, bestimmt die benutzer aus dem aktuellen mandanten,
	 * diese koennen selektiert werden, und die zugriffe eingetragen werden
	 */ 
	private class SelectUser extends MyE2_SelectField
	{
		public SelectUser() throws myException
		{
			super();
			
			String[][] cUsersMitLeer = new String[MOD_ZUGRIFF.this.cUsers.length+1][2];
			
			cUsersMitLeer[0][0]="-";cUsersMitLeer[0][1]="";
			for (int i=0;i<cUsers.length;i++)
			{
				cUsersMitLeer[i+1][0]=cUsers[i][0]+(cUsers[i][2].equals("N")?" *":"");
				cUsersMitLeer[i+1][1]=cUsers[i][1];
			}
			
			this.set_ListenInhalt(cUsersMitLeer,false);
			this.setSelectedIndex(0);   // standard ist leer als vorgabeselektion
			this.add_oActionAgent(new ownActionAgentForSelect());
			this.EXT().set_O_PLACE_FOR_EVERYTHING(cUsersMitLeer);
		}
		
		public String cGetActiveUserID()
		{
			String[][] cUsersMitLeer = (String[][])this.EXT().get_O_PLACE_FOR_EVERYTHING();
			int iActual = this.getSelectedIndex();
			String cID_User = cUsersMitLeer[iActual][1];
			return cID_User;
		}
		
		
	}

	
	

	
	
	
	
	// fuellt das inhaltsgrid mit den fuer den user aktivierten checkboxen
	private class ownActionAgentForSelect extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo)
		{
			// rauskriegen welcher user gerade angeklickt wurde
			SelectUser oSelect = (SelectUser)bibE2.get_LAST_ACTIONEVENT().getSource();
			String cID_User = oSelect.cGetActiveUserID();
			
			/*
			 * alle selektionen weg 
			 */
			for (int i=0;i<MOD_ZUGRIFF.this.oGridForButtons.get_vCheckBoxes().size();i++)
			{
				MyE2_CheckBox oCB = (MyE2_CheckBox)MOD_ZUGRIFF.this.oGridForButtons.get_vCheckBoxes().get(i);
				oCB.EXT().set_C_MERKMAL2("N");
				oCB.setSelected(false);
			}
			
			if (cID_User.equals(""))
				return;

			/*
			 * falls user gewaehlt wurde, dann die entsprechenden berechtigungen markieren
			 */
			String[][] cZuteilung = MOD_ZUGRIFF.this.oDB.EinzelAbfrageInArray("SELECT ID_BUTTON FROM "+bibE2.cTO()+
																			".JD_BUTTON_USER WHERE ID_USER="+cID_User);
			
			// suchvector mit den id_buttons, die dieser user hat
			Vector<String> cActivButtons = new Vector<String>();
			for (int i=0;i<cZuteilung.length;i++)
				cActivButtons.add(cZuteilung[i][0]);
	
			for (int i=0;i<MOD_ZUGRIFF.this.oGridForButtons.get_vCheckBoxes().size();i++)
			{
				MyE2_CheckBox oCB = (MyE2_CheckBox)MOD_ZUGRIFF.this.oGridForButtons.get_vCheckBoxes().get(i);
				if (cActivButtons.contains(oCB.EXT().get_C_MERKMAL()))
				{
					oCB.EXT().set_C_MERKMAL2("Y");
					oCB.setSelected(true);
				}
			}

			
		}
		
	}
	
	
	
	
	
	/*
	 * jede checkbox aus den benutzern bekommt eine actioagent, der die berechtigungen aller angekreuzten benutzers
	 * additiv darstellt
	 */
	private class ownActionAgentCheckBoxUsers extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo)
		{
			/*
			 * zuerst alle buttons loeschen
			 */
			/*
			 * alle selektionen weg 
			 */
			for (int i=0;i<MOD_ZUGRIFF.this.oGridForButtons.get_vCheckBoxes().size();i++)
			{
				MyE2_CheckBox oCB = (MyE2_CheckBox)MOD_ZUGRIFF.this.oGridForButtons.get_vCheckBoxes().get(i);
				oCB.EXT().set_C_MERKMAL2("N");
				oCB.setSelected(false);
			}
			
			/*
			 * dann alle ids sammeln
			 */
			Vector<String> vSelectedIDs = new Vector<String>();
			/*
			 * alle selektionen weg 
			 */
			for (int i=0;i<MOD_ZUGRIFF.this.oGridForUsers.get_vCheckBoxes().size();i++)
			{
				if (MOD_ZUGRIFF.this.oGridForUsers.get_vCheckBoxes().get(i).isSelected())
				{
					vSelectedIDs.add(MOD_ZUGRIFF.this.oGridForUsers.get_vCheckBoxes().get(i).EXT().get_C_MERKMAL());
				}
			}
			

			/*
			 * alle user zuteilen
			 */
			if (vSelectedIDs.size()>0)
			{
				String cIN = "(-1)";
				try
				{
					cIN = "("+bibALL.Concatenate(vSelectedIDs,",", null)+")";
				}
				catch (myException ex) {}
				
				String[][] cZuteilung = MOD_ZUGRIFF.this.oDB.EinzelAbfrageInArray("SELECT ID_BUTTON FROM "+bibE2.cTO()+
												".JD_BUTTON_USER WHERE ID_USER IN "+cIN);
				
				// suchvector mit den id_buttons, die dieser user hat
				Vector<String> cActivButtons = new Vector<String>();
				for (int i=0;i<cZuteilung.length;i++)
					cActivButtons.add(cZuteilung[i][0]);
		
				for (int i=0;i<MOD_ZUGRIFF.this.oGridForButtons.get_vCheckBoxes().size();i++)
				{
					MyE2_CheckBox oCB = (MyE2_CheckBox)MOD_ZUGRIFF.this.oGridForButtons.get_vCheckBoxes().get(i);
					if (cActivButtons.contains(oCB.EXT().get_C_MERKMAL()))
					{
						oCB.setSelected(true);
					}
				}
			}
		}
	}
	
	
	
	
	
	
	private class ownActionAgentSaveMask extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo)
		{
			MyDBToolBox o_DB = MOD_ZUGRIFF.this.oDB;
			
			Vector<String> vSQL = new Vector<String>();
			
			if (MOD_ZUGRIFF.this.oSelectUsers.isVisible())
			{
				vSQL.addAll(this.buildSingleUserInsert());
				
				if (vSQL.size()==0)
				{
					bibMSG.add_MESSAGE(new MyE2_Info_Message("Es gibt nichts zu speichern !"));
					return;
				}
				
				int iADD = 0;
				int iDEL = 0;
				
				for (int i=0;i<vSQL.size();i++)
				{
					String cSQL = (String)vSQL.get(i);
					if (cSQL.startsWith("INSERT")) iADD++;
					if (cSQL.startsWith("DELETE")) iDEL++;
				}
				
				bibMSG.add_MESSAGE(o_DB.ExecMultiSQLVector(vSQL,true));
				if (bibMSG.get_bIsOK())
				{
					MyE2_String oMeldung1 = new MyE2_String("Gelöscht: ");oMeldung1.addUnTranslated(""+iDEL);
					MyE2_String oMeldung2 = new MyE2_String("Zugefügt: ");oMeldung2.addUnTranslated(""+iADD);
					bibMSG.add_MESSAGE(new MyE2_Info_Message_OT(oMeldung1.CTrans()+"   /   "+oMeldung2.CTrans()));
				}
			}
			else
			{
				vSQL.addAll(this.buildMultiUserInsert());
				int iADD = 0;
				
				for (int i=0;i<vSQL.size();i++)
				{
					String cSQL = (String)vSQL.get(i);
					if (cSQL.startsWith("INSERT")) iADD++;
				}
				
				bibMSG.add_MESSAGE(o_DB.ExecMultiSQLVector(vSQL,true));
				if (bibMSG.get_bIsOK())
				{
					MyE2_String oMeldung = new MyE2_String("Zugefügt: ");oMeldung.addUnTranslated(""+iADD);
					bibMSG.add_MESSAGE(new MyE2_Info_Message(oMeldung), false);
				}
			}
		}
		
		/*
		 * insertvector bei eingeblendetem Benutzer-Selektor
		 */
		private Vector<String> buildSingleUserInsert()
		{
			Vector<String> vSQL_Statements = new Vector<String>();
			
			
			Vector<MyE2_CheckBox> 		vHelp = 		MOD_ZUGRIFF.this.oGridForButtons.get_vCheckBoxes();
			String 						cActiveUserID = MOD_ZUGRIFF.this.oSelectUsers.cGetActiveUserID();
			
			
			/*
			 * pruefen, ob ueberhaupt ein mitarbeiter angeklickt war
			 */
			if (vHelp.size()==0 || bibALL.isEmpty(cActiveUserID) )
			{
				return vSQL_Statements;
			}
			else
			{
				for (int i=0;i<vHelp.size();i++)
				{
					MyE2_CheckBox oCB = (MyE2_CheckBox)vHelp.get(i);
					if (oCB.isSelected() && oCB.EXT().get_C_MERKMAL2().equals("Y"))
						continue;
					
					if (!oCB.isSelected() && oCB.EXT().get_C_MERKMAL2().equals("N"))
						continue;
					
					// zufuegen
					if (oCB.isSelected() && oCB.EXT().get_C_MERKMAL2().equals("N"))
					{
						vSQL_Statements.add("INSERT INTO "+bibE2.cTO()+".JD_BUTTON_USER " +
												"(ID_BUTTON_USER,ID_BUTTON,ID_USER) " +
												"VALUES" +
												"(SEQ_BUTTON_USER.NEXTVAL,"+oCB.EXT().get_C_MERKMAL()+","+cActiveUserID+")");
					}	
					
					// loeschen
					if (!oCB.isSelected() && oCB.EXT().get_C_MERKMAL2().equals("Y"))
					{
						vSQL_Statements.add("DELETE FROM "+bibE2.cTO()+".JD_BUTTON_USER " +
												" WHERE ID_BUTTON="+oCB.EXT().get_C_MERKMAL()+" AND ID_USER=" +cActiveUserID);					
					}	
				}
			}
			return vSQL_Statements;
			
		}
		

		/*
		 * insertvector bei eingeblendetem Benutzer-Grid
		 */
		private Vector<String> buildMultiUserInsert()
		{
			Vector<String> vSQL_Statements = new Vector<String>();
			
			Vector<MyE2_CheckBox> 		vCB_ModulButtons = 		MOD_ZUGRIFF.this.oGridForButtons.get_vCheckBoxes();
			Vector<MyE2_CheckBox> 		vCB_Users = 			MOD_ZUGRIFF.this.oGridForUsers.get_vCheckBoxes();

			
			for (int k=0;k<vCB_Users.size();k++)
			{
				MyE2_CheckBox oCB_User = (MyE2_CheckBox)vCB_Users.get(k);
				
				/*
				 * nur die benutzer behandeln, die selektiert sind,
				 * es wird jeder vorhandene button dieser gruppe fuer den user geloescht,
				 * jeder ausgewaehlte wird neu gesetzt
				 */
				if (oCB_User.isSelected())
				{
					String cActiveUserID = oCB_User.EXT().get_C_MERKMAL();
					
					for (int i=0;i<vCB_ModulButtons.size();i++)
					{

						MyE2_CheckBox oCB = (MyE2_CheckBox)vCB_ModulButtons.get(i);
						vSQL_Statements.add("DELETE FROM "+bibE2.cTO()+".JD_BUTTON_USER " +
												" WHERE ID_BUTTON="+oCB.EXT().get_C_MERKMAL()+" AND ID_USER=" +cActiveUserID);
						
						if (oCB.isSelected())
							vSQL_Statements.add("INSERT INTO "+bibE2.cTO()+".JD_BUTTON_USER " +
													"(ID_BUTTON_USER,ID_BUTTON,ID_USER) " +
													"VALUES" +
													"(SEQ_BUTTON_USER.NEXTVAL,"+oCB.EXT().get_C_MERKMAL()+","+cActiveUserID+")");
						
					}
				}
			}
			return vSQL_Statements;
		}
	}
	
}
