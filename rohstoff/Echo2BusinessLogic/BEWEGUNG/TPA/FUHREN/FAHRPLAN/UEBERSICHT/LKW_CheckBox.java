package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN.UEBERSICHT;

import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MASCHINEN;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class LKW_CheckBox extends MyE2_CheckBox
{
	private RECORD_MASCHINEN  						recLKW = null;
	private Vector<SPEC_RECORD_VPOS_TPA_FUHRE> 		vREC_Fuhren_ZuLKW = new Vector<SPEC_RECORD_VPOS_TPA_FUHRE>();
	private String  								Date_in_YYYYMMDD = null;
	private int    									iCountAnzahlFahren = 0;

	public LKW_CheckBox(RECORD_MASCHINEN recLKW, String cDate_in_YYYYMMDD) throws myException
	{
		super(recLKW.get_KFZKENNZEICHEN_cUF_NN(""));
		this.recLKW = recLKW;
		this.Date_in_YYYYMMDD = cDate_in_YYYYMMDD;
		
		this.Rebuild_Fuhren_Zu_LKW();
		this.setSelected(this.iCountAnzahlFahren>0);
		
		this.add_oActionAgent(new XX_ActionAgent()   //bei klicken wird der inhalt aufgebaut
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				if (LKW_CheckBox.this.isSelected())
				{
					LKW_CheckBox.this.Rebuild_Fuhren_Zu_LKW();
				}
			}
		});
		
	}
	
	
	public void Rebuild_Fuhren_Zu_LKW() throws myException
	{
		// alle fahrten des besgten tages
		RECLIST_VPOS_TPA_FUHRE  oRecListFahrten = new RECLIST_VPOS_TPA_FUHRE("SELECT * FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE " +
																		" WHERE NVL(JT_VPOS_TPA_FUHRE.FUHRE_AUS_FAHRPLAN,'N')='Y'"+
																		" AND NVL(JT_VPOS_TPA_FUHRE.DELETED,'N')='N'"+
																		" AND NVL(JT_VPOS_TPA_FUHRE.IST_STORNIERT,'N')='N'"+
																		" AND TO_CHAR(JT_VPOS_TPA_FUHRE.DAT_FAHRPLAN_FP,'yyyy-MM-dd')="+bibALL.MakeSql(this.Date_in_YYYYMMDD)+
																		" AND ID_MASCHINEN_LKW_FP="+recLKW.get_ID_MASCHINEN_cUF()+
																		" ORDER BY JT_VPOS_TPA_FUHRE.ID_MASCHINEN_LKW_FP,JT_VPOS_TPA_FUHRE.SORTIERUNG_FP");

		this.vREC_Fuhren_ZuLKW.removeAllElements();
		
		for (int i=0;i<oRecListFahrten.get_vKeyValues().size();i++)
		{
			this.vREC_Fuhren_ZuLKW.add(new SPEC_RECORD_VPOS_TPA_FUHRE(oRecListFahrten.get(i)));
		}
		
		this.iCountAnzahlFahren = this.vREC_Fuhren_ZuLKW.size();
		
	}

	
	

	public int get_iCountAnzahlFahren()
	{
		return iCountAnzahlFahren;
	}


	public void set_iCountAnzahlFahren(int countAnzahlFahren)
	{
		iCountAnzahlFahren = countAnzahlFahren;
	}


	public RECORD_MASCHINEN get_recLKW()
	{
		return recLKW;
	}


	public Vector<SPEC_RECORD_VPOS_TPA_FUHRE> get_vREC_Fuhren_ZuLKW()
	{
		return vREC_Fuhren_ZuLKW;
	}
	
	
	
}
