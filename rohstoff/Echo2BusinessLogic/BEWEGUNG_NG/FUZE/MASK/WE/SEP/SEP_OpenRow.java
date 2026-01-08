package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WE.SEP;

import java.math.BigDecimal;
import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR_POS;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_BEWEGUNG_VEKTOR_POS;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_VEKTOR_POS;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_BEWEGUNG_ATOM_ext;

public class SEP_OpenRow {

	private  SEP_SearchSorte	searchArtBez = 	new SEP_SearchSorte();
	private RB_TextField  		tf_menge = 		new RB_TextField(150,20)._size_and_font(14, 100,  new E2_FontPlain())._right();
	private MyE2_Button     	bt_close = 		(MyE2_Button)new MyE2_Button(E2_ResourceIcon.get_RI("ok.png")		)._aaa(new ownActionCloseRow());
	private MyE2_Button     	bt_open = 		(MyE2_Button)new MyE2_Button(E2_ResourceIcon.get_RI("edit.png")		)._aaa(new ownActionOpenRow());
	private MyE2_Button     	bt_add = 		(MyE2_Button)new MyE2_Button(E2_ResourceIcon.get_RI("add_field.png"))._aaa(new ownActionAddRow());
	private MyE2_Button     	bt_delete = 	(MyE2_Button)new MyE2_Button(E2_ResourceIcon.get_RI("clear.png")	)._aaa(new ownActionRemoveRow());
	
	private SEP_OpenMASK  	ownSeparator = null;
	
	private boolean         statusOpen  =    true;
	
	public SEP_OpenRow(SEP_OpenMASK  p_ownSeparator) throws myException {
		super();
		this.ownSeparator = p_ownSeparator; 
		this.tf_menge.mark_Neutral();
	}	
	
	
	public boolean is_valid() {
		boolean b_rueck = false;
		if (searchArtBez.is_search_done_correct()) {
			MyLong id_artbez = new MyLong(this.searchArtBez.get_tf_search_input().getText());
			if (id_artbez.get_bOK()) {
				MyBigDecimal  bd_menge = new MyBigDecimal(tf_menge.getText());
				if (bd_menge.get_bOK()) {
					b_rueck = true;
					tf_menge.setText(bd_menge.get_FormatedRoundedNumber(3));
				}
			}
		}
		return b_rueck;
	}
	
	
	
	public void render_status_open_closed(boolean open) throws myException {
		this.searchArtBez.set_bEnabled_For_Edit(open);
		this.tf_menge.set_bEnabled_For_Edit(open);
	}
	
	public void clear_line() throws myException {
		this.searchArtBez.do_mask_settings_after_search("",true);
		this.tf_menge.setText("");
	}
	
	public boolean is_empty() throws myException {
		return S.isEmpty(this.tf_menge.getText()) && S.isEmpty(this.searchArtBez.get_tf_search_input().getText());
	}
	
	

	
	private class ownActionAddRow extends XX_ActionAgent {
		SEP_OpenRow  oThis = SEP_OpenRow.this;

		public ownActionAddRow() {
			super();
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			oThis.ownSeparator.get_rows().add(new SEP_OpenRow(oThis.ownSeparator));
			oThis.ownSeparator._render_list();
		}
	}

	
	private class ownActionCloseRow extends XX_ActionAgent {
		SEP_OpenRow  oThis = SEP_OpenRow.this;

		public ownActionCloseRow() {
			super();
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			bibMSG.add_MESSAGE(oThis.set_row_closed());
		}
	}

	
	public MyE2_MessageVector  set_row_closed() throws myException {
		MyE2_MessageVector  mv = new MyE2_MessageVector();
		if (this.is_valid()) {
			this.statusOpen=false;
			this.render_status_open_closed(this.statusOpen);
			if (this.ownSeparator.calculate_liste()) {
				this.ownSeparator._render_list();
			}
		} else {
			mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte Sorte und Menge exakt angeben !")));
		}
		return mv;
	}
	
	
	private class ownActionOpenRow extends XX_ActionAgent {
		SEP_OpenRow  oThis = SEP_OpenRow.this;

		public ownActionOpenRow() {
			super();
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			oThis.statusOpen=true;
			oThis.render_status_open_closed(oThis.statusOpen);
			oThis.ownSeparator._render_list();
		}
	}


	private class ownActionRemoveRow extends XX_ActionAgent {
		SEP_OpenRow  oThis = SEP_OpenRow.this;
	
