 
package panter.gmbh.Echo2.basic_tools.emailv2;
  
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Export.EXP_popup_menue_exporter;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.MODULE_REPORTLISTS.REP__POPUP_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
 
 
public class EM2_LIST_BedienPanel extends E2_Grid {
    
    private EM2_LIST_Selector  EMAIL_SEND_LIST_Selector = null;
    
    
    //zentrale hashmap fuer transport von infos
    private EM2_TransportHashMap   tpHashMap = null;
    
    //sammelt alle im Falle des maskeneinsatzes disabled/enabled zu setzen
    private VEK<MyE2IF__Component>  toDisableAtMask = new VEK<MyE2IF__Component>();
    private EM2_LIST_DATASEARCH    dataSearch = null;
    
    
    public EM2_LIST_DATASEARCH getDataSearch() {
		return dataSearch;
	}
    
    public EM2_LIST_BedienPanel(EM2_TransportHashMap  p_params) throws myException     {
        super();
        this.setSize(2);
        
        this.tpHashMap = p_params;
        this.tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_BEDIENPANEL,this);

        boolean allowNew = tpHashMap.getAllowNew();
        boolean allowEdit = tpHashMap.getAllowEdit();
        boolean allowDelete = tpHashMap.getAllowDelete();
        boolean allowCopy = tpHashMap.getAllowCopy();
        
        
        boolean showForAllEmails = (tpHashMap.getTable()==null);

        E2_Grid grid4Components = new E2_Grid()._s(10);
        
      	this.EMAIL_SEND_LIST_Selector = new EM2_LIST_Selector(tpHashMap);
        
      	//bei spezialisierten keinen selektor anzeigen
      	if (showForAllEmails) {
      		this._a(EMAIL_SEND_LIST_Selector,   	new RB_gld()._ins(0,2,10,2)._span(2)._left_mid());
      	}
      	
        this._a(grid4Components,   							new RB_gld()._ins(0,2,10,2)._left_mid());
        this._a(this.dataSearch = new EM2_LIST_DATASEARCH(tpHashMap), 	new RB_gld()._ins(0,2,10,2)._left_mid());
        
        grid4Components._a(new E2_ButtonToSelectVisibleListColumns_and_other_settings(this.tpHashMap.getNavigationList()),  new RB_gld()._ins(2,2,20,2)._left_mid());
        if (allowEdit) {
        	grid4Components._a((Component)this.toDisableAtMask._ar(new EM2_LIST_bt_ListToMask(true,tpHashMap)),   new RB_gld()._ins(2,2,10,2)._left_mid());
        }
        grid4Components._a(new EM2_LIST_bt_ListToMask(false,tpHashMap),   new RB_gld()._ins(2,2,10,2)._left_mid());
        
        if (allowNew) {
        	grid4Components._a((Component)this.toDisableAtMask._ar(new EM2_LIST_bt_New(tpHashMap)),  		new RB_gld()._ins(2,2,10,2)._left_mid());
        }
        if (allowCopy) {
        	grid4Components._a((Component)this.toDisableAtMask._ar(new EM2_LIST_bt_Copy(tpHashMap)),   	new RB_gld()._ins(2,2,10,2)._left_mid());
        }

        if (allowDelete) {
        	grid4Components._a((Component)this.toDisableAtMask._ar(new EM2_LIST_bt_multiDelete(tpHashMap)),new RB_gld()._ins(2,2,10,2)._left_mid());
        }
         
        //benutzerdefinierte Reports anzeigen  
        grid4Components._a((Component)new REP__POPUP_Button(E2_MODULNAME_ENUM.MODUL.EMAIL_SEND_V2_LIST.get_callKey(),this.tpHashMap.getNavigationList()),new RB_gld()._ins(2,2,10,2)._left_mid());
 
        grid4Components._a(new ownExporter(this.tpHashMap.getNavigationList()),					new RB_gld()._ins(10, 2, 10, 2)._left_mid());
        grid4Components._s(grid4Components.getComponentCount());
    }
    
    
    
  
    public EM2_LIST_Selector get_list_Selector() 
    {
        return EMAIL_SEND_LIST_Selector;
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
 
 
