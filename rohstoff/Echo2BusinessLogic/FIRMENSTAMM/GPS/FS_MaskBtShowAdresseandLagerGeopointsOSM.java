/**
 * 
 */
package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.GPS;

import java.math.BigDecimal;

import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.GEOCODIERUNG.ENUM_GEO_MARKER_COLOR;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO_BtShowGeoPointsOnOSMLocal;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO_Coordinate4Map;
import panter.gmbh.basics4project.GEOCODIERUNG.GEO_Factory_URLForLocations;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;

/**
 * @author martin
 *
 */
public class FS_MaskBtShowAdresseandLagerGeopointsOSM extends GEO_BtShowGeoPointsOnOSMLocal {

	
	/**
	 * @throws myException
	 */
	public FS_MaskBtShowAdresseandLagerGeopointsOSM() throws myException {
		super();
		this._ttt(S.ms("Karte zu den GPS-Koordinaten der Adressen aufrufen ..."));
	}


	/* (non-Javadoc)
	 * @see panter.gmbh.basics4project.GEOCODIERUNG.GEO_BtShowGeoPointOnOSMLocal#getURLFactory()
	 */
	@Override
	public GEO_Factory_URLForLocations getURLFactory() throws myException {
		
		MyLong _id = new MyLong(this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
		
		if (_id.isOK()) {
			GEO_Factory_URLForLocations factory = new GEO_Factory_URLForLocations();

			Rec21_adresse r;
			try {
				
				r = new Rec21_adresse()._fill_id(_id.get_cUF_LongString());
				if (r != null){

					
					RecList21 rl  = r.getMainStoreAdresses();
					
					String text = "";
					
					for (Rec21 adr : rl){
						BigDecimal lat = adr.get_raw_resultValue_bigDecimal(ADRESSE.latitude);
						BigDecimal lng = adr.get_raw_resultValue_bigDecimal(ADRESSE.longitude);
						
						if(rl.size()>100){
							text = "";
						} else if (rl.size()>50){
							text = adr.get_fs_dbVal(ADRESSE.id_adresse) ;
						} else if (rl.size()>30){
							
							text =  adr.get_ufs_dbVal(ADRESSE.name1) + " " + adr.get_fs_dbVal(ADRESSE.name2,"");
							if (text.length() > 30){
								text = text.substring(0, 30) + "...";
							}							
							text = text + " (" +  adr.get_fs_dbVal(ADRESSE.id_adresse) + ")";
						} else {
							text =  adr.get_ufs_dbVal(ADRESSE.name1) + " " + adr.get_fs_dbVal(ADRESSE.name2,"");
							if (text.length() > 50){
								text = text.substring(0, 50) + "...";
							}							
							text = text + " (" +  adr.get_fs_dbVal(ADRESSE.id_adresse) + ")";
						}
						
						
						String id = adr.get_ufs_dbVal(ADRESSE.id_adresse);
						
						ENUM_GEO_MARKER_COLOR col = id.equals(_id.get_cUF_LongString()) 
								? ENUM_GEO_MARKER_COLOR.GREEN 
								: ENUM_GEO_MARKER_COLOR.YELLOW;
						
						if (lat == null || lng == null) continue;
						
						try{
							GEO_Coordinate4Map c = new GEO_Coordinate4Map(lat, lng, text, col);
							factory.addGeoCoordiante(c);
						} catch(myException e){
							// fehler beim der Koordinatenumwandlung
						}
					}
				}

			} catch (myException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return factory;
		}
		
		return null;
		
	}

}
