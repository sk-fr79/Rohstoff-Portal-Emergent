package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KOSTEN_TP;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_CONST;
import rohstoff.Echo2BusinessLogic._AUSWERTUNGEN._TP_KOSTEN_DIVERSE.TP_KOSTEN_SUCHE_MittlererRealer_TP_Preis;

public class FSK_BT_ErmittleKosten_In_Zeile extends MyE2_Button {


	public FSK_BT_ErmittleKosten_In_Zeile() throws myException {
		super(E2_ResourceIcon.get_RI("wizard.png"), true);
		
		this.setToolTipText(new MyE2_String("Sucht aus erfaﬂten Transportrechnungen den Preis dieses Warenbewegungsprofils").CTrans());
		
		this.add_oActionAgent(new ownActionAgent());
		this.add_GlobalAUTHValidator_AUTO(FS_CONST.MASK_VALID_ERMITTLE_KOSTEN);

	}

	
	
	private class ownActionAgent extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			if (FSK_BT_ErmittleKosten_In_Zeile.this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP()==null) {
				throw new myException(this,"Can only be used in existing datasets!!");
			}
			
			String cid_KOSTEN_LIEFERBED_ADR=FSK_BT_ErmittleKosten_In_Zeile.this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
			
			new ownKOSTEN_SUCHE_MittlererRealer_TP_Preis(cid_KOSTEN_LIEFERBED_ADR).SHOW_SettingsWindow();
		}
	}
	
	
	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		try {
			return new FSK_BT_ErmittleKosten_In_Zeile();
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.ErrorMessage);
		}
	}
	


	private class ownKOSTEN_SUCHE_MittlererRealer_TP_Preis extends TP_KOSTEN_SUCHE_MittlererRealer_TP_Preis {

		public ownKOSTEN_SUCHE_MittlererRealer_TP_Preis(String ID_KOSTEN_LIEFERBED_ADR) throws myException {
			super(bibALL.get_cDateFirstDateActualMonth(),true ,null ,ID_KOSTEN_LIEFERBED_ADR);
		}

		@Override
		public MyE2_MessageVector do_After_UpdateStatements() throws myException {
			
			FSK_BT_ErmittleKosten_In_Zeile.this.EXT().get_oComponentMAP()._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_VIEW, false, false);
			
			return new MyE2_MessageVector();
		}
		
	}
	
}
