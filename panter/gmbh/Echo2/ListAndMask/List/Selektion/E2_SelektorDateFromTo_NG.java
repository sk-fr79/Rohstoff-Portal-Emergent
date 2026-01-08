package panter.gmbh.Echo2.ListAndMask.List.Selektion;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_MessageBoxMultiText;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.MyE2_TextField_DatePOPUP_OWN;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.TestingDate;


/*
 * ein listenselektor, mit datum von-bis
 */
public class E2_SelektorDateFromTo_NG extends XX_ListSelektor 
{
	
	private MyString   						cBeschreibung = null;
	private MyE2_TextField_DatePOPUP_OWN 	cDatumVon = 			null;
	private MyE2_TextField_DatePOPUP_OWN 	cDatumBis = 			null;
	private MyE2_Button						buttonLos = 			new MyE2_Button(E2_ResourceIcon.get_RI("ok.png"));
	private MyE2_Button						buttonHelp = 			new MyE2_Button(E2_ResourceIcon.get_RI("help.png"),true);
	private String 							cFieldForSelektion = 	null;
	private String 							cFieldForSelektionEnd = 	null;
	
	private GridLayoutData   				gl4Components = null;
	

	

	/*
	 * 2012-02-14: weiterer selektor
	 */
	public E2_SelektorDateFromTo_NG() throws myException
	{
		super();
		this.cDatumVon = 	new MyE2_TextField_DatePOPUP_OWN("",100,true,true);
		this.cDatumBis = 	new MyE2_TextField_DatePOPUP_OWN("",100,true,true);

		this.gl4Components = MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_0_0_5_0);
		
		this.cDatumVon.get_oTextField().setMaximumLength(10);
		this.cDatumBis.get_oTextField().setMaximumLength(10);

		Alignment oAlign = new Alignment(Alignment.CENTER,Alignment.CENTER);
		this.cDatumVon.setAlignment(oAlign);
		this.cDatumBis.setAlignment(oAlign);
		
		this.buttonHelp.add_oActionAgent(new helpActionAgent());
		
