 
package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.BELEG_GRENZ_UBERTRITT;
  
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
import panter.gmbh.basics4project.DB_ENUMS.PROFIL_GRENZUBERTRITT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
  
public class BGL_PROFIL_MASK_bt_new extends RB_bt_New {
    
	private BGL_LIST_popup_BelegGrenzUbertritt popUp;
	
	public BGL_PROFIL_MASK_bt_new(BGL_LIST_popup_BelegGrenzUbertritt p_parent) {
        super();
        this.popUp = p_parent;
        this.setToolTipText(new MyE2_String("Neues Profil erstellen").CTrans());
    }
    
    @Override
    public RB_ModuleContainerMASK generate_MaskContainer() throws myException {
        return new BGL_PROFIL_MASK_MaskModulContainer();
    }
    
    @Override
    public RB_DataobjectsCollector generate_DataObjects4New() throws myException {
        return new BGL_PROFIL_MASK_DataObjectCollector();
    }
    
    @Override
    public void define_Actions_4_saveButton(RB_bt_New btNewInList,RB_bt_maskSaveAndClose bt_saveAndClose_In_Mask,RB_ModuleContainerMASK maskPopup) throws myException {
        bt_saveAndClose_In_Mask.add_oActionAgent(new RB_actionStandardSave(maskPopup));
        bt_saveAndClose_In_Mask.add_oActionAgent(new RB_actionStandardClosePopup(maskPopup));
        bt_saveAndClose_In_Mask.add_oActionAgent(new XX_ActionAgent() {
            @Override
            public void executeAgentCode(ExecINFO oExecInfo) throws myException {
            	popUp.reload_profile_list();
            	popUp.fill_with_profile(get_last_entry());
                bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Der Eintrag wurde gespeichert.")));
            }
        });
       
        bt_saveAndClose_In_Mask.add_oActionAgent(new XX_ActionAgent() {
            @Override
            public void executeAgentCode(ExecINFO oExecInfo) throws myException {
            	
            	
            }
        });
        
       
    }
    
    String get_last_entry() throws myException{
    	String last_id_abfrage = new SEL(PROFIL_GRENZUBERTRITT.id_profil_grenzubertritt)
    	.FROM(_TAB.profil_grenzubertritt)
    	.WHERE(new vgl(PROFIL_GRENZUBERTRITT.id_mandant, bibALL.get_ID_MANDANT())).s()
    	+" AND ROWNUM=1 ORDER BY " + PROFIL_GRENZUBERTRITT.letzte_aenderung.fieldName()
    	+ " ASC";
    	
    	return bibALL.convertID2UnformattedID(bibDB.EinzelAbfrage(last_id_abfrage));
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
 
