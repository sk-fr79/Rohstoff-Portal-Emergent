package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.LIST;

import java.util.ArrayList;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_BEWEGUNG_VEKTOR_POS;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_BEWEGUNG_VEKTOR;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_VEKTOR_STATUS;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;

public class RECORD_BEWEGUNG_VEKTOR_SPEC extends RECORD_BEWEGUNG_VEKTOR {
	
	
	//erste start und letzte zielstation gibts immer
	private RECORD_BEWEGUNG_ATOM_SPEC    rec_erste_START_ATOM = null;
	private RECORD_BEWEGUNG_ATOM_SPEC    rec_letzte_ZIEL_ATOM = null;
	
	//erste physikalische start und letzte physikalische zielstation bleiben u.U. null
	private RECORD_BEWEGUNG_ATOM_SPEC    rec_erste_phys_START_ATOM = null;
	private RECORD_BEWEGUNG_ATOM_SPEC    rec_letzte_phys_ZIEL_ATOM = null;

	
	private ArrayList<RECORD_BEWEGUNG_ATOM_SPEC> v_record_atom = new ArrayList<RECORD_BEWEGUNG_ATOM_SPEC>();
	
	private boolean bFirstSearchWasDone = 	false;
	private int     countRechPos = 	0;
	private int     countGutschriftPos = 	0;
	
	
	public RECORD_BEWEGUNG_VEKTOR_SPEC(RECORD_BEWEGUNG_VEKTOR recordOrig)	{
		super(recordOrig);
	}
	
	
	public RECORD_BEWEGUNG_VEKTOR_SPEC(String c_ID_or_WHEREBLOCK_OR_SQL) throws myException {
		super(c_ID_or_WHEREBLOCK_OR_SQL);
	}


	private void suche() throws myException {
		
		if (!this.bFirstSearchWasDone) {
			this.bFirstSearchWasDone = true;
			this.countRechPos = 0;
			this.countGutschriftPos = 0;
			this.v_record_atom.clear();
			
//			RECLIST_BEWEGUNG_ATOM  			rlAtome = 		this.get_DOWN_RECORD_LIST_BEWEGUNG_ATOM_id_bewegung_vektor(null, BEWEGUNG_ATOM.posnr.fieldName(),false);
			RECLIST_BEWEGUNG_VEKTOR_POS  	rlVectorPos = 	this.get_DOWN_RECORD_LIST_BEWEGUNG_VEKTOR_POS_id_bewegung_vektor(null, BEWEGUNG_VEKTOR.posnr.fieldName(), false)  ;
			
			
			for (String id_vektor_pos: rlVectorPos.get_vKeyValues()) {
				RECLIST_BEWEGUNG_ATOM rlAtome = rlVectorPos.get(id_vektor_pos).get_DOWN_RECORD_LIST_BEWEGUNG_ATOM_id_bewegung_vektor_pos(null, BEWEGUNG_ATOM.posnr.fieldName(),false);

				for (String id_atom: rlAtome.get_vKeyValues()) {
					RECORD_BEWEGUNG_ATOM_SPEC recAtom = new RECORD_BEWEGUNG_ATOM_SPEC(rlAtome.get(id_atom));
					this.v_record_atom.add(recAtom);
					
					this.countRechPos+=recAtom.get_anzahl_rechnungsPos();
					this.countGutschriftPos+=recAtom.get_anzahl_gutschriftsPos();
					
					DEBUG.System_println("RECH: "+this.countRechPos+"  --  GUT: "+this.countGutschriftPos);
					
					if (this.rec_erste_START_ATOM==null) {
						this.rec_erste_START_ATOM = recAtom;
					}
					
					if (this.rec_erste_phys_START_ATOM==null && this.rec_erste_START_ATOM.is__physical_start()) {
						this.rec_erste_phys_START_ATOM = recAtom;
					}
					
					this.rec_letzte_ZIEL_ATOM = recAtom;
					
					if (this.rec_letzte_ZIEL_ATOM.is__physical_ziel()) {
						this.rec_letzte_phys_ZIEL_ATOM = recAtom;
					}
				}
			}

			
			
			
			
//			for (String id_atom: rlAtome.get_vKeyValues()) {
//				RECORD_BEWEGUNG_ATOM_SPEC recAtom = new RECORD_BEWEGUNG_ATOM_SPEC(rlAtome.get(id_atom));
//				this.v_record_atom.add(recAtom);
//				
//				this.countRechPos+=recAtom.get_anzahl_rechnungsPos();
//				this.countGutschriftPos+=recAtom.get_anzahl_gutschriftsPos();
//				
//				DEBUG.System_println("RECH: "+this.countRechPos+"  --  GUT: "+this.countGutschriftPos);
//				
//				if (this.rec_erste_START_ATOM==null) {
//					this.rec_erste_START_ATOM = recAtom;
//				}
//				
//				if (this.rec_erste_phys_START_ATOM==null && this.rec_erste_START_ATOM.is__physical_start()) {
//					this.rec_erste_phys_START_ATOM = recAtom;
//				}
//				
//				this.rec_letzte_ZIEL_ATOM = recAtom;
//				
//				if (this.rec_letzte_ZIEL_ATOM.is__physical_ziel()) {
//					this.rec_letzte_phys_ZIEL_ATOM = recAtom;
//				}
//		
//			}

		}
	}





