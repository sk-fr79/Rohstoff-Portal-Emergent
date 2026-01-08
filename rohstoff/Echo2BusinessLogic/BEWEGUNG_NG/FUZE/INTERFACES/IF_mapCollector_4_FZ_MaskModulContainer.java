package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.INTERFACES;


import java.util.Vector;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLight;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_VEKTOR;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_VEKT;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.FZ_MASK_ContainerSegmentExtender;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.FZ_MASK_ContainerSegmentInLine;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.FZ_MASK_MaskModulContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.FZ__bt_INFO_STRUCTURE;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.FZ__bt_primaNota;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._RECS.Rec20_vektor;

public interface IF_mapCollector_4_FZ_MaskModulContainer {
	
	public MASK_STATUS  				find_actual_mask_status() throws myException;

	public void         				set_focus_to_first_in_line() throws myException;
	
	public FZ_MASK_MaskModulContainer   get_my_maskContainer() throws myException;
	
	public void 						re_open(MASK_STATUS status_new) throws myException;

	public IF_MasterKey 				get_master_key();
	public KEY_VEKT  					get_key_vektor();
	public Component  					render_type_label() throws myException;
	public E2_Grid                      get_button_grid();
	public E2_Grid                      get_status_grid();	
	public Component 					fill_button_grid() throws myException;
	
	public Vector<FZ_MASK_ContainerSegmentInLine>   get_VectorOfSegments_in_maskline();														//sammelt die segmente
	public void    									set_actual_opened_segmentObject(FZ_MASK_ContainerSegmentInLine opened_segment);			//merkt sich das offene segment (falls ungeoeffnet, dann null)
	public FZ_MASK_ContainerSegmentInLine    		get_actual_opened_segmentObject();														//liefert das offene segment (falls ungeoeffnet, dann null)
	

	public int   						insets_for_hidden_addon_block();
	public RB_DataobjectsCollector 		rb_Actual_DataobjectCollector();


	
	
	
	
	
//	public Object     					create_hidden_addon_block() throws myException;
//	public void 						render_hidden_addon_block(Object object_from_check) throws myException;    //wenn keiner da, dann return null

	public default Object create_hidden_addon_block() throws myException {
    	Rec20_vektor  v = new Rec20_vektor(this.rb_Actual_DataobjectCollector().get(this.get_master_key().k_vektor()).rec20());
		return v.generate_info_grid_interne_buchungen();
	}

	

	public default void render_hidden_addon_block(Object object_from_check) throws myException {
		Color col_back = 		this.get_my_maskContainer().get_color_for_closed_block();
		E2_Grid mask_grid =		this.get_my_maskContainer().mask_grid();

		if (object_from_check!=null) {
			E2_Grid  addons = (E2_Grid)object_from_check;
			
			mask_grid._startLine(new RB_gld()._col(col_back)._span(this.insets_for_hidden_addon_block()))._endLine(addons, new RB_gld()._col(new E2_ColorLLight())._span(12));
		}
	}
	
	
	
	

	
	
	public default RB_ComponentMap get_map_vektor() {
		return  ((RB_ComponentMapCollector)this).rb_get_ComponentMAP(this.get_key_vektor());
	}

	
	public default Component  render_status_label() throws myException {
		if (this.find_actual_mask_status()!=null) {
			return ((Component) new RB_lab(this.find_actual_mask_status().get_icon())._ttt(this.find_actual_mask_status().get_statusBeschreibung()));
		}
		return new RB_lab("");
	}
	
	
	
