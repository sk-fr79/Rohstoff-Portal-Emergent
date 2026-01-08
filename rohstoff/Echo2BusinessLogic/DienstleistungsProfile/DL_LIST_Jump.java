/**
 * rohstoff.Echo2BusinessLogic.DienstleistungsProfile
 * @author martin
 * @date 03.12.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.DienstleistungsProfile;

import java.math.BigDecimal;
import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_V2;
import panter.gmbh.Echo2.ListAndMask.List.JUMPER.XX_ActionAgentJumpToTargetList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.DB.E2_UniversalListComponent;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.DB_ENUMS.DLP_JOIN_WARENBEWG;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_RG;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM.VglNull;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.vglParam;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * @date 03.12.2019
 *
 */
public class DL_LIST_Jump extends E2_UniversalListComponent {

	/**
	 * @author martin
	 * @date 03.12.2019
	 *
	 */
	public DL_LIST_Jump() {
		this.EXT().set_oLayout_ListElement(new RB_gld()._center_top()._ins(0,3,0,0));
		this._setSize(30,30);
	}

	@Override
	public String key() throws myException {
		return "DL_LIST_JumpToFuhren:<a7546f9c-15ec-11ea-8d71-362b9e155667>";
	}

	@Override
	public String userText() throws myException {
		return "Sprünge";
	}
	
	

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._clear();
	}

	@Override
	public void populate(SQLResultMAP resultMap) throws myException {
		E2_Button bt = new E2_Button().__setImages(E2_ResourceIcon.get_RI("kompass_fuhre.png"), E2_ResourceIcon.get_RI("kompass_fuhre__.png"));
		bt._aaa(new ownActionJumpToFuhren());
		bt._ttt(S.ms("Sprung zu den noch offenen Fuhren dieses Profils"));

		E2_Button btFp = new E2_Button().__setImages(E2_ResourceIcon.get_RI("kompass_rg.png"), E2_ResourceIcon.get_RI("kompass_rg__.png"));
		btFp._aaa(new ownActionJumpToFreiePos());
		btFp._ttt(S.ms("Sprung zu den noch offenen freien Positionen dieses Profils"));

		this._clear()	._a(bt, new RB_gld()._center_mid())
						._a(btFp, new RB_gld()._center_mid())
						;
	}

	
	
	private class ownActionJumpToFuhren extends XX_ActionAgentJumpToTargetList {

		public ownActionJumpToFuhren() throws myException {
			super(E2_MODULNAME_ENUM.MODUL.NAME_MODUL_FUHRENFUELLER.get_callKey(),"Fuhrenzentrale");
		}


		@Override
		public MyE2_MessageVector  OVERRIDE_PRUEFE_OB_SPRUNG_MOEGLICH_IST(Vector<String> vTargetList) throws myException {
			MyE2_MessageVector  oMV = new MyE2_MessageVector();

			if (vTargetList.size()==0)	{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Zielmodul: <",true,this.get_cLesbarerModulName(),false,"> enthält keine passenden Einträge !",true)));
			}
			return oMV;
		}


		@Override
		public Vector<String> get_vID_Target(ExecINFO oExecInfo) throws myException  {
			
			try {
				Rec21 recJoinWB = ((E2_ComponentMAP_V2)EXT().get_oComponentMAP()).getRec21();
				
				//alle connectors zu diesem profil suchen, die aktiv sind und deren fuhre noch unabgerechnet ist
				SEL sel = new SEL(DLP_JOIN_WARENBEWG.id_vpos_tpa_fuhre_dl).FROM(_TAB.dlp_join_warenbewg)
									.INNERJOIN(_TAB.vpos_tpa_fuhre, DLP_JOIN_WARENBEWG.id_vpos_tpa_fuhre_dl, VPOS_TPA_FUHRE.id_vpos_tpa_fuhre)
									.WHERE(	new vglParam(DLP_JOIN_WARENBEWG.id_dlp_profil))
									.AND(	new vgl_YN(DLP_JOIN_WARENBEWG.aktiv,true))
									.AND(	new vgl_YN(VPOS_TPA_FUHRE.deleted,false))
									.AND(   new vgl(VPOS_TPA_FUHRE.status_buchung,COMP.LT,"5"))
									;
				
				SqlStringExtended sql = new SqlStringExtended(sel.s())._addParameter(new Param_Long(recJoinWB.getId()));
				
				VEK<Object[]> lines =bibDB.getResultLines(sql,true);
				
				VEK<String>  ids = new VEK<>();
				
				for (Object[] o: lines) {
					ids._a(""+((BigDecimal)o[0]).longValue());
				}
				
				return ids;
			} catch (Exception e) {
				throw new myException(e.getMessage());
			}
		}
	}


	private class ownActionJumpToFreiePos extends XX_ActionAgentJumpToTargetList {

		public ownActionJumpToFreiePos() throws myException {
			super(E2_MODULNAME_ENUM.MODUL.NAME_MODUL_FREIEPOSITIONEN.get_callKey(),"Freie Positionen");
		}


		@Override
		public MyE2_MessageVector  OVERRIDE_PRUEFE_OB_SPRUNG_MOEGLICH_IST(Vector<String> vTargetList) throws myException {
			MyE2_MessageVector  oMV = new MyE2_MessageVector();

			if (vTargetList.size()==0)	{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Zielmodul: <",true,this.get_cLesbarerModulName(),false,"> enthält keine passenden Einträge !",true)));
			}
			return oMV;
		}


		@Override
		public Vector<String> get_vID_Target(ExecINFO oExecInfo) throws myException  {
			
			try {
				Rec21 recJoinWB = ((E2_ComponentMAP_V2)EXT().get_oComponentMAP()).getRec21();
				
				//alle connectors zu diesem profil suchen, die aktiv sind und deren fuhre ABGERECHNET ist
				SEL selInnen = new SEL(DLP_JOIN_WARENBEWG.id_vpos_tpa_fuhre_dl).FROM(_TAB.dlp_join_warenbewg)
										.INNERJOIN(_TAB.vpos_tpa_fuhre, DLP_JOIN_WARENBEWG.id_vpos_tpa_fuhre_dl, VPOS_TPA_FUHRE.id_vpos_tpa_fuhre)
										.WHERE(	new vglParam(DLP_JOIN_WARENBEWG.id_dlp_profil))
										.AND(	new vgl_YN(DLP_JOIN_WARENBEWG.aktiv,true))
										.AND(	new vgl_YN(VPOS_TPA_FUHRE.deleted,false))
										.AND(   new vgl(VPOS_TPA_FUHRE.status_buchung,COMP.EQ,"5"))
										;
				
				SEL selRGPos = new SEL(VPOS_RG.id_vpos_rg).FROM(_TAB.vpos_rg)
									.WHERE(	new VglNull(VPOS_RG.id_vpos_rg_storno_nachfolger))
									.AND(   new VglNull(VPOS_RG.id_vpos_rg_storno_nachfolger))
									.AND(   new VglNull(VPOS_RG.id_vkopf_rg))
									.AND(   new vgl_YN(VPOS_RG.deleted,false))
									.AND(   new TermSimple(VPOS_RG.id_vpos_tpa_fuhre_zugeord.tnfn()+" IN ("+selInnen.s()+")"));
									;
							
							
				
				
				SqlStringExtended sql = new SqlStringExtended(selRGPos.s())._addParameter(new Param_Long(recJoinWB.getId()));
				
				VEK<Object[]> lines =bibDB.getResultLines(sql,true);
				
				VEK<String>  ids = new VEK<>();
				
				for (Object[] o: lines) {
					ids._a(""+((BigDecimal)o[0]).longValue());
				}
				
				return ids;
			} catch (Exception e) {
				throw new myException(e.getMessage());
			}
		}
	}

	

	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
	}

	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		return new DL_LIST_Jump();
	}

	
	
}