		//2012-02-14: neutralisatoren
		this.set_oNeutralisator( new ownNeutralisator());

	}
	
	

	/**
	 * 	
	 * @param cTextVorDatum
	 * @param cfieldForSelektion
	 * @param cfieldForSelektionEnd
	 * @param oWidthText4Date
	 * @throws myException
	 */
	public E2_SelektorDateFromTo_NG(MyString cTextVorDatum,String cfieldForSelektion,String cfieldForSelektionEnd, Extent oWidthText4Date) throws myException
	{
		super();
		this.cDatumVon = 	new MyE2_TextField_DatePOPUP_OWN("",100,true,true);
		this.cDatumBis = 	new MyE2_TextField_DatePOPUP_OWN("",100,true,true);

		
		this.gl4Components = MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_0_0_5_0);
		
		
		this.cDatumVon.get_oTextField().setMaximumLength(10);
		this.cDatumBis.get_oTextField().setMaximumLength(10);

		Alignment oAlign = new Alignment(Alignment.CENTER,Alignment.CENTER);
		this.cDatumVon.setAlignment(oAlign);
		this.cDatumBis.setAlignment(oAlign);
		
		this.buttonHelp.add_oActionAgent(new helpActionAgent());
		
		this.INIT_Selector(cTextVorDatum, cfieldForSelektion, cfieldForSelektionEnd, oWidthText4Date);
		
		//2012-02-14: neutralisatoren
		this.set_oNeutralisator( new ownNeutralisator());

	}


	
	public void INIT_Selector(MyString cTextVorDatum,String cfieldForSelektion,String cfieldForSelektionEnd, Extent oWidthText4Date)
	{
		this.cBeschreibung = cTextVorDatum;
		this.cFieldForSelektion = 		cfieldForSelektion;
		this.cFieldForSelektionEnd = 	cfieldForSelektionEnd;               // !!! KANN NULL SEIN

		if (oWidthText4Date!=null)
		{
			this.cDatumVon.get_oTextField().setWidth(oWidthText4Date);
			this.cDatumBis.get_oTextField().setWidth(oWidthText4Date);
		}
		else
		{
			this.cDatumVon.get_oTextField().setWidth(new Extent(100));
			this.cDatumBis.get_oTextField().setWidth(new Extent(100));
		}
	}
	
	
	
	
	//2012-03-05: eine selectorweite tooltips-uebergabe
	public void set_ToolTips(MyE2_String cToolTipText)
	{
		if (cToolTipText != null)
		{
			this.cDatumVon.get_oTextField().setToolTipText(cToolTipText.CTrans());
			this.cDatumBis.get_oTextField().setToolTipText(cToolTipText.CTrans());
			this.buttonLos.setToolTipText(cToolTipText.CTrans());
			this.cDatumVon.get_oButtonCalendar().setToolTipText(cToolTipText.CTrans());
			this.cDatumVon.get_oButtonEraser().setToolTipText(cToolTipText.CTrans());
		}
	}
	
	
	
	
	/*
	 * der eigene actionlistener loescht die eingaben, wenn ein datum nicht korrekt war 
	 */
	public void doInternalCheck()
	{
		this._do_check(this.cDatumVon.get_oTextField());
		this._do_check(this.cDatumBis.get_oTextField());
	}
	
	private void _do_check(MyE2_TextField oTF)
	{
		if (!bibALL.isEmpty(oTF.getText()))
		{
			TestingDate oDate = new TestingDate(oTF.getText());
			if (! oDate.testing())
			{
				oTF.setText("");
			}
			else
			{
				oTF.setText(oDate.get_FormatedDateString("dd.mm.yyyy"));
			}
		}
	}
	

	public String get_WhereBlock() throws myException
	{
		String cDBTextVon = "";
		String cDBTextBis = "";
		
		TestingDate oDate = new TestingDate(this.cDatumVon.get_oTextField().getText());
		if (!bibALL.isEmpty(this.cDatumVon.get_oTextField().getText()))
			if (oDate.testing())
			{
				cDBTextVon=oDate.get_FormatedDateString("yyyy.mm.dd");
				cDBTextVon = "'"+cDBTextVon.replace('.','-')+"'";
			}

		oDate = new TestingDate(this.cDatumBis.get_oTextField().getText());
		if (!bibALL.isEmpty(this.cDatumBis.get_oTextField().getText()))
			if (oDate.testing())
			{
				cDBTextBis=oDate.get_FormatedDateString("yyyy.mm.dd");
				cDBTextBis = "'"+cDBTextBis.replace('.','-')+"'";
			}

		
		

		//2012-03-01: ausgelagert in separate static-methode
		String cRueck = E2_SelektorDateFromTo_NG.Generate_SelectBlock(this.cFieldForSelektion, this.cFieldForSelektionEnd, cDBTextVon, cDBTextBis);
		
//		/*
//		 * der string kann sein von - bis
//		 * oder kleiner gleich
//		 * oder groesser gleich
//		 * je nach ausgefuellten feldern
//		 */
//
//		if (bibALL.isEmpty(this.cFieldForSelektionEnd))
//			this.cFieldForSelektionEnd = this.cFieldForSelektion;
//				
//		
//		String cRueck = "";
//		if 		(!cDBTextVon.equals("")  && !cDBTextBis.equals(""))
//		{
//			cRueck = "(("+cDBTextVon+"<="+this.cFieldForSelektion+" AND "+cDBTextBis+" >= "+this.cFieldForSelektion+")";
//			cRueck += "OR ("+cDBTextVon+"<="+this.cFieldForSelektionEnd+" AND "+cDBTextBis+" >= "+this.cFieldForSelektionEnd+")";
//			cRueck += "OR ("+this.cFieldForSelektion+" <= "+cDBTextVon+" AND "+this.cFieldForSelektionEnd+" >= "+cDBTextBis+"))";
//		}
//		else if (cDBTextVon.equals("")  && !cDBTextBis.equals(""))
//		{
//			cRueck =this.cFieldForSelektionEnd+" <= "+cDBTextBis;
//		}
//		else if (! cDBTextVon.equals("")  && cDBTextBis.equals(""))
//		{
//			cRueck =this.cFieldForSelektion+" >= "+cDBTextVon;
//		}
		
		cRueck = this.get_extended_whereStatement(cRueck, cDBTextVon, cDBTextBis);
		
		return cRueck;
	}

	
	//ueberschreibbare-methode um weitere bedingungen anzuhaengen
	public String get_extended_whereStatement(String cRueck, String cEingabeVon_20121231, String cEingabeBis_20121231) throws myException
	{
		return cRueck;
	}
	
	
	
	/**
	 * 
	 * @param cDateField4SelectionStart  Feldname- oder ausdruck des startfeldes
	 * @param cDateField4SelectionEnd  Feldname- oder ausdruck des Endfeldes
	 * @param cEingabeWertVon   Eingabeinhalt Start vom Typ YYYY-MM-DD
	 * @param cEingabeWertBis   Eingabeinhalt Ende vom Typ YYYY-MM-DD
	 * @return
	 */
	public static String Generate_SelectBlock(		String cDateField4SelectionStart, 
													String cDateField4SelectionEnd, 
													String cEingabeWertVon, 
													String cEingabeWertBis) throws myException
	{
		String cStartFeldName = cDateField4SelectionStart;
		String cEndFeldName = cDateField4SelectionEnd;
		
		if (bibALL.isEmpty(cEndFeldName))
		{
			cEndFeldName = cStartFeldName;
		}
		
		if (S.isEmpty(cEndFeldName))
		{
			throw new myException("E2_SelektorDateFromTo_NG:Generate_SelectBlock: Not all db-fields defined");
		}
			
		
		String cRueck = "";
		
		if 		(!cEingabeWertVon.equals("")  && !cEingabeWertBis.equals(""))
		{
			cRueck = "(("+cEingabeWertVon+"<="+cStartFeldName+" AND "+cEingabeWertBis+" >= "+cStartFeldName+")";
			cRueck += "OR ("+cEingabeWertVon+"<="+cEndFeldName+" AND "+cEingabeWertBis+" >= "+cEndFeldName+")";
			cRueck += "OR ("+cStartFeldName+" <= "+cEingabeWertVon+" AND "+cEndFeldName+" >= "+cEingabeWertBis+"))";
		}
		else if (cEingabeWertVon.equals("")  && !cEingabeWertBis.equals(""))
		{
			cRueck =cEndFeldName+" <= "+cEingabeWertBis;
		}
		else if (! cEingabeWertVon.equals("")  && cEingabeWertBis.equals(""))
		{
			cRueck =cStartFeldName+" >= "+cEingabeWertVon;
		}

		return cRueck;
	}
	
	
	private class helpActionAgent extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			Vector<MyString> vInfos = new Vector<MyString>();
			vInfos.add(new MyE2_String("Wenn zwei Datumsfelder an der Selektion beteiligt sind,dann gilt folgendes:"));
			vInfos.add(new MyE2_String("Sind beide Datumseinträge gefüllt, werden die Daten gefunden, die sich mit dem Suchzeitraum überschneiden !!"));
			vInfos.add(new MyE2_String("Ist nur das ERSTE Datum im Suchfeld gefüllt, dann werden die Daten gefunden,deren <Datum von>-Feld grösser oder gleich dem Eintrag ist."));
			vInfos.add(new MyE2_String("Ist nur das ZEITE Datum im Suchfeld gefüllt, dann werden die Daten gefunden, deren <Datum bis>-Feld kleiner oder gleich dem Eintrag ist."));
			
			MyE2_MessageBoxMultiText oMB = new MyE2_MessageBoxMultiText(new MyE2_String("Hilfe zur Datums-Selektion..."),vInfos,new MyE2_String("OK"));
			
			bibE2.GET_FIRST_CONTENTPANE_IN_SESSION().add(oMB);
		}
		
	}



	@Override
	public void add_ActionAgentToComponent(XX_ActionAgent oAgent) 
	{
		this.buttonLos.add_oActionAgent(oAgent);
		
		this.cDatumVon.get_vActionAgentsZusatz().removeAllElements();
		this.cDatumBis.get_vActionAgentsZusatz().removeAllElements();
		
		this.cDatumVon.get_vActionAgentsZusatz().add(oAgent);
		this.cDatumBis.get_vActionAgentsZusatz().add(oAgent);
		
		this.cDatumVon.get_oButtonEraser().add_oActionAgent(oAgent);
		this.cDatumBis.get_oButtonEraser().add_oActionAgent(oAgent);
	}
	
	
	public void setWidthInPixel_InputFields(int iPixel)
	{
		this.cDatumVon.get_oTextField().set_iWidthPixel(iPixel);
		this.cDatumBis.get_oTextField().set_iWidthPixel(iPixel);
	}



	public MyE2_TextField_DatePOPUP_OWN get_oTFDatumVon()
	{
		return cDatumVon;
	}



	public MyE2_TextField_DatePOPUP_OWN get_oTFDatumBis()
	{
		return cDatumBis;
	}
	

	
	/**
	 * 
	 * @param iWidthBeschreibung
	 * @param iWidthDatum1
	 * @param iWidthDatum2
	 * @param iWidthStartButton
	 * @param iWidthHelpButton       !! wenn ein wert null ist, dann wird die korrlierende komponente weggelassen
	 * @return
	 */
	public MyE2_Grid get_oComponentForSelection(Integer iWidthBeschreibung, Integer iWidthDatum1, Integer iWidthDatum2, Integer iWidthStartButton, Integer iWidthHelpButton)
	{
		int iCount = 0;
	    if (iWidthBeschreibung!=null)  {iCount++;}
	    if (iWidthDatum1!=null)  {iCount++;}
	    if (iWidthDatum2!=null)  {iCount++;}
	    if (iWidthStartButton!=null)  {iCount++;}
	    if (iWidthHelpButton!=null)  {iCount++;}
	    
	    MyE2_Grid  oGrid = new MyE2_Grid(iCount, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	    
	    iCount = 0;
	    if (iWidthBeschreibung!=null) 	{oGrid.setColumnWidth(iCount++,new Extent(iWidthBeschreibung.intValue()));}
	    if (iWidthDatum1!=null) 		{oGrid.setColumnWidth(iCount++,new Extent(iWidthDatum1.intValue()));}
	    if (iWidthDatum2!=null) 		{oGrid.setColumnWidth(iCount++,new Extent(iWidthDatum2.intValue()));}
	    if (iWidthStartButton!=null) 	{oGrid.setColumnWidth(iCount++,new Extent(iWidthStartButton.intValue()));}
	    if (iWidthHelpButton!=null) 	{oGrid.setColumnWidth(iCount++,new Extent(iWidthHelpButton.intValue()));}
	    
	    iCount = 0;
	    if (iWidthBeschreibung!=null) 	{oGrid.add(new MyE2_Label(this.cBeschreibung), this.gl4Components);}
	    if (iWidthDatum1!=null) 		{oGrid.add(this.cDatumVon, this.gl4Components);}
	    if (iWidthDatum2!=null) 		{oGrid.add(this.cDatumBis, this.gl4Components);}
	    if (iWidthStartButton!=null) 	{oGrid.add(this.buttonLos, this.gl4Components);}
	    if (iWidthHelpButton!=null) 	{oGrid.add(this.buttonHelp, this.gl4Components);}
	    
	    return oGrid;
	}
	
	
	

	
	public Component get_oComponentForSelection(Integer iBreiteText)
	{
		return this.get_oComponentForSelection(iBreiteText, 110, 110, 25, 25);
	}



	@Override
	public Component get_oComponentForSelection()
	{
		return this.get_oComponentForSelection(200, 110, 110, 25, 25);
	}



	@Override
	public Component get_oComponentWithoutText()
	{
		return this.get_oComponentForSelection(null, 110, 110, 25, 25);
	}
	
	
	
	
	public GridLayoutData get_oGl4Components()
	{
		return gl4Components;
	}



	public void set_oGl4Components(GridLayoutData gl4Components)
	{
		this.gl4Components = gl4Components;
	}

	
	//2012-02-14: neutralisatoren
	private class ownNeutralisator extends XX_ListSelektor_Neutralisator
	{
		@Override
		public void set_to_Neutral() throws myException
		{
			E2_SelektorDateFromTo_NG.this.get_oTFDatumVon().get_oTextField().setText("");
			E2_SelektorDateFromTo_NG.this.get_oTFDatumBis().get_oTextField().setText("");
		}
		
	}
	

	public void CopyValuesFrom(E2_SelektorDateFromTo_NG  oQuellSelector)
	{
		this.cDatumVon.get_oTextField().setText(S.NN(oQuellSelector.get_oTFDatumVon().get_oTextField().getText()));
		this.cDatumBis.get_oTextField().setText(S.NN(oQuellSelector.get_oTFDatumBis().get_oTextField().getText()));
	}
	
	public void CopyValuesFrom(E2_SelektorDateFromTo_NNG  oQuellSelector)
	{
		this.cDatumVon.get_oTextField().setText(S.NN(oQuellSelector.get_oTFDatumVonBisPopUp().get_oTextFieldVon().getText()));
		this.cDatumBis.get_oTextField().setText(S.NN(oQuellSelector.get_oTFDatumVonBisPopUp().get_oTextFieldBis().getText()));
	}
	
	
	
}