	public default Component fill_status_grid() throws myException {
		
		String id_bewegung = "";
		String id_vektor = "";
		RB_KM key = (RB_KM)this.get_master_key();
		RB_ComponentMapCollector  	rb_cm_collector = (RB_ComponentMapCollector)this;
		RB_DataobjectsCollector  	rb_do_collector = rb_cm_collector.rb_Actual_DataobjectCollector();
		
		if (!rb_cm_collector.rb_Actual_DataobjectCollector().get(key).rb_MASK_STATUS().isStatusNew()) {
			id_bewegung = 	rb_do_collector.get(key).get_MyRECORD().fs(BEWEGUNG.id_bewegung);
			id_vektor = 	rb_do_collector.get(this.get_master_key().k_vektor()).get_MyRECORD().fs(BEWEGUNG_VEKTOR.id_bewegung_vektor);
		}
		
		this.get_status_grid().removeAll();
		this.get_status_grid()._gld(new RB_gld()._center_mid())._setSize(30,20,20,20)
					._a(this.render_type_label(),		new RB_gld()._ins(1))
					._a(this.render_status_label(),		new RB_gld()._ins(1))
					._a(new FZ__bt_INFO_STRUCTURE(this),new RB_gld()._ins(1))
					._a(new FZ__bt_primaNota(this),		new RB_gld()._ins(1))
					;
		
		
		if (S.isFull(id_bewegung)) {
			this.get_status_grid()	._a(new RB_lab("Beweg.")._fsa(-2)._i(),		new RB_gld()._ins(E2_INSETS.I(2,4,2,1))._span(2))
					._a(new RB_lab(id_bewegung)._fsa(-2)._i()._ttt(new MyE2_String("Gehört zur Warenbewegung mit der ID: ",true,""+id_bewegung,false)),new RB_gld()._ins(2,4,2,1)._right_top()._span(2));
		}
		if (S.isFull(id_vektor)) {
			this.get_status_grid()	._a(new RB_lab("Vektor")._fsa(-2)._i(),		new RB_gld()._ins(E2_INSETS.I(2,1,2,1))._span(2))
					._a(new RB_lab(id_vektor)._fsa(-2)._i()._ttt(new MyE2_String("Gehört zum Vektor mit der ID: ",true,""+id_vektor,false)),new RB_gld()._ins(2,1,2,1)._right_top()._span(2));
		}
		
		return this.get_status_grid();
	}


	
	public default void render_titel_line() throws myException {

		FZ_MASK_MaskModulContainer  cont = this.get_my_maskContainer();
		E2_Grid  					grid = cont.mask_grid();

		Color basic = 	this.get_my_maskContainer().get_color_for_closed_header();
		Color high = 	this.get_my_maskContainer().get_color_for_expanded_block();
		

		for (FZ_MASK_ContainerSegmentInLine segment: this.get_VectorOfSegments_in_maskline()) {
			//breite der komponente ist die Breite der spalten * span
			int i_width_of_segment = FZ_MASK_MaskModulContainer.COLWIDTH*segment.get_col_span();

			Color  col_actual = basic;
			if (this.get_actual_opened_segmentObject()!=null && segment==this.get_actual_opened_segmentObject()) {
				col_actual=high;
			}
			
			//jede titelkomponente kommt in ein grid, wenn ausklapp, dann mit button
			E2_Grid g_head = new E2_Grid();
			RB_gld ld = segment.get_gld_head_main()._c();
			//falls ein GridayoutData an der komponente klebt, das uebernehmen
			if (segment.get_headerComponent().getLayoutData()!=null && segment.get_headerComponent().getLayoutData() instanceof RB_gld) {
				ld = ((RB_gld)segment.get_headerComponent().getLayoutData())._c();
			}
			ld._col(col_actual);
			g_head._a(segment.get_headerComponent(),ld);
			if (segment.get_extender()==null) {
				g_head._setSize(i_width_of_segment);
			} else {
				g_head._setSize(i_width_of_segment-20,20);
				g_head._a(segment.get_extender().get_extenderButton(),ld._c()._right_top()._col(col_actual));
			}
			
			grid._a(g_head, new RB_gld()._span(segment.get_col_span())._col(col_actual));
		}
		grid._endLine(new RB_gld()._col(basic));
		

	}
	
	
	/**
	 * rendern der komponenten
	 * @throws myException
	 */
	public default 	void	render_body_line() throws myException {
		
		FZ_MASK_MaskModulContainer  cont = this.get_my_maskContainer();
		E2_Grid  					grid = cont.mask_grid();

		Color basic = cont.get_color_for_closed_block();
		Color high = cont.get_color_for_expanded_block();
		
//		for (FZ_MASK_ContainerSegmentInLine segment: this.get_VectorOfSegments_in_maskline()) {
		for (int s=0;s< this.get_VectorOfSegments_in_maskline().size();s++) {
			FZ_MASK_ContainerSegmentInLine segment = this.get_VectorOfSegments_in_maskline().get(s);
			
			Color  col_actual = basic;
			if (this.get_actual_opened_segmentObject()!=null && segment==this.get_actual_opened_segmentObject()) {
				col_actual=high;
			}
			
			RB_gld ld = segment.get_gld_data()._c();
			//falls ein GridayoutData an der komponente klebt, das uebernehmen
			if (segment.get_dataComponent().getLayoutData()!=null && segment.get_dataComponent().getLayoutData() instanceof RB_gld) {
				ld = ((RB_gld)segment.get_headerComponent().getLayoutData())._c();
			}
			ld._col(col_actual)._span(segment.get_col_span());
			grid._a(segment.get_dataComponent(), ld);
			
			//falls die zeile nicht vollstaendig, zeile abschliessen
			if (s==this.get_VectorOfSegments_in_maskline().size()-1) {
				grid._endLine(ld);
			}
		}
		
//		//zeile abschliessen (falls spalten ungefuellt sind
//		grid._endLine(gl)

		//jetzt nachsehen, ob evtl. weitere zeilen in der struktur hinterlegt sind
		int iAnzahlZusatz = this.sizeAddons();
		for (int i=0;i<iAnzahlZusatz;i++) {
//			for (FZ_MASK_ContainerSegmentInLine segment: this.get_VectorOfSegments_in_maskline()) {
			for (int s=0;s< this.get_VectorOfSegments_in_maskline().size();s++) {
				FZ_MASK_ContainerSegmentInLine segment = this.get_VectorOfSegments_in_maskline().get(s);
				
				Color  col_actual = basic;
				if (this.get_actual_opened_segmentObject()!=null && segment==this.get_actual_opened_segmentObject()) {
					col_actual=high;
				}
				
				RB_gld ld = segment.get_gld_data()._c();
				//falls ein GridayoutData an der komponente klebt, das uebernehmen
				if (segment.get_dataComponent().getLayoutData()!=null && segment.get_dataComponent().getLayoutData() instanceof RB_gld) {
					ld = ((RB_gld)segment.get_headerComponent().getLayoutData())._c();
				}
				
				Component comAddon = segment.get_addOnLine(i);
				if (comAddon==null) {
					comAddon=new RB_lab("");
				}
				
				ld._col(col_actual)._span(segment.get_col_span());
				grid._a(comAddon, ld);
				
			}
		}
		
		
		
		this.fill_status_grid();
		this.fill_button_grid();
		
		if (this.get_actual_opened_segmentObject()!=null) {
			this.get_actual_opened_segmentObject().get_extender().open_extension();
		}
		
		//ganz unter der zeile die hidden-infos
		if (this.get_my_maskContainer().is_hidden_positions_to_show()) {
			Object comp_hidden = this.create_hidden_addon_block();
			if (comp_hidden!=null) {
				this.render_hidden_addon_block(comp_hidden);
			}
		}
	}
	

	
	
