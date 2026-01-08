package panter.gmbh.basics4project.GEO_FAHRPLAN;

import java.math.BigDecimal;
import java.util.Vector;

import panter.gmbh.BasicInterfaces.ServiceBean.PdServiceGeoCodeShowRouteBean;
import panter.gmbh.BasicInterfaces.ServiceBean.PdServiceShowRoutingBetweenAddressesBean;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.O;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

public class GEO_FAHR_Routing_bt_show extends E2_Button{

	private IF_Field start_adresseId_field;
	private IF_Field ziel_adresseId_field;

	public GEO_FAHR_Routing_bt_show(IF_Field p_start_adresseId_field,IF_Field p_ziel_adresseId_field) throws myException {
		super();
		this._image("routing_show.png");
		this._ttt("Kartendarstellung aufrufen");
		this.start_adresseId_field = 	p_start_adresseId_field;
		this.ziel_adresseId_field = 	p_ziel_adresseId_field;	

		this._aaa(()-> {call_routing_maps();});
	}


	private void call_routing_maps() throws myException {
		E2_ComponentMAP map = this.EXT().get_oComponentMAP();

		if (map!=null) {

			Long lStart =  map.get_LActualDBValue(this.start_adresseId_field.fn() /*VPOS_TPA_FUHRE.id_adresse_lager_start.fn()*/,null,null);
			Long lZiel =   map.get_LActualDBValue(this.ziel_adresseId_field.fn() /*VPOS_TPA_FUHRE.id_adresse_lager_ziel.fn()*/,null,null);

			if (O.isNoOneNull(lStart,lZiel)) {
				Rec20 recAdresseStart = new Rec20(_TAB.adresse)._fill_id(lStart.longValue());
				Rec20 recAdresseZiel = new Rec20(_TAB.adresse)._fill_id(lZiel.longValue());

				if(ENUM_MANDANT_DECISION.GEOPOINT_SHOW_ROUTE_INTERNAL.is_NO()){
					
					BigDecimal bdLatitudeStart = recAdresseStart.get_raw_resultValue_bigDecimal(ADRESSE.latitude);
					BigDecimal bdLongitudeStart = recAdresseStart.get_raw_resultValue_bigDecimal(ADRESSE.longitude);
					BigDecimal bdLatitudeZiel = recAdresseZiel.get_raw_resultValue_bigDecimal(ADRESSE.latitude);
					BigDecimal bdLongitudeZiel = recAdresseZiel.get_raw_resultValue_bigDecimal(ADRESSE.longitude);
					
					new PdServiceGeoCodeShowRouteBean().get_route_preview(bdLatitudeStart, bdLongitudeStart, bdLatitudeZiel, bdLongitudeZiel);
			} else {
					Vector<String> v_ids = new Vector<>();
					v_ids.add(recAdresseStart.get_key_value());
					v_ids.add(recAdresseZiel.get_key_value());
					
					new PdServiceShowRoutingBetweenAddressesBean().showRoutingBetweenAddresses(v_ids);
			}
				
				
//				if (O.isNoOneNull(bdLatitudeStart ,bdLongitudeStart ,bdLatitudeZiel ,bdLongitudeZiel )) {
//					String sUrl = ENUM_MANDANT_CONFIG.GEOPOINT_ROUTING_URL.get_value();
//					PdParserResult resWidth =  new PdParser().parse(sUrl, "width");
//
//					PdParserResult resHeight=null;
//					if (resWidth!=null) {
//						resHeight =  new PdParser().parse(resWidth.parseTextWithoutBlock, "height");
//
//						if (resHeight!=null) {
//							MyLong lWidth = new MyLong(resWidth.value);
//							MyLong lHeight = new MyLong(resHeight.value);
//
//							if (lWidth.isOK() && lHeight.isOK()) {
//								String urlCode = resHeight.parseTextWithoutBlock;
//
//								urlCode=urlCode.replace("<start_breite>", bdLatitudeStart.toPlainString());
//								urlCode=urlCode.replace("<start_laenge>", bdLongitudeStart.toPlainString());
//								urlCode=urlCode.replace("<ziel_breite>", bdLatitudeZiel.toPlainString());
//								urlCode=urlCode.replace("<ziel_laenge>", bdLongitudeZiel.toPlainString());
//
//								ApplicationInstance.getActive().enqueueCommand(
//										new BrowserOpenWindowCommand(urlCode,"Karte zu Adresse ","width="+lWidth.get_cUF_LongString()+",height="+lHeight.get_cUF_LongString()));
//							}
//						}
//					}
			} else {
				bibMSG.MV()._addAlarm("Ein Routing ist nicht möglich, da die Adressen nicht definiert sind !");
			}
		}else {
			bibMSG.MV()._addAlarm("Unbekannter Fehler, die Maskeneinträge konnten nicht identifiziert werden !");
		}
	}
}

