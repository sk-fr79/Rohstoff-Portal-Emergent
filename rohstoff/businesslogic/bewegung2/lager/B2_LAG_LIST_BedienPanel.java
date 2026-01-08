 
package rohstoff.businesslogic.bewegung2.lager;
  
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Export.EXP_popup_menue_exporter;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonAttachmentUseOnToOfNavigationList;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
 
 
public class B2_LAG_LIST_BedienPanel extends E2_Grid {
    
    private B2_LAG_LIST_Selector  B2_ATOM_LIST_Selector = null;
    
    
    //zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   tpHashMap = null;
    
    //sammelt alle im Falle des maskeneinsatzes disabled/enabled zu setzen
    private VEK<MyE2IF__Component>  toDisableAtMask = new VEK<MyE2IF__Component>();
    private B2_LAG_LIST_DATASEARCH    dataSearch = null;
    
    
    public B2_LAG_LIST_DATASEARCH getDataSearch() {
		return dataSearch;
	}
    
    public B2_LAG_LIST_BedienPanel(RB_TransportHashMap  p_params) throws myException     {
        super();
        this.setSize(2);
        
        this.tpHashMap = p_params;
        this.tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_BEDIENPANEL,this);
               
        E2_Grid grid4Components = new E2_Grid()._s(8);
        
        this.B2_ATOM_LIST_Selector = new B2_LAG_LIST_Selector(tpHashMap);
        this._a(B2_ATOM_LIST_Selector,   	new RB_gld()._ins(0,2,10,2)._span(2)._left_mid());
        this._a(grid4Components,   							new RB_gld()._ins(0,2,10,2)._left_mid());
        this._a(this.dataSearch = new B2_LAG_LIST_DATASEARCH(tpHashMap), 	new RB_gld()._ins(0,2,10,2)._left_mid());
        
        grid4Components._a(new E2_ButtonToSelectVisibleListColumns_and_other_settings(this.tpHashMap.getNavigationList()),  new RB_gld()._ins(2,2,20,2)._left_mid());
//        grid4Components._a((Component)this.toDisableAtMask._ar(new B2_LAG_LIST_bt_ListToMask(true,tpHashMap)),   new RB_gld()._ins(2,2,10,2)._left_mid());
//        grid4Components._a(new B2_LAG_LIST_bt_ListToMask(false,tpHashMap),   new RB_gld()._ins(2,2,10,2)._left_mid());
//        
//        grid4Components._a((Component)this.toDisableAtMask._ar(new B2_LAG_LIST_bt_New(tpHashMap)),  		new RB_gld()._ins(2,2,10,2)._left_mid());
//        grid4Components._a((Component)this.toDisableAtMask._ar(new B2_LAG_LIST_bt_Copy(tpHashMap)),   	new RB_gld()._ins(2,2,10,2)._left_mid());
//        grid4Components._a((Component)this.toDisableAtMask._ar(new B2_LAG_LIST_bt_multiDelete(tpHashMap)),new RB_gld()._ins(2,2,10,2)._left_mid());
        
        //upload-button
        E2_ButtonAttachmentUseOnToOfNavigationList uploadBt = new E2_ButtonAttachmentUseOnToOfNavigationList()._setNaviList(this.tpHashMap.getNavigationList());
        uploadBt.add_GlobalValidator(B2_LAG_VALIDATORS.ATTACHMENT.getValidator());
        grid4Components._a(uploadBt,new RB_gld()._ins(2,2,10,2)._left_mid());
        
        grid4Components._a(new ownExporter(this.tpHashMap.getNavigationList()),					new RB_gld()._ins(10, 2, 10, 2)._left_mid());
        
    }
  
    public B2_LAG_LIST_Selector get_list_Selector() 
    {
        return B2_ATOM_LIST_Selector;
    }
    
    
    //exporter-button
    private class ownExporter extends EXP_popup_menue_exporter {
    	/**
		 * @param p_navigationlist
		 */
		public ownExporter(E2_NavigationList p_navigationlist) {
			super(p_navigationlist);
		}
		
    }
    
    @Override
    public void set_bEnabled_For_Edit(boolean bEnabled) throws myException {
		for (MyE2IF__Component c: this.toDisableAtMask) {
			c.set_bEnabled_For_Edit(bEnabled);
		}
	}
}
 
 
