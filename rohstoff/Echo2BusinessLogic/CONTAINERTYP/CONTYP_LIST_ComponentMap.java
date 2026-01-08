 
package rohstoff.Echo2BusinessLogic.CONTAINERTYP;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
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
import panter.gmbh.basics4project.DB_ENUMS.CONTAINERTYP;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_CONTAINERTYP;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
public class CONTYP_LIST_ComponentMap extends E2_ComponentMAP  {
    public CONTYP_LIST_ComponentMap() throws myException  {
        super(new CONTYP_LIST_SqlFieldMAP());
        
        SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
        
        this.add_Component(CONTYP_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,    new E2_CheckBoxForList(),new MyE2_String("?"));
        this.add_Component(CONTYP_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,    new E2_ButtonListMarker(),new MyE2_String("?"));
        //hier kommen die Felder  
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(CONTAINERTYP.abroll)),     	new MyE2_String("Abroll"));
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(CONTAINERTYP.absetz)),     	new MyE2_String("Absetz"));
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(CONTAINERTYP.dicht)),     		new MyE2_String("Dicht"));
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(CONTAINERTYP.ablauf)), 		new MyE2_String("Ablauf"));
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(CONTAINERTYP.symmetrisch)),    new MyE2_String("Sym"));
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(CONTAINERTYP.deckel)),     	new MyE2_String("Deckel"));
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(CONTAINERTYP.plane)),     		new MyE2_String("Plane"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(CONTAINERTYP.containerinhalt),true),     new MyE2_String("Inhalt"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(CONTAINERTYP.beschreibung),true),     new MyE2_String("Beschreibung"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(CONTAINERTYP.kurzbezeichnung),true),     new MyE2_String("Kurzbezeichnung"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(CONTAINERTYP.kuerzel),true),     new MyE2_String("Kürzel"));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(CONTAINERTYP.id_containertyp),true),     new MyE2_String("IdContainertyp"));
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(CONTAINERTYP.aktiv)),     		new MyE2_String("Aktiv"));
        
        
        this._setLineWrapListHeader(true 
                                  ,CONTAINERTYP.ablauf.fn()
                                  ,CONTAINERTYP.abroll.fn()
                                  ,CONTAINERTYP.absetz.fn()
                                  ,CONTAINERTYP.beschreibung.fn()
                                  ,CONTAINERTYP.containerinhalt.fn()
                                  ,CONTAINERTYP.deckel.fn()
                                  ,CONTAINERTYP.dicht.fn()
                                  ,CONTAINERTYP.id_containertyp.fn()
                                  ,CONTAINERTYP.kurzbezeichnung.fn()
                                  ,CONTAINERTYP.kuerzel.fn()
                                  ,CONTAINERTYP.plane.fn()
                                  ,CONTAINERTYP.symmetrisch.fn()
        );
        
        Color  colorElement = new E2_ColorBase();
       	Insets insElement = E2_INSETS.I(5,3,2,3);
        RB_gld gldElementLeft  = 	new RB_gld()._left_top()._ins(insElement)._col(colorElement);
        RB_gld gldElementRight = 	new RB_gld()._right_top()._ins(insElement)._col(colorElement);
       	RB_gld gldElementCenter = 	new RB_gld()._center_top()._ins(insElement)._col(colorElement);
       	
       	this._setLayoutElements(gldElementCenter
                                 ,CONTAINERTYP.ablauf.fn()
                                 ,CONTAINERTYP.abroll.fn()
                                 ,CONTAINERTYP.absetz.fn()
                                 ,CONTAINERTYP.deckel.fn()
                                 ,CONTAINERTYP.dicht.fn()
                                 ,CONTAINERTYP.plane.fn()
                                 ,CONTAINERTYP.symmetrisch.fn()
                                 ,CONTAINERTYP.aktiv.fn()
      	);
      	
       	this._setLayoutElements(gldElementLeft
       			,CONTAINERTYP.beschreibung.fn()
       			,CONTAINERTYP.kurzbezeichnung.fn()
       			,CONTAINERTYP.kuerzel.fn()
       			);
       	this._setLayoutElements(gldElementRight
       			,CONTAINERTYP.containerinhalt.fn()
       			,CONTAINERTYP.id_containertyp.fn()
       			);

       	
       	Color colorTitle = new E2_ColorDark();
       	Insets insTitle = E2_INSETS.I(5,2,2,2);
      	RB_gld gldTitelCenter 	= 	new RB_gld()._center_top()._ins(insTitle)._col(colorTitle);
      	RB_gld gldTitelLeft 	= 	new RB_gld()._left_top()._ins(insTitle)._col(colorTitle);
      	RB_gld gldTitelRight 	= 	new RB_gld()._right_top()._ins(insTitle)._col(colorTitle);
       	
      	this._setLayoutTitles(gldTitelCenter
                                 ,CONTAINERTYP.ablauf.fn()
                                 ,CONTAINERTYP.abroll.fn()
                                 ,CONTAINERTYP.absetz.fn()
                                 ,CONTAINERTYP.deckel.fn()
                                 ,CONTAINERTYP.dicht.fn()
                                 ,CONTAINERTYP.plane.fn()
                                 ,CONTAINERTYP.symmetrisch.fn()
                                 ,CONTAINERTYP.aktiv.fn()
      	);
      	this._setLayoutTitles(gldTitelLeft
      			,CONTAINERTYP.beschreibung.fn()
      			,CONTAINERTYP.kurzbezeichnung.fn()
      			,CONTAINERTYP.kuerzel.fn()
      			);
      	
      	this._setLayoutTitles(gldTitelRight
      			,CONTAINERTYP.containerinhalt.fn()
      			,CONTAINERTYP.id_containertyp.fn()
      			);
      	
        this.set_oSubQueryAgent(new CONTYP_LIST_FORMATING_Agent());
        	
        this.set_Factory4Records(new factory4Records());
    }
    private class factory4Records extends E2_ComponentMAP_Factory4Records {
        @Override
        public MyRECORD_IF_RECORDS get_RECORD(String cID_MAINTABLE) throws myException {
            return new RECORD_CONTAINERTYP(cID_MAINTABLE);
        }
        
    }
    
    
}
 
