# CDI-TestFramework-Gaming-BrowserStack

[TestNG](http://testng.org) Integration with BrowserStack.

![BrowserStack Logo](https://www.browserstack.com/images/static/header-logo.jpg)


### Run sample build

- Clone the repository
- Replace YOUR_USERNAME and YOUR_ACCESS_KEY with your BrowserStack access credentials in browserstack.yml.
- Install dependencies `mvn compile`
- To run the test suite having cross-platform with parallelization, run `mvn test -P sample-test`