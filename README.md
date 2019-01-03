# spring-cloud-contract-long-text

A sample project using spring cloud contract and openapi spec that check a long text on multi lines.

When doing a `mvn clean install`, the server module will fail with the following error:

```
org.opentest4j.AssertionFailedError: 
Expecting:
 <400>
to be equal to:
 <200>
but was not.
```

In the logs you'll notice the following:
```
2019-01-03 15:16:50.206  WARN 8789 --- [o-auto-1-exec-1] .w.s.m.s.DefaultHandlerExceptionResolver : Resolved [org.springframework.http.converter.HttpMessageNotReadableException: JSON parse error: Illegal unquoted character ((CTRL-CHAR, code 10)): has to be escaped using backslash to be included in string value; nested exception is com.fasterxml.jackson.databind.JsonMappingException: Illegal unquoted character ((CTRL-CHAR, code 10)): has to be escaped using backslash to be included in string value
 at [Source: (PushbackInputStream); line: 1, column: 12] (through reference chain: com.bh.sample.Workload["comment"])]

```

This seems to be caused by the multi-lines comment used in the contract that isn't properly escaped when passed to the test.
