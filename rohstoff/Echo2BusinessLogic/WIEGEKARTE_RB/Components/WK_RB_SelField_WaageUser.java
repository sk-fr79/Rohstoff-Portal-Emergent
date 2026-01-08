/**
 * rohstoff.Echo2BusinessLogic.WIEGEKARTE_MASK_RB.Components
 * @author manfred
 * @date 14.04.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components;

import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V22;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_ENUMS.WAAGE_USER;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.TERM.Nvl;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_LIST_Selector;


/**
 * @author manfred
 * @date 14.04.2020
 *
 */
public class WK_RB_SelField_WaageUser extends RB_selField {

	private SEL 		_selBase = null;
	private RB_TransportHashMap _tpHashMap = null;
	/**
	 * @author manfred
	 * @throws myException 
	 * @date 14.04.2020
	 *
	 */
	public WK_RB_SelField_WaageUser(RB_TransportHashMap  p_tpHashMap) throws myException {
		super();
		_tpHashMap = p_tpHashMap;
		
		refreshData("-1");

	}

	
	private WK_RB_SelField_WaageUser refreshData(String id_waage_standort) {
		
		try {
			
			VEK<ParamDataObject> vecParam = new VEK<ParamDataObject>();
			
			_selBase = new SEL()
					.ADDFIELD(USER.name1.tnfn() + " ||', ' || " + USER.vorname.tnfn() )
					.ADDFIELD(WAAGE_USER.id_user )
					.FROM(_TAB.waage_user)
					.INNERJOIN(_TAB.user, WAAGE_USER.id_user, USER.id_user)
					.WHERE(new TermSimple(WAAGE_USER.id_mandant.tnfn() + " = ? "))
					.AND(new vgl(new Nvl(WAAGE_USER.aktiv.t(), new TermSimple("'Y'")), new TermSimple("'Y'")))
					.AND(new TermSimple(WAAGE_USER.id_waage_standort .tnfn()  + " =  ? "))
					.ORDERUP(WAAGE_USER.sortierung).ORDERUP(WAAGE_USER.id_user)
					;
			
			MyLong l = new MyLong(id_waage_standort);
			if (!l.isOK()) {
				l = new MyLong(-1L);
			}
			
			vecParam._a(new Param_Long(Long.parseLong(bibALL.get_ID_MANDANT())))
					._a(new Param_Long(l.get_lValue()));
			
			
			String [][] cArrDaten = null;
			
			SqlStringExtended  sqlExt = new SqlStringExtended(_selBase.s())._addParameters(vecParam);
			cArrDaten =  bibDB.EinzelAbfrageInArray(sqlExt,(String)null);
			
			// Leereintrag vorne dran hängen
			String [][] cArr = new String[cArrDaten.length +1][2];
			int i = 0;
			cArr[i][0] = "-";
			cArr[i][1] = "";
			i++;
			for (String[] element : cArrDaten) {
				cArr[i][0] = element[0];
				cArr[i][1] = element[1];
				i++;
			}
			
			_populate(cArr);
			_setActiveOrFirstMaskVal("");

		
		} catch (myException e) {
			
		}
		return this;
	}

	
	
	public WK_RB_SelField_WaageUser refreshData() throws myException {
		String _idStandort = null;
		if (_tpHashMap.getLastMaskLoadStatus().equals(MASK_STATUS.EDIT) || _tpHashMap.getLastMaskLoadStatus().equals(MASK_STATUS.VIEW)) {
			RB_Dataobject_V22 dataobj = (RB_Dataobject_V22) _tpHashMap.getMaskDataObjectsCollector().getVectorDataobjects().get(0);
			_idStandort 	= dataobj.get_ufs_dbVal(WIEGEKARTE.id_waage_standort);
		} else {
			WK_RB_LIST_Selector sel= (WK_RB_LIST_Selector) _tpHashMap.get(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SELECTOR);
			_idStandort 	= sel.get_IDWaageStandort();
		}

		refreshData(_idStandort);

		return this;
	}
	
	
	
	
	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		return;
	}
	
	
	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.RB_selField#rb_readValue_4_dataobject()
	 */
	@Override
	public String rb_readValue_4_dataobject() {
		return "";
	}
	
	
	/**
	 * gibt den aktuellen DB-Wert zurück
	 * @author manfred
	 * @date 19.06.2020
	 *
	 * @return
	 */
	public String get_selected_dbVal() {
		return this.getVCompleteDBVals().get(this.getSelectedIndex());
	}
	
	
}
