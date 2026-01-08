package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ADRESSE_UST_ID;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE_UST_ID;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;


public class FS_LIST_ComponentMAP_SubqueryAgent extends XX_ComponentMAP_SubqueryAGENT
{
	
	/*
	 * subquery-agent, füllt die infofelder wieviele mitarbeiter/Infos usw eine adresse hat
	 */
	public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP) throws myException
	{
		HashMap<String, MyE2IF__Component> hmRealComponents = oMAP.get_REAL_ComponentHashMap();

		((MyE2_Label)hmRealComponents.get(FS_CONST.LIST_COL_INFO_INFOS				)).setText("");
		((MyE2_Label)hmRealComponents.get(FS_CONST.LIST_COL_INFO_MITARBEITER		)).setText("");
		((MyE2_Label)hmRealComponents.get(FS_CONST.LIST_COL_INFO_FILES				)).setText("");
		((MyE2_Label)hmRealComponents.get(FS_CONST.LIST_COL_INFO_LIEFERADRESSEN	)).setText("");
		
	}

	public void fillComponents(E2_ComponentMAP oMAP,SQLResultMAP oUsedResultMAP) throws myException
	{
		HashMap<String, MyE2IF__Component> hmRealComponents = oMAP.get_REAL_ComponentHashMap();
		
		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		String cID_Adresse = 	oMAP.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
		String cCountInfos = 		oDB.EinzelAbfrage("SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_ADRESSE_INFO WHERE ID_ADRESSE="+cID_Adresse);
		String cCountMitarbeiter = 	oDB.EinzelAbfrage("SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_MITARBEITER WHERE ID_ADRESSE_BASIS="+cID_Adresse);
		String cCountFiles =		oDB.EinzelAbfrage("SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_ARCHIVMEDIEN WHERE TABLENAME='ADRESSE' AND ID_TABLE="+cID_Adresse);
		String cCountAdresses = 	oDB.EinzelAbfrage("SELECT COUNT(*) FROM "+bibE2.cTO()+".JT_LIEFERADRESSE WHERE ID_ADRESSE_BASIS="+cID_Adresse);
	
		// schauen, ob kunde oder lieferant und haken setzen
		//  
		String cCountKUNDE = 	oDB.EinzelAbfrage("SELECT COUNT(JT_ADRESSKLASSE.ID_ADRESSE) FROM " +
										bibE2.cTO()+".JT_ADRESSKLASSE,"+
										bibE2.cTO()+".JT_ADRESSKLASSE_DEF " +
												" WHERE" +
												" JT_ADRESSKLASSE.ID_ADRESSKLASSE_DEF= JT_ADRESSKLASSE_DEF.ID_ADRESSKLASSE_DEF AND " +
												"   NVL(JT_ADRESSKLASSE_DEF.IST_KUNDE,'N')='Y' AND ID_ADRESSE="+cID_Adresse);

		String cCountLieferant = 	oDB.EinzelAbfrage("SELECT COUNT(JT_ADRESSKLASSE.ID_ADRESSE) FROM " +
											bibE2.cTO()+".JT_ADRESSKLASSE,"+
											bibE2.cTO()+".JT_ADRESSKLASSE_DEF " +
													" WHERE" +
													" JT_ADRESSKLASSE.ID_ADRESSKLASSE_DEF= JT_ADRESSKLASSE_DEF.ID_ADRESSKLASSE_DEF AND " +
													"   NVL(JT_ADRESSKLASSE_DEF.IST_LIEFERANT,'N')='Y' AND ID_ADRESSE="+cID_Adresse);

		
		bibALL.destroy_myDBToolBox(oDB);
		
		((MyE2_Label)hmRealComponents.get(FS_CONST.LIST_COL_INFO_INFOS				)).setText(cCountInfos);
		((MyE2_Label)hmRealComponents.get(FS_CONST.LIST_COL_INFO_MITARBEITER		)).setText(cCountMitarbeiter);
		((MyE2_Label)hmRealComponents.get(FS_CONST.LIST_COL_INFO_FILES				)).setText(cCountFiles);
		((MyE2_Label)hmRealComponents.get(FS_CONST.LIST_COL_INFO_LIEFERADRESSEN	)).setText(cCountAdresses);
	
		
		if (! cCountKUNDE.equals("0"))
			((MyE2_Label)hmRealComponents.get(FS_CONST.LIST_COL_IST_ABNEHMER)).setIcon(E2_ResourceIcon.get_RI("ok.png"));
			
		if (! cCountLieferant.equals("0"))
			((MyE2_Label)hmRealComponents.get(FS_CONST.LIST_COL_IST_LIEFERANT)).setIcon(E2_ResourceIcon.get_RI("ok.png"));

		

		MyE2_Grid oGrid = (MyE2_Grid) oMAP.get__Comp_From_RealComponents(FS_CONST.LIST_COL_USTID_LIST_ZUSATZ);
		
		if (oGrid.EXT().get_bIsVisibleInList())
		{
			RECORD_ADRESSE recAdresse = new RECORD_ADRESSE(oUsedResultMAP.get_cUNFormatedROW_ID());
			
			RECLIST_ADRESSE_UST_ID  reclistUST_IDs = recAdresse.get_DOWN_RECORD_LIST_ADRESSE_UST_ID_id_adresse();
			
			Vector<String> vSortFields = bibALL.get_Vector(RECORD_ADRESSE_UST_ID.FIELD__UMSATZSTEUERLKZ,RECORD_ADRESSE_UST_ID.FIELD__UMSATZSTEUERID);
			
			Vector<RECORD_ADRESSE_UST_ID>  vSortedUST = reclistUST_IDs.GET_SORTED_VECTOR(vSortFields, true);
			
			oGrid.removeAll();
			for (int i=0;i<vSortedUST.size();i++)
			{
				oGrid.add(new MyE2_Label(vSortedUST.get(i).get___KETTE(vSortFields)),E2_INSETS.I_1_1_1_1);
			}
		}
		
		
		
		
		
	}

}
