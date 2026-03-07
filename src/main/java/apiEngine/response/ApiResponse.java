package apiEngine.response;

import io.restassured.response.Response;

/**
 * 📝 ĐẦU BÀI 8: THIẾT KẾ ApiResponse (CÁI TÚI HỨNG CHIẾN LỢI PHẨM)
 * Mục tiêu: Xây dựng class ApiResponse đóng vai trò là một Wrapper (lớp vỏ bọc) bao quanh đối tượng Response gốc của RestAssured.
 * Class này cung cấp các tiện ích để kiểm tra (Verify) và trích xuất dữ liệu (Extract) cực nhanh.
 *
 * Yêu cầu nghiệp vụ (Business Requirements):
 * Lưu trữ Response gốc: Class phải nhận vào đối tượng Response thông qua Constructor và lưu giữ nó.
 * Kiểm tra Status Code (Fluent Design): Viết hàm verifyStatusCode(int expectedCode) để so sánh mã lỗi HTTP. Nếu sai, ném ra lỗi có thông báo rõ ràng (ví dụ: "Kỳ vọng status code là 200 nhưng nhận được 404"). Hàm này nên trả về chính đối tượng ApiResponse (return this;) để hỗ trợ viết code dạng chuỗi (Method Chaining).
 *
 * Trích xuất dữ liệu bằng JsonPath: Viết hàm getValue(String jsonPath) để lấy ra một giá trị bất kỳ trong cục JSON trả về (ví dụ lấy "token", lấy "data.user.id"). Nên dùng Generics (<T>) để ép kiểu tự động, giúp dự án con không phải ép kiểu thủ công ((String), (Integer)).
 *
 * Lấy toàn bộ Response gốc: Trong trường hợp dự án con cần dùng các tính năng nâng cao của RestAssured mà Core chưa bọc hết, phải cung cấp hàm getRawResponse() để họ lấy lại object gốc.
 *
 * Đầu vào giả định (Input):
 *
 * Lệnh từ class Test: ApiResponse res = api.requestGet("/users/1");
 *
 * Đầu ra mong đợi (Expected Output):
 * Khả năng viết test case "chuẩn bài" như sau:
 */
public class ApiResponse {
    private Response response;
    public ApiResponse(Response response) {
        this.response = response;
    }

    public ApiResponse verifyStatusCode(int expectedCode) {
        int actualCode = response.getStatusCode();

        if (actualCode != expectedCode) {
            throw new AssertionError("❌ API FAILED! Expected Status: " + expectedCode + ", but got: " + actualCode +
                    ". Response Body: " + response.getBody().asString());
        }
        return this;
    }

    public <T> T getValue(String jsonPath) {
        try {
            return response.jsonPath().get(jsonPath);
        } catch (Exception e) {
            throw new RuntimeException("❌ Wrong JsonPath: " + jsonPath, e);
        }
    }

    public Response getResponse() {
        return this.response;
    }
}
