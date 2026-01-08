package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainerWith_HTTP_PANE;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.WAEHRUNGSQUERY;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDataRecordHashMap;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;

public class KFIX_P_M_BT_Kurs_holen extends MyE2_Button implements IF_RB_Component{

	private RB_KF  key;
	private E2_ComponentMAP compMap;

	public KFIX_P_M_BT_Kurs_holen(E2_ComponentMAP oCompMap) 
	{
		super("Kurs ?", new MyE2_String("Kurs holen"), null);
		
		this.compMap = oCompMap;

		this.add_oActionAgent(new ownActionAgent());

	}
	private class ownActionAgent extends XX_ActionAgent{

		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			E2_BasicModuleContainerWith_HTTP_PANE oPanePopup = new E2_BasicModuleContainerWith_HTTP_PANE(600,500);

			String cID_WAEHRUNG_BASIS = bibALL.get_ID_BASISWAEHRUNG();
			String cID_WAEHRUNG_ZIEL  = ((KFIX__SelectField_Waehrung)compMap.get__Comp(VPOS_KON.id_waehrung_fremd)).rb_readValue_4_dataobject().replace(".", "");

			if (cID_WAEHRUNG_BASIS.equals(cID_WAEHRUNG_ZIEL))
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Umrechnung unsinnig, da die Währungen gleich sind !"));
				return;
			}

			if (bibALL.isEmpty(cID_WAEHRUNG_BASIS) || bibALL.isEmpty(cID_WAEHRUNG_ZIEL))
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Achtung! die Währungen sind nicht definiert !!"));
				return;
			}

			try
			{
				SEL queryInfoUrl = 
						new SEL(WAEHRUNGSQUERY.info_url)
						.FROM(_TAB.waehrungsquery)
						.WHERE(new vgl(WAEHRUNGSQUERY.id_waehrung_ziel, cID_WAEHRUNG_BASIS))
						.AND(new vgl(WAEHRUNGSQUERY.id_waehrung_ziel, cID_WAEHRUNG_ZIEL));


				MyDataRecordHashMap oHM = new MyDataRecordHashMap(queryInfoUrl.s());

				oPanePopup.showWebsite(oHM.get_UnFormatedValue("INFO_URL"));

				oPanePopup.CREATE_AND_SHOW_POPUPWINDOW(null, null, new MyE2_String("Währungsinfo"));
			} 
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Vermutlicht wurde für diese Kombination der Umrechnung keine Webseite hinterlegt !"));
				return;
			}
		}
	}
	@Override
	public void mark_Neutral() throws myException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mark_MustField() throws myException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mark_Disabled() throws myException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mark_FalseInput() throws myException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void set_Alignment(Alignment align) throws myException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public RB_KF rb_KF() throws myException {
		return key;
	}
	@Override
	public void set_rb_RB_K(RB_KF key) throws myException {
		this.key = key;
		
	}
	@Override
	public Vector<RB_Validator_Component> rb_VALIDATORS_4_INPUT() throws myException {
		// TODO Auto-generated method stub
		return new Vector<RB_Validator_Component>();
	}
}
