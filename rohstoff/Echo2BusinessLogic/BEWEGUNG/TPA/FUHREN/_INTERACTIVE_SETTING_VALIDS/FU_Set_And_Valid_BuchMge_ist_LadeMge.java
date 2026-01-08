package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN._INTERACTIVE_SETTING_VALIDS;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_MAP_Set_And_Valid;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU___CONST;

public class FU_Set_And_Valid_BuchMge_ist_LadeMge extends XX_MAP_Set_And_Valid
{

	private String DEF_QUELLE_ZIEL = null;
	
	private String  			FIELDNAME_LADE_MENGE = null;
	private String  			FIELDNAME_ABLADE_MENGE = null;
	private String    			FIELDNAME_CB_NORMALE_ABRECHNUNG = null;    	//schalter fuer Buchungsmenge=Lademengen usw
	
	//2017-01-16: beschriftung der checkbox
	private String         		LABEL_NAME_AT_CHECKBOX = null;   			//aktives label zur beschriftung der checkboc
	private String         		text_auf_label_wenn_cb_ein = null;   			//aktives label zur beschriftung der checkboc
	private String         		text_auf_label_wenn_cb_aus = null;   			//aktives label zur beschriftung der checkboc
	private String         		text_auf_label_wenn_cb_ein_lager = null;   			//aktives label zur beschriftung der checkboc
	private String         		text_auf_label_wenn_cb_aus_lager = null;   			//aktives label zur beschriftung der checkboc
	
	
	public FU_Set_And_Valid_BuchMge_ist_LadeMge(String dEF_QUELLE_ZIEL)
	{
		super();
		DEF_QUELLE_ZIEL = dEF_QUELLE_ZIEL;
		
		boolean bEK = (this.DEF_QUELLE_ZIEL.equals("EK"));
		
		this.FIELDNAME_LADE_MENGE = 					bEK?RECORD_VPOS_TPA_FUHRE.FIELD__ANTEIL_LADEMENGE_LIEF:		RECORD_VPOS_TPA_FUHRE.FIELD__ANTEIL_LADEMENGE_ABN;
		this.FIELDNAME_ABLADE_MENGE = 					bEK?RECORD_VPOS_TPA_FUHRE.FIELD__ANTEIL_ABLADEMENGE_LIEF:	RECORD_VPOS_TPA_FUHRE.FIELD__ANTEIL_ABLADEMENGE_ABN;
		
		this.FIELDNAME_CB_NORMALE_ABRECHNUNG = 			bEK?RECORD_VPOS_TPA_FUHRE.FIELD__LADEMENGE_GUTSCHRIFT:		RECORD_VPOS_TPA_FUHRE.FIELD__ABLADEMENGE_RECHNUNG;
		
		this.LABEL_NAME_AT_CHECKBOX =					bEK?FU___CONST.ACTIVLABEL_LADEMENGE_IST_BUCHUNGSMENGE: 		FU___CONST.ACTIVLABEL_ABLADEMENGE_IST_BUCHUNGSMENGE;
		this.text_auf_label_wenn_cb_ein =				bEK?"Anteil Lademge.(2)= Gutschriftsmenge": 				"Anteil Ablademge.(6)= Rechnungsmenge";
		this.text_auf_label_wenn_cb_aus =				bEK?"Anteil Ablademge.(3)= Gutschriftsmenge": 				"Anteil Lademge.(5)= Rechnungsmenge";
		this.text_auf_label_wenn_cb_ein_lager =			bEK?"Anteil Lademge.(2)= Mge. vom Lager": 					"Anteil Ablademge.(6)= Mge. ins Lager";
		this.text_auf_label_wenn_cb_aus_lager =			"<ACHTUNG! AUF LAGERSEITE in der Regel nicht sinnvoll!>";
		
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_COPY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
	{
		return this.makeInterneErmittlung(oMAP,ActionType);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_EMPTY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
	{
		return this.makeInterneErmittlung(oMAP,ActionType);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_EDIT(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
	{
		return this.makeInterneErmittlung(oMAP,ActionType);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_VIEW(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
	{
		return new MyE2_MessageVector();
	}
	
	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_UNDEFINED(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
	{
		return new MyE2_MessageVector();
	}

	
	
	private MyE2_MessageVector makeInterneErmittlung(E2_ComponentMAP oMAP, int ActionType) throws myException {
		MyE2_MessageVector oMV_Rueck = new MyE2_MessageVector();
		
		//2017-01-16: feststellen, ob es links oder rechts um ein lager geht, beschriftung des schalters setzen
		if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_KLICK_ACTION || ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION)	{
			boolean is_ek = 			this.DEF_QUELLE_ZIEL.equals("EK");
			boolean is_mandant = 		false;
			boolean is_chkbox_an = 		oMAP.get_bActualDBValue(this.FIELDNAME_CB_NORMALE_ABRECHNUNG);
			
			if (is_ek)	{
				long id_adresse = oMAP.get_LActualDBValue(VPOS_TPA_FUHRE.id_adresse_start.fn(), 0L, 0L).longValue();
				if ((""+id_adresse).equals(bibALL.get_ID_ADRESS_MANDANT())) {
					is_mandant = true;
				}
			} else {
				long id_adresse = oMAP.get_LActualDBValue(VPOS_TPA_FUHRE.id_adresse_ziel.fn(), 0L, 0L).longValue();
				if ((""+id_adresse).equals(bibALL.get_ID_ADRESS_MANDANT())) {
					is_mandant = true;
				}
			}

			if (!is_mandant) {
				if (is_chkbox_an) {
					((RB_lab)oMAP.get__Comp(this.LABEL_NAME_AT_CHECKBOX))._tr(this.text_auf_label_wenn_cb_ein);
				} else {
					((RB_lab)oMAP.get__Comp(this.LABEL_NAME_AT_CHECKBOX))._tr(this.text_auf_label_wenn_cb_aus);
				}
			} else {
				if (is_chkbox_an) {
					((RB_lab)oMAP.get__Comp(this.LABEL_NAME_AT_CHECKBOX))._tr(this.text_auf_label_wenn_cb_ein_lager);
				} else {
					((RB_lab)oMAP.get__Comp(this.LABEL_NAME_AT_CHECKBOX))._tr(this.text_auf_label_wenn_cb_aus_lager);
					if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_KLICK_ACTION) {
						if (is_ek) {
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Achtung! In der Regel sollte beim Laden vom eigenen Lager NUR die Lademenge gefüllt sein!"));
						} else {
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Achtung! In der Regel sollte beim Abladen auf das eigene Lager nur die Ablademenge gefüllt sein !"));
						}
					}
				}
			}
			
			
//			String text_checkbox_on = "";
//			String text_checkbox_off = "";
//			if (is_ek)	{
//				if (is_mandant) {
//					text_checkbox_on = "Anteil Lademge.(2)= Mge. vom Lager";
//					text_checkbox_off = "<ACHTUNG! AUF LAGERSEITE falsche Eingabe>";
//				} else {
//					text_checkbox_on = "Anteil Lademge.(2)= Gutschriftsmenge";
//					text_checkbox_off = "Anteil Ablademge.(3)= Gutschriftsmenge";
//				}
//			} else {
//				if (is_mandant) {
//					text_checkbox_on = "Anteil Ablademge.(6)= Mge. ins Lager";
//					text_checkbox_off = "<ACHTUNG! AUF LAGERSEITE in der Regel nicht sinnvoll!>";
//				} else {
//					text_checkbox_on = "Anteil Ablademge.(6)= Rechnungsmenge";
//					text_checkbox_off = "Anteil Lademge.(5)= Rechnungsmenge";
//				}
//			}
//			if (is_ek)	{
//				if (oMAP.get_bActualDBValue(this.FIELDNAME_CB_NORMALE_ABRECHNUNG)) {
//					((RB_lab)oMAP.get__Comp(FU___CONST.ACTIVLABEL_LADEMENGE_IST_BUCHUNGSMENGE))._tr(text_checkbox_on);
//				} else {
//					((RB_lab)oMAP.get__Comp(FU___CONST.ACTIVLABEL_LADEMENGE_IST_BUCHUNGSMENGE))._tr(text_checkbox_off);
//				}
//			} else {
//				if (oMAP.get_bActualDBValue(this.FIELDNAME_CB_NORMALE_ABRECHNUNG)) {
//					((RB_lab)oMAP.get__Comp(FU___CONST.ACTIVLABEL_ABLADEMENGE_IST_BUCHUNGSMENGE))._tr(text_checkbox_on);
//				} else {
//					((RB_lab)oMAP.get__Comp(FU___CONST.ACTIVLABEL_ABLADEMENGE_IST_BUCHUNGSMENGE))._tr(text_checkbox_off);
//				}
//			}
		}
		
		
		if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_KLICK_ACTION)	{
			if (oMAP.get_bActualDBValue(this.FIELDNAME_CB_NORMALE_ABRECHNUNG))	{
				if (this.DEF_QUELLE_ZIEL.equals("EK"))	{
					oMAP.set_ActiveADHOC(this.FIELDNAME_ABLADE_MENGE, false, true);
				} else {
					oMAP.set_ActiveADHOC(this.FIELDNAME_LADE_MENGE, false, true);
				}
			} else {
				if (this.DEF_QUELLE_ZIEL.equals("EK"))	{
					oMAP.set_ActiveADHOC(this.FIELDNAME_ABLADE_MENGE, true, true);
				} else {
					oMAP.set_ActiveADHOC(this.FIELDNAME_LADE_MENGE, true, true);
				}
			}
		} else if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION) {
			if (oMAP.get_bActualDBValue(this.FIELDNAME_CB_NORMALE_ABRECHNUNG)) {
				if (this.DEF_QUELLE_ZIEL.equals("EK"))	{
					oMAP.set_ActiveADHOC(this.FIELDNAME_ABLADE_MENGE, false, false);
				} else	{
					oMAP.set_ActiveADHOC(this.FIELDNAME_LADE_MENGE, false, false);
				}
			}
		}
		
		return oMV_Rueck;
	}
	
}
