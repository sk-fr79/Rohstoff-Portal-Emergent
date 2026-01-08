 
package panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS.PARAMS;
  
import java.util.LinkedHashMap;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_HelpButtonWithHelpWindow;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.basics4project.DB_ENUMS.REPORTING_QUERY_PARAM;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.Replacer.bibReplacer;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
 
public class RQP_MASK_MaskGrid extends E2_Grid {
 	
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
    
    public RQP_MASK_MaskGrid(RB_TransportHashMap  p_tpHashMap) throws myException {
        super();
        
        int iWidthComplete = RQP_CONST.RQP_NUM_CONST.MASKPOPUP_DESCRIPTION_COL_SIZE.getValue()+
                                  RQP_CONST.RQP_NUM_CONST.MASKPOPUP_FIELD_COL_SIZE.getValue()+20;
        this._setSize(iWidthComplete)._bo_no();
 
        
        this._setSize(800)._bo_no();
        
        this.m_tpHashMap = p_tpHashMap;
        this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MASK_GRID,this);
        
        RQP_MASK_ComponentMap  map1 = (RQP_MASK_ComponentMap) this.m_tpHashMap.getMaskComponentMapCollector().get(this.m_tpHashMap.getLeadingMaskKey());
        
        //beginn erster tab
        E2_Grid g1 = fieldContainers._ar(new E2_Grid()._setSize( RQP_CONST.RQP_NUM_CONST.MASKPOPUP_DESCRIPTION_COL_SIZE.getValue(),
        														 RQP_CONST.RQP_NUM_CONST.MASKPOPUP_FIELD_COL_SIZE.getValue(),
        														 20)._bo_no());        
        
        int iZahl = 1;
        
        tabText._a(S.ms("Tab "+(iZahl++)));
        
