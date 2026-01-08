package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import java.util.Vector;

import nextapp.echo2.app.Button;
import nextapp.echo2.app.CheckBox;
import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.BasicInterfaces.IF_FontandText;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.BETA.RASTER.Raster;
import panter.gmbh.Echo2.RB.COMP.BETA.RASTER.RasterBtOpenClose;
import panter.gmbh.Echo2.RB.COMP.BETA.RASTER.RasterRow;
import panter.gmbh.Echo2.RB.COMP.BETA.RASTER.RasterRowCell;
import panter.gmbh.Echo2.RB.COMP.BETA.RASTER.RasterRowCells;
import panter.gmbh.Echo2.RB.COMP.BETA.RASTER.RasterRows;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.E2_SelectAllNoneInvert_New;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON_TRAKT;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.__RECORD_VPOS_TPA_FUHRE_EXT;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_VPOS_KON;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_VPOS_TPA_FUHRE;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_VPOS_TPA_FUHRE_ORT;

public class KFIX_K_M_masklist_position_raster_grid extends Raster{

	private MyLong 						id_vkopf_kon = null;

	private KFIX_K_M_masklist_position 	position = null ;

	private boolean 					is_ek = false;

	private RB_gld 						left_al 	= new RB_gld()._ins(1)._left_top();
	private RB_gld 						right_al = new RB_gld()._ins(1)._right_top();
	private RB_gld 						mid_al 	= new RB_gld()._ins(1)._center_top();
	private String 						position_query = null;
	private Vector<RB_cb> 				vCb = new Vector<>();
	private Vector<String> 				vIdsSelected = new Vector<>();

	
	public KFIX_K_M_masklist_position_raster_grid(KFIX_K_M_masklist_position p_parent, String p_where_clause) throws myException {
		super();

		this.position		= p_parent;

		this.id_vkopf_kon 	= new MyLong(this.position.get_id_vkopf_kon());

		this.is_ek 			= this.position.is_ek_kontrakt();

		this.vCb.clear();
		
		this.position_query = p_where_clause;

		this._setRasterColCount(46);
		this._setRasterColWidth(25);
		this._initRaster();
		this._buildGrid();
		this._bo_ddd();
		this.setRowKey("KOPF"+ "#" + id_vkopf_kon.get_cUF_LongString());
	}

	public void _buildGrid() throws myException {
		this._clear();
		this.generateTitleRasterRowCells();
		this.generateRasterRows(this);
		this.render();
	}

	@Override
	public Color getBackgroundColor() {
		return null;
	}

	@Override
	public void generateTitleRasterRowCells() throws myException {
		RasterRowCells list = new RasterRowCells();

		ownAuswahlButton selAllNoneInvert_button = new ownAuswahlButton();
		
		//title-layoutddata
		RB_gld ld_tl = new RB_gld()._left_mid()._ins(2, 2, 2, 2);
		RB_gld ld_tm = new RB_gld()._center_mid()._ins(2, 2, 2, 2);
		RB_gld ld_tr = new RB_gld()._right_mid()._ins(2, 2, 2, 2);
		
		
		list.put(new RasterRowCell("new", 			selAllNoneInvert_button, 					this)._setColSpan(1));											//view
		list.put(new RasterRowCell("edit", 			new RB_lab(), 								this)._setColSpan(1));									//edit
		list.put(new RasterRowCell("del", 			new RB_lab(), 								this)._setColSpan(1));									//delete
		list.put(new RasterRowCell("up", 			new RB_lab()._t("S")._set_ld(ld_tm),		this)._setColSpan(2));									//up
		//list.put(new RasterRowCell("lbl", 			new RB_lab(), 								this)._setColSpan(1));									//indic
		list.put(new RasterRowCell("lock", 			new RB_lab("A.")._set_ld(ld_tm),			this)._setColSpan(1));
		list.put(new RasterRowCell("A/E", 			new RB_lab("A/E")._set_ld(ld_tm),			this)._setColSpan(2));
		//list.put(new RasterRowCell("clearing", 		new RB_lab(), 								this)._setColSpan(1));									//clear
		list.put(new RasterRowCell("ausklapp", 		new ownBtOpenClose(), 						this)._setColSpan(1));
		list.put(new RasterRowCell("pos", 			new RB_lab("Pos.")._set_ld(ld_tm), 			this)._setColSpan(2));
		list.put(new RasterRowCell("gueltigkeit", 	new RB_lab("Gültig von-bis")._set_ld(ld_tl), this)._setColSpan(6));
		list.put(new RasterRowCell("anr12",			new RB_lab("ANR1-2")._set_ld(ld_tl),		this)._setColSpan(3));
		list.put(new RasterRowCell("artbez1", 		new RB_lab("Artikelbezeichnung 1")._set_ld(ld_tl), 			this)._setColSpan(12));
		list.put(new RasterRowCell("anzahl", 		new RB_lab("Anzahl")._set_ld(ld_tr), 		this)._setColSpan(4));
		list.put(new RasterRowCell("ePreis", 		new RB_lab("E-Preis")._set_ld(ld_tr), 		this)._setColSpan(4));
		list.put(new RasterRowCell("gPreis", 		new RB_lab("G-Preis")._set_ld(ld_tr), 		this)._setColSpan(4));

		this.setTitelRasterRowCells(list);
	}

