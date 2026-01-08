package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.GPS;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.BasicInterfaces.ServiceBean.PdServiceGeoCodeExistingAdressBean;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.ServerPush.E2_ServerPushMessageContainer_STD;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.LIEFERADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.MITARBEITER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO_CodingResultGrid;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO_CodingResultScreen;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO_ErrorMap;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO_Location;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO_CodingResultGrid.ComponentGenerator;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.dataTools.TERM.UPDATE.UPDATE;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_CONST;
import rohstoff.Echo2BusinessLogic._4_ALL.POPUP_QueryInList_SELECTED_SEITE_ALL;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_adresse;

public class FS_ListBtGeoCodingAdresses extends MyE2_Button {

	private Vector<String>  		addressIds = new Vector<String>();
	
	private E2_NavigationList   	oNaviList = null;
	
	
	private E2_Grid  				anzeigeGrid = new E2_Grid();
	
	public FS_ListBtGeoCodingAdresses(E2_NavigationList  naviList) {
		super(new MyE2_String("Geocodieren der Adressen-Auswahl"), MyE2_Button.StyleImageButtonCenteredWithBlackBorder());
		this.oNaviList = naviList;
		this.add_oActionAgent(new OpenSelectionPopup());
		this.add_GlobalAUTHValidator_AUTO(FS_CONST.LIST_VALID_ERMITTLE_KOSTEN);
	}

