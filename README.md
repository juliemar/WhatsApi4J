WhatsApi4J
==========

[![Donate](https://www.paypalobjects.com/en_US/i/btn/btn_donate_LG.gif)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=7S5H6Q2Y7ZK3Q)

Java adaptation of PHP WA API by venomous0x.

Original PHP version is found here: https://github.com/venomous0x/WhatsAPI

## Testing

There is an example command line application which currently has 3 supported functions:
	* request - Requests an SMS for registration
	* register - Registers a code received by SMS
	* send - Sends a message to a user

You can run the example with:
$ java -cp target/dependency/*:target/whatsapi4j-1.0.0-SNAPSHOT.jar net.sumppen.whatsapi4j.example.ExampleApplication 358401122333 'mypassword' 'mytestapplication' 'My Test Account'

If you want to request an SMS or register a code, you need to leave the password field blank 

## License:
MIT License:

        Copyright (c) <2014> Kim Lindberg <kim@sumppen.net>

        Permission is hereby granted, free of charge, to any person obtaining a copy of this
        software and associated documentation files (the "Software"), to deal in the Software
        without restriction, including without limitation the rights to use, copy, modify,
        merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
        permit persons to whom the Software is furnished to do so, subject to the following
        conditions:

        The above copyright notice and this permission notice shall be included in all
        copies or substantial portions of the Software.

        THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
        INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR
        A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
        HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
        CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
        OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.



