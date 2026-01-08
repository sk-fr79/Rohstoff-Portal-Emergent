package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.E2_Grid4MaskSimple;
import panter.gmbh.Echo2.components.E2_Subgrid_4_Mask;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Grid_InLIST;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row_EveryTimeEnabled;
import panter.gmbh.Echo2.staticStyles.Style_Grid_Normal;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

/*
 *	hilfsklasse um die Grids, die in den Tabs der Maske stehen, zu fuellen,
 *	damit die layouts fuer die reine fuhrenmaske, aber auch fuer die anteile in 
 *  der TPA-POSitions-Maske verwendet werden kann
 */
public class FU_MASK_GRID extends MyE2_Grid
{
	// falls die Feld-Hashwerte einen Vorsatz haben, dann kann der hier uebergeben werden
	private E2_ComponentMAP oComponentMAP_Mask = null;

	
	
	/**
	 * 
	 * @param iPageNumber
	 * @param oMap
	 * @throws myException
	 */
	public FU_MASK_GRID(int iPageNumber, E2_ComponentMAP oMap) throws myException
	{
		super(7, new Style_Grid_Normal(0,new Insets(1)));
		//super(7, MyE2_Grid.STYLE_GRID_DDARK_BORDER_INSETS_11());
		
		
		Extent  extRahmenbreite = new Extent(350);
		
		this.oComponentMAP_Mask = 	oMap;
		
		Insets oINL = new Insets(3,2,5,0);    // linke spalte mit beschriftung wird weiter abgesetzt
		Insets oIN = new Insets(3,2,5,0);
		Insets oINTrenner = new Insets(0,1,0,0);    // linke spalte mit beschriftung wird weiter abgesetzt
	
		//2011-02-01: Alignment auf base
		Alignment oAlignBase = new Alignment(Alignment.LEFT, Alignment.BOTTOM);
		Alignment oAlignMid = new Alignment(Alignment.LEFT, Alignment.CENTER);

		E2_MaskFiller oFiller = new E2_MaskFiller(oMap,null,null,oINL,oIN, new Alignment(Alignment.LEFT,Alignment.CENTER)); 

		Border oBorder = new Border(2,Color.BLACK,Border.STYLE_SOLID);
		
		if (iPageNumber == 0)
		{
			GridLayoutData oGLTitel = new GridLayoutData();
			GridLayoutData oGLInhalt = new GridLayoutData();
			oGLTitel.setBackground(new E2_ColorDDDark());
			oGLTitel.setInsets(new Insets(1,1,5,0));
			oGLTitel.setAlignment(new Alignment(Alignment.CENTER,Alignment.CENTER));
			oGLInhalt.setBackground(new E2_ColorDDDark());
			oGLInhalt.setInsets(new Insets(1,1,5,5));
			oGLInhalt.setAlignment(new Alignment(Alignment.CENTER,Alignment.CENTER));
			
			MyE2_Grid oGridNummern = new MyE2_Grid(9,MyE2_Grid.STYLE_GRID_DDARK_BORDER());
			oGridNummern.add(new MyE2_Label("ID",MyE2_Label.STYLE_SMALL_ITALIC()),oGLTitel);
			oGridNummern.add(new MyE2_Label("Buchungsnr",MyE2_Label.STYLE_SMALL_ITALIC()),oGLTitel);
			oGridNummern.add(new MyE2_Label("Sonderdef",MyE2_Label.STYLE_SMALL_ITALIC()),oGLTitel);

			MyE2_Label labHelp3 = new MyE2_Label("Fremdwaren",MyE2_Label.STYLE_SMALL_ITALIC());
			labHelp3.setLineWrap(true);
			labHelp3.setToolTipText(new MyE2_String("Zeigt an, dass in dieser Fuhre keine Buchungssätze auf die gefahrene Sorte erlaubt sind (z.b. reine Fahraufträge)").CTrans());
			oGridNummern.add(labHelp3,oGLTitel);

			MyE2_Label labHelp1 = new MyE2_Label("Kein VK-Kon.",MyE2_Label.STYLE_SMALL_ITALIC());
			labHelp1.setLineWrap(true);
			labHelp1.setToolTipText(new MyE2_String("Zeigt an, dass in dieser Transport-Position kein Warenausgangskontrakt angegeben werden darf!").CTrans());
			oGridNummern.add(labHelp1,oGLTitel);
			
			MyE2_Label labHelp = new MyE2_Label("Ohne AVV-Vertr.Check",MyE2_Label.STYLE_SMALL_ITALIC());
			labHelp.setLineWrap(true);
			labHelp.setToolTipText(new MyE2_String("Zeigt an, dass in dieser Transport-Position kein AVV-Vertrags-Check erfolgt").CTrans());
			oGridNummern.add(labHelp,oGLTitel);
			
			MyE2_Label labHelp4 = new MyE2_Label("Fremdauftrag (=Fremdwaren-Fuhre) ",MyE2_Label.STYLE_SMALL_ITALIC());
			labHelp4.setLineWrap(true);
			labHelp4.setToolTipText(new MyE2_String("Zeigt an, dass in dieser Fuhre keine Buchungssätze auf die gefahrene Sorte erlaubt sind (z.b. reine Fahraufträge)").CTrans());
			oGridNummern.add(labHelp4,oGLTitel);
			
			oGridNummern.add(new MyE2_Label("Zeitstempel",MyE2_Label.STYLE_SMALL_ITALIC()),oGLTitel);
			oGridNummern.add(new MyE2_Label("Wer?",MyE2_Label.STYLE_SMALL_ITALIC()),oGLTitel);
			
			oGridNummern.add(C("ID_VPOS_TPA_FUHRE"),oGLInhalt);
			oGridNummern.add(C("BUCHUNGSNR_FUHRE"),oGLInhalt);
			
			oGridNummern.add(C("ID_VPOS_TPA_FUHRE_SONDER"),oGLInhalt);
			oGridNummern.add(C("OHNE_ABRECHNUNG"),oGLInhalt);
			oGridNummern.add(C("KEIN_KONTRAKT_NOETIG"),oGLInhalt);
			oGridNummern.add(C("OHNE_AVV_VERTRAG_CHECK"),oGLInhalt);
			
			oGridNummern.add(C("ID_ADRESSE_FREMDAUFTRAG"),oGLInhalt);
			
			oGridNummern.add(C("ZEITSTEMPEL"),oGLInhalt);
			oGridNummern.add(C("ERZEUGT_VON"),oGLInhalt);
			
			this.add(oGridNummern,7, oIN);
//			oFiller.add_Trenner(this, oIN);

			//die komponente FU_CONST.HASH_KEY_STORNO_ANZEIGE ist eine Row, wo alle storno-infos eingebettet werden, die rot gefaerbt wird, wenn ein storno vorliegt
			MyE2_Row_EveryTimeEnabled oRowhelp = (MyE2_Row_EveryTimeEnabled)oMap.get__Comp(FU___CONST.HASH_KEY_STORNO_ANZEIGE);
			Insets iHelp = new Insets(0,0,3,0);
			Insets iHelp2 = new Insets(0,0,5,0);
			
			oRowhelp.add(new MyE2_Label("Storniert ?"), iHelp);
			oRowhelp.add((Component)oMap.get_Comp("IST_STORNIERT"), iHelp);
			oRowhelp.add((Component)oMap.get_Comp("STORNO_GRUND"), iHelp2);
			oRowhelp.add((Component)oMap.get_Comp("STORNO_KUERZEL"), iHelp2);
			
			
			oFiller.add_Line(this,FU___CONST.HASH_KEY_STORNO_ANZEIGE+
										"|# |#Alt|ALT_WIRD_NICHT_GEBUCHT|" +
										 "# |#EK=VK-Sorte|EK_VK_SORTE_LOCK|" +
										 "# |#Kontraktzuord.|EK_VK_ZUORD_ZWANG|# |# TP-Verantwort.|TP_VERANTWORTUNG|# |"+FU___CONST.HASH_KEY_BUTTON_MASK_OPEN_TPA+"|# |#Überstimme Fehler|SPEICHERN_TROTZ_INKONSISTENZ|",7);

			
			oFiller.add_Trenner(this, oINTrenner);

			oFiller.add_Line(this,		new MyE2_String("Wiegesch.Laden"),     1,"WIEGEKARTENKENNER_LADEN|# |ID_WIEGEKARTE_LIEF|#Intra|INTRASTAT_MELD_IN|#Transit|"+_DB.VPOS_TPA_FUHRE$TRANSIT_EK,  2,new MyE2_String(" "),1,
										new MyE2_String("Wiegesch.AbLaden"),1,"WIEGEKARTENKENNER_ABLADEN|# |ID_WIEGEKARTE_ABN|#Intra|INTRASTAT_MELD_OUT|#Transit|"+_DB.VPOS_TPA_FUHRE$TRANSIT_VK,2);

			//2015-12-09: mehrfachauswahl datumsfelder, wird in ein grid mit datumszauberstab gelegt
			E2_Grid4MaskSimple datehelpers = new E2_Grid4MaskSimple()	.def_(E2_INSETS.I(0,0,2,0))
																		.def_(30)
																		.add_(this.oComponentMAP_Mask.get_Comp(FU___CONST.HASH_KEY_BUTTON_MASK_VERTEILE_DATUM))
																		.add_(this.oComponentMAP_Mask.get_Comp(FU___CONST.HASH_KEY_MASK_BUTTON_MULTI_POPUP_DATE));
			oFiller.add_Line(this,new MyE2_String("Abholzeit/Laded."),1,
													oFiller.get_oGridWithComponents("DATUM_ABHOLUNG|DATUM_ABHOLUNG_ENDE|DATUM_AUFLADEN|",oAlignMid).get_InBorderGrid(oBorder,extRahmenbreite,null),2,
													datehelpers,1,
													new MyE2_String("Lieferz./Abladedat."),1,
													oFiller.get_oGridWithComponents("DATUM_ANLIEFERUNG|DATUM_ANLIEFERUNG_ENDE|DATUM_ABLADEN",oAlignMid).get_InBorderGrid(oBorder,extRahmenbreite,null),2);
			
			//2011-12-30: aenderung wegen uma-kontrakt
			oFiller.add_Line(this,new MyE2_String("EK-Kontrakt"),      1,"ID_VPOS_KON_EK",  2,FU___CONST.HASH_MASK_BUTTON_UMA_KONTRAKT,1,new MyE2_String("VK-Kontrakt"),1,"ID_VPOS_KON_VK",2);
			
			//2012-01-09: uma-kontraktanzeigen
			oFiller.add_Line(this,FU___CONST.HASH_MASK_COMP_UMA_MASKEN_ANZEIGE,  7);
			
			//20180604: waehrungsinfo in die maske 
			E2_Grid gWaehrung = (E2_Grid) this.oComponentMAP_Mask.get_Comp(FU___CONST.FIELDNAME_INFOBOCK_WAEHRUNGEN);

			// /*alt-vor 20180604*/ oFiller.add_Line(this,new MyE2_String("Lieferant/Ladeort"),1,"ID_ADRESSE_START",2,new MyE2_String(" "),1,new MyE2_String("Abnehmer/Ablade."),1,"ID_ADRESSE_ZIEL",2);
			oFiller.add_Line(this,new MyE2_String("Lieferant/Ladeort"),1,"ID_ADRESSE_START",2,gWaehrung,1,new MyE2_String("Abnehmer/Ablade."),1,"ID_ADRESSE_ZIEL",2);
			oFiller.add_Line(this,"#Abholadresse|"+FU___CONST.HASH_MASK_COMP_FREMDWARENLAGER_LADESEITE,1,"ID_ADRESSE_LAGER_START",2,new MyE2_String(" "),1,"#Lieferadresse|"+FU___CONST.HASH_MASK_COMP_FREMDWARENLAGER_ABLADESEITE,1,"ID_ADRESSE_LAGER_ZIEL",2);
			oFiller.add_Line(this,new MyE2_String("Öffnungszeiten"),1,"OEFFNUNGSZEITEN_LIEF",2,new MyE2_String(" "),1,new MyE2_String("Öffnungszeiten"),1,"OEFFNUNGSZEITEN_ABN",2);

			oFiller.add_Line(this,new MyE2_String("Ext-Best-Nr.EK"),1,"BESTELLNUMMER_EK|<L3L>#Posten:|POSTENNUMMER_EK",2,new MyE2_String(" "),1,new MyE2_String("Ext-Best-Nr.VK"),1,"BESTELLNUMMER_VK|<L3L>#Posten:|POSTENNUMMER_VK",2);
			

			//2012-03-26: mengenverteilerbuttons statt labels in der ueberschrift
			oFiller.add_Line_WithComponents(this, 
									new MyE2_Label(new MyE2_String("Mengen (Teile)"),new E2_FontBold(2)), E2_INSETS.I_3_0_0_5, oAlignMid,
									new E2_Subgrid_4_Mask("Planmenge(1)||Lademenge(2)||Ablademenge(3)||","ANTEIL_PLANMENGE_LIEF|"+FU___CONST.HASH_KEY_BUTTON_ZAUBERSTAB_ANTEIL_PLANMENGE_LIEF+"|ANTEIL_LADEMENGE_LIEF|"+
																FU___CONST.HASH_KEY_BUTTON_ZAUBERSTAB_ANTEIL_LADEMENGE_LIEF+"|ANTEIL_ABLADEMENGE_LIEF||",oMap,
																null, null).get_InBorderGrid(new Border(2, Color.BLACK, Border.STYLE_SOLID),extRahmenbreite,null), 
																2, E2_INSETS.I_0_0_0_0,oAlignBase, 
									new MyE2_Label(new MyE2_String(" ")),1, E2_INSETS.I_3_0_0_5,oAlignBase,
									new MyE2_Label(new MyE2_String("Mengen (Teile)"),new E2_FontBold(2)),1, E2_INSETS.I_3_0_0_5,oAlignMid, 
									new E2_Subgrid_4_Mask("Planmenge (4)(!!!)||Lademenge(5)||Ablademenge(6)||","|ANTEIL_PLANMENGE_ABN||ANTEIL_LADEMENGE_ABN||ANTEIL_ABLADEMENGE_ABN|"+
																FU___CONST.HASH_KEY_BUTTON_ZAUBERSTAB_ANTEIL_ABLADEMENGE_ABN+"|",oMap, 
																null, null).get_InBorderGrid(new Border(2, Color.BLACK, Border.STYLE_SOLID),extRahmenbreite,null),
															     2, E2_INSETS.I_0_0_0_0,oAlignBase); 
			

			
			
			oFiller.add_Trenner(this, oINTrenner);

			
			oFiller.add_Line(this,new MyE2_String("Hauptsorte"),1,"ID_ARTIKEL", 2,new MyE2_String(" "),1,new MyE2_String("Zolltarif-Nummer"),1,"ZOLLTARIFNR<W80W>|#Einheit:|EINHEIT_MENGEN|"+FU___CONST.HASH_KEY_GEFAHRGUT_ANZEIGE,2);
			
			oFiller.add_Line(this,FU___CONST.HASH_KEY_ARTBEZ_ANZEIGE_EK, 3, new MyE2_String(" "),1, FU___CONST.HASH_KEY_ARTBEZ_ANZEIGE_VK, 3, oAlignBase);
			
			oFiller.add_Trenner(this, oINTrenner);

			oFiller.add_Line(this,new MyE2_String("Transitländer"),1,	"LAENDERCODE_TRANSIT1|LAENDERCODE_TRANSIT2|LAENDERCODE_TRANSIT3|# Druck EU-Blatt|PRINT_EU_AMTSBLATT",2,	new MyE2_String(" "),1,new MyE2_String("EU-Blatt abw.Mge"),1,"|EU_BLATT_MENGE|#Vol.|EU_BLATT_VOLUMEN|# Dat.EU-Bl.|AVV_AUSSTELLUNG_DATUM",2);
			oFiller.add_Line(this,new MyE2_String("Transportmittel"),1,"TRANSPORTMITTEL|#Kennz.|TRANSPORTKENNZEICHEN|#/|ANHAENGERKENNZEICHEN",2,		new MyE2_String(" "),1,new MyE2_String("Notifi./Begleit.-Nr"),1,"NOTIFIKATION_NR|#(Noti-EK:|NOTIFIKATION_NR_EK|#)",2);
			
			

			oFiller.add_Trenner(this, oINTrenner);
			
			oFiller.add_Line(this,new MyE2_String("Abnahme-Ang."),	1,	"ID_VPOS_STD_EK",2,							new MyE2_String(" "),1,new MyE2_String("Verkaufs-Ang."),	1, "ID_VPOS_STD_VK",2);
			oFiller.add_Line(this,new MyE2_Label(new MyE2_String("Einkaufspreis"),new E2_FontBold(2)),	1,	"EINZELPREIS_EK|MANUELL_PREIS_EK|#  |#MWSt.|STEUERSATZ_EK",2,	FU___CONST.HASH_KEY_BUTTON_MASK_LADE_PREIS_UND_STEUER,1,new MyE2_Label(new MyE2_String("Verkaufspreis"),new E2_FontBold(2)),1, "EINZELPREIS_VK|MANUELL_PREIS_VK|#  |#MWSt.|STEUERSATZ_VK",2);

			//2014-05-27: lieferbedingungen (alternativ)
			oFiller.add_Line(this,new MyE2_Label(new MyE2_String("Lieferbed. (EK)"),new E2_FontPlain()),	1,	FU___CONST.HASH_KEY_MASKFIELD_LIEFERBED_ALTERNATIV_EK,2,	new MyE2_String(""),1,new MyE2_Label(new MyE2_String("Lieferbed. (VK)"),new E2_FontPlain()),1, FU___CONST.HASH_KEY_MASKFIELD_LIEFERBED_ALTERNATIV_VK,2);
			//---------------------------------------------
			
			//2013-01-03: steuervermerk in grid anordnen
//			oFiller.add_Line(this,new MyE2_String("Steuervermerk"),	1,	"EU_STEUER_VERMERK_EK|"+FU___CONST.HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT_EK,2,	
//									new MyE2_String(" "),1,new MyE2_String("Steuervermerk"),	1, "EU_STEUER_VERMERK_VK|"+FU___CONST.HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT_VK,2);

			MyE2_Grid oGridSteuerLinks = new MyE2_Grid(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			MyE2_Grid oGridSteuerRechts = new MyE2_Grid(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			oGridSteuerLinks.add(this.oComponentMAP_Mask.get_Comp("EU_STEUER_VERMERK_EK"),1,2,E2_INSETS.I_0_0_2_0, new Alignment(Alignment.LEFT, Alignment.CENTER));
			oGridSteuerLinks.add(this.oComponentMAP_Mask.get_Comp("ID_TAX_EK"),E2_INSETS.I_0_0_0_0);
			if (ENUM_MANDANT_DECISION.FUHRENMASKE_ALTE_STEUER_AUSBLENDEN.is_YES_FromSession()) {
				oGridSteuerLinks.add(new RB_lab(),E2_INSETS.I_0_0_0_0);
			} else {
				oGridSteuerLinks.add(this.oComponentMAP_Mask.get_Comp(FU___CONST.HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT_EK),E2_INSETS.I_0_0_0_0);
			}
			
			oGridSteuerRechts.add(this.oComponentMAP_Mask.get_Comp("EU_STEUER_VERMERK_VK"),1,2,E2_INSETS.I_0_0_2_0, new Alignment(Alignment.LEFT, Alignment.CENTER));
			oGridSteuerRechts.add(this.oComponentMAP_Mask.get_Comp("ID_TAX_VK"),E2_INSETS.I_0_0_0_0);
			if (ENUM_MANDANT_DECISION.FUHRENMASKE_ALTE_STEUER_AUSBLENDEN.is_YES_FromSession()) {
				oGridSteuerRechts.add(new RB_lab(),E2_INSETS.I_0_0_0_0);
			} else {
				oGridSteuerRechts.add(this.oComponentMAP_Mask.get_Comp(FU___CONST.HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT_VK),E2_INSETS.I_0_0_0_0);
			}
			
			
			//20181127: handelsdef-komponente einblende
			E2_Grid gHandeldef = new E2_Grid()._bo_dd()._setSize(60)._a(new RB_lab("Steuer-Rg.")._fsa(-2)._i(), new RB_gld()._center_mid());
			gHandeldef._a(oComponentMAP_Mask.get__CompEcho(VPOS_TPA_FUHRE.id_handelsdef), new RB_gld()._center_mid());
			gHandeldef._a(new E2_Grid()._setSize(30,30)
								._a(oComponentMAP_Mask.get__CompEcho(FU___CONST.HASH_MASK_EDIT_ID_HANDELSDEF), new RB_gld()._center_mid())
								._a(oComponentMAP_Mask.get__CompEcho(FU___CONST.HASH_MASK_ERASE_ID_HANDELSDEF), new RB_gld()._center_mid())
						 );
			
			//alt:  oFiller.add_Line(this, new MyE2_String("Steuervermerk"), 1, oGridSteuerLinks, 2, new MyE2_String(""),1, new MyE2_String("Steuervermerk"),	1,  oGridSteuerRechts, 2);
			 oFiller.add_Line(this, new MyE2_String("Steuervermerk"), 1, oGridSteuerLinks, 2, gHandeldef,1, new MyE2_String("Steuervermerk"),	1,  oGridSteuerRechts, 2);
			
			
			
			
			//2013-01-014: weitere freigaben hier einblenden
			GridLayoutData  oGL_LeftCenter = 	MyE2_Grid.LAYOUT_LEFT_TOP(new Insets(0, 0, 2, 0));
			GridLayoutData  oGL_LeftCenterW =	MyE2_Grid.LAYOUT_LEFT_TOP(new Insets(0, 0, 5, 0));
			GridLayoutData  oGL_LeftCenterB =	MyE2_Grid.LAYOUT_CENTER_TOP(new Insets(10, 0, 0, 0));

			


			
			
			RB_gld ld = new RB_gld()._ins(1)._span(1)._span_r(2)._center_top();
			MyE2_Grid_InLIST gridFreigabeLinks = new MyE2_Grid_InLIST(7, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			gridFreigabeLinks.add(new MyE2_Label(new MyE2_String("Menge:")), oGL_LeftCenterW);
			gridFreigabeLinks.add(oMap.get_Comp(RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_LADEMENGE), oGL_LeftCenterW);
			gridFreigabeLinks.add(new MyE2_Label(new MyE2_String("von")),oGL_LeftCenter);
			gridFreigabeLinks.add(oMap.get_Comp(RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_LADEMENGE_VON), oGL_LeftCenterW);
			gridFreigabeLinks.add(new MyE2_Label(new MyE2_String("am")), oGL_LeftCenter);
			gridFreigabeLinks.add(oMap.get_Comp(RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_LADEMENGE_AM), oGL_LeftCenterW);
			gridFreigabeLinks.add(oMap.get_Comp(RECORD_VPOS_TPA_FUHRE.FIELD__LADEMENGE_GUTSCHRIFT), oGL_LeftCenterB);
			//z2
			gridFreigabeLinks.add(new MyE2_Label(new MyE2_String("Preis:")), oGL_LeftCenterW);
			gridFreigabeLinks.add(oMap.get_Comp(RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_EK_PREIS), oGL_LeftCenterW);
			gridFreigabeLinks.add(new MyE2_Label(new MyE2_String("von")),oGL_LeftCenter);
			gridFreigabeLinks.add(oMap.get_Comp(RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_EK_PREIS_VON), oGL_LeftCenterW);
			gridFreigabeLinks.add(new MyE2_Label(new MyE2_String("am")), oGL_LeftCenter);
			gridFreigabeLinks.add(oMap.get_Comp(RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_EK_PREIS_AM), oGL_LeftCenterW);
			gridFreigabeLinks.add(oMap.get_Comp(FU___CONST.ACTIVLABEL_LADEMENGE_IST_BUCHUNGSMENGE), ld);
			//gridFreigabeLinks.add(new MyE2_Label(""), oGL_LeftCenterW);
			//z3
			gridFreigabeLinks.add(new MyE2_Label(new MyE2_String("Fuhre:")), oGL_LeftCenterW);
			gridFreigabeLinks.add(oMap.get_Comp(RECORD_VPOS_TPA_FUHRE.FIELD__SCHLIESSE_FUHRE), oGL_LeftCenterW);
			gridFreigabeLinks.add(new MyE2_Label(new MyE2_String("von")),oGL_LeftCenter);
			gridFreigabeLinks.add(oMap.get_Comp(RECORD_VPOS_TPA_FUHRE.FIELD__SCHLIESSE_FUHRE_VON), oGL_LeftCenterW);
			gridFreigabeLinks.add(new MyE2_Label(new MyE2_String("am")), oGL_LeftCenter);
			gridFreigabeLinks.add(oMap.get_Comp(RECORD_VPOS_TPA_FUHRE.FIELD__SCHLIESSE_FUHRE_AM), oGL_LeftCenterW);
			//gridFreigabeLinks.add(new MyE2_Label(new MyE2_String("=Buch.Mge")), oGL_LeftCenterB);
 			

			
			
			
			MyE2_Grid_InLIST gridFreigabeRechts = new MyE2_Grid_InLIST(7, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			gridFreigabeRechts.add(new MyE2_Label(new MyE2_String("Menge:")), oGL_LeftCenterW);
			gridFreigabeRechts.add(oMap.get_Comp(RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_ABLADEMENGE), oGL_LeftCenterW);
			gridFreigabeRechts.add(new MyE2_Label(new MyE2_String("von")), oGL_LeftCenter);
			gridFreigabeRechts.add(oMap.get_Comp(RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_ABLADEMENGE_VON),oGL_LeftCenterW);
			gridFreigabeRechts.add(new MyE2_Label(new MyE2_String("am")), oGL_LeftCenter);
			gridFreigabeRechts.add(oMap.get_Comp(RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_ABLADEMENGE_AM), oGL_LeftCenterW);
			gridFreigabeRechts.add(oMap.get_Comp(RECORD_VPOS_TPA_FUHRE.FIELD__ABLADEMENGE_RECHNUNG), oGL_LeftCenterB);
			
			//z2
			gridFreigabeRechts.add(new MyE2_Label(new MyE2_String("Preis:")), oGL_LeftCenterW);
			gridFreigabeRechts.add(oMap.get_Comp(RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_VK_PREIS), oGL_LeftCenterW);
			gridFreigabeRechts.add(new MyE2_Label(new MyE2_String("von")),oGL_LeftCenter);
			gridFreigabeRechts.add(oMap.get_Comp(RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_VK_PREIS_VON), oGL_LeftCenterW);
			gridFreigabeRechts.add(new MyE2_Label(new MyE2_String("am")), oGL_LeftCenter);
			gridFreigabeRechts.add(oMap.get_Comp(RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_VK_PREIS_AM), oGL_LeftCenterW);
			gridFreigabeRechts.add(oMap.get_Comp(FU___CONST.ACTIVLABEL_ABLADEMENGE_IST_BUCHUNGSMENGE), ld);

			//z3
			gridFreigabeRechts.add(new MyE2_Label(new MyE2_String(" ")), oGL_LeftCenterW);
			gridFreigabeRechts.add(new MyE2_Label(new MyE2_String(" ")), oGL_LeftCenterW);
			gridFreigabeRechts.add(new MyE2_Label(new MyE2_String(" ")), oGL_LeftCenterW);
			gridFreigabeRechts.add(new MyE2_Label(new MyE2_String(" ")), oGL_LeftCenterW);
			gridFreigabeRechts.add(new MyE2_Label(new MyE2_String(" ")), oGL_LeftCenterW);
			gridFreigabeRechts.add(new MyE2_Label(new MyE2_String(" ")), oGL_LeftCenterW);
			//gridFreigabeRechts.add(new MyE2_Label(new MyE2_String("=Buch.Mge")), oGL_LeftCenterB);

			
			
			oFiller.add_Line(this,new MyE2_String("Abschluss"),1,gridFreigabeLinks.get_InBorderGrid_inList(oBorder,extRahmenbreite,null),2,
											FU___CONST.HASH_KEY_BUTTON_ZAUBERSTAB_BEIDE_SEITEN_MENGE_FREI+"|"+FU___CONST.HASH_KEY_BUTTON_ZAUBERSTAB_BEIDE_SEITEN_MENGE_FREI_UND_SAVE,1,
											new MyE2_String("Abschluss"),1,gridFreigabeRechts.get_InBorderGrid_inList(oBorder,extRahmenbreite,null),2);

			
			
		}
		
		if (iPageNumber == 1)
		{
			
			oFiller.add_Line(this,new MyE2_String("Lieferant"),1,"L_NAME1",2,new MyE2_String(" "),1,new MyE2_String("Abnehmer"),1,"A_NAME1",2);
			oFiller.add_Line(this,new MyE2_String("Abholadresse"),1,"L_NAME2",2,new MyE2_String(" "),1,new MyE2_String("Lieferadresse"),1,"A_NAME2",2);
			oFiller.add_Line(this,new MyE2_String(" "),1,"L_NAME3",2,new MyE2_String(" "),1,new MyE2_String(" "),1,"A_NAME3",2);
			oFiller.add_Line(this,new MyE2_String("Strasse"),1,"L_STRASSE|L_HAUSNUMMER",2,new MyE2_String(" "),1,new MyE2_String("Strasse"),1,"A_STRASSE|A_HAUSNUMMER",2);
			oFiller.add_Line(this,new MyE2_String("Land-Plz-Ort"),1,"L_LAENDERCODE|L_PLZ|L_ORT",2,new MyE2_String(" "),1,new MyE2_String("Land-Plz-Ort"),1,"A_LAENDERCODE|A_PLZ|A_ORT",2);
			oFiller.add_Line(this,new MyE2_String("Ortzusatz"),1,"L_ORTZUSATZ",2,new MyE2_String(" "),1,new MyE2_String("Ortzusatz"),1,"A_ORTZUSATZ",2);
			oFiller.add_Line(this,new MyE2_String("Tel"),1,"TEL_LIEFERANT",2,new MyE2_String(" "),1,new MyE2_String("Tel"),1,"TEL_ABNEHMER",2);
			oFiller.add_Line(this,new MyE2_String("Fax"),1,"FAX_LIEFERANT",2,new MyE2_String(" "),1,new MyE2_String("Fax"),1,"FAX_ABNEHMER",2);

			oFiller.add_Trenner(this, oINTrenner);
		
			oFiller.add_Line(this,new MyE2_String("EK-Artikel"),			4,new MyE2_String("VK-Artikel"),	1);
			oFiller.add_Line(this,new MyE2_String("ID Art.Bez."),			1,"ID_ARTIKEL_BEZ_EK|#(|ANR1_EK|#/|ANR2_EK|#)",	2,"#   ",1,new MyE2_String("ID Art.Bez"),	1,"ID_ARTIKEL_BEZ_VK|#(|ANR1_VK|#/|ANR2_VK|#)",2);
			oFiller.add_Line(this,new MyE2_String("Art.Bez.1"),				1,"ARTBEZ1_EK",									2,"#   ",1,new MyE2_String("Art.Bez.1"),	1,"ARTBEZ1_VK",2);
			oFiller.add_Line(this,new MyE2_String("Art.Bez.2"),				1,"ARTBEZ2_EK",									2,"#   ",1,new MyE2_String("Art.Bez.2"),	1,"ARTBEZ2_VK",2);

			//2016-04-21: martin: felder fuer mrs
			oFiller.add_Trenner(this, E2_INSETS.I_0_5_10_5);
			//2020-08-19: verarbeitungsmerkmal dazu
			oFiller.add_Line(this,new MyE2_String("Verpackungsart"),1,		VPOS_TPA_FUHRE.id_verpackungsart.fn(),2,new MyE2_String(" "),1,new MyE2_String("Verarbeitung"),1,		VPOS_TPA_FUHRE.id_verarbeitung.fn(),2);
			
			
			oFiller.add_Trenner(this, E2_INSETS.I_0_5_10_5);
			oFiller.add_Line(this,new MyE2_String("Bem.für Sachbearb."),1,	"BEMERKUNG_SACHBEARBEITER",6);

			//2016-04-21: martin: felder fuer mrs
			oFiller.add_Trenner(this, E2_INSETS.I_0_5_10_5);
			oFiller.add_Line(this,new MyE2_String("Bem.für Kd.Belege"),1,	VPOS_TPA_FUHRE.bemerkung_fuer_kunde.fn(),6);
			
		}
		
		if (iPageNumber == 2)
		{
			oFiller.add_Line(this,new MyE2_String("Mengen (Fuhre)"),1,"|#Planmenge|MENGE_VORGABE_KO|#  |#Lademenge|MENGE_AUFLADEN_KO|#  |#Ablademenge|MENGE_ABLADEN_KO|",	6);
	
			oFiller.add_Trenner(this, oINTrenner);
			if (oMap.containsKey("ID_ADRESSE_SPEDITION"))
			{
				oFiller.add_Line(this,new MyE2_String("Spedition"),1,"ID_ADRESSE_SPEDITION|",6);
				oFiller.add_Trenner(this, oINTrenner);
			}
			//2011-12-30: fremdauftrag immer an der gleichen stelle im seite 1 fuhre
			//2011-12-30: ID-UMA-Kontrakt hier ans maskenfeld einblenden, wird vom button auf seite 1 definiert
			oFiller.add_Line(this,new MyE2_String("UMA-Kontrakt"),1,	"ID_UMA_KONTRAKT|#  |",6);
			oFiller.add_Trenner(this, oINTrenner);
			
			
			oFiller.add_Line(this,new MyE2_String("AVV-Code"),1,	"ID_EAK_CODE|#  |#Nationaler Abfall-Code|NATIONALER_ABFALL_CODE",6);
			oFiller.add_Trenner(this, oINTrenner);
			oFiller.add_Line(this,new MyE2_String("Basel-Code"),1,	"BASEL_CODE",6);
			oFiller.add_Line(this,new MyE2_String("Basel-Notiz"),1,	"BASEL_NOTIZ",6);
			oFiller.add_Trenner(this, oINTrenner);
			oFiller.add_Line(this,new MyE2_String("OECD-Code"),1,	"EUCODE",6);
			oFiller.add_Line(this,new MyE2_String("OECD-Notiz"),1,	"EUNOTIZ",6);
			
			// aenderung AVV
			
			oFiller.add_Trenner(this, E2_INSETS.I_0_5_10_5);
			oFiller.add_Line(this,new MyE2_String("CMR-Ord.Nr."),1,	"ORDNUNGSNUMMER_CMR",6);
			
			
			//2013-09-28: neues feld fuer die handelsdefinition
			oFiller.add_Trenner(this, E2_INSETS.I_0_5_10_5);
			//20181127: neuer editbutton fuer handelsdefinition
			//alt: oFiller.add_Line(this,new MyE2_String("USt./Steuertext-Definition"),1,	_DB.VPOS_TPA_FUHRE$ID_HANDELSDEF+"|"+FU___CONST.HASH_MASK_ERASE_ID_HANDELSDEF,6);
		
			//20181127: neuer editbutton fuer handelsdefinition
			//auf dieser seite weg, auf den ersten reiter: oFiller.add_Line(this,new MyE2_String("USt./Steuertext-Definition"),1,	_DB.VPOS_TPA_FUHRE$ID_HANDELSDEF+"|"+FU___CONST.HASH_MASK_ERASE_ID_HANDELSDEF+"|"+FU___CONST.HASH_MASK_EDIT_ID_HANDELSDEF,6);
			
			
//			oFiller.add_Trenner(this, E2_INSETS.I_0_5_10_5);
//			oFiller.add_Line(this,new MyE2_String("Art des Geschäfts"),1,	RECORD_VPOS_TPA_FUHRE.FIELD__ART_GESCHAEFT,6);
//			
			//2014-02-20: Gelangensbestaetigung
			oFiller.add_Trenner(this, E2_INSETS.I_0_5_10_5);
			oFiller.add_Line(this,new MyE2_String("Gelangensbestätigung"),1,	_DB.VPOS_TPA_FUHRE$GELANGENSBESTAETIGUNG_ERHALTEN,6);
			
			//20180305: routinginfos in subgrid
			oFiller.add_Trenner(this, E2_INSETS.I_0_5_10_5);
			
			E2_Grid g = new E2_Grid()._setSize(100,100,100);
			
			RB_gld ldTitle = new RB_gld()._col_back_dd()._center_top()._ins(2, 2, 2, 2);
			RB_gld ldCont = new RB_gld()._center_top()._ins(2, 2, 2, 2);
			
			g	._a(new RB_lab(S.ms("Entfernung / km"))._lw(), 		ldTitle)
				._a(new RB_lab(S.ms("Zeitprognose (hh:mm)"))._lw(),	ldTitle)
				._a(S.ms("Suchen"),									ldTitle);
			if (ENUM_MANDANT_DECISION.USE_GEOPOINT_SHOW_ROUTE.is_YES()) {
				g._setSize(100,100,100,100);
				g._a(new RB_lab(S.ms("Karten- darstellung"))._lw(),	ldTitle);
			}
			
			g	._a(oMap.get__CompEcho(VPOS_TPA_FUHRE.routing_distance_km),		ldCont)
				._a(oMap.get__CompEcho(VPOS_TPA_FUHRE.routing_time_minutes),	ldCont)
				._a(oMap.get__CompEcho(FU___CONST.HASH_KEY_BT_ROUTING),			ldCont);
			if(ENUM_MANDANT_DECISION.USE_GEOPOINT_SHOW_ROUTE.is_YES()) {
				g._a(oMap.get__CompEcho(FU___CONST.HASH_KEY_BT_ROUTING_SHOW),	ldCont);
			}

			oFiller.add_Line(this, S.ms("Routing"),1, g,6);
			//20180305: routinginfos in subgrid
			//20180308: routing schauen
		}

		if (iPageNumber == 3)
		{
			MyE2_Label oLab1 = new MyE2_Label(new MyE2_String("Kosten"));
			//oLab1.setLayoutData(MyE2_Grid.GRID_LAYOUTDATA(new Insets(5,20,5,5), 1, new Alignment(Alignment.LEFT,Alignment.TOP)));
			oLab1.setLayoutData(LayoutDataFactory.get_GridLayoutGridLeftTOP(new Insets(5,8,5,5)));
			
			this.add(oLab1);
			this.add(C(FU___CONST.HASH_KEY_MASKE_TOCHTER_KOSTEN),6,E2_INSETS.I_5_5_5_5);

			oFiller.add_Trenner(this, oINTrenner);

			MyE2_Label oLab2 = new MyE2_Label(new MyE2_String("Drucke"));
			oLab2.setLayoutData(LayoutDataFactory.get_GridLayoutGridLeftTOP(new Insets(5,8,5,5)));
			
			this.add(oLab2);
			this.add(C("TOCHTER_DRUCKPROTOKOLL"),6,E2_INSETS.I_5_5_5_5);

			oFiller.add_Trenner(this, oINTrenner);

			
			MyE2_Label oLab3 = new MyE2_Label(new MyE2_String("Sonderpositionen"));
			oLab3.setLayoutData(LayoutDataFactory.get_GridLayoutGridLeftTOP(new Insets(5,8,5,5)));
			
			this.add(oLab3);
			this.add(C(FU___CONST.HASH_KEY_DAUGHTER_POS_VORLAGEN),6,E2_INSETS.I_5_5_5_5);

		
		}
	

		if (iPageNumber == 4)
		{
//			oFiller.add_Line(this,"#Abzüge Ladeort (NUR MENGENABZÜGE ausser Inhaltsanteile)|#  |ABZUG_LADEMENGE_LIEF|",7);
//			this.add(new Separator(),7,E2_INSETS.I_1_1_1_1);
			oFiller.add_Line(this,FU___CONST.HASH_KEY_MASKE_TOCHTER_ABZUEGE_EK,7);
		}
		
		if (iPageNumber == 5)
		{
			
//			oFiller.add_Line(this,"#Abzüge Abladeort (NUR MENGENABZÜGE ausser Inhaltsanteile)|#  |ABZUG_ABLADEMENGE_ABN|",7);
//			this.add(new Separator(),7,E2_INSETS.I_1_1_1_1);
			oFiller.add_Line(this,FU___CONST.HASH_KEY_MASKE_TOCHTER_ABZUEGE_VK,7);
		}
		
		
		
		if (iPageNumber == 6)
		{
			
			oFiller.add_Line(this,new MyE2_String("Zusatz-Abholorte"),1,FU___CONST.HASH_KEY_MASKE_TOCHTER_QUELLORTE,6);
			oFiller.add_Trenner(this, oINTrenner);
			oFiller.add_Line(this,new MyE2_String("Zusatz-Zielorte"),1,FU___CONST.HASH_KEY_MASKE_TOCHTER_ZIELORTE,6);

		}
		
		
		//fahrplanangaben
		if (iPageNumber == 7)
		{
			//this._bo_dd();
			this._setSize(130,400,100,320);
			//neuer maskenaufbau
			oFiller.add_Line(this, RB_lab.tr("Anrufer")			,1,"ANRUFER_FP",		1,RB_lab.tr("Datum Anruf"),			1,"ANRUFDATUM_FP|# |#Erfasser|ERFASSER_FP",			1);
			oFiller.add_Trenner(this, E2_INSETS.I_2_2_2_2);
			oFiller.add_Line(this, RB_lab.tr("Fahrplan-Fuhre")	,1,"FUHRE_AUS_FAHRPLAN|# |#Archivsatz|KENNER_ALTE_SAETZE_FP|# |#Fuhre bearbeitet |FUHRE_KOMPLETT",1, RB_lab.tr("Sortierung")		,1,"SORTIERUNG_FP|# |#Gruppe|FAHRPLANGRUPPE_FP",		1);
			oFiller.add_Line(this, RB_lab.tr("Wurde geplant ")	,1,"IST_GEPLANT_FP",	1,RB_lab.tr("EAN-Code"),	1,"EAN_CODE_FP",		1);			
			oFiller.add_Trenner(this, E2_INSETS.I_2_2_2_2);
			oFiller.add_Line(this, RB_lab.tr("Fahrplaninfo Abholadresse")	,1,"BEMERKUNG_START_FP",	1,RB_lab.tr("Fahrplaninfo Zieladresse"),	1,"BEMERKUNG_ZIEL_FP",		1);
			oFiller.add_Trenner(this, E2_INSETS.I_2_2_2_2);
			oFiller.add_Line(this, RB_lab.tr("Fahrpl. Vormerk.")	,1,"DAT_VORGEMERKT_FP|#bis|DAT_VORGEMERKT_ENDE_FP",	1);
			oFiller.add_Line(this, RB_lab.tr("Fahrer")	,1,"FAHRER_FP",	3);

			oFiller.add_Trenner(this, E2_INSETS.I_2_2_2_2);
			
			oFiller.add_Line(this, RB_lab.tr("Durchführung am")	,1,"DAT_FAHRPLAN_FP",1);
			oFiller.add_Line(this, RB_lab.tr("LKW")	,1,"ID_MASCHINEN_LKW_FP",	1,RB_lab.tr("Anhänger"),1,	"ID_MASCHINEN_ANH_FP",1);
			oFiller.add_Line(this, RB_lab.tr("Tätigkeit")	,1,"TAETIGKEIT_FP",3);			
			oFiller.add_Line(this, RB_lab.tr("Container")	,1,"ANZAHL_CONTAINER_FP|ID_CONTAINERTYP_FP",	1);
			oFiller.add_Trenner(this, E2_INSETS.I_2_2_2_2);
			oFiller.add_Line(this, RB_lab.tr("Zeiten")	,1,"FAHRT_ANFANG_FP|#bis|FAHRT_ENDE_FP",	1,  RB_lab.tr("Ergänzung"),1,VPOS_TPA_FUHRE.id_fahrplan_zeitangabe.fn(),1);
			oFiller.add_Line(this, RB_lab.t("")			,1,RB_lab.tr(""),	1,  RB_lab.tr(""),1,VPOS_TPA_FUHRE.zeitangabe.fn(),1);
			oFiller.add_Trenner(this, E2_INSETS.I_2_2_2_2);
			oFiller.add_Line(this, RB_lab.tr("Bemerkung")	,1,"BEMERKUNG",	3);

			
			//alter maskenaufbau
			//oFiller.add_Line(this, new MyE2_String("Anrufer "), 1, "ANRUFER_FP|#  |#am |#  |ANRUFDATUM_FP|# |#Erfasst von |ERFASSER_FP",6);
			//oFiller.add_Trenner(this, E2_INSETS.I_2_2_2_2);
			//oFiller.add_Line(this, new MyE2_String("Fahrplan-Fuhre "), 1, "FUHRE_AUS_FAHRPLAN|#  |#Archivdatensatz|KENNER_ALTE_SAETZE_FP|#  |#Fuhre bearbeitet|FUHRE_KOMPLETT|#  |#Sortierung|SORTIERUNG_FP|#  |#Gruppe|FAHRPLANGRUPPE_FP",6);
			//oFiller.add_Line(this, new MyE2_String("Wurde geplant  "), 1, "IST_GEPLANT_FP|#  |#EAN-Code: |EAN_CODE_FP|",6);
			//oFiller.add_Trenner(this, E2_INSETS.I_2_2_2_2);
			
			//oFiller.add_Line(this,new MyE2_String(""),		  1,new MyE2_String("Abholadresse"),2,new MyE2_String(" "),1,new MyE2_String(""),1,new MyE2_String("Zieladresse"),2);
			//oFiller.add_Line(this,new MyE2_String("Fahrplaninfo"),1,"BEMERKUNG_START_FP",2,new MyE2_String(" "),1,new MyE2_String(""),1,"BEMERKUNG_ZIEL_FP",2);
			//oFiller.add_Trenner(this, E2_INSETS.I_2_2_2_2);

			// oFiller.add_Line(this, new MyE2_String("Fahrplan-Vormerkung "), 1, "DAT_VORGEMERKT_FP|#  |#bis|#  |DAT_VORGEMERKT_ENDE_FP",6);
			//oFiller.add_Line(this, new MyE2_String("Fahrer "), 1, "FAHRER_FP",6);
			//oFiller.add_Line(this, new MyE2_String("Durchführung am "), 1, 		"DAT_FAHRPLAN_FP|#  |#mit |#  |ID_MASCHINEN_LKW_FP|#  |#Anhänger|#  |ID_MASCHINEN_ANH_FP",6);
			//oFiller.add_Line(this, new MyE2_String("Tätigkeit "), 1, "TAETIGKEIT_FP",6);
			//oFiller.add_Line(this, new MyE2_String("Container "), 1, "#Anz.|#  |ANZAHL_CONTAINER_FP|# |ID_CONTAINERTYP_FP|# ",6);
			//oFiller.add_Line(this, new MyE2_String("Zeiten "), 1, "FAHRT_ANFANG_FP|#  |#bis |#  |FAHRT_ENDE_FP",6);
			//oFiller.add_Line(this,new MyE2_String("Bemerkung"),1,	"BEMERKUNG",6);
			
			

		}
	
		
		
		
		//this.ChangeDBComponents();
		
	}
	
	
	
	private Component  C(String cHashName)			
	{
		Component ocomp = null;
		try
		{
			ocomp =	this.oComponentMAP_Mask.get_Comp(cHashName);	
		}
		catch (myException ex)
		{
			ocomp = new MyE2_Label(new MyE2_String("ERROR:"+cHashName,false));
		}
		
		return ocomp;
	}

	//private MyE2_Label L(String cText) 									{		return new MyE2_Label(new MyE2_String(cText));	}

	
}
