 
package panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_V2;
  
import java.util.Comparator;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown2;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_ExpandableRow;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.DB_ENUMS.HILFETEXT;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_ENUMS.VERSION;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_user;


public class HELP2_LIST_Selector extends E2_ExpandableRow {
    
    /*
     * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
     */
	private SelectorDeveloper 	selDeveloper = 	new SelectorDeveloper();
	private SelectorInfoVon 	selInfoVon = 	new SelectorInfoVon();
	private SelectorVersion 	selVersion = 	new SelectorVersion();
	private SelectorModul       selModul = 		new SelectorModul();
	private SelectorStatus      selStatus = 	new SelectorStatus();
	private SelectorTyp     	selTyp = 		new SelectorTyp();
    
    private E2_SelectionComponentsVector     oSelVector = null;
    
    //zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   m_tpHashMap = null;
    
    
    public HELP2_LIST_Selector(RB_TransportHashMap  p_tpHashMap) throws myException
    {
        super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
        
        this.m_tpHashMap = p_tpHashMap;       
        this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SELECTOR,this);
        this.oSelVector = new E2_SelectionComponentsVector(this.m_tpHashMap.getNavigationList());
        this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SELECTIONCOMPONENTVECTOR,this.oSelVector );
        
        this.oSelVector.add(selDeveloper);
        this.oSelVector.add(selInfoVon);
        this.oSelVector.add(selVersion);
        this.oSelVector.add(selModul);
        this.oSelVector.add(selStatus);
        this.oSelVector.add(selTyp);
        
        //jetzt nachsehen, was voreingestellt werden muss
        HELP2__TransportHashMap tphm = (HELP2__TransportHashMap)this.m_tpHashMap;
        E2_MODULNAME_ENUM.MODUL modul = tphm.getModul();
        if (modul!=null) {
        	String s_modul = modul.get_callKey();
        	this.selModul.get_oSelFieldBasis().set_ActiveValue_OR_FirstValue(s_modul);
        }
        
        E2_Grid oGridInnen = new E2_Grid();
        this.add(oGridInnen, E2_INSETS.I_4_4_4_4);
        
        oGridInnen	._a(selDeveloper.get_oComponentForSelection(), new RB_gld()._ins(0,0,20,0) )
        			._a(selInfoVon.get_oComponentForSelection(), new RB_gld()._ins(0,0,20,0) )
        			._a(selVersion.get_oComponentForSelection(), new RB_gld()._ins(0,0,20,0) )
        			._a(selModul.get_oComponentForSelection(), new RB_gld()._ins(0,0,20,0) )
        			._a(selStatus.get_oComponentForSelection(), new RB_gld()._ins(0,0,20,0) )
        			._a(selTyp.get_oComponentForSelection(), new RB_gld()._ins(0,0,20,0) )
        			._s(6)
        			;
        
        
    }
    public E2_SelectionComponentsVector get_oSelVector()
    {
        return oSelVector;
    }
    
    /**
     * selektor fuer die auswahl von modulen ....
     * @author martin
     *
     */
    private class SelectorDeveloper extends E2_ListSelectorMultiDropDown2 {
        public SelectorDeveloper() throws myException {
            super();
            
            //alle vorkommenden benutzer raussuchen
            SEL selIds = new SEL(HILFETEXT.id_user_bearbeiter).ADD_Distinct().FROM(_TAB.hilfetext);
            SEL selUsersDevelop = new SEL("JD_USER.*").FROM(_TAB.user).WHERE(new TermSimple("JD_USER.ID_USER IN ("+selIds.s()+")")).ORDERUP(USER.name1).ORDERUP(USER.vorname);

            RecList21 rlUsers = new RecList21(_TAB.user)._fill(new SqlStringExtended(selUsersDevelop.s()));

            String[][] arr = new String[rlUsers.size()+1][2];
            
            arr[0][0]= "-"; arr[0][1]= "";
            
            int i = 1;
            for (Rec21 r: rlUsers) {
            	arr[i][0]= new Rec21_user(r).getVal4DropDown();
            	arr[i++][1]= r.getUfs(USER.id_user);
            }
            
            MyE2_SelectField  selFieldKenner = new MyE2_SelectField( arr,"", false);    
            
            And  bed = new And(HILFETEXT.id_user_bearbeiter,"#WERT#");
            this.INIT(selFieldKenner, bed.s(), null);
        }
        @Override
        public E2_BasicModuleContainer get_PopupContainer() throws myException {
            return new ownBasicContainer();
        }
        
        private class ownBasicContainer extends E2_BasicModuleContainer {}
        
        @Override
        public Component get_oComponentForSelection() throws myException {
            E2_Grid  gridHelp = new E2_Grid()._setSize(50,120);
            gridHelp._a(S.ms("Entwickler "), new RB_gld()._ins(0,1,10,1)._left_mid())
            		._a(this.get_oComponentWithoutText(), new RB_gld()._ins(0,1,1,1)._left_mid());
            return gridHelp;
        }
        
    }
    
    
    private class SelectorInfoVon extends E2_ListSelectorMultiDropDown2 {
        public SelectorInfoVon() throws myException {
            super();
            
            //alle vorkommenden benutzer raussuchen
            SEL selIds = new SEL(HILFETEXT.id_user_ursprung).ADD_Distinct().FROM(_TAB.hilfetext);
            SEL selUsersDevelop = new SEL("JD_USER.*").FROM(_TAB.user).WHERE(new TermSimple("JD_USER.ID_USER IN ("+selIds.s()+")")).ORDERUP(USER.name1).ORDERUP(USER.vorname);

            RecList21 rlUsers = new RecList21(_TAB.user)._fill(new SqlStringExtended(selUsersDevelop.s()));

            String[][] arr = new String[rlUsers.size()+1][2];
            
            arr[0][0]= "-"; arr[0][1]= "";
            
            int i = 1;
            for (Rec21 r: rlUsers) {
            	arr[i][0]= new Rec21_user(r).getVal4DropDown();
            	arr[i++][1]= r.getUfs(USER.id_user);
            }
            
            MyE2_SelectField  selFieldKenner = new MyE2_SelectField( arr,"", false);    
            
            And  bed = new And(HILFETEXT.id_user_ursprung,"#WERT#");
            this.INIT(selFieldKenner, bed.s(), null);
        }
        @Override
        public E2_BasicModuleContainer get_PopupContainer() throws myException {
            return new ownBasicContainer();
        }
        
        private class ownBasicContainer extends E2_BasicModuleContainer {}
        
        @Override
        public Component get_oComponentForSelection() throws myException {
            E2_Grid  gridHelp = new E2_Grid()._setSize(90,120);
            gridHelp._a(S.ms("Meldung von "), new RB_gld()._ins(0,1,10,1)._left_mid())
            		._a(this.get_oComponentWithoutText(), new RB_gld()._ins(0,1,1,1)._left_mid());
            return gridHelp;
        }
        
    }
    
  
    
    private class SelectorVersion extends E2_ListSelectorMultiDropDown2 {
        public SelectorVersion() throws myException {
            super();
            
            //alle vorkommenden benutzer raussuchen
            SEL selIds = new SEL(HILFETEXT.id_version).ADD_Distinct().FROM(_TAB.hilfetext);
            SEL sel = new SEL(_TAB.version.fullTableName()+".*").FROM(_TAB.version).WHERE(
            				new TermSimple(VERSION.id_version.tnfn()+" IN ("+selIds.s()+")")).ORDERUP(VERSION.version_code);

            RecList21 rl4DD = new RecList21(_TAB.version)._fill(new SqlStringExtended(sel.s()));

            String[][] arr = new String[rl4DD.size()+1][2];
            
            arr[0][0]= "-"; arr[0][1]= "";
            
            int i = 1;
            for (Rec21 r: rl4DD) {
            	arr[i][0]= 		r.getUfs(VERSION.version_code,r.getUfs(VERSION.id_version));
            	arr[i++][1]= 	r.getUfs(VERSION.id_version);
            }
            
            MyE2_SelectField  selComp = new MyE2_SelectField( arr,"", false);    
            
            And  bed = new And(HILFETEXT.id_version,"#WERT#");
            this.INIT(selComp, bed.s(), null);
        }
        @Override
        public E2_BasicModuleContainer get_PopupContainer() throws myException {
            return new ownBasicContainer();
        }
        
        private class ownBasicContainer extends E2_BasicModuleContainer {}
        
        @Override
        public Component get_oComponentForSelection() throws myException {
            E2_Grid  gridHelp = new E2_Grid()._setSize(60,120);
            gridHelp._a(S.ms("Version "), new RB_gld()._ins(0,1,10,1)._left_mid())
            		._a(this.get_oComponentWithoutText(), new RB_gld()._ins(0,1,1,1)._left_mid());
            return gridHelp;
        }
        
    }
        
    
    private class SelectorModul extends E2_ListSelectorMultiDropDown2 {
        public SelectorModul() throws myException {
            super();
            
            //alle vorkommenden benutzer raussuchen
            SEL selInt = new SEL(HILFETEXT.modulkenner).ADD_Distinct()
            			.FROM(_TAB.hilfetext).ORDERUP(HILFETEXT.modulkenner)
            			.WHERE(new vgl(HILFETEXT.modulkenner, COMP.NOT_EQ,HELP2_CONST.pairGlobalRange.getVal1()));

            String[][] kenners = bibDB.EinzelAbfrageInArray(new SqlStringExtended(selInt.s()));
            
            VEK<String> v = new VEK<String>()._addArray(kenners);
            
            //dropdown nach der betextung sortieren
             v.sort(new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
	            	MODUL m1 = E2_MODULNAME_ENUM.find_Corresponding_Enum(o1);
	            	MODUL m2 = E2_MODULNAME_ENUM.find_Corresponding_Enum(o2);
	            	
	            	String s1=o1;
	            	String s2=o2;
	            	
	            	if (m1!=null) {
	            		s1=m1.get_userInfo().CTrans();
	            	}
	            	if (m2!=null) {
	            		s2=m2.get_userInfo().CTrans();
	            	}
					return s1.compareTo(s2);
				}
			});
            
            
            String[][] arr = new String[kenners.length+2][2];
            arr[0][0]= "-"; arr[0][1]= "";
            arr[1][0]= S.ms(HELP2_CONST.pairGlobalRange.getVal2()).CTrans(); arr[1][1]= HELP2_CONST.pairGlobalRange.getVal1();
            int i = 2;
            for (String m_s: v) {
            	
            	MODUL m = E2_MODULNAME_ENUM.find_Corresponding_Enum(m_s);
            	if (m!=null) {
            		arr[i][0]= 		m.get_userInfo().CTrans();
            	} else {
            		arr[i][0]= 		m_s;
            	}
            	arr[i++][1]= 	m_s;
            	
            }
            MyE2_SelectField  selComp = new MyE2_SelectField( arr,"", false);    
            
            And  bed = new And(HILFETEXT.modulkenner,"'#WERT#'");
            this.INIT(selComp, bed.s(), null);
        }
        @Override
        public E2_BasicModuleContainer get_PopupContainer() throws myException {
            return new ownBasicContainer();
        }
        
        private class ownBasicContainer extends E2_BasicModuleContainer {}
        
        @Override
        public Component get_oComponentForSelection() throws myException {
            E2_Grid  gridHelp = new E2_Grid()._setSize(50,120);
            gridHelp._a(S.ms("Modul "), new RB_gld()._ins(0,1,10,1)._left_mid())
            		._a(this.get_oComponentWithoutText(), new RB_gld()._ins(0,1,1,1)._left_mid());
            return gridHelp;
        }
        
    }
        
    
    private class SelectorStatus extends E2_ListSelectorMultiDropDown2 {
    	
        public SelectorStatus() throws myException {
            super();
            
            String[][] arr = new String[4][2];
            arr[0][0]= "-"; 												arr[0][1]= "";
            arr[1][0]= HELP2_CONST.TICKET_STATUS.NEW.user_text(); 			arr[1][1]= HELP2_CONST.TICKET_STATUS.NEW.db_val();
            arr[2][0]= HELP2_CONST.TICKET_STATUS.IN_PROGRESS.user_text(); 	arr[2][1]= HELP2_CONST.TICKET_STATUS.IN_PROGRESS.db_val();
            arr[3][0]= HELP2_CONST.TICKET_STATUS.CLOSED.user_text();		arr[3][1]= HELP2_CONST.TICKET_STATUS.CLOSED.db_val();
            
            MyE2_SelectField  selComp = new MyE2_SelectField( arr,"", false);    
            
            And  bed = new And(HILFETEXT.status,"'#WERT#'");
            this.INIT(selComp, bed.s(), null);
        }
        @Override
        public E2_BasicModuleContainer get_PopupContainer() throws myException {
            return new ownBasicContainer();
        }
        
        private class ownBasicContainer extends E2_BasicModuleContainer {}
        
        @Override
        public Component get_oComponentForSelection() throws myException {
            E2_Grid  gridHelp = new E2_Grid()._setSize(50,120);
            gridHelp._a(S.ms("Status "), new RB_gld()._ins(0,1,10,1)._left_mid())
            		._a(this.get_oComponentWithoutText(), new RB_gld()._ins(0,1,1,1)._left_mid());
            return gridHelp;
        }
        
    }

    
    private class SelectorTyp extends E2_ListSelectorMultiDropDown2 {
    	
        public SelectorTyp() throws myException {
            super();
            
            String[][] arr = new String[4][2];
            arr[0][0]= "-"; 												arr[0][1]= "";
            arr[1][0]= HELP2_CONST.TICKET_TYP.DOKUMENT.user_text(); 			arr[1][1]= HELP2_CONST.TICKET_TYP.DOKUMENT.db_val();
            arr[2][0]= HELP2_CONST.TICKET_TYP.FEATURE.user_text(); 	arr[2][1]= HELP2_CONST.TICKET_TYP.FEATURE.db_val();
            arr[3][0]= HELP2_CONST.TICKET_TYP.BUGFIX.user_text();		arr[3][1]= HELP2_CONST.TICKET_TYP.BUGFIX.db_val();
            
            MyE2_SelectField  selComp = new MyE2_SelectField( arr,"", false);    
            
            And  bed = new And(HILFETEXT.typ,"'#WERT#'");
            this.INIT(selComp, bed.s(), null);
        }
        @Override
        public E2_BasicModuleContainer get_PopupContainer() throws myException {
            return new ownBasicContainer();
        }
        
        private class ownBasicContainer extends E2_BasicModuleContainer {}
        
        @Override
        public Component get_oComponentForSelection() throws myException {
            E2_Grid  gridHelp = new E2_Grid()._setSize(40,120);
            gridHelp._a(S.ms("Typ "), new RB_gld()._ins(0,1,10,1)._left_mid())
            		._a(this.get_oComponentWithoutText(), new RB_gld()._ins(0,1,1,1)._left_mid());
            return gridHelp;
        }
        
    }


    
}
 
 
