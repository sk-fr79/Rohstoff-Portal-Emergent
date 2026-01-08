/**
 * panter.gmbh.json.reader
 * @author martin
 * @date 15.06.2020
 * 
 */
package panter.gmbh.json.reader;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Vector;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;

import panter.gmbh.BasicInterfaces.Service.PdServiceReplaceSysVariablesInString;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.myVectors.HMAP;
import panter.gmbh.indep.myVectors.VEK;
import panter.gmbh.json.EnumJsonFileNames;
import panter.gmbh.json.defClass.JsonMaskTransportArtSetting;
import rohstoff.businesslogic.bewegung2.global.EnTabKeyInMask;
import rohstoff.businesslogic.bewegung2.global.EnTransportTyp;
import rohstoff.businesslogic.bewegung2.global.TK;
import rohstoff.businesslogic.bewegung2.recs.RecA1;
import rohstoff.businesslogic.bewegung2.recs.RecA2;
import rohstoff.businesslogic.bewegung2.recs.RecS1;
import rohstoff.businesslogic.bewegung2.recs.RecS2;
import rohstoff.businesslogic.bewegung2.recs.RecS3;
import rohstoff.businesslogic.bewegung2.recs.RecV;

/**
 * @author martin
 * @date 15.06.2020
 *
 *
 * @author martin
 * @date 26.05.2020
 *
 Beispiel-File:
 
 // moegliche namen: 
//  WE("Wareneingang (Einkauf)",							true, false, 	true, 		false, 	EnBesitzerTyp.QUELLFIRMA, EnBesitzerTyp.MANDANT, EnBesitzerTyp.MANDANT) 
// 	,WA("Warenausgang (Verkauf)",							true, false, 	false, 		true, 	EnBesitzerTyp.MANDANT, EnBesitzerTyp.MANDANT, EnBesitzerTyp.ZIELFIRMA) 
// 	,STRECKE("Strecke (Einkauf-Verkauf)",					true, false, 	true, 		true, 	EnBesitzerTyp.QUELLFIRMA, EnBesitzerTyp.MANDANT, EnBesitzerTyp.ZIELFIRMA) 
// 	,LAGER_LAGER("Umlagerung Eigenware",					true, true, 	false, 		false, 	EnBesitzerTyp.MANDANT, EnBesitzerTyp.MANDANT, EnBesitzerTyp.MANDANT) 
// 	,FREMDWARENTRANSPORT("Fremdwarentransport",				true, true, 	false, 		false, 	EnBesitzerTyp.FREMDFIRMA, EnBesitzerTyp.FREMDFIRMA, EnBesitzerTyp.FREMDFIRMA) 
// 	,TESTSTELLUNG("Teststellung", 							true, false, 	false, 		false, 	EnBesitzerTyp.MANDANT, EnBesitzerTyp.MANDANT, EnBesitzerTyp.MANDANT)
// 	,LEERGUTRANSPORT("Leerguttransport", 					true, true, 	false, 		false, 	EnBesitzerTyp.MANDANT, EnBesitzerTyp.MANDANT, EnBesitzerTyp.MANDANT) 
// 	,WE_L("Einkauf Fremdware (bereits im Lager)", 			true, true, 	true, 		false, 	EnBesitzerTyp.FREMDFIRMA, EnBesitzerTyp.MANDANT, EnBesitzerTyp.MANDANT) 
// 	,WA_L("Verkauf Eigenware (bleibt im Lager)", 			true, true, 	false, 		true, 	EnBesitzerTyp.MANDANT, EnBesitzerTyp.MANDANT, EnBesitzerTyp.FREMDFIRMA) 
// 	,UMBUCHUNG("Umbuchung",									true, false,	false, 		false, 	EnBesitzerTyp.MANDANT, EnBesitzerTyp.MANDANT, EnBesitzerTyp.MANDANT
// 	,EINBUCHUNG("Einbuchung",								true, true,		false, 		false, 	EnBesitzerTyp.MANDANT, EnBesitzerTyp.MANDANT, EnBesitzerTyp.MANDANT) 
// 	,AUSBUCHUNG("Ausbuchung",								true, true, 	false, 		false, 	EnBesitzerTyp.MANDANT, EnBesitzerTyp.MANDANT, EnBesitzerTyp.MANDANT) 
// 	,EINBUCHUNG_F("Einbuchung Fremdware",					false, true, 	false, 		false, 	EnBesitzerTyp.FREMDFIRMA, EnBesitzerTyp.FREMDFIRMA, EnBesitzerTyp.FREMDFIRMA)
// 	,AUSBUCHUNG_F("Ausbuchung Fremdware",					false, true, 	false, 		false, 	EnBesitzerTyp.FREMDFIRMA, EnBesitzerTyp.FREMDFIRMA, EnBesitzerTyp.FREMDFIRMA) 


//Feldbereiche   V@  S1@ S2@ S3@ A1@ A2@
 * 		Vector<String> enablelist;
		Vector<String> disablelist;
		Vector<String> disablelabellist;
		Vector<String> cleardisablelabellist;
		Vector<String> clearlist;
		Vector<String> cleardisablelist;

 *   
 *   
 {
	"name": 	     "WA",  
	"enablelist" :  ["-"],
	"disablelist" : ["V@id_adresse_fremdware",
                     "V@id_adresse_fremdware"],
	"clearlist":    ["V@bemerkung"],
	"disablelabellist": ["V@bemerkung"],
	"valuesmap" :   { "RecV@id_adresse_fremdware":"333"}
},
{
	"name": 	     "WE",  
	"enablelist" :  ["-"],
	"disablelist" : ["V@id_adresse_fremdware",
				     "V@id_adresse_fremdware"],
	"clearlist":    ["V@bemerkung"],			
	"valuesmap" :   { "RecV@id_adresse_fremdware":"333"}
}
,
{
	"name": 	     "WE_L",  
	"disablelist" : ["V@id_adresse_fremdware",
				     "V@id_adresse_fremdware"],
	"clearlist":    ["V@bemerkung"],			
	"valuesmap" :   { "RecV@id_adresse_fremdware":"333"}
}
	
]

 *
 *
 */


