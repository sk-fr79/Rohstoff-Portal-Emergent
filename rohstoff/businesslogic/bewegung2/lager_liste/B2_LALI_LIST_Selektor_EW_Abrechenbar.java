package rohstoff.businesslogic.bewegung2.lager_liste;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;

public class B2_LALI_LIST_Selektor_EW_Abrechenbar extends E2_ListSelektorMultiselektionStatusFeld_STD
{
	
	/**
	 * Selektor für die einzelnen typen
	 * @author manfred
	 *
	 */
	public enum ENUM_SELEKTOR_EWFW { EIGENWAREN, FREMDWAREN	}
	private Vector<ENUM_SELEKTOR_EWFW> vCheckboxen = new Vector<ENUM_SELEKTOR_EWFW>(); 
	
	
	
	public B2_LALI_LIST_Selektor_EW_Abrechenbar(E2_NavigationList oNavigationList) throws myException
	{
		super(1,true,new MyE2_String("Zeige: "),new Extent(40));
		
		String sColDef = oNavigationList.get_oComponentMAP__REF().get_hmRealDBComponents().get("EW_FW").EXT_DB().get_oSQLField().get_cFieldAusdruck();
//		String sColDef = "ADR_GEGEN.ID_ADRESSE_GEGEN";
		// die Selektion wird nicht auf die gesetzte Checkbox gemacht, sondern auf die NICHT-gesetzte,
		// damit man die AND-Verknüpfung nehmen kann anstatt der OR (schneller)

		// alle Sonderlager	
		String s_EW = "(" + sColDef + " = 'Y' ) ";
		String s_FW = "(" + sColDef + " = 'N' ) ";
		
		this.addCheckbox(ENUM_SELEKTOR_EWFW.EIGENWAREN,true,	s_EW,	new MyE2_String("Ware im eigenen Besitz (Eigenwaren)"), new MyE2_String("Einträge mit Eigenwaren, d.h. der Eigentümer der Ware ist der Mandant."));
		this.addCheckbox(ENUM_SELEKTOR_EWFW.FREMDWAREN,true,	s_FW,	new MyE2_String("Ware im fremden Besitz (Fremdwaren)"), new MyE2_String("Einträge mit Fremdwaren, d.h. der Eigentümer der Ware ist der Kunde."));
	}

	/**
	 * Anlegen der Selektorenelemente und dem parallelen Vektor der Typen, um gezielt die Checkboxen zu de- /selektieren zu können
	 * @param enumTyp
	 * @param bPreselected
	 * @param cBedingungsBlockFuer_OR_Statement
	 * @param cBeschriftung
	 * @param cToolTips
	 */
	private void addCheckbox(ENUM_SELEKTOR_EWFW enumTyp, boolean bPreselected, String cBedingungsBlockFuer_OR_Statement, MyString cBeschriftung, MyString cToolTips){
		vCheckboxen.add(enumTyp);
		this.ADD_STATUS_TO_Selector(bPreselected,cBedingungsBlockFuer_OR_Statement,cBeschriftung, cToolTips);
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
	 * de-/selektiert die angegebene Checkbox 
	 * @param enumCheckbox
	 * @param bSelected
	 */
	public void selectCheckbox(ENUM_SELEKTOR_EWFW enumCheckbox, boolean bSelected ){
		int count = vCheckboxen.size();
		for (int i= 0; i<count; i++){
			if (vCheckboxen.get(i).equals(enumCheckbox)){
				selectCheckbox(i, bSelected);
				break;
			}
		}
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

	
	
}
