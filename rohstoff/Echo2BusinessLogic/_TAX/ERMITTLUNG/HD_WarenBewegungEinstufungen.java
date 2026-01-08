package rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG;

import java.util.Vector;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBoldItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.ENUM_MESSAGE_PROVIDER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAND;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;


/**
 * spezielle Vector-klasse, die dafuer sorgt, dass jeder stationstyp nur einmal aufgenommen wird
 * @author martin
 *
 */
public class HD_WarenBewegungEinstufungen extends Vector<HD_WarenBewegungEinstufung>
{
	/*
	 * layoutData-Objecte fuer die fehler-darstellungen
	 */
	private GridLayoutData				oGL						= MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I_2_2_2_2, 	new E2_ColorBase(), 1);
	private GridLayoutData				oGC_REL					= MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2, 	new E2_ColorBase(), 1);

	
	
	private HD_Station 		oStationEK = null;
	private HD_Station 		oStationVK = null;

	private String          idFuhre4Message = null;
	
	public HD_WarenBewegungEinstufungen() throws myException {
		super();
	}


	public HD_WarenBewegungEinstufungen( HD_Station 	StationEK,  HD_Station  StationVK) throws myException
	{
		super();
		
		this.oStationEK = StationEK;
		this.oStationVK = StationVK;
	}


	public boolean add(HD_WarenBewegungEinstufung oEinstufung)
	{
		return this.add(oEinstufung, true);
	}
	
	
	public boolean addAll(HD_WarenBewegungEinstufungen vEinstufung)
	{
		return this.addAll(vEinstufung, true);
	}
	
	
	/*
	 * 2014-01-02: pruefen, der Vector der gefundenen handelsdefinitionen evtl. aus erlaubten
	 *             mehrfachvarianten besteht
	 */
	public boolean  get_bVectorHasAllowedMultidefinitions() throws myException {
		
//		Vector<String> vPruefung = new Vector<String>();
//		
//		for (HD_WarenBewegungEinstufung oStufung: this) {
//			String cDoubleKey = ""+(oStufung.get_recHANDELSDEF().get_VERSIONSZAEHLER_lValue(new Long(-1)).longValue());
//			if (!vPruefung.contains(cDoubleKey)) {
//				vPruefung.add(cDoubleKey);
//			}
//		}
//		
//		
//		return (this.size()==vPruefung.size());    //dann hat jede gefundene handelsdefinition einen eigenen versionszaehler, damit ist es als mehrfacheingabe erlaubt
		return true;
	}
	
	
	
	/**
	 * 
	 * @param oEinstufung
	 * @param bAllowDoubles
	 * @return
	 */
	public boolean add(HD_WarenBewegungEinstufung oEinstufung, boolean bAllowDoubles)
	{
		if (this.contains(oEinstufung) && !bAllowDoubles)
		{
			return false;
		}
		else
		{
			return super.add(oEinstufung);
		}
	}
	
	
	public boolean addAll(HD_WarenBewegungEinstufungen vEinstufung, boolean bAllowDoubles)
	{
		for (HD_WarenBewegungEinstufung oStat: vEinstufung)
		{
			this.add(oStat, bAllowDoubles);
		}
		return true;
	}

	

	
	
	/**
	 * Grid wird eingeblendet, wenn dieser Vector != 1 elemente enthaelt
	 * 
	 * @param oAutValidator_4_inputButton   :  falls != null, dann erscheint ein erfassungbutton fuer eine fehlende handelsdefinition
	 * @param oTitel
	 * @return
	 * @throws myException 
	 */
	public MyE2_Grid get_GridWithHandelsRelation(MyE2_String oTitel) throws myException
	{
		// felder: adresse-jur-quelle / land / adresse geo-quelle / land / Sorte Quelle / rc / prod / dienst / tpverantwort /adresse-jur-quelle / land / adresse geo-quelle / land / Sorte Quelle / rc / prod / dienst

		if (this.oStationEK==null || this.oStationVK==null)
		{
			throw new myException("Error: Grid only posible with notnull stations");
		}
		
		
		int[] iSpalten =
		{ 100, 20, 20, 100, 20, 20, 100, 10, 10, 10, 10,   100,   100, 20, 20, 100, 20, 20, 100, 10, 10, 10, 10 };

		MyE2_Grid oGridRueck = new MyE2_Grid(iSpalten,  MyE2_Grid.STYLE_GRID_BORDER_W100(Color.BLACK));

		
		MyE2_Grid  oGrid4Buttons = new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		oGridRueck.add(new MyE2_Label(oTitel, new E2_FontBoldItalic()), MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2, new E2_ColorDDDark(), 22));
		oGridRueck.add(oGrid4Buttons, MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2, new E2_ColorDDDark(), 1));

		//wenn keins gefunden, dann eines erfassen
		if (this.size()==0)
		{
			oGrid4Buttons.add(new HD_Bt_CreateNewHandelsdef(this.oStationEK, this.oStationVK),E2_INSETS.I_0_0_2_0);

			this.idFuhre4Message = null;
			if (this.oStationEK!=null && this.oStationEK.get_P_RecFuhre()!=null) {
				this.idFuhre4Message = this.oStationEK.get_P_RecFuhre().get_ID_VPOS_TPA_FUHRE_cUF_NN(null);
			}
			
			if (S.isFull(idFuhre4Message)) {
				oGrid4Buttons.add((Component)new E2_Button()._image(E2_ResourceIcon.get_RI("meldung30.png"), true)._ttt(S.ms("An die im System hinterlegten Ansprechpartner eine Meldung schicken, mit der Bitte um die Erstellung einer Handelsdefinition."))._aaa(
						()-> {
							ENUM_MESSAGE_PROVIDER.MESSAGE_ERSTELLUNG_HANDELSDEFINITION
									.generateMessages(MODUL.NAME_MODUL_FUHRENFUELLER, idFuhre4Message, "Bitte um Erstellung einer Handelsdefinition für die Fuhre: "+idFuhre4Message, "Fuhrenzentrale");
						}));  
			} else {
				oGrid4Buttons.add((Component)new E2_Button()._image(E2_ResourceIcon.get_RI("meldung30.png"), true)._aaa(
						()-> {
							bibMSG.MV()._addAlarm(S.ms("Bitte lassen Sie sich eine Handelsdefinition für diese Fuhre erstellen"));
						}));  
			}
			
			
		}

		if (this.size()>1)
		{
			//wenn mehrer gefunden, dann bearbeiten
			Vector<String>  vID_HandelsDefs = new Vector<String>();
			for (HD_WarenBewegungEinstufung oEinstufung: this)
			{
				vID_HandelsDefs.add(oEinstufung.get_recHANDELSDEF().get_ID_HANDELSDEF_cUF());
			}
			oGrid4Buttons.add(new HD_Bt_OpenHandelsDef_Module(vID_HandelsDefs),E2_INSETS.I_0_0_2_0);
		}
		
		
		
		oGridRueck.add(new MyE2_Label(new MyE2_String("Ladeseite (Einkauf oder Startlager)"), new E2_FontBold()), MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2, new E2_ColorDDark(), 11));
		oGridRueck.add(new MyE2_Label(new MyE2_String(""), new E2_FontBold()), MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2, new E2_ColorDDark(), 1));
		oGridRueck.add(new MyE2_Label(new MyE2_String("Abladeseite (Verkauf oder Ziellager)"), new E2_FontBold()), MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2, new E2_ColorDDark(), 11));

		Font oFontPlain = new E2_FontPlain();
		oGridRueck.add(new MyE2_Label(new MyE2_String("Lieferant"), 	oFontPlain), MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I_2_2_2_2, new E2_ColorDark(), 2));
		oGridRueck.add(new MyE2_Label(new MyE2_String("MWSt."), 	oFontPlain), MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I_2_2_2_2, new E2_ColorDark(), 1));
		oGridRueck.add(new MyE2_Label(new MyE2_String("Ladestation"), 	oFontPlain), MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I_2_2_2_2, new E2_ColorDark(), 2));
		
		if (bibALL.get_RECORD_MANDANT().is_STEUERFINDUNG_OHNE_EIGENORTE_YES()) {
			oGridRueck.add(new MyE2_Label(new MyE2_String(""), 		oFontPlain), MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I_2_2_2_2, new E2_ColorDark(), 1));
		} else {
			oGridRueck.add(new MyE2_Label(new MyE2_String("Mand."), 		oFontPlain), MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I_2_2_2_2, new E2_ColorDark(), 1));
		}
		
		oGridRueck.add(new MyE2_Label(new MyE2_String("Ladesorte"), 	oFontPlain), MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I_2_2_2_2, new E2_ColorDark(), 1));
		oGridRueck.add(new MyE2_Label(new MyE2_String("RC"), 			oFontPlain), MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2, new E2_ColorDark(), 1));
		oGridRueck.add(new MyE2_Label(new MyE2_String("Prod."), 		oFontPlain), MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2, new E2_ColorDark(), 1));
		oGridRueck.add(new MyE2_Label(new MyE2_String("EoW"), 			oFontPlain), MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2, new E2_ColorDark(), 1));
		oGridRueck.add(new MyE2_Label(new MyE2_String("Dienst."), 		oFontPlain), MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2, new E2_ColorDark(), 1));
		
		oGridRueck.add(new MyE2_Label(new MyE2_String("TP-Verantwort."),oFontPlain), MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2, new E2_ColorDark(), 1));
		
		
		oGridRueck.add(new MyE2_Label(new MyE2_String("Abnehmer"), 		oFontPlain), MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I_2_2_2_2, new E2_ColorDark(), 2));
		oGridRueck.add(new MyE2_Label(new MyE2_String("MWSt."), 		oFontPlain), MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I_2_2_2_2, new E2_ColorDark(), 1));
		oGridRueck.add(new MyE2_Label(new MyE2_String("Abladestation"), oFontPlain), MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I_2_2_2_2, new E2_ColorDark(), 2));
		
		if (bibALL.get_RECORD_MANDANT().is_STEUERFINDUNG_OHNE_EIGENORTE_YES()) {
			oGridRueck.add(new MyE2_Label(new MyE2_String(""), 		oFontPlain), MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I_2_2_2_2, new E2_ColorDark(), 1));
		} else {
			oGridRueck.add(new MyE2_Label(new MyE2_String("Mand."), 		oFontPlain), MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I_2_2_2_2, new E2_ColorDark(), 1));
		}
		
		oGridRueck.add(new MyE2_Label(new MyE2_String("Abladesorte"), 	oFontPlain), MyE2_Grid.LAYOUT_LEFT(E2_INSETS.I_2_2_2_2, new E2_ColorDark(), 1));
		oGridRueck.add(new MyE2_Label(new MyE2_String("RC"), 			oFontPlain), MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2, new E2_ColorDark(), 1));
		oGridRueck.add(new MyE2_Label(new MyE2_String("Prod."), 		oFontPlain), MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2, new E2_ColorDark(), 1));
		oGridRueck.add(new MyE2_Label(new MyE2_String("EoW"), 			oFontPlain), MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2, new E2_ColorDark(), 1));
		oGridRueck.add(new MyE2_Label(new MyE2_String("Dienst."), 		oFontPlain), MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_2_2_2_2, new E2_ColorDark(), 1));
		
		// ladeseite
		ownREC_ADRESSE ownRecAdresseEK_JUR = 	new ownREC_ADRESSE(this.oStationEK.get_recAdresse_JUR());
		ownREC_ADRESSE ownRecAdresseEK_GEO = 	new ownREC_ADRESSE(this.oStationEK.get_recAdresse_GEO());
		ownREC_ARTIKEL ownRecArtikelEK = 		new ownREC_ARTIKEL(this.oStationEK.get_recArtikel());

		ownREC_ADRESSE ownRecAdresseVK_JUR = 	new ownREC_ADRESSE(this.oStationVK.get_recAdresse_JUR());
		ownREC_ADRESSE ownRecAdresseVK_GEO = 	new ownREC_ADRESSE(this.oStationVK.get_recAdresse_GEO());
		ownREC_ARTIKEL ownRecArtikelVK =	 	new ownREC_ARTIKEL(this.oStationVK.get_recArtikel());

		
		oGridRueck.add(ownRecAdresseEK_JUR.__LabelName());
		oGridRueck.add(ownRecAdresseEK_JUR.__LabelLand());
		oGridRueck.add(this.__CB_FirmaIstUstFaehig(this.oStationEK));
		
		oGridRueck.add(ownRecAdresseEK_GEO.__LabelName());
		oGridRueck.add(ownRecAdresseEK_GEO.__LabelLand());
		
		if (bibALL.get_RECORD_MANDANT().is_STEUERFINDUNG_OHNE_EIGENORTE_YES()) {
			oGridRueck.add(new MyE2_Label(new MyE2_String("")));
		} else {
			oGridRueck.add(this.__CB_StationIstMandant(true));
		}

		
		oGridRueck.add(ownRecArtikelEK.__LabelSorte());
		oGridRueck.add(this.__CB_RC(true));
		oGridRueck.add(this.__CB_PROD(true));
		oGridRueck.add(this.__CB_EOW(true));
		oGridRueck.add(this.__CB_DIENST(true));
		
		oGridRueck.add(this.__label_TP_Verantwortung());
		
		oGridRueck.add(ownRecAdresseVK_JUR.__LabelName());
		oGridRueck.add(ownRecAdresseVK_JUR.__LabelLand());
		oGridRueck.add(this.__CB_FirmaIstUstFaehig(this.oStationVK));

		oGridRueck.add(ownRecAdresseVK_GEO.__LabelName());
		oGridRueck.add(ownRecAdresseVK_GEO.__LabelLand());


		if (bibALL.get_RECORD_MANDANT().is_STEUERFINDUNG_OHNE_EIGENORTE_YES()) {
			oGridRueck.add(new MyE2_Label(new MyE2_String("")));
		} else {
			oGridRueck.add(this.__CB_StationIstMandant(false));
		}

		oGridRueck.add(ownRecArtikelVK.__LabelSorte());
		oGridRueck.add(this.__CB_RC(false));
		oGridRueck.add(this.__CB_PROD(false));
		oGridRueck.add(this.__CB_EOW(false));
		oGridRueck.add(this.__CB_DIENST(false));
		

		return oGridRueck;

	}

	private class ownREC_ADRESSE extends RECORD_ADRESSE
	{

		public ownREC_ADRESSE(RECORD_ADRESSE recAdresse) throws myException
		{
			super(recAdresse);
		}

		public MyE2_Label __LabelName() throws myException
		{
			MyE2_Label oLab = new MyE2_Label(this.get_NAME1_cUF_NN("<name1>") + " " + this.get_ORT_cUF_NN("<ort>"),MyE2_Label.STYLE_NORMAL_GREY());
			oLab.setLayoutData(HD_WarenBewegungEinstufungen.this.oGL);
			oLab.setLineWrap(true);
			return oLab;
		}

		public MyE2_Label __LabelLand() throws myException
		{
			MyE2_Label oLab = new MyE2_Label("");

			RECORD_LAND recLand = this.get_UP_RECORD_LAND_id_land();
			if (recLand != null)
			{
				oLab = new MyE2_Label(recLand.get_LAENDERCODE_cUF_NN("<code>"), new E2_FontBold(2));
			}
			oLab.setLayoutData(HD_WarenBewegungEinstufungen.this.oGC_REL);
			oLab.setLineWrap(true);

			return oLab;
		}

	}

	private class ownREC_ARTIKEL extends RECORD_ARTIKEL
	{

		public ownREC_ARTIKEL(RECORD_ARTIKEL recArtikel) throws myException
		{
			super(recArtikel);
		}

		public MyE2_Label __LabelSorte() throws myException
		{
			MyE2_Label oLab = new MyE2_Label(this.get_ANR1_cUF_NN("<anr1>") + " " + this.get_ARTBEZ1_cUF_NN("<artbez1>"), MyE2_Label.STYLE_NORMAL_GREY());
			oLab.setLayoutData(HD_WarenBewegungEinstufungen.this.oGL);
			oLab.setLineWrap(true);

			return oLab;
		}

	}

	private MyE2_Label  __CB_RC(boolean bEK)
	{
		boolean bAN =(bEK ? this.oStationEK.get_bSorteRC() : this.oStationVK.get_bSorteRC());
		
		MyE2_Label oCB = new MyE2_Label(bAN?E2_ResourceIcon.get_RI("checkboxon.gif"):E2_ResourceIcon.get_RI("checkboxoff.gif"));
		oCB.setLayoutData(this.oGC_REL);
		oCB.setEnabled(false);
		oCB.setToolTipText(new MyE2_String("Sorte trägt das Merkmal <Reverse Charge>").CTrans());
		return oCB;
	}

	private MyE2_Label __CB_PROD(boolean bEK)
	{
		boolean bAN =(bEK ? this.oStationEK.get_bSorteProdukt() : this.oStationVK.get_bSorteProdukt());

		MyE2_Label oCB = new MyE2_Label(bAN?E2_ResourceIcon.get_RI("checkboxon.gif"):E2_ResourceIcon.get_RI("checkboxoff.gif"));

		oCB.setLayoutData(this.oGC_REL);
		oCB.setEnabled(false);
		oCB.setToolTipText(new MyE2_String("Sorte trägt das Merkmal <Produkt/EoW>").CTrans());
		return oCB;
	}

	private MyE2_Label __CB_EOW(boolean bEK)
	{
		boolean bAN =(bEK ? this.oStationEK.get_bSorteEoW() : this.oStationVK.get_bSorteEoW());

		MyE2_Label oCB = new MyE2_Label(bAN?E2_ResourceIcon.get_RI("checkboxon.gif"):E2_ResourceIcon.get_RI("checkboxoff.gif"));

		oCB.setLayoutData(this.oGC_REL);
		oCB.setEnabled(false);
		oCB.setToolTipText(new MyE2_String("Sorte trägt das Merkmal <Produkt/EoW>").CTrans());
		return oCB;
	}

	
	
	
	private MyE2_Label __CB_DIENST(boolean bEK)
	{
		boolean bAN =(bEK ? this.oStationEK.get_bSorteDienstleistung() : this.oStationVK.get_bSorteDienstleistung());

		MyE2_Label oCB = new MyE2_Label(bAN?E2_ResourceIcon.get_RI("checkboxon.gif"):E2_ResourceIcon.get_RI("checkboxoff.gif"));

		oCB.setLayoutData(this.oGC_REL);
		oCB.setEnabled(false);
		oCB.setToolTipText(new MyE2_String("Sorte trägt das Merkmal <Dienstleistung>").CTrans());
		return oCB;
	}

	
	private MyE2_Label __CB_StationIstMandant(boolean bEK)
	{
		boolean bAN =(bEK ? this.oStationEK.get_bStationIstMandant() : this.oStationVK.get_bStationIstMandant());
		
		MyE2_Label oCB = new MyE2_Label(bAN?E2_ResourceIcon.get_RI("checkboxon.gif"):E2_ResourceIcon.get_RI("checkboxoff.gif"));

		oCB.setLayoutData(this.oGC_REL);
		oCB.setEnabled(false);
		oCB.setToolTipText(new MyE2_String("Firma ist der Mandant").CTrans());
		return oCB;
	}

	private MyE2_Label __CB_FirmaIstUstFaehig(HD_Station  oStat)
	{

		MyE2_Label oCB = new MyE2_Label(oStat.get_bFirmaKannUST()?E2_ResourceIcon.get_RI("checkboxon.gif"):E2_ResourceIcon.get_RI("checkboxoff.gif"));

		oCB.setLayoutData(this.oGC_REL);
		oCB.setEnabled(false);
		oCB.setToolTipText(new MyE2_String("Firma nimmt am Umsatzsteuerverfahren teil (Inland RC+Vorsteuerabzug, Ausland steuerfreie Lieferung)").CTrans());
		return oCB;
	}


	
	
	private MyE2_Label  __label_TP_Verantwortung()
	{
		String cVerant = this.oStationEK.get_cTP_Verantwortung();
		
		MyE2_Label oLab = new MyE2_Label(cVerant,new E2_FontBold());
		oLab.setLayoutData(this.oGC_REL);
		oLab.setLineWrap(true);
		return oLab;
	}


	
	/*
	 * prueft, ob alle vorhandenen einstufungen gesetzt werden koennen,
	 * oder ob es bereits preisabschluesse gibt
	 */
	public boolean isUpdatingBothSidesAllowedForAllMembers() throws myException
	{
		boolean bRueck = true;
		
		for (HD_WarenBewegungEinstufung oEinstufung: this){
			if (!oEinstufung.isUpdatingBothSidesAllowed()) {
				bRueck=false;
				break;
			}
		}
		return bRueck;
	}
	
	
	
	/**
	 * macht system-Meldungen fuer Handelsdef-vector
	 * @throws myException
	 */
	public void generate_Messages_4_User() throws myException
	{
		//meldung(en) generieren
		VectorSingle vMeldungstexte = new VectorSingle();
		for (HD_WarenBewegungEinstufung HD_FU: this) {
			if (HD_FU.get_recHANDELSDEF()!=null && S.isFull(HD_FU.get_recHANDELSDEF().get_MELDUNG_FUER_USER_cUF_NN(""))) {
				if (!vMeldungstexte.contains(HD_FU.get_recHANDELSDEF().get_MELDUNG_FUER_USER_cUF_NN(""))) {
					new HD_GenerateMessageFromFoundHandelsdef(HD_FU.get_recHANDELSDEF());
				}
			}
		}

	}


	public GridLayoutData getoGL() {
		return oGL;
	}


	public GridLayoutData getoGC_REL() {
		return oGC_REL;
	}


	public HD_Station getStationEK() {
		return oStationEK;
	}


	public HD_Station getStationVK() {
		return oStationVK;
	}

	public HD_WarenBewegungEinstufungen _a(HD_WarenBewegungEinstufung einstufung) {
		this.add(einstufung);
		return this;
	}
	
	
	
}

