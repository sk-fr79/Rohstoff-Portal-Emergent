package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.IF_ADDING_Allowed;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.ListAndMask.Mask.IF_BaseComponent4Mask;
import panter.gmbh.Echo2.components.E2_Subgrid_4_Mask;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU___CONST;

/*
* maskenvariante mit TabbedPane 
* ... MUSS Umbenannt werden in FUO_MASK
*/
public class FUO_MASK__old extends MyE2_Column  implements IF_BaseComponent4Mask	
{
	private FU_DAUGHTER_ORTE  				oFUO_DaughterComponent = null;

	private Vector<IF_ADDING_Allowed>  vMaskGrids = new Vector<IF_ADDING_Allowed>();
	
	public FUO_MASK__old(FUO_MASK_ComponentMAP oMap, String cEK_VK, FU_DAUGHTER_ORTE FUO_DaugherComponent) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
	

		this.oFUO_DaughterComponent = FUO_DaugherComponent;
	
		
		boolean bEK = (cEK_VK.equals("EK"));
		
		MyE2_TabbedPane oTabbedPaneMaske = new MyE2_TabbedPane(new MyE2_TabbedPane.MyE2_TabModel(new E2_FontPlain()),null);
		oTabbedPaneMaske.set_bAutoHeight(true);

		
		
		MyE2_Grid oGrid0 = new MyE2_Grid(7,0);
		MyE2_Grid oGrid0a = new MyE2_Grid(7,0);
		MyE2_Grid oGrid1 = new MyE2_Grid(7,0);
		MyE2_Grid oGrid2 = new MyE2_Grid(7,0);
		MyE2_Grid oGrid3 = new MyE2_Grid(7,0);
		
		oTabbedPaneMaske.add_Tabb(new MyE2_String("Fuhrenort"), 		oGrid0);
		oTabbedPaneMaske.add_Tabb(new MyE2_String("Adresse/Artikel"), 	oGrid0a);
		oTabbedPaneMaske.add_Tabb(new MyE2_String("Diverse"), 			oGrid1);
		oTabbedPaneMaske.add_Tabb(new MyE2_String("Abzüge"), 			oGrid2);
		oTabbedPaneMaske.add_Tabb(new MyE2_String("Sonderregeln"),		oGrid3);
		
		this.add(oTabbedPaneMaske, E2_INSETS.I_2_2_2_2);

		Insets oIN = new Insets(0,2,5,0);
		Insets oINL = new Insets(0,2,10,0);    // linke spalte mit beschriftung wird weiter abgesetzt
		Insets oINL2 = new Insets(0,2,10,5);    // linke spalte mit beschriftung wird weiter abgesetzt
	
		E2_MaskFiller oFiller = new E2_MaskFiller(oMap,null,null,oINL,oIN, new Alignment(Alignment.LEFT,Alignment.CENTER)); 
		

		oFiller.add_Line(oGrid0,new MyE2_String("ID"),1,"ID_VPOS_TPA_FUHRE_ORT|#  |#Zusatz in Fuhrenbuchungsnummer: |BUCHUNGSNUMMER_ZUSATZ|# |"+FUO__CONST.HASHKEY_MASK_SHOW_DELETED+"|# |ZEITSTEMPEL",6);

		oFiller.add_Trenner(oGrid0, E2_INSETS.I_0_5_0_5);
		oFiller.add_Line(oGrid0,new MyE2_Label(new MyE2_String(bEK?"Ladeort/EK-Kontrakt":"Abladeort/VK-Kontrakt"), MyE2_Label.STYLE_TITEL_BIG()),5,"#Wiegekarte:|WIEGEKARTENKENNER|ID_WIEGEKARTE|#",2);
								
		oFiller.add_Line(oGrid0,new MyE2_String(" "),1,"ID_VPOS_KON",6);
		oFiller.add_Line(oGrid0,new MyE2_String("Firma"),1,"ID_ADRESSE",6);
		oFiller.add_Line(oGrid0,new MyE2_String("Lager"),1,"ID_ADRESSE_LAGER",6);
		oFiller.add_Line(oGrid0,new MyE2_String("Öffnungszeiten"),1,"OEFFNUNGSZEITEN",6);
		oFiller.add_Line(oGrid0,new MyE2_String("Bestell-Nummer"),	1,"BESTELLNUMMER",	6);
		oFiller.add_Line(oGrid0,new MyE2_String("Posten-Nummer"),	1,"POSTENNUMMER",	6);
		
