 
package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.ST;

import java.util.Map.Entry;
import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_K;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_date_selektor;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_PopUpMenue;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_VEKTOR;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.INTERFACES.IF_mapCollector_4_FZ_MaskModulContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_VEKT;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.CM_Bewegung;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.CM_Vektor;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.CM_VektorPos;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.FZ_AA_UpdateOther_RECORD_BEWEGUNG_AfterSave;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.FZ_MASK_ContainerSegmentInLine;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.FZ_MASK_MaskModulContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._BASICS.FZ__CONST;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_STATION_TYP;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_VEKTORPOS_TYP;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_VEKTOR_TYP;


public class ST_CM__Collector extends RB_ComponentMapCollector implements IF_mapCollector_4_FZ_MaskModulContainer {
	
	private MyE2_Button  				bt_save_and_close = 	new MyE2_Button(E2_ResourceIcon.get_RI("save_and_close.png"),new MyE2_String("Speichern und die Warenbewegung aus der Maske entfernen.."),null);
	private MyE2_Button  				bt_save_and_next = 		new MyE2_Button(E2_ResourceIcon.get_RI("save_and_add.png"),new MyE2_String("Speichern und eine weitere Erfassung zufügen.."),null);
	private MyE2_Button  				bt_abbruch = 			new MyE2_Button(E2_ResourceIcon.get_RI("cancel.png"),new MyE2_String("Die Warenbewegung aus der Maske entfernen.."),null);

	private MyE2_Button  				bt_open4Edit = 			new MyE2_Button(E2_ResourceIcon.get_RI("edit.png"),new MyE2_String("Die erneut zur Bearbeitung öffnen .."),null);

	private MyE2_Button  				bt_save_and_reopen_as_view = 	new MyE2_Button(E2_ResourceIcon.get_RI("save.png"),new MyE2_String("Speichern und Datensatz schliessen."),null);

	private MyE2_PopUpMenue   			bt_PopUpZusatz  =  		new MyE2_PopUpMenue(E2_ResourceIcon.get_RI("popup_midi.png"), E2_ResourceIcon.get_RI("popup_midi__.png"), false);
	
	private E2_NavigationList   		naviList = null;
	
	private __ST_MASTER_KEY 				master_key = null;
	
	private E2_Grid                  	status_grid = new E2_Grid();
	private E2_Grid                  	button_grid = new E2_Grid();
	
	
	private Vector<FZ_MASK_ContainerSegmentInLine>  v_segments = new Vector<>();
	private FZ_MASK_ContainerSegmentInLine          actual_open_segment = null;


