package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.ActionAgent_RadioFunction_CheckBoxList;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.ServerPush.E2_ServerPushMessageContainer;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.PRUEF_RECORD_VPOS_TPA_FUHRE;

public class FU_LIST_BT_REBUILD_FUHRENSTATUS extends MyE2_Button
{
	
	private int iOK = 0;
	private int iFehler = 0;
	
	private E2_NavigationList  oNaviList = null;
	
	private Vector<String>     vIDs = new Vector<String>();
	private MyE2_CheckBox      oCBAlle = new MyE2_CheckBox(new MyE2_String("Alle in der Liste"));
	private MyE2_CheckBox      oCBSeite = new MyE2_CheckBox(new MyE2_String("Alle auf der Seite"));
	private MyE2_CheckBox      oCBAuswahl = new MyE2_CheckBox(new MyE2_String("Ausgewählte"));
	
	
	public FU_LIST_BT_REBUILD_FUHRENSTATUS(E2_NavigationList NaviList)
	{
		super(new MyE2_String("Fuhrenstatus aller Fuhren neuaufbauen"));
		this.oNaviList = NaviList;
		
		this.add_GlobalAUTHValidator_AUTO("ALLE_FUHREN_STATUS_PRUEFEN");
		this.add_oActionAgent(new XX_ActionAgent()
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				new ownPopupBox();
			}
		});
		
	}

	
	private class ownPopupBox extends E2_BasicModuleContainer
	{

		public ownPopupBox() throws myException
		{
			super();
			
			FU_LIST_BT_REBUILD_FUHRENSTATUS oThis = FU_LIST_BT_REBUILD_FUHRENSTATUS.this;
			
			MyE2_Grid oBaseGrid = new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_INSETS_11());
			
			ActionAgent_RadioFunction_CheckBoxList  oCB_Radio = new  ActionAgent_RadioFunction_CheckBoxList(true);
			
			oCB_Radio.add_CheckBox(oThis.oCBAlle);
			oCB_Radio.add_CheckBox(oThis.oCBSeite);
			oCB_Radio.add_CheckBox(oThis.oCBAuswahl);
			
			oBaseGrid.add(new MyE2_Label(new MyE2_String("Welche Fuhren sollen betrachtet werden ?"), MyE2_Label.STYLE_TITEL_BIG()), E2_INSETS.I_10_10_10_10);
			oBaseGrid.add(oThis.oCBAlle, E2_INSETS.I_10_2_10_2);
			oBaseGrid.add(oThis.oCBSeite, E2_INSETS.I_10_2_10_2);
			oBaseGrid.add(oThis.oCBAuswahl, E2_INSETS.I_10_2_10_2);

			MyE2_Button btStart = new MyE2_Button(new MyE2_String("Start"));
			
			btStart.add_oActionAgent(new ownAction());
			
			oBaseGrid.add(btStart, E2_INSETS.I_10_10_10_10);
			
			this.add(oBaseGrid);
			
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(400), new Extent(300), new MyE2_String("Neuaufbau der Fuhrenstatus-Einträge"));
			
			
		}
		
	}
	
	
	
	private class ownAction extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			
			FU_LIST_BT_REBUILD_FUHRENSTATUS oThis = FU_LIST_BT_REBUILD_FUHRENSTATUS.this;
		
			oThis.vIDs.removeAllElements();
			
			if (oThis.oCBAlle.isSelected())
			{
				oThis.vIDs.addAll(oThis.oNaviList.get_vectorSegmentation());
			} 
			else if (oThis.oCBSeite.isSelected())
			{
				oThis.vIDs.addAll(oThis.oNaviList.get_vActualID_Segment());
			}
			else if (oThis.oCBAuswahl.isSelected())
			{
				oThis.vIDs.addAll(oThis.oNaviList.get_vSelectedIDs_Unformated());
			}
				 
			
			if (oThis.vIDs.size()==0)
			{
				bibMSG.add_MESSAGE(new  MyE2_Alarm_Message("Es wurde kein Datensatz zur Bearbeitung gewählt !"));
				return;
			}
			
			
				new E2_ServerPushMessageContainer(new Extent(400),new Extent(150),new MyE2_String("Fuhren-Check"),true,true,false,3000)
				{
					@Override
					public void Run_Loop() throws myException
					{
						FU_LIST_BT_REBUILD_FUHRENSTATUS o_This = FU_LIST_BT_REBUILD_FUHRENSTATUS.this;
						
						o_This.iFehler  = 0;
						o_This.iOK = 0;
						
						
						for (int i=0;i<o_This.vIDs.size();i++)
						{
							PRUEF_RECORD_VPOS_TPA_FUHRE pruefRec = new PRUEF_RECORD_VPOS_TPA_FUHRE(o_This.vIDs.get(i),false);

							MyE2_MessageVector oMV=new MyE2_MessageVector();
							try
							{
								oMV = pruefRec.__writeSQLStatemtents_MengenSituation_undFuhrenStatus(true);
							} 
							catch (myException e)
							{
								e.printStackTrace();
								oMV.add_MESSAGE(e.get_ErrorMessage());
							}

							//							//den statusupdate ueber ein dummy-update im daemon starten
//							MyE2_MessageVector oMV = bibDB.ExecMultiSQLVector(bibALL.get_Vector("UPDATE JT_VPOS_TPA_FUHRE SET L_NAME1 = L_NAME1 WHERE ID_VPOS_TPA_FUHRE="+o_This.vIDs.get(i)), true);
							
							if (oMV.get_bIsOK())
							{
								FU_LIST_BT_REBUILD_FUHRENSTATUS.this.iOK++;
							}
							else
							{
								FU_LIST_BT_REBUILD_FUHRENSTATUS.this.iFehler++;
								bibMSG.add_MESSAGE(oMV);
							}
							
							MyE2_String  cInfo = new MyE2_String(	"Zähler: ",true,
																	""+(i+1),false,
																	" //  OK: ",true,
																	""+FU_LIST_BT_REBUILD_FUHRENSTATUS.this.iOK,false,
																	" //  Fehler: ",true, 
																	""+FU_LIST_BT_REBUILD_FUHRENSTATUS.this.iFehler,false);
						
							this.get_oGridBaseForMessages().removeAll();
							this.get_oGridBaseForMessages().add(new MyE2_Label(cInfo));
						}
						
						MyE2_String  cInfo = new MyE2_String(	"Geprüft: ",true,
																""+FU_LIST_BT_REBUILD_FUHRENSTATUS.this.vIDs.size(),false,
																" //  OK: ",true,
																""+FU_LIST_BT_REBUILD_FUHRENSTATUS.this.iOK,false,
																" //  Fehler: ",true, 
																""+FU_LIST_BT_REBUILD_FUHRENSTATUS.this.iFehler,false);

						if (FU_LIST_BT_REBUILD_FUHRENSTATUS.this.iFehler != 0)
						{
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message(cInfo));
						}
						else
						{
							bibMSG.add_MESSAGE(new MyE2_Info_Message(cInfo));
						}
						
						FU_LIST_BT_REBUILD_FUHRENSTATUS.this.oNaviList._REBUILD_COMPLETE_LIST("");
					}
					
					@Override
					public void setWindowPaneLookAndFeel(MyE2_WindowPane oWindowPane) throws myException
					{
					}

				};
		}
	}
	
	
	
	
}
