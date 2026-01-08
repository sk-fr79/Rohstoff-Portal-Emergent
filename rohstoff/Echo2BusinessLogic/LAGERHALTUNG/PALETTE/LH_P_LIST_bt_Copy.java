 
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE;
  
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.E2_Break4PopupController;
import panter.gmbh.Echo2.ActionEventTools.Break4Popup.Breakers.Break4MaskCloseWhenSomethingWasChanged;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator_NG;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.CONTROLLERS_V4.RB_BtV4_NewCopy;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_PALETTE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
  
public class LH_P_LIST_bt_Copy extends RB_BtV4_NewCopy {
	
    public LH_P_LIST_bt_Copy(RB_TransportHashMap  p_tpHashMap) {
        super();
        this.add_GlobalValidator(LH_P_VALIDATORS.NEW.getValidator());
        this._setTransportHashMap(p_tpHashMap);
        this.add_GlobalValidator(new ownValidator());
    }
 
    
	@Override
	public RB_ModuleContainerMASK generateMaskContainer() throws myException {
        this.getTtransportHashMap().put(RB_TransportHashMap_ENUM.MODULCONTAINER_MASK,new LH_P_MASK_MaskModulContainer(this.getTtransportHashMap()));
        return this.getTtransportHashMap().getRBModulContainerMask();
	}
 	
 	
	@Override
	public RB_DataobjectsCollector generateDataObjects4MaskCopy(Long idToCopy) throws myException {
 
		if (idToCopy==null) {
			throw new myException("Cannot copy null-id");
		}
		
		Rec21 oRec = new Rec21(_TAB.lager_palette)._fill_id(idToCopy);
		if(S.isFull(oRec.getUfs(LAGER_PALETTE.id_vpos_tpa_fuhre_aus, ""))) {
			throw new myException("Ausgebucht Palette kann nicht kopiert sein");
		}
		
        this.getTtransportHashMap().put(RB_TransportHashMap_ENUM.MASK_DATAOBJECTS_COLLECTOR,new LH_P_MASK_DataObjectCollector(this.getTtransportHashMap(), idToCopy.toString(), RB__CONST.MASK_STATUS.NEW_COPY));
        return this.getTtransportHashMap().getMaskDataObjectsCollector();
	}
 	
 	
 	
	@Override
	public RB_DataobjectsCollector generateDataObjects4Copy(String idToCopy) throws myException {
 
		MyLong l = new MyLong(idToCopy);
 		
		if (l.isNotOK()) {
			throw new myException("Cannot copy null-id");
		}
        this.getTtransportHashMap().put(RB_TransportHashMap_ENUM.MASK_DATAOBJECTS_COLLECTOR,
        			new LH_P_MASK_DataObjectCollector(this.getTtransportHashMap(), idToCopy.toString(), RB__CONST.MASK_STATUS.EDIT));
        return this.getTtransportHashMap().getMaskDataObjectsCollector();
	}
 
 	
   @Override
   public E2_Break4PopupController getBreak4PopupController4Save() throws myException {
       return null;
   }
   
   
   @Override
   public E2_Break4PopupController getBreak4PopupController4Cancel() throws myException {
       return new E2_Break4PopupController()._registerBreak(
    		   new Break4MaskCloseWhenSomethingWasChanged(
    				   LH_P_LIST_bt_Copy.this.getTtransportHashMap().getRBModulContainerMask().rb_FirstAndOnlyComponentMapCollector(), null));
   }
 
   private class ownValidator extends XX_ActionValidator_NG{
		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();
			boolean ausgebucht = false;

			VEK<String> vSelectedIds = new VEK<String>()._a(getTtransportHashMap().getNavigationList().get_vSelectedIDs_Unformated());

			if(vSelectedIds.size()==0) {
				mv._addAlarm("Sie müssen mindestens eine Palette auswahlen.");
			}else {
				for(String selectedId: vSelectedIds) {
					Rec21 recPalette = new Rec21(_TAB.lager_palette)._fill_id(selectedId);
					ausgebucht = 
							S.isFull(recPalette.get_ufs_dbVal(LAGER_PALETTE.id_vpos_tpa_fuhre_aus, "")) ||
							recPalette.is_yes_db_val(LAGER_PALETTE.ausbuchung_hand);
					
					if(ausgebucht == true) {
						mv._addAlarm("Die Palette kann nicht kopiert werden, weil sie ist schon ausgebucht !");
					}
				}
			}

			return mv;
		}

	}
}
 
 
