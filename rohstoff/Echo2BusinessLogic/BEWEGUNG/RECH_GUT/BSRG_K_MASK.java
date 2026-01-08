package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.IF_ADDING_Allowed;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.ListAndMask.Mask.IF_BaseComponent4Mask;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__CompMap_FieldMAP_Gemeinsamkeiten;
import echopointng.Separator;


/*
 * enthaelt die definition der maske
 */
public class BSRG_K_MASK extends MyE2_Column  implements IF_BaseComponent4Mask	
{
	private BSRG_K_MASK__ComponentMAP oMaskMAP = null;

	private Vector<IF_ADDING_Allowed>  vMaskGrids = new Vector<IF_ADDING_Allowed>();
	
	public BSRG_K_MASK(BSRG_K_MASK__ComponentMAP maskMAP, MyE2_TabbedPane	oTabbed) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
		this.oMaskMAP = maskMAP;
		
		/*
		 * die maske besteht aus einer column, in dieser ist ein tabbed-pane,
		 * auf jedem tab ein grid
		 */
		
		E2_MaskFiller oFill = new E2_MaskFiller(this.oMaskMAP,null,null,E2_INSETS.I_0_2_10_2,E2_INSETS.I_0_2_10_2, new Alignment(Alignment.LEFT,Alignment.CENTER));

		oTabbed.set_bAutoHeight(true);

		
		
		/*
		 * grid seite 1
		 */
		MyE2_Grid oGrid1 = new MyE2_Grid(6,MyE2_Grid.STYLE_GRID_NO_BORDER());
		//MyE2_Grid oGrid1 = new MyE2_Grid(6,MyE2_Grid.STYLE_GRID_BORDER(Color.RED));
		oGrid1.setWidth(new Extent(100,Extent.PERCENT));
		
		oFill.add_Line(oGrid1,new MyE2_String("Vorgang-ID"),1,			"ID_VKOPF_RG|#  |#Storno Vorgänger|#|ID_VKOPF_RG_STORNO_VORGAENGER|#|#<L110L>Nachfolger:|<L10L>ID_VKOPF_RG_STORNO_NACHFOLGER",5);
		oFill.add_Line(oGrid1,new MyE2_String("Buchungs-Nr."),1,		"BUCHUNGSNUMMER|#  |#Abgeschlossen|<L20L>ABGESCHLOSSEN|# |#<L190L>Beleg wird nur intern gedruckt|BELEG_IST_INTERN",5);
		oFill.add_Line(oGrid1,new MyE2_String("Erstellungsdatum "),1,	"ERSTELLUNGSDATUM|#  |#Druckdatum|<L40L>DRUCKDATUM|#    |<L70L>"+BSRG__CONST.HASH_KEY_SELECT_USTID_MANDANT+"|UMSATZSTEUERLKZ_MANDANT|UMSATZSTEUERID_MANDANT|",5);
		
		oGrid1.add(new Separator(),6,new Insets(2,1,2,1));
		oFill.add_Line(oGrid1,new MyE2_String("Adressat"),1,			"ID_ADRESSE|#|"+BSRG__CONST.HASH_KEY_BUTTON_MASK_OPEN_EDITADRESS+"|#|"+
																		BSRG__CONST.HASH_KEY_BUTTON_MASK_OPEN_NEWADRESS+"|#    |"+BSRG__CONST.HASH_KEY_SELECT_USTID+"|UMSATZSTEUERLKZ|UMSATZSTEUERID|",5);
		oGrid1.add(new Separator(),6,new Insets(2,1,2,1));
		
		//2012-01-17: rechung ohne mahnung vorsehen
		//2012-01-18: nicht-mahnen-schalter
		MyE2_DB_CheckBox  cbNICHT_MAHNEN = (MyE2_DB_CheckBox)this.oMaskMAP.get("KEINE_MAHNUNGEN");

