package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.util.HashMap;
import java.util.Iterator;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class FU_MASK_BT_ZAUBERSTAB_MENGEN_PLANMENGE_LIEF extends MyE2_Button 
{

	public FU_MASK_BT_ZAUBERSTAB_MENGEN_PLANMENGE_LIEF() 
	{
		super(E2_ResourceIcon.get_RI("wizard_mini.png"),true);
		this.setToolTipText(new MyE2_String("Planmenge übertragen auf Planmenge Abladeseite ...").CTrans());
		this.add_oActionAgent(new ownActionAgent());
		
	}

	
	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			E2_ComponentMAP oMap = FU_MASK_BT_ZAUBERSTAB_MENGEN_PLANMENGE_LIEF.this.EXT().get_oComponentMAP();

			//zuerst status der maske feststellen
			boolean bMengenfreigabeEK = S.NN(oMap.get__DBComp("PRUEFUNG_LADEMENGE").get_cActualMaskValue()).equals("Y");
			boolean bMengenfreigabeVK = S.NN(oMap.get__DBComp("PRUEFUNG_ABLADEMENGE").get_cActualMaskValue()).equals("Y");
			
			if (bMengenfreigabeEK || bMengenfreigabeVK)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Nach erfolgter Mengenprüfung ist diese Funktion nicht erlaubt !")));
				return;
			}
			
//			boolean bLademengeGutschrift = S.NN(oMap.get__DBComp("LADEMENGE_GUTSCHRIFT").get_cActualMaskValue()).equals("Y");
//			boolean bAblademengeRechnung = S.NN(oMap.get__DBComp("ABLADEMENGE_RECHNUNG").get_cActualMaskValue()).equals("Y");
		
			HashMap<String, String> hmWerte = new HashMap<String, String>();
			hmWerte.put("ANTEIL_PLANMENGE_LIEF", 	S.NN(oMap.get_cActualDBValueFormated("ANTEIL_PLANMENGE_LIEF")));
			hmWerte.put("ANTEIL_LADEMENGE_LIEF", 	S.NN(oMap.get_cActualDBValueFormated("ANTEIL_LADEMENGE_LIEF")));
			hmWerte.put("ANTEIL_ABLADEMENGE_LIEF", 	S.NN(oMap.get_cActualDBValueFormated("ANTEIL_ABLADEMENGE_LIEF")));
			hmWerte.put("ANTEIL_PLANMENGE_ABN", 	S.NN(oMap.get_cActualDBValueFormated("ANTEIL_PLANMENGE_ABN")));
			hmWerte.put("ANTEIL_LADEMENGE_ABN", 	S.NN(oMap.get_cActualDBValueFormated("ANTEIL_LADEMENGE_ABN")));
			hmWerte.put("ANTEIL_ABLADEMENGE_ABN", 	S.NN(oMap.get_cActualDBValueFormated("ANTEIL_ABLADEMENGE_ABN")));
			
			Iterator<String> oIter = hmWerte.keySet().iterator();
			while (oIter.hasNext())
			{
				String ckey = oIter.next();
				String cTest = hmWerte.get(ckey);
				
				if (S.isFull(cTest))
				{
					MyBigDecimal bdTest = new MyBigDecimal(cTest);
					if (!bdTest.get_cErrorCODE().equals(MyBigDecimal.ALL_OK))
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Falscheingabe im Feld: <"+ckey+">")));
						return;
					}
				}
			}
			

			//variante 1: Lademenge links voll, alles andere leer
			if (    S.isFull( hmWerte.get("ANTEIL_PLANMENGE_LIEF")))
			{
				if (S.isEmpty( hmWerte.get("ANTEIL_PLANMENGE_ABN")))
				{
					oMap.set_cActualDatabaseValueToMask("ANTEIL_PLANMENGE_ABN", hmWerte.get("ANTEIL_PLANMENGE_LIEF"));
				}
			}
		}
	}

	
}
