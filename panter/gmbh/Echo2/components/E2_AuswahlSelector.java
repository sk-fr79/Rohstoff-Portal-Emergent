package panter.gmbh.Echo2.components;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.list.ListSelectionModel;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.dataToView;
import panter.gmbh.indep.exceptions.myException;

public class E2_AuswahlSelector extends MyE2_Row
{
	private cMultiSelectListBox oSelQuelle = new cMultiSelectListBox();
	private cMultiSelectListBox oSelZiel = new cMultiSelectListBox();
	private MyE2_Button  btnSelectAll = new MyE2_Button(E2_ResourceIcon.get_RI("to_right_dbl.png"),E2_ResourceIcon.get_RI("to_right_dbl.png"));
	private MyE2_Button  btnClearAll = new MyE2_Button(E2_ResourceIcon.get_RI("to_left_dbl.png"),E2_ResourceIcon.get_RI("to_left_dbl.png"));
	
	private MyE2_Button  btnAddSelected = new MyE2_Button(E2_ResourceIcon.get_RI("to_right.png"),E2_ResourceIcon.get_RI("to_right.png"));
	private MyE2_Button  btnClearSelected = new MyE2_Button(E2_ResourceIcon.get_RI("to_left.png"),E2_ResourceIcon.get_RI("to_left.png"));
	
//	private dataToView  dataWerteLinks = null;
//	private dataToView  dataWerteRechts = null;

	
	private dataToView  data2View_REFERENZ_LEFT = null;
	private dataToView  data2View_REFERENZ_RIGHT = null;
	
	
	
