package panter.gmbh.Echo2.__CONTAINER_ADDONS.TODOS;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Font;
import panter.gmbh.Echo2.FontsAndColors.E2_Font;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.TestingDate;

public class TODO_LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT 
{

	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)	throws myException 
	{
		((MyE2_DB_Label)oMAP.get__Comp("ABLAUFDATUM")).setBackground(null);
		 E2_Font oFont = new E2_Font(Font.PLAIN,0);

		((MyE2_DB_Label)oMAP.get__Comp("ABLAUFDATUM")).setFont(oFont);
		((MyE2_DB_Label)oMAP.get__Comp("GENERIERUNGSDATUM")).setFont(oFont);
		((MyE2_DB_Label)oMAP.get__Comp("ABSCHLUSSDATUM")).setFont(oFont);
		((MyE2_DB_Label)oMAP.get__Comp("AUFGABEKURZ")).setFont(oFont);


	}

	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
	{
		String cDate =oUsedResultMAP.get_oResultField("ABLAUFDATUM").get_cDateValueFormated();

		((MyE2_DB_Label)oMAP.get__Comp("ABLAUFDATUM")).setBackground(null);
		
		boolean bIsDone = bibALL.null2leer(oUsedResultMAP.get_oResultField("ERLEDIGT").get_FieldValueFormated()).equals("Y");
		

		// alles auf start
		E2_Font oFont = new E2_Font(Font.PLAIN,0);
		((MyE2_DB_Label)oMAP.get__Comp("ABLAUFDATUM")).setFont(oFont);
		((MyE2_DB_Label)oMAP.get__Comp("GENERIERUNGSDATUM")).setFont(oFont);
		((MyE2_DB_Label)oMAP.get__Comp("ABSCHLUSSDATUM")).setFont(oFont);
		((MyE2_DB_Label)oMAP.get__Comp("AUFGABEKURZ")).setFont(oFont);

		
		
		if (!bIsDone && !bibALL.isEmpty(cDate))
		{
			TestingDate oTDateAblauf = new TestingDate(cDate);
			TestingDate oTDateNow = new TestingDate(bibALL.get_cDateNOW());
			
			if (oTDateAblauf.testing() && oTDateNow.testing())
			{
				long Diffdate = myDateHelper.get_DayDifference_Date2_MINUS_Date1(oTDateNow.get_Calendar(),oTDateAblauf.get_Calendar());
				
				if (Diffdate>30)
				{
				}
				else if (Diffdate<=30 && Diffdate>=10)
				{
					((MyE2_DB_Label)oMAP.get__Comp("ABLAUFDATUM")).setBackground(Color.YELLOW);
				}
				else
				{
					((MyE2_DB_Label)oMAP.get__Comp("ABLAUFDATUM")).setBackground(Color.RED);
				}
			}
		}
		
		if (bIsDone)
		{
			 E2_Font oDelFont = new E2_Font(Font.ITALIC+Font.LINE_THROUGH,-2);
			((MyE2_DB_Label)oMAP.get__Comp("ABLAUFDATUM")).setFont(oDelFont);
			((MyE2_DB_Label)oMAP.get__Comp("GENERIERUNGSDATUM")).setFont(oDelFont);
			((MyE2_DB_Label)oMAP.get__Comp("ABSCHLUSSDATUM")).setFont(oDelFont);
			((MyE2_DB_Label)oMAP.get__Comp("AUFGABEKURZ")).setFont(oDelFont);
		}
		
		
		

	}

}
