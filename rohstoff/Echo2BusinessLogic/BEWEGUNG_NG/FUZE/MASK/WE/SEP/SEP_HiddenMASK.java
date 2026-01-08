package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WE.SEP;

import java.math.BigDecimal;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR_POS;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_VEKTOR_POS;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.FZ_MASK_MaskModulContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WE.WE_CM_Atom;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WE.WE_CM__Collector;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WE.WE_DO_Atom;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.SORTENWECHSEL.SW_position;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.SORTENWECHSEL.SW_stack;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._RECS.Rec20_vektor;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_BEWEGUNG_ATOM_ext;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_BEWEGUNG_VEKTOR_POS_ext;


public class SEP_HiddenMASK extends E2_BasicModuleContainer {

	private E2_Grid  					internalGrid = new E2_Grid();
	
	private MyBigDecimal  				baseQuantity = null; 			//basis-menge
	private MyBigDecimal  				sumSubQuantity = null; 			//verteil-mengen-summe
	
	private SEP_HiddenRowCollection    	row_collection = new SEP_HiddenRowCollection();
	
	private MyE2_Button     			bt_save = (MyE2_Button)new MyE2_Button(new MyE2_String("Speichern"))._bordBlack()._backDDark()._center()._font(new E2_FontBold(2))._aaa(new ownActionSaveMask());
	private MyE2_Button     			bt_cancel = (MyE2_Button)new MyE2_Button(new MyE2_String("Abbruch"))._bordBlack()._backDDark()._center()._font(new E2_FontBold(2))._aaa(new ownActionCancelMask());
	
	private RECORD_BEWEGUNG_ATOM_ext 	recAtom = null;
	private FZ_MASK_MaskModulContainer  f_mask =null;
	
