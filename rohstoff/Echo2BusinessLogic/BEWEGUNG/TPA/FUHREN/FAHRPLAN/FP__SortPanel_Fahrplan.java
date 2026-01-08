package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.unboundDataFields.UB_TextFieldForNumbers;
import panter.gmbh.Echo2.components.unboundDataFields.VECTOR_UB_FIELDS;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN.UEBERSICHT.FPU_BasicModuleContainer;

public abstract class FP__SortPanel_Fahrplan extends E2_BasicModuleContainer
{
	private String 	cID_Maschinen_LKW = null;
	private String  Date_in_YYYYMMDD = null;
	
	private Vector<OWN_RECORD_VPOS_TPA_FUHRE> vRecFuhren = new Vector<OWN_RECORD_VPOS_TPA_FUHRE>();
	
	
	
	
	public FP__SortPanel_Fahrplan(String maschinen_LKW, String date_in_YYYYMMDD) throws myException
	{
		super();
		this.cID_Maschinen_LKW = maschinen_LKW;
		this.Date_in_YYYYMMDD = date_in_YYYYMMDD;
		
		RECLIST_VPOS_TPA_FUHRE  oRecListFahrten = new RECLIST_VPOS_TPA_FUHRE("SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE " +
				" WHERE NVL(JT_VPOS_TPA_FUHRE.FUHRE_AUS_FAHRPLAN,'N')='Y'"+
				" AND NVL(JT_VPOS_TPA_FUHRE.DELETED,'N')='N'"+
				" AND NVL(JT_VPOS_TPA_FUHRE.IST_STORNIERT,'N')='N'"+
				" AND TO_CHAR(JT_VPOS_TPA_FUHRE.DAT_FAHRPLAN_FP,'yyyy-MM-dd')="+bibALL.MakeSql(this.Date_in_YYYYMMDD)+
				" AND ID_MASCHINEN_LKW_FP="+this.cID_Maschinen_LKW+
				" ORDER BY JT_VPOS_TPA_FUHRE.SORTIERUNG_FP");

		
		if (oRecListFahrten.get_vKeyValues().size()==0)
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es gibt nichts zu sortieren !!"));
			return;
		}
		
		for (int i=0;i<oRecListFahrten.get_vKeyValues().size();i++)
		{
			this.vRecFuhren.add(new OWN_RECORD_VPOS_TPA_FUHRE(oRecListFahrten.get(i)));
		}
		

		//maske bauen:
		
		MyE2_Grid  oGridForSort = new MyE2_Grid(MyE2_Grid.STYLE_GRID_DDARK_BORDER_INSETS_11());
		
		oGridForSort.setSize(4);
		
		oGridForSort.add(new localLabel("Startort",true),E2_INSETS.I_2_2_2_2);
		oGridForSort.add(new localLabel("Zielort",true),E2_INSETS.I_2_2_2_2);
		oGridForSort.add(new localLabel("Sorte",true),E2_INSETS.I_2_2_2_2);
		oGridForSort.add(new localLabel("Sort-Nr",true),E2_INSETS.I_2_2_2_2);

		for (int i=0;i<this.vRecFuhren.size();i++)
		{
			OWN_RECORD_VPOS_TPA_FUHRE recFuhre = this.vRecFuhren.get(i); 
			
			String cStart = recFuhre.get_L_NAME1_cUF_NN("")+" "+recFuhre.get_L_ORT_cUF_NN("");
			String cZiel = recFuhre.get_A_NAME1_cUF_NN("")+" "+recFuhre.get_A_ORT_cUF_NN("");
			
			String cSorte = new MyE2_String("--- Sorte unbekannt ---").CTrans();
			if (recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek() != null)
			{
				cSorte = recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek().get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cUF_NN("")+" - "+
							recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek().get_ANR2_cUF_NN("")+"   "+
							recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek().get_ARTBEZ1_cUF_NN("");
			}
			else
			{
				if (recFuhre.get_UP_RECORD_ARTIKEL_id_artikel()!=null)
				{
					cSorte = recFuhre.get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cUF_NN("")+" "+recFuhre.get_UP_RECORD_ARTIKEL_id_artikel().get_ARTBEZ1_cUF_NN("");
				}
			}
			cSorte = bibALL.get_LeftString(cSorte, 25);

			
			oGridForSort.add(new localLabel(cStart,false),E2_INSETS.I_2_2_2_2);
			oGridForSort.add(new localLabel(cZiel,false),E2_INSETS.I_2_2_2_2);
			oGridForSort.add(new localLabel(cSorte,false),E2_INSETS.I_2_2_2_2);
			oGridForSort.add(recFuhre.get_UbSortField(),E2_INSETS.I_2_2_2_2);
			
		}

