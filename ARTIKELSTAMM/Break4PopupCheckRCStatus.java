/**
 * rohstoff.Echo2BusinessLogic.ARTIKELSTAMM
 * @author martin
 * @date 27.10.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.ARTIKELSTAMM;

import java.math.BigDecimal;

import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4Popup;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLight;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_ENUMS.LAND_RC_SORTEN;
import panter.gmbh.basics4project.DB_ENUMS.ZOLLTARIFNUMMER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.dataTools.RECORD2.RecList22;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec22Land;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec22LandRcSorte;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec22ZollTarifnummer;

/**
 * @author martin
 * @date 27.10.2020
 *
 */
public class Break4PopupCheckRCStatus extends E2_Break4Popup {

	private AS_BasicModuleContainerMASK	maskContainer = 		null;
	private VEK<Rec22Land>         		laenderMitRcSorten = 	new VEK<Rec22Land>();
    private boolean   					edit = 					false;
    private boolean 					oldZtIsRc = 			false;
    private boolean 					newZtIsRc = 			false;
    private Rec22ZollTarifnummer 		rZollTarif = 			null;
    private boolean 					sorteIsInLaenderRc = 	false;
    private AS_MASK_ComponentMAP		maskComponentMap = 		null;
	private Rec22 						artikel = 				null;
	private VEK<Sorte>            		vAlleZuordnungen = new VEK<Sorte>();
	private String 						anr1VonMaske = null;
	
	
	public Break4PopupCheckRCStatus(AS_BasicModuleContainerMASK	maskContainer) {
		super();
		this.maskContainer = maskContainer;
		
		this.setTitle(S.ms("Reverse Charge-Stautus der Sorte"));
		
		this._registerExecuterBeforeSave((o)-> {
			MyE2_MessageVector mv = bibMSG.getNewMV();
			
			//die zurordnungen definieren und in die transporthashmap schreiben, damit sie im commit geschrieben werden können
			maskComponentMap.getSqlStatementsAfterSave()._clear();
			
			
			for (Sorte sorte: vAlleZuordnungen) {
				String sql = sorte.getSqlStatement();
				if (S.isFull(sql)) {
					maskComponentMap.getSqlStatementsAfterSave()._a(sql);
				}
			}
			return mv;
		});

		
	}

	@Override
	public E2_BasicModuleContainer generatePopUpContainer() throws myException {
		return new OwnPopupContainer();
	}

