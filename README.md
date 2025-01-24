# Bug Report: Incorrect HTTP Status Recorded in Micronaut Micrometer Metrics

## Summary
When running a Micronaut server with a custom event handler that maps specific exceptions to HTTP statuses other than 500, the metrics incorrectly record the status as 500. This issue arises when using versions 4.7.x and above, whereas the expected behavior was observed in version 4.6.x.

## Steps to Reproduce
1. **Set Up a Custom Event Handler**: Implement a custom event handler to map the exception `DemoException` to HTTP status 400 (Bad Request).

   Example implementation see `CustomExceptionHandler` class.

2. **Trigger the Exception**: Call a specific HTTP endpoint designed to trigger the `DemoException`. For instance, perform a GET request to `/api/error`.

   Example request:
   ```
   GET /api/error
   ```

3. **Observe Metrics**: Check the recorded metrics for `http_server_requests_seconds_count`.

## Expected Behavior
Upon invoking the endpoint that triggers the `DemoException`, the expected metric should be:
```
http_server_requests_seconds_count{exception="none",method="GET",status="400",uri="/api/error"} 1
```

## Actual Behavior
Instead of recording the expected status, the metric reflects:
```
http_server_requests_seconds_count{exception="DemoException",method="GET",status="500",uri="/api/error"} 1
```

## Additional Information
- **Previous Version**: This behavior was functioning correctly in Micronaut version 4.6.x.
- **Current Version**: The issue began occurring with the release of Micronaut version 4.7.x and continues in subsequent versions.
