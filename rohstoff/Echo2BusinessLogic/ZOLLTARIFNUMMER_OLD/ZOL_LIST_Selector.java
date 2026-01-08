package rohstoff.Echo2BusinessLogic.ZOLLTARIFNUMMER_OLD;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown2;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.USER_SELECTOR_GENERATOR_NT_KUERZEL;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_LIST_Selektor_Bestaetigt;
import panter.gmbh.Echo2.components.E2_ExpandableRow;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.ENUM_USER_TYP;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.exceptions.myException;


public class ZOL_LIST_Selector extends E2_ExpandableRow
{
    
    /*
     * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
     */
    
    private E2_SelectionComponentsVector     	oSelVector 	= null;
    private ZOL_LIST_Selektor_Aktiv 			oSelAktiv 	= null;
    private ZOL_LIST_Selektor_RC				oSelRC 		= null;
    
    
    public ZOL_LIST_Selector(E2_NavigationList oNavigationList) throws myException
    {
        super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
        
        this.oSelVector = new E2_SelectionComponentsVector(oNavigationList);
        
        oSelAktiv 	=  new ZOL_LIST_Selektor_Aktiv();
        oSelVector.add(oSelAktiv);
        
        oSelRC 		= new ZOL_LIST_Selektor_RC();
        oSelVector.add(oSelRC);
        
        
        MyE2_Grid oGridInnen = new MyE2_Grid(4,1);
        this.add(oGridInnen, E2_INSETS.I_4_4_4_4);
        oGridInnen.add(oSelAktiv.get_oComponentForSelection());
        oGridInnen.add(oSelRC.get_oComponentForSelection(),E2_INSETS.I_20_0_0_0	);
        
    }

    
    
    public E2_SelectionComponentsVector get_oSelVector()
    {
        return oSelVector;
    }
    
    
    
}
 