	@Override
	protected boolean check4break(MyE2_MessageVector mv) throws myException {
		maskComponentMap = ((AS_MASK_ComponentMAP)maskContainer.get_ComponentMAP_FROM_TABLE(ARTIKEL.fullTabName()));
		
		//aufraeumen
		artikel = null;
		rZollTarif = null;
		laenderMitRcSorten._clear();
		vAlleZuordnungen._clear();

		
		boolean erstaufrufDesPopup =(this.getBreak4PopupController().getHmCounter().get(this)==0);

		if (erstaufrufDesPopup) {
			maskComponentMap.getSqlStatementsAfterSave()._clear();
		}
		
		
		//feststellen, welche laender rc-sorten besitzen
		SEL selLaender = new SEL(LAND.id_land).FROM(_TAB.land).ADD_Distinct().INNERJOIN(_TAB.land_rc_sorten, LAND.id_land, LAND_RC_SORTEN.id_land);
		VEK<Object[]> laender = bibDB.getResultLines(new SqlStringExtended(selLaender.s()), true);
		if (laender.size()>0) {
			for (Object[] o: laender) {
				Long idLand = ((BigDecimal)o[0]).longValue();
				laenderMitRcSorten._a(new Rec22Land()._fill_id(idLand));
			}
		}
		
		laenderMitRcSorten = laenderMitRcSorten._sort((r1,r2)-> {
			return r1.getUfs("",LAND.laendername).compareTo(r2.getUfs("",LAND.laendername));
		});
		
		AS_MASK_ComponentMAP maskComponentMAP = (AS_MASK_ComponentMAP)maskContainer.get_ComponentMAP_FROM_TABLE(ARTIKEL.fullTabName());
		
		boolean mustPop = false;
		
		edit = maskComponentMAP.get_STATUS_LAST_FILL().equals(E2_ComponentMAP.STATUS_EDIT);
		oldZtIsRc = false;
		newZtIsRc = false;
		
		anr1VonMaske   = maskComponentMAP.get_cActualDBValueFormated(ARTIKEL.anr1.fn(),"");
		Long   ztNummer =  new MyLong(maskComponentMAP.get_cActualDBValueFormated(ARTIKEL.id_zolltarifnummer.fn(),"")).get_oLong();
		
		if (ztNummer!=null) {
			rZollTarif = new Rec22ZollTarifnummer()._fill_id(ztNummer);
			newZtIsRc = rZollTarif.get_fs_dbVal(ZOLLTARIFNUMMER.reverse_charge,"N").equals("Y");
		}

		//jetzt feststellen, ob die sorte einen RC-laendereintrag hat
		sorteIsInLaenderRc = false;
		
		if (edit) {
			artikel = new Rec22(_TAB.artikel)._fill_id(maskComponentMAP.get_oInternalSQLResultMAP().getLongId());
			RecList22 laenderRcSorte = artikel.get_down_reclist22(LAND_RC_SORTEN.id_artikel);
			sorteIsInLaenderRc = (laenderRcSorte.size()>0);
			Rec22 recZtBeforeLoad = artikel.getUpRec2(ZOLLTARIFNUMMER.id_zolltarifnummer);
			if (recZtBeforeLoad!=null) {
				oldZtIsRc = recZtBeforeLoad.get_fs_dbVal(ZOLLTARIFNUMMER.reverse_charge,"N").equals("Y");
			}
		}
		
		if (newZtIsRc && laenderMitRcSorten.size()>0) {
			mustPop = true;
		} else {
			if (edit) {
				if (oldZtIsRc && !newZtIsRc && sorteIsInLaenderRc) {
					mustPop=true;
				}
			}
		}
		
		return mustPop&&erstaufrufDesPopup;
	}

	
	
	private class OwnPopupContainer extends E2_BasicModuleContainer {

