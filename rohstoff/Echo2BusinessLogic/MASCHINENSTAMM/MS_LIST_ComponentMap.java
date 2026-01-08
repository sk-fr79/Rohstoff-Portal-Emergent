package rohstoff.Echo2BusinessLogic.MASCHINENSTAMM;

import java.math.BigDecimal;

import panter.gmbh.BasicInterfaces.ServiceBean.PdServiceLkwKostenBean;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4Popup;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.DB_Component_USER_DROPDOWN_NEW;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.DB_SELECTOR_AlleEigenenAdressen;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentColumn;
import panter.gmbh.Echo2.components.DB.MyE2_DB_PlaceHolder_NT;
import panter.gmbh.basics4project.DB_ENUMS.MASCHINEN;
import panter.gmbh.basics4project.DB_ENUMS.MASCHINEN_KOSTEN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic._4_ALL.DB_SELEKTOR_MaschinenTyp;

public class MS_LIST_ComponentMap extends E2_ComponentMAP 
{

	public MS_LIST_ComponentMap() throws myException
	{
		super(new MS_LIST_SqlFieldMAP());
		
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		
		this.add_Component(MS_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(MS_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,		new E2_ButtonListMarker(),new MyE2_String("?"));

		MyE2_DB_MultiComponentColumn 	oMulti_Nummern = 					new MyE2_DB_MultiComponentColumn();
		MyE2_DB_MultiComponentColumn 	oMulti_Datum_Anschaffung_Gewaehr = 	new MyE2_DB_MultiComponentColumn();
		MyE2_DB_MultiComponentColumn 	oMulti_Datum_Leasing_Kauf = 		new MyE2_DB_MultiComponentColumn();
		MyE2_DB_MultiComponentColumn 	oMulti_Bediener = 					new MyE2_DB_MultiComponentColumn();
		
		
		oMulti_Nummern.add_Component(new MyE2_DB_Label_INROW(oFM.get_("FAHRGESTELLNUMMER")), 						new MyE2_String("Fahrgest.Nr"),null);
		oMulti_Nummern.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BRIEFNUMMER")), 								new MyE2_String("Brief-Nummer"),null);

		oMulti_Datum_Anschaffung_Gewaehr.add_Component(new MyE2_DB_Label_INROW(oFM.get_("DATUM_ANSCHAFFUNG")), 		new MyE2_String("Anschaff."),null);
		oMulti_Datum_Anschaffung_Gewaehr.add_Component(new MyE2_DB_Label_INROW(oFM.get_("GEWAEHRLEISTUNG_BIS")), 	new MyE2_String("Gewähr bis"),null);
		
		oMulti_Datum_Leasing_Kauf.add_Component(new MyE2_DB_Label_INROW(oFM.get_("LEASING_BIS")), 					new MyE2_String("Ende Leasing"),null);
		oMulti_Datum_Leasing_Kauf.add_Component(new MyE2_DB_Label_INROW(oFM.get_("GEKAUFT_AB")), 					new MyE2_String("Kauf am"),null);

		oMulti_Bediener.add_Component(new DB_Component_USER_DROPDOWN_NEW(oFM.get_("ID_USER_BEDIENER1"),true,150), new MyE2_String("Bediener 1"),null);
		oMulti_Bediener.add_Component(new DB_Component_USER_DROPDOWN_NEW(oFM.get_("ID_USER_BEDIENER2"),true,150), new MyE2_String("Bediener 2"),null);

		
		
		//hier kommen die Felder	
		this.add_Component(MS__CONST.ADDON_FIELDS.LIST_ANZAHL_KOSTEN.alias(), new anzeigeAnzahl(),		new MyE2_String("Kost."));
		
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("AKTIV")), 						new MyE2_String("Akt."));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("KFZKENNZEICHEN")), 			new MyE2_String("Kennzeichen"));
		
		this.add_Component("HASH_KEY_NUMMERN",oMulti_Nummern, 								new MyE2_String("Fahrgestell-/Brief-Nummer"));
		this.add_Component("HASH_KEY_DATUM1",oMulti_Datum_Anschaffung_Gewaehr, 				new MyE2_String("Datum Anschaffung/Gewährleistung"));
		this.add_Component("HASH_KEY_DATUM2",oMulti_Datum_Leasing_Kauf, 					new MyE2_String("Ende Leasing/Kauf am"));
		this.add_Component("HASH_KEY_BENUTZER",oMulti_Bediener, 							new MyE2_String("Bediener"));
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("HERSTELLER")), 				new MyE2_String("Hersteller"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BESCHREIBUNG"),true),	 		new MyE2_String("Beschreibung"));
		
		this.add_Component(new DB_SELECTOR_AlleEigenenAdressen(oFM.get_("ID_ADRESSE_STANDORT"),150), new MyE2_String("Standort"));
		
		this.add_Component(new DB_SELEKTOR_MaschinenTyp(oFM.get_("ID_MASCHINENTYP"), 150,false), 	new MyE2_String("Maschinentyp"));
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("KOSTEN_ANSCHAFFUNG")), 		new MyE2_String("Anschaffung"),true,true,new MyE2_String("Anschaffungskosten"),null,null);
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("KOSTENSTELLE1")), 				new MyE2_String("KST1"),true,true,new MyE2_String("Kostenstelle Teil 1"),null,null);
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("KOSTENSTELLE2")), 				new MyE2_String("KST2"),true,true,new MyE2_String("Kostenstelle Teil 2"),null,null);
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(MS__CONST.ADDON_FIELDS.LIST_KOSTENSTELLE_GESAMT.alias())), 	new MyE2_String("KST-Ges"),true,true,new MyE2_String("Kostenstelle 1 + 2"),null,null);
		
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("TYPENBEZ")), 					new MyE2_String("Typ"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("BEMERKUNG"),true), 			new MyE2_String("Bemerkungen"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_MASCHINEN")), 				new MyE2_String("ID"));
		


		this.set_oSubQueryAgent(new MS_LIST_FORMATING_Agent());
	}

	
	/**
	 * anzeigekomponente fuer die anzahl kosten einer maschine
	 * @author martin
	 *
	 */
	private class anzeigeAnzahl extends MyE2_DB_PlaceHolder_NT {

		/**
		 * @throws myException
		 */
		public anzeigeAnzahl() throws myException {
			super();
		}

		@Override
		public void prepare_ContentForNew(boolean bSetDefault) throws myException {
			this._clear()._add_r(new E2_Grid()._bord_black()._setSize(20)._a(new RB_lab()._t("0")._b()._i(), new RB_gld()._ins(1)._center_mid()),new RB_gld()._ins(0)._center_mid());
		}

		@Override
		public void set_cActual_Formated_DBContent_To_Mask_WhenVisisble(String value_from_db, String cMASK_STATUS,	SQLResultMAP oResultMAP) throws myException {
			String zahl = S.NN(oResultMAP.get_FormatedValue(MS__CONST.ADDON_FIELDS.LIST_ANZAHL_KOSTEN.alias()));
			this._clear()._add_r(new E2_Grid()._bord_black()._setSize(20)._a(new btWithPopup(zahl, oResultMAP.get_cUNFormatedROW_ID()), new RB_gld()._ins(1)._center_mid()),new RB_gld()._ins(0)._center_mid());
		}
		
		
		public anzeigeAnzahl get_Copy(Object objHelp) throws myExceptionCopy	{
			try {
				return new anzeigeAnzahl();
			} catch (myException e) {
				e.printStackTrace();
				throw new myExceptionCopy(e.ErrorMessage);
			}
		}

		@Override
		public void set_bEnabled_For_Edit(boolean enabled) throws myException {
		}
	}
	
	
	
	private class btWithPopup extends E2_Button {
		
		private String idMaschinen = null;
		
		public btWithPopup(String anzahl, String p_idMaschinen) {
			super();
			this._t(anzahl)._b()._i();
			this.idMaschinen = p_idMaschinen;
			
			E2_Break4PopupController breaker = new E2_Break4PopupController();
			
			breaker._registerBreak(new E2_Break4Popup() {
				
				@Override
				public E2_BasicModuleContainer generatePopUpContainer() throws myException {
					ownBasicModuleContainer cont = new ownBasicModuleContainer();

					Rec20 r = new Rec20(_TAB.maschinen)._fill_id(idMaschinen);
					RecList20 kosten = r.get_down_reclist20(MASCHINEN_KOSTEN.id_maschinen,null,null);

					this.setPopupWidth(550);
					this.setPopupHeight(300);
					this.setTitle(S.ms("Kostenübersicht "+r.getFs(MASCHINEN.kfzkennzeichen,"<kennzeichen>")));
					 
					
						
					E2_Grid  g = new E2_Grid()._setSize(200,150,150);
					cont.add(g, E2_INSETS.I(6,6,6,6));
						
					RB_gld ldTitel = new RB_gld()._ins(2, 1, 2, 1)._col_back_dd()._left_top();
					RB_gld ldText = new RB_gld()._ins(2, 1, 2, 1)._left_top();
						
					g._a(new RB_lab("Kostentyp"), ldTitel)._a(new RB_lab("Beschreibung"), ldTitel)._a(new RB_lab("Kosten (EUR)"), ldTitel._c()._right_top());
						
					for (Rec20 k: kosten) {
						g	._a(new RB_lab(ENUM_KOSTENTYP.ANSCHAFFUNG.getEnum(k.getUfs(MASCHINEN_KOSTEN.ms_enum_kostentyp)).userText()), ldText)
							._a(new RB_lab(k.getUfs(MASCHINEN_KOSTEN.kosten_beschreibung)), 	ldText)
							._a(new RB_lab(k.getFs(MASCHINEN_KOSTEN.kosten_betrag)), 		ldText._c()._right_top())
							;
					}

					//falls moeglich, noch die summe der km-kosten anzeigen
					BigDecimal bdSummeKmKosten = new PdServiceLkwKostenBean().getKostenProKm(r.getUfs(MASCHINEN.id_maschinen));
					//test mit kennzeichen
					//BigDecimal bdSummeKmKosten = new PdServiceLkwKostenBean().getKostenProKm(r.getUfs(MASCHINEN.kfzkennzeichen));
					
					if (bdSummeKmKosten!=null) {
						g.addSeparator();
						g	._a(new RB_lab()._tr("Gesamtkosten/Kilometer"), 						ldText._c()._ins(2,20,2,2)._span(2))
							._a(new RB_lab(MyNumberFormater.formatDez(bdSummeKmKosten, 2, true)), 	ldText._c()._ins(2,20,2,2)._right_top())
							;
					}
					
					this.getOwnCloseButton()._t("OK-Schliessen")._center()._bord_black()._backDark();
					g._a(this.getOwnCloseButton(), new RB_gld()._ins(2,20,2,2)	);
					return cont;
				}
				
				@Override
				protected boolean check4break(MyE2_MessageVector mv) throws myException {
					return true;
				}
				
				final class ownBasicModuleContainer extends E2_BasicModuleContainer {
					public ownBasicModuleContainer() throws myException {
						super();
					}
				}
			});
			
			
			this.setBreak4PopupController(breaker);
			
			//dummy, damit ueberhaupt ein listener da ist
			this._aaa(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				}
			});
			
		}

		@Override
		public void set_bEnabled_For_Edit(boolean enabled) throws myException {
			this.setEnabled(true);	//immer enabled, da in der liste
		}

		
	}
	
	
}
