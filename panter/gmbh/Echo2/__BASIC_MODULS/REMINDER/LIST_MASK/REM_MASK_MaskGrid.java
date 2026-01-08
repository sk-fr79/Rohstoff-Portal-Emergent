 
package panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.LIST_MASK;

import echopointng.Separator;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.TOOLS.RB_MaskGrid;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_ENUMS.REMINDER_DEF;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;

public class REM_MASK_MaskGrid extends RB_MaskGrid {

	public REM_MASK_MaskGrid(REM_MASK_ComponentMapCollector  mapColl) throws myException {
		
        super();
        
        RB_gld gl = 		new RB_gld()._ins(E2_INSETS.I(2,1,0,1));
        RB_gld gld = 		new RB_gld()._ins(E2_INSETS.I(2,1,0,1))._col(new E2_ColorDDark());
        RB_gld gl_label = 	new RB_gld()._ins(E2_INSETS.I(2,3,0,1));
        
        REM_MASK_ComponentMap  map1 = (REM_MASK_ComponentMap) mapColl.get(new RB_KM(_TAB.reminder_def));
        
        RB_MaskGrid  innen = new RB_MaskGrid()	._add(new MyE2_Label(new MyE2_String("ID Erinnerung"),MyE2_Label.STYLE_NORMAL_ITALLIC()),gld)
        										._add(new MyE2_Label(new MyE2_String("ID Tabelle (verknüpft)"),MyE2_Label.STYLE_NORMAL_ITALLIC()),gld)
        										._add(new MyE2_Label(new MyE2_String("Tabelle (verknüpft)"),MyE2_Label.STYLE_NORMAL_ITALLIC()),gld)
        										._add(new MyE2_Label(new MyE2_String("Modul (verknüpft)"),MyE2_Label.STYLE_NORMAL_ITALLIC()),gld)
        										._add(new MyE2_Label(new MyE2_String("angelegt von"),MyE2_Label.STYLE_NORMAL_ITALLIC()),gld)
        										._addIF(map1.getRbComponent(REMINDER_DEF.id_reminder_def),gl)
           										._addIF(map1.getRbComponent(REMINDER_DEF.id_table),gl)
           										._addIF(map1.getRbComponent(REMINDER_DEF.table_name),gl)
           										._addIF(map1.getRbComponent(REMINDER_DEF.modul_connect_ziel),gl)
           										._addIF(map1.getRbComponent(REMINDER_DEF.id_user_angelegt),gl)
           										._setSize(200,150,150,150,150)
           										._style(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS())
           										;
        												
        this.add(innen,2,E2_INSETS.I(0,0,0,0));
        
//        this.add(new MyE2_Label(new MyE2_String("ID")),1, E2_INSETS.I(2,2,2,2));
//        this.add(map1.rb_Component(new RB_KF(REMINDER_DEF.id_reminder_def)),4, E2_INSETS.I(2,2,2,2));
//
//        this.add(new MyE2_Label(new MyE2_String("ID Tabelle")),1, E2_INSETS.I(2,2,2,2));
//        this.add(map1.rb_Component(new RB_KF(REMINDER_DEF.id_table)),1, E2_INSETS.I(2,2,2,2));
//        this.add(new MyE2_Label(new MyE2_String("Verknüpfte Tabelle")),1, E2_INSETS.I(2,2,2,2));
//        this.add(map1.rb_Component(new RB_KF(REMINDER_DEF.table_name)),2, E2_INSETS.I(2,2,2,2));
//
//        this.add(new MyE2_Label(new MyE2_String("")),2, E2_INSETS.I(2,2,2,2));
//        this.add(new MyE2_Label(new MyE2_String("Verknüpftes Modul")),1, E2_INSETS.I(2,2,2,2));
//        this.add(map1.rb_Component(new RB_KF(REMINDER_DEF.modul_connect_ziel)),2, E2_INSETS.I(2,2,2,2));
//        
//        this.add(new MyE2_Label(new MyE2_String("Angelegt von")),1, E2_INSETS.I(2,2,2,2));
//        this.add(map1.rb_Component(new RB_KF(REMINDER_DEF.id_user_angelegt)),4, E2_INSETS.I(2,2,2,2));
        
        this.add(new Separator(),2,E2_INSETS.I(2,2,2,2));
        
        RB_MaskGrid innen2 = new RB_MaskGrid(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS())
        									._addIF(map1.getRbComponent(REMINDER_DEF.erinnerung_ab),gl)
        									._add(new MyE2_Label(new MyE2_String("Wiederholung alle")),gl_label)
        									._addIF(map1.getRbComponent(REMINDER_DEF.intervall),gl)
        									._add(new MyE2_Label(new MyE2_String("Tage")),gl_label)
          									._addIF(map1.getRbComponent(REMINDER_DEF.erinnerung_bei_anlage),gl)
       										._setSize(150,110,60,130,250)
     									;
       									
//        innen2.add(map1.rb_Component(new RB_KF(REMINDER_DEF.erinnerung_ab)),1, E2_INSETS.I(2,2,2,2));
//        innen2.add(new MyE2_Label(new MyE2_String("Wiederholung alle")),1, E2_INSETS.I(20,2,2,2));
//        innen2.add(map1.rb_Component(new RB_KF(REMINDER_DEF.intervall_tage)),1, E2_INSETS.I(2,2,2,2));
//        innen2.add(new MyE2_Label(new MyE2_String("Tage")),1, E2_INSETS.I(2,2,2,2));

        this.add(new MyE2_Label(new MyE2_String("Erinnerungsmeldung ab:")),gl_label);
        this.add(innen2,1,E2_INSETS.I(0,0,0,0));

//        this.add(new MyE2_Label(new MyE2_String("")),1, E2_INSETS.I(2,2,2,2));
//        this.add(map1.rb_Component(new RB_KF(REMINDER_DEF.erinnerung_bei_anlage)),gl);
//        this.add(new MyE2_Label(new MyE2_String("")),1, E2_INSETS.I(2,2,2,2));

        this.add(new Separator(),2,E2_INSETS.I(2,2,2,2));
        
        this.add(new MyE2_Label(new MyE2_String("Titel")),gl_label);
        this.add(map1.getRbComponent(new RB_KF(REMINDER_DEF.reminder_heading)),gl);
        
        this.add(new MyE2_Label(new MyE2_String("Nachricht")),gl_label);
        this.add(map1.getRbComponent(new RB_KF(REMINDER_DEF.reminder_text)),gl);
        
        this.add(new Separator(),2,E2_INSETS.I(2,2,2,2));

        this.add(new MyE2_Label(new MyE2_String("Benutzer")),gl_label);
        this.add(map1.getRbComponent(REM_CONST.MASK_KEYS.USER_CROSSTABLE.key()),gl);

        this.add(new Separator(),2,E2_INSETS.I(2,2,2,2));
        
        this.add(new MyE2_Label(new MyE2_String("Abgeschlossen am")),gl_label);
        
        RB_MaskGrid innen3 = new RB_MaskGrid(4, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS())
												._addIF(map1.getRbComponent(REMINDER_DEF.abgeschlossen_am), gl)
												._add(new MyE2_Label(new MyE2_String("Abgeschlossen von")),gl_label)
												._addIF(map1.getRbComponent(REMINDER_DEF.id_user_abgeschlossen),gl)
//												._add(new REM_MASK_Button_Abschliessen(map1),gl)
												._add(map1.get_Comp(REM_CONST.MASK_KEYS.BUTTON_ABSCHLUSS.name()),gl)
//												._add(map1.rb_Component(REM_CONST.MASK_KEYS.BUTTON_ABSCHLUSS.key()),gl)
												._setSize(150,150,150,150)
												;
        
        
//        innen3.add(map1.rb_Component(new RB_KF(REMINDER_DEF.abgeschlossen_am)),1, E2_INSETS.I(2,2,2,2));
//        innen3.add(new MyE2_Label(new MyE2_String("Abgeschlossen von")),1, E2_INSETS.I(20,2,2,2));
//        innen3.add(map1.rb_Component(new RB_KF(REMINDER_DEF.id_user_abgeschlossen)),1, E2_INSETS.I(2,2,2,2));
//        innen3.add(new REM_MASK_Button_Abschliessen(map1),1, E2_INSETS.I(2,2,2,2));
//        
        this.add(innen3,1,E2_INSETS.I(0,0,0,0));
        this._setSize(200,900)._style(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
        this.setWidth(new Extent(1100));
    }
  
    
}
 
