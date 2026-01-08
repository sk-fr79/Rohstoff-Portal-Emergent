/**
 * rohstoff.businesslogic.bewegung2.global
 * @author martin
 * @date 08.03.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.mask.Setters;

import java.util.HashMap;

import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.basics4project.ENUM_MANDANT_SESSION_STORE;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.WAEHRUNG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.O;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.HMAP;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_SONDERLAGER;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_VPosKon;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_VPosStd;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_artikel_bez;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_bgVector;
import rohstoff.businesslogic.bewegung2.global.EnPositionStation;
import rohstoff.businesslogic.bewegung2.global.EnTransportTyp;
import rohstoff.businesslogic.bewegung2.list.B2_TransportHashMapAddons;
import rohstoff.businesslogic.bewegung2.mask.B2_MaskComponentMap_VEKTOR;
import rohstoff.businesslogic.bewegung2.mask.Components.B2_MaskComponentPruefungStamp;
import rohstoff.businesslogic.bewegung2.recs.RecA1;
import rohstoff.businesslogic.bewegung2.recs.RecA2;
import rohstoff.businesslogic.bewegung2.recs.RecS1;
import rohstoff.businesslogic.bewegung2.recs.RecS2;
import rohstoff.businesslogic.bewegung2.recs.RecS3;
import rohstoff.businesslogic.bewegung2.recs.RecV;

/**
 * @author martin
 * @date 08.03.2019
 *
 *
 */
public class B2_MaskController extends RB_MaskController {

//	//hier koennen actionen hinterlegt werden, die maskeninhalte als folge einer aktion auf der maske aendern,
//	// beispiel: adresse wurde gesucht und die nachfolgenden felder wurden gesetzt
//	private VEK<IF_agentSimple>		actionsValueChangers = new VEK<>();
//	
//
//	//WICHTIG: werden in einem zusammenhang mehrere Controller hintereinander benoetigt, dann muessen zuerst alle actionsMaskPreSetters am stueck
//	//         durchgefuehrt werden, erst danach die actionsMaskSetters. sonst kann es sein, dass sich die einzelnen Controller die felder nach deaktiveren wieder einschalten !!
//	// hier koennen maskenfelder deaktiviert werden, z.b. fuer lager-lager-fuhren gibt es keinen grund , die 
//	// felder "suche nach kontrakt" oder "suche nach angebot" aktiv zu lassen
//	private VEK<IF_agentSimple>		actionsMaskSetters = new VEK<>();
//	
//	// werden auf in einem controller bestimmte settings vorgenommen, sollten diese vorher resettet werden.
//	// hier koennen diese resets durchgefuehrt werden
//	private VEK<IF_agentSimple>		actionsMaskPreSetters = new VEK<>();
//	
//	
//	
//	
//	private MyE2_MessageVector    messagesErrorInfos = bibMSG.getNewMV();
//	private MyE2_MessageVector    messagesSettingInfos = bibMSG.getNewMV();

	
	/**
	 * @author martin
	 * @date 08.03.2019
	 *
	 * @param p_component
	 * @throws myException
	 */
	public B2_MaskController(IF_RB_Component p_component) throws myException {
		super(p_component);
	}

	public B2_MaskController(RB_ComponentMap p_componentMap) throws myException {
		super(p_componentMap);
	}

	
	public B2_MaskController(IF_RB_Component p_component, MyE2_MessageVector mv) {
		super(p_component,mv);
	}

	public B2_MaskController(RB_ComponentMap p_componentMap, MyE2_MessageVector mv) {
		super(p_componentMap,mv);
	}


	
	
	
	
	
	public boolean isStartAdressFull() throws myException {
		return this.getLongLiveVal(RecS1.key, BG_STATION.id_adresse)!=null;
	}


	public boolean isZielAdressFull() throws myException {
		return this.getLongLiveVal(RecS3.key, BG_STATION.id_adresse)!=null;
	}

	
	
