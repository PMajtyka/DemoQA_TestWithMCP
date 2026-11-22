package org.example.demoqa.bookstore;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginTest {
    private static final String LOGIN_URL = "https://demoqa.com/login";

    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;
    private LoginPage loginPage;
    private APIRequestContext apiContext;
    private String testUsername;
    private String testPassword;
    private String testUserId;

    @BeforeEach
    void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new com.microsoft.playwright.BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        page = context.newPage();
        page.navigate(LOGIN_URL);
        loginPage = new LoginPage(page);
        loginPage.hideFloatingAds();
        apiContext = playwright.request().newContext();
    }

    @AfterEach
    void tearDown() {
        try {
            if (testUserId != null) {
                deleteTestUser();
            }
        } finally {
            try {
                if (apiContext != null) apiContext.dispose();
            } finally {
                try {
                    if (page != null) page.close();
                } finally {
                    try {
                        if (context != null) context.close();
                    } finally {
                        try {
                            if (browser != null) browser.close();
                        } finally {
                            if (playwright != null) playwright.close();
                        }
                    }
                }
            }
        }
    }

    @Test
    @DisplayName("A registered user can log in and reach the profile")
    void testSuccessfulLogin() {
        createTestUser();

        loginPage.login(testUsername, testPassword);

        page.waitForURL("**/profile");
        assertEquals("https://demoqa.com/profile", page.url());
        ProfilePage profilePage = new ProfilePage(page);
        assertThat(profilePage.username).hasText(testUsername);
        assertEquals(testUsername, profilePage.getUsername());
        assertThat(profilePage.logoutButton).isVisible();
    }

    @Test
    @DisplayName("A wrong password and an unknown username show the login error")
    void testLoginWithInvalidCredentials() {
        createTestUser();
        loginPage.login(testUsername, "WrongPassword123!");
        assertThat(loginPage.errorMessage).hasText("Invalid username or password!");
        assertEquals(LOGIN_URL, page.url());

        loginPage.login("unknown-" + UUID.randomUUID(), testPassword);
        assertThat(loginPage.errorMessage).hasText("Invalid username or password!");
        assertEquals(LOGIN_URL, page.url());
    }

    @Test
    @DisplayName("Submitting empty fields is blocked by required-field validation")
    void testValidationForEmptyFields() {
        loginPage.loginButton.click();

        assertTrue(loginPage.isUsernameInvalid(), "Username is a required field.");
        assertTrue(loginPage.isPasswordInvalid(), "Password is a required field.");
        assertEquals("", loginPage.getErrorMessage(),
                "Client-side validation should prevent an authentication request.");
        assertEquals(LOGIN_URL, page.url());
    }

    private void createTestUser() {
        testUsername = "pwlogin" + UUID.randomUUID().toString().replace("-", "");
        testPassword = "QaTest!2026Pass";

        APIResponse response = apiContext.post("https://demoqa.com/Account/v1/User",
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setData(Map.of("userName", testUsername, "password", testPassword)));
        assertEquals(201, response.status(), "DemoQA should create the temporary test account.");
        testUserId = jsonString(response.text(), "userID");
        assertTrue(testUserId != null && !testUserId.isBlank(),
                "The account-creation response should include a user ID.");
    }

    private void deleteTestUser() {
        APIResponse tokenResponse = apiContext.post("https://demoqa.com/Account/v1/GenerateToken",
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setData(Map.of("userName", testUsername, "password", testPassword)));
        assertEquals(200, tokenResponse.status(), "DemoQA should issue a token to the temporary user.");
        String token = jsonString(tokenResponse.text(), "token");
        assertTrue(token != null && !token.isBlank(),
                "The token response should include an access token.");

        APIResponse deleteResponse = apiContext.delete(
                "https://demoqa.com/Account/v1/User/" + testUserId,
                RequestOptions.create().setHeader("Authorization", "Bearer " + token));
        assertEquals(204, deleteResponse.status(), "The temporary test account should be removed.");
    }

    private String jsonString(String json, String key) {
        Matcher matcher = Pattern.compile("\"" + Pattern.quote(key) + "\"\\s*:\\s*\"([^\"]*)\"")
                .matcher(json);
        return matcher.find() ? matcher.group(1) : null;
    }
}
