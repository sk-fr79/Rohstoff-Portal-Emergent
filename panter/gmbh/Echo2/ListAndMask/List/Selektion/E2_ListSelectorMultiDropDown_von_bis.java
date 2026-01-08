package panter.gmbh.Echo2.ListAndMask.List.Selektion;

import java.util.ArrayList;
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
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal_NG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

/*
 * dropDownSelektion
 * wirkt wie ein Standard-DropDown-Selektor,  kann aber mehrere Auswahlen mit logischem oder zusammenfuehren 
 */
public abstract class E2_ListSelectorMultiDropDown_von_bis extends XX_ListSelektor
{
	private String 							cWhereStringSchablone = "";         //variante mit eingelagertem #WERT1# und #WERT2#
	
	private SelectFieldPair                 oSelectPairBasis = 		null;
	
	private Vector<ZusatzwertPaar>		   	vZusatzWerte = 			new Vector<ZusatzwertPaar>();
	
	private MyE2_Grid    		            grid4Anzeige = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	private ownButtonOpenMultiSelectPopup 	oButtonOpenMultiSelectPopup = 	new ownButtonOpenMultiSelectPopup();
	
	//abstracte methode, damit eine eigene popupklasse mit groessen-speicherfunktion moeglich wird
	public abstract E2_BasicModuleContainer  get_PopupContainer()  throws myException;     
	public abstract void 					fill_Grid4AnzeigeStatusMulti(); 
	public abstract void 					fill_Grid4AnzeigeStatusSingle();
	
	//der Agent des SelectionVectors
	private XX_ActionAgent   				oAgent4Select = null;
	
	
	//die buttons fuer das popup-Fenster
	private MyE2_Button btSpeichern = 		new MyE2_Button("OK",
											new MyE2_String("Auswahl speichern und Mehrfachselektion ausführen"),
											null,true);

	private MyE2_Button btAbbruch = 		new MyE2_Button("Abbrechen",
											new MyE2_String("Auswahl abbrechen, nichts verändern"),
											null,true);

	private MyE2_Button btClear = 			new MyE2_Button("Selektion löschen",
											new MyE2_String("Alle Felder löschen"),
											null,true);

	
	/**
	 * @param SelFieldBasis wird als blaupause fuer die selektionen genutzt
	 * @param cwhereString: string im wherebereich mit eingelagertem #WERT1#, z.b. <SPRACHE=#WERT#>
	 * @throws myException 
	 */
	public E2_ListSelectorMultiDropDown_von_bis(	MyE2_SelectField 	SelFieldBasis_von, 
													MyE2_SelectField 	SelFieldBasis_bis, 
													String 				WhereStringSchablone) throws myException
	{
		super();
		
		//wichtiges hervorheben
		this.btSpeichern.setFont(new E2_FontBold());
		this.btClear.setIcon(E2_ResourceIcon.get_RI("multi_select_delete.png"));
		
		this.pruefe_schablone_und_setze_whereStatement(WhereStringSchablone);

		this.oSelectPairBasis = new SelectFieldPair(SelFieldBasis_von, SelFieldBasis_bis);

		//es muss in der select-auswahl der erste punkt einen leeren wert geben
		if (S.isFull(this.oSelectPairBasis.SelFieldVon.get_oDataToView().get_cValueAtPosition(0))  || 
			S.isFull(this.oSelectPairBasis.SelFieldBis.get_oDataToView().get_cValueAtPosition(0)))
		{
			throw new myException("E2_ListSelectorMultiDropDown_von_bis:Constuctor ! please put an empty value in front !!");
		}

		this.set_oNeutralisator(new ownSelectFieldNeutralisator());
		this.fill_Grid4AnzeigeStatusSingle();
	}

	

