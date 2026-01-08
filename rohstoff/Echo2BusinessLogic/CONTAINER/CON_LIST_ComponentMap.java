 
package rohstoff.Echo2BusinessLogic.CONTAINER;
import com.fasterxml.jackson.databind.node.ContainerNode;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_Factory4Records;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.basics4project.DB_ENUMS.CONTAINER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_CONTAINER;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;


public class CON_LIST_ComponentMap extends E2_ComponentMAP  {
	
	// Selektor hier ablegen, damit man auf die Inhalte des Selektors zugreifen kann
	CON_LIST_Selector  _selector = null;
	
	
    public CON_LIST_ComponentMap(CON_LIST_Selector selector) throws myException  {
        super(new CON_LIST_SqlFieldMAP());
        
        _selector = selector;
        
        SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
        
        this.add_Component(CON_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,    new E2_CheckBoxForList(),new MyE2_String("?"));
        this.add_Component(CON_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,    new E2_ButtonListMarker(),new MyE2_String("?"));

        //hier kommen die Felder  
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(CONTAINER.id_container),true),     new MyE2_String("ID Container"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(CONTAINER.container_nr),true),     new MyE2_String("Container-Nr"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(CONTAINER.tara),true),     new MyE2_String("Tara-Gewicht (kg)"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(CONTAINER.bemerkung),true),     new MyE2_String("Bemerkung"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(CONTAINER.id_containertyp),true),     new MyE2_String("ID Containertyp"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(CONTAINER.uvv_datum),true),     new MyE2_String("UVV-Datum"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(CONTAINER.aktiv),true),     new MyE2_String("Aktiv"));
        
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(CONTAINER.tara_korrektur),true),     new MyE2_String("Tara-Korrektur"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(CONTAINER.datum_korrektur),true),     new MyE2_String("Korrektur-Datum"));


        
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_("CT_KURZBEZEICHNUNG")), new MyE2_String("Containertyp"));

        this._setLineWrapListHeader(true 
                                  ,CONTAINER.bemerkung.fn()
                                  ,CONTAINER.container_nr.fn()
                                  ,CONTAINER.id_container.fn()
                                  ,CONTAINER.id_containertyp.fn()
                                  ,CONTAINER.tara.fn()
                                  ,CONTAINER.uvv_datum.fn()
                                  ,CONTAINER.aktiv.fn()
                                  ,CONTAINER.tara_korrektur.fn()
                                  ,CONTAINER.datum_korrektur.fn()
                                  ,"CT_KURZBEZEICHNUNG"
        );
        
        RB_gld gldElementLeftTop = 	new RB_gld()._left_top()._ins(2,4,2,2)._col(new E2_ColorBase());
        RB_gld gldElementRightTop  = 	new RB_gld()._right_top()._ins(2,4,2,2)._col(new E2_ColorBase());
        
//       	this._setLayoutElements(gldElementLeftTop
//                                 ,CONTAINER.bemerkung.fn()
//                                 ,CONTAINER.container_nr.fn()
//                                 ,CONTAINER.id_container.fn()
//                                 ,CONTAINER.id_containertyp.fn()
//                                 ,CONTAINER.tara.fn()
//      	);
//      	
//      	RB_gld gldTitelCenter = 	new RB_gld()._center_top()._ins(2,4,2,2)._col(new E2_ColorDark());
//       	this._setLayoutElements(gldTitelCenter
//                                 ,CONTAINER.bemerkung.fn()
//                                 ,CONTAINER.container_nr.fn()
//                                 ,CONTAINER.id_container.fn()
//                                 ,CONTAINER.id_containertyp.fn()
//                                 ,CONTAINER.tara.fn()
//      	);
      	
        this.set_oSubQueryAgent(new CON_LIST_FORMATING_Agent());
        	
        this.set_Factory4Records(new factory4Records());
    }
    
    
    
    private class factory4Records extends E2_ComponentMAP_Factory4Records {
        @Override
        public MyRECORD_IF_RECORDS get_RECORD(String cID_MAINTABLE) throws myException {
            return new RECORD_CONTAINER(cID_MAINTABLE);
        }
        
    }
    
    
    /**
     * das Selektor-Objekt der Liste
     * @author manfred
     * @date 06.12.2017
     *
     * @return
     */
    public CON_LIST_Selector getListSelector(){
    	return _selector;
    }
    
}
 
