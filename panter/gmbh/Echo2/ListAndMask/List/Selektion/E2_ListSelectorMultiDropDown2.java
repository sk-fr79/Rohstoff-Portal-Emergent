package panter.gmbh.Echo2.ListAndMask.List.Selektion;

import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal_NG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_LabelWithBorder;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;

/*
 * dropDownSelektion
 * wirkt wie ein Standard-DropDown-Selektor,  kann aber mehrere Auswahlen mit logischem oder zusammenfuehren 
 */
public abstract class E2_ListSelectorMultiDropDown2 extends XX_ListSelektor_EXT
{
	public static String TRENNZEICHEN_FUER_SPEICHER="@";
	
	
	private String 							cWhereStringSchablone = 		"";         //variante mit eingelagertem #WERT#
	private HashMap<String, String>         hmValuePlusWhereblock =         new HashMap<String, String>();
	
	private MyE2_SelectField                oSelFieldBasis = 				null;
	private VectorSingle 					vSelectFieldsZusatzWerte = 		new VectorSingle();
	private MyE2_Grid    		            grid4Anzeige = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	private ownButtonOpenMultiSelectPopup 	oButtonOpenMultiSelectPopup = 	new ownButtonOpenMultiSelectPopup();
	
	
	//abstracte methode, damit eine eigene popupklasse mit groessen-speicherfunktion moeglich wird
	public abstract E2_BasicModuleContainer  get_PopupContainer()  throws myException;     
	
	private XX_ActionAgent   				oAgent4Select = null;
	
	
	private boolean    						bCopyFontAndSizeOfOrigSelectField = false;
	
	private Extent  						extOfSelectComponentDropDown = new Extent(100);
	
	
	public E2_ListSelectorMultiDropDown2() throws myException	{
		super();
	}
	
	
	public E2_ListSelectorMultiDropDown2(MyE2_SelectField SelFieldBasis, String WhereStringSchablone, HashMap<String, String>    p_hmValuePlusWhereblock) throws myException	{
		super();
		this.INIT(SelFieldBasis, WhereStringSchablone, p_hmValuePlusWhereblock);
	}

	public void  INIT(MyE2_SelectField SelFieldBasis, String WhereStringSchablone, HashMap<String, String>    p_hmValuePlusWhereblock) throws myException	{
		this.oSelFieldBasis = SelFieldBasis;
		this.cWhereStringSchablone = WhereStringSchablone;
		this.hmValuePlusWhereblock = p_hmValuePlusWhereblock;
		
		//es darf nur entweder der cWhereStringSchablone oder die hmValuePlusWhereblock != null sein
		if (  (this.cWhereStringSchablone==null && this.hmValuePlusWhereblock==null) ||
			  (this.cWhereStringSchablone!=null && this.hmValuePlusWhereblock!=null) ) {
			throw new myException("E2_ListSelectorMultiDropDown2:Constuctor please exact one parameter must be null and one not: decision between: WhereStringSchablone ..and..  p_hmValuePlusWhereblock");
		}
		
		if (this.cWhereStringSchablone!=null && this.cWhereStringSchablone.indexOf("#WERT#")<0)	{
			throw new myException("E2_ListSelectorMultiDropDown2: Constuctor ! please put the string <#WERT#> to the query-block");
		}

		//es muss in der select-auswahl der erste punkt einen leeren wert geben
		if (S.isFull(this.oSelFieldBasis.get_oDataToView().get_cValueAtPosition(0))) {
			throw new myException("E2_ListSelectorMultiDropDown:Constuctor ! please put an empty value in front !!");
		}
		
		this.set_oNeutralisator(new ownSelectFieldNeutralisator_1st_is_neutral());
		
		this.fill_Grid4AnzeigeStatusSingle();
	}

	
	
	
	
	
	
	
	public MyE2_SelectField get_oSelFieldBasis() {
		return oSelFieldBasis;
	}

	public MyE2_Button get_oButtonOpenMultiSelectPopup(){
		return oButtonOpenMultiSelectPopup;
	}

	
	
