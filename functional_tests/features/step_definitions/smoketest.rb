Given(/^the user has opened a browser$/) do
  puts "Code goes here to launch browser"
end

When(/^the user gots to our URL$/) do
  puts "Code goes here to navigate to url passed as launch parm"
end

Then(/^he will see "(.*?)"$/) do |arg1|
  puts "Code here to check to see if arg1 exists"
end
