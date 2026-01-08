package rohstoff.Echo2BusinessLogic.FIBU.SUCHE;

import java.util.HashSet;
import java.util.concurrent.CopyOnWriteArrayList;

import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;

public class VerbundenFibuIdSuche {

	public VerbundenFibuIdSuche() {

	}

	public VectorSingle suche(String fibuId) throws myException {
		VectorSingle ergebnis = new VectorSingle();

		VectorSingle vposRgErgebnis = new VectorSingle();
		VectorSingle fuhreOrtErgebnis = new VectorSingle();
		VectorSingle fuhreErgebnis = new VectorSingle();
		

		VerbundenFibuIdsViaVposRg vposRgSuche = new VerbundenFibuIdsViaVposRg(fibuId);
		vposRgErgebnis.addAll(sucheFibuIds(vposRgSuche));

		VerbundenFibuIdsViaFuhreOrt ortFuhreSuche = new VerbundenFibuIdsViaFuhreOrt(fibuId);
		fuhreOrtErgebnis.addAll(sucheFibuIds(ortFuhreSuche));

		if(fuhreOrtErgebnis.size()==0){
			VerbundenFibuIdsViaFuhre fuhreSuche = new VerbundenFibuIdsViaFuhre(fibuId);
			fuhreErgebnis.addAll(sucheFibuIds(fuhreSuche));
		}
		
		ergebnis.addAll(vposRgErgebnis);
		ergebnis.addAll(fuhreOrtErgebnis);
		ergebnis.addAll(fuhreErgebnis);

		return ergebnis;
	}

	private HashSet<String> sucheFibuIds(AbstractFibuIdSuche fr) throws myException {

		CopyOnWriteArrayList<String> stage1;

		HashSet<String> finaleErgebnisVektor;

		finaleErgebnisVektor = new HashSet<String>(fr.suche());
		stage1 = new CopyOnWriteArrayList<String>(finaleErgebnisVektor);//new CopyOnWriteArrayList<String>(fr.suche());


		//passe 2...n

		if(! stage1.isEmpty()){

			for(int i=0;i<stage1.size();i++){
				String id = stage1.get(i);

				fr.setFibuId(id);
				HashSet<String> intermediarStage =fr.suche() ;

				if(intermediarStage.size()>0){		

					for(String ergebnisId: intermediarStage){

						if(! stage1.contains(ergebnisId) || ergebnisId.isEmpty()){
							stage1.add(ergebnisId);
						}
					}
				}else 
					continue;
			}

			finaleErgebnisVektor.addAll(stage1);


		}	
		return finaleErgebnisVektor;
	}
	
	public VectorSingle sucheBelegenFibuIds(String fibuId) throws myException{
		
		CopyOnWriteArrayList<String> stage1;

		VectorSingle finaleErgebnisVektor = new VectorSingle();
		
		stage1 = new CopyOnWriteArrayList<String>();
		
		VectorSingle vposRgErgebnis = new VectorSingle();
		VectorSingle fuhreOrtErgebnis = new VectorSingle();
		VectorSingle fuhreErgebnis = new VectorSingle();
		
		VerbundenFibuIdsViaVposRg vposRgSuche = new VerbundenFibuIdsViaVposRg(fibuId);
		vposRgErgebnis.addAll(vposRgSuche.suche());
		
		VerbundenFibuIdsViaFuhreOrt ortFuhreSuche = new VerbundenFibuIdsViaFuhreOrt(fibuId);
		fuhreOrtErgebnis.addAll(ortFuhreSuche.suche());
		
		VerbundenFibuIdsViaFuhre fuhreSuche = new VerbundenFibuIdsViaFuhre(fibuId);
		
		if(fuhreOrtErgebnis.size()==0){
			fuhreErgebnis.addAll(fuhreSuche.suche());
		}
		

		stage1.addAll(vposRgErgebnis);
		stage1.addAll(fuhreOrtErgebnis);
		stage1.addAll(fuhreErgebnis);
		vposRgErgebnis.clear();
		fuhreOrtErgebnis.clear();
		fuhreErgebnis.clear();
		
		if(! stage1.isEmpty()){

			for(int i=0;i<stage1.size();i++){
				String id = stage1.get(i);
				
				
				vposRgSuche.setFibuId(id);
				vposRgErgebnis.addAll(vposRgSuche.suche());
				HashSet<String> intermediarStage =  new HashSet<String>(vposRgErgebnis);
				
				ortFuhreSuche.setFibuId(id);
				fuhreOrtErgebnis.addAll(ortFuhreSuche.suche());
				intermediarStage.addAll(fuhreOrtErgebnis);
				
				if(fuhreOrtErgebnis.size()==0){
					fuhreSuche.setFibuId(id);
					fuhreErgebnis.addAll(fuhreSuche.suche());
					intermediarStage.addAll(fuhreErgebnis);
				}

				if(intermediarStage.size()>0){
				
					for(String ergebnisId: intermediarStage){
//						vposRgSuche.setFibuId(ergebnisId);
//						vposRgErgebnis.addAll(vposRgSuche.suche());
//						
//						ortFuhreSuche.setFibuId(ergebnisId);
//						fuhreOrtErgebnis.addAll(ortFuhreSuche.suche());
//						
//						if(fuhreOrtErgebnis.size()==0){
//							fuhreSuche.setFibuId(ergebnisId);
//							fuhreErgebnis.addAll(fuhreSuche.suche());
//						}
//						
						if(! stage1.contains(ergebnisId) || ergebnisId.isEmpty()){
							stage1.add(ergebnisId);
						}else continue;
						
					}
				}
				
				
				
//				fr.setFibuId(id);
//				HashSet<String> intermediarStage =fr.suche() ;

//				if(intermediarStage.size()>0){		
//
//					for(String ergebnisId: intermediarStage){
//
//						if(! stage1.contains(ergebnisId) || ergebnisId.isEmpty()){
//							stage1.add(ergebnisId);
//						}
//					}
//				}else 
//					continue;
			}

			finaleErgebnisVektor.addAll(stage1);

			vposRgErgebnis.clear();
			fuhreOrtErgebnis.clear();
			fuhreErgebnis.clear();

		}	
		
		return finaleErgebnisVektor;
	}

}
