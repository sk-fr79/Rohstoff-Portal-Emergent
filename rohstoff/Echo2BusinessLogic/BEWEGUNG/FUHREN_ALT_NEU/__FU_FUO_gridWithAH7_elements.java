/**
 * 
 */
package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.DB_ENUMS.AH7_STEUERDATEI;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.AH7.AH7__ENUM_STATUSRELATION;
import rohstoff.Echo2BusinessLogic.AH7._services._PdServiceOpenAH7SteuerTabelleMask4EditOrView;
import rohstoff.Echo2BusinessLogic.AH7._services._PdServiceOpenAH7SteuerTabelleMask4New;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_AH7_Steuerdatei;

/**
 * @author martin
 * baut die infospalten-elemente zum thema ah7 in der fuhre/fuhrenort auf
 */
public class __FU_FUO_gridWithAH7_elements extends E2_Grid {

	
	private enum ah7_status {
		steuersatz_fehlt
		,steuersatz_status_undef
		,steuersatz_status_inactive
		,steuersatz_status_active
	}
	
	
	private boolean isAh7_relation = false;
	private Long    ladresse_lager_start = null;
	private Long    ladresse_lager_ziel = null;
	private XX_ActionAgent agentRefreshLine;
	
	/**
	 * @throws myException 
	 * 
	 */
	public __FU_FUO_gridWithAH7_elements(boolean isAHRelation, Long id_adresse_lager_start, Long id_adresse_lager_ziel, XX_ActionAgent agentRefreshLine)  {
		super();
		this.agentRefreshLine = agentRefreshLine;
		this._s(1);
		
		this.isAh7_relation=isAHRelation;
		this.ladresse_lager_start = id_adresse_lager_start;
		this.ladresse_lager_ziel = 	id_adresse_lager_ziel;
		
		try {
			if (ENUM_MANDANT_DECISION.AH7_USE_STEUERTABELLE.is_YES()) {
				if (this.isAh7_relation) {
					this._a(new Ah7buttonYes());
				} else {
					this._a(new Ah7labelNo());
				}
			} else {
				if (this.isAh7_relation) {
					this._a(new Ah7labelYes());
				} else {
					this._a(new Ah7labelNo());
				}
			}
		} catch (myException e) {
			this._a("ERR");
			e.printStackTrace();
		}
	}

	private class Ah7labelYes extends RB_lab {
		public Ah7labelYes() throws myException {
			super();
			this._icon(E2_ResourceIcon.get_RI("ah7_yes.png"));
			this._ttt("Fuhre braucht einen AH7");
		}
	}
	
	
	private class Ah7labelNo extends RB_lab {
		public Ah7labelNo() throws myException {
			super();
			this._icon(E2_ResourceIcon.get_RI("ah7_no.png"));
			this._ttt("Fuhre braucht keinen AH7");
		}
	}
	
