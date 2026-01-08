package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import java.util.HashMap;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_SaveMaskStandard;
import panter.gmbh.Echo2.ListAndMask.Mask.ActionButtons.MaskButtonCancelMaskSTANDARD;
import panter.gmbh.Echo2.ListAndMask.Mask.ActionButtons.maskButtonSaveMask;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_edit_or_view_Singular;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskClose;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_bt_maskSaveAndClose;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MaskSearchField.MyE2_DB_MaskSearchField;
import panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal.DefSpalteLayout_And_Else;
import panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal.TransKurzesDatum;
import panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal.TransZahlenMitEinheit;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON_TRAKT;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARTIKELBEZ_LIEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS__SETTING;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX.KFIX_P_M__DataObjectCollector;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX.KFIX_P_M__MaskModulContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.BSK_P_MASK__ModulContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE.FUO_MASK_ComponentMAP;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARTBEZ_LIEF_extend;
import rohstoff.utils.My_VPos_KON;

public class FU___MASK_SEARCH_KONTRAKT_POS extends MyE2_DB_MaskSearchField
{
	private String EK_VK = null;
	private String EK_VK_Komplement = null;

		
	public FU___MASK_SEARCH_KONTRAKT_POS(SQLField osqlField, String cEK_VK) throws myException
	{
		//spaltennamen: ID,BUCHNR,FIRMA,MENGE,REST,SORTE,ARTBEZ,PREIS,GUELTIG
		
		super(		osqlField, 
				                " JT_VPOS_KON.ID_VPOS_KON AS ID,"+
								"    NVL(JT_VKOPF_KON.BUCHUNGSNUMMER,TO_CHAR(JT_VKOPF_KON.ID_VKOPF_KON))||'-'||NVL(TO_CHAR(JT_VPOS_KON.POSITIONSNUMMER),'?') AS BUCHNR,"+
				                "    (CASE WHEN NVL("+VKOPF_KON.ist_fixierung.tnfn()+",'N')='Y' THEN 'F' ELSE '-' END) AS FIXIERUNG, "  + 
								"    (CASE WHEN NVL("+VPOS_KON.typ_ladung.tnfn()+",'N')='Y' THEN 'LD' ELSE '-' END) AS LADUNGSPOSITION, "  +
//				                "    (SELECT COUNT(*) FROM "+bibE2.cTO()+"."+VPOS_TPA_FUHRE.fullTabName()+" FU "
//				                		+ " WHERE FU."+VPOS_TPA_FUHRE.id_vpos_kon_ek.fn()+"="+VPOS_KON.id_vpos_kon.tnfn()
//				                		+ " AND NVL("+VPOS_TPA_FUHRE.deleted.fn()+",'N')='N'"
//				                		+ " AND NVL("+VPOS_TPA_FUHRE.ist_storniert.fn()+",'N')='N' ) AS ANZAHL_FUHREN, "+
								"    NVL(TO_CHAR(DRUCKDATUM,'dd.mm.yy'),'<datum>') AS DRUCKDATUM,"+
								"    NVL(JT_VKOPF_KON.NAME1,'')||' '||NVL(JT_VKOPF_KON.ORT,'') AS FIRMA,"+
								"    to_char(NVL(JT_VPOS_KON.ANZAHL,0),'fm999g999g990','NLS_NUMERIC_CHARACTERS = '',.''')||' '||NVL(JT_VPOS_KON.EINHEITKURZ,'EH')  AS MENGE, "+
								"    0 AS REST, "+
								"    NVL(JT_VPOS_KON.ANR1,'')||'-'||NVL(JT_VPOS_KON.ANR2,'') AS SORTE , "+
								"    NVL(JT_VPOS_KON.ARTBEZ1,'')||' '||CASE WHEN JT_VPOS_KON.ARTBEZ2 IS NOT NULL THEN CSCONVERT(' // '||JT_VPOS_KON.ARTBEZ2,'NCHAR_CS') ELSE CSCONVERT('','NCHAR_CS') END AS ARTBEZ , "+
								"    NVL(to_char(JT_VPOS_KON.EINZELPREIS_FW,'fm999g990d00','NLS_NUMERIC_CHARACTERS = '',.'''),'-')||' '||NVL(JD_WAEHRUNG.WAEHRUNGSSYMBOL,'??') AS PREIS , "+
								"    NVL(TO_CHAR(JT_VPOS_KON_TRAKT.GUELTIG_VON,'dd.mm.yy'),'-')||' - '||NVL(TO_CHAR(JT_VPOS_KON_TRAKT.GUELTIG_BIS,'dd.mm.yy'),'-') AS GUELTIG ",
								
								" "+bibE2.cTO()+".JT_VPOS_KON  " +
								" LEFT OUTER JOIN "+bibE2.cTO()+".JT_VKOPF_KON ON       (JT_VPOS_KON.ID_VKOPF_KON=JT_VKOPF_KON.ID_VKOPF_KON) " +
								" LEFT OUTER JOIN "+bibE2.cTO()+".JT_VPOS_KON_TRAKT ON  (JT_VPOS_KON.ID_VPOS_KON=JT_VPOS_KON_TRAKT.ID_VPOS_KON) " +
								" LEFT OUTER JOIN "+bibE2.cTO()+".JT_ADRESSE ON         (JT_VKOPF_KON.ID_ADRESSE=JT_ADRESSE.ID_ADRESSE) "+ 
								" LEFT OUTER JOIN "+bibE2.cTO()+".JD_WAEHRUNG ON        (JT_VPOS_KON.ID_WAEHRUNG_FREMD=JD_WAEHRUNG.ID_WAEHRUNG) ", 
								
				"NVL(JT_VPOS_KON.ANR1,'')||'-'||NVL(JT_VPOS_KON.ANR2,'')||' ('||NVL(JT_VPOS_KON.ARTBEZ1,'')||')'",
				
				" NVL(JT_VPOS_KON_TRAKT.ABGESCHLOSSEN,'N')='N' " +
				" AND  NVL(JT_VPOS_KON.DELETED,'N')='N' " +
				" AND  NVL(JT_VKOPF_KON.DELETED,'N')='N' " +
				" AND  JT_VKOPF_KON.VORGANG_TYP='"+cEK_VK+"_KONTRAKT'"+
				" AND  (NVL("+VPOS_KON.typ_ladung.tnfn()+",'N')='N' OR "
									+ " ("
									+ " (SELECT COUNT(*) FROM "+bibE2.cTO()+"."+VPOS_TPA_FUHRE.fullTabName()+" FU "
				                	+ " WHERE FU."+VPOS_TPA_FUHRE.id_vpos_kon_ek.fn()+"="+VPOS_KON.id_vpos_kon.tnfn()
				                	+ " AND NVL("+VPOS_TPA_FUHRE.deleted.fn()+",'N')='N'"
				                	+ " AND NVL("+VPOS_TPA_FUHRE.ist_storniert.fn()+",'N')='N' )"
				                	+" + "
									+ " (SELECT COUNT(*) FROM "+bibE2.cTO()+"."+VPOS_TPA_FUHRE.fullTabName()+" FU "
				                	+ " WHERE FU."+VPOS_TPA_FUHRE.id_vpos_kon_vk.fn()+"="+VPOS_KON.id_vpos_kon.tnfn()
				                	+ " AND NVL("+VPOS_TPA_FUHRE.deleted.fn()+",'N')='N'"
				                	+ " AND NVL("+VPOS_TPA_FUHRE.ist_storniert.fn()+",'N')='N' )"
				                	+" + "
									+ " (SELECT COUNT(*) FROM "+bibE2.cTO()+"."+VPOS_TPA_FUHRE_ORT.fullTabName()+" FUO "
									+"                   LEFT OUTER JOIN "+bibE2.cTO()+"."+VPOS_TPA_FUHRE.fullTabName()+" FU "
									+"                   ON (FU.ID_VPOS_TPA_FUHRE=FUO.ID_VPOS_TPA_FUHRE) "
				                	+ " WHERE FUO."+VPOS_TPA_FUHRE_ORT.id_vpos_kon.fn()+"="+VPOS_KON.id_vpos_kon.tnfn()
				                	+ " AND NVL("+VPOS_TPA_FUHRE_ORT.deleted.fn("FUO")+",'N')='N'"
				                	+ " AND NVL("+VPOS_TPA_FUHRE.ist_storniert.fn("FU")+",'N')='N' "
				                	+" )"
				                	
				                	+ ")=0"
				     + ")"
				,
				
				" UPPER(JT_ADRESSE.NAME1) LIKE UPPER('%#WERT#%')" +
				" OR UPPER(JT_ADRESSE.ORT) LIKE UPPER('%#WERT#%') " +
				" OR TRIM(TO_CHAR(JT_ADRESSE.ID_ADRESSE))='#WERT#'" +
				" OR UPPER(JT_VPOS_KON.ARTBEZ1) LIKE UPPER('%#WERT#%')" +
				" OR UPPER(JT_VPOS_KON.ARTBEZ1) LIKE UPPER('%#WERT#%') " +
				" OR UPPER(JT_VPOS_KON.ANR1) LIKE UPPER('%#WERT#%')" +
				" OR UPPER(JT_VPOS_KON.ANR2) = UPPER('#WERT#')" +
				" OR  TRIM(TO_CHAR(JT_VPOS_KON.ID_VPOS_KON))='#WERT#' "+
				" OR NVL(UPPER(JT_VKOPF_KON.BUCHUNGSNUMMER),'-') LIKE UPPER('%#WERT#%')",      
				
				null,
				null, 
				"SELECT NVL(JT_VKOPF_KON.BUCHUNGSNUMMER,'??')||'-'||NVL(JT_VPOS_KON.POSITIONSNUMMER,'')||' '||"+
				"'['||NVL(JT_VPOS_KON.ANR1,'')||'-'||NVL(JT_VPOS_KON.ANR2,'')||'] '||NVL(JT_VKOPF_KON.NAME1,'')||' '||NVL(JT_VKOPF_KON.ORT,'')||' - '||" +
						" TO_CHAR(JT_VPOS_KON.ANZAHL,'999G999G999', 'NLS_NUMERIC_CHARACTERS='',.''')||' '||NVL(JT_VPOS_KON.EINHEITKURZ,'??') ||' '|| NVL(JT_VPOS_KON.ARTBEZ1,'')||''||'   ('||"+
				"    NVL(TO_CHAR(JT_VPOS_KON_TRAKT.GUELTIG_VON,'dd.mm.'),'-')||' - '||"+
				"    NVL(TO_CHAR(JT_VPOS_KON_TRAKT.GUELTIG_BIS,'dd.mm.'),'-')||')'"+
				" FROM "+
				"    JT_VKOPF_KON, JT_VPOS_KON,JT_VPOS_KON_TRAKT "+
				" WHERE "+
				"    JT_VKOPF_KON.ID_VKOPF_KON   = JT_VPOS_KON.ID_VKOPF_KON AND JT_VPOS_KON.ID_VPOS_KON = JT_VPOS_KON_TRAKT.ID_VPOS_KON AND " +
				"    JT_VPOS_KON.ID_VPOS_KON=#WERT#",				
				 E2_INSETS.I_0_0_0_0, false);
		
		
		
		this.set_oPosX(null);
		this.set_oPosY(null);
		this.set_oPopupHigh(new Extent(500));
		this.set_oPopupWidth(new Extent(800));
		
		this.EK_VK = cEK_VK;
		
		this.EK_VK_Komplement = this.EK_VK.equals("EK")?"VK":"EK";
		
//		this.set_FormatterForButtons(new ownButtonFormatter());
		
		HashMap<Integer, DefSpalteLayout_And_Else> hmLabels=new HashMap<Integer, DefSpalteLayout_And_Else>();
		

		
		//spaltennamen: ID,BUCHNR,FIRMA,MENGE,REST,SORTE,ARTBEZ,PREIS,GUELTIG
		hmLabels.put(0,    	new DefSpalteLayout_And_Else(-1,true,new MyE2_String("POS-ID"),		"ID", 			null,null,null,null,null,null,								null,false,null));
		hmLabels.put(1, 	new DefSpalteLayout_And_Else(0,true,new MyE2_String("Buchungs-Nr"),	"BUCHNR", 		null,null,E2_ALIGN.CENTER_MID,E2_ALIGN.CENTER_MID,null,null,null,false,null));
		hmLabels.put(2,    	new DefSpalteLayout_And_Else(1,true,new MyE2_String("Fix"),		    "FIXIERUNG", 	null,null,null,null,null,null,								null,false,null));
		hmLabels.put(3,    	new DefSpalteLayout_And_Else(2,true,new MyE2_String("LD"),			"LADUNGSPOSITION", null,null,null,null,null,null,							null,false,null));
		hmLabels.put(4,  	new DefSpalteLayout_And_Else(3,true,new MyE2_String("Datum"),		"DRUCKDATUM", 	null,null,E2_ALIGN.CENTER_MID,E2_ALIGN.CENTER_MID,null,null,null,false,new TransKurzesDatum()));
		hmLabels.put(5,   	new DefSpalteLayout_And_Else(4,true,new MyE2_String("Firma"),		"FIRMA", 		null,null,null,null,null,null,								null,true,null));
		hmLabels.put(6,   	new DefSpalteLayout_And_Else(5,true,new MyE2_String("Menge"),		"MENGE", 		null,null,E2_ALIGN.RIGHT_MID,E2_ALIGN.RIGHT_MID,null,null,	null,false,new TransZahlenMitEinheit()));
		hmLabels.put(7,   	new DefSpalteLayout_And_Else(6,true,new MyE2_String("Offen"),		"REST", 		null,null,E2_ALIGN.RIGHT_MID,E2_ALIGN.RIGHT_MID,null,null,	null,false,new TransZahlenMitEinheit()));
		hmLabels.put(8,   	new DefSpalteLayout_And_Else(7,true,new MyE2_String("Sorte"),		"SORTE", 		null,null,null,null,null,null,								null,false,null));
		hmLabels.put(9,   	new DefSpalteLayout_And_Else(8,true,new MyE2_String("Artikelbez."),	"ARTBEZ",	 	null,null,null,null,null,null,								null,true,null));
		hmLabels.put(10,   	new DefSpalteLayout_And_Else(9,true,new MyE2_String("Preis"),		"PREIS", 		null,null,E2_ALIGN.RIGHT_MID,E2_ALIGN.RIGHT_MID,null,null,	null,false,new TransZahlenMitEinheit()));
		hmLabels.put(11, 	new DefSpalteLayout_And_Else(10,true,new MyE2_String("Gültig"),		"GUELTIG", 		null,null,E2_ALIGN.CENTER_MID,E2_ALIGN.CENTER_MID,null,null,null,false,new TransKurzesDatum()));
		
		this.get_oSeachBlock().set_hmSortierInfo(hmLabels);
		
//		this.set_hmTitelzeileAuswahlBlock(hmLabels);
		
		
	}


