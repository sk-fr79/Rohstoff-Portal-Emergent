package panter.gmbh.Echo2.ListAndMask.List.Selektion;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_MessageBoxMultiText;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.MyE2_TextField_Date_von_bis_POPUP_OWN;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.TestingDate;


/*
 * ein listenselektor, mit datum von-bis-selektor
 */
public abstract class E2_SelektorDateFromTo_NNG extends XX_ListSelektor 
{
	
	private MyE2_TextField_Date_von_bis_POPUP_OWN 	cDatumVonBis = 				null;
	private MyE2_Button								buttonLos = 				new MyE2_Button(E2_ResourceIcon.get_RI("ok.png"));
	private MyE2_Button								buttonHelp = 				new MyE2_Button(E2_ResourceIcon.get_RI("help.png"),true);
	private String 									cFieldForSelektion = 		null;
	private String 									cFieldForSelektionEnd = 	null;
	
	private GridLayoutData   						gl4Components = 			null;
	
	
	
	//abstrakte methode, die die komponente baut (anordnung und breite der buttons und felder)
	public abstract void Ordne_Komponenten_An_in_DateVonbisPopup(
								MyE2_TextField_Date_von_bis_POPUP_OWN ownSelectVonBisPopup,
								MyE2_TextField oTextFieldVon,
								MyE2_TextField oTextFieldBis,
								MyE2_Button oButtonCalendar,
								MyE2_Button oButtonEraserVon,
								MyE2_Button oButtonEraserBis) throws myException;

	public abstract void HaengeMeineElementeAn_DateVonBisPopup(
										MyE2_TextField_Date_von_bis_POPUP_OWN 	ownSelectVonBisPopup,
										MyE2_Button                             oButtonLos,
										MyE2_Button                             oButtonHelp) throws myException;
	

	/*
	 * 2012-02-14: weiterer selektor
	 */
	public E2_SelektorDateFromTo_NNG() throws myException
	{
		super();
		this.cDatumVonBis = 	new own_TextField_Date_von_bis_POPUP_OWN();

		this.gl4Components = MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_0_0_5_0);
		
		Alignment oAlign = new Alignment(Alignment.CENTER,Alignment.CENTER);
		this.cDatumVonBis.get_oTextFieldVon().setAlignment(oAlign);
		this.cDatumVonBis.get_oTextFieldBis().setAlignment(oAlign);
		
		this.buttonHelp.add_oActionAgent(new helpActionAgent());
		
		this.cDatumVonBis.set_bShowOKButton(true);
		this.cDatumVonBis.set_bAutoCloseOnBisCalendar(true);
		
