 
package panter.gmbh.Echo2.basic_tools.emailv2;
  
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown2;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDownV3;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelector_DB_Defined;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.USER_SELECTOR_GENERATOR_NT_KUERZEL;
import panter.gmbh.Echo2.components.E2_ExpandableRow;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.ENUM_USER_TYP;
import panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.exceptions.myException;
             
public class EM2_LIST_Selector extends E2_ExpandableRow {
    
    /*
     * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
     */
    
    private E2_SelectionComponentsVector     oSelVector = null;
    
    //zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   m_tpHashMap = null;
    
    
    public EM2_LIST_Selector(RB_TransportHashMap  p_tpHashMap) throws myException
    {
        super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
        
        this.m_tpHashMap = p_tpHashMap;       
        this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SELECTOR,this);
        this.oSelVector = new E2_SelectionComponentsVector(this.m_tpHashMap.getNavigationList());
        this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SELECTIONCOMPONENTVECTOR,this.oSelVector );
        
        E2_Grid oGridInnen = new E2_Grid();
        
        ownSelectorGeaendertVon sel_geaendert_von = new ownSelectorGeaendertVon();
        this.oSelVector.add(sel_geaendert_von);
        
        //selektoren erzeugen
        XX_ListSelektor selbetreff = new E2_ListSelectorMultiDropDownV3(_TAB.find_field(_TAB.email_send,"betreff"),null)._setBeschriftung(EM2_READABLE_FIELD_NAME.getMaskText(EMAIL_SEND.betreff));
        XX_ListSelektor selbetreff_2_send = new E2_ListSelectorMultiDropDownV3(_TAB.find_field(_TAB.email_send,"betreff_2_send"),null)._setBeschriftung(EM2_READABLE_FIELD_NAME.getMaskText(EMAIL_SEND.betreff_2_send));
        XX_ListSelektor selid_email_send = new E2_ListSelectorMultiDropDownV3(_TAB.find_field(_TAB.email_send,"id_email_send"),null)._setBeschriftung(EM2_READABLE_FIELD_NAME.getMaskText(EMAIL_SEND.id_email_send));
        XX_ListSelektor selid_table = new E2_ListSelectorMultiDropDownV3(_TAB.find_field(_TAB.email_send,"id_table"),null)._setBeschriftung(EM2_READABLE_FIELD_NAME.getMaskText(EMAIL_SEND.id_table));
        XX_ListSelektor selsender_adress = new E2_ListSelectorMultiDropDownV3(_TAB.find_field(_TAB.email_send,"sender_adress"),null)._setBeschriftung(EM2_READABLE_FIELD_NAME.getMaskText(EMAIL_SEND.sender_adress));
        XX_ListSelektor selsend_type = new E2_ListSelectorMultiDropDownV3(_TAB.find_field(_TAB.email_send,"send_type"),null)._setBeschriftung(EM2_READABLE_FIELD_NAME.getMaskText(EMAIL_SEND.send_type));
        XX_ListSelektor seltable_base_name = new E2_ListSelectorMultiDropDownV3(_TAB.find_field(_TAB.email_send,"table_base_name"),null)._setBeschriftung(EM2_READABLE_FIELD_NAME.getMaskText(EMAIL_SEND.table_base_name));
        XX_ListSelektor seltext = new E2_ListSelectorMultiDropDownV3(_TAB.find_field(_TAB.email_send,"text"),null)._setBeschriftung(EM2_READABLE_FIELD_NAME.getMaskText(EMAIL_SEND.text));
        XX_ListSelektor seltext_2_send = new E2_ListSelectorMultiDropDownV3(_TAB.find_field(_TAB.email_send,"text_2_send"),null)._setBeschriftung(EM2_READABLE_FIELD_NAME.getMaskText(EMAIL_SEND.text_2_send));
        this.oSelVector.add(selbetreff);
        this.oSelVector.add(selbetreff_2_send);
        this.oSelVector.add(selid_email_send);
        this.oSelVector.add(selid_table);
        this.oSelVector.add(selsender_adress);
        this.oSelVector.add(selsend_type);
        this.oSelVector.add(seltable_base_name);
        this.oSelVector.add(seltext);
        this.oSelVector.add(seltext_2_send);
        //
        
        oGridInnen.add(sel_geaendert_von.get_oComponentForSelection());
        
        //selektoren am panel anordnen
        oGridInnen._a(selbetreff.get_oComponentForSelection(), new RB_gld()._ins(0, 0, 20, 0)._left_mid());
        oGridInnen._a(selbetreff_2_send.get_oComponentForSelection(), new RB_gld()._ins(0, 0, 20, 0)._left_mid());
        oGridInnen._a(selid_email_send.get_oComponentForSelection(), new RB_gld()._ins(0, 0, 20, 0)._left_mid());
        oGridInnen._a(selid_table.get_oComponentForSelection(), new RB_gld()._ins(0, 0, 20, 0)._left_mid());
        oGridInnen._a(selsender_adress.get_oComponentForSelection(), new RB_gld()._ins(0, 0, 20, 0)._left_mid());
        oGridInnen._a(selsend_type.get_oComponentForSelection(), new RB_gld()._ins(0, 0, 20, 0)._left_mid());
        oGridInnen._a(seltable_base_name.get_oComponentForSelection(), new RB_gld()._ins(0, 0, 20, 0)._left_mid());
        oGridInnen._a(seltext.get_oComponentForSelection(), new RB_gld()._ins(0, 0, 20, 0)._left_mid());
        oGridInnen._a(seltext_2_send.get_oComponentForSelection(), new RB_gld()._ins(0, 0, 20, 0)._left_mid());
        
        // db-gestuetzter listselektor
		E2_ListSelector_DB_Defined 	oDB_BasedSelektor =  new E2_ListSelector_DB_Defined(E2_MODULNAME_ENUM.MODUL.EMAIL_SEND_V2_LIST.get_callKey());
		oSelVector.add(oDB_BasedSelektor);
		oGridInnen._a(oDB_BasedSelektor.get_oComponentForSelection(S.ms("Diverse:"),100), new RB_gld()._ins(0,0,5,0));
        
        oGridInnen._s(oGridInnen.getComponentCount());
        this.add(oGridInnen, E2_INSETS.I_4_4_4_4);
        
    }
    public E2_SelectionComponentsVector get_oSelVector()
    {
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
            
            And  bed = new And(EMAIL_SEND.geaendert_von,"'#WERT#'");
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
            gridHelp.add(new MyE2_Label(new MyE2_String("#-- SELEKTOR --#")), E2_INSETS.I(0,2,10,2));
            gridHelp.add(this.get_oComponentWithoutText(), E2_INSETS.I(0,2,10,2));
            return gridHelp;
        }
        
    }
    
    
}
 
 
