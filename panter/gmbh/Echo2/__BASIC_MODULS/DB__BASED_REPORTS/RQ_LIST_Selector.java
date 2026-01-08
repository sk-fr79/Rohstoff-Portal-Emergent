 
package panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS;
  
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown2;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SimpleStatus_an_aus;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.USER_SELECTOR_GENERATOR_NT_KUERZEL;
import panter.gmbh.Echo2.components.E2_ExpandableRow;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.ENUM_USER_TYP;
import panter.gmbh.basics4project.DB_ENUMS.REPORTING_QUERY;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.exceptions.myException;
public class RQ_LIST_Selector extends E2_ExpandableRow {
    
    /*
     * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
     */
    
    private E2_SelectionComponentsVector     oSelVector = null;
    
    //zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   m_tpHashMap = null;
    
    
    public RQ_LIST_Selector(RB_TransportHashMap  p_tpHashMap) throws myException
    {
        super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
        
        this.m_tpHashMap = p_tpHashMap;       
        this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SELECTOR,this);
        this.oSelVector = new E2_SelectionComponentsVector(this.m_tpHashMap.getNavigationList());
        this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SELECTIONCOMPONENTVECTOR,this.oSelVector );
        
        ownSelectorGeaendertVon sel_geaendert_von = new ownSelectorGeaendertVon();
        SelectAnAus 			selAnAus = new SelectAnAus();
        this.oSelVector.add(sel_geaendert_von);
        this.oSelVector.add(selAnAus);
        E2_Grid oGridInnen = new E2_Grid()._setSize(300,300);
        this.add(oGridInnen, E2_INSETS.I_4_4_4_4);
        
        oGridInnen	._a(sel_geaendert_von.get_oComponentForSelection(), new RB_gld()._left_mid())
        			._a(selAnAus.get_oComponentForSelection(), new RB_gld()._left_mid());
        
    }
    
    public E2_SelectionComponentsVector get_oSelVector()   {
        return oSelVector;
    }
    
    /**
     * selektor fuer die auswahl von modulen ....
     * @author martin
     *
     */
    private class ownSelectorGeaendertVon extends E2_ListSelectorMultiDropDown2 {
        public ownSelectorGeaendertVon() throws myException {
            super();
            
            String[][] userList = new USER_SELECTOR_GENERATOR_NT_KUERZEL(ENUM_USER_TYP.BUERO).get_selUsers(true);
            MyE2_SelectField  selFieldKenner = new MyE2_SelectField(userList,"", false);    
            
            And  bed = new And(REPORTING_QUERY.geaendert_von,"'#WERT#'");
            this.INIT(selFieldKenner, bed.s(), null);
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
            gridHelp.add(new MyE2_Label(new MyE2_String("Letzte Änderung von..")), E2_INSETS.I(0,2,10,2));
            gridHelp.add(this.get_oComponentWithoutText(), E2_INSETS.I(0,2,10,2));
            return gridHelp;
        }
        
    }
    
    
    private class SelectAnAus extends E2_SimpleStatus_an_aus {

		/**
		 * @author martin
		 * @date 08.11.2018
		 */
		public SelectAnAus() {
			super(REPORTING_QUERY.aktiv.tnfn(), "Aktive Reports", "Inaktive Reports", "", "", "",true,true,100);
		}
		public Component get_oComponentForSelection() throws myException {
			//baut ein grid mit ueberschrift kursiv, klein und checkboxen an/aus nebeneinander
			E2_Grid  g = new E2_Grid()._setSize(40,120,120)
						._a(new RB_lab("Status")._fs(-2)._i(), new RB_gld()._ins(E2_INSETS.I(1,1,1,1)))
						._a(this.get_vCheckBoxTypen().get(0), new RB_gld()._ins(E2_INSETS.I(1,1,1,1)))
						._a(this.get_vCheckBoxTypen().get(1), new RB_gld()._ins(E2_INSETS.I(1,1,1,1)))
						;
			return g;
		}

    }
    
}
 
 
