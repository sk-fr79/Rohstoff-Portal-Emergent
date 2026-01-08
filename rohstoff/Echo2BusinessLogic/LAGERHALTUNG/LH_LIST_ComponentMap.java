 
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG;
  
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_Factory4Records;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_BOX;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_PALETTE_BOX;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAGER_BOX;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
  
public class LH_LIST_ComponentMap extends E2_ComponentMAP  {
	
    private RB_TransportHashMap   m_tpHashMap = null;
    
    public LH_LIST_ComponentMap(RB_TransportHashMap  p_tpHashMap) throws myException  {
  
    	super(new LH_LIST_SqlFieldMAP(p_tpHashMap));
        
        this.m_tpHashMap = p_tpHashMap;        
        
        SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
        
        this.add_Component(LH_CONST.LH_NAMES.CHECKBOX_LISTE.db_val(), 	new E2_CheckBoxForList(),new MyE2_String("?"));
        this.add_Component(LH_CONST.LH_NAMES.MARKER_LISTE.db_val(),   	new E2_ButtonListMarker(),new MyE2_String("?"));
  
        this.add_Component(LH_CONST.LH_NAMES.DIRECT_VIEW.db_val(), 		new LH_LIST_bt_ListToMaskInListRow(false, this.m_tpHashMap), S.ms("?"));
        this.add_Component(LH_CONST.LH_NAMES.DIRECT_EDIT.db_val(), 		new LH_LIST_bt_ListToMaskInListRow(true, this.m_tpHashMap), S.ms("?"));

        //hier kommen die Felder  
        this.add_Component(LH_CONST.LH_NAMES.SHOW_BOX_NR.db_val(),		new LH_LIST_COMP_LabelTransportTyp(),     	S.ms("Box Nr."));
        this.add_Component(LH_CONST.LIST_KEY.LH_LIST_DETAIL.k(),		new LH_LIST_COMP_DetailBlock(this.m_tpHashMap), S.ms("Detail"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(LAGER_BOX.id_lager_box), true),    S.ms(LH_READABLE_FIELD_NAME.getReadable(LAGER_BOX.id_lager_box)));

        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(LAGER_BOX.beschreibung),true),     S.ms("Beschreibung"));

        //neu ab 20171025        
        this._setLineWrapListHeader(true 
                                  ,LH_CONST.LH_NAMES.SHOW_BOX_NR.db_val()
                                  ,LAGER_BOX.beschreibung.fn()
                                  ,LH_CONST.LIST_KEY.LH_LIST_DETAIL.k()
        );
      	
      	RB_gld gldTitelCenter = 	new RB_gld()._center_mid()._ins(1,2,1,1)._col(new E2_ColorDark())._center_top();
       	this._setLayoutTitles(gldTitelCenter
//       		 ,LAGER_BOX.id_lager_box.fn()
       		 ,LH_CONST.LH_NAMES.SHOW_BOX_NR.db_val()
//             ,LAGER_BOX.beschreibung.fn()
      	);
    
       	RB_gld gldTitelLeft = 	new RB_gld()._left_mid()._ins(1,2,1,1)._col(new E2_ColorDark())._center_top();
       	this._setLayoutTitles(gldTitelLeft
      			,LH_CONST.LIST_KEY.LH_LIST_DETAIL.k()
       	);
       	
       	RB_gld gldElementCenter = 	new RB_gld()._center_top()._ins(2,4,2,2)._col(new E2_ColorBase())._center_top();
       	this._setLayoutElements(gldElementCenter
//       			,LAGER_BOX.id_lager_box.fn()
       			,LH_CONST.LH_NAMES.SHOW_BOX_NR.db_val()
//       			,LAGER_BOX.beschreibung.fn()
       			);
    	
       	RB_gld gldElementLeft = 	new RB_gld()._left_top()._ins(2,4,2,2)._col(new E2_ColorBase())._center_top();
       	this._setLayoutElements(gldElementLeft
       			,LH_CONST.LIST_KEY.LH_LIST_DETAIL.k()
       	);
        
        this.set_oSubQueryAgent(new LH_LIST_FORMATING_Agent(this.m_tpHashMap));
        	
        this.set_Factory4Records(new factory4Records());
    }
    private class factory4Records extends E2_ComponentMAP_Factory4Records {
        @Override
        public MyRECORD_IF_RECORDS get_RECORD(String cID_MAINTABLE) throws myException {
            return new RECORD_LAGER_BOX(cID_MAINTABLE);
        }
    }
    
  
    
}
 
 
