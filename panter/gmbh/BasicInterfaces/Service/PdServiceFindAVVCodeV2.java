/**
 * panter.gmbh.BasicInterfaces.Service
 * @author martin
 * @date 18.03.2019
 * 
 */
package panter.gmbh.BasicInterfaces.Service;

import java.util.HashMap;

import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKELBEZ_LIEF;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.EAK_CODE;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.O;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_String;
import panter.gmbh.indep.dataTools.TERM.VglNotNull;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_artikel_bez;


/**
 * 
 * @author martin
 * @date 08.01.2020
 *
 */
public class PdServiceFindAVVCodeV2 {
	

	public Rec21 getAVVCode(Rec21_adresse adresse, Rec21 artikelBez, boolean isLeft, StringBuffer herkunft) throws myException {
		Rec21 ret = null;
		
		try {
			if (O.isNoOneNull(adresse,artikelBez)) {
				Rec21 artikel = artikelBez.get_up_Rec21(ARTIKEL.id_artikel);
				if (adresse.isAdressOfMandant()) {
					if (isLeft) {
						ret = artikel.get_up_Rec21(ARTIKEL.id_eak_code_ex_mandant, EAK_CODE.id_eak_code, true);
						herkunft.append("AVV-Code wurde aus dem Artikelstamm (EX-Mandant) geladen");
					} else {
						ret = artikel.get_up_Rec21(ARTIKEL.id_eak_code, EAK_CODE.id_eak_code, true);
						herkunft.append("AVV-Code wurde aus dem Artikelstamm (Eingang) geladen");
					}
				} else {
					if (isLeft) {
						
						//fremdadresse ist lieferant
						And bed = new And(	new vgl(ARTIKELBEZ_LIEF.artbez_typ,"EK"))
								.and(new vgl(ARTIKELBEZ_LIEF.id_artikel_bez,artikelBez.getUfs(ARTIKEL_BEZ.id_artikel_bez)))
								.and(new VglNotNull(ARTIKELBEZ_LIEF.id_eak_code))
								;
	
						RecList21 rl = adresse._getMainAdresse().get_down_reclist21(ARTIKELBEZ_LIEF.id_adresse, bed.s(), null);
	
						if (rl.size()==1) {
							ret = rl.get(0).get_up_Rec21(EAK_CODE.id_eak_code);
							herkunft.append("AVV-Code wurde aus den kundenspezifischen Sorten geladen ( Typ:"+(isLeft?"EK":"VK")+")");
						}
						
						if (ret==null) {
							//dann avv fuer anlieferer nehmen
							ret = artikel.get_up_Rec21(ARTIKEL.id_eak_code, EAK_CODE.id_eak_code, true);
							herkunft.append("AVV-Code wurde aus dem Artikelstamm (Eingang) geladen");
						}
						
					} else {
						
						// nachsehen, ob die fremdadresse eine verkaufs-avv hat
						//fremdadresse ist abnehmer
						And bed = new And(	new vgl(ARTIKELBEZ_LIEF.artbez_typ,"VK"))
								.and(new vgl(ARTIKELBEZ_LIEF.id_artikel_bez,artikelBez.getUfs(ARTIKEL_BEZ.id_artikel_bez)))
								.and(new VglNotNull(ARTIKELBEZ_LIEF.id_eak_code))
								;
	
						RecList21 rl = adresse._getMainAdresse().get_down_reclist21(ARTIKELBEZ_LIEF.id_adresse, bed.s(), null);
	
						if (rl.size()==1) {
							ret = rl.get(0).get_up_Rec21(EAK_CODE.id_eak_code);
							herkunft.append("AVV-Code wurde aus den kundenspezifischen Sorten geladen ( Typ:"+(isLeft?"EK":"VK")+")");
						}
						
						//falls keine abnehmer-avv in kundenspezifischen verkaufssorten, dann rechte avv leer lassen
					}
					
					//falls immer noch nichts gefunden, dann
				}
			}
			if (ret == null || ret.is_newRecordSet()) {
				ret = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new myException(e.getLocalizedMessage()+"<3e0e45c8-320a-11ea-aec2-2e728ce88125>");
		}
		
		return ret;
	}
	
	
	
	
	
	
	public enum ENUM_RetValues {
		avvcode,   		// String
		gefaehrlich,	// boolean
		id_eak_code,	// Long
		herkunft		// String
	}
	
	public String getAVVCode_As_String(Rec21_adresse adresse, Rec21_artikel_bez artikelbez, boolean bIstLieferant,HashMap<ENUM_RetValues, Object> hmResult) throws myException {
		
		String sAvvCode = null;
		
		StringBuffer sbHerkunft = new StringBuffer();
		Rec21 recAVV = getAVVCode(adresse,artikelbez,bIstLieferant,sbHerkunft);
		if (recAVV != null) {
			
			String sqlAVV = " "
					+ "			   SELECT " + 
					"			    JT_EAK_BRANCHE.KEY_BRANCHE||'-'|| " + 
					"			    JT_EAK_GRUPPE.KEY_GRUPPE||'-'|| " + 
					"			    JT_EAK_CODE.KEY_CODE||' '|| " + 
					"			    TRANSLATE(NVL(JT_EAK_CODE.GEFAEHRLICH,'N'),'YN','* ') " +
					"				,NVL(JT_EAK_CODE.GEFAEHRLICH ,'N')	" +	
					"			 FROM " + 
					"			   JT_EAK_CODE " + 
					"			   LEFT OUTER JOIN JT_EAK_GRUPPE 	ON (JT_EAK_CODE.ID_EAK_GRUPPE    = JT_EAK_GRUPPE.ID_EAK_GRUPPE 		AND JT_EAK_CODE.ID_MANDANT 	 = JT_EAK_GRUPPE.ID_MANDANT) " + 
					"			   LEFT OUTER JOIN JT_EAK_BRANCHE 	ON (JT_EAK_GRUPPE.ID_EAK_BRANCHE = JT_EAK_BRANCHE.ID_EAK_BRANCHE 	AND JT_EAK_GRUPPE.ID_MANDANT = JT_EAK_BRANCHE.ID_MANDANT) " + 
					"			 WHERE " + 
					"			   JT_EAK_CODE.ID_EAK_CODE = ? AND " + 
					"			   JT_EAK_CODE.ID_MANDANT = ?" +
					" ";
			
			
			SqlStringExtended sqlExt = new SqlStringExtended(sqlAVV)
						._addParameter(new Param_Long(recAVV.getIdLong()))
						._addParameter(new Param_Long(recAVV.get_raw_resultValue_Long(EAK_CODE.id_mandant)));
						
			
				String sResult[][] = bibDB.EinzelAbfrageInArray(sqlExt);
				if (sResult.length == 1 ) {
					sAvvCode 	= sResult[0][0];
					String sGefaehrlich = sResult[0][1];
					
					// hmResult hält eine Mengen von Werten, die in der ENUM_RetValues definiert sind.
					hmResult.put(ENUM_RetValues.avvcode, sAvvCode);
					hmResult.put(ENUM_RetValues.gefaehrlich, sGefaehrlich.equalsIgnoreCase("Y") ? true : false);
					hmResult.put(ENUM_RetValues.id_eak_code,recAVV.getIdLong());
					hmResult.put(ENUM_RetValues.herkunft, sbHerkunft.toString());
				}
			} 
		
		
		
		return sAvvCode;
	}
	
	
//	
//	/**
//	 * 
//	 * @author manfred
//	 * @date 04.02.2021
//	 *
//	 * @param idAdresse
//	 * @param idArtikelbez
//	 * @param bIstLieferant
//	 * @param hmResult Rückgabewerte zusätzlich: (key:value) = {avvcode:String,gefaehrlich:boolean,id_eak_code:Long}
//	 * @return avvcode in der Form 01-02-03 *  (* == gefaehrlich)
//	 */
//	public String getAVVCode_As_String(String idAdresse, String idArtikelbez, boolean bIstLieferant,HashMap<String, Object> hmResult) {
//		
//		
//		
//		
//		Long lidArtikelBez = new MyLong(idArtikelbez!=null ? idArtikelbez : "-1").get_lValue();
//		Long lidAdresseKunde = new MyLong(idAdresse!=null ? idAdresse : "-1").get_lValue();
//		
//		String ekvk =  bIstLieferant ? "EK" : "VK";
//	
//		
//		String sqlAVV = " "
//				+ "			   SELECT " + 
//				"			    JT_EAK_BRANCHE.KEY_BRANCHE||'-'|| " + 
//				"			    JT_EAK_GRUPPE.KEY_GRUPPE||'-'|| " + 
//				"			    JT_EAK_CODE.KEY_CODE||' '|| " + 
//				"			    TRANSLATE(NVL(JT_EAK_CODE.GEFAEHRLICH,'N'),'YN','* ') " +
//				"				,JT_EAK_CODE.ID_EAK_CODE	" +	
//				"				,NVL(JT_EAK_CODE.GEFAEHRLICH ,'N')	" +	
//				"			 FROM " + 
//				"			   JT_EAK_CODE " + 
//				"			   LEFT OUTER JOIN JT_EAK_GRUPPE ON (JT_EAK_CODE.ID_EAK_GRUPPE          = JT_EAK_GRUPPE.ID_EAK_GRUPPE) " + 
//				"			   LEFT OUTER JOIN JT_EAK_BRANCHE ON (JT_EAK_GRUPPE.ID_EAK_BRANCHE   = JT_EAK_BRANCHE.ID_EAK_BRANCHE) " + 
//				"			 WHERE " + 
//				"			   JT_EAK_CODE.ID_EAK_CODE=nvl( " + 
//				"			                               (SELECT MAX(ABL1.ID_EAK_CODE) FROM JT_ARTIKELBEZ_LIEF ABL1 " + 
//				"        		                                        	WHERE ABL1.ID_ADRESSE          = ?" + 
//				"			                                        	AND ABL1.ID_ARTIKEL_BEZ        = ?" + 
//				"			                                        	AND ABL1.ARTBEZ_TYP            =  nvl(?,'-')) " + 
//				"			                                , 													" + 
//				"			                                ( SELECT CASE WHEN 'EK'= nvl(?,'-')  " + 
//				"			                                                      THEN   A1.ID_EAK_CODE " + 
//				"			                                                      ELSE   A1.ID_EAK_CODE_EX_MANDANT  " + 
//				"			                                                      END " + 
//				"			                                                FROM JT_ARTIKEL A1 " + 
//				"			                                                INNER JOIN JT_ARTIKEL_BEZ B1 ON A1.ID_ARTIKEL = B1.ID_ARTIKEL " + 
//				"			                                                WHERE B1.ID_ARTIKEL_BEZ = ?   " + 
//				"			                                         ) " + 
//				"			                               ) " +
//				" ";
//		
//		String sAvvCode = null;
//		if (lidArtikelBez > 0L ) {
//			SqlStringExtended sqlExt = new SqlStringExtended(sqlAVV)
//					._addParameter(new Param_Long(lidAdresseKunde))
//					._addParameter(new Param_Long(lidArtikelBez))
//					._addParameter(new Param_String("", ekvk))
//					._addParameter(new Param_String("", ekvk))
//					._addParameter(new Param_Long(lidArtikelBez));
//			
//			String sResult[][] = bibDB.EinzelAbfrageInArray(sqlExt);
//			if (sResult.length == 1 ) {
//				sAvvCode 	= sResult[0][0];
//				String sID_EAK_CODE = sResult[0][1];
//				String sGefaehrlich = sResult[0][2];
//				// {avvcode:String,gefaehrlich:boolean,id_eak_code:integer}
//				hmResult.put("avvcode", sAvvCode);
//				hmResult.put("gefaehrlich", sGefaehrlich.equalsIgnoreCase("Y") ? true : false);
//				hmResult.put("id_eak_code", new MyLong(sID_EAK_CODE).get_oLong());
//			}
//		} 
//		
//		
//		return sAvvCode;
//	}
//	
//	
//	
	
}
