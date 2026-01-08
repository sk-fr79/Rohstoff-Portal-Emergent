/**
 * rohstoff.businesslogic.bewegung2.global
 * @author martin
 * @date 04.01.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.global;

import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.HMAP;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * @date 04.01.2019
 * klasse, um die ids einer 3-wertigen transportzeile zu lesen
 */
public class EnJoins4Querys {

	public enum NamesOfFieldsToRead {
		
		 JT_BG_VEKTOR(BG_VEKTOR.id_bg_vektor,	null,   	null,null)	
//		,VE(BG_VEKTOR.id_bg_vektor, 		new vgl("VE",	BG_VEKTOR.id_bg_vektor, "JT_BG_TRANSPORT", 	BG_TRANSPORT.id_bg_vektor),  	
//											null, 
//											null,
//											false)
//
		 
// alt		 
//		,S1(	BG_STATION.id_bg_station, 	new vgl("S1",	BG_STATION.id_bg_vektor, "JT_BG_VEKTOR", 	BG_VEKTOR.id_bg_vektor),  	
//											new TermLMR(    BG_STATION.pos_in_mask.fn(	"S1"), "=", EnStationPos.S1.dbVal4SqlTerm()), 
//											null, 
//											false)
//		,A1(	BG_ATOM.id_bg_atom, 		new vgl("S1",	BG_STATION.id_bg_station, 	"A1", 	BG_ATOM.id_bg_station_quelle),	
//											null, 
//											null, 
//											false)	
//		,S2(	BG_STATION.id_bg_station,   new vgl("A1", 	BG_ATOM.id_bg_station_ziel,	"S2",  	BG_STATION.id_bg_station), 	
//											null, 
//											null, 
//											false)
//		,A2(	BG_ATOM.id_bg_atom, 		new vgl("S2", 	BG_STATION.id_bg_station, 	"A2",  	BG_ATOM.id_bg_station_quelle),	
//											null, 
//											null, 
//											false )
//		,S3(	BG_STATION.id_bg_station,   new vgl("A2", 	BG_ATOM.id_bg_station_ziel, "S3",  	BG_STATION.id_bg_station),	
//											null, 
//											null, 
//											true)
//		;
		 
// neu Manfred ->		
//		,S1(	BG_STATION.id_bg_station, 	new vgl("S1",	BG_STATION.id_bg_vektor, "JT_BG_VEKTOR", 	BG_VEKTOR.id_bg_vektor),  	
//						new TermLMR(    BG_STATION.pos_in_mask.fn(	"S1"), "=", EnTabKeyInMask.S1.dbVal4SqlTerm()), 
//						null, 
//						false)
//		,S2(	BG_STATION.id_bg_station,   new vgl("S2",	BG_STATION.id_bg_vektor, "JT_BG_VEKTOR", 	BG_VEKTOR.id_bg_vektor), 	
//				 new TermLMR(    BG_STATION.pos_in_mask.fn(	"S2"), "=", EnTabKeyInMask.S2.dbVal4SqlTerm()), 
//						null, 
//						false)
//		,S3(	BG_STATION.id_bg_station,   new vgl("S3",	BG_STATION.id_bg_vektor, "JT_BG_VEKTOR", 	BG_VEKTOR.id_bg_vektor),	
//				new TermLMR(    BG_STATION.pos_in_mask.fn(	"S3"), "=", EnTabKeyInMask.S3.dbVal4SqlTerm()), 
//						null, 
//						false)
//		,A1(	BG_ATOM.id_bg_atom, 		new vgl("S1",	BG_STATION.id_bg_station, 	"A1", 	BG_ATOM.id_bg_station_quelle),	
//						null, 
//						null, 
//						false)	
//		,A2(	BG_ATOM.id_bg_atom, 		new vgl("S2", 	BG_STATION.id_bg_station, 	"A2",  	BG_ATOM.id_bg_station_quelle),	
//						null, 
//						null, 
//						true )
//		;

		 //neu-umgedrehte keys
		 ,A1(	BG_ATOM.id_bg_atom, 		new vgl("JT_BG_VEKTOR",		BG_VEKTOR.id_bg_atom_quelle, 	"A1", 				BG_ATOM.id_bg_atom),	
				 null, 
				 null)	
		 ,A2(	BG_ATOM.id_bg_atom, 		new vgl("JT_BG_VEKTOR", 	BG_VEKTOR.id_bg_atom_ziel, 		"A2",  				BG_ATOM.id_bg_atom),	
				 null, 
				 null )
		,S1(	BG_STATION.id_bg_station, 	new vgl("S1",				BG_STATION.id_bg_station, 		"A1",           	BG_ATOM.id_bg_station_quelle),  	
				null, 
				null)
		,S2(	BG_STATION.id_bg_station,   new vgl("S2",				BG_STATION.id_bg_station, 		"A1", 				BG_ATOM.id_bg_station_ziel), 	
				null, 
				null)
		,S3(	BG_STATION.id_bg_station,   new vgl("S3",				BG_STATION.id_bg_station, 		"A2", 				BG_ATOM.id_bg_station_ziel),	
				null, 
				null)
		;
				
		

