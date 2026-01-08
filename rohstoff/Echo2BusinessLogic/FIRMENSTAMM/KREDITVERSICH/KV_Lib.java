/**
 * rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KREDITVERSICH
 * @author martin
 * @date 07.06.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KREDITVERSICH;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.KREDITVERS_ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.KREDITVERS_KOPF;
import panter.gmbh.basics4project.DB_ENUMS.KREDITVERS_POS;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_RG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.O;
import panter.gmbh.indep.PAIR;
import panter.gmbh.indep.S;
import panter.gmbh.indep.TRIPLE;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_BigDecimal;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Date;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.TERM.TermLMR;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.HMAP;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * @date 07.06.2019
 *
 */
public class KV_Lib {

	
	/**
	 * 
	 * @author martin
	 * @date 07.06.2019
	 *
	 * @param queryField (KREDITVERS_POS.id_kreditvers_pos,KREDITVERS_POS.id_kreditvers_kopf,KREDITVERS_KOPF.fakturierungsfrist,MIN(KREDITVERS_KOPF.fakturierungsfrist,COUNT("+KREDITVERS_POS.id_kreditvers_pos
	 * @param idAdresse
	 * @param leistungsDatum
	 * @return
	 * @throws myException
	 */
	public static SEL getSELKreditversicherungMitFakturierungsFrist(String queryField, Long idAdresse, Date leistungsDatum) throws myException {
		
		VEK<String> vAllowedFields = new VEK<String>()	._a(KREDITVERS_POS.id_kreditvers_pos.tnfn())
														._a(KREDITVERS_POS.id_kreditvers_kopf.tnfn())
														._a(KREDITVERS_KOPF.fakturierungsfrist.tnfn())
														._a("MIN("+KREDITVERS_KOPF.fakturierungsfrist.tnfn()+")")
														._a("COUNT("+KREDITVERS_POS.id_kreditvers_pos.tnfn()+")")
														;
		
		if (!vAllowedFields.contains(queryField)) {
			throw new myException("System-Error: <31139c56-8922-11e9-bc42-526af7764f64>: queryField is NO allowed field !");
		}

		if (O.NN(idAdresse,0l)==0l) {
			throw new myException("System-Error: <244c0bcc-8920-11e9-bc42-526af7764f64>: AdresseID MUST NOT be null");
		}
		
		if (leistungsDatum==null) {
			throw new myException("System-Error: <403efe48-8920-11e9-bc42-526af7764f64>: leistungsDatum MUST NOT be null");
		}

		
		SEL sel_kv_Frist = 
				new SEL(queryField)
				.FROM(_TAB.kreditvers_pos)
				.INNERJOIN(_TAB.kreditvers_kopf, KREDITVERS_KOPF.id_kreditvers_kopf, KREDITVERS_POS.id_kreditvers_kopf)
				.INNERJOIN(_TAB.kreditvers_adresse, KREDITVERS_ADRESSE.id_kreditvers_kopf, KREDITVERS_KOPF.id_kreditvers_kopf)
				.WHERE(	new TermLMR(KREDITVERS_ADRESSE.id_adresse.fn(),COMP.EQ.s(), "?" ) ) 
				.AND( new TermLMR("trunc("+KREDITVERS_POS.gueltig_ab.tnfn() + ",'DD')",COMP.LE.s(),"trunc(?,'DD')" )   )
				.AND( new TermLMR("trunc(nvl("+KREDITVERS_POS.gueltig_bis.tnfn() + ",sysdate),'DD')",COMP.GE.s(),"trunc(?,'DD')" )   )
				.AND( new TermSimple("NVL("+KREDITVERS_KOPF.fakturierungsfrist.tnfn()+",0)>0"))
				.ORDER("1");
				;
		
		SqlStringExtended sqlExt = new SqlStringExtended(sel_kv_Frist.s());
		sqlExt.getValuesList().add(new Param_BigDecimal( new BigDecimal(idAdresse)));
		sqlExt.getValuesList().add(new Param_Date(leistungsDatum));
		sqlExt.getValuesList().add(new Param_Date(leistungsDatum));

		return sel_kv_Frist;
	}
	
	
	public static SqlStringExtended getSQLExtKreditversicherungMitFakturierungsFrist(String queryField, Long idAdresse, Date leistungsDatum) throws myException {
		SqlStringExtended sqlExt = new SqlStringExtended(KV_Lib.getSELKreditversicherungMitFakturierungsFrist(queryField, idAdresse, leistungsDatum).s());
		sqlExt.getValuesList().add(new Param_BigDecimal( new BigDecimal(idAdresse)));
		sqlExt.getValuesList().add(new Param_Date(leistungsDatum));
		sqlExt.getValuesList().add(new Param_Date(leistungsDatum));

		return sqlExt;
	}


	
	
