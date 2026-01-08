
package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.KOSTEN_NG;

import java.util.Collections;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.button.AbstractButton;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardClosePopup;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_New;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskClose;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskSaveAndClose;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;


public class FK_MASK_bt_MULTI_FUHREN_KOSTEN extends RB_bt_New {

	private E2_NavigationList naviList;

	public FK_MASK_bt_MULTI_FUHREN_KOSTEN(E2_NavigationList oNaviList) {
		super(E2_ResourceIcon.get_RI("kosten.png"),true);

		this.setStyle(MyE2_Button.StyleImageButtonNoBorders());
		this.setProperty( AbstractButton.PROPERTY_ALIGNMENT,new Alignment(Alignment.CENTER, Alignment.CENTER));

		this.naviList = oNaviList;

		this.add_GlobalValidator(new FK_Button_Eingabe_Kosten_NG_Validators(this.getNavigationList()));

		this.add_GlobalAUTHValidator_AUTO("MEHRFACHEINGABE_KOSTEN_ZU_FUHRE");

		this.setToolTipText(new MyE2_String("Kosten erfassen und auf mehrere Fuhren verteilen").CTrans());
	}

	public Vector<String> getSelectedFuhreId() throws myException{
		Vector<String> vSelection = this.getNavigationList().get_vSelectedIDs_Unformated();
		Collections.sort(vSelection);
		return vSelection;
	}

	public String getSmallestSelectedFuhreId() throws myException{
		Vector<String> selectedIds = this.getSelectedFuhreId();
		Collections.sort(selectedIds);
		if(selectedIds.size()>0){
			return selectedIds.get(0);
		}else return "";
	}

	public E2_NavigationList getNavigationList() {
		return naviList;
	}

	public String totalSelectedFuhreId() throws myException{
		return new Integer(this.getSelectedFuhreId().size()).toString();
	}

	@Override
	public RB_ModuleContainerMASK generate_MaskContainer() throws myException {
		return new FK_MASK_MaskModulContainer(this);
	}

	@Override
	public RB_DataobjectsCollector generate_DataObjects4New() throws myException {

		return new FK_MASK_DataObjectCollector(this);
	}

	@Override
	public void define_Actions_4_saveButton(RB_bt_New btNewInList, RB_bt_maskSaveAndClose bt_saveAndClose_In_Mask, RB_ModuleContainerMASK maskPopup) throws myException {

		bt_saveAndClose_In_Mask.add_oActionAgent(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {

				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Der Eintrag wurde gespeichert.")));

			}
		});

		bt_saveAndClose_In_Mask.add_oActionAgent(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				bibMSG.add_MESSAGE(maskPopup.rb_FirstAndOnlyComponentMapCollector().rb_COMPLETE_SAVE_CYCLE(true));	
			}
		});
		
		bt_saveAndClose_In_Mask.add_oActionAgent(new RB_actionStandardClosePopup(maskPopup));
	}

	@Override
	public void define_Actions_4_CloseButton(RB_bt_New btNewInList, RB_bt_maskClose bt_Close, RB_ModuleContainerMASK maskPopup) throws myException {

		bt_Close.add_oActionAgent(new RB_actionStandardClosePopup(maskPopup));
		bt_Close.add_oActionAgent(new XX_ActionAgent() {

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {

				bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Die Maske wurde abgebrochen ...")));

			}
		});
	}
}