	protected void init() {
	}
	
	
	/**
	 * 
	 * @author martin
	 * @date 08.03.2019
	 *
	 * @return s true, when startadresse is full and adress in own range
	 * @throws myException
	 */
	public boolean isStartAdressFullAndOwn() throws myException {
		Long id_adresse = this.getLongLiveVal(RecS1.key, BG_STATION.id_adresse);
		Rec21_adresse ra = null;
		if (id_adresse!=null) {
			ra = new Rec21_adresse()._fill_id(id_adresse);
			return ra.isAdressOfMandant();
		}
		return false;
	}

	
	/**
	 * 
	 * @author martin
	 * @date 08.03.2019
	 *
	 * @return s true, when zieladresse is full and adress in own range
	 * @throws myException
	 */
	public boolean isZielAdressFullAndOwn() throws myException {
		Long id_adresse = (Long)this.getValueJustInTime(RecS3.key, BG_STATION.id_adresse);
		Rec21_adresse ra = null;
		if (id_adresse!=null) {
			ra = new Rec21_adresse()._fill_id(id_adresse);
			return ra.isAdressOfMandant();
		}
		return false;
	}

	
	public Rec21_adresse getStartAdresse() throws myException {
		Long id_adresse = (Long)this.getValueJustInTime(RecS1.key, BG_STATION.id_adresse);
		Rec21_adresse ra = null;
		if (id_adresse!=null) {
			ra = new Rec21_adresse()._fill_id(id_adresse);
		}
		return ra;
	}

	
	public Rec21_adresse getZielAdresse() throws myException {
		Long id_adresse = (Long)this.getValueJustInTime(RecS3.key, BG_STATION.id_adresse);
		Rec21_adresse ra = null;
		if (id_adresse!=null) {
			ra = new Rec21_adresse()._fill_id(id_adresse);
		}
		return ra;
	}
	
	public Rec21_adresse getAdresse(EnPositionStation posStation) throws myException {
		Long idAdresse = null;
		switch (posStation) {
			case LEFT:
				idAdresse = this.getLongLiveVal(RecS1.key, BG_STATION.id_adresse);
				break;
				
			case MID:				
				idAdresse = this.getLongLiveVal(RecS2.key, BG_STATION.id_adresse);
				break;
				
			case RIGHT:
				idAdresse = this.getLongLiveVal(RecS3.key, BG_STATION.id_adresse);
				break;
		
			case UNDEF:
				idAdresse =null;
				break;
				
			default:
				idAdresse = null;
		
		}

		Rec21_adresse ra = null;
		if (idAdresse != null) {
			ra = new Rec21_adresse()._fill_id(idAdresse);
		} 
		
		return ra;
		
	}
	
	
	
	public Rec21_VPosKon getKontraktPosStart() throws myException {
		Long id_vposKon = this.getLongLiveVal(RecA1.key, BG_ATOM.id_vpos_kon);
		if (id_vposKon!=null) {
			return (Rec21_VPosKon)new Rec21_VPosKon()._fill_id(id_vposKon);
		}
		return null;
	}

	public Rec21_VPosKon getKontraktPosZiel() throws myException {
		Long id_vposKon = this.getLongLiveVal(RecA2.key, BG_ATOM.id_vpos_kon);
		if (id_vposKon!=null) {
			return (Rec21_VPosKon)new Rec21_VPosKon()._fill_id(id_vposKon);
		}
		return null;
	}

	
	public Rec21_VPosStd getAngebotPosStart() throws myException {
		Long id_vposStd = this.getLongLiveVal(RecA1.key, BG_ATOM.id_vpos_std);
		if (id_vposStd!=null) {
			return new Rec21_VPosStd()._fill_id(id_vposStd);
		}
		return null;
	}

	public Rec21_VPosStd getAngebotPosZiel() throws myException {
		Long id_vposStd = this.getLongLiveVal(RecA2.key, BG_ATOM.id_vpos_std);
		if (id_vposStd!=null) {
			return new Rec21_VPosStd()._fill_id(id_vposStd);
		}
		return null;
	}


	public Rec21_artikel_bez getArtbezQuelle() throws myException {
		Long id_artBez = (Long)this.getValueJustInTime(RecA1.key, BG_ATOM.id_artikel_bez);
		if (id_artBez!=null) {
			return new Rec21_artikel_bez()._fill_id(id_artBez);
		}
		return null;
	}

	public Rec21_artikel_bez getArtbezZiel() throws myException {
		Long id_artBez =  (Long)this.getValueJustInTime(RecA2.key, BG_ATOM.id_artikel_bez);
		if (id_artBez!=null) {
			return new Rec21_artikel_bez()._fill_id(id_artBez);
		}
		return null;
	}


	
	
	/**
	 * 
	 * @author martin
	 * @date 08.03.2019
	 *
	 * @return s true, when startadresse is full and adress in own range
	 * @throws myException
	 */
	public boolean isStartAdressFullAndNotOwn() throws myException {
		Long id_adresse = this.getLongLiveVal(RecS1.key, BG_STATION.id_adresse);
		Rec21_adresse ra = null;
		if (id_adresse!=null) {
			ra = new Rec21_adresse()._fill_id(id_adresse);
			return !ra.isAdressOfMandant();
		}
		return false;
	}

	
	/**
	 * 
	 * @author martin
	 * @date 08.03.2019
	 *
	 * @return s true, when Fredmbesitzer is full and adress in own range
	 * @throws myException
	 */
	public boolean isFremdBesitzerAdressFullAndNotOwn() throws myException {
		Long id_adresse = this.getLongLiveVal(RecV.key, BG_VEKTOR.id_adresse_fremdware);
		Rec21_adresse ra = null;
		if (id_adresse!=null) {
			ra = new Rec21_adresse()._fill_id(id_adresse);
			return !ra.isAdressOfMandant();
		}
		return false;
	}

