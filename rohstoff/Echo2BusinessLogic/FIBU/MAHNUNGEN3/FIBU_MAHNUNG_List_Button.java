package rohstoff.Echo2BusinessLogic.FIBU.MAHNUNGEN3;

import java.util.HashMap;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid_InLIST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MAHNUNG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MAHNUNG_POS;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MAHNUNG;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIBU.MAHNUNGEN2.FIBU_Container4Mahnung_NG;


/*
 * gibt grid zurueck mit 1 2 oder 3 mahnbuttons
 */
public class FIBU_MAHNUNG_List_Button extends MyE2_Grid_InLIST 
{
	private RECORD_FIBU recFIBU = null;

	private E2_NavigationList   oNaviList = null;

	public FIBU_MAHNUNG_List_Button(RECORD_FIBU rec_FIBU, E2_NavigationList NaviList) throws myException 
	{
		super(3,MyE2_Grid_InLIST.STYLE_GRID_NO_BORDER_NO_INSETS());

		this.recFIBU = rec_FIBU;

		this.oNaviList = NaviList;
		
		RECLIST_MAHNUNG_POS  				recListFibuMahnungenWithPos = this.recFIBU.get_DOWN_RECORD_LIST_MAHNUNG_POS_id_fibu();

		RECLIST_MAHNUNG       				recListMahnungen 			= new RECLIST_MAHNUNG();

		HashMap<String, Boolean>			oldNewMahnungMap			= new HashMap<String, Boolean>();
		
		for (int i=0;i<recListFibuMahnungenWithPos.get_vKeyValues().size();i++){
			recListMahnungen.ADD(recListFibuMahnungenWithPos.get(i).get_UP_RECORD_MAHNUNG_id_mahnung(), false);
			oldNewMahnungMap.put(recListFibuMahnungenWithPos.get(i).get_UP_RECORD_MAHNUNG_id_mahnung().get_ID_MAHNUNG_cUF_NN(""), true);
		}

		recListMahnungen.GET_SORTED_VECTOR(bibALL.get_Vector(RECORD_MAHNUNG.FIELD__MAHNSTUFE), true);
		
		for (int i=0;i<recListMahnungen.size();i++)
		{
			RECORD_MAHNUNG recMahnung = recListMahnungen.get(i);
			
			this.add(new buttonMahnung(recMahnung, oldNewMahnungMap.get(recMahnung.get_ID_MAHNUNG_cUF_NN(""))), E2_INSETS.I_1_1_1_1);
		}

	}


	private class buttonMahnung extends MyE2_Button
	{
		private RECORD_MAHNUNG  recMahnung = null;

		public buttonMahnung(RECORD_MAHNUNG  rec_Mahnung, boolean true_if_new__false_if_old) throws myException
		{
			super();
			this.recMahnung = rec_Mahnung;

			this.add_GlobalAUTHValidator_AUTO("MAHNUNG_ERSTELLEN");
			this.add_GlobalValidator(new FIBU_MAHNUNG_Sachbearbeiter_Validator_3());


			if (this.recMahnung.get_MAHNSTUFE_cUF_NN("-1").equals("1"))
			{
				this.setIcon(E2_ResourceIcon.get_RI("m1.png"));
				this.add_oActionAgent(new actionOpenMahnung(this.recMahnung, true_if_new__false_if_old));
			}
			else if (this.recMahnung.get_MAHNSTUFE_cUF_NN("-1").equals("2"))
			{
				this.setIcon(E2_ResourceIcon.get_RI("m2.png"));
				this.add_oActionAgent(new actionOpenMahnung(this.recMahnung, true_if_new__false_if_old));
			} 
			else if (this.recMahnung.get_MAHNSTUFE_cUF_NN("-1").equals("3"))
			{
				this.setIcon(E2_ResourceIcon.get_RI("m3.png"));
				this.add_oActionAgent(new actionOpenMahnung(this.recMahnung, true_if_new__false_if_old));
			} 
			else
			{
				this.setIcon(E2_ResourceIcon.get_RI("error.png"));
			}
		}
	}


	private class actionOpenMahnung extends XX_ActionAgent
	{
		private RECORD_MAHNUNG  recMahnung = null;
		
		private boolean newMahnung = false; 
		
		public actionOpenMahnung( RECORD_MAHNUNG  rec_Mahnung, boolean isNewMahnung) throws myException
		{
			super();
			this.recMahnung = rec_Mahnung;
			this.newMahnung = isNewMahnung;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			if(newMahnung){
				new FIBU_MAHNUNG_Container(this.recMahnung,FIBU_MAHNUNG_List_Button.this.oNaviList);
				
			}else{
				new FIBU_Container4Mahnung_NG(this.recMahnung,FIBU_MAHNUNG_List_Button.this.oNaviList);
			}
		}

	}



}
