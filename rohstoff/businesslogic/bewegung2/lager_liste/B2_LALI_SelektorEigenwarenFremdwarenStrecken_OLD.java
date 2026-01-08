package rohstoff.businesslogic.bewegung2.lager_liste;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import org.apache.commons.collections.bag.HashBag;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.Replacer.bibReplacer;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.utils.bibROHSTOFF;


public class B2_LALI_SelektorEigenwarenFremdwarenStrecken_OLD extends E2_ListSelektorMultiselektionStatusFeld_STD
{
	/**
	 * der zu selektierende Lagertyp
	 * @author manfred
	 *
	 */
	public enum ENUM_SELEKTOR_Lagertyp  {EIGEN,FREMD,STRECKE};
	private ArrayList<Integer> vIDChecboxesInComponent = new ArrayList<Integer>();
	private ArrayList<Integer> vIDChecboxesExternal = new ArrayList<Integer>();
	
	
	public B2_LALI_SelektorEigenwarenFremdwarenStrecken_OLD(E2_NavigationList oNavigationList, String sTablealiasLieferadresse, String sTablealiasStation, boolean bHorizontal, String Heading) throws myException
	{
		super((bHorizontal?3:1),((Heading == null || Heading.length() == 0) ? false : true),new MyE2_String(Heading),new Extent(40));
		
		// die Selektion wird nicht auf die gesetzte Checkbox gemacht, sondern auf die NICHT-gesetzte,
		// damit man die AND-Verknüpfung nehmen kann anstatt der OR (schneller)
		
		String sIDAdresseStrecke =bibSES.get_ID_ADRESSE_LAGER_STRECKE();
		
		// alle Sonderlager	
		String s_Eigen 		= "(" + sTablealiasLieferadresse + ".ID_ADRESSE_FREMDWARE IS NULL) AND " + "(" + sTablealiasStation 		 + ".ID_ADRESSE !=  " + sIDAdresseStrecke + ") ";
		String s_Fremd 		= "(" + sTablealiasLieferadresse + ".ID_ADRESSE_FREMDWARE IS NOT NULL) AND " + "(" + sTablealiasStation 		 + ".ID_ADRESSE != " + sIDAdresseStrecke + ") ";
		String s_Strecke 	= "(" + sTablealiasStation 		 + ".ID_ADRESSE = " + sIDAdresseStrecke + " ) ";
		
		Integer iCount = 0;
		this.ADD_STATUS_TO_Selector(true,	s_Eigen,		new MyE2_String("Eigenwarenlager"),	new MyE2_String("Lager berücksichtigen, die in den Stammdaten als Eigenwarenlager definiert sind."));
		vIDChecboxesInComponent.add(iCount++);
		this.ADD_STATUS_TO_Selector(true,	s_Fremd,  		new MyE2_String("Fremdwarenlager"), 	new MyE2_String("Lager berücksichtigen, die in den Stammdaten einen fremden Besitzer definiert haben."));
		vIDChecboxesInComponent.add(iCount++);
		this.ADD_STATUS_TO_Selector(true,	s_Strecke,		new MyE2_String("inclusive Streckenlager"), 	new MyE2_String("Streckengeschäfte berücksichtigen."));
		vIDChecboxesInComponent.add(iCount++);
	}


	
	/**
	 * an-/ ausschalten einer Checkbox 
	 * @author manfred
	 * @date   13.02.2012
	 * @param index
	 * @param bTrue
	 */
	public void selectCheckbox(int index, boolean bSelected){
		int count = get_vCheckBoxTypen().size();
		if (index > count -1 || index < 0) return;
		
		get_vCheckBoxTypen().get(index).setSelected(bSelected);
	}
	
	
	/**
	 * Setzt alle Checkboxen mit dem Wert bSelected
	 * 
	 * @author manfred
	 * @date   08.05.2015
	 *
	 * @param bSelected
	 */
	public void selectAllCheckboxes(boolean bSelected){
		int count = get_vCheckBoxTypen().size();
		for(int i=0; i<count; i++){
			selectCheckbox(i, bSelected);
		}
	}
	