	/**
	 * 
	 * @author martin
	 * @date 08.03.2019
	 *
	 * @return s true, when Fredmbesitzer is full and adress in own range
	 * @throws myException
	 */
	public boolean isFremdBesitzerAdressFullAndOwn() throws myException {
		Long id_adresse = this.getLongLiveVal(RecV.key, BG_VEKTOR.id_adresse_fremdware);
		Rec21_adresse ra = null;
		if (id_adresse!=null) {
			ra = new Rec21_adresse()._fill_id(id_adresse);
			return ra.isAdressOfMandant();
		}
		return false;
	}

	
	
	/**
	 * 
	 * @author martin
	 * @date 08.03.2019
	 *
	 * @return s true, when zieladresse is full and adress in own range
	 * @throws myException
	 */
	public boolean isZielAdressFullAndNotOwn() throws myException {
		Long id_adresse = this.getLongLiveVal(RecS3.key, BG_STATION.id_adresse);
		Rec21_adresse ra = null;
		if (id_adresse!=null) {
			ra = new Rec21_adresse()._fill_id(id_adresse);
			return !ra.isAdressOfMandant();
		}
		return false;
	}

	
	
	public boolean isEKKontraktFull() throws myException {
		return this.getLongLiveVal(RecA1.key, BG_ATOM.id_vpos_kon)!=null;
	}

	public boolean isVKKontraktFull() throws myException {
		return this.getLongLiveVal(RecA2.key, BG_ATOM.id_vpos_kon)!=null;
	}
	public boolean isEKAngebotFull() throws myException {
		return this.getLongLiveVal(RecA1.key, BG_ATOM.id_vpos_std)!=null;
	}

	public boolean isVKAngebotFull() throws myException {
		return this.getLongLiveVal(RecA2.key, BG_ATOM.id_vpos_std)!=null;
	}

	
	public EnTransportTyp getTransportArt() throws myException {
		EnTransportTyp ret = null;
		
		String s_transport = this.get_liveVal(RecV.key, BG_VEKTOR.en_transport_typ);
		
		ret = EnTransportTyp.WA.getEnum(s_transport);
		
		return ret;
	}
	
	

	public boolean isNewCopy() throws myException {
		return this.getRbComponentMapStatus(RecV.key)==MASK_STATUS.NEW_COPY;
	}

	public boolean isNewOrNewCopy() throws myException {
		return this.isNew() || this.isNewCopy();
	}

	
	public boolean isEdit() throws myException {
		return this.getRbComponentMapStatus(RecV.key)==MASK_STATUS.EDIT;
	}

	public boolean isViewOnly() throws myException {
		return this.getRbComponentMapStatus(RecV.key)==MASK_STATUS.VIEW;
	}
	
	
	public boolean isSaveable() throws myException {
		return this.getRbComponentMapStatus(RecV.key).isStatusCanBeSaved();
	}

	
	
	/**
	 * 
	 * @author martin
	 * @date 08.03.2019
	 *
	 * @return s true, when startadresse is full and adress in own range
	 * @throws myException
	 */
	public boolean isStartBesitzerFullAndOwn() throws myException {
		Long id_adresse = this.getLongLiveVal(RecS1.key, BG_STATION.id_adresse_besitz_ldg);
		Rec21_adresse ra = null;
		if (id_adresse!=null) {
			ra = new Rec21_adresse()._fill_id(id_adresse);
			return ra.isAdressOfMandant();
		}
		return false;
	}

	
	/**
	 * 
	 * @author martin
	 * @date 08.03.2019
	 *
	 * @return s true, when startadresse is full and adress in own range
	 * @throws myException
	 */
	public boolean isMidBesitzerFullAndOwn() throws myException {
		Long id_adresse = this.getLongLiveVal(RecS2.key, BG_STATION.id_adresse_besitz_ldg);
		Rec21_adresse ra = null;
		if (id_adresse!=null) {
			ra = new Rec21_adresse()._fill_id(id_adresse);
			return ra.isAdressOfMandant();
		}
		return false;
	}

	
	/**
	 * 
	 * @author martin
	 * @date 08.03.2019
	 *
	 * @return s true, when startadresse is full and adress in own range
	 * @throws myException
	 */
	public boolean isZielBesitzerFullAndOwn() throws myException {
		Long id_adresse = this.getLongLiveVal(RecS3.key, BG_STATION.id_adresse_besitz_ldg);
		Rec21_adresse ra = null;
		if (id_adresse!=null) {
			ra = new Rec21_adresse()._fill_id(id_adresse);
			return ra.isAdressOfMandant();
		}
		return false;
	}

	

	
	
