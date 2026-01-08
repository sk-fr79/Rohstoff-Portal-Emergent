package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WE.SEP;

import java.math.BigDecimal;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class SEP_HiddenRow {

	private  SEP_SearchSorte	searchArtBez = 	new SEP_SearchSorte();
	private RB_TextField  		tf_menge = 		new RB_TextField(150,20)._size_and_font(14, 100,  new E2_FontPlain())._right();
	private MyE2_Button     	bt_close = 		(MyE2_Button)new MyE2_Button(E2_ResourceIcon.get_RI("ok.png")		)._aaa(new ownActionCloseRow());
	private MyE2_Button     	bt_open = 		(MyE2_Button)new MyE2_Button(E2_ResourceIcon.get_RI("edit.png")		)._aaa(new ownActionOpenRow());
	private MyE2_Button     	bt_add = 		(MyE2_Button)new MyE2_Button(E2_ResourceIcon.get_RI("add_field.png"))._aaa(new ownActionAddRow());
	private MyE2_Button     	bt_delete = 	(MyE2_Button)new MyE2_Button(E2_ResourceIcon.get_RI("clear.png")	)._aaa(new ownActionRemoveRow());
	
	private SEP_HiddenMASK  	ownSeparator = null;
	
	private boolean         	statusOpen  =    true;
	
	public SEP_HiddenRow(SEP_HiddenMASK  p_ownSeparator) throws myException {
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
	
	public boolean is_empty() {
		return S.isEmpty(this.tf_menge.getText()) && S.isEmpty(this.searchArtBez.get_tf_search_input().getText());
	}
	
	

	
	private class ownActionAddRow extends XX_ActionAgent {
		SEP_HiddenRow  oThis = SEP_HiddenRow.this;

		public ownActionAddRow() {
			super();
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			oThis.ownSeparator.get_rows().add(new SEP_HiddenRow(oThis.ownSeparator));
			oThis.ownSeparator._render_list();
		}
	}

	
	private class ownActionCloseRow extends XX_ActionAgent {
		SEP_HiddenRow  oThis = SEP_HiddenRow.this;

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
			BigDecimal bd_sum = this.ownSeparator.get_row_collection().calculate_liste(mv); 
			if (mv.get_bIsOK()) {
				this.ownSeparator.set_sumSubQuantity(new MyBigDecimal(bd_sum,3));
				this.ownSeparator._render_list();
			} 
		} else {
			mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte Sorte und Menge exakt angeben !")));
		}
		return mv;
	}
	
	
	private class ownActionOpenRow extends XX_ActionAgent {
		SEP_HiddenRow  oThis = SEP_HiddenRow.this;

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
		SEP_HiddenRow  oThis = SEP_HiddenRow.this;
	
		public ownActionRemoveRow() {
			super();
		}
	
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			oThis.ownSeparator.get_rows().remove(oThis);
			if (oThis.ownSeparator.get_rows().size()==0) {
				oThis.ownSeparator.get_rows().add(new SEP_HiddenRow(oThis.ownSeparator));
			}
			oThis.ownSeparator._render_list();
		}
	}

	
	public MyE2_MessageVector  check_row() {
		MyE2_MessageVector  mv = new MyE2_MessageVector();
		MyLong l_artbez = new MyLong(this.searchArtBez.get_tf_search_input().getText());
		if (!(this.searchArtBez.is_search_done_correct() && l_artbez.get_bOK())) {
			mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Sortenbezeichnung wurde nicht korrekt ermittelt !")));
		}

		MyBigDecimal  bdTest = new MyBigDecimal(this.tf_menge.getText());
		if (!(bdTest.get_bOK())) {
			mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die erfaﬂte Zahl in der Mengenangabe ist nicht korrekt !")));
		}
		
		
		return mv;
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
