package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.QUERYAGENT_MarkiereInaktiveInNaviList;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ZusatzFelder.ADD_ZusatzFelder;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_GenericListComponentShowAddonDocuments;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_LIST_comp_anlagen_email;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.GPS.FS_ListBtn_ShowGeopoint_Heading;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.GPS.FS_ListCompShowGeopoint;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.GPS.FS_ListCompShowGeopointAllAddressesOSM;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.GPS.FS_ListCompShowGeopointOSM;

public class FS_LIST_ComponentMAP extends E2_ComponentMAP
{


	
	public FS_LIST_ComponentMAP(E2_NavigationList c_navilist) throws myException
	{
		super(new FS_LIST_SQLFieldMap());
		
		this.add_Component("NAME_OF_CHECKBOX_IN_LIST", new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component("NAME_OF_LISTMARKER_IN_LIST", new E2_ButtonListMarker(),new MyE2_String("?"));
		
		SQLFieldMAP oSQLFM = this.get_oSQLFieldMAP();

		
		this.add_Component(new FS__LIST_BUTTON_INFO(oSQLFM.get_("F_ID_ADRESSE")), new MyE2_String("I"))._setLongText4ColumnSelection(S.ms("Info-Button"));
		
		//2017-02-10: webseiten-popup
		this.add_Component(FS_CONST.LIST_COL_SHOW_WEBSITES, new FS_LIST_comp_showWebsites(), new MyE2_String("WWW"))._setLongText4ColumnSelection(S.ms("Homepage der Firma"));
		
		//2017-02-10: geocoding aufruf 
		if(ENUM_MANDANT_DECISION.USE_GEOPOINT_SHOW.is_YES()) {
			this.add_Component(FS_CONST.LIST_GEOCODE, new FS_ListCompShowGeopoint(), new MyE2_String("G"))._setLongText4ColumnSelection(S.ms("Zeige selektierte Adressen auf Karte"));
			this.add_Component(FS_CONST.LIST_OSM_SHOW_ADR_LAGER, new FS_ListCompShowGeopointAllAddressesOSM(), new MyE2_String("A+L"))._setLongText4ColumnSelection(S.ms("Zeige alle Stationen der Adresse"));
			this.add_Component(FS_CONST.LIST_OSM_SHOW_SINGLE, new FS_ListCompShowGeopointOSM(), new MyE2_String("OSM"))._setLongText4ColumnSelection(S.ms("Zeige selektierte Adressen auf Karte (OSM)"));
			
			// Button im Heading der Google-Anzeigen
			FS_ListBtn_ShowGeopoint_Heading oBtnHeadingShowAdrs = new FS_ListBtn_ShowGeopoint_Heading(this);
			this.get__Comp(FS_CONST.LIST_GEOCODE).EXT().set_oCompTitleInList(oBtnHeadingShowAdrs);
			oBtnHeadingShowAdrs.EXT().set_oLayout_ListTitelElement(LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_0_0_0_0));

			// Button im Heading der OSM-Anzeigen
			FS_ListBtn_ShowGeopoint_Heading oBtnHeadingShowAdrsOSM = new FS_ListBtn_ShowGeopoint_Heading(this);
			this.get__Comp(FS_CONST.LIST_OSM_SHOW_SINGLE).EXT().set_oCompTitleInList(oBtnHeadingShowAdrsOSM);
			oBtnHeadingShowAdrsOSM.EXT().set_oLayout_ListTitelElement(LayoutDataFactory.get_ColLayoutGridRight(E2_INSETS.I_0_0_0_0));

		}
		
		//2012-05-15: multijump-button
		this.add_Component(FS_CONST.LIST_BUTTON_MULTI_JUMP, new FS_BT_LIST_SPRUNGBUTTONS_EX_ADRESSE(), new MyE2_String("S"))._setLongText4ColumnSelection(S.ms("Sprünge auf Bewegungsdaten"));
		
		//2015-08-28: spalte fuer die farbanzeige der adressklasse
		this.add_Component(new FS_LIST_compMarkAdressklasse_color(oSQLFM.get_(FS_CONST.SQL_FIELDS.ID2.fieldAlias())), FS_CONST.SQL_FIELDS.ID2.userText());

		//2020-09-24: Status Wareneingang/Warenausgang
		this.add_Component(new FS_ListComponentShowEinkaufVerkaufFreigabe(c_navilist),true);
		
		//2015-09-02: anzeige der upload-dokumente direkt in der liste
		this.add_Component(E2_GenericListComponentShowAddonDocuments.LISTKEY4COMPONENTMAP,new E2_GenericListComponentShowAddonDocuments(), E2_GenericListComponentShowAddonDocuments.LISTINFO4COMPONENTMAP);
		
		//2016-04-06: neue spalte fu anzeigen die Dokumente für mails und druck
		if(bibALL.get_bDebugMode()){
			this.add_Component(FS_CONST.LIST_ANLAGEN_EMAILS, new E2_LIST_comp_anlagen_email(c_navilist), new MyE2_String("A/E"));
		}
		
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("ID_ADRESSE")),new MyE2_String("KdNr"));
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("VORNAME")),new MyE2_String("Vorname"));
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("NAME1")),new MyE2_String("Name 1"));
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("NAME2")),new MyE2_String("Name 2"));
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("PLZ")),new MyE2_String("PLZ"));
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("ORT")),new MyE2_String("Ort"));
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("STRASSE")),new MyE2_String("Strasse"));
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_(ADRESSE.hausnummer)),new MyE2_String("HNr"));
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("L_LAENDERNAME")),new MyE2_String("Land"));
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("U_NAME")),new MyE2_String("Bearbeiter"));
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("AUSWEIS_NUMMER")),new MyE2_String("Ausweisnr."));
		
		// spalte mit barkunde/Transfer-Kunde
		this.add_Component(new MyE2_DB_CheckBox(oSQLFM.get_("BARKUNDE")),new MyE2_String("Bar"));
		this.add_Component(new MyE2_DB_CheckBox(oSQLFM.get_("F_SCHECKDRUCK_JN")),new MyE2_String("Scheck"));
		this.add_Component(new MyE2_DB_CheckBox(oSQLFM.get_("TRANSFER")),new MyE2_String("Trans."));
		this.add_Component(new MyE2_DB_CheckBox(oSQLFM.get_("AKTIV")),new MyE2_String("Akt."));
		
		//2012-01-18: keine mahnung
		this.add_Component(new MyE2_DB_CheckBox(oSQLFM.get_("F_KEINE_MAHNUNGEN")),new MyE2_String("Keine Mahn."));
		
		
		this.add_Component(FS_CONST.LIST_COL_IST_LIEFERANT,		new MyE2_Label(E2_ResourceIcon.get_RI("empty10.png")),new MyE2_String("LIEF."));
		this.add_Component(FS_CONST.LIST_COL_IST_ABNEHMER,		new MyE2_Label(E2_ResourceIcon.get_RI("empty10.png")),new MyE2_String("ABN"));
		
		this.add_Component(FS_CONST.LIST_COL_INFO_INFOS,			new MyE2_Label(""),new MyE2_String("Infos"));
		this.add_Component(FS_CONST.LIST_COL_INFO_MITARBEITER,		new MyE2_Label(""),new MyE2_String("Mitarb."));
		this.add_Component(FS_CONST.LIST_COL_INFO_FILES,			new MyE2_Label(""),new MyE2_String("Dat."));
		this.add_Component(FS_CONST.LIST_COL_INFO_LIEFERADRESSEN,	new MyE2_Label(""),new MyE2_String("Lief."));
		
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("F_STEUERNUMMER")),new MyE2_String("Steuernummer"));
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("UST_LKZ_ID")),new MyE2_String("UST-ID(Basis)"));
		MyE2_Grid  oGrid  = new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		this.add_Component(FS_CONST.LIST_COL_USTID_LIST_ZUSATZ,	oGrid, new MyE2_String("UST-ID(Zusatz)"));
		
		
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("F_DEBITOR_NUMMER")),new MyE2_String("Debitornummer"));
		this.add_Component(new MyE2_DB_Label_INROW(oSQLFM.get_("F_KREDITOR_NUMMER")),new MyE2_String("Kreditornummer"));
		
		this.add_Component(new MyE2_DB_Label(oSQLFM.get_("F_ID_FIRMENINFO")),new MyE2_String("Firmeninfo"));
		
		((MyE2IF__Component)this.get("ID_ADRESSE")).EXT().set_oColExtent(new Extent(50));
		((MyE2IF__Component)this.get("NAME1")).EXT().set_oColExtent(new Extent(200));
		((MyE2IF__Component)this.get("PLZ")).EXT().set_oColExtent(new Extent(60));
		((MyE2IF__Component)this.get("ORT")).EXT().set_oColExtent(new Extent(200));
		((MyE2IF__Component)this.get("STRASSE")).EXT().set_oColExtent(new Extent(200));
		((MyE2IF__Component)this.get("L_LAENDERNAME")).EXT().set_oColExtent(new Extent(100));
		((MyE2IF__Component)this.get("U_NAME")).EXT().set_oColExtent(new Extent(100));
		((MyE2IF__Component)this.get("F_ID_FIRMENINFO")).EXT().set_oColExtent(new Extent(50));
		
		
		
		((MyE2IF__Component)this.get("F_ID_FIRMENINFO")).EXT().set_bIsVisibleInList(false);
		((MyE2IF__Component)this.get("F_DEBITOR_NUMMER")).EXT().set_bIsVisibleInList(false);
		((MyE2IF__Component)this.get("F_KREDITOR_NUMMER")).EXT().set_bIsVisibleInList(false);
		
		this.set_oSubQueryAgent(new FS_LIST_ComponentMAP_SubqueryAgent());
		this.add_oSubQueryAgent(new QUERYAGENT_MarkiereInaktiveInNaviList("AKTIV"));
		
		
		//experimentell
		//this.move_Row("PLZ", 2);
		new ADD_ZusatzFelder(this, E2_MODULNAMES.NAME_MODUL_FIRMENSTAMM_LIST);
		
	}

}
