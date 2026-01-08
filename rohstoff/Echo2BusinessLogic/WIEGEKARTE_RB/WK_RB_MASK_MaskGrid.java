 
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB;
  
import echopointng.Separator;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_BEFUND;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_Popup_ContainerAbsetzGrund;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_Popup_Kennzeichen;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_Popup_Kennzeichen_Trailer;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_cb_Adresse_Hand;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_cb_Adresse_Spedition_Hand;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_cb_LagerplatzAbsetz;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_cb_LagerplatzSchuett;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_cb_Mehrfachverwiegung;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_tfHinweisAllgemein;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_tfSortenHinweis;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWiegekarteBefund;
 
public class WK_RB_MASK_MaskGrid extends E2_Grid {
 	
    //zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   m_tpHashMap = null;
    
    //wird benutzt, falls mehr als ein E2_Grid verwendung findet
    private MyE2_TabbedPane  ta  = new MyE2_TabbedPane(600);
    
	/*
	 * vector nimmt alle container auf, die reale felder enthalten
	 * und die zugehoerigen tab-texte (falls mehr als ein container noetig ist) 
	 */
    
	private VEK<E2_Grid>   fieldContainers = 	new VEK<E2_Grid>();
	
	private VEK<MyString>  tabText = 			new VEK<MyString>();

	private WK_RB_MASK_ComponentMap_Wiegekarte mapWK;
	private WK_RB_MASK_ComponentMap_Befund mapBefund;

	private MyE2_TabbedPane tabbedPaneAbzuege;

	
    public WK_RB_MASK_MaskGrid(RB_TransportHashMap  p_tpHashMap) throws myException {
        super();
        
        int iWidthComplete = WK_RB_CONST.WK_RB_NUM_CONST.MASKPOPUP_DESCRIPTION_COL_SIZE.getValue()+
                                  WK_RB_CONST.WK_RB_NUM_CONST.MASKPOPUP_FIELD_COL_SIZE.getValue()+20;
//        this._setSize(iWidthComplete)._bo_no();
        // Äußeres Grid auf 100%
        this._setSize(new Extent(100, Extent.PERCENT));
 
        this.m_tpHashMap = p_tpHashMap;
        this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MASK_GRID,this);
        
        mapWK = (WK_RB_MASK_ComponentMap_Wiegekarte) this.m_tpHashMap.getMaskComponentMapCollector().get(this.m_tpHashMap.getLeadingMaskKey());
        mapBefund = (WK_RB_MASK_ComponentMap_Befund) 	this.m_tpHashMap.getMaskComponentMapCollector().get(RecDOWiegekarteBefund.key);

        
        Insets _i_grid = new Insets(2, 2, 2, 2);
        
        //beginn erster tab
        E2_Grid _grid_Main = fieldContainers._ar( new E2_Grid()._setSize(
        		WK_RB_CONST.WK_RB_NUM_CONST.MASK_COL_1.getValue(),
				WK_RB_CONST.WK_RB_NUM_CONST.MASK_COL_2.getValue(),
				WK_RB_CONST.WK_RB_NUM_CONST.MASK_COL_3.getValue(),
				WK_RB_CONST.WK_RB_NUM_CONST.MASK_COL_4.getValue(),
				WK_RB_CONST.WK_RB_NUM_CONST.MASK_COL_5.getValue(),
				WK_RB_CONST.WK_RB_NUM_CONST.MASK_COL_6.getValue(),
				WK_RB_CONST.WK_RB_NUM_CONST.MASK_COL_7.getValue(),
				WK_RB_CONST.WK_RB_NUM_CONST.MASK_COL_8.getValue(),
				WK_RB_CONST.WK_RB_NUM_CONST.MASK_COL_9.getValue(),
				WK_RB_CONST.WK_RB_NUM_CONST.MASK_COL_10.getValue(),
				WK_RB_CONST.WK_RB_NUM_CONST.MASK_COL_11.getValue(),
				WK_RB_CONST.WK_RB_NUM_CONST.MASK_COL_12.getValue(),
				WK_RB_CONST.WK_RB_NUM_CONST.MASK_COL_13.getValue(),
				WK_RB_CONST.WK_RB_NUM_CONST.MASK_COL_14.getValue(),
				WK_RB_CONST.WK_RB_NUM_CONST.MASK_COL_15.getValue()
        		) );

