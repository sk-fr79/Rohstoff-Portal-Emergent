package panter.gmbh.Echo2.ListAndMask.List.ZusatzFelder;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor_Neutralisator;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.E2_HelpButton;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LISTEN_ZUSATZFELDER;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.DotFormatter_autoFormat;
import panter.gmbh.indep.myVectors.bibVECTOR;


/**
 * Ein selektor fuer zusatzfelder in liste.
 * Dieser hat folgende Eigenschaften: Er baut sich klicken via popup so auf, dass
 * alle sichtbaren sonderfelder beruecksichtigt werden, aber nur diese
 * Wenn er gefuellt ist, wird das durch ein gruenes karo neben dem Button symbolisiert
 * Bei numerischen Feldern wird ein von-bis angezeigt, bei nicht numerischen feldern ein Feld, das 
 * via like - Abfrage in die query einfliesst 
 * @author martin
 *
 */
public abstract class E2_ListSelektor_ZUSATZFELDER extends XX_ListSelektor
{
	
	private HashMap<String, ownInputComponents>  	hmInputComponents = new HashMap<String, E2_ListSelektor_ZUSATZFELDER.ownInputComponents>();
	
	
	private RECLIST_LISTENZUSATZFELDER_SPEC rlLISTENZUSATZFELDER = null;
	
	private E2_ComponentMAP      			oComponentMAP_REF = null;
	private String   						cMODULKENNER_LIST = null;
	private MyE2_Button                     oButtonStartSettings = new MyE2_Button(new MyE2_String("Sonderspalten"));
	private MyE2_Button                     oButtonCleanSettings = new MyE2_Button(E2_ResourceIcon.get_RI("eraser.png"),true);

	// knoepfe im popup-fenster
	private MyE2_Button                     oButtonSaveSelektorPopUpAndStartSelektion = new MyE2_Button(new MyE2_String("Starte Selektion"));
	private MyE2_Button                     oButtonCleanFieldsInMask = new MyE2_Button(new MyE2_String("Felder leeren"));

	private E2_BasicModuleContainer  		oPopcontainer = null;
	
	/*
	 * damit eine eigene klasse uebergeben werden kann, deren groesse speicherbar ist
	 */
	public abstract                         E2_BasicModuleContainer   get_E2_BasicContainer4Popup() throws myException;
	
	
	public E2_ListSelektor_ZUSATZFELDER(E2_ComponentMAP o_ComponentMAP_REF, String c_MODULKENNER_LIST) throws myException
	{
		super();
		this.oComponentMAP_REF = 		o_ComponentMAP_REF;
		this.cMODULKENNER_LIST = 		c_MODULKENNER_LIST;
		this.rlLISTENZUSATZFELDER = 	new RECLIST_LISTENZUSATZFELDER_SPEC(this.cMODULKENNER_LIST,true);
		this.oPopcontainer = 			this.get_E2_BasicContainer4Popup();
		
		//die eingabefelder aufbauen
		for (RECORD_LISTEN_ZUSATZFELDER rlZF:this.rlLISTENZUSATZFELDER.values())
		{
			this.hmInputComponents.put(rlZF.get_FIELDNAME_cUF(), new ownInputComponents(rlZF));
		}
		
		this.oButtonStartSettings.add_oActionAgent(new actionStartPopup());
		this.oButtonStartSettings.setToolTipText(new MyE2_String("Eingabe von Selektionen für die Sonder-/Auswerte-Spalten").CTrans());
		
		this.oButtonCleanSettings.add_oActionAgent(new ownActionCleanFields());
		this.oButtonCleanSettings.setToolTipText(new MyE2_String("Alle Selektionsangaben für die Sonder-/Auswerte-Spalten entfernen und Selektion neu aufbauen").CTrans());
		
		this.oButtonCleanFieldsInMask.add_oActionAgent(new ownActionCleanFields());
		//this.oButtonCloseSelektorPopUpAndCancelSelektion.add_oActionAgent(new ownActionClosePopupWindow());
		
		this.oButtonSaveSelektorPopUpAndStartSelektion.add_oActionAgent(new ownActionCheckInputFields());
		this.oButtonSaveSelektorPopUpAndStartSelektion.add_oActionAgent(new ownActionClosePopupWindow());
		
		
		//einen neutralisator mit einbauen
		this.set_oNeutralisator(new ownSelectorNeutralizer());
		
	}

	
	@Override
	public String get_WhereBlock() throws myException
	{
		String cWhere = "";
		
		Vector<ownInputComponents>  vSortedVisibleInputComponents = this.get_vSortedVisibleInputComponents();
		
		Vector<String> vWheres = new Vector<String>();
		
		for (ownInputComponents oCompContainer: vSortedVisibleInputComponents)
		{
			String cSQL = oCompContainer.get_SqlWhereBlock();
			if (S.isFull(cSQL))
			{
				vWheres.add("("+oCompContainer.get_SqlWhereBlock()+")");
			}
		}

		cWhere = bibALL.Concatenate(vWheres, " AND ", "1=1");
		
		return cWhere;
	}

