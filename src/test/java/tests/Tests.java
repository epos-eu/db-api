package tests;

import java.util.ArrayList;

import org.epos.eposdatamodel.Category;
import org.epos.eposdatamodel.CategoryScheme;
import org.epos.eposdatamodel.DataProduct;
import org.epos.eposdatamodel.Identifier;
import org.epos.eposdatamodel.State;
import org.epos.handler.dbapi.dbapiimplementation.CategoryDBAPI;
import org.epos.handler.dbapi.dbapiimplementation.CategorySchemeDBAPI;
import org.epos.handler.dbapi.dbapiimplementation.DataProductDBAPI;
import org.epos.handler.dbapi.model.EDMIspartofCategory;


public class Tests {
	
	public static void main(String[] args) {
		
		CategorySchemeDBAPI scheme = new CategorySchemeDBAPI();
		System.out.println(scheme.getAll());
		
		CategoryDBAPI cat = new CategoryDBAPI();
		System.out.println(cat.getAll());

	}

}
