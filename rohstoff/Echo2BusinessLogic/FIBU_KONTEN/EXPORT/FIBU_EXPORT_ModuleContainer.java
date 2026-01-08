package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2_Grid_100_percent;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_DATEV_PROFILE;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.STAMM.FKR_MASK_BasicModuleContainer;

public class FIBU_EXPORT_ModuleContainer  extends Project_BasicModuleContainer 
{
	public static final String NAME_OF_CHECKBOX_IN_LIST =		"NAME_OF_CHECKBOX_IN_LIST";
	public static final String NAME_OF_LISTMARKER_IN_LIST =		"NAME_OF_LISTMARKER_IN_LIST";
	
	public static String MSG_NO_DATEV_PROFILE = "Ihrem Account ist kein Datev-Exportprofil zugeordnet (siehe Benutzerverwaltung > Sonderrechte eines Benutzers)";

	public FIBU_EXPORT_ModuleContainer() throws myException {
		super(E2_MODULNAMES.NAME_FIBU_KONTEN_EXPORT);
		
		this.set_bVisible_Row_For_Messages(true);
		
//		MyE2_Grid_100_percent cp = new MyE2_Grid_100_percent();
//		this.add(cp);
			
		E2_NavigationList oNaviList = new E2_NavigationList();
		
		// Check if user has an associated export profile
		String idExportProfile = bibALL.get_RECORD_USER().get_ID_DATEV_PROFILE_cUF_NN("");
		if (idExportProfile.equals("")) {
			myException me = new myExceptionForUser(new MyE2_String(MSG_NO_DATEV_PROFILE));
			throw me;
		} 
	
		// Reload configuration
		System.out.println("Reloading configuration for Datev Exporter from profile "+idExportProfile);
		Config.instance().reload();
		
		

		this.set_bVisible_Row_For_Messages(true);
		
		oNaviList.INIT_WITH_ComponentMAP(new FIBU_EXPORT_LIST_ComponentMap(),E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
		FIBU_EXPORT_LIST_BedienPanel oPanel = new FIBU_EXPORT_LIST_BedienPanel(oNaviList, new FKR_MASK_BasicModuleContainer());

		this.add(oPanel);
		this.add(oNaviList, E2_INSETS.I_2_2_2_2);
		
		oPanel.get_oFIBU_EXPORT_LIST_Selector().get_oSelVector().doActionPassiv();

		System.out.println("Emptying caches for module.");
		oPanel.get_oFIBU_EXPORT_LIST_Selector().emptyCache();
}
}