package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK;


import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Vector;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_K;
import panter.gmbh.Echo2.RB.BASICS.RB_MASK_DUMMY_KEY;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.Project_RB_ModuleContainerMASK;
import panter.gmbh.indep.MyInteger;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.INTERFACES.IF_mapCollector_4_FZ_MaskModulContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.LG.LG_CM__Collector;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.LG.LG_DO__Collector;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.LG.__LG_MASTER_KEY;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.LL.LL_CM__Collector;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.LL.LL_DO__Collector;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.LL.__LL_MASTER_KEY;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.ST.ST_CM__Collector;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.ST.ST_DO__Collector;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.ST.__ST_MASTER_KEY;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.TS.TS_CM__Collector;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.TS.TS_DO__Collector;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.TS.__TS_MASTER_KEY;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WA.WA_CM__Collector;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WA.WA_DO__Collector;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WA.__WA_MASTER_KEY;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WE.WE_CM__Collector;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WE.WE_DO__Collector;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WE.__WE_MASTER_KEY;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_MANDANT_ext;

/**
 * test-variante der maskenlistung
 * @author martin
 *
 */
public class FZ_MASK_MaskModulContainer extends Project_RB_ModuleContainerMASK {
    
	
	public static int COLWIDTH = 25;
	public static int COLCOUNT = 60;
	
	private E2_Grid     		mask_grid = new E2_Grid()._bo_dd();

	private E2_Grid     		grid_settings_and_buttons = new E2_Grid();
	
	private FZ_MASK_cb_zeige_interne_buchungen 		cb_show_internal_bookings = new FZ_MASK_cb_zeige_interne_buchungen(this);
	
	
	private E2_NavigationList   naviList =              null;

	/**
	 * konstuktor zur neuerfassung
	 * @throws myException
	 */
	public FZ_MASK_MaskModulContainer(E2_NavigationList   p_naviList) throws myException {
        super();
    
        int[] i_breite = new int[FZ_MASK_MaskModulContainer.COLCOUNT];
        for (int i=0;i<FZ_MASK_MaskModulContainer.COLCOUNT;i++) {
        	i_breite[i]=FZ_MASK_MaskModulContainer.COLWIDTH;
        }
        
        this.mask_grid._setSize(i_breite);
        
        this.naviList = p_naviList;
        
        this.grid_settings_and_buttons._gld(new RB_gld()._ins(E2_INSETS.I(2,1,1,1)))._a_lm(this.cb_show_internal_bookings);
        
        this.rb_INIT(E2_MODULNAME_ENUM.MODUL.FUZE_ATOM_BASED_MASK, this.mask_grid, false);
        
        this.add(this.grid_settings_and_buttons,E2_INSETS.I(3,3,3,3));
        this.add(this.mask_grid,E2_INSETS.I(3,3,3,3));
        
        
        this.add_CloseActions(new ownCloseAction());
	}