		oFiller.add_Trenner(oGrid0, E2_INSETS.I_0_5_0_5);

		oFiller.add_Line_WithComponents(oGrid0, new MyE2_Label(new MyE2_String("Mengen (Teile)")), 
				new E2_Subgrid_4_Mask("Planmenge"+(bEK?"":" !!! ")+"|Lademenge|Ablademenge|Mengeneinheit",
						"ANTEIL_PLANMENGE|ANTEIL_LADEMENGE|ANTEIL_ABLADEMENGE|EINHEIT_MENGEN",oMap, null, null), 6, E2_INSETS.I_0_0_0_0,null,0,null,null,0,null); 

		//pruefblock fuer die mengepruefung
		oFiller.add_Line(oGrid0,new MyE2_String("Mengenfreigabe"),1,FUO__CONST.HASHKEY_PRUEFBLOCK_MENGE+"|#"+
																	(bEK?"Buchg:Lademenge":"Buchg:Ablademenge")+"|"+
																	(bEK?"LADEMENGE_GUTSCHRIFT":"ABLADEMENGE_RECHNUNG")+"|#  |"+
																	"#"+(bEK?"Ladedatum":"Abladedatum")+"|DATUM_LADE_ABLADE"
																	,6);
		
		oFiller.add_Trenner(oGrid0, E2_INSETS.I_0_5_0_5);

		oFiller.add_Line(oGrid0,new MyE2_String("Transitländer"),1,	"LAENDERCODE_TRANSIT1|LAENDERCODE_TRANSIT2|LAENDERCODE_TRANSIT3|# ",		3,"|#Drucke EU-Blatt|PRINT_EU_AMTSBLATT|",3);
		oFiller.add_Line(oGrid0,new MyE2_String("Nationaler Code"),1,	"NATIONALER_ABFALL_CODE|# ",											3,"|#EU-Blatt abweich. Menge|EU_BLATT_MENGE|#Volumen|EU_BLATT_VOLUMEN",3);
		oFiller.add_Line(oGrid0,new MyE2_String("Ausstelldatum AVV"),1,"AVV_AUSSTELLUNG_DATUM|# ",												3,"|#Notifi./Begleitformular-Nr:|NOTIFIKATION_NR",3);
		oFiller.add_Trenner(oGrid0, E2_INSETS.I_0_5_0_5);
		
		oGrid0.add(new MyE2_Label(new MyE2_String("Informationen zur Erzeugen von freien Positionen"),MyE2_Label.STYLE_NORMAL_BOLD()),7, oINL2);;
		
		oFiller.add_Line(oGrid0,new MyE2_String("Preis manuell"),	1,	"MANUELL_PREIS|#  |#  |#  |#  |#  |#  |#  |"+FUO__CONST.HASH_KEY_BUTTON_MASK_LADE_PREIS_UND_STEUER,6);
		oFiller.add_Line(oGrid0,new MyE2_String("Abnahme-Ang."),	1,	"ID_VPOS_STD",6);
		oFiller.add_Line(oGrid0,new MyE2_String(bEK?"Einkaufspreis":"Verkaufspreis"),			1,	"EINZELPREIS|#  |#MWSt.|STEUERSATZ",6);
		oFiller.add_Line(oGrid0,new MyE2_String("Steuer-Vermerk"),	1,	"EU_STEUER_VERMERK|"+FUO__CONST.HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT,6);

		
		
