package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.VALIDATION.Validation_Error;
import panter.gmbh.Echo2.ListAndMask.VALIDATION.Validation_Error_Vector;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ADRESSE_UST_ID;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE_UST_ID;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIRMENINFO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAND;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_UST_ID_ext;

/**
 * klasse, die prueft, ob eine Adresse zum handel zugelassen ist, bzw. in welchem steuerstatus sie steht
 * @author martin
 *
 */
public class __FS_Adress_Check extends Validation_Error_Vector {
	
	
	
	//diagnose-variable
	private boolean 	B_SchalterPrivat = 				false;
	private boolean 	B_SchalterFirma = 				false;
	private String   	C_Ausweisnummer = 				null;
	private boolean 	B_SchalterFirmaOhne_UST_ID = 	false;
	private boolean 	B_SchalterPrivatMit_UST_ID = 	false;
	private boolean 	B_HAT_ZUSATZ_USTID = 			false;


	private String   	C_ID_LAND_UF = 					null;
	
	private boolean 	B_HAT_USTID = 					false;
	private boolean 	B_HAT_KOMPLETTE_USTID = 		false;
	

	private boolean   B_HOMELAND = 					false;
	private boolean   b_EU_LAND = 						false;

	private MyDate     o_DatumAblauf = 					null;
	private boolean   b_DatumAusweisGueltig = 			false;
	private boolean   b_AusweisAngabenGueltig = 		false;
	private boolean   b_AusweisAngabenVorhanden = 		false;
	private boolean   b_HAT_Ausweis_oder_steuernummer = false;
	private boolean   b_HAT_Steuernummer = false;

	private boolean   b_BASIS_UST_LKZ_STIMMT_MIT_LAND_EINTRAG_UEBEREIN = false;
	private boolean   b_LAND_HAT_UST_PRAEFIX = false;
	
	private RECORD_LAND recLAND = null;
	

	private FS_MASK_ComponentMAP 									oMAP_ADRESSE = 		null;
	private FS_MASK_ComponentMAP.FS_ComponentMAP_MASK_FIRMENINFO 	oMAP_FIRMENINFO = 	null;

	private String 		cNameHomeLand = null; 
	
	private RECORD_ADRESSE 		recADRESSE = null;
	private RECORD_FIRMENINFO 	recFI = null;
	

	private String 				cKennungStringAdresse = null;
	
	
	/**
	 * 
	 * @param b_SchalterPrivat
	 * @param b_SchalterFirma
	 * @param c_Ausweisnummer
	 * @param c_AusweisAblaufDatumFormated
	 * @param b_SchalterFirmaOhneUST_ID
	 * @param b_SchalterPrivatMitUST_ID
	 * @param c_UST_LKZ
	 * @param c_UST_ID
	 * @param c_STEUERNUMMER
	 * @param b_HAT_ZUSATZ_USTID
	 * @param c_ID_LAND_UF
	 * @throws myException
	 */
	public __FS_Adress_Check(		String  	cNAME_ADRESSE,
									boolean 	b_SchalterPrivat, 
									boolean 	b_SchalterFirma, 
									String 		c_Ausweisnummer,
									String   	c_AusweisAblaufDatumFormated,
									boolean 	b_SchalterFirmaOhneUST_ID,
									boolean 	b_SchalterPrivatMitUST_ID,
									String 		c_UST_LKZ,
									String 		c_UST_ID,
									String    	c_STEUERNUMMER,
									boolean 	b_HAT_ZUSATZ_USTID, 
									String 		c_ID_LAND_UF) throws myException {
		super();
		
		this.cKennungStringAdresse = cNAME_ADRESSE;
		
		this.__init(b_SchalterPrivat, b_SchalterFirma, c_Ausweisnummer, c_AusweisAblaufDatumFormated, b_SchalterFirmaOhneUST_ID, b_SchalterPrivatMitUST_ID, c_UST_LKZ, c_UST_ID, c_STEUERNUMMER, b_HAT_ZUSATZ_USTID, c_ID_LAND_UF);
		
	}

	
	/**
	 * 
	 * @param recAdresse (MUSS Hauptadresse sein)
	 * @throws myException
	 */
	public __FS_Adress_Check(RECORD_ADRESSE  recAdresse) throws myException  {
		this.recADRESSE = 		recAdresse;
		
		if (this.recADRESSE.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get_vKeyValues().size()!=1) {
			throw new myException("Adress is not a Customer !!");
		}
		
		this.recFI = 		this.recADRESSE.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0);
		RECLIST_ADRESSE_UST_ID  rlZusatzUSTID = this.recADRESSE.get_DOWN_RECORD_LIST_ADRESSE_UST_ID_id_adresse();

		this.cKennungStringAdresse = this.recADRESSE.get___KETTE(bibALL.get_Vector(_DB.ADRESSE$NAME1,_DB.ADRESSE$ORT, _DB.ADRESSE$ID_ADRESSE),"","",""," // ");
		