	/**
	 * bei schliessen der maske werden bei allen bewegungsvektoren die start-und end-atome aktualisiert
	 * @author martin
	 *
	 */
	private class ownCloseAction extends XX_ActionAgentWhenCloseWindow {
		public ownCloseAction() {
			super(FZ_MASK_MaskModulContainer.this);
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			FZ_MASK_MaskModulContainer oThis = FZ_MASK_MaskModulContainer.this;
			
//			for (RB_ComponentMapCollector co: oThis.rb_hm_component_map_collector().values()) {
//				RB_DataobjectsCollector col = co.rb_Actual_DataobjectCollector();
//				
//				for (RB_Dataobject  dob: col.rb_hm_DataObjects().values()) {
//					if (dob.get_RecORD()!=null && dob.get_MyRECORD() instanceof RECORD_BEWEGUNG_VEKTOR) {
//						RECORD_BEWEGUNG_VEKTOR_spec help = new RECORD_BEWEGUNG_VEKTOR_spec((RECORD_BEWEGUNG_VEKTOR)dob.get_MyRECORD());
//						help.update_start_and_end_atom_id();
//					}
//				}
//			}
		}
		
	}
	
	
	public void CREATE_AND_SHOW_POPUPWINDOW(		Extent 					oWidth, 
													Extent 					oHeight,
													boolean 				bSplit,
													Extent    				oSplitPosition,
													MyE2_String 			oTitle) throws myException  {
		super.CREATE_AND_SHOW_POPUPWINDOW(oWidth, oHeight, bSplit, oSplitPosition, oTitle);
		
		//jetzt den ersten RB_ComponentMapCollector rausholen und die datumskomponente focussieren
		LinkedHashMap<RB_K, RB_ComponentMapCollector> hm = this.rb_hm_component_map_collector();
		Vector<RB_MASK_DUMMY_KEY>  key_vektor = new Vector<>();
		for (RB_K key: hm.keySet()) {
			key_vektor.add((RB_MASK_DUMMY_KEY)key);
		}
		if (key_vektor.size()>0) {
			RB_ComponentMapCollector collectorNumberOne = this.rb_hm_component_map_collector().get(key_vektor.get(0));
			if (collectorNumberOne instanceof IF_mapCollector_4_FZ_MaskModulContainer) {
				((IF_mapCollector_4_FZ_MaskModulContainer)collectorNumberOne).set_focus_to_first_in_line();
			}
		}

	}

	
	/**
	 * zeile hinzufuegen
	 * @param collector
	 * @throws myException
	 */
	public RB_ComponentMapCollector rb_register(RB_ComponentMapCollector  collector) throws myException {
		super.rb_register(collector);
		return collector;
	}
	
	
	
	
	
	
	
	public boolean is_hidden_positions_to_show() {
		return this.cb_show_internal_bookings.isSelected();
	}
	
	public E2_Grid mask_grid() {
		return mask_grid;
	}

	public  Color  get_color_for_expanded_block() throws myException {
		return new RECORD_MANDANT_ext(bibALL.get_RECORD_MANDANT()).get_COLOR_MASK_HIGHLIGHT();
	}

	public  Color  get_color_for_closed_block() {
		return new E2_ColorBase();
	}

	public  Color  get_color_for_closed_header() {
		return new E2_ColorDDDark();
	}

	
	public void rebuild_container_grid() throws myException {
		this.mask_grid.removeAll();

		if (bibALL.get_bDebugMode()) {
			//hier eine "messreihe" einblenden, um die positionierung zu erleichtern
			for (int i=0; i<this.mask_grid.getSize();i++) {
				E2_Grid help_grid = new E2_Grid()._bo_b()._a_cm(new RB_lab()._t(""+(i+1)))._w(new Extent(100,Extent.PERCENT));
				this.mask_grid._a(help_grid);
			}
			this.mask_grid.setRowHeight(0, new Extent(10));
		}
		
		LinkedHashMap<RB_K, RB_ComponentMapCollector> hm = this.rb_hm_component_map_collector();

		//immer in der reihenfolge der eintragung rendern, d.h. nach der groesse der key-zaehlers
		Vector<RB_MASK_DUMMY_KEY>  key_vektor = new Vector<>();
		for (RB_K key: hm.keySet()) {
			key_vektor.add((RB_MASK_DUMMY_KEY)key);
		}
		
		key_vektor.sort(new Comparator<RB_MASK_DUMMY_KEY>() {
			@Override
			public int compare(RB_MASK_DUMMY_KEY o1, RB_MASK_DUMMY_KEY o2) {
				int i1 = new MyInteger(o1.HASHKEY()).get_iValue();
				int i2 = new MyInteger(o2.HASHKEY()).get_iValue();
				if (i1<i2) {
					return -1;
				} else if (i1>i2) {
					return 1;
				}
				return 0;
			}
			
		});
		
		//jetzt alle RB_ComponentMapCollector rendern
		for (RB_MASK_DUMMY_KEY key: key_vektor) {
			RB_ComponentMapCollector collector = this.rb_hm_component_map_collector().get(key);
			if (collector instanceof IF_mapCollector_4_FZ_MaskModulContainer) {
				((IF_mapCollector_4_FZ_MaskModulContainer)collector).render_titel_line();
				((IF_mapCollector_4_FZ_MaskModulContainer)collector).render_body_line();
			}
		}
	}
	
	
	public RB_ComponentMapCollector get_last_ComponentMapCollector() {
		LinkedHashMap<RB_K, RB_ComponentMapCollector> hm = this.rb_hm_component_map_collector();
		
		Vector<RB_MASK_DUMMY_KEY>  key_vektor = new Vector<>();
		for (RB_K key: hm.keySet()) {
			key_vektor.add((RB_MASK_DUMMY_KEY)key);
		}
		
		key_vektor.sort(new Comparator<RB_MASK_DUMMY_KEY>() {
			@Override
			public int compare(RB_MASK_DUMMY_KEY o1, RB_MASK_DUMMY_KEY o2) {
				int i1 = new MyInteger(o1.HASHKEY()).get_iValue();
				int i2 = new MyInteger(o2.HASHKEY()).get_iValue();
				if (i1<i2) {
					return -1;
				} else if (i1>i2) {
					return 1;
				}
				return 0;
			}
		});

		RB_ComponentMapCollector collectorRueck = null;
		for (RB_MASK_DUMMY_KEY key: key_vektor) {
			collectorRueck = this.rb_hm_component_map_collector().get(key);
		}

		return collectorRueck;
	}

	
	public E2_NavigationList get_naviList() {
		return naviList;
	}

	
	
