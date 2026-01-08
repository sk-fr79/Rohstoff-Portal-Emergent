package rohstoff.Echo2BusinessLogic.BAM_IMPORT;


import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.components.E2_ExpandableRow;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataTools.bibDB_SYNTAX;
import panter.gmbh.indep.exceptions.myException;

public class BAM_IMPORT_LIST_Selector extends E2_ExpandableRow
{
	
	/*
	 * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
	 */
	
	private E2_SelectionComponentsVector 	oSelVector = null;
	
	public BAM_IMPORT_LIST_Selector(E2_NavigationList oNavigationList) throws myException
	{
		super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		
		this.oSelVector = new E2_SelectionComponentsVector(oNavigationList);
		
		MyE2_CheckBox cbNurOffene = new MyE2_CheckBox("Nur unbearbeitete Befundungen anzeigen");
		cbNurOffene.setSelected(true);
		oSelVector.add(new E2_ListSelectorStandard(cbNurOffene,bibDB_SYNTAX.IS_FALSE(_DB.BAM_IMPORT$ABGESCHLOSSEN),""));
		
		MyE2_Grid oGridInnen = new MyE2_Grid(4,0);
		this.add(oGridInnen, E2_INSETS.I(2,2,2,2));
		oGridInnen.add((Component)cbNurOffene, E2_INSETS.I(2,2,2,2));
		
	}

	public E2_SelectionComponentsVector get_oSelVector()
	{
		return oSelVector;
	}

}
