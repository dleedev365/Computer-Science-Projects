# Purpose
The purpose of the project is to design a database management system on MS SQL with given schemas, constraints and triggers

# Technologies
  - MS SQL
  - JDBC (Java API)
  - Java Swing (GUI)

# Process
### Step 1: The Language of Choice
For this project, I needed to pick two languages: Java and SQL. Java is a powerful object-oriented language for designing database systems with a user interface. It has a widget toolkit, Java Swing, provide graphic user interface (GUI) design and JDBC, a Java API, to manage connection to a database, issuing queries and commands, and handling result sets obtained from the database. On the other hand, SQL is used to communicate with a database and is the standard language for relational database management systems. SQL statements are used to perform tasks such as update data on a database, or retrieve data from a database. Microsoft SQL Server supports full functionalities to build a database management system from a scratch and that it can be integrated with Java. It was the choice to go.

### Step 2: The Understanding of Relational Database
<img src="https://miro.medium.com/max/3024/1*LEksJP5OtS8GEBdd2Jy4WQ.png" width="500">

To keep things simple, a relational database is a type of database that stores and provides access to data points that are related to one another. It is based on the relational model, an intuitive, straightforward way of representing data in tables. In a relational database, each row in the table is a record with a unique ID called the key. The columns of the table hold attributes of the data, and each record usually has a value for each attribute, making it easy to establish the relationships among data points. With this in mind, I proceeded to the next step of my project - the implementation.

### Step 3: The Implementation & Testing
Coding was fairly straight-forward. With Java Swing, I added functionalities or buttons to search flights, enter customer booking information, view flights on any given date and others. With a custom user interface, I tested several cases where a user connects to the database system via JDBC and queries flights or modifies (edit/add/delete) his/her flight reservation(s).

### Step 4: Final Result & Reflection
<img src="https://github.com/danlee0528/Airline-Database-System/blob/master/db.jpg" width="500">

As a result, I successfully developed a fully working airline database system and learned a lot about SQL and Java, and learned about query optimizations - how different data retrieval methods or queries can improve the overall processing time. Although this project relies on SFU's own MS SQL server to connect to the database system and run the executable .jar file, it introduced me to the fundamentals of the database management system which can be useful for my career in the future. 

# For More Details
Please contact me via hla191@sfu.ca to gain full access of the program. This program requires to be run on Simon Frasuer University CSIL computer network and database system, therefore only java files are included. This repository only shows partial code for creating a .jar application integrated with MS SQL Server Management System.

# Basic Requirement to Run the Program
1. You need JDBC software installed for databse connection. (This is where you need authorization & private information)
2. rs2xml.jar libarary for populating table, otherwise an error will liekly occur
3. JSwing for interface.
