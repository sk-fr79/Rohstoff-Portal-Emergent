package rohstoff.Echo2BusinessLogic.FIBU.EXPORT_PROFILES;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FIBU_EXPORT_PROFILES_LIST_ComponentMap extends E2_ComponentMAP 
{

	public FIBU_EXPORT_PROFILES_LIST_ComponentMap() throws myException
	{
		super(new FIBU_EXPORT_PROFILES_LIST_SqlFieldMAP());
		
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		
		this.add_Component(FIBU_EXPORT_PROFILES_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(FIBU_EXPORT_PROFILES_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));

		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("ID_DATEV_PROFILE")), new MyE2_String("Profil-ID"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("DESCRIPTION")), new MyE2_String("Profilname"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("DATEV_GESCHAEFTSJAHRESBEGINN")), new MyE2_String("Datev-Geschäftsjahresbeginn"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("DATEV_BERATERNUMMER")), new MyE2_String("Datev-Beraternummer"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("DATEV_MANDANTNUMMER")), new MyE2_String("Datev-Mandantnummer"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("DRUCKER_LESBAR")), new MyE2_String("Drucker"));

		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("EXPORT_DIR")), new MyE2_String("Server-Exportpfad"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("EXPORTS_STARTING_DATE")), new MyE2_String("Älteste mögliche Export-Vpos"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("EXPORTS_STARTING_ID")), new MyE2_String("Älteste mögliche Export-ID"));
//		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("FLUSH_TABLES")), new MyE2_String("DEBUG: Exporttabellen bei "));
//		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("PRINT_PROTOCOLS")), new MyE2_String("DEBUG: Text-Protokolle erstellen"));
//		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("CORRECT_DATES")), new MyE2_String("Alte Buchungsdaten an letztes mögliches Datum anpassen"));
//		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("STAMP_IMPORTED_WITH_DEBUGFLAGS")), new MyE2_String("Exporte mit Debugflag versehen"));

		
//		this.add_Component(new MyE2_DB_Label(oFM.get_("ID_USER")), new MyE2_String("ID_USER"));
		


		this.set_oSubQueryAgent(new FIBU_EXPORT_PROFILES_LIST_FORMATING_Agent());
	}

}