		//2012-02-14: neutralisatoren
		this.set_oNeutralisator( new ownNeutralisator());

	}
	
	
	/**
	 * 2014-01-17: neuer contruktor mit felduebergabe
	 * @throws myException
	 */
	public E2_SelektorDateFromTo_NNG(String DBField4SelectionVON, String cDBField4SelectionBIS) throws myException
	{
		super();
		
		this.cFieldForSelektion = 		DBField4SelectionVON;
		this.cFieldForSelektionEnd = 	cDBField4SelectionBIS;
		
		
		this.cDatumVonBis = 	new own_TextField_Date_von_bis_POPUP_OWN();

		this.gl4Components = MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_0_0_5_0);
		
		Alignment oAlign = new Alignment(Alignment.CENTER,Alignment.CENTER);
		this.cDatumVonBis.get_oTextFieldVon().setAlignment(oAlign);
		this.cDatumVonBis.get_oTextFieldBis().setAlignment(oAlign);
		
		this.buttonHelp.add_oActionAgent(new helpActionAgent());
		
		this.cDatumVonBis.set_bShowOKButton(true);
		this.cDatumVonBis.set_bAutoCloseOnBisCalendar(true);
		
		//2012-02-14: neutralisatoren
		this.set_oNeutralisator( new ownNeutralisator());

	}
	

	
	
	
	
	
	private class own_TextField_Date_von_bis_POPUP_OWN extends MyE2_TextField_Date_von_bis_POPUP_OWN
	{

		public own_TextField_Date_von_bis_POPUP_OWN() throws myException
		{
			super();
		}

		@Override
		public void Ordne_Komponenten_An(MyE2_TextField oTextFieldVon, MyE2_TextField oTextFieldBis, MyE2_Button oButtonCalendar, MyE2_Button oButtonEraserVon, MyE2_Button oButtonEraserBis) throws myException
		{
			E2_SelektorDateFromTo_NNG.this.Ordne_Komponenten_An_in_DateVonbisPopup(this,oTextFieldVon, oTextFieldBis, oButtonCalendar, oButtonEraserVon, oButtonEraserBis);
		}
		
		
	}
	
	
	
	//2012-03-05: eine selectorweite tooltips-uebergabe
	public void set_ToolTips(MyE2_String cToolTipText)
	{
		if (cToolTipText != null)
		{
			this.cDatumVonBis.get_oTextFieldVon().setToolTipText(cToolTipText.CTrans());
			this.cDatumVonBis.get_oTextFieldBis().setToolTipText(cToolTipText.CTrans());
			this.buttonLos.setToolTipText(cToolTipText.CTrans());
			this.cDatumVonBis.get_oButtonCalendar().setToolTipText(cToolTipText.CTrans());
		}
	}
	
	
	
	
	/*
	 * der eigene actionlistener loescht die eingaben, wenn ein datum nicht korrekt war 
	 */
	public void doInternalCheck()
	{
		this._do_check(this.cDatumVonBis.get_oTextFieldVon());
		this._do_check(this.cDatumVonBis.get_oTextFieldBis());
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
		
		TestingDate oDate = new TestingDate(this.cDatumVonBis.get_oTextFieldVon().getText());
		if (!bibALL.isEmpty(this.cDatumVonBis.get_oTextFieldVon().getText()))
		{
			if (oDate.testing())
			{
				cDBTextVon=oDate.get_FormatedDateString("yyyy.mm.dd");
				cDBTextVon = "'"+cDBTextVon.replace('.','-')+"'";
			}
		}
		
		oDate = new TestingDate(this.cDatumVonBis.get_oTextFieldBis().getText());
		if (!bibALL.isEmpty(this.cDatumVonBis.get_oTextFieldBis().getText()))
		{
			if (oDate.testing())
			{
				cDBTextBis=oDate.get_FormatedDateString("yyyy.mm.dd");
				cDBTextBis = "'"+cDBTextBis.replace('.','-')+"'";
			}
		}
		

		//2012-03-01: ausgelagert in separate static-methode
		String cRueck = E2_SelektorDateFromTo_NNG.Generate_SelectBlock(this.cFieldForSelektion, this.cFieldForSelektionEnd, cDBTextVon, cDBTextBis);
		
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
		
		this.cDatumVonBis.get_vActionAgentsZusatzVonCalender().removeAllElements();
		this.cDatumVonBis.get_vActionAgentsZusatzBisCalender().removeAllElements();
		
		this.cDatumVonBis.get_vActionAgentsZusatzBisCalender().add(oAgent);
		this.cDatumVonBis.get_oButtonEraserVon().add_oActionAgent(oAgent);
		this.cDatumVonBis.get_oButtonEraserBis().add_oActionAgent(oAgent);

		this.cDatumVonBis.get_oButtonOK().add_oActionAgent(oAgent);
	}
	
	
	public void setWidthInPixel_InputFields(int iPixel)
	{
		this.cDatumVonBis.get_oTextFieldVon().set_iWidthPixel(iPixel);
		this.cDatumVonBis.get_oTextFieldBis().set_iWidthPixel(iPixel);
	}



	public MyE2_TextField_Date_von_bis_POPUP_OWN get_oTFDatumVonBisPopUp()
	{
		return this.cDatumVonBis;
	}


	



	@Override
	public Component get_oComponentForSelection() throws myException
	{
		return this.get_oComponentWithoutText();
	}

	
	
	@Override
	public Component get_oComponentWithoutText() throws myException
	{
		this.HaengeMeineElementeAn_DateVonBisPopup(this.cDatumVonBis,this.buttonLos,this.buttonHelp);
		
		
		return this.cDatumVonBis;
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
			E2_SelektorDateFromTo_NNG.this.get_oTFDatumVonBisPopUp().get_oTextFieldVon().setText("");
			E2_SelektorDateFromTo_NNG.this.get_oTFDatumVonBisPopUp().get_oTextFieldBis().setText("");
		}
		
	}
	

	public void CopyValuesFrom(E2_SelektorDateFromTo_NNG  oQuellSelector)
	{
		this.get_oTFDatumVonBisPopUp().get_oTextFieldVon().setText(S.NN(oQuellSelector.get_oTFDatumVonBisPopUp().get_oTextFieldVon().getText()));
		this.get_oTFDatumVonBisPopUp().get_oTextFieldBis().setText(S.NN(oQuellSelector.get_oTFDatumVonBisPopUp().get_oTextFieldBis().getText()));
	}
	
	public void CopyValuesFrom(E2_SelektorDateFromTo_NG  oQuellSelector)
	{
		this.get_oTFDatumVonBisPopUp().get_oTextFieldVon().setText(S.NN(oQuellSelector.get_oTFDatumVon().get_oTextField().getText()));
		this.get_oTFDatumVonBisPopUp().get_oTextFieldBis().setText(S.NN(oQuellSelector.get_oTFDatumBis().get_oTextField().getText()));
	}
	
	
	
}