	public E2_AuswahlSelector( 		String[][]  WerteLinks,
									String[][]  WerteRechts,
									Extent SpaltenBreite,
									Extent SpaltenHoehe,
									Component  ComponentMitte
									) throws myException 
	{
		this(WerteLinks, WerteRechts, SpaltenBreite, SpaltenHoehe, ComponentMitte,false);
	}
	
	
	public E2_AuswahlSelector(		String[][]  WerteLinks,
									String[][]  WerteRechts,
									Extent SpaltenBreite,
									Extent SpaltenHoehe,
									Component  ComponentMitte,
									boolean bShowMoveAllButtons
									) throws myException
	{
		super(MyE2_Row.STYLE_NO_BORDER_NO_INSETS_LEFT_TOP());
		
		// Buttons definieren
		btnAddSelected.setToolTipText(new MyE2_String("Ausgewählte Einträge hinzufügen").CTrans());
		btnAddSelected.setBorder(new Border(1, E2_ColorBase.LIGHTGRAY, Border.STYLE_SOLID ));
		
		btnSelectAll.setToolTipText(new MyE2_String("Alle Einträge hinzufügen").CTrans());
		btnSelectAll.setBorder(new Border(1, E2_ColorBase.LIGHTGRAY, Border.STYLE_SOLID ));
		
		btnClearAll.setToolTipText(new MyE2_String("Alle Einträge löschen").CTrans());
		btnClearAll.setBorder(new Border(1, E2_ColorBase.LIGHTGRAY, Border.STYLE_SOLID ));
		
		btnClearSelected .setToolTipText(new MyE2_String("Ausgewählte Einträge löschen").CTrans());
		btnClearSelected.setBorder(new Border(1, E2_ColorBase.LIGHTGRAY, Border.STYLE_SOLID ));
		

		dataToView dataWerteLinks = 			new dataToView(WerteLinks,false,bibE2.get_CurrSession());
		dataToView dataWerteRechts = 			new dataToView(WerteRechts,false,bibE2.get_CurrSession());
		
		this.data2View_REFERENZ_LEFT = 	new dataToView(WerteLinks,false,bibE2.get_CurrSession());
		this.data2View_REFERENZ_RIGHT = new dataToView(WerteRechts,false,bibE2.get_CurrSession());
	
		if 		(this.data2View_REFERENZ_LEFT.size()==0)
		{
			this.data2View_REFERENZ_LEFT = this.data2View_REFERENZ_RIGHT;
		}
		else if (this.data2View_REFERENZ_RIGHT.size()==0)
		{
			this.data2View_REFERENZ_RIGHT = this.data2View_REFERENZ_LEFT;
		}
		
		
		
		
		oSelQuelle.set_oDataToView(dataWerteLinks, true);
		oSelZiel.set_oDataToView(dataWerteRechts, true);
		
		oSelQuelle.setWidth(SpaltenBreite);
		oSelQuelle.setHeight(SpaltenHoehe);
		oSelZiel.setWidth(SpaltenBreite);
		oSelZiel.setHeight(SpaltenHoehe);
		
		this.add(this.oSelQuelle, E2_INSETS.I_0_0_0_0);
		if (ComponentMitte!=null)
		{
			this.add(ComponentMitte,MyE2_Row.LAYOUT_CENTER(E2_INSETS.I_5_0_0_0));
		}

		// wenn man die Buttons anzeigen will, einfach noch dazwischen hängen
		if (bShowMoveAllButtons){
			MyE2_Column colButtons = new MyE2_Column();
			colButtons.add( btnAddSelected,E2_INSETS.I_2_2_2_2);
			colButtons.add (btnSelectAll,E2_INSETS.I_2_2_2_0);
			colButtons.add (btnClearAll,E2_INSETS.I_2_2_2_2);
			colButtons.add (btnClearSelected,E2_INSETS.I_2_2_2_0);
			
//			btnSelectAll.add_oActionAgent(new actionAlleLinksRechts());
//			btnClearAll.add_oActionAgent(new actionAlleLinksRechts());

			btnSelectAll.add_oActionAgent(new actionFuerLinksRechts(true,false,false));
			btnClearAll.add_oActionAgent(new actionFuerLinksRechts(false,false,false));

			
			btnAddSelected.add_oActionAgent(new actionFuerLinksRechts(true,true,false));
			btnClearSelected.add_oActionAgent(new actionFuerLinksRechts(false,true,false));
			
			this.add(colButtons, E2_INSETS.I_5_0_0_0);
		}
		
		
		this.add(this.oSelZiel, E2_INSETS.I_5_0_0_0);
		
		this.oSelQuelle.RemoveActualSelection();
		this.oSelZiel.RemoveActualSelection();
		 
		
		
		this.bewerteActionAgents_Selekt_listen();
		
		
	}
	

	
	public dataToView get_DataToView_VORRAT()
	{
		//return this.dataWerteLinks;
		return this.oSelQuelle.get_DataToView();
	}
	
	public dataToView get_DataToView_AUSWAHL()
	{
		//return this.dataWerteRechts;
		return this.oSelZiel.get_DataToView();
	}
	
	



	/*
	 * eintrag nach rechts
	 */
	private class actionFuerLinksRechts extends XX_ActionAgent
	{
		
		private boolean bAddEntries = false;
		
		private boolean bMoveOnlyActive = true;         //wenn false, dann werden alle bewegt
		private boolean bUseInListClick = false; 		//wird die liste geklickt, wenn weniger als 6 eintraege da sind, dann wird sofort bewegt 

		/**
		 * 
		 * true, dann werden Einträge hinzugefügt (von Links nach rechts) sonst weggenommen (von Rechts nach links) 
		 * @author manfred
		 * @date   07.02.2012
		 * @param addEntries
		 * @param bOnlyActives
		 * @param bUseInList
		 */
		public actionFuerLinksRechts(boolean addEntries, boolean bOnlyActives, boolean bUseInList) 
		{
			this.bAddEntries = 		addEntries;
			this.bMoveOnlyActive = 	bOnlyActives;
			this.bUseInListClick = 	bUseInList;
		}
		
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			E2_AuswahlSelector oThis = E2_AuswahlSelector.this;

