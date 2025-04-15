package SIKULI;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.WebDriver;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
public class SikuliExDeskTopApplications
{
	public static void main(String[] args) throws Throwable {

		//WebDriver driver;

		Screen scr = new Screen();

		Pattern minimize = new Pattern("C:\\Users\\binoy\\OneDrive - Moe, Inc\\Desktop\\QSPIDER\\Selenium Basic\\ECLIPSE WORKSPACE\\ADVANCE_SELENIUM_E19\\src\\test\\resources\\Screenshots\\eclipseMinimise.png");
		scr.click(minimize);
		
		Pattern resizeFromTaskBar=new Pattern("C:\\Users\\binoy\\OneDrive - Moe, Inc\\Desktop\\QSPIDER\\Selenium Basic\\ECLIPSE WORKSPACE\\ADVANCE_SELENIUM_E19\\src\\test\\resources\\Screenshots\\eclipseTaskBarIcon.png");
		//Thread.sleep(3500);
		scr.click(resizeFromTaskBar);
		
		Pattern searchBar = new Pattern("C:\\Users\\binoy\\OneDrive - Moe, Inc\\Desktop\\QSPIDER\\Selenium Basic\\ECLIPSE WORKSPACE\\ADVANCE_SELENIUM_E19\\src\\test\\resources\\Screenshots\\searchBarIcon.png");
		//Thread.sleep(3500);
		scr.type(searchBar, "NotePad");
		 
		Pattern clickNotepad=new Pattern("C:\\Users\\binoy\\OneDrive - Moe, Inc\\Desktop\\QSPIDER\\Selenium Basic\\ECLIPSE WORKSPACE\\ADVANCE_SELENIUM_E19\\src\\test\\resources\\Screenshots\\NOtePad.png");
		//Thread.sleep(3500);
		scr.click(clickNotepad);
		
		Pattern closeNotepad=new Pattern("C:\\Users\\binoy\\OneDrive - Moe, Inc\\Desktop\\QSPIDER\\Selenium Basic\\ECLIPSE WORKSPACE\\ADVANCE_SELENIUM_E19\\src\\test\\resources\\Screenshots\\notepadClose.png");
		//Thread.sleep(3500);
		scr.click(closeNotepad);
		Thread.sleep(2500);
		scr.type(searchBar, "Excel");
		 		
		Robot rob = new Robot();
		rob.keyPress(KeyEvent.VK_ENTER);
		rob.keyRelease(KeyEvent.VK_ENTER);

		 Thread.sleep(2000); 
	    Pattern excel = new  Pattern("C:\\Users\\binoy\\OneDrive - Moe, Inc\\Desktop\\QSPIDER\\Selenium Basic\\ECLIPSE WORKSPACE\\ADVANCE_SELENIUM_E19\\src\\test\\resources\\Screenshots\\BlankWorkBook.png"); 
		scr.type(excel, "Hello good evening");
		Thread.sleep(1500);
		Pattern closeExcel = new  Pattern("C:\\Users\\binoy\\OneDrive - Moe, Inc\\Desktop\\QSPIDER\\Selenium Basic\\ECLIPSE WORKSPACE\\ADVANCE_SELENIUM_E19\\src\\test\\resources\\Screenshots\\ExcelClose.png");
		scr.click(closeExcel);
		Pattern dontSaveExcel = new  Pattern("C:\\Users\\binoy\\OneDrive - Moe, Inc\\Desktop\\QSPIDER\\Selenium Basic\\ECLIPSE WORKSPACE\\ADVANCE_SELENIUM_E19\\src\\test\\resources\\Screenshots\\dontSave.png");
		
		scr.click(dontSaveExcel);
		
		

	}

}