	@Override
	public void buildRasterRows(RasterRow callingRasterRow) throws myException {
		this.getRasterRows().clear();
		
		this.vCb.clear();
		
		RecList20 rl_position = new RecList20(_TAB.vpos_kon)._fill(this.position_query);

		for(Rec20 r : rl_position){
			this.getRasterRows().put(new RR_position(r.get_myLong_dbVal(VPOS_KON.id_vpos_kon), this));
		}
	}

	
	
	private class RR_position extends Rec20_VPOS_KON implements RasterRow{

		private String          key = null;
		private RasterRow       callingRasterRow = null;
		private RasterRowCells 	cellList = 						new RasterRowCells();  
		private RasterRows  	daughterRowList = 				new RasterRows();
		private MyLong    		pos_id = null;
		private RecList20 		rl_fu = null;
		private RecList20 		rl_fuo = null;

		
		public RR_position(MyLong id_vpos_kon, RasterRow p_callingRasterRow) throws myException {
			super();

			this._fill_id(id_vpos_kon.get_cUF_LongString());

			this.key = 					id_vpos_kon.get_cUF_LongString();
			this.callingRasterRow = 	p_callingRasterRow;
			this.pos_id = 				id_vpos_kon;

			this.generateCellList(p_callingRasterRow);
			this.generateRasterRows(callingRasterRow);

		}

		@Override
		public RasterRowCells getCellList() throws myException {
			return this.cellList;
		}

		@Override
		public int getInsetColCount() throws myException {
			return 0;
		}

		@Override
		public RasterRows getRasterRows() throws myException {
			return this.daughterRowList;
		}

		@Override
		public void buildRasterRows(RasterRow callingRasterRow) throws myException {
			this.getRasterRows().clear();

			if(is_ek){
				this.rl_fu = new RecList20(_TAB.vpos_tpa_fuhre)._fill("SELECT * FROM JT_VPOS_TPA_FUHRE WHERE " + VPOS_TPA_FUHRE.id_vpos_kon_ek.fn()+"="+this.pos_id.get_lValue());
			}else{
				this.rl_fu = new RecList20(_TAB.vpos_tpa_fuhre)._fill("SELECT * FROM JT_VPOS_TPA_FUHRE WHERE " + VPOS_TPA_FUHRE.id_vpos_kon_vk.fn()+"="+this.pos_id.get_lValue());
			}

			this.rl_fuo = new RecList20(_TAB.vpos_tpa_fuhre_ort)._fill("SELECT * FROM JT_VPOS_TPA_FUHRE_ORT WHERE "+ VPOS_TPA_FUHRE_ORT.id_vpos_kon.fn() +"="+this.pos_id.get_lValue());

			if(this.cellList.get("KEYBT").getComponent4Cell() instanceof RasterBtOpenClose){
				if (((RasterBtOpenClose)this.cellList.get("KEYBT").getComponent4Cell()).isOpen()) {
					daughterRowList.put(new fuhre_header_list(callingRasterRow));

					if(rl_fu.size()==0 && rl_fuo.size()==0){
						daughterRowList.put(new no_fuhre(callingRasterRow, "< Keine Fuhre >"));
					}
					else{
						for (Rec20 r_fu: rl_fu) {
							if(is_ek){
								daughterRowList.put(new RR_fuhre(r_fu.get_myLong_dbVal(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre), callingRasterRow));
							}else{
								daughterRowList.put(new RR_fuhre(r_fu.get_myLong_dbVal(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre), callingRasterRow));
							}
						}
						for(Rec20 r_fuo: rl_fuo){
							daughterRowList.put(new RR_fuo(r_fuo.get_myLong_dbVal(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre_ort), callingRasterRow));
						}
					}

					if( !(rl_fu.size()==0 && rl_fuo.size()==0) ){
						daughterRowList.put(new no_fuhre(callingRasterRow, "", "END_ROW"));
						daughterRowList.put(new RR_fuhre_gesamt(callingRasterRow, pos_id));
						daughterRowList.put(new no_fuhre(callingRasterRow, "", "END_ROW2"));
					}
				}
			}
		}