		this.add(oGridForSort, E2_INSETS.I_5_5_5_5);
		this.add(new E2_ComponentGroupHorizontal(0,new ownButtonSave(), E2_INSETS.I_0_0_0_0), E2_INSETS.I_5_5_5_5);
		
		this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(600), new Extent(400), new MyE2_String("Sortierung des Fahrplans"));
		
	}
	
	

	private class OWN_RECORD_VPOS_TPA_FUHRE extends RECORD_VPOS_TPA_FUHRE
	{
		private UB_TextFieldForNumbers  ubSortField = null;
		
		public OWN_RECORD_VPOS_TPA_FUHRE(RECORD_VPOS_TPA_FUHRE recordOrig) throws myException
		{
			super(recordOrig);
			this.ubSortField = new UB_TextFieldForNumbers("SORTIERUNG_FP",0,false,  this.get_SORTIERUNG_FP_cUF_NN("0"),50,4);
		}

		public UB_TextFieldForNumbers get_UbSortField()
		{
			return ubSortField;
		}
	}
	
	
	private class localLabel extends MyE2_Label
	{
		public localLabel(Object cText, boolean bTitle) 
		{
			super(cText);
			if (bTitle)
				this.setFont(FPU_BasicModuleContainer.FontTitle);
			else
				this.setFont(FPU_BasicModuleContainer.FontList);
				
		}
	}

	
	private class ownButtonSave extends MyE2_Button
	{

		public ownButtonSave()
		{
			super(new MyE2_String("Speichern"));
			this.add_oActionAgent(new ownActionSave());
		}
		
		private class ownActionSave extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				FP__SortPanel_Fahrplan oThis = FP__SortPanel_Fahrplan.this;
				
				//zuerst die eingaben checken
				VECTOR_UB_FIELDS vUB = new VECTOR_UB_FIELDS();
				for (int i=0;i<oThis.vRecFuhren.size();i++)
				{
					vUB.add(oThis.vRecFuhren.get(i).get_UbSortField());
				}
				
				bibMSG.add_MESSAGE(vUB.get_MV_AllFieldsAreOK_ShowErrorInput());
				if (bibMSG.get_bIsOK())
				{
					Vector<String> vSQL = new Vector<String>();
					for (int i=0;i<oThis.vRecFuhren.size();i++)
					{
						if (oThis.vRecFuhren.get(i).get_UbSortField().get_bFieldHasChanged())
						{
							oThis.vRecFuhren.get(i).set_NEW_VALUE_SORTIERUNG_FP(oThis.vRecFuhren.get(i).get_UbSortField().get_cInsertValuePart());
							vSQL.add(oThis.vRecFuhren.get(i).get_SQL_UPDATE_STATEMENT(null, true));
						}
					}
					if (vSQL.size()>0)
					{
						bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL, true));
						if (bibMSG.get_bIsOK())
						{
							oThis.CLOSE_AND_DESTROY_POPUPWINDOW(true);
							oThis.do_action_after_Sorting(oThis.cID_Maschinen_LKW, oThis.Date_in_YYYYMMDD);
						}
					}
					else
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es wurde nichts verändert !"));
					}
				}
			}
		}
		
		
	}
	
	
	
	public abstract void do_action_after_Sorting(String cID_MASCHINEN_LKW, String cDate_YYYYMMDD) throws myException;
	
}
