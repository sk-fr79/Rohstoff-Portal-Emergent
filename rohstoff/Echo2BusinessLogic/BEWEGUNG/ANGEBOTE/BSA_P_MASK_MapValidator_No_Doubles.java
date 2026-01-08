package rohstoff.Echo2BusinessLogic.BEWEGUNG.ANGEBOTE;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_ValidBeforeSAVE;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;
import rohstoff.utils.MyVKopfSMALL;


/*
 * positionen-validator, der dafuer sorgt, dass keine doppelten positionen in einem
 * angebot vorkommen (heist: gleicher gueltigkeitsbereich von-bis, gleiche id_artikel_bez, gleiche id_adresse
 */
public class BSA_P_MASK_MapValidator_No_Doubles extends XX_MAP_ValidBeforeSAVE
{
	private BS__SETTING oSetting = null;
	

	public BSA_P_MASK_MapValidator_No_Doubles(BS__SETTING setting)
	{
		super();
		oSetting = setting;
	}


	
	public MyE2_MessageVector _doValidation(E2_ComponentMAP oMap,SQLMaskInputMAP oInputMap, String cSTATUS_MASKE)
	{
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
		//MyDBToolBox oDB = bibALL.get_myDBToolBox();

		
		SQLFieldMAP 	oSQLFieldMAP = (SQLFieldMAP)oMap.get_oSQLFieldMAP();
		
		try
		{
			
			// wenn der artikeltyp ALTERNATIV oder TEXT ist, dann alles ok
			String cPOSITIONSTY = (String)oInputMap.get("POSITION_TYP");
			if (cPOSITIONSTY.equals(myCONST.VG_POSITION_TYP_ALTERNATIV) || 
				cPOSITIONSTY.equals(myCONST.VG_POSITION_TYP_ZUSATZTEXT))
			{
				return oMV;
			}
			
			
			// ID_VKOPF beschaffen
			String ID_VKOPF = ((SQLFieldForRestrictTableRange) oSQLFieldMAP.get_("ID_VKOPF_STD")).get_cRestictionValue_IN_DB_FORMAT();
			
			// aktuelle eingabe: 
			String cID_ARTIKEL_BEZ = bibALL.null2leer(bibALL.ReplaceTeilString((String)oInputMap.get("ID_ARTIKEL_BEZ"),".",""));
			
			MyVKopfSMALL oVK = new MyVKopfSMALL(ID_VKOPF,this.oSetting.get_oVorgangTableNames(),bibE2.get_CurrSession());
		
			String cID_ADRESSE = bibALL.null2leer(oVK.get_cID_ADRESSE());
			
			
			
			// feststellen, ob der dublettenpruefer aus der liste oder aus der maske kommt
			boolean bMaske = false;
			if (oMap.get_E2_vCombinedComponentMAPs() != null)
				bMaske = true;
			
			myDateHelper oDateHelpGueltigVon = null;
			myDateHelper oDateHelpGueltigBis = null;
			
			if (bMaske)
			{
				// die tochter-componentmap besorgen, die werte stehen in den komponenten
				E2_ComponentMAP oMapAngebot = (E2_ComponentMAP)oMap.get_E2_vCombinedComponentMAPs().get(1);
				try
				{
					oDateHelpGueltigVon = new myDateHelper(((MyE2IF__DB_Component)oMapAngebot.get("GUELTIG_VON")).get_cActualMaskValue());
					oDateHelpGueltigBis = new myDateHelper(((MyE2IF__DB_Component)oMapAngebot.get("GUELTIG_BIS")).get_cActualMaskValue());
				}
				catch (myException ex)
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message("Falsche oder fehlende Datumsangaben in der Gültigkeit !!"), false);
					return oMV;
				}
				
			}
			else
			{
				try
				{
					// in der maske stehen die werte in der letzen resultmap
					oDateHelpGueltigVon = new myDateHelper(oMap.get_oInternalSQLResultMAP().get_FormatedValue("G_GUELTIG_VON"));
					oDateHelpGueltigBis = new myDateHelper(oMap.get_oInternalSQLResultMAP().get_FormatedValue("G_GUELTIG_BIS"));
				}
				catch (myException ex)
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message("Falsche Datumsangaben in der Gültigkeit !!"), false);
					return oMV;
				}
			}
			
			
					
			// jetzt kann der check beginnen:
			if (	!bibALL.isEmpty(ID_VKOPF)  && 
					bibALL.isInteger(ID_VKOPF)  && 
					!bibALL.isEmpty(cID_ARTIKEL_BEZ) && 
					bibALL.isInteger(cID_ARTIKEL_BEZ) &&
					!bibALL.isEmpty(cID_ADRESSE))
			{
				String cID_VPOS_STD_CheckedRow = null;
				if (oMap.get_oInternalSQLResultMAP() != null)
					cID_VPOS_STD_CheckedRow = oMap.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
				
				
				BSA__DUBLETTEN_CHECKER oDBCheck = new BSA__DUBLETTEN_CHECKER( cID_ADRESSE,
														oDateHelpGueltigVon.get_oCalDate(),
														oDateHelpGueltigBis.get_oCalDate(),
														cID_ARTIKEL_BEZ,
														oSetting,
														cID_VPOS_STD_CheckedRow);
				
				oMV.add_MESSAGE(oDBCheck.getvFehler());
				
				if (oMV.size()==0)
				{
					// dann evtl. vorhandene warnungen hier aufploppen lassen
					if (oDBCheck.getvInfos().size()>0)
					{
						bibMSG.add_MESSAGE(oDBCheck.getvInfos());
					}
				}
			}
			else
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Die Position ist nicht vollständig ausgefüllt !!"), false);
			}
		
		
		}
		catch (myException ex)
		{
			oMV.add_MESSAGE(ex.get_ErrorMessage(), false);
		}
		
		return oMV;
	}

	
}
