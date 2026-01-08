package panter.gmbh.Echo2.ListAndMask.List;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;

public class E2_SelectAllNoneInvert extends MyE2_Grid
{

	/*
	 * vector mit allen E2_ComponentMAPs innerhalb einer liste
	 */
	private static 		int[] 					iSpalten  = {10,10};
	private 			E2_NavigationList 		oNaviList = null;
	
	/**
	 * 
	 * @param NaviList 
	 * @param bMakeSettings (true, dann wird der settingsknopf eingeblendet)
	 */
	public E2_SelectAllNoneInvert(E2_NavigationList 	NaviList)
	{
		super(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		//super();
		this.set_Spalten(iSpalten);
		this.setBorder(null);
		this.setInsets(E2_INSETS.I_0_0_0_0);
		
		this.oNaviList = NaviList;

		this.add(new ownButtonUnCheckAll(this.oNaviList), E2_INSETS.I_0_0_0_0);
		this.add(new ownButtonCheckAll(this.oNaviList), E2_INSETS.I_0_0_0_0);
		this.add(new ownButtonCheckInvert(this.oNaviList), E2_INSETS.I_0_0_0_0);
		
//		//2014-09-04: infos zum Modul/Spalten
//		if (bibALL.get_bIST_SUPERVISOR()) {
//			this.add(new ownButtonInfo(this.oNaviList), E2_INSETS.I_0_0_0_0);
//		}
//		//2012-10-18: summationsfunktion
	//	this.add(new ownButtonSummieren(this.oNaviList), E2_INSETS.I_0_0_0_0);
		
	}
	
	private class ownButtonUnCheckAll extends MyE2_Button
	{

		public ownButtonUnCheckAll(E2_NavigationList 	NaviList)
		{
			super(E2_ResourceIcon.get_RI("checkbox_quartett_uncheck.png"),true);
			this.setInsets(new Insets(1,1,1,1));
			this.setToolTipText(new MyE2_String("Die Auswahlschalter aller Listenzeilen entfernen").CTrans());
			this.add_oActionAgent(new ActionAgentForChangeSelection("KEINE", NaviList));
		}
		
	}
	
	private class ownButtonCheckAll extends MyE2_Button
	{

		public ownButtonCheckAll(E2_NavigationList 	NaviList)
		{
			super(E2_ResourceIcon.get_RI("checkbox_quartett_check.png"),true);
			this.setInsets(new Insets(1,1,1,1));
			this.setToolTipText(new MyE2_String("Alle Zeilen der Liste auswählen").CTrans());
			this.add_oActionAgent(new ActionAgentForChangeSelection("ALLE", NaviList));
		}
		
	}

	private class ownButtonCheckInvert extends MyE2_Button
	{

		public ownButtonCheckInvert(E2_NavigationList 	NaviList)
		{
			super(E2_ResourceIcon.get_RI("checkbox_quartett_invert.png"),true);
			this.setInsets(new Insets(1,1,1,1));
			this.setToolTipText(new MyE2_String("Die Listenauswahl invertieren ...").CTrans());
			this.add_oActionAgent(new ActionAgentForChangeSelection("INVERT", NaviList));
		}
		
	}
	

//	private class ownButtonInfo extends MyE2_Button
//	{
//
//		public ownButtonInfo(E2_NavigationList 	NaviList)
//		{
//			super(E2_ResourceIcon.get_RI("inforound_mini.png"),true);
//			this.setInsets(new Insets(1,1,1,1));
//			this.setToolTipText(new MyE2_String("Systeminfos zum Listenmodul ...").CTrans());
//			this.add_oActionAgent(new ownActionSucheInfosZuNaviList());
//		}
//		
//	}
//	
	

	
	

	
	private class ActionAgentForChangeSelection extends XX_ActionAgent
	{
		private E2_NavigationList oNaviList = null;
		private String cType = ""; 

		public ActionAgentForChangeSelection(String type, E2_NavigationList NaviList)
		{
			super();
			cType = type;
			this.oNaviList = NaviList;
		}

		
		public void executeAgentCode(ExecINFO oExecInfo)
		{
			Vector<E2_ComponentMAP> vE2_Components = this.oNaviList.get_vComponentMAPS();
			
			if (vE2_Components==null)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Systemfehler: Das Listenobjekt ist unbekannt !")));
				return;
			}
			
			for (int i=0;i<vE2_Components.size();i++)
			{
				E2_ComponentMAP oMap = (E2_ComponentMAP)vE2_Components.get(i);
				for (int k=0;k<oMap.get_vComponentHashKeys().size();k++)
				{
					Component oComp = (Component)oMap.get(oMap.get_vComponentHashKeys().get(k));
					if (oComp instanceof E2_CheckBoxForList)
					{
						if (this.cType.equals("ALLE")) ((E2_CheckBoxForList)oComp).setSelected(true);
						if (this.cType.equals("KEINE")) ((E2_CheckBoxForList)oComp).setSelected(false);
						if (this.cType.equals("INVERT")) ((E2_CheckBoxForList)oComp).setSelected(!((E2_CheckBoxForList)oComp).isSelected());
					}
					
				}
				
			}
			
			//2012-08-30: multiselektion anzeigen
			if (E2_SelectAllNoneInvert.this.oNaviList!=null)
			{
				E2_SelectAllNoneInvert.this.oNaviList.ShowMessageWithInfoAboutSelectedLinesAndMarkSelectedLines();
			}
		}

	}
	
	
//	/**
//	 * 2014-09-04: info zu einem Modul (nur fuer admins)
//	 * @author martin
//	 *
//	 */
//	private class ownActionSucheInfosZuNaviList extends XX_ActionAgent {
//
//		@Override
//		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
//			
//			E2_SelectAllNoneInvert  oThis = E2_SelectAllNoneInvert.this;
//			
//			String cModulKenner = oThis.oNaviList.get_MODULE_IDENTIFIER_OF_CONTAINING_MODULE();
//			
//			if (S.isFull(cModulKenner)) {
//				HashMap<String, String> hmSpalten = new HashMap<String, String>();
//				
//				E2_ComponentMAP  oMAP_List = oThis.oNaviList.get_oComponentMAP__REF();
//				
//				try {
//					for (String cKEY: oMAP_List.get_hmRealComponents().keySet()) {
//
//						//DEBUG.System_println(cKEY);
//						
//						if (S.isFull(cKEY) && S.isFull(((MyE2IF__Component)oMAP_List.get_hmRealComponents().get(cKEY)).EXT().get_cList_or_Mask_Titel())) {
//							hmSpalten.put(cKEY, ((MyE2IF__Component)oMAP_List.get_hmRealComponents().get(cKEY)).EXT().get_cList_or_Mask_Titel().CTrans());
//						}
//						
//					}
//				} catch (NullPointerException e) {
//					e.printStackTrace();
//				}
//				
//				Vector<String> vSort = new Vector<String>();
//				vSort.addAll(hmSpalten.keySet());
//				Collections.sort(vSort);
//				
//				
//				
//				MyE2_Grid oGridInnen = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
//				
//				oGridInnen.add(new MyE2_Label(new MyE2_String("Informationen zum Listenmodul:"),MyE2_Label.STYLE_NORMAL_BOLD()),2, E2_INSETS.I(2,5,2,5));
//				
//				oGridInnen.add(new MyE2_Label(new MyE2_String("Modulkenner:")),1, E2_INSETS.I(2,5,2,5));
//				oGridInnen.add(new MyE2_Label(new MyE2_String(cModulKenner),MyE2_Label.STYLE_NORMAL_BOLD()),1, E2_INSETS.I(2,5,2,5));
//				
//				oGridInnen.add_Separator(E2_INSETS.I(0,2,0,2));
//	
//				oGridInnen.add(new MyE2_Label(new MyE2_String("Titel",true)), MyE2_Grid.LAYOUT_LEFT_TOP( E2_INSETS.I(2,5,2,5), new E2_ColorDDDark(), 1));
//				oGridInnen.add(new MyE2_Label(new MyE2_String("Kennung",true)),MyE2_Grid.LAYOUT_LEFT_TOP( E2_INSETS.I(2,5,2,5), new E2_ColorDDDark(), 1));
//				
//				for (String cHASHKEY: vSort) {
//					oGridInnen.add(new MyE2_Label(new MyE2_String(hmSpalten.get(cHASHKEY),false)),1, E2_INSETS.I(2,1,2,1));
//					oGridInnen.add(new MyE2_Label(new MyE2_String(cHASHKEY,false),MyE2_Label.STYLE_NORMAL_BOLD()),1, E2_INSETS.I(2,1,2,1));
//				}
//				
//				
//				
//				E2_BasicModuleContainer  oContainer = new E2_BasicModuleContainer();
//				
//				oContainer.add(oGridInnen,E2_INSETS.I_0_0_0_0);
//				oContainer.CREATE_AND_SHOW_POPUPWINDOW(new Extent(500), new Extent(700), new MyE2_String("Sysinfos"));
//			}
//		}
//	}
	
	 
	
	
}
