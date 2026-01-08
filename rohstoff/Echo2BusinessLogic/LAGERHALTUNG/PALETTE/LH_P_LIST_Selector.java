 
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE;
  
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown2;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelector_DB_Defined;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.USER_SELECTOR_GENERATOR_NT_KUERZEL;
import panter.gmbh.Echo2.components.E2_ExpandableRow;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.ENUM_USER_TYP;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_BOX;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_PALETTE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.TERM.VglNotNull;
import panter.gmbh.indep.dataTools.TERM.VglNull;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE.LH_P_CONST.TRANSLATOR;
public class LH_P_LIST_Selector extends E2_ExpandableRow {
    
    /*
     * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
     */
    
    private E2_SelectionComponentsVector     oSelVector = null;
    
    //zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   m_tpHashMap = null;
    
    
    public LH_P_LIST_Selector(RB_TransportHashMap  p_tpHashMap) throws myException
    {
        super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
        
        this.m_tpHashMap = p_tpHashMap;       
        this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SELECTOR,this);
        this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SELECTIONCOMPONENTVECTOR,this.oSelVector );

        this.oSelVector = new E2_SelectionComponentsVector(this.m_tpHashMap.getNavigationList());
        
        ownSelectorGeaendertVon 	sel_geaendert_von 	= new ownSelectorGeaendertVon();
        ownSelectorBoxNummer 		sel_boxnr 			= new ownSelectorBoxNummer();
        ownSelectorSorte 			sel_sorte 			= new ownSelectorSorte();
        ownSelectorFuhre			sel_fuhre 			= new ownSelectorFuhre();
        ownSelectorBuchungStatus 	sel_status 		= new ownSelectorBuchungStatus();
        ownSelectorKunde 			sel_kunde 		= new ownSelectorKunde();
        ownSelectorBuchungsnr		sel_buchungsnr	= new ownSelectorBuchungsnr();
        
        this.oSelVector.add(sel_geaendert_von);
        this.oSelVector.add(sel_status);
        this.oSelVector.add(sel_boxnr);
        this.oSelVector.add(sel_sorte);
        this.oSelVector.add(sel_fuhre);
        this.oSelVector.add(sel_kunde);
        this.oSelVector.add(sel_buchungsnr);
        
     // 2013-03-05: neuer db-gestuetzter listselektor
     	E2_ListSelector_DB_Defined oDB_BasedSelektor = new E2_ListSelector_DB_Defined(TRANSLATOR.LIST.get_modul().get_callKey());
     	this.oSelVector.add(oDB_BasedSelektor);
        
		MyE2_Row oRowPassivschalter = new MyE2_Row();
		oRowPassivschalter.add(oSelVector.get_AktivPassivComponent());

        
        
        E2_Grid oGridInnen = new E2_Grid()._setSize(350,300,400,100,100)._bo_no();
       
        this.add(oGridInnen, E2_INSETS.I_4_4_4_4);
        
        oGridInnen
        ._a(sel_geaendert_von.get_oComponentForSelection(), new RB_gld()._left_top()._ins(1))
        ._a(sel_kunde.get_oComponentForSelection(), 		new RB_gld()._left_top()._ins(1))
        ._a(sel_fuhre.get_oComponentForSelection(), 		new RB_gld()._left_top()._ins(1))
        ._a(sel_buchungsnr.get_oComponentForSelection(), 	new RB_gld()._left_top()._ins(1)._span(2))
        
        ._a(sel_boxnr.get_oComponentForSelection(), 		new RB_gld()._left_top()._ins(1))
        ._a(sel_sorte.get_oComponentForSelection(), 		new RB_gld()._left_top()._ins(1))
        ._a(sel_status.get_oComponentForSelection(), 		new RB_gld()._left_top()._ins(10,1,1,1))
        ._a(oRowPassivschalter ,							new RB_gld()._left_bottom()._ins(10,1,1,1))
        ._a(oDB_BasedSelektor.get_oComponentForSelection(100), new RB_gld()._left_bottom()._ins(10,1,1,1))
        ;
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
            
            And  bed = new And(LAGER_PALETTE.geaendert_von,"'#WERT#'");
            this.INIT(selFieldKenner, bed.s(), null);
            
            this.set_extOfSelectComponentDropDown(new Extent(175));
        }
        @Override
        public E2_BasicModuleContainer get_PopupContainer() throws myException {
            return new ownBasicContainer();
        }
        
        private class ownBasicContainer extends E2_BasicModuleContainer {}
        @Override
        public Component get_oComponentForSelection() throws myException {
            E2_Grid  gridHelp = new E2_Grid()._setSize(100,100)._bo_no()._ins(E2_INSETS.I_0_0_0_0);
            gridHelp._a(new RB_lab("Geändert von:"),			new RB_gld()._ins(0,2,10,2)._left_mid());
            gridHelp._a(this.get_oComponentWithoutText(), 	new RB_gld()._ins(0,2,10,2)._left_mid());
            return gridHelp;
        }
        
    }
    
    private class ownSelectorBoxNummer extends E2_ListSelectorMultiDropDown2 {
        public ownSelectorBoxNummer() throws myException {
            super();
            
            String lagerBoxAbfrage = new SEL()
            		.ADDFIELD(LAGER_BOX.boxnummer.tnfn() + "|| (case when(NVL(JT_LAGER_BOX.IS_DEFAULT_BOX,'N')='Y')then ' *' end)", "BOXNUMMER")
            		.ADDFIELD(LAGER_BOX.id_lager_box)
            		.FROM(_TAB.lager_box)
            		.ORDERDOWN("NVL("+LAGER_BOX.is_default_box.tnfn()+",'N')")
            		.ORDERUP(LAGER_BOX.id_lager_box.tnfn())
            		.s();
          
            String[][] BoxNrArray = bibDB.EinzelAbfrageInArray(lagerBoxAbfrage);
            if(BoxNrArray == null) {
            	BoxNrArray = new String[0][2];
            }
            BoxNrArray =  bibARR.add_emtpy_db_value_inFront(BoxNrArray);
            
            MyE2_SelectField  selFieldKenner = new MyE2_SelectField(BoxNrArray, "", false);
            
            And  bed = new And("NVL("+LAGER_PALETTE.id_lager_box+",'0')","'#WERT#'");
            
            this.INIT(selFieldKenner, bed.s(), null);
            this.set_extOfSelectComponentDropDown(new Extent(175));
        }
        @Override
        public E2_BasicModuleContainer get_PopupContainer() throws myException {
            return new ownBasicContainer();
        }
        
        private class ownBasicContainer extends E2_BasicModuleContainer {}
        @Override
        public Component get_oComponentForSelection() throws myException {
            E2_Grid  gridHelp = new E2_Grid()._setSize(100,100)._bo_no();
            
            gridHelp._a(new RB_lab("Boxnummer:"), 			new RB_gld()._ins(0,2,10,2)._left_mid());
            gridHelp._a(this.get_oComponentWithoutText(), 	new RB_gld()._ins(0,2,10,2)._left_mid());
            return gridHelp;
        }
    }
    
    private class ownSelectorSorte extends E2_ListSelectorMultiDropDown2 {
        public ownSelectorSorte() throws myException {
            super();
           
           String sorte_abfrage = 
        	new SEL()
        	.ADDFIELD("DISTINCT(''||NVL("+ARTIKEL.anr1.tnfn()+",' ')||'-'||NVL("+ARTIKEL_BEZ.anr2.tnfn()+",'')||' '||"+ARTIKEL_BEZ.artbez1.tnfn()+")", "ART_SORTE") 
           .ADDFIELD(ARTIKEL_BEZ.id_artikel_bez.tnfn())
           .FROM(_TAB.lager_palette)
           .INNERJOIN(_TAB.artikel_bez, ARTIKEL_BEZ.id_artikel_bez.tnfn(), LAGER_PALETTE.id_artikel_bez.tnfn())
           .INNERJOIN(_TAB.artikel, ARTIKEL.id_artikel.tnfn(), ARTIKEL_BEZ.id_artikel.tnfn())
           .ORDER("1")
           .s();
            MyE2_SelectField  selFieldKenner = new MyE2_SelectField(sorte_abfrage, false, true, false,false, new Extent(250));    
            
            And  bed = new And(LAGER_PALETTE.id_artikel_bez,"'#WERT#'");
            this.INIT(selFieldKenner, bed.s(), null);
            
            this.set_extOfSelectComponentDropDown(new Extent(200));
        }
        @Override
        public E2_BasicModuleContainer get_PopupContainer() throws myException {
            return new ownBasicContainer();
        }
        
        private class ownBasicContainer extends E2_BasicModuleContainer {}
        @Override
        public Component get_oComponentForSelection() throws myException {
            E2_Grid  gridHelp = new E2_Grid()._setSize(100,300)._bo_no()._ins(E2_INSETS.I_0_0_0_0);
            gridHelp._a(new RB_lab()._t("Material:"),		new RB_gld()._ins(20,2,10,2)._left_mid());
            gridHelp._a(this.get_oComponentWithoutText(), 	new RB_gld()._ins(0,2,10,2)._left_mid());
            return gridHelp;
        }
        
    }
    
    private class ownSelectorFuhre extends E2_ListSelectorMultiDropDown2 {
        public ownSelectorFuhre() throws myException {
            super();
           
           String sorte_abfrage = new SEL("DISTINCT("+LAGER_PALETTE.id_vpos_tpa_fuhre_ein+")")
        		   .ADDFIELD(LAGER_PALETTE.id_vpos_tpa_fuhre_ein)
           .FROM(_TAB.lager_palette)
           .WHERE(new VglNull(LAGER_PALETTE.id_vpos_tpa_fuhre_aus))
           .AND(new vgl_YN(LAGER_PALETTE.ausbuchung_hand,false))
           .AND(new VglNotNull(LAGER_PALETTE.id_vpos_tpa_fuhre_ein))
           .ORDER("1")
           .s();
            
            MyE2_SelectField  selFieldKenner = new MyE2_SelectField(sorte_abfrage, false, true, false,false, new Extent(250));    
            
            And  bed = new And(LAGER_PALETTE.id_vpos_tpa_fuhre_ein,"'#WERT#'");
            this.INIT(selFieldKenner, bed.s(), null);
            
            this.set_extOfSelectComponentDropDown(new Extent(200));
        }
        
        @Override
        public E2_BasicModuleContainer get_PopupContainer() throws myException {
            return new ownBasicContainer();
        }
        
        private class ownBasicContainer extends E2_BasicModuleContainer {}
        @Override
        public Component get_oComponentForSelection() throws myException {
            E2_Grid  gridHelp = new E2_Grid()._setSize(90,300)._bo_no()._ins(E2_INSETS.I_0_0_0_0);
            gridHelp._a(new RB_lab()._t("Fuhre ID:"),		new RB_gld()._ins(10,2,10,2)._left_mid());
            gridHelp._a(this.get_oComponentWithoutText(), 	new RB_gld()._ins(0,2,10,2)._left_mid());
            return gridHelp;
        }
    }
    
    private class ownSelectorBuchungStatus extends E2_ListSelektorMultiselektionStatusFeld_STD{
		
		public ownSelectorBuchungStatus() throws myException {
			super(new int[] {150,150}, true, S.ms("Paletten:") ,new Extent(80));
			
			this.ADD_STATUS_TO_Selector(true,
					(new And(new VglNotNull(LAGER_PALETTE.id_vpos_tpa_fuhre_ein))
							.and(new VglNull(LAGER_PALETTE.id_vpos_tpa_fuhre_aus))
							.and(new vgl_YN(LAGER_PALETTE.ausbuchung_hand, false))
							.s()) 
					,S.ms("Eingebucht")
					,S.ms("Zeige alle eingebuchten Paletten"));
			
			this.ADD_STATUS_TO_Selector(false, 
					(new And(new VglNotNull(LAGER_PALETTE.id_vpos_tpa_fuhre_aus))
							.and(new vgl_YN(LAGER_PALETTE.ausbuchung_hand, false)).s()), 	
					S.ms("Ausgebucht"), 
					S.ms("Zeige alle ausgebuchten Paletten"));
			
			this.ADD_STATUS_TO_Selector(true,
					(new And(new vgl_YN(LAGER_PALETTE.einbuchung_hand, true))
							.and(new VglNull(LAGER_PALETTE.id_vpos_tpa_fuhre_ein)) 
							.and(new VglNull(LAGER_PALETTE.id_vpos_tpa_fuhre_aus))
							.and(new vgl_YN(LAGER_PALETTE.ausbuchung_hand, false))).s()
					,S.ms("Hand Eingebucht")
					,S.ms("Zeige alle eingebuchten Paletten"));
			
			this.ADD_STATUS_TO_Selector(false,
					(new And(new vgl_YN(LAGER_PALETTE.ausbuchung_hand,true))
							.and(new VglNull(LAGER_PALETTE.id_vpos_tpa_fuhre_aus))).s(), 	
					S.ms("Hand Ausgebucht"), 
					S.ms("Zeige alle ausgebuchten Paletten"));
//			this.ADD_STATUS_TO_Selector(false, (LAGER_PALETTE_BOX.ausbuchung_am.tnfn()+" IS NOT NULL AND "+	LAGER_PALETTE.id_vpos_tpa_fuhre_aus.tnfn() +" IS NULL"), 		S.ms("Box änderung"), S.ms("Zeige alle ausgebucht Palette"));

		}
    }
    
    private class ownSelectorKunde extends E2_ListSelectorMultiDropDown2{
		public ownSelectorKunde() throws myException {
			super();
			
			SEL fuellungAbfrage = new SEL()
			.ADDFIELD("DISTINCT(" + ADRESSE.name1.tnfn() 
			+ "||' '|| + NVL("+ADRESSE.name2.tnfn()+",'')||' - '||"+ADRESSE.ort.tnfn() +" ||' ('||" +ADRESSE.id_adresse.tnfn()+"||')')", "FNAME")
			.ADDFIELD(ADRESSE.id_adresse)
			.FROM(_TAB.adresse)
			.INNERJOIN(_TAB.vpos_tpa_fuhre, ADRESSE.id_adresse, 			VPOS_TPA_FUHRE.id_adresse_start)
			.INNERJOIN(_TAB.lager_palette, 	LAGER_PALETTE.id_vpos_tpa_fuhre_ein,VPOS_TPA_FUHRE.id_vpos_tpa_fuhre)
			.WHERE(new VglNull(LAGER_PALETTE.id_vpos_tpa_fuhre_aus))
			.ORDER("FNAME")
			;
			
			MyE2_SelectField  selFieldKenner = new MyE2_SelectField(fuellungAbfrage.s(), false, true, false, false);
			
			SEL subselect = 
					new SEL(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre)
					.FROM(_TAB.vpos_tpa_fuhre)
					.WHERE(VPOS_TPA_FUHRE.id_adresse_start,COMP.EQ.ausdruck(), "#WERT#");
			
			And bed = new And(LAGER_PALETTE.id_vpos_tpa_fuhre_ein,COMP.IN.ausdruck(), "(" + subselect.s() + ")");
			
			this.INIT(selFieldKenner, bed.s(),null);
			
			this.set_extOfSelectComponentDropDown(new Extent(200));
		}
    	
    	@Override
         public E2_BasicModuleContainer get_PopupContainer() throws myException {
             return new ownBasicContainer();
         }
         
         private class ownBasicContainer extends E2_BasicModuleContainer {}
         @Override
         public Component get_oComponentForSelection() throws myException {
             E2_Grid  gridHelp = new E2_Grid()._setSize(100,200)._bo_no()._ins(E2_INSETS.I_0_0_0_0)
             
             ._a(new RB_lab()._t("Kunde:"),			new RB_gld()._ins(20,2,10,2)._left_mid())
             ._a(this.get_oComponentWithoutText(), 	new RB_gld()._ins(0,2,10,2)._left_mid());
             return gridHelp;
             
         }
    }
    
    private class ownSelectorBuchungsnr extends E2_ListSelectorMultiDropDown2 {
        public ownSelectorBuchungsnr() throws myException {
            super();

            SEL fuellungAbfrage1 = new SEL().ADD_Distinct()
        			.ADDFIELD(VPOS_TPA_FUHRE.buchungsnr_fuhre)
        			.ADDFIELD(VPOS_TPA_FUHRE.buchungsnr_fuhre)
        			.FROM(_TAB.vpos_tpa_fuhre)
        			.INNERJOIN(_TAB.lager_palette, 	LAGER_PALETTE.id_vpos_tpa_fuhre_ein,VPOS_TPA_FUHRE.id_vpos_tpa_fuhre)
        			.WHERE(new VglNull(LAGER_PALETTE.id_vpos_tpa_fuhre_aus))
        			.AND(new VglNotNull(VPOS_TPA_FUHRE.buchungsnr_fuhre))
        			;
           
            SEL fuellungAbfrage2 = new SEL().ADD_Distinct()
            		.ADDFIELD(LAGER_PALETTE.buchungsnr_hand)
            		.ADDFIELD(LAGER_PALETTE.buchungsnr_hand)
            		.FROM(_TAB.lager_palette)
            		.WHERE(new VglNull(LAGER_PALETTE.id_vpos_tpa_fuhre_aus))
            		.AND(new VglNotNull(LAGER_PALETTE.buchungsnr_hand))
            		.ORDER("1")
            		;
           /*String sorte_abfrage = new SEL("DISTINCT("+LAGER_PALETTE.id_vpos_tpa_fuhre_ein+")")
        		   .ADDFIELD(LAGER_PALETTE.id_vpos_tpa_fuhre_ein)
           .FROM(_TAB.lager_palette)
           .WHERE(new VglNotNull(LAGER_PALETTE.id_vpos_tpa_fuhre_ein))
           .AND(new VglNull(LAGER_PALETTE.id_vpos_tpa_fuhre_aus))
           .s();*/
            
            MyE2_SelectField  selFieldKenner = new MyE2_SelectField(fuellungAbfrage1.s() + " UNION " + fuellungAbfrage2.s(), false, true, false,false, new Extent(250));    
            
            And  bed = new And(VPOS_TPA_FUHRE.buchungsnr_fuhre.tnfn(),"'#WERT#'").or(LAGER_PALETTE.buchungsnr_hand.tnfn(), "'#WERT#'");
            this.INIT(selFieldKenner, bed.s(), null);
            
            this.set_extOfSelectComponentDropDown(new Extent(150));
        }
        
        @Override
        public E2_BasicModuleContainer get_PopupContainer() throws myException {
            return new ownBasicContainer();
        }
        
        private class ownBasicContainer extends E2_BasicModuleContainer {}
        @Override
        public Component get_oComponentForSelection() throws myException {
            E2_Grid  gridHelp = new E2_Grid()._setSize(75,300)._bo_no()._ins(E2_INSETS.I_0_0_0_0);
            gridHelp._a(new RB_lab()._t("Buchungsnr:"),		new RB_gld()._ins(10,2,10,2)._right_mid());
            gridHelp._a(this.get_oComponentWithoutText(), 	new RB_gld()._ins(0,2,10,2)._left_mid());
            return gridHelp;
        }
    }
}
 
 