	public WE_CM__Collector add_new_WE() throws myException {
		__WE_MASTER_KEY  	key = 				new __WE_MASTER_KEY(MASK_STATUS.NEW);
		WE_CM__Collector  	mask_collector = 	new WE_CM__Collector(this.naviList, key);
		WE_DO__Collector 	dataColl = 			new WE_DO__Collector(key);
		this.rb_register(mask_collector);
		bibMSG.add_MESSAGE(mask_collector.rb_COMPLETE_FILL_CYCLE(dataColl));
		return mask_collector;
	}
	

	public WE_CM__Collector add_edit_WE(String id_bewegung_vektor_uf) throws myException {
		__WE_MASTER_KEY  		key = 			new __WE_MASTER_KEY(MASK_STATUS.EDIT);
		WE_CM__Collector  	mask_collector = 	new WE_CM__Collector(this.naviList, key);
		WE_DO__Collector 	dataColl = 			new WE_DO__Collector(id_bewegung_vektor_uf, key);
		this.rb_register(mask_collector);
		bibMSG.add_MESSAGE(mask_collector.rb_COMPLETE_FILL_CYCLE(dataColl));
		return mask_collector;
	}

	
	public LL_CM__Collector add_new_LL() throws myException {
		__LL_MASTER_KEY  	key = 				new __LL_MASTER_KEY(MASK_STATUS.NEW);
		LL_CM__Collector  	mask_collector = 	new LL_CM__Collector(this.naviList, key);
		LL_DO__Collector 	dataColl = 			new LL_DO__Collector(key);
		this.rb_register(mask_collector);
		bibMSG.add_MESSAGE(mask_collector.rb_COMPLETE_FILL_CYCLE(dataColl));
		return mask_collector;
	}
	

	public LL_CM__Collector add_edit_LL(String id_bewegung_vektor_uf) throws myException {
		__LL_MASTER_KEY  		key = 				new __LL_MASTER_KEY(MASK_STATUS.EDIT);
		LL_CM__Collector  	mask_collector = 	new LL_CM__Collector(this.naviList, key);
		LL_DO__Collector 	dataColl = 			new LL_DO__Collector(id_bewegung_vektor_uf, key);
		this.rb_register(mask_collector);
		bibMSG.add_MESSAGE(mask_collector.rb_COMPLETE_FILL_CYCLE(dataColl));
		return mask_collector;
	}

	
	public LG_CM__Collector add_new_LG() throws myException {
		__LG_MASTER_KEY  	key = 				new __LG_MASTER_KEY(MASK_STATUS.NEW);
		LG_CM__Collector  	mask_collector = 	new LG_CM__Collector(this.naviList, key);
		LG_DO__Collector 	dataColl = 			new LG_DO__Collector(key);
		this.rb_register(mask_collector);
		bibMSG.add_MESSAGE(mask_collector.rb_COMPLETE_FILL_CYCLE(dataColl));
		return mask_collector;
	}
	

