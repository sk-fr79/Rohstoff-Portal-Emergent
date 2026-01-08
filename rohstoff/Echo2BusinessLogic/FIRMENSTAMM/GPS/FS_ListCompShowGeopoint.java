package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.GPS;

import java.math.BigDecimal;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.DB.MyE2_DB_PlaceHolder_NT;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO_BtShowGeopointOnMap;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class FS_ListCompShowGeopoint extends MyE2_DB_PlaceHolder_NT {

	public FS_ListCompShowGeopoint() throws myException {
		super();
	}

	@Override
	public void set_cActual_Formated_DBContent_To_Mask_WhenVisisble(String value_from_db, String cMASK_STATUS,	SQLResultMAP oResultMAP) throws myException {
		//nachsehen, ob die geokoordinaten vorhanden sind
		BigDecimal bdLatitude = oResultMAP.get_bdActualValue(ADRESSE.latitude.fn(), false);
		BigDecimal bdLongitude = oResultMAP.get_bdActualValue(ADRESSE.longitude.fn(), false);
		
		this.removeAll();
		if (bdLatitude!=null && bdLongitude!=null) {
			this._add_r(new btOpenUrl(bdLatitude,bdLongitude), new RB_gld()._center_top()._ins(2));
		} else {
			this._add_r(new RB_lab(E2_ResourceIcon.get_RI("geo_karte.png")), new RB_gld()._center_top()._ins(2));
		}
	}

	
	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		try {
			return new FS_ListCompShowGeopoint();
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.getLocalizedMessage());
		}
	}
	
	
	
	
	private class btOpenUrl extends GEO_BtShowGeopointOnMap {

		private BigDecimal bdLatitude;
		private BigDecimal bdLongitude;

		public btOpenUrl(BigDecimal bdLatitude, BigDecimal bdLongitude) throws myException {
			super();
			this.bdLatitude = bdLatitude;
			this.bdLongitude = bdLongitude;
			this._image(E2_ResourceIcon.get_RI("geo_karte.png"), true);
			this._ttt(S.ms("Karte zu den GPS-Koordinaten "	+MyNumberFormater.formatDez(btOpenUrl.this.bdLatitude, 8, false, '.',null, false)
															+","
															+MyNumberFormater.formatDez(btOpenUrl.this.bdLongitude, 8, false, '.',null, false)
															+" aufrufen ..."
							));
		}

		@Override
		public BigDecimal getLatitude() throws myException {
			return this.bdLatitude;
		}

		@Override
		public BigDecimal getLongitude() throws myException {
			return this.bdLongitude;
		}
	}
	

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
	
	}
}
