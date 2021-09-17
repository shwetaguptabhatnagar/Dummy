Feature: Login Page feature

Scenario: Login Page Title
Given user is on Login page
When  user gets the title of the page
Then page title should be "Login - My Store"

Scenario: Forgot Password Link
Given user is on Login page
Then forgot your password link should be displayed

Scenario: Login with correct credentials
Given user is on Login page
When  user enters username "shwetagupta806@gmail.com"
And user enters password "Shweta@24"
And user clicks on Login button
Then user gets the title of the page
And page title should be "My account - My Store"