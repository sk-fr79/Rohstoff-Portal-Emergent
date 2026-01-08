package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.util.HashMap;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;

public class FU_MASK_BT_ZAUBERSTAB_VerteileDatum extends MyE2_Button 
{

	private HashMap<String, String> hmUmsetzer = new HashMap<String, String>();
	
	public FU_MASK_BT_ZAUBERSTAB_VerteileDatum() 
	{
		super(E2_ResourceIcon.get_RI("wizard.png"),true);
		this.add_oActionAgent(new ownActionAgent());
		
		hmUmsetzer.put(VPOS_TPA_FUHRE.datum_abholung.fn(), 		"Abholung Start");
		hmUmsetzer.put(VPOS_TPA_FUHRE.datum_abholung_ende.fn(), "Abholung Ende");
		hmUmsetzer.put(VPOS_TPA_FUHRE.datum_aufladen.fn(), 		"Ladedatum");
		hmUmsetzer.put(VPOS_TPA_FUHRE.datum_anlieferung.fn(), 		"Anlieferung Start");
		hmUmsetzer.put(VPOS_TPA_FUHRE.datum_anlieferung_ende.fn(), 	"Anlieferung Ende");
		hmUmsetzer.put(VPOS_TPA_FUHRE.datum_abladen.fn(), 			"Abladedatum");
	}

	//2015-08-22: neue version: verteile links und rechts getrennt, statt beide mit dem startwert von links zu fuellen
	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo)	throws myException
		{
			FU_MASK_ComponentMAP  oThis = (FU_MASK_ComponentMAP)FU_MASK_BT_ZAUBERSTAB_VerteileDatum.this.EXT().get_oComponentMAP();
		
			MyE2_MessageVector mv = new MyE2_MessageVector();
			mv.add_MESSAGE(this.pruefeDatumsFeld(VPOS_TPA_FUHRE.datum_abholung.fn()));
			mv.add_MESSAGE(this.pruefeDatumsFeld(VPOS_TPA_FUHRE.datum_abholung_ende.fn()));
			mv.add_MESSAGE(this.pruefeDatumsFeld(VPOS_TPA_FUHRE.datum_aufladen.fn()));
			mv.add_MESSAGE(this.pruefeDatumsFeld(VPOS_TPA_FUHRE.datum_anlieferung.fn()));
			mv.add_MESSAGE(this.pruefeDatumsFeld(VPOS_TPA_FUHRE.datum_anlieferung_ende.fn()));
			mv.add_MESSAGE(this.pruefeDatumsFeld(VPOS_TPA_FUHRE.datum_abladen.fn()));
			
			if (mv.get_bHasAlarms()) {
				bibMSG.add_MESSAGE(mv);
				return;
			}
			
			if (oThis.get_cActualDBValueFormated("PRUEFUNG_LADEMENGE").equals("Y")  ||	oThis.get_cActualDBValueFormated("PRUEFUNG_ABLADEMENGE").equals("Y")) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Diese Funktion ist nach dem Abschliessen einer Prüfung nicht mehr möglich !"));
				return;
			}
			
			//ein sonderfall: das ladedatum enthaelt einen wert, das abladedatum nicht, dann wird das abladedatum mit dem ladedatum gefuellt 
			String cDatumZumFuellenEK = S.NN(myDateHelper.get_CheckedDate(oThis.get_cActualDBValueFormated(VPOS_TPA_FUHRE.datum_abholung.fn(), "")));
			String cDatumZumFuellenVK = S.NN(myDateHelper.get_CheckedDate(oThis.get_cActualDBValueFormated(VPOS_TPA_FUHRE.datum_anlieferung.fn(), "")));
			
			if (S.isEmpty(cDatumZumFuellenEK)) {
				cDatumZumFuellenEK=bibALL.get_cDateNOW();
			}

			if (S.isEmpty(cDatumZumFuellenVK)) {
				cDatumZumFuellenVK = cDatumZumFuellenEK;
			}

			//2011-02-01: nur fuellen, wenn vorher leer
			this.fuelleWennLeer(VPOS_TPA_FUHRE.datum_abholung.fn(), 			cDatumZumFuellenEK);
			this.fuelleWennLeer(VPOS_TPA_FUHRE.datum_abholung_ende.fn(), 		cDatumZumFuellenEK);
			this.fuelleWennLeer(VPOS_TPA_FUHRE.datum_aufladen.fn(), 			cDatumZumFuellenEK);
			this.fuelleWennLeer(VPOS_TPA_FUHRE.datum_anlieferung.fn(), 			cDatumZumFuellenVK);
			this.fuelleWennLeer(VPOS_TPA_FUHRE.datum_anlieferung_ende.fn(), 	cDatumZumFuellenVK);
			this.fuelleWennLeer(VPOS_TPA_FUHRE.datum_abladen.fn(), 				cDatumZumFuellenVK);
			
		}
		
		private void fuelleWennLeer(String fieldName, String wert) throws myException {
			FU_MASK_ComponentMAP  oThis = (FU_MASK_ComponentMAP)FU_MASK_BT_ZAUBERSTAB_VerteileDatum.this.EXT().get_oComponentMAP();
			if (oThis.get_cActualDBValueFormated(fieldName,"@@@@#@@@@@").equals("@@@@#@@@@@")) { 	
				((MyE2IF__DB_Component)oThis.get__Comp(fieldName)).set_cActualMaskValue(wert);
			}
		}

		private MyE2_MessageVector pruefeDatumsFeld(String fieldName) throws myException {
			FU_MASK_ComponentMAP  oThis = (FU_MASK_ComponentMAP)FU_MASK_BT_ZAUBERSTAB_VerteileDatum.this.EXT().get_oComponentMAP();

			MyE2_MessageVector mv = new MyE2_MessageVector();
			String userText = FU_MASK_BT_ZAUBERSTAB_VerteileDatum.this.hmUmsetzer.get(fieldName);
			if (S.isEmpty(userText)) {
				userText=fieldName; 
			}
			if (S.isFull(((MyE2IF__DB_Component)oThis.get__Comp(fieldName)).get_cActualMaskValue())) {
				if (myDateHelper.get_CheckedDate(oThis.get_cActualDBValueFormated(fieldName, ""))==null) {
					mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Das Datumsfeld <",true,userText,true,"> erscheint nicht korrekt. Bitte prüfen Sie den Eintrag!",true)));
				} else {
					((MyE2IF__DB_Component)oThis.get__Comp(fieldName)).set_cActualMaskValue(myDateHelper.get_CheckedDate(oThis.get_cActualDBValueFormated(fieldName, "")));
				}
			}
			return mv;
		}
		
		
		
	}
	
