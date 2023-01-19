package org.api.testing.demo.runners;


import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(publish = true,
        features = "src/test/resources/features",
        glue = "org.api.testing.demo.steps",
        tags = "@FunctionalTest",
        snippets = SnippetType.CAMELCASE)

public class TestRunner {
}