        E2_Grid _grid_Right = new E2_Grid()._setSize(new Extent(100, Extent.PERCENT))._bc(Color.LIGHTGRAY);
//        _grid_Right._a(new RB_lab(new MyString("<GRID RECHTS>").CTrans())._fo_s_plus(20),new RB_gld()._ins(_i_grid)._left_mid());
        
        tabbedPaneAbzuege = new MyE2_TabbedPane(null);
    	tabbedPaneAbzuege.setWidth(new Extent(600,Extent.PX));
    	tabbedPaneAbzuege.setHeight(new Extent(350,Extent.PX));
    	tabbedPaneAbzuege.setBackground(new E2_ColorBase());
    	_grid_Right._a(tabbedPaneAbzuege, new RB_gld()._col_back_base()._center_top());
    	// Tab-Panes aufbauen..
    	_initTabPanes();

    	
    	// Layout Hilfe
    	if (1==0) {
    		_grid_Main._a("1")._a("2")._a("3")._a("4")._a("5")._a("6")._a("7")._a("8")._a("9")._a("10")._a("11")._a("12")._a("13")._a("14")._a("15");
    		_grid_Main._bo_green();
    	}
    	
        _grid_Main	._a(new RB_lab(new MyE2_String("Wiegekarte: "))._b()._fo_s_plus(3)	, new RB_gld()._left_mid() )
					._a(mapWK.getComp(WIEGEKARTE.lfd_nr)								, new RB_gld()._left_mid() ._span(2) )
					._a(mapWK.getComp(WIEGEKARTE.storno) 								, new RB_gld()._left_mid())
					._a(new RB_lab(new MyE2_String(""))									, new RB_gld()._right_mid()._span(3))
					._a(new RB_lab(new MyE2_String("IdLager: "))						, new RB_gld()._right_mid())
        			._a(mapWK.getComp(WIEGEKARTE.id_adresse_lager)						, new RB_gld()._left_mid()._ins(10, 0,0,0))
        			._a(new RB_lab(new MyE2_String("IdWaageStandort: "))				, new RB_gld()._right_mid())
        			._a(mapWK.getComp(WIEGEKARTE.id_waage_standort)						, new RB_gld()._left_mid()._ins(10, 0,0,0))
					._a(new RB_lab(new MyE2_String("Gedruckt am: "))					, new RB_gld()._right_mid())
        			._a(mapWK.getComp(WIEGEKARTE.gedruckt_am)							, new RB_gld()._right_mid())
        			._a(new RB_lab(new MyE2_String("ID-Wiegekarte: "))					, new RB_gld()._right_mid())
        			._a(mapWK.getComp(WIEGEKARTE.id_wiegekarte)							, new RB_gld()._right_mid())
        			;
        _grid_Main	._a(new RB_lab(new MyE2_String("EingangsscheinNr: "))				, new RB_gld()._left_mid())
					._a(mapWK.getComp(WIEGEKARTE.es_nr)									, new RB_gld()._left_mid() ._span(2) )
					._a(new RB_lab(new MyE2_String("#"))								, new RB_gld()._right_mid()._span(9))
					._a(mapWK.getComp(WIEGEKARTE.druckzaehler)							, new RB_gld()._right_mid())
					._a(new RB_lab(new MyE2_String("ID-Parent: "))						, new RB_gld()._right_mid())
					._a(mapWK.getComp(WIEGEKARTE.id_wiegekarte_parent)					, new RB_gld()._right_mid())
					;
        			
        			
        // Horizontaler Strich
        _grid_Main._a(new Separator(),new RB_gld()._span(15));
        
        // 2. Fuhrenauswahl
        
