package rohstoff.Echo2BusinessLogic.AH7.RELATION;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.basics4project.DB_ENUMS.AH7_STEUERDATEI;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.AH7.AH7__COMPONENT_KEYS;
import rohstoff.Echo2BusinessLogic.AH7.AH7_lComp_statusButton;

public class AH7_LIST_ComponentMap extends E2_ComponentMAP {

	public AH7_LIST_ComponentMap(E2_NavigationList navilist) throws myException
	{
		super(new AH7_LIST_SqlFieldMAP());
		
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		
		this.add_Component(AH7_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(AH7_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));
		this.add_Component("KEY"+AH7_STEUERDATEI.status_relation.fn(), 					new AH7_lComp_statusButton(AH7_STEUERDATEI.status_relation,navilist,true,S.ms("Status")),		
																																		S.ms("Status"));
		this.add_Component(AH7__COMPONENT_KEYS.LISTKEY_JUMP_TO_FUHRENZENTRALE.name(),   new AH7_LIST_JUMPER_TO_Fuhre(), S.ms("Sprung in Fuhrenzentrale"));
				
		
		//hier kommen die Felder
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(AH7_STEUERDATEI.locked)), 		S.ms("Sperre"));

		//this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_("P_"+AH7_PROFIL.bezeichnung.fn()),true), S.ms("Profil zugeordnet"));
		this.add_Component(AH7__COMPONENT_KEYS.LISTKEY_SHOW_PROFIL.name(), 				new AH7_LIST_ShowAndEditProfil(),	S.ms("Profil zugeordnet"));
		
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(AH7_STEUERDATEI.id_adresse_geo_start)), S.ms("ID Adr. Start"));
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_("LS_"+LAND.laendercode.fn())), S.ms("S-Land"));
		
		this.add_Component("KEY"+AH7_STEUERDATEI.id_adresse_geo_start.fn(), new AH7_LIST_ShowAdressStartZiel(true)._setField(AH7_STEUERDATEI.id_adresse_geo_start), S.ms("Startadresse"));

		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(AH7_STEUERDATEI.id_adresse_geo_ziel)), S.ms("ID Adr. Ziel"));
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_("LZ_"+LAND.laendercode.fn())), S.ms("Z-Land"));

		this.add_Component("KEY"+AH7_STEUERDATEI.id_adresse_geo_ziel.fn(),  new AH7_LIST_ShowAdressStartZiel(false)._setField(AH7_STEUERDATEI.id_adresse_geo_ziel), S.ms("Zieladresse"));
		
		this.add_Component("KEY"+AH7_STEUERDATEI.id_1_verbr_veranlasser.fn(),new AH7_LIST_ShowAdressAh7Field(AH7_STEUERDATEI.id_1_verbr_veranlasser), 
									new MyE2_String("Kontrollblatt: <Feld_1:_Veranlasser>"));

		this.add_Component("KEY"+AH7_STEUERDATEI.id_1_import_empfaenger.fn(),new AH7_LIST_ShowAdressAh7Field(AH7_STEUERDATEI.id_1_import_empfaenger), 
									new MyE2_String("Kontrollblatt: <Feld_2:_Importeur>"));
		
		this.add_Component("KEY"+AH7_STEUERDATEI.id_1_abfallerzeuger.fn(),new AH7_LIST_ShowAdressAh7Field(AH7_STEUERDATEI.id_1_abfallerzeuger), 
									new MyE2_String("Kontrollblatt: <Feld_6:_Erzeuger>"));
		
		this.add_Component("KEY"+AH7_STEUERDATEI.id_1_verwertungsanlage.fn(),new AH7_LIST_ShowAdressAh7Field(AH7_STEUERDATEI.id_1_verwertungsanlage), 
									new MyE2_String("Kontrollblatt: <Feld_7:_Verwertung>"));
		
		
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("DRUCKE_BLATT2")), new MyE2_String("Blatt 2"));
		this.add_Component("KEY"+AH7_STEUERDATEI.id_2_verbr_veranlasser.fn(),new AH7_LIST_ShowAdressAh7Field(AH7_STEUERDATEI.id_2_verbr_veranlasser), 
									new MyE2_String("Abladestelle (1) Veranlasser"));

		this.add_Component("KEY"+AH7_STEUERDATEI.id_2_import_empfaenger.fn(),new AH7_LIST_ShowAdressAh7Field(AH7_STEUERDATEI.id_2_import_empfaenger), 
									new MyE2_String("Abladestelle (2) Importeur"));

		this.add_Component("KEY"+AH7_STEUERDATEI.id_2_abfallerzeuger.fn(),new AH7_LIST_ShowAdressAh7Field(AH7_STEUERDATEI.id_2_abfallerzeuger), 
									new MyE2_String("Abladestelle (6) Erzeuger"));

		this.add_Component("KEY"+AH7_STEUERDATEI.id_2_verwertungsanlage.fn(),new AH7_LIST_ShowAdressAh7Field(AH7_STEUERDATEI.id_2_verwertungsanlage), 
									new MyE2_String("Abladestelle (7) Verwertung"));

		
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_("DRUCKE_BLATT3")), new MyE2_String("Blatt 3"));
		this.add_Component("KEY"+AH7_STEUERDATEI.id_3_verbr_veranlasser.fn(),new AH7_LIST_ShowAdressAh7Field(AH7_STEUERDATEI.id_3_verbr_veranlasser), 
									new MyE2_String("Ladestelle (1) Veranlasser"));

		this.add_Component("KEY"+AH7_STEUERDATEI.id_3_import_empfaenger.fn(),new AH7_LIST_ShowAdressAh7Field(AH7_STEUERDATEI.id_3_import_empfaenger), 
									new MyE2_String("Ladestelle (2) Importeur"));

		this.add_Component("KEY"+AH7_STEUERDATEI.id_3_abfallerzeuger.fn(),new AH7_LIST_ShowAdressAh7Field(AH7_STEUERDATEI.id_3_abfallerzeuger), 
									new MyE2_String("Ladestelle (6) Erzeuger"));

		this.add_Component("KEY"+AH7_STEUERDATEI.id_3_verwertungsanlage.fn(),new AH7_LIST_ShowAdressAh7Field(AH7_STEUERDATEI.id_3_verwertungsanlage), 
									new MyE2_String("Ladestelle (7) Verwertung"));
		
		this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(AH7_STEUERDATEI.id_ah7_steuerdatei)), S.ms("ID"));

		
		
		RB_gld gldTitleCenter = 	new RB_gld()._center_top()._ins(2,2,3,2)._col(new E2_ColorDark());
		RB_gld gldElementCenter = 	new RB_gld()._center_top()._ins(2,2,3,2)._col(new E2_ColorBase());
		RB_gld gldTitleLeft = 		new RB_gld()._left_top()._ins(2,2,3,2)._col(new E2_ColorDark());
		RB_gld gldElementLeft = 	new RB_gld()._left_top()._ins(2,2,3,2)._col(new E2_ColorBase());
		RB_gld gldTitleRight = 		new RB_gld()._right_top()._ins(2,2,3,2)._col(new E2_ColorDark());
		RB_gld gldElementRight = 	new RB_gld()._right_top()._ins(2,2,3,2)._col(new E2_ColorBase());
		
		
		String[] centered = {AH7__COMPONENT_KEYS.LISTKEY_JUMP_TO_FUHRENZENTRALE.name(),"KEY"+AH7_STEUERDATEI.status_relation.fn(),AH7_STEUERDATEI.locked.fn()
							,"LS_"+LAND.laendercode.fn(),"LZ_"+LAND.laendercode.fn(),AH7_STEUERDATEI.drucke_blatt2.fn(), AH7_STEUERDATEI.drucke_blatt3.fn()
							};
		
		String[] right =    {AH7_STEUERDATEI.id_adresse_geo_start.fn(),AH7_STEUERDATEI.id_adresse_geo_ziel.fn()};
		
		this._setLayoutTitles(gldTitleCenter, 		centered);
		this._setLayoutElements(gldElementCenter, 	centered);
		this._setLayoutTitles(gldTitleRight, 		right);
		this._setLayoutElements(gldElementRight, 	right);
		this._setLayoutTitlesAllBut(gldTitleLeft, 	bibARR.concatenate(centered, right));
		this._setLayoutElementsAllBut(gldElementLeft, 	bibARR.concatenate(centered, right));
		
		this._setLineWrapListHeader(true, 	 "KEY"+AH7_STEUERDATEI.id_adresse_geo_start.fn()
											,"KEY"+AH7_STEUERDATEI.id_adresse_geo_ziel.fn()
											,"KEY"+AH7_STEUERDATEI.id_1_verbr_veranlasser.fn()
											,"KEY"+AH7_STEUERDATEI.id_1_import_empfaenger.fn()
											,"KEY"+AH7_STEUERDATEI.id_1_abfallerzeuger.fn()
											,"KEY"+AH7_STEUERDATEI.id_1_verwertungsanlage.fn()
											,"KEY"+AH7_STEUERDATEI.id_2_verbr_veranlasser.fn()
											,"KEY"+AH7_STEUERDATEI.id_2_import_empfaenger.fn()
											,"KEY"+AH7_STEUERDATEI.id_2_abfallerzeuger.fn()
											,"KEY"+AH7_STEUERDATEI.id_2_verwertungsanlage.fn()
											,"KEY"+AH7_STEUERDATEI.id_3_verbr_veranlasser.fn()
											,"KEY"+AH7_STEUERDATEI.id_3_import_empfaenger.fn()
											,"KEY"+AH7_STEUERDATEI.id_3_abfallerzeuger.fn()
											,"KEY"+AH7_STEUERDATEI.id_3_verwertungsanlage.fn()
											,AH7_STEUERDATEI.id_adresse_geo_start.fn()
											,AH7_STEUERDATEI.id_adresse_geo_ziel.fn()
									);
		
		
		this._setColExtent(new Extent(300), "KEY"+AH7_STEUERDATEI.id_adresse_geo_start.fn()
											,"KEY"+AH7_STEUERDATEI.id_adresse_geo_ziel.fn()
											,"KEY"+AH7_STEUERDATEI.id_1_verbr_veranlasser.fn()
											,"KEY"+AH7_STEUERDATEI.id_1_import_empfaenger.fn()
											,"KEY"+AH7_STEUERDATEI.id_1_abfallerzeuger.fn()
											,"KEY"+AH7_STEUERDATEI.id_1_verwertungsanlage.fn()
											,"KEY"+AH7_STEUERDATEI.id_2_verbr_veranlasser.fn()
											,"KEY"+AH7_STEUERDATEI.id_2_import_empfaenger.fn()
											,"KEY"+AH7_STEUERDATEI.id_2_abfallerzeuger.fn()
											,"KEY"+AH7_STEUERDATEI.id_2_verwertungsanlage.fn()
											,"KEY"+AH7_STEUERDATEI.id_3_verbr_veranlasser.fn()
											,"KEY"+AH7_STEUERDATEI.id_3_import_empfaenger.fn()
											,"KEY"+AH7_STEUERDATEI.id_3_abfallerzeuger.fn()
											,"KEY"+AH7_STEUERDATEI.id_3_verwertungsanlage.fn()
									);

		this._setColExtent(new Extent(300), AH7__COMPONENT_KEYS.LISTKEY_SHOW_PROFIL.name());
		
		
		this
			._setHeaderComponent(AH7__COMPONENT_KEYS.LISTKEY_JUMP_TO_FUHRENZENTRALE.name(), new singleLable("Fuhren"))
			._setHeaderComponent("KEY"+AH7_STEUERDATEI.id_1_verbr_veranlasser.fn(), new doubleLabel("AH7 Kontrollblatt:", "Feld 1: Veranlasser"))
			._setHeaderComponent("KEY"+AH7_STEUERDATEI.id_1_import_empfaenger.fn(), new doubleLabel("AH7 Kontrollblatt:", "Feld 2: Importeur/Empfänger"))
			._setHeaderComponent("KEY"+AH7_STEUERDATEI.id_1_abfallerzeuger.fn(), 	new doubleLabel("AH7 Kontrollblatt:", "Feld 6: Abfallerzeuger"))
			._setHeaderComponent("KEY"+AH7_STEUERDATEI.id_1_verwertungsanlage.fn(), new doubleLabel("AH7 Kontrollblatt:", "Feld 7: Verwertungsanlage"))
			
			._setHeaderComponent("KEY"+AH7_STEUERDATEI.id_2_verbr_veranlasser.fn(), new doubleLabel("AH7 für Abladestelle:", "Feld 1: Veranlasser"))
			._setHeaderComponent("KEY"+AH7_STEUERDATEI.id_2_import_empfaenger.fn(), new doubleLabel("AH7 für Abladestelle:", "Feld 2: Importeur/Empfänger"))
			._setHeaderComponent("KEY"+AH7_STEUERDATEI.id_2_abfallerzeuger.fn(), 	new doubleLabel("AH7 für Abladestelle:", "Feld 6: Abfallerzeuger"))
			._setHeaderComponent("KEY"+AH7_STEUERDATEI.id_2_verwertungsanlage.fn(), new doubleLabel("AH7 für Abladestelle:", "Feld 7: Verwertungsanlage"))

			._setHeaderComponent("KEY"+AH7_STEUERDATEI.id_3_verbr_veranlasser.fn(), new doubleLabel("AH7 für Ladestelle:", "Feld 1: Veranlasser"))
			._setHeaderComponent("KEY"+AH7_STEUERDATEI.id_3_import_empfaenger.fn(), new doubleLabel("AH7 für Ladestelle:", "Feld 2: Importeur/Empfänger"))
			._setHeaderComponent("KEY"+AH7_STEUERDATEI.id_3_abfallerzeuger.fn(), 	new doubleLabel("AH7 für Ladestelle:", "Feld 6: Abfallerzeuger"))
			._setHeaderComponent("KEY"+AH7_STEUERDATEI.id_3_verwertungsanlage.fn(), new doubleLabel("AH7 für Ladestelle:", "Feld 7: Verwertungsanlage"))
			;
			
		this._setToolTipToHeader(new MyE2_String("Datensatz wurde manuell geändert und nicht mehr automatisch aktualisiert"), AH7_STEUERDATEI.locked.fn());
		this.set_oSubQueryAgent(new AH7_LIST_FORMATING_Agent());
	}
	
	
	private class doubleLabel extends E2_Grid{
		public doubleLabel(String zeile1, String zeile2) {
			super();
			this._s(1)._a(new RB_lab()._t(zeile1)._lwn(), new RB_gld()._ins(0))._a(new RB_lab()._t(zeile2)._lwn(),new RB_gld()._ins(0,2,0,0));
		}
	}
	
	private class singleLable extends E2_Grid{
		public singleLable(String zeile1) throws myException {
			super();
			this._s(1)._a(new RB_lab()._t(zeile1)._lwn(), new RB_gld()._ins(0));
		}
	}
	

}
