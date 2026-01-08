package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.MaskController;

import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_BEFUND;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_CONST;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_CONST.ENUM_ZustandWiegekarte;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_ENUM_WKTYP;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_MASK_ComponentMap_Wiegekarte;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_Popup_Kennzeichen;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_Popup_Kennzeichen_Trailer;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_SelField_WiegekartenTyp;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components.WK_RB_WeWa;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWiegekarte;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.REC.RecDOWiegekarteBefund;


public class WK_RB_MC_ZustandWiegekarte extends RB_MaskController {

	private Vector<MyE2IF__Component> _v_all = new Vector<>();
	private Vector<MyE2IF__Component> _v_disabled_when_new = new Vector<>();
	private Vector<MyE2IF__Component> _v_disabled_when_stammdaten = new Vector<>();
	private Vector<MyE2IF__Component> _v_disabled_when_waegung1 = new Vector<>();
	private Vector<MyE2IF__Component> _v_disabled_when_waegung2 = new Vector<>();
	private Vector<MyE2IF__Component> _v_disabled_when_gedruckt = new Vector<>();
	
	
	// wichtig zum Setzen der einzelnen Masken-Elemente
	private RecDOWiegekarte			_recWK = null;
	private ENUM_ZustandWiegekarte  _Zustand_WK = null;
	private MASK_STATUS				_Mask_Status = null;
	private enumWEWA				_WEWA = null;
	
	private enum enumWEWA {
		WE,
		WA,
		INVALID
	};
	
	
	public WK_RB_MC_ZustandWiegekarte(IF_RB_Component p_component) throws myException {
		super(p_component);
		init_fields();
	}

	public WK_RB_MC_ZustandWiegekarte(RB_ComponentMap p_componentMap) throws myException {
		super(p_componentMap);
		init_fields();
	}
	
	public WK_RB_MC_ZustandWiegekarte(RB_ComponentMap p_componentMap, MyE2_MessageVector mvForErrors) {
		super(p_componentMap, mvForErrors);
		init_fields();
	}

	
	private void init_fields ()  {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		
		try {
			
			WK_RB_MASK_ComponentMap_Wiegekarte compMap = (WK_RB_MASK_ComponentMap_Wiegekarte) this.get_ComponentMapCollector().get(RecDOWiegekarte.key);
			WK_RB_WeWa 						   cWEWA   = (WK_RB_WeWa) this.getRbComp(RecDOWiegekarte.key, WIEGEKARTE.ist_lieferant, bibMSG.MV());	
			
			_Mask_Status = getMaskStatus();
			
			_recWK = (RecDOWiegekarte) compMap.getParams().getMaskDataObjectsCollector().get(RecDOWiegekarte.key);
			_Zustand_WK = _recWK._get_ZustandWiegekarte();
			_WEWA = cWEWA.isValid() ? (cWEWA.isLieferant()? enumWEWA.WE : enumWEWA.WA) : enumWEWA.INVALID;

			
			_v_all.addAll(compMap.getHmOnlyRbComponentsSaveable().values());
			_v_all.add(this.get_comp(RecDOWiegekarte.key,WK_RB_Popup_Kennzeichen.key, mv));
			_v_all.add(this.get_comp(RecDOWiegekarte.key,WK_RB_Popup_Kennzeichen_Trailer.key, mv));
			
			
			_v_all.addAll(this.get_ComponentMapCollector().get(RecDOWiegekarteBefund.key).getHmOnlyRbComponentsSaveable().values());
			_v_all.add(this.getRbComp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.CHILD_ABZUG_GEBINDE.key(),mv) );
			_v_all.add(this.getRbComp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.CHILD_ABZUG_MENGE.key(),mv) );
			_v_all.add(this.getRbComp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.BT_PRINT_HOFSCHEIN.key(),mv) );
			
			// wenn neu, dann keine Tochtertabllen
			_v_disabled_when_new.add(this.getRbComp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.CHILD_ABZUG_GEBINDE.key(),mv));
			_v_disabled_when_new.add(this.getRbComp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.CHILD_ABZUG_MENGE.key(),mv) );
			
			_v_disabled_when_new.add(this.getRbComp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.BT_PRINT_HOFSCHEIN.key(),mv) );
			_v_disabled_when_new.add(this.getRbComp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.BT_PRINT_BUERO.key(),mv) );
			_v_disabled_when_new.add(this.getRbComp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.BT_PRINT_ETIKETT.key(),mv) );
			_v_disabled_when_new.add(this.getRbComp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.BT_PRINT_WK.key(),mv) );
			_v_disabled_when_new.add(this.getRbComp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.TXT_NUM_ETIKETTEN.key(),mv) );
			_v_disabled_when_new.add(this.getRbComp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.COMP_SEL_DRUCKTYP.key(),mv) );

