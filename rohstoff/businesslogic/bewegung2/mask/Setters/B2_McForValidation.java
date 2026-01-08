/**
 * rohstoff.businesslogic.bewegung2.mask.Setters
 * @author martin
 * @date 09.01.2020
 * 
 */
package rohstoff.businesslogic.bewegung2.mask.Setters;

import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.basics4project.ENUM_MANDANT_SESSION_STORE;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.EINHEIT;
import panter.gmbh.basics4project.DB_ENUMS.MANDANT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.O;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_artikel_bez;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_mandant;
import rohstoff.businesslogic.bewegung2.global.B2_EnumMaskComponentKeys;
import rohstoff.businesslogic.bewegung2.global.EnPositionStation;
import rohstoff.businesslogic.bewegung2.global.EnTransportTyp;
import rohstoff.businesslogic.bewegung2.recs.RecA1;
import rohstoff.businesslogic.bewegung2.recs.RecA2;
import rohstoff.businesslogic.bewegung2.recs.RecS1;
import rohstoff.businesslogic.bewegung2.recs.RecV;

/**
 * @author martin
 * @date 09.01.2020
 *
 */
public class B2_McForValidation extends B2_MaskController {

	/**
	 * @author martin
	 * @date 09.01.2020
	 *
	 * @param p_component
	 * @throws myException
	 */
	public B2_McForValidation(IF_RB_Component p_component) throws myException {
		super(p_component);
	}

	/**
	 * @author martin
	 * @date 09.01.2020
	 *
	 * @param p_componentMap
	 * @throws myException
	 */
	public B2_McForValidation(RB_ComponentMap p_componentMap) throws myException {
		super(p_componentMap);
	}



	
	public B2_McForValidation(IF_RB_Component p_component, MyE2_MessageVector mv) {
		super(p_component, mv);
	}

	public B2_McForValidation(RB_ComponentMap p_componentMap, MyE2_MessageVector mv) {
		super(p_componentMap, mv);
	}

	/**
	 * univeseller validierer, z.b. fuer das speichern der maske 
	 * @author martin
	 * @date 10.01.2020
	 *
	 * @return
	 */
	public MyE2_MessageVector validateAll() {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		
		_validateArtikelGleichheit(mv);
		_validateArtikelAehnlichkeit(mv);
		_validateEinheitenGleich(mv);
		mv._add(validAbschlussMenge(EnPositionStation.LEFT));
		mv._add(validAbschlussMenge(EnPositionStation.RIGHT));
		mv._add(validAbschlussPreis(EnPositionStation.LEFT));
		mv._add(validAbschlussPreis(EnPositionStation.RIGHT));
		mv._add(validAbschlussLadung(EnPositionStation.LEFT));
		mv._add(validAbschlussLadung(EnPositionStation.RIGHT));
		
		
		//speichern verhindern nur, wenn ein abschluss gesetzt ist
		if (		this.isAbschlussLadungSelected(RecA1.key)
				|| 	this.isAbschlussLadungSelected(RecA2.key) 
				|| 	this.isAbschlussMengenSelected(RecA1.key) 
				|| 	this.isAbschlussMengenSelected(RecA2.key) 
				|| 	this.isAbschlussPreisSelected(RecA1.key) 
				|| 	this.isAbschlussPreisSelected(RecA2.key) 
			) {
			//dann wird als alarm uebergeben
			
		} else {
			if (mv.hasAlarms()) {
				mv._changeErrorsToWarnings();
			}
		}
		
		return mv;
	}
	
	
	