	public static SqlStringExtended getSqlQueryKreditversicherungWithFaktFristExists(Long idAdresse) throws myException {
		SEL sel_kv_exists = 
				new SEL("COUNT(*)")
				.FROM(_TAB.kreditvers_pos)
				.INNERJOIN(_TAB.kreditvers_kopf, KREDITVERS_KOPF.id_kreditvers_kopf, KREDITVERS_POS.id_kreditvers_kopf)
				.INNERJOIN(_TAB.kreditvers_adresse, KREDITVERS_ADRESSE.id_kreditvers_kopf, KREDITVERS_KOPF.id_kreditvers_kopf)
				.WHERE(	new TermLMR(KREDITVERS_ADRESSE.id_adresse.fn(),COMP.EQ.s(), "?" ) ) 
				.AND(   new TermSimple("NVL("+KREDITVERS_KOPF.fakturierungsfrist.tnfn()+",0)>0"));
				;
		
		return new SqlStringExtended(sel_kv_exists.s())._addParameter(new Param_Long(idAdresse));
				
	}
	
	

	/**
	 * 
	 * @author martin
	 * @date 28.06.2019
	 *
	 * @param idAdresse
	 * @return s number of kreditversicherungspositions with faktfrist
	 * @throws myException
	 */
	public static Long getCountKreditVersicherungsPositionenMitFaktFrist(Long idAdresse) throws myException {
		VEK<Object[]> result = bibDB.getResultLines(KV_Lib.getSqlQueryKreditversicherungWithFaktFristExists(idAdresse),true);
		
		if (result==null) {
			throw new myException("Error Querying Number of KV-Positions with Faktfrist in Header: Code<829eb798-99a0-11e9-80fe-2a2ae2dbcce4>");
		} else {
			return ((BigDecimal)result.get(0)[0]).longValue();
		}
	}
	
	
	
