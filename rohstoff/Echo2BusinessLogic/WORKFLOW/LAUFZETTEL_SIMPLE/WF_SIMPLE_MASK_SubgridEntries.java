package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_SIMPLE;

import java.util.GregorianCalendar;

import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.E2_Grid_V2;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_STATUS;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.TERM.Nvl;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.exceptions.myException;

public class WF_SIMPLE_MASK_SubgridEntries extends E2_Grid_V2 {

	private boolean _bIsDeletedLZ = false;
	private boolean _bShowDeleted = false;
	
	Rec21 laufzettel = null;
	private RB_TransportHashMap m_tpHashMap = null;

	public RB_TransportHashMap getParams() {
		return m_tpHashMap;
	}

	public WF_SIMPLE_MASK_SubgridEntries(RB_TransportHashMap tpHashMap) {
		this.m_tpHashMap = tpHashMap;
		
		this._setWidth(500);
		this._setSize(200,200,100)._bo_dd();
	}
	
	public void refresh() throws myException{
		this.rb_set_db_value_manual(laufzettel.getUfs(LAUFZETTEL.id_laufzettel));
	}

	
	
	/**
	 * Setzen ob gelöschte angezeigt werden sollen oder nicht
	 * @author manfred
	 * @date 02.05.2019
	 *
	 * @param bShow
	 * @throws myException 
	 */
	public void setShowDeleted(boolean bShow) throws myException{
		if (_bShowDeleted != bShow){
			_bShowDeleted = bShow;
			this.refresh();
		}
	}
	
	
	
	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {

		laufzettel = dataObject.rec21();
		this.rb_set_db_value_manual(laufzettel.getUfs(LAUFZETTEL.id_laufzettel));
	
		_bIsDeletedLZ = laufzettel.get_ufs_dbVal(LAUFZETTEL.geloescht,"N").equals("Y");

		this.refresh();
		
	}

	
	
	/**
	 * formatiert Label als Header-Label
	 * @author manfred
	 * @date 17.04.2019
	 *
	 * @param label
	 * @return
	 */
	private RB_lab Header(RB_lab label){
		label._fo_s_plus(-1)._b();
		return label;
	}

	
	
	/**
	 * formatiert Label als Eintrag-Label
	 * @author manfred
	 * @date 17.04.2019
	 *
	 * @param label
	 * @return
	 */
	private RB_lab Item(Rec21 recLZE, RB_lab label){
		return Item(recLZE,label,false);
	}
	
	
	
