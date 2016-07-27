require 'rubygems'
require 'watir-webdriver'
require 'headless'


if ENV['APPURL']
    puts "APPURL = #{ENV['APPURL']}"
else
    fail!(raise(ArgumentError.new('APPURL was not passed as an ENV parm.')))
end


browser = Watir::Browser.new :firefox

Before do
   @browser = browser
end

if ENV['HEADLESS']
  require 'headless'
  headless = Headless.new
  headless.start
  at_exit do
    headless.destroy
    browser.close
  end
else
  at_exit do
    browser.close
  end
end
