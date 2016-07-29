# feature/smoketest.feature

Feature: Poem
As a product owner
I want to show our users a poem
So that I can demo how entertaining I am

Scenario: User sees hello world and a poem
Given the user has opened a browser
When the user goes to our URL
Then he will see "Now is the time for all good men"