	/**
	 * 
	 * @param cSQL_Query_von
	 * @param cSQL_Query_bis
	 * @param WhereStringSchablone_von
	 * @param WhereStringSchablone_bis
	 * @throws myException
	 */
	public E2_ListSelectorMultiDropDown_von_bis(String cSQL_Query_von,String cSQL_Query_bis, String WhereStringSchablone) throws myException
	{
		//wichtiges hervorheben
		this.btSpeichern.setFont(new E2_FontBold());
		this.btClear.setIcon(E2_ResourceIcon.get_RI("multi_select_delete.png"));

		
		this.pruefe_schablone_und_setze_whereStatement(WhereStringSchablone);
		
		this.oSelectPairBasis = new SelectFieldPair(this.baue_selectField(cSQL_Query_von), this.baue_selectField(cSQL_Query_bis));

		//es muss in der select-auswahl der erste punkt einen leeren wert geben
		if (S.isFull(this.oSelectPairBasis.SelFieldVon.get_oDataToView().get_cValueAtPosition(0))  || 
			S.isFull(this.oSelectPairBasis.SelFieldVon.get_oDataToView().get_cValueAtPosition(0)))
		{
			throw new myException("E2_ListSelectorMultiDropDown_von_bis:Constuctor ! please put an empty value in front !!");
		}

		this.set_oNeutralisator(new ownSelectFieldNeutralisator());
		this.fill_Grid4AnzeigeStatusSingle();
	}
	

	/**
	 * 
	 * @param cSQL_Query_von_bis
	 * @param WhereStringSchablone_von
	 * @param WhereStringSchablone_bis
	 * @throws myException
	 */
	public E2_ListSelectorMultiDropDown_von_bis(String cSQL_Query_von_bis, String WhereStringSchablone) throws myException
	{
		//wichtiges hervorheben
		this.btSpeichern.setFont(new E2_FontBold());
		this.btClear.setIcon(E2_ResourceIcon.get_RI("multi_select_delete.png"));
		
		this.pruefe_schablone_und_setze_whereStatement(WhereStringSchablone);
		
		this.oSelectPairBasis = new SelectFieldPair(this.baue_selectField(cSQL_Query_von_bis), this.baue_selectField(cSQL_Query_von_bis));
		//es muss in der select-auswahl der erste punkt einen leeren wert geben
		if (S.isFull(this.oSelectPairBasis.SelFieldVon.get_oDataToView().get_cValueAtPosition(0))  || 
			S.isFull(this.oSelectPairBasis.SelFieldVon.get_oDataToView().get_cValueAtPosition(0)))
		{
			throw new myException("E2_ListSelectorMultiDropDown_von_bis:Constuctor ! please put an empty value in front !!");
		}

		this.set_oNeutralisator(new ownSelectFieldNeutralisator());
		this.fill_Grid4AnzeigeStatusSingle();
	}

	
	private void pruefe_schablone_und_setze_whereStatement(String WhereStringSchablone) throws myException
	{
		this.cWhereStringSchablone = WhereStringSchablone;
		
		if (this.cWhereStringSchablone.indexOf("#WERT1#")<0 || this.cWhereStringSchablone.indexOf("#WERT2#")<0 )
		{
			throw new myException("E2_ListSelectorMultiDropDown_von_bis:Constuctor ! please put the strings <#WERT1#> and <#WERT2#> to the query-block");
		}

	}
	
	private MyE2_SelectField baue_selectField(String cSQL_Query) throws myException
	{
		return new MyE2_SelectField(E2_ListSelectorMultiDropDown_von_bis.baue_SelectFieldArray(cSQL_Query),null,false);
	}

	
	public static String[][] baue_SelectFieldArray(String cSQL_Query) throws myException
	{
		String[][] cRueck = bibDB.EinzelAbfrageInArray(cSQL_Query,"");

		String[][] cHelp;
		if (cRueck== null)
			throw new myException("E2_ListSelectorMultiDropDown ! Query gives null-result!");
		
		if (cRueck.length==0)
		{
			cHelp = new String[1][2];
			cHelp[0][0]="--";
			cHelp[0][1]="";
		}
		else
		{
			if (cRueck[0].length!=2)
				throw new myException("E2_ListSelectorMultiDropDown ! Please set query with exact 2 columns: View and Value");
			
			cHelp = new String[cRueck.length+1][2];
			cHelp[0][0]="--";
			cHelp[0][1]="";
			for (int i=0;i<cRueck.length;i++)
			{
				cHelp[i+1][0]=cRueck[i][0];
				cHelp[i+1][1]=cRueck[i][1];
			}
		}
		
		return cHelp;
	}
	
	
	
