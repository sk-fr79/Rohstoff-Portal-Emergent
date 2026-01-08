/**
 * 
 */
package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.GPS;

import java.math.BigDecimal;

import nextapp.echo2.app.Extent;
import panter.gmbh.BasicInterfaces.Service.PdServiceFindNearestPtFromGeoMidPt;
import panter.gmbh.BasicInterfaces.ServiceBean.PdServiceGeoCodeNameStrassePlzOrtLandBean;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.GEOCODIERUNG.ENUM_GEO_Error;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO_BtShowGeopointOnMap;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO_ErrorMap;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO_Location;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO_Locations;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO__LocationConst;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 *
 */
public class FS_MaskBtGeoCodingAdresse extends E2_Button {


	
	/**
	 * rueckgabe-felder:
	 * @param p_tfLongitude
	 * @param p_tfLatitude
	 */
	public FS_MaskBtGeoCodingAdresse(MyE2_DB_TextField p_tfLongitude, MyE2_DB_TextField p_tfLatitude) {
		super();
		
		this._image(E2_ResourceIcon.get_RI("gps_search.png"), true)._aaa(new ownAction());
		
	}
	

	
//	private class ownAction extends XX_ActionAgent {
//		
//		@Override
//		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
//			E2_ComponentMAP maskMap = FS_MaskBtGeoCodingAdresse.this.EXT().get_oComponentMAP();
//			
//			//die noetigen angaben
//			String idAdress = null;
//			String isoLaenderCode = null;
//			String strasse = null;
//			String hnr = null;
//			String plz = null;
//			String ort = null;
//			
//			if (maskMap.get_bIs_Neueingabe()) {
//				idAdress="0";   //pseudowert
//			} else {
//				idAdress=maskMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
//			}
//			
//			MyLong idLand = new MyLong(maskMap.get_cActualDBValueFormated(ADRESSE.id_land.fn()));
//			if (idLand.isOK()) {
//				isoLaenderCode = new Rec20(_TAB.land)._fill_id(idLand.get_oLong()).getUfs(LAND.iso_country_code);
//			} else {
//				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Das Land ist nicht definiert !"));
//				return;
//			}
//			
//			strasse = maskMap.get_cActualDBValueFormated(ADRESSE.strasse.fn());
//			hnr = maskMap.get_cActualDBValueFormated(ADRESSE.hausnummer.fn());
//			plz = maskMap.get_cActualDBValueFormated(ADRESSE.plz.fn());
//			ort = maskMap.get_cActualDBValueFormated(ADRESSE.ort.fn());
//			
//			GEO_ErrorMap errorMap = new GEO_ErrorMap("Maskenabfrage");
//			
//			GEO_Location loc = new PdServiceGeoCodeNameStrassePlzOrtLandBean().getLocation(idAdress, isoLaenderCode, strasse, hnr, plz, ort, errorMap);
//			
//			if (loc!=null) {
//				maskMap.set_cActualDatabaseValueToMask(ADRESSE.longitude.fn(), loc.get_longitude().get_FormatedRoundedNumber(8));
//				maskMap.set_cActualDatabaseValueToMask(ADRESSE.latitude.fn(), loc.get_latitude().get_FormatedRoundedNumber(8));
//				
//				bibMSG.add_MESSAGE(new MyE2_Info_Message(S.ms("Geo-Koordinaten gefunden !")));
//			} else {
//				if (errorMap.keySet().size()>0) {
//					ENUM_GEO_Error status = errorMap.getStatusMeldung(idAdress);
//					
//					if (status == ENUM_GEO_Error.NO_EXACT_LOCALISATION) {
//						new OwnPopupWindow();
//					} else {
//						if (status != ENUM_GEO_Error.UNDEFINED_ERROR) {
//							if (status.isErrorStatus()) {
//								bibMSG.add_MESSAGE(new MyE2_Alarm_Message(status.user_text()));
//							} else {
//								bibMSG.add_MESSAGE(new MyE2_Info_Message(status.user_text()));
//							}
//						}
//					}
//				}
//			}
//		}
//	};
	

	/**
	 * 
	 * @author martin
	 * @date 15.10.2018  aenderung wegen tieferer suche
	 *
	 */
	private class ownAction extends XX_ActionAgent {
		

		public ownAction() {
			super();
		}

		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			E2_ComponentMAP maskMap = FS_MaskBtGeoCodingAdresse.this.EXT().get_oComponentMAP();
			
			//die noetigen angaben
			String idAdress = null;
			String isoLaenderCode = null;
			
