/**
 * rohstoff.Echo2BusinessLogic.WIEGEKARTE_MASK_RB.Components
 * @author manfred
 * @date 14.04.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components;

import java.util.ArrayList;

import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_USER_BEFUND;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;


/**
 * 
 * @author manfred
 * @date 07.07.2020
 *
 */
public class WK_RB_SelField_BefundungUser extends RB_selField {

	private SEL 		_selBase = null;
	private RB_TransportHashMap _tpHashMap = null;
	/**
	 * @author manfred
	 * @throws myException 
	 * @date 14.04.2020
	 *
	 */
	public WK_RB_SelField_BefundungUser(RB_TransportHashMap  p_tpHashMap) throws myException {
		super();
		_tpHashMap = p_tpHashMap;
		
		refreshData();
		_setActiveOrFirstMaskVal("");
	}

	
	private WK_RB_SelField_BefundungUser refreshData() {
		
		try {
			
			VEK<ParamDataObject> vecParam = new VEK<ParamDataObject>();
			
			_selBase = new SEL()
					.ADDFIELD(USER.name1.tnfn() + " ||', ' || " + USER.vorname.tnfn() )
					.ADDFIELD(WIEGEKARTE_USER_BEFUND.id_wiegekarte_user_befund )
					.ADDFIELD(WIEGEKARTE_USER_BEFUND.aktiv)
					.FROM(_TAB.wiegekarte_user_befund)
					.INNERJOIN(_TAB.user, WIEGEKARTE_USER_BEFUND.id_user, USER.id_user)
					.WHERE(new TermSimple(WIEGEKARTE_USER_BEFUND.id_mandant.tnfn() + " = ? "))
//					.AND(new vgl(new Nvl(WIEGEKARTE_USER_BEFUND.aktiv.t(), new TermSimple("'Y'")), new TermSimple("?")))
					.ORDERUP(USER.name1).ORDERUP(USER.vorname)
					;
			
			
			vecParam._a(new Param_Long(Long.parseLong(bibALL.get_ID_MANDANT())));
					
			String [][] cArrDaten = null;
			
			SqlStringExtended  sqlExt = new SqlStringExtended(_selBase.s())._addParameters(vecParam);
			
			cArrDaten = bibDB.EinzelAbfrageInArray(sqlExt, (String) null);
			
			ArrayList<String[]> alAktiv = new ArrayList<>();
			ArrayList<String[]> alInAktiv = new ArrayList<>();

			
			// Leereintrag vorne dran hängen
			alAktiv.add(new String[]{"-",""}) ;
			String sAktiv = "";


			for (String[] element : cArrDaten) {
				sAktiv = element[2];
				if (sAktiv != null && sAktiv.equalsIgnoreCase("Y")) {
					alAktiv.add(new String[]{element[0],element[1]}) ;
				} else {
					alInAktiv.add(new String[]{element[0],element[1]}) ;
				}
			}
			
			String [][] aAktiv = new String[alAktiv.size()][2];
			String [][] aInAktiv = new String[alInAktiv.size()][2];
			
			int i=0;
			for (String[] row : alAktiv) {
				aAktiv[i][0] = row[0];
				MyLong l = new MyLong(row[1]);
				aAktiv[i][1] = l.get_cF_LongString();
				i++;
			}
			
			i=0;
			for (String[] row : alInAktiv) {
				aInAktiv[i][0] = row[0];
				MyLong l = new MyLong(row[1]);
				aAktiv[i][1] = l.get_cF_LongString();
				i++;
			}
			
			_populate(aAktiv);
			_populateShadow(aInAktiv);

		
		} catch (myException e) {
			
		}
		return this;
	}

	
	
//	@Override
//	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
//		return;
//	}
	
	
	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.RB_selField#rb_readValue_4_dataobject()
	 */
//	@Override
//	public String rb_readValue_4_dataobject() {
//		return "";
//	}
	
	
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
