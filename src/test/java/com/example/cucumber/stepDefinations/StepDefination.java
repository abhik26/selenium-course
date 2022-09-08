package com.example.cucumber.stepDefinations;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefination {
	
	@Given("^User in on Netbanking landing page$")
    public void user_in_on_netbanking_landing_page() throws Throwable {
		System.out.println("Navigated to login page");
    }

//    @When("^User login into application with username and password$")
//    public void user_login_into_application_with_username_and_password() throws Throwable {
//    	System.out.println("User logged in");
//    }
    
    @When("^User login into application with username \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void user_login_into_application_with_username_something_and_password_something(String strArg1, String strArg2) throws Throwable {
    	System.out.println("user logging in with provided credentials. Username: " + strArg1 + ", password: " + strArg2);
    }

    @Then("^Home page is populated$")
    public void home_page_is_populated() throws Throwable {
    	System.out.println("user came on home page");
    }

//    @And("^Cards are displayed$")
//    public void cards_are_displayed() throws Throwable {
//    	System.out.println("cards are shown to user");
//    }
    
    @Then("^Cards are displayed \"([^\"]*)\"")
    public void cards_are_displayed(String arg) {
    	System.out.println("cards are shown to user: " + arg);
    }
}
