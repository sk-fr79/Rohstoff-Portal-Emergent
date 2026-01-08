package panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS;

import java.util.HashMap;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V2;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.basics4project.myCONST_ENUM;
import panter.gmbh.basics4project.DB_ENUMS.REPORTING_QUERY;
import panter.gmbh.basics4project.DB_ENUMS.REPORTING_QUERY_PARAM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class RQ_MASK_BtAnalyseQuery extends E2_Button {

	public static final  RB_KF KEY = (RB_KF)new RB_KF()._setKeyAndName("64ebc156-a21f-11e8-98d0-529269fb1459");
	
	private RB_TransportHashMap hm_trans = null;
	
	public RQ_MASK_BtAnalyseQuery(RB_TransportHashMap trans) {
		super();
		
		this.hm_trans = trans;
		
		this._tr("Analyse der Query")._s_BorderTextCentered()._fo_bold()._ttt(S.ms("Query prüfen auf Korrektheit bzgl. Felder und Parameter"));
		this._aaa(new OwnAction());
	}
	
	
	
	private class OwnAction extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			RB_MaskController c = new RB_MaskController(RQ_MASK_BtAnalyseQuery.this);
			
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
							
							// dann eine Testabfrage erzeugen fuer die Zwischenablage
							// ersatz fuer den index-platzhalter
							String id=((RB_Dataobject_V2)hm_trans.getMaskDataObjectsCollector().get(RQ_CONST.getLeadingMaskKey())).get_rec20().get_key_value();
							Rec21 recInMask = new Rec21(_TAB.reporting_query)._fill_id(id);
							RecList21 rlParam = recInMask.get_down_reclist21(REPORTING_QUERY_PARAM.id_reporting_query);
							HashMap<String, String> hmValues = new HashMap<>();
							for (Rec21 r: rlParam) {
								hmValues.put(r.getUfs(REPORTING_QUERY_PARAM.paramkey), "<"+r.getUfs(REPORTING_QUERY_PARAM.paramkey)+">");
							}
							new OwnContainer(new RQ__serviceBuildQuery().getSqlSelectBlock(recInMask, hmValues));
							
							
						} else {
							bibMSG.MV()._addAlarm(S.ms("In der Abfrage sind nicht alle Parameter geschlossen: ").ut(RQ_CONST.RQ_NAMES.TAG_FOR_FIELDS.db_val()).t(" muss immer vor und hinter einem SQL-Parameter-Name stehen !"));
						}
						
					}
					
				} else {
					bibMSG.MV()._addAlarm(S.ms("Der Platzhalter ").ut(RQ_CONST.RQ_NAMES.INDEX_PLACEHOLDER.db_val()).t(" muss genau einmal im SQL-String vorhanden sein !"));
				}
			}
			
		}
		

		
		private class OwnContainer extends E2_BasicModuleContainer {

			public OwnContainer(String sqlToShow) throws myException {
				super();
				
				RB_TextArea textArea = new RB_TextArea(800, 40);
				textArea.setText(sqlToShow);
				
				this.add(textArea, E2_INSETS.I(10,10,10,10));
				
				this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(840), new Extent(800), S.ms("SQL-String-Vorschau"));
				
			}
			
		}
		
	}
	
	
	
	
}
