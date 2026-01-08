package panter.gmbh.Echo2.components.SortGrid;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public abstract class E2_SortGrid extends MyE2_Grid
{
	public static final String SORTED_NOT = 	"UNSORTED";
	public static final String SORTED_UP = 		"SORTEDUP";
	public static final String SORTED_DOWN = 	"SORTEDDOWN";
	
	
	private Vector<E2_SortGridListenZeile>   	VectorWithComponentArrays = null;
	private boolean               				bHasTitelZeile = true;
	
	//hashmap speichert den letzten sortstatus der spalte. key = spalte, value = status 
	private Integer                             iLastSortedColumn = null;
	private String   	    					cLastSortStatus = null;
	
	public E2_SortGrid()
	{
		super();
	}

	public E2_SortGrid(int iNumCols, int iBorderSize)
	{
		super(iNumCols, iBorderSize);
	}

	public E2_SortGrid(int iNumCols, MutableStyle oStyle)
	{
		super(iNumCols, oStyle);
	}

	public E2_SortGrid(int iNumCols)
	{
		super(iNumCols);
	}

	public E2_SortGrid(int[] iSpalten, int iBorderSize)
	{
		super(iSpalten, iBorderSize);
	}

	public E2_SortGrid(int[] iSpalten, MutableStyle oStyle, boolean bScaleRowsTo100Percent)
	{
		super(iSpalten, oStyle, bScaleRowsTo100Percent);
	}

	public E2_SortGrid(int[] iSpalten, MutableStyle oStyle)
	{
		super(iSpalten, oStyle);
	}

	public E2_SortGrid(MutableStyle oStyle)
	{
		super(oStyle);
	}

	public boolean get_bHasTitelZeile()
	{
		return bHasTitelZeile;
	}

	public void set_bHasTitelZeile(boolean HasTitelZeile)
	{
		this.bHasTitelZeile = HasTitelZeile;
	}

	
	public void RESET_LISTE()
	{
		this.VectorWithComponentArrays=null;                //zwingt zum neuaufbau
	}
	
	
	public void SortListe(Integer Spalte, boolean bUPDown_true_is_up) throws myException
	{
		if (this.VectorWithComponentArrays==null)
		{
			this.VectorWithComponentArrays = this.build_VectorWithComponentArrays();
		}
		
		Sorter  	oSorter = new Sorter(bUPDown_true_is_up, Spalte);
		Collections.sort(this.VectorWithComponentArrays,oSorter);
	}
	
	
	public void SortListe() throws myException
	{
		if (this.VectorWithComponentArrays==null)
		{
			this.VectorWithComponentArrays = this.build_VectorWithComponentArrays();
		}
		
		if (this.iLastSortedColumn != null && S.isFull(this.cLastSortStatus))
		{
			Sorter  	oSorter = new Sorter(S.NN(this.cLastSortStatus).equals(E2_SortGrid.SORTED_UP), this.iLastSortedColumn);
			Collections.sort(this.VectorWithComponentArrays,oSorter);
		}
		else
		{
			throw new myException(this,"Sortinformation is not available !!!");
		}
	}
	
	
	
	
	
	public void BaueListeAuf() throws myException
	{
		if (this.VectorWithComponentArrays==null)
		{
			this.VectorWithComponentArrays = this.build_VectorWithComponentArrays();
		}
		
		
		this.removeAll();
		
		int iSpaltenZahl = this.get_iSpaltenZahl();
		
		this.setSize(iSpaltenZahl);
		
		if (this.bHasTitelZeile)
		{
			for (int i=0;i<iSpaltenZahl;i++)
			{
				Component oTitle = this.get_TitelComponent(i);
				if (oTitle == null)
				{
					throw new myException(this,"Error: In column "+i+" is a null-title-component !!!");
				}

				this.add(oTitle);
			}
		}

		for (int i=0;i<this.VectorWithComponentArrays.size();i++)
		{
			Component[] oZeile = this.VectorWithComponentArrays.get(i).get_KomponentenZeile();
			
			if (this.VectorWithComponentArrays.get(i).get_bZeileIstSichtbar())
			{
				if (oZeile.length != iSpaltenZahl)
				{
					throw new myException(this,"Error! Number of columns is not consistent !!!");
				}
				
				for (int k=0;k<oZeile.length;k++)
				{
					if (oZeile[k] == null)
					{
						throw new myException(this,"Error: In column "+k+" is a null-component !!!");
					}
					
					this.add(oZeile[k]);
				}
			}
		}
	}
	
	
	public abstract Integer   get_iSpaltenZahl() throws myException;
	public abstract Component get_TitelComponent(int iSpalte) throws myException;
	public abstract Vector<E2_SortGridListenZeile>  build_VectorWithComponentArrays() throws myException;
	public abstract String    get_SaveKeyForUserSettings();
	
	

	
	@SuppressWarnings("rawtypes")
	private class Sorter implements Comparator<E2_SortGridListenZeile>
	{
		
		public 	boolean 				UP_DOWN = 		false;
		private Integer 				ColumnToSort = null;
		
		public Sorter(boolean bUP_DOWN, int iColumnToSort) 
		{
			super();
			this.UP_DOWN = bUP_DOWN; 
			this.ColumnToSort = iColumnToSort;
		}

		
		@SuppressWarnings("unchecked")
		public int compare(E2_SortGridListenZeile o1, E2_SortGridListenZeile o2) 
		{
			Comparable 	oSorter1 = null;
			Comparable 	oSorter2 = null;
			
			try
			{
				oSorter1 = o1.get_SortableObject(this.ColumnToSort);
				oSorter2 = o2.get_SortableObject(this.ColumnToSort);
				
				if (oSorter1==null || oSorter2==null)
				{
					return 0;
				}
				
				if (this.UP_DOWN)
				{
					return oSorter1.compareTo(oSorter2);
				}
				else
				{
					return oSorter2.compareTo(oSorter1);
				}
			}
			catch (myException e)   //bei fehler wird gleichheit vorgegaukelt
			{
				e.printStackTrace();
				return 0;
			}
		}
	}




	public Integer get_iLastSortedColumn()
	{
		return iLastSortedColumn;
	}

	public String get_cLastSortStatus()
	{
		return cLastSortStatus;
	}

	public void set_iLastSortedColumn(Integer iLastSortedColumn)
	{
		this.iLastSortedColumn = iLastSortedColumn;
	}

	public void set_cLastSortStatus(String cLastSortStatus)
	{
		this.cLastSortStatus = cLastSortStatus;
	}


	
	
	
}
