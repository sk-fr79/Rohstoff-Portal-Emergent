package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.MATSPEZ;

import java.util.Date;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.basics4project.DB_ENUMS.MAT_SPEZ;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;

public class FSMS_MASK_ComponentMAP extends E2_ComponentMAP
{
	
	
	public FSMS_MASK_ComponentMAP() throws myException
	{
		super(new FSMS_MASK_SQLFieldMap());
		
		FSMS_MASK_SQLFieldMap oSQLFieldMAP_MatSpez = (FSMS_MASK_SQLFieldMap)this.get_oSQLFieldMAP();

		this.add_Component(new MyE2_DB_Label(oSQLFieldMAP_MatSpez.get_("ID_MAT_SPEZ")),new MyE2_String("ID-Mat-Spez"));
		this.add_Component(new MyE2_DB_CheckBox(oSQLFieldMAP_MatSpez.get_("IST_LIEFERANT")),new MyE2_String("Lieferant ?"));
		this.add_Component(new MyE2_DB_CheckBox(oSQLFieldMAP_MatSpez.get_("IST_ABNEHMER")),new MyE2_String("Abnehmer ?"));
		this.add_Component(new MyE2_DB_TextField(oSQLFieldMAP_MatSpez.get_("BEZEICHNUNG"),true,600),new MyE2_String("Bezeichnung"));
		this.add_Component(new MyE2_DB_TextArea(oSQLFieldMAP_MatSpez.get_("BEMERKUNGEN"),600,6 ),new MyE2_String("Bemerkungen"));
		
		this.add_Component(new FSMS_MASK_compSearchSorte(oSQLFieldMAP_MatSpez.get_(MAT_SPEZ.id_artikel.fn()),this),new MyE2_String("Sorte"));
		//2015-09-02: sortenbezeichnung in der materialspez
		this.add_Component(new FSMS_MASK_compSearchSortenBez(oSQLFieldMAP_MatSpez.get_(MAT_SPEZ.id_artikel_bez.fn()),this),new MyE2_String("Sortenbezeichnung"));
		
		//2013-04-10: neue felder soll-ist
		this.add_Component(new FSMS_DB_Select_STATUS_SOLL_IST(oSQLFieldMAP_MatSpez.get_(_DB.MAT_SPEZ$SOLL_IST_STATUS),120),new MyE2_String("Soll/Ist"));
		this.add_Component(new MyE2_DB_TextField(oSQLFieldMAP_MatSpez.get_(_DB.MAT_SPEZ$DATUM_ERFASSUNG),true,80),new MyE2_String("Datum"));
		this.add_Component(new MyE2_DB_TextField(oSQLFieldMAP_MatSpez.get_(_DB.MAT_SPEZ$ZEIT_ERFASSUNG),true,50),new MyE2_String("Zeit"));
		
		this.add_Component(FSMS_Const.FSMS_MASK_KEY_SETZE_DATUM_UHRZEIT, new ownButtonSetzeDatumUhrzeit(), new MyE2_String("Aktuelle Datums-/ Uhrzeit-Kombination setzen !"));
		
		this.add_Component("DAUGHTER_ANTEILE",new FSMS_Component_MASK_DAUGHTER_MAT_ELEMENT(oSQLFieldMAP_MatSpez,this),new MyE2_String("Anteile"));
		
		this.get_hmInteractiv_settings_validation().put_(_DB.MAT_SPEZ$ZEIT_ERFASSUNG, new FSMS_MASK_VALID_UHRZEIT_KORREKT());
		
		this.add_oMAPValidator(this.get_hmInteractiv_settings_validation().get_XX_MAP_ValidBeforeSave());
	}

	
	
	private class ownButtonSetzeDatumUhrzeit extends MyE2_Button {
		public ownButtonSetzeDatumUhrzeit() {
			super("Datum-Uhrzeit jetzt");
			
			this.add_oActionAgent(new XX_ActionAgent() {
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					FSMS_MASK_ComponentMAP.this.set_cActualDatabaseValueToMask(_DB.MAT_SPEZ$DATUM_ERFASSUNG, myDateHelper.FormatDateNormal(new Date()));
					FSMS_MASK_ComponentMAP.this.set_cActualDatabaseValueToMask(_DB.MAT_SPEZ$ZEIT_ERFASSUNG, myDateHelper.FormatTimeNormal(new Date()));
				}
			});
		}
	}
	
	
	