//			_v_disabled_when_new.add(this.getRbComp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.COMP_WAEGUNG1.key(),mv));
			_v_disabled_when_new.add(this.getRbComp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.COMP_WAEGUNG2.key(),mv));
			
			
			
			// STAMMDATEN
			_v_disabled_when_stammdaten.add(this.getRbComp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.COMP_WAEGUNG2.key(),mv));
			_v_disabled_when_stammdaten.add(this.getRbComp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.BT_PRINT_HOFSCHEIN.key(),mv) );
			
			_v_disabled_when_stammdaten.add(this.getRbComp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.BT_PRINT_BUERO.key(),mv) );
			_v_disabled_when_stammdaten.add(this.getRbComp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.BT_PRINT_ETIKETT.key(),mv) );
			_v_disabled_when_stammdaten.add(this.getRbComp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.BT_PRINT_WK.key(),mv) );
			_v_disabled_when_stammdaten.add(this.getRbComp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.TXT_NUM_ETIKETTEN.key(),mv) );
			_v_disabled_when_stammdaten.add(this.getRbComp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.COMP_SEL_DRUCKTYP.key(),mv) );

			
			//_v_disabled_when_stammdaten.add(this.getRbComp(coo, mv));
			
			// WAEGUNG1
			_v_disabled_when_waegung1.add(this.getRbComp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.COMP_WAEGUNG1.key(),mv));
			
			_v_disabled_when_waegung1.add(this.getRbComp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.BT_PRINT_BUERO.key(),mv) );
			_v_disabled_when_waegung1.add(this.getRbComp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.BT_PRINT_ETIKETT.key(),mv) );
			_v_disabled_when_waegung1.add(this.getRbComp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.BT_PRINT_WK.key(),mv) );
			_v_disabled_when_waegung1.add(this.getRbComp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.TXT_NUM_ETIKETTEN.key(),mv) );
			_v_disabled_when_waegung1.add(this.getRbComp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.COMP_SEL_DRUCKTYP.key(),mv) );
			
			
			
			// WAEGUNG2
			_v_disabled_when_waegung2.add(this.getRbComp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.COMP_WAEGUNG1.key(),mv));
			_v_disabled_when_waegung2.add(this.getRbComp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.COMP_WAEGUNG2.key(),mv));
			_v_disabled_when_waegung2.add(this.getRbComp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.BT_PRINT_HOFSCHEIN.key(),mv) );
			
			// GEDRUCKT alle sperren...
			_v_disabled_when_gedruckt.addAll(_v_all);
			_v_disabled_when_gedruckt.remove(this.getRbComp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.TXT_NUM_ETIKETTEN.key(),mv));
			_v_disabled_when_gedruckt.remove(this.getRbComp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.COMP_SEL_DRUCKTYP.key(),mv));

			// ...ausser Befundungsauftrags-Daten
			_v_disabled_when_gedruckt.remove(this.getRbComp(RecDOWiegekarteBefund.key, WIEGEKARTE_BEFUND.sortierung,mv));
			_v_disabled_when_gedruckt.remove(this.getRbComp(RecDOWiegekarteBefund.key, WIEGEKARTE_BEFUND.analyse,mv));
			_v_disabled_when_gedruckt.remove(this.getRbComp(RecDOWiegekarteBefund.key, WIEGEKARTE_BEFUND.naesseprobe,mv));
			_v_disabled_when_gedruckt.remove(this.getRbComp(RecDOWiegekarteBefund.key, WIEGEKARTE_BEFUND.bemerkung,mv));
			_v_disabled_when_gedruckt.remove(this.getRbComp(RecDOWiegekarteBefund.key, WIEGEKARTE_BEFUND.id_wiegekarte_user_befund,mv));


			
			
			
			_v_disabled_when_gedruckt.add(this.getRbComp(RecDOWiegekarte.key, WK_RB_CONST.MASK_KEYS_String.BT_PRINT_HOFSCHEIN.key(),mv) );
			
		} catch (myException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * enabled alle componten die in der Liste _v_all erfasst sind
	 * @author manfred
	 * @date 23.07.2020
	 *
	 * @return
	 */
	public WK_RB_MC_ZustandWiegekarte _enableAll() {
		if (_Mask_Status.equals(MASK_STATUS.NEW)) {
			return this	;
		}
		
		
		boolean bEnable = true;
		
		for (MyE2IF__Component comp: _v_all) {
			try {
				comp.set_bEnabled_For_Edit(bEnable);
			} catch (myException e) {};
		}
		return this;
	}
	
	

	/**
	 * disabled alle Objekte die zur Liste gehören
	 * @author manfred
	 * @date 23.07.2020
	 *
	 * @param vcomp
	 * @throws myException
	 */
	private void disableAllOf(Vector<MyE2IF__Component> vcomp) throws myException {
		for (MyE2IF__Component comp: vcomp) {
			disableComp(comp);
		}
	}

	
	private void disableComp(MyE2IF__Component comp) throws myException {
		comp.set_bEnabled_For_Edit(false);
	}
	
	
	
	/**
	 * Disabled alle zwingenden Felder die bei einem bestimmten Zustand disabled sein müssen
	 * @author manfred
	 * @date 23.07.2020
	 *
	 * @param zustand
	 * @return
	 */
	public WK_RB_MC_ZustandWiegekarte _disableAllOf(ENUM_ZustandWiegekarte zustand) {
		
		try {
			if (zustand.equals(ENUM_ZustandWiegekarte.NEU)) {
				disableAllOf(_v_disabled_when_new);
			}
			else if (zustand.equals(ENUM_ZustandWiegekarte.STAMMDATEN)) {
				disableAllOf(_v_disabled_when_stammdaten);
			}
			else if (zustand.equals(ENUM_ZustandWiegekarte.WAEGUNG1)) {
				disableAllOf(_v_disabled_when_waegung1);
			}
			else if (zustand.equals(ENUM_ZustandWiegekarte.WAEGUNG2)) {
				disableAllOf(_v_disabled_when_waegung2);
			}
			else if (zustand.equals(ENUM_ZustandWiegekarte.GEDRUCKT)) {
				disableAllOf(_v_disabled_when_gedruckt);
			} else if (zustand.equals(ENUM_ZustandWiegekarte.STORNO)) {
				disableAllOf(_v_all);
			}
			
		} catch (myException e) {
			e.printStackTrace();
		}
		
		// besonderer Zustand des Wiegekarten-Typs
		_setStatus_WiegekartenTyp(zustand);

		;
		return this;
	}

	
	
	/**
	 * disabled alle Felder, die zwingend anhand des Wiegekarten-Zustands (NEU,STAMMDATEN,....GEDRUCKT) disabled werden müssen
	 * @author manfred
	 * @date 08.07.2020
	 */
	public WK_RB_MC_ZustandWiegekarte _disable_by_ZustandWiegekarte() {
		
		if (_Mask_Status.equals(MASK_STATUS.VIEW)) {
			return this;
		}
		
		_disableAllOf(_recWK._get_ZustandWiegekarte());
		
		return this;
	}
	
	
	
	
	/**
	 * WK-Typ darf geändert werden, bis gedruckt wurde.
	 * Ausnahme: Dokumentarische Verwiegung. Dann darf nach der 1. Verwiegung nichts mehr geändert werden.
	 * @author manfred
	 * @date 15.07.2020
	 *
	 */
	private void _setStatus_WiegekartenTyp(ENUM_ZustandWiegekarte zustand) {
		
		if (_Mask_Status.equals(MASK_STATUS.VIEW)) {
			return;
		}
		
		try {
			WK_RB_SelField_WiegekartenTyp wkTyp = (WK_RB_SelField_WiegekartenTyp) this.getRbComp(RecDOWiegekarte.key, WIEGEKARTE.typ_wiegekarte, bibMSG.MV());
			
			boolean bIsEnabled = wkTyp.isEnabled();
			
			WK_RB_ENUM_WKTYP typ = wkTyp._getCurrentSelectedTyp();
			
			boolean bDisable = ( typ.equals(WK_RB_ENUM_WKTYP.DOKUMENTARISCH)    
				               && !zustand.equals(ENUM_ZustandWiegekarte.NEU) 
				               && !zustand.equals(ENUM_ZustandWiegekarte.STAMMDATEN)
				               ) ;
			// falls es eine Storno-Korrektur ist, dann darf der Typ nicht geändert werden.
			bDisable |= typ.equals(WK_RB_ENUM_WKTYP.WIEGEKARTE_KORREKTUR);
			
			wkTyp.set_bEnabled_For_Edit(bIsEnabled & !bDisable);
			
		} catch (myException e) {}
		
	}

	
}