	/**
	 * 
	 * @param p_naviList
	 * @param dataobjects_collector (wird direkt eingefuellt)
	 * @param mask  FZ_MASK_MaskModulContainer, darin wird direkt registriert
	 * @throws myException
	 */
    public ST_CM__Collector(E2_NavigationList p_naviList, __ST_MASTER_KEY p_key) throws myException {
        super();
        
        this.master_key = p_key;
        this.naviList 	= p_naviList;
        
        this.registerComponent(this.master_key, new ST_CM_Bewegung());
        
      	this.registerComponent(this.master_key.k_vektor(), 										new CM_Vektor(this.master_key));

       	this.registerComponent(this.master_key.k_vektor_pos_left(), 								new CM_VektorPos());
       	
       	this.registerComponent(this.master_key.k_vektor_pos_left__atom_left(), 					new ST_CM_Atom(ENUM_VEKTORPOS_TYP.ST_MAIN_LEFT));
       	this.registerComponent(this.master_key.k_vektor_pos_left__atom_left__station_start(), 	new ST_CM_Station(ENUM_STATION_TYP.START_STATION,true));
       	this.registerComponent(this.master_key.k_vektor_pos_left__atom_left__station_ziel(), 		new ST_CM_Station(ENUM_STATION_TYP.ZIEL_STATION,false));
       
       	this.registerComponent(this.master_key.k_vektor_pos_left__atom_right(), 					new ST_CM_Atom(ENUM_VEKTORPOS_TYP.ST_MAIN_LEFT));
       	this.registerComponent(this.master_key.k_vektor_pos_left__atom_right__station_start(), 	new ST_CM_Station(ENUM_STATION_TYP.START_STATION,false));
       	this.registerComponent(this.master_key.k_vektor_pos_left__atom_right__station_ziel(), 	new ST_CM_Station(ENUM_STATION_TYP.ZIEL_STATION,false));
       	
       	
       	this.registerComponent(this.master_key.k_vektor_pos_right(), 								new CM_VektorPos());
       
       	this.registerComponent(this.master_key.k_vektor_pos_right__atom_left(), 					new ST_CM_Atom(ENUM_VEKTORPOS_TYP.ST_MAIN_RIGHT));
       	this.registerComponent(this.master_key.k_vektor_pos_right__atom_left__station_start(),	new ST_CM_Station(ENUM_STATION_TYP.START_STATION,false));
       	this.registerComponent(this.master_key.k_vektor_pos_right__atom_left__station_ziel(),		new ST_CM_Station(ENUM_STATION_TYP.ZIEL_STATION,false));
    	
       	this.registerComponent(this.master_key.k_vektor_pos_right__atom_right(), 					new ST_CM_Atom(ENUM_VEKTORPOS_TYP.ST_MAIN_RIGHT));
       	this.registerComponent(this.master_key.k_vektor_pos_right__atom_right__station_start(), 	new ST_CM_Station(ENUM_STATION_TYP.START_STATION,false));
       	this.registerComponent(this.master_key.k_vektor_pos_right__atom_right__station_ziel(),	new ST_CM_Station(ENUM_STATION_TYP.ZIEL_STATION,true));
        
        this.bt_save_and_close.focus_on();
        this.bt_save_and_next.focus_on();
        this.bt_abbruch.focus_on();
        this.bt_open4Edit.focus_on();
        this.bt_save_and_reopen_as_view.focus_on();
        
        this.bt_save_and_close._aaa(			new ownActionSave())._aaa(new FZ_AA_UpdateOther_RECORD_BEWEGUNG_AfterSave(this))._aaa(	new ownActionCloseLine());
        this.bt_save_and_reopen_as_view._aaa(	new ownActionSave())._aaa(new FZ_AA_UpdateOther_RECORD_BEWEGUNG_AfterSave(this))._aaa(	new ownActionReopen(MASK_STATUS.VIEW));
        this.bt_save_and_next._aaa(				new ownActionSave())._aaa(new FZ_AA_UpdateOther_RECORD_BEWEGUNG_AfterSave(this))._aaa(new ownActionAddNewLine())._aaa(	new ownActionReopen(MASK_STATUS.VIEW))._aaa(new ownActionFocusLastComponentMapCollector());
        this.bt_abbruch._aaa(new ownActionCloseLine());
        
        this.bt_open4Edit._aaa(					new ownActionReopen(MASK_STATUS.EDIT));
        

        FZ_MASK_MaskModulContainer maskContainer = this.get_my_maskContainer();
	    
      
        
        //jetzt die segmente fuer die zeile registrieren
        RB_gld ld = new RB_gld()._ins(2,2,2,1);
        this.add_segment_in_maskline(new FZ_MASK_ContainerSegmentInLine(this, new RB_lab()._tr("Infos"), 		this.get_status_grid(), 												FZ__CONST.COLUMN_WIDTH.INFOS.get_column_span(), ld,ld),	null);
        
        //Interessant visuell loesung: nur als beispiel
        	       
        this.add_segment_in_maskline(new FZ_MASK_ContainerSegmentInLine(this, new RB_lab()._tr("Leistungsdatum"), 		
        					new E2_Grid()._setSize(54,96)._a(new RB_lab()._t(S.mt("Quelle"))._i())._a(this.get_startAtom().getComp(BEWEGUNG_ATOM.leistungsdatum)), 						FZ__CONST.COLUMN_WIDTH.LDATUM.get_column_span(), ld,ld)
        	._addLine(0, 	new E2_Grid()._setSize(54,96)._a(new RB_lab()._t(S.mt("Ziel"))._i())._a(this.get_zielAtom().getComp(BEWEGUNG_ATOM.leistungsdatum))),	new ST_CM__column_extenders.ExtDatum(maskContainer, this));
        
        this.add_segment_in_maskline(new FZ_MASK_ContainerSegmentInLine(this, new RB_lab()._tr("Startstation"), this.get_startStationLeft().getComp(BEWEGUNG_STATION.id_adresse),		FZ__CONST.COLUMN_WIDTH.STATION.get_column_span(), ld,ld)
				._addLine(0, new RB_lab("Streckenlager")._i()),																		new ST_CM__column_extenders.ExtStation_variante2(maskContainer,this, true));
        
        this.add_segment_in_maskline(new FZ_MASK_ContainerSegmentInLine(this, new RB_lab()._tr("Zielstation"), 	new RB_lab("Streckenlager")._i(),										FZ__CONST.COLUMN_WIDTH.STATION.get_column_span(), ld,ld)
			._addLine(0, this.get_zielStationRight().getComp(BEWEGUNG_STATION.id_adresse)),						new ST_CM__column_extenders.ExtStation_variante2(maskContainer,this, false));
        
        this.add_segment_in_maskline(new FZ_MASK_ContainerSegmentInLine(this, new RB_lab()._tr("Sorte"), 		this.get_startAtom().getComp(BEWEGUNG_ATOM.id_artikel_bez),  			FZ__CONST.COLUMN_WIDTH.SORTE.get_column_span(), ld,ld)
        	._addLine(0, this.get_zielAtom().getComp(BEWEGUNG_ATOM.id_artikel_bez)),							new ST_CM__column_extenders.ExtSorte(maskContainer,this));
        
        this.add_segment_in_maskline(new FZ_MASK_ContainerSegmentInLine(this, new RB_lab()._tr("Ange./Kont."),	this.get_startAtom().getComp(FZ__CONST.f_keys.KOMBI_ANG_KON_LEFT.k()), 	FZ__CONST.COLUMN_WIDTH.KONTRAKT.get_column_span(), ld,ld)
        	._addLine(0, this.get_zielAtom().getComp(FZ__CONST.f_keys.KOMBI_ANG_KON_RIGHT.k()))	, 				null);
        
        this.add_segment_in_maskline(new FZ_MASK_ContainerSegmentInLine(this, new RB_lab()._tr("Menge"), 		grd_for_menge(this.get_startAtom().getComp(BEWEGUNG_ATOM.menge)),	 	FZ__CONST.COLUMN_WIDTH.MENGE.get_column_span(), ld,ld)
        	._addLine(0, grd_for_menge(this.get_zielAtom().getComp(BEWEGUNG_ATOM.menge)))						,new ST_CM__column_extenders.ExtMenge(maskContainer,this));
        
        this.add_segment_in_maskline(new FZ_MASK_ContainerSegmentInLine(this, new RB_lab()._tr("EH"), 			this.get_startAtom().getComp(FZ__CONST.f_keys.EINHEIT_LEFT.k()), 		FZ__CONST.COLUMN_WIDTH.EINHEIT.get_column_span(), ld,ld)
        	._addLine(0, this.get_zielAtom().getComp(FZ__CONST.f_keys.EINHEIT_RIGHT.k())),						null);
        
        
        this.add_segment_in_maskline(new FZ_MASK_ContainerSegmentInLine(this, new RB_lab()._tr("Preis/EH"), 
        		new E2_Grid()._s(2)
        		._a(this.get_startAtom().getComp(BEWEGUNG_ATOM.e_preis), new RB_gld()._ins(2,0,2,1)._span(2))
        		._a(new RB_lab("Pr. manuell"), ld)
        		._a(this.get_startAtom().getComp(BEWEGUNG_ATOM.manuell_preis), ld)	, 				
        				FZ__CONST.COLUMN_WIDTH.PREIS.get_column_span(), ld, ld)
        	
        		._addLine(0, new E2_Grid()._s(2)
                		._a(this.get_zielAtom().getComp(BEWEGUNG_ATOM.e_preis), new RB_gld()._ins(2,0,2,1)._span(2))
                		._a(new RB_lab("Pr. manuell"), ld)
                		._a(this.get_zielAtom().getComp(BEWEGUNG_ATOM.manuell_preis), ld))
        				,	null);
        
        this.add_segment_in_maskline(new FZ_MASK_ContainerSegmentInLine(this, new RB_lab()._tr("Betrag"), 		this.get_startAtom().getComp(BEWEGUNG_ATOM.gesamtpreis), 				FZ__CONST.COLUMN_WIDTH.BETRAG.get_column_span(), ld,ld)
        	._addLine(0, this.get_zielAtom().getComp(BEWEGUNG_ATOM.gesamtpreis)), 								null);
        
        this.add_segment_in_maskline(new FZ_MASK_ContainerSegmentInLine(this, new RB_lab()._tr("Aktionen"), 	this.get_button_grid(), 												FZ__CONST.COLUMN_WIDTH.AKTION.get_column_span(), ld,ld),	null);
    }
    
    



