package rohstoff.Echo2BusinessLogic.ARTIKELSTAMM;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.AgentsAndValidators.XX_MAP_ValidBeforeSAVE;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_EINHEITEN_KOMBINATIONEN;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.DotFormatter;
import panter.gmbh.indep.maggie.DotFormatterGermanFixed;

public class AS_MAP_ValidatorEinheiten extends XX_MAP_ValidBeforeSAVE
{

	public MyE2_MessageVector _doValidation(E2_ComponentMAP oMap,SQLMaskInputMAP oInputMap, String cSTATUS_MASKE) throws myException
	{
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
		String cID_Einheit = (String)oInputMap.get("ID_EINHEIT");
		String cID_EinheitPreis = (String)oInputMap.get("ID_EINHEIT_PREIS");
		String cMengenDivisor =  (String)oInputMap.get("MENGENDIVISOR");
		
		
		
		
		
		if (bibALL.isEmpty(cMengenDivisor))
		{
			oMV.add_MESSAGE(new MyE2_Alarm_Message("Einheiten und Mengendivisor MÜSSEN gesetzt werden !"), false);
		}
		else
		{

			//hier pruefen, ob die einheiten-kombination zugelassen ist
			RECLIST_EINHEITEN_KOMBINATIONEN  recList = new RECLIST_EINHEITEN_KOMBINATIONEN("SELECT * FROM "+bibE2.cTO()+
					".JT_EINHEITEN_KOMBINATIONEN " +
					" WHERE" +
					" ID_EINHEIT="+bibALL.ReplaceTeilString(cID_Einheit,".","")+" AND "+
					" ID_EINHEIT_PREIS="+bibALL.ReplaceTeilString(cID_EinheitPreis,".",""));
		
			if (recList.get_vKeyValues().size()==0)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Die Einheitenkombination aus Mengen- und Preiseinheit ist nicht zugelassen !!!"), false);
			}
			else
			{
				DotFormatter oDF = new DotFormatterGermanFixed(cMengenDivisor);
				if (oDF.doFormat())
				{
					try
					{
						Integer oInt = new Integer(oDF.getStringUnFormated());
						
						if (cID_EinheitPreis.trim().equals(""))
						{
							if (oInt.intValue()!=1)
							{
								oMV.add_MESSAGE(new MyE2_Alarm_Message("Wenn die Einheit = Preiseinheit ist, dann MUSS der Divisor 1 sein !"), false);
							}
						}
						else if (cID_Einheit.trim().equals(cID_EinheitPreis.trim()))
						{
							if (oInt.intValue()!=1)
							{
								oMV.add_MESSAGE(new MyE2_Alarm_Message("Wenn die Einheit = Preiseinheit ist, dann MUSS der Divisor 1 sein !"), false);
							}
						}
						else
						{
							if (oInt.intValue()==1)
							{
								oMV.add_MESSAGE(new MyE2_Alarm_Message("Wenn die Einheit <> Preiseinheit ist, dann DARF der Divisor NICHT 1 sein !"), false);
							}
						}
	
						
					}
					catch (Exception ex)
					{
						oMV.add_MESSAGE(new MyE2_Alarm_Message("Mengendivisor ist nicht korrekt !"), false);
					}
				}
				else
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message("Mengendivisor ist nicht korrekt !"), false);
				}
			}
		}
		return oMV;
	}

}
