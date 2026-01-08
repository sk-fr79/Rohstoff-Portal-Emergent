 
package rohstoff.businesslogic.bewegung2.list.listComponents;
  
  
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_IF_Rec21;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.businesslogic.bewegung2.list.B2_ListBtListToMaskAbstract;
import rohstoff.businesslogic.bewegung2.list.B2_TransportHashMapAddons;
import rohstoff.businesslogic.bewegung2.mask.B2_MaskModulContainer;
  
public class B2_ListBtListToMaskInListRow extends B2_ListBtListToMaskAbstract {
    
    public B2_ListBtListToMaskInListRow(boolean bEdit, RB_TransportHashMap  p_tpHashMap) {
        super(bEdit,p_tpHashMap);
        RB_cb cbSwitchGridBorderOn = new RB_cb();
        cbSwitchGridBorderOn._aaa(
        		()->{ 
        			VEK<E2_Grid> fieldContainers = (VEK<E2_Grid>) getTransportHashMap().getFromExtender(B2_TransportHashMapAddons.MASK_BASIC_GRID);
        			for (E2_Grid g: fieldContainers) {
        			if (cbSwitchGridBorderOn.isSelected()) {
        				g._bo_red();
        			} else {
        				g._bo_no();
        			}
        			}
        		}
        		);
        
        this.get_grid4MaskExternal()._a(cbSwitchGridBorderOn);
    }
    @Override
    public RB_ModuleContainerMASK generateMaskContainer() throws myException {
    	this.getTransportHashMap()._setRBModulContainerMask(new B2_MaskModulContainer(this.getTransportHashMap()));
        return this.getTransportHashMap().getRBModulContainerMask();
    }
  
    @Override
    protected MyE2_String getTextForViewOnlyOnEdit() {
    	return S.ms("Der Bewegungssatz ist storniert: Bearbeiten ist nicht möglich !");
    }
 
    
    
    @Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
	}
	
   
	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		B2_ListBtListToMaskInListRow copy= new B2_ListBtListToMaskInListRow(this.isUsedToEdit(), this.getTransportHashMap());
		copy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(copy));
		return copy;
	}
   
	/**
	 * spezielle methode zum ein permanent enables object doch noch disablen zu koennen
	 * @param enabled
	 * @throws myException
	 */
	public void setEnabledForEditOfSuperClass(boolean enabled) throws myException {
		super.set_bEnabled_For_Edit(enabled);
	}

	
	@Override
	public VEK<String> getIdsSelected() {
		try {
			return new VEK<String>()._a(""+((E2_ComponentMAP_IF_Rec21)this.EXT().get_oComponentMAP()).getRec21().getId());
		} catch (myException e) {
			e.printStackTrace();
			return new VEK<String>();
		}
	}
	
   
    
}
 
 
