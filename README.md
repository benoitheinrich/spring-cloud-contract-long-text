# spring-cloud-contract-long-text

A sample project using spring cloud contract and openapi spec that check a long text on multi lines.

# Update from 2019-03-28

When doing a `mvn clean install`, the server module will fail to compile with the following error:
```
INFO] -------------------------------------------------------------
[ERROR] COMPILATION ERROR :
[INFO] -------------------------------------------------------------
[ERROR] /.../spring-cloud-contract-long-text/server/target/generated-sources/openapi/src/main/java/com/bh/sample/StuffApi.java:[53,123] element value must be a constant expression
[ERROR] /.../spring-cloud-contract-long-text/server/target/generated-sources/openapi/src/main/java/com/bh/sample/StuffApi.java:[53,200] element value must be a constant expression
[ERROR] /.../spring-cloud-contract-long-text/server/target/generated-sources/openapi/src/main/java/com/bh/sample/StuffApi.java:[53,259] element value must be a constant expression
[ERROR] /.../spring-cloud-contract-long-text/server/target/generated-sources/openapi/src/main/java/com/bh/sample/StuffApi.java:[53,337] element value must be a constant expression
[INFO] 4 errors
```

The error is caused by the generated class which define the following method:
```
    default ResponseEntity<WorkedOperationStatus> getStuff(@NotNull @ApiParam(value = "", required = true, defaultValue = null) @Valid @RequestParam(value = "username", required = true, defaultValue=null) String username,@ApiParam(value = "", defaultValue = null) @Valid @RequestParam(value = "filename", required = false, defaultValue=null) String filename) {
        return getDelegate().getStuff(username, filename);
    }
```

The issue is due to the `defaultValue = null` which doesn't compile, in order to compile it would have to use a `String` value.
Using version 3.3.3 of openapi fixes this problem as it generates the following: `defaultValue = "null"`.

This issue is reported as https://github.com/OpenAPITools/openapi-generator/issues/2540.

# Update from 2019-01-03

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
