package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.EU_VERTRAG;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_IF_components4decision;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_PopupContainer4Decision;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid4MaskSimple;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.exceptions.myException;


public class FS_ValAgent_WarnWhenNoStation extends DS_ActionAgent {

	private FS_POPUP_PrintMail_EU_VERTRAG parent;
	
	public FS_ValAgent_WarnWhenNoStation(DS_IF_components4decision p_actionComponent, FS_POPUP_PrintMail_EU_VERTRAG p_parent) {
		super(p_actionComponent);
		this.parent = p_parent;
	}

	@Override
	public Boolean make_decision_when_true_then_popup() throws myException {
		if(!(this.parent.isLagerListEmpty()) && this.parent.isSelectedLagerListEmpty()){
			this.parent.getLagerListInputStatus(false);
			return true;
		}
		return false;
	}

	@Override
	public DS_PopupContainer4Decision generate_popupContainer(DS_IF_components4decision activeComponent) throws myException {
		return new ownPopupContainer(this.get_actionComponent());
	}

	@Override
	public void fill_popupContainer(DS_PopupContainer4Decision container) throws myException {
		E2_Grid4MaskSimple  gm = new E2_Grid4MaskSimple()
						._add(new MyE2_Label(new MyE2_String("Sie haben keines der Lager ausgwählt ! Ist das korrekt)"),MyE2_Label.STYLE_TITEL_NORMAL()), new RB_gld()._span(2))
						._add(container.get_bt_OK(), new RB_gld()._ins(E2_INSETS.I(2,10,5,2)))
						._add(container.get_bt_NO(), new RB_gld()._ins(E2_INSETS.I(2,10,5,2)))
						._setSize(200,200);
		
		container.add(gm, E2_INSETS.I(2,3,2,3));
	}

	@Override
	public MyE2_MessageVector is_something_to_do_before_ok_is_possible() throws myException {
		return null;
	}

	
	private class ownPopupContainer extends DS_PopupContainer4Decision {

		public ownPopupContainer(DS_IF_components4decision p_motherComponent) {
			super(p_motherComponent);
		}

		@Override
		public int get_width_in_pixel() {
			return 400;
		}

		@Override
		public int get_height_in_pixel() {
			return 200;
		}

		@Override
		public MyE2_String get_titleText4PopUp() {
			return new MyE2_String("Es gibt Lager-Adressen ...");
		}
		
	}
	
}
