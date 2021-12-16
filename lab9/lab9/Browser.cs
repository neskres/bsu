using OpenQA.Selenium.Chrome;
using System;
using System.Collections.Generic;
using System.Text;

namespace lab9
{
    public class Browser
    {
        public static ChromeDriver driver;
        public void InitBrowser()
        {
            driver = new ChromeDriver();
        }
        public void CloseBrowser()
        {
            driver.Quit();
        }
        public void OpenURL(string URL)
        {
            driver.Navigate().GoToUrl(URL);
        }
    }
}
