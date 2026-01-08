 
package rohstoff.Echo2BusinessLogic.CONTAINERTYP;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown2;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.USER_SELECTOR_GENERATOR_NT_KUERZEL;
import panter.gmbh.Echo2.components.E2_ExpandableRow;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.ENUM_USER_TYP;
import panter.gmbh.basics4project.DB_ENUMS.CONTAINERTYP;
import panter.gmbh.indep.dataTools.TERM.Nvl;
import panter.gmbh.indep.dataTools.TERM.TermLMR;
import panter.gmbh.indep.dataTools.TERM._TermCONST;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.CONTAINERTYP.BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL;
public class CONTYP_LIST_Selector extends E2_ExpandableRow {
    
    /*
     * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
     */
    
    private E2_SelectionComponentsVector     oSelVector = null;
    
    public CONTYP_LIST_Selector(E2_NavigationList oNavigationList) throws myException
    {
        super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
        
        this.oSelVector = new E2_SelectionComponentsVector(oNavigationList);
//        ownSelectorGeaendertVon sel_geaendert_von = new ownSelectorGeaendertVon();
//        this.oSelVector.add(sel_geaendert_von);
        
        
        int colWidthSelector = 50;
        
        BTN_JaNeinEgal btnAbroll = new BTN_JaNeinEgal()
            .setHeading("Abrollcontainer")
            .setZustand(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.EGAL)
			.setColWidth(null, colWidthSelector)
//			.setColWidth(ENUM_BTN_JA_NEIN_EGAL.JA, colWidthSelector-10)
//			.setCheckboxStyle(null, MyE2_CheckBox.STYLE_SMALL_NO_INSETS_NO_BORDER())
			.setColWidthHeading(100)
			;

        E2_ListSelector_JaNeinEgal sel = new E2_ListSelector_JaNeinEgal(btnAbroll)
        											.setSQLForStatus(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.JA, new TermLMR(new Nvl(CONTAINERTYP.abroll.t(), "'N'") ,_TermCONST.COMP.EQ.s(),"'Y'").s())
        											.setSQLForStatus(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.NEIN, new TermLMR(new Nvl(CONTAINERTYP.abroll.t(), "'N'") ,_TermCONST.COMP.EQ.s(),"'N'").s())
        											.setSQLForStatus(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.EGAL, "")	;
        
        this.oSelVector.add(sel);
        
        
        
        BTN_JaNeinEgal btnAbsetz = new BTN_JaNeinEgal()
        		.setHeading("Absetzcontainer")
    			.setZustand(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.EGAL)
    			.setColWidth(null, colWidthSelector)
    			.setColWidthHeading(100);
            
        sel = new E2_ListSelector_JaNeinEgal(btnAbsetz)
								.setSQLForStatus(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.JA, new TermLMR(new Nvl(CONTAINERTYP.absetz.t(), "'N'") ,_TermCONST.COMP.EQ.s(),"'Y'").s())
								.setSQLForStatus(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.NEIN, new TermLMR(new Nvl(CONTAINERTYP.absetz.t(), "'N'") ,_TermCONST.COMP.EQ.s(),"'N'").s())
								.setSQLForStatus(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.EGAL, "")	;

        this.oSelVector.add(sel);
        
        
        
        BTN_JaNeinEgal btnDicht = new BTN_JaNeinEgal()
        		.setHeading("Dicht")
    			.setZustand(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.EGAL)
    			.setColWidth(null, colWidthSelector)
    			.setColWidthHeading(100);
            
        sel = new E2_ListSelector_JaNeinEgal(btnDicht)
								.setSQLForStatus(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.JA, new TermLMR(new Nvl(CONTAINERTYP.dicht.t(), "'N'") ,_TermCONST.COMP.EQ.s(),"'Y'").s())
								.setSQLForStatus(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.NEIN, new TermLMR(new Nvl(CONTAINERTYP.dicht.t(), "'N'") ,_TermCONST.COMP.EQ.s(),"'N'").s())
								.setSQLForStatus(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.EGAL, "")	;

        this.oSelVector.add(sel);
        
        BTN_JaNeinEgal btnAblauf = new BTN_JaNeinEgal()
        		.setHeading("Mit Ablauf")
    			.setZustand(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.EGAL)
    			.setColWidth(null, colWidthSelector)
    			.setColWidthHeading(100);
            
        sel = new E2_ListSelector_JaNeinEgal(btnAblauf)
								.setSQLForStatus(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.JA, new TermLMR(new Nvl(CONTAINERTYP.ablauf.t(), "'N'") ,_TermCONST.COMP.EQ.s(),"'Y'").s())
								.setSQLForStatus(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.NEIN, new TermLMR(new Nvl(CONTAINERTYP.ablauf.t(), "'N'") ,_TermCONST.COMP.EQ.s(),"'N'").s())
								.setSQLForStatus(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.EGAL, "")	;

        this.oSelVector.add(sel);

        BTN_JaNeinEgal btnSym = new BTN_JaNeinEgal()
        		.setHeading("Symmetrisch")
    			.setZustand(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.EGAL)
    			.setColWidth(null, colWidthSelector)
    			.setColWidthHeading(100);
            
        sel = new E2_ListSelector_JaNeinEgal(btnSym)
								.setSQLForStatus(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.JA, new TermLMR(new Nvl(CONTAINERTYP.symmetrisch.t(), "'N'") ,_TermCONST.COMP.EQ.s(),"'Y'").s())
								.setSQLForStatus(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.NEIN, new TermLMR(new Nvl(CONTAINERTYP.symmetrisch.t(), "'N'") ,_TermCONST.COMP.EQ.s(),"'N'").s())
								.setSQLForStatus(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.EGAL, "")	;

        this.oSelVector.add(sel);

        BTN_JaNeinEgal btnDeckel = new BTN_JaNeinEgal()
        		.setHeading("Mit Deckel")
    	        .setZustand(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.EGAL)
    			.setColWidth(null, colWidthSelector)
    			.setColWidthHeading(100);
            
        sel = new E2_ListSelector_JaNeinEgal(btnDeckel)
								.setSQLForStatus(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.JA, new TermLMR(new Nvl(CONTAINERTYP.deckel.t(), "'N'") ,_TermCONST.COMP.EQ.s(),"'Y'").s())
								.setSQLForStatus(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.NEIN, new TermLMR(new Nvl(CONTAINERTYP.deckel.t(), "'N'") ,_TermCONST.COMP.EQ.s(),"'N'").s())
								.setSQLForStatus(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.EGAL, "")	;

        this.oSelVector.add(sel);

        
        BTN_JaNeinEgal btnPlane = new BTN_JaNeinEgal()
        		.setHeading("Mit Plane")
    			.setZustand(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.EGAL)
    			.setColWidth(null, colWidthSelector)
    			.setColWidthHeading(100);
            
        sel = new E2_ListSelector_JaNeinEgal(btnPlane)
								.setSQLForStatus(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.JA, new TermLMR(new Nvl(CONTAINERTYP.plane.t(), "'N'") ,_TermCONST.COMP.EQ.s(),"'Y'").s())
								.setSQLForStatus(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.NEIN, new TermLMR(new Nvl(CONTAINERTYP.plane.t(), "'N'") ,_TermCONST.COMP.EQ.s(),"'N'").s())
								.setSQLForStatus(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.EGAL, "")	;

        this.oSelVector.add(sel);

        
        //
        // GUI
        //
        
        E2_Grid oGridInnen = new E2_Grid()._s(4);
        this.add(oGridInnen, E2_INSETS.I_4_4_4_4);

//        oGridInnen.add(sel_geaendert_von.get_oComponentForSelection());
//        oGridInnen._endLine(new RB_gld());
        oGridInnen._a(btnAbroll	,new RB_gld()._ins(0, 3, 3, 3));
        oGridInnen._a(btnDicht 	,new RB_gld()._ins(20, 3, 3, 3));
        oGridInnen._a(btnDeckel	,new RB_gld()._ins(20, 3, 3, 3));
        oGridInnen._a(btnSym	,new RB_gld()._ins(20, 3, 3, 3));

        oGridInnen._a(btnAbsetz,new RB_gld()._ins(0, 3, 3, 3));
        oGridInnen._a(btnAblauf,new RB_gld()._ins(20, 3, 3, 3));
        oGridInnen._a(btnPlane,new RB_gld()._ins(20, 3, 3, 3));
        oGridInnen._endLine(new RB_gld());
        
       
        
//        oGridInnen.add(selectorAblauf.get_oComponentForSelection());
//        oGridInnen.add(selectorSym.get_oComponentForSelection());
//        oGridInnen.add(selectorDeckel.get_oComponentForSelection());
//        oGridInnen.add(selectorPlane.get_oComponentForSelection());
        
        
        
        
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
            
            And  bed = new And(CONTAINERTYP.geaendert_von,"'#WERT#'");
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
 
