package panter.gmbh.Echo2.ListAndMask.List;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.UserSettings.E2_UserSettingListeBleibtAusgeklappt;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;



/*
 * kann in eine liste als kopf fuer die Tochter-Aufklapp-Knoepfe benutzt werden
 */
public class E2_ListButtonExtendDaughterObjects_LISTHEADLINE extends MyE2_Button
{
	
	private E2_NavigationList	oNaviList = null;
	private E2_BasicModuleContainer	oListContainer = null;
	
	private boolean      		bSaveStatusPerm = false;
	
	
	public E2_ListButtonExtendDaughterObjects_LISTHEADLINE(E2_NavigationList  NaviList)
	{
		super(E2_ResourceIcon.get_RI("expandopenclose.png"));
		this.oNaviList = NaviList;
		this.setLayoutData(E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutLeftTOPHeader());
		this.add_oActionAgent(new ownActionAgent());
	}

	
	//2011-01-12: weiterer konstuktor, dann wird der ausklappzustand gespeichert
	public E2_ListButtonExtendDaughterObjects_LISTHEADLINE(E2_NavigationList  NaviList, E2_BasicModuleContainer ListContainer) throws myException
	{
		super(E2_ResourceIcon.get_RI("expandopenclose.png"));
		this.oNaviList = NaviList;
		
		if (S.isEmpty(ListContainer.get_MODUL_IDENTIFIER()))
		{
			throw new myException("E2_BasicModuleContainer MUST HAVE an IDENTIFIER !!!");
		}

		this.oListContainer = ListContainer;
		this.bSaveStatusPerm = true;
		
		this.setLayoutData(E2_NavigationList_StandardLayoutFactory.get_GRID_LayoutLeftTOPHeader());
		this.add_oActionAgent(new ownActionAgent());
	}
	
	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			E2_ListButtonExtendDaughterObjects_LISTHEADLINE oThis = E2_ListButtonExtendDaughterObjects_LISTHEADLINE.this;
			Vector<E2_ComponentMAP> vComponentMAPs = oThis.oNaviList.get_vComponentMAPS();
			
			boolean bOpen=true;
			
			/*
			 * zuerst feststellen, ob oeffnen oder schliessen,
			 * wenn mindestens einer offen ist, dann schliessen, sonst oeffnen
			 */
			for (int i=0;i<vComponentMAPs.size();i++)
			{
				E2_ComponentMAP oMap = (E2_ComponentMAP)vComponentMAPs.get(i);
				if (oMap.get_List_EXPANDER_4_ComponentMAP() != null)
				{
					if (oMap.get_List_EXPANDER_4_ComponentMAP().get_bIsOpen())
					{
						bOpen = false;
						break;
					}
				}
			}

			
			//falls der 2. konstuktor mit gueltigem Modulkenner kommt, dann den status automatisch speichern
			if (E2_ListButtonExtendDaughterObjects_LISTHEADLINE.this.bSaveStatusPerm)
			{
				String cActualWert = bOpen?"Y":"N";
				new E2_UserSettingListeBleibtAusgeklappt().STORE(E2_ListButtonExtendDaughterObjects_LISTHEADLINE.this.oListContainer.get_MODUL_IDENTIFIER(), cActualWert);
				
				//dann den status aktuelle auf die componentmap der liste uebertragen (sonst wuerde der zustand erst beim naechsten oeffnen des moduls aktiviert) 
				if (E2_ListButtonExtendDaughterObjects_LISTHEADLINE.this.oListContainer!=null)
				{
					E2_ListButtonExtendDaughterObjects_LISTHEADLINE.this.oListContainer.get_oNaviListFirstAdded().get_oComponentMAP__REF().set_bExtendSublistsAutomatic(bOpen);
				}
			}
			
			
			for (int i=0;i<vComponentMAPs.size();i++)
			{
				E2_ComponentMAP oMap = (E2_ComponentMAP)vComponentMAPs.get(i);
				if (oMap.get_List_EXPANDER_4_ComponentMAP() != null && oMap.get_OPEN_ToggleButton()!=null && oMap.get_OPEN_ToggleButton().get_bCanBeOpened())
				{
					oMap.set_OPEN_DaughterObjectForList_With_ToggleButton(bOpen);
				}
			}
			/*
			 * dann liste in der aktuellen ansicht neu aufbauen 
			 */
			try
			{
				oThis.oNaviList.FILL_GRID_From_InternalComponentMAPs(true, true);
			}
			catch (myException ex)
			{
				ex.printStackTrace();
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Neuaufbau der Liste !!!"));
			}

		}

	}
	

	
}
