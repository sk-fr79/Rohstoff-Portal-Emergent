 
package panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.LIST_MASK;
import java.util.Arrays;
import java.util.Vector;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import oracle.security.o3logon.O3LoginClientHelper;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorContainer;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelectorMultiDropDown2;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelectionComponentsVector;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.USER_SELECTOR_GENERATOR_NT;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL_TYP;
import panter.gmbh.basics4project.ENUM_USER_TYP;
import panter.gmbh.basics4project.DB_ENUMS.REMINDER_DEF;
import panter.gmbh.basics4project.DB_ENUMS.REMINDER_USER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.Comparator_For_2_dimensional_Arrays;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.Or;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;


public class REM_LIST_Selector extends E2_ListSelectorContainer
{
    
    /*
     * id_adresse in (select id_adresse from jt_adressklasse where id_adressklasse_Def=#WERT#)
     */
    
    private E2_SelectionComponentsVector    oSelVector = null;
    ownSelectorAngelegtVon 					sel_angelegt_von = null;
    cSelectorModul						sel_modul_connect = null;
    cSelektorReminderUser   				sel_reminder_user = null;
    cSelectorTabelle						sel_tabelle	= null;


    
    public REM_LIST_Selector(E2_NavigationList oNavigationList) throws myException
    {
        super(new MyE2_String("Selektionsblock geschlossen"), new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID),new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
        
        this.oSelVector = new E2_SelectionComponentsVector(oNavigationList);
        
        sel_angelegt_von 	= new ownSelectorAngelegtVon();
        sel_modul_connect 	= new cSelectorModul();
        sel_reminder_user 	= new cSelektorReminderUser();
//        sel_tabelle 		= new ownSelectorTabelle();
        
        oSelVector.add(sel_angelegt_von);
        oSelVector.add(sel_modul_connect);
        oSelVector.add(sel_reminder_user);
//        oSelVector.add(sel_tabelle);
        

        // Aktiv / Inaktiv
        cSelectorAbgeschlossene oSelBestaetigt =  new cSelectorAbgeschlossene();
        oSelVector.add(oSelBestaetigt);        


        // 
        // GUI
        //
        MyE2_Grid oGridInnen = new MyE2_Grid(5,0);
        this.add(oGridInnen, E2_INSETS.I_4_4_4_4);

        oGridInnen.add(oSelBestaetigt.get_oComponentForSelection(),1,E2_INSETS.I_0_0_0_0);
        
        oGridInnen.add(new MyE2_Label(new MyE2_String("Beteiligter Mitarb.:")),1, E2_INSETS.I(10,0,0,0));
        oGridInnen.add(sel_reminder_user.get_oComponentForSelection(),1,E2_INSETS.I_5_0_0_0);
		
        oGridInnen.add(new MyE2_Label(new MyE2_String("Ziel-Modul:")), E2_INSETS.I(10,0,0,0));
        oGridInnen.add(sel_modul_connect.get_oComponentForSelection(),1,E2_INSETS.I_5_0_0_0);
        
		oGridInnen.add(new MyE2_Label(""),1,E2_INSETS.I_0_0_0_0);
		
		oGridInnen.add(new MyE2_Label(new MyE2_String("Angelegt von:")),1, E2_INSETS.I(10,0,0,0));
		oGridInnen.add(sel_angelegt_von.get_oComponentForSelection(),1,E2_INSETS.I_5_0_0_0);
		
//		oGridInnen.add(sel_tabelle.get_oComponentForSelection(),1,E2_INSETS.I_20_0_0_0);
       
        
    }
    
    
    public E2_SelectionComponentsVector get_oSelVector()
    {
        return oSelVector;
    }
    
    
    /**
     * Setzen des Listenmoduls (keine Masken)
     * @author manfred
     * @date 26.04.2016
     *
     * @param modul
     * @throws myException 
     */
    public void setSelectionModul(MODUL modul) throws myException{
    	if (modul.is_LIST()){
    		sel_modul_connect.LEER_MACHEN();
    		sel_modul_connect.get_oSelFieldBasis().set_ActiveValue_OR_FirstValue(modul.get_callKey());
    		oSelVector.doActionPassiv();
    	}
    }
    
    
    /**
     * setzt den aktuellen Benutzer
     * @author manfred
     * @date 26.04.2016
     *
     * @param idUser
     * @throws myException 
     */
    public void setSelectionUser(String idUser) throws myException{
    	sel_reminder_user.LEER_MACHEN();
    	sel_reminder_user.get_oSelFieldBasis().set_ActiveValue_OR_FirstValue(idUser);    	
    	oSelVector.doActionPassiv();
    }
    
    
    /**
     * setzt den aktuellen Besitzer
     * @author manfred
     * @date 26.04.2016
     *
     * @param idUser
     * @throws myException 
     */
    public void setSelectionAngelegtVon(String idUser) throws myException{
    	sel_angelegt_von.LEER_MACHEN();
    	sel_angelegt_von.get_oSelFieldBasis().set_ActiveValue_OR_FirstValue(idUser);
    	oSelVector.doActionPassiv();
    }
    
    
    
    
    