	/**
	 * Enablen-Disablen der Checkboxen
	 * 
	 * @author manfred
	 * @date   08.05.2015
	 *
	 * @param bEnable
	 */
	public void set_bEnabled(boolean bEnable){
		int count = get_vCheckBoxTypen().size();
		for(int i=0; i<count; i++){
			try {
				get_vCheckBoxTypen().get(i).set_bEnabled_For_Edit(bEnable);
			} catch (myException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
	/**
	 * löst eine Checkbox aus der Liste der in der Implizit dargestellten Checkboxen raus 
	 * und setzt sie in eine Liste von extern anzeigbaren Checkboxen.
	 * Die Checkbox muss explizit mit der Methode getCheckbox(int) aus dem aufrufenden Code
	 * referenziert und angezeigt werden.
	 * @param index
	 */
	public void set_CheckboxIsExternal(int index){
		for (Integer idx: vIDChecboxesInComponent){
			if (idx.intValue()==index){
				vIDChecboxesInComponent.remove(idx.intValue());
				vIDChecboxesExternal.add(idx);
				break;
			}
		}
	}
	
	
	/**
	 * gibt die Referenz auf die Checkbox zurück 
	 * @author manfred
	 * @date   17.07.2013
	 * @param index
	 * @return
	 */
	public MyE2_CheckBox getCheckbox(int index){
		int count = get_vCheckBoxTypen().size();
		return ((count == 0 || index > count -1 ) ? null : get_vCheckBoxTypen().get(index));
	}
	
	
	
	@Override
	public String get_WhereBlock() throws myException
	{
		/*		
		 * Der Block soll eine Bedinung wie folgt ergeben!
		 * 				
				) )
		 */		
		String cWHERE = " 1 = 2 ";
		boolean bHasWhereblock = false;
		String sAND = " OR ";
		for (MyE2_CheckBox oCB: get_vCheckBoxTypen())
		{
			if ( oCB.isSelected())
			{
				
				if (bHasWhereblock == false){
					bHasWhereblock = true;
				} else {
					sAND = " OR ";
				}
				
				cWHERE += sAND + oCB.EXT().get_C_MERKMAL();
			}
		}
		
		if (bHasWhereblock)
		{
			cWHERE = "(" + cWHERE + " )";
		}
		
		return cWHERE;
	}

	
	@Override
	public Component get_oComponentForSelection() {
		MyE2_Grid oGridAussen = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		//MyE2_Grid oGridAussen = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_DDARK_BORDER_NO_INSETS());
		
		MyE2_Grid oGridSelektors = new MyE2_Grid(this.get_iCountColsInGrid(), MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		if (this.get_oExtBeschriftung()==null)
		{
			oGridSelektors.setColumnWidth(0,this.get_oExtBeschriftung());
		}
		
		Insets  oIN = new Insets(0, 1, 3, 1);
		Insets  oIN2 = new Insets(0, 0, 3, 1);
		
		oGridAussen.add(new MyE2_Label(this.get_cBeschriftung()!=null?this.get_cBeschriftung():new MyE2_String("")), MyE2_Grid.LAYOUT_LEFT_TOP(oIN));
		oGridAussen.add(oGridSelektors, MyE2_Grid.LAYOUT_LEFT_TOP(oIN));
		
		if (this.get_oExtBeschriftung()!=null)
		{
			oGridAussen.setColumnWidth(0, this.get_oExtBeschriftung());
		}
		
		for (int i=0;i<this.get_vCheckBoxTypen().size();i++)
		{
			// checkbox soll nur dann im internen Grid angezeigt werden, wenn sie auch in der Liste der "internen" checkboxen steht.
			if (vIDChecboxesInComponent.contains(new Integer(i))){
				oGridSelektors.add(this.get_vCheckBoxTypen().get(i), MyE2_Grid.LAYOUT_LEFT_TOP(oIN2));
			}
		}

		if (intSpaltenBreitenSelectors!=null)
		{
			for (int i=0;i<this.intSpaltenBreitenSelectors.length;i++)
			{
				oGridSelektors.setColumnWidth(i,new Extent(this.intSpaltenBreitenSelectors[i]));
			}
		}
		
		if (this.get_bZeigeBeschriftungAn())
		{
			return oGridAussen;
		}
		else
		{
			return oGridSelektors;
		}
	}
	
	
	
	/**
	 * Gibt den Selektionsstatus der Checkbox zurück
	 * @param lagertyp
	 * @return
	 *
	 * Autor:	 manfred
	 * Erstellt: 04.06.2014
	 *
	 */
	public boolean getStatusOf(ENUM_SELEKTOR_Lagertyp lagertyp){
		boolean bRet = false;
		int idx = -1;
		MyE2_CheckBox cb = null;
		switch (lagertyp) {
			case EIGEN:
				idx = 0;
				break;
			case FREMD:
				idx = 1;
				break;
			case STRECKE:
				idx = 2;
				break;
		default:
			idx = -1;
			break;
			
		}
		if (idx >= 0){
			cb = getCheckbox(idx);
			bRet = cb.isSelected();
		}
		
		return bRet;	
	}
	
	
	/**
	 * Gibt den Selektionsstatus der Checkbox zurück
	 * @param lagertyp
	 * @return
	 *
	 * Autor:	 manfred
	 * Erstellt: 04.06.2014
	 *
	 */
	public MyE2_CheckBox getCheckboxOf(ENUM_SELEKTOR_Lagertyp lagertyp){
		int idx = -1;
		MyE2_CheckBox cb = null;
		switch (lagertyp) {
			case EIGEN:
				idx = 0;
				break;
			case FREMD:
				idx = 1;
				break;
			case STRECKE:
				idx = 2;
				break;
		default:
			idx = -1;
			break;
			
		}
		if (idx >= 0){
			cb = getCheckbox(idx);
		}
		return cb;	
	}
	
}
