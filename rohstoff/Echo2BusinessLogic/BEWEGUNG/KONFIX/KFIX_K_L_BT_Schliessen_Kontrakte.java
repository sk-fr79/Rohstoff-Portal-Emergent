package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class KFIX_K_L_BT_Schliessen_Kontrakte extends MyE2_ButtonInLIST implements E2_IF_Copy{

	private Rec20 record_kopf;
	private E2_NavigationList naviList;

	public KFIX_K_L_BT_Schliessen_Kontrakte(E2_NavigationList oNavigationList) throws myException {
		super();
		this.naviList = oNavigationList;
		this.add_GlobalAUTHValidator_AUTO("LOCK_UNLOCK_KONTRAKT");
		this._aaa(new ownActionAgent());
		this.EXT().set_oLayout_ListElement(new RB_gld()._center_mid()._ins(1));
	}

	public KFIX_K_L_BT_Schliessen_Kontrakte _fill(Rec20 rec20_kopf) throws myException{
		this.record_kopf = rec20_kopf;
		
		if(this.record_kopf.is_yes_db_val(VKOPF_KON.deleted)){
			
			this.remove_AllActionAgents();
			this.set_bEnabled_For_Edit(false);
			this.setIcon(E2_ResourceIcon.get_RI("empty.png"));
			
		} else if (this.record_kopf.is_yes_db_val(VKOPF_KON.ist_fixierung)) {
			
			if(this.record_kopf.is_yes_db_val(VKOPF_KON.abgeschlossen)){
				this.setIcon(E2_ResourceIcon.get_RI("locked.png"));
				this._ttt(S.mt("Entsperren des Fixierungskontrakts (um weitere Fixierungen anhängen zu können)"));
			}else {
				this.setIcon(E2_ResourceIcon.get_RI("unlock.png"));
				this._ttt(S.mt("Sperren des Fixierungskontrakts (keine weitere Fixierungspositionen mehr möglich)"));
			}
			this.set_bEnabled_For_Edit(true);
			
			
		}else if(this.record_kopf.is_no_db_val(VKOPF_KON.ist_fixierung)){
			
			if(this.record_kopf.is_yes_db_val(VKOPF_KON.abgeschlossen)){
				this.setIcon(E2_ResourceIcon.get_RI("locked.png"));
				this._ttt(S.mt("Der Kontrakt wurde bereits geschlossen"));
			}else{
				this.setIcon(E2_ResourceIcon.get_RI("empty.png"));
				this._ttt(S.mt("Der Kontrakt ist noch offen"));
			}
			
			this.remove_AllActionAgents();
			this.set_bEnabled_For_Edit(false);
			
		}
		
		return this;
	}

	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		try {
			KFIX_K_L_BT_Schliessen_Kontrakte oBT_Copy = new KFIX_K_L_BT_Schliessen_Kontrakte(this.naviList);
			oBT_Copy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oBT_Copy));

			return oBT_Copy;
		}catch(myException e){
			e.printStackTrace();
			throw new myExceptionCopy(e.ErrorMessage);
		}
	}


	private class ownActionAgent extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();

			KFIX_K_L_BT_Schliessen_Kontrakte oThis = KFIX_K_L_BT_Schliessen_Kontrakte.this;

			String kontrakt_status = oThis.record_kopf.get_fs_dbVal(VKOPF_KON.abgeschlossen,"N");

			if(kontrakt_status.equals("Y")){
				oThis.record_kopf.set_NewValueForDatabase(VKOPF_KON.abgeschlossen.fieldName(), "N");
			}else{
				oThis.record_kopf.set_NewValueForDatabase(VKOPF_KON.abgeschlossen.fieldName(), "Y");
			}

			oThis.record_kopf.get_sql_4_save(false, mv);
			oThis.record_kopf._SAVE(true, mv);

			bibMSG.add_MESSAGE(mv);

			oThis.record_kopf._rebuild();

			if(mv.get_bIsOK()){
				if(kontrakt_status.equals("Y")){
					oThis.setIcon(E2_ResourceIcon.get_RI("locked.png"));
				}else{
					oThis.setIcon(E2_ResourceIcon.get_RI("unlock.png"));
				}
			}
			oThis.naviList._REBUILD_ACTUAL_SITE(false,true, bibALL.get_Vector(oThis.record_kopf.get_key_value()));
		}
	}
}
