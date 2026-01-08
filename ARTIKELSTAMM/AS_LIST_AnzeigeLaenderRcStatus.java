/**
 * rohstoff.Echo2BusinessLogic.ARTIKELSTAMM
 * @author martin
 * @date 22.10.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.ARTIKELSTAMM;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.E2_MessageBoxYesNo;
import panter.gmbh.Echo2.components.DB.E2_UniversalListComponent;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_ENUMS.LAND_RC_SORTEN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.DBMap;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM.vglParam;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.LeftOuterJoin;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * @date 22.10.2020
 *
 */
public class AS_LIST_AnzeigeLaenderRcStatus extends E2_UniversalListComponent {

	private Rec22 sorte = null;
	
	public AS_LIST_AnzeigeLaenderRcStatus() {
		super();
		this.EXT().setLongString4ColumnSelection(S.ms("Länder-RC-Status"));
	}

	@Override
	public String key() throws myException {
		return this.getClass().getName()+"-cecd06d0-147c-11eb-adc1-0242ac120002";
	}

	@Override
	public String userText() throws myException {
		return "Länder-RC";
	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._clear();
	}

	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
	}

	
	@Override
	public void populate(SQLResultMAP resultMap) throws myException {
		this._clear();
		this.sorte = new Rec22(_TAB.artikel)._fill_id(resultMap.getLongId());
		try {
			boolean zolltarifRc = resultMap.get_booleanActualValue("ZT_REVERSE_CHARGE");
			
			
			// 2er-list die rc-einstufung in den laendern anzeigt
			//
			SEL selId = new SEL(LAND.id_land).FROM(_TAB.land).ADD_Distinct().INNERJOIN(_TAB.land_rc_sorten, LAND.id_land, LAND_RC_SORTEN.id_land);
			SEL sel = new SEL(LAND_RC_SORTEN.id_land_rc_sorten,LAND.id_land, LAND.laendercode, LAND.laendername).FROM(_TAB.land).LEFTOUTER(
														new LeftOuterJoin(_TAB.land_rc_sorten, new And(LAND.id_land,LAND_RC_SORTEN.id_land).and(new vglParam(LAND_RC_SORTEN.id_artikel))))
							.WHERE(new TermSimple(LAND.id_land.tnfn()+" IN ("+selId.s()+")"))
							.ORDERUP(LAND.laendercode.tnfn())
							;
			SqlStringExtended sex = new SqlStringExtended(sel.s())._addParameter(new Param_Long("id_artikel", resultMap.getLongId()));
			VEK<DBMap> result = bibDB.getResultMaps(sex, true);
			
			this._a(new ownGrid(result,resultMap.getLongId(),zolltarifRc));
		} catch (Exception e) {
			this._a(new RB_lab()._t(e.getLocalizedMessage()), new RB_gld()._col_back_alarm());
			e.printStackTrace();
		}
		
	}

	
	private class ownGrid extends E2_Grid {
		public ownGrid(VEK<DBMap> result, Long idArtikel, boolean zolltarifRc) throws myException {
			super();
			this._setSize(70,70)._bo_dd();
			for (DBMap line: result) {
				Rec22 landRcSorte = null;
				if (line.getLong(LAND_RC_SORTEN.id_land_rc_sorten)==null) {
					landRcSorte = new Rec22(_TAB.land_rc_sorten)
									._setNewVal(LAND_RC_SORTEN.id_artikel, idArtikel, bibMSG.MV())
									._setNewVal(LAND_RC_SORTEN.id_land,    line.getLong(LAND.id_land), bibMSG.MV())
									;
				} else {
					landRcSorte = new Rec22(_TAB.land_rc_sorten)._fill_id(line.getLong(LAND_RC_SORTEN.id_land_rc_sorten)) ;
				}
				
				GridCbWithWarnschild subGrid = new GridCbWithWarnschild(landRcSorte,line.getString(LAND.laendercode),line.getString(LAND.laendername),zolltarifRc);
				this._a(subGrid, new RB_gld()._ins(0,0,10,2));
			}
		}
	}



	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		return new AS_LIST_AnzeigeLaenderRcStatus();
	}
	
	private class GridCbWithWarnschild extends E2_Grid {
		private Rec22  landRcSorte = null;
		private RB_cb  checkBox = new RB_cb();
		private RB_lab warnSchild = new RB_lab();
		private boolean zolltarifRc = false;
		private String  laendername = null;
		
		public GridCbWithWarnschild(Rec22 landRcSorte, String laenderCode,String laendername, boolean zolltarifRc) throws myException {
			super();
			this.landRcSorte = landRcSorte;
			this.zolltarifRc = zolltarifRc;
			this.laendername = laendername;
			checkBox.setSelected(this.landRcSorte.is_ExistingRecord());
			checkBox._t(laenderCode)._ttt(laendername);
			
			this._setSize(50,20)._a(checkBox)._a(warnSchild);
			this.setActualStatus();
			
			checkBox._aaa(new ActionchangeStatus());
			checkBox.add_GlobalValidator(ENUM_VALIDATION.ARTIKEL_LAENDER_ZOLLTARIF.getValidator());
			
		}
		
		private void setActualStatus() throws myException {
			warnSchild.removeAll();
			if (checkBox.isSelected()) {
				if (zolltarifRc) {
					warnSchild.setIcon(E2_ResourceIcon.get_RI("empty10.png"));
					warnSchild._ttt("");
				} else {
					warnSchild.setIcon(E2_ResourceIcon.get_RI("warnschild_16.png"));
					warnSchild._ttt(S.ms("Die Einstellung widersspricht der aktuellen Einstellung der Zolltarifnummer !"));
				}
			} else {
				if (zolltarifRc) {
					warnSchild.setIcon(E2_ResourceIcon.get_RI("warnschild_16.png"));
					warnSchild._ttt(S.ms("Die Einstellung widersspricht der aktuellen Einstellung der Zolltarifnummer !"));
				} else {
					warnSchild.setIcon(E2_ResourceIcon.get_RI("empty10.png"));
					warnSchild._ttt("");
				}
			}
		}
		
		private class ActionchangeStatus extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				VEK<MyString> infos = new VEK<MyString>(); 
				if (landRcSorte.is_ExistingRecord()) {
					infos._a(S.ms("Bitte bestätigen Sie das Entfernen des RC-Status der Sorte "))
						 ._a(S.msUt(sorte.getUfs(ARTIKEL.artbez1)))
						 ._a(S.ms(" aus dem Land ").ut(laendername+"!"));
				} else {
					infos._a(S.ms("Bitte bestätigen Sie das Hinzufügen des RC-Status  der Sorte "))
						._a(S.msUt(sorte.getUfs(ARTIKEL.artbez1)))
						._a(S.ms("in das Land ").ut(laendername+"!"));
				}
				
				E2_MessageBoxYesNo msgBox =new E2_MessageBoxYesNo(S.ms("Wollen Sie den RC-Status ändern ?"), S.ms("JA"), S.ms("Nein"), infos, new SaveNewStatus());
				msgBox.getButtonYES()._aaa(()-> {
					AS_LIST_AnzeigeLaenderRcStatus.this.EXT().get_oComponentMAP()._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_VIEW,true, false);
				});
				msgBox.getButtonNO()._aaa(()-> {
					AS_LIST_AnzeigeLaenderRcStatus.this.EXT().get_oComponentMAP()._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_VIEW,true, false);
				});
			}

			
			private class SaveNewStatus extends XX_ActionAgent {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					if (checkBox.isSelected() && landRcSorte.is_newRecordSet()) {
						landRcSorte._SAVE(true, bibMSG.MV());
					} else if  (!checkBox.isSelected() && landRcSorte.is_ExistingRecord()) {
						bibMSG.MV().addAll(landRcSorte.DELETE());
					}
				}
			}
			
		}
		
	}
	
	
}
