package panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Vector;

import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.DotFormatter_autoFormat;

public class E2_SortVector extends Vector<E2_SortObject>
{
	private Vector<XX_Button4SearchResultList[]> 	vButtons = null;
	private int    									ColumnNumber = -1;
	private boolean  								bSortUp = true;
	
	public E2_SortVector(Vector<XX_Button4SearchResultList[]> v_Buttons, int iColumnNumberToSort, boolean bUp) throws myException
	{
		super();
		
		this.vButtons = 		v_Buttons;
		this.ColumnNumber = 	iColumnNumberToSort;
		this.bSortUp = 			bUp;
		
		this.prepare_Sort();
		
		if (this.bSortUp)
		{
			this.SortUp();
		}
		else
		{
			this.SortDown();
		}
		
		//jetzt den ausgangsvector neu fuellen
		//nach der sortierung neu aufbauen
		this.vButtons.removeAllElements();
		for (int i=0;i<this.size();i++)
		{
			vButtons.add(this.get(i).ArrayButtons);
		}
		
	}

	/*
	 * prepare-sort baut den vector auf
	 */
	private void prepare_Sort() throws myException
	{
		if (this.get_TypeIsNumber(this.ColumnNumber))
		{
			for (int i = 0; i < vButtons.size(); i++)
			{
				BigDecimal bdSort = new BigDecimal(0);
				MyBigDecimal mbdSort = new MyBigDecimal(vButtons.get(i)[this.ColumnNumber].get_SortText());
				if (mbdSort.get_cErrorCODE().equals(MyBigDecimal.ALL_OK))
				{
					bdSort = mbdSort.get_bdWert();
				}
				this.add(new E2_SortObject(this.vButtons.get(i), bdSort));
			}
		}
		else if (this.get_TypeIsDate(this.ColumnNumber))
		{
			for (int i = 0; i < this.vButtons.size(); i++)
			{
				GregorianCalendar calSort = new MyDate("01.01.1900").get_Calendar();
	
				MyDate mcalSort = new MyDate(this.vButtons.get(i)[this.ColumnNumber].get_SortText());
				if (mcalSort.get_cErrorCODE().equals(MyDate.ALL_OK))
				{
					calSort = mcalSort.get_Calendar();
				}
				this.add(new E2_SortObject(this.vButtons.get(i), calSort));
			}
		}
		else
		// sortierung nach string
		{
			for (int i = 0; i < this.vButtons.size(); i++)
			{
				this.add(new E2_SortObject(this.vButtons.get(i), S.NN(this.vButtons.get(i)[this.ColumnNumber].get_SortText())));
			}
		}
	}
	
	
	
	
	public void SortUp()
	{
		Collections.sort(this, new Comparator<E2_SortObject>()
		{

			@Override
			public int compare(E2_SortObject o1, E2_SortObject o2)
			{
				if (o1.SortString != null && o2.SortString != null)
				{
					return o1.SortString.compareTo(o2.SortString);
				}
				else if (o1.BDSort != null && o2.BDSort != null)
				{
					return o1.BDSort.compareTo(o2.BDSort);
				}
				else if (o1.Calendar != null && o2.Calendar != null)
				{
					return o1.Calendar.compareTo(o2.Calendar);
				}

				return 0;
			}
		});
	}

	public void SortDown()
	{
		Collections.sort(this, new Comparator<E2_SortObject>()
		{

			@Override
			public int compare(E2_SortObject o1, E2_SortObject o2)
			{
				if (o2.SortString != null && o1.SortString != null)
				{
					return o2.SortString.compareTo(o1.SortString);
				}
				else if (o2.BDSort != null && o1.BDSort != null)
				{
					return o2.BDSort.compareTo(o1.BDSort);
				}
				else if (o2.Calendar != null && o1.Calendar != null)
				{
					return o2.Calendar.compareTo(o1.Calendar);
				}

				return 0;
			}
		});
	}
	
	
	
	/*
	 * prueft, ob alle buttons einer spalte eine nummer tragen
	 */
	public boolean get_TypeIsNumber(int iColumn) throws myException
	{
		boolean bRueck = true;

		for (int i = 0; i < this.vButtons.size(); i++)
		{
			if (S.isEmpty(this.vButtons.get(i)[iColumn].get_SortText()) || this.vButtons.get(i)[iColumn].get_SortText().equals("-") || this.vButtons.get(i)[iColumn].get_SortText().equals("--"))
			{
				bRueck = bRueck && true;
			}
			else if (new MyBigDecimal( new DotFormatter_autoFormat(this.vButtons.get(i)[iColumn].get_SortText())).get_cErrorCODE().equals(MyBigDecimal.ALL_OK))
			{
				bRueck = bRueck && true;
			}
			else
			{
				bRueck = bRueck && false;
			}

			if (!bRueck)
			{
				break;
			}
		}
		return bRueck;
	}

	public boolean get_TypeIsDate(int iColumn) throws myException
	{
		boolean bRueck = true;

		for (int i = 0; i < this.vButtons.size(); i++)
		{
			if (S.isEmpty(this.vButtons.get(i)[iColumn].get_SortText()) || this.vButtons.get(i)[iColumn].get_SortText().equals("-") || this.vButtons.get(i)[iColumn].get_SortText().equals("--"))
			{
				bRueck = bRueck && true;
			}
			else if (new MyDate(this.vButtons.get(i)[iColumn].get_SortText()).get_cErrorCODE().equals(MyDate.ALL_OK))
			{
				bRueck = bRueck && true;
			}
			else
			{
				bRueck = bRueck && false;
			}

			if (!bRueck)
			{
				break;
			}
		}
		return bRueck;
	}

	
	
	
}

	

