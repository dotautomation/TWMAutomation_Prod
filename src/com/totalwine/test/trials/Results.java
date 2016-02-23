package com.totalwine.test.trials;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import com.totalwine.test.config.ConfigurationFunctions;


public class Results {
	
	@Test
	public void ArchiveResults() throws IOException {
		//File file = new File("C:\\Users\\rsud\\.jenkins\\jobs\\Regression Suite (Bugfix)\\htmlreports\\Bugfix_Regression_Test_Results\\BugfixTestResults.html");
		File file = new File(ConfigurationFunctions.RESULTSPATH+"ProductionTestResults.html");
		//SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String nameAppend = sdf.format(file.lastModified());
		//File archivedFile =  new File("C:\\Users\\rsud\\.jenkins\\jobs\\Regression Suite (Bugfix)\\htmlreports\\Bugfix_Regression_Test_Results\\BugfixTestResults_"+nameAppend+".html");
		File archivedFile =  new File(ConfigurationFunctions.RESULTSPATH+"ProductionTestResults_"+nameAppend+".html");
		FileUtils.copyFile(file, archivedFile);
		System.out.println("Previous results are stored as ProductionTestResults_"+nameAppend+".html");
    }
}
