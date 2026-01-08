/**
 * 
 */
package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.GPS;

import java.math.BigDecimal;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO_BtShowGeopointOnMap;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public class FS_MaskBtShowGeopoint extends GEO_BtShowGeopointOnMap {

	
	/**
	 * @throws myException
	 */
	public FS_MaskBtShowGeopoint() throws myException {
		super();
		this._image(E2_ResourceIcon.get_RI("geo_karte.png"), true);
		this._ttt(S.ms("Karte zu den GPS-Koordinaten der Adresse aufrufen ..."));
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.basics4project.GEOCODIERUNG.GEO_BtShowGeopointOnMap#getLatitude()
	 */
	@Override
	public BigDecimal getLatitude() throws myException {
		MyBigDecimal mBd= new MyBigDecimal(this.EXT().get_oComponentMAP().get_cActualDBValueFormated(ADRESSE.latitude.fn()));
		if (mBd.isOK()) {
			return mBd.get_bdWert();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.basics4project.GEOCODIERUNG.GEO_BtShowGeopointOnMap#getLongitude()
	 */
	@Override
	public BigDecimal getLongitude() throws myException {
		MyBigDecimal mBd= new MyBigDecimal(this.EXT().get_oComponentMAP().get_cActualDBValueFormated(ADRESSE.longitude.fn()));
		if (mBd.isOK()) {
			return mBd.get_bdWert();
		}
		return null;
	}

}
