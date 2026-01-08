/**
 * rohstoff.Echo2BusinessLogic.ZOLLTARIFNUMMER
 * @author martin
 * @date 19.10.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.ZOLLTARIFNUMMER;

import java.math.BigDecimal;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Grid;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4Popup;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLight;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_ENUMS.LAND_RC_SORTEN;
import panter.gmbh.basics4project.DB_ENUMS.ZOLLTARIFNUMMER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.dataTools.RECORD2.RecList22;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec22Land;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec22LandRcSorte;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec22ZollTarifnummer;

/**
 * @author martin
 * @date 19.10.2020
 *
 */
public class Break4PopupCheckRcStatus extends E2_Break4Popup {

	private RB_TransportHashMap  	tpHashMap = null;

	private VEK<Rec22Land>         	laenderMitRcSorten = new VEK<Rec22Land>();

	private RecList22 			   	sortenDieserZolltarifnummer = null;
	private VEK<Rec22LandRcSorte>  	betreffendeVorhandenlandRcSorten = new VEK<Rec22LandRcSorte>();
	
	private int 					breite = 1000;
	private int 					hoehe = 700;

	private Boolean 				rcStatusOld = null;
	private Boolean    				rcStatusNew = null;  
	
	
	private VEK<Sorte>             vAlleZuordnungen = new VEK<Sorte>();
	
	/**
	 * @author martin
	 * @date 19.10.2020
	 * @param p_tpHashMap
	 */
	public Break4PopupCheckRcStatus(RB_TransportHashMap p_tpHashMap) {
		super();
		this.tpHashMap = p_tpHashMap;
		this._setWidth(breite)._setHeight(hoehe);
		this.setTitle(S.ms("Bitte gleichen Sie die gewünschten Sorten in den Länder-RC-Einstellungen ab"));
	
		this._registerExecuterBeforeSave((o)-> {
			MyE2_MessageVector mv = bibMSG.getNewMV();
			
			//die zurordnungen definieren und in die transporthashmap schreiben, damit sie im commit geschrieben werden können
			MapForChangeRcTables mapForChangeRcTables = (MapForChangeRcTables)this.tpHashMap.getSB("ZT_KEY_AUTOCHANGE_RC_TABLE");
			mapForChangeRcTables._clear();
			
			
			for (Sorte sorte: vAlleZuordnungen) {
				if (sorte.landRcSorte==null && sorte.cb.isSelected()  || sorte.landRcSorte!=null && !sorte.cb.isSelected()) {
				   //dann hat sich was geaendert
					if (sorte.landRcSorte!=null) {
						mapForChangeRcTables.addToDelete(sorte.landRcSorte);
					} else {
						Rec22LandRcSorte neuland = (Rec22LandRcSorte) new Rec22LandRcSorte()
											._setNewVal(LAND_RC_SORTEN.id_artikel, 	sorte.artikel.getIdLong(), mv)
											._setNewVal(LAND_RC_SORTEN.id_land, 	sorte.land.getIdLong(), mv);
						mapForChangeRcTables.addToAdd(neuland);
					}
				}
			}
			return mv;
		});
		
	}

	@Override
	public E2_BasicModuleContainer generatePopUpContainer() throws myException {
		return new OwnContainer();
	}
	
	

