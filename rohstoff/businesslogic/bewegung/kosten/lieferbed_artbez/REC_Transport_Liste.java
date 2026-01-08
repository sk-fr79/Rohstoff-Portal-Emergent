/**
 * rohstoff.businesslogic.bewegung.kosten.lieferbed_artbez
 * @author manfred
 * @date 24.04.2018
 * 
 */
package rohstoff.businesslogic.bewegung.kosten.lieferbed_artbez;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Vector;

import panter.gmbh.basics4project.DB_RECORDS.RECLIST_KOSTEN_LIEFERBED_DEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_KOSTEN_LIEFERBED_DEF;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.AH7._VEK_allIdAdressFields;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.valid_KopiereNurBelegeMitAktiveAdressen;



/**
 * Liste von Datensätzen, die einen Transport definieren, d.h. von einem BG-Vektor. Der kann aber mehrere einzeltransporte haben, vgl. historisch Fuhren-Orte
 * 
 * Das kann ein WE, WA, LL -Atom sein, oder ein Tupel für zwei Strecken-Atome, die links und rechts eigene "Vektoren" haben. 
 * 
 * @author manfred
 * @date 24.04.2018
 *
 */
public class REC_Transport_Liste {
	
	
	// Liste aller Atome die zu einem Vektor in gehören. Das können auch halbseitige Strecken sein (Fuhrenorte)
	Vector<REC_BG_AtomInfo> _list_BGAtomInfoWE 	= new Vector<>();
	Vector<REC_BG_AtomInfo> _list_BGAtomInfoWA 	= new Vector<>();
	

	// Liste der erzeugten Vektor-Infos
	private Vector<REC_Transport_Info> _list_Transport_Info = new Vector<>();
	
		
	// der Vektor um den es geht
	String _idBG_Vektor 						= null;


	private RECLIST_KOSTEN_LIEFERBED_DEF _oReclistLieferbedDef;

	private Vector<String> 	_vEigeneLagerorte;
	private Vector<String> 	_vIDLagerToIgnore;
	String 				 	_IDLagerStrecke;

	
	
	
	/**
	 * @author manfred
	 * @date 24.04.2018
	 *
	 */
	public REC_Transport_Liste(String idVektor,Vector<String> vIDLagerToIgnore,  Vector<String> vEigeneLagerorte,  RECLIST_KOSTEN_LIEFERBED_DEF listLieferbed){
		_vIDLagerToIgnore 		= vIDLagerToIgnore;
		_oReclistLieferbedDef   = listLieferbed;
		_vEigeneLagerorte 		= vEigeneLagerorte;

		_idBG_Vektor = idVektor;
		
		_IDLagerStrecke = bibSES.get_ID_ADRESSE_LAGER_STRECKE();
		
		// lese alle Atome des Vektors
		ReadBGAtomInfos(_idBG_Vektor);
		
		// Baue die Vektor-Infos auf
		GenerateTransportInfos();
		
	}
	
	
	
	
	/**
	 * die einzelnen zeilen in die Records überführen 
	 * @param IdBewegungVektor
	 */
	private void ReadBGAtomInfos(String IdBGVektor){
		
		//  
		SqlStringExtended query = new SqlStringExtended(REC_BG_AtomInfo.sSelectBGATOMInfo + " AND V.ID_BG_VEKTOR = ? ");
		query.getValuesList().add(new Param_Long(Integer.parseInt(IdBGVektor)) );
		
		String [][] cResult = new String[0][0];
		cResult =  bibDB.EinzelAbfrageInArray(query);
		
		// wenn nichts gefunden wird, einen leeren Vektor zurückgeben
		if (cResult == null || cResult.length == 0){
			return;
		}

		// Atominfos generieren und an die Liste hängen
		for (int iCount = 0; iCount < cResult.length; iCount++) {
			int i = 0;
			
			String[] sRow = cResult[iCount];
			
			REC_BG_AtomInfo o = new REC_BG_AtomInfo(sRow);
			
			if (_vEigeneLagerorte.contains(o.get_IDAdresse_Ablade())){
				_list_BGAtomInfoWE.add(o);
			} else if (_vEigeneLagerorte.contains(o.get_IDAdresse_Lade())){
				_list_BGAtomInfoWA.add(o);
				
			// Strecke ...
			} else if (_IDLagerStrecke.equals(o.get_IDAdresse_Lade())){
				_list_BGAtomInfoWA.add(o);
			} else if (_IDLagerStrecke.equals(o.get_IDAdresse_Ablade())){
				_list_BGAtomInfoWE.add(o);
			}
		}
	}

	

	/**
	 * geht durch alle Atominfos durch und setzt ggf. Vektor-Infos zusammen
	 * @author manfred
	 * @date 24.04.2018
	 *
	 */
	private void GenerateTransportInfos(){

		// alle Wareneingangs-Atominfos durchlaufen
		for (REC_BG_AtomInfo a: _list_BGAtomInfoWE){
			REC_Transport_Info transportInfo = new REC_Transport_Info(_vIDLagerToIgnore,_vEigeneLagerorte,_oReclistLieferbedDef);
			transportInfo.set_AtomInfo1(a);
			
			// falls es in ein Streckenlager geht, schauen, ob man das WA-Atom findet
			if (a.get_IDAdresse_Ablade().equals(_IDLagerStrecke)){
				for(REC_BG_AtomInfo s: _list_BGAtomInfoWA){
					if (a.get_ID_BG_Station_ablade().equals(s.get_ID_BG_Station_lade())){
						// zweites Atom der Strecke gefunden!
						transportInfo.set_AtomInfo2(s);
						// Streckenende aus der Liste löschen
						_list_BGAtomInfoWA.remove(s);
						break;
					}
				}
			}
			// das transportinfo-Element komplettieren und zur Liste hinzufügen
			transportInfo.Initialize();
			_list_Transport_Info.add(transportInfo);
		}
		
		// alle Warenausgangs-Atominfos durchlaufen
		for (REC_BG_AtomInfo a: _list_BGAtomInfoWA){
			REC_Transport_Info transportInfo = new REC_Transport_Info(_vIDLagerToIgnore,_vEigeneLagerorte,_oReclistLieferbedDef);
			transportInfo.set_AtomInfo1(a);
			
//			// falls es in ein Streckenlager geht, schauen, ob man das WA-Atom findet
//			if (a.get_IDAdresse_Ablade().equals(_IDLagerStrecke)){
//				for(REC_BG_AtomInfo s: _list_BGAtomInfoWA){
//					if (a.get_ID_BG_Station_ablade().equals(s.get_ID_BG_Station_lade())){
//						// zweites Atom der Strecke gefunden!
//						transportInfo.set_AtomInfo2(s);
//						_list_BGAtomInfoWA.remove(s);
//						break;
//					}
//				}
//			}
			transportInfo.Initialize();
			_list_Transport_Info.add(transportInfo);
		}
	}
	
	

	/**
	 * 
	 * @author manfred
	 * @date 26.04.2018
	 *
	 * @return
	 */
	public Vector<REC_Transport_Info> get_list_Transport_Info() {
		return _list_Transport_Info;
	}


		
	
	
}
