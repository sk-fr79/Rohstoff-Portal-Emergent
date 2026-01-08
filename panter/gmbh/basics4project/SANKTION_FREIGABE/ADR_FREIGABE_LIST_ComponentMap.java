package panter.gmbh.basics4project.SANKTION_FREIGABE;
  
import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_Factory4Records;
import panter.gmbh.Echo2.ListAndMask.E2_QUERYAGENT_MarkiereInaktiveInNaviList;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.basics4project.DB_ENUMS.SANKTION_PRUEFUNG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_SANKTION_PRUEFUNG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
  
public class ADR_FREIGABE_LIST_ComponentMap extends E2_ComponentMAP  {
    public ADR_FREIGABE_LIST_ComponentMap() throws myException  {
        super(new ADR_FREIGABE_LIST_SqlFieldMAP());
        
        SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
        
        this.add_Component(ADR_FREIGABE_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,    	new E2_CheckBoxForList(),new MyE2_String("?"));
        this.add_Component(ADR_FREIGABE_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));
      
        //hier kommen die Felder  
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(SANKTION_PRUEFUNG.id_adresse),true),     		S.ms("Adresse Id"));
        
        this.add_Component(ADR_FREIGABE_CONST.LIST_KEY.ADRESSE_DETAIL.k(), new ADR_FREIGABE_COMP_LIST_AdresseDetail_und_sprung(),S.ms("Adresse"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(SANKTION_PRUEFUNG.geprueft_am),true), 			S.ms("Geprüft am"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(SANKTION_PRUEFUNG.freigabe), true), 			S.ms("Freigabe"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(SANKTION_PRUEFUNG.freigabe_datum),true),     	S.ms("Freigabe Datum"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(SANKTION_PRUEFUNG.freigabe_user), true),		S.ms("Freigabe Benutzer"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(SANKTION_PRUEFUNG.freigabe_bemerkung),true), 	S.ms("Bemerkung"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(SANKTION_PRUEFUNG.aktiv),true), 				S.ms("Aktiv"));
        
        this._setLineWrapListHeader(true 
                                  ,SANKTION_PRUEFUNG.freigabe.fn()
                                  ,SANKTION_PRUEFUNG.freigabe_bemerkung.fn()
                                  ,SANKTION_PRUEFUNG.geprueft_am.fn()
                                  ,SANKTION_PRUEFUNG.freigabe_datum.fn()
                                  ,SANKTION_PRUEFUNG.freigabe_user.fn()
                                  ,SANKTION_PRUEFUNG.id_adresse.fn()
        );
        
        RB_gld gldElementLeft = 	new RB_gld()._left_top()._ins(2,4,2,2)._col(new E2_ColorBase());
        this._setLayoutElements(gldElementLeft
                ,SANKTION_PRUEFUNG.freigabe_user.fn()
                ,SANKTION_PRUEFUNG.id_adresse.fn()
                ,SANKTION_PRUEFUNG.freigabe_bemerkung.fn()
        		);
        
        RB_gld gldElementCenter = 	new RB_gld()._center_top()._ins(2,4,2,2)._col(new E2_ColorBase());
        this._setLayoutElements(gldElementCenter
                                 ,SANKTION_PRUEFUNG.freigabe.fn()
                                 ,SANKTION_PRUEFUNG.geprueft_am.fn()
                                 ,SANKTION_PRUEFUNG.freigabe_datum.fn()
      	);
      	
        RB_gld gldTiteltLeft = 	new RB_gld()._left_top()._ins(2)._col(new E2_ColorBase());
        this._setLayoutElements(gldTiteltLeft
                ,SANKTION_PRUEFUNG.freigabe_user.fn()
                ,SANKTION_PRUEFUNG.id_adresse.fn()
                ,SANKTION_PRUEFUNG.freigabe_bemerkung.fn()
        		);
        
      	RB_gld gldTitelCenter = 	new RB_gld()._center_top()._ins(2)._col(new E2_ColorBase());
       	this._setLayoutElements(gldTitelCenter
                                 ,SANKTION_PRUEFUNG.freigabe.fn()
                                 ,SANKTION_PRUEFUNG.freigabe_datum.fn()
                                 ,SANKTION_PRUEFUNG.aktiv.fn()
      	);
      	
        this.set_oSubQueryAgent(new ADR_FREIGABE_LIST_FORMATING_Agent());
        
        this.add_oSubQueryAgent(new E2_QUERYAGENT_MarkiereInaktiveInNaviList("AKTIV",Color.DARKGRAY,Color.BLACK));
        
        this.set_Factory4Records(new factory4Records());
    }
   
    private class factory4Records extends E2_ComponentMAP_Factory4Records {
        @Override
        public MyRECORD_IF_RECORDS get_RECORD(String cID_MAINTABLE) throws myException {
            return new RECORD_SANKTION_PRUEFUNG(cID_MAINTABLE);
        }
        
    }
    
    
}
 
