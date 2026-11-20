# ta_training-java

## WebDriver – Practice Task 2: Locators

Test case: **"Validate that the user can search for a position based on criteria"** on https://www.epam.com/

Code: `src/test/java/com/epam/training/radu_andrei_chelu/webdriver/practice_task2/`
(Page Object Pattern: `BasePage` + one class per page, `JobSearchTest` is a parameterized JUnit 5 test.)

Run:
```
mvn test -Dtest='com.epam.training.radu_andrei_chelu.webdriver.practice_task2.**'
```

### Locator Mapping Table

| # | Locator type | Element | Locator (Java) | Page class |
|---|---|---|---|---|
| 1 | ID | "Accept all" button in the cookie banner | `By.id("onetrust-accept-btn-handler")` | `BasePage` |
| 1 | ID | Cookie banner (waited to disappear) | `By.id("onetrust-banner-sdk")` | `BasePage` |
| 2 | Name | "Keywords" input | `@FindBy(name = "search")` | `JobSearchPage` |
| 3 | ClassName | "Location" dropdown | `@FindBy(className = "dropdown__control")` | `JobSearchPage` |
| 4 | TagName | Job description page content | `By.tagName("body")` | `JobDetailsPage` |
| 5 | LinkText | "Careers" link | `By.linkText("Careers")` | `HomePage` |
| 6 | PartialLinkText | "Start your search here" button | `By.partialLinkText("START YOUR SEARCH")` | `CareersPage` |
| 7 | CSS Selector | Job titles in the search results | `@FindBy(css = "[data-testid='job-card-link']")` | `JobSearchPage` |
| 8 | XPath (relative) | "Search" button | `@FindBy(xpath = "//form//button[@type='submit']")` | `JobSearchPage` |
| 9 | XPath with operators / functions | "All available countries" option | `By.xpath("//div[contains(@class,'dropdown__option') and normalize-space()='All available countries']")` | `JobSearchPage` |
| 9 | XPath with operators / functions | Results counter ("Viewing ... jobs found") | `By.xpath("//div[not(*) and contains(., 'jobs found')]")` | `JobSearchPage` |
| 10 | XPath with axes | "Remote" checkbox (label next to the hidden input) | `@FindBy(xpath = "//input[@name='vacancy_type-Remote']/following-sibling::label")` | `JobSearchPage` |

### Differences between the task description and the current website

- **"View and apply" no longer exists.** The result card only has an "Apply" `<button>` (not a link), so the job page is opened by clicking the job title.
  PartialLinkText is used for "Start your search here" instead.
- **"All Locations" is called "All available countries"** and is selected by default.
- **Remote is selected before the keyword.** The Remote filter is applied instantly and re-renders the search form, which clears the Keywords field.
- **"Latest" job = first result.** The results page has no sorting option and shows no dates, so the first listed job is used.
- **Cookie banner appears twice.** The job search runs on `careers.epam.com` (a different domain from `www.epam.com`), where the consent banner may show up again, so it is accepted there too when it is displayed.
- **Possible intermittent failures.** The careers.epam.com job API sometimes does not respond (the page stays in its loading state), which makes the test time out.
- `"START YOUR SEARCH"` is uppercase because PartialLinkText matches the rendered text (CSS `text-transform`).
