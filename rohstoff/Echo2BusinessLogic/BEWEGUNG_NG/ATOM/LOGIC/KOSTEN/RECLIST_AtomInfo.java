package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.KOSTEN;

import java.math.BigDecimal;
import java.util.Vector;

import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;

public class RECLIST_AtomInfo {
	Vector<REC_AtomInfo> 	_list_AtomInfo = new Vector<REC_AtomInfo>();
	
	public RECLIST_AtomInfo() {
	}

	public RECLIST_AtomInfo(String IDBewegung){
		String sWhere = "WHERE a.ID_BEWEGUNG = " + IDBewegung;
		
		String sSql = REC_AtomInfo.getSelectAtominfo() + sWhere ;
		
		String [][] cResult = new String[0][0];
		cResult =  bibDB.EinzelAbfrageInArray(sSql,(String)null);
		
		// wenn nichts gefunden wird, einen leeren Vektor zurückgeben
		if (cResult == null || cResult.length == 0){
			return;
		}
		
/*		+ "			a.ID_BEWEGUNG_VEKTOR, "
		+ "			a.ID_BEWEGUNG_ATOM, "
		+ "			a.ID_BEWEGUNG, "
		+ "			s1.ID_ADRESSE, "
		+ "			s1.ID_ADRESSE_BESITZER, "
		+ " 		s2.ID_ADRESSE, "
		+ "			s2.ID_ADRESSE_BESITZER, "
		+ " 		a.ID_ARTIKEL, "
		+ "			a.ID_ARTIKEL_BEZ, "
		+ "			a.MENGE, "
		+ "			a.MENGE / art.MENGENDIVISOR,"
		+ "			a.MENGE_NETTO,"
		+ "			a.ID_LIEFERBEDINGUNGEN, "
		+ " 		art.ID_EINHEIT, "
		+ "			art.ID_EINHEIT_PREIS, "
		+ "			art.MENGENDIVISOR "
*/
	
		for (int iCount = 0; iCount < cResult.length; iCount++) {
			int i = 0;
			REC_AtomInfo o = new REC_AtomInfo();
			
			String _IDBewegungVektor			= cResult[iCount][i++];
			o.set_IDBewegungVektor(_IDBewegungVektor);
			
			String _IDBewegungAtom 				= cResult[iCount][i++];
			o.set_IDBewegungAtom(_IDBewegungAtom);
			
			
			String _IDBewegung 					= cResult[iCount][i++];
			o.set_IDBewegung(_IDBewegung);
			
			String _IDAdresse_Lade 				= cResult[iCount][i++];
			o.set_IDAdresse_Lade(_IDAdresse_Lade);
			
			String _IDBesitzer_Lade 			= cResult[iCount][i++];
			o.set_IDBesitzer_Lade(_IDBesitzer_Lade);
			
			String _IDAdresse_Ablade 			= cResult[iCount][i++];
			o.set_IDAdresse_Ablade(_IDAdresse_Ablade);
			
			String _IDBesitzer_Ablade 			= cResult[iCount][i++];
			o.set_IDBesitzer_Ablade(_IDBesitzer_Ablade);
			
			String _IDArtikel 					= cResult[iCount][i++];
			o.set_IDArtikel(_IDArtikel);
			
			String _IDArtikelBez 				= cResult[iCount][i++];
			o.set_IDArtikelBez(_IDArtikelBez);
			
			BigDecimal _MengeBrutto 			= bibALL.convertDBTextToBigDecimal(cResult[iCount][i++]);
			o.set_MengeBrutto(_MengeBrutto );
			
			BigDecimal _MengeAbrechnungseinheit = bibALL.convertDBTextToBigDecimal(cResult[iCount][i++]);
			o.set_MengeAbrechnungseinheit(_MengeAbrechnungseinheit != null ? _MengeAbrechnungseinheit.setScale(3, BigDecimal.ROUND_HALF_UP) : null );
			
			BigDecimal _MengeNetto  			= bibALL.convertDBTextToBigDecimal(cResult[iCount][i++]);
			o.set_MengeNetto(_MengeNetto );
			
			String _IDLieferbedingungen 		= cResult[iCount][i++];
			o.set_IDLieferbedingungen(_IDLieferbedingungen);
			
			String _IDEinheit_Menge 			= cResult[iCount][i++];
			o.set_IDEinheit_Menge(_IDEinheit_Menge);
			
			String _IDEinheit_Preis 			= cResult[iCount][i++];
			o.set_IDEinheit_Preis(_IDEinheit_Preis);
			
			BigDecimal _MengenDivisor   		= bibALL.convertDBTextToBigDecimal(cResult[iCount][i++]);
			o.set_MengenDivisor(_MengenDivisor);
			

			
			// record zur Liste hinzufügen
			_list_AtomInfo.add(o);
		}
	}
	
	
	
	public void Add(REC_AtomInfo oAtomInfo){
		_list_AtomInfo.add(oAtomInfo);
	}
	
	public Vector<REC_AtomInfo> getList(){
		return _list_AtomInfo;
	}
	
	
	
}