		@Override
		public void buildCellList() throws myException {
			VEK<MyE2_Button> v_sammle = new VEK<>();
			
			this.cellList.clear();

			Rec20 rec_vkopf = this.get_up_Rec20(VPOS_KON.id_vkopf_kon, VKOPF_KON.id_vkopf_kon, false);
			
			Rec20 rec_vpos_kon = this.get_down_reclist20(VPOS_KON_TRAKT.id_vpos_kon, "", "").get(0);

			KFIX_P_L_BT_Position_Anlage_Management anlage_bt	= new KFIX_P_L_BT_Position_Anlage_Management(null, this);
			anlage_bt.setOrientation(0);

			RB_cb cb = new RB_cb();
			cb.EXT().set_C_MERKMAL(this.get_ufs_dbVal(VPOS_KON.id_vpos_kon));
			vCb.add(cb);

			KFIX_P_M_BT_Delete 		del__bt 	= new KFIX_P_M_BT_Delete(this.get_fs_dbVal(VPOS_KON.id_vpos_kon), position);
			
			KFIX_P_L_BT_Reihenfolge_aenderung_Fixierung reihen_bt = new KFIX_P_L_BT_Reihenfolge_aenderung_Fixierung(rec_vkopf,position);
			reihen_bt.setLayoutData(new RB_gld()._center_mid());
			
			KFIX__BT_Lock_unlock_position lock_unlock_bt = new KFIX__BT_Lock_unlock_position(
					this.get_ufs_dbVal(VPOS_KON.id_vpos_kon), 
					rec_vpos_kon.is_yes_db_val(VPOS_KON_TRAKT.abgeschlossen),
					this.get_ufs_dbVal(VPOS_KON.position_typ).equals(myCONST.VG_POSITION_TYP_ARTIKEL),
					new RB_gld()._ins(1)._center_top()._col(Color.GREEN),
					new RB_gld()._ins(1)._center_top()._col(Color.RED),
					""
					);

			XX_ActionAgent agentBefore = new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					KFIX_K_M_masklist_position_raster_grid.this.vIdsSelected.addAll(KFIX_K_M_masklist_position_raster_grid.this.get_selected_ids());
				}
			};
				
