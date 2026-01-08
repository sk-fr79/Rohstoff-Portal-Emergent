 
package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_SIMPLE;
  
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown2;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor;
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
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG;
import panter.gmbh.indep.dataTools.TERM.Nvl;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.TermLMR;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.VglNull;
import panter.gmbh.indep.dataTools.TERM.VglNotNull;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.Or;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.UTILITIES.UTIL_MultiSelectField_Factory_ForUser;
import rohstoff.Echo2BusinessLogic.CONTAINERTYP.BTN_JaNeinEgal;
import rohstoff.Echo2BusinessLogic.CONTAINERTYP.BTN_JaNeinEgal_EXT.ENUM_BTN_JA_NEIN_EGAL;
import rohstoff.Echo2BusinessLogic.CONTAINERTYP.E2_ListSelector_JaNeinEgal;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL.SEL_NT.WF_ListSelektor_3_multi_user_selektor;
public class WF_SIMPLE_LIST_Selector extends E2_ExpandableRow {
    
    /*
     * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
     */
    
    private E2_SelectionComponentsVector     oSelVector = null;
    
    private UTIL_MultiSelectField_Factory_ForUser oSelFactoryForUser = null;
    
	WF_SIMPLE_LIST_multi_user_selektor  			oSelUsers = null;

    
    //zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   m_tpHashMap = null;
    
    
    public WF_SIMPLE_LIST_Selector(RB_TransportHashMap  p_tpHashMap) throws myException
    {
        super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
        
        this.m_tpHashMap = p_tpHashMap;       
        this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SELECTOR,this);
        this.oSelVector = new E2_SelectionComponentsVector(this.m_tpHashMap.getNavigationList());
        this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SELECTIONCOMPONENTVECTOR,this.oSelVector );
        
        
