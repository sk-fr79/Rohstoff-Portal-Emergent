/**
 * 
 */
package rohstoff.Echo2BusinessLogic.BAM_BETRIEB;

import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_SettingAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.Echo2.components.DB.CROSS_CONNECTOR.MyE2_DBC_CrossConnection;
import panter.gmbh.basics4project.DB_ENUMS.FBAM;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FBAM;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BAM_FUHREN.BAMF__CONST;

public class MAPSETTER_BAMB_MASK extends XX_MAP_SettingAgent
{

	
	
	
	public void __doSettings_BEFORE(E2_ComponentMAP oMap, String STATUS_MASKE) throws myException
	{
		
		// grundstellung
		oMap.set_DisabledFromInteractive(BAMB_MASK_ModulContainer.FBAM_LOCKLIST_BAM,":",false);
		((MyE2_DBC_CrossConnection)oMap.get__Comp(BAMB_MASK_ModulContainer.FIELDNAME_CROSSREFERENCE_BAM_USER)).EXT().set_bDisabledFromBasic(false);

		if (STATUS_MASKE.equals(E2_ComponentMAP.STATUS_EDIT))
		{
			
			if (oMap.get_oInternalSQLResultMAP().get_UnFormatedValue("ABGESCHLOSSEN_BEHEBUNG").equals("Y"))
			{
				((MyE2_DB_SelectField) oMap.get("ID_USER_BEHEBUNG")).EXT().set_bDisabledFromInteractive(true);
				((MyE2_DB_CheckBox)oMap.get("ABGESCHLOSSEN_BEHEBUNG")).EXT().set_bDisabledFromInteractive(true);
			}
			if (oMap.get_oInternalSQLResultMAP().get_UnFormatedValue("ABGESCHLOSSEN_KONTROLLE").equals("Y")) {
				oMap.set_DisabledFromInteractive(BAMB_MASK_ModulContainer.FBAM_LOCKLIST_BAM,":",true);	
			}
			
			//2016-12-20: wenn der anwender nicht der ist, der die bam gemacht hat, koennen verteiler nicht geaendert werden
			boolean sperren_verteilerliste = true;
			try {
				RECORD_FBAM recFBAM = new RECORD_FBAM(oMap.get_oInternalSQLResultMAP().get_LActualDBValue(FBAM.id_fbam.fn(), false));
				String user_erzeuger = recFBAM.ufs(FBAM.erzeugt_von,"");
				String user_aktiv = bibALL.get_RECORD_USER().ufs(USER.kuerzel,"#");
				boolean b_geschaeftsfuehrer = bibALL.get_RECORD_USER().is_DEVELOPER_YES()||bibALL.get_RECORD_USER().is_GESCHAEFTSFUEHRER_YES();
				if (user_erzeuger.equals(user_aktiv) || b_geschaeftsfuehrer) {
					sperren_verteilerliste=false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (sperren_verteilerliste) {
				((MyE2_DBC_CrossConnection)oMap.get__Comp(BAMB_MASK_ModulContainer.FIELDNAME_CROSSREFERENCE_BAM_USER)).EXT().set_bDisabledFromBasic(true);
			}
			//2016-12-20: ende

			
		}			
	}


	
	public void __doSettings_AFTER(E2_ComponentMAP oMap, String STATUS_MASKE) throws myException
	{
	}

}