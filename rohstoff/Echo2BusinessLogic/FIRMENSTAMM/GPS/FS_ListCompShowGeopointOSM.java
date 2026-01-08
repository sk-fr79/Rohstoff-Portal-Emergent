package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.GPS;

import java.math.BigDecimal;

import panter.gmbh.BasicInterfaces.ServiceBean.PdServiceShowAddressLocationsOnMapBean;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.DB.MyE2_DB_PlaceHolder_NT;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class FS_ListCompShowGeopointOSM extends MyE2_DB_PlaceHolder_NT {

	public FS_ListCompShowGeopointOSM() throws myException {
		super();
	}

	@Override
	public void set_cActual_Formated_DBContent_To_Mask_WhenVisisble(String value_from_db, String cMASK_STATUS,	SQLResultMAP oResultMAP) throws myException {
		//nachsehen, ob die geokoordinaten vorhanden sind
		BigDecimal bdLatitude = oResultMAP.get_bdActualValue(ADRESSE.latitude.fn(), false);
		BigDecimal bdLongitude = oResultMAP.get_bdActualValue(ADRESSE.longitude.fn(), false);
		
		this.removeAll();
		if (bdLatitude!=null && bdLongitude!=null) {
			this._add_r(new btOpenUrl(oResultMAP.get_cUNFormatedROW_ID()), new RB_gld()._center_top()._ins(2));
		} else {
			this._add_r(new RB_lab(E2_ResourceIcon.get_RI("gps_klein__.png")), new RB_gld()._center_top()._ins(2));
		}
	}

	
	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		try {
			return new FS_ListCompShowGeopointOSM();
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.getLocalizedMessage());
		}
	}
	
	
	
	
	private class btOpenUrl extends E2_Button {
		private String _sIDAdress = null;

		public btOpenUrl(String sIDAddress) throws myException {
			super();
			this._sIDAdress  = sIDAddress;
			
			this._image(E2_ResourceIcon.get_RI("gps_klein.png"), true);
			this._ttt(S.ms("Karte öffnen"));
			this._aaa(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					PdServiceShowAddressLocationsOnMapBean o  = new PdServiceShowAddressLocationsOnMapBean();
					o.showSingleAddressLocationsOnMap(_sIDAdress);
				};
			} );
		};
	}
	

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
	
	}

}
