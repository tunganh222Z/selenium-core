package config;

import uiEngine.enums.FileType;
import utils.Supporter;

import java.io.IOException;
import java.util.Properties;

/**
 * ĐẦU BÀI: XÂY DỰNG ConfigurationManager MẠNH MẼ
 * Mục tiêu: Viết class ConfigurationManager (nằm trong thư mục com.config) có nhiệm vụ cung cấp mọi thông số cấu hình cho toàn bộ framework.
 * Class này phải hoạt động như một "Single Source of Truth" (Nguồn chân lý duy nhất) về cấu hình.
 * Nơi đọc các biến môi trường (Environment Variables) hoặc file properties/yaml được truyền từ dự án con.
 * Hỗ trợ đa môi trường (Multi-environment): Framework phải có khả năng chạy trên các môi trường khác nhau (dev, staging, prod) mà không cần sửa code.
 *
 * Hỗ trợ nhiều nguồn dữ liệu (Multiple Sources): Cấu hình có thể đến từ:
 * File properties (.properties).
 * Tham số Command Line của Maven (VD: mvn test -Dbrowser=firefox).
 * Biến môi trường của Hệ điều hành (OS Environment Variables).
 *
 * Cơ chế ghi đè (Override Priority): Nếu cùng một key (ví dụ: browser) xuất hiện ở nhiều nơi, phải tuân thủ thứ tự ưu tiên sau (từ cao xuống thấp):
 * Top 1: Maven Command Line (System Properties).
 * Top 2: Cấu hình theo môi trường (VD: config-staging.properties).
 * Top 3: Cấu hình mặc định (VD: config-default.properties).
 * Kiểu dữ liệu an toàn (Type Safety): Không trả về Object hay String chung chung cho mọi thứ. Phải có các hàm trả về đúng kiểu dữ liệu (int cho timeout, boolean cho headless).
 */
public class ConfigReader {
    private static Properties prop = new Properties();
    private static Properties sysProps = System.getProperties();

    static {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            prop.load(Supporter.readResourceStreamFile(classLoader, FileType.DEFAULT.getFileName()));
            String env = System.getProperty("env", "dev").toUpperCase();
            prop.load(Supporter.readResourceStreamFile(classLoader,
                    FileType.valueOf(env).getFileName()));

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static String get(String key) {
        String value = sysProps.getProperty(key);

        if (value != null) {
            return value;
        }
        return prop.getProperty(key);
    }

    public static int getInt(String key) {
        String value = get(key);

        if (value != null) {
            return Integer.parseInt(value);
        }
        throw new RuntimeException("Config key does not exists : " + key);
    }

    public static boolean getBoolean(String key) {
        String value = get(key);

        if (value != null) {
            return Boolean.parseBoolean(key);
        }
        return false;
    }
}
