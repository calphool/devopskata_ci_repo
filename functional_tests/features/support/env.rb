require 'rubygems'
require 'watir-webdriver'
require 'headless'


if ENV['APPURL']
    puts "APPURL = #{ENV['APPURL']}"
else
    fail!(raise(ArgumentError.new('APPURL was not passed as an ENV parm.')))
end


if ENV['HEADLESS']
  puts "Headless mode"
  headless = Headless.new
  headless.start
  puts "creating browser"
  browser = Watir::Browser.new :firefox

  at_exit do
    puts "closing browser"
    browser.close
    puts "destroying headless object"
    headless.destroy
  end
else
  puts "creating browser"
  browser = Watir::Browser.new :firefox
  at_exit do
    puts "closing browser"
    browser.close
  end
end

Before do
#   puts "making @browser global"
   @browser = browser
end
