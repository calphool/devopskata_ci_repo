Given(/^the user has opened a browser$/) do
  puts "...browser opened by env.rb..."
end

When(/^the user goes to our URL$/) do
  @browser.goto ENV['APPURL']
end

Then(/^he will see "(.*?)"$/) do |arg1|
  @browser.text.include? "#{arg1}"
end
