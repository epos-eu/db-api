package tests;

import java.util.ArrayList;
import java.util.List;

import org.epos.eposdatamodel.DataProduct;
import org.epos.eposdatamodel.Distribution;
import org.epos.eposdatamodel.LinkedEntity;



public class Tests {

	public static void main(String[] args) {
		
		DataProduct dp = new DataProduct();
		dp.setUid("MOCCABBACCA2");
		dp.setEditorId("fixedUser5_metaid");

		System.out.println("----> CREATE DATAPRODUCT");
		ApiResponseMessage message_one = DataProductManager.createDataProduct(dp, true, true);
		System.out.println(message_one.getEntity());
		LinkedEntity dataproduct = message_one.getEntity();
		
		dp.setInstanceId(dataproduct.getInstanceId());
		dp.setMetaId(dataproduct.getMetaId());
		dp.setUid(dataproduct.getUid());
		ArrayList<String> titles = new ArrayList<String>();
		titles.add("TEST");
		dp.setTitle(titles);
		System.out.println("----> UPDATE DATAPRODUCT");
		message_one = DataProductManager.updateDataProduct(dp, true, true);
		System.out.println(message_one.getEntity());
	
		
		Distribution distr = new Distribution();
		distr.setDataProduct(List.of(dataproduct));
		distr.setEditorId("fixedUser5_metaid");
		System.out.println("----> CREATE DISTRIBUTION");
		ApiResponseMessage message_three = DistributionManager.createDistribution(distr, true, true);
		System.out.println(message_three);
		
		System.out.println(DataProductManager.getDataProduct(dataproduct.getMetaId(), dataproduct.getInstanceId()));
		

		System.out.println(DistributionManager.getDistribution(message_three.getEntity().getMetaId(), message_three.getEntity().getInstanceId()));
		
		System.out.println(List.of(message_three.getEntity()));

		dp.setDistribution(List.of(message_three.getEntity()));

		System.out.println("----> UPDATE DATAPRODUCT ---> \n"+dp+"\n\n");
		ApiResponseMessage message_two = DataProductManager.updateDataProduct(dp, true, true);
		System.out.println(message_two);
		
		distr.setUid(message_three.getEntity().getUid());
		distr.setInstanceId(message_three.getEntity().getInstanceId());
		distr.setMetaId(message_three.getEntity().getMetaId());
		distr.setTitle(List.of("STOCAZZO"));
		message_three = DistributionManager.updateDistribution(distr, true, true);
		
		System.out.println(DataProductManager.getDataProduct(dataproduct.getMetaId(), dataproduct.getInstanceId()));
		
		/*DataProduct dp = new DataProduct();

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
		System.out.println(WebServiceManager.getWebService(webservicelink.getMetaId(), webservicelink.getInstanceId()));*/
		
		
	}

}
