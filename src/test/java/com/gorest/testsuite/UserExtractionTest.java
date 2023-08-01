package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.basePath = "/public/v2";
        //RestAssured.port = ;
        response = given()
                .when()
                .get("/users")
                .then().statusCode(200);
    }

    // 1. Extract the All Ids
    @Test
    public void extractTheAllIds(){
        List<Integer> allIds = response.extract().path("id");
        System.out.println(allIds);
    }

    //2. Extract the all Names
    @Test
    public void extractTheAllNames(){
        List<Integer> allNames = response.extract().path("name");
        System.out.println(allNames);
    }

    //3. Extract the name of 5th object
    @Test
    public void extractTheNameOfObject(){
        String object = response.extract().path("[4].name");
        System.out.println(object);
    }

    //4. Extract the names of all object whose status = inactive
    @Test
    public void extractAllObject(){
        List<String> status1 = response.extract().path("findAll{it.status == 'inactive'}.name");
        System.out.println(status1);
    }

    //5. Extract the gender of all the object whose status = active
    @Test
    public void extractTheGender(){
        List<String> activegender = response.extract().path("findAll{it.status == 'active'}.gender");
        System.out.println(activegender);
    }

    //6. Print the names of the object whose gender = female
    @Test
    public void printObjectGenderFemale(){
      List<String> genderFemale =  response.extract().path("findAll{it.gender == 'female'}.id");
        System.out.println(genderFemale);
    }

    //7. Get all the emails of the object where status = inactive
    @Test
    public void allTheEmailsObjectInactive(){
        List<String> emailObjects =  response.extract().path("findAll{it.status == 'inactive'}.email");
        System.out.println(emailObjects);
    }

    //8. Get the ids of the object where gender = male
    @Test
    public void genderMale(){
        List<String> genderMale =  response.extract().path("findAll{it.gender == 'male'}.id");
        System.out.println(genderMale);
    }

    //9. Get all the status
    @Test
    public void getAllStatus(){
        List<String> allStatus =  response.extract().path("status");
        System.out.println(allStatus);
    }

    //10. Get email of the object where name = Karthik Dubashi IV
    @Test
    public void getEmailObjects(){
       List<?> getEmailObjects = response.extract().path("findAll{it.name == 'Anish Reddy Sr.'}.email");
        System.out.println(getEmailObjects);
    }

    //11. Get gender of id = 5471
    @Test
    public void getGenderId(){
        List<String> getId = response.extract().path("findAll{it.id == 4040709}.gender");
        System.out.println(getId);

    }


}
