package rohstoff.utils.ECHO2;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.XX_ListSelektor;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;


/**
 * list-selector-klasse fuer die selektion von benutzern
 * @author martin
 *
 */
public class LIST_SELECTOR_BENUTZER extends XX_ListSelektor
{
	private GridLayoutData  gl4Components = null;

	private Vector<String> 	vSQL_FillDropDownQuery = new Vector<String>();      //vector aus abfragen, die nacheinander in der liste auftauchen
	private String 			cSQL_WhereBlock = "";
	
	private MyString 		cBeschriftung = new MyE2_String("Mitarbeiter");
	
	private String[][]      arrDD = null;
	
	private MyE2_SelectField  oSelUser = new MyE2_SelectField();
	
	/**
	 * 
	 * @param cWhereBlock (mit #WERT#), mit vorgefertigten benutzerabfragen
	 * @throws myException
	 */
	public LIST_SELECTOR_BENUTZER(MyString cBeschriftungstext, String cWhereBlock) throws myException
	{
		super();

		this.cSQL_WhereBlock = cWhereBlock;
		if (cBeschriftungstext!=null){ this.cBeschriftung = cBeschriftungstext;}
		this.gl4Components = MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_0_0_5_0);

		
		//buero benutzer aktiv
		this.vSQL_FillDropDownQuery.add("SELECT NVL(VORNAME,'-')||' '||NVL(NAME1,'-') AS A,ID_USER AS B FROM "+bibE2.cTO()+".JD_USER" +
				" WHERE NVL(IST_VERWALTUNG,'N')='Y' AND NVL(AKTIV,'N')='Y' AND ID_MANDANT="+bibALL.get_ID_MANDANT()+" ORDER BY 1");
		
		//buero benutzer inaktiv
		this.vSQL_FillDropDownQuery.add("SELECT NVL(VORNAME,'-')||' '||NVL(NAME1,'-')||' (INAKTIV)' AS A,ID_USER AS B FROM "+bibE2.cTO()+".JD_USER" +
				" WHERE NVL(IST_VERWALTUNG,'N')='Y' AND NVL(AKTIV,'N')='N' AND ID_MANDANT="+bibALL.get_ID_MANDANT()+" ORDER BY 1");
		
		//rest
		this.vSQL_FillDropDownQuery.add("SELECT NVL(VORNAME,'-')||' '||NVL(NAME1,'-')||' (BETRIEB)' AS A,ID_USER AS B FROM "+bibE2.cTO()+".JD_USER" +
				" WHERE NVL(IST_VERWALTUNG,'N')='N'  AND ID_MANDANT="+bibALL.get_ID_MANDANT()+" ORDER BY 1");
		

		this.fill_Array();
		this.oSelUser.set_ListenInhalt(this.arrDD, false);

	}

	
	private void fill_Array()
	{
		Vector<String[][]>  vListe = new Vector<String[][]>();
		
		for (int i=0;i<this.vSQL_FillDropDownQuery.size();i++)
		{
			vListe.add(bibDB.EinzelAbfrageInArray(this.vSQL_FillDropDownQuery.get(i),"-")); 
		}
		
		int iAnzahl = 0;
		for (int i=0;i<vListe.size();i++)
		{
			iAnzahl = iAnzahl+ (vListe.get(i)==null?0:vListe.get(i).length);
		}
		
		this.arrDD = new String[iAnzahl+1][2];
		
		this.arrDD[0][0]="-";
		this.arrDD[0][1]="";
		
		int iNdex= 1;
		for (int i=0;i<vListe.size();i++)
		{
			String[][] array = vListe.get(i);
			if (array != null)
			{
				for (int k=0;k<array.length;k++)
				{
					this.arrDD[iNdex][0]=array[k][0];
					this.arrDD[iNdex++][1]=array[k][1];
				}
			}
		}
		
		
	}
	
	
	@Override
	public String get_WhereBlock() throws myException
	{
		if (S.isEmpty(this.oSelUser.get_ActualWert()))
		{
			return "";	
		}
		else
		{
			return bibALL.ReplaceTeilString(this.cSQL_WhereBlock, "#WERT#", this.oSelUser.get_ActualWert());
		}
	}

	@Override
	public Component get_oComponentForSelection()
	{
		return this.get_oComponentForSelection(100,100);
	}

	@Override
	public Component get_oComponentWithoutText()
	{
		return this.get_oComponentForSelection(null,100);
	}

	@Override
	public void add_ActionAgentToComponent(XX_ActionAgent oAgent)
	{
		this.oSelUser.add_oActionAgent(oAgent);
	}

	@Override
	public void doInternalCheck()
	{

	}
	
	
	
	public MyE2_Grid get_oComponentForSelection(Integer iWidthBeschreibung, Integer iWidthSelektor)
	{
		int iCount = 0;
	    if (iWidthBeschreibung!=null)  {iCount++;}
	    if (iWidthSelektor!=null)  {iCount++;}
	    
	    MyE2_Grid  oGrid = new MyE2_Grid(iCount, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
	    
	    iCount = 0;
	    if (iWidthBeschreibung!=null) 	{oGrid.setColumnWidth(iCount++,new Extent(iWidthBeschreibung.intValue()));}
	    if (iWidthSelektor!=null) 		
	    {
	    	oGrid.setColumnWidth(iCount++,new Extent(iWidthSelektor.intValue()));
	    	this.oSelUser.setWidth(new Extent(iWidthSelektor.intValue()-10));
	    }
	    
	    iCount = 0;
	    if (iWidthBeschreibung!=null) 	{oGrid.add(new MyE2_Label(this.cBeschriftung), this.gl4Components);}
	    if (iWidthSelektor!=null) 		{oGrid.add(this.oSelUser, this.gl4Components);}
	    
	    return oGrid;
	}

	public GridLayoutData get_oGl4Components()
	{
		return gl4Components;
	}



	public void set_oGl4Components(GridLayoutData gl4Components)
	{
		this.gl4Components = gl4Components;
	}

}
