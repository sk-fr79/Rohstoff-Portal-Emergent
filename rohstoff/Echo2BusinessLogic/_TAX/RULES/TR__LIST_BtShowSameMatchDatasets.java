/**
 * rohstoff.Echo2BusinessLogic._TAX.RULES
 * @author martin
 * @date 18.02.2019
 * 
 */
package rohstoff.Echo2BusinessLogic._TAX.RULES;

import java.math.BigDecimal;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.E2_UniversalListComponent;
import panter.gmbh.basics4project.DB_ENUMS.HANDELSDEF;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_String;
import panter.gmbh.indep.dataTools.TERM.vglParam;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * @date 18.02.2019
 *
 */
public class TR__LIST_BtShowSameMatchDatasets extends E2_UniversalListComponent {


	@Override
	public String key() throws myException {
		return "b49f3de4-335c-11e9-b210-d663bd873d93";
	}

	@Override
	public String userText() throws myException {
		return "Multi?";
	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._clear();
	}

	@Override
	public void populate(SQLResultMAP resultMap) throws myException {
		this._clear();
		try {
			E2_NavigationList navi = this.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to();

			
			Long l = Long.parseLong(resultMap.get_cUNFormatedROW_ID());
			
			Rec21 rec = new Rec21(_TAB.handelsdef)._fill_id(l);
			
			VEK<IF_Field> fields = new VEK<IF_Field>()
					._a(HANDELSDEF.id_land_quelle_jur)
					._a(HANDELSDEF.id_land_quelle_geo)
					._a(HANDELSDEF.id_land_ziel_jur)
					._a(HANDELSDEF.id_land_ziel_geo)
					._a(HANDELSDEF.quelle_ist_mandant)
					._a(HANDELSDEF.ziel_ist_mandant)
					._a(HANDELSDEF.tp_verantwortung)
					._a(HANDELSDEF.ust_teilnehmer_quelle)
					._a(HANDELSDEF.ust_teilnehmer_ziel)
					._a(HANDELSDEF.sorte_rc_quelle)
					._a(HANDELSDEF.sorte_rc_ziel)
					._a(HANDELSDEF.sorte_produkt_quelle)
					._a(HANDELSDEF.sorte_produkt_ziel)
					._a(HANDELSDEF.sorte_dienstleist_quelle)
					._a(HANDELSDEF.sorte_dienstleist_ziel)
					._a(HANDELSDEF.sorte_eow_quelle)
					._a(HANDELSDEF.sorte_eow_ziel)
					;

			And and = new And();
			for (IF_Field f: fields) {
				and.add(new vglParam(f));
			}
			
			SqlStringExtended sql = new SqlStringExtended(new SEL(HANDELSDEF.id_handelsdef).FROM(_TAB.handelsdef).WHERE(and).s()); 
			
			sql._addParameters(new VEK<ParamDataObject>()
							._a(new Param_Long(rec.getLongDbValue(HANDELSDEF.id_land_quelle_jur)))
							._a(new Param_Long(rec.getLongDbValue(HANDELSDEF.id_land_quelle_geo)))
							._a(new Param_Long(rec.getLongDbValue(HANDELSDEF.id_land_ziel_jur)))
							._a(new Param_Long(rec.getLongDbValue(HANDELSDEF.id_land_ziel_geo)))
							._a(new Param_String("",rec.getUfs(HANDELSDEF.quelle_ist_mandant)))
							._a(new Param_String("",rec.getUfs(HANDELSDEF.ziel_ist_mandant)))
							._a(new Param_String("",rec.getUfs(HANDELSDEF.tp_verantwortung)))
							._a(new Param_Long(rec.getLongDbValue(HANDELSDEF.ust_teilnehmer_quelle)))
							._a(new Param_Long(rec.getLongDbValue(HANDELSDEF.ust_teilnehmer_ziel)))
							._a(new Param_Long(rec.getLongDbValue(HANDELSDEF.sorte_rc_quelle)))
							._a(new Param_Long(rec.getLongDbValue(HANDELSDEF.sorte_rc_ziel)))
							._a(new Param_Long(rec.getLongDbValue(HANDELSDEF.sorte_produkt_quelle)))
							._a(new Param_Long(rec.getLongDbValue(HANDELSDEF.sorte_produkt_ziel)))
							._a(new Param_Long(rec.getLongDbValue(HANDELSDEF.sorte_dienstleist_quelle)))
							._a(new Param_Long(rec.getLongDbValue(HANDELSDEF.sorte_dienstleist_ziel)))
							._a(new Param_Long(rec.getLongDbValue(HANDELSDEF.sorte_eow_quelle)))
							._a(new Param_Long(rec.getLongDbValue(HANDELSDEF.sorte_eow_ziel)))
							);
			
			VEK<Object[]> ergebnis = bibDB.getResultLines(sql,true);
			
			if (ergebnis != null && ergebnis.size()>0 && ergebnis.get(0).length==1) {
				VEK<Long> vl = new VEK<>();
				for (Object[] o: ergebnis) {
					if (o.length==1) {
						vl.add(((BigDecimal)o[0]).longValue());
					}
				}
				if (vl.size()<2) {
					this._a(new RB_lab()._t(""+vl.size())._fsa(2));
				} else {
					this._a(new BtSaveStatusOfListeAndSetSublistActive(vl));
				}
			} else {
				this._a(new RB_lab("?"));
			}
			
		} catch (Exception e) {
			this._a(new RB_lab("?"));
		}
		
		//SEL s = new SEL(_TAB.handelsdef).FROM(_TAB.handelsdef).WHERE(term);
		
		
	}

	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
	}

	
	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		TR__LIST_BtShowSameMatchDatasets copy = new TR__LIST_BtShowSameMatchDatasets();
		
		copy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(copy));

		return copy;
		
	}

	
	private class BtSaveStatusOfListeAndSetSublistActive extends E2_Button {

		private VEK<Long> ids=new VEK<>();

		public BtSaveStatusOfListeAndSetSublistActive(VEK<Long> p_ids) {
			super();
			this.ids._a(p_ids);
			//this._setShapeBigHighLightText();
			this._fsa(2)._bord_black()._i(new Insets(6, 2, 6, 2));
			this._t(""+p_ids.size())._aaa(new Action());
			this._ttt(S.ms("Diese Umsatz-Steuerregel ist Teil einer Gruppe von "+this.ids.size()+" Regeln. Diese Gruppe anzeigen "));
			
		}
		
		private class Action extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				
				E2_NavigationList navi = TR__LIST_BtShowSameMatchDatasets.this.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to();
				if (navi.getStatusSaver()==null) {
					navi.saveStatus(bibMSG.MV());
					
					navi.get_vectorSegmentation().clear();
					
					for (Long l: ids) {
						navi.get_vectorSegmentation().add(l.toString());
					}
					
					navi._RebuildListWithActualIds();
				}
			}
		}

		
		
	}
	
	
	
}