	public MyE2_Grid get_grid4Anzeige(){
		return grid4Anzeige;
	}

	
	/**
	 * erstellt ein ausgerichtetes rueckgabegrid
	 * @param iBreiten
	 * @return
	 */
	public MyE2_Grid get_grid4Anzeige(int[] iBreiten)
	{
		for (int i=0;i<iBreiten.length;i++) {
			if (grid4Anzeige.getSize()>i) {
				grid4Anzeige.setColumnWidth(i, new Extent(iBreiten[i]));
			}
		}
		return grid4Anzeige;
	}


	//2012-02-14: neutralisatoren
	private class ownSelectFieldNeutralisator_1st_is_neutral extends XX_ListSelektor_Neutralisator {
		@Override
		public void set_to_Neutral() throws myException	{
			E2_ListSelectorMultiDropDown2.this.LEER_MACHEN();
		}
	}

	
	/**
	 * Gibt alle Selektierten Werte (IDs) zurück
	 * @return
	 * @throws myException
	 */
	public Vector<String> get_SelectedValues() throws myException {
		Vector<String> vRet = new Vector<String>();
		
		if (!bibALL.isEmpty(this.oSelFieldBasis.get_ActualWert()) )  {
			vRet.add( this.oSelFieldBasis.get_ActualWert() ) ;
		}
		
		if (this.vSelectFieldsZusatzWerte.size() > 0)	{
			for (String cWert:this.vSelectFieldsZusatzWerte) {
				vRet.add(cWert);
			}
		}
		return vRet;
	}
	
	
	/**
	 * Gibt alle selektierten Dropdown-Texte zurück
	 * @return
	 * @throws myException
	 */
	public Vector<String> get_SelectedText() throws myException{
		Vector<String> vRet = new Vector<String>();
		if (!bibALL.isEmpty(this.oSelFieldBasis.get_ActualWert()) ) {
			vRet.add( this.oSelFieldBasis.get_ActualView() ) ;
		}
		
		if (this.vSelectFieldsZusatzWerte.size() > 0) {
			for (String cWert:this.vSelectFieldsZusatzWerte) {
				vRet.add(cWert);
			}
		}
		return vRet;
	}
	
	
	@Override
	public String get_WhereBlock() throws myException {
		String cWhere = "";
		if (this.vSelectFieldsZusatzWerte.size()==0) {
			cWhere = this.get_WhereBlockSingleStatement(this.oSelFieldBasis.get_ActualWert());
		} else	{
			Vector<String>  vSelectBloecke = new Vector<String>();
			for (String cWert:this.vSelectFieldsZusatzWerte) {
				String cWhere2 = this.get_WhereBlockSingleStatement(cWert);
				if (S.isFull(cWhere2)) {
					vSelectBloecke.add("("+cWhere2+")");
				}
			}
			
			cWhere = "("+bibALL.Concatenate(vSelectBloecke, " OR ", " 1=1 ")+")";
			
		}
		
		//DEBUG.System_println("E2_ListSelektorMultiDropDown2: Whereblock:"+cWhere);
		return cWhere;
	}

	
	private String get_WhereBlockSingleStatement(String cWert) throws myException {
		String cWhere = "";
		if (S.isFull(cWert)) {
			if (this.cWhereStringSchablone == null && this.hmValuePlusWhereblock.size()>0) {
				cWhere = this.hmValuePlusWhereblock.get(cWert);
			} else {
				//klassisch via eingelagertem #WERT#
				cWhere = bibALL.ReplaceTeilString(this.cWhereStringSchablone,"#WERT#",cWert);
			}
		}
		return cWhere;
	}
	
	

	
	@Override
	public Component get_oComponentWithoutText() {
		return this.grid4Anzeige;
	}
	
	
	

	@Override
	public void add_ActionAgentToComponent(XX_ActionAgent oAgent) {
		
		this.oAgent4Select=oAgent;
		this.oSelFieldBasis.remove_AllActionAgents();
		
		//2014-07-03: neu
		for (XX_ActionAgent a : this.get_vAgents4ActiveComponentsBeforeSelection()){
			this.oSelFieldBasis.add_oActionAgent(a);
		}
		
		this.oSelFieldBasis.add_oActionAgent(oAgent);
		
		//2014-07-03: neu
		for (XX_ActionAgent a2 : this.get_vAgents4ActiveComponentsAfterSelection()){
			this.oSelFieldBasis.add_oActionAgent(a2);
		}
	}

