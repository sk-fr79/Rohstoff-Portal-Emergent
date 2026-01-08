package rohstoff.xmlpopups;

import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.xmlDefTools.XX_ModuleContainerListEditPopup;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message_OT;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGrid;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.exceptions.myException;

public class PopUpAllowedUsers extends XX_ModuleContainerListEditPopup
{
	private MyString 					cError = null;
	private HashMap<String,String> 		oHashZuordnung = null;
	private MyE2_Button 				oButtonOK = null;
	private MyE2_Button 				oButtonCancel = null;
	private Vector<MyE2_CheckBox>  		vCheckBoxes = null;
	private MyDBToolBox 				oDB = null;
	private String 						cServletID = null;

	
	protected void finalize()
	{
		bibALL.destroy_myDBToolBox(oDB);
	}
	
	public MyString get_Check_CanBeShown()
	{
		return cError;
	}

	
	
	public void build_Content() throws myException
	{
		Vector<String> vIds = this.get_oNavigationListFromListModule().get_vSelectedIDs_Unformated();
		
		if (vIds.size()!=1)
		{
			this.cError = new MyE2_String("Bitte genau einen Servlet-Eintrag zur Benutzerzuordnung auswählen !!");
		}
		else
		{
			this.cServletID = (String) vIds.get(0);
			this.get_oContainer().add(new MyE2_Label(new MyE2_String("Bitte kreuzen Sie die Zuordnung an: Welche Benutzer bekommen das gewählte Servlet")), new Insets(5,5,5,10));
			
			this.oDB = bibALL.get_myDBToolBox();
			
			String cSQLQuery = "SELECT " +
								" JD_ZUGRIFF.ID_USER,JD_ZUGRIFF.ID_SERVLETS " +
								" FROM " +
								bibE2.cTO()+".JD_ZUGRIFF JD_ZUGRIFF " +
								" WHERE " +
								" JD_ZUGRIFF.ID_SERVLETS="+this.cServletID;
			
			String cSQLQuery2 = "SELECT " +
								" JD_USER.ID_USER,  NVL(NAME,'-')||' ('||  NVL(KUERZEL,'-')||')', ID_MANDANT " +
								" FROM " +
								bibE2.cTO()+".JD_USER JD_USER ORDER BY NAME ";
			
			String cSQLQuery3 = "SELECT "+
								" ID_MANDANT,NAME1 "+
								" FROM " +
								bibE2.cTO()+".JD_MANDANT ORDER BY ID_MANDANT ";
			
			String[][] cZuordnung =	oDB.EinzelAbfrageInArray(cSQLQuery);
			String[][] cUser	 = 	oDB.EinzelAbfrageInArray(cSQLQuery2);
			String[][] cMandanten= 	oDB.EinzelAbfrageInArray(cSQLQuery3);
			
			if (cZuordnung == null  || cUser == null)
			{
				this.cError = new MyE2_String("Fehler beim Aufbau der Zuordnungsmatrix !");
			}
			else
			{
				this.oHashZuordnung = 	new HashMap<String,String>();
				this.vCheckBoxes = 		new Vector<MyE2_CheckBox>();
				
				for (int i=0;i<cZuordnung.length;i++)
					oHashZuordnung.put(cZuordnung[i][0],cZuordnung[i][1]);

				for (int i=0;i<cUser.length;i++)
				{
					MyE2_CheckBox oCB = new MyE2_CheckBox(new MyE2_String(cUser[i][1],false)); 
					oCB.EXT().set_C_MERKMAL(cUser[i][0]); 					// die user-id kommt ins C_MERKMAL
					oCB.EXT().set_O_PLACE_FOR_EVERYTHING(cUser[i][2]);  	// mandanten_id in set_O_PLACE_FOR_EVERYTHING
					if (oHashZuordnung.containsKey(cUser[i][0]))
						oCB.setSelected(true);
					
					this.vCheckBoxes.add(oCB);
					
				}
				
				/*
				 * Jeder mandant kommt auf einen tabbed-pane
				 */
				Vector<E2_ComponentGrid> vGrids = new Vector<E2_ComponentGrid>();              // ein grid fuer jeden mandanten
				MyE2_TabbedPane oTabbedPane = new MyE2_TabbedPane(null);
				for (int i=0;i<cMandanten.length;i++)
				{
					E2_ComponentGrid oGrid = new E2_ComponentGrid(5);
					vGrids.add(oGrid);
					oGrid.EXT().set_C_MERKMAL(cMandanten[i][0]);   					// id_mandant in c_merkmal
					oTabbedPane.add_Tabb(new MyE2_String(cMandanten[i][1],false),oGrid);		
				}
				
				for (int i=0;i<this.vCheckBoxes.size();i++)
				{
					MyE2_CheckBox oCB = (MyE2_CheckBox)this.vCheckBoxes.get(i);
					String cID_Mandant = (String)oCB.EXT().get_O_PLACE_FOR_EVERYTHING();
					for (int k=0;k<vGrids.size();k++)
					{
						E2_ComponentGrid oGrid = (E2_ComponentGrid)vGrids.get(k);
						if (oGrid.EXT().get_C_MERKMAL().equals(cID_Mandant))
						{
							oGrid.add((MyE2_CheckBox)this.vCheckBoxes.get(i));							
							break;
						}
					}
				}
				
				this.get_oContainer().add(oTabbedPane, new Insets(5,5,5,10));
				
				
				this.oButtonOK = new MyE2_Button(new MyE2_String("Speichern"));
				this.oButtonCancel = new MyE2_Button(new MyE2_String("Abbrechen"));
				
				this.oButtonOK.add_oActionAgent(new ActionAgentSAVE());
				this.oButtonCancel.add_oActionAgent(new ActionAgentCANCEL());
				
				this.get_oContainer().add(new E2_ComponentGroupHorizontal(1,this.oButtonOK,this.oButtonCancel, null), new Insets(5,5,5,10));
				
			}
		}
	}

	
	private class ActionAgentSAVE extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			Vector<String> vSQL = new Vector<String>();
			vSQL.add("DELETE FROM "+bibE2.cTO()+".JD_ZUGRIFF WHERE ID_SERVLETS="+PopUpAllowedUsers.this.cServletID);
			int iAnzahl=0;
			for (int i=0;i<PopUpAllowedUsers.this.vCheckBoxes.size();i++)
			{
				MyE2_CheckBox oCB = (MyE2_CheckBox)PopUpAllowedUsers.this.vCheckBoxes.get(i);
				
				if (oCB.isSelected())
				{
					vSQL.add("INSERT INTO JD_ZUGRIFF(ID_ZUGRIFF,ID_USER,ID_SERVLETS) " +
							" VALUES(SEQ_ZUGRIFF.NEXTVAL,"+oCB.EXT().get_C_MERKMAL()+","+PopUpAllowedUsers.this.cServletID+")");
					iAnzahl++;
				}
			}
			
			if (PopUpAllowedUsers.this.oDB.ExecMultiSQLVector(vSQL,true).get_bIsOK())
			{
				bibMSG.add_MESSAGE(new MyE2_Info_Message_OT(new MyE2_String("Anzahl Benutzer für diesen Menüpunkt: ").CTrans()+" "+iAnzahl));
				PopUpAllowedUsers.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
			}
			else
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Schreiben der Zuordnung !"));
			}
		}
		
	}
	
	private class ActionAgentCANCEL extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			PopUpAllowedUsers.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
		}
	}


	
	
	

}
