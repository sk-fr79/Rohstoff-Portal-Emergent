package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.Routen;

import java.math.BigDecimal;
import java.util.Vector;

import panter.gmbh.BasicInterfaces.ServiceBean.PdServiceGeoCodeShowRouteBean;
import panter.gmbh.BasicInterfaces.ServiceBean.PdServiceShowRoutingBetweenAddressesBean;
import panter.gmbh.BasicInterfaces.ServiceBeanInterface.PdServiceShowRoutingBetweenAddresses;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.O;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class FU_LIST_bt_show_routing extends E2_Button{

	public FU_LIST_bt_show_routing(){
		super();
		this._image("routing_show.png");
		this._ttt(new MyE2_String("Routen anzeigen"));
		this._aaa(()->{show_route();});
	}

	private void show_route() throws myException {
		E2_ComponentMAP map = this.EXT().get_oComponentMAP();

		if (map!=null) {

			Rec20 fuhre = new Rec20(_TAB.vpos_tpa_fuhre)._fill_id(map.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());

			Rec20 recStart =  fuhre.get_up_Rec20(VPOS_TPA_FUHRE.id_adresse_lager_start, ADRESSE.id_adresse, true);
			Rec20 recZiel =   fuhre.get_up_Rec20(VPOS_TPA_FUHRE.id_adresse_lager_ziel, ADRESSE.id_adresse, true);

			if (O.isNoOneNull(fuhre,recStart,recZiel)) {
				if(ENUM_MANDANT_DECISION.GEOPOINT_SHOW_ROUTE_INTERNAL.is_NO()){
						
						BigDecimal bdLatitudeStart = 	recStart.get_raw_resultValue_bigDecimal(ADRESSE.latitude);
						BigDecimal bdLongitudeStart = 	recStart.get_raw_resultValue_bigDecimal(ADRESSE.longitude);
						BigDecimal bdLatitudeZiel = 	recZiel.get_raw_resultValue_bigDecimal(ADRESSE.latitude);
						BigDecimal bdLongitudeZiel = 	recZiel.get_raw_resultValue_bigDecimal(ADRESSE.longitude);
	
						new PdServiceGeoCodeShowRouteBean().get_route_preview(bdLatitudeStart, bdLongitudeStart, bdLatitudeZiel, bdLongitudeZiel);
				} else {
						Vector<String> v_ids = new Vector<>();
						v_ids.add(recStart.get_key_value());
						v_ids.add(recZiel.get_key_value());
						
						new PdServiceShowRoutingBetweenAddressesBean().showRoutingBetweenAddresses(v_ids);
					
				}
			}
			

		}
	}

	
	//	E2_Button save_bt= new ownSaveEntfZeit();
//	save_bt._aaa(()-> this.CLOSE_AND_DESTROY_POPUPWINDOW(true));
	
	@Override
	//listenbutton immer enabled
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
		super.set_bEnabled_For_Edit(true);
	}

	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		FU_LIST_bt_show_routing oButton = new FU_LIST_bt_show_routing();
		oButton.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oButton));
		return oButton;
	}
}
