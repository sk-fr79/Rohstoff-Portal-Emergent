package rohstoff.Echo2BusinessLogic.BEWEGUNG;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.utils.MyVKopfSMALL;
import rohstoff.utils.VorgangTableNames;

public class BS_K_Validator_BELEG_MENGEN_UND_PREISE_GEFUELLT extends XX_ActionValidator {

	private MyE2_String cError = new MyE2_String("Bearbeitung ist verboten, ein/mehrere Vorgänge haben noch keine vollständigen Mengen und/oder Preise !");

	private VorgangTableNames oTN = null;
	
	public BS_K_Validator_BELEG_MENGEN_UND_PREISE_GEFUELLT(VorgangTableNames TN) 
	{
		super();
		this.oTN = TN;
	}

	public MyE2_MessageVector isValid(Component oCompWhichIsValidated) 
	{
		return new MyE2_MessageVector();
	}


	public MyE2_MessageVector isValid(String cID_Unformated)
	{

		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
		try
		{
			MyVKopfSMALL oVKSmall = new MyVKopfSMALL(cID_Unformated,oTN,bibE2.get_CurrSession());
			
			int iPosAlternativ = 				oVKSmall.get_iNumberOfPositions(myCONST.VG_POSITION_TYP_ALTERNATIV);
			int iPosArtikel = 					oVKSmall.get_iNumberOfPositions(myCONST.VG_POSITION_TYP_ARTIKEL);
			int iVollPosArtikelNullMenge = 		oVKSmall.get_iNumberOfPositionsNULL_MENGE(myCONST.VG_POSITION_TYP_ARTIKEL);
			int iVollPosArtikelNullPreis = 		oVKSmall.get_iNumberOfPositionsNULL_PRICE(myCONST.VG_POSITION_TYP_ARTIKEL);
			int iVollPosAlternativNullPreis = 	oVKSmall.get_iNumberOfPositionsNULL_MENGE(myCONST.VG_POSITION_TYP_ALTERNATIV);

			
			if (iPosArtikel>0)
			{
				if (iVollPosArtikelNullMenge!=0 || iVollPosArtikelNullPreis !=0)
					oMV.add_MESSAGE(new MyE2_Alarm_Message(this.cError));
			}
			
			if (iPosAlternativ>0)
			{
				if (iVollPosAlternativNullPreis!=0)
					oMV.add_MESSAGE(new MyE2_Alarm_Message(this.cError));
			}
			
		}
		catch (myException ex)
		{
			oMV.add_MESSAGE(new MyE2_Alarm_Message("Fehler bei der Validierung eines Vorgangs !!"));
		}
		return oMV;
	}


}
