package rohstoff.businesslogic.bewegung2.lager_saldo;

import java.util.ArrayList;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_ListSelektorMultiselektionStatusFeld_STD;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.businesslogic.bewegung2.global.ENUM_SELEKTOR_Lagertyp;



public class B2_LAG_SALDO_LIST_Selector_EW_FW_ST extends E2_ListSelektorMultiselektionStatusFeld_STD
{
	private ArrayList<Integer> vIDChecboxesInComponent 	= new ArrayList<Integer>();

	private ArrayList<Integer> vIDChecboxesExternal 	= new ArrayList<Integer>();

	public B2_LAG_SALDO_LIST_Selector_EW_FW_ST(E2_NavigationList oNavigationList) throws myException
	{
		super(3,false, S.ms(""),new Extent(40));


		//		String sColDef = "ADR_GEGEN.ID_ADRESSE_GEGEN";

		String sTablealiasLieferadresse = "LA"; //_TAB.lieferadresse.fullTableName();

		String sTablealiasBasis 		= "BASE";//_TAB.bg_station.fullTableName();

		// die Selektion wird nicht auf die gesetzte Checkbox gemacht, sondern auf die NICHT-gesetzte,
		// damit man die AND-Verknüpfung nehmen kann anstatt der OR (schneller)

		String sIDAdresseStrecke =bibSES.get_ID_ADRESSE_LAGER_STRECKE();

		// alle Sonderlager	
		String s_Eigen 		= "(" + sTablealiasLieferadresse+".ID_ADRESSE_FREMDWARE IS NULL) AND " + "(" + sTablealiasBasis 		 + ".SUBADRESSE !=  " + sIDAdresseStrecke + ") ";
		String s_Fremd 		= "(" + sTablealiasLieferadresse+".ID_ADRESSE_FREMDWARE IS NOT NULL) AND " + "(" + sTablealiasBasis 		 + ".SUBADRESSE != " + sIDAdresseStrecke + ") ";		
		String s_Strecke 	= "(" + sTablealiasBasis 		 + ".SUBADRESSE = " + sIDAdresseStrecke + " ) ";

		Integer iCount = 0;
		this.ADD_STATUS_TO_Selector(true,	s_Eigen,	S.ms("Eigenwarenlager"),			S.ms("Lager berücksichtigen, die in den Stammdaten als Eigenwarenlager definiert sind."));
		vIDChecboxesInComponent.add(iCount++);
		this.ADD_STATUS_TO_Selector(true,	s_Fremd,  	S.ms("Fremdwarenlager"), 			S.ms("Lager berücksichtigen, die in den Stammdaten einen fremden Besitzer definiert haben."));
		vIDChecboxesInComponent.add(iCount++);
		this.ADD_STATUS_TO_Selector(true,	s_Strecke,	S.ms("inclusive Streckenlager"), 	S.ms("Streckengeschäfte berücksichtigen."));
		vIDChecboxesInComponent.add(iCount++);
	}



	/**
	 * an-/ ausschalten einer Checkbox 
	 * @author sebastien
	 * @date 28.08.2019
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
	 * @author sebastien
	 * @date 28.08.2019
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
	 * @author sebastien
	 * @date 28.08.2019
	 *
	 * @param bEnable
	 */
	public void set_bEnabled(boolean bEnable){
		int count = get_vCheckBoxTypen().size();
		for(int i=0; i<count; i++){
			try {
				get_vCheckBoxTypen().get(i).set_bEnabled_For_Edit(bEnable);
			} catch (myException e) {
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
		E2_Grid oGridAussen = new E2_Grid()._s(2)._bo_no();

		E2_Grid oGridSelektors = new E2_Grid()._s(this.get_iCountColsInGrid())._bo_no();

		if (this.get_oExtBeschriftung()==null)
		{
			oGridSelektors.setColumnWidth(0,this.get_oExtBeschriftung());
		}

		RB_gld  gld1 = new RB_gld()._ins(0, 1, 3, 1)._left_top();
		RB_gld  gld2 = new RB_gld()._ins(0, 0, 3, 1)._left_top();

		MyE2_String sBeschriftung = this.get_cBeschriftung()!=null?this.get_cBeschriftung():new MyE2_String("");
		
		oGridAussen._a(new RB_lab()._t(sBeschriftung), 	gld1);
		oGridAussen._a(oGridSelektors, 					gld1);

		if (this.get_oExtBeschriftung()!=null)
		{
			oGridAussen.setColumnWidth(0, this.get_oExtBeschriftung());
		}

		for (int i=0;i<this.get_vCheckBoxTypen().size();i++)
		{
			// checkbox soll nur dann im internen Grid angezeigt werden, wenn sie auch in der Liste der "internen" checkboxen steht.
			if (vIDChecboxesInComponent.contains(new Integer(i))){
				oGridSelektors._a(this.get_vCheckBoxTypen().get(i), gld2);
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

	public MyE2_CheckBox getCheckbox(int index){
		int count = get_vCheckBoxTypen().size();
		return ((count == 0 || index > count -1 ) ? null : get_vCheckBoxTypen().get(index));
	}
}