	/** 
	 * Shows a popup in which the current selection can be made more precise or extended to all
	 * visible / all records at all of the list 
	 */
	private class OpenSelectionPopup extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			new SelectAdresses().ShowPopup(oNaviList, new MyE2_String("Geo-Codierung der Adressen (GPS-Koordinaten ermitteln)"));
		}			
	}
	 
	private class SelectAdresses extends POPUP_QueryInList_SELECTED_SEITE_ALL {
		@Override
		public void do_when_ok_is_clicked(Vector<String> ids) 	throws myException {
			addressIds.removeAllElements();
			addressIds.addAll(ids);
			
			anzeigeGrid._clear();
			
			GEO_ErrorMap 			errorMap = 			new GEO_ErrorMap("Hauptadressen");
			GEO_ErrorMap 			errorMapLager = 	new GEO_ErrorMap("Lieferadressen");
			GEO_ErrorMap 			errorMapMitarb = 	new GEO_ErrorMap("Mitarbeiteradressen");
			MyE2_MessageVector  	mv = 		new MyE2_MessageVector();
			
			if (addressIds.size()<=30) {
				FS_ListBtGeoCodingAdresses.this.update(addressIds, errorMap, errorMapLager, errorMapMitarb,mv, null);
				new GEO_CodingResultScreen(new genButtons(), errorMap, errorMapLager, errorMapMitarb);
				bibMSG.add_MESSAGE(mv);
				oNaviList._REBUILD_ACTUAL_SITE("");
			} else {
				new E2_ServerPushMessageContainer_STD(new Extent(400), new Extent(250), new MyE2_String("Geocodierung der Adressen"),false,true,2000,anzeigeGrid,E2_INSETS.I(10,10,10,10)) {
	
					@Override
					public void Run_Loop() throws myException {
						FS_ListBtGeoCodingAdresses.this.update(addressIds, errorMap, errorMapLager, errorMapMitarb, mv, this);
						this.get_oGridBaseForMessages()._clear();
						this.get_oGridBaseForMessages().add(new GEO_CodingResultGrid(new genButtons(), errorMap, errorMapLager, errorMapMitarb));
						this.get_oWindowPane().setWidth(new Extent(510));
						this.get_oWindowPane().setHeight(new Extent(560));
						bibMSG.add_MESSAGE(mv);
						oNaviList._REBUILD_ACTUAL_SITE("");
					}
	
					@Override
					public void setWindowPaneLookAndFeel(MyE2_WindowPane oWindowPane) throws myException {
					}
				};
			}
		}
	}
	
	
	private void update(	Vector<String> 	idsBaseAdress, 
							GEO_ErrorMap 	errorMap, 
							GEO_ErrorMap 	errorMapLager, 
							GEO_ErrorMap 	errorMapMitarb, 
							MyE2_MessageVector mv,
							E2_ServerPushMessageContainer_STD container) throws myException {
		
		long zaehler = 0;
		long zaehlerLager = 0;
		long zaehlerMitarbeiter = 0;
		
		boolean first = true;
		
		VEK<String>  vSql = new VEK<>();
		
		for (String id: idsBaseAdress) {
			
			zaehler++;
			
			MyLong l = new MyLong(id);
			
			if (l.isOK()) {
				SEL sLager = 		new SEL(LIEFERADRESSE.id_adresse_liefer).FROM(_TAB.lieferadresse).WHERE(new vgl(LIEFERADRESSE.id_adresse_basis,id));
				SEL sMitarbeiter = 	new SEL(MITARBEITER.id_adresse_mitarbeiter).FROM(_TAB.mitarbeiter).WHERE(new vgl(MITARBEITER.id_adresse_basis,id));
				
				String[][] lager = bibDB.EinzelAbfrageInArray(sLager.s(), "");
				String[][] mitarb = bibDB.EinzelAbfrageInArray(sMitarbeiter.s(), "");

				GEO_Location loc = new PdServiceGeoCodeExistingAdressBean().getLocation(l.get_oLong(), errorMap);
				if (loc!=null) {
					//dann  wurde eine eindeutige kodierung gefunden
					UPDATE up = new UPDATE(_TAB.adresse,l.get_oLong())	._addRawPair(ADRESSE.latitude, loc.get_latitude().get_UnFormatedRoundedNumber(8))
																		._addRawPair(ADRESSE.longitude, loc.get_longitude().get_UnFormatedRoundedNumber(8));
					vSql.add(up.s());
				}
				
//				Rec20_geocodierung r = new GlobalServiceGeoCodingAdress().makeGeoCoding(l.get_oLong(), errorMap, true,false);
//				vSql._addValidated(sql1 -> {return S.isFull(sql1);}, r.get_sql_4_save(true,mv));
				
				if (lager!=null && lager.length>0) {
					for (int i=0; i< lager.length; i++) {
						MyLong la = new MyLong(lager[i][0]);
						if (la.isOK()) {
							GEO_Location loc2 = new PdServiceGeoCodeExistingAdressBean().getLocation(la.get_oLong(), errorMapLager);
							if (loc2!=null) {
								//dann  wurde eine eindeutige kodierung gefunden
								UPDATE up = new UPDATE(_TAB.adresse,la.get_oLong())	._addRawPair(ADRESSE.latitude, loc2.get_latitude().get_UnFormatedRoundedNumber(8))
																					._addRawPair(ADRESSE.longitude, loc2.get_longitude().get_UnFormatedRoundedNumber(8));
								vSql.add(up.s());
							}
							zaehlerLager++;
							first = this.refreshPopUp(zaehler, zaehlerLager, zaehlerMitarbeiter, idsBaseAdress.size(),first);
						}
					}
				}
				
				if (mitarb!=null && mitarb.length>0) {
					for (int i=0; i< mitarb.length; i++) {
						MyLong lmit = new MyLong(mitarb[i][0]);
						if (lmit.isOK()) {
							GEO_Location loc2 = new PdServiceGeoCodeExistingAdressBean().getLocation(lmit.get_oLong(), errorMapMitarb);
							if (loc2!=null) {
								//dann  wurde eine eindeutige kodierung gefunden
								UPDATE up = new UPDATE(_TAB.adresse,lmit.get_oLong())	._addRawPair(ADRESSE.latitude, loc2.get_latitude().get_UnFormatedRoundedNumber(8))
																					._addRawPair(ADRESSE.longitude, loc2.get_longitude().get_UnFormatedRoundedNumber(8));
								vSql.add(up.s());
							}
						
							zaehlerMitarbeiter++;
							first = this.refreshPopUp(zaehler, zaehlerLager, zaehlerMitarbeiter, idsBaseAdress.size(),first);
						}
					}
				}
				
				if (vSql.size()>10) {
					//alle 100 statements ein schreibvorgang
					mv.add_MESSAGE(bibDB.ExecMultiSQLVector(vSql, true));
					//DEBUG.System_print(vSql,true);
					vSql.clear();
				}
			}
			
			first = this.refreshPopUp(zaehler, zaehlerLager, zaehlerMitarbeiter, idsBaseAdress.size(),first);
			if (container!=null && E2_ServerPushMessageContainer_STD.get_LoopStopped()) {
				vSql.clear();
				break;
			}
		}
		
		if (vSql.size()>0) {
			//abschluss-schreibvorgang
			mv.add_MESSAGE(bibDB.ExecMultiSQLVector(vSql, true));
			//DEBUG.System_print(vSql,true);
			vSql.clear();
		}

	}
	
	
	private boolean refreshPopUp(long zaehler,long zaehlerLager,long zaehlerMitarbeiter, int idAdressBaseSize, boolean first) {
		if (first || (zaehler+zaehlerMitarbeiter+zaehlerLager)%10==0) {
			this.anzeigeGrid._clear();
			this.anzeigeGrid._setSize(50,20,50,50,50)._bo_dd();
			
			RB_gld gl = new RB_gld()._center_mid()._ins(5);
			RB_gld glt = new RB_gld()._center_mid()._ins(5)._col(new E2_ColorDark());
			
			this.anzeigeGrid._a("Firmen",glt)._a(new RB_lab()._tr("von"),glt)._a("Firmen/Ges.",glt)._a("Mitarb.",glt)._a("Lieferad.",glt);
			this.anzeigeGrid._a(new RB_lab()._t(""+zaehler)._b(),gl)._a(new RB_lab()._tr("von")._b(),gl)._a(new RB_lab()._t(""+idAdressBaseSize)._b(),gl)._a(""+zaehlerMitarbeiter,gl)._a(""+zaehlerLager,gl);
		}
		return false;

	}
	
	
	
	private class genButtons implements ComponentGenerator {

		@Override
		public Component gen(VEK<String> vek) throws myException {
			if (vek.size()==0) {
				return new RB_lab()._t("0");
			} else {
				return new E2_Button()._t(""+vek.size())._standard_text_button()._aaa(new ownAction(vek))._ttt(S.ms("Auswahl der betreffenden Adressen in der Liste anzeigen"));
			}
		}
		
		private class ownAction extends XX_ActionAgent {
			private VEK<String>  v = new VEK<>();

			public ownAction(VEK<String> vek) {
				super();
				this.v._a(vek);
			}

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				VEK<String>  vIDsBase = new VEK<String>();
				for (String id: this.v) {
					Rec20_adresse r = new Rec20_adresse()._fill_id(id);
					vIDsBase._a(r._getMainAdresse().getUfs(ADRESSE.id_adresse));
				}
				MyE2_MessageVector mv = new MyE2_MessageVector();
				FS_ListBtGeoCodingAdresses.this.oNaviList.saveStatus(mv);
				if (mv.get_bHasAlarms()) {
					bibMSG.add_MESSAGE(mv);
				} else {
					FS_ListBtGeoCodingAdresses.this.oNaviList.get_vectorSegmentation().clear();
					FS_ListBtGeoCodingAdresses.this.oNaviList.get_vectorSegmentation().addAll(vIDsBase);
					
					FS_ListBtGeoCodingAdresses.this.oNaviList.gotoSiteWithID_orFirstSite(null);
				}
			}
		}
		
	}
	
	
}