			dataToView  refLinks = null;
			dataToView  refRechts = null;
			
			cMultiSelectListBox o_SelQuelle = null;
			cMultiSelectListBox o_SelZiel = null;
			
			
			
			if (bAddEntries )
			{
				// add
				o_SelQuelle = oThis.oSelQuelle;
				o_SelZiel = oThis.oSelZiel;
				
				refLinks = oThis.data2View_REFERENZ_LEFT;
				refRechts = oThis.data2View_REFERENZ_RIGHT;

			} 
			else 
			{
				// del
				o_SelQuelle = oThis.oSelZiel;
				o_SelZiel = oThis.oSelQuelle;

				refLinks = oThis.data2View_REFERENZ_RIGHT;
				refRechts = oThis.data2View_REFERENZ_LEFT;
				
			}
				
			
			//der direkte klick auf die listen wirkt nur bei listen, die komplett einsehbar sind
			if (this.bUseInListClick)
			{
				if (o_SelQuelle.get_oDataToView().size()>8)
				{
					return;               //nur bei kleinen auswahlen direkt klicken
				}
			}
			
			
			// hin ...
			int[] iSelIdxs = null;
			if (this.bMoveOnlyActive)
			{
				iSelIdxs = o_SelQuelle.getSelectedIndices();
			}
			else
			{
				iSelIdxs= o_SelQuelle.get_all_Indices();
			}
			
			// felder dazufügen
			for (int i=0; i < iSelIdxs.length; i++ ){
				int iAuswahl = iSelIdxs[i];
				dataToView.zuOrdnung oZuordnung = o_SelQuelle.get_DataToView().get_zoAtPosition(iAuswahl);
				o_SelZiel.get_DataToView().add(0,oZuordnung);        //immer oben anfuegen
				
			}
			
			// Felder auf der anderen Seite wegnehmen
			// Rückwärts rausnehmen, sonst nimmer er immer die falschen Einträge durch die Indizierung
			for (int i=iSelIdxs.length-1; i >= 0; i-- ){
				int iAuswahl = iSelIdxs[i];
				dataToView.zuOrdnung oZuordnung = o_SelQuelle.get_DataToView().get_zoAtPosition(iAuswahl);
				o_SelQuelle.get_DataToView().remove(oZuordnung);
			}
			
			
			
			//2012-02-09: dafuer sorgen, dass die anzeigen in der alten reihenfolge bleiben
//			dataToView  dwNeuQuelle = 	get_DataToView_in_original_order(o_SelQuelle.get_DataToView(),refLinks);
//			dataToView  dwNeuZiel = 	get_DataToView_in_original_order(o_SelZiel.get_DataToView(),refRechts);

			o_SelQuelle.set_oDataToView(get_DataToView_in_original_order(o_SelQuelle.get_DataToView(),refLinks), false);
			o_SelZiel.set_oDataToView(get_DataToView_in_original_order(o_SelZiel.get_DataToView(),refRechts), false);

