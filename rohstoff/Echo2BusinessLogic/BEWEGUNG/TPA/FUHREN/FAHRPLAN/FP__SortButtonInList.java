package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

/*
 * button in der listen-componentmap, wird zur sortierung benutzt
 */
public class FP__SortButtonInList extends MyE2_ButtonInLIST
{
	
	private String 				cID_VPOS_TPA_FUHRE_UF = null;
	
	
	public FP__SortButtonInList(String ID_VPOS_TPA_FUHRE_UF, String cSortNummer)
	{
		super(E2_ResourceIcon.get_RI("up_low.png"));
		this.setToolTipText(new MyE2_String("Sortierung im Fahrplan verändern ",true,"("+cSortNummer+")",false).CTrans());
		
		this.cID_VPOS_TPA_FUHRE_UF = ID_VPOS_TPA_FUHRE_UF;
		this.add_oActionAgent(new actionSort());
	}


	private class actionSort extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			//zuerst die Fuhre beschaffen
			FP__SortButtonInList oThis = FP__SortButtonInList.this;
			
			RECORD_VPOS_TPA_FUHRE recFuhre = new RECORD_VPOS_TPA_FUHRE(oThis.cID_VPOS_TPA_FUHRE_UF);

			RECLIST_VPOS_TPA_FUHRE  recFuhrenFahrplan = new RECLIST_VPOS_TPA_FUHRE("SELECT * FROM "+
														bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE " +
															" ID_MASCHINEN_LKW_FP="+recFuhre.get_ID_MASCHINEN_LKW_FP_cUF_NN("")+
														" AND TO_CHAR(DAT_FAHRPLAN_FP,'dd.MM.yyyy')="+bibALL.MakeSql(recFuhre.get_DAT_FAHRPLAN_FP_cF())+
														" AND NVL(JT_VPOS_TPA_FUHRE.FUHRE_AUS_FAHRPLAN,'N')='Y' "+
														" AND NVL(JT_VPOS_TPA_FUHRE.DELETED,'N')='N'"+
														" AND NVL(JT_VPOS_TPA_FUHRE.IST_STORNIERT,'N')='N' " +
														" ORDER BY NVL(JT_VPOS_TPA_FUHRE.SORTIERUNG_FP,0)");
			
			
			String[][] oSortArray = new String[recFuhrenFahrplan.get_vKeyValues().size()][3];
			
			//jetzt die position des geklickten suchen
			for (int i=0;i<recFuhrenFahrplan.get_vKeyValues().size();i++)
			{
				
				oSortArray[i][0]=recFuhrenFahrplan.get_vKeyValues().get(i);   	// id
				oSortArray[i][1]=""+(i*10+10);                                   	// sort-value
				oSortArray[i][2]="N";                               		 	// merker, ob es der sortierte satz ist
				if (recFuhrenFahrplan.get_vKeyValues().get(i).equals(recFuhre.get_ID_VPOS_TPA_FUHRE_cUF()))
				{
					oSortArray[i][2]="Y";  
				}
			}

			//jetzt die markierte position mit der vorigen tauschen
			for (int i=0;i<oSortArray.length;i++)
			{
				if (oSortArray[i][2].equals("Y"))
				{
					if (i>0)
					{
						//sortwerte mit dem vorgaenger tauschen, der besagte satz rutscht eines nach oben
						String cZwischenWert = oSortArray[i-1][1];
						oSortArray[i-1][1] = oSortArray[i][1];
						oSortArray[i][1]=cZwischenWert;
					}
				}
			}
			
			Vector<String> vSQL = new Vector<String>();
			for (int i=0;i<oSortArray.length;i++)
			{
				vSQL.add("UPDATE "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE SET SORTIERUNG_FP="+oSortArray[i][1]+" WHERE ID_VPOS_TPA_FUHRE="+oSortArray[i][0]);
			}
			
			bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL, true));
			if (bibMSG.get_bIsOK())
			{
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Sortierung war erfolgreich !")));
			}
		}
	}
	
	
}
