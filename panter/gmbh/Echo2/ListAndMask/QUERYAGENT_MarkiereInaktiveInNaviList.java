package panter.gmbh.Echo2.ListAndMask;

import java.util.Iterator;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Column;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.Row;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;

public class QUERYAGENT_MarkiereInaktiveInNaviList extends 	XX_ComponentMAP_SubqueryAGENT {

	private String cFieldNameACTIVE = null;
	
	
	public QUERYAGENT_MarkiereInaktiveInNaviList(String FieldNameACTIVE) 
	{
		super();
		this.cFieldNameACTIVE = FieldNameACTIVE;
	}

	@Override
	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP) throws myException 
	{

	}

	@Override
	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP)	throws myException 
	{

		if (oUsedResultMAP.containsKey(this.cFieldNameACTIVE))
		{
		
			boolean bAktiv = S.NN(oUsedResultMAP.get_UnFormatedValue(this.cFieldNameACTIVE)).equals("Y");
			
			
			if (!bAktiv)
			{
				Iterator<String>  oIterator = oMAP.get_hmRealComponents().keySet().iterator();
				
				while (oIterator.hasNext())
				{
					MyE2IF__Component oComp = oMAP.get_hmRealComponents().get(oIterator.next());
					
					if (oComp instanceof Grid)
					{
						Grid oGridHelp = (Grid)oComp;
						Component[] oComps = oGridHelp.getComponents();
						
						for (int i=0;i<oComps.length;i++)
						{
							if (oComps[i] instanceof MyE2IF__Component)
							{
								this.pruefeKomponente((MyE2IF__Component)oComps[i]);
							}
						}
					} 
					else if (oComp instanceof Column)
					{
						Column oGridHelp = (Column)oComp;
						Component[] oComps = oGridHelp.getComponents();
						
						for (int i=0;i<oComps.length;i++)
						{
							if (oComps[i] instanceof MyE2IF__Component)
							{
								this.pruefeKomponente((MyE2IF__Component)oComps[i]);
							}
						}
					} 
					else if (oComp instanceof Row)
					{
						Row oGridHelp = (Row)oComp;
						Component[] oComps = oGridHelp.getComponents();
						
						for (int i=0;i<oComps.length;i++)
						{
							if (oComps[i] instanceof MyE2IF__Component)
							{
								this.pruefeKomponente((MyE2IF__Component)oComps[i]);
							}
						}
					} 
					else
					{
						this.pruefeKomponente(oComp);
					}
					
				}
			}
		}
	}
	
	private void pruefeKomponente(MyE2IF__Component oComp)
	{
		Color  oColInaktiv = new Color(80, 80, 80);

		
		if (oComp instanceof MyE2_DB_Label_INROW)
		{
			((MyE2_DB_Label_INROW)oComp).get_oErsatzButton().setForeground(oColInaktiv);
		}
		else if (oComp instanceof MyE2_Label)
		{
			((MyE2_Label)oComp).setForeground(oColInaktiv);
		} 

	}

}
