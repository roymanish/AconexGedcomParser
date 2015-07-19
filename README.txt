==================================================
Aconex-Coding-Challenge - GEDCOM Parser Challenge
==================================================

Why this Project?
=========================================
If I be honest, I felt this one was the easiest of all the problems. And with the limited amount of time given i wanted to focus more on the designing part of it. 
So that I can make the solution very generic and easily extendible in nature. The enclosed program has interface methods for any kind of document parsing. IDocumentParser
is the interface that will be exposed to the client program. Rest will be taken care by the implementation classes depending on the kind of input document provided and
a kind of output document expected. This program can be extended to support Text-to-Excel, JSON-to-XML, XML-to-Text etc. For all these cases interface methods will be same
and implementation classes only has to take care of how they want to process it.


How to run it?
=========================================
Import the extracted zip to eclipse as a Java Project.

For default input enclosed.
	Right click on the project Run As->Java Application-> Select GedcomClient from the drop down.
	
For custom input.
	Provide 2 arguments for input file and output file in that order to GedcomClient class. Place your input file in testdata folder.
	
To Run the test cases.
	Right click on the project Run As->JUnit Test
	
	
Design Approach
==========================================
It has two main parts parser API and Client program which will use it. From the API a interface(IDocumentParser) is exposed to the client to call its method
parseDocument which takes two arguments input document path and output document path.

Internally IDocumentParser is implemented by ATextToXMLParser which converts any input document in TXT format into XML. There can be other implementation 
classes such as EXCEL to XML parser which will separate set of validations. It validates the input file and reads it line by line
and provide it to the final implementation class to process.

Parsing logic will vary based on the format of input text file. So a concrete implementation class(GedcomParser) which extends ATextToXMLParser is created to handle the
specific procession logic. It converts the input in a tree data-structure so that it is easy to write in XML. It also validates the content of the text file
for GECOM format. If the format is not correct. It throws prints a message and program exits.