//        ownSelectorGeaendertVon sel_geaendert_von = new ownSelectorGeaendertVon();
//        oSelVector.add(sel_geaendert_von);
        
        selektorBetrachtungszeitraum sel_betrachtungszeitraum = new selektorBetrachtungszeitraum();
        oSelVector.add(sel_betrachtungszeitraum);
        
        // Haupt-Benutzer Auswahl 
        Term SelectUserBearbeiter = new TermLMR(LAUFZETTEL_EINTRAG.id_user_bearbeiter.tnfn(),COMP.EQ.ausdruck(), "#WERT#");
        Term SelectUserErzeuger = new TermLMR(LAUFZETTEL_EINTRAG.id_user_besitzer.tnfn(),COMP.EQ.ausdruck(), "#WERT#");
        Term SelectUserAbgeschlossen = new TermLMR(LAUFZETTEL_EINTRAG.id_user_abgeschlossen_von.tnfn(),COMP.EQ.ausdruck(), "#WERT#");
        Term SelectWFUserBesitzer = new TermLMR(LAUFZETTEL.id_user_besitzer.tnfn(),COMP.EQ.ausdruck(), "#WERT#");
        Term SelectWFUserSupervisor = new TermLMR(LAUFZETTEL.id_user_supervisor.tnfn(),COMP.EQ.ausdruck(), "#WERT#");
        Term SelectWFUserAbgeschlossen = new TermLMR(LAUFZETTEL.id_user_abgeschlossen_von.tnfn(),COMP.EQ.ausdruck(), "#WERT#");
        Or _or = new Or(SelectUserBearbeiter).OR(SelectUserErzeuger).OR(SelectUserAbgeschlossen).OR(SelectWFUserBesitzer).OR(SelectWFUserSupervisor).OR(SelectWFUserAbgeschlossen);
        String sqlUserSelection = _or.s();
        oSelFactoryForUser = new UTIL_MultiSelectField_Factory_ForUser("", 200, sqlUserSelection , false);
        oSelVector.add(oSelFactoryForUser);

        ((WF_SIMPLE__NaviList) this.m_tpHashMap.getNavigationList()).set_MainUser(oSelFactoryForUser);	
        
        BTN_JaNeinEgal btnSelektorAbgeschlossen = new BTN_JaNeinEgal()
         		.setHeading("Aufgabe ist")
     			.setZustand(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.JA)
     			.setColWidthHeading(80)
     			.setColWidth(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.JA, 100)
     			.setColWidth(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.NEIN, 150)
     			.setColWidth(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.EGAL, 100)
     			.setCaption(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.JA, "Offen")
     			.setCaption(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.NEIN, "Abgeschlossen")
     			.setCaption(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.EGAL, "n/a")
     			;

        E2_ListSelector_JaNeinEgal selAbgeschlossen = new E2_ListSelector_JaNeinEgal(btnSelektorAbgeschlossen)
 								.setSQLForStatus(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.JA, new VglNull(LAUFZETTEL_EINTRAG.abgeschlossen_am) .s())
 								.setSQLForStatus(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.NEIN, new VglNotNull(LAUFZETTEL_EINTRAG.abgeschlossen_am) .s())
 								.setSQLForStatus(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.EGAL, "")	;

         oSelVector.add(selAbgeschlossen);
         
         
         
         BTN_JaNeinEgal btnSelDel = new BTN_JaNeinEgal()
          		.setHeading("Aufgabe ist")
      			.setZustand(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.JA)
     			.setColWidth(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.JA, 100)
     			.setColWidth(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.NEIN, 150)
     			.setColWidth(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.EGAL, 100)
      			.setColWidthHeading(80)
      			.setCaption(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.JA, "Ungelöscht")
      			.setCaption(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.NEIN, "Gelöscht")
      			.setCaption(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.EGAL, "n/a")
      			;

         String s = new TermLMR(new Nvl(LAUFZETTEL_EINTRAG.geloescht.t(),"'N'"),"=","'N'").s();
         
         Or deleted = new Or( new TermLMR(new Nvl(LAUFZETTEL_EINTRAG.geloescht.t(),"'N'"),"=","'Y'") ).OR(new TermLMR(new Nvl(LAUFZETTEL.geloescht.t(),"'N'"),"=","'Y'") ) ;
         And undeleted = new And(new TermLMR(new Nvl(LAUFZETTEL_EINTRAG.geloescht.t(),"'N'"),"=","'N'")).and(new TermLMR(new Nvl(LAUFZETTEL.geloescht.t(),"'N'"),"=","'N'"));
         
         String stest = deleted.s();
         E2_ListSelector_JaNeinEgal selDel = new E2_ListSelector_JaNeinEgal(btnSelDel)
        		 .setSQLForStatus(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.JA, undeleted.s())
        		 .setSQLForStatus(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.NEIN, deleted.s())
        		 .setSQLForStatus(BTN_JaNeinEgal.ENUM_BTN_JA_NEIN_EGAL.EGAL, "")
        		 ;
         
         
         oSelVector.add(selDel);
       
         
        
         this.oSelUsers = new WF_SIMPLE_LIST_multi_user_selektor();
         
         oSelVector.add(this.oSelUsers);
         
         
        
        E2_Grid oGridStatus = new E2_Grid()  //._setSize(600)
        		._a(new RB_lab()._tr("Status des Laufzettels")._fsa(-1)._i(), new RB_gld()._ins(2))
        		._a( btnSelektorAbgeschlossen, new RB_gld()._ins(2))
        		._a( btnSelDel				, new RB_gld()._ins(2))
        		._s(1)
        		;
        
        E2_Grid oGridMainUser = new E2_Grid()._setSize(150)
        		._a(new RB_lab()._tr("Laufzettel mit Beteiligung von")._fsa(-1)._i(), new RB_gld()._ins(2))
        		._a(oSelFactoryForUser.get_oComponentWithoutText())
        		;
        
        E2_Grid oGridUsers = new E2_Grid()._setSize(400)
        		._a(oSelUsers.get_oComponentForSelection(),new RB_gld())
        		;
        
        E2_Grid oGridTimespan = new E2_Grid()._setSize(150)
        		._a(new RB_lab()._tr("Offene Aufgaben mit Fälligkeit ab jetzt in: ")._fsa(-1)._i())
        		._a(sel_betrachtungszeitraum.get_oComponentForSelection())
        		;
        
        E2_Grid oGridSelBase = new E2_Grid();
        
        oGridSelBase
        	._a( oGridMainUser , new RB_gld()._ins(2,2,2,10) )
        	._a( oGridUsers    , new RB_gld()._span(3)._ins(2,0,2,10) )
        	._a( oGridTimespan , new RB_gld()._ins(10,2,2,10) )
        	._a( oGridStatus   , new RB_gld()._ins(10,2,2,10) )
        	._s( 6 )	;
        

        this.add(oGridSelBase, E2_INSETS.I_0_0_0_0);
        
