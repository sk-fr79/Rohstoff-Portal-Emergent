package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.KOSTEN;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelektorDateFromTo_NNG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.MyE2_TextField_Date_von_bis_POPUP_OWN;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.indep.exceptions.myException;

public class TP_KST_LIST_SelectorLadedatum_von_bis extends 	E2_SelektorDateFromTo_NNG {

	
	
	public TP_KST_LIST_SelectorLadedatum_von_bis() throws myException {
		super(VPOS_TPA_FUHRE.datum_aufladen.t().s(),VPOS_TPA_FUHRE.datum_aufladen.t().s());
		this.setWidthInPixel_InputFields(120);
	}


	@Override
	public void Ordne_Komponenten_An_in_DateVonbisPopup(MyE2_TextField_Date_von_bis_POPUP_OWN ownSelectVonBisPopup, MyE2_TextField oTextFieldVon, MyE2_TextField oTextFieldBis, MyE2_Button oButtonCalendar, MyE2_Button oButtonEraserVon,	MyE2_Button oButtonEraserBis) throws myException {
		ownSelectVonBisPopup.setSize(5);
		ownSelectVonBisPopup.setColumnWidth(0, new Extent(50));
//		oTextFieldVon.set_iWidthPixel(200);
//		oTextFieldBis.set_iWidthPixel(200);
////		ownSelectVonBisPopup.add(new MyE2_Label(new MyE2_String("Druck: "),new E2_FontPlain(-2)),E2_INSETS.I(0, 0, 2, 0));
		ownSelectVonBisPopup.add(oTextFieldVon,E2_INSETS.I(0, 0, 1, 0));
		ownSelectVonBisPopup.add(oButtonEraserVon,E2_INSETS.I(0, 0, 5, 0));
		ownSelectVonBisPopup.add(oTextFieldBis,E2_INSETS.I(0, 0, 1, 0));
		ownSelectVonBisPopup.add(oButtonEraserBis,E2_INSETS.I(0,0,5,0));
		ownSelectVonBisPopup.add(oButtonCalendar,E2_INSETS.I(0,0,1,0));
	}

	@Override
	public void HaengeMeineElementeAn_DateVonBisPopup(MyE2_TextField_Date_von_bis_POPUP_OWN ownSelectVonBisPopup,MyE2_Button oButtonLos, MyE2_Button oButtonHelp) throws myException {
		ownSelectVonBisPopup.setSize(6);
		ownSelectVonBisPopup.add(oButtonHelp,E2_INSETS.I_0_0_5_0);
	}

}
