/**
 * 
 */
package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.Routen;

import java.math.BigDecimal;
import java.util.HashMap;

import panter.gmbh.BasicInterfaces.ServiceBean.PdServiceGeoRoutingEntfernungUndKostenBean;
import panter.gmbh.BasicInterfaces.ServiceBeanInterface.PdServiceGeoRoutingEntfernungUndKosten.ENUM_ROUTING_TYP;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.O;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public class FU_MASK_btRoutingEntfernung extends E2_Button {
	public FU_MASK_btRoutingEntfernung() {

		super();
		this._ttt(new MyE2_String("Routen planen, Entfernung und Zeitprognose"));
		this.__setImages(E2_ResourceIcon.get_RI("routing.png"),E2_ResourceIcon.get_RI("routing__.png"));
	
		this._aaa(new ownActionAgent());
		
	}

	
	private class ownActionAgent extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			E2_ComponentMAP map = FU_MASK_btRoutingEntfernung.this.EXT().get_oComponentMAP();
			if (map!=null) {
				Long lStart =  map.get_LActualDBValue(VPOS_TPA_FUHRE.id_adresse_lager_start.fn(),null,null);
				Long lZiel =   map.get_LActualDBValue(VPOS_TPA_FUHRE.id_adresse_lager_ziel.fn(),null,null);
				
				if (O.isNoOneNull(lStart,lZiel)) {
					Rec20 recAdresseStart = new Rec20(_TAB.adresse)._fill_id(lStart.longValue());
					Rec20 recAdresseZiel = new Rec20(_TAB.adresse)._fill_id(lZiel.longValue());
					
					BigDecimal bdLatitudeStart = recAdresseStart.get_raw_resultValue_bigDecimal(ADRESSE.latitude);
					BigDecimal bdLongitudeStart = recAdresseStart.get_raw_resultValue_bigDecimal(ADRESSE.longitude);
					BigDecimal bdLatitudeZiel = recAdresseZiel.get_raw_resultValue_bigDecimal(ADRESSE.latitude);
					BigDecimal bdLongitudeZiel = recAdresseZiel.get_raw_resultValue_bigDecimal(ADRESSE.longitude);
					
					if (O.isNoOneNull(bdLatitudeStart ,bdLongitudeStart ,bdLatitudeZiel ,bdLongitudeZiel )) {
						PdServiceGeoRoutingEntfernungUndKostenBean service = new PdServiceGeoRoutingEntfernungUndKostenBean();
						MyE2_MessageVector mv = new MyE2_MessageVector();
						HashMap<ENUM_ROUTING_TYP,BigDecimal> response_map = service.getDistanceTimeAmdCosts(bdLatitudeStart, bdLongitudeStart, bdLatitudeZiel, bdLongitudeZiel, null, null, mv);
						
						if (mv.get_bIsOK()) {
							
							BigDecimal bdDiffKm = response_map.get(ENUM_ROUTING_TYP.ENTFERNUNG);
							BigDecimal bdDiffZeit = response_map.get(ENUM_ROUTING_TYP.ZEIT_MIN);
							
							if (bdDiffKm!=null ) {
								((MyE2IF__DB_Component)map.get__Comp(VPOS_TPA_FUHRE.routing_distance_km)).set_cActual_Formated_DBContent_To_Mask(
										new MyBigDecimal(bdDiffKm).get_FormatedRoundedNumber(2),E2_ComponentMAP.STATUS_EDIT, null);
							}
							if (bdDiffZeit!=null ) {
								((MyE2IF__DB_Component)map.get__Comp(VPOS_TPA_FUHRE.routing_time_minutes)).set_cActual_Formated_DBContent_To_Mask(
										new MyBigDecimal(bdDiffZeit).get_FormatedRoundedNumber(0),E2_ComponentMAP.STATUS_EDIT, null);
							}
							
							
						} else {
							bibMSG.MV()._add(mv);
						}
						
						
						
						
					} else {
						bibMSG.MV()._addAlarm("Die Geokoordinaten sind nicht vollständig!");
					}
				} else {
					bibMSG.MV()._addAlarm("Ein Routing ist nicht möglich, da die Adressen nicht definiert sind !");
				}
				
			} else {
				bibMSG.MV()._addAlarm("Unbekannter Fehler, die Maskeneinträge konnten nicht identifiziert werden !");
			}
			
			
		}
		
	}
	
}