	/**
	 * 
	 * @param p_cm_Atom  MUSS das atom von lieferant ins lager sein
	 * @param mask
	 * @throws myException
	 */
	public SEP_HiddenMASK(WE_CM_Atom  p_cm_Atom, FZ_MASK_MaskModulContainer mask) throws myException {
		super();
		
		if (p_cm_Atom.getRbDataObjectActual().get_RecORD()==null) {
			throw new myException("Error finding dataObject !!");   			//sollte nicht vorkommen
		}
		
		//jetzt die relevante menge raussuchen, wenn es eine unterschiedliche lagermenge gibt, dann ist die gueltig
		WE_DO_Atom do_atom = (WE_DO_Atom)p_cm_Atom.getRbDataObjectActual();
		
//		
//		Rec20 			r_vektor_pos = do_atom.get_rec20().get_up_rec20(BEWEGUNG_VEKTOR_POS.id_bewegung_vektor_pos);
//		Rec20_vektor 	r_vektor = new Rec20_vektor(r_vektor_pos.get_up_rec20(BEWEGUNG_VEKTOR.id_bewegung_vektor));
	
		this.baseQuantity = do_atom.get_relevant_lager_menge();
		
		if (this.baseQuantity==null || this.baseQuantity.isNotOK()) {
			throw new myException(this,"Error: Cannot find relevant MENGE!");
		}

		
//		RECORD_BEWEGUNG_ATOM  p_atom = (RECORD_BEWEGUNG_ATOM)do_atom.get_RecORD();
		this.recAtom = new RECORD_BEWEGUNG_ATOM_ext((RECORD_BEWEGUNG_ATOM)do_atom.get_RecORD());
		
		this.f_mask = mask;
		
		this.add(this.internalGrid, E2_INSETS.I(2,2,2,2));
		
//		this.baseQuantity = new MyBigDecimal(recAtom.bd(BEWEGUNG_ATOM.menge, BigDecimal.ZERO, 3),3);
		this.sumSubQuantity=new MyBigDecimal(BigDecimal.ZERO, 3);

		MyE2_MessageVector  mv_sammler = new MyE2_MessageVector();
		
		if (this.recAtom.is_atom_WE_MAIN()) {
			
			Vector<RECORD_BEWEGUNG_VEKTOR_POS>  v_hiddensep = this.recAtom.vektor_pos_HIDDENSEP();
			for (RECORD_BEWEGUNG_VEKTOR_POS v: v_hiddensep) {
				RECORD_BEWEGUNG_ATOM  at = new RECORD_BEWEGUNG_VEKTOR_POS_ext(v).get_atom_sw_lager_to_lager();
				SEP_HiddenRow row = new SEP_HiddenRow(this);
				row.get_tf_menge().setText(at.fs(BEWEGUNG_ATOM.menge_netto));
				row.get_searchArtBez().rb_set_db_value_manual(at.ufs(BEWEGUNG_ATOM.id_artikel_bez), false,false);
				this.row_collection.add(row);
				
				mv_sammler.add_MESSAGE(row.set_row_closed());
			}

			if (mv_sammler.get_bIsOK()) {
				this._render_list();
				
				this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(700), new Extent(500), new MyE2_String("Aufteilung eines Wareneingangs ..."));
			} else {
				bibMSG.add_MESSAGE(mv_sammler);
			}
		} else {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Prüfung des Atoms zeigt keine korrekte WE-Struktur!!!")));
		}
		
	}

	
	public SEP_HiddenMASK _add_new_Row() throws myException {
		this.row_collection.add(new SEP_HiddenRow(this));
		this._render_list();
		
		return this;
	}
	
	
	
	public void _render_list() throws myException {
		
		this.internalGrid.removeAll();
		this.internalGrid._setSize(370,200,25,25,25);
		
		Insets  i_std = E2_INSETS.I(2,2,2,2);
		
		this.internalGrid	._a(new MyE2_Label(new MyE2_String("Aufteilen der Warenbewegung (intern)"),	MyE2_Label.STYLE_TITEL_BIG()),new RB_gld()._span(5)._ins(E2_INSETS.I(2,2,2,10)))
							
							._a(new MyE2_Label(new MyE2_String("Basismenge"),							MyE2_Label.STYLE_NORMAL_BOLD()),new RB_gld()._span(1)._ins(i_std))
							._a(new MyE2_Label(this.baseQuantity.get_FormatedRoundedNumber(3),			new E2_FontBold()), new RB_gld()._span(1)._right_top()._ins(i_std))
							._a(new MyE2_Label("",														new E2_FontBold()), new RB_gld()._span(3)._ins(i_std))
							._a(new MyE2_Label(new MyE2_String("Summe verteilt"),						MyE2_Label.STYLE_NORMAL_BOLD()),new RB_gld()._span(1)._ins(i_std))
							._a(new MyE2_Label(this.sumSubQuantity.get_FormatedRoundedNumber(3),		new E2_FontBold()), new RB_gld()._span(1)._right_top()._ins(i_std))
							._a(new MyE2_Label("",														new E2_FontBold()), new RB_gld()._span(3)._ins(i_std))
							
							._a(new MyE2_Label("",														new E2_FontBold()), new RB_gld()._span(5)._ins(E2_INSETS.I(2,2,2,10)))
							;
		
		
		RB_gld glst = new RB_gld()._ins(E2_INSETS.I(2,2,2,2))._right_top();
		
		int i=0;
		for (SEP_HiddenRow r: this.row_collection) {
			this.internalGrid	._a(r.get_searchArtBez(),	glst)._a(r.get_tf_menge(),		glst);
			if (r.isStatusOpen()) {
				this.internalGrid._a(r.get_bt_close(),	glst)._a(r.get_bt_delete(), glst);
			}else {
				this.internalGrid._a(r.get_bt_open(),	glst)._a(r.get_bt_delete(), glst);
			}
			if (i==this.row_collection.size()-1) {
				this.internalGrid._a(r.get_bt_add(),	glst);
			} else {
				this.internalGrid._a(new MyE2_Label(""), glst);
			}
			i++;
		}

		this.internalGrid	._a(new E2_Grid()._a(this.bt_save)._a(this.bt_cancel, new RB_gld()._ins(E2_INSETS.I(10,0,0,0))), new RB_gld()._span(5)._ins(E2_INSETS.I(2,10,2,2))._left_top());
	}
	
	
	
	private class ownActionSaveMask extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			SEP_HiddenMASK oThis = SEP_HiddenMASK.this;
			
			if (oThis.row_collection.is_valid()) {
				
				oThis.sumSubQuantity = new MyBigDecimal(row_collection.calculate_liste(bibMSG.MV()),3);
				if (bibMSG.get_bIsOK()) {
					if (oThis.baseQuantity.get_bdWert().compareTo(oThis.sumSubQuantity.get_bdWert())<0) {
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Sie können nicht mehr verteilen als die Basismenge !")));
					} else {
						Vector<String> v_sql = new Vector<>();
						
						//die alten verteiler-atome loeschen
						RECORD_BEWEGUNG_VEKTOR 				v = 		oThis.recAtom.get_UP_RECORD_BEWEGUNG_VEKTOR_id_bewegung_vektor();
						//sortierte arraylist
						Vector<RECORD_BEWEGUNG_VEKTOR_POS> 	v_pos =		v.get_DOWN_RECORD_LIST_BEWEGUNG_VEKTOR_POS_id_bewegung_vektor(null,BEWEGUNG_VEKTOR_POS.posnr.fn(),true).get_v_records();
						
						for (RECORD_BEWEGUNG_VEKTOR_POS bvp: v_pos) {
							RECORD_BEWEGUNG_VEKTOR_POS_ext bvp_e = new RECORD_BEWEGUNG_VEKTOR_POS_ext(bvp);
							
							if (bvp_e.is_correct_WE_HIDDEN_SEP()) {
								v_sql.addAll(bvp_e.sql_to_delete_vektor_pos_and_atom());
							}
						}
	
						
						SW_stack  sw = new SW_stack();
						
						//jetzt alle ausgefuellten rows beschaffen
						SEP_HiddenRowCollection collection_full_rows = row_collection.get_full_lines();
						
						//vektor-pos-nummer hier fortlaufend vergeben (ab 2, da die erste immer die hauptpos ist)this.searchArtBez.get_tf_search_input().getText()
						int id_vektor_pos_nummer = 3;
						for (SEP_HiddenRow r: collection_full_rows) {
							bibMSG.MV().add_MESSAGE(r.check_row());
							if (bibMSG.MV().get_bIsOK()) {
								SW_position sw_pos = new SW_position()	._set_id_artikel_bez_source(oThis.recAtom.ufs(BEWEGUNG_ATOM.id_artikel_bez))
																		._set_id_artikel_bez_target(r.get_searchArtBez().get_tf_search_input().getText())
																		._set_menge_umbuchung(r.get_tf_menge().getText())
																		._check();
								sw._add(sw_pos);
							}
						}
						
						if (bibMSG.MV().get_bIsOK()) {
							v_sql.addAll(sw.generate_sql_save_stack(	id_vektor_pos_nummer
																		, oThis.recAtom.get_StationZiel().ufs(BEWEGUNG_STATION.id_adresse)
																		, oThis.recAtom.get_StationZiel().ufs(BEWEGUNG_STATION.id_adresse_besitzer)
																		, oThis.recAtom.ufs(BEWEGUNG_ATOM.id_bewegung_vektor)
																		, oThis.recAtom.fs(BEWEGUNG_ATOM.e_preis)));
							
							bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(v_sql, true));
							if (bibMSG.MV().get_bIsOK()) {
								oThis.f_mask.rebuild_container_grid();
								oThis.CLOSE_AND_DESTROY_POPUPWINDOW(true);
							}
						}
					}
				}
			} else {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es wurden Fehler in den erfaßten Daten gefunden, bitte die Zeilen komplett ausfüllen !")));
			}
		}
	}
	
	private class ownActionCancelMask extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			SEP_HiddenMASK oThis = SEP_HiddenMASK.this;
			oThis.CLOSE_AND_DESTROY_POPUPWINDOW(true);
		}
	}

	
	public SEP_HiddenRowCollection get_rows() {
		return row_collection;
	}


	
	public RECORD_BEWEGUNG_ATOM get_recAtom() {
		return recAtom;
	}


	public void set_sumSubQuantity(MyBigDecimal p_sumSubQuantity) {
		this.sumSubQuantity = p_sumSubQuantity;
	}


	public SEP_HiddenRowCollection get_row_collection() {
		return row_collection;
	}



}
