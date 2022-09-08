package com.example.cucumber.cucumberOptions;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/java/com/example/cucumber/features",
		glue="com.example.cucumber.stepDefinations")
		
public class TestRunner {
	
}
