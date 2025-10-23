package src.test.java.prueba.v6;

import java.io.File;
import java.io.IOException;

import org.apache.xmlbeans.XmlException;
import org.etsi.uri.x02231.v2.ServiceSupplyPointsType;
import org.etsi.uri.x02231.v2.TSPServiceType;
import org.etsi.uri.x02231.v2.TSPType;
import org.etsi.uri.x02231.v2.TrustServiceStatusListDocument;
import org.etsi.uri.x02231.v2.TrustStatusListType;

public class pruebaV6 {

	public static void main(String[ ] args) {
		try {
			
			File file = new File("C:\\Users\\Jairo.Figueroa\\TSLs\\TSL\\AT-68.xml");
			
			TrustServiceStatusListDocument tslDoc = TrustServiceStatusListDocument.Factory.parse(file);
			
			TrustStatusListType tsl = tslDoc.getTrustServiceStatusList();
			
			for (TSPType tspType : tsl.getTrustServiceProviderList().getTrustServiceProviderArray()) {
			    for (TSPServiceType service : tspType.getTSPServices().getTSPServiceArray()) {
			    	ServiceSupplyPointsType supplyPoints = service.getServiceInformation().getServiceSupplyPoints();
			    	if(supplyPoints != null) {
			    		System.out.println(supplyPoints.getServiceSupplyPointArray(0)); // o accede a los campos que necesites
			    	}
			    }
			}

			
			tsl.getTrustServiceProviderList().getTrustServiceProviderArray();
			
			tsl.getSchemeInformation().getTSLVersionIdentifier();
		} catch (XmlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
