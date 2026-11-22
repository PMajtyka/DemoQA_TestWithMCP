# DemoQA Page Object Model

This document describes the Java Playwright POMs in `src/main/java/org/example/demoqa`, mapped against the live [DemoQA site](https://demoqa.com). The homepage presents six sections; their sidebar destinations are listed below. The Book Store API (`/swagger`) is included as a sidebar destination and its own POM.

The POMs provide page locators, business actions, and state/text accessors. `BasePage` supplies shared helpers; page constructors accept a Playwright `Page`. Locators prefer accessible roles and labels where the site exposes them, and use stable placeholders or IDs for remaining controls.

## Coverage matrix

| Section | Live page | Route | POM class | Available business and retrieval methods |
|---|---|---|---|---|
| Home | Homepage | `/` | `home.HomePage` | `openElements()`, `openForms()`, `openAlertsWindows()`, `openWidgets()`, `openInteractions()`, `openBookStore()`, `section(String)`, `getSectionCount()` |
| Elements | Text Box | `/text-box` | `elements.TextBoxPage` | `submitForm(String name, String emailAddress, String address, String permanent)`, `getOutputText()` |
| Elements | Check Box | `/checkbox` | `elements.CheckBoxPage` | `expandHomeNode()`, `selectHome()`, `getSelectionText()` |
| Elements | Radio Button | `/radio-button` | `elements.RadioButtonPage` | `chooseYes()`, `chooseImpressive()`, `getSelectedAnswer()` |
| Elements | Web Tables | `/webtables` | `elements.WebTablesPage` | `openAddRecordDialog()`, `addRecord(String givenName, String familyName, String years, String emailAddress, String annualSalary, String team)`, `search(String query)`, `getTableRowsCount()` |
| Elements | Buttons | `/buttons` | `elements.ButtonsPage` | `doubleClick()`, `rightClick()`, `clickDynamicButton()`, `getClickMessages()` |
| Elements | Links | `/links` | `elements.LinksPage` | `openHomeInNewTab()`, `requestCreatedResponse()`, `requestNoContentResponse()`, `getApiResponseMessage()` |
| Elements | Broken Links - Images | `/broken` | `elements.BrokenLinksPage` | `openValidLink()`, `openBrokenLink()`, `getImageCount()`, `getBrokenImageCount()` |
| Elements | Upload and Download | `/upload-download` | `elements.UploadDownloadPage` | `upload(Path file)`, `getUploadedFilePath()`, `download()` |
| Elements | Dynamic Properties | `/dynamic-properties` | `elements.DynamicPropertiesPage` | `waitForDelayedButton()`, `clickDelayedButton()`, `hoverColorChangeButton()`, `isDelayedButtonEnabled()`, `isDelayedButtonVisible()`, `getColorChangeButtonColor()` |
| Forms | Practice Form | `/automation-practice-form` | `forms.PracticeFormPage` | `fillStudentDetails(String givenName, String familyName, String emailAddress, String phoneNumber, String address)`, `chooseGender(String gender)`, `addSubject(String subject)`, `addHobby(String hobby)`, `submitForm()`, `getSubmissionSummary()` |
| Alerts, Frame & Windows | Browser Windows | `/browser-windows` | `alerts.BrowserWindowsPage` | `openNewTab()`, `openNewWindow()`, `openMessageWindow()` |
| Alerts, Frame & Windows | Alerts | `/alerts` | `alerts.AlertsPage` | `acceptImmediateAlert()`, `acceptTimedAlert()`, `acceptConfirmation()`, `dismissConfirmation()`, `submitPrompt(String value)`, `getConfirmationResult()`, `getPromptResult()` |
| Alerts, Frame & Windows | Frames | `/frames` | `alerts.FramesPage` | `getFirstFrameText()`, `getSecondFrameText()` |
| Alerts, Frame & Windows | Nested Frames | `/nestedframes` | `alerts.NestedFramesPage` | `getParentFrameText()`, `getChildFrameText()` |
| Alerts, Frame & Windows | Modal Dialogs | `/modal-dialogs` | `alerts.ModalDialogsPage` | `openSmallModal()`, `openLargeModal()`, `closeModal()`, `getModalTitle()`, `getModalBody()`, `isModalVisible()` |
| Widgets | Accordian | `/accordian` | `widgets.AccordianPage` | `openSection(String title)`, `getExpandedSectionText()` |
| Widgets | Auto Complete | `/auto-complete` | `widgets.AutoCompletePage` | `addColor(String color)`, `chooseSingleColor(String color)`, `getMultipleColors()`, `getSingleColor()` |
| Widgets | Date Picker | `/date-picker` | `widgets.DatePickerPage` | `setDate(String date)`, `setDateAndTime(String dateTime)`, `getSelectedDate()`, `getSelectedDateAndTime()`, `isCalendarVisible()` |
| Widgets | Slider | `/slider` | `widgets.SliderPage` | `setValue(int value)`, `getValue()` |
| Widgets | Progress Bar | `/progress-bar` | `widgets.ProgressBarPage` | `startOrStop()`, `reset()`, `getProgressValue()`, `getStartStopLabel()` |
| Widgets | Tabs | `/tabs` | `widgets.TabsPage` | `selectTab(String name)`, `getSelectedTabText()`, `getSelectedTabName()` |
| Widgets | Tool Tips | `/tool-tips` | `widgets.ToolTipsPage` | `hoverButton()`, `hoverTextField()`, `getTooltipText()` |
| Widgets | Menu | `/menu` | `widgets.MenuPage` | `hoverMenuItem(String label)`, `getVisibleMenuItemsCount()` |
| Widgets | Select Menu | `/select-menu` | `widgets.SelectMenuPage` | `selectCustomOption(int selectIndex, String optionName)`, `selectOldStyleOption(String label)`, `selectCar(String value)`, `getOldStyleSelection()` |
| Interactions | Sortable | `/sortable` | `interactions.SortablePage` | `selectListView()`, `selectGridView()`, `moveListItem(int fromIndex, int toIndex)`, `getItemsCount()`, `getItemText(int index)` |
| Interactions | Selectable | `/selectable` | `interactions.SelectablePage` | `selectListItem(String label)`, `selectGridView()`, `selectListView()`, `getSelectableItemsCount()`, `getSelectedItemsCount()` |
| Interactions | Resizable | `/resizable` | `interactions.ResizablePage` | `resizeBy(int horizontalPixels, int verticalPixels)`, `getBoxDimensions()` |
| Interactions | Droppable | `/droppable` | `interactions.DroppablePage` | `selectTab(String tabName)`, `dropItem()`, `getDropTargetText()` |
| Interactions | Dragabble | `/dragabble` | `interactions.DraggablePage` | `selectTab(String tabName)`, `dragBy(int horizontalPixels, int verticalPixels)`, `getDraggableText()` |
| Book Store Application | Login | `/login` | `bookstore.LoginPage` | `login(String user, String secret)`, `openRegistration()`, `getErrorMessage()` |
| Book Store Application | Book Store | `/books` | `bookstore.BookStorePage` | `searchBooks(String query)`, `openBook(String title)`, `openLogin()`, `getBookRowsCount()`, `getBookListText()` |
| Book Store Application | Profile | `/profile` | `bookstore.ProfilePage` | `logout()`, `deleteAccount()`, `isLoginNoticeVisible()`, `getUsername()`, `getBookRowsCount()` |
| Book Store Application | Book Store API | `/swagger` | `bookstore.BookStoreApiPage` | `openAuthorizeDialog()`, `expandOperation(String method, String path)`, `getOperationCount()`, `getApiTitle()` |

`BasePage` methods inherited by all page objects: `scrollTo(Locator)` scrolls a locator into view, `frame(String selector)` returns a `FrameLocator`, and `hideFloatingAds()` injects CSS to hide common floating/ad containers. The shared protected `textOf(Locator)` helper retrieves visible element text.

## Package structure

```text
org.example.demoqa
├── alerts       # browser windows, dialogs, frames
├── bookstore    # login, books, profile, API documentation
├── common       # BasePage shared helpers
├── elements     # element demonstrations
├── forms        # practice form
├── home         # homepage section navigation
├── interactions # sortable, selectable, drag/drop, resize
└── widgets      # widget demonstrations
```

## Running the login tests

The project uses Java 21, Maven, Playwright for Java, and JUnit 5 (`pom.xml`). `src/test/java/org/example/demoqa/bookstore/LoginTest.java` covers successful login, invalid credentials, and required-field validation. Run from the project root:

1. Install Playwright's browser binaries (one time per machine):

   ```powershell
   mvn exec:java -e -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install"
   ```

2. Compile the POM source:

   ```powershell
   mvn compile
   ```

3. Run the login test class:

   ```powershell
   mvn -Dtest=LoginTest test
   ```

The successful-login and invalid-credential tests provision unique temporary users through the DemoQA account API and remove them after each test. The empty-fields test checks native required-field validation. These tests require network access to the live DemoQA site and API.

Run the entire Maven test suite with `mvn test`. Do not put account credentials in source code or commit them to the repository.
