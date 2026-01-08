package rohstoff.Echo2BusinessLogic.PROJEKTVERWALTUNG;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Font;
import panter.gmbh.Echo2.FontsAndColors.E2_Font;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.TestingDate;

public class PROJEKT_LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT 
{

	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException 
	{
		this.resetAll(oMAP);
	}

	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
	{
		String cDate =oUsedResultMAP.get_oResultField("PROJEKTDEADLINE").get_cDateValueFormated();

		boolean bIsDone = bibALL.null2leer(oUsedResultMAP.get_oResultField("BEENDET").get_FieldValueFormated()).equals("Y");
		boolean bIsAktiv = bibALL.null2leer(oUsedResultMAP.get_oResultField("AKTIV").get_FieldValueFormated()).equals("Y");

		// alles auf start
		this.resetAll(oMAP);
		
		
		
		if (bIsDone)
		{
			this.makeAll(oMAP, new E2_Font(Font.ITALIC+Font.LINE_THROUGH,-2));
		}
		else if (!bIsAktiv)
		{
			this.makeAll(oMAP, new E2_Font(Font.PLAIN,-2));
		}
		
	
		if (!bibALL.isEmpty(cDate))
		{
			TestingDate oTDateAblauf = new TestingDate(cDate);
			TestingDate oTDateNow = new TestingDate(bibALL.get_cDateNOW());
			
			if (oTDateAblauf.testing() && oTDateNow.testing())
			{
				long Diffdate = myDateHelper.get_DayDifference_Date2_MINUS_Date1(oTDateNow.get_Calendar(),oTDateAblauf.get_Calendar());

				((MyE2_Label)oMAP.get__Comp(PROJEKT_LIST_BasicModuleContainer.NAME_OF_TAGE_DIFF_IN_LIST)).setText(""+Diffdate);
				
				if (!bIsDone && bIsAktiv)  // wenn aktiv, dann einfaerben
				{
					if (Diffdate>30)
					{
					}
					else if (Diffdate<=30 && Diffdate>=10)
					{
						((MyE2_DB_Label)oMAP.get__Comp("PROJEKTDEADLINE")).setBackground(Color.YELLOW);
					}
					else
					{
						((MyE2_DB_Label)oMAP.get__Comp("PROJEKTDEADLINE")).setBackground(Color.RED);
					}
				}
			}
		}
		
	}
	

	private void resetAll(E2_ComponentMAP oMAP) throws myException
	{
		
		((MyE2_DB_Label)oMAP.get__Comp("PROJEKTDEADLINE")).setBackground(null);

		E2_Font oFont = new E2_Font(Font.PLAIN,0);

		((MyE2_Label)oMAP.get__Comp(PROJEKT_LIST_BasicModuleContainer.NAME_OF_TAGE_DIFF_IN_LIST)).setFont(oFont);
		((MyE2_DB_Label)oMAP.get__Comp("U_NAME_KUERZEL")).setFont(oFont);
		((MyE2_DB_Label)oMAP.get__Comp("PROJEKTNAME")).setFont(oFont);
		((MyE2_DB_TextArea)oMAP.get__Comp("PROJEKTBESCHREIBUNG")).setFont(oFont);
		((MyE2_DB_Label)oMAP.get__Comp("PROJEKTBEGIN")).setFont(oFont);
		((MyE2_DB_Label)oMAP.get__Comp("PROJEKTDEADLINE")).setFont(oFont);
		((MyE2_DB_Label)oMAP.get__Comp("WIEDERVORLAGE")).setFont(oFont);

	}
	

	private void makeAll(E2_ComponentMAP oMAP, Font oFont) throws myException
	{
		((MyE2_Label)oMAP.get__Comp(PROJEKT_LIST_BasicModuleContainer.NAME_OF_TAGE_DIFF_IN_LIST)).setFont(oFont);
		((MyE2_DB_Label)oMAP.get__Comp("U_NAME_KUERZEL")).setFont(oFont);
		((MyE2_DB_Label)oMAP.get__Comp("PROJEKTNAME")).setFont(oFont);
		((MyE2_DB_TextArea)oMAP.get__Comp("PROJEKTBESCHREIBUNG")).setFont(oFont);
		((MyE2_DB_Label)oMAP.get__Comp("PROJEKTBEGIN")).setFont(oFont);
		((MyE2_DB_Label)oMAP.get__Comp("PROJEKTDEADLINE")).setFont(oFont);
		((MyE2_DB_Label)oMAP.get__Comp("WIEDERVORLAGE")).setFont(oFont);

	}

}
