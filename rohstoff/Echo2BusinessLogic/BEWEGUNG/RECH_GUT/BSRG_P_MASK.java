package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.IF_ADDING_Allowed;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.ListAndMask.Mask.IF_BaseComponent4Mask;
import panter.gmbh.Echo2.components.E2_Subgrid_4_Mask;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__CompMap_FieldMAP_Gemeinsamkeiten;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;
import echopointng.Separator;


/*
 * enthaelt die definition der maske
 */
public class BSRG_P_MASK extends MyE2_Column implements IF_BaseComponent4Mask	
{
	private Vector<IF_ADDING_Allowed>  vMaskGrids = new Vector<IF_ADDING_Allowed>();
	
	public BSRG_P_MASK(E2_ComponentMAP maskMAP,BS__SETTING oBS_Setting) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());

		E2_MaskFiller oFill = new E2_MaskFiller(maskMAP,null,null,E2_INSETS.I_0_2_10_2,E2_INSETS.I_0_2_10_2, new Alignment(Alignment.LEFT,Alignment.CENTER));

		
		/*
		 * grid seite 1
		 */
		MyE2_TabbedPane oTabbedPane = new MyE2_TabbedPane(null);
		oTabbedPane.set_bAutoHeight(true);

		
		MyE2_Grid oGrid1 = new MyE2_Grid(7,MyE2_Grid.STYLE_GRID_NO_BORDER());
		oTabbedPane.add_Tabb(new MyE2_String("Positionsangaben"),oGrid1);
		
		
		String cHelp = "Adresse: ";
		if (oBS_Setting != null)
		{
			cHelp = "Lieferant: ";
			if (oBS_Setting.get_cBELEGTYP().equals(myCONST.VORGANGSART_RECHNUNG))
				cHelp = "Abnehmer: ";
		}
		
		oFill.add_Line(oGrid1, new MyE2_String(cHelp), 1, "ID_ADRESSE", 3,"#Sonder-Pos|IST_SONDERPOSITION|#  |#Strecke|STRECKENGESCHAEFT",4);
		oGrid1.add(new Separator(),7,new Insets(0,1,0,1));
		
		oFill.add_Line_WithComponents(oGrid1, 
				new MyE2_Label(new MyE2_String("IDs")), 
				new E2_Subgrid_4_Mask("Kopf|Position|Fuhre|Fuhren-Ort|Positionsnummer|Positionstyp","ID_VKOPF_RG|ID_VPOS_RG|ID_VPOS_TPA_FUHRE_ZUGEORD|ID_VPOS_TPA_FUHRE_ORT_ZUGEORD|POSITIONSNUMMER|POSITION_TYP|",maskMAP, E2_INSETS.I_1_1_20_1, E2_INSETS.I_1_1_20_1), 3, E2_INSETS.I_0_0_0_0,
				null,1,null,null,1,null);
				
		
		
		oGrid1.add(new Separator(),7,new Insets(0,1,0,1));
		
		MyE2_Row oGroupSorte = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		oGroupSorte.add(maskMAP.get_Comp("ID_ARTIKEL_BEZ"),E2_INSETS.I_0_0_10_0);
		oGroupSorte.add(new MyE2_Label("("),E2_INSETS.I_0_0_2_0);
		oGroupSorte.add(maskMAP.get_Comp("ANR1"),E2_INSETS.I_0_0_2_0);
		oGroupSorte.add(new MyE2_Label(" / "));
		oGroupSorte.add(maskMAP.get_Comp("ANR2"),E2_INSETS.I_0_0_2_0);
		oGroupSorte.add(new MyE2_Label(")"),E2_INSETS.I_0_0_10_0);
		oGroupSorte.add(new MyE2_Label(new MyE2_String("ID Artikel:")),E2_INSETS.I_0_0_2_0);
		oGroupSorte.add(maskMAP.get_Comp("ID_ARTIKEL"));

		oGrid1.add(new MyE2_Label(new MyE2_String("Sorte")),E2_INSETS.I_0_2_10_2);
		oGrid1.add(oGroupSorte,6,E2_INSETS.I_0_2_10_2);
		
		oGrid1.add(new Separator(),7,new Insets(0,1,0,1));
		oFill.add_Line(oGrid1, new MyE2_String("Kontrakt-Pos"), 1, "ID_VPOS_KON_ZUGEORD|#  |#Best.Nr:|BESTELLNUMMER", 6);
		
		oGrid1.add(new Separator(),7,new Insets(0,1,0,1));

		//den inneren block einfuegen
		oGrid1.add(BS__CompMap_FieldMAP_Gemeinsamkeiten.get_MASK_GRID_Preise(maskMAP,"RG",null,null),7,E2_INSETS.I_0_0_2_0);

		
		oGrid1.add(new Separator(),7,new Insets(0,1,0,1));

		MyE2_Row oGroupEinheiten = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		oGroupEinheiten.add(new MyE2_Label("Menge:"),E2_INSETS.I_0_0_2_0);
		oGroupEinheiten.add(maskMAP.get_Comp("EINHEITKURZ"),E2_INSETS.I_0_0_10_0);
		oGroupEinheiten.add(new MyE2_Label("Mengen-Divisor:"),E2_INSETS.I_0_0_2_0);
		oGroupEinheiten.add(maskMAP.get_Comp("MENGENDIVISOR"),E2_INSETS.I_0_0_10_0);
		oGroupEinheiten.add(new MyE2_Label("Preiseinheit:"),E2_INSETS.I_0_0_2_0);
		oGroupEinheiten.add(maskMAP.get_Comp("EINHEIT_PREIS_KURZ"));

		oGrid1.add(new MyE2_Label(new MyE2_String("Einheiten")),E2_INSETS.I_0_2_10_2);
		oGrid1.add(oGroupEinheiten,6,E2_INSETS.I_0_2_10_2);
		
		oFill.add_Line(oGrid1,new MyE2_String("Lieferbed. "),1,			"LIEFERBEDINGUNGEN",3);
		oFill.add_Line(oGrid1,new MyE2_String("Zahlungsbedingungen"),1,		"ID_ZAHLUNGSBEDINGUNGEN|#   |ZAHLUNGSBEDINGUNGEN|# |ZAHLTAGE|# |FIXMONAT|# |FIXTAG|# |SKONTO_PROZENT",6);
		
		oFill.add_Line(oGrid1,new MyE2_String("Positionstyp"),1,		"LAGER_VORZEICHEN|#   |#  ",2);
		oFill.add_Line(oGrid1,new MyE2_String("Leistungdatum"),1,		"AUSFUEHRUNGSDATUM|#    |#Zahldatum:|ZAHLUNGSBED_CALC_DATUM",4);

		oGrid1.add(new Separator(),7,new Insets(0,1,0,1));
		oFill.add_Line(oGrid1,new MyE2_String("Steuersatz "),1,			"ID_TAX|#    (alt:|STEUERSATZ|# )       |#Ohne Steuer |OHNE_STEUER",4);
		oFill.add_Line(oGrid1,new MyE2_String("Steuervermerk"),1,		"EU_STEUER_VERMERK|"+BSRG__CONST.HASH_KEY_BUTTON_MASK_DROPDOWN_STEUERTEXT,4);

		
		MyE2_Grid oGrid2 = new MyE2_Grid(7,MyE2_Grid.STYLE_GRID_NO_BORDER());
		oTabbedPane.add_Tabb(new MyE2_String("Abzüge"),oGrid2);
		

		
		oGrid2.add(maskMAP.get_Comp(BSRG__CONST.HASH_KEY_DAUGHTER_ABZUEGE_IN_POS),7,E2_INSETS.I_2_2_2_2);

		this.vMaskGrids.add(oGrid1);
		this.vMaskGrids.add(oGrid2);
	
		this.add(oTabbedPane);
	}

	@Override
	public Vector<IF_ADDING_Allowed> get_Basic_Mask_Container_Components() throws myException
	{
		return this.vMaskGrids;
	}
	

}