	@Override
	public void doInternalCheck()
	{
	}

	


	
	
	//alles leermachen
	//2015-05-13: von private zu public
	public void LEER_MACHEN() throws myException
	{
		this.vSelectFieldsZusatzWerte.removeAllElements();
		this.oButtonOpenMultiSelectPopup.set_StatusHasMultiSelect(0);
		this.oSelFieldBasis.set_ActiveValue_OR_FirstValue(null);
		this.oSelFieldBasis.set_bEnabled_For_Edit(true);
		this.fill_Grid4AnzeigeStatusSingle();
	}
	
	
	
	private class ownButtonOpenMultiSelectPopup extends MyE2_Button
	{
		private int iAnzahlZusatzSelektionen = 0;
		
		
		public ownButtonOpenMultiSelectPopup()
		{
			super("0");
			this.setFont(new E2_FontBold());
			this.set_StatusHasMultiSelect(0);
			this.setWidth(new Extent(20));
			
//			this.setToolTipText(new MyE2_String("Die Auswahl auf weitere Bereiche erweitern").CTrans());
			this.add_oActionAgent(new actionAgentCreatePopup());
			
		}
		
		public void set_StatusHasMultiSelect(int anzahlZusatzWerte)   //anzahlZusatzWerte kann nur null oder >=2 werden
		{
			E2_ListSelectorMultiDropDown2 oThis = E2_ListSelectorMultiDropDown2.this;

			this.iAnzahlZusatzSelektionen = anzahlZusatzWerte;
			
			if (this.iAnzahlZusatzSelektionen==0)
			{
				this.setForeground(Color.BLACK);
				this.setBackground(new E2_ColorDark());
			}
			else
			{
				this.setForeground(Color.BLACK);
				this.setBackground(new E2_ColorHelpBackground());
			}
			
			this.setText(""+this.iAnzahlZusatzSelektionen+"");
			this.setAlignment(new Alignment(Alignment.CENTER, Alignment.CENTER));
			
			String cToolTipText = new MyE2_String("Die Auswahl auf weitere Bereiche erweitern").CTrans();
			
			if (anzahlZusatzWerte>=2)   //dann die selektionswahl im tooltip anzeigen
			{
				cToolTipText = new MyE2_String("Erweiterte Auswahl:").CTrans() +" \n\n";
				for (int i=0;i<oThis.vSelectFieldsZusatzWerte.size();i++)
				{
					cToolTipText = cToolTipText+oThis.oSelFieldBasis.get_DataToView().get_ViewStringToData(oThis.vSelectFieldsZusatzWerte.get(i), false)+"\n";
				}
			}
			
			this.setToolTipText(cToolTipText);
			
			
		}
	}

	
	
	
	private class actionAgentCreatePopup extends XX_ActionAgent
	{
		private Vector<MyE2_SelectField>	vSelectFieldZusatz = 					new Vector<MyE2_SelectField>();
		private E2_BasicModuleContainer     oContainerToSelectZusatzValues =	 	null;
		private MyE2_Grid 					oGridInhaltFenster = 					new MyE2_Grid(3, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());         
		
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			E2_ListSelectorMultiDropDown2 oThis = E2_ListSelectorMultiDropDown2.this;
			
			oGridInhaltFenster.setColumnWidth(0, new Extent(95,Extent.PERCENT));
			
			//wenn der Vector oThis.vSelectFieldsZusatzWerte leer ist, dann mindestens 1 SelectField mit den Werten der ursprungsselektion
			//und ein neues anbieten
			this.vSelectFieldZusatz.removeAllElements();
			