	/**
	 * button wird angezeigt, wenn die fuhre eine ah7-fuhre und das AH7_USE_STEUERTABELLE = Y ist
	 */
	private class Ah7buttonYes extends E2_Button {
		public Ah7buttonYes() throws myException {
			super();
			
				
			//jetzt feststellen, ob es einen steuerdatensatz gibt
			Rec21_AH7_Steuerdatei r = new Rec21_AH7_Steuerdatei(ladresse_lager_start.toString(),ladresse_lager_ziel.toString());
			
			ah7_status meinStatus = ah7_status.steuersatz_fehlt;
			if (r.is_ExistingRecord()) {
				if (S.isFull(r.getUfs(AH7_STEUERDATEI.status_relation))) {
					if (r.getUfs(AH7_STEUERDATEI.status_relation).equals(AH7__ENUM_STATUSRELATION.UNDEF.db_val())) {
						meinStatus=ah7_status.steuersatz_status_undef;
					} else if (r.getUfs(AH7_STEUERDATEI.status_relation).equals(AH7__ENUM_STATUSRELATION.INACTIVE.db_val())) {
						meinStatus=ah7_status.steuersatz_status_inactive;
					} else if (r.getUfs(AH7_STEUERDATEI.status_relation).equals(AH7__ENUM_STATUSRELATION.ACTIVE.db_val())) {
						meinStatus=ah7_status.steuersatz_status_active;
					}
				}
			} 
			
			switch (meinStatus) {
			case steuersatz_fehlt:
				this.__setImages(E2_ResourceIcon.get_RI("ah7_yes.png"), E2_ResourceIcon.get_RI("ah7_yes.png"))._s_Image();
				if (ENUM_MANDANT_DECISION.AH7_USE_OLD_IF_NO_RULE_EXISTING.is_YES()) {
					this._ttt("Fuhre braucht einen AH7! Der Steuerungsdatensatz kann hier erstellt werden! Falls keiner vorhanden ist, wird das alte AH7-Formular benutzt !");
				} else {
					this._ttt("Fuhre braucht einen AH7! Der Steuerungsdatensatz kann hier erstellt werden! Falls keiner vorhanden ist, wird beim Druck ein Fehler angezeigt !");
				}
				this.add_GlobalValidator(ENUM_VALIDATION.AH7_PROFIL_NEW.getValidatorWithoutSupervisorPersilschein());
				this._aaa(()->{ new _PdServiceOpenAH7SteuerTabelleMask4New().show(ladresse_lager_start, ladresse_lager_ziel, agentRefreshLine);});
				//hier neu-erfassungs action
				
				break;
			case steuersatz_status_active:
				this.__setImages(E2_ResourceIcon.get_RI("ah7_yes_green.png"), E2_ResourceIcon.get_RI("ah7_no.png"))._s_Image();
				this._ttt("Fuhre braucht einen AH7, der Steuerdatensatz dafür ist aktiv !");
				if (ENUM_VALIDATION.AH7_PROFIL_EDIT.getValidatorWithoutSupervisorPersilschein().isValid((Component)null).isOK()) {
					this._aaa(()->{ new _PdServiceOpenAH7SteuerTabelleMask4EditOrView().show(ladresse_lager_start, ladresse_lager_ziel, agentRefreshLine,MASK_STATUS.EDIT);  });
				} else {
					this._aaa(()->{ new _PdServiceOpenAH7SteuerTabelleMask4EditOrView().show(ladresse_lager_start, ladresse_lager_ziel, agentRefreshLine,MASK_STATUS.VIEW);  });
				}
				
				break;
			case steuersatz_status_inactive:
				this.__setImages(E2_ResourceIcon.get_RI("ah7_yes_yellow.png"), E2_ResourceIcon.get_RI("ah7_no.png"))._s_Image();
				this._ttt("Fuhre braucht einen AH7, der Steuerdatensatz dafür ist gefüllt, aber noch nicht freigegeben !");

				if (ENUM_VALIDATION.AH7_PROFIL_EDIT.getValidatorWithoutSupervisorPersilschein().isValid((Component)null).isOK()) {
					this._aaa(()->{ new _PdServiceOpenAH7SteuerTabelleMask4EditOrView().show(ladresse_lager_start, ladresse_lager_ziel, agentRefreshLine,MASK_STATUS.EDIT);  });
				} else {
					this._aaa(()->{ new _PdServiceOpenAH7SteuerTabelleMask4EditOrView().show(ladresse_lager_start, ladresse_lager_ziel, agentRefreshLine,MASK_STATUS.VIEW);  });
				}
				
				break;
			case steuersatz_status_undef:
				this.__setImages(E2_ResourceIcon.get_RI("ah7_yes_red.png"), E2_ResourceIcon.get_RI("ah7_no.png"))._s_Image();
				this._ttt("Fuhre braucht einen AH7, der Steuerdatensatz dafür ist angelegt, aber noch nicht definiert und freigegeben !");

				if (ENUM_VALIDATION.AH7_PROFIL_EDIT.getValidatorWithoutSupervisorPersilschein().isValid((Component)null).isOK()) {
					this._aaa(()->{ new _PdServiceOpenAH7SteuerTabelleMask4EditOrView().show(ladresse_lager_start, ladresse_lager_ziel, agentRefreshLine,MASK_STATUS.EDIT);  });
				} else {
					this._aaa(()->{ new _PdServiceOpenAH7SteuerTabelleMask4EditOrView().show(ladresse_lager_start, ladresse_lager_ziel, agentRefreshLine,MASK_STATUS.VIEW);  });
				}

				
				break;
			default:
				break;
			}
		}
	}

	
}
