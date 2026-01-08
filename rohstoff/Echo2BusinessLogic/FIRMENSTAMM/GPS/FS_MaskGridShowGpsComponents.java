/**
 * 
 */
package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.GPS;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * maskengrid fuer die gps-erfassung
 */
public class FS_MaskGridShowGpsComponents extends E2_Grid {

	/**
	 * 
	 * @param fieldLongitude
	 * @param fieldLatitude
	 * @param btSearchGPS
	 * @throws myException 
	 */
	public FS_MaskGridShowGpsComponents(Component fieldLongitude, Component fieldLatitude, Component btSearchGPS, Component btShowInMap, Component btShowAllInMap) throws myException {
		super();

		this._setSize(100,100,40,40,40)._bo_dd();

		this._a(new RB_lab()._tr("Breitengrad")._fsa(-2)._i(), new RB_gld()._center_mid()._ins(2))
		._a(new RB_lab()._tr("Längengrad")._fsa(-2)._i(), new RB_gld()._center_mid()._ins(2))
		._a(btSearchGPS, new RB_gld()._center_mid()._ins(2)._span_r(2));
		if(ENUM_MANDANT_DECISION.USE_GEOPOINT_SHOW.is_YES()) {
			this._a(btShowInMap, new RB_gld()._center_mid()._ins(2)._span_r(2));
		}else {
			this._a("", new RB_gld()._center_mid()._ins(2)._span_r(2));
		}
		if(ENUM_MANDANT_DECISION.USE_GEOPOINT_SHOW.is_YES() && btShowAllInMap != null) {
			this._a(btShowAllInMap, new RB_gld()._center_mid()._ins(2)._span_r(2));
		}else {
			this._a("", new RB_gld()._center_mid()._ins(2)._span_r(2));
		}
		this._a(fieldLatitude, new RB_gld()._center_mid()._ins(2))
			._a(fieldLongitude, new RB_gld()._center_mid()._ins(2))
		;
	}



}