public class JsonMaskTransportArtSettingReader {

	/**
	 * @author martin
	 * @date 15.06.2020
	 *
	 */
	
	private HMAP<String,JsonMaskTransportArtSetting> valuesToSet = new HMAP<String,JsonMaskTransportArtSetting>();
	private File jsonFile = null;

	private HMAP<RB_KM,String> maskKeys = new HMAP<RB_KM, String>();
	
	/**
	 * @author martin
	 * @throws IOException 
	 * @throws JsonProcessingException 
	 * @date 26.05.2020
	 *
	 */
	public JsonMaskTransportArtSettingReader() throws JsonProcessingException, IOException, Exception {
		this.jsonFile=EnumJsonFileNames.B2MaskTransportArtSetting.getFile();

		maskKeys._put(RecV.key, "V")._put(RecA1.key, "A1")._put(RecA2.key, "A2")._put(RecS1.key, "S1")._put(RecS2.key, "S2")._put(RecS3.key, "S3");
		
		ObjectMapper mapper = new ObjectMapper(new JsonFactory().enable(Feature.ALLOW_COMMENTS));
		
		JsonMaskTransportArtSetting[] settings = mapper.readValue(jsonFile, JsonMaskTransportArtSetting[].class);
		
		for (JsonMaskTransportArtSetting setting: settings) {
			valuesToSet.put(setting.getName(), setting);
		}
		
	}

	
	
