package panter.gmbh.Echo2.__CONTAINER_ADDONS.MODULSETTINGS;

import java.util.StringTokenizer;
import java.util.Vector;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_BASIC_EditListButtonPanel;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message_OT;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_PopUpHelp;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.Echo2.staticStyles.Style_Table_Normal;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.exceptions.myException;

public class MOD_GROUPS extends MyE2_Column
{
	private String 							cMODULKENNER = null; 
	private E2_BasicModuleContainer 		oContainer = null;
	private E2_NavigationList				oNaviList = null;

	
	private MyE2_TextArea					oTextAreaWithAddonNumbers = new MyE2_TextArea();
	
	public MOD_GROUPS(E2_BasicModuleContainer  	ocontainer) throws myException
	{
		super();
		
		this.oContainer = 					ocontainer;

		
		this.oTextAreaWithAddonNumbers.setWidth(new Extent(450));
		this.oTextAreaWithAddonNumbers.setHeight(new Extent(380));
		
		
		
		this.cMODULKENNER = this.oContainer.get_MODUL_IDENTIFIER();
		if (bibALL.isEmpty(this.cMODULKENNER))
			throw new myException("MOD_GROUPS:Constuctor: Modul has no MODUL_KENNER !!!");

		
		this.oNaviList = new E2_NavigationList();
		
		MOD_GROUPS_ComponentMAPLIST oComponentMAPList = new MOD_GROUPS_ComponentMAPLIST(this.cMODULKENNER);
		
		oNaviList.INIT_WITH_ComponentMAP(oComponentMAPList,new Style_Table_Normal(new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID)), null);
		
		
		Vector<MyString> vHelp = new Vector<MyString>();
		vHelp.add(new MyE2_String("Bitte beachten!"));
		vHelp.add(new MyE2_String("Wird ein Button-Kenner hinterlegt, so entsteht ein Zugriffs-Schutz"));
		vHelp.add(new MyE2_String("auf das Anfügen von Gruppen-IDs"));

		MyE2_PopUpHelp oHelp = new MyE2_PopUpHelp(vHelp);
		
		E2_BASIC_EditListButtonPanel oButtonPanel = new E2_BASIC_EditListButtonPanel(
										this.oNaviList,true,true,true,null,null,null,"", null, null, null);
		
		// sonder-delete einfuegen, das die buttons-definition loescht
		String cSonderSQL = "DELETE FROM "+bibE2.cTO()+".JD_BUTTON WHERE MODULKENNER="+bibALL.MakeSql(this.cMODULKENNER)+
								" AND BUTTONKENNER=(SELECT   NVL(BUTTONKENNER,'@@@@@@@@@@@@@@') FROM "+bibE2.cTO()+".JT_COLLECTION_DEF WHERE ID_COLLECTION_DEF=#ID#)";
		oButtonPanel.get_vSQLStatementsBeforeDeleteStatement().add(cSonderSQL);
		
		
		
