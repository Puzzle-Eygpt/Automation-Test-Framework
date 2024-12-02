package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import managers.DriverManager;

public class Hooks {
    @Before
    public void setUp() {
        DriverManager.getDriver();
    }

    @After
    public void tearDown() {
       DriverManager.quitDriver();
   }
}