			o_SelQuelle.RemoveActualSelection();
			o_SelZiel.RemoveActualSelection();
			
			
//			System.out.println("Linke Box: "+oThis.oSelQuelle.get_oDataToView().size());
//			System.out.println("Rechte Box: "+oThis.oSelZiel.get_oDataToView().size());
			
			
			//jetzt die actionAgents der listen neu bewerten
			oThis.bewerteActionAgents_Selekt_listen();
		}
		
		
	}


	
	private void bewerteActionAgents_Selekt_listen()
	{
		this.oSelQuelle.remove_AllActionAgents();
		this.oSelZiel.remove_AllActionAgents();
		
		if (this.oSelQuelle.get_oDataToView().size()<=7)
		{
			oSelQuelle.add_oActionAgent(new actionFuerLinksRechts(true,true,true));
		}
		if (this.oSelZiel.get_oDataToView().size()<=7)
		{
			oSelZiel.add_oActionAgent(new actionFuerLinksRechts(false,true,true));
		}
	}
	

	private dataToView get_DataToView_in_original_order(dataToView oDW_Actual, dataToView oDW_orig)
	{
		
		if (oDW_orig!=null && oDW_orig.size()>0)
		{

			dataToView  dwRueck = new dataToView(false, bibE2.get_CurrSession());
			
			for (int i=0;i<oDW_orig.size();i++)                 //kopiert alle aus der aktuellen liste in die rueckgabe-liste, allerdings in der alten reihenfolge  
			{
				if (oDW_Actual.get_bHasData(oDW_orig.get(i).get_cData()))
				{
					dwRueck.add(oDW_orig.get(i));
				}
				
			}
			
			//jetzt noch sicherstellen, dass keine vergessen wurden
			for (int i=0;i<oDW_Actual.size();i++)
			{
				if (!dwRueck.get_bHasData(oDW_Actual.get(i).get_cData()))
				{
					dwRueck.add(oDW_Actual.get(i));
				}
			}
		
			return dwRueck;
		}
		else
		{
			return oDW_Actual;
		}
		
	}
	
	

//	/*
//	 * ActionAgent, um alle nach links und Rechts zu schieben
//	 */
//	private class actionAlleLinksRechts extends XX_ActionAgent
//	{
//		@Override
//		public void executeAgentCode(ExecINFO execInfo) throws myException
//		{
//			E2_AuswahlSelector oThis = E2_AuswahlSelector.this;
//
//			MyE2_Button o_Quelle = (MyE2_Button)execInfo.get_MyActionEvent().getSource();
//			
//			
//			MyE2_ListBox o_SelQuelle = null;
//			MyE2_ListBox o_SelZiel = null;
//			
//			// alles selektieren, d.h. nach rechts schieben
//			int iAuswahl = 0;
//			int iCount = 0;
//			
//			if (o_Quelle==oThis.btnSelectAll)
//			{
//				o_SelQuelle = oThis.oSelQuelle;
//				o_SelZiel = oThis.oSelZiel;
//				iCount = o_SelQuelle.get_DataToView().size();
//				for (iAuswahl = 0;iAuswahl < iCount; iAuswahl++){
//					dataToView.zuOrdnung oZuordnung = o_SelQuelle.get_DataToView().get_zoAtPosition(0);
//					o_SelQuelle.get_DataToView().remove(oZuordnung);
//					o_SelZiel.get_DataToView().add(0,oZuordnung);        //immer oben anfuegen
//				}
//
//				java.util.Collections.sort(o_SelZiel.get_DataToView());   // immer sortieren
//				o_SelQuelle.set_oDataToView(o_SelQuelle.get_DataToView(), false);
//				o_SelZiel.set_oDataToView(o_SelZiel.get_DataToView(), false);
//				
//				o_SelQuelle.RemoveActualSelection();
//				o_SelZiel.RemoveActualSelection();
//			}
//			else
//			{
//				// ... und her
//				o_SelQuelle = oThis.oSelZiel;
//				o_SelZiel= oThis.oSelQuelle;
//				
//				
//				iAuswahl = 0;
//				iCount = o_SelQuelle.get_DataToView().size();
//				for (iAuswahl = 0;iAuswahl < iCount; iAuswahl++){
//					dataToView.zuOrdnung oZuordnung = o_SelQuelle.get_DataToView().get_zoAtPosition(0);
//					o_SelQuelle.get_DataToView().remove(oZuordnung);
//					o_SelZiel.get_DataToView().add(oZuordnung);   
//				}				
//				java.util.Collections.sort(o_SelZiel.get_DataToView());   // quelle immer sortieren
//				o_SelQuelle.set_oDataToView(o_SelQuelle.get_DataToView(), false);
//				o_SelZiel.set_oDataToView(o_SelZiel.get_DataToView(), false);
//				o_SelQuelle.RemoveActualSelection();
//				o_SelZiel.RemoveActualSelection();
//			}
//		}
//	}



	public MyE2_ListBox get_oSelQuelle()
	{
		return oSelQuelle;
	}



	public MyE2_ListBox get_oSelZiel()
	{
		return oSelZiel;
	}

	
	public MyE2_Button get_oButtonSelectAll(){
		return btnSelectAll;
	}
	
	
	public MyE2_Button get_oButtonClearAll(){
		return btnClearAll;
	}
	
	
	public MyE2_Button get_oButtonAddSelected(){
		return btnAddSelected;
	}
	
	
	public MyE2_Button get_oButtonClearSelected(){
		return btnClearSelected;
	}
	
		
	
	
	/**
	 * ermöglicht, einen Wert von aussen zu Setzen
	 * Ist die übergebene ID nicht in der möglichen Auswahl, 
	 * wird der Aufruf ignoriert.
	 * Wenn man mehrere Einträge setzen will, muss die Methode mehrmals aufgerufen werden
	 * @param id
	 * @throws myException 
	 */
	public void addSelectionByID(String id) 
	{
		MyE2_ListBox o_SelQuelle = oSelQuelle;
		MyE2_ListBox o_SelZiel = oSelZiel;
		int iCount = o_SelQuelle.get_DataToView().size();
		for (int iAuswahl = 0;iAuswahl < iCount; iAuswahl++)
		{
			dataToView.zuOrdnung oZuordnung;
			try 
			{
				oZuordnung = o_SelQuelle.get_DataToView().get_zoAtPosition(iAuswahl);
				if (oZuordnung.get_cData().equals(id))
				{
					o_SelQuelle.get_DataToView().remove(oZuordnung);
					o_SelZiel.get_DataToView().add(0,oZuordnung);        //immer oben anfuegen
					break;
				}
			} 
			catch (myException e) 
			{
				// zuordnung ist fehlgelaufen
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyString("Keinen Eintrag mit der ID " + id + " gefunden." )), false);
			}
		}

