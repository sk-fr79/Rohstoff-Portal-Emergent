package rohstoff.utils.SQL_DAEMONS;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.E2_InfoButton;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;

/*
 * wird implementiert, um im mengenpruef-daemon die vielfaeltige verkettung
 * diverser pruefgesichtspunkte zu beruecksichtigen.
 * In den Check-Methoden wird einerseits alles direkt geprueft, was primaer zum objekt gehoert,
 * alle verketteten bestandteile werden in der methode __Check(Vector<PRUEF_Interface> vSammler))
 * gesammelt, nicht sofort ausgefuehrt.
 * Am Anfang der __Check()-Methode wird geprueft, ob sich das objekt (via equals) bereits in der liste
 * befindet. Wenn ja, erfolgt abbruch, um eine rekursion zu vermeiden
 * 
 * Zum Schluss werden alle, die noch nicht erledigt sind, ausgefuehrt und geprueft
 */
public interface IF_PRUEF_Interface
{
	public boolean IsDone();
	public void  setDone(boolean bDone);
	public boolean equals(Object oCompare);
	public MyE2_MessageVector __Check(VectorSingle_PRUEF_Interface vSammler,  VectorSingle vAusgangsTabs) throws myException; 

	// feld, um festzustellen, ob ein pruefobject primaer (d.h. direkt aus dem SQL-Daemon kommt, oder ob der sekundaer aus einem anderen
	// pruef-interface erzeugt wurde. bei sekundaeren werden u.U. keine warnmeldungen ausgegeben, da die warnungen ueber verknuepfte datensaetze beim 
	// speichern einer maske (z.B.) verwirrend sind
	
	public boolean IsPrimary();
	public void  setPrimary(boolean bPrimary);
	
	
	//fuer die meldungen muss es aussagekraeftige infos geben. dazu wird in den messages ein infobutton speziell fuer diese pruefklasse eingefuehrt
	public E2_InfoButton  get_ownInfoButton(MyString cFehler);
	
	
	public String get_ID() throws myException;
	
	// enthaelt den namen der tabelle, die geaendert wurde. damit kann in der __check()-methode geprueft werden, ob die pruefung fuer die erste
	//aufruf-ebene stattfindet oder ein kaskadierender aufruf ist 
	public String get_TABLENAME_CHANGED() throws myException;
	
	
	
}
