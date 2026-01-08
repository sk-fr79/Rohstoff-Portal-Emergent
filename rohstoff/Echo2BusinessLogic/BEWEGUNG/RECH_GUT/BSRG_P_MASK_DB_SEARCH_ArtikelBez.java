package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_MaskInfo;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MaskSearchField.MyE2_DB_MaskSearchField;
import panter.gmbh.Echo2.components.DB.MaskSearchField.XX_MaskActionAfterFound;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARTIKELBEZ_LIEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKELBEZ_LIEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_Sel_Zahlungsbedingungen;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARTBEZ_LIEF_extend;
import rohstoff.utils.ECHO2.DB_SEARCH_ArtikelBez;

public class BSRG_P_MASK_DB_SEARCH_ArtikelBez extends DB_SEARCH_ArtikelBez 
{
	
	SQLFieldMAP   oSQLFieldMAP = null;
	
	public BSRG_P_MASK_DB_SEARCH_ArtikelBez(SQLFieldMAP oFM, Insets INSETS_For_Components) throws myException 
	{
		super(oFM.get_("ID_ARTIKEL_BEZ"), INSETS_For_Components, null, null, null, null,true,null);
		
		this.oSQLFieldMAP = oFM;
		
		//this.set_oTell_Adress_ID(new ownTell_Me_The_adresse_id());
		
		this.set_oMaskActionAfterMaskValueIsFound(new ownMaskActionAfterFound());
		this.set_bTextForAnzeigeVisible(false);
	}

	



	/*
	 * mask-setting-agent fuer das laden der artikel in die maske
	 */
	private class ownMaskActionAfterFound extends XX_MaskActionAfterFound
	{
		public void doMaskSettingsAfterValueWrittenInMaskField(String ID_ArtikelBez, MyE2_DB_MaskSearchField oSearchField, boolean bAfterAction, boolean bIS_PrimaryCall) 
						throws myException 
		{
			if (bAfterAction)
			{
				String cID_ADRESSE = null;
				
				E2_RecursiveSearch_MaskInfo 	oMaskInfo = new E2_RecursiveSearch_MaskInfo(BSRG_P_MASK_DB_SEARCH_ArtikelBez.this);
				
				//unterscheiden, ob das ganze aus den freien positionen oder aus der kopf-pos-beziehung stammt
				if (BSRG_P_MASK_DB_SEARCH_ArtikelBez.this.oSQLFieldMAP.get_("ID_VKOPF_RG") instanceof SQLFieldForRestrictTableRange)    //dann kopf-pos-beziehung
				{

					try    //kann sein, dass es keine artikelbezeichnung-lief gibt
					{
						cID_ADRESSE = 			new SearchAdressID().get_cID_ADRESS();
						this.CheckAndFillArtbezLief(ID_ArtikelBez, cID_ADRESSE, oMaskInfo);
					}
					catch (myException ex) {}
				}
				else
				{
					// hier freie positionen
					//dann nachsehen, ob der lieferant schon geladen wurde, sonst fehlermeldung
					BSRG_P_MASK_DB_SEARCH_Adresse oSearchADRESSE = (BSRG_P_MASK_DB_SEARCH_Adresse)BSRG_P_MASK_DB_SEARCH_ArtikelBez.this.EXT().get_oComponentMAP().get__Comp("ID_ADRESSE");
					cID_ADRESSE = bibALL.ReplaceTeilString(S.NN(oSearchADRESSE.get_cActualDBValueFormated()),".","");
					if (S.isEmpty(cID_ADRESSE))
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte zuerst die Kunden/Lieferanten-Adresse laden !"));
						BSRG_P_MASK_DB_SEARCH_ArtikelBez.this.prepare_ContentForNew(false);
						return ;
					}
					this.CheckAndFillArtbezLief(ID_ArtikelBez, cID_ADRESSE, oMaskInfo);
				}
			}
		}
		
