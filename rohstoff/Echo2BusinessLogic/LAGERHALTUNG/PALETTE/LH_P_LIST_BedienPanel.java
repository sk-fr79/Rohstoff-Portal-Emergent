 
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE;
  
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Export.EXP_popup_menue_exporter;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.MODULE_REPORTLISTS.REP__POPUP_Button;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonAttachmentUseOnToOfNavigationList;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
 
 
public class LH_P_LIST_BedienPanel extends E2_Grid {
    
    private LH_P_LIST_Selector  LAGER_PALETTE_LIST_Selector = null;
    
    
    //zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   tpHashMap = null;
    
    //sammelt alle im Falle des maskeneinsatzes disabled/enabled zu setzen
    private VEK<MyE2IF__Component>  toDisableAtMask = new VEK<MyE2IF__Component>();
    private LH_P_LIST_DATASEARCH    dataSearch = null;
    
    
    public LH_P_LIST_DATASEARCH getDataSearch() {
		return dataSearch;
	}
    
    public LH_P_LIST_BedienPanel(RB_TransportHashMap  p_params) throws myException     {
        super();
        this.setSize(2);
        
        this.tpHashMap = p_params;
        this.tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_BEDIENPANEL,this);
               
        E2_Grid grid4Components = new E2_Grid()._s(10);
        
        this.LAGER_PALETTE_LIST_Selector = new LH_P_LIST_Selector(tpHashMap);
        this._a(LAGER_PALETTE_LIST_Selector,   										new RB_gld()._ins(0,2,10,2)._span(2)._left_mid());
        this._a(grid4Components,   													new RB_gld()._ins(0,2,10,2)._left_mid());
        this._a(this.dataSearch = new LH_P_LIST_DATASEARCH(tpHashMap), 				new RB_gld()._ins(0,2,10,2)._left_mid());
        
        E2_ButtonToSelectVisibleListColumns_and_other_settings select_visible = new E2_ButtonToSelectVisibleListColumns_and_other_settings(this.tpHashMap.getNavigationList());

        E2_ButtonAttachmentUseOnToOfNavigationList uploadBt = new E2_ButtonAttachmentUseOnToOfNavigationList()._setNaviList(this.tpHashMap.getNavigationList());
        uploadBt.add_GlobalValidator(LH_P_VALIDATORS.ATTACHMENT.getValidator());
        REP__POPUP_Button list_knopf = new REP__POPUP_Button(E2_MODULNAME_ENUM.MODUL.LAGER_PALETTE_LIST.get_callKey(),this.tpHashMap.getNavigationList());
        
        grid4Components
        ._a(select_visible,  														new RB_gld()._ins(2,2,20,2)._left_mid())
        ._a((Component)this.toDisableAtMask._ar(new LH_P_LIST_bt_New(tpHashMap)),  	new RB_gld()._ins(0,2,10,2)._left_mid())
        ._a((Component)this.toDisableAtMask._ar(new LH_P_LIST_bt_Copy(tpHashMap)),  new RB_gld()._ins(0,2,10,2)._left_mid())
        ._a(new LH_P_LIST_bt_Ausbuchung(tpHashMap), 								new RB_gld()._ins(0,2,10,2)._left_mid())
        ._a(new LH_P_LIST_btAusbuchungRueckgaengig(tpHashMap), 						new RB_gld()._ins(0,2,10,2)._left_mid())
        
        ;
        grid4Components
        ._a(new LH_P_LIST_bt_move_box2box(tpHashMap), 								new RB_gld()._ins(0,2,10,2)._left_mid())
        ._a(new LH_P_LIST_bt_DruckEtikette(tpHashMap), 								new RB_gld()._ins(0,2,10,2)._left_mid())
        ._a(uploadBt,																new RB_gld()._ins(0,2,10,2)._left_mid())
        ._a(list_knopf, 															new RB_gld()._ins(10, 2, 10, 2)._left_mid())
        ._a(new ownExporter(this.tpHashMap.getNavigationList()),					new RB_gld()._ins(10, 2, 10, 2)._left_mid())
        ;
        //upload-button
        
        
        
        
    }
  
    public LH_P_LIST_Selector get_list_Selector() 
    {
        return LAGER_PALETTE_LIST_Selector;
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
 
 