	public IF_Field[]  getFieldsToEnable(EnTransportTyp typ, RB_KM maskKey) {
		VEK<IF_Field> ret;
		try {
			ret = new VEK<>();
			
			JsonMaskTransportArtSetting ts = this.valuesToSet.get(typ.dbVal());
			String maskTag = this.maskKeys.get(maskKey);
			
			if (ts!=null && S.isFull(maskTag)) {
				Vector<String> fields = ts.getEnablelist();
				if (fields!=null) {
					for (String s: fields) {
						if (s.startsWith(maskTag+"@") && maskKey.getTable()!=null) {
							IF_Field f = _TAB.find_field(maskKey.getTable(), s.substring((maskTag+"@").length()));
							if (f!=null) {
								ret._addIfNotIN(f);
							}
						}
					}
				}
			}
			if (ret.size()==0) {
				return new IF_Field[0];
			} else {
				return ret.getArray();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public IF_Field[]  getFieldsToDisable(EnTransportTyp typ, RB_KM maskKey) {
		VEK<IF_Field> ret;
		try {
			ret = new VEK<>();
			
			JsonMaskTransportArtSetting ts = this.valuesToSet.get(typ.dbVal());
			String maskTag = this.maskKeys.get(maskKey);
			
			if (ts!=null && S.isFull(maskTag)) {
				VEK<String> disablefields = new VEK<String>()._a(ts.getDisablelist());
				VEK<String> clearDisablefields = new VEK<String>()._a(ts.getCleardisablelist());
				VEK<String> clearDisablelabefields = new VEK<String>()._a(ts.getCleardisablelabellist());
				VEK<String> allToDisable = new VEK<String>()._addIfNotIN(disablefields.getArray())._addIfNotIN(clearDisablefields.getArray())._addIfNotIN(clearDisablelabefields.getArray());

				for (String s: allToDisable) {
					if (s.startsWith(maskTag+"@") && maskKey.getTable()!=null ) {
						IF_Field f = _TAB.find_field(maskKey.getTable(), s.substring((maskTag+"@").length()));
						if (f!=null) {
							ret._addIfNotIN(f);
						}
					}
				}

			}
			if (ret.size()==0) {
				return new IF_Field[0];
			} else {
				return ret.getArray();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public IF_Field[]  getFieldsToClear(EnTransportTyp typ, RB_KM maskKey) {
		VEK<IF_Field> ret;
		try {
			ret = new VEK<>();
			
			JsonMaskTransportArtSetting ts = this.valuesToSet.get(typ.dbVal());
			String maskTag = this.maskKeys.get(maskKey);
			
			if (ts!=null && S.isFull(maskTag)) {
				VEK<String> clearfields = new VEK<String>()._a(ts.getClearlist());
				VEK<String> clearDisablefields = new VEK<String>()._a(ts.getCleardisablelist());
				VEK<String> clearDisablelabefields = new VEK<String>()._a(ts.getCleardisablelabellist());
				VEK<String> allToClear = new VEK<String>()._addIfNotIN(clearfields.getArray())._addIfNotIN(clearDisablefields.getArray())._addIfNotIN(clearDisablelabefields.getArray());

				for (String s: allToClear) {
					if (s.startsWith(maskTag+"@") && maskKey.getTable()!=null) {
						IF_Field f = _TAB.find_field(maskKey.getTable(), s.substring((maskTag+"@").length()));
						if (f!=null) {
							ret._addIfNotIN(f);
						}
					}
				}

			}
			if (ret.size()==0) {
				return new IF_Field[0];
			} else {
				return ret.getArray();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public HMAP<IF_Field,String>  getValuesToSet(EnTransportTyp typ, RB_KM maskKey) {
		 HMAP<IF_Field,String> ret;
		try {
			ret = new HMAP<IF_Field,String>();
			
			JsonMaskTransportArtSetting ts = this.valuesToSet.get(typ.dbVal());
			String maskTag = this.maskKeys.get(maskKey);
			
			PdServiceReplaceSysVariablesInString replaceSysVars = new PdServiceReplaceSysVariablesInString();
			
			if (ts!=null && S.isFull(maskTag)) {
				Map<String,String> fieldsAndValues = ts.getValuesmap();
				if (fieldsAndValues != null) {
					for (String s: fieldsAndValues.keySet()) {
						if (s.startsWith(maskTag+"@") && maskKey.getTable()!=null) {
							IF_Field f = _TAB.find_field(maskKey.getTable(), s.substring((maskTag+"@").length()));
							if (f!=null) {
								ret._put(f,replaceSysVars.replace(fieldsAndValues.get(s)));
							}
						}
					}
				}
			}
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	//eine liste von textkeys, die farblich abgesenkt werden
	public VEK<TK>  getMaskLablesToDisable(EnTransportTyp typ) {
		VEK<TK> ret;
		try {
			ret = new VEK<>();
			
			JsonMaskTransportArtSetting ts = this.valuesToSet.get(typ.dbVal());
			
			if (ts!=null) {
				Vector<String>  disableLabel = ts.getDisablelabellist();
				Vector<String>  cleardisableLabel = ts.getCleardisablelabellist();
				
				VEK<String> labelsToDisable = new VEK<String>()._a(disableLabel);
				labelsToDisable._addIfNotIN( new VEK<String>()._a(cleardisableLabel).getArray());
				
				for (String s: labelsToDisable) {
					if (s.contains("@")) {
						String tableKey = s.substring(0,s.indexOf("@"));
						String fieldKey = s.substring(s.indexOf("@")+1);
						
						EnTabKeyInMask enTab = EnTabKeyInMask.A1.getEnum(tableKey);
						if (enTab!=null) {
							_TAB tab = enTab.getTable();
							IF_Field field = _TAB.find_field(tab, fieldKey);
							if (field!=null) {
								TK labelKey = TK.gen(enTab, field);
								ret._a(labelKey);
							}
						}
					}
				}

			}
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	
}
