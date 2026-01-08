package panter.gmbh.Echo2.RB.CONTROLLERS_V4;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_MessageBox;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * button, um eine neu maske zu oeffnen, in der die werte eines ausgewaehlten satzes drinstehen
 * @author martin
 *
 */
public abstract class RB_BtV4_Delete extends E2_Button {

	public abstract MyE2_String 		get_message_text_mindestens_eine_irgendwas_markieren();     
	public abstract MyE2_String 		get_warnung_achtung_es_wird_ein_irgendwas_geloescht();
	public abstract String      		get_warnung_achtung_es_werden_n_irgendwas_geloescht_mit_platzhalter_fuer_zahl();   //#WERT# ist der platzhalter
	public abstract Vector<String> 		get_delete_sql_statements(String id_to_delete, MyE2_MessageVector mv) throws myException;
	
	
	private RB_TransportHashMap  		m_transportHashMap = null;
	
	private VEK<XX_ActionAgent>   		addOnActionsOkAfterDeleteAction = new VEK<>();
	private VEK<XX_ActionAgent>   		addOnActionsOkBeforeDeleteAction = new VEK<>();
	
	public RB_BtV4_Delete() {
		super();
		this._image(E2_ResourceIcon.get_RI("delete_mini.png") , true);
		
		this.add_oActionAgent(new actionStartDelete());
	}

	public RB_TransportHashMap getTransportHashMap() {
		return m_transportHashMap;
	}
	
	public RB_BtV4_Delete _setTransportHashMap(RB_TransportHashMap  transportHashMap) {
		this.m_transportHashMap = transportHashMap;
		return this;
	}
	

	private class actionStartDelete extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			RB_BtV4_Delete oThis = RB_BtV4_Delete.this; 
			
			Vector<Long> v = oThis.getIdsToDelete(bibMSG.MV());
			
			if (bibMSG.get_bIsOK()) {
				if (v.size()==0) {
					bibMSG.MV()._addAlarm(oThis.get_message_text_mindestens_eine_irgendwas_markieren());
				} else {
					//all in der liste markieren, die geloescht werden sollen
					VEK<String> vid = new VEK<>();
					for (Long l: v) {
						vid._a(l.toString());
					}
					oThis.getTransportHashMap().getNavigationList()._clearAllMarkers()._setMarkedIds(vid);
					new PopUpSindSieSicher(v);
				}
			}
		}
		
	}
	
	
	
	
	private class PopUpSindSieSicher extends E2_MessageBox {

		public PopUpSindSieSicher(Vector<Long> vIds) throws myException {
			super();
	
			RB_BtV4_Delete oThis = RB_BtV4_Delete.this; 
			
			this._setTitleOfPopup(S.ms("Sicherheitsabfrage vor dem Löschen"));
			
			if (vIds.size()==1) {
				this.getVInfos()._a(oThis.get_warnung_achtung_es_wird_ein_irgendwas_geloescht());
			} else {
				String s = oThis.get_warnung_achtung_es_werden_n_irgendwas_geloescht_mit_platzhalter_fuer_zahl();
				s=s.replace("#WERT#", (""+vIds.size()));
				
				this.getVInfos()._a(S.msUt(s));
			}
			this.getVInfos()._a(S.ms("Bitte bestätigen Sie"));
			
			this.getBtYes()._t(S.ms("Ja: löschen"));
			this.getBtNo()._t(S.ms("Nein: Abbruch"));
			
			this.getBtYes()._aaaV(addOnActionsOkBeforeDeleteAction);
			this.getBtYes()._aaa(new ownActionDelete(vIds));
			this.getBtYes()._aaaV(addOnActionsOkAfterDeleteAction);
			
			this._show(500, 200);
		}
		
	}
	
	
	
	public class ownActionDelete extends XX_ActionAgent {

		
		private Vector<Long> m_Ids;

		public ownActionDelete(Vector<Long> vIds) {
			super();
			this.m_Ids = vIds;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			Vector<String> vDel = new Vector<>();
			
			RB_BtV4_Delete oThis = RB_BtV4_Delete.this; 
			
			MyE2_MessageVector mv = new MyE2_MessageVector();
			
			for (Long l: m_Ids) {
				vDel.addAll(oThis.get_delete_sql_statements(l.toString(), mv));
			}
			
			mv._add(bibDB.ExecMultiSQLVector(vDel, true));
			
			if (mv.isOK()) {
				bibMSG.MV()._addInfo(S.ms("Löschen erfolgreich !"));
				
				if (oThis.m_transportHashMap.getNavigationList()!=null) {
					oThis.m_transportHashMap.getNavigationList()._REBUILD_ACTUAL_SITE("");
				}
				
			} else {
				bibMSG.MV()._add(mv);
			}
			
			
		}
		
	}
	
	
	/**
	 * holt die id zum kopieren, kann ueberschrieben werden
	 * @return
	 */
	public Vector<Long> getIdsToDelete(MyE2_MessageVector mv) throws myException {
		
		if (this.m_transportHashMap.getNavigationList()!=null) {


			Vector<Long>   v_l = new Vector<>();
			Vector<String> v = this.m_transportHashMap.getNavigationList().get_vSelectedIDs_Unformated();
			
			if (v.size()==0) {
				mv._addAlarm(S.ms("Sie müssen mindestens eine Zeile zu Löschen auswählen !"));
			} else {
				for (String s: v) {
					MyLong l = new MyLong(s);
					if (l.isOK()) {
						v_l.add(l.get_oLong());
					} else {
						v_l = null;
						mv._addAlarm(S.ms("Fehler beim feststellen der IDs!"));
					}
				}
			}
			return v_l;
		}
		
		throw new myException(this,"NavigationList is not in the transport-hashmap");
		
	}

	
	public VEK<XX_ActionAgent> getAddOnActionsOkAfterDeleteAction() {
		return addOnActionsOkAfterDeleteAction;
	}
	public VEK<XX_ActionAgent> getAddOnActionsOkBeforeDeleteAction() {
		return addOnActionsOkBeforeDeleteAction;
	}

	
}