        _grid_Main	._a(new RB_lab(new MyE2_String("Fuhre: ")), new RB_gld()._ins(_i_grid)._left_mid())
        		  	._a(mapWK.getComp(WK_RB_CONST.MASK_KEYS_String.COMP_FUHRE.key()), new RB_gld()._span(8)._left_mid())
        			// 	Button Folgewägung in den Container
        		  	._a(mapWK.getComp(WK_RB_CONST.MASK_KEYS_String.BT_FOLGEWAEGUNG.key()), new RB_gld()._left_mid())
        			._endLine(new RB_gld()._ins(_i_grid)) 
					;
        // Horizontaler Strich
        _grid_Main	._a(new Separator(),new RB_gld()._span(15));

        _grid_Main	._a(new RB_lab("") 							, new RB_gld()._ins(_i_grid)._left_mid())
        			._a(mapWK.getComp(WIEGEKARTE.ist_lieferant)	, new RB_gld()._ins(_i_grid)._left_mid()._span(6))
        			._a(mapWK.getComp(WK_RB_tfHinweisAllgemein.key), new RB_gld()._ins(_i_grid)._left_mid()._span(8)) ;

        
        // Horizontaler Strich
        _grid_Main	._a(new Separator(),new RB_gld()._span(15));		
        
        // Wiegeprozess
        _grid_Main	._a(new RB_lab(new MyE2_String("Wiegeprozess: ")), new RB_gld()._ins(_i_grid)._left_mid())
		  			._a(mapWK.getComp(WIEGEKARTE.typ_wiegekarte), new RB_gld()._span(4)._left_mid())
		  			._a(mapWK.getComp(WK_RB_cb_Mehrfachverwiegung.key),new RB_gld()._span(9)._left_mid() )
		  			._endLine(new RB_gld()._ins(_i_grid));
        
        
        // Horizontaler Strich
        _grid_Main._a(new Separator(),new RB_gld()._span(15));		
        
        Component cPopUpKFZ 	= (Component) mapWK.get__Comp(WK_RB_Popup_Kennzeichen.key.get_HASHKEY());
        Component cPopUpTrailer = (Component) mapWK.get__Comp(WK_RB_Popup_Kennzeichen_Trailer.key.get_HASHKEY());

        E2_Grid grid_kennzeichen = new E2_Grid()._setSize(210,30,65,210,30)
    			._a(mapWK.getComp(WIEGEKARTE.kennzeichen), new RB_gld()._ins(2,2,2,2)._left_mid())
    			._a(cPopUpKFZ,new RB_gld()._ins(0)._left_mid())
    			
    			._a(new RB_lab(new MyString("Trailer").CTrans()),new RB_gld()._ins(_i_grid)._right_mid())
    			._a(mapWK.getComp(WIEGEKARTE.trailer), new RB_gld()._ins(2,2,2,2)._left_mid())
        		._a(cPopUpTrailer,new RB_gld()._ins(0)._left_mid());
        
        
        _grid_Main	._a(new RB_lab(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.kennzeichen)) ,new RB_gld()._ins(_i_grid)._left_mid())
        			._a(grid_kennzeichen,new RB_gld()._ins(0)._left_mid()._span(6))
