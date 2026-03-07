# Hybrid Automation Core Engine (UI + API)

Một thư viện nền tảng (Core Framework) chuyên biệt cho kiểm thử tự động, tích hợp Selenium WebDriver và RestAssured. Thiết kế chú trọng vào tính đóng gói (Encapsulation), an toàn luồng (Thread-safety) và khả năng mở rộng thông qua các Interface.

## 📑 Table of Contents
1. [Architecture Overview](#-architecture-overview)
2. [Prerequisites](#-prerequisites)
3. [Installation](#-installation)
4. [Configuration](#-configuration)
5. [Usage: UI Engine](#-usage-ui-engine)
6. [Usage: API Engine](#-usage-api-engine)
7. [Design Patterns Used](#-design-patterns-used)

---

## 🏗 Architecture Overview

Hệ thống được thiết kế theo nguyên lý Framework-Agnostic (không phụ thuộc TestNG/JUnit/Cucumber), đóng vai trò là Middleware giữa Testing Framework và Drivers.

```text
automation-core/
├── core.ui/                  # UI Automation Engine (Selenium)
│   ├── driver/               # DriverFactory, TargetFactory, ContextManager
│   ├── actions/              # BaseActions (Wrapper layer)
│   ├── strategy/             # WaitStrategy, LocatorStrategy
│   └── listeners/            # EventFiring, ScreenshotBus (Pub-Sub)
│
├── core.api/                 # API Automation Engine (RestAssured)
│   ├── builder/              # CoreRequestBuilder (Spec builders, Auth headers)
│   ├── client/               # ApiClient (HTTP Executions)
│   ├── response/             # ApiResponse (Validation & JsonPath extractors)
│   └── filter/               # RestLoggerFilter (Auto-logging interceptor)
│
└── core.config/              # Properties Reader & Environment Management
```
⚙️ Prerequisites
Java: 17+

Maven: 3.8+

Selenium: 4.x

RestAssured: 5.x

📦 Installation
Khai báo dependency trong pom.xml của dự án Client để kéo thư viện Core này thông qua JitPack:

XML
<repositories>
<repository>
<id>jitpack.io</id>
<url>[https://jitpack.io](https://jitpack.io)</url>
</repository>
</repositories>

<dependency>
    <groupId>io.github.tunganh222Z</groupId>
    <artifactId>automation-core</artifactId>
    <version>1.0.0</version>
</dependency>
🛠 Configuration
Dự án Client cần cung cấp file cấu hình tại src/test/resources/config-default.properties.
Các key bắt buộc:

Properties
# UI Configuration
browser=chrome
timeout=30
headless=false

# API Configuration
apiUrl=[https://api.yourdomain.com](https://api.yourdomain.com)
🖥 Usage: UI Engine
1. Khởi tạo và Quản lý Luồng (Thread-Safe Context)
   Core tự động cấp phát và cô lập WebDriver cho từng luồng (ThreadLocal). Không cần truyền đối tượng driver qua lại giữa các class.

Java
// Tại @Before method của dự án Client
DriverFactory.initDriver();

// Lấy context an toàn ở bất kỳ đâu
WebDriver driver = DriverFactory.getDriver();
2. Thao tác với BaseActions (Wrapper)
   Tuyệt đối không sử dụng driver.findElement(). Mọi thao tác đều phải đi qua BaseActions để kế thừa cơ chế Auto-Wait và JS Fallback.

Java
BaseActions.click(By.id("login-btn"));
BaseActions.setText(By.name("username"), "admin");
3. Đăng ký Screenshot Event
   Core phát sự kiện chụp ảnh độc lập. Dự án Client đăng ký Listener để đính kèm vào Report của riêng họ:

Java
ScreenshotBus.register(screenshotBytes -> {
Allure.addAttachment("Error Screenshot", "image/png", new ByteArrayInputStream(screenshotBytes), ".png");
});
🌐 Usage: API Engine
1. Thực thi HTTP Request cơ bản
   ApiClient tự động đọc apiUrl từ cấu hình, gắn bộ lọc Log (RestLoggerFilter) và trả về đối tượng ApiResponse chuẩn hóa.

Java
// 1. Khởi tạo Client với cấu hình mặc định
ApiClient api = new ApiClient(new CoreRequestBuilder());

// 2. Gửi request và nhận kết quả
ApiResponse response = api.requestGet("/users/1");
2. Fluent Validation & Data Extraction
   Chuỗi method hỗ trợ verify Status Code và bóc tách JSON bằng Generics cực kỳ ngắn gọn.

Java
// Xác thực status 200 và lấy ID
int userId = api.requestPost("/users", payload)
.verifyStatusCode(200)
.getValue("data.id");
3. Custom Request Builder (Tiêm Header/Token)
   Dự án Client có thể tiêm cấu hình riêng (như Bearer Token) bằng cách tạo class implement IRequestBuilder và truyền vào Client.

Java
CoreRequestBuilder builderWithAuth = new CoreRequestBuilder(new AccessToken("your_token_here"));
ApiClient secureApi = new ApiClient(builderWithAuth);
🧠 Design Patterns Used
Kiến trúc Lõi áp dụng triệt để các Design Pattern để giải quyết bài toán Framework mở rộng:

Singleton & ThreadLocal (Context Pattern): Quản lý luồng thực thi, đảm bảo Thread-Safety cho WebDriver và Test State.

Factory Method: Khởi tạo WebDriver thông qua TargetFactory dựa trên cấu hình (Local/Remote).

Builder & Fluent Interface: Cấu hình API Requests và chuỗi các hàm Verify trong ApiResponse.

Observer (Publish-Subscribe): ScreenshotBus phát sự kiện chụp màn hình mà không cần biết ai đang nhận nó.

Wrapper (Facade): Bọc toàn bộ các hàm Selenium gốc vào BaseActions để ẩn đi sự phức tạp của cơ chế Synchronizations.