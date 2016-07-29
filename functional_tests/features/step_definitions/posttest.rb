When(/^he types "([^"]*)"$/) do |arg1|
    t = @browser.text_field :name => 'thenumber'
	t.exists?
	t.set arg1
end

When(/^he clicks "([^"]*)"$/) do |arg1|
	btn = @browser.button :value => arg1
	btn.exists?
	btn.click
end

Then(/^he will see "([^"]*)" on the next page$/) do |arg1|
	  Watir::Wait.until(timeout=12,message="failed waiting for text to appear") { @browser.text.include? "#{arg1}" }
end