	/**
	 * 
	 * @author martin
	 * @date 24.06.2019
	 *
	 * @param idAdresse
	 * @param leistungsDatum
	 * @return verlaengerte fakturierungsfrist of kunde in date or null (when no faktfrist)
	 * @throws myException
	 */
	public static Long getVerlaengerteFakturierungsFrist(long idAdresse, Date leistungsDatum) throws myException {
		// jetzt nachsehen, ob zum leistungszeitpunkt eine kreditversicherung existiert
		SqlStringExtended query = KV_Lib.getSQLExtKreditversicherungMitFakturierungsFrist("MIN(" + KREDITVERS_KOPF.fakturierungsfrist.tnfn() + ")"
									, idAdresse
									, leistungsDatum
									);
		String[][] kvTage = bibDB.EinzelAbfrageInArray(query);

		if (kvTage == null) {
			throw new myException("Error querying: "+query.getSqlString()+" Code:<b91851c6-9694-11e9-bc42-526af7764f64>"); 
		} else {
			if (kvTage.length == 0 || kvTage[0].length == 0 || S.isEmpty(kvTage[0][0]) 	|| kvTage[0][0].trim().equals("0")) {
				return null;
			} else {
				return Long.parseLong(kvTage[0][0]);
			}
		}
	}
	
	
	
	
	/**
	 * Abfrage alle Kreditversicherungs-position und deren fakturierungsfristen aus dem zugehoerenden Kopf (mit distinct keine doppelten)
	 * um festzustelle, ob eine doppelung innerhalb einer position oder eines belegs vorliegt. doppelung heisst auch:
	 * eine position mit und eine ohne fakturierungsfrist 
	 * 
	 * 
	 * @author martin
	 * @date 27.06.2019
	 * 
	 * @param VEK<PAIR<Long, Date>> is vector of pairs with id_adresse,leistungsdatum
	 * @return VEK<KV_PosUndTage> is vector of pairs with id_kredivers_pos, leistungsdatum
	 * Bei einem Beleg darf nur ein einziger kreditversicherungsvertrag mit fakturierungsfrist vorkommen, keine mehrfachen und
	 * auch keine mischformen aus position mit vertrag und ohne vertrag 
	 * @throws myException
	 */
	public static VEK<KV_IdKvPosUndFristTage>  getAllFaktFristIdsAndVals(VEK<KV_idAdresseUndRecVposRg> v_idAdresseUndRecVposRg) throws myException {
			
		try {
			SEL sel = new SEL(KREDITVERS_POS.id_kreditvers_pos,KREDITVERS_KOPF.fakturierungsfrist).ADD_Distinct()
						.FROM(_TAB.kreditvers_pos)
						.INNERJOIN(_TAB.kreditvers_kopf, KREDITVERS_KOPF.id_kreditvers_kopf, KREDITVERS_POS.id_kreditvers_kopf)
						.INNERJOIN(_TAB.kreditvers_adresse, KREDITVERS_ADRESSE.id_kreditvers_kopf, KREDITVERS_KOPF.id_kreditvers_kopf)
						.WHERE(	new TermLMR(KREDITVERS_ADRESSE.id_adresse.fn(),COMP.EQ.s(), "?" ) ) 
						.AND( new TermLMR("trunc("+KREDITVERS_POS.gueltig_ab.tnfn() + ",'DD')",COMP.LE.s(),"trunc(?,'DD')" )   )
						.AND( new TermLMR("trunc(nvl("+KREDITVERS_POS.gueltig_bis.tnfn() + ",sysdate),'DD')",COMP.GE.s(),"trunc(?,'DD')" )   )
						.AND( new TermSimple("NVL("+KREDITVERS_KOPF.fakturierungsfrist.tnfn()+",0)>0"))
						.ORDER("1");
						;
			
			DecimalFormat df = new DecimalFormat("00000000000000");
			HMAP<String, KV_IdKvPosUndFristTage> hmMap = new HMAP<>();
						 
			for (KV_idAdresseUndRecVposRg  pair: v_idAdresseUndRecVposRg) {
				SqlStringExtended sqlExt = new SqlStringExtended(sel.s());
				sqlExt.getValuesList().add(new Param_Long(pair.idAdresse));
				sqlExt.getValuesList().add(new Param_Date(pair.rec21VposRg.getDateDbValue(VPOS_RG.ausfuehrungsdatum)));
				sqlExt.getValuesList().add(new Param_Date(pair.rec21VposRg.getDateDbValue(VPOS_RG.ausfuehrungsdatum)));
				

				VEK<Object[]>  result = bibDB.getResultLines(sqlExt, true);
				
				if (result.size()==0) {
					hmMap.put(df.format(0l)+"@"+df.format(0l), new KV_IdKvPosUndFristTage(0l,0l));
				} else {
					//falls in RecVposRg entweder keine zugehoerige kontraktpos oder die kontraktpos nicht den schalter faktfrist enthaelt, wird das wie keine frist gehandelt (0)
					if (pair.rec21VposRg.hatVerlaengerteFaktFristSchalterImKontrakt()) {
						for (Object[] r: result) {
							Long idKVPos = ((BigDecimal)r[0]).longValue();
							Long tage =    ((BigDecimal)r[1]).longValue();
							hmMap.put(df.format(idKVPos)+"@"+df.format(tage), new KV_IdKvPosUndFristTage(idKVPos,tage));
						}
					} else {
						hmMap.put(df.format(0l)+"@"+df.format(0l), new KV_IdKvPosUndFristTage(0l,0l));
					}
				}
			}
			return new VEK<KV_IdKvPosUndFristTage>()._a(hmMap.values());
		} catch (Exception e) {
			e.printStackTrace();
			throw new myException("Error querying krediversich_pos/fakturierungsfrist: Code: <d5d9e3a6-6dce-4492-9789-2ab99e4842e6>");
		}
	}

	
	
	
	
