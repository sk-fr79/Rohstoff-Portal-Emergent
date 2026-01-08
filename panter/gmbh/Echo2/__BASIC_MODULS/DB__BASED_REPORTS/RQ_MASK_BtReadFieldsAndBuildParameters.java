package panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.basics4project.DB_ENUMS.REPORTING_QUERY_PARAM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class RQ_MASK_BtReadFieldsAndBuildParameters extends E2_Button {

	public static final  RB_KF KEY = (RB_KF)new RB_KF()._setKeyAndName("a26be2d4-a230-11e8-98d0-529269fb1459");
	
	private RB_TransportHashMap hm_trans = null;
	
	public RQ_MASK_BtReadFieldsAndBuildParameters(RB_TransportHashMap trans) {
		super();
		
		this.hm_trans = trans;
		
		this._tr("Parameter-Rohlinge aus der Abfrage lesen und aufbauen")._s_BorderTextCentered()
						._fo_bold()._ttt(S.ms("Die Liste der Parameter, die in der Query gefunden wird, für die Bearbeitung aufbauen und in die Liste einfügen"));
		
		
		this._aaa(new OwnAction());
		
	}
	
	
	
	private class OwnAction extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			RB_MaskController c = new RB_MaskController(RQ_MASK_BtReadFieldsAndBuildParameters.this);
			
			RQ_TextAnzeigeAllSQL sqlComp = (RQ_TextAnzeigeAllSQL)c.get_comp(RQ_CONST.getLeadingMaskKey(), RQ_TextAnzeigeAllSQL.KEY, bibMSG.MV());
			
			String s = sqlComp.getText();
			
			//zuerst speichern, damit der tabellenname hinterlegt ist
			((E2_Button)hm_trans.getButtonMaskSaveAndReload()).do_OnlyCode_from_OtherActionAgent(null);
			
			if (bibMSG.get_bIsOK()) {
				
				// String tabName = 
				if (S.countNumberOfOccurences(s, RQ_CONST.RQ_NAMES.INDEX_PLACEHOLDER.db_val())==1) {
					
					//jetzt die parameter parsen
					VEK<Long> v = S.getPositionsOfOccurences(s, RQ_CONST.RQ_NAMES.TAG_FOR_FIELDS.db_val());
					
					if (v.size()>0) {
						if (v.size()%2==0) {
							
							
							//jetzt die parameter raussuchen
							VEK<String> v_params = new VEK<>();
							
							for (int i=0; i<v.size();i+=2) {
								int posStart = v.get(i).intValue();
								int posZiel = v.get(i+1).intValue();
								String name = s.substring(posStart+RQ_CONST.RQ_NAMES.TAG_FOR_FIELDS.db_val().length(), posZiel);
								v_params._addIfNotIn(name);
							}
							
							//jetzt parameter in die parameter-eingabeliste uebernehmen, wenn noch kein eintrag vorhanden
							String maskKey = hm_trans.getMaskComponentMapCollector().get(RQ_CONST.getLeadingMaskKey()).getRbDataObjectActual().rec20().get_key_value();
							if (S.isEmpty(maskKey)) {
								bibMSG.MV()._addAlarm(S.ms("Aktuelle Report-ID kann nicht gefunden werden !"));
							} else {
								for (String sp: v_params) {
									SEL sel_p = new SEL("*").FROM(_TAB.reporting_query_param)	.WHERE(new vgl(REPORTING_QUERY_PARAM.paramkey, sp))
																								.AND(new vgl(REPORTING_QUERY_PARAM.id_reporting_query,maskKey));
									try {
										Rec21 r_p = new Rec21(_TAB.reporting_query_param)._fill_sql(sel_p.s());
										if (r_p.is_newRecordSet()) {
											//neu schreiben
											MyE2_MessageVector  mv = new MyE2_MessageVector();
											r_p = new Rec21(_TAB.reporting_query_param);
											r_p._nv(REPORTING_QUERY_PARAM.id_reporting_query, maskKey,mv);
											r_p._nv(REPORTING_QUERY_PARAM.paramkey, sp,mv);
											r_p._nv(REPORTING_QUERY_PARAM.paramname_4_user, sp,mv);
											r_p._nv(REPORTING_QUERY_PARAM.paramdefault, "-",mv);
											r_p._nv(REPORTING_QUERY_PARAM.typ, RQ__PARAM_TYP.TEXT.db_val(),mv);
											r_p._SAVE(true, bibMSG.MV());
										}
									} catch (myException e) {
										e.printStackTrace();
										bibMSG.MV()._addAlarm(S.ms("Abfrage nach bestehendem Parameter-Eintrag fehlerhaft !"));
									}
								}
							}
							
							
							(((RQ__TPHM_Zusaetze)hm_trans.getPlace4Everything()).daughterParams).getTransportHashMap().getNavigationList().RefreshList();

							
							
						} else {
							bibMSG.MV()._addAlarm(S.ms("In der Abfrage sind nicht alle Parameter geschlossen: ").ut(RQ_CONST.RQ_NAMES.TAG_FOR_FIELDS.db_val()).t(" muss immer vor und hinter einem SQL-Parameter-Name stehen !"));
						}
					}
				} else {
					bibMSG.MV()._addAlarm(S.ms("Der Platzhalter ").ut(RQ_CONST.RQ_NAMES.INDEX_PLACEHOLDER.db_val()).t(" muss genau einmal im SQL-String vorhanden sein !"));
				}
			}
		}
		

		
//		private class OwnContainer extends E2_BasicModuleContainer {
//
//			private E2_Button  btStart = new E2_Button()._t(S.ms("Abfrage mit den o.g. Parametern starten"));
//			
//			public OwnContainer(String sql, VEK<String> parameterNames) throws myException {
//				super();
//				
//				E2_Grid  g = new E2_Grid()._setSize(300,300);
//				LinkedHashMap<String, RB_TextField>  hmFields = new LinkedHashMap<>();
//				
//				for (String s: parameterNames) {
//					hmFields.put(s, new RB_TextField());
//					g._a(new RB_lab()._t(s))._a(hmFields.get(s));
//				}
//				
//				g._a(this.btStart, new RB_gld()._ins(2, 10, 2, 2));
//
//				this.add(g, E2_INSETS.I(5));
//				
//				this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(840), new Extent(800), S.ms("SQL-Ausführen"));
//				
//			}
//			
//		}
		
	}
	
	
	
	
}