	/**
	 * formatiert Label als Eintrag-Label
	 * @author manfred
	 * @date 17.04.2019
	 *
	 * @param label
	 * @return
	 */
	private RB_lab Item(Rec21 recLZE, RB_lab label, boolean bDeleted){
		
		label._fo_s_plus(-1);
		
		try {
			if(!S.isEmpty(recLZE.get_ufs_dbVal(LAUFZETTEL_EINTRAG.abgeschlossen_am))){
				label._col_fore_dgrey();
			}
			if (bDeleted){
				label._fo_lineThrough()._col_fore_dgrey();
			}
			
		} catch (myException e) { /* nothing really fancy, just formatting */ }
		return label;
	}
	

	
	
	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {
		// TODO Auto-generated method stub
//		super.rb_set_db_value_manual(valueFormated);
		
		MyLong l = new MyLong(valueFormated);
		
		if (l.isOK()) {
			setHeader(l);
			
			Term t = new vgl(new Nvl(LAUFZETTEL_EINTRAG.geloescht.t(),"'N'"), COMP.EQ, new TermSimple("'N'"));
			String s = t.s();
			if (_bShowDeleted){
				s = "";
			}
			
			RecList21  rlLaufzetteleintrag =  new Rec21(_TAB.laufzettel)
													._fill_id(l.getLong())
													.get_down_reclist21(
																LAUFZETTEL_EINTRAG.id_laufzettel, 
																s,
																null );

			RB_gld gldOwnEntry = new RB_gld()._col_back_ddd();
			
			Rec21 rec;
			for (Rec21 r: rlLaufzetteleintrag) {
				
				String sIDEntry = r.get_ufs_dbVal(LAUFZETTEL_EINTRAG.id_laufzettel_eintrag);
				String sIDLZ = r.get_ufs_dbVal(LAUFZETTEL_EINTRAG.id_laufzettel);

				// der aktuelle Haupt-Eintrag:
				WF_SIMPLE_MASK_DataObject_LAUFZETTEL do_LZ= (WF_SIMPLE_MASK_DataObject_LAUFZETTEL) m_tpHashMap.getMaskDataObjectsCollector().get(WF_SIMPLE_CONST.getMaskKeyLaufzettelEintrag());
				String idLZE = do_LZ.get_ufs_dbVal(LAUFZETTEL_EINTRAG.id_laufzettel_eintrag);
				
				// laufzettel gelöscht
				boolean bIsDeletedLZE 	= r.get_ufs_dbVal(LAUFZETTEL_EINTRAG.geloescht,"N").equals("Y");
				// eigener Laufzettel
				boolean bIsThisLZE 		= r.get_ufs_dbVal(LAUFZETTEL_EINTRAG.id_laufzettel_eintrag).equals(idLZE);
								
				
				String s_von = "-";
				rec = r.get_up_Rec21( LAUFZETTEL_EINTRAG.id_user_besitzer,USER.id_user,true);
				if (rec != null && rec.is_ExistingRecord()) {
					s_von = S.Concatenate(" ", "-",rec.getUfs(USER.vorname,"-"),rec.getUfs(USER.name1,"-"));
				}
				
				String s_bearb = "-";
				rec = r.get_up_Rec21(LAUFZETTEL_EINTRAG.id_user_bearbeiter,USER.id_user,true);
				if (rec != null && rec.is_ExistingRecord()) {
					s_bearb = S.Concatenate(" ", "-",rec.getUfs(USER.vorname,"-"),rec.getUfs(USER.name1,"-"));
				}
				
				String s_abgeschl = "-";
				rec = r.get_up_Rec21(LAUFZETTEL_EINTRAG.id_user_abgeschlossen_von,USER.id_user,true);
				if (rec != null && rec.is_ExistingRecord()) {
					s_abgeschl = S.Concatenate(" ", "-",rec.getUfs(USER.vorname,"-"),rec.getUfs(USER.name1,"-")	);
				}
								
				String s_status = "-";
				rec = r.get_up_Rec21(LAUFZETTEL_EINTRAG.id_laufzettel_status);
				if (rec != null && rec.is_ExistingRecord()) {
					s_status = r.get_up_Rec21(LAUFZETTEL_STATUS.id_laufzettel_status).getUfs(LAUFZETTEL_STATUS.status);
				}
				
				E2_Grid grid_buttons = new E2_Grid()._setSize(40,40);
				WF_SIMPLE_BT_SendMail btnSend = new WF_SIMPLE_BT_SendMail(sIDLZ, sIDEntry);
				if (bIsDeletedLZE || _bIsDeletedLZ){
					btnSend.set_bEnabled_For_Edit(false);
				} 
				grid_buttons._a(btnSend);
				
				if (!bIsThisLZE){
					WF_SIMPLE_BT_MaskToMask btnJump = new WF_SIMPLE_BT_MaskToMask(true, m_tpHashMap, sIDEntry, m_tpHashMap.getRBModulContainerMask());
					
					// jedem Button den breakcontroller des Cancel-Buttons der Maske zuweisen
					E2_Button btnRef = (E2_Button) m_tpHashMap.getRBModulContainerMask().getButtonForClosingWindow();
					if (btnRef != null ) {
						btnJump.setBreak4PopupController(btnRef.getBreak4PopupController());
					}
					grid_buttons._a(btnJump);
				}
				
				
				// Farbliche Markierung, falls überfällig
				MyDate dtToday = new MyDate(new GregorianCalendar().getTime());
				MyDate dtFaellig = r.get_myDate_dbVal(LAUFZETTEL_EINTRAG.faellig_am, new MyDate(new GregorianCalendar().getTime()));
				Color colTime = new E2_ColorBase();
				
				if (S.isEmpty(r.get_ufs_dbVal(LAUFZETTEL_EINTRAG.abgeschlossen_am))){
					if (dtFaellig.get_Calendar().getTime().getTime() >=  dtToday.get_DatePlusDays(WF_SIMPLE_CONST.Color_Date.OK.getDays()).getTime().getTime() ){
						colTime = WF_SIMPLE_CONST.Color_Date.OK.getCol();
					} else if (dtFaellig.get_Calendar().getTime().getTime() >=  dtToday.get_DatePlusDays(WF_SIMPLE_CONST.Color_Date.LATE.getDays()).getTime().getTime() ){
						// LATE
						colTime = WF_SIMPLE_CONST.Color_Date.LATE.getCol();
					} else {
						// VERY LATE
						colTime = WF_SIMPLE_CONST.Color_Date.VERY_LATE.getCol();
					}
				}
				
				
				
				RB_gld gldStd 	= new RB_gld()._ins(2,0,2,1);
				RB_gld gldEigen = new RB_gld()._ins(2,0,2,1); //._col_back_d();
				RB_gld gldLZE =  bIsThisLZE ? gldEigen : gldStd;
				RB_lab labEigen = new RB_lab("");
				if (bIsThisLZE){
					labEigen._icon(E2_ResourceIcon.get_RI("listlabel_mark.png"));
				}
				
				
				this._a(Item(r,new RB_lab()._t(r.get_fs_dbVal(LAUFZETTEL_EINTRAG.id_laufzettel_eintrag))._col_back(colTime),bIsDeletedLZE), gldLZE)
					._a(Item(r,labEigen,bIsDeletedLZE), gldLZE)
					._a(grid_buttons, gldLZE)
					._a(Item(r,new RB_lab()._t(s_bearb),bIsDeletedLZE), gldLZE)
					._a(Item(r,new RB_lab()._t(r.get_fs_dbVal(LAUFZETTEL_EINTRAG.faellig_am,"")),bIsDeletedLZE), gldLZE)
					._a(Item(r,new RB_lab()._t(r.get_ufs_dbVal(LAUFZETTEL_EINTRAG.aufgabe)),bIsDeletedLZE), gldLZE)
					._a(Item(r,new RB_lab()._t(r.get_ufs_dbVal(LAUFZETTEL_EINTRAG.bericht)),bIsDeletedLZE), gldLZE)
					._a(Item(r,new RB_lab()._t(s_abgeschl),bIsDeletedLZE), gldLZE)
					._a(Item(r,new RB_lab()._t(r.get_fs_dbVal(LAUFZETTEL_EINTRAG.abgeschlossen_am,"-")),bIsDeletedLZE), gldLZE)
					._a(new RB_cb()._setSelected(r.get_fs_dbVal(LAUFZETTEL_EINTRAG.send_nachricht,"N").equals("Y"))._disable()._al_center(), gldLZE)
					._a(Item(r,new RB_lab()._t(s_status),bIsDeletedLZE)		, gldLZE)
					
					;
			}
			
		} else {
			setHeader(l);
		}
		
	}
	
