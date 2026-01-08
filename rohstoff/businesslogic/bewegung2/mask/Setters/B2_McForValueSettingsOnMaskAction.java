/**
 * rohstoff.businesslogic.bewegung2.mask.Setters
 * @author martin
 * @date 09.01.2020
 * 
 */
package rohstoff.businesslogic.bewegung2.mask.Setters;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Vector;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import panter.gmbh.BasicInterfaces.Service.PdServiceErmittleSteuerAlgorithmisch;
import panter.gmbh.BasicInterfaces.Service.PdServiceFindAVVCodeV2;
import panter.gmbh.BasicInterfaces.Service.PdServiceFindLkzUstId;
import panter.gmbh.BasicInterfaces.Service.PdServiceReadProformaSteuerEintrag;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_BASIC_InfoMessageWithAddonComponent;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.ZUSATZFELDER.__RECORD_MANDANT_ZUSATZ;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.basics4project.ENUM_MANDANT_SESSION_STORE;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.EAK_CODE;
import panter.gmbh.basics4project.DB_ENUMS.HANDELSDEF;
import panter.gmbh.basics4project.DB_ENUMS.LIEFERBEDINGUNGEN;
import panter.gmbh.basics4project.DB_ENUMS.TAX;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_STD;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_STD;
import panter.gmbh.basics4project.DB_ENUMS.WAEHRUNG;
import panter.gmbh.basics4project.DB_ENUMS.ZAHLUNGSBEDINGUNGEN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.O;
import panter.gmbh.indep.Pair;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.HMAP;
import panter.gmbh.indep.myVectors.VEK;
import panter.gmbh.json.EnumJsonFileNames;
import panter.gmbh.json.reader.JsonMaskTransportArtSettingReader;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_SONDERLAGER;
import rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG.HD_ErmittlePassendeHandelsDef;
import rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG.HD_FehlerBerichte;
import rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG.HD_PopupZeigeFuhreneinstufung;
import rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG.HD_WarenBewegungEinstufung;
import rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG.HD_WarenBewegungEinstufungen;
import rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG._HD_Station_BgVektor;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_Kommunikation;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_VKopfKon;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_VKopfStd;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_VPosKon;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_VPosStd;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_artikel_bez;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_tax;
import rohstoff.businesslogic.bewegung2.global.EnBesitzerTyp;
import rohstoff.businesslogic.bewegung2.global.EnPositionStation;
import rohstoff.businesslogic.bewegung2.global.EnTpVerantwortung;
import rohstoff.businesslogic.bewegung2.global.EnTransportTyp;
import rohstoff.businesslogic.bewegung2.recs.RecA1;
import rohstoff.businesslogic.bewegung2.recs.RecA2;
import rohstoff.businesslogic.bewegung2.recs.RecS1;
import rohstoff.businesslogic.bewegung2.recs.RecS2;
import rohstoff.businesslogic.bewegung2.recs.RecS3;
import rohstoff.businesslogic.bewegung2.recs.RecV;

/**
 * @author martin
 * @date 09.01.2020
 *
 */
public class B2_McForValueSettingsOnMaskAction extends B2_MaskController {

	
	
//	private IF_RB_Component initalComponent = null;;
//	private RB_ComponentMap initalComponentMap = null;


	/**
	 * @author martin
	 * @date 09.01.2020
	 *
	 * @param p_component
	 * @throws myException
	 */
	public B2_McForValueSettingsOnMaskAction(IF_RB_Component p_component) throws myException {
		super(p_component);
//		this.initalComponent = p_component;
	}

	/**
	 * @author martin
	 * @date 09.01.2020
	 *
	 * @param p_componentMap
	 * @throws myException
	 */
	public B2_McForValueSettingsOnMaskAction(RB_ComponentMap p_componentMap) throws myException {
		super(p_componentMap);
//		this.initalComponentMap = p_componentMap;
	}

	
//	public B2_McForValueSettingsOnMaskAction(IF_RB_Component p_component, MyE2_MessageVector mv) {
//		super(p_component, mv);
//	}

	public B2_McForValueSettingsOnMaskAction(RB_ComponentMap p_componentMap, MyE2_MessageVector mv) {
		super(p_componentMap, mv);
	}

	
	
	public B2_MaskController _setMaskValuesAfterAdressIsSet(EnPositionStation pos) {
		try {
			this._refresh();
			
			RB_KM keyStation = 	(pos==EnPositionStation.LEFT?RecS1.key:RecS3.key);
			RB_KM keyAtom = 	(pos==EnPositionStation.LEFT?RecA1.key:RecA2.key);
			
			this.set_maskVal(keyStation, BG_STATION.id_adresse_basis,	"", bibMSG.MV());
			this.set_maskVal(keyStation, BG_STATION.id_land.fk(),			"", bibMSG.MV());
			this.set_maskVal(keyStation, BG_STATION.name1.fk(),				"", bibMSG.MV());
			this.set_maskVal(keyStation, BG_STATION.name2.fk(),				"", bibMSG.MV());
			this.set_maskVal(keyStation, BG_STATION.name3.fk(),				"", bibMSG.MV());
			this.set_maskVal(keyStation, BG_STATION.strasse.fk(),			"", bibMSG.MV());
			this.set_maskVal(keyStation, BG_STATION.plz.fk(),				"", bibMSG.MV());
			this.set_maskVal(keyStation, BG_STATION.ort.fk(),				"", bibMSG.MV());
			this.set_maskVal(keyStation, BG_STATION.ortzusatz.fk(),			"", bibMSG.MV());
			this.set_maskVal(keyStation, BG_STATION.oeffnungszeiten.fk(),	"", bibMSG.MV());
			
			this.set_maskVal(keyStation, BG_STATION.umsatzsteuerid.fk(),	"", bibMSG.MV());
			this.set_maskVal(keyStation, BG_STATION.umsatzsteuerlkz.fk(),	"", bibMSG.MV());
			this.set_maskVal(keyStation, BG_STATION.telefon.fk(),			"", bibMSG.MV());
			this.set_maskVal(keyStation, BG_STATION.fax.fk(),				"", bibMSG.MV());
		
//			this.set_maskVal(keyAtom, 	 BG_ATOM.id_zahlungsbedingungen.fk(),"", bibMSG.MV());
//			this.set_maskVal(keyAtom, 	 BG_ATOM.zahlungsbedingungen.fk(),	"", bibMSG.MV());
			this.set_maskVal(keyAtom, 	 BG_ATOM.id_eak_code.fk(),			"", bibMSG.MV());
			this.set_maskVal(keyAtom,    BG_ATOM.id_waehrung,				"", bibMSG.MV());
			this.set_maskVal(keyAtom,    BG_ATOM.waehrungskurs,				"", bibMSG.MV());
			
			
			Rec21_adresse station = (pos==EnPositionStation.LEFT?this.getStartAdresse():this.getZielAdresse());
			if (station != null) {
				Rec21_adresse adresseHpt   = station._getMainAdresse();
		
				this.set_maskVal(keyStation, BG_STATION.id_adresse_basis.fk(),	adresseHpt.getIdLong().toString(), bibMSG.MV());
				this.set_maskVal(keyStation, BG_STATION.id_land.fk(),			station.getFs(ADRESSE.id_land), bibMSG.MV());
				this.set_maskVal(keyStation, BG_STATION.name1.fk(),				station.getUfs(ADRESSE.name1), bibMSG.MV());
				this.set_maskVal(keyStation, BG_STATION.name2.fk(),				station.getUfs(ADRESSE.name2,""), bibMSG.MV());
				this.set_maskVal(keyStation, BG_STATION.name3.fk(),				station.getUfs(ADRESSE.name3,""), bibMSG.MV());
				this.set_maskVal(keyStation, BG_STATION.strasse.fk(),			station.getUfs(ADRESSE.strasse,""), bibMSG.MV());
				this.set_maskVal(keyStation, BG_STATION.plz.fk(),				station.getUfs(ADRESSE.plz,""), bibMSG.MV());
				this.set_maskVal(keyStation, BG_STATION.ort.fk(),				station.getUfs(ADRESSE.ort,""), bibMSG.MV());
				this.set_maskVal(keyStation, BG_STATION.ortzusatz.fk(),			station.getUfs(ADRESSE.ortzusatz,""), bibMSG.MV());
				this.set_maskVal(keyStation, BG_STATION.oeffnungszeiten.fk(),	station.getOeffnungszeiten(), bibMSG.MV());
				
				Rec21_Kommunikation tel = station.getStandardTelefonNummerLagerOrMain();
				Rec21_Kommunikation fax = station.getStandardTelefaxNummerLagerOrMain();
				if (tel!=null) {
					this.set_maskVal(keyStation, BG_STATION.telefon.fk(),		tel.getTelefonNummerGanz(),bibMSG.MV());
				}
				if (fax != null) {
					this.set_maskVal(keyStation, BG_STATION.fax.fk(),		 	fax.getTelefonNummerGanz(),bibMSG.MV());
				}
				
				Pair<String> lkzId = new PdServiceFindLkzUstId().getUstLkzUstID(station, this.getStartAdresse(), this.getZielAdresse()) ;
				
				if (lkzId!=null) {
					this.set_maskVal(keyStation, BG_STATION.umsatzsteuerlkz.fk(),	lkzId.getVal1(),bibMSG.MV());
					this.set_maskVal(keyStation, BG_STATION.umsatzsteuerid.fk(),	lkzId.getVal2(),bibMSG.MV());
				}
					
				
				Rec21 waehrung = adresseHpt.getWaehrung();
				if (waehrung!=null) {
					this.set_maskVal(keyAtom,  BG_ATOM.id_waehrung.fk(),waehrung.getFs(WAEHRUNG.id_waehrung), bibMSG.MV());
					Rec21 waehrungMandant = ((Rec21)ENUM_MANDANT_SESSION_STORE.REC21_MANDANT.getValueFromSession()).get_up_Rec21(WAEHRUNG.id_waehrung);
					if (waehrung.getId()==waehrungMandant.getId()) {
						this.set_maskVal(keyAtom,  BG_ATOM.waehrungskurs.fk(),"1", bibMSG.MV());
					} else {
						this.set_maskVal(keyAtom,  BG_ATOM.waehrungskurs.fk(),"", bibMSG.MV());   //kurs muss zwingend eingestellt werden
					}
				}
			}
			
		} catch (myException e) {
			e.printStackTrace();
			bibMSG.add_MESSAGE(e);
		} catch (Exception e) {
			bibMSG.MV()._addAlarm(e.getLocalizedMessage());
			e.printStackTrace();
		}
		return this;
	}
	

	

	
	
