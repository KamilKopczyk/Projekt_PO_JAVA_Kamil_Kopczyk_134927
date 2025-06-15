package Services;

public class SessionManager {
    private static int currentUserId = 0;

    public static int getCurrentUserId() {
        return currentUserId;
    }

    public static void setCurrentUserId(int userId) {
        currentUserId = userId;
    }

    public static void clearSession() {
        currentUserId = 0;
    }
}