	/**
	 * Abfrage alle Kreditversicherungs-position zu einer adresse, die eine verlaengerte fakturierungsfrist besitzen 
	 * 
	 * 
	 * @author martin
	 * @date 27.06.2019
	 * 
	 * @param  id_adresse is adresse with kreditversicherung
	 * @return VEK<TRIPLE<Long,Long,Long>> is vector of triple mit idKVKopf, idKvPos, faktfrist, wenn keine gefunden, dann leere rueckgabe
	 * 
	 * @throws myException
	 */
	public static VEK<TRIPLE<Long,Long,Long>>  getAllKVPositionsWithFaktFristIds(Long idAdresse) throws myException {
			
		try {
			SEL sel = new SEL(KREDITVERS_KOPF.id_kreditvers_kopf,KREDITVERS_POS.id_kreditvers_pos,KREDITVERS_KOPF.fakturierungsfrist)
						.FROM(_TAB.kreditvers_pos)
						.INNERJOIN(_TAB.kreditvers_kopf, KREDITVERS_KOPF.id_kreditvers_kopf, KREDITVERS_POS.id_kreditvers_kopf)
						.INNERJOIN(_TAB.kreditvers_adresse, KREDITVERS_ADRESSE.id_kreditvers_kopf, KREDITVERS_KOPF.id_kreditvers_kopf)
						.WHERE(	new TermLMR(KREDITVERS_ADRESSE.id_adresse.fn(),COMP.EQ.s(), "?" ) ) 
						.ORDER("1");
						;
						 
			SqlStringExtended sqlExt = new SqlStringExtended(sel.s());
			sqlExt.getValuesList().add(new Param_Long(idAdresse));

			VEK<Object[]>  result = bibDB.getResultLines(sqlExt, true);
			VEK<TRIPLE<Long,Long,Long>>  ret = new VEK<TRIPLE<Long,Long,Long>>();
			for (Object[] r: result) {
				Long idKVKopf = ((BigDecimal)r[0]).longValue();
				Long idKVPos = ((BigDecimal)r[1]).longValue();
				Long faktFrist = O.NN((BigDecimal)r[2],BigDecimal.ZERO).longValue();
				ret._a(new TRIPLE<Long,Long,Long>()._setVal1(idKVKopf)._setVal2(idKVPos)._setVal3(faktFrist));
			}
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			throw new myException("Error querying krediversich_pos: Code: <ce8610a2-9bd5-11e9-a2a3-2a2ae2dbcce4>");
		}
	}

	
	