	/**
	 * 
	 * @author martin
	 * @date 08.03.2019
	 *
	 * @return s true, when startadresse is full and adress in own range
	 * @throws myException
	 */
	public boolean isStartBesitzerFullAndLieferant() throws myException {
		Long id_adresseBesitzer = this.getLongLiveVal(RecS1.key, BG_STATION.id_adresse_besitz_ldg);
		Long id_adresseLieferant = this.getLongLiveVal(RecS1.key, BG_STATION.id_adresse);
		Rec21_adresse aBesitzer = null;
		Rec21_adresse aLieferant = null;
		
		if (O.isNoOneNull(id_adresseBesitzer,id_adresseLieferant)) {
			aBesitzer = new Rec21_adresse()._fill_id(id_adresseBesitzer)._getMainAdresse();
			aLieferant = new Rec21_adresse()._fill_id(id_adresseLieferant)._getMainAdresse();
			return aBesitzer.getId()==aLieferant.getId();
		}
		return false;
	}

	
	/**
	 * 
	 * @author martin
	 * @date 08.03.2019
	 *
	 * @return s true, when startadresse is full and adress in own range
	 * @throws myException
	 */
	public boolean isMidBesitzerFullAndLieferant() throws myException {
		Long id_adresseBesitzer = this.getLongLiveVal(RecS2.key, BG_STATION.id_adresse_besitz_ldg);
		Long id_adresseLieferant = this.getLongLiveVal(RecS1.key, BG_STATION.id_adresse);
		Rec21_adresse aBesitzer = null;
		Rec21_adresse aLieferant = null;
		
		if (O.isNoOneNull(id_adresseBesitzer,id_adresseLieferant)) {
			aBesitzer = new Rec21_adresse()._fill_id(id_adresseBesitzer)._getMainAdresse();
			aLieferant = new Rec21_adresse()._fill_id(id_adresseLieferant)._getMainAdresse();
			return aBesitzer.getId()==aLieferant.getId();
		}
		return false;
	}

	
	/**
	 * 
	 * @author martin
	 * @date 08.03.2019
	 *
	 * @return s true, when startadresse is full and adress in own range
	 * @throws myException
	 */
	public boolean isZielBesitzerFullAndLieferat() throws myException {
		Long id_adresseBesitzer = this.getLongLiveVal(RecS3.key, BG_STATION.id_adresse_besitz_ldg);
		Long id_adresseLieferant = this.getLongLiveVal(RecS1.key, BG_STATION.id_adresse);
		Rec21_adresse aBesitzer = null;
		Rec21_adresse aLieferant = null;
		
		if (O.isNoOneNull(id_adresseBesitzer,id_adresseLieferant)) {
			aBesitzer = new Rec21_adresse()._fill_id(id_adresseBesitzer)._getMainAdresse();
			aLieferant = new Rec21_adresse()._fill_id(id_adresseLieferant)._getMainAdresse();
			return aBesitzer.getId()==aLieferant.getId();
		}
		return false;
	}


	
	
	
	/**
	 * 
	 * @author martin
	 * @date 08.03.2019
	 *
	 * @return s true, when startadresse is full and adress in own range
	 * @throws myException
	 */
	public boolean isStartBesitzerFullAndAbnehmer() throws myException {
		Long id_adresseBesitzer = this.getLongLiveVal(RecS1.key, BG_STATION.id_adresse_besitz_ldg);
		Long id_adresseAbnehmer = this.getLongLiveVal(RecS3.key, BG_STATION.id_adresse);
		Rec21_adresse aBesitzer = null;
		Rec21_adresse aLieferant = null;
		
		if (O.isNoOneNull(id_adresseBesitzer,id_adresseAbnehmer)) {
			aBesitzer = new Rec21_adresse()._fill_id(id_adresseBesitzer)._getMainAdresse();
			aLieferant = new Rec21_adresse()._fill_id(id_adresseAbnehmer)._getMainAdresse();
			return aBesitzer.getId()==aLieferant.getId();
		}
		return false;
	}

	
	/**
	 * 
	 * @author martin
	 * @date 08.03.2019
	 *
	 * @return s true, when startadresse is full and adress in own range
	 * @throws myException
	 */
	public boolean isMidBesitzerFullAndAbnehmer() throws myException {
		Long id_adresseBesitzer = this.getLongLiveVal(RecS2.key, BG_STATION.id_adresse_besitz_ldg);
		Long id_adresseAbnehmer = this.getLongLiveVal(RecS3.key, BG_STATION.id_adresse);
		Rec21_adresse aBesitzer = null;
		Rec21_adresse aLieferant = null;
		
		if (O.isNoOneNull(id_adresseBesitzer,id_adresseAbnehmer)) {
			aBesitzer = new Rec21_adresse()._fill_id(id_adresseBesitzer)._getMainAdresse();
			aLieferant = new Rec21_adresse()._fill_id(id_adresseAbnehmer)._getMainAdresse();
			return aBesitzer.getId()==aLieferant.getId();
		}
		return false;
	}

	
	/**
	 * 
	 * @author martin
	 * @date 08.03.2019
	 *
	 * @return s true, when startadresse is full and adress in own range
	 * @throws myException
	 */
	public boolean isZielBesitzerFullAndAbnehmer() throws myException {
		Long id_adresseBesitzer = this.getLongLiveVal(RecS3.key, BG_STATION.id_adresse_besitz_ldg);
		Long id_adresseAbnehmer = this.getLongLiveVal(RecS3.key, BG_STATION.id_adresse);
		Rec21_adresse aBesitzer = null;
		Rec21_adresse aLieferant = null;
		
		if (O.isNoOneNull(id_adresseBesitzer,id_adresseAbnehmer)) {
			aBesitzer = new Rec21_adresse()._fill_id(id_adresseBesitzer)._getMainAdresse();
			aLieferant = new Rec21_adresse()._fill_id(id_adresseAbnehmer)._getMainAdresse();
			return aBesitzer.getId()==aLieferant.getId();
		}
		return false;
	}


	
	public boolean isAllBesitzersAreDefined() {
		boolean ret = false;
		
		try {
			Long id_adresseBesitzerLeft = this.getLongLiveVal(RecS1.key, BG_STATION.id_adresse_besitz_ldg);
			Long id_adresseBesitzerMid = this.getLongLiveVal(RecS2.key, BG_STATION.id_adresse_besitz_ldg);
			Long id_adresseBesitzerRight = this.getLongLiveVal(RecS3.key, BG_STATION.id_adresse_besitz_ldg);
			
			ret = O.isNoOneNull(id_adresseBesitzerLeft,id_adresseBesitzerMid,id_adresseBesitzerRight);
			
		} catch (myException e) {
			e.printStackTrace();
			ret = false;
		}

		
		return ret;
	}
	
	
	
