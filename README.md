# SignaturEE
## Very simple signature pad for JSF

Sometimes it's useful put a signature on electronic documents, particularly with mobile devices (thanks to touchscreen).
[Signature Pad](https://github.com/szimek/signature_pad) uses HTML5 canvas for recording variable width Bézier curve interpolation.
SignaturEE is a Java Server Faces webapp that allow users to persist signatures into database.

Technologies
---
* JavaEE 7 - JPA, CDI, JSF.
* [Signature Pad](https://github.com/szimek/signature_pad).
* [BootsFaces](http://www.bootsfaces.net).
* [Omnifaces](http://omnifaces.org).

How to build
---
* Pull the code, it's a Maven based [Netbeans] (https://netbeans.org) project.
* SignaturEE has been developed with [Glassfish] (https://glassfish.java.net), so you can use it or [Payara] (http://www.payara.fish/home).
* Build and run the project.
* Tada!
