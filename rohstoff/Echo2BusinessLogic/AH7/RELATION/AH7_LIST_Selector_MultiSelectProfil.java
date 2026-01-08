/**
 * 
 */
package rohstoff.Echo2BusinessLogic.AH7.RELATION;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown2;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.DB_ENUMS.AH7_PROFIL;
import panter.gmbh.basics4project.DB_ENUMS.AH7_STEUERDATEI;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public class AH7_LIST_Selector_MultiSelectProfil extends E2_ListSelectorMultiDropDown2 {

	private static String[][] NullValCompare = {{"-",""},{"ohne Profil","-1"}};
	
	/**
	 * @throws myException
	 */
	public AH7_LIST_Selector_MultiSelectProfil() throws myException {
		super();
		
		SEL sel = new SEL().ADDFIELD(AH7_PROFIL.bezeichnung, AH7_PROFIL.id_ah7_profil).FROM(_TAB.ah7_profil).ORDERUP(AH7_PROFIL.bezeichnung);
		
		String[][] valArray = bibDB.EinzelAbfrageInArray(sel.s());
		
		valArray = bibARR.add_array_inFront2(valArray, AH7_LIST_Selector_MultiSelectProfil.NullValCompare);
		
		MyE2_SelectField  selField = new MyE2_SelectField(new Extent(200));
		selField.set_ListenInhalt(valArray, false);
		
		this.INIT(selField, "NVL("+AH7_STEUERDATEI.id_ah7_profil.tnfn()+",-1)=#WERT#", null);
		this.set_extOfSelectComponentDropDown(new Extent(200));
	}

	
    @Override
    public E2_BasicModuleContainer get_PopupContainer() throws myException {
        return new ownBasicContainer();
    }
    
    private class ownBasicContainer extends E2_BasicModuleContainer {}
    
    @Override
    public Component get_oComponentForSelection() throws myException {
        int[] breite = {100,100};
        MyE2_Grid  gridHelp = new MyE2_Grid(breite, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
        gridHelp.add(new MyE2_Label(new MyE2_String("Zugeordnetes Profil:")), E2_INSETS.I(0,0,10,0));
        gridHelp.add(this.get_oComponentWithoutText(), E2_INSETS.I(0,0,10,0));
        //gridHelp.setBorder(new Border(1, Color.BLACK, Border.STYLE_SOLID));
        return gridHelp;
    }

}