	public MASK_STATUS getStatus() {
		try {
			return this.getRbComponentMapStatus(RecV.key);
		} catch (myException e) {
			e.printStackTrace();
			return null;
		}
	}
	

	
	public Long getIdPruefungMengeLeft() {
		try {
			return this.getLongLiveVal(RecA1.key, BG_ATOM.id_bg_pruefprot_menge);
		} catch (myException e) {
			e.printStackTrace();
			bibMSG.MV()._addAlarm("Fehler beim Auslesen eines Maskenwertes: <999e37aa-56e0-11e9-8647-d663bd873d93>");
			return null;
		}
	}
	
	public Long getIdPruefungMengeRight() {
		try {
			return this.getLongLiveVal(RecA2.key, BG_ATOM.id_bg_pruefprot_menge);
		} catch (myException e) {
			e.printStackTrace();
			bibMSG.MV()._addAlarm("Fehler beim Auslesen eines Maskenwertes: <d0945780-56e0-11e9-8647-d663bd873d93>");
			return null;
		}
	}
	
	public Long getIdPruefungPreisLeft() {
		try {
			return this.getLongLiveVal(RecA1.key, BG_ATOM.id_bg_pruefprot_preis);
		} catch (myException e) {
			e.printStackTrace();
			bibMSG.MV()._addAlarm("Fehler beim Auslesen eines Maskenwertes: <da0e7f20-56e0-11e9-8647-d663bd873d93>");
			return null;
		}
	}
	
	
	/**
	 * 
	 * @author martin
	 * @date 12.08.2020
	 *
	 * @return
	 * @throws Exception
	 */
	public boolean isLeftPreisAbschluss() throws Exception {
		return ( this.getValueJustInTime(RecA1.key,BG_ATOM.id_bg_pruefprot_preis.fk())!=null && this.getValueJustInTime(RecA1.key,BG_ATOM.id_bg_pruefprot_preis.fk()) instanceof Long);
	}
	
	
	/**
	 * 
	 * @author martin
	 * @date 12.08.2020
	 *
	 * @return
	 * @throws Exception
	 */
	public boolean isRightPreisAbschluss() throws Exception {
		return ( this.getValueJustInTime(RecA2.key,BG_ATOM.id_bg_pruefprot_preis.fk())!=null && this.getValueJustInTime(RecA2.key,BG_ATOM.id_bg_pruefprot_preis.fk()) instanceof Long);
	}
	
	
	
