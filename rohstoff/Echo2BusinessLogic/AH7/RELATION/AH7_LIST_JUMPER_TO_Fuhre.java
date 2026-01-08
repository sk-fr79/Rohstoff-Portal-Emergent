package rohstoff.Echo2BusinessLogic.AH7.RELATION;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.List.JUMPER.XX_ActionAgentJumpToTargetList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_ENUMS.AH7_STEUERDATEI;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.TERM.vglParam;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import panter.gmbh.indep.myVectors.VectorSingle;

public class AH7_LIST_JUMPER_TO_Fuhre extends MyE2_ButtonInLIST {
	
	public AH7_LIST_JUMPER_TO_Fuhre() throws myException	{
		super(E2_ResourceIcon.get_RI("kompass.png"));

		this.setToolTipText(new MyE2_String("Zeigt alle Fuhren an, die zu diesem AH7-Steuerungs-Eintrag passen").CTrans());

		this.add_oActionAgent(new ownJumpAction());
	}

	
	private class ownJumpAction extends XX_ActionAgentJumpToTargetList {

		public ownJumpAction() throws myException 	{
			super(E2_MODULNAMES.NAME_MODUL_FUHRENFUELLER, "Fuhrenzentrale");
		}

		@Override
		public Vector<String> get_vID_Target(ExecINFO oExecInfo) throws myException {
			MyE2_ButtonInLIST oJumper = (MyE2_ButtonInLIST)oExecInfo.get_MyActionEvent().getSource();
			
			VectorSingle  vID_Fuhren = new VectorSingle();
			
			
			if (oJumper.EXT().get_oComponentMAP() != null   && oJumper.EXT().get_oComponentMAP().get_oInternalSQLResultMAP() != null)	{
				MyLong lAdresseGeoStart = 	new MyLong(oJumper.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_UnFormatedValue(AH7_STEUERDATEI.id_adresse_geo_start.fn()));
				MyLong ldAdresseGeoZiel = 	new MyLong(oJumper.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_UnFormatedValue(AH7_STEUERDATEI.id_adresse_geo_ziel.fn()));
				MyLong ldAdresseJurStart = 	new MyLong(oJumper.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_UnFormatedValue(AH7_STEUERDATEI.id_adresse_jur_start.fn()));
				MyLong ldAdresseJurZiel = 	new MyLong(oJumper.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_UnFormatedValue(AH7_STEUERDATEI.id_adresse_jur_ziel.fn()));
				
				if (lAdresseGeoStart.isOK()&&ldAdresseGeoZiel.isOK()&&ldAdresseJurStart.isOK()&&ldAdresseJurZiel.isOK()) {
				
					SEL selFuhren = new SEL(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre).FROM(_TAB.vpos_tpa_fuhre)		.WHERE(	new vglParam(VPOS_TPA_FUHRE.id_adresse_lager_start))
																											.AND(	new vglParam(VPOS_TPA_FUHRE.id_adresse_lager_ziel))
																											.AND(	new vglParam(VPOS_TPA_FUHRE.id_adresse_start))
																											.AND(	new vglParam(VPOS_TPA_FUHRE.id_adresse_ziel))
																											;
					SqlStringExtended sql = new SqlStringExtended(bibALL.ReplaceTeilString(selFuhren.s(), _TAB.vpos_tpa_fuhre.fullTableName(), "V"+bibALL.get_ID_MANDANT()+"_FUHREN"));
					sql._addParameters(new VEK<ParamDataObject>()
									._a(new Param_Long(lAdresseGeoStart.get_oLong()))
									._a(new Param_Long(ldAdresseGeoZiel.get_oLong()))
									._a(new Param_Long(ldAdresseJurStart.get_oLong()))
									._a(new Param_Long(ldAdresseJurZiel.get_oLong()))
									)
									;
					
					String[][] fuhrenIds = bibDB.EinzelAbfrageInArray(sql) ;
					if (fuhrenIds!=null && fuhrenIds.length>0) {
						VEK<String> v_ids = new VEK<String>()._addArray(fuhrenIds);
						vID_Fuhren.addAll(v_ids);
					}
				} else {
					bibMSG.MV()._addAlarm("Fehler: Die Adress-IDs sind nicht zu ermitteln !");
				}
			}
			return vID_Fuhren;
		}
	
		
		
		//kann ueberschrieben werden wenn innerhalb der aktion der sprung abgelehnt werden muss
		public MyE2_MessageVector  OVERRIDE_PRUEFE_OB_SPRUNG_MOEGLICH_IST(Vector<String> vTargetList) throws myException
		{
			MyE2_MessageVector  oMV = new MyE2_MessageVector();
			
			if (vTargetList.size()==0)
			{
				oMV.add(new MyE2_Alarm_Message(new MyE2_String("Es wurde keine passende Fuhre für den Sprung gefunden ...")));
			}
			return oMV;
		}

	}
	
	
}