	/**
	 * werte, die gesetzt werden, wenn eine transportart ausgewaehlt wird
	 * @author martin
	 * @date 14.01.2020
	 *
	 * @return
	 */
	public B2_MaskController _setMaskValuesWithTransportArt() {
		try {
			EnTransportTyp art = EnTransportTyp.WA.getEnum(this.get_liveVal(RecV.key, BG_VEKTOR.en_transport_typ));

			VEK<RB_KM> keysIterate = new VEK<RB_KM>()._a(RecV.key,RecA1.key,RecA2.key,RecS1.key,RecS2.key,RecS3.key);
			JsonMaskTransportArtSettingReader helper = null;
			try {
				helper = new JsonMaskTransportArtSettingReader();
			} catch (Exception e) {
				e.printStackTrace();
				bibMSG.MV()._addWarn("Error reading mask-setting-json-file: "+EnumJsonFileNames.B2MaskTransportArtSetting.getFileName()+", "+e.getLocalizedMessage());
			}

			
			if (art != null) {
				
				this._clearTaxFields();

				
				
				//zuerst die steuerdatei anwenden
				//jetzt den json-definierer noch loslassen
				if (helper!=null) {
					for (RB_KM k: keysIterate) {
						if (helper.getFieldsToEnable(art,k)!=null) {
							this._clearFields(k, helper.getFieldsToClear(art,k));
						}
						HMAP<IF_Field, String> values = helper.getValuesToSet(art, k);
						if (values!=null) {
							for (IF_Field f: values.keySet()) {
								this._setMaskValue(k, f, values.get(f));
							}
						}
					}
				}				
				
				
				//dann die sonderfaelle
				
				switch (art) {
				case WA:

					if (this.isZielAdressFullAndOwn()) {
						this.getRbComp(RecS3.key, BG_STATION.id_adresse, bibMSG.MV()).rb_set_db_value_manual("");
						this._setMaskValuesAfterAdressIsSet(EnPositionStation.RIGHT);
					} 
					
					if (this.isStartAdressFullAndNotOwn()) {
						this.getRbComp(RecS1.key, BG_STATION.id_adresse, bibMSG.MV()).rb_set_db_value_manual("");
						this._setMaskValuesAfterAdressIsSet(EnPositionStation.LEFT);
					} 
					
					this._setProformaSteuerFieldsOnMandantSite(EnPositionStation.LEFT);
					
					
					break;

					
				case WA_L:    //verkauf ware, verbleibt im fremdlager
					
					
					if (this.isStartAdressFullAndNotOwn()) {
						this.getRbComp(RecS1.key, BG_STATION.id_adresse, bibMSG.MV()).rb_set_db_value_manual("");
						this._setMaskValuesAfterAdressIsSet(EnPositionStation.LEFT);
					} 
					if (this.isZielAdressFullAndNotOwn()) {
						this.getRbComp(RecS3.key, BG_STATION.id_adresse, bibMSG.MV()).rb_set_db_value_manual("");
						this._setMaskValuesAfterAdressIsSet(EnPositionStation.RIGHT);
					} 

					this._setProformaSteuerFieldsOnMandantSite(EnPositionStation.LEFT);
					
					break;
					
					
					
				case WE:
					
					if (this.isStartAdressFullAndOwn()) {
						this.getRbComp(RecS1.key, BG_STATION.id_adresse, bibMSG.MV()).rb_set_db_value_manual("");
						this._setMaskValuesAfterAdressIsSet(EnPositionStation.LEFT);
					} 
					
					if (this.isZielAdressFullAndNotOwn()) {
						this.getRbComp(RecS3.key, BG_STATION.id_adresse, bibMSG.MV()).rb_set_db_value_manual("");
						this._setMaskValuesAfterAdressIsSet(EnPositionStation.RIGHT);
					} 

					this._setProformaSteuerFieldsOnMandantSite(EnPositionStation.RIGHT);
					
					break;
	
					
					
				case WE_L:   //einkauf ware, war bereits in fremdlager

					if (this.isStartAdressFullAndNotOwn()) {
						this.getRbComp(RecS1.key, BG_STATION.id_adresse, bibMSG.MV()).rb_set_db_value_manual("");
						this._setMaskValuesAfterAdressIsSet(EnPositionStation.LEFT);
					} 
					if (this.isZielAdressFullAndNotOwn()) {
						this.getRbComp(RecS3.key, BG_STATION.id_adresse, bibMSG.MV()).rb_set_db_value_manual("");
						this._setMaskValuesAfterAdressIsSet(EnPositionStation.RIGHT);
					} 

					this._setProformaSteuerFieldsOnMandantSite(EnPositionStation.RIGHT);

					break;
					
					
				case STRECKE:
					
					if (this.isStartAdressFullAndOwn()) {
						this.getRbComp(RecS1.key, BG_STATION.id_adresse, bibMSG.MV()).rb_set_db_value_manual("");
						this._setMaskValuesAfterAdressIsSet(EnPositionStation.LEFT);
					} 
					if (this.isZielAdressFullAndOwn()) {
						this.getRbComp(RecS3.key, BG_STATION.id_adresse, bibMSG.MV()).rb_set_db_value_manual("");
						this._setMaskValuesAfterAdressIsSet(EnPositionStation.RIGHT);
					} 


					break;

					
				case LAGER_LAGER:
					

					if (this.isStartAdressFullAndNotOwn()) {
						this.getRbComp(RecS1.key, BG_STATION.id_adresse, bibMSG.MV()).rb_set_db_value_manual("");
						this._setMaskValuesAfterAdressIsSet(EnPositionStation.LEFT);
					} 
					if (this.isZielAdressFullAndNotOwn()) {
						this.getRbComp(RecS3.key, BG_STATION.id_adresse, bibMSG.MV()).rb_set_db_value_manual("");
						this._setMaskValuesAfterAdressIsSet(EnPositionStation.RIGHT);
					} 

					this._setProformaSteuerFieldsOnMandantSite(EnPositionStation.LEFT);
					this._setProformaSteuerFieldsOnMandantSite(EnPositionStation.RIGHT);

					break;

				case LEERGUTRANSPORT: 

					this._setProformaSteuerFieldsOnMandantSite(EnPositionStation.LEFT);
					this._setProformaSteuerFieldsOnMandantSite(EnPositionStation.RIGHT);

					break;
					
					
				case TESTSTELLUNG: 

					break;
					
				case FREMDWARENTRANSPORT: 
					
					
					this._setProformaSteuerFieldsOnMandantSite(EnPositionStation.LEFT);
					this._setProformaSteuerFieldsOnMandantSite(EnPositionStation.RIGHT);
					
					break;
					
					
				case EINBUCHUNG:

					this._setProformaSteuerFieldsOnMandantSite(EnPositionStation.LEFT);
					this._setProformaSteuerFieldsOnMandantSite(EnPositionStation.RIGHT);

					this._setMaskValue(RecS1.key, BG_STATION.id_adresse_basis, 	((Rec21)ENUM_MANDANT_SESSION_STORE.MANDANTEN_ADRESS_REC21.getValueFromSession()).getUfs(ADRESSE.id_adresse));
					this._setMaskValue(RecS1.key, BG_STATION.id_adresse, 		this.getSonderlager(ENUM_SONDERLAGER.OFFLG_EIN).getIdLong().toString());
					this._setMaskValue(RecS1.key, BG_STATION.name1, 			this.getSonderlager(ENUM_SONDERLAGER.OFFLG_EIN).getUfs(ADRESSE.name1));
					this._setMaskValue(RecS1.key, BG_STATION.id_land, 			((Rec21)ENUM_MANDANT_SESSION_STORE.MANDANTEN_ADRESS_REC21.getValueFromSession()).getUfs(ADRESSE.id_land));
					this._setMaskValue(RecS1.key, BG_STATION.plz, 		    	((Rec21)ENUM_MANDANT_SESSION_STORE.MANDANTEN_ADRESS_REC21.getValueFromSession()).getUfs(ADRESSE.plz));
					this.setzeFeldWerteEinAusUmbuchung();

					
					break;
					
				case AUSBUCHUNG:
					
					this._setProformaSteuerFieldsOnMandantSite(EnPositionStation.LEFT);
					this._setProformaSteuerFieldsOnMandantSite(EnPositionStation.RIGHT);

					this._setMaskValue(RecS3.key, BG_STATION.id_adresse_basis, 	((Rec21)ENUM_MANDANT_SESSION_STORE.MANDANTEN_ADRESS_REC21.getValueFromSession()).getUfs(ADRESSE.id_adresse));
					this._setMaskValue(RecS3.key, BG_STATION.id_adresse, 	this.getSonderlager(ENUM_SONDERLAGER.OFFLG_AUS).getIdLong().toString());
					this._setMaskValue(RecS3.key, BG_STATION.name1, 		this.getSonderlager(ENUM_SONDERLAGER.OFFLG_AUS).getUfs(ADRESSE.name1));
					this._setMaskValue(RecS3.key, BG_STATION.id_land, 		((Rec21)ENUM_MANDANT_SESSION_STORE.MANDANTEN_ADRESS_REC21.getValueFromSession()).getUfs(ADRESSE.id_land));
					this._setMaskValue(RecS3.key, BG_STATION.plz, 		    ((Rec21)ENUM_MANDANT_SESSION_STORE.MANDANTEN_ADRESS_REC21.getValueFromSession()).getUfs(ADRESSE.plz));
					this.setzeFeldWerteEinAusUmbuchung();

					break;
					
				case UMBUCHUNG:
					
					this.setzeFeldWerteEinAusUmbuchung();
					
					break;
				default:
					this.getRbComp(RecA1.key, BG_ATOM.kontraktzwang, bibMSG.MV()).rb_set_db_value_manual("N");
					this.getRbComp(RecA2.key, BG_ATOM.kontraktzwang, bibMSG.MV()).rb_set_db_value_manual("N");

					this._setProformaSteuerFieldsOnMandantSite(EnPositionStation.LEFT);
					this._setProformaSteuerFieldsOnMandantSite(EnPositionStation.RIGHT);

					break;
				}
				
				//wenn keine fremdware benoetigt wird, dann das feld loeschen
				if (! art.needsFremdbesitzer()) {
					this.getRbComp(RecV.key, BG_VEKTOR.id_adresse_fremdware, bibMSG.MV()).rb_set_db_value_manual(""); 
				}
				

				
				
			}

		} catch (myException e) {
			bibMSG.MV()._addAlarm(e.get_ErrorMessage()+"  <5dd7c2a8-5942-11ea-8e2d-0242ac130003-2>");
			e.printStackTrace();
		} catch (Exception e) {
			bibMSG.MV()._addAlarm(e.getLocalizedMessage()+"  <5dd7c2a8-5942-11ea-8e2d-0242ac130003-2>");
			e.printStackTrace();
		}

		
		return this;
	}

	private void setzeFeldWerteEinAusUmbuchung() throws Exception {
		this._setProformaSteuerFieldsOnMandantSite(EnPositionStation.LEFT);
		this._setProformaSteuerFieldsOnMandantSite(EnPositionStation.RIGHT);
		this._setMaskValue(RecV.key, BG_VEKTOR.planmenge, "0");
		this._setMaskValue(RecV.key, BG_VEKTOR.datum_planung_von, new MyDate(new Date()).get_cDateStandardFormat());
		this._setMaskValue(RecV.key, BG_VEKTOR.datum_planung_bis, new MyDate(new Date()).get_cDateStandardFormat());
		this._setMaskValue(RecV.key, BG_VEKTOR.transportverantwortung, EnTpVerantwortung.MANDANT.dbVal());
		
		this._setMaskValue(RecA1.key, BG_ATOM.id_waehrung, bibALL.get_RECORD_MANDANT().get_ID_WAEHRUNG_cUF());
		
		this._setMaskValue(RecA1.key, BG_ATOM.waehrungskurs, "1");
		this._setMaskValue(RecA1.key, BG_ATOM.datum_ausfuehrung, new MyDate(new Date()).get_cDateStandardFormat());

		this._setMaskValue(RecA2.key, BG_ATOM.id_waehrung, bibALL.get_RECORD_MANDANT().get_ID_WAEHRUNG_cUF());
		this._setMaskValue(RecA2.key, BG_ATOM.waehrungskurs, "1");
		this._setMaskValue(RecA2.key, BG_ATOM.datum_ausfuehrung, new MyDate(new Date()).get_cDateStandardFormat());
		
		this._clearFields(RecV.key,  BG_VEKTOR.ah7_ausstellung_datum
									,BG_VEKTOR.ah7_menge
									,BG_VEKTOR.ah7_volumen
									,BG_VEKTOR.print_anhang7
									,BG_VEKTOR.id_handelsdef
									);
		this._clearFields(RecA1.key,  BG_ATOM.id_bg_pruefport_gelangensbest);
		this._clearFields(RecA2.key,  BG_ATOM.id_bg_pruefport_gelangensbest);

		
	}
	
	public B2_MaskController _setMaskValueAVVCode(EnPositionStation pos) {
		try {
			
			Rec21_adresse 		station = (pos==EnPositionStation.LEFT?this.getStartAdresse():this.getZielAdresse());
			Rec21_artikel_bez	artbez =  (pos==EnPositionStation.LEFT?this.getArtbezQuelle():this.getArtbezZiel());
			IF_RB_Component     compAVV = (pos==EnPositionStation.LEFT?this.getRbComp(RecA1.key,BG_ATOM.id_eak_code, bibMSG.MV()):this.getRbComp(RecA2.key,BG_ATOM.id_eak_code, bibMSG.MV()));
			
			StringBuffer sbInfoWoher = new StringBuffer();
			
			Rec21 avvCode = new PdServiceFindAVVCodeV2().getAVVCode(station, artbez, (pos==EnPositionStation.LEFT), sbInfoWoher);
			
			if (avvCode != null && pos==EnPositionStation.LEFT) {
				compAVV.rb_set_db_value_manual(avvCode.getFs(EAK_CODE.id_eak_code));
			} else {
				compAVV.rb_set_db_value_manual("");
			}

			if (S.isFull(sbInfoWoher.toString())) {
				bibMSG.MV()._addInfo(S.ms("AVV-Code: geladen aus: "+sbInfoWoher.toString()));
			}
			
		} catch (myException e) {
			bibMSG.MV()._addAlarm(e.get_ErrorMessage()+"  <5dd7c2a8-5942-11ea-8e2d-0242ac130003-3>");
			e.printStackTrace();
		} catch (Exception e) {
			bibMSG.MV()._addAlarm(e.getLocalizedMessage()+"  <5dd7c2a8-5942-11ea-8e2d-0242ac130003-3>");
			e.printStackTrace();
		}
		
		return this;
	}
	
	
	
