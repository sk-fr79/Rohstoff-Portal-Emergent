package rohstoff.Echo2BusinessLogic.AH7.PROFIL;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_Factory4Records;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.basics4project.DB_ENUMS.AH7_PROFIL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_AH7_PROFIL;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.AH7.AH7_lComp_statusButton;
import rohstoff.Echo2BusinessLogic.AH7.PROFIL.AH7P_CONST.AH7P_NAMES;

public class AH7P_LIST_ComponentMap extends E2_ComponentMAP {
	
    public AH7P_LIST_ComponentMap(E2_NavigationList navilist) throws myException   {
        
    	super(new AH7P_LIST_SqlFieldMAP());
        
        SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
        
        this.add_Component(AH7P_NAMES.CHECKBOX_LISTE.db_val(),    	new E2_CheckBoxForList(),new MyE2_String("?"));
        this.add_Component(AH7P_NAMES.MARKER_LISTE.db_val(),   		 new E2_ButtonListMarker(),new MyE2_String("?"));
        //hier kommen die Felder  
		this.add_Component("KEY"+AH7_PROFIL.status_relation.fn(), 	new AH7_lComp_statusButton(AH7_PROFIL.status_relation,navilist,false,S.ms("Autom. vergeb. Status")), S.ms("Autom. vergeb. Status"));

        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(AH7_PROFIL.bezeichnung),true),     					new MyE2_String("Bezeichnung"));

        this.add_Component("KEY"+AH7_PROFIL.start_inland.fn(), 			new AH7P_LCompShowStartInland(),     		new MyE2_String("Start im .."));
        this.add_Component("KEY"+AH7_PROFIL.start_eigenes_lager.fn(),	new AH7P_LCompShowStartEigenesLager(),     	new MyE2_String("Besitzer Startlager"));
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(AH7_PROFIL.ah7_quelle_sicher),true),     				new MyE2_String("Sichere Quelle"));
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(AH7_PROFIL.startlager_fremdbesitz),true),     				new MyE2_String("Quelle Dritt-Besitz"));
        this.add_Component("KEY"+AH7_PROFIL.ziel_inland.fn(), 			new AH7P_LCompShowZielInland(),     		new MyE2_String("Ziel im .."));
        this.add_Component("KEY"+AH7_PROFIL.ziel_eigenes_lager.fn(),	new AH7P_LCompShowZielEigenesLager(),     	new MyE2_String("Besitzer Ziellager"));
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(AH7_PROFIL.ah7_ziel_sicher),true),     				new MyE2_String("Sicheres Ziel"));
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(AH7_PROFIL.ziellager_fremdbesitz),true),     				new MyE2_String("Ziel Dritt-Besitz"));

        this.add_Component("KEY"+AH7_PROFIL.verbr_veranlasser_1.fn(), 	new AH7P_LCompShowStationsDef(AH7_PROFIL.verbr_veranlasser_1),     new MyE2_String("Kontrolle (1) Veranlasser "));
        this.add_Component("KEY"+AH7_PROFIL.import_empfaenger_1.fn(), 	new AH7P_LCompShowStationsDef(AH7_PROFIL.import_empfaenger_1),     new MyE2_String("Kontrolle (2) Importeur"));
        this.add_Component("KEY"+AH7_PROFIL.abfallerzeuger_1.fn(), 		new AH7P_LCompShowStationsDef(AH7_PROFIL.abfallerzeuger_1),     	new MyE2_String("Kontrolle (6) Erzeuger"));
        this.add_Component("KEY"+AH7_PROFIL.verwertungsanlage_1.fn(), 	new AH7P_LCompShowStationsDef(AH7_PROFIL.verwertungsanlage_1),     new MyE2_String("Kontrolle (7) Verwertung"));

        this.add_Component("KEY"+AH7_PROFIL.verbr_veranlasser_2.fn(), 	new AH7P_LCompShowStationsDef(AH7_PROFIL.verbr_veranlasser_2),     new MyE2_String("Abladestelle (1) Veranlasser "));
        this.add_Component("KEY"+AH7_PROFIL.import_empfaenger_2.fn(), 	new AH7P_LCompShowStationsDef(AH7_PROFIL.import_empfaenger_2),     new MyE2_String("Abladestelle (2) Importeur"));
        this.add_Component("KEY"+AH7_PROFIL.abfallerzeuger_2.fn(), 		new AH7P_LCompShowStationsDef(AH7_PROFIL.abfallerzeuger_2),     	new MyE2_String("Abladestelle (6) Erzeuger"));
        this.add_Component("KEY"+AH7_PROFIL.verwertungsanlage_2.fn(), 	new AH7P_LCompShowStationsDef(AH7_PROFIL.verwertungsanlage_2),     new MyE2_String("Abladestelle (7) Verwertung"));

        this.add_Component("KEY"+AH7_PROFIL.verbr_veranlasser_3.fn(), 	new AH7P_LCompShowStationsDef(AH7_PROFIL.verbr_veranlasser_3),     new MyE2_String("Ladestelle (1) Veranlasser "));
        this.add_Component("KEY"+AH7_PROFIL.import_empfaenger_3.fn(), 	new AH7P_LCompShowStationsDef(AH7_PROFIL.import_empfaenger_3),     new MyE2_String("Ladestelle (2) Importeur"));
        this.add_Component("KEY"+AH7_PROFIL.abfallerzeuger_3.fn(), 		new AH7P_LCompShowStationsDef(AH7_PROFIL.abfallerzeuger_3),     	new MyE2_String("Ladestelle (6) Erzeuger"));
        this.add_Component("KEY"+AH7_PROFIL.verwertungsanlage_3.fn(), 	new AH7P_LCompShowStationsDef(AH7_PROFIL.verwertungsanlage_3),     new MyE2_String("Ladestelle (7) Verwertung"));

        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(AH7_PROFIL.id_ah7_profil),true),     new MyE2_String("ID"));
        
        
		this._setLineWrapListHeader(true, 	 
											"KEY"+AH7_PROFIL.start_inland.fn()
											,"KEY"+AH7_PROFIL.start_eigenes_lager.fn()
											,"KEY"+AH7_PROFIL.ziel_inland.fn()
											,"KEY"+AH7_PROFIL.ziel_eigenes_lager.fn()
											,"KEY"+AH7_PROFIL.verbr_veranlasser_1.fn()
											,"KEY"+AH7_PROFIL.import_empfaenger_1.fn()
											,"KEY"+AH7_PROFIL.abfallerzeuger_1.fn()
											,"KEY"+AH7_PROFIL.verwertungsanlage_2.fn()
											,"KEY"+AH7_PROFIL.verbr_veranlasser_2.fn()
											,"KEY"+AH7_PROFIL.import_empfaenger_2.fn()
											,"KEY"+AH7_PROFIL.abfallerzeuger_2.fn()
											,"KEY"+AH7_PROFIL.verwertungsanlage_3.fn()
											,"KEY"+AH7_PROFIL.verbr_veranlasser_3.fn()
											,"KEY"+AH7_PROFIL.import_empfaenger_3.fn()
											,"KEY"+AH7_PROFIL.abfallerzeuger_3.fn()
											,"KEY"+AH7_PROFIL.verwertungsanlage_1.fn()
											,AH7_PROFIL.ah7_quelle_sicher.fn()
											,AH7_PROFIL.ah7_ziel_sicher.fn()
											,AH7_PROFIL.bezeichnung.fn()
											,"KEY"+AH7_PROFIL.status_relation.fn()
											,AH7_PROFIL.startlager_fremdbesitz.fn()
											,AH7_PROFIL.ziellager_fremdbesitz.fn()
											);
		String[]   leftCols = new String[16] ;

		int i=0;
		leftCols[i++] = AH7_PROFIL.bezeichnung.fn();
		leftCols[i++] = "KEY"+AH7_PROFIL.start_eigenes_lager.fn();
		leftCols[i++] = "KEY"+AH7_PROFIL.ziel_eigenes_lager.fn();
		leftCols[i++] = "KEY"+AH7_PROFIL.verbr_veranlasser_1.fn();
		leftCols[i++] = "KEY"+AH7_PROFIL.import_empfaenger_1.fn();
		leftCols[i++] = "KEY"+AH7_PROFIL.abfallerzeuger_1.fn();
		leftCols[i++] = "KEY"+AH7_PROFIL.verwertungsanlage_1.fn();
		leftCols[i++] = "KEY"+AH7_PROFIL.verbr_veranlasser_2.fn();
		leftCols[i++] = "KEY"+AH7_PROFIL.import_empfaenger_2.fn();
		leftCols[i++] = "KEY"+AH7_PROFIL.abfallerzeuger_2.fn();
		leftCols[i++] = "KEY"+AH7_PROFIL.verwertungsanlage_2.fn();
		leftCols[i++] = "KEY"+AH7_PROFIL.verbr_veranlasser_3.fn();
		leftCols[i++] = "KEY"+AH7_PROFIL.import_empfaenger_3.fn();
		leftCols[i++] = "KEY"+AH7_PROFIL.abfallerzeuger_3.fn();
		leftCols[i++] = "KEY"+AH7_PROFIL.verwertungsanlage_3.fn();
		leftCols[i++] = "KEY"+AH7_PROFIL.verwertungsanlage_1.fn();
				
		RB_gld gldElementLeft = 	new RB_gld()._left_top()._ins(2,4,2,2)._col(new E2_ColorBase());
		this._setLayoutElements(gldElementLeft,  			leftCols); 	 
		
		RB_gld gldTitelLeft = 	new RB_gld()._left_top()._ins(2,2,1,1)._col(new E2_ColorDark());
		this._setLayoutTitles(gldTitelLeft, 	 			leftCols);

		RB_gld gldElementCenter = 	new RB_gld()._center_top()._ins(2,4,2,2)._col(new E2_ColorBase());
		this._setLayoutElementsAllBut(gldElementCenter, 	leftCols);
		
		RB_gld gldTitelCenter = 	new RB_gld()._center_top()._ins(1,2,1,1)._col(new E2_ColorDark());
		this._setLayoutTitlesAllBut(gldTitelCenter, 	 	leftCols);
		
		
		
		this
			._setHeaderComponent("KEY"+AH7_PROFIL.verbr_veranlasser_1.fn(), new doubleLabel("AH7 Kontrollblatt:", "Feld 1: Veranlasser"))
			._setHeaderComponent("KEY"+AH7_PROFIL.import_empfaenger_1.fn(), new doubleLabel("AH7 Kontrollblatt:", "Feld 2: Importeur/Empfänger"))
			._setHeaderComponent("KEY"+AH7_PROFIL.abfallerzeuger_1.fn(), 	new doubleLabel("AH7 Kontrollblatt:", "Feld 6: Abfallerzeuger"))
			._setHeaderComponent("KEY"+AH7_PROFIL.verwertungsanlage_1.fn(), new doubleLabel("AH7 Kontrollblatt:", "Feld 7: Verwertungsanlage"))
			
			._setHeaderComponent("KEY"+AH7_PROFIL.verbr_veranlasser_2.fn(), new doubleLabel("AH7 für Abladestelle:", "Feld 1: Veranlasser"))
			._setHeaderComponent("KEY"+AH7_PROFIL.import_empfaenger_2.fn(), new doubleLabel("AH7 für Abladestelle:", "Feld 2: Importeur/Empfänger"))
			._setHeaderComponent("KEY"+AH7_PROFIL.abfallerzeuger_2.fn(), 	new doubleLabel("AH7 für Abladestelle:", "Feld 6: Abfallerzeuger"))
			._setHeaderComponent("KEY"+AH7_PROFIL.verwertungsanlage_2.fn(), new doubleLabel("AH7 für Abladestelle:", "Feld 7: Verwertungsanlage"))

			._setHeaderComponent("KEY"+AH7_PROFIL.verbr_veranlasser_3.fn(), new doubleLabel("AH7 für Ladestelle:", "Feld 1: Veranlasser"))
			._setHeaderComponent("KEY"+AH7_PROFIL.import_empfaenger_3.fn(), new doubleLabel("AH7 für Ladestelle:", "Feld 2: Importeur/Empfänger"))
			._setHeaderComponent("KEY"+AH7_PROFIL.abfallerzeuger_3.fn(), 	new doubleLabel("AH7 für Ladestelle:", "Feld 6: Abfallerzeuger"))
			._setHeaderComponent("KEY"+AH7_PROFIL.verwertungsanlage_3.fn(), new doubleLabel("AH7 für Ladestelle:", "Feld 7: Verwertungsanlage"))
			;

		
		
		
		this.get__Comp(AH7_PROFIL.bezeichnung).EXT().set_oColExtent(new Extent(200));
		this.get__Comp(AH7_PROFIL.ah7_quelle_sicher).EXT().set_oColExtent(new Extent(50));
		this.get__Comp(AH7_PROFIL.ah7_ziel_sicher).EXT().set_oColExtent(new Extent(50));
        
        this.set_Factory4Records(new factory4Records());
    }

    
    private class factory4Records extends E2_ComponentMAP_Factory4Records {
        @Override
        public MyRECORD_IF_RECORDS get_RECORD(String cID_MAINTABLE) throws myException {
            return new RECORD_AH7_PROFIL(cID_MAINTABLE);
        }
    }
    
	private class doubleLabel extends E2_Grid{
		public doubleLabel(String zeile1, String zeile2) {
			super();
			this._s(1)._a(new RB_lab()._t(zeile1)._lwn(), new RB_gld()._ins(0))._a(new RB_lab()._t(zeile2)._lwn(),new RB_gld()._ins(0,2,0,0));
		}
	}
	
    
}
 