	@Override
	protected boolean check4break(MyE2_MessageVector mv) throws myException {
		
		boolean erstaufrufDesPopup =(this.getBreak4PopupController().getHmCounter().get(this)==0);
		
		laenderMitRcSorten._clear();
		sortenDieserZolltarifnummer = null;
		betreffendeVorhandenlandRcSorten._clear();
		rcStatusOld = null;
		rcStatusNew = null;
		vAlleZuordnungen._clear();
		
		if (erstaufrufDesPopup) {
			//die aenderungsmap leeren
			MapForChangeRcTables mapForChangeRcTables = (MapForChangeRcTables)this.tpHashMap.getSB("ZT_KEY_AUTOCHANGE_RC_TABLE");
			mapForChangeRcTables._clear();
		}
		
		//DEBUG.System_println("Anzahl zuordnungen"+vAlleZuordnungen.size());

		if (this.tpHashMap.getLastMaskLoadStatus()==MASK_STATUS.EDIT) {
			
			
			RB_KM maskKeyZt = _TAB.zolltarifnummer.rb_km();
			Rec22 recOnMask = (Rec22)this.tpHashMap.getMaskComponentMapCollector().rb_Actual_DataobjectCollector().get(maskKeyZt);
			
			RB_MaskController mc = new RB_MaskController(this.tpHashMap.getMaskComponentMapCollector().get(maskKeyZt));
			
			rcStatusOld = S.NN((String)recOnMask.getRawVal(ZOLLTARIFNUMMER.reverse_charge),"N").equals("Y");
			rcStatusNew   = mc.getBooleanValueFromScreen(_TAB.zolltarifnummer.rb_km(),ZOLLTARIFNUMMER.reverse_charge);
			
//			if (rcStatusOld.booleanValue()!=rcStatusNew.booleanValue()) {

				SEL selLaender = new SEL(LAND.id_land).FROM(_TAB.land).ADD_Distinct().INNERJOIN(_TAB.land_rc_sorten, LAND.id_land, LAND_RC_SORTEN.id_land);
				VEK<Object[]> laender = bibDB.getResultLines(new SqlStringExtended(selLaender.s()), true);
				if (laender.size()>0) {
					for (Object[] o: laender) {
						Long idLand = ((BigDecimal)o[0]).longValue();
						laenderMitRcSorten._a(new Rec22Land()._fill_id(idLand));
					}
				}

				boolean showPopup = false;

				if (laenderMitRcSorten.size()>0) {
					Rec22ZollTarifnummer 		recZt= new Rec22ZollTarifnummer(recOnMask);
					sortenDieserZolltarifnummer = 	recZt.getSortenWithThisZolltarifnummer(ARTIKEL.anr1);
					
					if (sortenDieserZolltarifnummer.size()>0) {
					
						String sqlLandRcSorten = "select lrc.id_land_rc_sorten from jt_land_rc_sorten lrc where lrc.id_artikel in "
										+ " (select a.id_artikel from jt_artikel a where a.id_zolltarifnummer="+recOnMask.getActualID().toString()+")";
						
						VEK<Object[]> landRcSortenMitDieserZolltarifnummer = bibDB.getResultLines(new SqlStringExtended(sqlLandRcSorten), true);
						for (Object[] o: landRcSortenMitDieserZolltarifnummer) {
							betreffendeVorhandenlandRcSorten._a(new Rec22LandRcSorte()._fill_id( ((BigDecimal)o[0]).longValue()));
						}
					
						if (!rcStatusNew  /*RC wurde ausgeschaltet*/) {
							if (betreffendeVorhandenlandRcSorten.size()!=0 ) { /*es existieren referenzen, evtl korrigeren*/
								showPopup = true;
							}
						} else {     /*RC wurde eingeschaltet */
							showPopup=true;
						}
					}
				}

				return showPopup&&erstaufrufDesPopup;
				
//			}
		}
		return false;
	}

	
	
	
	private class OwnContainer extends E2_BasicModuleContainer {
		
		/**
		 * @author martin
		 * @date 19.10.2020
		 *
		 */
		public OwnContainer() {
			super();
			
			E2_Grid gridContainer = new E2_Grid()._setSize(breite);
			E2_Grid gridForTabs = new E2_Grid()._setSize(breite-10);

			gridContainer._a(new E2_Grid()	._a(new RB_lab()._t(S.ms("Reverse-Charge-Status der Zolltarifnummer nach dem Speichern: ")), new RB_gld()._ins(0,0,20,0))
											._a(new RB_lab()._t(S.ms(rcStatusNew?"AN":"AUS"))._fsa(2)._b())
											, new RB_gld()._ins(2,5,2,2))
						._a(new RB_lab()._lw()._t(S.ms("In der Liste angehakte Sorten dieser Zolltarifnummer sind im Land angelegt. Sie können diese Auswahl hier anpassen !"))
											, new RB_gld()._ins(2,2,2,2))
						._a(new RB_lab()._lw()._t(S.ms("Die Veränderungen (hell hinterlegt) werden beim Speichern der Maske abgespeichert !"))
											, new RB_gld()._ins(2,2,2,5))
						
						;
			gridContainer._a(gridForTabs);
			
			this.add(gridContainer, E2_INSETS.I(5));
			
			try {
				
				this._setWidth(breite)._setHeight(hoehe);
				
				MyE2_TabbedPane tab = new MyE2_TabbedPane(420);
				tab.setWidth(new Extent(100,Extent.PERCENT));
				tab.setHeight(new Extent(this.get_oExtHeight().getValue()-220));
				gridForTabs._a(tab);
				
				for (Rec22Land land: laenderMitRcSorten) {
					tab.add_Tabb(S.ms((String)land.getRawVal(LAND.laendername)),new OrdnungsGrid(land, rcStatusNew));
				}
				
			} catch (myException e) {
				e.printStackTrace();
				getOwnSaveButton().setEnabled(false);
			}
			getOwnSaveButton()._setShapeSaveAndClose()._setShapeStandardTextButton();
			getOwnCloseButton()._setShapeCancelAndClose()._setShapeStandardTextButton();
			
			gridContainer._a(new E2_Grid()._s(2)._a(getOwnSaveButton(), new RB_gld()._ins(2, 10, 15, 2))._a(getOwnCloseButton(), new RB_gld()._ins(2, 10, 2, 2)));
		}
	}
	
	
	private class OrdnungsGrid extends E2_Grid {
		
