# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Run

This is a Spring Boot 2.7.18 project using Maven. A Maven wrapper (`mvnw`) is committed.

```bash
# Build (compile + test)
./mvnw clean package

# Compile only
./mvnw clean compile

# Run all tests
./mvnw test

# Run a single test class or method
./mvnw test -Dtest=ClassName
./mvnw test -Dtest=ClassName#methodName

# Run the app (defaults to port 8080; actuator on 8081)
./mvnw spring-boot:run
```

Lombok and MapStruct are both annotation processors. If the IDE reports missing generated code or unresolved methods, run `./mvnw compile` to regenerate annotation-processor output, then refresh the project.

## Architecture

### Package layout

The source root is `tk.yuqi.tools.tools` (the double-`tools` is intentional — the artifact is named `tools` inside `tk.yuqi.tools`).

| Package | Purpose |
|---|---|
| `handler/` | Annotation-based task dispatch system |
| `statusmachine/` | Custom state-machine / rule engine |
| `copy/` | Reflection-based bean copy with pluggable strategies |
| `exception/` | i18n-backed error messages with SPI extensibility |
| `mybatis/` | MyBatis Plus example CRUD (City domain) |
| `reactor/` | Project Reactor / Flux experiments |
| `pool/` | Thread-pool usage examples |
| `utils/` | MapStruct converter example, misc utilities |
| `model/` | Shared DTOs (Student, NewStudent) |
| `rest/` | Basic Spring MVC `@RestController` example |

### Handler / task-dispatch system

A zero-config task dispatcher: at startup `HandlerRegister` (a `BeanPostProcessor`) scans all beans annotated with `@Handler` and registers them by a composite key (`group#jobName#bizKey`). `TaskExecutor` looks up the matching `TaskHandler` and delegates execution. To add a new handler, implement `TaskHandler` and annotate with `@Handler(group="...", jobName="...")`.

### Status machine

A lightweight rule engine. Implement `StatusMachineInit` to define a state machine: supply a `StatusMachineMatcher` (decides which business contexts this machine applies to) and an `init()` method that builds `StatusValidator` chains. Validators are composable via `StatusUtils.and()` / `StatusUtils.or()` and can reference Spring beans by name with `StatusUtils.bean("beanName")`. Call `StatusUtils.isStatusPass(ctx, newStatus)` to evaluate. `AppStatusMachine` shows usage.

### Bean copy

`BeanCopyUtil` provides reflection-based property copying between Java beans. Two built-in modes: `COPY_NOT_NULL_MODE` (skip nulls) and `OVER_WRITE_MODE` (overwrite all). The `CopyStrategy` interface allows custom matching logic. Prefer MapStruct (`StudentConverter` is an example) for compile-time mapping; the reflection-based copy utility is for dynamic cases.

### Exception / error messages

Exceptions carry i18n error codes backed by `ResourceBundleMessageLoader` which loads `i18n/errors_*.properties`. `ErrorMessage.of(code)` creates a localisable error message. `ErrorMessageException` and `TkYuException` wrap it as a runtime exception. `Asserts` offers assertion-style helpers (`isNull`, `isBlank`, `equalsTrue`, `equalsFalse`) that throw on failure. Readable-code resolution and displayed-message resolution use Java `ServiceLoader` SPI — plug in implementations of `ReadableErrorCodeResolver` and `DisplayedErrorMessageResolver`.

### MyBatis Plus

MyBatis Plus is used with annotation-based mappers (see `CityDao`). The `@MapperScan` base package is `tk.yuqi.tools.tools`. Domain entities extend `BaseFeatrue` which serializes a `Map<String,String>` into a JSON `feature` column via FastJSON.

## Configuration

- **application.properties**: DB connection (`zhizhou` on localhost:3306) and MyBatis type-aliases package. Replace credentials for your environment.
- **application.yaml**: Actuator exposed on port 8081 with all endpoints enabled (`/actuator` base path).
- `WebMvcConfigurerAdapter` enables custom content negotiation (file-extension-based, e.g., `.json` / `.xml`).

## Key dependencies

Spring Boot 2.7.18, MyBatis Plus 3.5.4.1, MapStruct 1.6.3, Lombok 1.18.36, RocketMQ Spring Boot Starter 2.2.3, FastJSON 2.0.42, Guava 25.1, Reactor Core 3.4.23, Apache Commons Lang3 / IO / Codec.

## Code style

- Java classes use Chinese-language Javadoc comments with the pattern `类 Xxx 的实现描述：Xxx`.
- Chinese is used for error message properties (`errors_zh_CN.properties`, `errors_readable.properties`).
- Lombok `@Data` and `@Slf4j` are used throughout.
