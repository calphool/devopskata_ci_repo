Given(/^the user has opened a browser$/) do
  #puts "...browser opened by env.rb..."
end

When(/^the user goes to our URL$/) do
  puts "navigating to #{ENV['APPURL']}"
  @browser.goto ENV['APPURL']
end

Then(/^he will see "(.*?)"$/) do |arg1|
  Watir::Wait.until(timeout=12,message="failed waiting for text to appear") { @browser.text.include? "#{arg1}" }
end