//2015-08-22: alt	
//	private class ownActionAgent extends XX_ActionAgent
//	{
//		@Override
//		public void executeAgentCode(ExecINFO execInfo)	throws myException
//		{
//			/*
//			 * betroffene felder:
//			 */
//			FU_MASK_ComponentMAP  oThis = (FU_MASK_ComponentMAP)FU_MASK_BT_ZAUBERSTAB_VerteileDatum.this.EXT().get_oComponentMAP();
//			
//			if (oThis.get_cActualDBValueFormated("PRUEFUNG_LADEMENGE").equals("Y")  || 
//				oThis.get_cActualDBValueFormated("PRUEFUNG_ABLADEMENGE").equals("Y"))
//			{
//				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Diese Funktion ist nach dem Abschliessen einer Prüfung nicht mehr möglich !"));
//				return;
//			}
//			
//			
//			//ein sonderfall: das ladedatum enthaelt einen wert, das abladedatum nicht, dann wird das abladedatum mit dem ladedatum gefuellt 
//			String cDatumZumFuellen = null;
//
//			
//			String cDatumAbholungStart = myDateHelper.get_CheckedDate(oThis.get_cActualDBValueFormated("DATUM_ABHOLUNG", bibALL.get_cDateNOW()));
//			if (cDatumAbholungStart == null) 
//			{
//				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Falsches Datum im Feld <DATUM_ABHOLUNG>"));
//				return;
//			}
//			else
//			{
//				//auf jeden fall richtig formatieren
//				((MyE2IF__DB_Component)oThis.get__Comp("DATUM_ABHOLUNG")).set_cActualMaskValue(cDatumAbholungStart);
//			}
//			
//			//standard: 
//			cDatumZumFuellen = cDatumAbholungStart;
//			
//			//jetzt die kombination aufladedatum/abladedatum pruefen (durchfuehrungsdatum) pruefen
//			if (S.isFull(oThis.get_cActualDBValueFormated("DATUM_AUFLADEN")))
//			{
//				String cDatumAufladen = myDateHelper.get_CheckedDate(oThis.get_cActualDBValueFormated("DATUM_AUFLADEN", ""));
//				if (cDatumAufladen == null) 
//				{
//					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Falsches Datum im Feld <DATUM_AUFLADEN>"));
//					return;
//				}
//				else
//				{
//					//auf jeden fall richtig formatieren
//					((MyE2IF__DB_Component)oThis.get__Comp("DATUM_AUFLADEN")).set_cActualMaskValue(cDatumAufladen);
//				}
//			
//				String cDatumAbladen = 	myDateHelper.get_CheckedDate(oThis.get_cActualDBValueFormated("DATUM_ABLADEN", ""));
//				if (S.isFull(cDatumAufladen) && S.isEmpty(cDatumAbladen))
//				{
//					cDatumZumFuellen = cDatumAufladen;
//				}
//			}
//			//das cDatumZumFuellen wird jetzt auf die leeren felder verteilt
//			
//			
//			MyDate  oDate = new MyDate(cDatumZumFuellen);
//			if (oDate.get_cErrorCODE().equals(MyDate.ALL_OK))
//			{
//				cDatumZumFuellen = oDate.get_cUmwandlungsergebnis();
//			}
//
//			//2011-02-01: nur fuellen, wenn vorher leer
//			if (oThis.get_cActualDBValueFormated("DATUM_ABHOLUNG","@@@@#@@@@@").equals("@@@@#@@@@@")) 			{ ((MyE2IF__DB_Component)oThis.get__Comp("DATUM_ABHOLUNG")).set_cActualMaskValue(cDatumZumFuellen);}
//			if (oThis.get_cActualDBValueFormated("DATUM_ABHOLUNG_ENDE","@@@@#@@@@@").equals("@@@@#@@@@@")) 		{ ((MyE2IF__DB_Component)oThis.get__Comp("DATUM_ABHOLUNG_ENDE")).set_cActualMaskValue(cDatumZumFuellen);}
//			if (oThis.get_cActualDBValueFormated("DATUM_ANLIEFERUNG","@@@@#@@@@@").equals("@@@@#@@@@@")) 		{ ((MyE2IF__DB_Component)oThis.get__Comp("DATUM_ANLIEFERUNG")).set_cActualMaskValue(cDatumZumFuellen);}
//			if (oThis.get_cActualDBValueFormated("DATUM_ANLIEFERUNG_ENDE","@@@@#@@@@@").equals("@@@@#@@@@@")) 	{ ((MyE2IF__DB_Component)oThis.get__Comp("DATUM_ANLIEFERUNG_ENDE")).set_cActualMaskValue(cDatumZumFuellen);}
//			if (oThis.get_cActualDBValueFormated("DATUM_ABLADEN","@@@@#@@@@@").equals("@@@@#@@@@@")) 			{ ((MyE2IF__DB_Component)oThis.get__Comp("DATUM_ABLADEN")).set_cActualMaskValue(cDatumZumFuellen);}
//			if (oThis.get_cActualDBValueFormated("DATUM_AUFLADEN","@@@@#@@@@@").equals("@@@@#@@@@@")) 			{ ((MyE2IF__DB_Component)oThis.get__Comp("DATUM_AUFLADEN")).set_cActualMaskValue(cDatumZumFuellen);}
//			
//		}
//		
//
//	}
	
}
