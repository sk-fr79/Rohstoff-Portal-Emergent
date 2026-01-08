package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.FREIEPOS;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Grid_InLIST;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.BSRG__CONST;

public class BSFP_MASK_MarkerSubQueryAgent extends XX_ComponentMAP_SubqueryAGENT {

	
	
	
	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP) throws myException 
	{
		//((MyE2_Label)oMAP.get__Comp(BSRG__CONST.COMPONENTKEY_ANZEIGE_ZUGANG_ABGANG)).setIcon(BSRG__CONST.ICON_LEER);
		MyE2_Grid_InLIST oGridInfo = (MyE2_Grid_InLIST)oMAP.get__Comp(BSRG__CONST.COMPONENTKEY_ANZEIGE_ZUGANG_ABGANG);
		oGridInfo.removeAll();
		oGridInfo.setSize(1);   //vorgabe
		
		MyE2_Label  oLabelFuerGrid = new MyE2_Label(BSRG__CONST.ICON_LEER);
		oGridInfo.add(oLabelFuerGrid);

		
	}

	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
	{ 
		
		//pruefen, ob es streckengeschaeft ist
		// mit anzeigebutton den typ des belegursprungs anzeigen

		/*
		 * den anzeiger fuer zugang/abgang einblenden
		 */
//		((MyE2_Label)oMAP.get__Comp(BSRG__CONST.COMPONENTKEY_ANZEIGE_ZUGANG_ABGANG)).setIcon(BSRG__CONST.ICON_LEER);
//		((MyE2_Label)oMAP.get__Comp(BSRG__CONST.COMPONENTKEY_ANZEIGE_ZUGANG_ABGANG)).setToolTipText("");

		MyE2_Grid_InLIST oGridInfo = (MyE2_Grid_InLIST)oMAP.get__Comp(BSRG__CONST.COMPONENTKEY_ANZEIGE_ZUGANG_ABGANG);
		oGridInfo.removeAll();
		oGridInfo.setSize(1);   //vorgabe
		
		MyE2_Label  oLabelFuerGrid = new MyE2_Label(BSRG__CONST.ICON_LEER);
		
		
		boolean bStrecke = S.NN(oUsedResultMAP.get_UnFormatedValue("STRECKENGESCHAEFT")).equals("Y");
		
			
		if 		(	oUsedResultMAP.get_UnFormatedValue("LAGER_VORZEICHEN").equals("1") && !bStrecke)
		{
			oLabelFuerGrid.setIcon(BSRG__CONST.ICON_ZUGANG);
			oLabelFuerGrid.setToolTipText(new MyE2_String("Lagerzugangsbuchung (GUTSCHRIFT)").CTrans());
		}
		else if (	oUsedResultMAP.get_UnFormatedValue("LAGER_VORZEICHEN").equals("-1") && !bStrecke)
		{
			oLabelFuerGrid.setIcon(BSRG__CONST.ICON_ABGANG);
			oLabelFuerGrid.setToolTipText(new MyE2_String("Lagerabgangsbuchung (RECHNUNG)").CTrans());
		}
		else if (	oUsedResultMAP.get_UnFormatedValue("LAGER_VORZEICHEN").equals("1") && bStrecke)
		{
			oLabelFuerGrid.setIcon(BSRG__CONST.ICON_STRECKE_GUTSCHRIFT);
			oLabelFuerGrid.setToolTipText(new MyE2_String("Streckengeschäft (GUTSCHRIFT)").CTrans());
		}
		else if (	oUsedResultMAP.get_UnFormatedValue("LAGER_VORZEICHEN").equals("-1") && bStrecke)
		{
			oLabelFuerGrid.setIcon(BSRG__CONST.ICON_STRECKE_RECHNUNG);
			oLabelFuerGrid.setToolTipText(new MyE2_String("Streckengeschäft (RECHNUNG)").CTrans());
		}
		oGridInfo.add(oLabelFuerGrid);
		
	}

}
