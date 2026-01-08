package panter.gmbh.Echo2.ListAndMask.List.JUMPER;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.Messaging.MessageAgent_InPOPUP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_ListSelectorContainer;
import panter.gmbh.basics4project.CallStringAnalyser;
import panter.gmbh.basics4project.E2_TabbedPaneForFirstContainer;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.exceptions.myException;


public class BaseJumper
{

	public BaseJumper(	String cModule_KENNER_TARGET, 
						String cLesbarerName , 
						Vector<String> vIDsTargetList, 
						boolean bRefuseJumpWhenEmptyList, 
						Vector<E2_BasicModuleContainer> vContainersToClose) throws myException
	{
		super();
		
		if (vIDsTargetList==null || S.isEmpty(cModule_KENNER_TARGET))
		{
			throw new myException(this,"vIDSTargetList and Modulename MUST NOT BE empty !!!");
		}
		
		
		//2011-06-07: wenn gewollt, dann leer jumps unterbinden
		if (bRefuseJumpWhenEmptyList)
		{
			if (vIDsTargetList.size()==0)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Sprunganforderung in ",true,S.NN(cLesbarerName),false,": abgebrochen, da keine zugehörenden Daten vorliegen!",true)));
				return;
			}
			
		}
			
		//2012-02-01: feststellen, aus welchem aktiven container der sprung startet
		String cACTIV_MODULE_KENNER = bibE2.GET_PROGRAMM_TABBEDPANE_IN_SESSION().get_MODULKENNER_OF_ACTIVE_MODULE();
		
		
		
		E2_TabbedPaneForFirstContainer  	oTabbedPane = 			bibE2.GET_PROGRAMM_TABBEDPANE_IN_SESSION();
		E2_NavigationList   				oNaviList_TargetModule = 	null;

		E2_TabbedPaneForFirstContainer.ContainerInfo oContainerInfo =  oTabbedPane.get_ContainerInfo(cModule_KENNER_TARGET);

		if (oContainerInfo==null)
		{
			//2. nachsehen, ob die liste aufgerufen werden darf .. (sprung nur in die echo2_starter-typen erlabut)
			String AllowedType = CallStringAnalyser.ModulTypes.echo2_starter.toString()+":";
			HashMap<String, String> hmMenues = bibSES.get_ALLOWED_MENUES_4_USER();
			
			//20171128: sprung auf module ermoeglichen, die im Zusaetze-Menue hinterlegt sind
			HashMap<String, String> hmMenuesZusaetze = ENUM_GLOBALE_MODULE_TO_JUMP.get_hmGlobaleModule();
			
			if (hmMenues.containsKey(AllowedType+cModule_KENNER_TARGET))
			{
				oTabbedPane.add_TabModule(AllowedType+cModule_KENNER_TARGET+"@@@@@"+hmMenues.get(AllowedType+cModule_KENNER_TARGET),true);
			} /*20171128*/ else if (hmMenuesZusaetze.containsKey(AllowedType+cModule_KENNER_TARGET)) {
				oTabbedPane.add_TabModule(AllowedType+cModule_KENNER_TARGET+"@@@@@"+hmMenuesZusaetze.get(AllowedType+cModule_KENNER_TARGET),true);
			}
			
		}
		//nochmal probieren
		oContainerInfo =  oTabbedPane.get_ContainerInfo(cModule_KENNER_TARGET);
		
		if (oContainerInfo!=null) {
			
			Vector<String> vIDs = new Vector<String>();
			
			vIDs.addAll(vIDsTargetList);
			
			if (bibMSG.get_bIsOK())
			{
				//dann wird ruebergesprungen und die selektion gestartet  ###HIER###
				//ALT: 2015-04-20
				//oTabbedPane.setSelectedIndex(oContainerInfo.iTabPosOfContainer);
				//NEU: 2015-04-20
				oTabbedPane.build_ModuleContainer(oContainerInfo.iTabPosOfContainer, true);
				
				//dann die (evtl. gerade neu gebaut navilist holen
				oNaviList_TargetModule = oContainerInfo.oTabSheet.get_oModuleContainer().get_oNaviListFirstAdded();
				
				if (oNaviList_TargetModule !=null) {
					//2011-09-06: variante, wenn ein selektor einem E2_ListSelectorContainer sitzt statt in einer E2_ExpandableRow_NG
					E2_ListSelectorContainer  oListSelektorContainer = new E2_RecursiveSearch_ListSelectorContainer(oContainerInfo.oTabSheet.get_oModuleContainer()).get_found_E2_ListSelectorContainer();
					
					if (oListSelektorContainer!=null)   //wird einer gefunden, dann wird der jump-vorgang anders geregelt
					{
						oListSelektorContainer.SONDERSTATUS_EINSCHALTEN(vIDs,cACTIV_MODULE_KENNER,cModule_KENNER_TARGET);
					}
					//Manfred: 2019.12.04: wenn keine IDs angegeben sind, dann wird die ganze Liste geladen
					else if (vIDs.size() > 0)
					{
						oNaviList_TargetModule.get_vActualID_Segment().removeAllElements();
						oNaviList_TargetModule.get_vActualID_Segment().addAll(vIDs);
						
						oNaviList_TargetModule._REBUILD_ACTUAL_SITE("");
						
					} else {
						oNaviList_TargetModule._REBUILD_ACTUAL_SITE("");
					}
					
					
					if (vIDs.size()==1)  //dann selektieren
					{
						oNaviList_TargetModule.set_SelectIDs(vIDs);
					}
					
					//die message muss hier haendisch angezeigt werden, da der globale messagevector in der fuhrentab ankommt
					oTabbedPane.get_vTabbs().get(oContainerInfo.iTabPosOfContainer).get_oModuleContainer().showMessages(
							new MyE2_MessageVector(new MyE2_Info_Message(
									new MyE2_String("Die Liste zeigt die selektierte(n) Datensätz(e) im Modul <"+cLesbarerName+"> an : ",true,
											""+(bibALL.Concatenate(vIDsTargetList,","," ?? ")+
											"                                                    ").substring(0,50).trim()+"...",false))));
					
					if (vContainersToClose != null && vContainersToClose.size()>0)
					{
	// 2001-08-31: Manfred: Irgendwie wurden die Fenster nicht geschlossen, wenn es mehr als eines war.. 						
	//						for (int i=(vContainersToClose.size()-1);i==0;i--)
	//						{
	//							vContainersToClose.get(i).CLOSE_AND_DESTROY_POPUPWINDOW(true);
	//						}
	
						// NEU:
						int idxMax = vContainersToClose.size() -1 ;
						while(idxMax >= 0)
						{
							vContainersToClose.get(idxMax).CLOSE_AND_DESTROY_POPUPWINDOW(true);
							idxMax --;
						}
							
					}
				}
			}
		
			else
			{
				//diese meldung muss in popup angezeigt werden
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim finden der Liste im Modul <"+cLesbarerName+"> !!!"));
				new MessageAgent_InPOPUP(bibE2.GET_FIRST_CONTENTPANE_IN_SESSION()).show_Messages();
			}
		}
		else
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Sie haben keine Berechtigung zum Öffnen des Moduls <"+cLesbarerName+"> !!!"));
			new MessageAgent_InPOPUP(bibE2.GET_FIRST_CONTENTPANE_IN_SESSION()).show_Messages();
		}
		
	}
	
}
