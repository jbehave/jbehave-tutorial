Scenario:

Given a stock exchange EXCH1
Given a resource root directory classpath:org/jbehave/web/examples/trader/steps/data
When the stock exchange is opened
Then the stock exchanges opened are as contained in STOCK-EXCHANGES.txt

Scenario:

Given a threshold of 10.0
When the stock is traded at 5.0
Then the alert status should be OFF
When the stock is traded at 11.0
Then the alert status should be ON