	private B2_McForValidation _validateArtikelAehnlichkeit(MyE2_MessageVector mv) {
		
		try {
			Rec21_mandant recM = (Rec21_mandant)ENUM_MANDANT_SESSION_STORE.REC21_MANDANT.getValueFromSession();
			Long idArtikelBezLeft = getLongLiveVal(RecA1.key,BG_ATOM.id_artikel_bez);
			Long idArtikelBezRight = getLongLiveVal(RecA2.key,BG_ATOM.id_artikel_bez);
			
			if (idArtikelBezLeft!=null && idArtikelBezRight!=null) {
				Rec21 artbezLeft =  new Rec21(_TAB.artikel_bez)._fill_id(idArtikelBezLeft);
				Rec21 artbezRight = new Rec21(_TAB.artikel_bez)._fill_id(idArtikelBezRight);
				
				String anr1Left =	artbezLeft.get_up_Rec21(ARTIKEL.id_artikel).getFs(ARTIKEL.anr1);
				String anr1Right = 	artbezRight.get_up_Rec21(ARTIKEL.id_artikel).getFs(ARTIKEL.anr1);
				
				int zahl = recM.getLongDbValue(MANDANT.anr1_gleichheit_fuhre_stellen).intValue();
				
				if (! ((anr1Left+"            ").substring(0, zahl).trim().equals((anr1Right+"            ").substring(0, zahl)))) {
					mv._addAlarm(S.ms("Die Forderung nach Sortengleichheit in der Warenbewegung mit ").ut(""+zahl).t(" Zeichen ist verletzt!"));
				}
			}
		} catch (Exception e) {
			mv._addAlarm(S.ms("Validierungsfehler:_validateArtikelAehnlichkeit(): <a3f041ac-808a-11e9-bc42-526af7764f64>"));
			e.printStackTrace();
		}

		
		
		return this;
	}
	
	
	
	private B2_McForValidation _validateArtikelGleichheit(MyE2_MessageVector mv) {
		
		try {
			EnTransportTyp 		typ = 			this.getTransportArt();
			Rec21_artikel_bez 	artbezLeft = 	this.getArtbezQuelle();
			Rec21_artikel_bez 	artbezRight = 	this.getArtbezZiel();
			
			if (typ !=null && typ.isSortenGleichheit() && artbezLeft!=null && artbezRight!=null && !this.isSorteStartZielGleich()) {
	
				if (artbezLeft.getLongDbValue(ARTIKEL_BEZ.id_artikel).longValue()!=artbezRight.getLongDbValue(ARTIKEL_BEZ.id_artikel).longValue()) {
					mv._addAlarm(S.ms("Die Quellsorte ist nicht gleich der Zielsorte, obwohl die Transportart <").ut(typ.userText()+"> dieses verlangt!"));
				}
			}
		} catch (Exception e) {
			mv._addAlarm(S.ms("Validierungsfehler:_validateArtikelGleichheit(): <a3f041ac-808a-11e9-bc42-526af7764f64>"));
			e.printStackTrace();
		}
		
		return this;
	}

	
	


	private B2_McForValidation _validateEinheitenGleich(MyE2_MessageVector mv) {
		try {
			
			Long idArtbezQuelle = 	this.getLongLiveVal(RecA1.key,BG_ATOM.id_artikel_bez.fk());;
			Long idArtbezZiel = 	this.getLongLiveVal(RecA2.key,BG_ATOM.id_artikel_bez.fk());;
			
			Rec21 einheitQuelle = 	null;
			Rec21 einheitZiel = 	null;
			
			if (O.isNotNull(idArtbezQuelle)) {
				einheitQuelle = new Rec21_artikel_bez()._fill_id(idArtbezQuelle).getEinheit();
			}
			if (O.isNotNull(idArtbezZiel)) {
				einheitZiel = new Rec21_artikel_bez()._fill_id(idArtbezZiel).getEinheit();
			}

			String eh = "-";
			
			if (einheitQuelle!=null && einheitZiel==null) {
				eh = einheitQuelle.getUfs(EINHEIT.einheitkurz,EINHEIT.einheitlang);

			} else if (einheitQuelle==null && einheitZiel!=null) {
				eh = einheitZiel.getUfs(EINHEIT.einheitkurz,EINHEIT.einheitlang);
			} else  if (einheitQuelle!=null && einheitZiel!=null) {
				if (einheitQuelle.getId()==einheitZiel.getId()) {
					eh = einheitZiel.getUfs(EINHEIT.einheitkurz,EINHEIT.einheitlang);
				} else {
					eh = "ERR";
					mv._addAlarm(S.ms("Sorte auf der Quelleseite hat eine andere Einheit als Sorte auf der Zielseite !!!"));
				}
			} 
			
			String eh2 = eh;
			this.getActionsMaskPreSetters()._a( ()-> {
				this.getComponent(RecV.key, B2_EnumMaskComponentKeys.LABEL_4_EINHEIT.getKey()).rb_set_db_value_manual("-");
			});
			this.getActionsMaskSetters()._a( ()-> {
				this.getComponent(RecV.key, B2_EnumMaskComponentKeys.LABEL_4_EINHEIT.getKey()).rb_set_db_value_manual(eh2);
			});
			
			
			
		} catch (myException e) {
			mv._add(e.get_ErrorMessage());
			e.printStackTrace();
		}

		
		return this;
	}
	
	
	
