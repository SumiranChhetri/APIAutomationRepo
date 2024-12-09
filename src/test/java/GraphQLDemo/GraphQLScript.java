package GraphQLDemo;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

import org.testng.Assert;

import io.restassured.RestAssured;

public class GraphQLScript {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	// Query GraphQL
		long characId = 10762l;
		
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
	 String response = given().log().all().header("Content-Type","application/json").body("{\r\n"
	 		+ "  \"query\": \"\\nquery($characterId:Int!, $locationId: Int! )\\n{\\n  \\n character(characterId: $characterId )\\n  {\\n    id\\n    name\\n    type\\n    origin\\n    {\\n      id\\n      dimension\\n    }\\n  }\\n  \\n  location(locationId:$locationId)\\n  {\\n    id\\n    created\\n  }\\n  episodes(filters:{name:\\\"GOT\\\", episode:\\\"Episode10\\\"})\\n  {\\n    info \\n    {\\n      count \\n      pages\\n    }\\n    result{\\n       id\\n       name\\n      \\n    }\\n  }\\n  \\n  episode(episodeId:11711)\\n  {\\n    id\\n    name\\n    air_date\\n    \\n  }\\n}\\n\\n  \\n\",\r\n"
	 		+ "  \"variables\": {\r\n"
	 		+ "    \"characterId\": "+characId+",\r\n"
	 		+ "    \"locationId\": 15842\r\n"
	 		+ "  }\r\n"
	 		+ "}")
			 .when().post("https://rahulshettyacademy.com/gq/graphql")
			 .then().extract().response().asPrettyString();
	 
	  System.out.println(response);
	  JsonPath js = new JsonPath(response);
	  String charName = js.getString("data.character.name");
	  Assert.assertEquals(charName, "Sumiran");
	  
	  // Mutation GraphQL
	  
	  RestAssured.baseURI = "https://rahulshettyacademy.com";
		 String mutationresponse = given().log().all().header("Content-Type","application/json").body("{\r\n"
		 		+ "  \"query\": \"mutation{\\n\\n   createLocation(location :{name :\\\"India\\\" , type: \\\"Asia\\\" , dimension: \\\"555\\\"} )\\n  {\\n    id \\n  }\\n  \\n  createCharacter(character :{name :\\\"Sumiran\\\", type:\\\"HERO\\\", status : \\\"ALIVE\\\", species: \\\"fantasy\\\", gender:\\\"MALE\\\",image: \\\"png\\\", originId:15842 , locationId:15842 })\\n  {\\n    id\\n  }\\n\\n   createEpisode(episode: {name : \\\"GOT\\\", air_date: \\\"2014-06-12T07:36:58.583Z\\\", episode : \\\"Episode01\\\"})\\n  {\\n    id\\n  }\\n  }\\n  \\n\",\r\n"
		 		+ "  \"variables\": null\r\n"
		 		+ "}")
				 .when().post("https://rahulshettyacademy.com/gq/graphql")
				 .then().extract().response().asPrettyString();
		 
		  System.out.println(mutationresponse);
		  JsonPath js1 = new JsonPath(mutationresponse);
		  String characterCreateID = js.getString("data.createCharacter.id");
		  System.out.println(characterCreateID);

	}

}
