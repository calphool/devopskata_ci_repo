# feature/posttest.feature

Feature: PostTest
As a product owner
I want to be able to convert integer values to text
So that I actually have something usable to provide to customers

Scenario: User tries to use the input fields to convert a number to text
Given the user has opened a browser
When the user goes to our URL
And he types "12355"
And he clicks "submit"
Then he will see "twelve thousand three hundred fifty five" on the next page
