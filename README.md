# File-Tracking-System-with-J2SE-JDBC-and-Oracle-10g
File Tracking System ,as the name suggests,helps to track the files relevant to you( and your department if you are an HOD) which can get very difficult to track and search  manually. It can also be referred to as document management system and the idea can be implemented for any company as long as every employee is accessed to a database by a PC.


					INTRODUCTION
File Tracking System ,as the name suggests,helps to track the files relevant to you( and your department if you are an HOD) which can get very difficult to track and search  manually. It can also be referred to as document management system and the idea can be implemented for any company as long as every employee is accessed to a database by a PC.
The basic idea is as follows:-
Someone wants to initiate a file ,he/she writes the necessary details on the system like in a virtual diary and the details are stored in the database. 
The file has then,gone through subsequent forwards from many employees depending upon the general office hierarchy(Chair > HOD > Employees) . The employees  along with other details like their departments through which the file has gone through are also stored in the database.
Now,suppose,after few days, that initiator employee wants to know where that file is , the computer automatically tells in a matter of milliseconds and s/he does not have to keep on asking his/her  colleagues.
Any employee can also see which are the files currently forwarded to him(or other employees of his/her department in case of an HOD) at that given moment.

The program has a lot of other features ,too,catering to the specific needs of any kind of employee ( Chair,HOD or other employees), which I think , might be useful.


 
	
			     WORKING AND MECHANISM 
Technology used:-
	Back end – Oracle 10.2.0 , JDBC driver (ojdbc 14_g)
	Front end – Java on Eclipse Mars IDE (989 lines of java code written(excluding SQL queries in sqlplus))
Relation Tables:-
	Master table:-

Relation schema  ->

Name                                          Null?    	Type
-------------------------------------------------------------------------
-------------------------------------------------------------------------
USERNO
                                          NOT NULL 	NUMBER(4)
USERNAME
                                                   		VARCHAR2(30)
DEPNO
                                                   		NUMBER(3)
ETYPE
                                                  		 VARCHAR2(20)
PASS
                                                   		VARCHAR2(20)

Snapshot - >






       335 Mr.R.K. Ghosh                         102 		hod                 	 sys335                    
       .
       .
       .
       700 Mr.Sayan R.                             0 		chair                tmvrises      


	Department table :-


Relation schema ->
Name                                      Null?   		 Type
 ----------------------------------------- -------- ----------------------------
 DEPNO                                     NOT NULL 		NUMBER(3)
 DEPNAME                                            		VARCHAR2(20)

Snapshot ->
     DEPNO 	DEPNAME
---------- -----------------------------
       101 		Finance
       102 		System
       103 		Personal
       104 		Material Management
       105		 Human Resource
         0 		ALL

	Transaction table :-
Relation schema->

Name                               Null?    	Type
---------------------------------------------------------------------------
---------------------------------------------------------------------------
FILENO
                                     Not null     	 NUMBER(4)
FILESUB
                                                   	VARCHAR2(35)
FORBY
                                                  	 VARCHAR2(30)
FORTO
                                                  	 VARCHAR2(30)
SDATE
                                                   	VARCHAR2(25)
EDATE
                                                   	VARCHAR2(25)
STATUS
                                                   	VARCHAR2(10)
MONEY
                                                   	NUMBER(10)
DEPFR	
                                                   	VARCHAR2(20)
DEPTO
                                                  	VARCHAR2(20)

Fileno - Unique file no for a particular file
Filesub – Subject of the file
forby- Username of the person who has forwarded the file
forto- Username of the person to whom it has been forwarded
sdate- Date and time when the file has arrived to the particular employee
edate- Date and time when the file has departed from the particular employee
status- “Initiated” means  the file is initiated by the employee
             “Through” means  the file passed through by the employee
             “Now” means the file is currently present on the employee
money-Money involved in the file
depfr- Dept  name of the person who has forwarded the file
depto- Dept name of the person to whom it has been forwarded


User interface:-
It is a command line interface with user-friendly instructions giving the user ability to exit from the system and going to the main menu at his/her command.
	Start-up authentication/login :-
A user is asked to give his/her user number and password . S/he has got five attempts to enter the correct credentials otherwise the user  gets an automatic exit ensuring security.
MAIN MENU ->
	Logout:-
The user gets prompted into the start-up login again.

	Initiate:-

The user is asked to give the file subject and money involved.
File no. is automatically generated . 
Forby and depfr is of the current employee
Status is “initiated”
Sdate is the current date and time.

User is asked whether to go to main menu,exit or forward.
 If forward is chosen,the user asked to whom s/he wants it to be forwarded to.Then,forto is automatically generated and status of the next record becomes “Now”.

FOR AN HOD - > 
HOD can forward the file to the employees of his department,other HODs and the chair.

FOR CHAIR ->
He can forward it to anyone.

FOR OTHER EMPLOYEES ->
He can forward it to employees of his dept and his HOD.

If the user tries to forward file outside of his domain,he is asked to try again.

	Check-n-track your files :-