			XX_ActionAgent agentAfter = new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					KFIX_K_M_masklist_position_raster_grid.this._clearCheckboxes()._markCheckboxes(KFIX_K_M_masklist_position_raster_grid.this.vIdsSelected);
				}
			};
			
			new VEK<XX_ActionAgent>()._a(agentBefore);
			
			RasterBtOpenClose expand_bt = new RasterBtOpenClose(this.getRaster(),new VEK<XX_ActionAgent>()._a(agentBefore), new VEK<XX_ActionAgent>()._a(agentAfter));
			expand_bt.set_open(false);
			expand_bt.setLayoutData(mid_al);

			this.cellList = new RasterRowCells();

			//listen-layoutddata
			RB_gld ldl = new RB_gld()._left_mid()._ins(2, 2, 2, 2);
			RB_gld ldm = new RB_gld()._center_mid()._ins(2, 2, 2, 2);
			RB_gld ldr = new RB_gld()._right_mid()._ins(2, 2, 2, 2);

			
			String anr12_key = VPOS_KON.anr1.fn()+VPOS_KON.anr2.fn();
			String posnr_key = VPOS_KON.positionsnummer.fn();

			RasterRowCell cell_bt_del 		= 	null;
			RasterRowCell cell_bt_poPos 	= 	null;
			RasterRowCell cell_bt_lock		= 	null;
			RasterRowCell cell_bt_ausklapp	=	null;

			E2_Grid gridEditView = new E2_Grid()._setSize(20,20)._a(new KFIX_P_M_BT_List2Mask(false,this.get_ufs_dbVal(VPOS_KON.id_vpos_kon),KFIX_K_M_masklist_position_raster_grid.this))
																._a(v_sammle._ar(new KFIX_P_M_BT_List2Mask(true,this.get_ufs_dbVal(VPOS_KON.id_vpos_kon),KFIX_K_M_masklist_position_raster_grid.this)));
			
			//RasterRowCell cell_lbl 			= 	new RasterRowCell("infos" , 				new RB_lab(),																this)._setColSpan(1);
			RasterRowCell cell_selection_cb = 	new RasterRowCell("selector", 				cb._set_ld(ldm),														this)._setColSpan(1);
			RasterRowCell cell_bt_edit 		= 	new RasterRowCell("edit", 					gridEditView._set_ld(ldm) ,												this)._setColSpan(1);
			RasterRowCell cell_bt_ae	 	= 	new RasterRowCell("anlage",					anlage_bt._set_ld(ldm),													this)._setColSpan(2);
			//RasterRowCell cell_bt_clearing	= 	new RasterRowCell("clearing", 				new RB_lab(),	 															this)._setColSpan(1);
			RasterRowCell cell_posNr		= 	new RasterRowCell(posnr_key ,	 			new selBt(this.get_fs_dbVal(VPOS_KON.positionsnummer),cb)._set_ld(ldm),	this)._setColSpan(2);
			RasterRowCell cell_gueltigkeit	= 	new RasterRowCell("gueltigkeit",			new selBt(this.getGueltigkeit(),cb)._set_ld(ldl), 						this)._setColSpan(6);
			RasterRowCell cell_anr12		= 	new RasterRowCell(anr12_key, 				new selBt(this.getAnr12(),cb)._set_ld(ldl), 							this)._setColSpan(3);
			RasterRowCell cell_artbez1		= 	new RasterRowCell(VPOS_KON.artbez1.fn(),	new selBt(this.get_fs_dbVal(VPOS_KON.artbez1),cb)._set_ld(ldl),			this)._setColSpan(12);
			RasterRowCell cell_anzahl		= 	new RasterRowCell(VPOS_KON.anzahl.fn()	,	new selBt(this.getMengeMitEinh(3), cb)._set_ld(ldr),			  		this)._setColSpan(4);
			RasterRowCell cell_ePreis		= 	new RasterRowCell(VPOS_KON.einzelpreis.fn(),new selBt(this.getPreisMitEinheiten_FW(),cb)._set_ld(ldr),				this)._setColSpan(4);
			RasterRowCell cell_gPreis		= 	new RasterRowCell(VPOS_KON.gesamtpreis.fn(),new selBt(this.getGesPreisMitEinheiten_FW(), cb)._set_ld(ldr),			this)._setColSpan(4);
			
			if(this.is_no_last_val(VPOS_KON.deleted)){
				del__bt.setLayoutData(new RB_gld()._center_mid());
				
				cell_bt_del 		= 	new RasterRowCell("delete", 		v_sammle._ar(del__bt),			this)._setColSpan(1);	
				cell_bt_poPos 		= 	new RasterRowCell("up_pos" ,		v_sammle._ar(reihen_bt), 		this)._setColSpan(2);
				cell_bt_lock		= 	new RasterRowCell("lock_unlock", 	v_sammle._ar(lock_unlock_bt),	this)._setColSpan(1);
				cell_bt_ausklapp	=	new RasterRowCell("KEYBT",			expand_bt._set_ld(ldm), 	 	this)._setStatic(true)._setColSpan(1);
			}else{
				cell_bt_del 		= 	new RasterRowCell("delete", 				new RB_lab(), 		this)._setColSpan(1);	
				cell_bt_poPos 		= 	new RasterRowCell("up_pos" ,				new RB_lab(), 		this)._setColSpan(2);
				cell_bt_lock		= 	new RasterRowCell("lock_unlock", 			new RB_lab(), 		this)._setColSpan(1);
				cell_bt_ausklapp	=	new RasterRowCell("KEYBT",					new RB_lab(), 		this)._setColSpan(1);
			}
			
			cellList.put(cell_selection_cb);
			cellList.put(cell_bt_edit);
			cellList.put(cell_bt_del);
			cellList.put(cell_bt_poPos);
			//cellList.put(cell_lbl);
			cellList.put(cell_bt_lock);
			cellList.put(cell_bt_ae);
			//cellList.put(cell_bt_clearing);
			cellList.put(cell_bt_ausklapp);
			cellList.put(cell_posNr);
			cellList.put(cell_gueltigkeit);
			cellList.put(cell_anr12);
			cellList.put(cell_artbez1);
			cellList.put(cell_anzahl);
			cellList.put(cell_ePreis);
			cellList.put(cell_gPreis);
			
			this.line_trough_when_deleted();
			
			MASK_STATUS  status_vpos_kon_mask = KFIX_K_M_masklist_position_raster_grid.this.position._find_componentMap_i_belong_to().getRbDataObjectActual().rb_MASK_STATUS();
			if (status_vpos_kon_mask.isStatusView()) {
				for (Button b: v_sammle) {
					b.setEnabled(false);
				}
			}
		}

		@Override
		public String getRowKey() {
			return this.key;
		}

		@Override
		public void setRowKey(String key) {
			this.key = key;
		}

		@Override
		public Raster getRaster() {
			return KFIX_K_M_masklist_position_raster_grid.this;
		}

		@Override
		public RasterRow getMotherRasterRow() {
			return this.callingRasterRow;
		}

		@Override
		public Color getBackgroundColor() {
			return new E2_ColorBase();
		}


		@SuppressWarnings("rawtypes")
		private void line_trough_when_deleted() throws myException{
			if(this.is_yes_db_val(VPOS_KON.deleted)){
				for (RasterRowCell c: this.getCellList().values()) {
					if (c.getComponent4Cell() instanceof IF_FontandText) {
						((IF_FontandText)c.getComponent4Cell())._fo_lineThrough();
					}
				}
			}
		}

	}

	private class selBt extends E2_Button {

		private RB_cb cb=null;

		public selBt(String text, RB_cb p_cb) {
			super();
			this._t(text);
			this.cb = p_cb;
			this._aaa(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					selBt.this.cb._toggle();
				}
			});
		}
		
	}
	

	private class fuhre_header_list implements RasterRow{

		private RasterRowCells 	cellList 	= 	new RasterRowCells();  

		private RasterRows		rasterRows 			= new RasterRows();
		private String 			key		  			= "FU_COLUMN_HEADER";
		private RasterRow       callingRasterRow 	= null;

		public fuhre_header_list(RasterRow p_callingRasterRow) throws myException {
			super();

			this.callingRasterRow = p_callingRasterRow;

			this.generateCellList(p_callingRasterRow);
			this.generateRasterRows(callingRasterRow);
		}

		@Override
		public RasterRowCells getCellList() throws myException {
			return this.cellList;
		}

		@Override
		public int getInsetColCount() throws myException {
			return 11;
		}

		@Override
		public RasterRows getRasterRows() throws myException {
			return rasterRows;
		}

		@Override
		public void buildRasterRows(RasterRow callingRasterRow) throws myException {
			RB_lab lbl_col_1 = new RB_lab()._t("Buchungsnr.");
			RB_lab lbl_col_2 =	new RB_lab()._t("Sorte");
			RB_lab lbl_col_3 = new RB_lab()._t("Plan Mge.");
			RB_lab lbl_col_4 =	new RB_lab()._t("Real Mge.");

			lbl_col_1._fsa(-1)._i().setLayoutData(new RB_gld()._ins(1)._left_top()._col(new E2_ColorDark()));
			lbl_col_2._fsa(-1)._i().setLayoutData(new RB_gld()._ins(1)._left_top()._col(new E2_ColorDark()));
			lbl_col_3._fsa(-1)._i().setLayoutData(new RB_gld()._ins(1)._right_top()._col(new E2_ColorDark()));
			lbl_col_4._fsa(-1)._i().setLayoutData(new RB_gld()._ins(1)._right_top()._col(new E2_ColorDark()));

			this.cellList.put(new RasterRowCell("fu_head_id", 	lbl_col_1, this)._setColSpan(8));
			this.cellList.put(new RasterRowCell("fu_head_sorte",lbl_col_2, this)._setColSpan(15));
			this.cellList.put(new RasterRowCell("fu_head_plan_menge",lbl_col_3, this)._setColSpan(4));	
			this.cellList.put(new RasterRowCell("fu_head_menge",lbl_col_4, this)._setColSpan(4));	
		}

		@Override
		public void buildCellList() throws myException {

		}

		@Override
		public String getRowKey() {
			return key;
		}

		@Override
		public void setRowKey(String key) {
		}

		@Override
		public Raster getRaster() {
			return null;
		}

		@Override
		public RasterRow getMotherRasterRow() {
			return null;
		}

		@Override
		public Color getBackgroundColor() {
			return null;
		}

	}

	private class no_fuhre implements RasterRow{

		private RasterRowCells 	cellList 	= 	new RasterRowCells();  

		private RasterRows		rasterRows 			= new RasterRows();
		private String 			key		  			= "NO_FUHRE";
		private RasterRow       callingRasterRow 	= null;
		private String msg = "";

		public no_fuhre(RasterRow p_callingRasterRow, String p_message) throws myException {
			super();

			this.callingRasterRow = p_callingRasterRow;

			this.msg = p_message;

			this.generateCellList(p_callingRasterRow);
			this.generateRasterRows(callingRasterRow);
		}

		public no_fuhre(RasterRow p_callingRasterRow, String p_message, String p_key) throws myException {
			this(p_callingRasterRow, p_message);
			this.key=p_key;
		}

		@Override
		public RasterRowCells getCellList() throws myException {
			return this.cellList;
		}

		@Override
		public int getInsetColCount() throws myException {
			return 11;
		}

		@Override
		public RasterRows getRasterRows() throws myException {
			return rasterRows;
		}

		@Override
		public void buildRasterRows(RasterRow callingRasterRow) throws myException {
			RB_lab lbl_col_3 =	new RB_lab()._t(msg)._i()._col_fore_dgrey();

			lbl_col_3.setLayoutData(new RB_gld()._ins(1)._center_top()._col(new E2_ColorBase()));

			this.cellList.put(new RasterRowCell("No_fuhre", 	lbl_col_3, this)._setColSpan(31));
		}

		@Override
		public void buildCellList() throws myException {

		}

		@Override
		public String getRowKey() {
			return key;
		}

		@Override
		public void setRowKey(String key) {
		}

		@Override
		public Raster getRaster() {
			return null;
		}

		@Override
		public RasterRow getMotherRasterRow() {
			return null;
		}

		@Override
		public Color getBackgroundColor() {
			return new E2_ColorBase();
		}

	}

	private class RR_fuhre extends Rec20 implements RasterRow{

		private RasterRowCells 	cellList = 						new RasterRowCells();  

		private String			key = null;

		private RasterRow 		callingRasterRow = null;

		private RasterRows		rasterRows = new RasterRows();

		private boolean 		is_ek = false;

		public RR_fuhre(MyLong l_uf__id_fuhre, RasterRow p_callingRasterRow) throws myException {
			super(_TAB.vpos_tpa_fuhre);

			this._fill_sql(new SEL().FROM(_TAB.vpos_tpa_fuhre).WHERE(new vgl(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre, l_uf__id_fuhre.get_cUF_LongString())).s());

			this.key = "FU#"+l_uf__id_fuhre.get_cUF_LongString();

			this.callingRasterRow = p_callingRasterRow;

			this.generateCellList(callingRasterRow);
			this.generateRasterRows(callingRasterRow);

		}

		@Override
		public RasterRowCells getCellList() throws myException {
			return this.cellList;
		}

		@Override
		public int getInsetColCount() throws myException {
			return 11;
		}

		@Override
		public RasterRows getRasterRows() throws myException {

			return this.rasterRows;
		}

		@Override
		public void buildRasterRows(RasterRow callingRasterRow) throws myException {
			this.cellList.clear();

			__RECORD_VPOS_TPA_FUHRE_EXT sp_fuhre_record = new __RECORD_VPOS_TPA_FUHRE_EXT(this.get_myLong_dbVal(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre).get_cUF_LongString());

			Rec20 rec_artikel = this.get_up_Rec20(ARTIKEL.id_artikel);
			Rec20 rec_artbez  = this.get_up_Rec20(is_ek? VPOS_TPA_FUHRE.id_artikel_bez_ek:VPOS_TPA_FUHRE.id_artikel_bez_vk, ARTIKEL_BEZ.id_artikel_bez,  false);

			RB_lab lbl_buchungsnummer =	new RB_lab()._t(sp_fuhre_record.get_BUCHUNGSNR_FUHRE_cF_NN("ID:"+this.get_fs_dbVal(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre)));
			lbl_buchungsnummer._fsa(-1).setLayoutData(left_al);

			//			RB_lab restmenge = new RB_lab()._t(VPOS_KON. )

			RB_lab lbl_datum	= new RB_lab()._t(is_ek?
					get_myDate_dbVal(VPOS_TPA_FUHRE.datum_aufladen).get_cDateStandardFormat(): 
						get_myDate_dbVal(VPOS_TPA_FUHRE.datum_abladen,new MyDate("99.99.9999")).get_cDateStandardFormat()	
					);
			lbl_datum._fsa(-1).setLayoutData(left_al);

			RB_lab lbl_artikel = new RB_lab()._t(
					rec_artbez.get_fs_dbVal(ARTIKEL_BEZ.artbez1) + "<" + rec_artikel.get_fs_dbVal(ARTIKEL.anr1, "?")+"-"+rec_artbez.get_fs_dbVal(ARTIKEL_BEZ.anr2,"?") + ">");
			lbl_artikel._fsa(-1).setLayoutData(left_al);

			RB_lab lbl_mge 	= new Rec20_VPOS_TPA_FUHRE(this)	.get_rbLabel_real_menge(is_ek, 0);
			lbl_mge._fsa(-1).setLayoutData(right_al);

			RB_lab lbl_plan_mge = new Rec20_VPOS_TPA_FUHRE(this).get_rbLabel_plan_menge(is_ek, 0);
			lbl_plan_mge._fsa(-1).setLayoutData(right_al);

			this.cellList.put(new RasterRowCell("id_fuhre"		, lbl_buchungsnummer, this)._setColSpan(8));

			this.cellList.put(new RasterRowCell("artikel"		, lbl_artikel		, this)._setColSpan(15)); 

			this.cellList.put(new RasterRowCell("fu_mge"		, lbl_mge			, this)._setColSpan(4)); 

			this.cellList.put(new RasterRowCell("fu_plan_mge"	, lbl_plan_mge		, this)._setColSpan(4));
		}

		@Override
		public void buildCellList() throws myException {

		}

		@Override
		public String getRowKey() {
			return this.key;
		}

		@Override
		public void setRowKey(String p_key) {
			this.key= p_key;
		}

		@Override
		public Raster getRaster() {
			return null;
		}

		@Override
		public RasterRow getMotherRasterRow() {
			return null;
		}

		@Override
		public Color getBackgroundColor() {
			return new E2_ColorBase();
		}

	}

	private class RR_fuo extends Rec20 implements RasterRow{

		private RasterRowCells 	cellList = 						new RasterRowCells();  

		private String			key = null;

		private RasterRow 		callingRasterRow = null;

		private RasterRows		rasterRows = new RasterRows();

		private boolean 		is_ek = false;

		public RR_fuo(MyLong l_uf__id_fuhre_ort, RasterRow p_callingRasterRow) throws myException {
			super(_TAB.vpos_tpa_fuhre_ort);

			this._fill_sql(
					new SEL()
					.FROM(_TAB.vpos_tpa_fuhre_ort)
					.WHERE(new vgl(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre_ort, l_uf__id_fuhre_ort.get_cUF_LongString())).s());

			this.key = "FUO#"+l_uf__id_fuhre_ort.get_cUF_LongString();

			this.callingRasterRow = p_callingRasterRow;

			this.generateCellList(callingRasterRow);
			this.generateRasterRows(callingRasterRow);
		}

		@Override
		public RasterRowCells getCellList() throws myException {
			return this.cellList;
		}

		@Override
		public int getInsetColCount() throws myException {
			return 10;
		}

		@Override
		public RasterRows getRasterRows() throws myException {
			return this.rasterRows;
		}

		@Override
		public void buildRasterRows(RasterRow callingRasterRow) throws myException {
			this.cellList.clear();

			String fu_nr = this.get_up_Rec20(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre, VPOS_TPA_FUHRE.id_vpos_tpa_fuhre, false)
					.get_fs_dbVal(VPOS_TPA_FUHRE.buchungsnr_fuhre, this.get_fs_dbVal(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre))+" *";

			RB_lab lbl_fuo = 
					new RB_lab()
					._t(fu_nr)
					._ttt("ID-Fuhre oder Buchungsnummer Fuhre:  Dieser Eintrag kommt von einem Zusatzort in der betreffenden Fuhre");
			lbl_fuo.setLayoutData(left_al);

			RB_lab lbl_sorte = new RB_lab()
					._t(this.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.artbez1)+ "<"+this.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.anr1)+"-"+this.get_ufs_dbVal(VPOS_TPA_FUHRE_ORT.anr2)+">");
			lbl_sorte._fsa(-1).setLayoutData(left_al);

			RB_lab lbl_mge 	= new Rec20_VPOS_TPA_FUHRE_ORT(this)	.get_rbLabel_real_menge(is_ek, 0);
			lbl_mge._fsa(-1).setLayoutData(right_al);

			RB_lab lbl_plan_mge = new Rec20_VPOS_TPA_FUHRE_ORT(this).get_rbLabel_plan_menge(is_ek, 0);
			lbl_plan_mge._fsa(-1).setLayoutData(right_al);


			this.cellList.put(new RasterRowCell("id_fuhre",		 lbl_fuo, 		this)._setColSpan(8));
			this.cellList.put(new RasterRowCell("datum", 		lbl_sorte,		this)._setColSpan(15)); 
			this.cellList.put(new RasterRowCell("fu_mge",	 	lbl_mge, 		this)._setColSpan(4)); 
			this.cellList.put(new RasterRowCell("fu_plan_mge", 	lbl_plan_mge, 	this)._setColSpan(4)); 
		}

		@Override
		public void buildCellList() throws myException {
		}

		@Override
		public String getRowKey() {
			return this.key;
		}

		@Override
		public void setRowKey(String p_key) {
			this.key = p_key;
		}

		@Override
		public Raster getRaster() {
			return null;
		}

		@Override
		public RasterRow getMotherRasterRow() {
			return null;
		}

		@Override
		public Color getBackgroundColor() {
			return new E2_ColorBase();
		}

	}

	private class RR_fuhre_gesamt implements RasterRow{

		private RasterRowCells 	cellList 	= 	new RasterRowCells();  

		private RasterRows		rasterRows 			= new RasterRows();
		private String 			key		  			= "GESAMT_FUHRE";
		private RasterRow       callingRasterRow 	= null;
		private MyLong 			id_position = null;

		public RR_fuhre_gesamt(RasterRow p_callingRasterRow, MyLong l_uf_id_position) throws myException {
			super();

			this.callingRasterRow = p_callingRasterRow;

			this.id_position = l_uf_id_position;

			this.generateCellList(p_callingRasterRow);
			this.generateRasterRows(callingRasterRow);
		}


		@Override
		public RasterRowCells getCellList() throws myException {
			return this.cellList;
		}

		@Override
		public int getInsetColCount() throws myException {
			return 10;
		}

		@Override
		public RasterRows getRasterRows() throws myException {
			return rasterRows;
		}

		@Override
		public void buildRasterRows(RasterRow callingRasterRow) throws myException {
			Rec20_VPOS_KON rec_pos = new Rec20_VPOS_KON(new Rec20(_TAB.vpos_kon)._fill_id(id_position.get_lValue()));

			RB_lab lbl_col_1 = new RB_lab()._t("Gesamt")._i()._fsa(-1);
			RB_lab lbl_col_2 = new RB_lab()._t(rec_pos.get_gesamt_fuhre_menge(is_ek).get_FormatedRoundedNumber(0)		+ " " +rec_pos.get_fs_dbVal(VPOS_KON.einheitkurz))._fsa(-1);
			RB_lab lbl_col_3 = new RB_lab()._t(rec_pos.get_gesamt_fuhre_planmenge(is_ek).get_FormatedRoundedNumber(0)	+ " " +rec_pos.get_fs_dbVal(VPOS_KON.einheitkurz))._fsa(-1);

			lbl_col_1.setLayoutData(right_al);
			lbl_col_2.setLayoutData(right_al);
			lbl_col_3.setLayoutData(right_al);

			this.cellList.put(new RasterRowCell("lbl_g_fu", 	lbl_col_1, this)._setColSpan(23));
			this.cellList.put(new RasterRowCell("g_real_mge", 	lbl_col_2, this)._setColSpan(4));
			this.cellList.put(new RasterRowCell("g_plan_mge", 	lbl_col_3, this)._setColSpan(4));
		}

		@Override
		public void buildCellList() throws myException {

		}

		@Override
		public String getRowKey() {
			return key;
		}

		@Override
		public void setRowKey(String key) {
		}

		@Override
		public Raster getRaster() {
			return null;
		}

		@Override
		public RasterRow getMotherRasterRow() {
			return null;
		}

		@Override
		public Color getBackgroundColor() {
			return new E2_ColorBase();
		}

	}
	
	private class ownBtOpenClose extends E2_Button {

		public ownBtOpenClose() throws myException {
			super();
			this._image("expandopenclose.png");
			this._aaa(new ownActionAgent());
			this._gld_align_cm()._gld_add_insets(2,2,2,2);
			
		}
		
		private class ownActionAgent extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				KFIX_K_M_masklist_position_raster_grid raster = KFIX_K_M_masklist_position_raster_grid.this;
				
				boolean oldOpen = false;
				
				for (RasterRow row: raster.getRasterRows().values()) {
					RasterBtOpenClose bt = (RasterBtOpenClose) row.getCellList().get("KEYBT").getComponent4Cell();
					if (bt.isOpen()) {
						oldOpen = true;
						break;
					}
				}
				
				boolean newOpen = !oldOpen;
				
				
				for (RasterRow row: raster.getRasterRows().values()) {
					RasterBtOpenClose bt = (RasterBtOpenClose) row.getCellList().get("KEYBT").getComponent4Cell();
					bt.set_open(newOpen);
				}
				
				Vector<String>  vIdsOldSelected = raster.get_selected_ids();
				
				raster.completeBuildAndRender();
				
				raster._markCheckboxes(vIdsOldSelected);
				
			}
			
		}
	}
	
	private class ownAuswahlButton extends E2_SelectAllNoneInvert_New{

		@Override
		public Vector<CheckBox> get_vAllCheckboxes() throws myException {
			Vector<CheckBox> vRueck = new Vector<CheckBox>();
			vRueck.addAll(vCb);
			return vRueck;
		}

		@Override
		public void do_after_action() throws myException {}
		
	}
	
	

	
	private class ownBedienPanel extends E2_Grid{
		public ownBedienPanel() throws myException {
			super();
			
			this._setSize(25,25,25);
			
			VEK<MyE2_Button> v_sammle = new VEK<>();
			
			Rec20 record_kopf = new Rec20(_TAB.vkopf_kon)._fill_id(KFIX_K_M_masklist_position_raster_grid.this.id_vkopf_kon.get_lValue());
			
			KFIX_P_M_BT_new new_bt = new KFIX_P_M_BT_new(
					KFIX_K_M_masklist_position_raster_grid.this.position.get_navigation_list().get_MODULE_IDENTIFIER_OF_CONTAINING_MODULE(), 
					KFIX_K_M_masklist_position_raster_grid.this.position, 
					record_kopf);
			
			KFIX_P_M_BT_List2Mask view_bt = new KFIX_P_M_BT_List2Mask(
					false, 
					KFIX_K_M_masklist_position_raster_grid.this, 
					KFIX_K_M_masklist_position_raster_grid.this.position.get_navigation_list().get_MODULE_IDENTIFIER_OF_CONTAINING_MODULE(), 
					record_kopf);

			KFIX_P_M_BT_List2Mask edit_bt = new KFIX_P_M_BT_List2Mask(
					true, 
					KFIX_K_M_masklist_position_raster_grid.this, 
					KFIX_K_M_masklist_position_raster_grid.this.position.get_navigation_list().get_MODULE_IDENTIFIER_OF_CONTAINING_MODULE(), 
					record_kopf);
			
			this
			._a(v_sammle._ar(new_bt), 	new RB_gld()._ins(2,5,2,5)._center_mid())
			._a(v_sammle._ar(view_bt),	new RB_gld()._ins(2,5,2,5)._center_mid())
			._a(v_sammle._ar(edit_bt),	new RB_gld()._ins(2,5,2,5)._center_mid())
			;
			
			MASK_STATUS  status_vpos_kon_mask = KFIX_K_M_masklist_position_raster_grid.this.position._find_componentMap_i_belong_to().getRbDataObjectActual().rb_MASK_STATUS();
			if (status_vpos_kon_mask.isStatusView()) {
				for (MyE2_Button b: v_sammle) {
					b.set_bEnabled_For_Edit(false);
				}
			}
			
		}
	}
	
	public E2_Grid get_bedien_panel() throws myException{
		return new ownBedienPanel();
	}
	
	public Vector<String> get_selected_ids(){
		Vector<String> rueck_vekt = new Vector<String>();
		for(RB_cb cb: this.vCb){
			if(cb.isSelected()){
				rueck_vekt.add(cb.EXT().get_C_MERKMAL());
			}
		}
		return rueck_vekt;
	}
	
	public KFIX_K_M_masklist_position get_parent_container(){
		return this.position;
	}
	
	
	public KFIX_K_M_masklist_position_raster_grid _clearCheckboxes() {
		for (RB_cb cb: this.vCb) {
			cb.setSelected(false);
		}
		return this;
	}
	
	public KFIX_K_M_masklist_position_raster_grid _markCheckboxes(Vector<String> idsToMark) {
		for (RB_cb cb: this.vCb) {
			if (idsToMark.contains(S.NN(cb.EXT().get_C_MERKMAL()))) {
				cb.setSelected(true);
			}
		}
		return this;
	}
}