package rohstoff.Echo2BusinessLogic.BEWEGUNG.ANGEBOTE;

import java.util.GregorianCalendar;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;

public class BSA__DUBLETTEN_CHECKER
{

	private MyE2_MessageVector  vIFehler = 	new MyE2_MessageVector();
	private MyE2_MessageVector  vInfos = 	new MyE2_MessageVector();
	
	public BSA__DUBLETTEN_CHECKER(	String 				cID_ADRESSE, 	
									GregorianCalendar 	oCalGueltigVon,
									GregorianCalendar 	oCalGueltigBis,
									String 				cID_ARTIKEL_BEZ,
									BS__SETTING			oSetting,
									String 				cID_VPOS_STD_CheckedRow) throws myException
	{
		
		
		MyE2_String oBaseString = new MyE2_String("Angebot über diese Position und diesen Zeitraum existiert schon ");
		
		
		myDateHelper oDH_VON = new myDateHelper(oCalGueltigVon);
		myDateHelper oDH_BIS = new myDateHelper(oCalGueltigBis);
		
		
		String cSQL = "SELECT JT_VPOS_STD.ID_VPOS_STD,JT_VPOS_STD.ANR1,JT_VPOS_STD.ANR2 FROM " +
		bibE2.cTO()+".JT_VKOPF_STD,"+bibE2.cTO()+".JT_VPOS_STD, "+bibE2.cTO()+".JT_VPOS_STD_ANGEBOT "+
						" WHERE " +
						" JT_VKOPF_STD.ID_VKOPF_STD=JT_VPOS_STD.ID_VKOPF_STD AND " +
						" JT_VPOS_STD.ID_VPOS_STD=JT_VPOS_STD_ANGEBOT.ID_VPOS_STD AND " +
						"   NVL(JT_VPOS_STD.DELETED,'N')='N' AND " +
						" JT_VKOPF_STD.VORGANG_TYP='"+oSetting.get_cBELEGTYP()+"' AND "+
						" JT_VPOS_STD_ANGEBOT.GUELTIG_VON='"+oDH_VON.get_cDateFormat_ISO_FORMAT()+"' AND "+
						" JT_VPOS_STD_ANGEBOT.GUELTIG_BIS='"+oDH_BIS.get_cDateFormat_ISO_FORMAT()+"' AND "+
						" JT_VKOPF_STD.ID_ADRESSE="+cID_ADRESSE+" AND "+
						" JT_VPOS_STD.ID_ARTIKEL_BEZ="+cID_ARTIKEL_BEZ;
		
		String[][] cQuery = bibDB.EinzelAbfrageInArray(cSQL);
		
		if (cQuery == null)
		{
			vIFehler.add_MESSAGE(new MyE2_Message(new MyE2_String("Fehler bei der Dublettenvalidierung !!"),MyE2_Message.TYP_ALARM,false), false);
		}
		else
		{
			if (cQuery.length>0)            // bei 0 alles ok
			{
				// jetzt unterscheiden zwischen neueingabe und update (neueingabe: cID_VPOS_STD_CheckedRow==null)
				if (cID_VPOS_STD_CheckedRow==null)
				{
					MyE2_String oOut = new MyE2_String(oBaseString.CTrans(),false);
					oOut.addUnTranslated(":ID_ADRESSE: "+cID_ADRESSE+"  ("+cQuery[0][1]+"-"+cQuery[0][2]+")");
					// es wird nur noch vor der dublette gewarnt, nicht mehr als fehler
					vInfos.add_MESSAGE(new MyE2_Info_Message(oOut), false);
				}
				else
				{
					if (cQuery.length==1)    // dann kann es noch der gleiche satz sein
					{
						if (! cID_VPOS_STD_CheckedRow.equals(cQuery[0][0]))
						{
							MyE2_String oOut = new MyE2_String(oBaseString.CTrans(),false);
							oOut.addUnTranslated(":ID_ADRESSE: "+cID_ADRESSE+"  ("+cQuery[0][1]+"-"+cQuery[0][2]+")");
							vInfos.add_MESSAGE(new MyE2_Info_Message(oOut), false);
						}
					}
					else
					{
						MyE2_String oOut = new MyE2_String(oBaseString.CTrans(),false);
						oOut.addUnTranslated(":ID_ADRESSE: "+cID_ADRESSE+"  ("+cQuery[0][1]+"-"+cQuery[0][2]+")");
						vInfos.add_MESSAGE(new MyE2_Info_Message(oOut), false);
					}
				}
			}
		}
	}

	public MyE2_MessageVector getvFehler()
	{
		return vIFehler;
	}

	public MyE2_MessageVector getvInfos()
	{
		return vInfos;
	}

	
}
