package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.POS_VORLAGE;

import java.util.Vector;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.IF_ADDING_Allowed;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.ListAndMask.Mask.IF_BaseComponent4Mask;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__CompMap_FieldMAP_Gemeinsamkeiten;
import echopointng.Separator;


/*
 * enthaelt die definition der maske
 */
public class BS_VL_MASK extends MyE2_Column implements IF_BaseComponent4Mask	
{
	private Vector<IF_ADDING_Allowed>  vMaskGrids = new Vector<IF_ADDING_Allowed>();
	
	public BS_VL_MASK(E2_ComponentMAP maskMAP) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());

		E2_MaskFiller oFill = new E2_MaskFiller(maskMAP,null,null,E2_INSETS.I_0_2_10_2,E2_INSETS.I_0_2_10_2, null);

		
		/*
		 * grid seite 1
		 */
			
		MyE2_Grid oGrid1 = new MyE2_Grid(7,MyE2_Grid.STYLE_GRID_NO_BORDER());
		
		oGrid1.add(new Separator(),7,new Insets(0,1,0,1));
		
		MyE2_Row oGroupIDs = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		
		oGroupIDs.add(maskMAP.get_Comp("ID_VPOS_RG_VL"),E2_INSETS.I_0_0_10_0);
		
		oGrid1.add(new MyE2_Label(new MyE2_String("ID")),E2_INSETS.I_0_2_10_2);
		oGrid1.add(oGroupIDs,6,E2_INSETS.I_0_2_10_2);
		
		oGrid1.add(new Separator(),7,new Insets(0,1,0,1));
		
		oFill.add_Line(oGrid1,new MyE2_String("Anzeige in der Auswahl "),1,			"EINTRAG_FUER_DROPDOWN|#     |#Verteiler |VERTEILER",2);
		
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

		//den inneren block einfuegen
		oGrid1.add(BS__CompMap_FieldMAP_Gemeinsamkeiten.get_MASK_GRID_Preise(maskMAP,"RG",null, null),7,E2_INSETS.I_0_0_2_0);

		
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
		
		oFill.add_Line(oGrid1,new MyE2_String("Steuersatz "),1,			"STEUERSATZ",2);
		oFill.add_Line(oGrid1,new MyE2_String("Lieferbed. "),1,			"LIEFERBEDINGUNGEN",2);
		oFill.add_Line(oGrid1,new MyE2_String("Zahlungsbedingungen"),1,		"ID_ZAHLUNGSBEDINGUNGEN|#   |ZAHLUNGSBEDINGUNGEN|# |ZAHLTAGE|# |FIXMONAT|# |FIXTAG|# |SKONTO_PROZENT",2);
		
		oFill.add_Line(oGrid1,new MyE2_String("Lagervorzeichen"),1,		"LAGER_VORZEICHEN|#   |#  ",2);
		
	
		this.add(oGrid1);
		
		vMaskGrids.add(oGrid1);
		
	}

	@Override
	public Vector<IF_ADDING_Allowed> get_Basic_Mask_Container_Components() throws myException
	{
		return this.vMaskGrids;
	}
	

}