			if (maskMap.get_bIs_Neueingabe()) {
				idAdress="0";   //pseudowert
			} else {
				idAdress=maskMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
			}
			
			MyLong idLand = new MyLong(maskMap.get_cActualDBValueFormated(ADRESSE.id_land.fn()));
			if (idLand.isOK()) {
				isoLaenderCode = new Rec20(_TAB.land)._fill_id(idLand.get_oLong()).getUfs(LAND.iso_country_code);
			} else {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Das Land ist nicht definiert !"));
				return;
			}
			
			String strasse = maskMap.get_cActualDBValueFormated(ADRESSE.strasse.fn());
			String hnr = maskMap.get_cActualDBValueFormated(ADRESSE.hausnummer.fn());
			String plz = maskMap.get_cActualDBValueFormated(ADRESSE.plz.fn());
			String ort = maskMap.get_cActualDBValueFormated(ADRESSE.ort.fn());
			
			GEO_ErrorMap errorMap = new GEO_ErrorMap("Maskenabfrage");
			
			//zuerst alle loaklisierungspunkte holen
			GEO_Locations locs = new PdServiceGeoCodeNameStrassePlzOrtLandBean().getLocations(idAdress, isoLaenderCode, strasse, hnr, plz, ort, errorMap);
			
			GEO_Locations locs_good = new GEO_Locations();
			
			
//			DEBUG._print("--- start ----------------------------");
//			for (GEO_Location l: locs) {
//				DEBUG._print("Länge/Breite: "+l.get_latitude().get_UnFormatedRoundedNumber(6)+"  "+l.get_longitude().get_UnFormatedRoundedNumber(6)+"   "+l.get_ort()+" / "+l.get_strasse()+" / "+l.get_hausnummer());				
//			}
//			DEBUG._print("--- stop ----------------------------");
			
			
			