		/**
		 * @author martin
		 * @date 21.10.2020
		 *
		 * @param land
		 * @param aktuellRcStatus
		 * @throws myException 
		 */
		public OrdnungsGrid(Rec22Land land, Boolean aktuellRcStatus) throws myException {
			super();
			this._bo_dd();
			this.setOrientation(Grid.ORIENTATION_VERTICAL);
			this.setSize(20);
			for (Rec22 artikel: sortenDieserZolltarifnummer) {
				this._a(new Sorte(land, artikel, aktuellRcStatus), new RB_gld()._ins(2));
			}
		}
	}
	
	
	
	/**
	 * klass zeigt den aktuellen status einer sorte in der RC-Zuordnung
	 * @author martin
	 * @date 21.10.2020
	 *
	 */
	private class Sorte extends E2_Grid {
		private Rec22 				artikel = null;        //artikel kommt immer
		private Rec22Land         	land = null; 			//land kommt immer
		private Rec22LandRcSorte  	landRcSorte = null;    //land-rc-sorte nur wenn aktuell eine zuordnung existiert
		private Boolean  			aktuellRcStatus = null;
		private RB_cb   			cb = new RB_cb();
		private RB_lab  			warnschild = new RB_lab();
		private Color               actualColorBack = new E2_ColorBase();
		
		/**
		 * @author martin
		 * @date 21.10.2020
		 *
		 * @param land
		 * @param artikel
		 * @param landRcSorte (nullable)
		 * @param aktuellRcStatus
		 * @throws myException 
		 */
		public Sorte(Rec22Land land, Rec22 artikel,  Boolean aktuellRcStatus) throws myException {
			super();
			this.land = land;
			this.artikel = artikel;
			this.aktuellRcStatus = aktuellRcStatus;
			
			this._setSize(20,60,250,20)	._a(cb, 											new RB_gld()._col_back(actualColorBack))
										._a(new RB_lab(artikel.getUfs(ARTIKEL.anr1,"")), 	new RB_gld()._col_back(actualColorBack))
										._a(new RB_lab(artikel.getUfs(ARTIKEL.artbez1,"")), new RB_gld()._ins(0, 0, 5, 0)._col_back(actualColorBack))
										._a(warnschild, 									new RB_gld()._col_back(actualColorBack));
			
			//jetzt nachsehen, ob die kombination land und sorte angelegt ist
			for (Rec22LandRcSorte lrc: betreffendeVorhandenlandRcSorten) {
				if (lrc.getLongDbValue(LAND_RC_SORTEN.id_land).longValue()==land.getId() &&
					lrc.getLongDbValue(LAND_RC_SORTEN.id_artikel).longValue()==artikel.getId()) {
					landRcSorte = lrc;
					break;
				}
			}
			
			this.cb._setSelected(this.landRcSorte!=null);
			
			if (this.landRcSorte!=null) {
				this.cb._ttt_ut("ID-Land-RC-Sorte"+this.landRcSorte.getId());
			}
			

			setActualStatus();
			
			this.cb._aaa(()->{
				setActualStatus();
			});

			vAlleZuordnungen._a(this);
		}
		
		private void setActualStatus() throws myException {
			warnschild.removeAll();
			if (cb.isSelected()) {
				if (aktuellRcStatus) {
					warnschild.setIcon(E2_ResourceIcon.get_RI("empty10.png"));
					warnschild._ttt("");
				} else {
					warnschild.setIcon(E2_ResourceIcon.get_RI("warnschild_16.png"));
					warnschild._ttt(S.ms("Die Einstellung widersspricht der aktuellen Einstellung der Zolltarifnummer !"));
				}
			} else {
				if (aktuellRcStatus) {
					warnschild.setIcon(E2_ResourceIcon.get_RI("warnschild_16.png"));
					warnschild._ttt(S.ms("Die Einstellung widersspricht der aktuellen Einstellung der Zolltarifnummer !"));
				} else {
					warnschild.setIcon(E2_ResourceIcon.get_RI("empty10.png"));
					warnschild._ttt("");
				}
			}
			
			//hintergrundfarbe, wenn status veraendert
			if (landRcSorte==null && cb.isSelected()  || landRcSorte!=null && !cb.isSelected()) {
				actualColorBack=new E2_ColorLLight();
			} else {
				actualColorBack=new E2_ColorBase();
			}
			this._clear()._setSize(20,60,250,20)	._a(cb, 											new RB_gld()._col_back(actualColorBack))
													._a(new RB_lab(artikel.getUfs(ARTIKEL.anr1,"")), 	new RB_gld()._col_back(actualColorBack))
													._a(new RB_lab(artikel.getUfs(ARTIKEL.artbez1,"")), new RB_gld()._ins(0, 0, 5, 0)._col_back(actualColorBack))
													._a(warnschild, 									new RB_gld()._col_back(actualColorBack));

		}
	}
	
	
}