		private void CheckAndFillArtbezLief(String cID_Artbez, String cID_ADRESSE,E2_RecursiveSearch_MaskInfo 	oMaskInfo) throws myException
		{
			RECORD_ARTIKEL_BEZ recArtikelBEZ = new RECORD_ARTIKEL_BEZ(cID_Artbez);
			oMaskInfo.get_DBComponent("ANR1", 				"JT_VPOS_RG").set_cActualMaskValue(recArtikelBEZ.get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cUF_NN(""));
			oMaskInfo.get_DBComponent("ANR2", 				"JT_VPOS_RG").set_cActualMaskValue(recArtikelBEZ.get_ANR2_cUF_NN(""));
			oMaskInfo.get_DBComponent("ARTBEZ1", 			"JT_VPOS_RG").set_cActualMaskValue(recArtikelBEZ.get_ARTBEZ1_cUF_NN(""));
			oMaskInfo.get_DBComponent("ARTBEZ2", 			"JT_VPOS_RG").set_cActualMaskValue(recArtikelBEZ.get_ARTBEZ2_cUF_NN(""));
			oMaskInfo.get_DBComponent("ID_ARTIKEL", 		"JT_VPOS_RG").set_cActualMaskValue(recArtikelBEZ.get_ID_ARTIKEL_cUF());
			oMaskInfo.get_DBComponent("EINHEITKURZ", 		"JT_VPOS_RG").set_cActualMaskValue(recArtikelBEZ.get_UP_RECORD_ARTIKEL_id_artikel().get_UP_RECORD_EINHEIT_id_einheit().get_EINHEITKURZ_cUF_NN(""));
			oMaskInfo.get_DBComponent("EINHEIT_PREIS_KURZ", "JT_VPOS_RG").set_cActualMaskValue(	recArtikelBEZ.get_UP_RECORD_ARTIKEL_id_artikel().get_UP_RECORD_EINHEIT_id_einheit_preis()!=null ?
																								recArtikelBEZ.get_UP_RECORD_ARTIKEL_id_artikel().get_UP_RECORD_EINHEIT_id_einheit_preis().get_EINHEITKURZ_cUF_NN(""):
																								recArtikelBEZ.get_UP_RECORD_ARTIKEL_id_artikel().get_UP_RECORD_EINHEIT_id_einheit().get_EINHEITKURZ_cUF_NN(""));
			oMaskInfo.get_DBComponent("MENGENDIVISOR", 		"JT_VPOS_RG").set_cActualMaskValue(recArtikelBEZ.get_UP_RECORD_ARTIKEL_id_artikel().get_MENGENDIVISOR_cUF_NN(""));
			
			
			//jetzt nachsehen, ob es eine ARTIKEL_BEZ_LIEF - eintragung dazu gibt
			RECLIST_ARTIKELBEZ_LIEF  rlArtbezLief = new RECLIST_ARTIKELBEZ_LIEF(
					"SELECT * FROM "+bibE2.cTO()+".JT_ARTIKELBEZ_LIEF WHERE ID_ARTIKEL_BEZ="+cID_Artbez+
																	" AND ID_ADRESSE="+cID_ADRESSE);
			
			//falls einer oder mehrere gefunden, dann den ersten
			RECORD_ARTIKELBEZ_LIEF recArtbez_lief = null;
			if (rlArtbezLief.get_vKeyValues().size()>0) 	recArtbez_lief = rlArtbezLief.get(0);

			//2012-05-16: zahlungsbedingungen komplett laden

			// falls es in den JT_ARTIKELBEZ_LIEF eigene gibt, dann diese nehmen
			//ALT//  if (recArtbez_lief!=null && S.isFull(recArtbez_lief.get_ZAHLUNGSBEDINGUNGEN_cUF_NN("")))
			//ALT//  	oMaskInfo.get_DBComponent("ZAHLUNGSBEDINGUNGEN","JT_VPOS_RG").set_cActualMaskValue(recArtbez_lief.get_ZAHLUNGSBEDINGUNGEN_cUF_NN(""));
			if (recArtbez_lief!=null)
			{
				if (recArtbez_lief.get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen()!=null)
				{
					E2_ComponentMAP 					oMap = 		BSRG_P_MASK_DB_SEARCH_ArtikelBez.this.EXT().get_oComponentMAP();
					
					//den drop-down-wert setzen 
					((MyE2IF__DB_Component)oMap.get("ID_ZAHLUNGSBEDINGUNGEN")).set_cActualMaskValue(recArtbez_lief.get_ID_ZAHLUNGSBEDINGUNGEN_cF_NN(""));
					//und die werte nachladen
					BS_Sel_Zahlungsbedingungen.LeseID_Zahlungsbedingung_und_setze_korrellierendeFelder(oMap);
				}
			}
			//2012-05-16: zahlungsbedingung neu setzen
			
			
			
			if (recArtbez_lief!=null && S.isFull(recArtbez_lief.get_LIEFERBEDINGUNGEN_cUF_NN("")))
				oMaskInfo.get_DBComponent("LIEFERBEDINGUNGEN","JT_VPOS_RG").set_cActualMaskValue(recArtbez_lief.get_LIEFERBEDINGUNGEN_cUF_NN(""));
			
			if (recArtbez_lief!=null)
				oMaskInfo.get_DBComponent("ARTBEZ2","JT_VPOS_RG").set_cActualMaskValue(new RECORD_ARTBEZ_LIEF_extend(recArtbez_lief).get_ARTBEZ_2_Incl_Specials());

		}
		
	}

	
	private class SearchAdressID 
	{
		private String cID_ADRESS = null;

		public SearchAdressID() throws myException
		{
			
			BSRG_P_MASK_DB_SEARCH_ArtikelBez oThis = BSRG_P_MASK_DB_SEARCH_ArtikelBez.this;
			String cID_TEST = ((SQLFieldForRestrictTableRange) oThis.oSQLFieldMAP.get_("ID_VKOPF_RG")).get_cRestictionValue_IN_DB_FORMAT();
			
			String cID__Test = bibALL.ReplaceTeilString(cID_TEST,".","");
			if (bibALL.isInteger(cID__Test))
			{
				// dann die kunden-spezifischen artikelbezeichungnen
				String cSQL = "SELECT ID_ADRESSE FROM "+bibE2.cTO()+".JT_VKOPF_RG WHERE ID_VKOPF_RG="+cID__Test;
				
				String[][] cErgebnis = bibDB.EinzelAbfrageInArray(cSQL);
				if (cErgebnis != null && cErgebnis.length==1)
				{
					this.cID_ADRESS= cErgebnis[0][0];
				}
			}
		}
		public String get_cID_ADRESS() 
		{
			return cID_ADRESS;
		}
	}
	
	
	