	private void setHeader(MyLong laufzettelID){
		E2_Grid grid_buttons = new E2_Grid()._setSize(40);
		String sIDLZ = laufzettelID.get_cUF_LongString();
		grid_buttons._a(new WF_SIMPLE_BT_SendMail(sIDLZ));
		
		this._clear()
		._setSize(80,40,40,80,80,300,300,80,80,80,100)
		._a(Header(new RB_lab("Id"))			,new RB_gld()._ins(2, 0, 2, 1)._col_back_d())
		._a(Header(new RB_lab("*"))	,new RB_gld()._ins(2, 0, 2, 1)._col_back_d())
		._a(grid_buttons 						,new RB_gld()._ins(2, 0, 2, 1)._center_top()._col_back_d())
		._a(Header(new RB_lab("Bearbeiter"))	,new RB_gld()._ins(2, 0, 2, 1)._col_back_d())
		._a(Header(new RB_lab("Fällig am"))		,new RB_gld()._ins(2, 0, 2, 1)._col_back_d())
			
		._a(Header(new RB_lab("Aufgabe"))		,new RB_gld()._ins(2, 0, 2, 1)._col_back_d())
		._a(Header(new RB_lab("Antwort"))		,new RB_gld()._ins(2, 0, 2, 1)._col_back_d())
		
		._a(Header(new RB_lab("Abschluss von"))	,new RB_gld()._ins(2, 0, 2, 1)._col_back_d())
		._a(Header(new RB_lab("am"))			,new RB_gld()._ins(2, 0, 2, 1)._col_back_d())
		._a(Header(new RB_lab("Nachricht"))		,new RB_gld()._ins(2, 0, 2, 1)._col_back_d())
		._a(Header(new RB_lab("Status"))		,new RB_gld()._ins(2, 0, 2, 1)._col_back_d());
	}
	

	
}