	public MyE2_Button get_bt_save_and_close() {
		return bt_save_and_close;
	}


	public MyE2_Button get_bt_save_and_next() {
		return bt_save_and_next;
	}


	public MyE2_Button get_bt_abbruch() {
		return bt_abbruch;
	}
    		

	@Override
	public Component render_type_label() throws myException {
		return ENUM_VEKTOR_TYP.S.get_vektor_typ_label();
	}

	
	
	@Override
	public Component fill_button_grid() throws myException {
		
		//den status feststellen
		MASK_STATUS stat = this.find_actual_mask_status();
		
		this.button_grid._clear()._setSize(20,20,20,20);
		
		
		if (stat==MASK_STATUS.NEW) {
			this.button_grid._a(this.bt_PopUpZusatz)._a(this.bt_save_and_next)._a(this.bt_save_and_close)._a(this.bt_abbruch);
		} else if (stat==MASK_STATUS.EDIT) {
			this.button_grid._a(this.bt_PopUpZusatz)._a(this.bt_save_and_close)._a(this.bt_save_and_reopen_as_view)._a(this.bt_abbruch);
		} else if (stat==MASK_STATUS.VIEW) {
			this.button_grid._a(new MyE2_Label(E2_ResourceIcon.get_RI("empty25.png")))._a(this.bt_open4Edit)._a(new MyE2_Label(E2_ResourceIcon.get_RI("empty25.png")))._a(this.bt_abbruch);
		} else {
			this.button_grid._a(this.bt_abbruch);
		}
		
		return this.button_grid;
	}

	
	
	
	
	
	
	
    private class ownActionCloseLine extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			ST_CM__Collector  oThis = ST_CM__Collector.this;
			FZ_MASK_MaskModulContainer cont = (FZ_MASK_MaskModulContainer)oThis.rb_get_belongs_to();
			
