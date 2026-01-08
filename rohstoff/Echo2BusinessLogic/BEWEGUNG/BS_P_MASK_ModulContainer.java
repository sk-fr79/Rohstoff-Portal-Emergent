package rohstoff.Echo2BusinessLogic.BEWEGUNG;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.basics4project.Project_BasicModuleContainer_MASK;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_WAEHRUNG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDataRecordHashMap;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;

/*
 * geruest fuer die 4 verschiedenen E2_BasicModuleContainer_Mask der belegtypen
 */
public abstract class BS_P_MASK_ModulContainer extends Project_BasicModuleContainer_MASK 
{

	
	public BS_P_MASK_ModulContainer(String cMODULEIDENTIFIER)
	{
		super(cMODULEIDENTIFIER);
	}
	
	/*
	 * basis-id setzen, wird von der maske aus gesteuert
	 */
	public void make_SettingsFrom_Head_to_Position(String cID_Unformated_VKOPF) throws myException
	{
		try
		{
			if (S.isEmpty(cID_Unformated_VKOPF) || !bibALL.isLong(cID_Unformated_VKOPF))
				throw new myException(this,"Empty or No-Number-ID ist not allowed !");
			
			
			String cNAME_VKOPF_ID = this.get_SETTING().get_oVorgangTableNames().get_cVKOPF_PK();
			
			((SQLFieldForRestrictTableRange)this.get_ComponentMapMask().get_oSQLFieldMAP().get(cNAME_VKOPF_ID)).set_cRestrictionValue_IN_DB_FORMAT(""+cID_Unformated_VKOPF);
			
			/*
			 * Es werden immer die zahlungs- und lieferbedingungen aus der datenbank ueber die
			 * restrict-einstellung des feld ID_VKOPF__ definiert, da bevor ein positionsatz neu eingegeben oder
			 * geaendert werden kann, zuerst dieser immer gespeichert wird
			 */
			
			//sonst holt ers aus der datenbank
			MyDataRecordHashMap  oHM = new MyDataRecordHashMap(	this.get_SETTING().get_oVorgangTableNames().get_cVKOPF_TAB(),
																cID_Unformated_VKOPF);
			
			this.get_ComponentMapMask().get_oSQLFieldMAP().get_("ID_ZAHLUNGSBEDINGUNGEN").set_cDefaultValueFormated(S.NN(oHM.get_FormatedValue("ID_ZAHLUNGSBEDINGUNGEN")));
			this.get_ComponentMapMask().get_oSQLFieldMAP().get_("ZAHLUNGSBEDINGUNGEN").set_cDefaultValueFormated(S.NN(oHM.get_FormatedValue("ZAHLUNGSBEDINGUNGEN")));
			this.get_ComponentMapMask().get_oSQLFieldMAP().get_("ZAHLTAGE").set_cDefaultValueFormated(S.NN(oHM.get_FormatedValue("ZAHLTAGE")));
			this.get_ComponentMapMask().get_oSQLFieldMAP().get_("FIXMONAT").set_cDefaultValueFormated(S.NN(oHM.get_FormatedValue("FIXMONAT")));
			this.get_ComponentMapMask().get_oSQLFieldMAP().get_("FIXTAG").set_cDefaultValueFormated(S.NN(oHM.get_FormatedValue("FIXTAG")));
			this.get_ComponentMapMask().get_oSQLFieldMAP().get_("SKONTO_PROZENT").set_cDefaultValueFormated(S.NN(oHM.get_FormatedValue("SKONTO_PROZENT")));
			
			this.get_ComponentMapMask().get_oSQLFieldMAP().get_("LIEFERBEDINGUNGEN").set_cDefaultValueFormated(S.NN(oHM.get_FormatedValue("LIEFERBEDINGUNGEN")));
			this.get_ComponentMapMask().get_oSQLFieldMAP().get_("ID_WAEHRUNG_FREMD").set_cDefaultValueFormated(S.NN(oHM.get_FormatedValue("ID_WAEHRUNG_FREMD")));

			//jetzt die waehrung des kunden (Fremdwaehrung) setzen in der maske
			String cWaehrung = "??";
			
			if (!bibALL.isEmpty(oHM.get_UnFormatedValue("ID_WAEHRUNG_FREMD")))
			{
				cWaehrung = new RECORD_WAEHRUNG(oHM.get_UnFormatedValue("ID_WAEHRUNG_FREMD")).get_WAEHRUNGSSYMBOL_cUF_NN("??");
			}
			
			((BS_ComponentMAP)this.get_vCombinedComponentMAPs().get(0)).set_FremdWaehrungsSymbol(cWaehrung);
			
		}
		catch (Exception ex)
		{
			throw new myException(this,"make_SettingsFrom_Head_to_Position: Unknown Error: "+ex.getLocalizedMessage());
		}
	}

	public abstract BS__SETTING 					get_SETTING();
	public abstract E2_ComponentMAP 				get_ComponentMapMask();
	
	
}
