package rohstoff.Echo2BusinessLogic.ORACLETOOLS;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.ORACLE.MyDBToolBox_ORA;
import panter.gmbh.indep.exceptions.myException;
import echopointng.Separator;

/**
 * 
 * @deprecated
 *
 */
public class OT_BasicContainer extends Project_BasicModuleContainer
{

	private 	MyDBToolBox			oDBSAP = bibALL.get_myDBToolBox();
	private 	MyE2_TabbedPane		oTabbedModules = new MyE2_TabbedPane(null);
	
	private     MyDBToolBox_ORA  oDBOra = null;
	
	
	protected void finalize()
	{
		if (this.oDBOra != null)
			this.oDBOra = null;
		
		bibALL.destroy_myDBToolBox(this.oDBSAP);
	}
	
	
	public OT_BasicContainer() throws myException
	{
		super(E2_MODULNAMES.NAME_MODUL_ORACLETOOLS);

		this.oDBOra = new MyDBToolBox_ORA();
		
		
		MyE2_Column	 oColAussen = new MyE2_Column(MyE2_Column.STYLE_THIN_BORDER());
		MyE2_Column	 oColInnnen = new MyE2_Column(MyE2_Column.STYLE_NO_BORDER());
		this.add(oColAussen,E2_INSETS.I_5_5_5_5);
		oColAussen.add(oColInnnen);
		
		oColInnnen.add(new MyE2_Label(new MyE2_String("Verbindung Tutos-Daten -> Rohstoffprogramm"),MyE2_Label.STYLE_TITEL_BIG()),E2_INSETS.I_0_10_0_10);
		oColInnnen.add(new Separator(),E2_INSETS.I_0_10_0_10);
		oColInnnen.add(this.oTabbedModules,E2_INSETS.I_0_10_0_10);
		
		this.oTabbedModules.add_Tabb(new MyE2_String("Lieferanten-Preise importieren"),new OT_COL_IMPORT_PREISE(OT_COL_IMPORT_PREISE.TYPE_NORMALARTIKEL,this));
		this.oTabbedModules.add_Tabb(new MyE2_String("Dienstleistungs-Preise importieren"),new OT_COL_IMPORT_PREISE(OT_COL_IMPORT_PREISE.TYPE_DIENSTLEISTUNG,this));
		this.oTabbedModules.add_Tabb(new MyE2_String("Rest-Kontrakte löschen"),new OT_COL_Delete_Old_Kontrakts(this));
		this.oTabbedModules.add_Tabb(new MyE2_String("Oracle-User entsperren"),new OT_COL_UnlockOracleUsers(this));
		
	}
	


	public MyDBToolBox get_oDBORA()
	{
		return oDBOra;
	}
	
	public MyDBToolBox get_oDBSAP()
	{
		return oDBSAP;
	}
	
}