//		java.util.Collections.sort(o_SelZiel.get_DataToView());   // immer sortieren
		dataToView  refLinks = this.data2View_REFERENZ_LEFT;
		dataToView  refRechts = this.data2View_REFERENZ_RIGHT;

		dataToView  dwNeuQuelle = 	get_DataToView_in_original_order(o_SelQuelle.get_DataToView(),refLinks);
		dataToView  dwNeuZiel = 	get_DataToView_in_original_order(o_SelZiel.get_DataToView(),refRechts);

		
		o_SelQuelle.set_oDataToView(dwNeuQuelle, false);
		o_SelZiel.set_oDataToView(dwNeuZiel, false);
		
		o_SelQuelle.RemoveActualSelection();
		o_SelZiel.RemoveActualSelection();
		
		
		this.bewerteActionAgents_Selekt_listen();

	}
	

	/**
	 * Listen neu aufbauen
	 * @author martin
	 * @date 2014-03-11
	 *  
	 */
	public void RefreshSelektor() {
		this.oSelQuelle.set_oDataToView(get_DataToView_in_original_order(this.oSelQuelle.get_DataToView(),this.data2View_REFERENZ_LEFT), false);
		this.oSelZiel.set_oDataToView(get_DataToView_in_original_order(this.oSelZiel.get_DataToView(),this.data2View_REFERENZ_RIGHT), false);

		this.oSelQuelle.RemoveActualSelection();
		this.oSelZiel.RemoveActualSelection();

	}
	
	
	/**
	 * Multiselektor in der Listbox
	 * @author manfred
	 * @date   07.02.2012
	 */
	private class cMultiSelectListBox extends MyE2_ListBox{
		
		public cMultiSelectListBox() {
			super();
			this.setSelectionMode(ListSelectionModel.MULTIPLE_SELECTION);
		}
	}
	
}
