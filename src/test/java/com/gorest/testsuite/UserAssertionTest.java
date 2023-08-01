package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserAssertionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2/";
        //RestAssured.port = ;
        response = given()
                .when()
                .get("/users?page=1&per_page=20")
                .then().statusCode(200);
    }

    // 1. Verify the if the total record is 20
    @Test
    public void verifyTheTotal(){
        response.body("size", equalTo(20));
    }

    // 2. Verify the if the name of id = 5487 is equal to ”Hamsini Trivedi”
    @Test
    public void verifyTheIdAmarnathDevar(){
        response.body(".findAll{it.id == '4040717'}", hasItem(hasEntry("name","Shreyashi Kakkar Sr.")));
    }

   // 3. Check the single ‘Name’ in the Array list (Subhashini Talwar)
   @Test
   public void CheckTheSingleName(){
       response.body("name",hasItem("Sanya Kaur"));
   }

   //4. Check the multiple ‘Names’ in the ArrayList (Mrs. Menaka Bharadwaj, Msgr. Bodhan Guha, Karthik Dubashi IV)
   @Test
   public void CheckTheMultipleName(){
       response.body("name",hasItems("Mr. Preity Patel","Ms. Vaishvi Shukla","Malati Gandhi"));
   }

   //5. Verify the email of userid = 5471 is equal “adiga_aanjaneya_rep@jast.org”
   @Test
   public void VerifyTheEmailOfUserId(){
       response.body("find{it.id == 4040717}.email", equalTo("kakkar_shreyashi_sr@ratke.test"));
   }

  // 6. Verify the status is “Active” of user name is “Shanti Bhat V”
  @Test
  public void VerifyTheStatus(){
      response.body("find{it.name =='Ms. Vaishvi Shukla'}.status", equalTo("inactive"));
  }

  // 7. Verify the Gender = male of user name is “Niro Prajapat”
  @Test
  public void VerifyTheGender(){
      response.body("find{it.name == 'Malati Gandhi'}.gender", equalTo("male"));
  }

}
