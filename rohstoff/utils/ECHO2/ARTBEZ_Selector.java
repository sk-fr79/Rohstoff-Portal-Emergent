package rohstoff.utils.ECHO2;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.E2_Selection_Row_With_Multi_Cols;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class ARTBEZ_Selector extends E2_Selection_Row_With_Multi_Cols
{

	/*
	 * soll verhindert werden, dass auf EK-Seite ein nicht gelisteter Artikel auswaehlbar ist,
	 * dann muss hier ein MyE2_textField mit dem aktuellen maskeninhalt der adress-id uebergeben werden 
	 */
	private XX_Tell_me_the_actual_adress_id  oTell_Adress_ID = null;
	
	public ARTBEZ_Selector(XX_Tell_me_the_actual_adress_id  tell_adress_id) throws myException
	{
		super(MyE2_Row.STYLE_THIN_BORDER(),200,true);
		
		this.oTell_Adress_ID = tell_adress_id;
		
		this.add_QueryStringForSteps("SELECT ID_ARTIKEL_BEREICH,BEREICH  FROM "+bibE2.cTO()+".JT_ARTIKEL_BEREICH ORDER BY BEREICH " , 
										new MyE2_String("Sortenbereich"),300,1,new ownButtonbuilder(300,0));
		
		this.add_QueryStringForSteps("SELECT ID_ARTIKEL_GRUPPE,GRUPPE  FROM "+bibE2.cTO()+".JT_ARTIKEL_GRUPPE WHERE JT_ARTIKEL_GRUPPE.ID_ARTIKEL_BEREICH=#WERT1# ORDER BY GRUPPE " , 
										new MyE2_String("Sortengruppe"),300,1,new ownButtonbuilder(300,1));

		this.add_QueryStringForSteps("SELECT ID_ARTIKEL,ANR1||'  '||ARTBEZ1 FROM "+bibE2.cTO()+".JT_ARTIKEL WHERE JT_ARTIKEL.ID_ARTIKEL_GRUPPE=#WERT2#  ORDER BY 2" , 
										new MyE2_String("Sorte"),300,1,new ownButtonbuilder(300,2));
		
		this.add_QueryStringForSteps("SELECT ID_ARTIKEL_BEZ, JT_ARTIKEL.ANR1||'-'||JT_ARTIKEL_BEZ.ANR2||'  '||JT_ARTIKEL_BEZ.ARTBEZ1 FROM "+bibE2.cTO()+".JT_ARTIKEL_BEZ " +
									" INNER JOIN JT_ARTIKEL ON (JT_ARTIKEL_BEZ.ID_ARTIKEL=JT_ARTIKEL.ID_ARTIKEL)" +
									" WHERE JT_ARTIKEL.ID_ARTIKEL=#WERT3#  ORDER BY 2" , 
										new MyE2_String("Sortenbezeichnung"),300,1,new ownButtonbuilder(300,3));

		
	}

	
	private class ownButtonbuilder extends  E2_Selection_Row_With_Multi_Cols.standardButtonBuilder
	{
		private int iColWidth = 200;
		private int iColNum = 0;
		
		
		public ownButtonbuilder(int iColWidth, int icolNum) 
		{
			super();
			this.iColWidth = iColWidth;
			this.iColNum = icolNum;
			
		}

		
		public Vector<MyE2_Button> build_Buttons_In_Grid(E2_Selection_Row_With_Multi_Cols oMultiSel, String[] ergebnisZeile, SelectBlockGrid ownSelectGrid)	throws myException
		{
			Vector<MyE2_Button>  vButtonsInRow= super.build_Buttons_In_Grid(oMultiSel, ergebnisZeile, ownSelectGrid);
			
			ARTBEZ_Selector oThis = ARTBEZ_Selector.this;
			
			for (int i=0;i<vButtonsInRow.size();i++)
			{
				if (S.NN(vButtonsInRow.get(i).getText()).length()>(this.iColWidth/7))
				{
					vButtonsInRow.get(i).setFont(new E2_FontPlain(-2));
				}
				
				vButtonsInRow.get(i).set_bEnabled_For_Edit(false);
				
				//jetzt pruefen, ob die selektion erlaubt ist
				
				if (oThis.oTell_Adress_ID!=null)
				{
					//dann muss die pruefung auf "Artikelbez. gelistet" geprueft werden
					MyLong lHelp = new  MyLong(bibALL.ReplaceTeilString(S.NN(oThis.oTell_Adress_ID.get_unformated_Adress_ID_or_null()),".",""));
					
					if (lHelp.get_oLong()!=null)
					{
						//buttons mit artikelbereich
						String cQuery0= "SELECT JT_ARTIKEL_BEREICH.ID_ARTIKEL_BEREICH FROM JT_ARTIKELBEZ_LIEF "+
											" INNER JOIN JT_ARTIKEL_BEZ ON (JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ=JT_ARTIKELBEZ_LIEF.ID_ARTIKEL_BEZ)"+
											" INNER JOIN JT_ARTIKEL ON (JT_ARTIKEL_BEZ.ID_ARTIKEL=JT_ARTIKEL.ID_ARTIKEL)"+
											" INNER JOIN JT_ARTIKEL_GRUPPE ON (JT_ARTIKEL.ID_ARTIKEL_GRUPPE=JT_ARTIKEL_GRUPPE.ID_ARTIKEL_GRUPPE)"+
											" INNER JOIN JT_ARTIKEL_BEREICH ON (JT_ARTIKEL_GRUPPE.ID_ARTIKEL_BEREICH=JT_ARTIKEL_BEREICH.ID_ARTIKEL_BEREICH)"+
											" WHERE JT_ARTIKEL_BEREICH.ID_ARTIKEL_BEREICH="+vButtonsInRow.get(i).EXT().get_C_MERKMAL()+" AND "+
											" JT_ARTIKELBEZ_LIEF.ID_ADRESSE="+lHelp.get_oLong().longValue();

						//buttons mit artikelbereich
						String cQuery1= "SELECT JT_ARTIKEL_GRUPPE.ID_ARTIKEL_GRUPPE FROM JT_ARTIKELBEZ_LIEF "+
											" INNER JOIN JT_ARTIKEL_BEZ ON (JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ=JT_ARTIKELBEZ_LIEF.ID_ARTIKEL_BEZ)"+
											" INNER JOIN JT_ARTIKEL ON (JT_ARTIKEL_BEZ.ID_ARTIKEL=JT_ARTIKEL.ID_ARTIKEL)"+
											" INNER JOIN JT_ARTIKEL_GRUPPE ON (JT_ARTIKEL.ID_ARTIKEL_GRUPPE=JT_ARTIKEL_GRUPPE.ID_ARTIKEL_GRUPPE)"+
											" WHERE JT_ARTIKEL_GRUPPE.ID_ARTIKEL_GRUPPE="+vButtonsInRow.get(i).EXT().get_C_MERKMAL()+" AND "+
											" JT_ARTIKELBEZ_LIEF.ID_ADRESSE="+lHelp.get_oLong().longValue();
	
						
						//buttons mit artikel
						String cQuery2= "SELECT JT_ARTIKEL.ID_ARTIKEL FROM JT_ARTIKELBEZ_LIEF "+
											" INNER JOIN JT_ARTIKEL_BEZ ON (JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ=JT_ARTIKELBEZ_LIEF.ID_ARTIKEL_BEZ)"+
											" INNER JOIN JT_ARTIKEL ON (JT_ARTIKEL_BEZ.ID_ARTIKEL=JT_ARTIKEL.ID_ARTIKEL)"+
											" WHERE JT_ARTIKEL.ID_ARTIKEL="+vButtonsInRow.get(i).EXT().get_C_MERKMAL()+" AND "+
											" JT_ARTIKELBEZ_LIEF.ID_ADRESSE="+lHelp.get_oLong().longValue();
						
						
						//buttons mit artikel_bez
						String cQuery3= "SELECT JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ FROM JT_ARTIKELBEZ_LIEF "+
											" INNER JOIN JT_ARTIKEL_BEZ ON (JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ=JT_ARTIKELBEZ_LIEF.ID_ARTIKEL_BEZ)"+
											" WHERE JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ="+vButtonsInRow.get(i).EXT().get_C_MERKMAL()+" AND "+
											" JT_ARTIKELBEZ_LIEF.ID_ADRESSE="+lHelp.get_oLong().longValue();
						

						if (this.iColNum==0)
						{
							if (bibDB.EinzelAbfrage(cQuery0).equals(vButtonsInRow.get(i).EXT().get_C_MERKMAL()))
							{
								vButtonsInRow.get(i).set_bEnabled_For_Edit(true);
							}
						}
						if (this.iColNum==1)
						{
							if (bibDB.EinzelAbfrage(cQuery1).equals(vButtonsInRow.get(i).EXT().get_C_MERKMAL()))
							{
								vButtonsInRow.get(i).set_bEnabled_For_Edit(true);
							}
						}
						if (this.iColNum==2)
						{
							if (bibDB.EinzelAbfrage(cQuery2).equals(vButtonsInRow.get(i).EXT().get_C_MERKMAL()))
							{
								vButtonsInRow.get(i).set_bEnabled_For_Edit(true);
							}
						}
						if (this.iColNum==3)
						{
							if (bibDB.EinzelAbfrage(cQuery3).equals(vButtonsInRow.get(i).EXT().get_C_MERKMAL()))
							{
								vButtonsInRow.get(i).set_bEnabled_For_Edit(true);
							}
						}
					}
				}
				else
				{
					vButtonsInRow.get(i).set_bEnabled_For_Edit(true);
				}
				
				
				
				
			}
			return vButtonsInRow;
		}
		
		
	}
	
	
	
	//hilfsklasse um in der jeweiligen situation die zugehoerende adress-id zu evaluieren
	public static abstract class XX_Tell_me_the_actual_adress_id 
	{
		public abstract String get_unformated_Adress_ID_or_null() throws myException;
	}
	

}