			if (oThis.vSelectFieldsZusatzWerte.size()<=1)
			{
				this.vSelectFieldZusatz.add(this.get_CopyOfSelectField(oThis.oSelFieldBasis.get_ActualWert()));
			}
			else
			{
				for (String cWert: oThis.vSelectFieldsZusatzWerte)
				{
					this.vSelectFieldZusatz.add(this.get_CopyOfSelectField(cWert));
				}
			}
		
			//immer ein neues, leeres feld dazu
			this.vSelectFieldZusatz.add(this.get_CopyOfSelectField(null));
			
			this.oContainerToSelectZusatzValues=oThis.get_PopupContainer();
			this.oContainerToSelectZusatzValues.add(this.oGridInhaltFenster, E2_INSETS.I_5_5_5_5);
			
			this.BaueGridNeu();
			
			this.oContainerToSelectZusatzValues.CREATE_AND_SHOW_POPUPWINDOW(new Extent(370), new Extent(300), new MyE2_String("Weitere Bereiche zufügen .."));
			
		}

		
		private MyE2_SelectField get_CopyOfSelectField(String cValue) throws myException
		{
			MyE2_SelectField oSelFieldNeu = new MyE2_SelectField();
			oSelFieldNeu.set_oDataToView(E2_ListSelectorMultiDropDown2.this.oSelFieldBasis.get_oDataToView());
			oSelFieldNeu.setWidth(new Extent(100,Extent.PERCENT));
			oSelFieldNeu.set_ActiveValue_OR_FirstValue(cValue);
			oSelFieldNeu.add_oActionAgent(new actionAddNewFieldBehind());
			
			//2016-07-20: der listcellrenderer wird auch uebergeben
			oSelFieldNeu.setCellRenderer(E2_ListSelectorMultiDropDown2.this.oSelFieldBasis.getCellRenderer());
			
			//2015-05-15: beim kopierten selectfield auch den font kopieren
			if (E2_ListSelectorMultiDropDown2.this.bCopyFontAndSizeOfOrigSelectField) {
				oSelFieldNeu.setFont(E2_ListSelectorMultiDropDown2.this.oSelFieldBasis.getFont());
				oSelFieldNeu.setWidth(E2_ListSelectorMultiDropDown2.this.oSelFieldBasis.getWidth());
			}
			
			return oSelFieldNeu;
		}

		
		/*
		 * agent sorgt dafuer, dass wenn beim letzte zusatzfeld der liste etwas eingefuegt wird,
		 * automatische ein neues leeres feld angehaengt wird 
		 */
		private class actionAddNewFieldBehind extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				MyE2_SelectField 		ich = 		(MyE2_SelectField)oExecInfo.get_MyActionEvent().getSource();
				actionAgentCreatePopup 	oThis2 = 	actionAgentCreatePopup.this;
				
				//bei nicht leeres selektion wird ein leerer selektor angehaengt
				if (oThis2.vSelectFieldZusatz.size()>0)
				{
					if (ich == oThis2.vSelectFieldZusatz.get(oThis2.vSelectFieldZusatz.size()-1))
					{
						if (S.isFull(ich.get_ActualWert()))
						{
							oThis2.vSelectFieldZusatz.add(oThis2.get_CopyOfSelectField(null));
							oThis2.BaueGridNeu();
						}
					}
				}
				
