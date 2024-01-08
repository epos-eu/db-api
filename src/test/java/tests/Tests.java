package tests;

import java.util.List;

import org.epos.eposdatamodel.Category;
import org.epos.eposdatamodel.DataProduct;
import org.epos.eposdatamodel.Distribution;
import org.epos.eposdatamodel.LinkedEntity;
import org.epos.eposdatamodel.State;
import org.epos.eposdatamodel.WebService;
import org.epos.handler.dbapi.DBAPIClient;
import org.epos.handler.dbapi.dbapiimplementation.CategoryDBAPI;



public class Tests {

	public static void main(String[] args) {

		DataProduct dataproduct = new DataProduct();
		dataproduct.setEditorId("fixedUser5_metaid");

		LinkedEntity dataproductlink = DataProductManager.createDataProduct(dataproduct, true, true).getEntity();
		
		System.out.println(dataproductlink);
		System.out.println(DataProductManager.getDataProduct(dataproductlink.getMetaId(),dataproductlink.getInstanceId()));

		dataproduct.setTitle(List.of("MIAOMIAO"));
		dataproduct.setUid(dataproductlink.getUid());
		dataproduct.setInstanceId(dataproductlink.getInstanceId());
		dataproduct.setMetaId(dataproductlink.getMetaId());
		
		dataproductlink = DataProductManager.updateDataProduct(dataproduct, true, true).getEntity();
		
		System.out.println(dataproductlink);


		
		/*Distribution distribution = new Distribution();
		distribution.setEditorId("fixedUser5_metaid");
		distribution.setDataProduct(List.of(dataproductlink));
		
		LinkedEntity distributionlink = DistributionManager.createDistribution(distribution, true, true).getEntity();
		
		
		WebService webservice = new WebService();
		webservice.setEditorId("fixedUser5_metaid");
		webservice.setDistribution(List.of(distributionlink));
		
		LinkedEntity webservicelink = WebServiceManager.createWebService(webservice, true, true).getEntity();*/

		System.out.println(DataProductManager.getDataProduct(dataproductlink.getMetaId(),dataproductlink.getInstanceId()));

		/*System.out.println(DistributionManager.getDistribution(distributionlink.getMetaId(),distributionlink.getInstanceId()));
		
		System.out.println(WebServiceManager.getWebService(webservicelink.getMetaId(),webservicelink.getInstanceId()));*/
		
	}

}
