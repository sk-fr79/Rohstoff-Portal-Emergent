package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE._INTERACTIVE_SETTING_VALIDS;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_MAP_Set_And_Valid;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE.FUO__CONST;

public class FUO_Set_And_Valid_BuchMge_ist_LadeMge extends XX_MAP_Set_And_Valid
{

	//2017-01-16: active beschriftung der checkbox
	private String         		text_auf_label_wenn_cb_ein = null;   			//aktives label zur beschriftung der checkboc
	private String         		text_auf_label_wenn_cb_aus = null;   			//aktives label zur beschriftung der checkboc
	private String         		text_auf_label_wenn_cb_ein_lager = null;   			//aktives label zur beschriftung der checkboc
	private String         		text_auf_label_wenn_cb_aus_lager = null;   			//aktives label zur beschriftung der checkboc

	
	public FUO_Set_And_Valid_BuchMge_ist_LadeMge() {
		super();
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

	
	
	private MyE2_MessageVector makeInterneErmittlung(E2_ComponentMAP oMAP, int ActionType) throws myException
	{
		MyE2_MessageVector oMV_Rueck = new MyE2_MessageVector();
		
		boolean 	bEK = new FUO_Ermittle_EK_VK(oMAP).is_EK();
		
		this.text_auf_label_wenn_cb_ein =				bEK?"Anteil Lademge.(2)= Gutschriftsmenge": 				"Anteil Ablademge.(3)= Rechnungsmenge";
		this.text_auf_label_wenn_cb_aus =				bEK?"Anteil Ablademge.(3)= Gutschriftsmenge": 				"Anteil Lademge.(2)= Rechnungsmenge";
		this.text_auf_label_wenn_cb_ein_lager =			bEK?"Anteil Lademge.(2)= Mge. vom Lager": 					"Anteil Ablademge.(3)= Mge. ins Lager";
		this.text_auf_label_wenn_cb_aus_lager =			"<ACHTUNG! AUF LAGERSEITE in der Regel nicht sinnvoll!>";

		String 		cFIELD_KORREKTE_ABRECHNUNG 	= bEK?RECORD_VPOS_TPA_FUHRE_ORT.FIELD__LADEMENGE_GUTSCHRIFT:RECORD_VPOS_TPA_FUHRE_ORT.FIELD__ABLADEMENGE_RECHNUNG;
		String      cFIELD_GEGEN_MENGE 			= bEK?RECORD_VPOS_TPA_FUHRE_ORT.FIELD__ANTEIL_ABLADEMENGE:RECORD_VPOS_TPA_FUHRE_ORT.FIELD__ANTEIL_LADEMENGE;

		//2017-01-16: feldbeschriftung der checkbox
		if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_KLICK_ACTION || ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION)	{

			boolean is_mandant = 		false;
			boolean is_chkbox_an = 		oMAP.get_bActualDBValue(cFIELD_KORREKTE_ABRECHNUNG);
			
			long id_adresse = oMAP.get_LActualDBValue(VPOS_TPA_FUHRE_ORT.id_adresse.fn(), 0L, 0L).longValue();
			if ((""+id_adresse).equals(bibALL.get_ID_ADRESS_MANDANT())) {
				is_mandant = true;
			}
	
			if (!is_mandant) {
				if (is_chkbox_an) {
					((RB_lab)oMAP.get__Comp(FUO__CONST.ACTIVLABEL_LADE_ABLADE_MENGE_IST_BUCHUNGSMENGE))._tr(this.text_auf_label_wenn_cb_ein);
				} else {
					((RB_lab)oMAP.get__Comp(FUO__CONST.ACTIVLABEL_LADE_ABLADE_MENGE_IST_BUCHUNGSMENGE))._tr(this.text_auf_label_wenn_cb_aus);
				}
			} else {
				if (is_chkbox_an) {
					((RB_lab)oMAP.get__Comp(FUO__CONST.ACTIVLABEL_LADE_ABLADE_MENGE_IST_BUCHUNGSMENGE))._tr(this.text_auf_label_wenn_cb_ein_lager);
				} else {
					((RB_lab)oMAP.get__Comp(FUO__CONST.ACTIVLABEL_LADE_ABLADE_MENGE_IST_BUCHUNGSMENGE))._tr(this.text_auf_label_wenn_cb_aus_lager);
					if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_KLICK_ACTION) {
						if (bEK) {
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Achtung! In der Regel sollte beim Laden vom eigenen Lager NUR die Lademenge gefüllt sein!"));
						} else {
							bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Achtung! In der Regel sollte beim Abladen auf das eigene Lager nur die Ablademenge gefüllt sein !"));
						}
					}
				}
			}
		}
		
		
		
		if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_KLICK_ACTION)
		{
			if (oMAP.get_bActualDBValue(cFIELD_KORREKTE_ABRECHNUNG))
			{
				oMAP.set_ActiveADHOC(cFIELD_GEGEN_MENGE, false, true);
			}
			else
			{
				oMAP.set_ActiveADHOC(cFIELD_GEGEN_MENGE, true, false);
			}
		}
		else if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION)
		{
			if (oMAP.get_bActualDBValue(cFIELD_KORREKTE_ABRECHNUNG))
			{
				//dann wird das gegenfeld noch gesperrt
				oMAP.set_ActiveADHOC(cFIELD_GEGEN_MENGE, false, false);
			}
		}
		
		return oMV_Rueck;
	}
	
}
