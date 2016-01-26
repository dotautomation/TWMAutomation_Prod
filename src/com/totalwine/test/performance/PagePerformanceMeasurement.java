package com.totalwine.test.performance;
/*
 *  * Access site URLs and capture performance metrics via YSlow/Pagespeed via HAR files
 */

import java.io.*;
import java.lang.InterruptedException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class PagePerformanceMeasurement  {
    public static void main(String[] args) throws IOException, InterruptedException {
        FirefoxProfile profile = new FirefoxProfile();

        File firebug = new File("C:\\Users\\rsud\\Downloads\\firebug-2.0.12-fx.xpi");
        File netExport = new File("C:\\Users\\rsud\\Downloads\\netExport-0.8.xpi");
        profile.addExtension(firebug);
        profile.addExtension(netExport);
        profile.setPreference("app.update.enabled", false);

        String domain = "extensions.firebug.";

        profile.setPreference(domain + "currentVersion", "2.0");
        profile.setPreference(domain + "allPagesActivation", "on");
        profile.setPreference(domain + "defaultPanelName", "net");
        profile.setPreference(domain + "net.enableSites", true);

        profile.setPreference(domain + "netexport.alwaysEnableAutoExport", true);
        profile.setPreference(domain + "netexport.showPreview", false);
        profile.setPreference(domain + "netexport.defaultLogDir", "C:\\Downloads\\_har\\");

        WebDriver driver = new FirefoxDriver(profile);
        Thread.sleep(5000);
        driver.get("http://www.totalwine.com");
        driver.get("http://www.totalwine.com/wine/white-wine/c/000002");
        driver.quit();
    }
}
