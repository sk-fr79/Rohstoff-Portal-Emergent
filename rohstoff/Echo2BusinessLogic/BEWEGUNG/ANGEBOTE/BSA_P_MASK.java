package rohstoff.Echo2BusinessLogic.BEWEGUNG.ANGEBOTE;

import java.util.Vector;

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
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__CompMap_FieldMAP_Gemeinsamkeiten;
import echopointng.Separator;


/*
 * enthaelt die definition der maske
 */
public class BSA_P_MASK extends MyE2_Column implements IF_BaseComponent4Mask	
{
	private Vector<IF_ADDING_Allowed>  vMaskGrids = new Vector<IF_ADDING_Allowed>();
	
	private int iWidthLeftCol = 120;
	
	public BSA_P_MASK(BSA_P_MASK__ComponentMAP maskMAP) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());

		BSA_PA_MASK_ComponentMAP oComponentMAP_Zusatz = maskMAP.get_oComponentMAP_ZusatzfelderAngebot();
		
		E2_MaskFiller oFill = new E2_MaskFiller(maskMAP,oComponentMAP_Zusatz,null,E2_INSETS.I_0_2_10_2,E2_INSETS.I_0_2_10_2, null);

		/*
		 * grid seite 1
		 */
		MyE2_TabbedPane oTabbedPane = new MyE2_TabbedPane(null);
		oTabbedPane.set_bAutoHeight(true);

		
		MyE2_Grid oGrid1 = new MyE2_Grid(7,MyE2_Grid.STYLE_GRID_NO_BORDER());
		oGrid1.setColumnWidth(0, new Extent(this.iWidthLeftCol));
		
		oTabbedPane.add_Tabb(new MyE2_String("Positionsangaben"),oGrid1);
		
		
		MyE2_Row oGroupIDs = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		oGroupIDs.add(maskMAP.get_Comp("ID_VKOPF_STD"),E2_INSETS.I_0_0_10_0);
		oGroupIDs.add(maskMAP.get_Comp("ID_VPOS_STD"),E2_INSETS.I_0_0_10_0);
		oGroupIDs.add(new MyE2_Label(new MyE2_String("Positionsnummer:")),E2_INSETS.I_30_0_0_0);
		oGroupIDs.add(maskMAP.get_Comp("POSITIONSNUMMER"),E2_INSETS.I_5_0_0_0);
		oGroupIDs.add(new MyE2_Label(new MyE2_String("Typ:")),E2_INSETS.I_30_0_0_0);
		oGroupIDs.add(maskMAP.get_Comp("POSITION_TYP"),E2_INSETS.I_5_0_0_0);
		
		
		
		oGrid1.add(new MyE2_Label(new MyE2_String("IDs (Kopf/Pos)")),E2_INSETS.I_0_2_10_2);
		oGrid1.add(oGroupIDs,6,E2_INSETS.I_0_2_10_2);
		
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
		oGrid1.add(BS__CompMap_FieldMAP_Gemeinsamkeiten.get_MASK_GRID_Preise(maskMAP,"STD",new Integer(this.iWidthLeftCol),null),6,E2_INSETS.I_0_0_2_0);
		
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
		oFill.add_Line(oGrid1,new MyE2_String("Zahlungsbed."),1,		"ID_ZAHLUNGSBEDINGUNGEN|#   |ZAHLUNGSBEDINGUNGEN|# |ZAHLTAGE|# |FIXMONAT|# |FIXTAG|# |SKONTO_PROZENT",2);
		
		
		
		// gueltigkeitsbereich wird separat angefuegt (hervorgehoben)
		MyE2_Row	oRowGueltigkeit = new MyE2_Row(MyE2_Row.STYLE_3D_BORDER());
		oRowGueltigkeit.add(new MyE2_Label(new MyE2_String("von")),	E2_INSETS.I_2_2_2_2);
		oRowGueltigkeit.add(oComponentMAP_Zusatz.get_Comp("GUELTIG_VON"),		E2_INSETS.I_2_2_10_2);
		oRowGueltigkeit.add(new MyE2_Label(new MyE2_String("bis")),	E2_INSETS.I_2_2_2_2);
		oRowGueltigkeit.add(oComponentMAP_Zusatz.get_Comp("GUELTIG_BIS"),		E2_INSETS.I_2_2_10_2);
		oRowGueltigkeit.add(oComponentMAP_Zusatz.get_Comp(BSA__CONST.HASH_KEY_POSITION_MASK_SET_ACTUAL_MONTH),	E2_INSETS.I_10_2_10_2);
		oRowGueltigkeit.add(oComponentMAP_Zusatz.get_Comp(BSA__CONST.HASH_KEY_POSITION_MASK_SET_NEXT_MONTH),		E2_INSETS.I_10_2_10_2);
		
		oGrid1.add(new MyE2_Label(new MyE2_String("Gültigkeit")),1,	E2_INSETS.I_2_2_10_2);
		oGrid1.add(oRowGueltigkeit,6,E2_INSETS.I_2_2_10_2);

		this.vMaskGrids.add(oGrid1);
		
		this.add(oTabbedPane);
	}

	@Override
	public Vector<IF_ADDING_Allowed> get_Basic_Mask_Container_Components() throws myException
	{
		return this.vMaskGrids;
	}
	

}