FOR AN HOD - > 
1.Files initiated by you		2.Files currently fwd to you
3.Files initiated by your dept	4.Files currently fwd to your dept
5.Back to MAIN MENU		6.Exit
He is asked to enter the option.
1.For option 1:- He is shown all the records of the transaction table of the files which have been initiated by him.
He is asked if he wants to forward any file or not.If he enters the file no. of a valid file(which he has not forwarded yet) ,the file will be forwarded accordingly. If he does not enter a valid file no,he is asked to try again.
2. For option 2:-He is shown all the records of the transaction table of the files which have been forwarded to him and for which he is the current holder.
He is asked if he wants to forward any file or not.If he enters the file no. of a valid file(which he has not forwarded yet) ,the file will be forwarded accordingly. If he does not enter a valid file no,he is asked to try again.
3.For option 3:- He is shown all the records of the transaction table of the files which have been initiated by him or other employees of his department.
He is asked whether to show it as per order of money involved or initiation date.
4.For option 4:- He is shown all the records of the transaction table of the files which have been forwarded to the employees of his dept incl. him and for which they are the current holder.
He is asked whether to show it as per order of money involved or initiation date.

FOR A CHAIR->

1.Files initiated by you		2.Files currently fwd to you
3.Files initiated by any dept	4.Files currently fwd to any dept
5.All the files
6.Back to MAIN MENU		7.Exit
He is asked to enter the option.
1.For option 1:- He is shown all the records of the transaction table of the files which have been initiated by him.
He is asked if he wants to forward any file or not.If he enters the file no. of a valid file(which he has not forwarded yet) ,the file will be forwarded accordingly. If he does not enter a valid file no,he is asked to try again.
2. For option 2:-He is shown all the records of the transaction table of the files which have been forwarded to him and for which he is the current holder.
He is asked if he wants to forward any file or not.If he enters the file no. of a valid file(which he has not forwarded yet) ,the file will be forwarded accordingly. If he does not enter a valid file no,he is asked to try again.
3.For option 3:- He is asked to enter a dept number.
He is shown all the records of the transaction table of the files which have been initiated by employees of the particular department.
He is asked whether to show it as per order of money involved or initiation date.
4.For option 4:- He is asked to enter a dept number.
He is shown all the records of the transaction table of the files which have been forwarded to the employees of the particular dept and for which they are the current holder.
He is asked whether to show it as per order of money involved or initiation date.
5.For option 5 :- He is shown all the records of the transaction table.
He is asked whether to show it as per order of money involved or initiation date.

FOR OTHER EMPLOYEES->
1.Files initiated by you		2.Files currently fwd to you
3.Back to MAIN MENU		4.Exit
He is asked to enter the option.
1.For option 1:- He is shown all the records of the transaction table of the files which have been initiated by him.
He is asked if he wants to forward any file or not.If he enters the file no. of a valid file(which he has not forwarded yet) ,the file will be forwarded accordingly. If he does not enter a valid file no,he is asked to try again.
2. For option 2:-He is shown all the records of the transaction table of the files which have been forwarded to him and for which he is the current holder.
He is asked if he wants to forward any file or not.If he enters the file no. of a valid file(which he has not forwarded yet) ,the file will be forwarded accordingly. If he does not enter a valid file no,he is asked to try again.

	Search where your file is :-

FOR AN HOD->
He is asked :- Enter file no. of the relevant(initiated by you or others of your dept.) file to be searched


FOR A CHAIR->
He is asked :- Enter file no. of the file to be searched

FOR OTHER EMPLOYEES->
He is asked :- Enter file no. of the relevant(initiated by you ) file to be searched

If they do not enter a relevant file’s file number,they are asked to try again.This is to ensure the jurisdiction of domain.
If they enter the correct file no.,then the file’s current holder,money,file subject and the “sdate” is shown,that is, only one unique record is shown where the file is not forwarded to anyone yet(forto is NULL).
	Exit:-

The control gets out of the user and a message is prompted.







            


 


 			                advantages(pros)
	It ensures security at the start-up login as the user is given five attempts to enter the correct credentials (username and password) .If he takes more than that,he gets an automatic exit.

	It is a highly efficient program which gets compiled in 0.0002 sec on average.This is partly because I have used parameterized functions enabling them to be called for different purposes thus minimizing the source code.Also,I have taken help of recursion.

	The user at any given moment can go to the main menu for other options  and exit the program.

	The logout option ensures multiple login from a single terminal.

	The user-friendly interface ensures that if any layman user enters wrong input of any kind,he/she is told about the problem and asked to enter again.

	Deleting records from the tables can only be done by the DBA who has the necessary password to login to the database.

limitations(cons)
	Due to the lack of time(my examinations were underway ) ,the client-side version of the program could not be developed ,i.e.,it does not have a graphical user interface.Instead,it has command line interface.
	The passwords are not encrypted by any keys nor it incorporates any hashing algorithm,thus compromising security to an extent.
	No safety measures have been taken to prevent SQL injections.







SCREENSHOTS   OF  THE  APPLICATION




 
  
 

The right part of the screen is as follows:- 
 

The right part of the screen is as follows:-
 
 

The right part of the screen is as follows:-
 
 

Multiple logins from a single terminal can be done  and also,there are different user interfaces and instructions for each of the different types of employees ,i.e.,chair,hod and other employees. However,I have given the above outputs mainly from an HOD’s perspective.
 
