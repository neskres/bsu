using NUnit.Framework;

namespace lab9
{
    public class Tests : Browser
    {
        [SetUp]
        public void Setup()
        {
            InitBrowser();
            OpenURL("https://mishka.travel/");
        }

        [TearDown]
        public void TearDown()
        {
            CloseBrowser();
        }


        [Test]
        public void Test1()
        {
            var commands = new Commands();
            commands.InputText("search-main-panel__input", "Париж");
            commands.Click("search-main-panel__button");
            Assert.IsTrue(commands.FindElement("node-item__img").Displayed);

        }
    }
}