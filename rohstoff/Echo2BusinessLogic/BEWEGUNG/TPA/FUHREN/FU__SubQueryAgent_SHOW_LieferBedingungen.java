package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.components.MyE2_Grid_InLIST;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ALT_LIEFBED.FU_AL_LieferbedZeigeAktuellStatistik;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ALT_LIEFBED.FU_AL_SucheLieferBed;

public class FU__SubQueryAgent_SHOW_LieferBedingungen extends	XX_ComponentMAP_SubqueryAGENT 
{
	private FU_LIST_ModulContainer oModulcontainerLIST = null;
	
	
	public FU__SubQueryAgent_SHOW_LieferBedingungen(FU_LIST_ModulContainer modulcontainerLIST) {
		super();
		this.oModulcontainerLIST = modulcontainerLIST;
	}

	@Override
	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP) throws myException 
	{

	}

	@Override
	public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException 
	{
		
		MyE2_Grid_InLIST oGrid_LiefBedEK = (MyE2_Grid_InLIST)oMAP.get_hmRealComponents().get(FU___CONST.FU_LIST_FIELDNAME_FU_LIEFERBEDINGUNGEN_EK);
		MyE2_Grid_InLIST oGrid_LiefBedVK = (MyE2_Grid_InLIST)oMAP.get_hmRealComponents().get(FU___CONST.FU_LIST_FIELDNAME_FU_LIEFERBEDINGUNGEN_VK);
		
		//2014-07-04: zwei infofelder mit passnummern fuer bessere LIEFERBED-Diagnose
		MyE2_Grid_InLIST oGrid_PassnummerEK = (MyE2_Grid_InLIST)oMAP.get_hmRealComponents().get(FU___CONST.FU_LIST_FIELDNAME_FU_PASSNUMMER_EK);
		MyE2_Grid_InLIST oGrid_PassnummerVK = (MyE2_Grid_InLIST)oMAP.get_hmRealComponents().get(FU___CONST.FU_LIST_FIELDNAME_FU_PASSNUMMER_VK);
		
		
		oGrid_LiefBedEK.removeAll();
		oGrid_LiefBedVK.removeAll();
		oGrid_PassnummerEK.removeAll();
		oGrid_PassnummerVK.removeAll();

		if (this.oModulcontainerLIST.get_oNavList().get_bIsVisible(FU_LIST_ModulContainer.NAME_OF_LIEFERBEDINGUNGSBLOCK)) {
			
			FU_AL_SucheLieferBed oSucheEK = new FU_AL_SucheLieferBed( 	oMAP,	
																		_DB.VPOS_TPA_FUHRE$ID_ADRESSE_START, 
																		_DB.VPOS_TPA_FUHRE$LIEFERBED_ALTERNATIV_EK, 
																		_DB.VPOS_TPA_FUHRE$ID_VPOS_KON_EK, 
																		_DB.VPOS_TPA_FUHRE$ID_VPOS_STD_EK, 
																		_DB.ADRESSE$ID_LIEFERBEDINGUNGEN,
																		true);
			FU_AL_SucheLieferBed oSucheVK = new FU_AL_SucheLieferBed( 	oMAP,	
																		_DB.VPOS_TPA_FUHRE$ID_ADRESSE_ZIEL, 
																		_DB.VPOS_TPA_FUHRE$LIEFERBED_ALTERNATIV_VK, 
																		_DB.VPOS_TPA_FUHRE$ID_VPOS_KON_VK, 
																		_DB.VPOS_TPA_FUHRE$ID_VPOS_STD_VK, 
																		_DB.ADRESSE$ID_LIEFERBEDINGUNGEN_VK,
																		true);
		
			FU_AL_LieferbedZeigeAktuellStatistik oAktuelleStatEK = new FU_AL_LieferbedZeigeAktuellStatistik(oUsedResultMAP.get_cUNFormatedROW_ID(), null, "EK") ;
			FU_AL_LieferbedZeigeAktuellStatistik oAktuelleStatVK = new FU_AL_LieferbedZeigeAktuellStatistik(oUsedResultMAP.get_cUNFormatedROW_ID(), null, "VK") ;
			
			FU_LIST_BT_EditLieferbedingung  oEditLieferbedEK = new FU_LIST_BT_EditLieferbedingung(_DB.VPOS_TPA_FUHRE$LIEFERBED_ALTERNATIV_EK, oSucheEK, oAktuelleStatEK, oMAP);
			FU_LIST_BT_EditLieferbedingung  oEditLieferbedVK = new FU_LIST_BT_EditLieferbedingung(_DB.VPOS_TPA_FUHRE$LIEFERBED_ALTERNATIV_VK, oSucheVK, oAktuelleStatVK, oMAP);
			
			oGrid_LiefBedEK.add(oEditLieferbedEK);
			oGrid_LiefBedVK.add(oEditLieferbedVK);
			
			//2014-07-04: zwei infofelder mit passnummern fuer bessere LIEFERBED-Diagnose
			String cID_ADRESSE_START = oUsedResultMAP.get_UnFormatedValue(_DB.VPOS_TPA_FUHRE$ID_ADRESSE_START, "0");
			String cID_ADRESSE_ZIEL = oUsedResultMAP.get_UnFormatedValue(_DB.VPOS_TPA_FUHRE$ID_ADRESSE_ZIEL, "0");
			if (!cID_ADRESSE_START.equals("0")) {
				RECORD_ADRESSE recAdresseEK = new RECORD_ADRESSE(cID_ADRESSE_START);
				oGrid_PassnummerEK.add(new MyE2_Label(recAdresseEK.get_AUSWEIS_NUMMER_cUF_NN("<-->")));
			} else {
				oGrid_PassnummerEK.add(new MyE2_Label("<-->"));
			}

			if (!cID_ADRESSE_ZIEL.equals("0")) {
				RECORD_ADRESSE recAdresseVK = new RECORD_ADRESSE(cID_ADRESSE_ZIEL);
				oGrid_PassnummerVK.add(new MyE2_Label(recAdresseVK.get_AUSWEIS_NUMMER_cUF_NN("<-->")));
			} else {
				oGrid_PassnummerVK.add(new MyE2_Label("<-->"));
			}
		}
	}
}
