 
package rohstoff.businesslogic.StammDaten.FuhrenKostenTypen;
  
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_Factory4Records;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.basics4project.DB_ENUMS.FUHREN_KOSTEN_TYP;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FUHREN_KOSTEN_TYP;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
  
public class FKT_LIST_ComponentMap extends E2_ComponentMAP  {
    public FKT_LIST_ComponentMap() throws myException  {
        super(new FKT_LIST_SqlFieldMAP());
        
        SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
        
        this.add_Component(FKT_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,    new E2_CheckBoxForList(),new MyE2_String("?"));
        this.add_Component(FKT_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,    new E2_ButtonListMarker(),new MyE2_String("?"));
        //hier kommen die Felder  
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(FUHREN_KOSTEN_TYP.id_fuhren_kosten_typ),true), new MyE2_String("ID"));
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(FUHREN_KOSTEN_TYP.betrifft_zoll)),     			new MyE2_String("Zoll?"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(FUHREN_KOSTEN_TYP.kurztext_uebersicht),true),  new MyE2_String("Kurz"));
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(FUHREN_KOSTEN_TYP.neutral),true),     				new MyE2_String("Neutral"));
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(FUHREN_KOSTEN_TYP.speditionsrechnung),true),     	new MyE2_String("Speditions-Rech."));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(FUHREN_KOSTEN_TYP.text4benutzer),true),     	new MyE2_String("Infotext"));
 
        this._setLineWrapListHeader(true 
                                  ,FUHREN_KOSTEN_TYP.betrifft_zoll.fn()
                                  ,FUHREN_KOSTEN_TYP.id_fuhren_kosten_typ.fn()
                                  ,FUHREN_KOSTEN_TYP.kurztext_uebersicht.fn()
                                  ,FUHREN_KOSTEN_TYP.neutral.fn()
                                  ,FUHREN_KOSTEN_TYP.speditionsrechnung.fn()
                                  ,FUHREN_KOSTEN_TYP.text4benutzer.fn()
        );
        
       	this._setLayoutElements(	new RB_gld()._center_top()._ins(2,4,2,2)._col(new E2_ColorBase())
       								,FUHREN_KOSTEN_TYP.betrifft_zoll.fn()
       								,FUHREN_KOSTEN_TYP.id_fuhren_kosten_typ.fn()
       								,FUHREN_KOSTEN_TYP.kurztext_uebersicht.fn()
       								,FUHREN_KOSTEN_TYP.neutral.fn()
       								,FUHREN_KOSTEN_TYP.speditionsrechnung.fn()
      	);

       	this._setLayoutElements(	new RB_gld()._left_top()._ins(2,4,2,2)._col(new E2_ColorBase())
       								,FUHREN_KOSTEN_TYP.text4benutzer.fn()
				       				);
      	
       	
       	this._setLayoutTitles( 		new RB_gld()._center_top()._ins(1,2,1,1)._col(new E2_ColorDark())
       								,FUHREN_KOSTEN_TYP.betrifft_zoll.fn()
       								,FUHREN_KOSTEN_TYP.id_fuhren_kosten_typ.fn()
       								,FUHREN_KOSTEN_TYP.kurztext_uebersicht.fn()
       								,FUHREN_KOSTEN_TYP.neutral.fn()
       								,FUHREN_KOSTEN_TYP.speditionsrechnung.fn()
       								);
  
       	this._setLayoutTitles( 		new RB_gld()._left_top()._ins(1,2,1,1)._col(new E2_ColorDark())
									,FUHREN_KOSTEN_TYP.text4benutzer.fn()
									);

       	this._setColExtent(new Extent(100)
			       					, FUHREN_KOSTEN_TYP.betrifft_zoll.fn()
			       					, FUHREN_KOSTEN_TYP.id_fuhren_kosten_typ.fn()
			       					, FUHREN_KOSTEN_TYP.kurztext_uebersicht.fn()
			       					, FUHREN_KOSTEN_TYP.neutral.fn()
			       					);
     
       	this._setColExtent(new Extent(250)
									, FUHREN_KOSTEN_TYP.text4benutzer.fn()
									);

       	
       	
        this.set_oSubQueryAgent(new FKT_LIST_FORMATING_Agent());
        	
        this.set_Factory4Records(new factory4Records());
    }
    private class factory4Records extends E2_ComponentMAP_Factory4Records {
        @Override
        public MyRECORD_IF_RECORDS get_RECORD(String cID_MAINTABLE) throws myException {
            return new RECORD_FUHREN_KOSTEN_TYP(cID_MAINTABLE);
        }
        
    }
    
    
}
 