//	private class ownTell_Me_The_adresse_id extends XX_Tell_me_the_actual_adress_id
//	{
//
//		@Override
//		public String get_unformated_Adress_ID_or_null() throws myException 
//		{
//			
//			String cID_ADRESSE = null;
//			
//			//unterscheiden, ob das ganze aus den freien positionen oder aus der kopf-pos-beziehung stammt
//			if (BSRG_P_MASK_DB_SEARCH_ArtikelBez.this.oSQLFieldMAP.get_("ID_VKOPF_RG") instanceof SQLFieldForRestrictTableRange)    //dann kopf-pos-beziehung
//			{
//
//				try    //kann sein, dass es keine artikelbezeichnung-lief gibt
//				{
//					cID_ADRESSE = 			new SearchAdressID().get_cID_ADRESS();
//				}
//				catch (myException ex) {}
//			}
//			else
//			{
//				// hier freie positionen
//				//dann nachsehen, ob der lieferant schon geladen wurde, sonst fehlermeldung
//				BSRG_P_MASK_DB_SEARCH_Adresse oSearchADRESSE = (BSRG_P_MASK_DB_SEARCH_Adresse)BSRG_P_MASK_DB_SEARCH_ArtikelBez.this.EXT().get_oComponentMAP().get__Comp("ID_ADRESSE");
//				cID_ADRESSE = bibALL.ReplaceTeilString(S.NN(oSearchADRESSE.get_cActualDBValueFormated()),".","");
//				if (S.isEmpty(cID_ADRESSE))
//				{
//					cID_ADRESSE=null;;
//				}
//			}
//			return cID_ADRESSE;
//		}
//		
//	}



}
