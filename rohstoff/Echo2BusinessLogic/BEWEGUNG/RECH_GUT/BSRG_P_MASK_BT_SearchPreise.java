package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.TestingDate;

public class BSRG_P_MASK_BT_SearchPreise extends MyE2_Button
{
	

//	private ActionAgent_RadioFunction_CheckBoxList     vCheckBoxen = new ActionAgent_RadioFunction_CheckBoxList(true);
//	private E2_ComponentMAP 						   oMAP = null;
	
	public BSRG_P_MASK_BT_SearchPreise()
	{
		super(E2_ResourceIcon.get_RI("suche_preis.png"));
		
		this.add_oActionAgent(new XX_ActionAgent()
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo)	throws myException
			{
				BSRG_P_MASK_BT_SearchPreise oThis = BSRG_P_MASK_BT_SearchPreise.this;
				
				E2_ComponentMAP oMap = oThis.EXT().get_oComponentMAP();

				boolean bMaskeIn_RG_MODUL = oMap.get_oSQLFieldMAP().get_("ID_VKOPF_RG") instanceof SQLFieldForRestrictTableRange; 
				
				// notwendige werte beschaffen
				String  cID_ARTIKEL = 				""+oMap.get_LActualDBValue("ID_ARTIKEL", new Long(0), new Long(0));
				if (cID_ARTIKEL.trim().equals("0"))
				{
					cID_ARTIKEL = "";
				}
				
				String  cLEISTUNGS_DATUM_Formated = "";
				TestingDate  tdAusfuehrung = oMap.get_DateActualDBValue("AUSFUEHRUNGSDATUM", true, true);
				if (tdAusfuehrung!=null && tdAusfuehrung.testing())
				{
					cLEISTUNGS_DATUM_Formated = tdAusfuehrung.get_FormatedDateString("dd.mm.yyyy");
				}
				String  cEK_VK = "";
				long lLagerVorzeichen = oMap.get_LActualDBValue("LAGER_VORZEICHEN", true, true, new Long(0)).longValue();
				if (lLagerVorzeichen==-1)
				{
					cEK_VK = "VK";
				}
				else if (lLagerVorzeichen==1)
				{
					cEK_VK = "EK";
				}

				String cID_VPOS_KON = ""+oMap.get_LActualDBValue("ID_VPOS_KON_ZUGEORD", true, true, new Long(0)).longValue();
				if (cID_VPOS_KON.trim().equals("0"))
				{
					cID_VPOS_KON = "";
				}
				
				//das textfeld fuer die rueckgabe
				MyE2_TextField   oTextFuerRueckgabe = (MyE2_TextField)oMap.get__Comp("EINZELPREIS");
				
				String cID_ADRESSE = "";
				
				if (bMaskeIn_RG_MODUL)
				{
					String cID_VKOPF_RG = ((SQLFieldForRestrictTableRange)oMap.get_oSQLFieldMAP().get_("ID_VKOPF_RG")).get_cRestictionValue_IN_DB_FORMAT();
					RECORD_VKOPF_RG recVKOPF = new RECORD_VKOPF_RG(cID_VKOPF_RG);
					cID_ADRESSE = recVKOPF.get_ID_ADRESSE_cUF_NN("");
				}
				else
				{
					cID_ADRESSE = ""+oMap.get_LActualDBValue("ID_ADRESSE", true, true, new Long(0)).longValue();
					if (cID_ADRESSE.trim().equals("0"))
					{
						cID_ADRESSE = "";
					}
				}
				
				new BSRG__POPUP_Select_EINZELPREIS(cID_ARTIKEL,cLEISTUNGS_DATUM_Formated,cID_ADRESSE,cEK_VK,cID_VPOS_KON,oTextFuerRueckgabe);
			}
		});

		
		
	}


	
}
	
