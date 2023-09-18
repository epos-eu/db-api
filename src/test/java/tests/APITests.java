package tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.epos.eposdatamodel.Category;
import org.epos.eposdatamodel.CategoryScheme;
import org.epos.eposdatamodel.ContactPoint;
import org.epos.eposdatamodel.DataProduct;
import org.epos.eposdatamodel.Distribution;
import org.epos.eposdatamodel.Identifier;
import org.epos.eposdatamodel.LinkedEntity;
import org.epos.eposdatamodel.Person;
import org.epos.eposdatamodel.State;
import org.epos.eposdatamodel.WebService;
import org.epos.handler.dbapi.DBAPIClient;
import org.epos.handler.dbapi.dbapiimplementation.CategoryDBAPI;
import org.epos.handler.dbapi.dbapiimplementation.CategorySchemeDBAPI;
import org.epos.handler.dbapi.dbapiimplementation.ContactPointDBAPI;
import org.epos.handler.dbapi.dbapiimplementation.DataProductDBAPI;
import org.epos.handler.dbapi.dbapiimplementation.DistributionDBAPI;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class APITests {

	private static String json_input = "{\n"
			+ "  \"category:bogdanka\":{\n"
			+ "     \"WP14-DDSS-045\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ],\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"BOGDANKA\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ],\n"
			+ "     \"category:undergroundmg\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:asfordbymine\":{\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"WP14-DDSS-024\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ],\n"
			+ "     \"ASFORDBY MINE\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ],\n"
			+ "     \"category:undergroundmg\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:starfish\":{\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"category:undergroundgasstage\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ],\n"
			+ "     \"STARFISH\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ],\n"
			+ "     \"WP14-DDSS-016\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:northwich\":{\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"WP14-DDSS-025\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ],\n"
			+ "     \"category:undergroundmg\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ],\n"
			+ "     \"NORTHWICH\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:cornwall\":{\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"WP14-DDSS-040\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ],\n"
			+ "     \"CORNWALL\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ],\n"
			+ "     \"category:geothermalenergyproducti\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:wysin\":{\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"WYSIN\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ],\n"
			+ "     \"category:uncventialhydrocarbextracti\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ],\n"
			+ "     \"WP14-DDSS-037\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:thoresbycoliery\":{\n"
			+ "     \"WP14-DDSS-023\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ],\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"category:undergroundmg\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ],\n"
			+ "     \"THORESBY COLIERY\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:soultzsousforets2003\":{\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"WP14-DDSS-014\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ],\n"
			+ "     \"SOULTZ-SOUS-FORETS 2003\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ],\n"
			+ "     \"category:geothermalenergyproducti\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:thegeysersprati929cluster\":{\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ],\n"
			+ "     \"The Geysers Prati-9/29 cluster\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ],\n"
			+ "     \"WP14-DDSS-017\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ],\n"
			+ "     \"category:geothermalenergyproducti\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:soultzsousforets2000\":{\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"WP14-DDSS-013\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ],\n"
			+ "     \"SOULTZ-SOUS-FORETS 2000\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ],\n"
			+ "     \"category:geothermalenergyproducti\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:cventialhydrocarbextracti\":{\n"
			+ "     \"category:groningenfield\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ],\n"
			+ "     \"category:lacqgasfield\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ],\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"category:emiliaregion\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ],\n"
			+ "     \"Conventional hydrocarbon extraction\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ],\n"
			+ "     \"category:gazli\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ],\n"
			+ "     \"category:multidisciplaryahdata\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ],\n"
			+ "     \"category:oklahoma\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ],\n"
			+ "     \"TCS Subdomain\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ],\n"
			+ "     \"category:valdagrioilfield\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:soultzsousforets2005\":{\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"WP14-DDSS-047\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ],\n"
			+ "     \"SOULTZ-SOUS-FORETS 2005\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ],\n"
			+ "     \"category:geothermalenergyproducti\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:undergroundmg\":{\n"
			+ "     \"category:pyhasalmimine\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ],\n"
			+ "     \"category:asfordbymine\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ],\n"
			+ "     \"category:bogdanka\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ],\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"category:bobrekmine\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ],\n"
			+ "     \"TCS Subdomain\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ],\n"
			+ "     \"category:northwich\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ],\n"
			+ "     \"category:thoresbycoliery\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ],\n"
			+ "     \"category:uscb\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ],\n"
			+ "     \"category:preesallmine\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ],\n"
			+ "     \"category:multidisciplaryahdata\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ],\n"
			+ "     \"Underground mining\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ],\n"
			+ "     \"category:lgcd\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ],\n"
			+ "     \"category:gisoscerville\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:soultzsousforets1993\":{\n"
			+ "     \"WP14-DDSS-012\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ],\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"SOULTZ-SOUS-FORETS 1993\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ],\n"
			+ "     \"category:geothermalenergyproducti\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:soultzsousforets2004\":{\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"WP14-DDSS-046\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ],\n"
			+ "     \"SOULTZ-SOUS-FORETS 2004\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ],\n"
			+ "     \"category:geothermalenergyproducti\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:virtualaccesstcsahepisodes\":{\n"
			+ "     \"WP14-DDSS-001\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ],\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"category:multidisciplaryahdata\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ],\n"
			+ "     \"Virtual access to TCS AH Episodes\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:groningenfield\":{\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"category:cventialhydrocarbextracti\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ],\n"
			+ "     \"GRONINGEN FIELD\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ],\n"
			+ "     \"WP14-DDSS-029\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:bobrekmine\":{\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"BOBREK MINE\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ],\n"
			+ "     \"category:undergroundmg\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ],\n"
			+ "     \"WP14-DDSS-007\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:gazli\":{\n"
			+ "     \"WP14-DDSS-044\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ],\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"category:cventialhydrocarbextracti\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ],\n"
			+ "     \"GAZLI\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:virtualaccesstcsahapplicatis\":{\n"
			+ "     \"category:processgols\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ],\n"
			+ "     \"WP14-DDSS-001-A\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ],\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"Virtual access to TCS AH Applications\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:preesehall\":{\n"
			+ "     \"WP14-DDSS-022\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ],\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"category:uncventialhydrocarbextracti\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ],\n"
			+ "     \"PREESE HALL\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:reservoirimpoundment\":{\n"
			+ "     \"category:monteynard\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ],\n"
			+ "     \"category:songtranh\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ],\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"category:vouglans\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ],\n"
			+ "     \"Reservoir impoundment\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ],\n"
			+ "     \"category:valdagriwaterreservoir\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ],\n"
			+ "     \"category:multidisciplaryahdata\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ],\n"
			+ "     \"category:czorsztyn\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ],\n"
			+ "     \"TCS Subdomain\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ],\n"
			+ "     \"category:laichau\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:cooperbasin\":{\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"COOPER BASIN\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ],\n"
			+ "     \"WP14-DDSS-048\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ],\n"
			+ "     \"category:geothermalenergyproducti\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:emiliaregion\":{\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"category:cventialhydrocarbextracti\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ],\n"
			+ "     \"WP14-DDSS-021\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ],\n"
			+ "     \"EMILIA REGION\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:lgcd\":{\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"category:undergroundmg\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ],\n"
			+ "     \"WP14-DDSS-004\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ],\n"
			+ "     \"LGCD\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:geothermalenergyproducti\":{\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"TCS Subdomain\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ],\n"
			+ "     \"category:cornwall\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ],\n"
			+ "     \"category:soultzsousforets2003\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ],\n"
			+ "     \"category:thegeysersprati929cluster\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ],\n"
			+ "     \"category:cooperbasin\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ],\n"
			+ "     \"category:grossschoenebeck\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ],\n"
			+ "     \"category:soultzsousforets2000\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ],\n"
			+ "     \"category:stgallen\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ],\n"
			+ "     \"category:soultzsousforets2005\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ],\n"
			+ "     \"category:soultzsousforets1993\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ],\n"
			+ "     \"category:soultzsousforets2004\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ],\n"
			+ "     \"category:multidisciplaryahdata\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ],\n"
			+ "     \"Geothermal energy production\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ],\n"
			+ "     \"category:thegeysers\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:monteynard\":{\n"
			+ "     \"WP14-DDSS-011\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ],\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"MONTEYNARD\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ],\n"
			+ "     \"category:reservoirimpoundment\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:cottonvalley\":{\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"WP14-DDSS-042\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ],\n"
			+ "     \"category:uncventialhydrocarbextracti\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ],\n"
			+ "     \"COTTON VALLEY\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:processgols\":{\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"TCS Subdomain\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ],\n"
			+ "     \"Processing tools\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ],\n"
			+ "     \"category:virtualaccesstcsahapplicatis\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:anthropogenichazards\":{\n"
			+ "     \"Anthropogenic Hazards\":[\n"
			+ "        \"dct:title\"\n"
			+ "     ],\n"
			+ "     \"TCS Domain\":[\n"
			+ "        \"dct:description\"\n"
			+ "     ],\n"
			+ "     \"skos:ConceptScheme\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:lacqgasfield\":{\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"category:cventialhydrocarbextracti\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ],\n"
			+ "     \"WP14-DDSS-010\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ],\n"
			+ "     \"LACQ GAS FIELD\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:valdagriwaterreservoir\":{\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"WP14-DDSS-020\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ],\n"
			+ "     \"VAL D'AGRI WATER RESERVOIR\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ],\n"
			+ "     \"category:reservoirimpoundment\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:undergroundgasstage\":{\n"
			+ "     \"category:starfish\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ],\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"Underground gas storage\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ],\n"
			+ "     \"category:multidisciplaryahdata\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ],\n"
			+ "     \"TCS Subdomain\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:oklahoma\":{\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"category:cventialhydrocarbextracti\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ],\n"
			+ "     \"OKLAHOMA\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ],\n"
			+ "     \"category:uncventialhydrocarbextracti\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ],\n"
			+ "     \"WP14-DDSS-027\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ],\n"
			+ "     \"category:wastewaterjecti\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:valdagrioilfield\":{\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"category:cventialhydrocarbextracti\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ],\n"
			+ "     \"WP14-DDSS-019\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ],\n"
			+ "     \"VAL D'AGRI OIL FIELD\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ],\n"
			+ "     \"category:wastewaterjecti\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:uscb\":{\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"category:undergroundmg\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ],\n"
			+ "     \"USCB\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ],\n"
			+ "     \"WP14-DDSS-006\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:songtranh\":{\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"SONG TRANH\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ],\n"
			+ "     \"WP14-DDSS-005\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ],\n"
			+ "     \"category:reservoirimpoundment\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:vouglans\":{\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"WP14-DDSS-043\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ],\n"
			+ "     \"VOUGLANS\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ],\n"
			+ "     \"category:reservoirimpoundment\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:grossschoenebeck\":{\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"WP14-DDSS-003\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ],\n"
			+ "     \"GROSS SCHOENEBECK\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ],\n"
			+ "     \"category:geothermalenergyproducti\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:lubocino\":{\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"WP14-DDSS-036\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ],\n"
			+ "     \"category:uncventialhydrocarbextracti\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ],\n"
			+ "     \"LUBOCINO\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:stgallen\":{\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"ST. GALLEN\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ],\n"
			+ "     \"WP14-DDSS-039\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ],\n"
			+ "     \"category:geothermalenergyproducti\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:preesallmine\":{\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"PREESALL MINE\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ],\n"
			+ "     \"category:undergroundmg\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ],\n"
			+ "     \"WP14-DDSS-028\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:thegeysers\":{\n"
			+ "     \"THE GEYSERS\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ],\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ],\n"
			+ "     \"category:geothermalenergyproducti\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ],\n"
			+ "     \"WP14-DDSS-018\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:wastewaterjecti\":{\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"category:multidisciplaryahdata\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ],\n"
			+ "     \"category:oklahoma\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ],\n"
			+ "     \"TCS Subdomain\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ],\n"
			+ "     \"Wastewater injection\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ],\n"
			+ "     \"category:valdagrioilfield\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:pyhasalmimine\":{\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"category:undergroundmg\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ],\n"
			+ "     \"WP14-DDSS-009\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ],\n"
			+ "     \"PYHASALMI MINE\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:czorsztyn\":{\n"
			+ "     \"CZORSZTYN\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ],\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"WP14-DDSS-002\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ],\n"
			+ "     \"category:reservoirimpoundment\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:uncventialhydrocarbextracti\":{\n"
			+ "     \"category:cottonvalley\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ],\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"Unconventional hydrocarbon extraction\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ],\n"
			+ "     \"category:lubocino\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ],\n"
			+ "     \"category:multidisciplaryahdata\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ],\n"
			+ "     \"category:oklahoma\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ],\n"
			+ "     \"TCS Subdomain\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ],\n"
			+ "     \"category:wysin\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ],\n"
			+ "     \"category:preesehall\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:multidisciplaryahdata\":{\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"category:cventialhydrocarbextracti\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ],\n"
			+ "     \"category:undergroundgasstage\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ],\n"
			+ "     \"category:undergroundmg\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ],\n"
			+ "     \"TCS Subdomain\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ],\n"
			+ "     \"Multidisciplinary AH data\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ],\n"
			+ "     \"category:virtualaccesstcsahepisodes\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ],\n"
			+ "     \"category:uncventialhydrocarbextracti\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ],\n"
			+ "     \"category:reservoirimpoundment\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ],\n"
			+ "     \"category:wastewaterjecti\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ],\n"
			+ "     \"category:geothermalenergyproducti\":[\n"
			+ "        \"skos:narrower\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:gisoscerville\":{\n"
			+ "     \"GISOS-CERVILLE\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ],\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"category:undergroundmg\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ],\n"
			+ "     \"WP14-DDSS-015\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ]\n"
			+ "  },\n"
			+ "  \"category:laichau\":{\n"
			+ "     \"WP14-DDSS-033\":[\n"
			+ "        \"skos:definition\"\n"
			+ "     ],\n"
			+ "     \"category:anthropogenichazards\":[\n"
			+ "        \"skos:inScheme\"\n"
			+ "     ],\n"
			+ "     \"LAI CHAU\":[\n"
			+ "        \"skos:prefLabel\"\n"
			+ "     ],\n"
			+ "     \"skos:Concept\":[\n"
			+ "        \"rdf:type\"\n"
			+ "     ],\n"
			+ "     \"category:reservoirimpoundment\":[\n"
			+ "        \"skos:broader\"\n"
			+ "     ]\n"
			+ "  }\n"
			+ "}";

	public static void main(String[] args) {

		DBAPIClient dbapi = new DBAPIClient();
		
		WebService a = new WebService();
		a.setUid("a");
		a.setState(State.DRAFT);
		a.setEditorId("ingestor");
		
		LinkedEntity leA = dbapi.create(a);
		System.out.println(leA);
		
		WebService b = new WebService();
		b.setUid("b");
		b.setState(State.DRAFT);
		b.setEditorId("ingestor");

		ArrayList<LinkedEntity> les = new ArrayList<>();
		les.add(leA);
		b.setRelation(les);
		
		LinkedEntity leB = dbapi.create(b);
		System.out.println(leB);
		
		/*DataProduct dp = new DataProduct();
		dp.setUid("prova-vv");
		dp.setState(State.DRAFT);
		dp.setEditorId("ingestor");
		
		LinkedEntity leDataproduct = dbapi.create(dp);
		System.out.println(leDataproduct);
		
		Distribution d = new Distribution();
		d.setUid("prova-vv distribution");
		d.setState(State.DRAFT);
		d.setEditorId("ingestor");
		ArrayList<LinkedEntity> dataproducts = new ArrayList<>();
		dataproducts.add(leDataproduct);
		d.setDataProduct(dataproducts);
		
		LinkedEntity leDistribution = dbapi.create(d);
		System.out.println(leDistribution);
		
		WebService ws = new WebService();
		ws.setUid("prova-vv webservice");
		ws.setState(State.DRAFT);
		ws.setEditorId("ingestor");
		
		LinkedEntity leWebService = dbapi.create(ws);
		System.out.println(leWebService);
		
		d.setInstanceId(leDistribution.getInstanceId());
		d.setMetaId(leDistribution.getMetaId());
		d.setAccessService(leWebService);
		
		dbapi.update(d, new DBAPIClient.UpdateQuery().hardUpdate(true));*/
		
		
		
		/*dp.setInstanceId(leDataproduct.getInstanceId());
		dp.setMetaId(leDataproduct.getMetaId());
		
		dbapi.update(dp, new DBAPIClient.UpdateQuery().hardUpdate(true));*/
		
		
	}
	
	public static Person getPersonUsingDBAPIClient() {
		DBAPIClient client = new DBAPIClient();
		LinkedEntity l = new LinkedEntity();
		l.setEntityType("Person");
		l.setInstanceId("870f1195-1391-4416-957c-747c8c7867fb");
		l.setMetaId("8ad830f8-946d-43b1-bceb-84910aae506e");
		l.setUid("http://orcid.org/0000-0001-8626-2703");
		
		List<Person> d = client.retrieve(Person.class,
				new DBAPIClient.GetQuery()
				.metaId(l.getMetaId()));
		
		return d.get(0);
	}
	
	public static void createContactPoint() {
		ContactPoint cp = new ContactPoint();
		cp.setUid("testprova");
		cp.setRole("legalContact");
		LinkedEntity l = new LinkedEntity();
		l.setEntityType("Person");
		l.setInstanceId("870f1195-1391-4416-957c-747c8c7867fb");
		l.setMetaId("8ad830f8-946d-43b1-bceb-84910aae506e");
		l.setUid("http://orcid.org/0000-0001-8626-2703");
		System.out.println(l);
		cp.setPerson(l);
		cp.setState(State.DRAFT);
		cp.setEditorId("test");

		System.out.println(cp);
		
		ContactPointDBAPI api = new ContactPointDBAPI();
		api.save(cp);
	}

	public static void createDataproduct() {
		if(getDataproduct()==null) {
			DataProduct dp = new DataProduct();
			dp.setUid("test/dataproduct");
			dp.setTitle(new ArrayList<String>());
			dp.getTitle().add("test title");
			dp.setDescription(new ArrayList<String>());
			dp.getDescription().add("test description");
			dp.setIdentifier(new ArrayList<Identifier>());
			Identifier identifier = new Identifier();
			identifier.setIdentifier("test");
			identifier.setType("DDSS");
			dp.getIdentifier().add(identifier);
			dp.setState(State.DRAFT);
			dp.setEditorId("test");

			System.out.println(dp);
			
			DataProductDBAPI api = new DataProductDBAPI();
			api.save(dp);
		}


	}

	public static void createDistribution() {
		if(getDistribution()==null) {
			Distribution distr = new Distribution();
			distr.setUid("test/distribution");
			distr.setTitle(new ArrayList<String>());
			distr.getTitle().add("test title");
			distr.setDescription(new ArrayList<String>());
			distr.getDescription().add("test description");
			distr.setState(State.DRAFT);
			distr.setEditorId("test");

			System.out.println(distr);

			DistributionDBAPI api = new DistributionDBAPI();
			api.save(distr);
		}
	}
	
	public static void testCategories() {
Gson gson = new Gson();
		
		JsonObject input = gson.fromJson(json_input, JsonObject.class);
		
		ArrayList<CategoryScheme> schemes = new ArrayList<CategoryScheme>();
		ArrayList<Category> categories = new ArrayList<Category>();
		
		Set<Map.Entry<String, JsonElement>> entries = input.entrySet();//will return members of your object
		for (Map.Entry<String, JsonElement> entry: entries) {
		    System.out.println(entry.getKey());
		    String uid = entry.getKey();
		    String type = new String();
		    String definition = new String();
		    String inscheme = new String();
		    String name = new String();
		    
		    ArrayList<String> narrowers = new ArrayList<String>();
		    ArrayList<String> broaders = new ArrayList<String>();
		    
		    JsonObject item = input.get(entry.getKey()).getAsJsonObject();
		    Set<Map.Entry<String, JsonElement>> values = item.entrySet();//will return members of your object
		    for (Map.Entry<String, JsonElement> entry2: values) {
		    	if(entry2.getValue().getAsJsonArray().get(0).getAsString().equals("rdf:type")) {
		    		type = entry2.getKey();
		    	}
		    	if(entry2.getValue().getAsJsonArray().get(0).getAsString().equals("skos:definition")) {
		    		definition = entry2.getKey();
		    	}
		    	if(entry2.getValue().getAsJsonArray().get(0).getAsString().equals("dct:description")) {
		    		definition = entry2.getKey();
		    	}
		    	if(entry2.getValue().getAsJsonArray().get(0).getAsString().equals("skos:inScheme")) {
		    		inscheme = entry2.getKey();
		    	}
		    	if(entry2.getValue().getAsJsonArray().get(0).getAsString().equals("skos:prefLabel")) {
		    		name = entry2.getKey();
		    	}
		    	if(entry2.getValue().getAsJsonArray().get(0).getAsString().equals("dct:title")) {
		    		name = entry2.getKey();
		    	}
		    	if(entry2.getValue().getAsJsonArray().get(0).getAsString().equals("skos:broader")) {
		    		broaders.add(entry2.getKey());
		    	}
		    	if(entry2.getValue().getAsJsonArray().get(0).getAsString().equals("skos:narrower")) {
		    		narrowers.add(entry2.getKey());
		    	}
		    }
		    switch(type) {
		    case "skos:ConceptScheme":
		    	CategoryScheme scheme = new CategoryScheme();
		    	scheme.setUid(uid);
		    	scheme.setTitle(name);
		    	scheme.setDescription(definition);
		    	schemes.add(scheme);
		    	break;
		    case "skos:Concept":
		    	Category cat = new Category();
		    	cat.setName(name);
		    	cat.setDescription(definition);
		    	cat.setInScheme(inscheme);
		    	cat.setUid(uid);
		    	if(broaders.size()>0) cat.setBroader(broaders);
		    	if(narrowers.size()>0) cat.setBroader(narrowers);
		    	categories.add(cat);
		    	break;
		    }
		}
		
		
		
		CategoryDBAPI catAPI = new CategoryDBAPI();
		CategorySchemeDBAPI schemeCatAPI = new CategorySchemeDBAPI();
		
		for(CategoryScheme scheme : schemes) {
			schemeCatAPI.save(scheme);
		}
		
		for(Category cat : categories) {
			catAPI.save(cat);
		}
	}

	public static DataProduct getDataproduct() {
		DataProductDBAPI api = new DataProductDBAPI();
		List<DataProduct> products = api.getByUid("test/dataproduct");
		return products.size()>0? products.get(0) : null;
	}

	public static Distribution getDistribution() {
		DistributionDBAPI api = new DistributionDBAPI();
		List<Distribution> products = api.getByUid("test/distribution");
		return products.size()>0? products.get(0) : null;
	}

	public static void updateDataproductWithDistribution(DataProduct dp, Distribution distr) {
		LinkedEntity le = new LinkedEntity();
		le.setEntityType("distribution");
		le.setInstanceId(distr.getInstanceId());
		le.setMetaId(distr.getMetaId());
		le.setUid(distr.getUid());
		dp.addDistribution(le);
		dp.setState(State.DRAFT);
		dp.setEditorId("test");
		DataProductDBAPI api = new DataProductDBAPI();
		api.save(dp);
	}

}
