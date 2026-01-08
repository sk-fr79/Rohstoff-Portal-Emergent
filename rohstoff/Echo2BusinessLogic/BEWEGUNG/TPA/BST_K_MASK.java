package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
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
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__CompMap_FieldMAP_Gemeinsamkeiten;
import echopointng.Separator;


/*
 * enthaelt die definition der maske
 */
public class BST_K_MASK extends MyE2_Column  implements IF_BaseComponent4Mask	
{
	private BST_K_MASK__ComponentMAP oMaskMAP = null;

	private Vector<IF_ADDING_Allowed>  vMaskGrids = new Vector<IF_ADDING_Allowed>();
	
	public BST_K_MASK(BST_K_MASK__ComponentMAP maskMAP) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
		this.oMaskMAP = maskMAP;
		
		/*
		 * die maske besteht aus einer column, in dieser ist ein tabbed-pane,
		 * auf jedem tab ein grid
		 */
		MyE2_TabbedPane	oTabbed = new MyE2_TabbedPane(null);
		oTabbed.set_bAutoHeight(true);

		
		E2_MaskFiller oFill = new E2_MaskFiller(this.oMaskMAP,null,null,E2_INSETS.I_0_2_10_2,E2_INSETS.I_0_2_10_2, null);

		
		
		/*
		 * grid seite 1
		 */
		MyE2_Grid oGrid1 = new MyE2_Grid(6,MyE2_Grid.STYLE_GRID_NO_BORDER());
		
		oFill.add_Line(oGrid1,new MyE2_String("Vorgang-ID"),1,			"ID_VKOPF_TPA",2,new MyE2_String("Abgeschlossen"),1,"ABGESCHLOSSEN",1);
		oFill.add_Line(oGrid1,new MyE2_String("TPA-Nr."),1,				"BUCHUNGSNUMMER",3);    //ZEITSTEMPEL
		oFill.add_Line(oGrid1,new MyE2_String("Erstellungsdatum "),1,	"ERSTELLUNGSDATUM",2,new MyE2_String("Druckdatum"),1,"DRUCKDATUM",1);
		
		oGrid1.add(new Separator(),6,new Insets(2,1,2,1));
		oFill.add_Line(oGrid1,new MyE2_String("Spedition"),1,			"ID_ADRESSE|#  |"+BST__CONST.HASH_KEY_BUTTON_MASK_OPEN_EDITADRESS+"|# |"+BST__CONST.HASH_KEY_BUTTON_MASK_OPEN_NEWADRESS,5);

		oGrid1.add(new Separator(),6,new Insets(2,1,2,1));
		
		oFill.add_Line(oGrid1,new MyE2_String("Währung"),1,				"ID_WAEHRUNG_FREMD|#  |",5);
		oFill.add_Line(oGrid1,new MyE2_String("Kunden-Nummer"),1,		"KDNR",2);
		oFill.add_Line(oGrid1,new MyE2_String("Vorname"),1,				"VORNAME",2);
		oFill.add_Line(oGrid1,new MyE2_String("Name 1"),1,				"NAME1",2);
		oFill.add_Line(oGrid1,new MyE2_String("Name 2"),1,				"NAME2",2);
		oFill.add_Line(oGrid1,new MyE2_String("Name 3"),1,				"NAME3",2);
		oFill.add_Line(oGrid1,new MyE2_String("Strasse"),1,				"STRASSE",2,new MyE2_String("Hausnummer"),1,"HAUSNUMMER",1);
		oFill.add_Line(oGrid1,new MyE2_String("Land - PLZ - ORT"),1,	"LAENDERCODE|PLZ|ORT",5);
		oFill.add_Line(oGrid1,new MyE2_String("Ortzusatz"),1,			"ORTZUSATZ",5);
		oFill.add_Line(oGrid1,new MyE2_String("PLZ-Postfach"),1,		"PLZ_POB|POB",2,new MyE2_String("Postfach aktiv ?"),1,"IS_POB_ACTIVE",1);

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
		oFill.add_Line(oGrid3,new MyE2_String("Formulartext (Anfang)"),1,	"FORMULARTEXT_ANFANG",5);
		oFill.add_Line(oGrid3,new MyE2_String("Formulartext (Ende)"),1,		"FORMULARTEXT_ENDE",5);
		oFill.add_Line(oGrid3,new MyE2_String("Bemerkungen (Intern)"),1,		"BEMERKUNGEN_INTERN",5);

		/*
		 * grid seite 4
		 */
		MyE2_Grid oGrid4 = new MyE2_Grid(6,MyE2_Grid.STYLE_GRID_NO_BORDER());
		oGrid4.add(this.oMaskMAP.get_Comp(BST__CONST.HASH_KEY_DAUGHTERTABLE_POSITIONS),6,E2_INSETS.I_0_2_2_2);

		/*
		 * grid seite 5
		 */
		MyE2_Grid oGrid5 = new MyE2_Grid(6,MyE2_Grid.STYLE_GRID_NO_BORDER_W100());
		oFill.add_Line(oGrid5,new MyE2_String("Anzahl Entsperrungen"),1,"ZAEHLER_ENTSPERRUNG",5);
		oGrid5.add(new MyE2_Label(new MyE2_String("Druckprotokoll")),1,1, new Insets(0,12,10,2), new Alignment(Alignment.LEFT,Alignment.TOP));
		oGrid5.add(this.oMaskMAP.get_Comp(BST__CONST.HASH_KEY_DAUGHTERTABLE_PRINTPROTOKOLL),5,E2_INSETS.I_0_2_10_2);
		
		
		/*
		 * grids anordnen
		 */
		oTabbed.add_Tabb(new MyE2_String("Spedition"),oGrid1);
		oTabbed.add_Tabb(new MyE2_String("Weiteres.."),oGrid2);
		oTabbed.add_Tabb(new MyE2_String("Texte/Bem."),oGrid3);
		oTabbed.add_Tabb(new MyE2_String("TPA-Positionen"),oGrid4);
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