	public B2_MaskController _setBesitzer() {
		try {
			
			EnTransportTyp 	transportArt =  		EnTransportTyp.WE.getEnum(this.getStringMaskVal(RecV.key,BG_VEKTOR.en_transport_typ.fk()));
			Long 			idAdresseStart = 		this.getLongLiveVal(RecS1.key, BG_STATION.id_adresse.fk());
			Long 			idAdresseZiel  = 		this.getLongLiveVal(RecS3.key, BG_STATION.id_adresse.fk());
			Long			idAdresseFremdware =  	this.getLongLiveVal(RecV.key, BG_VEKTOR.id_adresse_fremdware.fk());
				
			
			
			boolean bewertbar = false;
			
			if (transportArt!=null) {
				if (transportArt.needsFremdbesitzer()) {
					bewertbar = O.isNoOneNull(idAdresseStart,idAdresseZiel,idAdresseFremdware);
				} else {
					bewertbar = O.isNoOneNull(idAdresseStart,idAdresseZiel);
				}
			}
			
			//falls in der fremdwarenadresse eine eigene adresse steht, fehler
			if (idAdresseFremdware!=null) {
				Rec21_adresse adresse = new Rec21_adresse()._fill_id(idAdresseFremdware);
				if (adresse.isAdressOfMandant()) {
					this.set_maskVal(RecS1.key, 	BG_STATION.id_adresse_besitz_ldg, null, bibMSG.MV());
					this.set_maskVal(RecS2.key, 	BG_STATION.id_adresse_besitz_ldg, null, bibMSG.MV());
					this.set_maskVal(RecS3.key, 	BG_STATION.id_adresse_besitz_ldg, null, bibMSG.MV());
					bewertbar = false;
				}
			}
			
			if (bewertbar) {
				Rec21_adresse recAdresseStart = 	new Rec21_adresse()._fill_id(idAdresseStart)._getMainAdresse();
				Rec21_adresse recAdresseZiel  = 	new Rec21_adresse()._fill_id(idAdresseZiel)._getMainAdresse();
				Rec21_adresse recAdresseMandant = 	new Rec21_adresse()._fill_id(bibALL.get_ID_ADRESS_MANDANT());
				Rec21_adresse recAdresseFremdWare = null;
				
				if (O.isNotNull(idAdresseFremdware)) {
					recAdresseFremdWare = new Rec21_adresse()._fill_id(idAdresseFremdware)._getMainAdresse();
					idAdresseFremdware = recAdresseFremdWare.getIdLong();
				}
				
				HMAP<EnBesitzerTyp, Long> map = new HMAP<EnBesitzerTyp, Long>()
							._put(EnBesitzerTyp.MANDANT, recAdresseMandant.getIdLong())
							._put(EnBesitzerTyp.QUELLFIRMA, recAdresseStart.getIdLong())
							._put(EnBesitzerTyp.ZIELFIRMA, recAdresseZiel.getIdLong())
							._put(EnBesitzerTyp.FREMDFIRMA, idAdresseFremdware)
							;

				this.set_maskVal(RecS1.key, 	BG_STATION.id_adresse_besitz_ldg, transportArt.getSBesitzerStart(map), bibMSG.MV());
				this.set_maskVal(RecS2.key, 	BG_STATION.id_adresse_besitz_ldg, transportArt.getSBesitzerMid(map), bibMSG.MV());
				this.set_maskVal(RecS3.key, 	BG_STATION.id_adresse_besitz_ldg, transportArt.getSBesitzerZiel(map), bibMSG.MV());
				

			} else {
				this.set_maskVal(RecS1.key, 	BG_STATION.id_adresse_besitz_ldg, null, bibMSG.MV());
				this.set_maskVal(RecS2.key, 	BG_STATION.id_adresse_besitz_ldg, null, bibMSG.MV());
				this.set_maskVal(RecS3.key, 	BG_STATION.id_adresse_besitz_ldg, null, bibMSG.MV());
			}


		} catch (myException e) {
			bibMSG.MV()._addAlarm(e.get_ErrorMessage()+"  <5dd7c2a8-5942-11ea-8e2d-0242ac130003-4>");
			e.printStackTrace();
		} catch (Exception e) {
			bibMSG.MV()._addAlarm(e.getLocalizedMessage()+"  <5dd7c2a8-5942-11ea-8e2d-0242ac130003-4>");
			e.printStackTrace();
		}

		
		return this;
	}
	
	
	

	
	
	
	/**
	 * @author martin
	 * @date 08.01.2020
	 *
	 * @return
	 */
	public B2_MaskController _setSortenGleichIfNeeded(EnPositionStation posSuchStation) {
		
		try {
			EnTransportTyp art = EnTransportTyp.WA.getEnum(this.get_liveVal(RecV.key, BG_VEKTOR.en_transport_typ));
			
			RB_KM keyQuelle = (posSuchStation==EnPositionStation.LEFT?RecA1.key:RecA2.key);
			RB_KM keyZiel   = (posSuchStation==EnPositionStation.LEFT?RecA2.key:RecA1.key);
			
			
			if (art != null) {
				if (art.isSortenGleichheit()) {
					Rec21_artikel_bez artikelBez = (posSuchStation==EnPositionStation.LEFT?this.getArtbezQuelle():this.getArtbezZiel());
					if (artikelBez!=null) {
						this.set_maskVal(keyZiel, BG_ATOM.id_artikel_bez,artikelBez.getFs(ARTIKEL_BEZ.id_artikel_bez), bibMSG.MV());
					} else {
						this.set_maskVal(keyZiel, BG_ATOM.id_artikel_bez,null, bibMSG.MV());
					}
				}
			}
			
			
			
		} catch (myException e) {
			bibMSG.MV()._addAlarm(e.get_ErrorMessage()+"  <5dd7c2a8-5942-11ea-8e2d-0242ac130003-6>");
			e.printStackTrace();
		} catch (Exception e) {
			bibMSG.MV()._addAlarm(e.getLocalizedMessage()+"  <5dd7c2a8-5942-11ea-8e2d-0242ac130003-6>");
			e.printStackTrace();
		}
		return this;
	}

	
	/**
	 * @author martin
	 * @date 08.01.2020
	 *
	 * @return
	 */
	public B2_MaskController _setSortenAusSortenBez() {
		
		try {
			Rec21_artikel_bez artikelBezQuelle = this.getArtbezQuelle();
			Rec21_artikel_bez artikelBezZiel = this.getArtbezZiel();
			
			this.set_maskVal(RecA1.key, BG_ATOM.id_artikel, artikelBezQuelle==null  ? "": artikelBezQuelle.getFs(ARTIKEL_BEZ.id_artikel), bibMSG.MV());
			this.set_maskVal(RecA2.key, BG_ATOM.id_artikel, artikelBezZiel==null  ?   "": artikelBezZiel.getFs(ARTIKEL_BEZ.id_artikel), bibMSG.MV());
			
		} catch (myException e) {
			bibMSG.MV()._addAlarm(e.get_ErrorMessage()+"  <5dd7c2a8-5942-11ea-8e2d-0242ac130003-7>");
			e.printStackTrace();
		} catch (Exception e) {
			bibMSG.MV()._addAlarm(e.getLocalizedMessage()+"  <5dd7c2a8-5942-11ea-8e2d-0242ac130003-7>");
			e.printStackTrace();
		}
		return this;
	}

	
	

	
	
	public B2_McForValueSettingsOnMaskAction _setArtikelDatenAfterLoad(EnPositionStation pos) {

		if (pos != null && (pos==EnPositionStation.LEFT || pos==EnPositionStation.RIGHT)) {
			RB_KM key =  (pos == EnPositionStation.LEFT ? RecA1.key : RecA2.key);
			try {
				Long idArtbez = (Long)this.getValueJustInTime(key, BG_ATOM.id_artikel_bez);
				RB_KM k = key;
				if (idArtbez != null) {
					Rec21 rec = new Rec21(_TAB.artikel_bez)._fill_id(idArtbez);
					Rec21 recA = rec.get_up_Rec21(ARTIKEL.id_artikel);
					this.set_maskVal(k, BG_ATOM.anr1, recA.getFs(ARTIKEL.anr1), bibMSG.MV());
					this.set_maskVal(k, BG_ATOM.anr2, rec.getFs(ARTIKEL_BEZ.anr2), bibMSG.MV());
					this.set_maskVal(k, BG_ATOM.artbez1, rec.getFs(ARTIKEL_BEZ.artbez1), bibMSG.MV());
					this.set_maskVal(k, BG_ATOM.artbez2, rec.getFs(ARTIKEL_BEZ.artbez2), bibMSG.MV());
					this.set_maskVal(k, BG_ATOM.id_zolltarifnummer, recA.getFs(ARTIKEL.id_zolltarifnummer), bibMSG.MV());
				} else {
					this.set_maskVal(k, BG_ATOM.anr1, "", bibMSG.MV());
					this.set_maskVal(k, BG_ATOM.anr2, "", bibMSG.MV());
					this.set_maskVal(k, BG_ATOM.artbez1, "", bibMSG.MV());
					this.set_maskVal(k, BG_ATOM.artbez2, "", bibMSG.MV());
					this.set_maskVal(k, BG_ATOM.id_zolltarifnummer, "", bibMSG.MV());
				}
			} catch (myException e) {
				bibMSG.MV()._addAlarm(e.get_ErrorMessage()+"  <5dd7c2a8-5942-11ea-8e2d-0242ac130003-8>");
				e.printStackTrace();
			} catch (Exception e) {
				bibMSG.MV()._addAlarm(e.getLocalizedMessage()+"  <5dd7c2a8-5942-11ea-8e2d-0242ac130003-8>");
				e.printStackTrace();
			}
		} else {
			bibMSG.MV()._addAlarm("System-Error: Only left or right position is allowed");
		}

		return this;
	}
	
	
	
	
	
	
	public B2_McForValueSettingsOnMaskAction _setArtikelAfterKontraktOrAngebotIsFound(EnPositionStation  position) {
		
		try {
			
			boolean quelle =  (position==EnPositionStation.LEFT);
			
			Long idVposKon = this.getLongLiveVal(quelle?RecA1.key:RecA2.key,BG_ATOM.id_vpos_kon.fk());
			Long idVposStd = this.getLongLiveVal(quelle?RecA1.key:RecA2.key,BG_ATOM.id_vpos_std.fk());
	
			if (idVposKon!=null) {
				Rec21_VPosKon vpos = (Rec21_VPosKon)new Rec21_VPosKon()._fill_id(idVposKon);
				
				if (vpos.getLongDbValue(VPOS_KON.id_artikel_bez)!=null) {
					this.set_maskVal(quelle?RecA1.key:RecA2.key, BG_ATOM.id_artikel_bez, vpos.getLongDbValue(VPOS_KON.id_artikel_bez).toString(), bibMSG.MV());
					this._setArtikelDatenAfterLoad(position);
				}
				
				
			} else if (idVposStd!=null) {
				Rec21_VPosStd  vpos = (Rec21_VPosStd)new Rec21_VPosStd()._fill_id(idVposStd);
				
				if (vpos.getLongDbValue(VPOS_STD.id_artikel_bez)!=null) {
					this.set_maskVal(quelle?RecA1.key:RecA2.key, BG_ATOM.id_artikel_bez, vpos.getLongDbValue(VPOS_STD.id_artikel_bez).toString(), bibMSG.MV());
					this._setArtikelDatenAfterLoad(position);
				}
	
				
			} else {
				//hier wird nichts geaendert
			}
		} catch (myException e) {
			bibMSG.MV()._addAlarm(e.get_ErrorMessage()+"  <5dd7c2a8-5942-11ea-8e2d-0242ac130003-9>");
			e.printStackTrace();
		} catch (Exception e) {
			bibMSG.MV()._addAlarm(e.getLocalizedMessage()+"  <5dd7c2a8-5942-11ea-8e2d-0242ac130003-9>");
			e.printStackTrace();
		}


		return this;
	}
	
	
	
	
	public B2_McForValueSettingsOnMaskAction _setAdresseAfterKontraktOrAngebot_IF_SINGULAR(EnPositionStation  position) {
		
		try {
			
			boolean quelle =  (position==EnPositionStation.LEFT);
			
			Long idVposKon = this.getLongLiveVal(quelle?RecA1.key:RecA2.key,BG_ATOM.id_vpos_kon.fk());
			Long idVposStd = this.getLongLiveVal(quelle?RecA1.key:RecA2.key,BG_ATOM.id_vpos_std.fk());
	
			if (idVposKon!=null) {
				Rec21_VPosKon vpos = (Rec21_VPosKon)new Rec21_VPosKon()._fill_id(idVposKon);
				Rec21_VKopfKon vkopf = vpos.getVkopfKon();
				
//				if (vkopf.getLongDbValue(VKOPF_KON.id_adresse)!=null) {
				Rec21_adresse ra = new Rec21_adresse()._fill_id(vkopf.getLongDbValue(VKOPF_KON.id_adresse))._getMainAdresse();
				
				RecList21 alleAdressen = ra.getMainAndLagerAdresses(true);
				if (alleAdressen.size()==0) {
					this.set_maskVal(quelle?RecS1.key:RecS3.key, BG_STATION.id_adresse, "", bibMSG.MV());
				} else if (alleAdressen.size()==1) {
					this.set_maskVal(quelle?RecS1.key:RecS3.key, BG_STATION.id_adresse, ""+alleAdressen.get(0).getId(), bibMSG.MV());
				} else {
					//auch loeschen 
					this.set_maskVal(quelle?RecS1.key:RecS3.key, BG_STATION.id_adresse, "", bibMSG.MV());
				}
//				}
				
			} else if (idVposStd!=null) {
				Rec21_VPosStd  vpos = (Rec21_VPosStd)new Rec21_VPosStd()._fill_id(idVposStd);
				Rec21_VKopfStd vkopf = vpos.getVkopfStd();
	
				Rec21_adresse ra = new Rec21_adresse()._fill_id(vkopf.getLongDbValue(VKOPF_STD.id_adresse))._getMainAdresse();
				
				RecList21 alleAdressen = ra.getMainAndLagerAdresses(true);
				if (alleAdressen.size()==0) {
					this.set_maskVal(quelle?RecS1.key:RecS3.key, BG_STATION.id_adresse, "", bibMSG.MV());
				} else if (alleAdressen.size()==1) {
					this.set_maskVal(quelle?RecS1.key:RecS3.key, BG_STATION.id_adresse, ""+alleAdressen.get(0).getId(), bibMSG.MV());
				} else {
					//auch loeschen 
					this.set_maskVal(quelle?RecS1.key:RecS3.key, BG_STATION.id_adresse, "", bibMSG.MV());
				}
				
	
				
			} else {
				//hier wird nichts geaendert
			}
		} catch (myException e) {
			bibMSG.MV()._addAlarm(e.get_ErrorMessage()+"  <5dd7c2a8-5942-11ea-8e2d-0242ac130003-10>");
			e.printStackTrace();
		} catch (Exception e) {
			bibMSG.MV()._addAlarm(e.getLocalizedMessage()+"  <5dd7c2a8-5942-11ea-8e2d-0242ac130003-10>");
			e.printStackTrace();
		}


		return this;
	}
	
	
	
