 
package panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_V2;
  
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Export.EXP_popup_menue_exporter;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonAttachmentUseOnToOfNavigationList;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
 
 
public class HELP2_LIST_BedienPanel extends E2_Grid {
    
    private HELP2_LIST_Selector  listSelektor = null;
    
    
    //zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   tpHashMap = null;
    
    //sammelt alle im Falle des maskeneinsatzes disabled/enabled zu setzen
    private VEK<MyE2IF__Component>  toDisableAtMask = new VEK<MyE2IF__Component>();
    private HELP2_LIST_DATASEARCH    dataSearch = null;
    
    private RB_cb   				cbShowImages = new RB_cb(S.ms("Bilder anzeigen"))
    												._setSelected()
    												._aaa(new OwnActionBildAnBildAus())._ttt(S.ms("Bilder und Illustrationen in der Liste einblenden/ausblenden !"));
    
    public HELP2_LIST_DATASEARCH getDataSearch() {
		return dataSearch;
	}
    
    public HELP2_LIST_BedienPanel(RB_TransportHashMap  p_params) throws myException     {
        super();
        this._setSize(600,800);
        
        this.tpHashMap = p_params;
        this.tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_BEDIENPANEL,this);

        ((HELP2__TransportHashMap) p_params).setCheckBoxShowImages(this.cbShowImages);
        
        E2_Grid grid4Components = new E2_Grid()._s(12);
        this.listSelektor = new HELP2_LIST_Selector(tpHashMap);

        this._a(listSelektor,   	new RB_gld()._ins(0,2,10,2)._span(2)._left_mid());
        this._a(grid4Components,  	new RB_gld()._ins(0,2,10,2)._left_mid());
        this._a(this.dataSearch = new HELP2_LIST_DATASEARCH(tpHashMap), 	new RB_gld()._ins(0,2,10,2)._left_mid());
        
        grid4Components._a(new E2_ButtonToSelectVisibleListColumns_and_other_settings(this.tpHashMap.getNavigationList()),  new RB_gld()._ins(2,2,20,2)._left_mid());
        grid4Components._a((Component)this.toDisableAtMask._ar(new HELP2_LIST_btListToMask(true,tpHashMap)),   new RB_gld()._ins(2,2,10,2)._left_mid());
        grid4Components._a(new HELP2_LIST_btListToMask(false,tpHashMap),   new RB_gld()._ins(2,2,10,2)._left_mid());
        
        grid4Components._a((Component)this.toDisableAtMask._ar(new HELP2_LIST_btNew(tpHashMap)),  		new RB_gld()._ins(2,2,10,2)._left_mid());
        grid4Components._a((Component)this.toDisableAtMask._ar(new HELP2_LIST_btCopy(tpHashMap)),   	new RB_gld()._ins(2,2,10,2)._left_mid());
        grid4Components._a((Component)this.toDisableAtMask._ar(new HELP2_LIST_btMultiDelete(tpHashMap)),new RB_gld()._ins(2,2,10,2)._left_mid());
        
        grid4Components._a((Component)this.toDisableAtMask._ar(new HELP2_LIST_btExportToSql()._setNaviList(this.tpHashMap.getNavigationList())),new RB_gld()._ins(2,2,10,2)._left_mid());
        
        grid4Components._a((Component)this.toDisableAtMask._ar(new HELP2_LIST_btPrintHandBook()._setNaviList(this.tpHashMap.getNavigationList())),new RB_gld()._ins(2,2,10,2)._left_mid());
        
        //upload-button
        E2_ButtonAttachmentUseOnToOfNavigationList uploadBt = new E2_ButtonAttachmentUseOnToOfNavigationList()._setNaviList(this.tpHashMap.getNavigationList());
        uploadBt.add_GlobalValidator(ENUM_VALIDATION.HILFETEXT_ATTACHMENT.getValidator());
        grid4Components._a(uploadBt,new RB_gld()._ins(2,2,10,2)._left_mid());
        
        grid4Components._a(new ownExporter(this.tpHashMap.getNavigationList()),					new RB_gld()._ins(10, 2, 10, 2)._left_mid());
        grid4Components._a(this.cbShowImages,													new RB_gld()._ins(10, 2, 10, 2)._left_mid());
        
    }
  
    public HELP2_LIST_Selector get_list_Selector() 
    {
        return listSelektor;
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
    
    
    private class OwnActionBildAnBildAus extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			tpHashMap.getNavigationList()._REBUILD_ACTUAL_SITE("");
		}
    }
    
    
}
 
 
