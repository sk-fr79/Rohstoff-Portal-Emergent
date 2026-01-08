package panter.gmbh.Echo2.__BASIC_MODULS.MODUL_VERWALTUNG;

import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message_OT;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGrid;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class MODUL__LIST_BT_SelectUsers4Module extends MyE2_Button
{

	private E2_NavigationList  			oNaviList = null;
	private Vector<MyE2_CheckBox>  		vCheckBoxes = null;
	private String 						cServletID = null;
	private HashMap<String,String> 		oHashZuordnung = null;

	public MODUL__LIST_BT_SelectUsers4Module(E2_NavigationList NaviList)
	{
		super(E2_ResourceIcon.get_RI("person.png"), true);
		this.oNaviList = NaviList;
		this.setToolTipText(new MyE2_String("Benutzer auswählen, die das Modul in ihr Menü bekommen ...").CTrans());
		
		this.add_oActionAgent(new XX_ActionAgent()
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				new ownPopup();
			}
			
		});
	}



	private class ownPopup extends E2_BasicModuleContainer
	{
		private MyE2_Button 				oButtonOK = null;
		private MyE2_Button 				oButtonCancel = null;

		private int iNumCols				= 4;
		
		private HashMap<String, Integer> 	hmNumUsers      = new HashMap<String, Integer>();
		
		public ownPopup() throws myException
		{
			super();
			
			MODUL__LIST_BT_SelectUsers4Module oThis = MODUL__LIST_BT_SelectUsers4Module.this;
			
			Vector<String> vIds = oThis.oNaviList.get_vSelectedIDs_Unformated();
			
			if (vIds.size()!=1)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte genau einen Servlet-Eintrag zur Benutzerzuordnung auswählen !!"));
				return;
			}
			else
			{
				oThis.cServletID = (String) vIds.get(0);
				this.add(new MyE2_Label(new MyE2_String("Bitte kreuzen Sie die Zuordnung an: Welche Benutzer bekommen das gewählte Servlet")), new Insets(5,5,5,10));
				
				String cSQLQuery = "SELECT " +
									" JD_ZUGRIFF.ID_USER,JD_ZUGRIFF.ID_SERVLETS " +
									" FROM " +
									bibE2.cTO()+".JD_ZUGRIFF JD_ZUGRIFF " +
									" WHERE " +
									" JD_ZUGRIFF.ID_SERVLETS="+oThis.cServletID;
				
				String cSQLQuery2 = "SELECT " +
//									" JD_USER.ID_USER,  NVL(NAME,'-')||' ('||  NVL(KUERZEL,'-')||')', ID_MANDANT " +
									" JD_USER.ID_USER,  NVL(NAME1,'-')|| ' ' || NVL(VORNAME,'-') || ' ('||  NVL(KUERZEL,'?')||')', ID_MANDANT " +
									" FROM " +
//									bibE2.cTO()+".JD_USER JD_USER ORDER BY NAME ";
									bibE2.cTO()+".JD_USER JD_USER ORDER BY NAME1, VORNAME ";
				
				String cSQLQuery3 = "SELECT "+
									" ID_MANDANT,NAME1 "+
									" FROM " +
									bibE2.cTO()+".JD_MANDANT ORDER BY ID_MANDANT ";
				
				String[][] cZuordnung =	bibDB.EinzelAbfrageInArray(cSQLQuery);
				String[][] cUser	 = 	bibDB.EinzelAbfrageInArray(cSQLQuery2);
				String[][] cMandanten= 	bibDB.EinzelAbfrageInArray(cSQLQuery3);
				
				if (cZuordnung == null  || cUser == null)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Aufbau der Zuordnungsmatrix !"));
					return;
				}
				else
				{
					oThis.oHashZuordnung = 	new HashMap<String,String>();
					oThis.vCheckBoxes = 		new Vector<MyE2_CheckBox>();
					
					for (int i=0;i<cZuordnung.length;i++)
						oHashZuordnung.put(cZuordnung[i][0],cZuordnung[i][1]);

					for (int i=0;i<cUser.length;i++)
					{
						MyE2_CheckBox oCB = new MyE2_CheckBox(new MyE2_String(cUser[i][1],false)); 
						oCB.EXT().set_C_MERKMAL(cUser[i][0]); 					// die user-id kommt ins C_MERKMAL
						oCB.EXT().set_O_PLACE_FOR_EVERYTHING(cUser[i][2]);  	// mandanten_id in set_O_PLACE_FOR_EVERYTHING
						if (oHashZuordnung.containsKey(cUser[i][0]))
							oCB.setSelected(true);
						
						oThis.vCheckBoxes.add(oCB);

						
						//2012-10-25 **bug: die hashmap-users muss fuer jeden mandanten einen null-eintrag haben 
						if (!hmNumUsers.containsKey(cUser[i][2]))           //nachsehen, ob im benutzer-zaehler bereits ein schluessel fuer den mandanten existiert
						{
							hmNumUsers.put(cUser[i][2],new Integer(0));    //ein eintrag 0 als startwert
						}

						
					}
					
					
					
					
					// die Anzahl der Checkboxen für einen Mandanten ermitteln
					// indem man alle User durchgeht und sie den Mandanten zuordnet und diese zählt
					// ziemlich umständlich aber geht so
					for (int i=0;i<cUser.length;i++)
					{
						MyE2_CheckBox o = (MyE2_CheckBox)oThis.vCheckBoxes.get(i);
						String cID_Mandant = (String)o.EXT().get_O_PLACE_FOR_EVERYTHING();
						Integer iNum = 0;
						
						if(hmNumUsers.containsKey(cID_Mandant))
						{
							iNum= hmNumUsers.get(cID_Mandant);
							iNum++;
							hmNumUsers.put(cID_Mandant,iNum);
						} 
						else 
						{
							//hmNumUsers.put(cID_Mandant,new Integer(1));
							throw new myException("Module:"+"MODUL__LIST_BT_SelectUsers4Module:"+" Error building usergrid ...");
						} 
					}

					
					
					/*
					 * Jeder mandant kommt auf einen tabbed-pane
					 */
					Vector<E2_ComponentGrid> vGrids = new Vector<E2_ComponentGrid>();              // ein grid fuer jeden mandanten
					MyE2_TabbedPane oTabbedPane = new MyE2_TabbedPane(null);
					
					
					for (int i=0;i<cMandanten.length;i++)
					{
						
						//2012-10-25: ein grid fuer jeden mandanten aber nur wenn es einen benutzer gibt
						if (hmNumUsers.get(cMandanten[i][0])!=null && hmNumUsers.get(cMandanten[i][0])>0)
						{
						
							// Grid für Mandanten anlegen und die Zeilenzahl festlegen
							E2_ComponentGrid oGrid = new E2_ComponentGrid(4);
							oGrid.setOrientation(Grid.ORIENTATION_VERTICAL);
							
							vGrids.add(oGrid);
							oGrid.EXT().set_C_MERKMAL(cMandanten[i][0]);   					// id_mandant in c_merkmal
							
							// die anzahl der Zeilen fürs grid festlegen
							// Grid-Orientierung drehen
							if( oGrid.getOrientation() == Grid.ORIENTATION_VERTICAL) 
							{
								oGrid.setSize( (hmNumUsers.get(cMandanten[i][0]).intValue() / iNumCols) + 1);
							} 
							else 
							{
								oGrid.setSize(iNumCols);
							}
						
							
							oTabbedPane.add_Tabb(new MyE2_String(cMandanten[i][1],false),oGrid);	
						}
					}
					
					
					for (int i=0;i<oThis.vCheckBoxes.size();i++)
					{
						MyE2_CheckBox oCB = (MyE2_CheckBox)oThis.vCheckBoxes.get(i);
						String cID_Mandant = (String)oCB.EXT().get_O_PLACE_FOR_EVERYTHING();
						for (int k=0;k<vGrids.size();k++)
						{
							E2_ComponentGrid oGrid = (E2_ComponentGrid)vGrids.get(k);
							
							
							// Checkboxen ins Grid einfügen
							if (oGrid.EXT().get_C_MERKMAL().equals(cID_Mandant))
							{
								oGrid.add((MyE2_CheckBox)oThis.vCheckBoxes.get(i));							
								break;
							}
						}
					}
					
					this.add(oTabbedPane, E2_INSETS.I_10_10_10_10);
					
					
					this.oButtonOK = new MyE2_Button(new MyE2_String("Speichern"));
					this.oButtonCancel = new MyE2_Button(new MyE2_String("Abbrechen"));
					
					this.oButtonOK.add_oActionAgent(new ActionAgentSAVE());
					this.oButtonCancel.add_oActionAgent(new ActionAgentCANCEL());
					
					this.add(new E2_ComponentGroupHorizontal(1,this.oButtonOK,this.oButtonCancel, null), new Insets(5,5,5,10));
					
					if (bibMSG.get_bIsOK())
					{
						this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(800), new Extent(800), new MyE2_String("Benutzer zuordnen !"));
					}
					
				}

			}	
		}
		
		
		private class ActionAgentSAVE extends XX_ActionAgent
		{
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				Vector<String> vSQL = new Vector<String>();
				vSQL.add("DELETE FROM "+bibE2.cTO()+".JD_ZUGRIFF WHERE ID_SERVLETS="+MODUL__LIST_BT_SelectUsers4Module.this.cServletID);
				int iAnzahl=0;
				for (int i=0;i<MODUL__LIST_BT_SelectUsers4Module.this.vCheckBoxes.size();i++)
				{
					MyE2_CheckBox oCB = (MyE2_CheckBox)MODUL__LIST_BT_SelectUsers4Module.this.vCheckBoxes.get(i);
					
					if (oCB.isSelected())
					{
						vSQL.add("INSERT INTO JD_ZUGRIFF(ID_ZUGRIFF,ID_USER,ID_SERVLETS) " +
								" VALUES(SEQ_ZUGRIFF.NEXTVAL,"+oCB.EXT().get_C_MERKMAL()+","+MODUL__LIST_BT_SelectUsers4Module.this.cServletID+")");
						iAnzahl++;
					}
				}
				
				if (bibDB.ExecMultiSQLVector(vSQL,true).get_bIsOK())
				{
					bibMSG.add_MESSAGE(new MyE2_Info_Message_OT(new MyE2_String("Anzahl Benutzer für diesen Menüpunkt: ").CTrans()+" "+iAnzahl));
					ownPopup.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
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
				ownPopup.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
			}
		}

		
	}

	


}
