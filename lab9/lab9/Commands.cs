using OpenQA.Selenium;
using OpenQA.Selenium.Support.UI;
using System;
using System.Collections.Generic;
using System.Text;

namespace lab9
{
    public class Commands : Browser
    {
        public void InputText(string className, string text)
        {
            WaitElement(className);
            driver.FindElement(By.ClassName(className)).Clear();
            driver.FindElement(By.ClassName(className)).SendKeys(text);
        }
        public void Click(string className)
        {
            WaitElement(className);
            driver.FindElement(By.ClassName(className)).Click();
        }

        public IWebElement FindElement(string className)
        {
            WaitElement(className);
            var we = driver.FindElement(By.ClassName(className));
            return we;
        }

        public void WaitElement(string className)
        {
            var wait = new WebDriverWait(driver, TimeSpan.FromSeconds(25));
            wait.Until(driver => driver.FindElement(By.ClassName(className)).Displayed);
        }

    }
}
