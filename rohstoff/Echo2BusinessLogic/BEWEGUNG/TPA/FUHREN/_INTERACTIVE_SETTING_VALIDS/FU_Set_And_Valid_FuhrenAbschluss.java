package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN._INTERACTIVE_SETTING_VALIDS;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_MAP_Set_And_Valid;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_MASK_MAP_SETTER;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU___CONST;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE.FU_DAUGHTER_ORTE;

public class FU_Set_And_Valid_FuhrenAbschluss extends XX_MAP_Set_And_Valid
{

		
	private String FIELD_PREISABSCHLUSS_EK =     null;
	private String FIELD_PREISABSCHLUSS_VK =     null;

	
	
	public FU_Set_And_Valid_FuhrenAbschluss()
	{
		super();
		
		this.FIELD_PREISABSCHLUSS_EK = 					RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_EK_PREIS;
		this.FIELD_PREISABSCHLUSS_VK = 					RECORD_VPOS_TPA_FUHRE.FIELD__PRUEFUNG_VK_PREIS;
		
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_COPY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
	{
		return this.makeInterneErmittlung(oMAP,ActionType);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_EMPTY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
	{
		return this.makeInterneErmittlung(oMAP,ActionType);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_EDIT(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
	{
		return this.makeInterneErmittlung(oMAP,ActionType);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_VIEW(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
	{
		return new MyE2_MessageVector();
	}
	
	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_UNDEFINED(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
	{
		return new MyE2_MessageVector();
	}

	
	
	private MyE2_MessageVector makeInterneErmittlung(E2_ComponentMAP oMAP, int ActionType) throws myException
	{
		MyE2_MessageVector oMV_Rueck = new MyE2_MessageVector();
		
		//normale abrechnung bedeutet links=lade und rechts gleich Abladegewicht
		boolean bPreisAbschlussEK =    oMAP.get_bActualDBValue(this.FIELD_PREISABSCHLUSS_EK);
		boolean bPreisAbschlussVK =    oMAP.get_bActualDBValue(this.FIELD_PREISABSCHLUSS_VK);
		
		if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_KLICK_ACTION)
		{
			if (oMAP.get_bActualDBValue(RECORD_VPOS_TPA_FUHRE.FIELD__SCHLIESSE_FUHRE))
			{
				if (bPreisAbschlussEK && bPreisAbschlussVK && this.get_bAlleVorhandenenZusatzOrteAbgeschlossen(oMAP, ActionType))
				{
					oMAP.get__DBComp(RECORD_VPOS_TPA_FUHRE.FIELD__SCHLIESSE_FUHRE_AM).set_cActualMaskValue(bibALL.get_cDateNOW());
					oMAP.get__DBComp(RECORD_VPOS_TPA_FUHRE.FIELD__SCHLIESSE_FUHRE_VON).set_cActualMaskValue(bibALL.get_RECORD_USER().get_KUERZEL_cUF_NN("--"));
					this.MacheAllesZu(oMAP);
				}
				else
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Für die Freigabe der Fuhre MUSS sowohl der EK- als auch der VK-Preis abgeschlossen sein (auch in allen evtl. vorhandenen Zusatzorten)!")));
	
					//dann den haken wieder entfernen
					oMAP.get__DBComp(RECORD_VPOS_TPA_FUHRE.FIELD__SCHLIESSE_FUHRE).set_cActualMaskValue("N");
				}
			}
			else
			{
				oMAP.get__DBComp(RECORD_VPOS_TPA_FUHRE.FIELD__SCHLIESSE_FUHRE_AM).set_cActualMaskValue("");
				oMAP.get__DBComp(RECORD_VPOS_TPA_FUHRE.FIELD__SCHLIESSE_FUHRE_VON).set_cActualMaskValue("");
				
				this.MachAllesAuf(oMAP);
			}
		}
		else if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION)
		{
			if (oMAP.get_bActualDBValue(RECORD_VPOS_TPA_FUHRE.FIELD__SCHLIESSE_FUHRE))
			{
				this.MacheAllesZu(oMAP);
			}
		}
		
		return oMV_Rueck;
	}

	
	
	private boolean get_bAlleVorhandenenZusatzOrteAbgeschlossen(E2_ComponentMAP oMAP, int ActionType) throws myException
	{
		boolean bAllesAbgeschlossen = true;
		
		if (ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_KLICK_ACTION && !oMAP.get_bIs_Neueingabe())
		{
			int iCountLadeOrte = 	((FU_DAUGHTER_ORTE)oMAP.get(FU___CONST.HASH_KEY_MASKE_TOCHTER_QUELLORTE)).get_oFUO_LIST_BasicModuleContainer().get_oNaviList().get_vectorSegmentation().size();
			int iCountAbladeOrte = 	((FU_DAUGHTER_ORTE)oMAP.get(FU___CONST.HASH_KEY_MASKE_TOCHTER_ZIELORTE)).get_oFUO_LIST_BasicModuleContainer().get_oNaviList().get_vectorSegmentation().size();
			
			if ((iCountLadeOrte+iCountAbladeOrte)>0) 
			{
				RECORD_VPOS_TPA_FUHRE  		recFuhre =	 	new RECORD_VPOS_TPA_FUHRE(oMAP.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
				RECLIST_VPOS_TPA_FUHRE_ORT 	recListOrte = 	recFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre();
				
				
				
				for (RECORD_VPOS_TPA_FUHRE_ORT  recOrt: recListOrte.values())
				{
					if (recOrt.is_DELETED_NO())
					{
						if (recOrt.is_PRUEFUNG_MENGE_NO() || recOrt.is_PRUEFUNG_PREIS_NO())
						{
							bAllesAbgeschlossen = false;
						}
					}
				}
			}
		}
	
		return bAllesAbgeschlossen;
	}
	
	
	
	
	
	
	
	private void MacheAllesZu(E2_ComponentMAP oMAP) throws myException
	{
		Vector<String> vNichtInaktiv = new Vector<String>();
		vNichtInaktiv.add(FU___CONST.HASH_KEY_MASKE_TOCHTER_KOSTEN);
		vNichtInaktiv.add(RECORD_VPOS_TPA_FUHRE.FIELD__ID_EAK_CODE);
		vNichtInaktiv.add(RECORD_VPOS_TPA_FUHRE.FIELD__EU_BLATT_MENGE);
		vNichtInaktiv.add(RECORD_VPOS_TPA_FUHRE.FIELD__EU_BLATT_VOLUMEN);
		vNichtInaktiv.add(RECORD_VPOS_TPA_FUHRE.FIELD__SCHLIESSE_FUHRE);
		
		//2013-07-26: das feld "bemerkung fuer sachbearbeiter bleibt auch bei abgeschlossenen fuhre offen
		vNichtInaktiv.add(_DB.VPOS_TPA_FUHRE$BEMERKUNG_SACHBEARBEITER);

		
		//2013-09.16: die felder "Wiegerkarten-kenner" werden von der sperre ausgenommen
		vNichtInaktiv.add(_DB.VPOS_TPA_FUHRE$WIEGEKARTENKENNER_LADEN);
		vNichtInaktiv.add(_DB.VPOS_TPA_FUHRE$WIEGEKARTENKENNER_ABLADEN);

		
		//2014-02-20: Gelangesbestaetigung-schalter ausschliessen
		vNichtInaktiv.add(_DB.VPOS_TPA_FUHRE$GELANGENSBESTAETIGUNG_ERHALTEN);
		
		//2014-06-04: die felder fuer die eingabe der alternativen Lieferbedingungen bleiben auch offen
		vNichtInaktiv.add(_DB.VPOS_TPA_FUHRE$LIEFERBED_ALTERNATIV_EK);
		vNichtInaktiv.add(_DB.VPOS_TPA_FUHRE$LIEFERBED_ALTERNATIV_VK);
		vNichtInaktiv.add(FU___CONST.HASH_KEY_MASKFIELD_LIEFERBED_ALTERNATIV_EK);
		vNichtInaktiv.add(FU___CONST.HASH_KEY_MASKFIELD_LIEFERBED_ALTERNATIV_VK);
		
		
		
		oMAP.set_bEnabled_For_Edit(false,true,vNichtInaktiv,false,false);
	}
	
	
	private void MachAllesAuf(E2_ComponentMAP oMAP) throws myException
	{
		//zuerst den ladevorgang nachvollziehen
		
		String cSTATUS_EDIT = E2_ComponentMAP.STATUS_EDIT;
		if (oMAP.get_oInternalSQLResultMAP()==null) {
			cSTATUS_EDIT = E2_ComponentMAP.STATUS_NEW_EMPTY;
		}
		
		new FU_MASK_MAP_SETTER().__doSettings_BEFORE(oMAP, cSTATUS_EDIT);
		oMAP.set_AllComponentsEnabled_For_Edit(true,cSTATUS_EDIT);

		//dann die einzelnen setter-objekte
		new FU_Set_And_Valid_BuchMge_ist_LadeMge("EK").make_InteractiveSettings_STATUS_EDIT(oMAP, XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION, null,null);
		new FU_Set_And_Valid_BuchMge_ist_LadeMge("VK").make_InteractiveSettings_STATUS_EDIT(oMAP, XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION, null,null);
		
		new FU_Set_And_Valid_Mengenfreigabe("EK").make_InteractiveSettings_STATUS_EDIT(oMAP, XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION, null,null);
		new FU_Set_And_Valid_Mengenfreigabe("VK").make_InteractiveSettings_STATUS_EDIT(oMAP, XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION, null,null);

		new FU_Set_And_Valid_Preisfreigabe("EK").make_InteractiveSettings_STATUS_EDIT(oMAP, XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION, null,null);
		new FU_Set_And_Valid_Preisfreigabe("VK").make_InteractiveSettings_STATUS_EDIT(oMAP, XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION, null,null);

	}
	
}
