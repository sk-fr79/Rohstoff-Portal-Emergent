package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN._VALID_DAEMON;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_BASIC_AlarmMessageWithAddonComponent;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.E2_InfoButton;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;

public class FUHREN_PRUEFUNG_SORTEN_ABRECHSTATUS extends XX_FUHREN_PRUEFUNG
{

	public FUHREN_PRUEFUNG_SORTEN_ABRECHSTATUS(	RECLIST_VPOS_TPA_FUHRE rec_ListFuhre)
	{
		super(rec_ListFuhre);
	}

	@Override
	public MyE2_MessageVector mache_Pruefung() throws myException
	{
		MyE2_MessageVector  oMV = new MyE2_MessageVector();
		
		//pruefung 1: Hauptsorte auf jeder seite gleich (Substr(0,2) von ANR1
		VectorSingle vTestHauptSorteEK = new VectorSingle();
		VectorSingle vTestHauptSorteVK = new VectorSingle();
		
		VectorSingle vEinheitenMenge = new VectorSingle();
		VectorSingle vEinheitenZahlung = new VectorSingle();
		
		VectorSingle vOhneAbrechnung = new VectorSingle();
		
		RECLIST_VPOS_TPA_FUHRE recLIST = this.get_recListFuhre();
		
		//sonderfall: wenn sich die VK-Seite komplett in einem LAGERORT des mandanten befindet, dann ist es eine wareneingangsfuhre und
		// kann beliebig aufgeteilt werden, d.h. die fuhre verhaelt sich, wie mit  recFuhre.is_EK_VK_SORTE_LOCK_NO - einstellung
		boolean bIstKomplettWareneingang = true;
		for (int i=0;i<recLIST.get_vKeyValues().size();i++)
		{
			if (!recLIST.get(i).get_ID_ADRESSE_ZIEL_cUF_NN("-").equals(bibALL.get_ID_ADRESS_MANDANT()))
			{
				bIstKomplettWareneingang = false;
			}
		}
		
		  
		for (int i=0;i<recLIST.get_vKeyValues().size();i++)
		{
			RECORD_VPOS_TPA_FUHRE recFuhre = recLIST.get(i);
			if (recFuhre.is_DELETED_NO() && recFuhre.is_EK_VK_SORTE_LOCK_YES() && !bIstKomplettWareneingang)    //wird nur benutzt, wenn die EK- und VK-sorte entsperrt sind
			{
				
				//sorten sammeln
				if (!recFuhre.get_ID_ARTIKEL_BEZ_EK_cUF_NN("").equals(""))	 	
				{
					vTestHauptSorteEK.add(__String2vonLinks(recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek().get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cUF_NN("")));
				}

				//falls kontrakte, auch mit in die Liste
				if (S.isFull(recFuhre.get_ID_VPOS_KON_EK_cUF_NN("")))
				{
					vTestHauptSorteEK.add(__String2vonLinks(recFuhre.get_UP_RECORD_VPOS_KON_id_vpos_kon_ek().get_ANR1_cUF_NN("")));
				}

				//auf die EK-Seite kommen auch die Artikel selbst
				if (S.isFull(recFuhre.get_ID_ARTIKEL_cUF_NN("")))
				{
					vTestHauptSorteEK.add(__String2vonLinks(recFuhre.get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cUF_NN("")));
				}

				
				if (S.isFull(recFuhre.get_ID_ARTIKEL_BEZ_VK_cUF_NN("")))
				{
					vTestHauptSorteVK.add(__String2vonLinks(recFuhre.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_vk().get_UP_RECORD_ARTIKEL_id_artikel().get_ANR1_cUF_NN("")));
				}
				//falls kontrakte, auch mit in die Liste
				if (S.isFull(recFuhre.get_ID_VPOS_KON_VK_cUF_NN("")))
				{
					vTestHauptSorteVK.add(__String2vonLinks(recFuhre.get_UP_RECORD_VPOS_KON_id_vpos_kon_vk().get_ANR1_cUF_NN("")));
				}
				
				//Einheiten sammeln
				vEinheitenMenge.add(recFuhre.get_UP_RECORD_ARTIKEL_id_artikel().get_UP_RECORD_EINHEIT_id_einheit().get_EINHEITKURZ_cUF());
				vEinheitenZahlung.add(recFuhre.get_UP_RECORD_ARTIKEL_id_artikel().get_UP_RECORD_EINHEIT_id_einheit_preis().get_EINHEITKURZ_cUF());
				
				//abrechnungsstatus sammeln
				vOhneAbrechnung.add(recFuhre.is_OHNE_ABRECHNUNG_YES()?"Y":"N");
			}

			if (vTestHauptSorteEK.size()>1)
			{
				Vector<String> vHilfe = new Vector<String>();
				vHilfe.add("Fehlerinfo:");
				vHilfe.add("---------");
				vHilfe.add("Auf der Wareneingangsseite sind im Hauptladeort und in den ");
				vHilfe.add("Zusatzorten Sorten oder Kontrakte mit unterschiedlichen ");
				vHilfe.add("Artikel-Hauptnummern eingetragen (ANR1 Stelle 1-2) !");
				vHilfe.add("Das ist nicht erlaubt");
				
				//oMV.add_MESSAGE(new MyE2_Alarm_Message("Unterschiedliche Sorten auf der Wareneingangsseite !!",new E2_InfoButton(vHilfe,true)));
				oMV.add_MESSAGE(new MyE2_BASIC_AlarmMessageWithAddonComponent(
						new MyE2_String("Unterschiedliche Sorten auf der Wareneingangsseite !!"),new E2_InfoButton(vHilfe,true),
						new Extent(95,Extent.PERCENT),
						new Extent(5,Extent.PERCENT)));
			}
			
			if (vTestHauptSorteVK.size()>1)
			{
				Vector<String> vHilfe = new Vector<String>();
				vHilfe.add("Fehlerinfo:");
				vHilfe.add("---------");
				vHilfe.add("Auf der Warenausgangsseite sind im Hauptabladeort und in den ");
				vHilfe.add("Zusatzorten Sorten oder Kontrakte mit unterschiedlichen ");
				vHilfe.add("Artikel-Hauptnummern eingetragen (ANR1 Stelle 1-2) !");
				vHilfe.add("Das ist nicht erlaubt");
				
				//oMV.add_MESSAGE(new MyE2_Alarm_Message("Unterschiedliche Sorten auf der Warenausgangsseite !!", new E2_InfoButton(vHilfe,true)));
				oMV.add_MESSAGE(new MyE2_BASIC_AlarmMessageWithAddonComponent(
										new MyE2_String("Unterschiedliche Sorten auf der Warenausgangsseite !!"), 
										new E2_InfoButton(vHilfe,true),
										new Extent(95,Extent.PERCENT),
										new Extent(5,Extent.PERCENT)
										));
			}
			
			
			// jetzt den locking-status EK-VK-Sorte pruefen
			if (vTestHauptSorteEK.size()==1 && vTestHauptSorteVK.size()==1)
			{
				if (!vTestHauptSorteEK.get(0).equals(vTestHauptSorteVK.get(0)))
				{
					if (recFuhre.is_EK_VK_SORTE_LOCK_YES())
					{
						Vector<String> vHilfe = new Vector<String>();
						vHilfe.add("Fehlerinfo:");
						vHilfe.add("---------");
						vHilfe.add("Ist der Schalter <EK=VK-Sorte> gesetzt, ");
						vHilfe.add("dann müssen auf beiden Seiten die Sorten (ANR1 Stelle 1-2) ");
						vHilfe.add("in der Fuhre und den Kontrakten gleich sein !");
						
//						oMV.add_MESSAGE(new MyE2_Alarm_Message("Unterschiedliche Sorten auf der Wareneingangs- und Ausgangs-Seite nicht erlaubt !!", new E2_InfoButton(vHilfe,true)));
						oMV.add_MESSAGE(new MyE2_BASIC_AlarmMessageWithAddonComponent(
									new MyE2_String("Unterschiedliche Sorten auf der Wareneingangs- und Ausgangs-Seite nicht erlaubt !!"), 
									new E2_InfoButton(vHilfe,true),
									new Extent(500),
									new Extent(30)));

					}
				}
			}
		}
		
		
		
		
		if (vEinheitenMenge.size()>1)
		{
			Vector<String> vHilfe = new Vector<String>();
			vHilfe.add("Fehlerinfo:");
			vHilfe.add("---------");
			vHilfe.add("Bei der EK-Sorte und der VK-Sorte ");
			vHilfe.add("sind im Sortenstamm unterschiedliche Einheiten eingetragen !");
			vHilfe.add("Ein solche Mischung ist verboten !!");
			
			oMV.add_MESSAGE(new MyE2_BASIC_AlarmMessageWithAddonComponent(new MyE2_String("Unterschiedliche Einheiten auf der EK- und VK-Seite bei den Sorten ist nicht erlaubt !!!"), new E2_InfoButton(vHilfe,true)));
		}
		if (vEinheitenZahlung.size()>1)
		{
			Vector<String> vHilfe = new Vector<String>();
			vHilfe.add("Fehlerinfo:");
			vHilfe.add("---------");
			vHilfe.add("Bei der EK-Sorte und der VK-Sorte ");
			vHilfe.add("sind im Sortenstamm unterschiedliche Einheiten eingetragen !");
			vHilfe.add("Ein solche Mischung ist verboten !!");
			
			oMV.add_MESSAGE(new MyE2_BASIC_AlarmMessageWithAddonComponent(new MyE2_String("Unterschiedliche Preis-Einheiten auf der EK- und VK-Seite bei den Sorten ist nicht erlaubt !!!"), new E2_InfoButton(vHilfe,true)));
		}
		
		if (vOhneAbrechnung.size()>1)
		{
			Vector<String> vHilfe = new Vector<String>();
			vHilfe.add("Fehlerinfo:");
			vHilfe.add("---------");
			vHilfe.add("Der Status bezüglich dem Schalter <Ohne Abrechnung> MUSS ");
			vHilfe.add("in allen Orten der Fuhre gleich sein !");
			oMV.add_MESSAGE(new MyE2_Alarm_Message("Der Status <Ohne Abrechnung> stimmt nicht in allen Lade/Abladeorten überein !!"), false);
		}
		
		return oMV;
	}
	

	
	private String __String2vonLinks(String cTestString)
	{
		String cRueck = S.NN(cTestString);
		
		if (cTestString.length()>=2)
		{
			cRueck = cTestString.substring(0,2);
		}
		else
		{
			cRueck = cTestString;
		}
		
		return cRueck;
	}

}