//        			._a(cPopUpKFZ,new RB_gld()._ins(0)._left_mid())

        			
        			
        			// 
        			// RECHTES FELD
        			// 14 Zeilen span
        			._a(_grid_Right,new RB_gld()._ins(_i_grid)._left_top()._span(8)._span_r(13))
        			._endLine(new RB_gld()._ins(_i_grid));
        			;
        
		_grid_Main	._a(new RB_lab(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.id_adresse_lieferant)) ,new RB_gld()._ins(_i_grid)._left_mid())
					._a(mapWK.getComp(WIEGEKARTE.id_adresse_lieferant), new RB_gld()._ins(_i_grid)._left_mid()._span(6))
					;
		
		_grid_Main	._a(mapWK.getComp(WK_RB_cb_Adresse_Hand.key),new RB_gld()._ins(_i_grid)._left_mid())
					._a(mapWK.getComp(WIEGEKARTE.adresse_lieferant), new RB_gld()._ins(_i_grid)._left_top()._span(5))
					._endLine(new RB_gld()._ins(_i_grid));

		_grid_Main	._a(new RB_lab("Sorte") ,new RB_gld()._ins(_i_grid)._left_mid())
					._a(mapWK.getComp(WK_RB_CONST.MASK_KEYS_String.SELECT_KUNDENARTIKEL.key()), new RB_gld()._ins(2,2,2,2)._left_mid()._span(6))
					;

		_grid_Main	._a(mapWK.getComp(WIEGEKARTE.sorte_hand) ,new RB_gld()._ins(_i_grid)._left_mid())
					._a(mapWK.getComp(WK_RB_CONST.MASK_KEYS_String.SEARCH_ARTIKELBEZ.key()), new RB_gld()._ins(_i_grid)._left_mid()._span(6))
					;
		
		_grid_Main	._a(new RB_lab("") ,new RB_gld()._ins(_i_grid)._left_mid())
					._a(mapWK.getComp(WK_RB_tfSortenHinweis.key), new RB_gld()._ins(2,2,2,2)._left_mid()._span(6))
					;
		
		
		
		_grid_Main	._a(new RB_lab(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.id_adresse_spedition)) ,new RB_gld()._ins(_i_grid)._left_mid())
					._a(mapWK.getComp(WIEGEKARTE.id_adresse_spedition), new RB_gld()._ins(_i_grid)._left_mid()._span(6));
		
		_grid_Main	._a(mapWK.getComp(WK_RB_cb_Adresse_Spedition_Hand.key) ,new RB_gld()._ins(_i_grid)._left_mid())
					._a(mapWK.getComp(WIEGEKARTE.adresse_spedition), new RB_gld()._ins(_i_grid)._left_mid()._span(6));

    	_grid_Main	._a(new RB_lab(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.id_adresse_abn_strecke)) ,new RB_gld()._ins(_i_grid)._left_mid())
    				._a(mapWK.getComp(WIEGEKARTE.id_adresse_abn_strecke), new RB_gld()._ins(_i_grid)._left_mid()._span(6));

    	_grid_Main	._a(new RB_lab(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.bemerkung1)) ,new RB_gld()._ins(_i_grid)._left_mid())
    				._a(mapWK.getComp(WIEGEKARTE.bemerkung1), new RB_gld()._ins(_i_grid)._left_mid()._span(6));

    	_grid_Main	._a(new RB_lab(new MyString("Eigen-Container-Nr").CTrans()) ,new RB_gld()._ins(_i_grid)._left_mid())
					._a(mapWK.getComp(WIEGEKARTE.id_container_eigen), new RB_gld()._ins(_i_grid)._left_mid()._span(6));
    	
    	_grid_Main	._a(mapWK.getComp(WK_RB_CONST.MASK_KEYS_String.CB_FREMDCONTAINER.key()), new RB_gld()._ins(_i_grid)._left_mid())
    				._a(mapWK.getComp(WIEGEKARTE.container_nr) ,new RB_gld()._ins(_i_grid)._left_mid()._span(2))
    				._a(new RB_lab(new MyString("Tara (Kg)").CTrans()), new RB_gld()._ins(_i_grid)._right_mid())
    				._a(mapWK.getComp(WIEGEKARTE.container_tara), new RB_gld()._ins(_i_grid)._left_mid())
    				._a(new RB_lab(new MyString("").CTrans()), new RB_gld()._ins(_i_grid)._left_mid()._span(2));

    	
    	_grid_Main	._a(new RB_lab(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.siegel_nr)) ,new RB_gld()._ins(_i_grid)._left_mid())
					._a(mapWK.getComp(WIEGEKARTE.siegel_nr), new RB_gld()._ins(_i_grid)._left_mid()._span(6));

    	_grid_Main	._a(new Separator(),new RB_gld()._span(15));	

    	_grid_Main	._a(new RB_lab(WK_RB_CONST.MASK_KEYS_String.COMP_GUETERKATEGORIE.realname())._fo_s_plus(3)._b() ,new RB_gld()._ins(_i_grid)._left_top())
       				._a(mapWK.getComp(WIEGEKARTE.gueterkategorie), new RB_gld()._ins(_i_grid)._left_top()._span(14));

    	
       	_grid_Main	._a(mapWK.getComp(WK_RB_cb_LagerplatzSchuett.key), new RB_gld()._ins(_i_grid)._left_top()._span(1))
       				._a(mapWK.getComp(WIEGEKARTE.id_lagerplatz_schuett), new RB_gld()._ins(_i_grid)._left_top()._span(6))