	public RECORD_BEWEGUNG_ATOM_SPEC getRec_erste_START_ATOM() throws myException {
		this.suche();
		return rec_erste_START_ATOM;
	}


	public RECORD_BEWEGUNG_ATOM_SPEC getRec_letzte_ZIEL_ATOM() throws myException {
		this.suche();
		return rec_letzte_ZIEL_ATOM;
	}


	public RECORD_BEWEGUNG_ATOM_SPEC getRec_erste_phys_START_ATOM() throws myException {
		this.suche();
		return rec_erste_phys_START_ATOM;
	}


	public RECORD_BEWEGUNG_ATOM_SPEC getRec_letzte_phys_ZIEL_ATOM() throws myException {
		this.suche();
		return rec_letzte_phys_ZIEL_ATOM;
	}
	
	
	
	
	public RECORD_BEWEGUNG_STATION get__rec_erste_START_STATION() throws myException {
		this.suche();
		return this.rec_erste_START_ATOM.get__startStation();
	}


	public RECORD_BEWEGUNG_STATION get__rec_letzte_ZIEL_STATION() throws myException {
		this.suche();
		return this.rec_letzte_ZIEL_ATOM.get__zielStation();
	}


	public RECORD_BEWEGUNG_STATION get_rec__erste_phys_START_STATION() throws myException {
		this.suche();
		if (this.rec_erste_phys_START_ATOM!=null) {
			return this.rec_erste_phys_START_ATOM.get__startStation();
		} else {
			return null;
		}
			
	}


	public RECORD_BEWEGUNG_STATION get_rec__letzte_phys_ZIEL_STATION() throws myException {
		this.suche();
		if (this.rec_letzte_phys_ZIEL_ATOM!=null) {
			return this.rec_letzte_phys_ZIEL_ATOM.get__zielStation();
		} else {
			return null;
		}
	}

	
	public RECORD_ADRESSE_extend get_first_startAdress() throws myException {
		this.suche();
		if (this.rec_erste_phys_START_ATOM!=null) {
			return new RECORD_ADRESSE_extend(rec_erste_phys_START_ATOM.get_startAdresse());
		} else {
			return new RECORD_ADRESSE_extend(this.rec_erste_START_ATOM.get_startAdresse());
		}
	}
	
	public RECORD_ADRESSE_extend get_last_zielAdress() throws myException {
		this.suche();
		if (this.rec_letzte_phys_ZIEL_ATOM!=null) {
			return new RECORD_ADRESSE_extend(this.rec_letzte_phys_ZIEL_ATOM.get_zielAdresse());
		} else {
			return new RECORD_ADRESSE_extend(this.rec_letzte_ZIEL_ATOM.get_zielAdresse());
		}
	}
	
	
	
	
	