		this.add(new E2_ComponentGroupHorizontal(0,oButtonPanel,new ButtonInsertList(),oHelp,E2_INSETS.I_0_0_10_0),E2_INSETS.I_6_6_6_6);
		this.add(this.oNaviList,E2_INSETS.I_6_6_6_6);
		this.oNaviList.Fill_NavigationList("");
	}

	
	
	
	/*
	 * ein button, der die eingabe eine ganzen liste von ids erlaubt
	 */
	private class ButtonInsertList extends MyE2_Button
	{

		public ButtonInsertList()
		{
			super(E2_ResourceIcon.get_RI("addlist.png"), true);
			
			this.setToolTipText(new MyE2_String("Liste mit IDs anfügen ...").CTrans());
			this.add_oActionAgent(new ownActionAgent());
		}
		
		
	}
	
	
	
	private class ownActionAgent extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo)
		{
			MOD_GROUPS oThis = MOD_GROUPS.this;
			
			/*
			 * nachsehen, ob eine zeile angekreutzt war
			 */
			Vector<String> vIDSel = oThis.oNaviList.get_vSelectedIDs_Unformated();
			
			if (vIDSel.size()!=1)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte genau eine Gruppe auswählen !"));
				return;
			}
			
			String cID_CollectionDef = (String)vIDSel.get(0);
			
			
			E2_BasicModuleContainer oContainerForInputListe = new E2_BasicModuleContainer();
			oContainerForInputListe.set_bVisible_Row_For_Messages(false);
			
			oThis.oTextAreaWithAddonNumbers.setText("");
			
			oContainerForInputListe.add(oThis.oTextAreaWithAddonNumbers,E2_INSETS.I_10_10_10_10);
			
			buttonSave oSave = new buttonSave(oContainerForInputListe,cID_CollectionDef);
			
			E2_ComponentGroupHorizontal oGroupButtons = new E2_ComponentGroupHorizontal(0,
									oSave,
									new buttonClose(oContainerForInputListe),
									E2_INSETS.I_10_2_10_2);
			
			oContainerForInputListe.set_Component_To_ButtonPane(oGroupButtons);
			
			try
			{
				oContainerForInputListe.CREATE_AND_SHOW_POPUPWINDOW_SPLIT(new Extent(500),new Extent(500),new MyE2_String("Eingabe von IDs"));
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
			}
			
		}
		
	}
	
	
	
	private class buttonClose extends MyE2_Button
	{
		public buttonClose(E2_BasicModuleContainer oContainerForInputListe)
		{
			super(new MyE2_String("Abbrechen"));
			this.add_oActionAgent(new ownActionClose(oContainerForInputListe));
		}
		
	}

	private class ownActionClose extends XX_ActionAgent
	{
		E2_BasicModuleContainer ContainerForInputListe = null;
		
		public ownActionClose(E2_BasicModuleContainer containerForInputListe)
		{
			super();
			ContainerForInputListe = containerForInputListe;
		}

		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			ContainerForInputListe.CLOSE_AND_DESTROY_POPUPWINDOW(false);
		}
		
	}
	
	

	
	private class buttonSave extends MyE2_Button
	{
		public buttonSave(E2_BasicModuleContainer oContainerForInputListe, String cID_Collection_Def)
		{
			super(new MyE2_String("SPEICHERN"));
			this.add_oActionAgent(new ownActionSave(oContainerForInputListe,cID_Collection_Def));
		}
		
	}

	private class ownActionSave extends XX_ActionAgent
	{
		private E2_BasicModuleContainer ContainerForInputListe = null;
		private String 					cIDCollectionDef = null;
		
		public ownActionSave(E2_BasicModuleContainer containerForInputListe, String ID_Collection_def)
		{
			super();
			ContainerForInputListe = containerForInputListe;
			this.cIDCollectionDef = ID_Collection_def;
		}

		
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			
			MOD_GROUPS oThis = MOD_GROUPS.this;
		
			String cTextListe = oThis.oTextAreaWithAddonNumbers.getText();
			
			// zuerst die trenner definieren - alles wird umgesetzt auf /
			cTextListe = bibALL.ReplaceTeilString(cTextListe,"\n\r","/");
			cTextListe = bibALL.ReplaceTeilString(cTextListe,"\r","/");
			cTextListe = bibALL.ReplaceTeilString(cTextListe,"\n","/");
			cTextListe = bibALL.ReplaceTeilString(cTextListe,"-","/");
			cTextListe = bibALL.ReplaceTeilString(cTextListe,";","/");
			cTextListe = bibALL.ReplaceTeilString(cTextListe,",","/");
			cTextListe = bibALL.ReplaceTeilString(cTextListe,"\t","/");
			cTextListe = bibALL.ReplaceTeilString(cTextListe,"  "," ");
			cTextListe = bibALL.ReplaceTeilString(cTextListe," ","/");
			
			StringTokenizer textTrenner = new StringTokenizer("/"+cTextListe+"/","/");
			String		    cZeile = null;

			int iGefunden = 0;
			int iKorrekt = 0;
			int iGeschrieben = 0;
			int iVorhanden = 0;
			
			MyDBToolBox oDB = bibALL.get_myDBToolBox();
			
			
			/*
			 * zuerst nachsehen, was schon vorhanden war
			 */
			String[][] cVorhanden = oDB.EinzelAbfrageInArray("SELECT ID_TABLE FROM "+bibE2.cTO()+".JT_COLLECTIONS WHERE ID_COLLECTION_DEF="+this.cIDCollectionDef);
			if (cVorhanden == null)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Error Querying Groupmembers !",false)));
				bibALL.destroy_myDBToolBox(oDB);
				return;
			}
			
			Vector<String> vVorhanden = new Vector<String>();
			for (int i=0;i<cVorhanden.length;i++)
				vVorhanden.add(cVorhanden[i][0]);
			
			
			while (textTrenner.hasMoreElements())
			{
				cZeile = textTrenner.nextToken();

				if (!cZeile.trim().equals(""))
				{
					iGefunden++;
					if (bibALL.isInteger(cZeile))
					{
						iKorrekt ++;
						
						if (vVorhanden.contains(cZeile))
						{
							iVorhanden++;
						}
						else
						{
							String cSQL = "INSERT INTO JT_COLLECTIONS(ID_COLLECTIONS,ID_COLLECTION_DEF,ID_TABLE) "+
										  " VALUES(SEQ_COLLECTIONS.NEXTVAL,"+this.cIDCollectionDef+","+cZeile+")";
							if (oDB.ExecSQL(cSQL,true))
							{
								iGeschrieben++;
							}
						}
					}
				}
			}
			bibALL.destroy_myDBToolBox(oDB);
			

			bibMSG.add_MESSAGE(new MyE2_Info_Message_OT(new MyE2_String("Gefunden:").CTrans()+": "+
									  iGefunden+"   "+
									  new MyE2_String("Korrekt").CTrans()+": "+
									  iKorrekt+"   "+
									  new MyE2_String("Bereits vorhanden ").CTrans()+": "+
									  iVorhanden+"   "+
									  new MyE2_String("Geschrieben").CTrans()+": "+
									  iGeschrieben+"   "));
			
			ContainerForInputListe.CLOSE_AND_DESTROY_POPUPWINDOW(false);
			
		}
		
	}
	

	
	
	
}
