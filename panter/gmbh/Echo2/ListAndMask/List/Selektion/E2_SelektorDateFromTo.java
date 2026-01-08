package panter.gmbh.Echo2.ListAndMask.List.Selektion;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.Row;
import nextapp.echo2.app.layout.RowLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearchParent_ContentPane_NUMBER_ONE;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_MessageBoxMultiText;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.TestingDate;


/*
 * ein listenselektor, mit datum von-bis
 */
public class E2_SelektorDateFromTo extends XX_ListSelektor 
{
	
	public static MutableStyle oStyleRow = null;
	static
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty( Row.PROPERTY_BORDER, new Border(0, new E2_ColorBase(-20), Border.STYLE_SOLID)); 
		oStyle.setProperty( Row.PROPERTY_INSETS, new Insets(0)); 
		oStyle.setProperty( Row.PROPERTY_FONT, new E2_FontPlain());
		oStyleRow = oStyle;
	}
	
	public static RowLayoutData  oRowLayout = null;
	static
	{
		RowLayoutData oRL = new RowLayoutData();
		oRL.setInsets(new Insets(0,0,4,0));	// abstand nur nach rechts
		oRL.setAlignment(new Alignment(Alignment.LEFT,Alignment.CENTER));
		oRowLayout = oRL;
	}
	
	
	
	private MyE2_Row		oBaseComponent = new MyE2_Row();
	private MyE2_TextField cDatumVon = new MyE2_TextField();
	private MyE2_TextField cDatumBis = new MyE2_TextField();
	private MyE2_Button		buttonLos = new MyE2_Button(E2_ResourceIcon.get_RI("ok.png"));
	private MyE2_Button		buttonHelp = new MyE2_Button(E2_ResourceIcon.get_RI("help.png"),true);
	private String 			cFieldForSelektion = null;
	private String 			cFieldForSelektionEnd = null;
	
	
	/**
	 * @param cTextVorDatum (kann  null sein)
	 * @param cfieldForSelektion
	 * @param cfieldForSelektionEnd
	 * @param oContentpane 
	 */
	public E2_SelektorDateFromTo(MyString cTextVorDatum,String cfieldForSelektion,String cfieldForSelektionEnd)
	{
		super();
		this.oBaseComponent.setStyle(oStyleRow);
		this.cFieldForSelektion = cfieldForSelektion;
		this.cFieldForSelektionEnd = cfieldForSelektionEnd;               // !!! KANN NULL SEIN
		
		
		if (cTextVorDatum != null) this.oBaseComponent.add(new MyE2_Label(cTextVorDatum),E2_INSETS.I_0_0_5_0);
		this.oBaseComponent.add(this.cDatumVon,E2_INSETS.I_0_0_5_0);
		this.oBaseComponent.add(new MyE2_Label("-"),E2_INSETS.I_2_0_2_0);
		this.oBaseComponent.add(this.cDatumBis,E2_INSETS.I_0_0_5_0);
		this.oBaseComponent.add(this.buttonLos,E2_INSETS.I_0_0_5_0);
		
		this.oBaseComponent.add(this.buttonHelp);
		
		this.cDatumVon.setWidth(new Extent(100));
		this.cDatumBis.setWidth(new Extent(100));
		this.cDatumVon.setMaximumLength(10);
		this.cDatumBis.setMaximumLength(10);

		this.cDatumVon.setLayoutData(oRowLayout);
		this.cDatumBis.setLayoutData(oRowLayout);
		this.buttonLos.setLayoutData(oRowLayout);

		Alignment oAlign = new Alignment(Alignment.CENTER,Alignment.CENTER);
		this.cDatumVon.setAlignment(oAlign);
		this.cDatumBis.setAlignment(oAlign);
		
		this.buttonHelp.add_oActionAgent(new helpActionAgent());
		
		//2012-02-14: neutralisatoren
		this.set_oNeutralisator( new ownNeutralisator());
		
	}
	
	
	


	
	
	/**
	 * @param cTextVorDatum (kann  null sein)
	 * @param cfieldForSelektion
	 * @param cfieldForSelektionEnd
	 * @param oContentpane 
	 */
	public E2_SelektorDateFromTo(MyString cTextVorDatum,String cfieldForSelektion,String cfieldForSelektionEnd, Font oFont)
	{
		super();
		this.oBaseComponent.setStyle(oStyleRow);
		this.cFieldForSelektion = cfieldForSelektion;
		this.cFieldForSelektionEnd = cfieldForSelektionEnd;               // !!! KANN NULL SEIN
		
		

		if (cTextVorDatum != null) this.oBaseComponent.add(new MyE2_Label(cTextVorDatum,oFont),E2_INSETS.I_0_0_5_0);
		this.oBaseComponent.add(this.cDatumVon,E2_INSETS.I_0_0_5_0);
		this.oBaseComponent.add(new MyE2_Label("-"),E2_INSETS.I_2_0_2_0);
		this.oBaseComponent.add(this.cDatumBis,E2_INSETS.I_0_0_5_0);
		this.oBaseComponent.add(this.buttonLos,E2_INSETS.I_0_0_5_0);
		
		this.oBaseComponent.add(this.buttonHelp,E2_INSETS.I_0_0_0_0);
		
		this.cDatumVon.setWidth(new Extent(100));
		this.cDatumBis.setWidth(new Extent(100));
		this.cDatumVon.setMaximumLength(10);
		this.cDatumBis.setMaximumLength(10);

		this.cDatumVon.setLayoutData(oRowLayout);
		this.cDatumBis.setLayoutData(oRowLayout);
		this.buttonLos.setLayoutData(oRowLayout);

		this.cDatumVon.setFont(oFont);
		this.cDatumBis.setFont(oFont);

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
	 * @param oFont
	 * @param InsetText
	 * @param InsetVonDatum
	 * @param InsetTrenner
	 * @param InsetBisDatum
	 * @param InsetStartButton
	 */
	public E2_SelektorDateFromTo(	MyString cTextVorDatum,
									String cfieldForSelektion,
									String cfieldForSelektionEnd, 
									Font oFont,
									Insets  InsetText,
									Insets  InsetVonDatum,
									Insets  InsetTrenner,
									Insets  InsetBisDatum,
									Insets  InsetStartButton)
	{
		super();
		this.oBaseComponent.setStyle(oStyleRow);
		this.cFieldForSelektion = cfieldForSelektion;
		this.cFieldForSelektionEnd = cfieldForSelektionEnd;               // !!! KANN NULL SEIN
		
		

		if (cTextVorDatum != null) this.oBaseComponent.add(new MyE2_Label(cTextVorDatum,oFont),	InsetText==null?E2_INSETS.I_0_0_5_0:InsetText);
		this.oBaseComponent.add(this.cDatumVon,													InsetVonDatum==null?E2_INSETS.I_0_0_5_0:InsetVonDatum);
		this.oBaseComponent.add(new MyE2_Label("-"),											InsetTrenner==null?E2_INSETS.I_2_0_2_0:InsetTrenner);
		this.oBaseComponent.add(this.cDatumBis,													InsetBisDatum==null?E2_INSETS.I_0_0_5_0:InsetBisDatum);
		this.oBaseComponent.add(this.buttonLos,													InsetStartButton==null?E2_INSETS.I_0_0_5_0:InsetStartButton);
		
		this.oBaseComponent.add(this.buttonHelp);
		
		this.cDatumVon.setWidth(new Extent(100));
		this.cDatumBis.setWidth(new Extent(100));
		this.cDatumVon.setMaximumLength(10);
		this.cDatumBis.setMaximumLength(10);

		this.cDatumVon.setLayoutData(oRowLayout);
		this.cDatumBis.setLayoutData(oRowLayout);
		this.buttonLos.setLayoutData(oRowLayout);

		this.cDatumVon.setFont(oFont);
		this.cDatumBis.setFont(oFont);

		Alignment oAlign = new Alignment(Alignment.CENTER,Alignment.CENTER);
		this.cDatumVon.setAlignment(oAlign);
		this.cDatumBis.setAlignment(oAlign);
		
		this.buttonHelp.add_oActionAgent(new helpActionAgent());

		//2012-02-14: neutralisatoren
		this.set_oNeutralisator( new ownNeutralisator());

	}

	
	
	
	
	/*
	 * der eigene actionlistener loescht die eingaben, wenn ein datum nicht korrekt war 
	 */
	public void doInternalCheck()
	{
		TestingDate oDate = new TestingDate(this.cDatumVon.getText());
		if (!bibALL.isEmpty(this.cDatumVon.getText()))
			if (! oDate.testing())
				this.cDatumVon.setText("");
			else
				this.cDatumVon.setText(oDate.get_FormatedDateString("dd.mm.yyyy"));

		oDate = new TestingDate(this.cDatumBis.getText());
		if (!bibALL.isEmpty(this.cDatumBis.getText()))
			if (! oDate.testing())
				this.cDatumBis.setText("");
			else
				this.cDatumBis.setText(oDate.get_FormatedDateString("dd.mm.yyyy"));

		
		
	}

	public String get_WhereBlock() throws myException
	{
		String cDBTextVon = "";
		String cDBTextBis = "";
		
		TestingDate oDate = new TestingDate(this.cDatumVon.getText());
		if (!bibALL.isEmpty(this.cDatumVon.getText()))
			if (oDate.testing())
			{
				cDBTextVon=oDate.get_FormatedDateString("yyyy.mm.dd");
				cDBTextVon = "'"+cDBTextVon.replace('.','-')+"'";
			}

		oDate = new TestingDate(this.cDatumBis.getText());
		if (!bibALL.isEmpty(this.cDatumBis.getText()))
			if (oDate.testing())
			{
				cDBTextBis=oDate.get_FormatedDateString("yyyy.mm.dd");
				cDBTextBis = "'"+cDBTextBis.replace('.','-')+"'";
			}


		/*
		 * der string kann sein von - bis
		 * oder kleiner gleich
		 * oder groesser gleich
		 * je nach ausgefuellten feldern
		 */

		if (bibALL.isEmpty(this.cFieldForSelektionEnd))
			this.cFieldForSelektionEnd = this.cFieldForSelektion;
				
		
		String cRueck = "";
		if 		(!cDBTextVon.equals("")  && !cDBTextBis.equals(""))
		{
			cRueck = "(("+cDBTextVon+"<="+this.cFieldForSelektion+" AND "+cDBTextBis+" >= "+this.cFieldForSelektion+")";
			cRueck += "OR ("+cDBTextVon+"<="+this.cFieldForSelektionEnd+" AND "+cDBTextBis+" >= "+this.cFieldForSelektionEnd+")";
			cRueck += "OR ("+this.cFieldForSelektion+" <= "+cDBTextVon+" AND "+this.cFieldForSelektionEnd+" >= "+cDBTextBis+"))";
			//cRueck = "("+this.cFieldForSelektion+" >= "+cDBTextVon+" AND "+this.cFieldForSelektionEnd+" <= "+cDBTextBis+")";
		}
		else if (cDBTextVon.equals("")  && !cDBTextBis.equals(""))
		{
			cRueck =this.cFieldForSelektionEnd+" <= "+cDBTextBis;
		}
		else if (! cDBTextVon.equals("")  && cDBTextBis.equals(""))
		{
			cRueck =this.cFieldForSelektion+" >= "+cDBTextVon;
		}
		
		return cRueck;
	}

	public Component get_oComponentForSelection()
	{
		return this.oBaseComponent;
	}


	public Component get_oComponentWithoutText() 
	{
		return this.oBaseComponent;
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
			
			new E2_RecursiveSearchParent_ContentPane_NUMBER_ONE().get_FoundPane_ThrowExWhenNotFound().add(oMB);
			
		}
		
	}



	@Override
	public void add_ActionAgentToComponent(XX_ActionAgent oAgent) 
	{
		this.buttonLos.add_oActionAgent(oAgent);
		
	}
	
	
	public void setWidthInPixel_InputFields(int iPixel)
	{
		this.cDatumVon.set_iWidthPixel(iPixel);
		this.cDatumBis.set_iWidthPixel(iPixel);
	}



	public MyE2_TextField get_oTFDatumVon()
	{
		return cDatumVon;
	}



	public MyE2_TextField get_oTFDatumBis()
	{
		return cDatumBis;
	}
	
	
	private class ownNeutralisator extends XX_ListSelektor_Neutralisator
	{
		@Override
		public void set_to_Neutral() throws myException
		{
			E2_SelektorDateFromTo.this.get_oTFDatumVon().setText("");
			E2_SelektorDateFromTo.this.get_oTFDatumBis().setText("");
		}
		
	}
	
	
	public void setToolTip(MyE2_String oText) {
		this.cDatumVon.setToolTipText(oText.CTrans());
		this.cDatumBis.setToolTipText(oText.CTrans());
		this.buttonLos.setToolTipText(oText.CTrans()+" ("+new MyE2_String("Selektion auslösen").CTrans()+")");
	}

}