				// sind die beiden letzten leer, dann wird einer entfernt
				if (oThis2.vSelectFieldZusatz.size()>2)
				{
					if (ich == oThis2.vSelectFieldZusatz.get(oThis2.vSelectFieldZusatz.size()-2))
					{
						if (S.isEmpty(ich.get_ActualWert()) 	&& 
							S.isEmpty(oThis2.vSelectFieldZusatz.get(oThis2.vSelectFieldZusatz.size()-1).get_ActualWert()))
						{
							oThis2.vSelectFieldZusatz.remove(oThis2.vSelectFieldZusatz.size()-1);
							oThis2.BaueGridNeu();
						}
					}
				}
			}
		}
		
		
		private void BaueGridNeu() throws myException
		{
			
			MyE2_Button btSpeichern = 				new MyE2_Button("OK",
																	new MyE2_String("Auswahl speichern und Mehrfachselektion ausführen"),
																	null,true);

			MyE2_Button btAbbruch = 				new MyE2_Button("Abbrechen",
																	new MyE2_String("Auswahl abbrechen, nichts verändern"),
																	new ownActionCancelListe(),true);

			MyE2_Button btClear = 					new MyE2_Button("Selektion löschen",
																	new MyE2_String("Alle Felder löschen"),
																	null,true);
			
			//wichtiges hervorheben
			btClear.setIcon(E2_ResourceIcon.get_RI("multi_select_delete.png"));
			
			
			//2014-07-03: BTNClear
			btClear.add_oActionAgent(new ownActionClearMultiSelect());
			for (XX_ActionAgent a : E2_ListSelectorMultiDropDown2.this.get_vAgents4ActiveComponentsBeforeSelection()){
				btClear.add_oActionAgent(a);
			}
			btClear.add_oActionAgent(E2_ListSelectorMultiDropDown2.this.oAgent4Select);
			for (XX_ActionAgent a2 :E2_ListSelectorMultiDropDown2.this.get_vAgents4ActiveComponentsAfterSelection()){
				btClear.add_oActionAgent(a2);
			}
	
			
			
			//der speicherbutton bekommt dann noch die zusatzaction des listenneubaus
			//2014-07-03: weitere agenten vor-und nach der Selektion (Ableitung von XX_ListSelektor_EXT
			btSpeichern.setFont(new E2_FontBold());
			
			for (XX_ActionAgent a3 : E2_ListSelectorMultiDropDown2.this.get_vAgents4ActiveComponentsBeforeSelection()){
				btSpeichern.add_oActionAgent(a3);
			}
			btSpeichern.add_oActionAgent(E2_ListSelectorMultiDropDown2.this.oAgent4Select);
			for (XX_ActionAgent a4 :E2_ListSelectorMultiDropDown2.this.get_vAgents4ActiveComponentsAfterSelection()){
				btSpeichern.add_oActionAgent(a4);
			}
			// ganz am Anfang das SaveListe-Event ausführen
			btSpeichern.add_oActionAgent(new ownActionSaveListe(), true);
			
			
			
			
			
			this.oGridInhaltFenster.removeAll();
			
			for (int i=0; i<this.vSelectFieldZusatz.size();i++)
			{
				MyE2_SelectField  oSelField = this.vSelectFieldZusatz.get(i);
				this.oGridInhaltFenster.add(oSelField,1, E2_INSETS.I_0_0_5_5);
				
				this.oGridInhaltFenster.add(new ownButtonDeleteSelectField(i),1, E2_INSETS.I_0_0_5_5);
				
				if (i==this.vSelectFieldZusatz.size()-1)           //beim letzten einen add-button zufuegen
				{
					this.oGridInhaltFenster.add(new ownButtonHinzuFuegen(),1, E2_INSETS.I_0_0_5_5);
				}
				else
				{
					this.oGridInhaltFenster.add(new MyE2_Label(" "),1, E2_INSETS.I_0_0_5_5);
				}
			}
			
			int iBreiteButtons[] = {80,150,120};
			this.oGridInhaltFenster.add(new E2_ComponentGroupHorizontal_NG(btSpeichern,btClear,btAbbruch,iBreiteButtons), 1, new Insets(0,10,5,5));
		}
		
		
		private class ownButtonHinzuFuegen extends MyE2_Button
		{
			public ownButtonHinzuFuegen()
			{
				super(E2_ResourceIcon.get_RI("multi_select_add_new.png"), true);
				this.setToolTipText(new MyE2_String("Weitere Auswahlmöglichkeit hinzufügen").CTrans());
				this.add_oActionAgent(new ownActionAddSelection());
			}
		}

		private class ownButtonDeleteSelectField extends MyE2_Button
		{
			private int iNumberInVector = 0;
			public ownButtonDeleteSelectField(int NumberInVector)
			{
				super(E2_ResourceIcon.get_RI("multi_select_delete.png"), true);
				this.setToolTipText(new MyE2_String("Diese Auswahlmöglichkeit entfernen").CTrans());
				this.iNumberInVector=NumberInVector;
				
				this.add_oActionAgent(new XX_ActionAgent()
				{
					@Override
					public void executeAgentCode(ExecINFO oExecInfo) throws myException
					{
						if (actionAgentCreatePopup.this.vSelectFieldZusatz.size()>2)    //falls noch mindestens 2 vorhanden sind, dann das select-field loeschen,
						{
							actionAgentCreatePopup.this.vSelectFieldZusatz.remove(ownButtonDeleteSelectField.this.iNumberInVector);
						}
						else                    										 //sonst das drop-down-feld leermachen
						{
							actionAgentCreatePopup.this.vSelectFieldZusatz.get(ownButtonDeleteSelectField.this.iNumberInVector).set_ActiveValue_OR_FirstValue(null);
						}
						actionAgentCreatePopup.this.BaueGridNeu();
							
					}
				});
				
			}
		}

		
		
		
		
		private class ownActionSaveListe extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				E2_ListSelectorMultiDropDown2 	oThis = 	E2_ListSelectorMultiDropDown2.this;
				actionAgentCreatePopup 			oThis2 = 	actionAgentCreatePopup.this;
				VectorSingle  vZusatzWerte = new VectorSingle();
				
				for (MyE2_SelectField  oSelField: oThis2.vSelectFieldZusatz)
				{
					if (S.isFull(oSelField.get_ActualWert()))
					{
						vZusatzWerte.add(oSelField.get_ActualWert());
					}
				}
				
				oThis.vSelectFieldsZusatzWerte.removeAllElements();
				if (vZusatzWerte.size()==0)
				{
					oThis.oButtonOpenMultiSelectPopup.set_StatusHasMultiSelect(0);
					oThis.oSelFieldBasis.set_ActiveValue_OR_FirstValue(null);
					oThis.oSelFieldBasis.set_bEnabled_For_Edit(true);
					oThis.fill_Grid4AnzeigeStatusSingle();
				}
				else if (vZusatzWerte.size()==1)
				{
					oThis.oButtonOpenMultiSelectPopup.set_StatusHasMultiSelect(0);
					oThis.oSelFieldBasis.set_ActiveValue(vZusatzWerte.get(0));
					oThis.oSelFieldBasis.set_bEnabled_For_Edit(true);
					oThis.fill_Grid4AnzeigeStatusSingle();
				}
				else
				{
					oThis.vSelectFieldsZusatzWerte.addAll(vZusatzWerte);
					oThis.oButtonOpenMultiSelectPopup.set_StatusHasMultiSelect(vZusatzWerte.size());
					oThis.oSelFieldBasis.set_ActiveValue_OR_FirstValue(null);
					oThis.oSelFieldBasis.set_bEnabled_For_Edit(false);
					oThis.fill_Grid4AnzeigeStatusMulti();
				}

				oThis2.oContainerToSelectZusatzValues.CLOSE_AND_DESTROY_POPUPWINDOW(true);
			}
		}
		
		
		private class ownActionClearMultiSelect extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				E2_ListSelectorMultiDropDown2.this.LEER_MACHEN();
				E2_ListSelectorMultiDropDown2.this.fill_Grid4AnzeigeStatusSingle();
				actionAgentCreatePopup.this.oContainerToSelectZusatzValues.CLOSE_AND_DESTROY_POPUPWINDOW(true);
			}
		}

		
		
		private class ownActionCancelListe extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				actionAgentCreatePopup 			oThis2 = 	actionAgentCreatePopup.this;
				oThis2.oContainerToSelectZusatzValues.CLOSE_AND_DESTROY_POPUPWINDOW(true);
			}
		}
		
		
		private class ownActionAddSelection extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				actionAgentCreatePopup 			oThis2 = 	actionAgentCreatePopup.this;
				oThis2.vSelectFieldZusatz.add(oThis2.get_CopyOfSelectField(null));
				
				oThis2.BaueGridNeu();
			}
		}

		
	}






	//2014-01-09: speichermoeglichkeit/wiedereinstellmoeglichkeit eines selektors einbauen
	public String get_MemStringStatusSelektor() throws myException  {
		String cRueck = "";
		if (this.vSelectFieldsZusatzWerte.size()>=2) {
			cRueck = E2_ListSelectorMultiDropDown2.TRENNZEICHEN_FUER_SPEICHER;
			for (String cWert:this.vSelectFieldsZusatzWerte) {
				cRueck=cRueck+cWert+E2_ListSelectorMultiDropDown2.TRENNZEICHEN_FUER_SPEICHER;
			}
		}
		else {
			cRueck = this.oSelFieldBasis.get_ActualWert();
		}
		return cRueck;	
	}
	
	
	public void set_MemStringStatusSelektor(String cWerteString) throws myException {
		this.LEER_MACHEN();

		if (S.isFull(cWerteString)) {
			Vector<String> vWerte = bibALL.TrenneZeile(cWerteString, E2_ListSelectorMultiDropDown2.TRENNZEICHEN_FUER_SPEICHER);

			if (vWerte.size()==1) {
				this.oSelFieldBasis.set_ActiveValue_OR_FirstValue(cWerteString);
				this.oSelFieldBasis.set_bEnabled_For_Edit(true);
				this.fill_Grid4AnzeigeStatusSingle();
			} else {
				this.vSelectFieldsZusatzWerte.addAll(vWerte);
				this.oButtonOpenMultiSelectPopup.set_StatusHasMultiSelect(this.vSelectFieldsZusatzWerte.size());
				this.oSelFieldBasis.set_bEnabled_For_Edit(false);
				this.fill_Grid4AnzeigeStatusMulti();
			}
		}
	}
	public boolean get_bCopyFontAndSizeOfOrigSelectField()
	{
		return this.bCopyFontAndSizeOfOrigSelectField;
	}
	public void set_bCopyFontAndSizeOfOrigSelectField(boolean CopyFontAndSizeOfOrigSelectField)
	{
		this.bCopyFontAndSizeOfOrigSelectField = CopyFontAndSizeOfOrigSelectField;
	}
	
	

	protected void fill_Grid4AnzeigeStatusMulti() {
		this.get_grid4Anzeige().removeAll();
		this.get_grid4Anzeige().setSize(2);
		MyE2_LabelWithBorder oLab = new MyE2_LabelWithBorder(new MyE2_String("<Mehrfach>"),MyE2_LabelWithBorder.STYLE_BORDER_DDDARKCOLOR(),MyE2_LabelWithBorder.CONTENT_LAYOUT_CENTER);
		oLab.setWidth(this.get_oSelFieldBasis().getWidth());
		oLab.get_ownLabel().setFont(new E2_FontBold(-2));
		this.get_grid4Anzeige().add(oLab,E2_INSETS.I_0_0_5_0);
		this.get_grid4Anzeige().add(this.get_oButtonOpenMultiSelectPopup(),E2_INSETS.I_0_0_0_0);
		oLab.get_ownLabel().setToolTipText(this.get_oButtonOpenMultiSelectPopup().getToolTipText());
		
		oLab.setWidth(this.extOfSelectComponentDropDown);
		
	}

	protected void fill_Grid4AnzeigeStatusSingle() {
		this.get_grid4Anzeige().removeAll();
		this.get_grid4Anzeige().setSize(2);
		this.get_grid4Anzeige().add(this.get_oSelFieldBasis(),E2_INSETS.I_0_0_5_0);
		this.get_grid4Anzeige().add(this.get_oButtonOpenMultiSelectPopup(),E2_INSETS.I_0_0_0_0);
		
		this.get_oSelFieldBasis().setWidth(this.extOfSelectComponentDropDown);
	}


	public Extent get_extOfSelectComponentDropDown() {
		return extOfSelectComponentDropDown;
	}


	public void set_extOfSelectComponentDropDown(Extent extOfSelectComponentDropDown) {
		this.extOfSelectComponentDropDown = extOfSelectComponentDropDown;
		this.get_oSelFieldBasis().setWidth(this.extOfSelectComponentDropDown);

	}

	
	
}