	public Long getIdPruefungPreisRight() {
		try {
			return this.getLongLiveVal(RecA2.key, BG_ATOM.id_bg_pruefprot_preis);
		} catch (myException e) {
			e.printStackTrace();
			bibMSG.MV()._addAlarm("Fehler beim Auslesen eines Maskenwertes: <de961152-56e0-11e9-8647-d663bd873d93>");
			return null;
		}
	}
	public Long getIdPruefungAbschlussLeft() {
		try {
			return this.getLongLiveVal(RecA1.key, BG_ATOM.id_bg_pruefprot_abschluss);
		} catch (myException e) {
			e.printStackTrace();
			bibMSG.MV()._addAlarm("Fehler beim Auslesen eines Maskenwertes: <e37cdd22-56e0-11e9-8647-d663bd873d93>");
			return null;
		}
	}
	
	public Long getIdPruefungAbschlussRight() {
		try {
			return this.getLongLiveVal(RecA2.key, BG_ATOM.id_bg_pruefprot_abschluss);
		} catch (myException e) {
			e.printStackTrace();
			bibMSG.MV()._addAlarm("Fehler beim Auslesen eines Maskenwertes: <e8548782-56e0-11e9-8647-d663bd873d93>");
			return null;
		}
	}
	

	public Long getIdPruefungMenge(EnPositionStation m_posStation) {
		if (m_posStation == EnPositionStation.LEFT) {
			return this.getIdPruefungMengeLeft();
		} else {
			return this.getIdPruefungMengeRight();
		}
	}
	
	public Long getIdPruefungPreis(EnPositionStation m_posStation) {
		if (m_posStation == EnPositionStation.LEFT) {
			return this.getIdPruefungPreisLeft();
		} else {
			return this.getIdPruefungPreisRight();
		}
	}

	public Long getIdPruefungAbschluss(EnPositionStation m_posStation) {
		if (m_posStation == EnPositionStation.LEFT) {
			return this.getIdPruefungAbschlussLeft();
		} else {
			return this.getIdPruefungAbschlussRight();
		}
	}

	public Color getHighlightColorSearchblocks() {
		
		Color highLight = (Color) ((B2_MaskComponentMap_VEKTOR)this.get_ComponentMapCollector().get(RecV.key)).getParams().getFromExtender(B2_TransportHashMapAddons.HIGHLIGHT_COLOR_SEARCHBLOCKS);
		
		return highLight;
		
	}
	
	
	public boolean isSorteStartZielGleich() throws myException {
		boolean ret = false;
		
		Long id_artikelbez_start  = O.NN(this.getLongLiveVal(RecA1.key, BG_ATOM.id_artikel_bez),-1l);
		Long id_artikelbez_ziel   = O.NN(this.getLongLiveVal(RecA2.key, BG_ATOM.id_artikel_bez),-1l);

		if (id_artikelbez_start.longValue()==id_artikelbez_ziel.longValue()) {
			ret = true;
		}
		
		return ret;
 	}
 	

