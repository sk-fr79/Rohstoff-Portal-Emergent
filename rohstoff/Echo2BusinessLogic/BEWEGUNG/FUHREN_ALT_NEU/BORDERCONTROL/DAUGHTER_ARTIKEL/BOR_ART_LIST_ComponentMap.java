 
package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL;
  
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
import panter.gmbh.basics4project.DB_ENUMS.AH7_PROFIL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_AH7_PROFIL;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL.BOR_ART_CONST.BOR_ART_NAMES;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL.BOR_ART_CONST.PARAMHASH;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL.BOR_ART_CONST.BOR_ART_PARAMS;
import panter.gmbh.basics4project.DB_ENUMS.BORDERCROSSING_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BORDERCROSSING_ARTIKEL;
  
public class BOR_ART_LIST_ComponentMap extends E2_ComponentMAP  {
    //zentrale hashmap fuer transport von infos
    private PARAMHASH   params = null;
    
    // prefix für die join-Tabellen
    private String      prefix_art = "ART_";
    
    public BOR_ART_LIST_ComponentMap(PARAMHASH  p_params) throws myException  {
        super(new BOR_ART_LIST_SqlFieldMAP(p_params));
        
        this.params = p_params;        
        
        SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
        
        this.add_Component(BOR_ART_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,    new E2_CheckBoxForList(),new MyE2_String("?"));
        this.add_Component(BOR_ART_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,    new E2_ButtonListMarker(),new MyE2_String("?"));
        //hier kommen die Felder  
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BORDERCROSSING_ARTIKEL.id_bordercrossing_artikel),true),     new MyE2_String("ID"));
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BORDERCROSSING_ARTIKEL.id_bordercrossing),true),     new MyE2_String("ID Grenzübertritt"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BORDERCROSSING_ARTIKEL.id_artikel),true),     new MyE2_String("ID Artikel"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(prefix_art + ARTIKEL.anr1.fn()),true),     new MyE2_String("ANR1"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(prefix_art + ARTIKEL.artbez1.fn()),true),     new MyE2_String("Artikelbez.1"));
        
        
//        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BORDERCROSSING_ARTIKEL.menge),true),     new MyE2_String("min. Menge"));
        
        
        this._setLineWrapListHeader(true 
                                  ,BORDERCROSSING_ARTIKEL.id_artikel.fn()
                                  ,BORDERCROSSING_ARTIKEL.id_bordercrossing_artikel.fn()
                                  ,prefix_art + ARTIKEL.anr1.fn()
                                  ,prefix_art + ARTIKEL.artbez1.fn()   
        );

        
        RB_gld gldElementRightTop = 	new RB_gld()._right_top()._ins(1,2,5,1)._col(new E2_ColorBase());
       	this._setLayoutElements(gldElementRightTop
       			,BORDERCROSSING_ARTIKEL.id_artikel.fn()
       			,BORDERCROSSING_ARTIKEL.id_bordercrossing_artikel.fn()
//                                 ,BORDERCROSSING_ARTIKEL.menge.fn()
      	);
      	
      	RB_gld gldTitelLeftTop = 	new RB_gld()._left_top()._ins(1,2,2,1)._col(new E2_ColorBase());
       	this._setLayoutElements(gldTitelLeftTop
                                 ,prefix_art + ARTIKEL.anr1.fn()
                                 ,prefix_art + ARTIKEL.artbez1.fn()
      	);
      	
       	this._setColExtent(new Extent(400), prefix_art + ARTIKEL.artbez1.fn());
       	this._setColExtent(new Extent(100), prefix_art + ARTIKEL.anr1.fn());
//       	this._setWidth(400, prefix_art + ARTIKEL.artbez1.fn());
       	
       	
        this.set_oSubQueryAgent(new BOR_ART_LIST_FORMATING_Agent(this.params));
        this.set_Factory4Records(new factory4Records());
    }
    
    
    private class factory4Records extends E2_ComponentMAP_Factory4Records {
        @Override
        public MyRECORD_IF_RECORDS get_RECORD(String cID_MAINTABLE) throws myException {
            return new RECORD_BORDERCROSSING_ARTIKEL(cID_MAINTABLE);
        }
        
    }
    
    
}
 
