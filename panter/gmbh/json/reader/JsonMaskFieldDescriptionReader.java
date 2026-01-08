/**
 * panter.gmbh.json.reader
 * @author martin
 * @date 15.06.2020
 * 
 */
package panter.gmbh.json.reader;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;

import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.COMP.RB_labInGridNoDatabase;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.myVectors.HMAP;
import panter.gmbh.json.EnumJsonFileNames;
import panter.gmbh.json.defClass.JsonMaskFieldDescription;
import rohstoff.businesslogic.bewegung2.global.EnTabKeyInMask;
import rohstoff.businesslogic.bewegung2.global.EnumMaskSonderLabel;
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
 */
public class JsonMaskFieldDescriptionReader {



	private File jsonFile = null;
	private HMAP<RB_KM,HMAP<TK,RB_labInGridNoDatabase>> 	mapWithLabels = new HMAP<RB_KM,  HMAP<TK,RB_labInGridNoDatabase>>();
	
	private HMAP<String,RB_KM> 								maskKeys = 		new HMAP<String,RB_KM>()
																			._put("V", RecV.key)
																			._put("A1",RecA1.key)
																			._put("A2",RecA2.key)
																			._put("S1",RecS1.key)
																			._put("S2",RecS2.key)
																			._put("S3",RecS3.key)
																			;
	
	
	/**
	 * @author martin
	 * @date 28.05.2020
	 *
	 */
	public JsonMaskFieldDescriptionReader() throws JsonProcessingException, IOException, Exception  {

		this.jsonFile=EnumJsonFileNames.B2MaskFieldDescription.getFile();
		
		ObjectMapper mapper = new ObjectMapper(new JsonFactory().enable(Feature.ALLOW_COMMENTS));
		
		JsonMaskFieldDescription[] settings = mapper.readValue(jsonFile, JsonMaskFieldDescription[].class);
		
		for (JsonMaskFieldDescription def: settings) {
			if (S.isFull(def.getTable()) && S.isFull(def.getField()) && maskKeys.keySet().contains(def.getTable())) {

				RB_KM maskKey =	maskKeys.get(def.getTable());

				if (maskKey!=null && maskKey.getTable()!=null) {
					
					EnTabKeyInMask referingEnum = EnTabKeyInMask.A1.getEnum(def.getTable());
					if (referingEnum != null) {
						_TAB 		table = referingEnum.getTable();
						IF_Field 	field = _TAB.find_field(table, def.getField());
						
						if (field != null) {
							TK textKey = TK.gen(referingEnum, field);
							
							HMAP<TK,RB_labInGridNoDatabase> tableMap = 	mapWithLabels.get(maskKey);
							if (tableMap==null) {
								mapWithLabels._put(maskKey,new HMAP<TK,RB_labInGridNoDatabase>());
								tableMap =mapWithLabels.get(maskKey);
							}
							
							//jetzt die tableMap fuellen
							//dazu nachsehen, ob der RB_KM eine _TAB enthaelt oder identifiziert
							tableMap._put(textKey, def.getLabel());
						} else {
							//dann koennte es ein sonderlabel sein
							String labelname = def.getField();
							EnumMaskSonderLabel enumMaskSonderLabel = EnumMaskSonderLabel.titel_0_0.getEnum(labelname);
							
							if (enumMaskSonderLabel!=null) {
								TK textKey = TK.gen(enumMaskSonderLabel);
								HMAP<TK,RB_labInGridNoDatabase> tableMap = 	mapWithLabels.get(maskKey);
								if (tableMap==null) {
									mapWithLabels._put(maskKey,new HMAP<TK,RB_labInGridNoDatabase>());
									tableMap =mapWithLabels.get(maskKey);
								}
								tableMap._put(textKey, def.getLabel());
							}  else {
								bibMSG.MV()._addWarn("fieldkey: "+def.getField()+" not found in maskTables !");
							}
						}
					}
				}
			}
		}
	}


	public HMAP<RB_KM, HMAP<TK, RB_labInGridNoDatabase>> getMapWithLabels() {
		return mapWithLabels;
	}

}
