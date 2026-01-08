 
package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL;
  
import java.util.Vector;
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator_NG;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_List2Mask_V3;
import panter.gmbh.Echo2.RB.DATA.RB_hm_multi_DataobjectsCollector;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL.BOR_ART_CONST.PARAMHASH;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL.BOR_ART_CONST.BOR_ART_PARAMS;
import panter.gmbh.indep.exceptions.myException;
  
public class BOR_ART_LIST_bt_ListToMask extends RB_bt_List2Mask_V3 {
    private E2_NavigationList  naviList = null;
    
    //zentrale hashmap fuer transport von infos
    private PARAMHASH   params = null;
    
    
    public BOR_ART_LIST_bt_ListToMask(boolean bEdit, E2_NavigationList p_naviList ,PARAMHASH  p_params) {
        super(bEdit,p_naviList);
        this.naviList = p_naviList;
        this.params = p_params;        
        
        this.add_GlobalValidator(bEdit?ENUM_VALIDATION.BORDERCROSSING_ARTIKEL_EDIT.getValidator():ENUM_VALIDATION.BORDERCROSSING_ARTIKEL_VIEW.getValidator());
        
         /*
         *  in der validierer-enum folgende eintrage erfassen:
         *  panter.gmbh.basics4project.validation.ENUM_VALID_THEME
            ,BORDERCROSSING_ARTIKEL("BORDERCROSSING_ARTIKEL-Beschreibung")
         *  
         *  
         *  panter.gmbh.basics4project.validation.ENUM_VALIDATION
         *  
          	,BORDERCROSSING_ARTIKEL_EDIT( 	ENUM_VALID_THEME.BORDERCROSSING_ARTIKEL, 		ENUM_VALID_BASICFUNCTION.EDIT)
			,BORDERCROSSING_ARTIKEL_VIEW( 	ENUM_VALID_THEME.BORDERCROSSING_ARTIKEL, 		ENUM_VALID_BASICFUNCTION.VIEW)
			,BORDERCROSSING_ARTIKEL_NEW( 		ENUM_VALID_THEME.BORDERCROSSING_ARTIKEL, 	ENUM_VALID_BASICFUNCTION.NEW)
			,BORDERCROSSING_ARTIKEL_DELETE( 	ENUM_VALID_THEME.BORDERCROSSING_ARTIKEL, 	ENUM_VALID_BASICFUNCTION.DELETE)
         * 
         */
        
          this.add_GlobalValidator(new XX_ActionValidator_NG() {
			@Override
			public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
				MyE2_MessageVector mv = new MyE2_MessageVector();
				if (naviList.get_vSelectedIDs_Unformated().size()==0) {
					mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Sie müssen mindestens eine Datenzeile auswählen !")));
				}
				return mv;
			}
		});
        
        
    }
    @Override
    public RB_ModuleContainerMASK generate_MaskContainer() throws myException {
        return new BOR_ART_MASK_MaskModulContainer(this.params);
    }
    @Override
    public RB_hm_multi_DataobjectsCollector generate_DataObjects4Mask(MyE2_MessageVector mv_sammler) throws myException {
        Vector<String> v_ids = new Vector<String>();
        v_ids.addAll(this.naviList.get_vSelectedIDs_Unformated());
        
        RB_hm_multi_DataobjectsCollector collector = new RB_hm_multi_DataobjectsCollector();
        RB__CONST.MASK_STATUS aktuellerStatus = this.is_UsedToEdit()?RB__CONST.MASK_STATUS.EDIT:RB__CONST.MASK_STATUS.VIEW;
        
        for (String id: v_ids) {
            collector.put(id, new BOR_ART_MASK_DataObjectCollector(id,aktuellerStatus));
        }
        return collector;
    }
    @Override
    public MyE2_String generate_TitelInfo4MaskWindow(RB_ModuleContainerMASK rb_ModulContainerMask) throws myException {
        return new MyE2_String(this.is_UsedToEdit()?"Bearbeiten eines Datensatzes vom Typ: Verwalten der Bordercrossing-Artikel":"Anzeige eines Datensatzes vom Typ: Verwalten der Bordercrossing-Artikel");
    }
    @Override
    public MyE2_String generate_Meldung4SaveRecord(    RB_ModuleContainerMASK rb_ModulContainerMask) throws myException {
        return new MyE2_String("Datensatzes vom Typ: Verwalten der Bordercrossing-Artikel wurde gespeichert");
    }
    @Override
    public Vector<XX_ActionAgentWhenCloseWindow> generate_WindowCloseActions(RB_ModuleContainerMASK rb_ModulContainerMask,E2_NavigationList navilist) throws myException {
     	Vector<XX_ActionAgentWhenCloseWindow>  v_rueck = new Vector<>();
    	
    	v_rueck.add(new XX_ActionAgentWhenCloseWindow(rb_ModulContainerMask) {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				BOR_ART_LIST_bt_ListToMask.this.get_NaviList()._REBUILD_ACTUAL_SITE("");
			}
		});
    	
        return v_rueck;
   }
    @Override
    public E2_Break4PopupController getBreak4PopupController4Save() throws myException {
        return null;
    }
    @Override
    public E2_Break4PopupController getBreak4PopupController4Cancel() throws myException {
        return null;
    }
}

 