        g1		._a(new RB_lab(RQP_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY_PARAM.id_reporting_query_param)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
        		._a(map1.getComp(REPORTING_QUERY_PARAM.id_reporting_query_param), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
        		._a(new HelpButton())
        		;
        g1		._a(new RB_lab(RQP_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY_PARAM.typ)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
        		._a(map1.getComp(REPORTING_QUERY_PARAM.typ), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
        		._a()
        		;
        g1		._a(new RB_lab(RQP_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY_PARAM.paramname_4_user)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
        		._a(map1.getComp(REPORTING_QUERY_PARAM.paramname_4_user), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
        		._a()
        		;
        g1		._a(new RB_lab(RQP_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY_PARAM.paramkey)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
        		._a(map1.getComp(REPORTING_QUERY_PARAM.paramkey), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
        		._a()
        		;
        g1		._a(new RB_lab(RQP_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY_PARAM.paramdefault)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
        		._a(map1.getComp(REPORTING_QUERY_PARAM.paramdefault), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
        		._a()
        		;
        g1		._a(new RB_lab(RQP_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY_PARAM.paramdef)) ,new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
        		._a(map1.getComp(REPORTING_QUERY_PARAM.paramdef), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_TOP))
        		._a()
        		;
         
        this.renderMask();
        
        this.resize(RQP_CONST.RQP_NUM_CONST.MASKPOPUP_WIDTH.getValue(),
        			  RQP_CONST.RQP_NUM_CONST.MASKPOPUP_HEIGHT.getValue());
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
    
     
     
 	private class HelpButton extends MyE2_HelpButtonWithHelpWindow {
 		
 		public HelpButton() {
 			super(new Extent(800), new Extent(650));
 		}

 		@Override
 		public E2_BasicModuleContainer createOwnBasicModuleContainer() throws myException {
 			return new ownBasicContainer();
 		}
 		private class ownBasicContainer extends E2_BasicModuleContainer
 		{
 		}
 		@Override
 		public void fillInternalColumn(MyE2_Column oColumn) throws myException
 		{
 			oColumn.removeAll();
 			
 			// hilfe fuer die parameter-definition (select-Feld)
 			Vector<MyString> vHelpText = new Vector<MyString>();
 			vHelpText.add(new MyE2_String("Information zur Parameter-Definition:"));
 			vHelpText.add(new MyE2_String("-------------------------------------------------------------------------"));
 			vHelpText.add(new MyE2_String("Der Wert <Beschreibung des Parameters> taucht als Hilfe in der Eingabe-Maske"));
 			vHelpText.add(new MyE2_String("bei der Parameter-Abfrage auf (falls er nicht leer ist)."));
 			vHelpText.add(new MyE2_String("-------------------------------------------------------------------------"));
 			vHelpText.add(new MyE2_String("Im Wert <Definition für Dropdown-Feld> kann eine Definition erzeugt werden,"));
 			vHelpText.add(new MyE2_String("mit der eine Eingabe via DropDown-Feld an den Report übergeben werden kann."));
 			vHelpText.add(new MyE2_String("Dies kann eine Abfrage sein:"));
 			vHelpText.add(new MyE2_String("<SELECT ANZEIGE,WERT FROM REFERENZTABELLE ...> wobei der Eintrag in der WERT- "));
 			vHelpText.add(new MyE2_String("Spalte an der Report übergeben wird."));
 			vHelpText.add(new MyE2_String("-------------------------------------------------------------------"));
 			
 			vHelpText.add(new MyE2_String("Eine DropDown-Auswahl kann mit einem 2-spaltigen Array definiert werden:"));
 			vHelpText.add(new MyE2_String("|Anzeige1:Wert1|Anzeige2:Wert2|Anzeige3:Wert3|Anzeige3:Wert3|"));
 			vHelpText.add(new MyE2_String("Der erste Wert in jedem Block ist in der Anzeige zu sehen, der 2. Wert wird an"));
 			vHelpText.add(new MyE2_String("den Report übergeben."));
 			vHelpText.add(new MyE2_String("-------------------------------------------------------------------"));
 			vHelpText.add(new MyE2_String("Eine Checkbox, die Y oder N übergibt, erzeugt der Eintrag #CHECKBOX# im Feld  <Definition für Dropdown-Feld> !"));
 			vHelpText.add(new MyE2_String("-------------------------------------------------------------------"));
 			
 			MyE2_Column  colInfoblock1 = new MyE2_Column();
 			MyE2_HelpButtonWithHelpWindow.add_StringsToColumn(vHelpText, colInfoblock1, true, new E2_FontPlain(-2));
 			oColumn.add(colInfoblock1);
 			
 			//jetzt ein grid mit den moeglichen platzhaltern
 			int iBreite[] = {100,200,200};
 			MyE2_Grid oGridInfoBlock2 = new MyE2_Grid(iBreite, MyE2_Grid.STYLE_GRID_DDARK_BORDER_NO_INSETS()) ;
 			oGridInfoBlock2.add(new MyE2_Label(new MyE2_String("In den Vorgabewerten und Abfragen koennen folgende Platzhalter stehen:",true),new E2_FontBold(-2)),3,E2_INSETS.I_1_0_1_0);
 			
 			LinkedHashMap<String, String[]> lhashMap = bibReplacer.get_ListOfReplaceFields(null);
 			
 			oGridInfoBlock2.add(new MyE2_Label(new MyE2_String("Platzhalter"),		new E2_FontBold(-2)),E2_INSETS.I_1_0_1_0);
 			oGridInfoBlock2.add(new MyE2_Label(new MyE2_String("Momentaner Wert"),	new E2_FontBold(-2)),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_1_0_1_0));
 			oGridInfoBlock2.add(new MyE2_Label(new MyE2_String("Beschreibung"),		new E2_FontBold(-2)),E2_INSETS.I_1_0_1_0);
 			
 			for (String cKey: lhashMap.keySet())
 			{
 				oGridInfoBlock2.add(new MyE2_Label(new MyE2_String(cKey),new E2_FontPlain(-2)),E2_INSETS.I_1_0_1_0);
 				oGridInfoBlock2.add(new MyE2_Label(new MyE2_String(lhashMap.get(cKey)[1]),new E2_FontItalic(-2)),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_1_0_1_0));
 				oGridInfoBlock2.add(new MyE2_Label(new MyE2_String(lhashMap.get(cKey)[0]),new E2_FontPlain(-2)),E2_INSETS.I_1_0_1_0);
 			}

// 			
// 			String modulkenner = MOD_REPORTS_MASK.this.oComponentMAPMASK.getModulkennerListBelongsTo();
// 			Vector<ENUM_Selector_Report_Params> selector_params = Selector_Report_Params.getParamForModul(E2_MODULNAME_ENUM.find_Corresponding_Enum(modulkenner));
// 			for (ENUM_Selector_Report_Params param: selector_params){
// 				oGridInfoBlock2.add(new MyE2_Label(new MyE2_String(param.get_Name_For_Param()),new E2_FontPlain(-2)),E2_INSETS.I_1_0_1_0);
// 				oGridInfoBlock2.add(new MyE2_Label(new MyE2_String("-"),new E2_FontItalic(-2)),MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I_1_0_1_0));
// 				oGridInfoBlock2.add(new MyE2_Label(new MyE2_String(param.get_Description()),new E2_FontPlain(-2)),E2_INSETS.I_1_0_1_0);
// 			}
// 			
 			
 			oColumn.add(oGridInfoBlock2);
 			
 		}
 	}

}
 
 
