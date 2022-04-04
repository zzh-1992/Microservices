
> openFeign超时设置
```java
/**
 * openFeign connectTimeoutUnit and readTimeoutUnit settings
 *
 * @return Options
 */
@Bean
public Request.Options options() {
    final Request.Options options = new Request.Options(2, TimeUnit.SECONDS,
            2500, TimeUnit.MILLISECONDS,
            true);
    return options;
}
```