	public EnTransportTyp  getEnTransportTyp() {
		try {
			String transportyp = this.get_liveVal(RecV.key, BG_VEKTOR.en_transport_typ);
			if (S.isFull(transportyp)) {
				EnTransportTyp typ = EnTransportTyp.AUSBUCHUNG.getEnum(transportyp);
				return typ;
			}
		} catch (myException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	


	/**
	 * 
	 * @author martin
	 * @date 15.01.2020
	 *
	 * @param key (RecA1.key oder RecA2.key)
	 * @return
	 * @throws myException 
	 */
	public Rec21 getTax(RB_KM key) throws myException {
		Rec21 tax = null;
		Long idTax = this.getLongLiveVal(key, BG_ATOM.id_tax);
		if (idTax!=null) {
			tax = new Rec21(_TAB.tax)._fill_id(idTax);
		}
		return tax;
		
	}

	
	public Rec21_bgVector getVector() {
		
		try {
			Rec21_bgVector vektor = null;
			
			RB_ComponentMapCollector collector = this.get_ComponentMapCollector();
			RB_ComponentMap mapVektor = collector.get(RecV.key);
			
			vektor = new Rec21_bgVector((Rec21)mapVektor.getRbDataObjectActual());
			
			return vektor;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	/**
	 * 
	 * @author martin
	 * @date 27.02.2020
	 *
	 * @param keyAtom
	 * @return
	 */
	public Boolean isAbschlussMengenSelected(RB_KM keyAtom) {
		Boolean ret = null;
		
		try {
			B2_MaskComponentPruefungStamp pruefComponent = (B2_MaskComponentPruefungStamp) this.get_comp(keyAtom, BG_ATOM.id_bg_pruefprot_menge);
			
			ret = pruefComponent.getCheckBox().isSelected();
			
		} catch (Exception e) {
			e.printStackTrace();
			ret = null;
		}
		
		return ret;
	}
	
	
	/**
	 * 
	 * @author martin
	 * @date 27.02.2020
	 *
	 * @param keyAtom
	 * @return
	 */
	public Boolean isAbschlussPreisSelected(RB_KM keyAtom) {
		Boolean ret = null;
		
		try {
			B2_MaskComponentPruefungStamp pruefComponent = (B2_MaskComponentPruefungStamp) this.get_comp(keyAtom, BG_ATOM.id_bg_pruefprot_preis);
			
			ret = pruefComponent.getCheckBox().isSelected();
			
		} catch (Exception e) {
			e.printStackTrace();
			ret = null;
		}
		
		return ret;
	}
	

	/**
	 * 
	 * @author martin
	 * @date 27.02.2020
	 *
	 * @param keyAtom
	 * @return
	 */
	public Boolean isAbschlussLadungSelected(RB_KM keyAtom) {
		Boolean ret = null;
		
		try {
			B2_MaskComponentPruefungStamp pruefComponent = (B2_MaskComponentPruefungStamp) this.get_comp(keyAtom, BG_ATOM.id_bg_pruefprot_abschluss);
			
			ret = pruefComponent.getCheckBox().isSelected();
			
		} catch (Exception e) {
			e.printStackTrace();
			ret = null;
		}
		
		return ret;
	}
	
	
	
	/**
	 * 
	 * @author martin
	 * @date 27.02.2020
	 *
	 * @param keyAtom
	 * @return
	 */
	public B2_MaskController _setAbschlussMengenSelected(RB_KM keyAtom, boolean selected) {
		
		try {
			B2_MaskComponentPruefungStamp pruefComponent = (B2_MaskComponentPruefungStamp) this.get_comp(keyAtom, BG_ATOM.id_bg_pruefprot_menge);
			pruefComponent.getCheckBox().setSelected(selected);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return this;
	}
	
	
	/**
	 * 
	 * @author martin
	 * @date 27.02.2020
	 *
	 * @param keyAtom
	 * @return
	 */
	public B2_MaskController _setAbschlussPreisSelected(RB_KM keyAtom, boolean selected) {
		
		try {
			B2_MaskComponentPruefungStamp pruefComponent = (B2_MaskComponentPruefungStamp) this.get_comp(keyAtom, BG_ATOM.id_bg_pruefprot_preis);
			pruefComponent.getCheckBox().setSelected(selected);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return this;
	}
	

	/**
	 * 
	 * @author martin
	 * @date 27.02.2020
	 *
	 * @param keyAtom
	 * @return
	 */
	public B2_MaskController _setAbschlussLadungSelected(RB_KM keyAtom, boolean selected) {
		try {
			B2_MaskComponentPruefungStamp pruefComponent = (B2_MaskComponentPruefungStamp) this.get_comp(keyAtom, BG_ATOM.id_bg_pruefprot_abschluss);
			
			pruefComponent.getCheckBox().setSelected(selected);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return this;
	}

	
	
	public B2_MaskController _setDisabledLeft(IF_Field field) throws Exception {
		_TAB tab = field._t();
		
		switch (tab) {
		case bg_vektor:
			this._setDisabled(RecV.key, field);
			break;
			
		case bg_atom:
			this._setDisabled(RecA1.key, field);
			break;

		case bg_station:
			this._setDisabled(RecS1.key, field);
			break;
		default:
			break;
		}
		return this;
	}
	
	public B2_MaskController _setDisabledRight(IF_Field field) throws Exception {
		_TAB tab = field._t();
		
		switch (tab) {
		case bg_vektor:
			this._setDisabled(RecV.key, field);
			break;
			
		case bg_atom:
			this._setDisabled(RecA2.key, field);
			break;

		case bg_station:
			this._setDisabled(RecS3.key, field);
			break;
		default:
			break;
		}
		return this;
	}
	
	
	
	
	public Rec21  _getRec21Waehrung(EnPositionStation posStation) throws Exception {
		RB_KM maskKey = (posStation == EnPositionStation.LEFT)?RecA1.key:RecA2.key;
		Long idWaehrung = this.getLongLiveVal(maskKey,BG_ATOM.id_waehrung);
		
		if (idWaehrung == null) {
			return null;
		} else {
			return new Rec21(_TAB.waehrung)._fill_id(idWaehrung);
		}
	}
	
	
	
	public Rec21  _getRec21WaehrungMandant() throws Exception {
		Rec21_adresse 	mandant = (Rec21_adresse)ENUM_MANDANT_SESSION_STORE.MANDANTEN_ADRESS_REC21.getValueFromSession();
		return	mandant.get_up_Rec21(ADRESSE.id_waehrung,WAEHRUNG.id_waehrung,true);
	}

	
	
	public Rec21_adresse getSonderlager(ENUM_SONDERLAGER lager) {
		Rec21_adresse adresseSonderlager = null;
		try {
			@SuppressWarnings("unchecked")
			HashMap<ENUM_SONDERLAGER,Rec21> sonderLager = (HMAP<ENUM_SONDERLAGER, Rec21>)ENUM_MANDANT_SESSION_STORE.ALL_SONDERLAGER_HASHMAP.getValueFromSession();
			
			Rec21 ad= sonderLager.get(lager);
			if (ad !=null) {
				return new Rec21_adresse(ad);
			}
			
		} catch (myException e) {
			e.printStackTrace();
		}
		
		return adresseSonderlager;
	}
	
	
	


	public boolean isLeftPreisManuel() throws myException  {
		return S.NN(((String)this.getValueFromScreen(RecA1.key, BG_ATOM.manuell_preis))).equals("Y");
	}
	
	
	public boolean isRightPreisManuel() throws myException  {
		return S.NN(((String)this.getValueFromScreen(RecA2.key, BG_ATOM.manuell_preis))).equals("Y");
	}
	
	
	
	public boolean isLeftSiteToBill() throws myException {
		Long idBesitzLeft =  (Long)this.getValueJustInTime(RecS1.key, BG_STATION.id_adresse_besitz_ldg);
		Long idBesitzMid =   (Long)this.getValueJustInTime(RecS2.key, BG_STATION.id_adresse_besitz_ldg);
		
		if (O.isOneNull(idBesitzLeft,idBesitzMid)) {
			throw new myException("Null is not allowed <3fb45dcc-d30e-11ea-87d0-0242ac130003>");
		}
		
		Rec21_adresse adLeft = new Rec21_adresse()._fill_id(idBesitzLeft);
		Rec21_adresse adMid =  new Rec21_adresse()._fill_id(idBesitzMid);
		
		if ((adLeft.isAdressOfMandant() && ! adMid.isAdressOfMandant()) || (!adLeft.isAdressOfMandant() &&  adMid.isAdressOfMandant())) {
			return true;
		} else {
			return false;
		}
		
	}
	
	
	public boolean isRightSiteToBill() throws myException {
		Long idBesitzMid =   (Long)this.getValueJustInTime(RecS2.key, BG_STATION.id_adresse_besitz_ldg);
		Long idBesitzRight =  (Long)this.getValueJustInTime(RecS3.key, BG_STATION.id_adresse_besitz_ldg);
		
		if (O.isOneNull(idBesitzMid,idBesitzRight)) {
			throw new myException("Null is not allowed <b8984960-d30e-11ea-87d0-0242ac130003>");
		}
		
		Rec21_adresse adMid =  new Rec21_adresse()._fill_id(idBesitzMid);
		Rec21_adresse adRight = new Rec21_adresse()._fill_id(idBesitzRight);
		
		if ((adMid.isAdressOfMandant() && ! adRight.isAdressOfMandant()) || (!adMid.isAdressOfMandant() &&  adRight.isAdressOfMandant())) {
			return true;
		} else {
			return false;
		}
		
	}
	
	
	
	public boolean isVoraussetzungSteuerErmittlungErfuellt() throws Exception {
		boolean ret = false;
		
		if (O.isNoOneNull(	 this.getValueJustInTime(RecS1.key, BG_STATION.id_adresse)
							,this.getValueJustInTime(RecS2.key, BG_STATION.id_adresse)
							,this.getValueJustInTime(RecS3.key, BG_STATION.id_adresse)
							,this.getValueJustInTime(RecA1.key, BG_ATOM.id_artikel_bez)
							,this.getValueJustInTime(RecA2.key, BG_ATOM.id_artikel_bez)
							,this.getValueJustInTime(RecA1.key, BG_ATOM.datum_ausfuehrung)
							,this.getValueJustInTime(RecA2.key, BG_ATOM.datum_ausfuehrung)
							,this.getValueJustInTime(RecS1.key, BG_STATION.id_adresse_besitz_ldg)
							,this.getValueJustInTime(RecS2.key, BG_STATION.id_adresse_besitz_ldg)
							,this.getValueJustInTime(RecS3.key, BG_STATION.id_adresse_besitz_ldg)
							,this.getValueJustInTime(RecA1.key, BG_ATOM.menge)
							,this.getValueJustInTime(RecA2.key, BG_ATOM.menge)
							,this.getValueJustInTime(RecA1.key, BG_ATOM.e_preis_basiswaehrung)
							,this.getValueJustInTime(RecA2.key, BG_ATOM.e_preis_basiswaehrung)
							
						)) {
			ret = true;
			
		}
		
		return ret;
	}

	
	
	
}
 