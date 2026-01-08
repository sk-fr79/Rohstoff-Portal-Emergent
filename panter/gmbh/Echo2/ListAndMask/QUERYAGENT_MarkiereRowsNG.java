package panter.gmbh.Echo2.ListAndMask;

import java.util.Iterator;
import java.util.Vector;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2IF__Indirect;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;

public abstract class QUERYAGENT_MarkiereRowsNG extends 	XX_ComponentMAP_SubqueryAGENT {

	
	public abstract boolean check_if_rowIsMarked(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException; 
	public abstract Color   get_colorUnmarked() throws myException;
	public abstract Color   get_colorMarked() throws myException;
	
	/**
	 * 
	 * @param FieldNameACTIVE
	 * @param colorInactive
	 * @param colorActive
	 */
	public QUERYAGENT_MarkiereRowsNG() {
		super();
	}


	
	@Override
	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP) throws myException  {

	}

	@Override
	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP)	throws myException {
		
		boolean bAktiv = this.check_if_rowIsMarked(oMAP, oUsedResultMAP);
		
		Iterator<String>  oIterator = oMAP.get_hmRealComponents().keySet().iterator();
		
		while (oIterator.hasNext())
		{
			MyE2IF__Component oComp = oMAP.get_hmRealComponents().get(oIterator.next());
			
			this.pruefeKomponenteNG(oMAP, oComp, bAktiv);
		}
	}
	

	
	private void pruefeKomponenteNG(E2_ComponentMAP oMAP, MyE2IF__Component oComp, boolean bActive) throws myException
	{
		Color  oColor = bActive?this.get_colorMarked():this.get_colorUnmarked();
		
		Vector<Component> vComponentsToCheck = new Vector<Component>(); 
		
		if (E2_ComponentMAP.is_visible_in_NavigationList(oComp)) {
			//wenn es eine indirekte komponente ist, dann muss diese von hand bearbeitet werden, da erst beim einfuegen in die navigationlist
			//die direkte in die indirekte umgewandelt wird, deshalb muss der ersatzbutton manuell zugefuegt werden
			
			if (oComp instanceof MyE2IF__Indirect) {
				vComponentsToCheck.add(((MyE2IF__Indirect) oComp).get_oErsatzButton());
			}
			
			vComponentsToCheck.addAll(new E2_RecursiveSearch_Component((Component)oComp,null,null).get_vAllComponents());
		
			for (Component comp: vComponentsToCheck) {
				if (comp instanceof MyE2_Label) {
					((MyE2_Label)comp).setForeground(oColor);
				} else if (comp instanceof MyE2_CheckBox) {
					((MyE2_CheckBox)comp).setForeground(oColor);
				} else if (comp instanceof MyE2_SelectField) {
					((MyE2_SelectField)comp).setForeground(oColor);
				} else if (comp instanceof MyE2_Button) {
					((MyE2_Button)comp).setForeground(oColor);
				}
			}
		}
	}

	
	
	
}