    /**
     * selektor fuer die auswahl von Usern
     * @author martin
     *
     */
    private class ownSelectorAngelegtVon extends E2_ListSelectorMultiDropDown2 {
        public ownSelectorAngelegtVon() throws myException {
            super();
            
            String[][] userList = new USER_SELECTOR_GENERATOR_NT(false,ENUM_USER_TYP.AKTIV).get_selUsers(true);
            MyE2_SelectField  selFieldKenner = new MyE2_SelectField(userList,"", false);    
            
            And  bed = new And(REMINDER_DEF.id_user_angelegt,"#WERT#");
            this.INIT(selFieldKenner, bed.s(), null);
        }
        @Override
        public E2_BasicModuleContainer get_PopupContainer() throws myException {
            return new ownBasicContainer();
        }
        
        private class ownBasicContainer extends E2_BasicModuleContainer {}
        
        @Override
        public Component get_oComponentForSelection() throws myException {
            int[] breite = {300,25};
            MyE2_Grid  gridHelp = new MyE2_Grid(breite, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
            gridHelp.add(this.get_oComponentWithoutText(), E2_INSETS.I(0,0,0,0));
            this.set_extOfSelectComponentDropDown(new Extent(300));
            
            MyE2_Button btnMe = new MyE2_Button(E2_ResourceIcon.get_RI("person.png"));
            btnMe.setToolTipText(new MyString("Auswahl des aktuell angemeldeten Benutzers.").CTrans());
            btnMe.add_oActionAgent(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					REM_LIST_Selector.this.setSelectionAngelegtVon(bibALL.get_ID_USER());
				}
			});
            gridHelp.add(btnMe,E2_INSETS.I(0,0,0,0));
            return gridHelp;
        }
        
    }
    
    
    /**
     * selektor fuer die auswahl von Usern
     * @author martin
     *
     */
    private class cSelektorReminderUser extends E2_ListSelectorMultiDropDown2 {
        public cSelektorReminderUser() throws myException {
            super();
            
            String[][] userList = new USER_SELECTOR_GENERATOR_NT(false,ENUM_USER_TYP.AKTIV).get_selUsers(true);
            MyE2_SelectField  selFieldKenner = new MyE2_SelectField(userList,"", false);    
            
            SEL selReminderID = new SEL(REMINDER_USER.id_reminder_def).FROM(REMINDER_USER.T());
            
            And bedSubselect = new And (	
            							new vgl(
            									new TermSimple(REMINDER_DEF.id_reminder_def.fieldName()),
            									COMP.IN,
            									new TermSimple(" (" +  selReminderID.s() + " WHERE " + REMINDER_USER.id_user + " = #WERT# )") 
            									) 
            							) ;
            bedSubselect.or(new vgl(REMINDER_DEF.id_user_angelegt,COMP.EQ,new TermSimple("#WERT#") ));
            this.INIT(selFieldKenner, bedSubselect.s(), null);
        }

        @Override
        public E2_BasicModuleContainer get_PopupContainer() throws myException {
            return new ownBasicContainer();
        }
        
        private class ownBasicContainer extends E2_BasicModuleContainer {}
        private MyE2_Button btnMe = null;
        
        
        @Override
        public Component get_oComponentForSelection() throws myException {
            int[] breite = {300,25};
            btnMe = new MyE2_Button(E2_ResourceIcon.get_RI("person.png"));
            btnMe.setToolTipText(new MyString("Auswahl des aktuell angemeldeten Benutzers.").CTrans());
            btnMe.add_oActionAgent(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					REM_LIST_Selector.this.setSelectionUser(bibALL.get_ID_USER());
				}
			});
            
            MyE2_Grid  gridHelp = new MyE2_Grid(breite, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
            gridHelp.add(this.get_oComponentWithoutText(), E2_INSETS.I(0,0,0,0));
            this.set_extOfSelectComponentDropDown(new Extent(300));
            gridHelp.add(btnMe,E2_INSETS.I(0,0,0,0));
            return gridHelp;
        }
        
    }
    
    
    private class cSelectorModul extends E2_ListSelectorMultiDropDown2 {
        public cSelectorModul() throws myException {
            super();
            Vector<E2_MODULNAME_ENUM.MODUL_TYP> vModule = new Vector<>();
            String[][] module ;
            
            
            vModule.add(MODUL_TYP.LIST);
            String[][] modul_list = E2_MODULNAME_ENUM.get_dd_moduls_for_selector(vModule, true,1,"'"); 
            modul_list[1][0] = "* ohne zugeordnetes Modul *";
            modul_list[1][1] = " '*' ";
            
            
            MyE2_SelectField  selFieldModule = new MyE2_SelectField(modul_list,"", false);    
            And  bed = new And("NVL(" + REMINDER_DEF.modul_connect_ziel + ",'*')","#WERT#");
            this.INIT(selFieldModule, bed.s(), null);
        }
        
        @Override
        public E2_BasicModuleContainer get_PopupContainer() throws myException {
            return new ownBasicContainer();
        }
        
        private class ownBasicContainer extends E2_BasicModuleContainer {}
        
        @Override
        public Component get_oComponentForSelection() throws myException {
            int[] breite = {300};
            MyE2_Grid  gridHelp = new MyE2_Grid(breite, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
            gridHelp.add(this.get_oComponentWithoutText(), E2_INSETS.I(0,0,0,0));
            this.set_extOfSelectComponentDropDown(new Extent(300));
            return gridHelp;
        }
        
    }
    
    
    
    private class cSelectorTabelle extends E2_ListSelectorMultiDropDown2 {
        public cSelectorTabelle() throws myException {
            super();
            
            String[][] modul_list = get_dd_tables(); 
            
            MyE2_SelectField  selFieldModule = new MyE2_SelectField(modul_list,"", false);    
            And  bed = new And("NVL(" + REMINDER_DEF.table_name + ",'*')","#WERT#");
            this.INIT(selFieldModule, bed.s(), null);
        }
        
        @Override
        public E2_BasicModuleContainer get_PopupContainer() throws myException {
            return new ownBasicContainer();
        }
        
        private class ownBasicContainer extends E2_BasicModuleContainer {}
        
        @Override
        public Component get_oComponentForSelection() throws myException {
            int[] breite = {300};
            MyE2_Grid  gridHelp = new MyE2_Grid(breite, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
            gridHelp.add(this.get_oComponentWithoutText(), E2_INSETS.I(0,0,0,0));
            this.set_extOfSelectComponentDropDown(new Extent(300));
            return gridHelp;
        }
        
        
        /**
         * holt alle Tabellen-Namen Sortiert
         * @author manfred
         * @date 27.04.2016
         *
         * @return
         */
    	private String[][] get_dd_tables() {
    		int icount= _TAB.values().length;
    		
    		String[][] ddarray =  new String[icount+1][2];
    		
    		int i=0;
   			ddarray[i][0] = "-"; ddarray[i][1] = ""; 
   			i++;
    		
   			for (_TAB mod: _TAB.values()) {
    				ddarray[i][0] = mod.baseTableName() ; 
    				ddarray[i][1] = "'" +  mod.baseTableName() + "'";
    				i++;
    		}
    		
    		
    		Arrays.sort(ddarray,new Comparator_For_2_dimensional_Arrays(0, true));
    		return ddarray;
    	}
        
    }
    
    
    /**
     * aktiv/abgeschlossene
     * @author manfred
     * @date 11.04.2016
     *
     */
    private static class cSelectorAbgeschlossene extends E2_ListSelektorMultiselektionStatusFeld_STD {

    	final static int[] CheckBoxSelektorSpalten = {60,60};
    	
    	public cSelectorAbgeschlossene()
    	{
    		super( CheckBoxSelektorSpalten,false,new MyE2_String(""),new Extent(0));
    		
    		String cAbgeschlossen_FELD = REMINDER_DEF.abgeschlossen_am.tnfn();
    		
    		this.ADD_STATUS_TO_Selector(true,	"(#FELD# IS NULL)".replace("#FELD#",cAbgeschlossen_FELD),	new MyE2_String("Offene"),   new MyE2_String("Zeigt alle aktive Erinnerungsmeldungen"));		
    		this.ADD_STATUS_TO_Selector(false,	"(#FELD# IS NOT NULL)".replace("#FELD#",cAbgeschlossen_FELD),	new MyE2_String("Abgeschlossene"),   new MyE2_String("Zeigt alle abgeschlossenen Erinnerungsmeldungen"));		
    	}
    }
    
    
}
 