	/**
	 * prueft, ob bei allen kredtiversicherungen einer Adresse mit Fakturierungsfrist maximal die letzte ohne abschlussdatum ist 
	 * @author martin
	 * @date 01.07.2019
	 *
	 * @param idAdresse
	 * @param idKopfAndFaktFrist (null, falls aus positionen, sonst der aktuelle kopf und die dazugehoerenden fakturierungsfristen
	 * @param zusatzPosition (null falls aus kopf oder triple aus (ID_Kreditversichungs_position / 0 wenn neu, gueltig_ab, gueltig_bis aus der maske)
	 * @return
	 */
	public static MyE2_MessageVector checkCorrectEndDates(Long idAdresse,PAIR<Long,Long> idKopfAndFaktFrist,  TRIPLE<Long, Date, Date> zusatzPosition ) throws Exception {
		
		MyE2_MessageVector mv = bibMSG.getNewMV();
		
		VEK<TRIPLE<Long,Long,Long>> ids_and_frist = 	KV_Lib.getAllKVPositionsWithFaktFristIds(idAdresse);   //triple: idKVKopf, idKVPos, faktfrist (auch leere faktfristen) aller KV-positionen des kunden
		
		Long idKVKopfAusMaske = 0l;
		if (idKopfAndFaktFrist!=null) {
			idKVKopfAusMaske = idKopfAndFaktFrist.getVal1();
		}
		
		Long idPosAusMaske = 0l;
		if (zusatzPosition!=null &&  O.NN(zusatzPosition.getVal1(),0l).longValue()>0) {
			idPosAusMaske = zusatzPosition.getVal1();
		}
		
		VEK<TRIPLE<Long, Date, Date>> v_sammler = new VEK<>();
		
		for (TRIPLE<Long,Long,Long> triple: ids_and_frist) {
			Long idKopf = triple.getVal1();
			Long idPos  = triple.getVal2();
			Long faktFrist = triple.getVal3();
			
			//wert komplett ausserhalb der masken
			if (idKopf.longValue()!=idKVKopfAusMaske.longValue()   && idPos.longValue()!=idPosAusMaske.longValue()) { 
				Rec21 r = new Rec21(_TAB.kreditvers_pos)._fill_id(idPos);
				if (faktFrist.longValue()>0) {
					v_sammler._a(new TRIPLE<Long, Date, Date>()	._setVal1(r.getIdLong())
																._setVal2(((Date)r.getRawVal(KREDITVERS_POS.gueltig_ab)))
																._setVal3(((Date)r.getRawVal(KREDITVERS_POS.gueltig_bis))));
				}
			} else if (idKopf.longValue()==idKVKopfAusMaske.longValue()   && idPos.longValue()!=idPosAusMaske.longValue()) {
				Rec21 r = new Rec21(_TAB.kreditvers_pos)._fill_id(idPos);
				if (O.NN(idKopfAndFaktFrist.getVal2(),0l).longValue()>0) {
					//dann ist die momentane kopfmaske auch mit einer faktfrist gesegnet die fuer das speichern gilt
					v_sammler._a(new TRIPLE<Long, Date, Date>()	._setVal1(r.getIdLong())
																._setVal2(((Date)r.getRawVal(KREDITVERS_POS.gueltig_ab)))
																._setVal3(((Date)r.getRawVal(KREDITVERS_POS.gueltig_bis))));
					
				}
			}
		}
		
		
		if (zusatzPosition!=null) {
			v_sammler._a(zusatzPosition);
		}

		v_sammler.sort(new Comparator<TRIPLE<Long, Date, Date>>() {

			@Override
			public int compare(TRIPLE<Long, Date, Date> o1, TRIPLE<Long, Date, Date> o2) {
				
				Date gueltigAb_1 = (Date)o1.getVal2();     //sortiert wird mit dem startdatum
				Date gueltigAb_2 = (Date)o2.getVal2();
				if (gueltigAb_1.before(gueltigAb_2)) {
					return -1;
				} else if (gueltigAb_1.after(gueltigAb_2)) {
					return 1;
				} else {
					return 0;
				}
			}
		});
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		//jetzt nachsehen, ob ein kv-vertrag ausser dem letzten ein offenes end-datum hat
		int countOffene = 0;
		for (TRIPLE<Long, Date, Date> r: v_sammler) {
			DEBUG._print(df.format(r.getVal2()));
			
			if (r.getVal3()==null) {
				countOffene++;
			}
		}
		

		if (countOffene>1 || (countOffene==1 && v_sammler.lastElement().getVal3()!=null)) {
			MyE2_String fehlermeldung =  S.ms("Achtung! Verlängerte Fakturierungsfrist: Fehler bei Konsistenzpruefung: \n")
					.t("Bitte prüfen Sie alle (auch inaktive) Kreditversicherungen mit verlängerter Fakturierungsfrist! In allen Versicherungen\n")
					.t("darf nur die neueste Position (sortiert nach Beginn-Datum) \n")
					.t("ein leeres Ablaufdatum besitzen !");
			
			return bibMSG.getNewMV()._addAlarm(fehlermeldung);
					
		}				
		
		return mv;
		
	}
	
	
	
	
}