			if (cont.rb_hm_component_map_collector().size()==1) {
				cont.CLOSE_AND_DESTROY_POPUPWINDOW(false);
			}  else {
				RB_K keyToRemove = null;
				for (Entry<RB_K, RB_ComponentMapCollector> e: cont.rb_hm_component_map_collector().entrySet()) {
					if (e.getValue()==oThis) {
						keyToRemove=e.getKey();
						break;
					}
				}
				//cont.rb_hm_component_map_collector().remove(keyToRemove);
				cont.rb_remove(keyToRemove);
			}
			cont.rebuild_container_grid();
		}
    }

	
	
	
	
	   private class ownActionSave extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				
				ST_CM__Collector oThis = ST_CM__Collector.this;
				
				MASK_STATUS  stat = oThis.find_actual_mask_status();

				bibMSG.add_MESSAGE(oThis.rb_COMPLETE_SAVE_CYCLE(true));
				
				
				if (bibMSG.get_bIsOK()) {
					Rec20 rec_vektor =oThis.get_vektor().getRbDataObjectActual().rec20();
					String id_vektor_saved = rec_vektor.is_newRecordSet()?rec_vektor.get_rec_after_save_new().get_key_value():rec_vektor.get_key_value();
					if (stat==MASK_STATUS.NEW) {
						// falls neueingabe, dann die id der liste zufuegen und seite refresh
						// id_vektor_to_reload = oThis.rb_Actual_DataobjectCollector().get_LastWrittenNewID(_TAB.bewegung_vektor);
						if (S.isFull(id_vektor_saved)) {
							oThis.naviList.ADD_NEW_ID_TO_ALL_VECTORS_IN_FRONT(bibVECTOR.get_Vector(id_vektor_saved));
							oThis.naviList._REBUILD_ACTUAL_SITE(id_vektor_saved);
						} 
					} else {
						oThis.naviList.Refresh_ComponentMAP(id_vektor_saved, E2_ComponentMAP.STATUS_VIEW);
					}
				}
			}
	    }

	   
	   /**
	    * actionagent, laesst ein wiederoeffnen einer zeile in einem beliebigen zustand zu
	    * @author martin
	    *
	    */
	   private class ownActionReopen extends XX_ActionAgent {
		   private MASK_STATUS  f_newStatus = null;
		   
		   
			public ownActionReopen(MASK_STATUS newStatus) {
				super();
				this.f_newStatus = newStatus;
			}


			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				ST_CM__Collector oThis = ST_CM__Collector.this;
				FZ_MASK_MaskModulContainer  cont = (FZ_MASK_MaskModulContainer)oThis.rb_get_belongs_to();
				oThis.re_open(this.f_newStatus);
				cont.rebuild_container_grid();
			}
	    }
   

	   @Override
	   public void re_open(MASK_STATUS reopen_status) throws myException {
			MASK_STATUS  status_old = this.find_actual_mask_status();
			
			String id_vektor_to_reload = null;
			
			if (status_old==MASK_STATUS.NEW) {
				//falls neueingabe, dann die id der liste zufuegen und seite refresh
				id_vektor_to_reload = this.rb_Actual_DataobjectCollector().get_LastWrittenNewID(_TAB.bewegung_vektor);
			} else {
				id_vektor_to_reload = ((RECORD_BEWEGUNG_VEKTOR)this.rb_Actual_DataobjectCollector().get(this.master_key.k_vektor()).get_RecORD()).ufs(BEWEGUNG_VEKTOR.id_bewegung_vektor);
			}
			
			
			__ST_MASTER_KEY my_key = this.master_key;
			my_key.set_mask_status(reopen_status);
			ST_DO__Collector  dataColl = new ST_DO__Collector(id_vektor_to_reload, master_key);
			bibMSG.add_MESSAGE(this.rb_COMPLETE_FILL_CYCLE(dataColl));
	   
	   }
	    
	    
	    private class ownActionAddNewLine extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				
				FZ_MASK_MaskModulContainer cont = ((FZ_MASK_MaskModulContainer)ST_CM__Collector.this.rb_get_belongs_to());
				
				ST_CM__Collector col = cont.add_new_ST();
				cont.rebuild_container_grid();
				col.set_focus_to_first_in_line();
				
			}
	    }


	    private class ownActionFocusLastComponentMapCollector extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				FZ_MASK_MaskModulContainer mask = (FZ_MASK_MaskModulContainer)ST_CM__Collector.this.rb_get_maskcontainer_i_belong_to();
				((ST_CM__Collector)mask.get_last_ComponentMapCollector()).set_focus_to_first_in_line();
			}
	    }
	    
	    

	    @Override
	    //den status feststellen
	    public MASK_STATUS  find_actual_mask_status() throws myException {
			return this.rb_Actual_DataobjectCollector().get(this.master_key).rb_MASK_STATUS();
	    }




		@Override
		public void set_focus_to_first_in_line() throws myException {
			//ersten vektor suchen
			ST_CM__Collector oThis = ST_CM__Collector.this;

		    ST_CM_Atom   		mapAtom =  		(ST_CM_Atom) 		this.get(oThis.master_key.k_vektor_pos_left__atom_left());
		    RB_date_selektor 	datumsblock = 	(RB_date_selektor) mapAtom.getComp(BEWEGUNG_ATOM.leistungsdatum.fk());
		    bibALL.setFocus(datumsblock.get_oTextField());
		}



		@Override
		public FZ_MASK_MaskModulContainer get_my_maskContainer() throws myException {
			return (FZ_MASK_MaskModulContainer)this.rb_get_maskcontainer_i_belong_to();
		}


		@Override
		public __ST_MASTER_KEY get_master_key() {
			return master_key;
		}

		
		public ST_CM_Station get_startStationLeft() {
			return  (ST_CM_Station)this.rb_get_ComponentMAP(this.master_key.k_vektor_pos_left__atom_left__station_start());
		}

		
		public ST_CM_Station get_zielStationRight() {
			return  (ST_CM_Station)this.rb_get_ComponentMAP(this.master_key.k_vektor_pos_right__atom_right__station_ziel());
		}
	
		public ST_CM_Atom get_startAtom() {
			return  (ST_CM_Atom)this.rb_get_ComponentMAP(this.master_key.k_vektor_pos_left__atom_left());
		}
		
		public ST_CM_Atom get_zielAtom() {
			return  (ST_CM_Atom)this.rb_get_ComponentMAP(this.master_key.k_vektor_pos_right__atom_right());
		}
		
		public CM_Vektor get_vektor() {
			return  (CM_Vektor)this.rb_get_ComponentMAP(this.master_key.k_vektor());
		}

		


		@Override
		public Vector<FZ_MASK_ContainerSegmentInLine> get_VectorOfSegments_in_maskline() {
			return this.v_segments;
		}



		@Override
		public void set_actual_opened_segmentObject(FZ_MASK_ContainerSegmentInLine opened_segment) {
			this.actual_open_segment=opened_segment;
		}



		@Override
		public FZ_MASK_ContainerSegmentInLine get_actual_opened_segmentObject() {
			return this.actual_open_segment;
		}

		@Override
		public E2_Grid get_button_grid() {
			return button_grid;
		}


		@Override
		public E2_Grid get_status_grid() {
			return status_grid;
		}



		@Override
		public KEY_VEKT get_key_vektor() {
			return this.master_key.k_vektor();
		}


		@Override
		public int insets_for_hidden_addon_block() {
			return 2;
		}


//		@Override
//		public Object create_hidden_addon_block() throws myException {
//	    	Rec20_vektor  v = new Rec20_vektor(this.rb_Actual_DataobjectCollector().get(this.get_master_key().k_vektor()).rec20());
//			return v.generate_info_grid_interne_buchungen();
//		}
//
//		
//
//		@Override
//		public void render_hidden_addon_block(Object object_from_check) throws myException {
//			Color col_back = 		this.get_my_maskContainer().get_color_for_closed_block();
//			E2_Grid mask_grid =		this.get_my_maskContainer().mask_grid();
//
//			if (object_from_check!=null) {
//				E2_Grid  addons = (E2_Grid)object_from_check;
//				
//				mask_grid._startLine(new RB_gld()._col(col_back)._span(this.insets_for_hidden_addon_block()))._endLine(addons, new RB_gld()._col(new E2_ColorLLight())._span(12));
//			}
//		}

		private E2_Grid grd_for_menge(Component oComp){
			return new E2_Grid()._setSize(50, 100)._a("")._a(oComp, new RB_gld()._ins(3,0,0,0));
		}

		
}
 
