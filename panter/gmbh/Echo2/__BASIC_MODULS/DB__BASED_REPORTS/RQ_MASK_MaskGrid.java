 
package panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS;
  
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS.FIELDS.RQF_MASK_DaughterListForMotherMask;
import panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS.PARAMS.RQP_MASK_DaughterListForMotherMask;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.E2_HelpButton;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.basics4project.DB_ENUMS.REPORTING_QUERY;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
 
public class RQ_MASK_MaskGrid extends E2_Grid {
 	
    //zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   m_tpHashMap = null;
    
    //wird benutzt, falls mehr als ein E2_Grid verwendung findet
    private MyE2_TabbedPane  ta  = new MyE2_TabbedPane(600);
    
	/*
	 * vector nimmt alle container auf, die reale felder enthalten
	 * und die zugehoerigen tab-texte (falls mehr als ein container noetig ist) 
	 */
    
	private VEK<E2_Grid>   fieldContainers = 	new VEK<E2_Grid>();
    private VEK<MyString>  tabText = 			new VEK<MyString>();
    
    public RQ_MASK_MaskGrid(RB_TransportHashMap  p_tpHashMap) throws myException {
        super();
        
        int iWidthComplete = RQ_CONST.RQ_NUM_CONST.MASKPOPUP_DESCRIPTION_COL_SIZE.getValue()+
                                  RQ_CONST.RQ_NUM_CONST.MASKPOPUP_FIELD_COL_SIZE.getValue()+20;
        this._setSize(iWidthComplete)._bo_no();
 
        
        this._setSize(RQ_CONST.RQ_NUM_CONST.MASKPOPUP_DESCRIPTION_COL_SIZE.getValue()+
                RQ_CONST.RQ_NUM_CONST.MASKPOPUP_FIELD_COL_SIZE.getValue()+40)._bo_no();
        
        this.m_tpHashMap = p_tpHashMap;
        this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MASK_GRID,this);
        
        RQ_MASK_ComponentMap  map1 = (RQ_MASK_ComponentMap) this.m_tpHashMap.getMaskComponentMapCollector().get(this.m_tpHashMap.getLeadingMaskKey());
        
        //beginn erster tab
        E2_Grid g1 = fieldContainers._ar(new E2_Grid()._setSize( 	RQ_CONST.RQ_NUM_CONST.MASKPOPUP_DESCRIPTION_COL_SIZE.getValue()
        															,15
        															,RQ_CONST.RQ_NUM_CONST.MASKPOPUP_FIELD_COL_SIZE.getValue())._bo_no());        
        

        E2_HelpButton help = new E2_HelpButton(S.ms("Infos zum Aufbau der SQL-Abfrage"),
        										new VEK<String>()._a("Die SQL-Abfrage muss folgendermassen formuliert sein:")
        														 ._a("Sie muss beginnen mit SELECT #PRIMAERKEY#, .... wobei der Rest wir eine  ")
        														 ._a("normale Abfrage formuliert ist, die in einem SQL-QUERY-Tool funktioniert.")
        														 ._a("Gewünschte Parameter müssen in der Form $$NAME$$")
        														 ._a("hinterlegt werden. Beim Aufbau der Query werden diese dann ausgelesen.")
        														 ._a("")
        														 ._a("Beispiel: ")
        														 ._a("SELECT #PRIMAERKEY#, A.* FROM JT_ADRESSE A WHERE A.ID_ADRESSE>=$$untergrenze$$")
        														 
        														,false)._setWidthPopup(600)._setHeightPopup(400);
        
        tabText._a(S.ms("Definitionsfelder"));
        
        g1	._a(new RB_lab(RQ_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY.id_reporting_query)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
        	._a()
        	._a(map1.getComp(REPORTING_QUERY.id_reporting_query), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));
        g1	._a(new RB_lab(RQ_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY.aktiv)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
        	._a()
        	._a(map1.getComp(REPORTING_QUERY.aktiv), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));
        g1	._a(new RB_lab(RQ_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY.titel_4_user)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
        	._a()
        	._a(map1.getComp(REPORTING_QUERY.titel_4_user), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));
        g1	._a(new RB_lab(RQ_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY.langtextinfo)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
	    	._a()
	    	._a(map1.getComp(REPORTING_QUERY.langtextinfo), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));