		this.__init(	recFI.is_PRIVAT_YES(), 
						recFI.is_FIRMA_YES(),
						this.recADRESSE.get_AUSWEIS_NUMMER_cUF_NN(""),
						this.recADRESSE.get_AUSWEIS_ABLAUF_DATUM_cF_NN(""),
						recFI.is_FIRMA_OHNE_USTID_YES(), 
						recFI.is_PRIVAT_MIT_USTID_YES(),
						recFI.get_UMSATZSTEUERLKZ_cUF_NN(""), 
						recFI.get_UMSATZSTEUERID_cUF_NN(""), 
						recFI.get_STEUERNUMMER_cUF_NN(""),
						rlZusatzUSTID.get_vKeyValues().size()>0, 
						this.recADRESSE.get_ID_LAND_cUF_NN("")
						);
		
	}
	
	
	
	public __FS_Adress_Check(E2_ComponentMAP oMAP) throws myException {
		super();


		//zuerst die zwei beteiligten maps auslesen
		
		for (E2_ComponentMAP oMap: oMAP.get_E2_vCombinedComponentMAPs()) {
			if (oMap.get_oSQLFieldMAP().get_cMAIN_TABLE().equals(_DB.FIRMENINFO)) {
				oMAP_FIRMENINFO = (FS_MASK_ComponentMAP.FS_ComponentMAP_MASK_FIRMENINFO)oMap;
			} else if (oMap.get_oSQLFieldMAP().get_cMAIN_TABLE().equals(_DB.ADRESSE)) {
				oMAP_ADRESSE = (FS_MASK_ComponentMAP)oMap;
			}
		}

		if (oMAP_ADRESSE == null || oMAP_FIRMENINFO == null) {
			throw new myException(this,"Maps for validation not found !");
		}
		
		if (oMAP_ADRESSE.containsKey(_DB.ADRESSE$NAME1) && oMAP_ADRESSE.containsKey(_DB.ADRESSE$ORT)) {
			this.cKennungStringAdresse = S.NN(oMAP_ADRESSE.get_cActualDBValueFormated(_DB.ADRESSE$NAME1))+" "+S.NN(oMAP_ADRESSE.get_cActualDBValueFormated(_DB.ADRESSE$ORT));
		}
		
		
		FS_Component_MASK_DAUGHTER_UST_IDS   oDaughterFremdUST_IDS = (FS_Component_MASK_DAUGHTER_UST_IDS)oMAP_ADRESSE.get__Comp(FS_CONST.MASK_FIELD_DAUGHTER_UST_IDS);
		boolean bHatFremd_UST_ID_Eintrag =  (oDaughterFremdUST_IDS.get_vE2_ComponentMAPs_NewAndEdit_WithoutDeleteMarker().size()>0);

		this.__init(
			oMAP_FIRMENINFO.get_bActualDBValue(_DB.FIRMENINFO$PRIVAT), 
			oMAP_FIRMENINFO.get_bActualDBValue(_DB.FIRMENINFO$FIRMA), 
			oMAP_ADRESSE.get_cActualDBValueFormated(_DB.ADRESSE$AUSWEIS_NUMMER), 
			oMAP_ADRESSE.get_cActualDBValueFormated(_DB.ADRESSE$AUSWEIS_ABLAUF_DATUM),
			oMAP_FIRMENINFO.get_bActualDBValue(_DB.FIRMENINFO$FIRMA_OHNE_USTID), 
			oMAP_FIRMENINFO.get_bActualDBValue(_DB.FIRMENINFO$PRIVAT_MIT_USTID),
			oMAP_FIRMENINFO.get_cActualDBValueFormated(_DB.FIRMENINFO$UMSATZSTEUERLKZ),
			oMAP_FIRMENINFO.get_cActualDBValueFormated(_DB.FIRMENINFO$UMSATZSTEUERID),
			oMAP_FIRMENINFO.get_cActualDBValueFormated(_DB.FIRMENINFO$STEUERNUMMER),
			bHatFremd_UST_ID_Eintrag, 
			(""+oMAP_ADRESSE.get_LActualDBValue(_DB.ADRESSE$ID_LAND, 0l, 0l).longValue()));
		
	}	

	
	
	
	
	
	
	private void __init(boolean 	b_SchalterPrivat, 
									boolean 	b_SchalterFirma, 
									String 		c_Ausweisnummer,
									String   	c_AusweisAblaufDatumFormated,
									boolean 	b_SchalterFirmaOhneUST_ID,
									boolean 	b_SchalterPrivatMitUST_ID,
									String 		c_UST_LKZ,
									String 		c_UST_ID,
									String    	c_STEUERNUMMER,
									boolean 	b_HAT_ZUSATZ_USTID, 
									String 		c_ID_LAND_UF) throws myException {
	
		B_SchalterPrivat = 				b_SchalterPrivat;
		B_SchalterFirma = 				b_SchalterFirma;
		C_Ausweisnummer = 				c_Ausweisnummer;
		B_SchalterFirmaOhne_UST_ID = 	b_SchalterFirmaOhneUST_ID;
		B_SchalterPrivatMit_UST_ID = 	b_SchalterPrivatMitUST_ID;
		B_HAT_ZUSATZ_USTID = 			b_HAT_ZUSATZ_USTID;
		C_ID_LAND_UF = 					c_ID_LAND_UF;
		
		
		if (S.isFull(c_AusweisAblaufDatumFormated)) {
			this.o_DatumAblauf = new MyDate(c_AusweisAblaufDatumFormated);
			if (!this.o_DatumAblauf.get_bOK()) {
				this.o_DatumAblauf = null;
			}
			
			if (this.o_DatumAblauf != null) {
				if (myDateHelper.get_Date1_GreaterEqual_Date2(this.o_DatumAblauf.get_cDateStandardFormat(), bibALL.get_cDateNOW())) {
					this.b_DatumAusweisGueltig = true;
				}
			}
		}
		this.b_AusweisAngabenGueltig = S.isFull(this.C_Ausweisnummer) && this.o_DatumAblauf!=null && this.b_DatumAusweisGueltig;
		this.b_AusweisAngabenVorhanden = S.isFull(this.C_Ausweisnummer);

		this.b_HAT_Ausweis_oder_steuernummer = S.isFull(c_STEUERNUMMER) || this.b_AusweisAngabenVorhanden;
		this.b_HAT_Steuernummer = S.isFull(c_STEUERNUMMER);
		
		
		RECORD_LAND  recHomeLand = new RECORD_LAND(bibALL.get_RECORD_MANDANT().get_ID_LAND_cUF_NN(""));
		
		if (recHomeLand == null || recHomeLand.is_INTRASTAT_JN_NO()) {
			this.cNameHomeLand = recHomeLand.get_LAENDERNAME_cF_NN("<land nicht benannt>");
			//wenn der mandant nicht in EU-Land sitzt, dann ist alles makulatur !!!
			this.add(new Validation_Error(	new MyE2_String("Systemfehler: Das Land des Mandanten ("+cNameHomeLand+") ist nicht der EU zugeordnet. Bitte korrigieren"),
											bibVECTOR.get_Vector(_DB.ADRESSE$ID_LAND, _DB.ADRESSE$AKTIV, _DB.ADRESSE$WARENEINGANG_SPERREN, _DB.ADRESSE$WARENAUSGANG_SPERREN, _DB.FIRMENINFO$PRIVAT, _DB.FIRMENINFO$FIRMA, _DB.FIRMENINFO$FIRMA_OHNE_USTID, _DB.FIRMENINFO$PRIVAT_MIT_USTID), 
											null, 
											Validation_Error.SHOW_WARNING, 
											Validation_Error.SHOW_ERROR,
											Validation_Error.SHOW_ERROR));
			
			return;
		} else {
			this.cNameHomeLand = recHomeLand.get_LAENDERNAME_cF_NN("<land nicht benannt>"); 
		}
		
		
		if (S.isFull(this.C_ID_LAND_UF) && !(this.C_ID_LAND_UF.trim().equals("0"))) {
			try {
				this.recLAND = new RECORD_LAND(S.NN(this.C_ID_LAND_UF));
			} catch (myException e) {
				e.printStackTrace();
			}
		}

		if (this.recLAND==null) {
			this.add(new Validation_Error(	new MyE2_String("Das Land ist noch nicht gesetzt. Bitte zuerst definieren"),
					bibVECTOR.get_Vector(_DB.ADRESSE$ID_LAND, _DB.ADRESSE$AKTIV, _DB.ADRESSE$WARENEINGANG_SPERREN, _DB.ADRESSE$WARENAUSGANG_SPERREN, _DB.FIRMENINFO$PRIVAT, _DB.FIRMENINFO$FIRMA, _DB.FIRMENINFO$FIRMA_OHNE_USTID, _DB.FIRMENINFO$PRIVAT_MIT_USTID), 
					null, 
					Validation_Error.SHOW_WARNING, 
					Validation_Error.SHOW_WARNING,
					Validation_Error.SHOW_ERROR));
			
		} else {

			this.b_LAND_HAT_UST_PRAEFIX = S.isFull(this.recLAND.get_UST_PRAEFIX_cF_NN(""));

			
			this.b_BASIS_UST_LKZ_STIMMT_MIT_LAND_EINTRAG_UEBEREIN = false;
			if (this.recLAND.get_UST_PRAEFIX_cUF_NN("@@").equals(S.NN(c_UST_LKZ))) {
				this.b_BASIS_UST_LKZ_STIMMT_MIT_LAND_EINTRAG_UEBEREIN = true;
			}
			
			//2014-12-08: fehler: this.B_HOMELAND = (bibALL.get_RECORD_MANDANT().get_ID_LAND_cF_NN("-").equals(this.recLAND.get_ID_LAND_cUF_NN("--")));
			this.B_HOMELAND = (bibALL.get_RECORD_MANDANT().get_ID_LAND_cUF_NN("-").equals(this.recLAND.get_ID_LAND_cUF_NN("--")));
			this.b_EU_LAND = this.recLAND.is_INTRASTAT_JN_YES();
			
			if (S.isFull(c_UST_LKZ) || S.isFull(c_UST_ID)) {
				this.B_HAT_USTID=true;
			}
			
			if (S.isFull(c_UST_LKZ) && S.isFull(c_UST_ID)) {
				this.B_HAT_KOMPLETTE_USTID=true;
			}

			// ende der beschaffung der werte, jetzt fehlersuche ...
			
			
			if (this.b_EU_LAND && !this.b_LAND_HAT_UST_PRAEFIX) {
				this.add(new Validation_Error(	new MyE2_String("Das EU-Land ",true, this.recLAND.get_LAENDERNAME_cF_NN("<land name undefiniert>"),false," besitzt keine UST-Länderkürzel, bitte korrigieren !",true),
						null, 
						bibVECTOR.get_Vector(_DB.ADRESSE$ID_LAND, _DB.ADRESSE$AKTIV, _DB.ADRESSE$WARENEINGANG_SPERREN, _DB.ADRESSE$WARENAUSGANG_SPERREN, _DB.FIRMENINFO$PRIVAT, _DB.FIRMENINFO$FIRMA, _DB.FIRMENINFO$FIRMA_OHNE_USTID, _DB.FIRMENINFO$PRIVAT_MIT_USTID), 
						Validation_Error.SHOW_WARNING, 
						Validation_Error.SHOW_WARNING,
						Validation_Error.SHOW_ERROR));
			}

			
			if (this.B_HAT_USTID && !this.B_HAT_KOMPLETTE_USTID) {
				this.add(new Validation_Error(	new ownString("Die Basis-UST-ID der Adresse ist nur teilweise ausgefüllt. Bitte komplettieren oder komplett leeren !"),
						null, 
						bibVECTOR.get_Vector(_DB.ADRESSE$ID_LAND, _DB.ADRESSE$AKTIV, _DB.ADRESSE$WARENEINGANG_SPERREN, _DB.ADRESSE$WARENAUSGANG_SPERREN, _DB.FIRMENINFO$PRIVAT, _DB.FIRMENINFO$FIRMA, _DB.FIRMENINFO$FIRMA_OHNE_USTID, _DB.FIRMENINFO$PRIVAT_MIT_USTID), 
						Validation_Error.SHOW_WARNING, 
						Validation_Error.SHOW_WARNING,
						Validation_Error.SHOW_ERROR));
				
			}
			
			
			/*
			 * vorab: wenn UST_LKZ vorhanden, dann muss es mit dem land uebereinstimmen
			 */
			if (this.B_HAT_USTID && (!this.recLAND.get_UST_PRAEFIX_cUF_NN("@@@@@@@@@@@").equals(S.NN(c_UST_LKZ))) ) {
				this.add(new Validation_Error(	new ownString("Das Länderkürzel der Basis-UST-ID (",true,c_UST_LKZ,false, ") stimmt nicht mit der Angabe im Land (",true,this.recLAND.get_UST_PRAEFIX_cUF_NN("<kein UST-Länderpräfix>"),false,") überein !",true),
						null, 
						bibVECTOR.get_Vector(_DB.ADRESSE$ID_LAND, _DB.ADRESSE$AKTIV, _DB.ADRESSE$WARENEINGANG_SPERREN, _DB.ADRESSE$WARENAUSGANG_SPERREN, _DB.FIRMENINFO$PRIVAT, _DB.FIRMENINFO$FIRMA, _DB.FIRMENINFO$FIRMA_OHNE_USTID, _DB.FIRMENINFO$PRIVAT_MIT_USTID), 
						Validation_Error.SHOW_WARNING, 
						Validation_Error.SHOW_WARNING,
						Validation_Error.SHOW_ERROR));
				
			}
				
			
			if (!this.B_HOMELAND && (this.B_SchalterFirmaOhne_UST_ID || this.B_SchalterPrivatMit_UST_ID) ) {
				this.add(new Validation_Error(	new ownString("Die Ausnahmeschalter <Einstufung: FIRMA  (ohne UST-ID)> und <PRIVAT (Mit UST-ID)> sind nur bei Adressen in "+cNameHomeLand+" sinnvoll!"),
						null, 
						bibVECTOR.get_Vector(_DB.ADRESSE$ID_LAND, _DB.ADRESSE$AKTIV, _DB.ADRESSE$WARENEINGANG_SPERREN, _DB.ADRESSE$WARENAUSGANG_SPERREN, _DB.FIRMENINFO$PRIVAT, _DB.FIRMENINFO$FIRMA, _DB.FIRMENINFO$FIRMA_OHNE_USTID, _DB.FIRMENINFO$PRIVAT_MIT_USTID), 
						Validation_Error.SHOW_WARNING, 
						Validation_Error.SHOW_WARNING,
						Validation_Error.SHOW_ERROR));
			}

			
			if (this.B_HOMELAND && (this.B_SchalterFirmaOhne_UST_ID && this.B_SchalterPrivatMit_UST_ID) ) {
				this.add(new Validation_Error(	new ownString("Die Ausnahmeschalter <Einstufung: FIRMA  (ohne UST-ID)> und <PRIVAT (Mit UST-ID)> können nicht gleichzeitig aktiv sein!"),
						null, 
						bibVECTOR.get_Vector(_DB.ADRESSE$ID_LAND, _DB.ADRESSE$AKTIV, _DB.ADRESSE$WARENEINGANG_SPERREN, _DB.ADRESSE$WARENAUSGANG_SPERREN, _DB.FIRMENINFO$PRIVAT, _DB.FIRMENINFO$FIRMA, _DB.FIRMENINFO$FIRMA_OHNE_USTID, _DB.FIRMENINFO$PRIVAT_MIT_USTID), 
						Validation_Error.SHOW_WARNING, 
						Validation_Error.SHOW_WARNING,
						Validation_Error.SHOW_ERROR));
			}

			
			
			if ((!this.B_SchalterFirma && !this.B_SchalterPrivat) || (this.B_SchalterFirma && this.B_SchalterPrivat)) {
				this.add(new Validation_Error(	new ownString("Eine Adresse muss entweder als PRIVAT oder als FIRMA eingestuft werden !"),
						null, 
						bibVECTOR.get_Vector(_DB.FIRMENINFO$PRIVAT, _DB.FIRMENINFO$FIRMA, _DB.ADRESSE$ID_LAND, _DB.ADRESSE$AKTIV, _DB.ADRESSE$WARENEINGANG_SPERREN, _DB.ADRESSE$WARENAUSGANG_SPERREN, _DB.FIRMENINFO$FIRMA_OHNE_USTID, _DB.FIRMENINFO$PRIVAT_MIT_USTID), 
						Validation_Error.SHOW_WARNING, 
						Validation_Error.SHOW_WARNING,
						Validation_Error.SHOW_ERROR));
			}
			
			
			
			if (this.B_SchalterPrivat) {
				
				if (this.B_HOMELAND && this.B_HAT_USTID && !this.B_SchalterPrivatMit_UST_ID) {
					this.add(new Validation_Error(	new ownString("Die Einstufung einer einer Adresse mit Basis-UST-ID als PRIVAT ist nur mit dem Sonderschalter <PRIVAT (Mit UST-ID)> möglich !"),
							null, 
							bibVECTOR.get_Vector(_DB.FIRMENINFO$PRIVAT, _DB.FIRMENINFO$FIRMA, _DB.ADRESSE$ID_LAND, _DB.ADRESSE$AKTIV, _DB.ADRESSE$WARENEINGANG_SPERREN, _DB.ADRESSE$WARENAUSGANG_SPERREN, _DB.FIRMENINFO$FIRMA_OHNE_USTID, _DB.FIRMENINFO$PRIVAT_MIT_USTID), 
							Validation_Error.SHOW_WARNING, 
							Validation_Error.SHOW_WARNING,
							Validation_Error.SHOW_ERROR));
				}
				
				if (!(this.B_HAT_USTID && this.B_HOMELAND && this.B_SchalterPrivatMit_UST_ID)) {
					if (!this.B_HOMELAND && !this.b_AusweisAngabenVorhanden) { 
						//Zustand: Ausland privat und kein ausweis
						this.add(new Validation_Error(	new ownString("Bei als PRIVAT eingestuften Adressen aus dem Ausland " +
																	   " MUSS die Ausweisnummer vorliegen!"),
								null, 
								bibVECTOR.get_Vector(_DB.FIRMENINFO$PRIVAT, _DB.FIRMENINFO$FIRMA, _DB.ADRESSE$ID_LAND, _DB.ADRESSE$AKTIV, _DB.ADRESSE$WARENEINGANG_SPERREN, _DB.ADRESSE$WARENAUSGANG_SPERREN, _DB.FIRMENINFO$FIRMA_OHNE_USTID, _DB.FIRMENINFO$PRIVAT_MIT_USTID), 
								Validation_Error.SHOW_WARNING, 
								Validation_Error.SHOW_WARNING,
								Validation_Error.SHOW_ERROR));
					} else if (this.B_HOMELAND && !this.b_HAT_Ausweis_oder_steuernummer) {
						//Zustand: inland, privat, weder ausweis noch steuernummer
						this.add(new Validation_Error(	new ownString("Bei als PRIVAT eingestuften Adressen aus dem Inland " +
								" MUSS die Ausweisnummer oder die Steuernummer vorliegen (außer es ist eine PRIVAT eingestufte Firmenadresse mit UST-ID)!"),
								null, 
								bibVECTOR.get_Vector(_DB.FIRMENINFO$PRIVAT, _DB.FIRMENINFO$FIRMA, _DB.ADRESSE$ID_LAND, _DB.ADRESSE$AKTIV, _DB.ADRESSE$WARENEINGANG_SPERREN, _DB.ADRESSE$WARENAUSGANG_SPERREN, _DB.FIRMENINFO$FIRMA_OHNE_USTID, _DB.FIRMENINFO$PRIVAT_MIT_USTID), 
								Validation_Error.SHOW_WARNING, 
								Validation_Error.SHOW_WARNING,
								Validation_Error.SHOW_ERROR));
						
					}
				}
				
				if (!this.B_HOMELAND &&  this.B_HAT_USTID) {
					this.add(new Validation_Error(	new ownString("Bei als PRIVAT eingestuften Adressen im Ausland darf keine UST-ID erfasst sein!"),
							null, 
							bibVECTOR.get_Vector(_DB.FIRMENINFO$PRIVAT, _DB.FIRMENINFO$FIRMA, _DB.FIRMENINFO$FIRMA_OHNE_USTID, _DB.FIRMENINFO$PRIVAT_MIT_USTID, _DB.ADRESSE$ID_LAND, _DB.ADRESSE$AKTIV, _DB.ADRESSE$WARENEINGANG_SPERREN, _DB.ADRESSE$WARENAUSGANG_SPERREN), 
							Validation_Error.SHOW_WARNING, 
							Validation_Error.SHOW_WARNING,
							Validation_Error.SHOW_ERROR));
				}
				
				
				if (this.B_HOMELAND && this.B_SchalterFirmaOhne_UST_ID) {
					this.add(new Validation_Error(	new ownString("Bei als PRIVAT eingestuften Adressen in "+cNameHomeLand+" darf der Sonderschalter <Einstufung: FIRMA  (ohne UST-ID)> nicht gesetzt sein!"),
							null, 
							bibVECTOR.get_Vector(_DB.FIRMENINFO$PRIVAT, _DB.FIRMENINFO$FIRMA, _DB.FIRMENINFO$FIRMA_OHNE_USTID, _DB.FIRMENINFO$PRIVAT_MIT_USTID, _DB.ADRESSE$ID_LAND, _DB.ADRESSE$AKTIV, _DB.ADRESSE$WARENEINGANG_SPERREN, _DB.ADRESSE$WARENAUSGANG_SPERREN), 
							Validation_Error.SHOW_WARNING, 
							Validation_Error.SHOW_WARNING,
							Validation_Error.SHOW_ERROR));
				}

			}
			
			
			if (this.B_SchalterFirma) {
			
				if (this.B_HOMELAND && !this.B_HAT_USTID && (!this.B_SchalterFirmaOhne_UST_ID || S.isEmpty(c_STEUERNUMMER))) {
					this.add(new Validation_Error(	new ownString("Die Einstufung einer Adresse ohne UST-ID als FIRMA" +
													" ist nur mit dem Sonderschalter <Einstufung: FIRMA  (ohne UST-ID)>, sowie der Angabe der Steuernummer möglich!"),
							null, 
							bibVECTOR.get_Vector(_DB.FIRMENINFO$PRIVAT, _DB.FIRMENINFO$FIRMA, _DB.ADRESSE$ID_LAND, _DB.ADRESSE$AKTIV, _DB.ADRESSE$WARENEINGANG_SPERREN, _DB.ADRESSE$WARENAUSGANG_SPERREN, _DB.FIRMENINFO$FIRMA_OHNE_USTID, _DB.FIRMENINFO$PRIVAT_MIT_USTID), 
							Validation_Error.SHOW_WARNING, 
							Validation_Error.SHOW_WARNING,
							Validation_Error.SHOW_ERROR));
				}
			
				
				
				if (!this.B_HOMELAND && this.b_EU_LAND && !this.B_HAT_USTID) {
					this.add(new Validation_Error(	new ownString("Eine Adresse mit Einstufung als FIRMA im EU-Ausland MUSS eine korrekte Basis-UST-ID haben !"),
							null, 
							bibVECTOR.get_Vector(_DB.FIRMENINFO$PRIVAT, _DB.FIRMENINFO$FIRMA, _DB.ADRESSE$ID_LAND, _DB.ADRESSE$AKTIV, _DB.ADRESSE$WARENEINGANG_SPERREN, _DB.ADRESSE$WARENAUSGANG_SPERREN, _DB.FIRMENINFO$FIRMA_OHNE_USTID, _DB.FIRMENINFO$PRIVAT_MIT_USTID), 
							Validation_Error.SHOW_WARNING, 
							Validation_Error.SHOW_WARNING,
							Validation_Error.SHOW_ERROR));
				}
				
				
				if (!this.B_HOMELAND && !this.b_EU_LAND && this.B_HAT_USTID && !(this.b_BASIS_UST_LKZ_STIMMT_MIT_LAND_EINTRAG_UEBEREIN && this.b_LAND_HAT_UST_PRAEFIX)) {
					this.add(new Validation_Error(	new ownString("Bei einer Adresse mit Einstufung als FIRMA im NICHT-EU-Ausland mit einer Basis-UST-ID MUSS das UST-Länderkürzel mit dem Eintrag im Länderstamm übereinstimmen!"),
							null, 
							bibVECTOR.get_Vector(_DB.ADRESSE$ID_LAND, _DB.FIRMENINFO$PRIVAT, _DB.FIRMENINFO$FIRMA, _DB.FIRMENINFO$FIRMA_OHNE_USTID, _DB.FIRMENINFO$PRIVAT_MIT_USTID, _DB.ADRESSE$AKTIV, _DB.ADRESSE$WARENEINGANG_SPERREN, _DB.ADRESSE$WARENAUSGANG_SPERREN), 
							Validation_Error.SHOW_WARNING, 
							Validation_Error.SHOW_WARNING,
							Validation_Error.SHOW_ERROR));
				}
				
				if (this.B_HOMELAND && this.B_HAT_USTID && this.B_SchalterFirmaOhne_UST_ID) {
					this.add(new Validation_Error(	new ownString("Bei einer Adresse mit Einstufung als FIRMA, die eine UST-ID hat, darf der Schalter <Einstufung: FIRMA  (ohne UST-ID)> nicht gesetzt sein !"),
							null, 
							bibVECTOR.get_Vector(_DB.ADRESSE$ID_LAND, _DB.FIRMENINFO$PRIVAT, _DB.FIRMENINFO$FIRMA, _DB.FIRMENINFO$FIRMA_OHNE_USTID, _DB.FIRMENINFO$PRIVAT_MIT_USTID, _DB.ADRESSE$AKTIV, _DB.ADRESSE$WARENEINGANG_SPERREN, _DB.ADRESSE$WARENAUSGANG_SPERREN), 
							Validation_Error.SHOW_WARNING, 
							Validation_Error.SHOW_WARNING,
							Validation_Error.SHOW_ERROR));
				}

				if (this.B_HOMELAND && this.B_SchalterPrivatMit_UST_ID) {
					this.add(new Validation_Error(	new ownString("Bei als FIRMA eingestuften Adressen in "+cNameHomeLand+" darf der Sonderschalter <Einstufung: <PRIVAT (Mit UST-ID)> nicht gesetzt sein!"),
							null, 
							bibVECTOR.get_Vector(_DB.FIRMENINFO$PRIVAT, _DB.FIRMENINFO$FIRMA, _DB.FIRMENINFO$FIRMA_OHNE_USTID, _DB.FIRMENINFO$PRIVAT_MIT_USTID, _DB.ADRESSE$ID_LAND, _DB.ADRESSE$AKTIV, _DB.ADRESSE$WARENEINGANG_SPERREN, _DB.ADRESSE$WARENAUSGANG_SPERREN), 
							Validation_Error.SHOW_WARNING, 
							Validation_Error.SHOW_WARNING,
							Validation_Error.SHOW_ERROR));
				}

			}
			
			
			//2015-09-22: weitere formale pruefung: wenn das land eine zusatz-ust-id-liste besitzt, dann muss die korrekt sein
			RECORD_ADRESSE rec4Test = this.recADRESSE;
			if (rec4Test==null && this.oMAP_ADRESSE!=null && this.oMAP_ADRESSE.get_oInternalSQLResultMAP()!=null) {
				rec4Test = new RECORD_ADRESSE(this.oMAP_ADRESSE.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
			}
			if (rec4Test!=null) {
				for (RECORD_ADRESSE_UST_ID ust: rec4Test.get_DOWN_RECORD_LIST_ADRESSE_UST_ID_id_adresse()) {
					RECORD_ADRESSE_UST_ID_ext ust_ext = new RECORD_ADRESSE_UST_ID_ext(ust);
					
					Vector<MyE2_String> vFehler = ust_ext.check_formal_korrekt();
					 for (MyE2_String fehler: vFehler) {
							this.add(new Validation_Error(	new ownString(fehler.CTrans(),false),
									null, 
									bibVECTOR.get_Vector(_DB.FIRMENINFO$PRIVAT, _DB.FIRMENINFO$FIRMA, _DB.FIRMENINFO$FIRMA_OHNE_USTID, _DB.FIRMENINFO$PRIVAT_MIT_USTID, _DB.ADRESSE$ID_LAND, _DB.ADRESSE$AKTIV, _DB.ADRESSE$WARENEINGANG_SPERREN, _DB.ADRESSE$WARENAUSGANG_SPERREN), 
									Validation_Error.SHOW_NOTHING, 
									Validation_Error.SHOW_WARNING,
									Validation_Error.SHOW_ERROR));
					 }
				}
			}
			
			
		}
		
	}
	
	
	
	public boolean get_bADRESSE_IST_OK() {
		return this.get_Messages_4_Bewertung().get_bIsOK();
	}
	
	
	public boolean get_bADRESSE_IST_PRIVAT_HOMELAND() {
		return 	this.B_HOMELAND && this.B_SchalterPrivat;
	}
	public boolean get_bADRESSE_IST_PRIVAT_EU() {
		return ( !this.B_HOMELAND && this.b_EU_LAND && this.B_SchalterPrivat	);
	}
	
	public boolean get_bADRESSE_IST_PRIVAT_EX_EU() {
		return ( !this.b_EU_LAND && this.B_SchalterPrivat	);
	}
	
	public boolean get_bAdresse_IST_PRIVAT() {
		return this.B_SchalterPrivat;
	}
	
	
	public boolean get_bADRESSE_IST_FIRMA_HOMELAND() {
		return ( this.B_HOMELAND && this.B_SchalterFirma);
	}
	
	public boolean get_bADRESSE_IST_FIRMA_EU() {
		return ( !this.B_HOMELAND && this.b_EU_LAND && this.B_SchalterFirma);
	}
	
	public boolean get_bADRESSE_IST_FIRMA_EX_EU() {
		return ( !this.b_EU_LAND && this.B_SchalterFirma);
	}
	
	public boolean get_b_HAT_KOMPLETTE_USTID() {
		return B_HAT_KOMPLETTE_USTID;
	}

	
	public boolean get_b_ADRESSE_MUSS_USTID_AUF_RECH_GUT_KOPF_HABEN() {
		boolean bRueck = false;
		
		bRueck = (this.B_HOMELAND && this.B_SchalterFirma && !this.B_SchalterFirmaOhne_UST_ID) ||
				 (!this.B_HOMELAND && this.b_EU_LAND && this.B_SchalterFirma);
		
		return bRueck;
	}
	
	
	public FS_MASK_ComponentMAP get_oMAP_ADRESSE() {
		return oMAP_ADRESSE;
	}


	public FS_MASK_ComponentMAP.FS_ComponentMAP_MASK_FIRMENINFO get_oMAP_FIRMENINFO() {
		return oMAP_FIRMENINFO;
	}

	public boolean get_bHAT_ZUSATZ_USTID() {
		return B_HAT_ZUSATZ_USTID;
	}

	
	public boolean get_bIstAktiv() throws myException {
		if (this.oMAP_ADRESSE != null ) {
			return this.oMAP_ADRESSE.get_bActualDBValue(_DB.ADRESSE$AKTIV);
		} else if (this.recADRESSE!=null){
			return this.recADRESSE.is_AKTIV_YES();
		} else {	
			throw new myException("unknown value");
		}
	}
	
	public boolean get_bWE_SPERRE() throws myException {
		if (this.oMAP_ADRESSE != null ) {
			return this.oMAP_ADRESSE.get_bActualDBValue(_DB.ADRESSE$WARENEINGANG_SPERREN);
		} else if (this.recADRESSE!=null) {
			return this.recADRESSE.is_WARENEINGANG_SPERREN_YES();
		} else {
			throw new myException("unknown value");
		}
	}

	public boolean get_bWA_SPERRE() throws myException {
		if (this.oMAP_ADRESSE != null ) {
			return this.oMAP_ADRESSE.get_bActualDBValue(_DB.ADRESSE$WARENAUSGANG_SPERREN);
		} else if (this.recADRESSE!=null) {
			return this.recADRESSE.is_WARENAUSGANG_SPERREN_YES();
		} else {
			throw new myException("unknown value");
		}
	}

	public String get_cNameHomeLand() {
		return cNameHomeLand;
	}


	public boolean get_bIstMandant() throws myException {
		if (this.oMAP_ADRESSE!=null && this.oMAP_ADRESSE.get_oInternalSQLResultMAP()!=null) {
			return (this.oMAP_ADRESSE.get_oInternalSQLResultMAP().get_cUNFormatedROW_ID().equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("@@")));
		} else if (this.recADRESSE!=null) {
			return this.recADRESSE.get_ID_ADRESSE_cUF().equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("@@"));
		} else {
			throw new myException("unknown value");
		}
	}
	 
	
	private class ownString extends MyE2_String {

		public ownString(MyString cString, String cNullValue) {
			super(cString, cNullValue);
			this.addUnTranslated(" .... ("+__FS_Adress_Check.this.cKennungStringAdresse+")");
		}


		public ownString(String cUntrans1, boolean bTrans1) {
			super(cUntrans1, bTrans1);
			this.addUnTranslated(" .... ("+__FS_Adress_Check.this.cKennungStringAdresse+")");
		}

		
		public ownString(String cUntrans1, boolean bTrans1, String cUntrans2,
				boolean bTrans2, String cUntrans3, boolean bTrans3,
				String cUntrans4, boolean bTrans4, String cUntrans5,
				boolean bTrans5) {
			super(cUntrans1, bTrans1, cUntrans2, bTrans2, cUntrans3, bTrans3, cUntrans4,
					bTrans4, cUntrans5, bTrans5);
			this.addUnTranslated(" .... ("+__FS_Adress_Check.this.cKennungStringAdresse+")");
		}



		public ownString(String cUntranslated) {
			super(cUntranslated);
			this.addUnTranslated(" .... ("+__FS_Adress_Check.this.cKennungStringAdresse+")");
		}

	}
	

}