		oFill.add_Line(oGrid1,new MyE2_String("Währung"),1,				"ID_WAEHRUNG_FREMD|#    |#Währungssymbole (Druck Basis/Fremd)):|WAEHRUNG_BASIS|WAEHRUNG_FREMD|",4,
																			cbNICHT_MAHNEN.get_InBorderGrid(null, null, E2_INSETS.I_5_5_5_5),1);
		oFill.add_Line(oGrid1,new MyE2_String("Kunden-Nummer"),1,		"KDNR|",5);
		oFill.add_Line(oGrid1,new MyE2_String("Vorname"),1,				"VORNAME",5);
		oFill.add_Line(oGrid1,new MyE2_String("Name 1"),1,				"NAME1",5);
		oFill.add_Line(oGrid1,new MyE2_String("Name 2"),1,				"NAME2",5);
		oFill.add_Line(oGrid1,new MyE2_String("Name 3"),1,				"NAME3",5);
		oFill.add_Line(oGrid1,new MyE2_String("Strasse"),1,				"STRASSE|#|#Hausnummer|HAUSNUMMER<W80W>",5);
		oFill.add_Line(oGrid1,new MyE2_String("Land - PLZ - ORT"),1,	"LAENDERCODE|PLZ|ORT",5);
		oFill.add_Line(oGrid1,new MyE2_String("Ortzusatz"),1,			"ORTZUSATZ",5);
		oFill.add_Line(oGrid1,new MyE2_String("PLZ-Postfach"),1,		"PLZ_POB|POB|# |#Postfach aktiv|IS_POB_ACTIVE",5);


		
		/*
		 * grid seite 2
		 */
		/*
		 * grid seite 2
		 */
		MyE2_Grid oGrid2 = BS__CompMap_FieldMAP_Gemeinsamkeiten.fill_BS_KOPF_MASK_Bereich_Zusatzinfos(oFill);
		
		
		
		
		/*
		 * grid seite 3
		 */
		MyE2_Grid oGrid3 = new MyE2_Grid(6,MyE2_Grid.STYLE_GRID_NO_BORDER());
		oFill.add_Line(oGrid3,new MyE2_String("Formulartext (Anfang)"),1,	"<H9H>FORMULARTEXT_ANFANG",5);
		oFill.add_Line(oGrid3,new MyE2_String("Formulartext (Ende)"),1,		"<H9H>FORMULARTEXT_ENDE",5);
		oFill.add_Line(oGrid3,new MyE2_String("Bemerkungen (Intern)"),1,	"<H9H>BEMERKUNGEN_INTERN",5);
		oFill.add_Line(oGrid3,new MyE2_String("Auslandsvertretung"),1,		"<H7H>TEXT_AUSLANDSVERTRETUNG",5);

		/*
		 * grid seite 4
		 */
		MyE2_Grid oGrid4 = new MyE2_Grid(6,MyE2_Grid.STYLE_GRID_NO_BORDER_W100());
		oGrid4.setColumnWidth(0, new Extent(10,Extent.PERCENT));
		oGrid4.add(this.oMaskMAP.get_Comp(BSRG__CONST.HASH_KEY_DAUGHTERTABLE_POSITIONS),6,E2_INSETS.I_0_2_2_2);

		/*
		 * grid seite 5
		 */
		MyE2_Grid oGrid5 = new MyE2_Grid(6,MyE2_Grid.STYLE_GRID_NO_BORDER_W100());
		oGrid5.setColumnWidth(0, new Extent(10,Extent.PERCENT));
		oFill.add_Line(oGrid5,new MyE2_String("Entsperrungen"),1,	"ZAEHLER_ENTSPERRUNG|# |#Druckzähler: |DRUCKZAEHLER<W30W>",5);
		oGrid5.add(new MyE2_Label(new MyE2_String("Druckprotokoll")),1,1, new Insets(0,12,10,2), new Alignment(Alignment.LEFT,Alignment.TOP));
		oGrid5.add(this.oMaskMAP.get_Comp(BSRG__CONST.HASH_KEY_DAUGHTERTABLE_PRINTPROTOKOLL),5,E2_INSETS.I_0_2_10_2);
		
		
		/*
		 * grids anordnen
		 */
		oTabbed.add_Tabb(new MyE2_String("Adresse"),oGrid1);
		oTabbed.add_Tabb(new MyE2_String("Weiteres.."),oGrid2);
		oTabbed.add_Tabb(new MyE2_String("Texte/Bem."),oGrid3);
		oTabbed.add_Tabb(new MyE2_String("Positionen"),oGrid4);
		oTabbed.add_Tabb(new MyE2_String("Druckprotokoll"),oGrid5);

		
		this.add(oTabbed);
		
		
		this.vMaskGrids.add(oGrid1);
		this.vMaskGrids.add(oGrid2);
		this.vMaskGrids.add(oGrid3);
		this.vMaskGrids.add(oGrid4);
		this.vMaskGrids.add(oGrid5);
	}

	@Override
	public Vector<IF_ADDING_Allowed> get_Basic_Mask_Container_Components() throws myException
	{
		return this.vMaskGrids;
	}
	
	
	

}