        g1	._a(new RB_lab(RQ_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY.table_basename)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
        	._a()
        	._a(map1.getComp(REPORTING_QUERY.table_basename), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));
        g1	._a(new RB_lab(RQ_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY.realname_temptable)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
        	._a()
        	._a(map1.getComp(REPORTING_QUERY.realname_temptable), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));
        g1	._a(new RB_lab(S.ms("SQL-Abfrage")) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
        	._a(help)
        	._a(map1.getComp(RQ_TextAnzeigeAllSQL.KEY), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));
         
        g1	._a()
	    	._a()
	    	._a(map1.getComp(RQ_MASK_BtAnalyseQuery.KEY), new RB_gld()._ins(2,6,2,2)._al(E2_ALIGN.LEFT_TOP));
        g1	._a()
	    	._a()
	    	._a(map1.getComp(RQ_MASK_BtReadFieldsAndBuildParameters.KEY), new RB_gld()._ins(2,6,2,2)._al(E2_ALIGN.LEFT_TOP));
        g1	._a()
	    	._a()
	    	._a(map1.getComp(RQ_MASK_BtSimulateInputDialog.KEY), new RB_gld()._ins(2,6,2,2)._al(E2_ALIGN.LEFT_TOP));
        g1	._a()
	    	._a()
	    	._a(map1.getComp(RQ_MASK_BtMakeQueryActive.KEY), new RB_gld()._ins(2,6,2,2)._al(E2_ALIGN.LEFT_TOP));
        g1	._a()
	    	._a()
	    	._a(map1.getComp(RQ_MASK_BtMakeQueryInActiveDropTable.KEY), new RB_gld()._ins(2,6,2,2)._al(E2_ALIGN.LEFT_TOP));
        g1	._a()
	    	._a()
	    	._a(map1.getComp(RQ_MASK_BtStartReport.KEY), new RB_gld()._ins(2,6,2,2)._al(E2_ALIGN.LEFT_TOP));
         
        
        E2_Grid g2 = fieldContainers._ar(new E2_Grid());
        tabText._a(S.ms("Parameter"));
        g2._setSize(760)._bo_no();
        g2._a(map1.getComp(RQP_MASK_DaughterListForMotherMask.keyForMotherMask), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));

        E2_Grid g3 = fieldContainers._ar(new E2_Grid());
        tabText._a(S.ms("Spalten der Liste"));
	    g3._setSize(760)._bo_no();
	    g3._a(map1.getComp(RQF_MASK_DaughterListForMotherMask.keyForMotherMask), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP));
    

        
        //hier alles rendern (entweder nur ein grid oder ein tab mit grids ...
        this.renderMask();
        
        this.resize(RQ_CONST.RQ_NUM_CONST.MASKPOPUP_WIDTH.getValue(),
        			  RQ_CONST.RQ_NUM_CONST.MASKPOPUP_HEIGHT.getValue());
    }
    
    
    private void renderMask() throws myException {
    
    	if (this.fieldContainers.size()==1) {
    		this._a(this.fieldContainers.get(0));
    	} else {
    		for (int i=0; i<this.fieldContainers.size(); i++) {
    			MyString s_tab = this.tabText.size()>i?S.NN(this.tabText.get(i), S.ms("..")):S.ms("Tab Nr: ").ut(" "+(i+1));
    			this.ta.add_Tabb(s_tab, this.fieldContainers.get(i));
    		}
    		this._a(this.ta);
    	}
    }
  
  
  	 /*
  	  * zieht bei groessenaenderungen der maske die interne tab-kompontente mit
  	  */
     public void resize(int width, int height) {
	   this.ta.setWidth(new Extent(width-60));
	   this.ta.setHeight(new Extent(height-170));
	 }
    
     
     

     
}
 
 