//        MyE2_Grid oGridInnen = new MyE2_Grid(4,0);
//        this.add(oGridInnen, E2_INSETS.I_4_4_4_4);
//        
//        
////        oGridInnen.add(sel_geaendert_von.get_oComponentForSelection());
//        // Bearbeiter-Auswahl
//        oGridInnen.add(oSelFactoryForUser.get_oComponentForSelection());
//
//        // Betrachtungszeitraum in die Zukunft
//        oGridInnen.add(sel_betrachtungszeitruam.get_oComponentForSelection());
//        
//        // Abgeschlossen-Auswahl
//        oGridInnen.add(btnSelektorAbgeschlossen);
//        oGridInnen.add(btnSelDel);
//        
//        // Benutzer selektieren
//        oGridInnen.add(oSelUsers.get_oComponentForSelection());
        
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
            
            And  bed = new And(LAUFZETTEL_EINTRAG.geaendert_von,"'#WERT#'");
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
    
    
    
//	//2011-11-31: eigener Selektor fuer den Beobachtungszeitraum
	private class selektorBetrachtungszeitraum extends XX_ListSelektor
	{

		private WF_SIMPLE_LIST_SelectField_Zeitraum  oSelZeitraum = null;
		private String[][] 		 				   arrZeitraum = null;
		
		public selektorBetrachtungszeitraum() throws myException
		{
			super();
			// Zeitraum in die Zukunft
			arrZeitraum = new String[][]{
					{new MyE2_String("1 Tag").CTrans(),"1"},
					{new MyE2_String("2 Tage").CTrans(),"2"},
					{new MyE2_String("3 Tage").CTrans(),"3"},
					{new MyE2_String("4 Tage").CTrans(),"4"},
					{new MyE2_String("5 Tage").CTrans(),"5"},
					{new MyE2_String("6 Tage").CTrans(),"6"},
					{new MyE2_String("7 Tage").CTrans(),"7"},
					{new MyE2_String("2 Wochen").CTrans(),"14"},
					{new MyE2_String("3 Wochen").CTrans(),"21"},
					{new MyE2_String("4 Wochen").CTrans(),"28"},
					{new MyE2_String("5 Wochen").CTrans(),"35"},
					{new MyE2_String("10 Wochen").CTrans(),"70"},
					{new MyE2_String("20 Wochen").CTrans(),"140"},
					{new MyE2_String("30 Wochen").CTrans(),"210"},
					{new MyE2_String("1 Jahr").CTrans(),"365"},
					{new MyE2_String("unbegrenzt").CTrans(),"999999"},
					};
			this.oSelZeitraum = new WF_SIMPLE_LIST_SelectField_Zeitraum(arrZeitraum, null, false);
			this.oSelZeitraum.set_ActiveInhalt_or_FirstInhalt( new MyE2_String("2 Wochen").CTrans() );
			this.oSelZeitraum.setWidth(new Extent(150));
		}

		@Override
		public String get_WhereBlock() throws myException
		{
			String cWert = this.oSelZeitraum.get_ActualWert();
			
			String cWhereBlockRueck = " NVL(JT_LAUFZETTEL_EINTRAG.FAELLIG_AM,SYSDATE) <= (SYSDATE +"+cWert+") ";
			return cWhereBlockRueck;
		}
		

		@Override
		public Component get_oComponentForSelection()
		{
			MyE2_Grid oGridRueck = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
			oGridRueck.add(this.oSelZeitraum);
			return oGridRueck;
		}

		
		@Override
		public Component get_oComponentWithoutText()
		{
			return this.get_oComponentForSelection();
		}

		@Override
		public void add_ActionAgentToComponent(XX_ActionAgent oAgent)
		{
			this.oSelZeitraum.add_oActionAgent(oAgent);
		}

		@Override
		public void doInternalCheck()
		{
			
		}
		
	}
    
    
    
    
}
 
 
