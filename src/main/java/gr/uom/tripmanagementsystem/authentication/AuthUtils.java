package gr.uom.tripmanagementsystem.authentication;

import java.util.Base64;

public class AuthUtils {
    public static String[] extractCredentials(String authHeader) {
        if (authHeader == null || !authHeader.toLowerCase().startsWith("basic ")) {
            throw new IllegalArgumentException("Invalid Authorization header");
        }

        String base64Credentials = authHeader.substring("Basic ".length()).trim();
        String credentials = new String(Base64.getDecoder().decode(base64Credentials));

        String[] parts = credentials.split(":", 2);

        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        return parts;
    }
}