		public ownActionRemoveRow() {
			super();
		}
	
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			oThis.ownSeparator.get_rows().remove(oThis);
			if (oThis.ownSeparator.get_rows().size()==0) {
				oThis.ownSeparator.get_rows().add(new SEP_OpenRow(oThis.ownSeparator));
			}
			oThis.ownSeparator._render_list();
		}
	}

	
	
	public Vector<String> sql_save_stack(MyE2_MessageVector mv, MyBigDecimal bd_menge_start, String id_bewegung_vektor_new) throws myException {
		Vector<String> v_sql = new Vector<>();
		
		
		//bestehende datensaetze
		RECORD_BEWEGUNG_ATOM_ext  	a = 	new RECORD_BEWEGUNG_ATOM_ext(this.ownSeparator.get_recAtom());
		RECORD_BEWEGUNG_VEKTOR  	v = 	a.get_UP_RECORD_BEWEGUNG_VEKTOR_id_bewegung_vektor();
		RECORD_BEWEGUNG_VEKTOR_POS 	vp = 	a.get_UP_RECORD_BEWEGUNG_VEKTOR_POS_id_bewegung_vektor_pos();
		RECORD_BEWEGUNG_STATION     start = a.get_StationStart();
		RECORD_BEWEGUNG_STATION     ziel = 	a.get_StationZiel();
		
		BigDecimal  bdMengeAusgang = a.bd(BEWEGUNG_ATOM.menge, BigDecimal.ZERO, 3);

		
		//jetzt kopien aller saetze ziehen (die neuen haengen an der gleichen bewegung)
		RECORDNEW_BEWEGUNG_VEKTOR 		v_new =   		v.get_Record_NEW_FilledWithActualValues(mv, false, true);
		RECORDNEW_BEWEGUNG_VEKTOR_POS 	vp_new =   		vp.get_Record_NEW_FilledWithActualValues(mv, true, true);
		RECORDNEW_BEWEGUNG_ATOM 		a_new =   		a.get_Record_NEW_FilledWithActualValues(mv, true, true);
		RECORDNEW_BEWEGUNG_STATION 		start_new =   	start.get_Record_NEW_FilledWithActualValues(mv, true, true);
		RECORDNEW_BEWEGUNG_STATION 		ziel_new =   	ziel.get_Record_NEW_FilledWithActualValues(mv, true, true);
	

		//jetzt die verlinkungsids
		mv._add(v_new.nv(BEWEGUNG_VEKTOR.id_bewegung_vektor, 		id_bewegung_vektor_new));
		v_new.add_raw_val(BEWEGUNG_VEKTOR.posnr, "(SELECT NVL(MAX(BV.POSNR),1)+1 FROM "+bibE2.cTO()+".JT_BEWEGUNG_VEKTOR BV WHERE BV.ID_BEWEGUNG="+v.ufs(BEWEGUNG_VEKTOR.id_bewegung)+")");
		
		vp_new.add_raw_val(BEWEGUNG_VEKTOR_POS.id_bewegung_vektor, 	id_bewegung_vektor_new);
		a_new.add_raw_val(BEWEGUNG_ATOM.id_bewegung_vektor, 		id_bewegung_vektor_new);
		a_new.add_raw_val(BEWEGUNG_ATOM.id_bewegung_vektor_pos, 	_TAB.bewegung_vektor_pos.seq_currval());
		start_new.add_raw_val(BEWEGUNG_STATION.id_bewegung_atom, 	_TAB.bewegung_atom.seq_currval());
		ziel_new.add_raw_val(BEWEGUNG_STATION.id_bewegung_atom, 	_TAB.bewegung_atom.seq_currval());


		//jetzt menge und sorte eintragen
		
		MyLong id_artbez = new MyLong(this.searchArtBez.get_tf_search_input().getText());
		MyBigDecimal  bd_menge = new MyBigDecimal(tf_menge.getText());

		bd_menge_start.subtract_from_me(bd_menge.get_bdWert());
		
		if ((!id_artbez.get_bOK()) || (!bd_menge.get_bOK())) {
			mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler in der Mengen- oder Sortenangabe !")));   //duerfte hier nicht vorkommen !!!!
		} else {
			RECORD_ARTIKEL_BEZ  rab = new RECORD_ARTIKEL_BEZ(id_artbez.get_lValue());
			
			mv.add_MESSAGE(a_new.nv(BEWEGUNG_ATOM.id_artikel_bez.v(rab.fs(ARTIKEL_BEZ.id_artikel_bez)),
									BEWEGUNG_ATOM.id_artikel.v(rab.fs(ARTIKEL_BEZ.id_artikel)),
									BEWEGUNG_ATOM.menge.v(bd_menge.get_FormatedRoundedNumber(3))
									));

			start_new.add_raw_val(BEWEGUNG_STATION.id_bewegung_atom, _TAB.bewegung_atom.seq_currval());
			ziel_new.add_raw_val(BEWEGUNG_STATION.id_bewegung_atom, _TAB.bewegung_atom.seq_currval());
			

			if (mv.get_bIsOK()) {
				v_sql.add(v_new.get_InsertSQLStatement(true));    							//bekommt die id von der rufenden einheit
				v_sql.add(vp_new.get_InsertSQLStatementWith_Id_Field(true, true));
				v_sql.add(a_new.get_InsertSQLStatementWith_Id_Field(true, true));
				v_sql.add(start_new.get_InsertSQLStatementWith_Id_Field(true, true));
				v_sql.add(ziel_new.get_InsertSQLStatementWith_Id_Field(true, true));
			}
			
			
		}

		DEBUG.System_print(v_sql, true);
		
		
		return v_sql;
	}


	
	public boolean isStatusOpen() {
		return statusOpen;
	}
	
	public void setStatusOpen(boolean statusOpen) {
		this.statusOpen = statusOpen;
	}
	
	public MyE2_Button get_bt_add() {
		return this.bt_add;
	}
	
	public MyE2_Button get_bt_delete() {
		return this.bt_delete;
	}
	
	public RB_TextField get_tf_menge() {
		return this.tf_menge;
	}
	
	public SEP_SearchSorte get_searchArtBez() {
		return this.searchArtBez;
	}

	public MyE2_Button get_bt_close() {
		return this.bt_close;
	}

	public MyE2_Button get_bt_open() {
		return bt_open;
	}

	

}
