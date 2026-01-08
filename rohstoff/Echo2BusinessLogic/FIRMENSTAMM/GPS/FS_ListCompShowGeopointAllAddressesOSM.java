package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.GPS;

import java.math.BigDecimal;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.DB.MyE2_DB_PlaceHolder_NT;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.GEOCODIERUNG.ENUM_GEO_MARKER_COLOR;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO_BtShowGeoPointsOnOSMLocal;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO_Coordinate4Map;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO_Factory_URLForLocations;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;

public class FS_ListCompShowGeopointAllAddressesOSM extends MyE2_DB_PlaceHolder_NT {

	public FS_ListCompShowGeopointAllAddressesOSM() throws myException {
		super();
	}

	@Override
	public void set_cActual_Formated_DBContent_To_Mask_WhenVisisble(String value_from_db, String cMASK_STATUS,
			SQLResultMAP oResultMAP) throws myException {

		String id = oResultMAP.get_cUNFormatedROW_ID();

		// nachsehen, ob die geokoordinaten vorhanden sind
		BigDecimal bdLatitude = oResultMAP.get_bdActualValue(ADRESSE.latitude.fn(), false);
		BigDecimal bdLongitude = oResultMAP.get_bdActualValue(ADRESSE.longitude.fn(), false);

		this.removeAll();
		if (bdLatitude != null && bdLongitude != null) {
			this._add_r(new btOpenUrl(id), new RB_gld()._center_top()._ins(2));
		} else {
			this._add_r(new RB_lab(E2_ResourceIcon.get_RI("geo_punkte_show__.png")),
					new RB_gld()._center_top()._ins(2));
		}
	}

	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		try {
			return new FS_ListCompShowGeopointAllAddressesOSM();
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.getLocalizedMessage());
		}
	}

	private class btOpenUrl extends GEO_BtShowGeoPointsOnOSMLocal {

		private MyLong _id;

		public btOpenUrl(String id_Address) throws myException {
			super();
			this._id = new MyLong(id_Address);
		}

		@Override
		public GEO_Factory_URLForLocations getURLFactory() throws myException {
			if (_id.isOK()) {
				GEO_Factory_URLForLocations factory = new GEO_Factory_URLForLocations();

				boolean bShowText = true;

				Rec21_adresse r;
				try {

					r = new Rec21_adresse()._fill_id(_id.get_cUF_LongString());
					if (r != null) {

						RecList21 rl = r.getMainStoreAdresses();
						if (rl.size() > 100) {
							bShowText = false;
						}

						String text = "";
						for (Rec21 adr : rl) {

							BigDecimal lat = adr.get_raw_resultValue_bigDecimal(ADRESSE.latitude);
							BigDecimal lng = adr.get_raw_resultValue_bigDecimal(ADRESSE.longitude);

							if (rl.size() > 100) {
								text = "";
							} else if (rl.size() > 50) {
								text = adr.get_fs_dbVal(ADRESSE.id_adresse);
							} else if (rl.size() > 30) {

								text = adr.get_ufs_dbVal(ADRESSE.name1) + " " + adr.get_fs_dbVal(ADRESSE.name2, "");
								if (text.length() > 30) {
									text = text.substring(0, 30) + "...";
								}
								text = text + " (" + adr.get_fs_dbVal(ADRESSE.id_adresse) + ")";
							} else {
								text = adr.get_ufs_dbVal(ADRESSE.name1) + " " + adr.get_fs_dbVal(ADRESSE.name2, "");
								if (text.length() > 50) {
									text = text.substring(0, 50) + "...";
								}
								text = text + " (" + adr.get_fs_dbVal(ADRESSE.id_adresse) + ")";
							}

							String id = adr.get_ufs_dbVal(ADRESSE.id_adresse);

							ENUM_GEO_MARKER_COLOR col = id.equals(_id.get_cUF_LongString())
									? ENUM_GEO_MARKER_COLOR.GREEN : ENUM_GEO_MARKER_COLOR.YELLOW;

							if (lat == null || lng == null)
								continue;

							try {
								GEO_Coordinate4Map c = new GEO_Coordinate4Map(lat, lng, text, col);
								factory.addGeoCoordiante(c);
							} catch (myException e) {
								// fehler beim der Koordinatenumwandlung
							}
						}
					}

				} catch (myException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return factory;
			}

			return null;

		}

	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {

	}
}