	/**
	 * sucht den naechsten RB_ComponentMapCollector in der maske
	 * @return
	 * @throws myException
	 */
	public default IF_mapCollector_4_FZ_MaskModulContainer find_next_in_mask() throws myException {
		
		FZ_MASK_MaskModulContainer container = this.get_my_maskContainer();
		
		IF_mapCollector_4_FZ_MaskModulContainer next = null;
		
		boolean b_foundMe=false;
		
		for (RB_ComponentMapCollector cont: container.rb_hm_component_map_collector().values()) {
			if (b_foundMe) {
				if (cont instanceof IF_mapCollector_4_FZ_MaskModulContainer) {
					next=(IF_mapCollector_4_FZ_MaskModulContainer)cont;
					break;
				}
			}
			
			if (cont instanceof IF_mapCollector_4_FZ_MaskModulContainer) {
				if (cont==this) {
					b_foundMe=true;
				}
			}
		}
				
		return next; 

	}
	
	
	public default IF_mapCollector_4_FZ_MaskModulContainer add_segment_in_maskline(FZ_MASK_ContainerSegmentInLine segment, FZ_MASK_ContainerSegmentExtender extender) throws myException {
		this.get_VectorOfSegments_in_maskline().addElement(segment);
		//pruefen, ob die segmente zu breit werden
		int i_gesamt_spalten =0;
		for (FZ_MASK_ContainerSegmentInLine seg: this.get_VectorOfSegments_in_maskline()) {
			i_gesamt_spalten+=seg.get_col_span();
		}
		
		if (i_gesamt_spalten>FZ_MASK_MaskModulContainer.COLCOUNT) {
			throw new myException(this,"Your columes exceed the number of "+FZ_MASK_MaskModulContainer.COLCOUNT);
		}
		
		if (extender!=null) {
			segment.set_extender(extender);
			extender.set_Segment(segment);
		}
		
		return this;
	}

	
	
	
//	public IF_mapCollector_4_FZ_MaskModulContainer 	add_segment_in_maskline(FZ_MASK_ContainerSegmentInLine  segment, FZ_MASK_ContainerSegmentExtender extender) throws myException;  	//fuegt ein segment in den vektor ein
	
	
	
