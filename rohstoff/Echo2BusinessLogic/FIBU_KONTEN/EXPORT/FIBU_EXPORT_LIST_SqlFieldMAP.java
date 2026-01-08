package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT;

import java.util.HashMap;

import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class FIBU_EXPORT_LIST_SqlFieldMAP extends Project_SQLFieldMAP 
{

	public FIBU_EXPORT_LIST_SqlFieldMAP() throws myException 
	{
		super("JT_VPOS_EXPORT_RG", "", false);
		
		
		this.get_("WAEHRUNG").set_cDefaultValueFormated("");
		this.get_("SUMME").set_cDefaultValueFormated("");
		this.get_("GEGENKONTO").set_cDefaultValueFormated("");
		this.get_("PRIO").set_cDefaultValueFormated("");
		this.get_("ID_FIBU_KONTENREGEL").set_cDefaultValueFormated("");
		this.get_("ID_VPOS_RG_PARENT").set_cDefaultValueFormated("");
		
		// Join Table "jt_vkopf_export_rg"
		HashMap<String, String>  hmKopfExport = new HashMap<String, String>();
		this.add_JOIN_Table(_DB.VKOPF_EXPORT_RG, "VKE", SQLFieldMAP.INNER, "", true, "JT_VPOS_EXPORT_RG.ID_VKOPF_RG = VKE.ID_VKOPF_EXPORT_RG", "VKE_", hmKopfExport);

		// Join Table "jt_vkopf_rg"
		HashMap<String, String>  hmKopf = new HashMap<String, String>();
		hmKopf.put("O_DRUCKDATUM", "VK.DRUCKDATUM");
		hmKopf.put("RECHNUNGSKOPF", "VK.BUCHUNGSNUMMER");
//		hmKopf.put("NUMMER", "CASE WHEN SUBSTR(VK.BUCHUNGSNUMMER,0,1) = 'G' THEN SUBSTR(VK.BUCHUNGSNUMMER,2) ELSE SUBSTR(VK.BUCHUNGSNUMMER,3) END");
		hmKopf.put("NUMMER", "VK.BUCHUNGSNUMMER");

		this.add_JOIN_Table(_DB.VKOPF_RG, "VK", SQLFieldMAP.INNER, "ID_VKOPF_RG", true, "VKE.ID_VKOPF_EXPORT_RG = VK.ID_VKOPF_RG", "VK_", hmKopf);
		
		// Join Table "jt_export_log"
		HashMap<String, String>  hmExportLog = new HashMap<String, String>();
		hmExportLog.put("ERZEUGT_VON_EL", "EL.ERZEUGT_VON");
		hmExportLog.put("ERZEUGT_AM_EL", "EL.ERZEUGT_AM");
		hmExportLog.put("BEMERKUNGEN", "EL.BEMERKUNGEN");
		hmExportLog.put("MATERIALISIERT", "EL.MATERIALISIERT");
		this.add_JOIN_Table("JT_EXPORT_LOG", "EL", SQLFieldMAP.INNER, "", true, "JT_VPOS_EXPORT_RG.ID_EXPORT_LOG = EL.ID_EXPORT_LOG", "EL_", hmExportLog);
		
		// Join Table "jt_vpos_rg"
		HashMap<String, String>  hmPos = new HashMap<String, String>();
		hmPos.put("RECHNUNGSPOSITION", "VP.POSITIONSNUMMER");
		//hmPos.put("GESAMTPREIS", "VP.GESAMTPREIS");

		// Table,                        Alias, JoinType        FieldList/incl    JOIN_COND 
		this.add_JOIN_Table(_DB.VPOS_RG, "VP", SQLFieldMAP.INNER, "", true, "JT_VPOS_EXPORT_RG.ID_VPOS_EXPORT_RG = VP.ID_VPOS_RG", "X_", hmPos);

		// Nummer, wie von Fr. Hecktor gewünscht, als Sortierung
		// this.add_ORDER_SEGMENT("KONTO ASC");
		this.add_ORDER_SEGMENT("VK.BUCHUNGSNUMMER ASC");
		this.add_ORDER_SEGMENT("VP.POSITIONSNUMMER ASC");

		
		this.initFields();
		
		// System.out.println("SQL benutzt: "+this.get_CompleteSQLQueryFor_ID_VECTOR("", false));
	}
}
