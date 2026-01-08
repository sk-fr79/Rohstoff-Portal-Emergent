package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.BUCHUNG;

import java.math.BigDecimal;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.PRUEF_RECORD_VPOS_TPA_FUHRE;

public class VECTOR_BUCH_StatementBuilder extends Vector<BUCH_StatementBuilder>
{

	public VECTOR_BUCH_StatementBuilder()
	{
		super();
	}


	/**
	 * Prueft, ob die fuhre aus der liste verbucht werden darf. 
	 * Dies ist nur bei komplett ungebuchten fuhren moeglich, die mindestens 1 moegliche verbuchung zulassen
	 * @return
	 * @throws myException
	 */
	public boolean get_bIstBereitZumBuchungsVorgang() throws myException
	{
		/*
		 * um den status kann gebucht werden zu erreichen, werden alle BUCH_StatementBuilder durchsucht und geprueft, ob mindestens 1 verbucht werden kann 
		 * der nicht nicht verbucht wurde
		 */
		int iKannGebuchtWerden = 	0;
		int iVerbucht = 			0;
		
		for (int i=0;i<this.size();i++)
		{
			if ((!this.get(i).get_bBuchung_Menge_IST_Zahl_0()) && (!this.get(i).get_bIstEigenesLager()))
			{
				if (this.get(i).get_bIstBereitsGebucht())
				{
					iVerbucht++;
				}
				else
				{
					if (this.get(i).get_bBuchungsangabenSindKomplett())
					{
						iKannGebuchtWerden++;
					}
				}
			}
		}

		
		if (iVerbucht==0 && iKannGebuchtWerden>0 && this.get_bALLE_0_Mengen_und_LagerPositionen_Sind_komplett())
		{
			return true;
		}
		else
		{
			return false;
		}
	}


	
	