	public B2_McForValueSettingsOnMaskAction _setDateFieldsOnPlanungVonSetting() {
		try {
			Date datumStartVon = this.getDateLiveVal(RecV.key, BG_VEKTOR.datum_planung_von.fk());
			
			if (datumStartVon != null) {
				MyDate mdatumStartVon = new MyDate(datumStartVon);
				this.setMaskVal(RecV.key, BG_VEKTOR.datum_planung_bis.fk(), mdatumStartVon.get_cDateStandardFormat(), true);
//				this.setMaskVal(RecA1.key, BG_ATOM.datum_ausfuehrung.fk(), mdatumStartVon.get_cDateStandardFormat(), true);
//				this.setMaskVal(RecA2.key, BG_ATOM.datum_ausfuehrung.fk(), mdatumStartVon.get_cDateStandardFormat(), true);
			}
		} catch (myException e) {
			bibMSG.MV()._addAlarm(e.get_ErrorMessage()+"  <5dd7c2a8-5942-11ea-8e2d-0242ac130003-13>");
			e.printStackTrace();
		} catch (Exception e) {
			bibMSG.MV()._addAlarm(e.getLocalizedMessage()+"  <5dd7c2a8-5942-11ea-8e2d-0242ac130003-13>");
			e.printStackTrace();
		}
		return this;
	}
	
	
	public B2_McForValueSettingsOnMaskAction _setOtherLeistungsdatum(EnPositionStation  position) {
		try {
			Date datumLeft = (Date)this.getValueFromScreen(RecA1.key, BG_ATOM.datum_ausfuehrung);
			Date datumRight = (Date)this.getValueFromScreen(RecA2.key, BG_ATOM.datum_ausfuehrung);

			
			Date datumOtherField = (Date)this.getValueFromScreen(position.getKeyAtomOtherSide(), BG_ATOM.datum_ausfuehrung);
			Date datumThisField = (Date)this.getValueFromScreen(position.getKeyAtom(), BG_ATOM.datum_ausfuehrung);
			
			if (datumThisField!=null) {
				if (datumOtherField!=null) {
					if (datumRight.before(datumLeft)) {
						this._setMaskValue(position.getKeyAtomOtherSide(), BG_ATOM.datum_ausfuehrung, new MyDate(datumThisField).get_cDateStandardFormat());
					}
				} else {
					this._setMaskValue(position.getKeyAtomOtherSide(), BG_ATOM.datum_ausfuehrung, new MyDate(datumThisField).get_cDateStandardFormat());
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			bibMSG.MV()._addWarn("Error reading date-value <372baf02-b15d-11ea-b3de-0242ac130004>");
		}
		
		
		return this;
	}
	
	
	
	public B2_McForValueSettingsOnMaskAction _clearTransportMittelFollowFieldsIfTransportmittelIsEmpty() {
		try {
			Long idTransportmittel = this.getLongLiveVal(RecV.key, BG_VEKTOR.id_transportmittel);
			
			if (O.isNull(idTransportmittel)) {
				this.getComponent(RecV.key, BG_VEKTOR.transportmittel).rb_set_db_value_manual("");
				this.getComponent(RecV.key, BG_VEKTOR.transportkennzeichen).rb_set_db_value_manual("");
				this.getComponent(RecV.key, BG_VEKTOR.anhaengerkennzeichen).rb_set_db_value_manual("");
			}
		} catch (myException e) {
			bibMSG.MV()._addAlarm(e.get_ErrorMessage()+"  <5dd7c2a8-5942-11ea-8e2d-0242ac130003-14>");
			e.printStackTrace();
		} catch (Exception e) {
			bibMSG.MV()._addAlarm(e.getLocalizedMessage()+"  <5dd7c2a8-5942-11ea-8e2d-0242ac130003-14>");
			e.printStackTrace();
		}
		return this;
	}

	
	
	
	public B2_McForValueSettingsOnMaskAction _clearAngebotIfKontraktIsFull(EnPositionStation  position) {
		try {
			Long idVposKon = this.getLongLiveVal(position==EnPositionStation.LEFT?RecA1.key:RecA2.key, BG_ATOM.id_vpos_kon);

			if (idVposKon!=null) {
				this.setMaskVal(position==EnPositionStation.LEFT?RecA1.key:RecA2.key, BG_ATOM.id_vpos_std.fk(), "", true);
			} 
			
		} catch (myException e) {
			bibMSG.MV()._addAlarm(e.get_ErrorMessage()+"  <5dd7c2a8-5942-11ea-8e2d-0242ac130003-15>");
			e.printStackTrace();
		} catch (Exception e) {
			bibMSG.MV()._addAlarm(e.getLocalizedMessage()+"  <5dd7c2a8-5942-11ea-8e2d-0242ac130003-15>");
			e.printStackTrace();
		}
		return this;
	}

	
	
	
	public B2_McForValueSettingsOnMaskAction _clearKontraktIfAngebotIsFull(EnPositionStation  position) {
		try {
			Long idVposStd = this.getLongLiveVal(position==EnPositionStation.LEFT?RecA1.key:RecA2.key, BG_ATOM.id_vpos_std);

			if (idVposStd!=null) {
				this.setMaskVal(position==EnPositionStation.LEFT?RecA1.key:RecA2.key, BG_ATOM.id_vpos_kon.fk(), "", true);
			} 
			
		} catch (myException e) {
			bibMSG.MV()._addAlarm(e.get_ErrorMessage()+"  <5dd7c2a8-5942-11ea-8e2d-0242ac130003-16>");
			e.printStackTrace();
		} catch (Exception e) {
			bibMSG.MV()._addAlarm(e.getLocalizedMessage()+"  <5dd7c2a8-5942-11ea-8e2d-0242ac130003-16>");
			e.printStackTrace();
		}
		return this;
	}

	
	public B2_McForValueSettingsOnMaskAction _setSteuerSatzAndSteuerTextAfterSteuerIsSet(EnPositionStation  position) {
		try {
			RB_KM mask =  (position==EnPositionStation.LEFT?RecA1.key:RecA2.key);
			Rec21 tax = this.getTax(mask);
			if (tax!=null) {
				this.setMaskVal(mask, BG_ATOM.eu_steuer_vermerk.fk(), tax.getFs(TAX.steuervermerk), false);
				this.setMaskVal(mask, BG_ATOM.steuersatz.fk(), tax.getFs(TAX.steuersatz), false);
			} else {
				this.setMaskVal(mask, BG_ATOM.eu_steuer_vermerk.fk(), "", false);
				this.setMaskVal(mask, BG_ATOM.steuersatz.fk(), "", false);
			}
		} catch (myException e) {
			bibMSG.MV()._addAlarm(e.get_ErrorMessage()+"  <5dd7c2a8-5942-11ea-8e2d-0242ac130003-17>");
			e.printStackTrace();
		} catch (Exception e) {
			bibMSG.MV()._addAlarm(e.getLocalizedMessage()+"  <5dd7c2a8-5942-11ea-8e2d-0242ac130003-17>");
			e.printStackTrace();
		}
		return this;
	}

	

	/**
	 * bei aenderung der Transportart werden die steuersatz-felder resettet
	 * @author martin
	 * @date 04.03.2020
	 *
	 * @param position
	 * @return
	 */
	private B2_McForValueSettingsOnMaskAction _clearTaxFields() {
		try {
			
			this.setMaskVal(RecA1.key, BG_ATOM.id_tax.fk(), "", false);
			this.setMaskVal(RecA1.key, BG_ATOM.steuersatz.fk(), "", false);
			this.setMaskVal(RecA1.key, BG_ATOM.eu_steuer_vermerk.fk(), "", false);
			
			this.setMaskVal(RecA2.key, BG_ATOM.id_tax.fk(), "", false);
			this.setMaskVal(RecA2.key, BG_ATOM.steuersatz.fk(), "", false);
			this.setMaskVal(RecA2.key, BG_ATOM.eu_steuer_vermerk.fk(), "", false);
		} catch (myException e) {
			bibMSG.MV()._addAlarm(e.get_ErrorMessage()+"  <5dd7c2a8-5942-11ea-8e2d-0242ac130003-22>");
			e.printStackTrace();
		} catch (Exception e) {
			bibMSG.MV()._addAlarm(e.getLocalizedMessage()+"  <5dd7c2a8-5942-11ea-8e2d-0242ac130003-22>");
			e.printStackTrace();
		}
		return this;
	}


	
	private B2_McForValueSettingsOnMaskAction _setProformaSteuerFieldsOnMandantSite(EnPositionStation  position) {
		try {
			RB_KM mask =  (position==EnPositionStation.LEFT?RecA1.key:RecA2.key);
			
			//jetzt den gueltigen proforma-steuersatz laden
			Rec21 recLagerSteuer = new PdServiceReadProformaSteuerEintrag().getTaxLagerProformaEintrag();
			
			if (recLagerSteuer!=null) {
				this.setMaskVal(mask, BG_ATOM.id_tax.fk(), recLagerSteuer.getFs(TAX.id_tax), false);
				this.setMaskVal(mask, BG_ATOM.steuersatz.fk(), recLagerSteuer.getFs(TAX.steuersatz), false);
				this.setMaskVal(mask, BG_ATOM.eu_steuer_vermerk.fk(), recLagerSteuer.getFs(TAX.steuervermerk), false);
			} else {
				this.setMaskVal(mask, BG_ATOM.steuersatz.fk(), "", false);
				this.setMaskVal(mask, BG_ATOM.id_tax.fk(), "", false);
				this.setMaskVal(mask, BG_ATOM.eu_steuer_vermerk.fk(), "", false);
				bibMSG.MV()._addAlarm("Stammdaten-Fehler! Bitte setzen Sie einen eindeutigen, aktiven Steuersatz fest, der den Typ <Lagerseite/Proformaeintrag> und die Steuer 0 besitzt !");
			}
		} catch (myException e) {
			bibMSG.MV()._addAlarm(e.get_ErrorMessage()+"  <5dd7c2a8-5942-11ea-8e2d-0242ac130003-18>");
			e.printStackTrace();
		} catch (Exception e) {
			bibMSG.MV()._addAlarm(e.getLocalizedMessage()+"  <5dd7c2a8-5942-11ea-8e2d-0242ac130003-18>");
			e.printStackTrace();
		}
		return this;
	}

	
	public B2_McForValueSettingsOnMaskAction _setLagerpreisGleichEkPreisWhenWE() {
		try {
			boolean manuellPreisRight = this.getYNMaskVal(RecA2.key,BG_ATOM.manuell_preis.fk());
			
			BigDecimal bdPreisLeft =    this.getBigDecimalLiveVal(RecA1.key,BG_ATOM.e_preis_basiswaehrung.fk());
			BigDecimal bdPreisRight=    this.getBigDecimalLiveVal(RecA2.key,BG_ATOM.e_preis_basiswaehrung.fk());
			
			EnTransportTyp typ = this.getTransportArt();
			
			if (typ!=null && typ==EnTransportTyp.WE) {
				if (!manuellPreisRight && bdPreisLeft!=null && O.notEquals(bdPreisLeft,bdPreisRight)) {
					this.setMaskVal(RecA2.key,BG_ATOM.e_preis_basiswaehrung.fk(), MyNumberFormater.formatDez(bdPreisLeft, 2, true),false);
					bibMSG.MV()._addInfo(S.ms("Der Preis auf der Lagerseite wurde an den Einkaufspreis angepasst"));
				}
			}
		} catch (myException e) {
			bibMSG.MV()._addAlarm(e.get_ErrorMessage()+"  <5dd7c2a8-5942-11ea-8e2d-0242ac130003-19>");
			e.printStackTrace();
		} catch (Exception e) {
			bibMSG.MV()._addAlarm(e.getLocalizedMessage()+"  <5dd7c2a8-5942-11ea-8e2d-0242ac130003-19>");
			e.printStackTrace();
		}

		return this;
	}
	
	
	
	public B2_McForValueSettingsOnMaskAction _clearKontraktAndAngebotWhenNotAllowed() {
		try {
			
			EnTransportTyp typ = this.getEnTransportTyp();
			
			if (typ != null) {
				if (!typ.isAllowKontraktAngebotLeft()) {
	
					if (this.getLongLiveVal(RecA1.key, BG_ATOM.id_vpos_kon)!=null) {
						this.getComponent(RecA1.key, BG_ATOM.id_vpos_kon).rb_set_db_value_manual("");
						bibMSG.MV()._addInfo("Kontrakt auf der Quellseite entfernt (Transporttyp erlaubt keinen EK-Kontrakt)");
					}
	
					if (this.getLongLiveVal(RecA1.key, BG_ATOM.id_vpos_std)!=null) {
						this.getComponent(RecA1.key, BG_ATOM.id_vpos_std).rb_set_db_value_manual("");
						bibMSG.MV()._addInfo("Angebot auf der Quellseite entfernt (Transporttyp erlaubt keinen EK-Angebot) ");
					}
				}
				
				if (!typ.isAllowKontraktAngebotRight()) {
	
					if (this.getLongLiveVal(RecA2.key, BG_ATOM.id_vpos_kon)!=null) {
						this.getComponent(RecA2.key, BG_ATOM.id_vpos_kon).rb_set_db_value_manual("");
						bibMSG.MV()._addInfo("Kontrakt auf der Quellseite entfernt (Transporttyp erlaubt keinen EK-Kontrakt)");
					}
	
					if (this.getLongLiveVal(RecA2.key, BG_ATOM.id_vpos_std)!=null) {
						this.getComponent(RecA2.key, BG_ATOM.id_vpos_std).rb_set_db_value_manual("");
						bibMSG.MV()._addInfo("Angebot auf der Quellseite entfernt (Transporttyp erlaubt keinen EK-Angebot) ");
					}
				}
			}
			
		} catch (myException e) {
			bibMSG.MV()._addAlarm(e.get_ErrorMessage()+"  <5dd7c2a8-5942-11ea-8e2d-0242ac130003-20>");
			e.printStackTrace();
		} catch (Exception e) {
			bibMSG.MV()._addAlarm(e.getLocalizedMessage()+"  <5dd7c2a8-5942-11ea-8e2d-0242ac130003-20>");
			e.printStackTrace();
		}
		return this;
	}
	
	
	
	public B2_McForValueSettingsOnMaskAction _clearFremdAdresseIfNotNeededOrFalse() {
		try {
			EnTransportTyp transportTyp = this.getTransportArt();

			if (transportTyp != null) {
				if (!transportTyp.needsFremdbesitzer()) {
					this._setDisabled(RecV.key, BG_VEKTOR.id_adresse_fremdware);
				}
			}
		} catch (myException e) {
			bibMSG.MV()._addAlarm(e.get_ErrorMessage()+"  <5dd7c2a8-5942-11ea-8e2d-0242ac130003-21>");
			e.printStackTrace();
		} catch (Exception e) {
			bibMSG.MV()._addAlarm(e.getLocalizedMessage()+"  <5dd7c2a8-5942-11ea-8e2d-0242ac130003-21>");
			e.printStackTrace();
		}
		
		
		return this;
	}


	
	
	public B2_McForValueSettingsOnMaskAction _clearPreisFields(EnPositionStation  position, MyE2_MessageVector mv) {
		RB_KM maskKey = (position == EnPositionStation.LEFT)?RecA1.key:RecA2.key;
		
		try {
			RB_cb  cbSetPreisManuell = null;

			cbSetPreisManuell = 	(RB_cb)			this.getComp(maskKey, BG_ATOM.manuell_preis);
			cbSetPreisManuell.setSelected(false);

			this._clearFields(maskKey, BG_ATOM.e_preis_basiswaehrung,BG_ATOM.e_preis_fremdwaehrung,BG_ATOM.waehrungskurs);
			
		} catch (Exception e) {
			mv._addAlarm("Error clearing Preis-Settings <390ba026-5e3c-11ea-bc55-dsjf324235>");
			e.printStackTrace();
		}

		
		return this;
	}
	
	

	public B2_McForValueSettingsOnMaskAction _loadPreisAndWaehrungskursFromKontraktOrAngebot(EnPositionStation  position, MyE2_MessageVector mv) {
		
		if (mv == null) {
			mv = bibMSG.MV();
		}
		
		try {
			Long idKontraktPos = null;
			Long idAngebotPos = null;
			Long idWaehrungFremd = null;
			
			
			RB_cb  cbSetPreisManuell = null;
			
			Rec21 rec21WaehrungBasis = (Rec21)ENUM_MANDANT_SESSION_STORE.BASE_CURRENCY.getValueFromSession();
			
			RB_KM maskKey = (position == EnPositionStation.LEFT)?RecA1.key:RecA2.key;
			
			cbSetPreisManuell = 		(RB_cb)			this.getComp(maskKey, BG_ATOM.manuell_preis);
			
			idKontraktPos = 			this.getLongLiveVal(maskKey, BG_ATOM.id_vpos_kon);
			idAngebotPos  = 			this.getLongLiveVal(maskKey, BG_ATOM.id_vpos_std);
			idWaehrungFremd  = 			this.getLongLiveVal(maskKey, BG_ATOM.id_waehrung);
		
			boolean preventLoad = cbSetPreisManuell.isSelected() || (O.isAllNull(idKontraktPos,idAngebotPos));

			BigDecimal bdPreisBasisWaehrungVorher = (BigDecimal)this.getValueJustInTime(maskKey, BG_ATOM.e_preis_basiswaehrung);
			BigDecimal bdWaehrungskursVorher      = (BigDecimal)this.getValueJustInTime(maskKey, BG_ATOM.waehrungskurs);
			Long       lWaehrungFremdVorher       = (Long)this.getValueJustInTime(maskKey, BG_ATOM.id_waehrung);
			
			if (!preventLoad) {
			
				
				BigDecimal bdPreisBasisWaehrungNacher = null;
				BigDecimal bdWaehrungskursNachher     = null;
				Long       lWaehrungFremdNachher 	  = null;

				String infoWoher = "";
				
				if (idKontraktPos!=null) {
					Rec21_VPosKon recKon = (Rec21_VPosKon)new Rec21_VPosKon()._fill_id(idKontraktPos);
					
					this	._setMaskValue(maskKey, BG_ATOM.e_preis_basiswaehrung, recKon.getFs(VPOS_KON.einzelpreis))
							._setMaskValue(maskKey, BG_ATOM.waehrungskurs, recKon.getFs(VPOS_KON.waehrungskurs))
							._setMaskValue(maskKey, BG_ATOM.e_preis_fremdwaehrung, "")
							._setMaskValue(maskKey, BG_ATOM.id_waehrung, recKon.getFs(VPOS_KON.id_waehrung_fremd))
							;
					
					cbSetPreisManuell.setSelected(false);

					infoWoher = " durch Angaben aus der Kontraktposition ";
					
				} else if (idAngebotPos!=null) {
					Rec21_VPosStd recStd = (Rec21_VPosStd)new Rec21_VPosStd()._fill_id(idAngebotPos);

					this	._setMaskValue(maskKey, BG_ATOM.e_preis_basiswaehrung, recStd.getFs(VPOS_STD.einzelpreis))
							._setMaskValue(maskKey, BG_ATOM.waehrungskurs, recStd.getFs(VPOS_STD.waehrungskurs))
							._setMaskValue(maskKey, BG_ATOM.e_preis_fremdwaehrung, "")
							._setMaskValue(maskKey, BG_ATOM.id_waehrung, recStd.getFs(VPOS_STD.id_waehrung_fremd))
							;

					infoWoher = " durch Angaben aus der Angebotsposition ";
					
					cbSetPreisManuell.setSelected(false);
				} 

				
				
				bdPreisBasisWaehrungNacher = 	(BigDecimal)this.getValueJustInTime(maskKey, BG_ATOM.e_preis_basiswaehrung);
				bdWaehrungskursNachher      = 	(BigDecimal)this.getValueJustInTime(maskKey, BG_ATOM.waehrungskurs);
				lWaehrungFremdNachher  = 		(Long)this.getValueJustInTime(maskKey, BG_ATOM.id_waehrung);

				if ( ! MyBigDecimal.areEquals(bdPreisBasisWaehrungVorher, bdPreisBasisWaehrungNacher)) {
					mv._addInfo(S.ms("Der Basispreis aus der Maske wurde "+infoWoher+" überschrieben !"));
				}
				if ( ! MyBigDecimal.areEquals(	bdWaehrungskursVorher,bdWaehrungskursNachher) ) {
					mv._addInfo(S.ms("Der Währungskurs aus der Maske wurde "+infoWoher+" überschrieben !"));
				}
				if ( ! MyLong.areEquals(lWaehrungFremdVorher,lWaehrungFremdNachher) ) {
					mv._addInfo(S.ms("Die Währung aus der Maske wurde "+infoWoher+" überschrieben !"));
				}
				
			}
			
			//immer pruefen, ob die waehrungen gleich sind
			idWaehrungFremd  = 			(Long)this.getValueJustInTime(maskKey, BG_ATOM.id_waehrung);
			if (O.NN(idWaehrungFremd,0l).longValue()==rec21WaehrungBasis.getId()) {
				this._setMaskValue(maskKey, VPOS_KON.waehrungskurs, "1");
				BigDecimal bdWaehrungskursNachher2      = 	(BigDecimal)this.getValueJustInTime(maskKey, BG_ATOM.waehrungskurs);
				if ( ! MyBigDecimal.areEquals(	bdWaehrungskursVorher,bdWaehrungskursNachher2) ) {
					mv._addInfo(S.ms("Der Währungskurs aus der Maske wurde korrigiert (Währungsgleichheit: Kurs = 1)"));
				}
			}

	

			
		} catch (myException e) {
			mv._addAlarm(e.get_ErrorMessage()+"  <5dd7c2a8-5942-11ea-8e2d-0242ac130003-23>");
			e.printStackTrace();
		} catch (Exception e) {
			mv._addAlarm(e.getLocalizedMessage()+"  <5dd7c2a8-5942-11ea-8e2d-0242ac130003-23>");
			e.printStackTrace();
		}
		return this;

	}
	

	//###hier gehts weiter
	
	
	
	
	public B2_McForValueSettingsOnMaskAction _berechneFremdPreis(EnPositionStation  position, MyE2_MessageVector mv) {
		if (mv==null) {
			mv = bibMSG.MV();
		}
		try {
			
			BigDecimal bdEinzelPreisBasisWaehrung = null;
			BigDecimal bdWaehrungsKurs = null;
			BigDecimal bdEinzelPreisFremdWaehrung = null;
			
			RB_KM maskKey = null;
			
			if (position==EnPositionStation.LEFT) {
				maskKey = RecA1.key;
			} else if (position==EnPositionStation.RIGHT) {
				maskKey = RecA2.key;
				bdEinzelPreisBasisWaehrung = (BigDecimal)this.getValueJustInTime(RecA2.key, BG_ATOM.e_preis_basiswaehrung);
				bdWaehrungsKurs = (BigDecimal)this.getValueJustInTime(RecA2.key, BG_ATOM.waehrungskurs);
			} else {
				maskKey = null;
			}

			if (maskKey==null) {
				mv._addAlarm("Systemerror, only LEFT / RIHT are allowed <bd04b71a-5edb-11ea-bc55-0242ac130003>");
				bdEinzelPreisBasisWaehrung = null;
				bdWaehrungsKurs = null;
				bdEinzelPreisFremdWaehrung=null;
				this._clearFields(maskKey, BG_ATOM.e_preis_basiswaehrung, BG_ATOM.e_preis_fremdwaehrung, BG_ATOM.waehrungskurs);
			} else {
				bdEinzelPreisBasisWaehrung = (BigDecimal)this.getValueJustInTime(maskKey, BG_ATOM.e_preis_basiswaehrung);
				bdWaehrungsKurs = (BigDecimal)this.getValueJustInTime(maskKey, BG_ATOM.waehrungskurs);
				if (O.isNoOneNull(bdEinzelPreisBasisWaehrung,bdWaehrungsKurs)) {
					bdEinzelPreisFremdWaehrung = bdEinzelPreisBasisWaehrung.multiply(bdWaehrungsKurs, new MathContext(32, RoundingMode.HALF_UP));
					bdEinzelPreisFremdWaehrung = bdEinzelPreisFremdWaehrung.setScale(2, RoundingMode.HALF_UP);
					
					this._setMaskValue(maskKey, BG_ATOM.e_preis_basiswaehrung, 	new MyBigDecimal(bdEinzelPreisBasisWaehrung).get_FormatedRoundedNumber(2));
					this._setMaskValue(maskKey, BG_ATOM.e_preis_fremdwaehrung,	new MyBigDecimal(bdEinzelPreisFremdWaehrung).get_FormatedRoundedNumber(2));
					this._setMaskValue(maskKey, BG_ATOM.waehrungskurs, 			new MyBigDecimal(bdWaehrungsKurs).get_FormatedRoundedNumber(4));
					
				} else {
					//alle felder leeren
					this._clearFields(maskKey, BG_ATOM.e_preis_fremdwaehrung);
				}
			}
			
		} catch (Exception e) {
			mv._addAlarm(e.getLocalizedMessage()+"  <5dd7c2a8-5942-11ea-8e2d-0242ac130003-24>");
			e.printStackTrace();
		}
		return this;
	}
	
	
	
	/**
	 * sucht korrekte lieferbedingung fuer die jweilige seite aus
	 * @author martin
	 * @date 05.03.2020
	 *
	 * @param position
	 * @param mv
	 * @return
	 */
	public B2_McForValueSettingsOnMaskAction _setLieferbedingungen(EnPositionStation position, MyE2_MessageVector mv) {
		if (mv==null) {
			mv = bibMSG.MV();
		}
		try {
			RB_KM maskKeyAtom = null;
			RB_KM maskKeyStation = null;
			if (position==EnPositionStation.LEFT) {
				maskKeyAtom = RecA1.key;
				maskKeyStation = RecS1.key;
			} else if (position==EnPositionStation.RIGHT) {
				maskKeyAtom = RecA2.key;
				maskKeyStation = RecS3.key;
			} else {
				maskKeyAtom = null;
				maskKeyStation = null;
			}

			if (maskKeyAtom == null) {
				throw new myException("Systemerror only LEFT/RIGHT are allowed !: <9fdd0306-5ef2-11ea-bc55-0242ac130001>");
			}
			
			Long idAdresse = (Long)this.getValueJustInTime(maskKeyStation, BG_STATION.id_adresse);
			Long idVposKon = (Long)this.getValueJustInTime(maskKeyAtom, BG_ATOM.id_vpos_kon);
			Long idVposStd = (Long)this.getValueJustInTime(maskKeyAtom, BG_ATOM.id_vpos_std);
			
			Rec21_adresse  recAdresse = null;
			if (idAdresse!=null) {
				recAdresse = new Rec21_adresse()._fill_id(idAdresse)._getMainAdresse();
			}
			
			String lieferBed = null;
			Long idLieferBedingung = null;
			if (idVposKon!=null) {
				Rec21 rvposkon = new Rec21(_TAB.vpos_kon)._fill_id(idVposKon);
				lieferBed = rvposkon.getUfs(VPOS_KON.lieferbedingungen);
			} else if (idVposStd!=null) {
				Rec21 rvposstd = new Rec21(_TAB.vpos_std)._fill_id(idVposStd);
				lieferBed = rvposstd.getUfs(VPOS_STD.lieferbedingungen);
			} else if (recAdresse!=null) {
				if (position==EnPositionStation.LEFT) {
					idLieferBedingung = recAdresse.getLongDbValue(ADRESSE.id_lieferbedingungen);
				} else {
					idLieferBedingung = recAdresse.getLongDbValue(ADRESSE.id_lieferbedingungen_vk);
				}
			}
			
			if (recAdresse != null && recAdresse.isAdressOfMandant()) {
				this._clearFields(maskKeyAtom, BG_ATOM.id_lieferbedingungen, BG_ATOM.lieferbedingungen);
			} else {
			
				if (S.isEmpty(lieferBed)) {
					this._clearFields(maskKeyAtom, BG_ATOM.id_lieferbedingungen, BG_ATOM.lieferbedingungen);
					
					//wenn  aus adresse
					if (idLieferBedingung!=null) {
						Rec21 rLieferBed = new Rec21(_TAB.lieferbedingungen)._fill_id(idLieferBedingung);
						this	._setMaskValue(maskKeyAtom, BG_ATOM.id_lieferbedingungen, 	rLieferBed.getFs(LIEFERBEDINGUNGEN.id_lieferbedingungen))
											;
	
					}
					
				} else {
					//jetzt eine lieferbedingung suchen (mit id), die der lieferbedingung entspricht
					RecList21 rlLieferbed = new RecList21(_TAB.lieferbedingungen)._fillWithAll();
					
					Rec21 recLieferbed = null;
					//erste pruefung: exacter treffer
					for (Rec21 r: rlLieferbed) {
						if (lieferBed.toUpperCase().trim().equals(r.getUfs(LIEFERBEDINGUNGEN.bezeichnung).toUpperCase().trim())) {
							recLieferbed = r;
							break;
						}
					}
	
	
					if (recLieferbed==null) {
						//zweite pruefung: wert aus den bewegungssaetzen ist gleich der kurzbezeichnung
						for (Rec21 r: rlLieferbed) {
							if (lieferBed.toUpperCase().trim().startsWith(r.getUfs(LIEFERBEDINGUNGEN.kurzbezeichnung).toUpperCase().trim())) {
								recLieferbed = r;
								break;
							}
						}
					}
	
					
					if (recLieferbed==null) {
						//dritte pruefung: wert aus den bewegungssaetzen beginnt mit der kurzbezeichnung
						for (Rec21 r: rlLieferbed) {
							if (lieferBed.toUpperCase().trim().startsWith(r.getUfs(LIEFERBEDINGUNGEN.kurzbezeichnung).toUpperCase().trim())) {
								recLieferbed = r;
								break;
							}
						}
					}
					
					if (recLieferbed==null) {
						//vierte pruefung: kurzbezeichnung ist im wert aus den bewegungssaetzen enthalten
						for (Rec21 r: rlLieferbed) {
							if (lieferBed.toUpperCase().trim().contains(r.getUfs(LIEFERBEDINGUNGEN.kurzbezeichnung).toUpperCase().trim())) {
								recLieferbed = r;
								break;
							}
						}
					}
					
					this._clearFields(maskKeyAtom, BG_ATOM.id_lieferbedingungen, BG_ATOM.lieferbedingungen);
					if (recLieferbed!=null) {
						this._setMaskValue(maskKeyAtom, BG_ATOM.id_lieferbedingungen, 	recLieferbed.getFs(LIEFERBEDINGUNGEN.id_lieferbedingungen));
						if (S.isFull(lieferBed) && !lieferBed.toUpperCase().trim().equals(recLieferbed.getFs(LIEFERBEDINGUNGEN.bezeichnung).toUpperCase().trim())) {
							this._setMaskValue(maskKeyAtom, BG_ATOM.lieferbedingungen, 		lieferBed);
						}
					}
					
				}
			}

		} catch (Exception e) {
			mv._addAlarm(e.getLocalizedMessage()+"  <9fdd0306-5ef2-11ea-bc55-0242ac130003>");
			e.printStackTrace();
		}
		
		return this;
	}
	

	
	/**
	 * sucht korrekte lieferbedingung fuer die jweilige seite aus
	 * @author martin
	 * @date 05.03.2020
	 *
	 * @param position
	 * @param mv
	 * @return
	 */
	public B2_McForValueSettingsOnMaskAction _setZahlungsbedingungen(EnPositionStation position, MyE2_MessageVector mv) {
		if (mv==null) {
			mv = bibMSG.MV();
		}
		try {
			RB_KM maskKeyAtom = null;
			RB_KM maskKeyStation = null;
			if (position==EnPositionStation.LEFT) {
				maskKeyAtom = RecA1.key;
				maskKeyStation = RecS1.key;
			} else if (position==EnPositionStation.RIGHT) {
				maskKeyAtom = RecA2.key;
				maskKeyStation = RecS3.key;
			} else {
				maskKeyAtom = null;
				maskKeyStation = null;
			}

			if (maskKeyAtom == null) {
				throw new myException("Systemerror only LEFT/RIGHT are allowed !: <9fdd0306-5ef2-11ea-bc55-0242ac130001>");
			}
			
			Long idAdresse = (Long)this.getValueJustInTime(maskKeyStation, BG_STATION.id_adresse);
			Long idVposKon = (Long)this.getValueJustInTime(maskKeyAtom, BG_ATOM.id_vpos_kon);
			Long idVposStd = (Long)this.getValueJustInTime(maskKeyAtom, BG_ATOM.id_vpos_std);
			
			Rec21_adresse  recAdresse = null;
			if (idAdresse!=null) {
				recAdresse = new Rec21_adresse()._fill_id(idAdresse)._getMainAdresse();
			}

			
			Long idZahlungsBedingung = null;
			if (idVposKon!=null) {
				Rec21 rvposkon = new Rec21(_TAB.vpos_kon)._fill_id(idVposKon);
				idZahlungsBedingung = rvposkon.getLongDbValue(VPOS_KON.id_zahlungsbedingungen);
			} else if (idVposStd!=null) {
				Rec21 rvposstd = new Rec21(_TAB.vpos_std)._fill_id(idVposStd);
				idZahlungsBedingung = rvposstd.getLongDbValue(VPOS_STD.id_zahlungsbedingungen);
			} else if (recAdresse!=null) {
				if (position==EnPositionStation.LEFT) {
					idZahlungsBedingung = recAdresse.getLongDbValue(ADRESSE.id_zahlungsbedingungen);
				} else {
					idZahlungsBedingung = recAdresse.getLongDbValue(ADRESSE.id_zahlungsbedingungen_vk);
				}
			}
			
			
			
			this._clearFields(maskKeyAtom, BG_ATOM.id_zahlungsbedingungen, BG_ATOM.zahlungsbedingungen);
				
				//wenn  aus adresse
			if (idZahlungsBedingung!=null && recAdresse != null && !recAdresse.isAdressOfMandant()) {
				Rec21 rZahlungsBed = new Rec21(_TAB.zahlungsbedingungen)._fill_id(idZahlungsBedingung);
					this	._setMaskValue(maskKeyAtom, BG_ATOM.id_zahlungsbedingungen, rZahlungsBed.getFs(ZAHLUNGSBEDINGUNGEN.id_zahlungsbedingungen))
					;
			}

		} catch (Exception e) {
			mv._addAlarm(e.getLocalizedMessage()+"  <9fdd0306-5ef2-11ea-bc55-0242ac130003>");
			e.printStackTrace();
		}
		
		return this;
	}

	
	
	
	public B2_McForValueSettingsOnMaskAction _clearLieferbedAfterSelected(EnPositionStation position, MyE2_MessageVector mv) {
		if (mv==null) {
			mv = bibMSG.MV();
		}
		try {
			RB_KM maskKeyAtom = null;
			if (position==EnPositionStation.LEFT) {
				maskKeyAtom = RecA1.key;
			} else if (position==EnPositionStation.RIGHT) {
				maskKeyAtom = RecA2.key;
			} else {
				maskKeyAtom = null;
			}

			if (maskKeyAtom == null) {
				throw new myException("Systemerror only LEFT/RIGHT are allowed !: <9fdd0306-5ef2-11ea-bc55-0242ac130001-02>");
			}
			
			this._clearFields(maskKeyAtom,BG_ATOM.lieferbedingungen);

			
		} catch (Exception e) {
			mv._addAlarm(e.getLocalizedMessage()+"  <9fdd0306-5ef2-11ea-bc55-0242ac130003-01>");
			e.printStackTrace();
		}
		
		return this;
	}
	

	public B2_McForValueSettingsOnMaskAction _clearZahlungsbedAfterSelected(EnPositionStation position, MyE2_MessageVector mv) {
		if (mv==null) {
			mv = bibMSG.MV();
		}
		try {
			RB_KM maskKeyAtom = null;
			if (position==EnPositionStation.LEFT) {
				maskKeyAtom = RecA1.key;
			} else if (position==EnPositionStation.RIGHT) {
				maskKeyAtom = RecA2.key;
			} else {
				maskKeyAtom = null;
			}

			if (maskKeyAtom == null) {
				throw new myException("Systemerror only LEFT/RIGHT are allowed !: <9fdd0306-5ef2-11ea-bc55-0242ac130001-02>");
			}
			
			this._clearFields(maskKeyAtom,BG_ATOM.zahlungsbedingungen);

			
		} catch (Exception e) {
			mv._addAlarm(e.getLocalizedMessage()+"  <9fdd0306-5ef2-11ea-bc55-0242ac130003-02>");
			e.printStackTrace();
		}
		
		return this;
	}

	
	/**
	 * besetzt die sorte der jeweils anderen seite mit der sorte eines ausgesuchten angebots oder kontrakts
	 * @author martin
	 * @date 06.03.2020
	 *
	 * @param position
	 * @param mv
	 * @return
	 */
	public B2_McForValueSettingsOnMaskAction _setSorteOnOtherSideAfterAngebotOrKontrakt(EnPositionStation position, MyE2_MessageVector mv) {
		if (mv==null) {
			mv = bibMSG.MV();
		}
		
		try {
			RB_KM 				maskKeyAtom = null;
			RB_KM 				maskKeyOtherSide = null;
			EnPositionStation 	posOtherSide = null;
			if (position==EnPositionStation.LEFT) {
				maskKeyAtom = RecA1.key;
				maskKeyOtherSide = RecA2.key;
				posOtherSide = EnPositionStation.RIGHT;
			} else if (position==EnPositionStation.RIGHT) {
				maskKeyAtom = RecA2.key;
				maskKeyOtherSide = RecA1.key;
				posOtherSide = EnPositionStation.LEFT;
			} else {
				maskKeyAtom = null;
				maskKeyOtherSide = null;
				posOtherSide = null;
			}

			if (maskKeyAtom == null) {
				throw new myException("Systemerror only LEFT/RIGHT are allowed !: <9fdd0306-5ef2-11ea-bc55-0242ac130001-02>");
			}
			
			Long idVposKon = (Long)this.getValueJustInTime(maskKeyAtom, BG_ATOM.id_vpos_kon);
			Long idVposStd = (Long)this.getValueJustInTime(maskKeyAtom, BG_ATOM.id_vpos_std);
			
			Rec21_artikel_bez  artBez = null;
			
			if (idVposKon!=null) {
				Rec21_VPosKon kon = new Rec21_VPosKon()._fill_id(idVposKon);
				artBez = new Rec21_artikel_bez(kon.get_up_Rec21(ARTIKEL_BEZ.id_artikel_bez));
			} else if (idVposStd!=null) {
				Rec21_VPosStd kon = new Rec21_VPosStd()._fill_id(idVposStd);
				artBez = new Rec21_artikel_bez(kon.get_up_Rec21(ARTIKEL_BEZ.id_artikel_bez));
			}
			
			if (artBez!=null) {
				Long lArtikelBezOtherSide = (Long)this.getValueJustInTime(maskKeyOtherSide, BG_ATOM.id_artikel_bez);
				if (lArtikelBezOtherSide==null) {
					//dann auf der anderen seite die sorteninfos nachladen
					this._setMaskValue(maskKeyOtherSide, BG_ATOM.id_artikel_bez, ""+artBez.getId());
					this._refresh();
					this._setArtikelDatenAfterLoad(posOtherSide);
				}
			}
			
			
		} catch (Exception e) {
			mv._addAlarm(e.getLocalizedMessage()+"  <9fdd0306-5ef2-11ea-bc55-0242ac130003-02>");
			e.printStackTrace();
		}
		
		return this;
	}

	

	
	/*
	 * alle aktionen zusammengefasst, die nach dem suchen und finden eines kontraktes ausgeloest werden
	 */
	public B2_McForValueSettingsOnMaskAction _doActionsAfterKontraktPosWasChanged(IF_RB_Component callingComponent, EnPositionStation posLeftRight, boolean setAdressFields) throws myException {
			
		new B2_McForValueSettingsOnMaskAction(callingComponent)._setArtikelAfterKontraktOrAngebotIsFound(posLeftRight);
		if (setAdressFields) {
			new B2_McForValueSettingsOnMaskAction(callingComponent)._setAdresseAfterKontraktOrAngebot_IF_SINGULAR(posLeftRight);
			new B2_McForValueSettingsOnMaskAction(callingComponent)._setMaskValuesAfterAdressIsSet(posLeftRight) ;
		}

		new B2_McForValueSettingsOnMaskAction(callingComponent)._setSorteOnOtherSideAfterAngebotOrKontrakt(posLeftRight,null) ;

		
		new B2_McForValueSettingsOnMaskAction(callingComponent)._setMaskValueAVVCode(posLeftRight);
		new B2_McForValueSettingsOnMaskAction(callingComponent)._setBesitzer();
		new B2_McForValueSettingsOnMaskAction(callingComponent)._clearAngebotIfKontraktIsFull(posLeftRight);
		
		new B2_McForValueSettingsOnMaskAction(callingComponent)._clearPreisFields(posLeftRight, bibMSG.MV());
		new B2_McForValueSettingsOnMaskAction(callingComponent)._loadPreisAndWaehrungskursFromKontraktOrAngebot(posLeftRight,null);
		new B2_McForValueSettingsOnMaskAction(callingComponent)._berechneFremdPreis(posLeftRight, null);
		new B2_McForValueSettingsOnMaskAction(callingComponent)._setLieferbedingungen(posLeftRight, null);
		new B2_McForValueSettingsOnMaskAction(callingComponent)._setZahlungsbedingungen(posLeftRight, null);


		new B2_McForMaskShapeSettings(callingComponent)._setAllMaskShapeSettings();
			
		return this;

	}
	
	
	
	/*
	 * alle aktionen zusammengefasst, die nach dem suchen und finden eines angebotes ausgeloest werden
	 */
	public B2_McForValueSettingsOnMaskAction _doActionsAfterAngebotPosWasChanged(IF_RB_Component callingComponent, EnPositionStation posLeftRight, boolean setAdressFields) throws myException {
			
		new B2_McForValueSettingsOnMaskAction(callingComponent)._setArtikelAfterKontraktOrAngebotIsFound(posLeftRight);
		
		if (setAdressFields) {
			new B2_McForValueSettingsOnMaskAction(callingComponent)._setMaskValuesAfterAdressIsSet(posLeftRight) ;
		}

		new B2_McForValueSettingsOnMaskAction(callingComponent)._setSorteOnOtherSideAfterAngebotOrKontrakt(posLeftRight,null) ;
		
		new B2_McForValueSettingsOnMaskAction(callingComponent)._setMaskValueAVVCode(posLeftRight);
		new B2_McForValueSettingsOnMaskAction(callingComponent)._setBesitzer();
		new B2_McForValueSettingsOnMaskAction(callingComponent)._clearKontraktIfAngebotIsFull(posLeftRight);
		
		new B2_McForValueSettingsOnMaskAction(callingComponent)._clearPreisFields(posLeftRight, bibMSG.MV());
		new B2_McForValueSettingsOnMaskAction(callingComponent)._loadPreisAndWaehrungskursFromKontraktOrAngebot(posLeftRight,null);
		new B2_McForValueSettingsOnMaskAction(callingComponent)._berechneFremdPreis(posLeftRight, null);
		new B2_McForValueSettingsOnMaskAction(callingComponent)._setLieferbedingungen(posLeftRight, null);
		new B2_McForValueSettingsOnMaskAction(callingComponent)._setZahlungsbedingungen(posLeftRight, null);


		new B2_McForMaskShapeSettings(callingComponent)._setAllMaskShapeSettings();
			
		return this;

	}
	
	
	
	
	
	public B2_MaskController _setPreisZielFuerWareneingangeOderNichtAbrechnungsFuhren() throws Exception {
		if (! this.isRightPreisManuel() && !this.isRightSiteToBill()) {
			
			BigDecimal bdRightPreis = BigDecimal.ZERO;
			
			if (this.isZielAdressFullAndOwn()) {
				//dann wareneingang mit dem linken Preis bebuchen
				BigDecimal bdLeftPreis = (BigDecimal)this.getValueJustInTime(RecA1.key, BG_ATOM.e_preis_basiswaehrung);
				bdRightPreis = bdLeftPreis;							
			}
			
			String c_preis = new MyBigDecimal(bdRightPreis).get_FormatedRoundedNumber(2);

			
			 this	._setMaskValue(RecA2.key, BG_ATOM.e_preis_basiswaehrung, c_preis)
						._setMaskValue(RecA2.key, BG_ATOM.e_preis_fremdwaehrung, c_preis)
						._setMaskValue(RecA2.key, BG_ATOM.e_preis_res_netto_mge_basis, c_preis)
						._setMaskValue(RecA2.key, BG_ATOM.e_preis_res_netto_mge_fremd, c_preis)
						._setMaskValue(RecA2.key, BG_ATOM.manuell_preis, "Y")
						;

		}
		
		return this;
	}
	
	
	
	
	
	
	
	public B2_MaskController _executeTaxDetectionIfPossible() throws Exception {
		
		if (this.isLeftPreisAbschluss() && this.isRightPreisAbschluss()) {
			bibMSG.MV()._addWarn(S.ms(" Nach einem Preisabschluss kann keine Steuerfindung mehr erfolgen !"));
		} else { 	
		
			if (this.isVoraussetzungSteuerErmittlungErfuellt()) {
				
				if ( __RECORD_MANDANT_ZUSATZ.IS__Value("STEUERERMITTLUNG_MIT_HANDELSDEF", "N", "N")) {
					_HD_Station_BgVektor stationQuelle = 		new _HD_Station_BgVektor()._init(	true, 
																									getStartAdresse(), 
																									getArtbezQuelle(), 
																									(BigDecimal)getValueJustInTime(RecA1.key, BG_ATOM.menge), 
																									(String)getValueJustInTime(RecV.key, BG_VEKTOR.transportverantwortung), 
																									(BigDecimal)getValueJustInTime(RecA1.key, BG_ATOM.e_preis_basiswaehrung), 
																									(Date)getValueJustInTime(RecA1.key, BG_ATOM.datum_ausfuehrung));
					
					_HD_Station_BgVektor stationZiel = 			new _HD_Station_BgVektor()._init(	false, 
																									getZielAdresse(), 
																									getArtbezZiel(), 
																									(BigDecimal)getValueJustInTime(RecA2.key, BG_ATOM.menge), 
																									(String)getValueJustInTime(RecV.key, BG_VEKTOR.transportverantwortung), 
																									(BigDecimal)getValueJustInTime(RecA2.key, BG_ATOM.e_preis_basiswaehrung), 
																									(Date)getValueJustInTime(RecA2.key, BG_ATOM.datum_ausfuehrung));				
					
					//fehlerberichte sammeln
					HD_FehlerBerichte berichte = new HD_FehlerBerichte();
					berichte.addAll(stationQuelle.get_vFehlerVector());
					berichte.addAll(stationZiel.get_vFehlerVector());
	
					if (berichte.size()>0) {
						new E2BasicModuleContainerShowFehlerberichte(berichte, S.ms("Es gibt Fehler bei der Ermittlung der Handelsregel ..."));
					} else {
						HD_ErmittlePassendeHandelsDef  	ermittlerPassendeHandelsdef = 	new HD_ErmittlePassendeHandelsDef(stationQuelle,stationZiel);
						HD_WarenBewegungEinstufungen 	warenBewegungsEinstufungen = 	ermittlerPassendeHandelsdef.getWarenBewegungEinstufungen();
						
						if (warenBewegungsEinstufungen.size()>1) {
							//2014-01-02: mehrfache moeglichkeit: erlaubte dubletten oder nicht (dann fehlermeldung)
							if (warenBewegungsEinstufungen.get_bVectorHasAllowedMultidefinitions()) {
								
								new E2BasicModuleContainerZeigeMultipleHandelsdefinitionen(warenBewegungsEinstufungen);
								
							} else {
							
								VEK<MyE2_Grid>  vGrid = new VEK<MyE2_Grid>()._a(warenBewegungsEinstufungen.get_GridWithHandelsRelation(
													new MyE2_String(
														"Es wurden MEHRE Einträge für folgende " +
														"Sachverhalte in der Tabelle <USt./Steuertext> gefunden, " +
														"die unterschiedliche Einstufungen erzeugen !")));
								
								new E2BasicModuleContainerShowErgebnisse(vGrid, S.ms("Ergebnis der Suche ..."));
	
							}
							
							
						} else if (warenBewegungsEinstufungen.size()==0) {
	
							VEK<MyE2_Grid>  vGrid = new VEK<MyE2_Grid>()._a(warenBewegungsEinstufungen.get_GridWithHandelsRelation(S.ms("Nichts gefunden  ..."))) ;
							new E2BasicModuleContainerShowErgebnisse(vGrid, S.ms("Ergebnis der Suche ..."));
							
						} else if (warenBewegungsEinstufungen.size()==1) {
							Vector<MyE2IF__Component> vMessageZusatzButtons = new Vector<MyE2IF__Component>();
							vMessageZusatzButtons.add(new ownInfoButton(warenBewegungsEinstufungen, true));
							
							uebernehmeEinstellungInMaske(warenBewegungsEinstufungen.get(0));
							
							bibMSG.add_MESSAGE(new MyE2_BASIC_InfoMessageWithAddonComponent(
									new MyE2_String("Es wurde der folgende Sachverhalt bei dieser Fuhre gefunden und geschrieben:"),
											new ownInfoButton(warenBewegungsEinstufungen, true),
											new Extent(400),
											new Extent(100)),
											true);
							
						}
					}
					
				} else {
					PdServiceErmittleSteuerAlgorithmisch service = new PdServiceErmittleSteuerAlgorithmisch()._initAndFindTax(
							this.getAdresse(EnPositionStation.LEFT), 
							this.getAdresse(EnPositionStation.RIGHT),
							this.getArtbezQuelle(),
							this.getArtbezZiel(),
							(Date)this.getValueJustInTime(RecA1.key, BG_ATOM.datum_ausfuehrung),
							(Date)this.getValueJustInTime(RecA2.key, BG_ATOM.datum_ausfuehrung),
							(BigDecimal)this.getValueJustInTime(RecA1.key, BG_ATOM.e_preis_basiswaehrung),
							(BigDecimal)this.getValueJustInTime(RecA2.key, BG_ATOM.e_preis_basiswaehrung)
							);
							
					if (service.getErrorMessages().size()>0) {
						service.getErrorMessages().stream().forEach((s)->{bibMSG.getNewMV()._addAlarm(s);});
					} else {
						if (service.isSteuerSatzErmitteltQuelle() && !this.isLeftPreisAbschluss()) {
							bibMSG.MV()._addWarn(S.ms("Steuersatz auf der Quell-Seite ermittelt! Bitte prüfen !"));
							this._setMaskValue(RecA1.key, BG_ATOM.steuersatz, new MyBigDecimal(service.getSteuersatzQuelle()).get_FormatedRoundedNumber(2));
						}
						if (service.isSteuerVermerkErmitteltQuelle() && !this.isLeftPreisAbschluss()) {
							bibMSG.MV()._addWarn(S.ms("Steuervermerk auf der Quell-Seite ermittelt! Bitte prüfen !"));
							this._setMaskValue(RecA1.key, BG_ATOM.eu_steuer_vermerk, service.getSteuerVermerkQuelle());
						}
						if (service.isSteuerSatzErmitteltZiel() && !this.isRightPreisAbschluss()) {
							bibMSG.MV()._addWarn(S.ms("Steuersatz auf der Ziel-Seite ermittelt! Bitte prüfen !"));
							this._setMaskValue(RecA2.key, BG_ATOM.steuersatz, new MyBigDecimal(service.getSteuersatzZiel()).get_FormatedRoundedNumber(2));
						}
						if (service.isSteuerVermerkErmitteltZiel() && !this.isRightPreisAbschluss()) {
							bibMSG.MV()._addWarn(S.ms("Steuervermerk auf der Ziel-Seite ermittelt! Bitte prüfen !"));
							this._setMaskValue(RecA2.key, BG_ATOM.eu_steuer_vermerk, service.getSteuerVermerkZiel());
						}
					}
				}
				
			} else {
				bibMSG.MV()._addWarn(S.ms("Steuerfindung noch nicht möglich! Es müssen vorhanden sein: Leistungdaten, alle Adressen, alle Besitzer, alle BasisMengen und alle Preise!"));
			}
		}
		return this;
	}
	
	
	
	private class  E2BasicModuleContainerShowFehlerberichte extends E2_BasicModuleContainer {
		
		public E2BasicModuleContainerShowFehlerberichte(HD_FehlerBerichte vVollstaendigkeitsFehler  , MyE2_String titelText) throws myException	{
			super();
			this.add(vVollstaendigkeitsFehler.get_GridWithMeldungen());
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1200), new Extent(500), titelText);
		}
	}

	
	private class  E2BasicModuleContainerShowErgebnisse extends E2_BasicModuleContainer {
		
