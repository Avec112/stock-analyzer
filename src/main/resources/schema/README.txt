1. Use Yahoo Query Language (YQL) API to get some JSON. 
   Like https://developer.yahoo.com/yql/console/?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%3D%22aapl%22&env=store://datatables.org/alltableswithkeys#h=SELECT+*+FROM+yahoo.finance.incomestatement+WHERE+symbol+in+%28%22aapl%22%2C%22T%22%29
2. Create JSON Schema from the JSON files http://jsonschema.net/
3. Generate Java objects that mappes to JSON. Use something like https://github.com/joelittlejohn/jsonschema2pojo/wiki/Getting-Started
4. Add generated source directory to build source path with build-helper-maven-plugin