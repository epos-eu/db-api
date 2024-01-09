package tests;

import java.util.ArrayList;
import java.util.List;

import org.epos.eposdatamodel.Category;
import org.epos.eposdatamodel.DataProduct;
import org.epos.eposdatamodel.Distribution;
import org.epos.eposdatamodel.LinkedEntity;
import org.epos.eposdatamodel.PeriodOfTime;
import org.epos.eposdatamodel.State;
import org.epos.eposdatamodel.WebService;
import org.epos.handler.dbapi.DBAPIClient;
import org.epos.handler.dbapi.dbapiimplementation.CategoryDBAPI;



public class Tests {

	public static void main(String[] args) {
		
		DataProduct dp = new DataProduct();

		ApiResponseMessage message_one = DataProductManager.createDataProduct(dp, true, true);
		System.out.println(message_one);
		LinkedEntity dataproduct = message_one.getEntity();
		

		System.out.println(dataproduct);
		
		dp.setInstanceId(dataproduct.getInstanceId());
		dp.setMetaId(dataproduct.getMetaId());
		dp.setUid(dataproduct.getUid());
		ArrayList<String> titles = new ArrayList<String>();
		titles.add("TEST");
		dp.setTitle(titles);
		PeriodOfTime pot = new PeriodOfTime();
		pot.setEndDate(null);
		pot.setStartDate(null);
		dp.setTemporalExtent(List.of(pot));
		

		ApiResponseMessage message_two = DataProductManager.updateDataProduct(dp, true, true);
		System.out.println(message_two);
		
	
		
		Distribution distr = new Distribution();
		distr.setDataProduct(List.of(dataproduct));
		ApiResponseMessage message_three = DistributionManager.createDistribution(distr, true, true);
		System.out.println(message_three);
		
		distr.setUid(message_three.getEntity().getUid());
		distr.setInstanceId(message_three.getEntity().getInstanceId());
		distr.setMetaId(message_three.getEntity().getMetaId());
		
		
		WebService webservice = new WebService();
		webservice.setDistribution(List.of(message_three.getEntity()));
		
		ApiResponseMessage message_four = WebServiceManager.createWebService(webservice, true, true);
		
		LinkedEntity webservicelink = message_four.getEntity();

		//DistributionManager.updateDistribution(distr, true, true);
		
		System.out.println(DataProductManager.getDataProduct(dataproduct.getMetaId(), dataproduct.getInstanceId()));
		System.out.println(DistributionManager.getDistribution(message_three.getEntity().getMetaId(), message_three.getEntity().getInstanceId()));
		System.out.println(WebServiceManager.getWebService(webservicelink.getMetaId(), webservicelink.getInstanceId()));
		/*DataProduct dataproduct = new DataProduct();
		dataproduct.setEditorId("fixedUser5_metaid");

		LinkedEntity dataproductlink = DataProductManager.createDataProduct(dataproduct, true, true).getEntity();
		
		System.out.println(dataproductlink);
		System.out.println(DataProductManager.getDataProduct(dataproductlink.getMetaId(),dataproductlink.getInstanceId()));

		dataproduct.setTitle(List.of("MIAOMIAO"));
		dataproduct.setUid(dataproductlink.getUid());
		dataproduct.setInstanceId(dataproductlink.getInstanceId());
		dataproduct.setMetaId(dataproductlink.getMetaId());
		
		dataproductlink = DataProductManager.updateDataProduct(dataproduct, true, true).getEntity();
		
		System.out.println(dataproductlink);*/


		
		/*Distribution distribution = new Distribution();
		distribution.setEditorId("fixedUser5_metaid");
		distribution.setDataProduct(List.of(dataproductlink));
		
		LinkedEntity distributionlink = DistributionManager.createDistribution(distribution, true, true).getEntity();
		
		
		WebService webservice = new WebService();
		webservice.setEditorId("fixedUser5_metaid");
		webservice.setDistribution(List.of(distributionlink));
		
		LinkedEntity webservicelink = WebServiceManager.createWebService(webservice, true, true).getEntity();*/

		//System.out.println(DataProductManager.getDataProduct(dataproductlink.getMetaId(),dataproductlink.getInstanceId()));

		/*System.out.println(DistributionManager.getDistribution(distributionlink.getMetaId(),distributionlink.getInstanceId()));
		
		System.out.println(WebServiceManager.getWebService(webservicelink.getMetaId(),webservicelink.getInstanceId()));*/
		
	}

}