	public String get__id_vpos_tpa_fuhre() throws myException {
		return this.get_UP_RECORD_BEWEGUNG_id_bewegung()==null?"":this.get_UP_RECORD_BEWEGUNG_id_bewegung().get_ID_VPOS_TPA_FUHRE_cUF_NN("");
	}


	public int countRechPos() throws myException {
		this.suche();
		return countRechPos;
	}


	public int countGutschriftPos() throws myException {
		this.suche();
		return countGutschriftPos;
	}
	
	
	public int countAtome() throws myException {
		this.suche();
		return this.v_record_atom.size();
	}
	
	public boolean is_Storniert_or_Deleted() throws myException {
		return this.is_DELETED_YES() || this.get_STATUS_cF_NN("").equals(ENUM_VEKTOR_STATUS.STORNIERT.db_val());
	}

	public boolean is_Storniert() throws myException {
		return this.get_STATUS_cF_NN("").equals(ENUM_VEKTOR_STATUS.STORNIERT.db_val());
	}

	public boolean is_Planned() throws myException {
		return this.get_STATUS_cF_NN("").equals(ENUM_VEKTOR_STATUS.GEPLANT.db_val());
	}
	
	public boolean is_Active() throws myException {
		return this.get_STATUS_cF_NN("").equals(ENUM_VEKTOR_STATUS.AKTIV.db_val());
	}
	
	
	/**
	 * korrektur der temporaeren id_atom_start und -ziel-felder 
	 * @return
	 * @throws myException 
	 */
	public MyE2_MessageVector korr_temp_id_atom() throws myException {
		
		MyE2_MessageVector mvRueck = new MyE2_MessageVector();
		
		String cSQL = this.get_korr_statement_temp_id_atom(mvRueck);
		//falls cSQL leer ist, dann steht eine fehlermessage im vector
		if (S.isFull(cSQL)) {
			mvRueck = this.ExecMultiSQLVector(bibALL.get_Vector(cSQL),true);
		} 
		return mvRueck;
	}
	
	
	
	/**
	 * korrektur der temporaeren id_atom_start und -ziel-felder 
	 * @return korr-Sql-Statement
	 * @throws myException 
	 */
	public String get_korr_statement_temp_id_atom(MyE2_MessageVector mvRueck) throws myException {
		String 		c_rueck = null;
		String[][] 	id_vektor = bibDB.EinzelAbfrageInArray("SELECT START_ATOM("+this.get_ID_BEWEGUNG_VEKTOR_cUF()+"),ZIEL_ATOM("+this.get_ID_BEWEGUNG_VEKTOR_cUF()+")"+
		                                                 " FROM DUAL","");
		
		if (id_vektor!=null && id_vektor.length==1) {
			if (S.isFull(id_vektor[0][0])) {
				this.set_NEW_VALUE_ID_BEWEGUNG_ATOM_TRIGSTART(id_vektor[0][0]);
			}
			if (S.isFull(id_vektor[0][1])) {
				this.set_NEW_VALUE_ID_BEWEGUNG_ATOM_TRIGZIEL(id_vektor[0][1]);
			}
			//wenn beide null sind, dann fehler
			if (S.isFull(id_vektor[0][0]) || S.isFull(id_vektor[0][1])) {
				c_rueck = this.get_SQL_UPDATE_STD();
			} else {
				mvRueck.add(new MyE2_Alarm_Message(new MyE2_String("Fehler bei der Korrektur von id_bewegung_vektor: ",true,this.get_ID_BEWEGUNG_VEKTOR_cUF(),false)));
			}
		} else {
			mvRueck.add(new MyE2_Alarm_Message(new MyE2_String("Fehler bei der Korrektur von id_bewegung_vektor: ",true,this.get_ID_BEWEGUNG_VEKTOR_cUF(),false)));
		}
		return c_rueck;
	}
	

	
	
}



