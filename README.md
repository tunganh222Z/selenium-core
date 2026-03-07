# 🚀 Automation Core Library

A reusable Selenium automation uiEngine framework designed for scalable, parallel execution and enterprise-level test automation projects.

---

## 📦 Overview
This project provides a clean, decoupled Selenium uiEngine library including:

- Thread-safe WebDriver management
- Event-driven screenshot capturing
- Execution context per thread
- Pluggable wait strategy
- Action abstraction layer
- Logging with thread & scenario support
- Utility helper classes

The uiEngine is framework-agnostic (no dependency on Cucumber, TestNG, or JUnit).

## 🏗 Architecture
    ├── base # Base driver & page abstractions
    ├── baseAction # Action layer (click, sendKeys, getText...)
    ├── context # Thread-local execution context
    ├── strategy # Interface classes
    ├── listeners # WebDriver event listeners
    ├── config_1 # Configuration reader
    ├── utils # Common utility classes

---

## 🔥 Key Features

### Thread-Safe WebDriver

Uses `ThreadLocal<WebDriver>` to support parallel execution safely.

```java
DriverFactory.initDriver();
WebDriver driver = DriverFactory.getDriver();
```

```java
## Event-Driven Screenshot System
DriverListener
    → ScreenshotBus.publish()
    → External framework handles screenshot
        
        ## ScreenshotBus Pattern
    ScreenshotBus.register(new ScreenshotHandler() {
    @Override
    public void handle(byte[] screenshot) {
        scenario.attach(screenshot, "image/png", "Failure Screenshot");
    }
});
```
⚙️ Configuration
``
browser=chrome
timeout=30
headless=true
``
## 📥Installation
`mvn clean install`

## 📦 Installation (For Other Projects)
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

```xml
<!-- Artifact uiEngine -->
<dependency>
    <groupId>io.github.tunganh222Z</groupId>
    <artifactId>selenium-uiEngine</artifactId>
    <version>1.0.3</version>
</dependency>
```

## 🛠Requirements
    Java 17+
    Maven 3.8+
    Selenium 4+
## 🎯 Design Principles
    No test framework dependency
    Clean separation of concerns
    Thread-safe by default
    Extensible & pluggable architecture
    Event-driven design
---