	/**
	 * Prueft, ob die fuhre aus der liste verbucht werden darf. 
	 * Dies ist nur bei komplett ungebuchten fuhren moeglich, die mindestens 1 moegliche verbuchung zulassen
	 * @return
	 * @throws myException
	 */
	public boolean get_bIstKomplettBuchbar() throws myException
	{
		/*
		 * um den status kann gebucht werden zu erreichen, werden alle BUCH_StatementBuilder durchsucht und geprueft, ob mindestens 1 verbucht werden kann 
		 * der nicht nicht verbucht wurde
		 */
		int iKannGebuchtWerden = 	0;
		int iVerbucht = 			0;
		
		int iBuchungsRelevant = 0;
		
		for (int i=0;i<this.size();i++)
		{
			if ((!this.get(i).get_bBuchung_Menge_IST_Zahl_0()) && (!this.get(i).get_bIstEigenesLager()))
			{
				iBuchungsRelevant++;
				
				if (this.get(i).get_bIstBereitsGebucht())
				{
					iVerbucht++;
				}
				else
				{
					if (this.get(i).get_bBuchungsangabenSindKomplett())
					{
						iKannGebuchtWerden++;
					}
				}
			}
		}

		
		if (iVerbucht==0 && iKannGebuchtWerden==iBuchungsRelevant && this.get_bALLE_0_Mengen_und_LagerPositionen_Sind_komplett())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	
	
	/**
	 * 	public static int     	STATUS_FUHRE__IST_STORNIERT = 					-2;
	 *	public static int     	STATUS_FUHRE__IST_ALT_WID_NICHT_BEGUCHT = 		-1;
	 *	public static int     	STATUS_FUHRE__EINGABE_IST_NOCH_NICHT_FERTIG = 	1;
	 *	public static int     	STATUS_FUHRE__BESITZT_KEINE_BUCHUNGSPOS = 		2;
	 * 	public static int     	STATUS_FUHRE__UNGEBUCHT= 						3;
	 *	public static int     	STATUS_FUHRE__TEILSGEBUCHT= 					4;
	 *	public static int     	STATUS_FUHRE__GANZGEBUCHT = 					5;

	 * @return  den Status einer Fuhre, die als grundlage diesen Vector geliefert hat
	 * @throws myException
	 */
	public int get_FUHREN_STATUS(PRUEF_RECORD_VPOS_TPA_FUHRE recFuhre) throws myException
	{
		

		
		// vorgeschaltete feste definitionen
		
		if (recFuhre.is_ALT_WIRD_NICHT_GEBUCHT_YES())
		{
			return myCONST.STATUS_FUHRE__IST_ALT_WID_NICHT_BEGUCHT;         //ist fuer den buchungsvorgang wertlos
		}

		if (recFuhre.is_IST_STORNIERT_YES())
		{
			return myCONST.STATUS_FUHRE__IST_STORNIERT;         //ist fuer den buchungsvorgang wertlos
		}

		
		if (recFuhre.is_DELETED_YES())
		{
			return myCONST.STATUS_FUHRE__BESITZT_KEINE_BUCHUNGSPOS;         //ist fuer den buchungsvorgang wertlos
		}


		
		
		//fall 1: fuhre ist fremdauftragsfuhre oder alle fuhre und zugeh. orte sind ohne ohne_abrechnung='Y'
		if (this.size()==0)
		{
			return myCONST.STATUS_FUHRE__BESITZT_KEINE_BUCHUNGSPOS;
		}
		
		//falls 2: fuhre besteht nur aus orten mit der menge 0 (nicht leer)
		int iZahl_Mit_Menge_0_oder_eigenes_lager							=	0;
		int iZahl_Mit_Menge_0_oder_eigenes_lager_ausgefuellt_und_geprueft	=	0;
		int iZahl_komplett_ausgefuellt_und_geprueft 						= 	0;
		
		
		for (int i=0;i<this.size();i++)
		{
			if (this.get(i).get_bBuchung_Menge_IST_Zahl_0() || this.get(i).get_bIstEigenesLager())
			{
				iZahl_Mit_Menge_0_oder_eigenes_lager++;
				if (this.get(i).get_bBuchungsangabenSindKomplett())
				{
					iZahl_Mit_Menge_0_oder_eigenes_lager_ausgefuellt_und_geprueft++;
				}
			}
			
			if (this.get(i).get_bBuchungsangabenSindKomplett())
			{
				iZahl_komplett_ausgefuellt_und_geprueft++;
			}
			
		}
		
		//lagerfuhren und 0-Fuhren werden nur nach pruefung als STATUS_FUHRE__BESITZT_KEINE_BUCHUNGSPOS, sonst noch nicht fertig
		if (this.size()==iZahl_Mit_Menge_0_oder_eigenes_lager)
		{
			if (this.size()==iZahl_komplett_ausgefuellt_und_geprueft)
			{
				return myCONST.STATUS_FUHRE__BESITZT_KEINE_BUCHUNGSPOS;
			}
			else
			{
				return myCONST.STATUS_FUHRE__EINGABE_IST_NOCH_NICHT_FERTIG;   //alles muss geprueft werden, auch 0-mengen und lager
			}
		}
		

		
		/*
		 * um den status <kann gebucht werden> zu erreichen, werden alle BUCH_StatementBuilder durchsucht und geprueft, ob mindestens 1 verbucht werden kann 
		 * der noch nicht verbucht wurde
		 */
		int iAnzahlUngebuchtUndVollstaendig = 	0;
		int iVerbucht = 			0;
		int iAnzahlVerbuchbare = 	0;
		
		for (int i=0;i<this.size();i++)
		{
			if ((!this.get(i).get_bBuchung_Menge_IST_Zahl_0()) && (!this.get(i).get_bIstEigenesLager()))
			{
				iAnzahlVerbuchbare++;
				
				if (this.get(i).get_bIstBereitsGebucht())
				{
					iVerbucht++;
				}
				else
				{
					if (this.get(i).get_bBuchungsangabenSindKomplett())
					{
						iAnzahlUngebuchtUndVollstaendig++;
					}
				}
			}
		}

		
		//DEBUG.System_println("Anzahl Buchungssaetze:"+this.size()+"  ->  Bereits gebucht:"+iVerbucht+"   --> Bereit zur Buchung: "+iAnzahlUngebuchtUndVollstaendig  , null);
		
		
		//jetzt steht der status fest:
		if (iVerbucht>0)
		{
			if (iVerbucht==iAnzahlVerbuchbare)
			{
				return myCONST.STATUS_FUHRE__GANZGEBUCHT;
			}
			else
			{
				return myCONST.STATUS_FUHRE__TEILSGEBUCHT;              //dann ist die fuhre trotz ungebuchter positionen geschlossen
			}
		}
		else
		{
			if (iAnzahlUngebuchtUndVollstaendig>0)
			{
				//wenn eigene lager (oder 0-Menge) im spiel sind und die noch nicht abgeschlossen sind, dann ist die eingabe noch nicht fertig
				if (iZahl_Mit_Menge_0_oder_eigenes_lager>0 && iZahl_Mit_Menge_0_oder_eigenes_lager!=iZahl_Mit_Menge_0_oder_eigenes_lager_ausgefuellt_und_geprueft)
				{
					return myCONST.STATUS_FUHRE__EINGABE_IST_NOCH_NICHT_FERTIG;
				}
				else
				{
					return myCONST.STATUS_FUHRE__UNGEBUCHT;
				}
			}
			else 
			{
				return myCONST.STATUS_FUHRE__EINGABE_IST_NOCH_NICHT_FERTIG;
			}
		}

	}
	
	
	
	/*
	 * hilfsmethode, die prueft, ob eine fuhre komplett verbucht werden kann
	 */
	public boolean get_bALLE_0_Mengen_und_LagerPositionen_Sind_komplett() throws myException
	{
		int iZahl_Mit_Menge_0_oder_eigenes_lager							=	0;
		int iZahl_Mit_Menge_0_oder_eigenes_lager_ausgefuellt_und_geprueft	=	0;
		
		for (int i=0;i<this.size();i++)
		{
			if (this.get(i).get_bBuchung_Menge_IST_Zahl_0() || this.get(i).get_bIstEigenesLager())
			{
				iZahl_Mit_Menge_0_oder_eigenes_lager++;
				if (this.get(i).get_bBuchungsangabenSindKomplett())
				{
					iZahl_Mit_Menge_0_oder_eigenes_lager_ausgefuellt_und_geprueft++;
				}
			}
		}
		
		return (iZahl_Mit_Menge_0_oder_eigenes_lager==iZahl_Mit_Menge_0_oder_eigenes_lager_ausgefuellt_und_geprueft);

	}
	
	
	
	/**
	 * 
	 * @param vBuchungenOK uebernimmt die Buchungen aus den Vectoren, die moeglich sind
	 * @return
	 * @throws myException 
	 * 
	 */
	public MyE2_MessageVector  get_PruefeBuchungsStatements_Sammle_Gute(VECTOR_BUCH_StatementBuilder  vBuchungenOK, MyE2_MessageVector oMV_Fehler) throws myException
	{
		MyE2_MessageVector  oMV_WarnmeldungenFuerBuchung = new MyE2_MessageVector();
		
		//jetzt pruefen, ob alle gebucht werden koennen (mit mindestens 1 Buchungsposition)
		// und nachschauen, ob in den einzelen positionen mengendifferenzen vorkommen
		for (BUCH_StatementBuilder oStmt: this)
		{
			if (oStmt.get_bBuchung_Menge_IST_Zahl_0() || oStmt.get_bIstEigenesLager())   //eigene lager und 0-mengen sind raus
			{
				continue;
			}
			
			MyE2_MessageVector  oMV1 = new MyE2_MessageVector();
			
			if (oStmt.get_bBuchungsangabenSindKomplett() && !oStmt.get_bIstBereitsGebucht())
			{
				//sonderfall, wenn die adresse keine zahlungsbedingungen hat
				oMV1.add_MESSAGE(oStmt.get_CheckAdresseZahlungsbedingung(oStmt.get_bEK()));   //2011-03-08: zahlungsbedingung fuer ek- und vk getrennt beachten
				if (oMV1.get_bHasAlarms())
				{
					oMV_Fehler.add_MESSAGE(oMV1);
					continue;
				}

				
				//dann die mengendifferenz zwischen wiege- und abrechnungsmenge anzeigen (wenn gegenlauefig gebucht wird)
				BigDecimal bdDiff = oStmt.get_DifferenzZwischenBuchungsmengeUndWiegemenge();
				if (bdDiff.compareTo(BigDecimal.ZERO)!=0)
				{
					MyE2_String cHelp = new MyE2_String("");
					if (oStmt.get_bEK())
					{
						cHelp.addTranslated("Gutschrift an:");
					}
					else
					{
						cHelp.addTranslated("Rechnung an:");
					}
					cHelp.addUnTranslated(oStmt.get_INFO_BLOCK_Fuer_Adresse_Sorte_id());
					cHelp.addTranslated(":  Differenz zwischen Wiegung und Abrechnung: ");
					cHelp.addUnTranslated(MyNumberFormater.formatDez(bdDiff, 3, true));
					
					oMV_WarnmeldungenFuerBuchung.add_MESSAGE(new MyE2_Warning_Message(cHelp));
				}
				
				//wenn er bis hierher kommt, wird er uebernommen
				vBuchungenOK.add(oStmt);
			}
			else
			{
				//die nicht buchbaren werden einfach uebergangen
			}
	
		}

		return oMV_WarnmeldungenFuerBuchung;
	}
	
	
	
	
	
}