		oFiller.add_Line(oGrid0a,new MyE2_String("Name "),1,"NAME1",6);
		oFiller.add_Line(oGrid0a,new MyE2_String(" "),1,"NAME2",6);
		oFiller.add_Line(oGrid0a,new MyE2_String(" "),1,"NAME3",6);
		oFiller.add_Line(oGrid0a,new MyE2_String("Strasse"),1,"STRASSE|HAUSNUMMER",6);
		oFiller.add_Line(oGrid0a,new MyE2_String("Land-Plz-Ort"),1,"LAENDERCODE|PLZ|ORT",6);
		oFiller.add_Line(oGrid0a,new MyE2_String("Ortzusatz"),1,"ORTZUSATZ",6);
		oFiller.add_Line(oGrid0a,new MyE2_String("Tel"),1,"TEL",6);
		oFiller.add_Line(oGrid0a,new MyE2_String("Fax"),1,"FAX",6);
		oFiller.add_Trenner(oGrid0a, E2_INSETS.I_0_5_0_5);
		oFiller.add_Line(oGrid0a,new MyE2_String("ID Art.Bez."),	1,"ID_ARTIKEL_BEZ",	6);
		oFiller.add_Line(oGrid0a,new MyE2_String(" "),				1,"#(|ANR1|#/|ANR2|#)|# ID-Artikel:|ID_ARTIKEL",	6);
		oFiller.add_Trenner(oGrid0a, E2_INSETS.I_0_5_0_5);
		oFiller.add_Line(oGrid0a,new MyE2_String("Art.Bez.1"),		1,"ARTBEZ1|#  |"+FU___CONST.HASH_KEY_GEFAHRGUT_ANZEIGE,	6);
		oFiller.add_Line(oGrid0a,new MyE2_String("Art.Bez.2"),		1,"ARTBEZ2",	6);

		// -------------------- seite 2
		
		


		
		oFiller.add_Trenner(oGrid1, E2_INSETS.I_0_5_0_5);
		oFiller.add_Line(oGrid1,new MyE2_String("AVV-Code"),1,			"ID_EAK_CODE|#  |"+FUO__CONST.HASHKEY_MASK_LADE_AVV_VON_FUHRE,6);
		oFiller.add_Trenner(oGrid1, E2_INSETS.I_0_5_0_5);
		oFiller.add_Line(oGrid1,new MyE2_String("EU-Code/Notiz"),1,		"EUCODE",6);
		oFiller.add_Line(oGrid1,new MyE2_String("EU-Code/Notiz"),1,		"EUNOTIZ",6);
		oFiller.add_Trenner(oGrid1, E2_INSETS.I_0_5_0_5);
		oFiller.add_Line(oGrid1,new MyE2_String("Basel-Code/Notiz"),1,	"BASEL_CODE",6);
		oFiller.add_Line(oGrid1,new MyE2_String("Basel-Code/Notiz"),1,	"BASEL_NOTIZ",6);
		oFiller.add_Trenner(oGrid1, E2_INSETS.I_0_5_0_5);
		oFiller.add_Line(oGrid1,new MyE2_String("Zolltarifnummer"),1,	"ZOLLTARIFNR",6);
		
		oFiller.add_Trenner(oGrid1, E2_INSETS.I_0_5_0_5);
		oFiller.add_Line(oGrid1,new MyE2_String("Info Zusatzort"),1,"BEMERKUNG",7);
		
		oFiller.add_Line(oGrid2,new MyE2_String("Abzüge"),2,"|#   |ABZUG_MENGE|",5);
		oFiller.add_Trenner(oGrid1, E2_INSETS.I_0_5_0_5);
		oFiller.add_Line(oGrid2,FUO__CONST.HASHKEY_MASK_ABZUG_FUHRE_ORT,7);

		
		//sonderregeln und rechnungsvorlagen
		
		oFiller.add_Line_WithComponents(oGrid3, new MyE2_Label(new MyE2_String("Sonderdef:")), 	
							new E2_Subgrid_4_Mask("Sonderdefinition|Fremdware|Kein VK-Kon|Ohne AVV-Vertr.Check",
												"ID_VPOS_TPA_FUHRE_SONDER|OHNE_ABRECHNUNG|KEIN_KONTRAKT_NOETIG|OHNE_AVV_VERTRAG_CHECK",oMap, null, null), 4, E2_INSETS.I_0_0_0_0,
												null,0, null,null,0,null);

		
		oFiller.add_Line(oGrid3,new MyE2_String("Sonderpositionen"),1,FUO__CONST.HASHKEY_DAUGHTER_POS_VORLAGEN,4);


		this.vMaskGrids.add(oGrid0);
		this.vMaskGrids.add(oGrid0a);
		this.vMaskGrids.add(oGrid1);
		this.vMaskGrids.add(oGrid2);
		this.vMaskGrids.add(oGrid3);
		
	}

	public FU_DAUGHTER_ORTE get_oFUO_DaughterComponent()
	{
		return oFUO_DaughterComponent;
	}

	@Override
	public Vector<IF_ADDING_Allowed> get_Basic_Mask_Container_Components() throws myException
	{
		return this.vMaskGrids;
	}


	
}
