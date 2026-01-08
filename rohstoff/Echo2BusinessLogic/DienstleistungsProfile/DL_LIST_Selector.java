 
package rohstoff.Echo2BusinessLogic.DienstleistungsProfile;
  
import java.math.BigDecimal;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown2;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorStandard;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelector_DB_Defined;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.USER_SELECTOR_GENERATOR_NT_KUERZEL;
import panter.gmbh.Echo2.components.E2_ExpandableRow;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.ENUM_USER_TYP;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.DLP_PROFIL;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.HMAP;
import panter.gmbh.indep.myVectors.VEK;
public class DL_LIST_Selector extends E2_ExpandableRow {
    
    /*
     * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
     */
    
    private E2_SelectionComponentsVector     oSelVector = null;
    
    //zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   	m_tpHashMap = null;
    private E2_Grid 				gridInnen = 	new E2_Grid()._s(8);
    
    
    public DL_LIST_Selector(RB_TransportHashMap  p_tpHashMap) throws myException
    {
        super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
        
        this.m_tpHashMap = p_tpHashMap;       
        this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SELECTOR,this);
        
        this.oSelVector = new E2_SelectionComponentsVector(this.m_tpHashMap.getNavigationList());
        this.rebuild();
        
    }
    
    
    private void rebuild() throws myException {
    	
    	gridInnen._clear();
    	
        this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SELECTIONCOMPONENTVECTOR,this.oSelVector );
        
        // selektoren definieren
        // freier selektor
        E2_ListSelector_DB_Defined 			oDB_BasedSelektor =  		new E2_ListSelector_DB_Defined(E2_MODULNAME_ENUM.MODUL.DIENSTLEISTUNG_PROFIL_LIST.get_callKey());

        ownSelectorGeaendertVon 			sel_geaendert_von = 		new ownSelectorGeaendertVon();
        ownSelectorFirmen 					adresse_start=	 			new ownSelectorFirmen(DLP_PROFIL.id_adresse_start);
        ownSelectorFirmen 					adresse_ziel=	 			new ownSelectorFirmen(DLP_PROFIL.id_adresse_ziel);
        ownSelectorFirmen 					adresse_fremd=	 			new ownSelectorFirmen(DLP_PROFIL.id_adresse_fremdware);
        ownSelectorFirmen 					adresse_abrechung=	 		new ownSelectorFirmen(DLP_PROFIL.id_adresse_rechnung);
        
        ownSelectorArtikelBez 				artbezQuelle = 				new ownSelectorArtikelBez(DLP_PROFIL.id_artikel_bez_quelle);
        ownSelectorArtikelBez 				artbezZiel = 				new ownSelectorArtikelBez(DLP_PROFIL.id_artikel_bez_ziel);
        ownSelectorArtikelBez 				artbezDl = 					new ownSelectorArtikelBez(DLP_PROFIL.id_artikel_bez_dl);

        ownSelector_KALKULATION             selKalulationstyp = 		new ownSelector_KALKULATION();

        
        this.oSelVector.add(oDB_BasedSelektor);
        
        this.oSelVector.add(sel_geaendert_von);
        this.oSelVector.add(adresse_start);
        this.oSelVector.add(adresse_ziel);
        this.oSelVector.add(adresse_fremd);
        this.oSelVector.add(artbezQuelle);
        this.oSelVector.add(artbezZiel);
        this.oSelVector.add(artbezDl);
        this.oSelVector.add(selKalulationstyp);
        
		
		this.add(gridInnen, E2_INSETS.I_4_4_4_4);
		
		gridInnen._a(S.ms("Startadresse"));
		gridInnen._a(adresse_start.get_oComponentWithoutText(), new RB_gld()._ins(5, 2, 20, 2));

		gridInnen._a(S.ms("Zieladresse"));
		gridInnen._a(adresse_ziel.get_oComponentWithoutText(), new RB_gld()._ins(5, 2, 20, 2));
		
		gridInnen._a(S.ms("Fremdbesitz"));
		gridInnen._a(adresse_fremd.get_oComponentWithoutText(), new RB_gld()._ins(5, 2, 20, 2));

		gridInnen._a(S.ms("Rechnungsempf."));
		gridInnen._a(adresse_abrechung.get_oComponentWithoutText(), new RB_gld()._ins(5, 2, 20, 2));

		
		gridInnen._a(S.ms("Artikelbez. (Quelle)"));
		gridInnen._a(artbezQuelle.get_oComponentWithoutText(), new RB_gld()._ins(5, 2, 20, 2));
		
		gridInnen._a(S.ms("Artikelbez. (Ziel)"));
		gridInnen._a(artbezZiel.get_oComponentWithoutText(), new RB_gld()._ins(5, 2, 20, 2));
		
		gridInnen._a(S.ms("Artikelbez. (Abrech)"));
		gridInnen._a(artbezDl.get_oComponentWithoutText(), new RB_gld()._ins(5, 2, 20, 2));

		gridInnen._a(S.ms("Letzter Bearbeiter"));
		gridInnen._a(sel_geaendert_von.get_oComponentWithoutText(), new RB_gld()._ins(5, 2, 20, 2));

		gridInnen._a(S.ms("Diverse:"));
		gridInnen._a(oDB_BasedSelektor.get_oComponentForSelection(null,100), new RB_gld()._ins(5, 2, 20, 2));
		
		gridInnen._a(S.ms("Abrechnungstyp"));
		gridInnen._a(selKalulationstyp.get_oComponentWithoutText(), new RB_gld()._ins(5, 2, 20, 2));
		
		gridInnen._a();
		gridInnen._a();

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
            
            And  bed = new And(DLP_PROFIL.geaendert_von,"'#WERT#'");
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
            gridHelp.add(new MyE2_Label(new MyE2_String("Geändert von")), E2_INSETS.I(0,2,10,2));
            gridHelp.add(this.get_oComponentWithoutText(), E2_INSETS.I(0,2,10,2));
            return gridHelp;
        }
        
    }
    
    
    
    /**
     * selektor fuer die auswahl von modulen ....
     * @author martin
     *
     */
    private class ownSelectorFirmen extends E2_ListSelectorMultiDropDown2 {
        public ownSelectorFirmen(IF_Field field) throws myException {
            super();
            
            
            SEL firmen = new SEL(ADRESSE.id_adresse,ADRESSE.name1,ADRESSE.ort).FROM(_TAB.adresse)
            				.INNERJOIN(_TAB.dlp_profil, field,ADRESSE.id_adresse)
            				.ADD_Distinct()
            				.ORDERUP(ADRESSE.name1)
            				; 
            
            VEK<Object[]> result = bibDB.getResultLines(new SqlStringExtended(firmen.s()), true);

            String[][] selArr = null;
            if (result!=null && result.size()!=0) {
            	selArr = new String[result.size()][2];
            	int i = 0;
            	for (Object[] o: result) {
            		selArr[i]  [0]=S.NN((String)o[1],"")+" "+S.NN((String)o[2],"");
            		selArr[i++][1]=(""+((BigDecimal)o[0]).longValue());
            	}
            }
            selArr = bibARR.add_array_inFront2(selArr, bibARR.ARR_EMPTY_DB_VALUE_IN_FRONT);
            
            MyE2_SelectField  selFirmen = new MyE2_SelectField(selArr,"", false);    
            And  bed = new And(field,"#WERT#");
            this.INIT(selFirmen, bed.s(), null);
            
            this.set_extOfSelectComponentDropDown(new Extent(200));

            
        }
        
        
        @Override
        public E2_BasicModuleContainer get_PopupContainer() throws myException {
            return new ownBasicContainer();
        }
        
        private class ownBasicContainer extends E2_BasicModuleContainer {}
        
        @Override
        public Component get_oComponentForSelection() throws myException {
            int[] breite = {100,200};
            MyE2_Grid  gridHelp = new MyE2_Grid(breite, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
            
            gridHelp.add(new MyE2_Label(new MyE2_String("Lieferant/Abnehmer")), E2_INSETS.I(0,2,10,2));
            gridHelp.add(this.get_oComponentWithoutText(), E2_INSETS.I(0,2,10,2));
            return gridHelp;
        }
        
    }
    
    
    /**
     * selektor fuer die auswahl von modulen ....
     * @author martin
     *
     */
    private class ownSelectorArtikelBez extends E2_ListSelectorMultiDropDown2 {
    	
    	
        private IF_Field artikelBez;
		public ownSelectorArtikelBez(IF_Field artikelBez) throws myException {
            super();
			this.artikelBez = artikelBez;
            
            
            //sucht alle dienstleistungssorten, die benutzt werden in den profilen (fuer das selektor-Dropdown)
            SEL artikelDL = new SEL(ARTIKEL.id_artikel,ARTIKEL.anr1,ARTIKEL.artbez1).FROM(_TAB.artikel)
            				.INNERJOIN(_TAB.artikel_bez, ARTIKEL.id_artikel,ARTIKEL_BEZ.id_artikel)
            				.INNERJOIN(_TAB.dlp_profil, artikelBez,ARTIKEL_BEZ.id_artikel_bez)
            				.ADD_Distinct()
            				.ORDERUP(ARTIKEL.artbez1)
            				; 
            
            SEL selForSelection = new SEL(ARTIKEL_BEZ.id_artikel_bez).FROM(_TAB.artikel_bez).WHERE(new TermSimple(ARTIKEL_BEZ.id_artikel.tnfn()+"=#WERT#"));
            String whereBlockForSelection = artikelBez.tnfn()+" IN ("+selForSelection.s()+")";
            
            
            VEK<Object[]> result = bibDB.getResultLines(new SqlStringExtended(artikelDL.s()), true);

            String[][] selArr = null;
            if (result!=null && result.size()!=0) {
            	selArr = new String[result.size()][2];
            	int i = 0;
            	for (Object[] o: result) {
            		selArr[i]  [0]=S.NN((String)o[1],"")+" "+S.NN((String)o[2],"");
            		selArr[i++][1]=(""+((BigDecimal)o[0]).longValue());
            	}
            }
            selArr = bibARR.add_array_inFront2(selArr, bibARR.ARR_EMPTY_DB_VALUE_IN_FRONT);
            
            MyE2_SelectField  selFirmen = new MyE2_SelectField(selArr,"", false);    
            And  bed = new And(new TermSimple(whereBlockForSelection));
            this.INIT(selFirmen, bed.s(), null);
            
            this.set_extOfSelectComponentDropDown(new Extent(200));
        }
        
        
        @Override
        public E2_BasicModuleContainer get_PopupContainer() throws myException {
            return new ownBasicContainer();
        }
        
        private class ownBasicContainer extends E2_BasicModuleContainer {}
        @Override
        public Component get_oComponentForSelection() throws myException {
            int[] breite = {100,200};
            MyE2_Grid  gridHelp = new MyE2_Grid(breite, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
            
            gridHelp.add(new MyE2_Label(S.ms(DL_READABLE_FIELD_NAME.getReadable(artikelBez))), E2_INSETS.I(0,2,10,2));
            gridHelp.add(this.get_oComponentWithoutText(), E2_INSETS.I(0,2,10,2));
            return gridHelp;
        }
        
    }


    
    

    
	private class ownSelector_KALKULATION extends E2_ListSelectorStandard 	{
		public ownSelector_KALKULATION() throws myException {
			super(	new RB_selField()._populate(DL_ENUM_TYP_MENGEN_CALC.PRO_LADUNG,true)._setStyleFlat()._width(200)
					,new HMAP<String,String>()
							._put("", "")
							._put(DL_ENUM_TYP_MENGEN_CALC.PRO_LADUNG.db_val(), new vgl(DLP_PROFIL.typ_mengen_calc,DL_ENUM_TYP_MENGEN_CALC.PRO_LADUNG.db_val()).s())
							._put(DL_ENUM_TYP_MENGEN_CALC.PRO_MENGE.db_val(), new vgl(DLP_PROFIL.typ_mengen_calc,DL_ENUM_TYP_MENGEN_CALC.PRO_MENGE.db_val()).s())
	    			,null
					, new Integer(70));
		}
	}
    

    
    
}
 
 