//	private class ownSearchSorteButton extends DB_SEARCH_Sorte
//	{
//		public ownSearchSorteButton(SQLField osqlField) throws myException {
//			super(osqlField);
//			
//			this.add_AddOnComponent(new ownAddonButtonShow_EK_VK_Sorten());
//			
//			this.set_oMaskActionAfterMaskValueIsFound(new XX_MaskActionAfterFound() {
//				@Override
//				public void doMaskSettingsAfterValueWrittenInMaskField(String cMaskValue,
//						MyE2_DB_MaskSearchField oSearchField, boolean bAfterAction,
//						boolean bIS_PrimaryCall) throws myException {
//					if (bAfterAction && S.isFull(cMaskValue) && S.isEmpty(FSMS_MASK_ComponentMAP.this.get_cActualDBValueFormated(_DB.MAT_SPEZ$BEZEICHNUNG)))
//					{
//						RECORD_ARTIKEL  recArtikel = new RECORD_ARTIKEL(bibALL.ReplaceTeilString(cMaskValue, ".", ""));
//						FSMS_MASK_ComponentMAP.this.set_cActualDatabaseValueToMask(_DB.MAT_SPEZ$BEZEICHNUNG, recArtikel.get_ARTBEZ1_cUF_NN(""));
//					}
//				}
//			});
//		}
//		
//		
//		/*
//		 * button als addon in der suchkomponente
//		 */
//		private class ownAddonButtonShow_EK_VK_Sorten extends MyE2_Button
//		{
//
//			public ownAddonButtonShow_EK_VK_Sorten() {
//				super("Gelistet");
//				this.setToolTipText(new MyE2_String("Zeigt die beim Kunden gelisteten Sorten an (EK- und VK-Sorten)").CTrans());
//				
//				this.add_oActionAgent(new XX_ActionAgent() {
//					@Override
//					public void executeAgentCode(ExecINFO oExecInfo) throws myException {
//						
//						new ownPopup();
//					}
//				});
//			}
//			
//		}
//		
//		
//		
//		private class ownPopup extends E2_BasicModuleContainer	{
//			public ownPopup() throws myException {
//				super();
//
//				//beschaffen der adress-id
//				String cID_Adresse = FSMS_MASK_ComponentMAP.this.get_oSQLFieldMAP().get_hmRestrictionFieldValues().get(_DB.MAT_SPEZ$ID_ADRESSE);
//				
//				RECORD_ADRESSE  			recAdress = 	new RECORD_ADRESSE(cID_Adresse);
//				RECLIST_ARTIKELBEZ_LIEF  	rlArtbezLief = recAdress.get_DOWN_RECORD_LIST_ARTIKELBEZ_LIEF_id_adresse();
//				
//				VectorSortenSingle   	vEK_Sorten = new VectorSortenSingle();
//				VectorSortenSingle   	vVK_Sorten = new VectorSortenSingle();
//				
//				for (RECORD_ARTIKELBEZ_LIEF recArtbezLief: rlArtbezLief.values())
//				{
//					if (recArtbezLief.get_ARTBEZ_TYP_cUF_NN("").equals("EK")) {
//						vEK_Sorten.add_sorte(recArtbezLief.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel());
//					} else {
//						vVK_Sorten.add_sorte(recArtbezLief.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez().get_UP_RECORD_ARTIKEL_id_artikel());
//					}
//				}
//				
//				Collections.sort(vEK_Sorten, new Comparator<RECORD_ARTIKEL>() {
//					@Override
//					public int compare(RECORD_ARTIKEL o1, RECORD_ARTIKEL o2) {
//						try {
//							return o1.get_ANR1_cUF_NN("").compareTo(o2.get_ANR1_cUF_NN(""));
//						} catch (myException e) {
//							e.printStackTrace();
//							return 0;
//						}
//					}
//				});
//				
//				Collections.sort(vVK_Sorten, new Comparator<RECORD_ARTIKEL>() {
//					@Override
//					public int compare(RECORD_ARTIKEL o1, RECORD_ARTIKEL o2) {
//						try {
//							return o1.get_ANR1_cUF_NN("").compareTo(o2.get_ANR1_cUF_NN(""));
//						} catch (myException e) {
//							e.printStackTrace();
//							return 0;
//						}
//					}
//				});
//			
//				
//				MyE2_Grid oGridAnzeige = new MyE2_Grid(3, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
//				
//				GridLayoutData  oGL_Titel = 	MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_0_2_2_2, new E2_ColorDDark(), 1, 1);
//				GridLayoutData  oGL_TitelR = 	MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_0_2_0_2, new E2_ColorDDark(), 1, 1);
//				
//				GridLayoutData  oGL_Body = 		MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_0_2_2_2, new E2_ColorBase(), 1, 1);
//				GridLayoutData  oGL_BodyR = 	MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I_0_2_0_2, new E2_ColorBase(), 1, 1);
//				 
//				oGridAnzeige.add(new MyE2_Label(new MyE2_String("Typ")), oGL_Titel);
//				oGridAnzeige.add(new MyE2_Label(new MyE2_String("ANR1")), oGL_Titel);
//				oGridAnzeige.add(new MyE2_Label(new MyE2_String("Artikelbezeichnung")), oGL_TitelR);
//				
//				for (RECORD_ARTIKEL recArt: vEK_Sorten)
//				{
//					oGridAnzeige.add(new ownButtonUebernahmeSorte("EK", recArt.get_ID_ARTIKEL_cUF(), "EK"), oGL_Body);
//					oGridAnzeige.add(new ownButtonUebernahmeSorte(recArt.get_ANR1_cUF_NN("<anr1>"), recArt.get_ID_ARTIKEL_cUF(), "EK"), oGL_Body);
//					oGridAnzeige.add(new ownButtonUebernahmeSorte(recArt.get_ARTBEZ1_cUF_NN("<artbez1>"), recArt.get_ID_ARTIKEL_cUF(), "EK"), oGL_BodyR);
//				}
//				
//				for (RECORD_ARTIKEL recArt: vVK_Sorten)
//				{
//					oGridAnzeige.add(new ownButtonUebernahmeSorte("VK", recArt.get_ID_ARTIKEL_cUF(), "VK"), oGL_Body);
//					oGridAnzeige.add(new ownButtonUebernahmeSorte(recArt.get_ANR1_cUF_NN("<anr1>"), recArt.get_ID_ARTIKEL_cUF(), "VK"), oGL_Body);
//					oGridAnzeige.add(new ownButtonUebernahmeSorte(recArt.get_ARTBEZ1_cUF_NN("<artbez1>"), recArt.get_ID_ARTIKEL_cUF(), "VK"), oGL_BodyR);
//				}
//			
//				this.add(oGridAnzeige, E2_INSETS.I_2_2_2_2);
//				
//				this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(500), new Extent(400), new MyE2_String("Gelistete Sorten der Firma ..."));
//				
//			}
//			
//			
//			/**
//			 * button fuer die uebernahme der suchergebnisse in das feld (aus der popup-liste)
//			 * @author martin
//			 *
//			 */
//			private class ownButtonUebernahmeSorte extends MyE2_Button {
//
//				public ownButtonUebernahmeSorte(String cText, String cID_ARTIKEL, String cEK_VK) {
//					super(cText, MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder());
//					this.EXT().set_C_MERKMAL(cID_ARTIKEL);
//					this.EXT().set_C_MERKMAL2(cEK_VK);
//					this.add_oActionAgent(new ownActionAgent());
//					this.setToolTipText(new MyE2_String("Übernahme der Sorte in das Suchfeld").CTrans());
//				}
//				
//				private class ownActionAgent extends XX_ActionAgent	{
//					@Override
//					public void executeAgentCode(ExecINFO oExecInfo) throws myException {
//						String cID_ARTIKEL=	ownButtonUebernahmeSorte.this.EXT().get_C_MERKMAL();
//						String cEK_VK=		ownButtonUebernahmeSorte.this.EXT().get_C_MERKMAL2();
//						
//						DB_SEARCH_Sorte oSearchField = ownSearchSorteButton.this;
//						oSearchField.set_cActualMaskValue(cID_ARTIKEL, true, true, true);
//						
//						if (cEK_VK.equals("EK")){
//							FSMS_MASK_ComponentMAP.this.set_cActualDatabaseValueToMask(_DB.MAT_SPEZ$IST_LIEFERANT, "Y");
//							FSMS_MASK_ComponentMAP.this.set_cActualDatabaseValueToMask(_DB.MAT_SPEZ$IST_ABNEHMER, "N");
//							
//						} else {
//							FSMS_MASK_ComponentMAP.this.set_cActualDatabaseValueToMask(_DB.MAT_SPEZ$IST_ABNEHMER, "Y");
//							FSMS_MASK_ComponentMAP.this.set_cActualDatabaseValueToMask(_DB.MAT_SPEZ$IST_LIEFERANT, "N");
//						}
//
//						ownPopup.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
//						
//					}
//				}
//			}
//
//		}
//		
//		
//		private class VectorSortenSingle extends Vector<RECORD_ARTIKEL>  {
//			public void add_sorte(RECORD_ARTIKEL recArtikel) throws myException {
//				boolean bereitsDa = false;
//				
//				for (RECORD_ARTIKEL recArt: this) 	{
//					if (recArt.get_ID_ARTIKEL_cUF().equals(recArtikel.get_ID_ARTIKEL_cUF())) {
//						bereitsDa = true;
//					}
//				}
//				if (!bereitsDa)	{
//					this.add(recArtikel);
//				}
//			}
//		}
//		
//	}
//
	

	
	
	
}
