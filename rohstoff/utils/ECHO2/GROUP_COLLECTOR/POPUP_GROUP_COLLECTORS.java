package rohstoff.utils.ECHO2.GROUP_COLLECTOR;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Label;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message_OT;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_PopDownMenue;
import panter.gmbh.Echo2.components.MyE2_PopMiddleMenue;
import panter.gmbh.Echo2.components.MyE2_PopUpMenue;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class POPUP_GROUP_COLLECTORS extends MyE2_Row
{

	private E2_NavigationList	oNaviList = null;
	private MyE2_PopUpMenue		oPopUpADD = null;
	private MyE2_PopUpMenue		oPopUpRemove = null;
	

	/*
	 * menue-leisten-komponente fuer die gruppen-selektoren
	 */
	public POPUP_GROUP_COLLECTORS(	String 				cMODULKENNER, 	
									E2_NavigationList 	NavigationList) throws myException
	{
		//super(E2_ResourceIcon.get_RI("groupbuilder.png"), E2_ResourceIcon.get_RI("groupbuilder__.png"));
		super(MyE2_Row.STYLE_NO_BORDER());
		
		//2015-07-17: popup auf middle umgestellt, stoesst sonst oben an
		this.oPopUpADD = new MyE2_PopMiddleMenue(E2_ResourceIcon.get_RI("collect.png"), E2_ResourceIcon.get_RI("collect.png"), new Alignment(Alignment.RIGHT, Alignment.CENTER));
		this.oPopUpRemove = new MyE2_PopDownMenue(E2_ResourceIcon.get_RI("uncollect.png"), E2_ResourceIcon.get_RI("uncollect.png"));
		
		if (bibALL.isEmpty(cMODULKENNER) || NavigationList==null)
			throw new myException("POPUP_GROUP_COLLECTORS:Constructor:empty parameters not allowed !!");

		this.oNaviList = NavigationList;
		
		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		
		String cQuery = "SELECT MENUETEXT,BUTTONKENNER,BESCHREIBUNG,ID_COLLECTION_DEF FROM "+bibE2.cTO()+
						".JT_COLLECTION_DEF WHERE MODULE_KENNER="+bibALL.MakeSql(cMODULKENNER)+" ORDER BY 1";
		
		String[][] cResult = oDB.EinzelAbfrageInArray(cQuery,"");
		bibALL.destroy_myDBToolBox(oDB);
		
		if (cResult == null)
			throw new myException("POPUP_ReportLists:Constructor:Error Querying reports ...!");
		
		if (cResult.length==0)
		{
			this.setVisible(false);   // das column-object wird unsichtbar
		}
		else
		{
			boolean startHinweisAdd = false;
			boolean startHinweisRemove = false;
			
			for (int i=0;i<cResult.length;i++)	{
				if (!startHinweisAdd) {
					startHinweisAdd=true;
					MyE2_Button bt_dummy = new MyE2_Button(new MyE2_String("Zu freier Gruppe zufügen ..."),MyE2_Button.StyleTextButton_LOOK_like_LABEL_BoldItalic());
					bt_dummy.setEnabled(false);
					this.oPopUpADD.addButton(bt_dummy, false);
				}
				
				MyE2_Button oButton = new MyE2_Button(cResult[i][0]);
				if (!bibALL.isEmpty(cResult[i][1]))oButton.add_GlobalValidator(new E2_ButtonAUTHValidator(cMODULKENNER,cResult[i][1]));
				oButton.add_oActionAgent(new ownActionAgentCollect(cResult[i][3]));
				this.oPopUpADD.addButton(oButton,true);
				this.oPopUpADD.setToolTipText(new MyE2_String("Zu freier Gruppe zufügen ...").CTrans());
			}
			for (int i=0;i<cResult.length;i++) 	{
				
				if (!startHinweisRemove) {
					startHinweisRemove=true;
					MyE2_Button bt_dummy = new MyE2_Button(new MyE2_String("Aus freier Gruppe entfernen ..."),MyE2_Button.StyleTextButton_LOOK_like_LABEL_BoldItalic());
					bt_dummy.setEnabled(false);
					this.oPopUpRemove.addButton(bt_dummy, false);
				}

				
				MyE2_Button oButton = new MyE2_Button(cResult[i][0]);
				if (!bibALL.isEmpty(cResult[i][1]))oButton.add_GlobalValidator(new E2_ButtonAUTHValidator(cMODULKENNER,cResult[i][1]));
				oButton.add_oActionAgent(new ownActionAgentUnCollect(cResult[i][3]));
				this.oPopUpRemove.addButton(oButton,true);
				this.oPopUpRemove.setToolTipText(new MyE2_String("Aus freier Gruppe entfernen ...").CTrans());
			}
		}

		this.add(this.oPopUpRemove,E2_INSETS.I_1_1_1_1);
		this.add(new Label(E2_ResourceIcon.get_RI("gruppe.png")),E2_INSETS.I_1_1_1_1);
		this.add(this.oPopUpADD,E2_INSETS.I_1_1_1_1);
		
		
	}

	

	/*
	 * actionagent, der bei den einzelnen buttons die selektierten ids anfuegt
	 */
	private class ownActionAgentCollect extends XX_ActionAgent
	{

		private String cID_COLLECTION_DEF = null; 
		
		
		public ownActionAgentCollect(String cid_collection_def)
		{
			super();
			this.cID_COLLECTION_DEF = cid_collection_def;
		}


		public void executeAgentCode(ExecINFO oExecInfo)
		{
			POPUP_GROUP_COLLECTORS oThis = POPUP_GROUP_COLLECTORS.this;
			
			Vector<String> vIDsToCollect = oThis.oNaviList.get_vSelectedIDs_Unformated();
			
			if (vIDsToCollect.size() == 0)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(new MyE2_String("Bitte mindestens einen Datensatz auswählen !!").CTrans(),false)));
			}
			else
			{
				Vector<String> vSQL = new Vector<String>();

				int iOK = 0;
				int iFehler = 0;
				int iBereitsVorhanden = 0;
				
				for (int i=0;i<vIDsToCollect.size();i++)
				{
					String cAnzahl = bibDB.EinzelAbfrage("SELECT COUNT(*) FROM "+
															bibE2.cTO()+".JT_COLLECTIONS WHERE ID_TABLE="+(String)vIDsToCollect.get(i)+
															" AND ID_COLLECTION_DEF="+this.cID_COLLECTION_DEF).trim();
					
					
					if (cAnzahl.equals("0"))
					{
						vSQL.add("INSERT INTO "+bibE2.cTO()+".JT_COLLECTIONS (ID_COLLECTIONS,ID_COLLECTION_DEF,ID_TABLE) VALUES " +
										" (SEQ_COLLECTIONS.NEXTVAL"+","+this.cID_COLLECTION_DEF+","+(String)vIDsToCollect.get(i)+")");
						iOK++;
					}
					else if (cAnzahl.equals("1"))
					{
						iBereitsVorhanden++;
					}
					else
					{
						iFehler++;
					}
				}
				if (vSQL.size()==0 | bibDB.ExecMultiSQLVector(vSQL,true).get_bIsOK())
				{
					MyE2_String cInfo = new MyE2_String("Anzahl zugefügt: ");
					cInfo.addUnTranslated(""+iOK+" --  ");
					cInfo.addString(new MyE2_String("Anzahl Fehlerhaft: "));
					cInfo.addUnTranslated(""+iFehler+" --  ");
					cInfo.addString(new MyE2_String("Anzahl bereits vorhanden: "));
					cInfo.addUnTranslated(""+iBereitsVorhanden);
					
					bibMSG.add_MESSAGE(new MyE2_Info_Message_OT(cInfo.CTrans()));
				}
				else
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Anfügen der Auswahlzeilen !!"));
				}
			}
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * actionagent, der bei den einzelnen buttons die selektierten ids wieder entfernt
	 */
	private class ownActionAgentUnCollect extends XX_ActionAgent
	{

		private String cID_COLLECTION_DEF = null; 
		
		
		public ownActionAgentUnCollect(String cid_collection_def)
		{
			super();
			this.cID_COLLECTION_DEF = cid_collection_def;
		}


		public void executeAgentCode(ExecINFO oExecInfo)
		{
			POPUP_GROUP_COLLECTORS oThis = POPUP_GROUP_COLLECTORS.this;
			
			Vector<String> vIDsToUnCollect = oThis.oNaviList.get_vSelectedIDs_Unformated();
			
			if (vIDsToUnCollect.size() == 0)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(new MyE2_String("Bitte mindestens einen Datensatz auswählen !!").CTrans(),false)));
			}
			else
			{
				MyDBToolBox oDB = bibALL.get_myDBToolBox();

				Vector<String> vSQL = new Vector<String>();

				int iOK = 0;
				int iFehler = 0;
				int iNichtVorhanden = 0;
				
				for (int i=0;i<vIDsToUnCollect.size();i++)
				{
					String cAnzahl = oDB.EinzelAbfrage("SELECT COUNT(*) FROM "+
															bibE2.cTO()+".JT_COLLECTIONS WHERE ID_TABLE="+(String)vIDsToUnCollect.get(i)+
															" AND ID_COLLECTION_DEF="+this.cID_COLLECTION_DEF).trim();
					
					
					if (cAnzahl.equals("1"))
					{
						vSQL.add("DELETE FROM "+bibE2.cTO()+".JT_COLLECTIONS WHERE " +
								         	" ID_COLLECTION_DEF="+this.cID_COLLECTION_DEF+" AND ID_TABLE="+(String)vIDsToUnCollect.get(i));
						iOK++;
					}
					else if (cAnzahl.equals("0"))
					{
						iNichtVorhanden++;
					}
					else
					{
						iFehler++;
					}
				}
				if (vSQL.size()==0 | oDB.ExecMultiSQLVector(vSQL,true).get_bIsOK())
				{
					MyE2_String cInfo = new MyE2_String("Anzahl entfernt: ");
					cInfo.addUnTranslated(""+iOK+" --  ");
					cInfo.addString(new MyE2_String("Anzahl Fehlerhaft: "));
					cInfo.addUnTranslated(""+iFehler+" --  ");
					cInfo.addString(new MyE2_String("Anzahl nicht in der Gruppe vorhanden: "));
					cInfo.addUnTranslated(""+iNichtVorhanden);
					
					bibMSG.add_MESSAGE(new MyE2_Info_Message_OT(cInfo.CTrans()));
				}
				else
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Entfernen der Auswahlzeilen !!"));
				}
				
				
				bibALL.destroy_myDBToolBox(oDB);
			}
		}
		
	}

	
}