	public MyE2_MessageVector validAbschlussMenge(EnPositionStation enPositionStation) {
		
		MyE2_MessageVector mv=bibMSG.getNewMV();
		try {
			RB_KM keyAtom = 	enPositionStation.getKeyAtom();
			RB_KM keyStation = 	enPositionStation.getKeyStation();
			
			this._setNormalColorInGrid(keyAtom, BG_ATOM.menge, BG_ATOM.datum_ausfuehrung,BG_ATOM.id_artikel_bez);
			this._setNormalColorInGrid(keyStation, BG_STATION.id_adresse);
		    this._setNormalColorInGrid(RecV.key,  BG_VEKTOR.en_transport_typ, BG_VEKTOR.planmenge, BG_VEKTOR.transportverantwortung);
			
			if (this.isAbschlussMengenSelected(enPositionStation.getKeyAtom())) {
			
				String message = "Für den Mengenabschluss müssen folgende Felder korrekt gefüllt sein: Transportart,Verantwortung, Planmenge, Sorte, Station, Bruttomenge, Leistungsdatum";
				
				if (O.isOneNull(	 this.getValueJustInTime(keyAtom, BG_ATOM.menge)
								   , this.getValueJustInTime(keyAtom, BG_ATOM.datum_ausfuehrung)
								   , this.getValueJustInTime(keyAtom, BG_ATOM.id_artikel_bez)
								   , this.getValueJustInTime(keyStation, BG_STATION.id_adresse)
								   , this.getValueJustInTime(RecV.key,  BG_VEKTOR.en_transport_typ)
								   , this.getValueJustInTime(RecV.key,  BG_VEKTOR.planmenge)
								   , this.getValueJustInTime(RecV.key,  BG_VEKTOR.transportverantwortung)
								   
								   )) {
							mv._addAlarm(message);
							
						Color high = new E2_ColorHelpBackground();	
							
						this._setHighlightColorInGridWhenNull(keyAtom, high,BG_ATOM.menge, BG_ATOM.datum_ausfuehrung,BG_ATOM.id_artikel_bez);
						this._setHighlightColorInGridWhenNull(keyStation, high,BG_STATION.id_adresse);
					    this._setHighlightColorInGridWhenNull(RecV.key,  high,BG_VEKTOR.en_transport_typ, BG_VEKTOR.planmenge, BG_VEKTOR.transportverantwortung);
				}
			}
		} catch (myException e) {
			mv._addAlarm(e.get_ErrorMessage()+"  <6acbece4-5949-11ea-8e2d-0242ac130003>");
			e.printStackTrace();
		} catch (Exception e) {
			mv._addAlarm(e.getLocalizedMessage()+"  <6acbece4-5949-11ea-8e2d-0242ac130003>");
			e.printStackTrace();
		}
		
		return mv;
	}
	