	public MyE2_SelectField get_oSelFieldBasis_von()
	{
		return oSelectPairBasis.SelFieldVon;
	}

	public MyE2_SelectField get_oSelFieldBasis_bis()
	{
		return oSelectPairBasis.SelFieldBis;
	}

	
	public MyE2_Button get_oButtonOpenMultiSelectPopup()
	{
		return oButtonOpenMultiSelectPopup;
	}

	public MyE2_Grid get_grid4Anzeige()
	{
		return grid4Anzeige;
	}

	public MyE2_Button get_btSpeichern()
	{
		return btSpeichern;
	}
	public MyE2_Button get_btAbbruch()
	{
		return btAbbruch;
	}
	public MyE2_Button get_btClear()
	{
		return btClear;
	}

	//2012-02-14: neutralisatoren
	private class ownSelectFieldNeutralisator extends XX_ListSelektor_Neutralisator
	{
		@Override
		public void set_to_Neutral() throws myException
		{
			E2_ListSelectorMultiDropDown_von_bis.this.LEER_MACHEN();
		}
	}

	
	
	@Override
	public String get_WhereBlock() throws myException
	{
		String cWhere = "";
		
		if (this.vZusatzWerte.size()==0)
		{
			cWhere = this.get_WhereBlockSingleStatement(this.oSelectPairBasis.SelFieldVon.get_ActualWert(), 
														this.oSelectPairBasis.SelFieldBis.get_ActualWert());
		}
		else
		{
			Vector<String>  vSelectBloecke = new Vector<String>();
			for (ZusatzwertPaar cWerte: this.vZusatzWerte)
			{
				String cWhere2 = this.get_WhereBlockSingleStatement(cWerte.WertVon,cWerte.WertBis);
				vSelectBloecke.add(cWhere2);
			}
			cWhere = "("+bibALL.Concatenate(vSelectBloecke, " OR ", " 1=1 ")+")";
		}
		return cWhere;
	}



	/**
	 * gibt ein Array zurück mit den in den SelectFields ausgewählten Werten
	 * @author manfred
	 * @date 23.10.2017
	 *
	 * @return
	 * @throws myException 
	 */
	public ArrayList<String[]> get_ArrayOfSelectedValues() throws myException{
		ArrayList<String[]> arrRet = new ArrayList<>();
		String[] values ;
		
		int count = this.vZusatzWerte.size();
		if (count == 0){
			values = new String[2];
			values[0] = this.oSelectPairBasis.SelFieldVon.get_ActualWert();
			values[1] = this.oSelectPairBasis.SelFieldBis.get_ActualWert();
			if (!bibALL.isEmpty(values[0]) && !bibALL.isEmpty(values[1])){
				arrRet.add(values);
			}
		} else {
			for (ZusatzwertPaar cWerte: this.vZusatzWerte){
				values = new String[2];
				values[0] = cWerte.WertVon;
				values[1] = cWerte.WertBis;
				
				if (!bibALL.isEmpty(values[0]) && !bibALL.isEmpty(values[1])){
					arrRet.add(values);
				}
			}
		}
		return arrRet;
	}
	
	
	
	
	private String get_WhereBlockSingleStatement(String cWert_von, String cWert_bis) throws myException
	{
		String cWhere = "";
		if (S.isFull(cWert_von) && S.isFull(cWert_bis))
		{
			//klassisch via eingelagertem #WERT#
			cWhere = bibALL.ReplaceTeilString(this.cWhereStringSchablone,"#WERT1#",cWert_von);
			cWhere = bibALL.ReplaceTeilString(cWhere,"#WERT2#",cWert_bis);
			cWhere = "("+cWhere+")";
		}
		else if (S.isEmpty(cWert_von) && S.isEmpty(cWert_bis))
		{
			cWhere = "";
		}
		else
		{
			throw new myException(this,"get_WhereBlockSingleStatement: Two full Where-Statements are needed (or two empty)");
		}
		return cWhere;
	}
	

	

	
	@Override
	public Component get_oComponentForSelection()
	{
		return this.grid4Anzeige;
	}

	
	@Override
	public Component get_oComponentWithoutText()
	{
		return this.get_oComponentForSelection();
	}
	
	
	