	public String get_EK_VK()
	{
		return EK_VK;
	}


	public String get_EK_VK_Komplement()
	{
		return EK_VK_Komplement;
	}

	
	protected class openKontraktPos extends MyE2_Button
	{

		public openKontraktPos()
		{
			super(E2_ResourceIcon.get_RI("edit_mini.png"), true);
			this.add_oActionAgent(new ownActionAgent());
			this.add_IDValidator(new ownValidator());
			this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("EDIT_KONTRAKT_POS_AUS_FUHRE"));
			this.setToolTipText(new MyE2_String("Bearbeite die Kontraktposition").CTrans());
		}
		
		
		//2011-03-25: schalter zum oeffnen der kontrakt-position ist immer aktiv
		public void set_bEnabled_For_Edit(boolean enabled) throws myException
		{
		}
		
		private class ownActionAgent extends XX_ActionAgent
		{
			public void executeAgentCode(ExecINFO oExecInfo) throws myException 
			{
				//zuerst nachsehen, ob es eine zahl im suchfeld gibt
				FU___MASK_SEARCH_KONTRAKT_POS oThis = FU___MASK_SEARCH_KONTRAKT_POS.this;
				
				long lWert = new MyLong(oThis.get_cActualMaskValue(),new Long(-1), new Long(-1)).get_lValue();
				
				if (lWert>0)
				{
					//zuerst pruefen, ob eine zahl im button steht
					String cZahl = ""+lWert;
					
					My_VPos_KON oKon = new My_VPos_KON(cZahl);
					
					MyE2_MessageVector oMV = new MyE2_MessageVector();
					oMV.add_MESSAGE(bibE2.get_LAST_ACTIONEVENT().make_ID_Validation(bibALL.get_Vector(cZahl)));

					BSK_P_MASK__ModulContainer oMask = new BSK_P_MASK__ModulContainer(new BS__SETTING(oKon.get_oVKopfKON().get_VORGANGSART()));
					
					if (oMV.get_bHasAlarms())
					{
						oMask.get_vCombinedComponentMAPs().MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_VIEW, oKon.get_UnFormatedValue("ID_VPOS_KON"));
						oMask.get_oRowForButtons().add(new MaskButtonCancelMaskSTANDARD(oMask));
					}
					else
					{
						oMask.get_vCombinedComponentMAPs().MAKRO_Fill_Build_Set_MASK(E2_ComponentMAP.STATUS_EDIT, oKon.get_UnFormatedValue("ID_VPOS_KON"));
						oMask.get_oRowForButtons().add(new maskButtonSaveMask(oMask,new E2_SaveMaskStandard(oMask, null),null, null));
						oMask.get_oRowForButtons().add(new MaskButtonCancelMaskSTANDARD(oMask));
					}
					
					MyE2_String Titel = new MyE2_String("EK-Kontrakt-Position");
					if (oKon.get_oVKopfKON().get_bIstVK())
						Titel = new MyE2_String("VK-Kontrakt-Position");
					
					oMask.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1000), new Extent(700), Titel);
						
				}
				else
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Diese Funktion ist nur bei vorhandener Kontrakt-Position möglich"));
				}
			}
		}
		
		
		private class ownValidator extends XX_ActionValidator
		{

			public MyE2_MessageVector isValid(Component oCompWhichIsValidated) throws myException 
			{
				return null;
			}

			protected MyE2_MessageVector isValid(String cID_Unformated) throws myException 
			{
				MyE2_MessageVector oMV = new MyE2_MessageVector();
				
				My_VPos_KON oKon = new My_VPos_KON(cID_Unformated);
				
				boolean bAgeschlossen =  oKon.get_bPosIstAbgeschlossen();

				if (bAgeschlossen)
					oMV.add_MESSAGE(new MyE2_Alarm_Message("Bearbeiten nur bei offenen Kontraktpositionen erlaubt !"));
				
				return oMV;
			}
			
		}
	}
	
	
	
	
	/**
	 * 2017-02-06: auch die neue kontraktpositions-variante implementieren
	 * @author martin
	 *
	 */
	protected class openKontraktPosNG extends RB_bt_edit_or_view_Singular {

		private Rec20  rec_vpos_kon = null;;
		
		/**
		 * 
		 */
		public openKontraktPosNG() {
			super();
			this._image(E2_ResourceIcon.get_RI("edit_mini.png"), true);
			this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("EDIT_KONTRAKT_POS_AUS_FUHRE"));
		}


		@Override
		public RB_ModuleContainerMASK generate_MaskContainer() throws myException {
			FU___MASK_SEARCH_KONTRAKT_POS oThis = FU___MASK_SEARCH_KONTRAKT_POS.this;
			
			long lWert = new MyLong(oThis.get_cActualMaskValue(),new Long(-1), new Long(-1)).get_lValue();
			
			if (lWert>0) {
				this.rec_vpos_kon = new Rec20(_TAB.vpos_kon)._fill_id(lWert);
				Rec20 rec_vopf = this.rec_vpos_kon.get_up_Rec20(VKOPF_KON.id_vkopf_kon);
				
				return new KFIX_P_M__MaskModulContainer(rec_vopf.get_ufs_dbVal(VKOPF_KON.vorgang_typ), rec_vopf);
			} else {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(S.mt("Bitte zuerst eine Kontraktposition definieren !")));
				
				return null;
			}
		}

		@Override
		public RB_DataobjectsCollector generate_DataObjects4Edit() throws myException {
			return  new KFIX_P_M__DataObjectCollector(rec_vpos_kon.get_ufs_dbVal(VPOS_KON.id_vpos_kon),RB__CONST.MASK_STATUS.EDIT,this.rec_vpos_kon.get_up_Rec20(VKOPF_KON.id_vkopf_kon).get_ufs_dbVal(VKOPF_KON.vorgang_typ));
		}

		@Override
		public void define_Actions_4_saveButton(RB_bt_edit_or_view_Singular btEditSingular,RB_bt_maskSaveAndClose bt_saveAndClose_In_Mask, RB_ModuleContainerMASK maskPopup) throws myException {
			bt_saveAndClose_In_Mask.add_oActionAgent(new SaveActionStandard());
			bt_saveAndClose_In_Mask.add_oActionAgent(new ClosePopupActionStandard());
		}

		@Override
		public void define_Actions_4_CloseButton(RB_bt_edit_or_view_Singular btEditSingular, RB_bt_maskClose bt_Close, 	RB_ModuleContainerMASK maskPopup) throws myException {
		}


		@Override
		public RB_DataobjectsCollector generate_DataObjects4View() throws myException {
			return  new KFIX_P_M__DataObjectCollector(rec_vpos_kon.get_ufs_dbVal(VPOS_KON.id_vpos_kon),RB__CONST.MASK_STATUS.VIEW,this.rec_vpos_kon.get_up_Rec20(VKOPF_KON.id_vkopf_kon).get_ufs_dbVal(VKOPF_KON.vorgang_typ));
		}


		@Override
		public boolean is_edit_allowed() throws myException {
			return this.rec_vpos_kon.get_down_reclist20(VPOS_KON_TRAKT.id_vpos_kon, null, null).get(0).is_no_db_val(VPOS_KON_TRAKT.abgeschlossen);
		}
		
	}
	
	
	
	
	protected class lagerButton extends MyE2_Button
	{

		private String FieldNameAdresse = null;
		
		public lagerButton(String cFieldNameAdresse)
		{
			super(E2_ResourceIcon.get_RI("lager_mini.png"), true);
			this.add_oActionAgent(new ownActionLagerButton());
			
			this.setToolTipText(new MyE2_String("Lagerlieferung ").CTrans());

			this.FieldNameAdresse = cFieldNameAdresse;
		}

		private class ownActionLagerButton extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{

				FU___MASK_SEARCH_KONTRAKT_POS 	oThis = 			FU___MASK_SEARCH_KONTRAKT_POS.this;
				E2_ComponentMAP  				oMap_Der_Maske = 	oThis.EXT().get_oComponentMAP();
				oThis.prepare_ContentForNew(false);
				
				((MyE2_DB_MaskSearchField)oMap_Der_Maske.get__Comp(lagerButton.this.FieldNameAdresse)).set_cActualMaskValue(bibALL.get_ID_ADRESS_MANDANT(),true,true,true);
				
				
				/* Hier pruefen, ob es bereits eine sortenauswahl gibt, dann den sortenpopup
				 * bei der artikelsuche starten, damit die artikelbezeichnungsfelder gefuellt werden.
				 * Die sorte kann ueber den gegenuebeliegenden kontrakt oder die ID_ARTIKEL festgelegt sein
				 */
				long lID_ARTIKEL = oMap_Der_Maske.get_LActualDBValue("ID_ARTIKEL", true, true, new Long(-1));
				long lID_VPOS_KON_komplement = -1;
				
				E2_ComponentMAP oMapDerFuhrenMaske = oMap_Der_Maske;
				
				if (oMap_Der_Maske instanceof FU_MASK_ComponentMAP)
				{
					lID_VPOS_KON_komplement = oMap_Der_Maske.get_LActualDBValue("ID_VPOS_KON_"+oThis.EK_VK_Komplement, true, true, new Long(-1));
				}
				else if (oMap_Der_Maske instanceof FUO_MASK_ComponentMAP)
				{
					//dann geht es ueber einen umweg
					oMapDerFuhrenMaske =((FUO_MASK_ComponentMAP)oMap_Der_Maske).get_oFU_FUO_DaughterComponent().EXT().get_oComponentMAP(); 
					lID_VPOS_KON_komplement = oMapDerFuhrenMaske.get_LActualDBValue("ID_VPOS_KON_"+oThis.EK_VK_Komplement, true, true, new Long(-1));
				}
				else
				{
					throw new myException("Fehler beim Suchen der Sorte !");
				}
				String cID_Artikel = "";
				if (lID_ARTIKEL != -1)
				{
					cID_Artikel = ""+lID_ARTIKEL;
					
				}
				else if (lID_VPOS_KON_komplement != -1)
				{
					RECORD_VPOS_KON recVPOS = new RECORD_VPOS_KON(lID_VPOS_KON_komplement);
					cID_Artikel = recVPOS.get_ID_ARTIKEL_cUF_NN("");
				}
				
				
				if (S.isFull(cID_Artikel))
				{
					if (oMap_Der_Maske.get__Comp("ID_ARTIKEL") instanceof MyE2_DB_MaskSearchField)  	// dann sind wir in der fuhrenmaske
					{
						MyE2_DB_MaskSearchField searchSorte = (MyE2_DB_MaskSearchField)oMap_Der_Maske.get__Comp("ID_ARTIKEL");
						searchSorte.set_cActualMaskValue(cID_Artikel, true, true, true);   					// loest den auswahl-popup aus
					}
					else          																			// hier im fuhrenort ist die ID_ARTIKEL-komponente ein einfache DB_Label
					{
						/*
						 * falls im Fuhrenort ein EIGEN-Lager definiert wird, dann nachschauen, ob auf der komplementaerseite eine sorte steht,
						 * wenn ja, die erste verfuegbare sortenbzeichnung laden
						 */

						if (oMapDerFuhrenMaske.get_LActualDBValue("ID_ARTIKEL", true, true, new Long(-1)).longValue()!=-1)
						{
							long l_ID_ARTIKEL = oMapDerFuhrenMaske.get_LActualDBValue("ID_ARTIKEL", true, true, new Long(-1)).longValue();
							
							/*
							 * dann muss noch die artikelbezeichnung auf der EK-seite geladen werden, damit nicht noch extra eine suche durchgefuehrt werden muss
							 * Dazu muss die erste vorhandene artikelbezeichnung, die der eigenen adresse zugeteilt wurde, geladen werden
							 */
							String cQuery = "SELECT JT_ARTIKELBEZ_LIEF.* FROM "+bibE2.cTO()+".JT_ARTIKELBEZ_LIEF "+
													"INNER JOIN "+bibE2.cTO()+".JT_ARTIKEL_BEZ ON (JT_ARTIKELBEZ_LIEF.ID_ARTIKEL_BEZ=JT_ARTIKEL_BEZ.ID_ARTIKEL_BEZ) "+
													"INNER JOIN "+bibE2.cTO()+".JT_ARTIKEL ON (JT_ARTIKEL_BEZ.ID_ARTIKEL= JT_ARTIKEL.ID_ARTIKEL) "+
													"WHERE JT_ARTIKEL.ID_ARTIKEL="+l_ID_ARTIKEL;
							
							if (oThis.EK_VK.equals("VK"))   //dann wird auf der EK-seite die artikelbez gesucht und damit muss sie als artikelbez_lief bekannt sein (wegen AVV-Code)
							{
								cQuery += " AND JT_ARTIKELBEZ_LIEF.ID_ADRESSE="+bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("-1");
							}
							cQuery += " ORDER BY JT_ARTIKEL_BEZ.ANR2";
							
							RECLIST_ARTIKELBEZ_LIEF  reclistArtbezLIEF = new RECLIST_ARTIKELBEZ_LIEF(cQuery);
							
							if (reclistArtbezLIEF.get_vKeyValues().size()>0)
							{
								RECORD_ARTBEZ_LIEF_extend recArtBezLief = new RECORD_ARTBEZ_LIEF_extend(reclistArtbezLIEF.get(0));
								((MyE2_DB_MaskSearchField)oMap_Der_Maske.get__Comp("ID_ARTIKEL_BEZ")).set_cActualMaskValue(recArtBezLief.get_ID_ARTIKEL_BEZ_cUF(),true,true,false);
							}
							else
							{
								((MyE2_DB_MaskSearchField)oMap_Der_Maske.get__Comp("ID_ARTIKEL_BEZ")).prepare_ContentForNew(false);
							}

						}
					}
					
				}
			}
		}
		
	}


	//kann bei eigenen suchbuttons ueberschrieben werden, damit eine eigene groesse gespeichert werden kann
	public E2_BasicModuleContainer get_ownContainer() throws myException
	{
		return new ownContainer();
	}

	
	private class ownContainer extends E2_BasicModuleContainer
	{
		public ownContainer() 
		{
			super();
		}
	}

	
	
	
	
	
	

	

	
	
}