	public MyE2_MessageVector validAbschlussPreis(EnPositionStation enPositionStation) {
		
		VEK<IF_Field> atomFieldToValid = new VEK<IF_Field>()	._a(BG_ATOM.e_preis_basiswaehrung)
																._a(BG_ATOM.e_preis_fremdwaehrung)
																._a(BG_ATOM.waehrungskurs)
																._a(BG_ATOM.id_waehrung)
																._a(BG_ATOM.steuersatz)
																._a(BG_ATOM.eu_steuer_vermerk)
																._a(BG_ATOM.id_bg_pruefprot_menge)
																;
		
		MyE2_MessageVector mv=bibMSG.getNewMV();;
		try {
			
			RB_KM keyAtom = 	enPositionStation.getKeyAtom();

			this._setNormalColorInGrid(keyAtom, atomFieldToValid.getArray());
			
			if (this.isAbschlussPreisSelected(enPositionStation.getKeyAtom())) { 

				Color high = new E2_ColorHelpBackground();	
				
				if (this.isAbschlussMengenSelected(enPositionStation.getKeyAtom())) {
					String message = "Für den Preisabschluss müssen folgende Felder korrekt gefüllt sein: Preis (Basis), Währung, Kurs, Steuersatz, Steuervermerk";
		
					if (O.isOneNull(this.getRawLiveVal(keyAtom, BG_ATOM.e_preis_basiswaehrung)
							,this.getRawLiveVal(keyAtom, BG_ATOM.e_preis_fremdwaehrung)
							,this.getRawLiveVal(keyAtom, BG_ATOM.waehrungskurs)
							,this.getRawLiveVal(keyAtom, BG_ATOM.id_waehrung)
							,this.getRawLiveVal(keyAtom, BG_ATOM.steuersatz)
							,this.getRawLiveVal(keyAtom, BG_ATOM.eu_steuer_vermerk)
							)) {
						mv._addAlarm(message);
						

						//felder markieren
						this._setHighlightColorInGridWhenNull(keyAtom, high,atomFieldToValid.getArray());
					}
				} else {
					mv._addAlarm(S.ms("Bitte zuerst Menge abschliessen !"));
					this._setHighlightColorInGrid(keyAtom, high,BG_ATOM.id_bg_pruefprot_menge);
					
				}
			}
		} catch (myException e) {
			mv._addAlarm(e.get_ErrorMessage()+"  <6acbece4-5949-11ea-8e2d-0242ac130003>");
			e.printStackTrace();
		} catch (Exception e) {
			mv._addAlarm(e.getLocalizedMessage()+"  <6acbece4-5949-11ea-8e2d-0242ac130003>");
			e.printStackTrace();
		}
		
		return mv;
	}

	
	
	
	public MyE2_MessageVector validAbschlussLadung(EnPositionStation enPositionStation) {
		
		MyE2_MessageVector mv=bibMSG.getNewMV();;
		try {
			RB_KM keyAtom = 	enPositionStation.getKeyAtom();
			
			this._setNormalColorInGrid(keyAtom, BG_ATOM.id_bg_pruefprot_menge);
			this._setNormalColorInGrid(keyAtom, BG_ATOM.id_bg_pruefprot_preis);

			
			if (this.isAbschlussLadungSelected(enPositionStation.getKeyAtom())) { 
				if (!this.isAbschlussMengenSelected(enPositionStation.getKeyAtom()) || !this.isAbschlussPreisSelected(enPositionStation.getKeyAtom())) {
					Color high = new E2_ColorHelpBackground();	
					
					mv._addAlarm("Für den Ladungsabschluss müssen zuerst Preis- und Mengenabschluss erfolgen");
					if (!this.getBooleanValueFromScreen(keyAtom, BG_ATOM.id_bg_pruefprot_menge)) {this._setHighlightColorInGrid(keyAtom, high,BG_ATOM.id_bg_pruefprot_menge);}
					if (!this.getBooleanValueFromScreen(keyAtom, BG_ATOM.id_bg_pruefprot_preis)) {this._setHighlightColorInGrid(keyAtom, high,BG_ATOM.id_bg_pruefprot_preis);}
				}
			}
		} catch (myException e) {
			mv._addAlarm(e.get_ErrorMessage()+"  <6acbece4-5949-11ea-8e2d-0242ac130003>");
			e.printStackTrace();
		} catch (Exception e) {
			mv._addAlarm(e.getLocalizedMessage()+"  <6acbece4-5949-11ea-8e2d-0242ac130003>");
			e.printStackTrace();
		}
		
		return mv;
	}

	
}
