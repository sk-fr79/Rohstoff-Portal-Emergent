package rohstoff.Echo2BusinessLogic._TAX.RULES.MAP_SETTERS;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_MAP_Set_And_Valid;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_ENUMS.HANDELSDEF;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic._TAX.TAX_CONST;
import rohstoff.Echo2BusinessLogic._TAX.TAX__DD_Auswahl_Y_N;
import rohstoff.Echo2BusinessLogic._TAX.TAX__DD_Auswahl_Y_N_EGAL;
import rohstoff.Echo2BusinessLogic._TAX.RULES.TR__CONST;

public class __TR_SETTING_PseudoFieldSchrott extends XX_MAP_Set_And_Valid {

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_COPY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException {
		return doInternal(oMAP, ActionType, oExecInfo, true);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_EMPTY(E2_ComponentMAP oMAP, int ActionType,	ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException {
		return doInternal(oMAP, ActionType, oExecInfo, true);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_EDIT(E2_ComponentMAP oMAP, int ActionType,	ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException {
		return doInternal(oMAP, ActionType, oExecInfo, false);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_VIEW(E2_ComponentMAP oMAP, int ActionType,	ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException {
		return doInternal(oMAP, ActionType, oExecInfo, false);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_UNDEFINED(E2_ComponentMAP oMAP, int ActionType,	ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException {
		return doInternal(oMAP, ActionType, oExecInfo, false);
	}
	
	
	private MyE2_MessageVector doInternal(E2_ComponentMAP oMAP, int ActionType,	ExecINFO oExecInfo, boolean isNew) throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		
		TAX__DD_Auswahl_Y_N 		schrottEk = ((TAX__DD_Auswahl_Y_N)oMAP.get__Comp(TR__CONST.KEY_MASK_SCHROTTSELEKTOR_EK));
		TAX__DD_Auswahl_Y_N 		schrottVk = ((TAX__DD_Auswahl_Y_N)oMAP.get__Comp(TR__CONST.KEY_MASK_SCHROTTSELEKTOR_VK));
		
		TAX__DD_Auswahl_Y_N_EGAL 	produktEk = ((TAX__DD_Auswahl_Y_N_EGAL)oMAP.get__Comp(HANDELSDEF.sorte_produkt_quelle));
		TAX__DD_Auswahl_Y_N_EGAL 	produktVk = ((TAX__DD_Auswahl_Y_N_EGAL)oMAP.get__Comp(HANDELSDEF.sorte_produkt_ziel));

		TAX__DD_Auswahl_Y_N_EGAL 	eowEk = ((TAX__DD_Auswahl_Y_N_EGAL)oMAP.get__Comp(HANDELSDEF.sorte_eow_quelle));
		TAX__DD_Auswahl_Y_N_EGAL 	eowVk = ((TAX__DD_Auswahl_Y_N_EGAL)oMAP.get__Comp(HANDELSDEF.sorte_eow_ziel));
		
		TAX__DD_Auswahl_Y_N_EGAL 	dienstEk = ((TAX__DD_Auswahl_Y_N_EGAL)oMAP.get__Comp(HANDELSDEF.sorte_dienstleist_quelle));
		TAX__DD_Auswahl_Y_N_EGAL 	dienstVk = ((TAX__DD_Auswahl_Y_N_EGAL)oMAP.get__Comp(HANDELSDEF.sorte_dienstleist_ziel));
		
		VComps  vRealCompsEK = new VComps()._a(produktEk,eowEk,dienstEk);
		VComps  vRealCompsVK = new VComps()._a(produktVk,eowVk,dienstVk);
				
		String valYes = ""+TAX_CONST.AUSWAHL_Y_N_EGAL__Y;
		String valNo = ""+TAX_CONST.AUSWAHL_Y_N_EGAL__N;
		
		
		SQLResultMAP resultMap = oMAP.get_oInternalSQLResultMAP();
		
		if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION) {
			if (isNew) {
				schrottEk.set_ActiveValue("N");
				schrottVk.set_ActiveValue("N");
			} else {
				if (   		S.NN(resultMap.getUfs(HANDELSDEF.sorte_dienstleist_quelle)).equals(valYes)
						||  S.NN(resultMap.getUfs(HANDELSDEF.sorte_eow_quelle)).equals(valYes)
						||  S.NN(resultMap.getUfs(HANDELSDEF.sorte_produkt_quelle)).equals(valYes)  	) {
					schrottEk.set_ActiveValue("N");
				} else {
					schrottEk.set_ActiveValue("Y");
				}
				
				if (   		S.NN(resultMap.getUfs(HANDELSDEF.sorte_dienstleist_ziel)).equals(valYes)
						||  S.NN(resultMap.getUfs(HANDELSDEF.sorte_eow_ziel)).equals(valYes)
						||  S.NN(resultMap.getUfs(HANDELSDEF.sorte_produkt_ziel)).equals(valYes)  	) {
					schrottVk.set_ActiveValue("N");
				} else {
					schrottVk.set_ActiveValue("Y");
				}
			}
		} else if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_KLICK_ACTION) {
			if (oExecInfo.get_MyActionEvent() != null && oExecInfo.get_MyActionEvent().getSource()!=null) {
			
				Component  klickSource = (Component)oExecInfo.get_MyActionEvent().getSource();
				
				if (klickSource == schrottEk && schrottEk.get_ActualWert().equals("Y")) {
					produktEk.set_ActiveValue(valNo);
					eowEk.set_ActiveValue(valNo);
					dienstEk.set_ActiveValue(valNo);
				} else if (klickSource == schrottEk && schrottEk.get_ActualWert().equals("N")) {
					if (vRealCompsEK.isAllNo()) {
						schrottEk.set_ActiveValue("Y");
						bibMSG.MV()._addInfo("Eintrag würd korrigiert: Alle Ausnahmen sind aus, d.h. es ist eine Schrottsorte!");
					}
				} else if (klickSource == schrottVk && schrottVk.get_ActualWert().equals("Y")) {
					produktVk.set_ActiveValue(valNo);
					eowVk.set_ActiveValue(valNo);
					dienstVk.set_ActiveValue(valNo);
				} else if (klickSource == schrottVk && schrottVk.get_ActualWert().equals("N")) {
					if (vRealCompsVK.isAllNo()) {
						schrottVk.set_ActiveValue("Y");
						bibMSG.MV()._addInfo("Eintrag würd korrigiert: Alle Ausnahmen sind aus, d.h. es ist eine Schrottsorte!");
					}
				} else if (vRealCompsEK.contains(klickSource) && vRealCompsEK.isAllNo()) {
					schrottEk.set_ActiveValue("Y");
				} else if (vRealCompsEK.contains(klickSource) && vRealCompsEK.isEvenOneYes()) {
					schrottEk.set_ActiveValue("N");
				} else if (vRealCompsVK.contains(klickSource) && vRealCompsVK.isAllNo()) {
					schrottVk.set_ActiveValue("Y");
				} else if (vRealCompsVK.contains(klickSource) && vRealCompsVK.isEvenOneYes()) {
					schrottVk.set_ActiveValue("N");
				}
			}
			
		}
		
		
		
		return mv;
	}
	
	private class VComps extends VEK<TAX__DD_Auswahl_Y_N_EGAL> {
		private String valYes = ""+TAX_CONST.AUSWAHL_Y_N_EGAL__Y;

		public boolean isAllNo() throws myException {
			boolean b = true;
			for (TAX__DD_Auswahl_Y_N_EGAL o: this) {
				if (o.get_ActualWert().equals(valYes)) {
					b=false;
				}
				
			}
			return b;
		}
		
		public boolean isEvenOneYes() throws myException {
			return !this.isAllNo();
		}

		@Override
		public VComps _a(TAX__DD_Auswahl_Y_N_EGAL... o) {
			super._a(o);
			return this;
		}

		
		
	}
	
	
}