	public LG_CM__Collector add_edit_LG(String id_bewegung_vektor_uf) throws myException {
		__LG_MASTER_KEY  		key = 				new __LG_MASTER_KEY(MASK_STATUS.EDIT);
		LG_CM__Collector  	mask_collector = 		new LG_CM__Collector(this.naviList, key);
		LG_DO__Collector 	dataColl = 				new LG_DO__Collector(id_bewegung_vektor_uf, key);
		this.rb_register(mask_collector);
		bibMSG.add_MESSAGE(mask_collector.rb_COMPLETE_FILL_CYCLE(dataColl));
		return mask_collector;
	}

	
	public ST_CM__Collector add_new_ST() throws myException {
		__ST_MASTER_KEY  	key = 				new __ST_MASTER_KEY(MASK_STATUS.NEW);
		ST_CM__Collector  	mask_collector = 	new ST_CM__Collector(this.naviList, key);
		ST_DO__Collector 	dataColl = 			new ST_DO__Collector(key);
		this.rb_register(mask_collector);
		bibMSG.add_MESSAGE(mask_collector.rb_COMPLETE_FILL_CYCLE(dataColl));
		return mask_collector;
	}
	

	public ST_CM__Collector add_edit_ST(String id_bewegung_vektor_uf) throws myException {
		__ST_MASTER_KEY  	key = 				new __ST_MASTER_KEY(MASK_STATUS.EDIT);
		ST_CM__Collector  	mask_collector = 	new ST_CM__Collector(this.naviList, key);
		ST_DO__Collector 	dataColl = 			new ST_DO__Collector(id_bewegung_vektor_uf, key);
		this.rb_register(mask_collector);
		bibMSG.add_MESSAGE(mask_collector.rb_COMPLETE_FILL_CYCLE(dataColl));
		return mask_collector;
	}

	public WA_CM__Collector add_edit_WA(String id_bewegung_vektor_uf) throws myException {
		__WA_MASTER_KEY  	key = 				new __WA_MASTER_KEY(MASK_STATUS.EDIT);
		WA_CM__Collector  	mask_collector = 	new WA_CM__Collector(this.naviList, key);
		WA_DO__Collector 	dataColl = 			new WA_DO__Collector(id_bewegung_vektor_uf, key);
		this.rb_register(mask_collector);
		bibMSG.add_MESSAGE(mask_collector.rb_COMPLETE_FILL_CYCLE(dataColl));
		return mask_collector;
	}
	
	public WA_CM__Collector add_new_WA() throws myException {
		__WA_MASTER_KEY  	key = 				new __WA_MASTER_KEY(MASK_STATUS.NEW);
		WA_CM__Collector  	mask_collector = 	new WA_CM__Collector(this.naviList, key);
		WA_DO__Collector 	dataColl = 			new WA_DO__Collector(key);
		this.rb_register(mask_collector);
		bibMSG.add_MESSAGE(mask_collector.rb_COMPLETE_FILL_CYCLE(dataColl));
		return mask_collector;
	}
	
	public TS_CM__Collector add_edit_TS(String id_bewegung_vektor_uf) throws myException {
		__TS_MASTER_KEY  	key = 				new __TS_MASTER_KEY(MASK_STATUS.EDIT);
		TS_CM__Collector  	mask_collector = 	new TS_CM__Collector(this.naviList, key);
		TS_DO__Collector 	dataColl = 			new TS_DO__Collector(id_bewegung_vektor_uf, key);
		this.rb_register(mask_collector);
		bibMSG.add_MESSAGE(mask_collector.rb_COMPLETE_FILL_CYCLE(dataColl));
		return mask_collector;
	}
	
	public TS_CM__Collector add_new_TS() throws myException {
		__TS_MASTER_KEY  	key = 				new __TS_MASTER_KEY(MASK_STATUS.NEW);
		TS_CM__Collector  	mask_collector = 	new TS_CM__Collector(this.naviList, key);
		TS_DO__Collector 	dataColl = 			new TS_DO__Collector(key);
		this.rb_register(mask_collector);
		bibMSG.add_MESSAGE(mask_collector.rb_COMPLETE_FILL_CYCLE(dataColl));
		return mask_collector;
	}
	
}
 
