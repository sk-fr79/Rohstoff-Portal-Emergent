package rohstoff.Echo2BusinessLogic.ARTIKELSTAMM;

import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_EAK_CODE_ext;

public class AS_MASK_ComponentMap_SubQueryAgent extends   XX_ComponentMAP_SubqueryAGENT {

	@Override
	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP) 	throws myException 
	{
		MyE2_Grid oGridInfo = (MyE2_Grid) oMAP.get__Comp(AS___CONST.HASH_KEY_MASK_INFO_FIELD);
		oGridInfo.removeAll();
	}


	@Override
	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP)	throws myException 
	{
		
		try 
		{
			MyE2_Grid oGridInfo = (MyE2_Grid) oMAP.get__Comp(AS___CONST.HASH_KEY_MASK_INFO_FIELD);
			
			oGridInfo.setSize(10);
			oGridInfo.removeAll();
			
			oMAP.get_cActualDBValueFormated("ANR1");
			
			oGridInfo.add(new MyE2_Label(oMAP.get_cActualDBValueFormated("ANR1")),E2_INSETS.I_2_2_10_2);
			oGridInfo.add(new MyE2_Label(oMAP.get_cActualDBValueFormated("ARTBEZ1")),E2_INSETS.I_2_2_10_2);
			
			GridLayoutData  oGL_RED = new GridLayoutData();
			
			oGL_RED.setInsets(E2_INSETS.I_2_2_10_2);
			oGL_RED.setBackground(new E2_ColorAlarm());
			
			String cID_EAK_CODE_EX_MANDANT=oMAP.get_cActualDBValueFormated("ID_EAK_CODE_EX_MANDANT", "");
			
			if (!S.isEmpty(cID_EAK_CODE_EX_MANDANT))
			{
				cID_EAK_CODE_EX_MANDANT = bibALL.ReplaceTeilString(cID_EAK_CODE_EX_MANDANT, ".", "");
					
				RECORD_EAK_CODE_ext  recCode = new RECORD_EAK_CODE_ext(cID_EAK_CODE_EX_MANDANT);
				if (recCode.is_GEFAEHRLICH_YES())
				{
					oGridInfo.add(new MyE2_Label(recCode.get_complete_Code()),oGL_RED);
				}
				else
				{
					oGridInfo.add(new MyE2_Label(recCode.get_complete_Code()),E2_INSETS.I_2_2_10_2);
				}
			}
			
			if (oMAP.get_cActualDBValueFormated("GEFAHRGUT","N").endsWith("Y"))
			{
				oGridInfo.add(new MyE2_Label(new MyE2_String("GEFAHRGUT")),oGL_RED);
			}
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Fehler bei der Darstellung des Sorteninfo-Felds ...")));
		}
		
		
	}

}
