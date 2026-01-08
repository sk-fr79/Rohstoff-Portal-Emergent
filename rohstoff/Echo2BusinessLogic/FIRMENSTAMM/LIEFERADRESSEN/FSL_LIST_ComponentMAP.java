package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.LIEFERADRESSEN;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.DB_Component_USER_DROPDOWN_NEW;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentColumn;
import panter.gmbh.Echo2.components.DB.MyE2_DB_MultiComponentGrid;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.GPS.FS_ListBtn_ShowGeopoint_Heading;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.GPS.FS_ListCompShowGeopoint;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.GPS.FS_ListCompShowGeopointOSM;

public class FSL_LIST_ComponentMAP extends E2_ComponentMAP
{


	
	public FSL_LIST_ComponentMAP() throws myException
	{
		super(new FSL_LIST_SQLFieldMap());
		
		this.add_Component("NAME_OF_CHECKBOX_IN_LIST", new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component("NAME_OF_LISTMARKER_IN_LIST", new E2_ButtonListMarker(),new MyE2_String("?"));
		
		SQLFieldMAP oSQLFM = this.get_oSQLFieldMAP();

		//2017-02-10: geocoding aufruf 
		if(ENUM_MANDANT_DECISION.USE_GEOPOINT_SHOW.is_YES()) {
			
			this.add_Component(FSL__CONST.LIST_GEOCODE_LIEF, new FS_ListCompShowGeopoint(), new MyE2_String("G"));
			this.add_Component(FSL__CONST.LIST_GEOCODE_LIEF_OSM, new FS_ListCompShowGeopointOSM(), new MyE2_String("OSM"));
			
			// Heading Google-Map-Spalte
			FS_ListBtn_ShowGeopoint_Heading oBtnHeadingShowAdrs = new FS_ListBtn_ShowGeopoint_Heading(this);
			this.get__Comp(FSL__CONST.LIST_GEOCODE_LIEF).EXT().set_oCompTitleInList(oBtnHeadingShowAdrs);
			oBtnHeadingShowAdrs.EXT().set_oLayout_ListTitelElement(LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_0_0_0_0));
			
			// Heading OSM-Map Spalte
			FS_ListBtn_ShowGeopoint_Heading oBtnHeadingShowAdrsOSM = new FS_ListBtn_ShowGeopoint_Heading(this);
			this.get__Comp(FSL__CONST.LIST_GEOCODE_LIEF_OSM).EXT().set_oCompTitleInList(oBtnHeadingShowAdrsOSM);
			oBtnHeadingShowAdrsOSM.EXT().set_oLayout_ListTitelElement(LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_0_0_0_0));
		}
		
		MyE2_DB_MultiComponentColumn oMulti_NAME = new MyE2_DB_MultiComponentColumn();
		oMulti_NAME.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("NAME1")),new MyE2_String("Name 1"),null);
		oMulti_NAME.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("NAME2")),new MyE2_String("Name 2"),null);
		
		//spalte fuer kontroll-info / kontrollbenutzer
		MyE2_DB_MultiComponentGrid oMulti_KONTROLL = new MyE2_DB_MultiComponentGrid(1);
		oMulti_KONTROLL.add_Component(new DB_Component_USER_DROPDOWN_NEW(oSQLFM.get_(RECORD_ADRESSE.FIELD__ID_USER_LAGER_SACHBEARB), true, 200), 
																			new MyE2_String("LAGER: Sachbearbeiter"),null);
		oMulti_KONTROLL.add_Component(new DB_Component_USER_DROPDOWN_NEW(oSQLFM.get_(RECORD_ADRESSE.FIELD__ID_USER_LAGER_HAENDLER), true, 200), 
																			new MyE2_String("LAGER: zuständiger Händler"),null);
		oMulti_KONTROLL.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_(RECORD_ADRESSE.FIELD__LAGER_KONTROLLINFO),true), new MyE2_String("LAGER: Verwaltungsinfo"),null);

		
		this.add_Component("COLUMFIELD_NAME",oMulti_NAME,new MyE2_String("Namen"));
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("LAND")),new MyE2_String("Land"));
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_(ADRESSE.plz)),new MyE2_String("PLZ"));
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("ORT")),new MyE2_String("Ort"));
		

		//2015-04-22: checkbox in list aktiv machen
		this.add_Component(new FSL_LIST_COMP_CheckboxToggleActive(oSQLFM.get_("AKTIV")),new MyE2_String("Aktiv"));       
		
		this.add_Component(new MyE2_DB_TextArea(oSQLFM.get_("U_BESCHREIBUNG")),new MyE2_String("Beschreibung"));

		this.add_Component("MULTI_COL_KONTROLLE",oMulti_KONTROLL,new MyE2_String("Verwaltung/Kontrolle"));
		
		this.add_Component(new MyE2_DB_CheckBox(oSQLFM.get_("U_IST_STANDARD")),new MyE2_String("Std."));
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("ID_ADRESSE")),new MyE2_String("ID(Adresse)"));
		
		// neu: Fremdwaren-Besitzer
//		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("U_ID_ADRESSE_FREMDWARE")), new MyE2_String("Besitzer der Fremdware"));
//		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("ADRESS_INFO"),true), new MyE2_String("Eigentümer (Fremdware)"));
		this.add_Component(new FSL_ListCompFremdAdressen());
		
		((MyE2IF__Component)this.get("COLUMFIELD_NAME")).EXT().set_oColExtent(new Extent(150));
		((MyE2IF__Component)this.get("ORT")).EXT().set_oColExtent(new Extent(150));
		((MyE2IF__Component)this.get("U_IST_STANDARD")).EXT().set_oColExtent(new Extent(50));
		((MyE2_DB_TextArea)this.get("U_BESCHREIBUNG")).EXT().set_oColExtent(new Extent(305));
		((MyE2_DB_TextArea)this.get("U_BESCHREIBUNG")).set_iWidthPixel(300);
		((MyE2_DB_TextArea)this.get("U_BESCHREIBUNG")).set_iRows(3);
		((MyE2IF__Component)this.get("ID_ADRESSE")).EXT().set_oColExtent(new Extent(50));
		((MyE2IF__Component)this.get("AKTIV")).EXT().set_oColExtent(new Extent(30));                          //NEU_09
		
	}
	
	
	

	

}
