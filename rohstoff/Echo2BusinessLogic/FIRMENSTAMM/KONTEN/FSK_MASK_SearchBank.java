package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KONTEN;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.DB.MaskSearchField.ButtonToOpenListContainerForSearch;
import panter.gmbh.Echo2.components.DB.MaskSearchField.MyE2_DB_MaskSearchField;
import panter.gmbh.Echo2.components.DB.MaskSearchField.XX_MaskActionAfterFound;
import panter.gmbh.basics4project.DB_ENUMS.BANKENSTAMM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.TERM.vglParam;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.BANKENSTAMM.BANK_LIST_BasicModuleContainer;
import rohstoff.utils.ECHO2.DB_SEARCH_Bank;

public class FSK_MASK_SearchBank extends DB_SEARCH_Bank {

	public FSK_MASK_SearchBank(SQLField osqlfield, SQLFieldMAP osqlFieldgroup) throws myException {
		super(osqlfield, osqlFieldgroup);
		
		
		this.set_bTextForAnzeigeVisible(false);
		
		
		//hier zusatzbutton zum oeffenen des bankenstamms in 
		this.add_AddOnComponent(new BtOpenBankenStamm());
		
		
		
		this.set_oMaskActionAfterMaskValueIsFound(new XX_MaskActionAfterFound() {
			
			@Override
			public void doMaskSettingsAfterValueWrittenInMaskField(String cMaskValue, MyE2_DB_MaskSearchField oSearchField,	boolean bAfterAction, boolean bIS_PrimaryCall) throws myException {
				if (bAfterAction) {
					E2_ComponentMAP oMap = FSK_MASK_SearchBank.this.EXT().get_oComponentMAP();
					FSK_MASK_BankInfo bi = (FSK_MASK_BankInfo) oMap.get(FSK__CONST.FSK_SONDERFELDER.BANKINFO.name());
					bi.fill(new MyLong(cMaskValue));
				}
			}
		});
	}
	
	
	private class BtOpenBankenStamm extends ButtonToOpenListContainerForSearch {

		public BtOpenBankenStamm() {
			super(FSK_MASK_SearchBank.this);
		}

		@Override
		public E2_BasicModuleContainer generateUnpopulatedBasicModuleContainer() throws myException {
			return new BANK_LIST_BasicModuleContainer();
		}


		/*
		 * aus der liste kommen adress-ids, die einem bankenstamm-satz zugeordnet sind
		 * 
		 */
		@Override
		public Long getIDForSearchField(String idAdress) throws myException {
			
			MyLong l = new MyLong(idAdress);
			if (l.isNotOK()) {
				throw new myException(this,"error! no correct id!");
			}
			
			Rec21 rBankenstamm = new Rec21(_TAB.bankenstamm)._fill_sql(
					new SqlStringExtended(
						new SEL("*").FROM(_TAB.bankenstamm).WHERE(new vglParam(BANKENSTAMM.id_adresse)).s())
					._addParameters(new VEK<ParamDataObject>()._a(new Param_Long(l.get_oLong()))));
			
			if (rBankenstamm.is_newRecordSet()) {
				throw new myException(this,"error! no correct id-Adress!");
			}
			
			return rBankenstamm.get_raw_resultValue_Long(BANKENSTAMM.id_bankenstamm);
		}

		/*
		 * in der liste werden adress-ids aufgelistet
		 * 
		 */
		@Override
		public Long getIDForPopupList(String idBankenStamm) throws myException {
			
			MyLong l = new MyLong(idBankenStamm);
			if (l.isNotOK()) {
				throw new myException(this,"error! no correct id-bankenstamm!");
			}
			
			String sel = new SEL("*").FROM(_TAB.bankenstamm).WHERE(new vglParam(BANKENSTAMM.id_bankenstamm)).s();
			
			Rec21 rBankenstamm = new Rec21(_TAB.bankenstamm)._fill_sql(
					new SqlStringExtended(sel)._addParameters(new VEK<ParamDataObject>()._a(new Param_Long(l.get_oLong()))));
			
			if (rBankenstamm.is_newRecordSet()) {
				throw new myException(this,"error! no correct id!");
			}
			
			return rBankenstamm.get_raw_resultValue_Long(BANKENSTAMM.id_adresse);
		}

		@Override
		public MyE2_String getTooltipTextForModulOpenButton() {
			return S.ms("Öffne in das Modul Bankenverwaltung in einem Popup-Fenster");
		}

		
	}

	
	
}