	@Override
	public void add_ActionAgentToComponent(XX_ActionAgent oAgent)
	{
		this.oAgent4Select=oAgent;
		this.oSelectPairBasis.add_ActionAgent(oAgent);
	}

	@Override
	public void doInternalCheck()
	{
	}

	
	
	
	//alles leermachen
	public void LEER_MACHEN() throws myException
	{
		this.vZusatzWerte.removeAllElements();
		this.oButtonOpenMultiSelectPopup.set_StatusHasMultiSelect(0);
		this.oSelectPairBasis.set_ActiveValue_OR_FirstValue(null,null);
		this.oSelectPairBasis.set_bEnabled_For_Edit(true);
		this.fill_Grid4AnzeigeStatusSingle();
	}
	
	
	
	private class ownButtonOpenMultiSelectPopup extends MyE2_Button
	{
		private int iAnzahlZusatzSelektionen = 0;
		
		
		public ownButtonOpenMultiSelectPopup() throws myException
		{
			super("0");
			this.setFont(new E2_FontBold());
			this.set_StatusHasMultiSelect(0);
			this.setWidth(new Extent(20));
			this.add_oActionAgent(new actionAgentCreatePopup());
		}
		
		public void set_StatusHasMultiSelect(int anzahlZusatzWerte) throws myException   //anzahlZusatzWerte kann nur null oder >=2 werden
		{
			
			E2_ListSelectorMultiDropDown_von_bis oThis = E2_ListSelectorMultiDropDown_von_bis.this;

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
				for (int i=0;i<oThis.vZusatzWerte.size();i++)
				{
					cToolTipText = cToolTipText+
						oThis.oSelectPairBasis.SelFieldVon.get_DataToView().get_ViewStringToData(oThis.vZusatzWerte.get(i).WertVon, false)+
						"  ---- "+
						oThis.oSelectPairBasis.SelFieldBis.get_DataToView().get_ViewStringToData(oThis.vZusatzWerte.get(i).WertBis, false)
						+"\n";
				}
			}
			