			if (locs==null || locs.size() == 0) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(ENUM_GEO_Error.NO_COORDINATE.user_text()));
			} else {
				
				// Gewichtungen erzeugen und
				// prüfen, wenn mehrere da sind, ob man welche genügend gewichtung haben
				locs.setSearchDataForWeight(idAdress, hnr, strasse, plz, ort, isoLaenderCode,60);
				
				locs.calculateWeights();
//				for (GEO_Location l : locs){
//					
//					if(l.get_weight() > 70){
//						locs_good._a(l);
//					}
//				}
				
				// wenn man keine guten gefunden hat, dann alle rin in die list
//				if (locs_good.size() == 0){
//					locs_good._a(locs);
//				}

				locs_good = locs;
				
				//jetzt den status der aktuellen toleranzen holen
				int 			maxNumberNodes = GEO__LocationConst.MaxNumberFoundNotes;
				BigDecimal 		maxDiffInMeters = GEO__LocationConst.GeoToleranceDiff;
				
				GEO_Location loc = locs_good.getSingleLocation(maxNumberNodes, maxDiffInMeters);
				
//				GEO_Location loc = locs_good.getSingleLocationByWeight(); //(maxNumberNodes, maxDiffInMeters);

//				GEO_Location loc = null;
//				if (locs_good.size() == 1){
//					loc = locs_good.get(0);
//				} 
				
				
				if (loc!=null) {
				
					maskMap.set_cActualDatabaseValueToMask(ADRESSE.longitude.fn(), loc.get_longitude().get_FormatedRoundedNumber(8));
					maskMap.set_cActualDatabaseValueToMask(ADRESSE.latitude.fn(), loc.get_latitude().get_FormatedRoundedNumber(8));
					
					bibMSG.add_MESSAGE(new MyE2_Info_Message(S.ms("Geo-Koordinaten gefunden !")));
				} else {
					ENUM_GEO_Error error = locs_good.getLastError();
					if (locs_good.getLastError()!=null) {
						errorMap._add(error, idAdress);
					} else {
						errorMap._add(ENUM_GEO_Error.UNDEFINED_ERROR, idAdress);
					}
					
					
					if (errorMap.keySet().size()>0) {
						ENUM_GEO_Error lastError = errorMap.getStatusMeldung(idAdress);
						
						if (lastError == null) {
							lastError = ENUM_GEO_Error.UNDEFINED_ERROR;
						}
						
						if (lastError == ENUM_GEO_Error.NO_EXACT_LOCALISATION) {
							new OwnPopupWindow(locs_good);
						} else {
							if (lastError.isErrorStatus()) {
								bibMSG.add_MESSAGE(new MyE2_Alarm_Message(lastError.user_text()));
							} else {
								bibMSG.add_MESSAGE(new MyE2_Info_Message(lastError.user_text()));
							}
						}
					}
				}
			}
		}
	};

	
	
	/**
	 * weitere funktion im dialog (modus auf der maske): Falls mehr als die 
	 * GEO__LocationConst.MaxNumberFoundNotes (6) oder punkte gefunden werden oder GEO__LocationConst.GeoToleranceDiff (500) dann mit anderen rahmendaten weitersuchen;
	 */
	private class OwnPopupWindow extends E2_BasicModuleContainer {
		
		//private	HashMap<LocationInfos, Object> hmInfoIntern = new HashMap<>();
		
		public OwnPopupWindow(GEO_Locations locations) throws myException {
			super();

			//jetzt den status der aktuellen toleranzen holen
			int 			maxNumberNodesPrevious = 	GEO__LocationConst.MaxNumberFoundNotes;
			BigDecimal 		maxDiffInMetersPrevious = 	GEO__LocationConst.GeoToleranceDiff;
			
			
			E2_Grid g = new E2_Grid()
//						._setSize(400,30,30)
//						._s(3)
						._setSize(new Extent(90, Extent.PERCENT),new Extent(5, Extent.PERCENT),new Extent(5, Extent.PERCENT))
						._a(new RB_lab()._t(S.ms("Es wurden offensichtlich Geopunkte gefunden, aber entweder zuviele (>"+maxNumberNodesPrevious+") ")), new RB_gld()._span(3)._ins(3, 3, 3, 1)._left_mid())
						._a(new RB_lab()._t(S.ms("oder in zu weiter Enfernung voneinander (>"+maxDiffInMetersPrevious.longValue()+" m)")), new RB_gld()._span(3)._ins(3, 1, 3, 10)._left_mid());
			
			for (GEO_Location l: locations) {
				g	._a(new RB_lab()._t(l.getShortTextInfo(true)), new RB_gld()._ins(3,3,3,1)._left_mid())
					._a(new OwnButtonGeo(l), new RB_gld()._ins(10,3,3,1)._left_mid())
					._a(new OwnButtonOk(l), new RB_gld()._ins(10,3,3,1)._left_mid());
			}
			
			g.setWidth(new Extent(100, Extent.PERCENT) );
			
			this.add(g, E2_INSETS.I(5));
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(600),new Extent(400), S.ms("Ungenaue Bestimmung ..."));
			
		}
		
		
		private class OwnButtonGeo extends GEO_BtShowGeopointOnMap {
			private GEO_Location m_loc = null;

			public OwnButtonGeo(GEO_Location loc) throws myException {
				super();
				this._image(E2_ResourceIcon.get_RI("gps.png"), true);
				this._ttt(S.ms("Karte zu den GPS-Koordinaten der Adresse aufrufen ..."));
				this.m_loc = loc;
			}

			/* (non-Javadoc)
			 * @see panter.gmbh.basics4project.GEOCODIERUNG.GEO_BtShowGeopointOnMap#getLatitude()
			 */
			@Override
			public BigDecimal getLatitude() throws myException {
				return m_loc.get_latitude().get_bdWert();
			}

			/* (non-Javadoc)
			 * @see panter.gmbh.basics4project.GEOCODIERUNG.GEO_BtShowGeopointOnMap#getLongitude()
			 */
			@Override
			public BigDecimal getLongitude() throws myException {
				return m_loc.get_longitude().get_bdWert();
			}
		}
		

		
		private class OwnButtonOk extends E2_Button {
			private GEO_Location m_loc = null;

			public OwnButtonOk(GEO_Location loc) {
				super();
				this._s_Image()._image("ok_small.png");
				this._ttt(S.ms("Koordinate übernehmen ..."));
				this.m_loc = loc;
				this._aaa(new ActionSetLocationToMask());
				this._aaa(()->{OwnPopupWindow.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);});
			}
			
			private class ActionSetLocationToMask extends XX_ActionAgent {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					E2_ComponentMAP maskMap = FS_MaskBtGeoCodingAdresse.this.EXT().get_oComponentMAP();
					
					maskMap.set_cActualDatabaseValueToMask(ADRESSE.longitude.fn(), OwnButtonOk.this.m_loc.get_longitude().get_FormatedRoundedNumber(8));
					maskMap.set_cActualDatabaseValueToMask(ADRESSE.latitude.fn(), OwnButtonOk.this.m_loc.get_latitude().get_FormatedRoundedNumber(8));
				}
			}
		}

	}
	
}
