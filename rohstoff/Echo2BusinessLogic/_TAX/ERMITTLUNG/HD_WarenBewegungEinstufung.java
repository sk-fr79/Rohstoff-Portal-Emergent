package rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorMaskHighlight;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_LabelWrap;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_HANDELSDEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_TAX;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_TAX_EXT;

public class HD_WarenBewegungEinstufung
{
	
	/*
	 * layoutData-Objecte fuer die fehler-darstellungen
	 */
	private GridLayoutData				oGC						= MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2, 	new E2_ColorBase(), 1);
	private GridLayoutData				oGL						= MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I_2_2_2_2, 	new E2_ColorBase(), 1);
	//die entscheidungsfelder heller anzeigen
	private GridLayoutData				oGC_REL					= MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2, 	new E2_ColorBase(), 1);

	
	
	private RECORD_HANDELSDEF  	r_recDef = 					null;
	
	//ergebnisfelder (koennen nie null sein, wenn ein ergebnis vorliegt, da alle zugrunde liegenden DB-Felder notnull sind)
	private String 				c_INTRASTAT_IN = 			null;
	private String 				c_INTRASTAT_OUT = 			null;
	private String    			c_ID_TAX_IN_UF = 			null;
	private String    			c_ID_TAX_OUT_UF = 			null;

	//2013-10-01: erweiterung der hd auf steuer fuer negative preise
	private String    			c_ID_TAX_NEGATIVPREIS_IN_UF = 	null;
	private String    			c_ID_TAX_NEGATIVPREIS_OUT_UF =	null;
	
	private boolean   			b_PreisIstPositivEK = true;
	private boolean   			b_PreisIstPositivVK = true;
	
	
	private String   			c_TRANSIT_EK = 				null;
	private String   			c_TRANSIT_VK = 				null;
	
	
	
	private HD_Station 		oStationEK = null;
	private HD_Station 		oStationVK = null;
	
	

	
	public HD_WarenBewegungEinstufung(RECORD_HANDELSDEF recDef,  HD_Station 	StationEK,  HD_Station  StationVK) throws myException
	{
		super();
		this.r_recDef = recDef;
		
		this.oStationEK = StationEK;
		this.oStationVK = StationVK;
		
		if (this.oStationEK==null || this.oStationVK==null){
			throw new myException(this,"Both side station MUST NOT be null !!");
		}
			
		
		this.c_INTRASTAT_IN = 					this.r_recDef.get_INTRASTAT_MELD_IN_cUF();
		this.c_INTRASTAT_OUT = 					this.r_recDef.get_INTRASTAT_MELD_OUT_cUF();
		this.c_ID_TAX_IN_UF =  					this.r_recDef.get_ID_TAX_QUELLE_cUF();
		this.c_ID_TAX_OUT_UF =  				this.r_recDef.get_ID_TAX_ZIEL_cUF();

		//2013-10-01: erweiterung der hd auf steuer fuer negative preise
		this.c_ID_TAX_NEGATIVPREIS_IN_UF =  	this.r_recDef.get_ID_TAX_NEGATIV_QUELLE_cUF_NN("");
		this.c_ID_TAX_NEGATIVPREIS_OUT_UF =		this.r_recDef.get_ID_TAX_NEGATIV_ZIEL_cUF_NN("");
		
		this.c_TRANSIT_EK = 					this.r_recDef.get_TRANSIT_EK_cUF();
		this.c_TRANSIT_VK = 					this.r_recDef.get_TRANSIT_VK_cUF();
		
		this.b_PreisIstPositivEK = this.oStationEK.get_bPreisIsPositiv();
		this.b_PreisIstPositivVK = this.oStationVK.get_bPreisIsPositiv();
		
	}
	
	
	public int hashCode()
	{
		return this.baueCompareString(this).hashCode();
	}	
	
	
	public boolean equals(Object theOther)
	{
		if (this == theOther) {
			return true;
		}
		
		if (theOther == null) {
			return false;
		}
		
		if (!(theOther instanceof HD_WarenBewegungEinstufung))		{
			return false;
		}
		
		return this.baueCompareString(this).equals(this.baueCompareString((HD_WarenBewegungEinstufung)theOther));
		
	}
	
	
	private String baueCompareString(HD_WarenBewegungEinstufung oEinstufung)
	{
		StringBuffer cCompareString = new StringBuffer();
		
		cCompareString.append(""+oEinstufung.c_INTRASTAT_IN);
		cCompareString.append(""+oEinstufung.c_INTRASTAT_OUT);
		
		cCompareString.append(""+FormatNumber(oEinstufung.c_ID_TAX_IN_UF));
		cCompareString.append(""+FormatNumber(oEinstufung.c_ID_TAX_OUT_UF));
		
		//2013-10-01: erweiterung der hd auf steuer fuer negative preise
		cCompareString.append(""+FormatNumber(oEinstufung.c_ID_TAX_NEGATIVPREIS_IN_UF));
		cCompareString.append(""+FormatNumber(oEinstufung.c_ID_TAX_NEGATIVPREIS_OUT_UF));
		
		cCompareString.append(""+oEinstufung.c_TRANSIT_EK);
		cCompareString.append(""+oEinstufung.c_TRANSIT_VK);
		
		return cCompareString.toString();
	}




	public RECORD_HANDELSDEF get_recHANDELSDEF()
	{
		return r_recDef;
	}


	/**
	 * 
	 * @author martin
	 * @date 18.08.2020
	 *
	 * @return
	 */
	public Rec21 getHandelsDef() throws Exception {
		Rec21 ret = null;
		if (this.r_recDef!=null) {
			ret = new Rec21(_TAB.handelsdef)._fill_id(this.r_recDef.get_ID_HANDELSDEF_cUF());
		}
		
		return ret;
	}
	

	/**
	 * erzeugt eine zahl mit  mindestens 20 stellen, damit der vergleichstring immer funktioniert
	 * @param cGanzZahl
	 * @return
	 */
	private String FormatNumber(String cGanzZahl) {
		
		MyLong oLG = new MyLong(cGanzZahl,new Long(0), new Long(0));
		
		String cRueck = oLG.get_cF_LongString();
		String cHelp = "00000000000000000000";
		
		if (cRueck.length()<20) {
			cRueck = cHelp.substring(0, 20-cRueck.length())+cRueck;
		}
		
		return cRueck;
	}
	
	

	public String get_cINTRASTAT_IN()
	{
		return c_INTRASTAT_IN;
	}


	public String get_cINTRASTAT_OUT()
	{
		return c_INTRASTAT_OUT;
	}