	@Override
	public Component get_oComponentForSelection()
	{
	
		//wird nur angezeigt, wenn die liste zusatzfelder hat
		if (this.rlLISTENZUSATZFELDER.get_vKeyValues().size()>0)
		{
			MyE2_Grid  	oGridForSelektorButton = new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			oGridForSelektorButton.add(this.oButtonStartSettings, E2_INSETS.I_0_0_5_0);
			oGridForSelektorButton.add(this.oButtonCleanSettings, E2_INSETS.I_0_0_0_0);
			return oGridForSelektorButton;
		}
		
		return new MyE2_Label("");
	}

	@Override
	public Component get_oComponentWithoutText()
	{
		return this.get_oComponentForSelection();
	}

	@Override
	public void add_ActionAgentToComponent(XX_ActionAgent oAgent)
	{
		this.oButtonCleanSettings.add_oActionAgent(oAgent);
		this.oButtonSaveSelektorPopUpAndStartSelektion.add_oActionAgent(oAgent);
	}

	@Override
	public void doInternalCheck()
	{
	}
	
	
	

	
	private class actionStartPopup extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			E2_ListSelektor_ZUSATZFELDER  oThis = E2_ListSelektor_ZUSATZFELDER.this;
			
			//nachsehen, welche felder gerade sichtbar sind
			Vector<String> vFieldsToSelect = oThis.get_vVisibleZusatzFields();
			