		public E2BasicModuleContainerShowErgebnisse(VEK<MyE2_Grid> grids  , MyE2_String titelText) throws myException	{
			super();
			E2_Grid baseGrid = new E2_Grid()._s(1);
			grids.stream().forEach((g)-> {baseGrid._a(g);});
			this.add(baseGrid);
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1200), new Extent(500), titelText);
		}
	}

	
	private class ownInfoButton extends MyE2_Button {
		private HD_WarenBewegungEinstufungen v_FuhrenEinstufung_single = null;
		private boolean b_SchreibeFuhrenInfoInPopup = false;
		public ownInfoButton(HD_WarenBewegungEinstufungen vFuhrenEinstufung_single, boolean bSchreibeFuhrenInfoInPopup) {
			super("Anzeigen der Einstufung",MyE2_Button.StyleTextButton(Color.YELLOW, Color.LIGHTGRAY, Color.BLACK, Color.DARKGRAY));
			this.v_FuhrenEinstufung_single = vFuhrenEinstufung_single;
			this.b_SchreibeFuhrenInfoInPopup = bSchreibeFuhrenInfoInPopup;
			this.add_oActionAgent(new XX_ActionAgent() {
				
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
					new HD_PopupZeigeFuhreneinstufung(
							ownInfoButton.this.v_FuhrenEinstufung_single,
							new MyE2_String("Es wurde der folgende Sachverhalt bei dieser Warenbewegung gefunden und geschrieben:"),
							ownInfoButton.this.b_SchreibeFuhrenInfoInPopup);
				}
			});
		}
	}	
	
	
	
	
	/**
	 * auswahl-popup-window fuer verschiedene, erlaubt ergebnisse zur auswahl fuer den anwender
	 */
	private class  E2BasicModuleContainerZeigeMultipleHandelsdefinitionen extends E2_BasicModuleContainer {
		
		public E2BasicModuleContainerZeigeMultipleHandelsdefinitionen(HD_WarenBewegungEinstufungen vMultipleMoeglichkeiten) throws myException	{
			super();
			E2_Grid baseGrid = new E2_Grid()._s(1)._a(new RB_lab()._t(S.ms("Es stehen unterschiedliche Lösungen zur Auswahl:")), new RB_gld()._ins(2, 2, 2, 5)._left_mid());

			for (HD_WarenBewegungEinstufung  oStufung: vMultipleMoeglichkeiten) {
				baseGrid._a(oStufung.get_GridMitFuhrenEinstufung(null,false,new ButtonUebernehmeEinstufungInMaske(oStufung)));
			}
			this.add(baseGrid);
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1200), new Extent(500), new MyE2_String("Bitte wählen Sie den gewünschten Einstufungsvorschlag aus..."));
		}
		
		
		private class ButtonUebernehmeEinstufungInMaske extends MyE2_Button {
			public ButtonUebernehmeEinstufungInMaske(HD_WarenBewegungEinstufung  oEinstufung) throws myException {
				super(E2_ResourceIcon.get_RI("ok_big.png"));
				this.add_oActionAgent(new ownActionAgent(oEinstufung));
				this.setToolTipText(new MyE2_String("Die USt./Steuertext-Definition in dieser Zeile auswählen").CTrans());
			}
		}
		
		
		private class ownActionAgent extends XX_ActionAgent {
			private HD_WarenBewegungEinstufung  einstufung = null;
			public ownActionAgent(HD_WarenBewegungEinstufung  pEinstufung) throws myException {
				super();
				this.einstufung = pEinstufung;
			}
			
			
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException	{
				uebernehmeEinstellungInMaske(einstufung);
				CLOSE_AND_DESTROY_POPUPWINDOW(true);
				
				bibMSG.add_MESSAGE(new MyE2_BASIC_InfoMessageWithAddonComponent(
						new MyE2_String("Es wurde der folgende Sachverhalt bei dieser Fuhre gefunden und geschrieben:"),
								new ownInfoButton(new HD_WarenBewegungEinstufungen()._a(einstufung), true),
								new Extent(400),
								new Extent(100)),
								true);
			}
		}
	}

	
	
	private void uebernehmeEinstellungInMaske(HD_WarenBewegungEinstufung einstufung)  {
		
		try {
			Rec21 handelsdef = einstufung.getHandelsDef();
			
			if (this.isLeftPreisAbschluss() && this.isRightPreisAbschluss()) {
				bibMSG.MV()._addWarn(S.ms(" Nach einem Preisabschluss kann keine Steuerfindung mehr erfolgen !"));
			} else {
				
				if (!this.isLeftPreisAbschluss()) {
					this._setMaskValue(RecA1.key, BG_ATOM.intrastat_meld, 	handelsdef.getUfs(HANDELSDEF.intrastat_meld_in, "N"));
					this._setMaskValue(RecA1.key, BG_ATOM.transit_meld, 	handelsdef.getUfs(HANDELSDEF.transit_ek,   "N"));
					this._setMaskValue(RecA1.key, BG_ATOM.id_tax, 			handelsdef.getFs(HANDELSDEF.id_tax_quelle));
					
					Rec21_tax recTax = new Rec21_tax(handelsdef.get_up_Rec21(HANDELSDEF.id_tax_quelle, TAX.id_tax, true));
					Rec21_tax recTaxNeg = recTax;
					
					Rec21     rec21 = handelsdef.get_up_Rec21(HANDELSDEF.id_tax_negativ_quelle, TAX.id_tax, true);
					if (rec21 != null) {
						recTaxNeg = new Rec21_tax(rec21);
					}
					
					BigDecimal tax = null;
					String steuerVermerk = null;
					if (((BigDecimal)this.getValueJustInTime(RecA1.key,BG_ATOM.e_preis_basiswaehrung)).compareTo(BigDecimal.ZERO)>=0) {
						tax = 			recTax.getSteuerSatzKorrigiert((Date)this.getValueJustInTime(RecA1.key, BG_ATOM.datum_ausfuehrung));
						steuerVermerk = recTax.getUfs(TAX.steuervermerk);
					} else {
						tax = recTaxNeg.getSteuerSatzKorrigiert((Date)this.getValueJustInTime(RecA1.key, BG_ATOM.datum_ausfuehrung));
						steuerVermerk = recTax.getUfs(TAX.steuervermerk);
					}
					
					this._setMaskValue(RecA1.key, BG_ATOM.steuersatz, 		new MyBigDecimal(tax).get_FormatedRoundedNumber(2));
					this._setMaskValue(RecA1.key, BG_ATOM.eu_steuer_vermerk,steuerVermerk);
				} else {
					bibMSG.MV()._addWarn(S.ms("Quell-Seite: Nach einem Preisabschluss kann keine Steuerfindung mehr erfolgen !"));
				}

				
				if (!this.isRightPreisAbschluss()) {
					this._setMaskValue(RecA2.key, BG_ATOM.intrastat_meld, 	handelsdef.getUfs(HANDELSDEF.intrastat_meld_out, "N"));
					this._setMaskValue(RecA2.key, BG_ATOM.transit_meld, 	handelsdef.getUfs(HANDELSDEF.transit_vk,   "N"));
					this._setMaskValue(RecA2.key, BG_ATOM.id_tax, 			handelsdef.getFs(HANDELSDEF.id_tax_ziel));
					
					Rec21_tax recTax = new Rec21_tax(handelsdef.get_up_Rec21(HANDELSDEF.id_tax_ziel, TAX.id_tax, true));
					Rec21_tax recTaxNeg = recTax;
					
					Rec21     rec21 = handelsdef.get_up_Rec21(HANDELSDEF.id_tax_negativ_ziel, TAX.id_tax, true);
					if (rec21 != null) {
						recTaxNeg = new Rec21_tax(rec21);
					}
					
					BigDecimal tax = null;
					String steuerVermerk = null;
					if (((BigDecimal)this.getValueJustInTime(RecA2.key,BG_ATOM.e_preis_basiswaehrung)).compareTo(BigDecimal.ZERO)>=0) {
						tax = 			recTax.getSteuerSatzKorrigiert((Date)this.getValueJustInTime(RecA2.key, BG_ATOM.datum_ausfuehrung));
						steuerVermerk = recTax.getUfs(TAX.steuervermerk);
					} else {
						tax = recTaxNeg.getSteuerSatzKorrigiert((Date)this.getValueJustInTime(RecA2.key, BG_ATOM.datum_ausfuehrung));
						steuerVermerk = recTax.getUfs(TAX.steuervermerk);
					}
					
					this._setMaskValue(RecA2.key, BG_ATOM.steuersatz, 		new MyBigDecimal(tax).get_FormatedRoundedNumber(2));
					this._setMaskValue(RecA2.key, BG_ATOM.eu_steuer_vermerk,steuerVermerk);
				} else {
					bibMSG.MV()._addWarn(S.ms("Ziel-Seite: Nach einem Preisabschluss kann keine Steuerfindung mehr erfolgen !"));
				}
				
				this._setMaskValue(RecV.key, BG_VEKTOR.id_handelsdef, handelsdef.getIdLong().toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			bibMSG.MV()._add(e);
		}
		
	}
	
	
	
}