		private IF_Field  field = null;
		private Term   	  termRelation = null;
		private Term      addOnTerm1 = null;
		private Term      addOnTerm2 = null;
		
		private NamesOfFieldsToRead(IF_Field p_field, Term p_termRelation, Term p_addonTerm1, Term p_addonTerm2)  {
			this.field = 			p_field;
			this.termRelation = 	p_termRelation;
			this.addOnTerm1 = 		p_addonTerm1;
			this.addOnTerm2 = 		p_addonTerm2;
		}

		public Term getTermRelation() {
			return termRelation;
		}

		public Term getAddOnTerm1() {
			return addOnTerm1;
		}

		public Term getAddOnTerm2() {
			return addOnTerm2;
		}

		public IF_Field getField() {
			return field;
		}

		public boolean isLastMember() {
			NamesOfFieldsToRead lastMember = null;
			for (NamesOfFieldsToRead n: NamesOfFieldsToRead.values()) {
				lastMember = n;
			}
			
			
			return (lastMember==this);
		}

		
		
		
	}
	
	public HMAP<NamesOfFieldsToRead, Long>  getIdsOfRecords(Long idBgVektor) {

		HMAP<NamesOfFieldsToRead, Long> ret = new HMAP<>();
		
		try {
			
			String sql = this.getSqlQuery4AllIdsWithoutWhereStatement();
			
			if (S.isEmpty(sql)) {
				throw new myException("Error buildung SQL-Statemtent");
			}
			
			sql = sql +(" WHERE "+NamesOfFieldsToRead.JT_BG_VEKTOR.field.fn(NamesOfFieldsToRead.JT_BG_VEKTOR.name())+"=?");
			
			//DEBUG._print(query.toString());
			
			SqlStringExtended sp = new SqlStringExtended(sql)._addParameters(new VEK<ParamDataObject>()._a(new Param_Long(idBgVektor)));
			
			String[][] a_s = bibDB.EinzelAbfrageInArray(sp);

			ret = new HMAP<>();
			if (a_s.length!=1) {
				ret = null;
			} else {
				if (a_s[0].length!=NamesOfFieldsToRead.values().length) {
					ret = null;
				} else {
					int counter = 0;
					for (NamesOfFieldsToRead n: NamesOfFieldsToRead.values()) {
						ret._put(n, Long.parseLong(a_s[0][counter++]));
					}
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			ret = null;
		} catch (myException e) {
			e.printStackTrace();
			ret = null;
		}
		

		
		return ret;
	}
	
	
	public String getSqlQuery4AllIdsWithoutWhereStatement() {
		
		String sql = null;
		StringBuffer query = new StringBuffer("SELECT ");
		
		for (NamesOfFieldsToRead n: NamesOfFieldsToRead.values()) {
			query.append(n.field.fn(n.name()));
			if (!n.isLastMember()) {
				query.append(",");
			}
		}
		try {
			for (NamesOfFieldsToRead n: NamesOfFieldsToRead.values()) {
				if (n.termRelation==null) {
					query.append(" FROM "+n.field.fullTableName()+" "+n.name());
				} else {
					String relationString = n.termRelation.s();
					if (n.addOnTerm1!=null) {
						relationString = relationString+" AND "+n.addOnTerm1.s();
					}
					if (n.addOnTerm2!=null) {
						relationString = relationString+" AND "+n.addOnTerm2.s();
					}
					
					query.append(" INNER JOIN "+n.field.fullTableName()+" "+n.name()+ " ON ("+relationString+")");
				}
			}
			
			sql = query.toString();

		} catch (myException e) {
			e.printStackTrace();
		}
		return sql;
	}
	
	
	
	
	public String getSqlQuery4TransportIdsWithoutWhereStatement() {
		
		String sql = null;
		StringBuffer query = new StringBuffer("SELECT "+NamesOfFieldsToRead.JT_BG_VEKTOR.field.fn(NamesOfFieldsToRead.JT_BG_VEKTOR.name()));

		try {
			for (NamesOfFieldsToRead n: NamesOfFieldsToRead.values()) {
				if (n.termRelation==null) {
					query.append(" FROM "+n.field.fullTableName()+" "+n.name());
				} else {
					String relationString = n.termRelation.s();
					if (n.addOnTerm1!=null) {
						relationString = relationString+" AND "+n.addOnTerm1.s();
					}
					if (n.addOnTerm2!=null) {
						relationString = relationString+" AND "+n.addOnTerm2.s();
					}
					
					query.append(" INNER JOIN "+n.field.fullTableName()+" "+n.name()+ " ON ("+relationString+")");
				}
			}
			
			sql = query.toString();

		} catch (myException e) {
			e.printStackTrace();
		}
		return sql;
	}
	
	
}