			if (vFieldsToSelect.size()==0)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Momentan sind keine selektierbaren Zusatzfelder sichtbar !")));
			}
			else
			{
				MyE2_Grid  oGrid4Popup = new MyE2_Grid(5, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
				
				Vector<ownInputComponents>  vAnzeigeKomponenten = oThis.get_vSortedVisibleInputComponents();
				
				//damit stehen im Vector vSorter alle sichtbaren sonderfelder, sortiert nach Titel
				for (ownInputComponents oComp: vAnzeigeKomponenten)
				{
					oGrid4Popup.add(new MyE2_Label(new MyE2_String(
							oComp.ownRECORD.get_BESCHRIFTUNG_SELEKTOR_cUF_NN("<sonderspalte>"),true,": ",false)),
											MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_2_2_2_2));
					
					
					if (oComp.ownRECORD.is_IST_NUMERISCH_YES())
					{
						oGrid4Popup.add(new ownHelpButtonNumerisch(),MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_2_2_2_2));
						oGrid4Popup.add(oComp.oTF_Von,MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_2_2_2_2));
						oGrid4Popup.add(new MyE2_Label(new MyE2_String("bis")),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2));
						oGrid4Popup.add(oComp.oTF_Bis,MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_2_2_2_2));
					}
					else
					{
						oGrid4Popup.add(new ownHelpButtonText(),MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_2_2_2_2));
						oGrid4Popup.add(oComp.oTF_Von,MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_2_2_2_2,null,3,null));
					}
				}
				
				oGrid4Popup.add(new E2_ComponentGroupHorizontal(0, 
						oThis.oButtonSaveSelektorPopUpAndStartSelektion, 
						oThis.oButtonCleanFieldsInMask, E2_INSETS.I_0_0_10_0),4, new Insets(2, 20, 2, 2));
				
				oThis.oPopcontainer.RESET_Content();
			    oThis.oPopcontainer.add(oGrid4Popup, new Insets(2, 20, 2, 2));
				
				//jetzt anzeigen
				oThis.oPopcontainer.CREATE_AND_SHOW_POPUPWINDOW( new Extent(400), new Extent(400) ,new MyE2_String("Selektionen auf den Sonderspalten"));
			}
		}
	}

	
	
	private Vector<String>  get_vVisibleZusatzFields() throws myException
	{
		Vector<String> vVisibleZusatzFields = 	new Vector<String>();
		Vector<String> vVisibleFields = 		this.oComponentMAP_REF.get_vVisibleElementsInList();
		
		for (RECORD_LISTEN_ZUSATZFELDER rlZF: this.rlLISTENZUSATZFELDER.values())
		{
			if (vVisibleFields.contains(rlZF.get_FIELDNAME_cUF()))
			{
				vVisibleZusatzFields.add(rlZF.get_FIELDNAME_cUF());
			}
		}
		
		return vVisibleZusatzFields;
	}
	
	
	
	
	private Vector<ownInputComponents>  get_vSortedVisibleInputComponents() throws myException
	{
		//nachsehen, welche felder gerade sichtbar sind
		Vector<String> vFieldsToSelect = this.get_vVisibleZusatzFields();
		
		Vector<RECORD_LISTEN_ZUSATZFELDER> vSorter = new Vector<RECORD_LISTEN_ZUSATZFELDER>();
		
		for (RECORD_LISTEN_ZUSATZFELDER rlZF: this.rlLISTENZUSATZFELDER.values())
		{
			if (vFieldsToSelect.contains(rlZF.get_FIELDNAME_cUF()))
			{
				vSorter.add(rlZF);
			}
		}

		
		//jetzt die records sortieren
		Collections.sort(vSorter,new Comparator<RECORD_LISTEN_ZUSATZFELDER>()
		{
			@Override
			public int compare(RECORD_LISTEN_ZUSATZFELDER o1, RECORD_LISTEN_ZUSATZFELDER o2)
			{
				try
				{
					return o1.get_BESCHRIFTUNG_SELEKTOR_cUF_NN("").compareTo(o2.get_BESCHRIFTUNG_SELEKTOR_cUF_NN(""));
				}
				catch (myException e)
				{
					return 0;
				}
			}
		});

		
		Vector<ownInputComponents>  vSortedInputComps = new Vector<E2_ListSelektor_ZUSATZFELDER.ownInputComponents>();
		
		//damit stehen im Vector vSorter alle sichtbaren sonderfelder, sortiert nach Titel
		for (RECORD_LISTEN_ZUSATZFELDER recLZ: vSorter)
		{
			vSortedInputComps.add(this.hmInputComponents.get(recLZ.get_FIELDNAME_cUF()));
		}

		return vSortedInputComps;
	}
	
	
	
	private class ownInputComponents
	{
		public MyE2_TextField  				oTF_Von = new MyE2_TextField("",100,100);
		public MyE2_TextField  				oTF_Bis = new MyE2_TextField("",100,100);
		public RECORD_LISTEN_ZUSATZFELDER 	ownRECORD = null;
		
		public ownInputComponents(RECORD_LISTEN_ZUSATZFELDER rlZF) throws myException
		{
			super();
			this.ownRECORD = rlZF;
			
			if (this.ownRECORD.is_IST_NUMERISCH_YES())
			{
				oTF_Von.setAlignment(new Alignment(Alignment.RIGHT, Alignment.CENTER));
				oTF_Bis.setAlignment(new Alignment(Alignment.RIGHT, Alignment.CENTER));
			}
			else
			{
				oTF_Von.set_iWidthPixel(300);
			}
			
		}
		
		
		public String get_SqlWhereBlock() throws myException
		{
			String cRueck = "";
			
			String cWertVonFeldSQL = "";
			String cWertBisFeldSQL = "";
			
			if (this.ownRECORD.is_IST_NUMERISCH_YES())
			{
				if (S.isFull(this.oTF_Von.getText()))
				{
					DotFormatter_autoFormat oDF = new DotFormatter_autoFormat(this.oTF_Von.getText().trim());
					cWertVonFeldSQL=oDF.getStringUnFormated();
				}
				if (S.isFull(this.oTF_Bis.getText()))
				{
					DotFormatter_autoFormat oDF = new DotFormatter_autoFormat(this.oTF_Bis.getText().trim());
					cWertBisFeldSQL=oDF.getStringUnFormated();
				}
				
				if (S.isFull(cWertVonFeldSQL) && S.isFull(cWertBisFeldSQL))
				{
					cRueck = "("+this.ownRECORD.get_SUBQUERY_cUF()+")"+">="+cWertVonFeldSQL+" AND "+"("+this.ownRECORD.get_SUBQUERY_cUF()+")"+"<="+cWertBisFeldSQL;
				}
				else if (S.isFull(cWertVonFeldSQL))
				{
					cRueck = "("+this.ownRECORD.get_SUBQUERY_cUF()+")"+">="+cWertVonFeldSQL;
				}
				else if (S.isFull(cWertBisFeldSQL))
				{
					cRueck = "("+this.ownRECORD.get_SUBQUERY_cUF()+")"+"<="+cWertBisFeldSQL;
				}
			}
			else
			{
				if (S.isFull(this.oTF_Von.getText()))   //bei diesen feldern wird nur das  textfeld "von" angezeigt
				{
					String cBase = this.oTF_Von.getText().trim();
					
					//maskenanalyse
					//zuerst von .. bis pruefen
					if (cBase.startsWith(".."))   //startet mit ..   ==  <=
					{
						cBase=cBase.substring(2);
						cRueck = "UPPER("+this.ownRECORD.get_SUBQUERY_cUF()+")"+"<=UPPER("+bibALL.MakeSql(cBase)+")";							
					}
					else if (cBase.endsWith("..")) //endet mit ..   == >=
					{
						cBase = cBase.substring(0,cBase.length()-2);
						cRueck = "UPPER("+this.ownRECORD.get_SUBQUERY_cUF()+")"+">=UPPER("+bibALL.MakeSql(cBase)+")";							
					}
					else if (cBase.contains(".."))
					{
						String[] arrVonBis = cBase.split("\\.\\.");

						if (arrVonBis.length==2)   //sonst wird das ignoriert
						{
							//where kann formuliert werden
							cRueck = 	"UPPER("+this.ownRECORD.get_SUBQUERY_cUF()+")"+">=UPPER("+bibALL.MakeSql(arrVonBis[0])+") AND "+
										"UPPER("+this.ownRECORD.get_SUBQUERY_cUF()+")"+"<=UPPER("+bibALL.MakeSql(arrVonBis[1])+")";							
						}
						else
						{
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Suchmasken in Textfeldern: Erlaubt: ..Suchwort oder Suchwort.. oder SucheVon..Bis")));
						}
					}
					else
					{
						//wieder auf anfang
						cBase = this.oTF_Von.getText().trim();
						
						if (cBase.startsWith("*") && cBase.endsWith("*"))
						{
							cBase = cBase.replace("*", "");
							cRueck = "UPPER("+this.ownRECORD.get_SUBQUERY_cUF()+")"+" LIKE UPPER('%"+cBase+"%')";
						}
						else if (cBase.startsWith("*"))
						{
							cBase = cBase.replace("*", "");
							cRueck = "UPPER("+this.ownRECORD.get_SUBQUERY_cUF()+")"+" LIKE UPPER('%"+cBase+"')";
						}
						else if (cBase.endsWith("*"))
						{
							cBase = cBase.replace("*", "");
							cRueck = "UPPER("+this.ownRECORD.get_SUBQUERY_cUF()+")"+" LIKE UPPER('"+cBase+"%')";
						}
						else
						{
							//simpler fall wird mit == behandelt
							cRueck = "UPPER("+this.ownRECORD.get_SUBQUERY_cUF()+")"+"=UPPER('"+cBase+"')";
						}
					}
				}
			}
			return cRueck;
		}
	}
	
	
	private class ownActionCleanFields extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			E2_ListSelektor_ZUSATZFELDER.this.cleanFields();
		}
	}
	
	
	private void cleanFields()
	{
		for (ownInputComponents oInput: this.hmInputComponents.values())
		{
			oInput.oTF_Von.setText("");
			oInput.oTF_Bis.setText("");
			oInput.oTF_Von.setStyle(oInput.oTF_Von.EXT().get_STYLE_FACTORY().get_Style(true,true,false));
			oInput.oTF_Bis.setStyle(oInput.oTF_Von.EXT().get_STYLE_FACTORY().get_Style(true,true,false));
		}
	}
	
	private class ownActionClosePopupWindow extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			E2_ListSelektor_ZUSATZFELDER.this.oPopcontainer.CLOSE_AND_DESTROY_POPUPWINDOW(true);
		}
	}
	
	
	private class ownActionCheckInputFields extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			E2_ListSelektor_ZUSATZFELDER  oThis = E2_ListSelektor_ZUSATZFELDER.this;

			Vector<ownInputComponents> vVisibleFields = oThis.get_vSortedVisibleInputComponents();
			
			boolean bFehler = false;
			
			for (ownInputComponents oInputField: vVisibleFields)
			{
				if (oInputField.ownRECORD.is_IST_NUMERISCH_YES())
				{
					if (S.isFull(oInputField.oTF_Von.getText()))
					{
						if (bibALL.isNumber(oInputField.oTF_Von.getText(), true))
						{
							oInputField.oTF_Von.setStyle(oInputField.oTF_Von.EXT().get_STYLE_FACTORY().get_Style(true,true,false));
						}
						else
						{
							bFehler = true;
							oInputField.oTF_Von.setStyle(oInputField.oTF_Von.EXT().get_STYLE_FACTORY().get_Style(true,true,true));
						}
					}
					if (S.isFull(oInputField.oTF_Bis.getText()))
					{
						if (bibALL.isNumber(oInputField.oTF_Bis.getText(), true))
						{
							oInputField.oTF_Bis.setStyle(oInputField.oTF_Bis.EXT().get_STYLE_FACTORY().get_Style(true,true,false));
						}
						else
						{
							bFehler = true;
							oInputField.oTF_Bis.setStyle(oInputField.oTF_Bis.EXT().get_STYLE_FACTORY().get_Style(true,true,true));
						}
					}
				}
			}
			
			if (bFehler)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte geben Sie in numerischen Feldern korrekte Zahlen ein !")));
			}
		}
	}
	
	
	
	private class ownSelectorNeutralizer extends XX_ListSelektor_Neutralisator
	{
		@Override
		public void set_to_Neutral() throws myException
		{
			E2_ListSelektor_ZUSATZFELDER.this.cleanFields();
		}
	}
	
	
	private class ownHelpButtonNumerisch extends E2_HelpButton
	{

		public ownHelpButtonNumerisch()
		{
			super(	new MyE2_String("Numerische Felder"), 
					bibVECTOR.get_Vector("Selektionen bei Zahlenfeldern:",""),
					bibVECTOR.get_Vector(
							"Eingabe 1 gefüllt:", 
							"Eingabe 2 gefüllt:", 
							"Beide Felder gefüllt:"), 
					bibVECTOR.get_Vector(
									"Selektion enthält alle Daten mit Spaltenwert>=Eingabewert", 
									"Selektion enthält alle Daten mit Spaltenwert<=Eingabewert", 
									"Selektion enthält alle Daten mit Spaltenwert von Eingabe 1 bis Eingabe 2"), 
				    false,
				    new Extent(500),
				    new Extent(400)
				    );
		}
		
	}
	
	private class ownHelpButtonText extends E2_HelpButton
	{

		public ownHelpButtonText()
		{
			super(	new MyE2_String("Text-Felder"), 
					bibVECTOR.get_Vector(
							"Selektionen bei Text-Feldern",""), 
					bibVECTOR.get_Vector(
							"Suchwert..      ", 
							"..Suchwert      ",
							"Suchwert 1 .. Suchwert 2", 
							"*Suchwert", 
							"Suchwert*", 
							"*Suchwert*", 
							"Suchwert"), 
					bibVECTOR.get_Vector(
							"wählt alles, wo die Spalte größer oder gleich der Eingabe ist", 
							"wählt alles, wo die Spalte kleiner oder gleich der Eingabe ist",
							"wählt alles, wo die Spalte zwischen Suchwert 1 und 2 ist", 
							"wählt alles, wo die Spalte auf Suchwert endet", 
							"wählt alles, wo die Spalte mit Suchwert beginnt", 
							"wählt alles, wo irgendwo in der Spalte  Suchwert steht", 
							"wählt alles, wo die Spalte exakt Suchwert ist"), 
					false,
				    new Extent(500),
				    new Extent(400));
		}
		
	}
	
	
}
