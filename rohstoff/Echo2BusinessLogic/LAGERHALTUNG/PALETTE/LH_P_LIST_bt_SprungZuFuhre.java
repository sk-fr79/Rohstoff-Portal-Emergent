/**
 * rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE
 * @author sebastien
 * @date 25.01.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.List.JUMPER.XX_ActionAgentJumpToTargetList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.DB.MyE2_DB_PlaceHolder_NT;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_PALETTE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;

/**
 * @author sebastien
 * @date 25.01.2019
 *
 */
public class LH_P_LIST_bt_SprungZuFuhre extends MyE2_DB_PlaceHolder_NT {
	
	private  	E2_Button sprung_knopf = null;
	private 	RB_lab fuhre_id_label = null;
//	private 	RB_lab buchungsnr_lbl = null;
	
	private RB_lab kunde_detail = null;
	
	private boolean eingangsfuhre = false;
	
	public LH_P_LIST_bt_SprungZuFuhre (boolean b_eingangsfuhre) throws myException{
		super();
		
		this.sprung_knopf = new E2_Button()._s_Image()
		.__setImages(E2_ResourceIcon.get_RI("kompass_mini.png"),E2_ResourceIcon.get_RI("empty.png"))
		._aaa(new ownJumpAction());
		
		this.sprung_knopf._ttt(S.ms("Sprung zu Fuhre.").CTrans());
		
		this.eingangsfuhre = b_eingangsfuhre;
	}

	private class ownJumpAction extends XX_ActionAgentJumpToTargetList
	{

		public ownJumpAction() throws myException
		{
			super(E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER, "Fuhrenzentrale");
		}

		@Override
		public Vector<String> get_vID_Target(ExecINFO oExecInfo) throws myException
		{
			LH_P_LIST_bt_SprungZuFuhre othis = LH_P_LIST_bt_SprungZuFuhre.this;
			
			SQLResultMAP result_map = othis.EXT().get_oComponentMAP().get_oInternalSQLResultMAP();
			
			String fuhre_feld = othis.eingangsfuhre? LAGER_PALETTE.id_vpos_tpa_fuhre_ein.fieldName():LAGER_PALETTE.id_vpos_tpa_fuhre_aus.fieldName();
			
			int i_IdVpos = result_map.get_bdActualValue(fuhre_feld, true).intValue();
			
			return new VEK<String>()._a(""+i_IdVpos);
		}

		//kann ueberschrieben werden wenn innerhalb der aktion der sprung abgelehnt werden muss
		public MyE2_MessageVector  OVERRIDE_PRUEFE_OB_SPRUNG_MOEGLICH_IST(Vector<String> vTargetList) throws myException
		{
			MyE2_MessageVector  oMV = new MyE2_MessageVector();

			if (vTargetList.size()==0)
			{
				oMV.add(new MyE2_Alarm_Message(new MyE2_String("Es wurde keine passende Fuhre für den Sprung gefunden ...")));
			}
			return oMV;
		}
	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._clear();
	}

	@Override
	public void set_cActual_Formated_DBContent_To_Mask_WhenVisisble(String value_from_db, String cMASK_STATUS,
			SQLResultMAP oResultMAP) throws myException {
		
		String fuhre_feld = this.eingangsfuhre? LAGER_PALETTE.id_vpos_tpa_fuhre_ein.fieldName():LAGER_PALETTE.id_vpos_tpa_fuhre_aus.fieldName();
		
//		boolean is_hand_einbuchung = oResultMAP.get_FormatedValue(LAGER_PALETTE.einbuchung_hand.fn(),"N").equals("Y");
		
		int iVposId = oResultMAP.get_bdActualValue(fuhre_feld, true).intValue();
		this._setSize(20,200)._bo_no();
		this.fuhre_id_label = new RB_lab()._t((iVposId==0)?"-":(""+iVposId))._lwn();
//		this.buchungsnr_lbl = new RB_lab()._fsa(-2)._lwn()._col_fore_dgrey();
		this.kunde_detail = new RB_lab()._fsa(-2)._lwn()._col_fore_dgrey();
		
		if(iVposId==0) {
			sprung_knopf.set_bEnabled_For_Edit(false);
		}else {
			String strAdresse = "";
//			String buchungsnr = "";

			Rec21 recVpos =	new Rec21(_TAB.vpos_tpa_fuhre)._fill_id(iVposId);
			Rec21_adresse rec_adresse = new Rec21_adresse(recVpos.get_up_Rec21(VPOS_TPA_FUHRE.id_adresse_start, ADRESSE.id_adresse, false));
			strAdresse = rec_adresse.get_ufs_kette(" " , ADRESSE.name1);
			
//			this.buchungsnr_lbl._t(buchungsnr);
			this.kunde_detail._t(strAdresse);
		}
		this._setWidth(1, 20);
		this._add(sprung_knopf, 	new RB_gld()._left_mid());
		this._add(fuhre_id_label, 	new RB_gld()._left_mid());
//		this._add(buchungsnr_lbl, 	new RB_gld()._left_mid()._span(2));
		this._add(kunde_detail, 	new RB_gld()._left_mid()._span(2));
	}
	
	public LH_P_LIST_bt_SprungZuFuhre get_Copy(Object objHelp)  throws myExceptionCopy {
		try {
			return new LH_P_LIST_bt_SprungZuFuhre(this.eingangsfuhre);
		}catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.getLocalizedMessage());
		}
	}
}