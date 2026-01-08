/**
 * panter.gmbh.Echo2.Tools.REPORT_VARIANTEN
 * @author martin
 * @date 06.02.2020
 * 
 */
package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ReportVarianten;

import java.util.HashMap;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4Popup;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.REP_VARIANTEN;
import panter.gmbh.basics4project.DB_ENUMS.REP_VARIANTEN_PARAM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * @date 06.02.2020
 *
 */
public class RV_Break4PopupWhenFalseParameter extends E2_Break4Popup {

	private MyE2_String   		s_windowTitle = 	S.ms("Bitte bestätigen ");
	private MyE2_String   		s_title = 			S.ms("Achtung!");
	private VEK<MyE2_String>	v_meldungsblock = 	new VEK<MyE2_String>()._a(S.ms("Sie haben unbekannte Parameter erfasst !"))._a(S.ms("Den Befehl trotzdem ausführen ?"));
	private MyE2_String	   		s_textForYes = 		S.ms("JA");
	private MyE2_String	   		s_textForNo = 		S.ms("NEIN");
	
	private RB_ComponentMapCollector 	m_mapCollector;
	private RB_TransportHashMap         m_transportMap = null;
	
	
	/**
	 * @author martin
	 * @date 06.02.2020
	 *
	 */
	public RV_Break4PopupWhenFalseParameter( RB_ComponentMapCollector 	mapCollector,  RB_TransportHashMap         transportMap) {
		this.m_mapCollector=mapCollector;
		this.m_transportMap = transportMap;
		
		this._setWidth(400);
		this._setHeight(250);
		
		this.setTitle(this.s_windowTitle);
		this.getOwnSaveButton()._t(this.s_textForYes.CTrans())._s_BorderText();
		this.getOwnCloseButton()._t(this.s_textForNo.CTrans())._s_BorderText();;

	}

	
	private class OwnContainer extends E2_BasicModuleContainer {};
	
	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4Popup#generatePopUpContainer()
	 */
	@Override
	public E2_BasicModuleContainer generatePopUpContainer() throws myException {
		OwnContainer container = new OwnContainer();

		E2_Grid g = new E2_Grid()._setSize(this.getPopupWidth()-10)._a(new RB_lab()._t(this.s_title)._b()._fsa(2), new RB_gld()._ins(2,4,4,2));
		for (MyE2_String s: this.v_meldungsblock) {
			g._a(new RB_lab()._t(s), new RB_gld()._ins(2,2,2,2));
		}
		
		E2_Grid gButtons = new E2_Grid()._setSize(this.getPopupWidth()/2,this.getPopupWidth()/2);
		gButtons._a(this.getOwnSaveButton(),new RB_gld()._center_mid()._ins(2))._a(this.getOwnCloseButton(),new RB_gld()._center_mid()._ins(2));
		
		g._a(gButtons, new RB_gld()._ins(2, 10, 2, 2));

		container.add(g,E2_INSETS.I(1,1,1,1));
		return container;
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4Popup#check4break(panter.gmbh.Echo2.Messaging.MyE2_MessageVector)
	 */
	@Override
	protected boolean check4break(MyE2_MessageVector mv) throws myException {
		boolean popupZaehler =(this.getBreak4PopupController().getHmCounter().get(this)==0);

		boolean breakNeeded = false;
		
		HashMap<String,String> parameters  = (HashMap<String,String>) m_transportMap.getSB("PARAMETERLISTE");
		RB_MaskController con = new RB_MaskController(m_mapCollector);
		if (parameters!=null && parameters.size()>0 && !con.isNew() && con.isEditable()) {	
			Long id_rep_varianten = con.getLongDBVal(REP_VARIANTEN.id_rep_varianten);
			
			Rec21 rv = new Rec21(_TAB.rep_varianten)._fill_id(id_rep_varianten);
			
			RecList21 rlParams = rv.get_down_reclist21(REP_VARIANTEN_PARAM.id_rep_varianten);
			
			for (Rec21 param: rlParams) {
				if (!parameters.keySet().contains(param.getUfs(REP_VARIANTEN_PARAM.param_name))) {
					breakNeeded=true;
				}
			}
		}
		
		return breakNeeded&&popupZaehler;
	}

}