//       				._a(new MyE2_SelectField(new Extent(200)), new RB_gld()._ins(_i_grid)._left_top()._span(2))
//       				._a(new MyE2_SelectField(new Extent(200)), new RB_gld()._ins(_i_grid)._left_top()._span(2))
       				._a(new RB_lab(""),new RB_gld()._ins(_i_grid)._left_top()._span(18));
       	
//       	Component cPopUpGrund = (Component) mapWK.get__Comp(WK_RB_Popup_ContainerAbsetzGrund.key.get_HASHKEY());
       	E2_Grid   gridGrund = new E2_Grid()._s(3)._insets(0)
//       			._a(new RB_lab(new MyString("Grundangabe").CTrans()) ,new RB_gld()._ins(_i_grid)._left_mid())
//				._a(mapWK.getComp(WIEGEKARTE.container_absetz_grund), new RB_gld()._ins(_i_grid)._left_mid())
//				._a(cPopUpGrund,new RB_gld()._ins(_i_grid)._left_mid());
       			;

       	_grid_Main	._a(mapWK.getComp(WK_RB_cb_LagerplatzAbsetz.key), new RB_gld()._ins(_i_grid)._left_mid()._span(1))
       				._a(mapWK.getComp(WIEGEKARTE.id_lagerplatz_absetz), new RB_gld()._ins(_i_grid)._left_mid()._span(6))
       				._a(gridGrund, new RB_gld()._ins(10,2,2,2))
					._a(new RB_lab(""),new RB_gld()._ins(_i_grid)._left_mid()._span(16));
       	//					._a(new RB_lab(""),new RB_gld()._ins(_i_grid)._left_top()._span(18));



    	
    	_grid_Main	._a(new Separator(),new RB_gld()._span(15));
    	
    	_grid_Main	._a(new RB_lab(new MyString("Wiegemeister*in").CTrans())._fo_s_plus(3)._b() ,new RB_gld()._ins(_i_grid)._left_top())
    				._a(mapWK.getComp(WK_RB_CONST.MASK_KEYS_String.COMP_SEL_WAAGE_USER.key()), new RB_gld()._ins(_i_grid)._left_top()._span(14))
    				._endLine(new RB_gld()._ins(_i_grid));;
    	

    	_grid_Main	._a(new RB_lab(new MyString("Waage").CTrans()) ,new RB_gld()._ins(_i_grid)._left_mid())
    				._a(mapWK.getComp(WK_RB_CONST.MASK_KEYS_String.COMP_WAAGELISTE.key()), new RB_gld()._ins(_i_grid)._left_mid()._span(14))
    				._endLine(new RB_gld()._ins(_i_grid));;
		        
        _grid_Main._a(new Separator(),new RB_gld()._span(15));		

		//
		// Grid für Abzüge
		//
		E2_Grid grid_abzuege = new E2_Grid()._setSize(130,130,130,130,130)
				// Zeile
				._a(new RB_lab(new MyE2_String("Nettogewichte / Abzüge"))._b()._fo_s_plus(3),new RB_gld()._ins(5, 0, 0, 5)._span(5)._left_top())
				// Zeile
				._a(new RB_lab(new MyE2_String("Gewicht")),new RB_gld()._ins(5,1,5,0)._right_mid() )
				._a(new RB_lab(new MyE2_String("Verpackung")),new RB_gld()._ins(5,1,5,0)._right_mid() )
				._a(new RB_lab(new MyE2_String("Netto")),new RB_gld()._ins(5,1,5,0)._right_mid() )
				._a(new RB_lab(new MyE2_String("Abzüge")),new RB_gld()._ins(5,1,5,0)._right_mid() )
				._a(new RB_lab(new MyE2_String("Netto")),new RB_gld()._ins(5,1,5,0)._right_mid() )
				// Zeile
				._a(mapWK.getComp(WIEGEKARTE.nettogewicht),new RB_gld()._ins(5, 1, 5, 0)._right_mid())
				._a(mapWK.getComp(WIEGEKARTE.gewicht_abzug),new RB_gld()._ins(5,1,5,0)._right_mid() )
				._a(mapWK.getComp(WIEGEKARTE.gewicht_nach_abzug),new RB_gld()._ins(5,1,5,0)._right_mid() )
				._a(mapWK.getComp(WIEGEKARTE.gewicht_abzug_fuhre),new RB_gld()._ins(5,1,5,0)._right_mid() )
				._a(mapWK.getComp(WIEGEKARTE.gewicht_nach_abzug_fuhre),new RB_gld()._ins(5,1,5,0)._right_mid() )
				;

        
        //hier weitere tabs bei bedarf falls nur ein Tab, diese loeschen
        //beginn erster tab
        E2_Grid grid_waegung = new E2_Grid()._setSize(
        								500,
        								500,
        								750
        								)._bo_d();
        
        grid_waegung._a(mapWK.getComp(WK_RB_CONST.MASK_KEYS_String.COMP_WAEGUNG1.key()), new RB_gld()._ins(2,2,20,2)._left_top())
          			._a(mapWK.getComp(WK_RB_CONST.MASK_KEYS_String.COMP_WAEGUNG2.key()), new RB_gld()._ins(2,2,20,2)._left_top())
          			._a(grid_abzuege ,new RB_gld()._ins(20,2,0,2)._left_top());
        

        
        
        _grid_Main  ._a(grid_waegung, new RB_gld()._ins(_i_grid)._left_top()._span(15));
        
        _grid_Main	._a(new Separator(),new RB_gld()._span(15));		

        E2_Grid grid_external = (E2_Grid) m_tpHashMap.getFromExtender(WK_RB_CONST.WK_RB_TransportExtender.GRID_4_MASK_EXTERNAL);  
        
        // Druckbuttons in den umliegenden Container legen...
        if (grid_external != null) {
        	E2_Grid gButtons = new E2_Grid()._setSize(1240);

        	MyE2_Row rDruckenWK = new MyE2_Row();
        	rDruckenWK.add(mapWK.getComp(WK_RB_CONST.MASK_KEYS_String.BT_PRINT_HOFSCHEIN.key()),E2_INSETS.I(150,0,5,0));
        	rDruckenWK.add(mapWK.getComp(WK_RB_CONST.MASK_KEYS_String.COMP_SEL_DRUCKTYP.key()),E2_INSETS.I(50,0,5,0));
        	rDruckenWK.add(mapWK.getComp(WK_RB_CONST.MASK_KEYS_String.BT_PRINT_WK.key()));
        	rDruckenWK.add(mapWK.getComp(WK_RB_CONST.MASK_KEYS_String.TXT_NUM_ETIKETTEN.key()),E2_INSETS.I(50,0,5,0));
        	rDruckenWK.add(mapWK.getComp(WK_RB_CONST.MASK_KEYS_String.BT_PRINT_ETIKETT.key()));
        	
        	gButtons._a(rDruckenWK , new RB_gld()._right_mid()._ins(0, 0, 5, 0));
        	
        	grid_external._a(gButtons,new RB_gld()._ins(0));
        }
        
        
        //hier alles rendern (entweder nur ein grid oder ein tab mit grids ...
        this.renderMask();
        
        this.resize(WK_RB_CONST.WK_RB_NUM_CONST.MASKPOPUP_WIDTH.getValue(),
        			  WK_RB_CONST.WK_RB_NUM_CONST.MASKPOPUP_HEIGHT.getValue());
    }


    
    /**
     * 
     * aufbau der TabPanes
     * @author manfred
     * @date 06.07.2020
     *
     * @throws myException
     */
	private void _initTabPanes() throws myException {
		    				
		    	E2_Grid gridTabAbzugGebinde = new E2_Grid()._setSize(600);
		    	E2_Grid gridTabAbzugFuhre	= new E2_Grid()._setSize(600);
		    	E2_Grid gridTabBefundung 	= new E2_Grid()._setSize(600);
		    	
		    	
		    	// Tab-Inhalte
		    	
		    	// TAB BEFUNDUNG
//		    	gridTabBefundung._a(new MyE2_Label(new MyE2_String("Befundungsauftrag"), MyE2_Label.STYLE_TITEL_BIG()));
		    	gridTabBefundung._a(_initGridBefundung(), new RB_gld()._ins(2,2,20,2)._left_top());

		    	// TAB GEBINDE
		    	gridTabAbzugGebinde._a(new MyE2_Label(new MyE2_String("Tara Gebinde"), MyE2_Label.STYLE_TITEL_BIG()));
		    	gridTabAbzugGebinde._a(mapWK.getComp(WK_RB_CONST.MASK_KEYS_String.CHILD_ABZUG_GEBINDE.key()), new RB_gld()._ins(2,2,20,2)._left_top());
		    	
		    	// TAB ABZÜGE
		    	gridTabAbzugFuhre._a(new MyE2_Label(new MyE2_String("Mengenabzüge"), MyE2_Label.STYLE_TITEL_BIG()));
		    	gridTabAbzugFuhre._a(mapWK.getComp(WK_RB_CONST.MASK_KEYS_String.CHILD_ABZUG_MENGE.key()), new RB_gld()._ins(2,2,20,2)._left_top());
		
		    		    	
		    	
		    	   	
		    	
		    	tabbedPaneAbzuege.add_Tabb(new MyString("Befundung"), gridTabBefundung)		;
		    	tabbedPaneAbzuege.add_Tabb(new MyString("Tara"), gridTabAbzugGebinde) ;
		    	tabbedPaneAbzuege.add_Tabb(new MyString("Abzug Fuhre"), gridTabAbzugFuhre)		;
		    	
		    	
				// Container-Reiter nur zeigen, wenn auch im Mandanten-Schalter eingeschaltet
		//		if (/*m_bShowContainerverwaltung*/true){
		//			E2_Grid   gridTabContainer = new E2_Grid()._setSize(600);
		//			
		//			gridTabContainer._a(new MyE2_Label(new MyE2_String("Container"), MyE2_Label.STYLE_TITEL_BIG()));
		//			tabbedPaneAbzuege.add_Tabb(new MyString("Container"), gridTabContainer);
		//		}
	}

	
	/**
	 * 
	 * @author manfred
	 * @date 06.07.2020
	 *
	 * @return
	 * @throws myException
	 */
	public E2_Grid _initGridBefundung() throws myException {
		
		
//        this.setBorder(new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
        
        E2_Grid _grid_befundung = new E2_Grid()._setSize(180,120,400)._bo_no();
        
        _grid_befundung
        		._a(new MyE2_Label(new MyE2_String("Befundungsauftrag"), MyE2_Label.STYLE_TITEL_BIG()),new RB_gld()._span(2))
		        ._a(mapWK.getComp(WIEGEKARTE.strahlung_geprueft), new RB_gld()._right_mid()._ins(10, 0,0,0))
        		;
        
        _grid_befundung
        		._a(new RB_lab("Durchführung von"), new RB_gld()._left_mid()._ins(0, 20, 0,0))
        		._a(mapBefund.getComp(WIEGEKARTE_BEFUND.id_wiegekarte_user_befund), new RB_gld()._left_mid()._ins(10, 20, 0,0)._span(2))
        		;
        
        
        // Checkboxen dürfen nur so lange sein, wie der Text, deshalb in eigene Row legen
        MyE2_Row rowCB1 = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
        rowCB1.add(mapBefund.getComp(WIEGEKARTE_BEFUND.sortierung));
        MyE2_Row rowCB2 = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
        rowCB2.add(mapBefund.getComp(WIEGEKARTE_BEFUND.analyse));
        MyE2_Row rowCB3 = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
        rowCB3.add(mapBefund.getComp(WIEGEKARTE_BEFUND.naesseprobe));
        
        E2_Grid _gridArbeitsauftrag = new E2_Grid()._s(1)
        		._a(rowCB1, new RB_gld()._left_mid()._ins(10, 0, 0,5))
        		._a(rowCB2, new RB_gld()._left_mid()._ins(10, 0, 0,5))
        		._a(rowCB3, new RB_gld()._left_mid()._ins(10, 0, 0,5))
//        		._a(mapBefund.getComp(WIEGEKARTE_BEFUND.sortierung), new RB_gld()._right_mid()._ins(10, 0, 0,5))
//        		._a(mapBefund.getComp(WIEGEKARTE_BEFUND.analyse), new RB_gld()._right_mid()._ins(10, 0, 0,5))
//        		._a(mapBefund.getComp(WIEGEKARTE_BEFUND.naesseprobe), new RB_gld()._right_mid()._ins(10, 0, 0,5))
        		;
        
        _grid_befundung
				._a(new RB_lab("Arbeitsauftrag"), new RB_gld()._left_top()._ins(0, 20, 0,0))
				._a(_gridArbeitsauftrag,new RB_gld()._left_top()._ins(0,20,0,0)._span(2));
				;
        
        _grid_befundung
				._a(new RB_lab("Bemerkung"), new RB_gld()._left_top()._ins(0, 20, 0,0))
        		._a(mapBefund.getComp(WIEGEKARTE_BEFUND.bemerkung), new RB_gld()._left_top()._ins(10, 20, 0,0)._span(2))
				;
        
        MyE2_Row rowButton = new MyE2_Row();
        rowButton.add(mapBefund.getComp(WK_RB_CONST.MASK_KEYS_String.BT_PRINT_BEFUNDUNG.key()));
        
        _grid_befundung
        		._a(new RB_lab())
        		._a(rowButton,new RB_gld()._left_mid()._ins(10)._span(2))
//        		._a(mapBefund.getComp(WK_RB_CONST.MASK_KEYS_String.BT_PRINT_BEFUNDUNG.key()),new RB_gld()._left_mid()._ins(10)._span(2))
        		;
        _grid_befundung
        		._a(new RB_lab())
        		._a(mapBefund.getComp(WIEGEKARTE_BEFUND.gedruckt_am), new RB_gld()._left_top()._ins(10, 20, 0,0)._span(2))
        		;
		
		return _grid_befundung;
	}

	
	
    
    /**
     * Erzeugen der Tab-Panes aus dem Array der FieldContainers
     * @author manfred
     * @date 23.03.2020
     *
     * @throws myException
     */
    private void renderMask() throws myException {
    	if (this.fieldContainers.size()==1) {
    		this._a(this.fieldContainers.get(0));
    	} else {
    		for (int i=0; i<this.fieldContainers.size(); i++) {
    			MyString s_tab = this.tabText.size()>i?S.NN(this.tabText.get(i), S.ms("..")):S.ms("Tab Nr: ").ut(" "+(i+1));
    			this.ta.add_Tabb(s_tab, this.fieldContainers.get(i));
    		}
    		this._a(this.ta);
    	}
    	
    	
    }
  
  
  	 /*
  	  * zieht bei groessenaenderungen der maske die interne tab-kompontente mit
  	  */
     public void resize(int width, int height) {
	   this.ta.setWidth(new Extent(width-60));
	   this.ta.setHeight(new Extent(height-170));
	 }
    
}
 
 
