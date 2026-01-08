 
package rohstoff.businesslogic.bewegung.lager.list_bewegung;
  
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardClosePopup;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardSave;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_New;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskClose;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskSaveAndClose;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_AND_DOCUMENTATION.HAD_Searcher;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;

@Deprecated
public class BG_Lager_Bewegung_MASK_bt_new extends RB_bt_New {
    public BG_Lager_Bewegung_MASK_bt_new() {
        super();
        this.setText(new MyE2_String("NEU").CTrans());
        this.setToolTipText(new MyE2_String("Neuen xxxx erstellen").CTrans());
        this.setStyle(MyE2_Button.StyleImageButtonCenteredWithBlackBorder());
    }
    @Override
    public RB_ModuleContainerMASK generate_MaskContainer() throws myException {
        return new BG_Lager_Bewegung_MASK_MaskModulContainer();
    }
    @Override
    public RB_DataobjectsCollector generate_DataObjects4New() throws myException {
        return new BG_Lager_Bewegung_MASK_DataObjectCollector();
    }
    @Override
    public void define_Actions_4_saveButton(RB_bt_New btNewInList,RB_bt_maskSaveAndClose bt_saveAndClose_In_Mask,RB_ModuleContainerMASK maskPopup) throws myException {
        bt_saveAndClose_In_Mask.add_oActionAgent(new RB_actionStandardSave(maskPopup));
        bt_saveAndClose_In_Mask.add_oActionAgent(new RB_actionStandardClosePopup(maskPopup));
        bt_saveAndClose_In_Mask.add_oActionAgent(new XX_ActionAgent() {
            @Override
            public void executeAgentCode(ExecINFO oExecInfo) throws myException {
                bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Der Eintrag wurde gespeichert.")));
            }
        });
        bt_saveAndClose_In_Mask.add_oActionAgent(new XX_ActionAgent() {
            @Override
            public void executeAgentCode(ExecINFO oExecInfo) throws myException {
                new HAD_Searcher()._rebuild();
            }
        });
    }
    @Override
    public void define_Actions_4_CloseButton(RB_bt_New btNewInList,RB_bt_maskClose bt_Close, RB_ModuleContainerMASK maskPopup) throws myException {
        bt_Close.add_oActionAgent(new RB_actionStandardClosePopup(maskPopup));
        bt_Close.add_oActionAgent(new XX_ActionAgent() {
            @Override
            public void executeAgentCode(ExecINFO oExecInfo) throws myException {
                bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Die Maske wurde abgebrochen ...")));
            }
        });
    }
    
}
 