//	public String get_cID_TAX_IN_UF()
//	{
//		return c_ID_TAX_IN_UF;
//	}
//
//
//	public String get_cID_TAX_OUT_UF()
//	{
//		return c_ID_TAX_OUT_UF;
//	}


	/*
	 * 2013-10-01: hier unterscheiden, welche tax-id uebergeben wird, die fuer positive oder (falls negativer preis und eine solche vorhanden) die fuer negative preise
	 */
	public String get_cID_TAX_IN_Korrekt_UF() {
		if ((!b_PreisIstPositivEK) && S.isFull(this.c_ID_TAX_NEGATIVPREIS_IN_UF)) {
			return this.c_ID_TAX_NEGATIVPREIS_IN_UF;
		} else {
			return  this.c_ID_TAX_IN_UF;
		}
	}
	
	
	/*
	 * 2013-10-01: hier unterscheiden, welche tax-id uebergeben wird, die fuer positive oder (falls negativer preis und eine solche vorhanden) die fuer negative preise
	 */
	public String get_cID_TAX_OUT_Korrekt_UF() {
		if ((!b_PreisIstPositivVK) && S.isFull(this.c_ID_TAX_NEGATIVPREIS_OUT_UF)) {
			return this.c_ID_TAX_NEGATIVPREIS_OUT_UF;
		} else {
			return  this.c_ID_TAX_OUT_UF;
		}
	}
	
	
	
	
	
	
	/**
	 * 
	 * @param oTitel
	 * @param bSchreibeFuhrenInfo
	 * @param bZeigeUebernahmeButtons
	 * @return  info-grid mit moeglichkeit, den steuersachverhalt zu bearbeiten
	 * @throws myException
	 */
	public MyE2_Grid get_GridMitFuhrenEinstufung(	MyE2_String 							oTitel, 
													boolean 								bSchreibeFuhrenInfo,
													MyE2IF__Component                       oComponent) throws myException
	{
		boolean bHatZusatzComp = (oComponent!=null);
		
		int[] Spalten = 			{ 20, 350, 20, 20,    10,    20, 350, 20, 20,          20};
		int[] SpaltenMitZusatz = 	{ 20, 350, 20, 20,    10,    20, 350, 20, 20,          20, 50};
		
		
		MyE2_Grid oGridRueck = new MyE2_Grid(bHatZusatzComp?SpaltenMitZusatz:Spalten,  MyE2_Grid.STYLE_GRID_BORDER_W100(new E2_ColorDDDark()));
		
		if (oTitel != null )
		{
			//zeile 1
			oGridRueck.add_raw(new MyE2_Label(oTitel, new E2_FontBold(2)), MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2, new E2_ColorDDDark(), 11));
			
			//zeile 2
			oGridRueck.add_raw(new MyE2_Label(new MyE2_String("Ladeseite"), new E2_FontBold()), MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2, new E2_ColorDDark(), 4));
			oGridRueck.add_raw(new MyE2_Label(new MyE2_String(""), new E2_FontBold()), MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2, new E2_ColorDDDark(), 1));
			oGridRueck.add_raw(new MyE2_Label(new MyE2_String("Abladeseite"), new E2_FontBold()), MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2, new E2_ColorDDark(), 4));
			oGridRueck.add_raw(new MyE2_Label(new MyE2_String(" "), new E2_FontBold()), MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2, new E2_ColorDDark(), bHatZusatzComp?2:1));
			
			
			//zeile 3
			oGridRueck.add_raw(new MyE2_Label(new MyE2_String("Steuer"), new E2_FontBold()), 						MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2, new E2_ColorBase(), 1));
			oGridRueck.add_raw(new MyE2_Label(new MyE2_String("Steuertext (grau: Steuer für negative Preise)"), new E2_FontBold()), 					MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I_2_2_2_2, new E2_ColorBase(), 1));
			oGridRueck.add_raw(new MyE2_Label(new MyE2_String("Intrastat - Import"), new E2_FontBold(),true), 		MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2, new E2_ColorBase(), 1));
			oGridRueck.add_raw(new MyE2_Label(new MyE2_String("Transfer - Gutschrift"), new E2_FontBold(),true), 	MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2, new E2_ColorBase(), 1));
			oGridRueck.add_raw(new MyE2_Label(new MyE2_String(""), new E2_FontBold()), MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2, new E2_ColorDDDark(), 1));
			oGridRueck.add_raw(new MyE2_Label(new MyE2_String("Steuer"), new E2_FontBold()), 						MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2, new E2_ColorBase(), 1));
			oGridRueck.add_raw(new MyE2_Label(new MyE2_String("Steuertext (grau: Steuer für negative Preise)"), new E2_FontBold()), 					MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I_2_2_2_2, new E2_ColorBase(), 1));
			oGridRueck.add_raw(new MyE2_Label(new MyE2_String("Intrastat - Export"), new E2_FontBold(),true), 		MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2, new E2_ColorBase(), 1));
			oGridRueck.add_raw(new MyE2_Label(new MyE2_String("Transfer - Rechnung"), new E2_FontBold(),true),		MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2, new E2_ColorBase(), 1));
			oGridRueck.add_raw(new MyE2_Label(new MyE2_String(" "), new E2_FontBold()), MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2, new E2_ColorBase(), bHatZusatzComp?2:1));
		}

		
		if (bSchreibeFuhrenInfo)
		{
			//zeile namen und ort firma, sowie menge und sorte
			oGridRueck.add_raw(new MyE2_Label(this.oStationEK.get_cInfoText_NameOrt_MengeSorte(), new E2_FontItalic(-2)), MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I_2_2_2_2, new E2_ColorBase(), 4));
			oGridRueck.add_raw(new MyE2_Label(new MyE2_String(""), new E2_FontBold()), MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2, new E2_ColorDDDark(), 1));
			oGridRueck.add_raw(new MyE2_Label(this.oStationVK.get_cInfoText_NameOrt_MengeSorte(), new E2_FontItalic(-2)), MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I_2_2_2_2, new E2_ColorBase(), 4));
			oGridRueck.add_raw(new MyE2_Label(new MyE2_String(" "), new E2_FontBold()), MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2, new E2_ColorBase(), bHatZusatzComp?2:1));
		}

		// 2018-07-13: id der handelsdefinition hinterlegen
		if (this.get_recHANDELSDEF()!=null) {
			oGridRueck.add_raw(new RB_lab()._tr("Handelsdefintion/USST-Regel-ID: ")._t_add(this.get_recHANDELSDEF().get_ID_HANDELSDEF_cF_NN("??"))._fo_s_plus(-2), new RB_gld()._ins(2,10,2,2)._left_mid()._span(4));
			oGridRueck.add_raw(new MyE2_Label(new MyE2_String(""), new E2_FontBold()), MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2, new E2_ColorDDDark(), 1));
			oGridRueck.add_raw(new RB_lab()._tr("Handelsdefintion/USST-Regel-ID: ")._t_add(this.get_recHANDELSDEF().get_ID_HANDELSDEF_cF_NN("??"))._fo_s_plus(-2), new RB_gld()._ins(2,10,2,2)._left_mid()._span(4));
			oGridRueck.add_raw(new MyE2_Label(new MyE2_String(" "), new E2_FontBold()), MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2, new E2_ColorBase(), bHatZusatzComp?2:1));
		}

		
		ownREC_TAX  recTAX_IN = 	new ownREC_TAX(new RECORD_TAX(this.c_ID_TAX_IN_UF),this.oStationEK.getLeistungsDatum());
		ownREC_TAX  recTAX_OUT = 	new ownREC_TAX(new RECORD_TAX(this.c_ID_TAX_OUT_UF),this.oStationVK.getLeistungsDatum());

		ownREC_TAX  recTAX_IN_NEG = null;
		ownREC_TAX  recTAX_OUT_NEG = null;
		
		if (S.isFull(this.c_ID_TAX_NEGATIVPREIS_IN_UF)) {
			recTAX_IN_NEG = new ownREC_TAX(new RECORD_TAX(this.c_ID_TAX_NEGATIVPREIS_IN_UF),this.oStationEK.getLeistungsDatum());
		}
		if (S.isFull(this.c_ID_TAX_NEGATIVPREIS_OUT_UF)) {
			recTAX_OUT_NEG = new ownREC_TAX(new RECORD_TAX(this.c_ID_TAX_NEGATIVPREIS_OUT_UF),this.oStationVK.getLeistungsDatum());
		}
		
		
		
		//sicherheitshalber den proforma-steuersatz auch zuladen
		RECORD_TAX  rec_TaxProforma = new HD__FinderProformaSteuersatzFuerMandantenorte().get_RECORD_TAX_proforma();
		if (rec_TaxProforma==null) {
			throw new myException("Error: Please define a <Proforma-Tax> for own Company-Stations with TAX=0 !!");
		}
		ownREC_TAX  recTaxProformaEK = new ownREC_TAX(rec_TaxProforma,this.oStationEK.getLeistungsDatum());
		ownREC_TAX  recTaxProformaVK = new ownREC_TAX(rec_TaxProforma,this.oStationVK.getLeistungsDatum());
		
		
		
		//jetzt pruefen, ob der mandant so definiert ist, dass mandantenseiten der fuhren automatisch gesetzt werden
		boolean bEK_Seite_Proforma = false;
		boolean bVK_Seite_Proforma = false;
		if (bibALL.get_RECORD_MANDANT().is_STEUERFINDUNG_OHNE_EIGENORTE_YES() && this.oStationEK.get_bStationIstMandant()) {
			bEK_Seite_Proforma = true;
		}
		if (bibALL.get_RECORD_MANDANT().is_STEUERFINDUNG_OHNE_EIGENORTE_YES() && this.oStationVK.get_bStationIstMandant()) {
			bVK_Seite_Proforma = true;
		}
		
		
			
		if (bEK_Seite_Proforma) {
			oGridRueck.add_raw(recTaxProformaEK.__LabelSteuerSatz(), this.oGC);
			oGridRueck.add_raw(recTaxProformaEK.__LabelSteuerText(), this.oGL);
			oGridRueck.add_raw(new MyE2_CheckBox(false,true), this.oGC);
			oGridRueck.add_raw(new MyE2_CheckBox(false,true), this.oGC);
		} else {
			oGridRueck.add_raw(this.get_DoppelComponentTax(recTAX_IN, recTAX_IN_NEG), this.oGC);
			oGridRueck.add_raw(this.get_DoppelComponentSteuertext(recTAX_IN, recTAX_IN_NEG), this.oGL);
			oGridRueck.add_raw(this.__CB_IntraStat(true), this.oGC);
			oGridRueck.add_raw(this.__CB_Transit(true), this.oGC);
		}

		oGridRueck.add_raw(new MyE2_Label(new MyE2_String(""), new E2_FontBold()), MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2, new E2_ColorDDDark(), 1));

		if (bVK_Seite_Proforma) {
			oGridRueck.add_raw(recTaxProformaVK.__LabelSteuerSatz(), this.oGC);
			oGridRueck.add_raw(recTaxProformaVK.__LabelSteuerText(), this.oGL);
			oGridRueck.add_raw(new MyE2_CheckBox(false,true), this.oGC);
			oGridRueck.add_raw(new MyE2_CheckBox(false,true), this.oGC);
		} else {
			oGridRueck.add_raw(this.get_DoppelComponentTax(recTAX_OUT, recTAX_OUT_NEG), this.oGC);
			oGridRueck.add_raw(this.get_DoppelComponentSteuertext(recTAX_OUT, recTAX_OUT_NEG), this.oGL);
			oGridRueck.add_raw(this.__CB_IntraStat(false), this.oGC);
			oGridRueck.add_raw(this.__CB_Transit(false), this.oGC);
		}

		if (bibALL.get_RECORD_USER().is_IST_SUPERVISOR_YES())	{
			oGridRueck.add_raw(new HD_Bt_EditHandelsdef(this.r_recDef), this.oGC);
		} else {
			oGridRueck.add_raw(new HD_Bt_ViewHandelsdef(this.r_recDef), this.oGC);
		}
			
		if (bHatZusatzComp) {
			oGridRueck.add_raw((Component)oComponent, this.oGC);
		}
		
		//20181122: zusatzinfo einblenden
		//zusatzinformation einblenden:
		if (this.get_recHANDELSDEF()!=null && S.isFull(this.get_recHANDELSDEF().get_MELDUNG_FUER_USER_cUF_NN(""))) {
			E2_Grid gridZusatzInfo = new E2_Grid()._setSize(100,400)._w100();
			gridZusatzInfo	._a(new RB_lab()._tr("Zusatzinformation zur obigen Bewertung: ")._i(),	 					new RB_gld()._left_top()._ins(2,2,2,2))
							._a(new RB_lab()._t(this.get_recHANDELSDEF().get_MELDUNG_FUER_USER_cUF_NN(""))._b(),   	new RB_gld()._left_top()._ins(2,2,2,2)._col_back(new E2_ColorMaskHighlight()))
							._bo_dd();
			
			
			oGridRueck._add(gridZusatzInfo, new RB_gld()._span(oGridRueck.getSize())._ins(2, 2, 2, 20));
			
		}
		//20181122: zusatzinfo
		
		
		
		return oGridRueck;
	}
	

	private MyE2_CheckBox __CB_IntraStat(boolean bEK)
	{
		MyE2_CheckBox oCB = new MyE2_CheckBox(bEK?(this.c_INTRASTAT_IN.equals("Y")):(this.c_INTRASTAT_OUT.equals("Y")),true);
		oCB.setLayoutData(this.oGC_REL);
		oCB.setEnabled(false);
		oCB.set_Icons(true);
		return oCB;
	}
	
	private MyE2_CheckBox __CB_Transit(boolean bEK)
	{
		MyE2_CheckBox oCB = new MyE2_CheckBox(bEK?(this.c_TRANSIT_EK.equals("Y")):(this.c_TRANSIT_VK.equals("Y")),true);
		oCB.setLayoutData(this.oGC_REL);
		oCB.setEnabled(false);
		oCB.set_Icons(true);
		return oCB;
	}

	
	
	
	private class ownREC_TAX extends RECORD_TAX_EXT		{
		
		private MyDate leistungsDatum = null;
		
		public ownREC_TAX(RECORD_TAX recordOrig, MyDate p_leistungsdatum) {
			super(recordOrig);
			
			this.leistungsDatum = p_leistungsdatum;
		}
		
		public MyE2_LabelWrap __LabelSteuerSatz() throws myException 	{
			MyE2_LabelWrap oLab = new MyE2_LabelWrap(this.getSteuerSatzKorrigiert(this.leistungsDatum,"<??>"));
			oLab.setLayoutData(HD_WarenBewegungEinstufung.this.oGL);
			oLab.setLineWrap(true);
			return oLab;
		}
		public MyE2_LabelWrap __LabelSteuerText() throws myException
		{
			MyE2_LabelWrap oLab = new MyE2_LabelWrap(this.get_STEUERVERMERK_cUF_NN("<vermerk>"));
			oLab.setLayoutData(HD_WarenBewegungEinstufung.this.oGL);
			oLab.setLineWrap(true);
			return oLab;
		}
	}

	
	

	private Component  get_DoppelComponentTax(ownREC_TAX  oRecTax, ownREC_TAX oRecTaxNegativ) throws myException {
		if (oRecTaxNegativ == null ){
			MyE2_LabelWrap steuerNormal = oRecTax.__LabelSteuerSatz();
			return steuerNormal;
		} else {
			
			MyE2_Grid oGrid = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
			MyE2_LabelWrap steuerNormal = oRecTax.__LabelSteuerSatz();
			MyE2_LabelWrap steuerNegativ = oRecTaxNegativ.__LabelSteuerSatz();
			steuerNegativ.setStyle(MyE2_Label.STYLE_NORMAL_GREY());
			
			oGrid.add(steuerNormal, this.oGC);
			oGrid.add(steuerNegativ, this.oGC);
			oGrid.setRowHeight(0, new Extent(50));
			oGrid.setRowHeight(1, new Extent(50));
			
			return oGrid;
		}
	}
	
	private Component  get_DoppelComponentSteuertext(ownREC_TAX  oRecTax, ownREC_TAX oRecTaxNegativ) throws myException {
		if (oRecTaxNegativ == null ){
			return new MyE2_LabelWrap(oRecTax.get_STEUERVERMERK_cUF_NN("<vermerk>"));
		} else {
			
			MyE2_Grid oGrid = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
			oGrid.add(new MyE2_LabelWrap(oRecTax.get_STEUERVERMERK_cUF_NN("<vermerk>")), this.oGL);
			oGrid.add(new MyE2_LabelWrap(oRecTaxNegativ.get_STEUERVERMERK_cUF_NN("<vermerk>"),MyE2_Label.STYLE_NORMAL_GREY()), this.oGL);
			oGrid.setRowHeight(0, new Extent(50));
			oGrid.setRowHeight(1, new Extent(50));

			return oGrid;
		}
	}
	
	
	
	

	public HD_Station get_oHD_StationEK() {
		return oStationEK;
	}


	public HD_Station get_oHD_StationVK()	{
		return oStationVK;
	}


	public String get_c_TRANSIT_EK()	{
		return c_TRANSIT_EK;
	}


	public String get_c_TRANSIT_VK()	{
		return c_TRANSIT_VK;
	}
	
	
	public boolean isUpdatingBothSidesAllowed() throws myException
	{
		return this.oStationEK.isUpdatingAllowd() && this.oStationVK.isUpdatingAllowd();
	}
	
	
}
