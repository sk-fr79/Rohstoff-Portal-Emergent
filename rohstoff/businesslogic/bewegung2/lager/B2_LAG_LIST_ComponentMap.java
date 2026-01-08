 
package rohstoff.businesslogic.bewegung2.lager;
  
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_Factory4Records;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_IF_Rec21;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BG_ATOM;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.businesslogic.bewegung2.lager.B2_LAG_CONST.B2_LAG_NAMES;
  
public class B2_LAG_LIST_ComponentMap extends E2_ComponentMAP implements E2_ComponentMAP_IF_Rec21{
	
    private RB_TransportHashMap   m_tpHashMap = null;
    
    public B2_LAG_LIST_ComponentMap(RB_TransportHashMap  p_tpHashMap) throws myException  {
  
    	super(new B2_LAG_LIST_SqlFieldMAP(p_tpHashMap));
        
        this.m_tpHashMap = p_tpHashMap;        
        
        SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
        
        this.add_Component(B2_LAG_NAMES.CHECKBOX_LISTE.db_val(), new E2_CheckBoxForList(),new MyE2_String("?"));
        this.add_Component(B2_LAG_NAMES.MARKER_LISTE.db_val(),   new E2_ButtonListMarker(),new MyE2_String("?"));
       
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_ATOM.id_bg_atom),true),    	S.ms(B2_LAG_READABLE_FIELD_NAME.getReadable(BG_ATOM.id_bg_atom)));

        this.add_Component(new MyE2_DB_CheckBox		(oFM.get(B2_LAG_NAMES.EW_FW.db_val())				,true), S.ms("EW"));
        this.add_Component(new MyE2_DB_CheckBox		(oFM.get(B2_LAG_NAMES.ABRECHENBAR_BESITZ.db_val())	,true), S.ms("Abr."));
        this.add_Component(B2_LAG_NAMES.SHOW_TYPE.db_val(), new B2_LAG_LIST_LabelTransportTyp(), B2_LAG_NAMES.SHOW_TYPE.user_text_ms());
        this.add_Component(new MyE2_DB_Label_INGRID	(oFM.get(BG_VEKTOR.id_bg_vektor.fn())				,true), S.ms("ID Vektor"));

        this.add_Component(new MyE2_DB_Label_INGRID	(oFM.get(B2_LAG_NAMES.STARTLAGER_ID.db_val())	,true), B2_LAG_NAMES.STARTLAGER_ID.user_text_ms());
        this.add_Component(new MyE2_DB_Label_INGRID	(oFM.get(B2_LAG_NAMES.ZWISCHENLAGER_ID.db_val()),true),	B2_LAG_NAMES.ZWISCHENLAGER_ID.user_text_ms());
        this.add_Component(new MyE2_DB_Label_INGRID	(oFM.get(B2_LAG_NAMES.ZIELLAGER_ID.db_val())	,true),	B2_LAG_NAMES.ZIELLAGER_ID.user_text_ms());

        this.add_Component(new B2_LAG_LIST_Cmp_LagerKunde(B2_LAG_NAMES.STARTLAGER_ADRESSE));
        this.add_Component(new B2_LAG_LIST_Cmp_LagerKunde(B2_LAG_NAMES.ZWISCHENLAGER_ADRESSE));
        this.add_Component(new B2_LAG_LIST_Cmp_LagerKunde(B2_LAG_NAMES.ZIELLAGER_ADRESSE));

        this.add_Component(new B2_LAG_LIST_Cmp_Sorte());
        
        this.add_Component(new MyE2_DB_Label_INGRID	(oFM.get(B2_LAG_NAMES.WE_MENGE_BRUTTO.db_val())		,true), S.ms("WE Bruttomge"));
        this.add_Component(new MyE2_DB_Label_INGRID	(oFM.get(B2_LAG_NAMES.WE_MENGE_NETTO.db_val())		,true),	S.ms("WE Nettomge"));
        this.add_Component(new MyE2_DB_Label_INGRID	(oFM.get(B2_LAG_NAMES.WE_MENGE_ABZUG.db_val())		,true),	S.ms("WE Abzugsmge"));

        this.add_Component(new MyE2_DB_Label_INGRID	(oFM.get(B2_LAG_NAMES.WA_MENGE_BRUTTO.db_val())		,true), S.ms("WA Bruttomge"));
        this.add_Component(new MyE2_DB_Label_INGRID	(oFM.get(B2_LAG_NAMES.WA_MENGE_NETTO.db_val())		,true),	S.ms("WA Nettomge"));
        this.add_Component(new MyE2_DB_Label_INGRID	(oFM.get(B2_LAG_NAMES.WA_MENGE_ABZUG.db_val())		,true),	S.ms("WA Abzugsmge"));
        
        this._setLineWrapListHeader(true
        		
        		);
      	
      	RB_gld gldTitelCenter = 	new RB_gld()._center_top()._ins(1,2,1,1)._col(new E2_ColorDark());
       	this._setLayoutTitles(gldTitelCenter
                ,"EW_FW"
                ,"ABRECHENBAR_BESITZ"
                ,B2_LAG_NAMES.SHOW_TYPE.db_val()
      	);
  
       	RB_gld gldTitelLeft = 	new RB_gld()._left_top()._ins(1,2,1,1)._col(new E2_ColorDark());
       	this._setLayoutTitles(gldTitelLeft
       			,B2_LAG_NAMES.STARTLAGER_ID.db_val()
                ,B2_LAG_NAMES.STARTLAGER_ADRESSE.db_val()
                ,B2_LAG_NAMES.ZIELLAGER_ID.db_val()
                ,B2_LAG_NAMES.ZIELLAGER_ADRESSE.db_val()
//                ,B2_LAG_NAMES.SORTE_INFO.db_val()
//                ,ARTIKEL_BEZ.artbez2.fn()
//                ,BG_VEKTOR.planmenge.fn()
                ,B2_LAG_NAMES.WE_MENGE_BRUTTO.db_val()
                ,B2_LAG_NAMES.WE_MENGE_NETTO.db_val()
                ,B2_LAG_NAMES.WE_MENGE_ABZUG.db_val()
                ,B2_LAG_NAMES.WA_MENGE_BRUTTO.db_val()
                ,B2_LAG_NAMES.WA_MENGE_NETTO.db_val()
                ,B2_LAG_NAMES.WA_MENGE_ABZUG.db_val()
      	);

       	RB_gld gldElementCenter = 	new RB_gld()._center_top()._ins(2,4,2,2)._col(new E2_ColorBase());
       	this._setLayoutElements(gldElementCenter
       			,"EW_FW"
       			,"ABRECHENBAR_BESITZ"
       			,B2_LAG_NAMES.SHOW_TYPE.db_val()
       			);
       	
       	RB_gld gldElementLeft = 	new RB_gld()._left_top()._ins(1,2,1,1)._col(new E2_ColorBase());
       	this._setLayoutElements(gldElementLeft
       			,B2_LAG_NAMES.STARTLAGER_ID.db_val()
       			,B2_LAG_NAMES.STARTLAGER_ADRESSE.db_val()
       			,B2_LAG_NAMES.ZIELLAGER_ID.db_val()
                ,B2_LAG_NAMES.ZIELLAGER_ADRESSE.db_val()
//                ,B2_LAG_NAMES.SORTE_INFO.db_val()
//                ,ARTIKEL_BEZ.artbez2.fn()
       	);
        
       	RB_gld gldElementRight = 	new RB_gld()._right_top()._ins(1,2,1,1)._col(new E2_ColorBase());
       	this._setLayoutElements(gldElementRight
//       			,BG_VEKTOR.planmenge.fn()
       			,B2_LAG_NAMES.WE_MENGE_BRUTTO.db_val()
                ,B2_LAG_NAMES.WE_MENGE_NETTO.db_val()
                ,B2_LAG_NAMES.WE_MENGE_ABZUG.db_val()
                ,B2_LAG_NAMES.WA_MENGE_BRUTTO.db_val()
                ,B2_LAG_NAMES.WA_MENGE_NETTO.db_val()
                ,B2_LAG_NAMES.WA_MENGE_ABZUG.db_val()
       	);
       	
        this.set_oSubQueryAgent(new B2_LAG_LIST_FORMATING_Agent(this.m_tpHashMap));
        	
        this.set_Factory4Records(new factory4Records());
    }
    
    private class factory4Records extends E2_ComponentMAP_Factory4Records {
        @Override
        public MyRECORD_IF_RECORDS get_RECORD(String cID_MAINTABLE) throws myException {
            return new RECORD_BG_ATOM(cID_MAINTABLE);
        }
    }
    
  
    
}
 
 