	/**
	 * 
	 * @return vector with alle RB_ComponentMapCollector of interface IF_useable_4_FZ_MaskModulContainer in the own mask
	 * @throws myException
	 */
	public default Vector<IF_mapCollector_4_FZ_MaskModulContainer> find_all_componentMapContainers_in_mask(boolean onlyTheOthers) throws myException {
		
		FZ_MASK_MaskModulContainer container = this.get_my_maskContainer();
		
		Vector<IF_mapCollector_4_FZ_MaskModulContainer> v_rueck = new Vector<>();
		
		for (RB_ComponentMapCollector cont: container.rb_hm_component_map_collector().values()) {
			if (cont instanceof IF_mapCollector_4_FZ_MaskModulContainer) {
				if (cont != this || (!onlyTheOthers)) {
					v_rueck.add((IF_mapCollector_4_FZ_MaskModulContainer)cont);
				}
			}
		}
				
		return v_rueck; 

	}
	
	
	
	
	public default void  focus_next_line() throws myException {
		IF_mapCollector_4_FZ_MaskModulContainer next = this.find_next_in_mask();
		if (next != null) {
			next.set_focus_to_first_in_line();
		}
	}
	
	
	/**
	 * 
	 * @return die zahl der zeilen im segment mit der maximalen anzahl zusatzelemente
	 */
	public default int sizeAddons() {
		int iMaxAddonLines = 0;
		for (FZ_MASK_ContainerSegmentInLine segment: this.get_VectorOfSegments_in_maskline()) {
			if (segment.sizeAddons()>iMaxAddonLines) {
				iMaxAddonLines=segment.sizeAddons();
			}
		}
		return iMaxAddonLines;
	}

	
	
	
	
	public default String get_id_bewegung_vektor() throws myException {
		String id = null;
		if (this.get_map_vektor()!=null && this.get_map_vektor().getRbDataObjectActual()!=null && this.get_map_vektor().getRbDataObjectActual().get_MyRECORD()!=null) {
			long l_id_vektor = this.get_map_vektor().getRbDataObjectActual().get_MyRECORD().l(BEWEGUNG_VEKTOR.id_bewegung_vektor, 0l).longValue();
			if (l_id_vektor>0) {
				id=""+l_id_vektor;
			}
		}
		return id;
	}
	
}