		public OwnPopupContainer() {
			super();
			
			E2_Grid grid = new E2_Grid()._setSize(500,100)
							._a(new RB_lab()._t(S.ms("Bitte RC-Status prüfen !"))._fsa(4), new RB_gld()._ins(2, 2, 2, 5)._span(2))
							._a(new RB_lab()._t(S.ms("Die aktuelle Zolltarifnummer hat den RC-Status"))._fsa(4), new RB_gld()._ins(2, 2, 2, 5))
							._a(new RB_lab()._t(S.ms(newZtIsRc?"RC: JA":"RC: NEIN"))._fsa(4)._b(), new RB_gld()._ins(2, 2, 2, 5))
							; 
			
			grid._addSeparator(2, 10, 2, 10);
			
			try {
				grid._a(new RB_lab()._tr("Länder mit Zolltarifstatus der Sorte:  ")._t_add(anr1VonMaske), new RB_gld()._col_back_dd()._ins(2,2,2,6)._span(2));
			} catch (myException e1) {
				e1.printStackTrace();
			}
			
			if (edit) {
				for (Rec22 land: laenderMitRcSorten) {
					try {
						RecList22 landRcSorten = land.get_down_reclist22(LAND_RC_SORTEN.id_land, new vgl(LAND_RC_SORTEN.id_artikel, ""+maskComponentMap.get_oInternalSQLResultMAP().getLongId()).s(), null);
						if (landRcSorten.size()==0) {
							grid._a(new Sorte(artikel,new Rec22Land(land), null, newZtIsRc), new RB_gld()._ins(2,2,2,6)._span(2));
						} else {
							grid._a(new Sorte(artikel,new Rec22Land(land),new Rec22LandRcSorte(landRcSorten.get(0)), newZtIsRc), new RB_gld()._ins(2,2,2,6)._span(2));
						}
					} catch (myException e) {
						grid._a(new RB_lab()._t("Error!!!"+e.getMessage()), new RB_gld()._col_back_alarm());
						e.printStackTrace();
					}
				}
			} else {
				for (Rec22 land: laenderMitRcSorten) {
					try {
						grid._a(new Sorte(null,new Rec22Land(land), null, newZtIsRc), new RB_gld()._ins(2,2,2,6)._span(2));
					} catch (myException e) {
						grid._a(new RB_lab()._t("Error!!!"+e.getMessage()), new RB_gld()._col_back_alarm());
						e.printStackTrace();
					}
				}

			}
			
			
			getOwnSaveButton()._setShapeSaveAndClose()._setShapeStandardTextButton();
			getOwnCloseButton()._setShapeCancelAndClose()._setShapeStandardTextButton();
			
			grid._a(new E2_Grid()._s(2)._a(getOwnSaveButton(), new RB_gld()._ins(2, 20, 15, 2))._a(getOwnCloseButton(), new RB_gld()._ins(2, 20, 2, 2)));
			
			this.add(grid,E2_INSETS.I(5));
		}
		
	}
	
	
	
	
	/**
	 * klass zeigt den aktuellen status einer sorte in der RC-Zuordnung
	 * @author martin
	 * @date 21.10.2020
	 *
	 */
	private class Sorte extends E2_Grid {
		private Rec22  				sorte = null;
		
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
		public Sorte(Rec22 sorte, Rec22Land land,Rec22LandRcSorte  	landRcSorte,  Boolean aktuellRcStatus) throws myException {
			super();
			this.sorte = sorte;
			this.land = land;
			this.aktuellRcStatus = aktuellRcStatus;
			this.landRcSorte = landRcSorte;
			
			this._setSize(20,250,20)	._a(cb, 											new RB_gld()._col_back(actualColorBack))
										._a(new RB_lab(land.getUfs(LAND.laendername,"")), 	new RB_gld()._col_back(actualColorBack))
										._a(warnschild, 									new RB_gld()._col_back(actualColorBack));
			
			
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
			this._clear()._setSize(20,250,20)	._a(cb, 												new RB_gld()._col_back(actualColorBack))
												._a(new RB_lab(land.getUfs(LAND.laendername,"")+"1"), 	new RB_gld()._col_back(actualColorBack))
												._a(warnschild, 										new RB_gld()._col_back(actualColorBack));

		}
		
		public String getSqlStatement() throws myException  {
			String sql = "";
			MySqlStatementBuilder  statement = new MySqlStatementBuilder(_TAB.land_rc_sorten.fullTableName());
			
			if (landRcSorte == null) {
				if (cb.isSelected()) {
					statement.addSQL_Paar(LAND_RC_SORTEN.id_land.fieldName(), land.getActualID().toString());
					statement.add_raw(LAND_RC_SORTEN.id_land_rc_sorten, _TAB.land_rc_sorten.seq_nextval(), false);	
					if (this.sorte==null) {
						statement.add_raw(LAND_RC_SORTEN.id_artikel, _TAB.artikel.seq_currval(), false);	
					} else {
						statement.addSQL_Paar(LAND_RC_SORTEN.id_artikel.fn(), this.sorte.getIdLong().toString(), false);	
					}
					sql = statement.get_InsertString();
				} 
			} else {
				if (!cb.isSelected()) {
					//dann loeschen
					sql = "DELETE FROM "+LAND_RC_SORTEN.fullTabName()+" WHERE "+LAND_RC_SORTEN.id_land_rc_sorten.fieldName()+"="+landRcSorte.getId();
				}
			}
			
			return sql;
		}
		
	}

	
}
