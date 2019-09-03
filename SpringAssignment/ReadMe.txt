This is a SpringBoot Project created for "Number to Word Converted" implemented with Springboot
Following are the two functionalities

i. Single number input
url: http://localhost:8081/api/num/123
output: {"num":"123","word":"one hundred twenty three"}

ii. Multiple number input separated by :
url: http://localhost:8081/api/nums/123:987654321
output: [{"num":"123","word":"one hundred twenty three"},{"num":"987654321","word":"nine hundred eighty seven million six hundred fifty four thousand three hundred twenty one"}]

Implemented with UnitTestCases for the methods of WebController