			this.setToolTipText(cToolTipText);
		}
	}

	
	
	
	private class actionAgentCreatePopup extends XX_ActionAgent
	{
		private Vector<SelectFieldPair> 	vSelectPairZusatzWerte = 			new Vector<SelectFieldPair>();

		private E2_BasicModuleContainer     oContainerToSelectZusatzValues =	 	null;
		private MyE2_Grid 					oGridInhaltFenster = 					new MyE2_Grid(5, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());         
		
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			
			E2_ListSelectorMultiDropDown_von_bis oThis = E2_ListSelectorMultiDropDown_von_bis.this;
			
			int iSpalten[] = {300,30,300,30,30};
			oGridInhaltFenster.set_Spalten(iSpalten);
			
			//wenn der Vector oThis.vSelectFieldsZusatzWerte leer ist, dann mindestens 1 SelectField mit den Werten der ursprungsselektion
			//und ein neues anbieten
			this.vSelectPairZusatzWerte.removeAllElements();
			
			if (oThis.vZusatzWerte.size()<=1)
			{
				this.vSelectPairZusatzWerte.add(oThis.oSelectPairBasis.get_SimpleCopy(	oThis.oSelectPairBasis.SelFieldVon.get_ActualWert(),
																						oThis.oSelectPairBasis.SelFieldBis.get_ActualWert()));
			}
			else
			{
				for (ZusatzwertPaar cWertPaar: oThis.vZusatzWerte)
				{
					this.vSelectPairZusatzWerte.add(oThis.oSelectPairBasis.get_SimpleCopy(	cWertPaar.WertVon,
																							cWertPaar.WertBis));
				}
			}
		
			//immer ein neues, leeres feld dazu
			this.vSelectPairZusatzWerte.add(oThis.oSelectPairBasis.get_SimpleCopy(null,null));
			
			
			this.oContainerToSelectZusatzValues=oThis.get_PopupContainer();
			this.oContainerToSelectZusatzValues.add(this.oGridInhaltFenster, E2_INSETS.I_5_5_5_5);
			
			this.BaueGridNeu();
			
			this.oContainerToSelectZusatzValues.CREATE_AND_SHOW_POPUPWINDOW(new Extent(550), new Extent(300), new MyE2_String("Weitere Bereiche zufügen .."));
			
		}

		
		

		
		private void BaueGridNeu() throws myException
		{
			E2_ListSelectorMultiDropDown_von_bis oThis = E2_ListSelectorMultiDropDown_von_bis.this;

//			System.out.println("anzahl Actionagents in speichern "+oThis.btSpeichern.get_vActionAgents().size());

			
			oThis.btSpeichern.add_oActionAgent_only_if_ClassNotInList(new ownActionSaveListe(), true);
			oThis.btAbbruch.add_oActionAgent_only_if_ClassNotInList(new ownActionCancelListe(), true);
			oThis.btClear.add_oActionAgent_only_if_ClassNotInList(new ownActionClearMultiSelect(), true);
			
//			System.out.println("anzahl Actionagents in speichern "+oThis.btSpeichern.get_vActionAgents().size());
			
			oThis.btSpeichern.add_GlobalValidator_only_if_ClassNotInList(new ownValidatorSymmetrie());
			
			//der speicherbutton bekommt dann noch die zusatzaction des listenneubaus
			btSpeichern.add_oActionAgent_only_if_ClassNotInList(E2_ListSelectorMultiDropDown_von_bis.this.oAgent4Select,false);
			btClear.add_oActionAgent_only_if_ClassNotInList(E2_ListSelectorMultiDropDown_von_bis.this.oAgent4Select,false);

//			System.out.println("anzahl Actionagents in speichern "+oThis.btSpeichern.get_vActionAgents().size());

			
			this.oGridInhaltFenster.removeAll();
			
			for (int i=0; i<this.vSelectPairZusatzWerte.size();i++)
			{
				SelectFieldPair  oSelPair = this.vSelectPairZusatzWerte.get(i);
				
				this.oGridInhaltFenster.add(oSelPair.SelFieldVon,1, E2_INSETS.I_0_0_5_5);
				this.oGridInhaltFenster.add(new MyE2_Label(new MyE2_String("bis")),1, E2_INSETS.I_0_0_5_5);
				this.oGridInhaltFenster.add(oSelPair.SelFieldBis,1, E2_INSETS.I_0_0_5_5);
				this.oGridInhaltFenster.add(new ownButtonDeleteSelectFieldPair(i),1, E2_INSETS.I_0_0_5_5);
				
				if (i==this.vSelectPairZusatzWerte.size()-1)           //beim letzten einen add-button zufuegen
				{
					this.oGridInhaltFenster.add(new ownButtonHinzuFuegen(),1, E2_INSETS.I_0_0_5_5);
				}
				else
				{
					this.oGridInhaltFenster.add(new MyE2_Label(" "),1, E2_INSETS.I_0_0_5_5);
				}
			}
			
			int iBreiteButtons[] = {80,150,120};
			this.oGridInhaltFenster.add(new E2_ComponentGroupHorizontal_NG(btSpeichern,btClear,btAbbruch,iBreiteButtons), 3, new Insets(0,10,5,5));
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

		
		private class ownButtonDeleteSelectFieldPair extends MyE2_Button
		{
			private int iNumberInVector = 0;
			public ownButtonDeleteSelectFieldPair(int NumberInVector)
			{
				super(E2_ResourceIcon.get_RI("multi_select_delete.png"), true);
				this.setToolTipText(new MyE2_String("Diese Auswahl entfernen").CTrans());
				this.iNumberInVector=NumberInVector;
				
				this.add_oActionAgent(new XX_ActionAgent()
				{
					@Override
					public void executeAgentCode(ExecINFO oExecInfo) throws myException
					{
						if (actionAgentCreatePopup.this.vSelectPairZusatzWerte.size()>2)    //falls noch mindestens 2 vorhanden sind, dann das select-field loeschen,
						{
							actionAgentCreatePopup.this.vSelectPairZusatzWerte.remove(ownButtonDeleteSelectFieldPair.this.iNumberInVector);
						}
						else                    										 //sonst das drop-down-feld leermachen
						{
							actionAgentCreatePopup.this.vSelectPairZusatzWerte.get(ownButtonDeleteSelectFieldPair.this.iNumberInVector).set_ActiveValue_OR_FirstValue(null,null);
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
				E2_ListSelectorMultiDropDown_von_bis 	oThis = 				E2_ListSelectorMultiDropDown_von_bis.this;
				actionAgentCreatePopup 					oThis2 = 				actionAgentCreatePopup.this;
				Vector<ZusatzwertPaar>  				vZusatzWerteLocal = 	new Vector<ZusatzwertPaar>();
				
				for (SelectFieldPair  oSelField: oThis2.vSelectPairZusatzWerte)
				{
					if (S.isFull(oSelField.SelFieldVon.get_ActualWert()) && S.isFull(oSelField.SelFieldBis.get_ActualWert()))
					{
						vZusatzWerteLocal.add(new ZusatzwertPaar(oSelField.SelFieldVon.get_ActualWert(),oSelField.SelFieldBis.get_ActualWert()));
					}
				}
				
				oThis.vZusatzWerte.removeAllElements();
				if (vZusatzWerteLocal.size()==0)
				{
					oThis.oButtonOpenMultiSelectPopup.set_StatusHasMultiSelect(0);
					oThis.oSelectPairBasis.set_ActiveValue_OR_FirstValue(null,null);
					oThis.oSelectPairBasis.set_bEnabled_For_Edit(true);
					oThis.fill_Grid4AnzeigeStatusSingle();
				}
				else if (vZusatzWerteLocal.size()==1)
				{
					oThis.oButtonOpenMultiSelectPopup.set_StatusHasMultiSelect(0);
					oThis.oSelectPairBasis.set_ActiveValue_OR_FirstValue(vZusatzWerteLocal.get(0).WertVon,vZusatzWerteLocal.get(0).WertBis);
					oThis.oSelectPairBasis.set_bEnabled_For_Edit(true);
					oThis.fill_Grid4AnzeigeStatusSingle();
				}
				else
				{
					oThis.vZusatzWerte.addAll(vZusatzWerteLocal);
					oThis.oButtonOpenMultiSelectPopup.set_StatusHasMultiSelect(vZusatzWerte.size());
					oThis.oSelectPairBasis.set_ActiveValue_OR_FirstValue(null,null);
					oThis.oSelectPairBasis.set_bEnabled_For_Edit(false);
					oThis.fill_Grid4AnzeigeStatusMulti();
				}

				oThis2.oContainerToSelectZusatzValues.CLOSE_AND_DESTROY_POPUPWINDOW(true);
			}
		}

		/**
		 * validierer prueft bei speichern, ob immer beide dropdown-werte gefuellt sind
		 * @author martin
		 *
		 */
		private class ownValidatorSymmetrie extends XX_ActionValidator
		{
			@Override
			public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException
			{
				MyE2_MessageVector oMV = new MyE2_MessageVector();
				for (SelectFieldPair oPair: actionAgentCreatePopup.this.vSelectPairZusatzWerte)
				{
					if (oPair.get_bEinerLeerEinerVoll())
					{
						oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es müssen immer beide Dropdownfelder paarweise gefüllt sein !")));
					}
				}
				return oMV;
			}

			@Override
			protected MyE2_MessageVector isValid(String cID_Unformated) throws myException		{return null;}
		}
		
		private class ownActionClearMultiSelect extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				E2_ListSelectorMultiDropDown_von_bis.this.LEER_MACHEN();
				E2_ListSelectorMultiDropDown_von_bis.this.fill_Grid4AnzeigeStatusSingle();
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
				E2_ListSelectorMultiDropDown_von_bis 	oThis = 	E2_ListSelectorMultiDropDown_von_bis.this;
				actionAgentCreatePopup 					oThis2 = 	actionAgentCreatePopup.this;
				oThis2.vSelectPairZusatzWerte.add(oThis.oSelectPairBasis.get_SimpleCopy(null, null));
				oThis2.BaueGridNeu();
			}
		}
	}


	/**
	 * paare von selektoren
	 * @author martin
	 *
	 */
	private class SelectFieldPair 
	{
		public MyE2_SelectField  SelFieldVon=null;
		public MyE2_SelectField  SelFieldBis=null;
		
		public SelectFieldPair(MyE2_SelectField selFieldVon, MyE2_SelectField selFieldBis)
		{
			super();
			SelFieldVon = selFieldVon;
			SelFieldBis = selFieldBis;
			
			this.SelFieldVon.add_oActionAgent(new ownActionAgent(selFieldBis));
			this.SelFieldBis.add_oActionAgent(new ownActionAgent(SelFieldVon));
			
		}
		public void add_ActionAgent(XX_ActionAgent  oAgent)
		{
			this.SelFieldVon.add_oActionAgent(oAgent);
			this.SelFieldBis.add_oActionAgent(oAgent);
		}
		
		public void set_ActiveValue_OR_FirstValue(String cValueVon,String cValueBis)
		{
			this.SelFieldVon.set_ActiveValue_OR_FirstValue(cValueVon);
			this.SelFieldBis.set_ActiveValue_OR_FirstValue(cValueBis);
		}
		public void set_bEnabled_For_Edit(boolean bEnabled) throws myException
		{
			this.SelFieldVon.set_bEnabled_For_Edit(bEnabled);
			this.SelFieldBis.set_bEnabled_For_Edit(bEnabled);
		}
		
		public SelectFieldPair get_SimpleCopy(String cVon, String cBis) throws myException
		{
			return new SelectFieldPair(this.SelFieldVon.get_SimpleCopy(cVon), this.SelFieldBis.get_SimpleCopy(cBis));
		}
		
		public boolean get_bEinerLeerEinerVoll() throws myException
		{
			return ( (S.isEmpty(this.SelFieldVon.get_ActualWert()) && S.isFull(this.SelFieldBis.get_ActualWert())) ||
					 (S.isEmpty(this.SelFieldBis.get_ActualWert()) && S.isFull(this.SelFieldVon.get_ActualWert())));
		}

		private class ownActionAgent extends XX_ActionAgent
		{
			private MyE2_SelectField oSelFieldOther = null;
			
			public ownActionAgent(MyE2_SelectField SelFieldOther)
			{
				super();
				this.oSelFieldOther = SelFieldOther;
			}

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				MyE2_SelectField ich = (MyE2_SelectField)oExecInfo.get_MyActionEvent().getSource();
				
				//falls der geklickte nicht leer und der andere leer ist, dann den wert uebertragen
				if (S.isFull(ich.get_ActualWert()) && S.isEmpty(this.oSelFieldOther.get_ActualWert()))
				{
					this.oSelFieldOther.set_ActiveValue_OR_FirstValue(ich.get_ActualWert());
				}
				else if (S.isEmpty(ich.get_ActualWert()) && S.isFull(this.oSelFieldOther.get_ActualWert()))
				{
					this.oSelFieldOther.set_ActiveValue_OR_FirstValue(null);
				}
			}
		}
	}

	private class ZusatzwertPaar
	{
		public String WertVon = null;
		public String WertBis = null;
		public ZusatzwertPaar(String wertVon, String wertBis) 
		{
			super();
			WertVon = wertVon;
			WertBis = wertBis;
		}
		
	}




	
	
}
