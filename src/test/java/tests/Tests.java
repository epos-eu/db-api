package tests;

import java.util.List;

import org.epos.eposdatamodel.Category;
import org.epos.handler.dbapi.dbapiimplementation.CategoryDBAPI;



public class Tests {

	public static void main(String[] args) {

		CategoryDBAPI cats = new CategoryDBAPI();
		List<Category> categoriesList = cats.getAll();

		Tests.recursivePrint(categoriesList, null);


	}

	public static void recursivePrint(List<Category> categoriesList, String id) {
		if(id==null) {
			for(Category cat : categoriesList) {
				System.out.println("---------- NODE -------- ");
				System.out.println("UID "+cat.getUid());
				System.out.println("NAME "+cat.getName());
				System.out.println("DESCRIPTION "+cat.getDescription());

				if(cat.getBroader()!=null) {
					System.out.println("--------- BROADER ----------");
					for(String uid : cat.getBroader()) {
						recursivePrint(categoriesList, uid);
					}
					System.out.println("--------- END BROADER ----------");
				}
				if(cat.getNarrower()!=null) {
					System.out.println("---------- NARROWERS -------- ");
					for(String uid : cat.getNarrower()) {
						recursivePrint(categoriesList, uid);
					}
					System.out.println("----------END  NARROWERS -------- ");
				}
			}
		}else {
			for(Category cat : categoriesList) {
				if(cat.getInstanceId().equals(id)) {
					System.out.println("---------- SUBNODE -------- ");
					System.out.println("UID "+cat.getUid());
					System.out.println("NAME "+cat.getName());
					System.out.println("DESCRIPTION "+cat.getDescription());
					System.out.println("---------- END SUBNODE -------- ");
				}
			}

		}
	}

}
