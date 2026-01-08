package rohstoff.Echo2BusinessLogic.FIBU.MAHNUNGEN;

import java.util.Vector;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid_InLIST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_FIBU_MAHNUNG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MAHNUNG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MAHNUNG;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


/*
 * gibt grid zurueck mit 1 2 oder 3 mahnbuttons
 */
public class FIBU_LIST_BUTTONS_Mahnung extends MyE2_Grid_InLIST 
{
	private RECORD_FIBU recFIBU = null;

	private E2_NavigationList   oNaviList = null;

	
	public FIBU_LIST_BUTTONS_Mahnung(RECORD_FIBU rec_FIBU, E2_NavigationList NaviList) throws myException 
	{
		super(3,MyE2_Grid_InLIST.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		this.recFIBU = rec_FIBU;
		
		this.oNaviList = NaviList;
		
		RECLIST_FIBU_MAHNUNG  recListFibuMahnungen = this.recFIBU.get_DOWN_RECORD_LIST_FIBU_MAHNUNG_id_fibu();
		RECLIST_MAHNUNG       recListMahnungen = new RECLIST_MAHNUNG();
		
		for (int i=0;i<recListFibuMahnungen.get_vKeyValues().size();i++)
		{
			recListMahnungen.ADD(recListFibuMahnungen.get(i).get_UP_RECORD_MAHNUNG_id_mahnung(),false);
		}
		
		
		Vector<RECORD_MAHNUNG>  vMahnungen = recListMahnungen.GET_SORTED_VECTOR(bibALL.get_Vector(RECORD_MAHNUNG.FIELD__MAHNSTUFE), true);
		
		
		for (int i=0;i<vMahnungen.size();i++)
		{
			RECORD_MAHNUNG recMahnung = vMahnungen.get(i);
			this.add(new buttonMahnung(recMahnung), E2_INSETS.I_1_1_1_1);
		}
		
	}
	
	
	private class buttonMahnung extends MyE2_Button
	{
		private RECORD_MAHNUNG  recMahnung = null;
		
		public buttonMahnung( RECORD_MAHNUNG  rec_Mahnung) throws myException
		{
			super();
			this.recMahnung = rec_Mahnung;
			this.add_GlobalAUTHValidator_AUTO("MAHNUNG_ERSTELLEN");

			if (this.recMahnung.get_MAHNSTUFE_cUF_NN("-1").equals("1"))
			{
				this.setIcon(E2_ResourceIcon.get_RI("m1.png"));
				this.add_oActionAgent(new actionOpenMahnung(this.recMahnung));
			}
			else if (this.recMahnung.get_MAHNSTUFE_cUF_NN("-1").equals("2"))
			{
				this.setIcon(E2_ResourceIcon.get_RI("m2.png"));
				this.add_oActionAgent(new actionOpenMahnung(this.recMahnung));
			} 
			else if (this.recMahnung.get_MAHNSTUFE_cUF_NN("-1").equals("3"))
			{
				this.setIcon(E2_ResourceIcon.get_RI("m3.png"));
				this.add_oActionAgent(new actionOpenMahnung(this.recMahnung));
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
		
		public actionOpenMahnung( RECORD_MAHNUNG  rec_Mahnung) throws myException
		{
			super();
			this.recMahnung = rec_Mahnung;
		}

		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			new FIBU_Container4Mahnung(this.recMahnung,FIBU_LIST_BUTTONS_Mahnung.this.oNaviList);
		}
		
	}
	
	
	
}
