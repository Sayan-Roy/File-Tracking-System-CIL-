package ftpk1;
import java.io.Console;






import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.*;
import java.sql.PreparedStatement;
import com.oracle.*;



public class ftracker 
{
	
	String uname1,pass1,etype1,depname1;
	//String eno="Emp No.",ename="Emp Name",et="Emp Type",dn="Dept Name";
	int uno1,depno1;
	static Connection con = null;
	static PreparedStatement pstmt1=null,pstmt2=null,pstmt3=null,pstmt4=null; 
	
	void exitf()
	{
		System.out.print("\n\n\n");
		System.out.println("\t\t\t\t\t\t\t\t\t\t\tTHANK YOU!!!");
		System.exit(0);

	}
	
	void menuf()throws Exception
	{
		System.out.print("\n\n\n");
		Scanner in1=new Scanner(System.in);
		ResultSet rs1=null;
		String qm1="select username,master.depno,etype,depname from master,dept where master.depno=dept.depno and userno=?";
		pstmt1=con.prepareStatement(qm1);		
		pstmt1.setInt(1,uno1);
		rs1=pstmt1.executeQuery();
		while(rs1.next())
		{
			uname1=rs1.getString(1);
			depno1=rs1.getInt(2);
			etype1=rs1.getString(3);
			depname1=rs1.getString(4);
			
			System.out.println("\t\t\t\t\t\t\t\tHello, "+uname1+" ( "+etype1+" , "+depname1+" )");
		}
		System.out.println("\n\t\t\t\t\t\t\t\t\t--------MAIN MENU--------");
		System.out.println("\t\t\t\t\t\t\t\t\t\t1.Logout(Login as a different user)\n\t\t\t\t\t\t\t\t\t\t2.Initiate\n\t\t\t\t\t\t\t\t\t\t3.Check-n-Track your files\n\t\t\t\t\t\t\t\t\t\t4.Search where your file is\n\t\t\t\t\t\t\t\t\t\t5.Exit");
		do
		{
			System.out.print("Enter choice:-");
			int ch=in1.nextInt();
			switch(ch)
			{
			case 1:
				loginf();
				break;
			case 2:
				initiatef();
				break;
			case 3:
				checkf();
				break;
			case 4:
				searchf();
				break;
			case 5:
				exitf();
				break;
			default:
				System.out.println("Kindly enter a valid choice");
			}
		}while(true);
	}

	void startf()throws Exception
	{
		Scanner in1=new Scanner(System.in);
		System.out.println("\t\t\t\t\t\t\t\t\t\t1.Login\t\t2.Exit");
		do
		{
			System.out.print("Enter choice:-");
//			String tch=in1.nextLine();
//			int ch=Integer.parseInt(tch);
			int ch=in1.nextInt();
			switch(ch)
			{
			case 1:
				loginf();
				break;
			case 2:
				exitf();
				break;
			default:
				System.out.println("Kindly enter a valid choice");
			}
		}while(true);
	}
				
				
		
		

	
	
	void loginf()throws Exception
	{
		System.out.print("\n\n\n");
		Console console=System.console();
		
		Scanner in=new Scanner(System.in);
		Scanner in2=new Scanner(System.in);
		String querym="select * from master";
		pstmt1=con.prepareStatement(querym);
		ResultSet rs1=null;
		int count=5;
		String pass,a;
		do
		{
			count--;
			System.out.println("\t\t\t\t\t\t\t\t\t\t----LOGIN----");
			System.out.print("Enter user number -> ");
			uno1=in.nextInt();
			//pass=new String(console.readPassword("Enter password"));
			//console.printf("password entered : %s%n",pass);
			System.out.print("Enter password -> ");
			pass1=in2.nextLine();
			System.out.println();
			rs1=pstmt1.executeQuery();
			while(rs1.next())
			{
				if(rs1.getInt(1)==uno1 && pass1.equals(rs1.getString(5)))
				{
					
					menuf();
					
				}
			}
			if(count==0)
				exitf();
			System.out.print("Login failed ( "+count+" attempts remaining)||Enter exit for exit,otherwise anything else -> ");
			a=in2.nextLine();
			if(a.equalsIgnoreCase("exit"))
				
				exitf();			
			

		}while(true);
	}
	
	void initiatef()throws Exception
	{
		System.out.print("\n\n\n");
		System.out.println("\t\t\t\t\t\t\t\t\t\t----INITIATION----");
		String sub,datestr=null;
		int mon,newfileno=0;
		Scanner in1=new Scanner(System.in);
		Scanner in2=new Scanner(System.in);
		System.out.print("\nWhat's the subject of the file(max 35 chars):-");
		sub=in1.nextLine();
		System.out.print("\nWhat's the money involved(max.200,00,00,000):-");
		mon=in2.nextInt();
		String query1="select max(fileno) from trans";
		pstmt1=con.prepareStatement(query1);
		ResultSet rs1=pstmt1.executeQuery();
		while(rs1.next())
		{
			newfileno=rs1.getInt(1)+1;
		}
		
		String query2="Insert into trans(fileno,filesub,forby,sdate,status,money,depfr) values(?,?,?,?,?,?,?)";
		pstmt2=con.prepareStatement(query2);
		pstmt2.setInt(1,newfileno);
		pstmt2.setString(2, sub);
		pstmt2.setString(3, uname1);
		//java.sql.Date date=getCurrentJavaSqlDate();
//		Calendar cal=Calendar.getInstance();
//		java.sql.Date dateobj=new java.sql.Date(cal.getTime().getTime());
		String query3="select to_char(sysdate,'dd-MON-yy  hh24:mi:ss') from dual";
		pstmt3=con.prepareStatement(query3);
		ResultSet rs2=pstmt3.executeQuery();
		while(rs2.next())
		{
			datestr=rs2.getString(1);
		}
		pstmt2.setString(4,datestr);
		pstmt2.setString(5,"Initiated");
		pstmt2.setInt(6,mon);
		pstmt2.setString(7,depname1);
		pstmt2.executeUpdate();
		System.out.println("\n\n\t\t\t\t\t\t\t\t\t1.Back to MAIN menu\t\t2.Forward\t\t3.Exit");
		do
		{
			System.out.print("Enter choice:-");
			int ch=in1.nextInt();
			switch(ch)
			{
			case 1:
				menuf();
				break;
			case 2:
				forwardf(newfileno,"init");
				menuf();
				break;
			case 3:
				exitf();
				break;
			default:
				System.out.println("Kindly enter a valid choice");
			}
		}while(true);
		
	}
	
	
	void forwardf(int fno,String from)throws Exception
	{
		Scanner in1=new Scanner(System.in);
		Scanner in2=new Scanner(System.in);
		ResultSet rs1,rs2,rs3;
		int eno1,earr[]=new int[100],count=0,flag=0,fmon=0;;
		String ch,enarr[]=new String[100],dnarr[]=new String[100],fsub=null,q3=null;
		if(etype1.equals("hod"))
		{
			System.out.println("Employees you can forward it to:-");
			String q1="select userno,username ,etype,depname from master,dept where (master.depno=dept.depno and (master.depno=? or etype='hod' or etype='chair') and userno!=?) ";
			pstmt1=con.prepareStatement(q1);
			pstmt1.setInt(1,depno1);
			pstmt1.setInt(2,uno1);
			rs1=pstmt1.executeQuery();
			System.out.format("%-9s %-30s %-20s %-20s","Emp No.","Emp Name","Emp Type","Dept Name");
			System.out.println("\n--------------------------------------------------------------------------------------------------------");
			while(rs1.next())
			{
				System.out.format("%-9d %-30s %-20s %-20s",rs1.getInt(1),rs1.getString(2),rs1.getString(3),rs1.getString(4));
				System.out.println();
				earr[count]=rs1.getInt(1);
				enarr[count]=rs1.getString(2);
				dnarr[count]=rs1.getString(4);
				count++;
				
			}
			do
			{
				System.out.print("To whom you want file numbered "+fno+" to be forwarded? Enter EMP NO. from the LIST ABOVE:- ");
				eno1=in1.nextInt();
				for(int i=0;i<count;i++)
				{
					if(eno1==earr[i])
					{
						String datestr=null;
						System.out.println("File forward successful!");
						//flag=1;
						if(from.equals("init"))							
							 q3="Update trans set forto=?,edate=?,depto=? where fileno=? and status='Initiated'";
						else if(from.equals("curr"))
							 q3="Update trans set forto=?,edate=?,depto=?,status='Through' where fileno=? and status='Now'";
						pstmt2=con.prepareStatement(q3);
						pstmt2.setString(1, enarr[i]);
						String query3="select to_char(sysdate,'dd-MON-yy  hh24:mi:ss') from dual";
						pstmt3=con.prepareStatement(query3);
						rs2=pstmt3.executeQuery();
						while(rs2.next())
						{
							datestr=rs2.getString(1);
						}
						pstmt2.setString(2,datestr);
						pstmt2.setString(3, dnarr[i]);
						pstmt2.setInt(4, fno);
						pstmt2.executeUpdate();
						String q4="select filesub,money from trans where fileno=? and status='Initiated'";
						pstmt3=con.prepareStatement(q4);
						pstmt3.setInt(1,fno);
						rs3=pstmt3.executeQuery();
						while(rs3.next())
						{
							fsub=rs3.getString(1);
							fmon=rs3.getInt(2);
						}
						String q5="Insert into trans(fileno,filesub,forby,sdate,status,money,depfr) values(?,?,?,?,?,?,?)";
						pstmt4=con.prepareStatement(q5);
						pstmt4.setInt(1,fno);
						pstmt4.setString(2, fsub);;
						pstmt4.setString(3, enarr[i]);
						pstmt4.setString(4,datestr);
						pstmt4.setString(5, "Now");
						pstmt4.setInt(6, fmon);
						pstmt4.setString(7, dnarr[i]);
						pstmt4.executeUpdate();
						return;
					}
					
				}
				System.out.print("Forward UNsuccessful:-Press 't' to try again,otherwise any other key ->  ");
				ch=in2.nextLine();
				if(!(ch.equalsIgnoreCase("t")))
					return;
			}while(true);
			
		}
		
		
		
		else if(etype1.equals("chair"))
		{
			System.out.println("Employees you can forward it to:-");
			String q1="select userno,username ,etype,depname from master,dept where master.depno=dept.depno and userno!=?";
			pstmt1=con.prepareStatement(q1);
			pstmt1.setInt(1, uno1);
			rs1=pstmt1.executeQuery();
			System.out.format("%-9s %-30s %-20s %-20s","Emp No.","Emp Name","Emp Type","Dept Name");
			System.out.println("\n--------------------------------------------------------------------------------------------------------");
			while(rs1.next())
			{
				System.out.format("%-9d %-30s %-20s %-20s",rs1.getInt(1),rs1.getString(2),rs1.getString(3),rs1.getString(4));
				System.out.println();
				earr[count]=rs1.getInt(1);
				enarr[count]=rs1.getString(2);
				dnarr[count]=rs1.getString(4);
				count++;				
				
			}
			do
			{
				System.out.print("To whom you want file numbered "+fno+" to be forwarded? Enter EMP NO. from the LIST ABOVE:- ");
				eno1=in1.nextInt();
				for(int i=0;i<count;i++)
				{
					if(eno1==earr[i])
					{
						String datestr=null;
						System.out.println("File forward successful!");
						//flag=1;
						if(from.equals("init"))							
							 q3="Update trans set forto=?,edate=?,depto=? where fileno=? and status='Initiated'";
						else if(from.equals("curr"))
							 q3="Update trans set forto=?,edate=?,depto=?,status='Through' where fileno=? and status='Now'";
						pstmt2=con.prepareStatement(q3);
						pstmt2.setString(1, enarr[i]);
						String query3="select to_char(sysdate,'dd-MON-yy  hh24:mi:ss') from dual";
						pstmt3=con.prepareStatement(query3);
						rs2=pstmt3.executeQuery();
						while(rs2.next())
						{
							datestr=rs2.getString(1);
						}
						pstmt2.setString(2,datestr);
						pstmt2.setString(3, dnarr[i]);
						pstmt2.setInt(4, fno);
						pstmt2.executeUpdate();
						String q4="select filesub,money from trans where fileno=? and status='Initiated'";
						pstmt3=con.prepareStatement(q4);
						pstmt3.setInt(1,fno);
						rs3=pstmt3.executeQuery();
						while(rs3.next())
						{
							fsub=rs3.getString(1);
							fmon=rs3.getInt(2);
						}
						String q5="Insert into trans(fileno,filesub,forby,sdate,status,money,depfr) values(?,?,?,?,?,?,?)";
						pstmt4=con.prepareStatement(q5);
						pstmt4.setInt(1,fno);
						pstmt4.setString(2, fsub);;
						pstmt4.setString(3, enarr[i]);
						pstmt4.setString(4,datestr);
						pstmt4.setString(5, "Now");
						pstmt4.setInt(6, fmon);
						pstmt4.setString(7, dnarr[i]);
						pstmt4.executeUpdate();
						return;
					}
					
				}
				System.out.print("Forward UNsuccessful:-Press 't' to try again,otherwise any other key ->  ");
				ch=in2.nextLine();
				if(!(ch.equalsIgnoreCase("t")))
					return;

				
			}while(true);
			
		}
		else
		{
			System.out.println("Employees you can forward it to:-");
			String q1="select userno,username ,etype,depname from master,dept where (master.depno=dept.depno and (master.depno=? ) and userno!=?) ";
			pstmt1=con.prepareStatement(q1);
			pstmt1.setInt(1,depno1);
			pstmt1.setInt(2, uno1);
			rs1=pstmt1.executeQuery();
			System.out.format("%-9s %-30s %-20s %-20s","Emp No.","Emp Name","Emp Type","Dept Name");
			System.out.println("\n--------------------------------------------------------------------------------------------------------");
			while(rs1.next())
			{
				System.out.format("%-9d %-30s %-20s %-20s",rs1.getInt(1),rs1.getString(2),rs1.getString(3),rs1.getString(4));
				System.out.println();
				earr[count]=rs1.getInt(1);
				enarr[count]=rs1.getString(2);
				dnarr[count]=rs1.getString(4);
				count++;				
			}
			do
			{
				System.out.print("To whom you want file numbered "+fno+" to be forwarded? Enter EMP NO. from the LIST ABOVE:- ");
				eno1=in1.nextInt();
				for(int i=0;i<count;i++)
				{
					if(eno1==earr[i])
					{
						String datestr=null;
						System.out.println("File forward successful!");
						//flag=1;
						if(from.equals("init"))							
							 q3="Update trans set forto=?,edate=?,depto=? where fileno=? and status='Initiated'";
						else if(from.equals("curr"))
							 q3="Update trans set forto=?,edate=?,depto=?,status='Through' where fileno=? and status='Now'";
						pstmt2=con.prepareStatement(q3);
						pstmt2.setString(1, enarr[i]);
						String query3="select to_char(sysdate,'dd-MON-yy  hh24:mi:ss') from dual";
						pstmt3=con.prepareStatement(query3);
						rs2=pstmt3.executeQuery();
						while(rs2.next())
						{
							datestr=rs2.getString(1);
						}
						pstmt2.setString(2,datestr);
						pstmt2.setString(3, dnarr[i]);
						pstmt2.setInt(4, fno);
						pstmt2.executeUpdate();
						String q4="select filesub,money from trans where fileno=? and status='Initiated'";
						pstmt3=con.prepareStatement(q4);
						pstmt3.setInt(1,fno);
						rs3=pstmt3.executeQuery();
						while(rs3.next())
						{
							fsub=rs3.getString(1);
							fmon=rs3.getInt(2);
						}
						String q5="Insert into trans(fileno,filesub,forby,sdate,status,money,depfr) values(?,?,?,?,?,?,?)";
						pstmt4=con.prepareStatement(q5);
						pstmt4.setInt(1,fno);
						pstmt4.setString(2, fsub);
						pstmt4.setString(3, enarr[i]);
						pstmt4.setString(4,datestr);
						pstmt4.setString(5, "Now");
						pstmt4.setInt(6, fmon);
						pstmt4.setString(7, dnarr[i]);
						pstmt4.executeUpdate();
						return;
					}
					
				}
				System.out.print("Forward UNsuccessful:-Press 't' to try again,otherwise any other key ->  ");
				ch=in2.nextLine();
				if(!(ch.equalsIgnoreCase("t")))
					return;
				
			}while(true);
			
		}	
		
	}
	
	
	void checkf()throws Exception
	{
		System.out.print("\n\n\n");
		System.out.println("\t\t\t\t\t\t\t\t\t  ----CHECK-N-TRACK MENU----");
		String q1,dnarr[]=new String[40],ch2;
		ResultSet rs1;
		Scanner in3=new Scanner(System.in);
		int dnoarr[]=new int[40],count1=0,dno1;
		Scanner in1=new Scanner(System.in);
		Scanner in2=new Scanner(System.in);
		if(etype1.equals("hod"))
		{
			System.out.println("\t\t\t\t\t\t1.Files initiated by you\t\t2.Files currently fwd to you\n\t\t\t\t\t\t3.Files initiated by your dept\t\t4.Files currently fwd to your dept\n\t\t\t\t\t\t5.Back to MAIN MENU\t\t\t6.Exit");
			do
			{
				System.out.print("Enter choice:-");
				int ch=in1.nextInt();
				switch(ch)
				{
				case 1:
					initbyf();
					break;
				case 2:
					currf();
					break;
				case 3:
					initbydeptf(depname1);
					break;
				case 4:
					currondeptf(depname1);
					break;
				case 5:
					menuf();
					break;
				case 6:
					exitf();
					break;
				default:
					System.out.println("Kindly enter a valid choice");
				}
			}while(true);
		}
		else if(etype1.equals("chair"))
		{
			System.out.println("\t\t\t\t\t\t1.Files initiated by you\t\t2.Files currently fwd to you\n\t\t\t\t\t\t3.Files initiated by any dept\t\t4.Files currently fwd to any dept\n\t\t\t\t\t\t5.All the files\n\t\t\t\t\t\t6.Back to MAIN MENU\t\t\t7.Exit");
			do
			{
				System.out.print("Enter choice:-");
				int ch=in1.nextInt();
				switch(ch)
				{
				case 1:
					initbyf();
					break;
				case 2:
					currf();
					break;
				case 3:
					count1=0;
					System.out.println("List of the departments:-");
					 q1="select * from dept where depno!=0";
					pstmt1=con.prepareStatement(q1);
					rs1=pstmt1.executeQuery();
					System.out.format("%-10s %-20s","Dept No.","Dept Name");
					System.out.println("\n--------------------------------------------------------------------------------------------------------");
					while(rs1.next())
					{
						System.out.format("%-10d %-20s",rs1.getInt(1),rs1.getString(2));
						System.out.println();
						dnoarr[count1]=rs1.getInt(1);
						dnarr[count1]=rs1.getString(2);
						count1++;
					}	
					do
					{
						System.out.print("Enter valid dept no.:-");
						dno1=in3.nextInt();
						for(int i=0;i<count1;i++)
						{
							if(dno1==dnoarr[i])
							{
								initbydeptf(dnarr[i]);
							}
						}
						System.out.print("INvalid dept no.:-Press 't' to try again,otherwise any other key ->  ");
						ch2=in2.nextLine();
						if(!(ch2.equalsIgnoreCase("t")))
							checkf();
						
					}while(true);
					//break;
				case 4:
					count1=0;
					System.out.println("List of the departments:-");
					 q1="select * from dept where depno!=0";
					pstmt1=con.prepareStatement(q1);
					rs1=pstmt1.executeQuery();
					System.out.format("%-10s %-20s","Dept No.","Dept Name");
					System.out.println("\n--------------------------------------------------------------------------------");
					while(rs1.next())
					{
						System.out.format("%-10d %-20s",rs1.getInt(1),rs1.getString(2));
						System.out.println();
						dnoarr[count1]=rs1.getInt(1);
						dnarr[count1]=rs1.getString(2);
						count1++;
					}	
					do
					{
						System.out.print("Enter valid dept no.:-");
						dno1=in3.nextInt();
						for(int i=0;i<count1;i++)
						{
							if(dno1==dnoarr[i])
							{
								currondeptf(dnarr[i]);
							}
						}
						System.out.print("INvalid dept no.:-Press 't' to try again,otherwise any other key ->  ");
						ch2=in2.nextLine();
						if(!(ch2.equalsIgnoreCase("t")))
							checkf();
						
					}while(true);
					//break;
				case 5:
					allfilef();
					break;
				case 6:
					menuf();
					break;
				case 7:
					exitf();
					break;
				default:
					System.out.println("Kindly enter a valid choice");
				}
			}while(true);
		}
		else
		{
			System.out.println("\t\t\t\t\t\t1.Files initiated by you\t\t2.Files currently fwd to you\n\t\t\t\t\t\t3.Back to MAIN MENU\t\t\t4.Exit");
			do
			{
				System.out.print("Enter choice:-");
				int ch=in1.nextInt();
				switch(ch)
				{
				case 1:
					initbyf();
					break;
				case 2:
					currf();
					break;
				case 3:
					menuf();
					break;
				case 4:
					exitf();
					break;
				default:
					System.out.println("Kindly enter a valid choice");
				}
			}while(true);
			
		}
		
	}
	
	
	void initbyf()throws Exception
	{
		Scanner in3=new Scanner(System.in);
		Scanner in2=new Scanner(System.in);
		ResultSet rs11,rs21;
		int fnoarr[]=new int[100],count1=0,fno1;
		String ch1,ch2;
		String query1="Select * from trans where fileno in (select fileno from trans where forby=? and status='Initiated') order by fileno,sdate";
		pstmt1=con.prepareStatement(query1);
		pstmt1.setString(1,uname1);
		rs11=pstmt1.executeQuery();
		System.out.format("%-9s %-35s %-30s %-30s %-25s %-25s %-10s %-16s %-20s %-20s","File No.","File Subject","Forwarded by","Forwarded to","Start Time","End Time","Status","Money Involved","Dept From","Dept To");
		System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		while(rs11.next())
		{
			System.out.format("%-9d %-35s %-30s %-30s %-25s %-25s %-10s %-16d %-20s %-20s",rs11.getInt(1),rs11.getString(2),rs11.getString(3),rs11.getString(4),rs11.getString(5),rs11.getString(6),rs11.getString(7),rs11.getInt(8),rs11.getString(9),rs11.getString(10));
			System.out.println();
			if(rs11.getString(4)==null && rs11.getString("status").equals("Initiated"))
			{
				fnoarr[count1]=rs11.getInt(1);
				count1++;
			}
		}
		System.out.print("Do you wish to forward any file?:-Enter yes ,otherwise any key -> ");
		ch1=in3.nextLine();
		//If forward--------------------------------------------------------------------------------------------------
		if(ch1.equalsIgnoreCase("yes"))
		{
			do
			{
				System.out.print("Enter valid(currently on you) file no. to be forwarded:-");
				fno1=in3.nextInt();
				for(int i=0;i<count1;i++)
				{
					if(fno1==fnoarr[i])
					{
						forwardf(fno1,"init");
						checkf();
					}
				}
				System.out.print("INvalid file no.:-Press 't' to try again,otherwise any other key ->  ");
				ch2=in2.nextLine();
				if(!(ch2.equalsIgnoreCase("t")))
					checkf();
				
			}while(true);
		}
		else 
			checkf();
	}
	
	void currf()throws Exception
	{
		Scanner in3=new Scanner(System.in);
		Scanner in2=new Scanner(System.in);
		String ch1,ch2;
		int fnoarr[]=new int[100],count1=0,fno1;
		ResultSet rs11;
		String query1="Select * from trans where forby=? and status='Now' order by fileno";
		pstmt1=con.prepareStatement(query1);
		pstmt1.setString(1,uname1);
		rs11=pstmt1.executeQuery();
		System.out.format("%-9s %-35s %-30s %-30s %-25s %-25s %-10s %-16s %-20s %-20s","File No.","File Subject","Forwarded by","Forwarded to","Start Time","End Time","Status","Money Involved","Dept From","Dept To");
		System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		while(rs11.next())
		{
			System.out.format("%-9d %-35s %-30s %-30s %-25s %-25s %-10s %-16d %-20s %-20s",rs11.getInt(1),rs11.getString(2),rs11.getString(3),rs11.getString(4),rs11.getString(5),rs11.getString(6),rs11.getString(7),rs11.getInt(8),rs11.getString(9),rs11.getString(10));
			System.out.println();
			fnoarr[count1++]=rs11.getInt(1);
		}
		System.out.print("Do you wish to forward any file?:-Enter yes ,otherwise any key -> ");
		ch1=in3.nextLine();
		//If forward--------------------------------------------------------------------------------------------------
		if(ch1.equalsIgnoreCase("yes"))
		{
			do
			{
				System.out.print("Enter valid(currently on you) file no. to be forwarded:-");
				fno1=in3.nextInt();
				for(int i=0;i<count1;i++)
				{
					if(fno1==fnoarr[i])
					{
						forwardf(fno1,"curr");
						checkf();
					}
				}
				System.out.print("INvalid file no.:-Press 't' to try again,otherwise any other key ->  ");
				ch2=in2.nextLine();
				if(!(ch2.equalsIgnoreCase("t")))
					checkf();
				
			}while(true);
		}
		else 
			checkf();
	}

	void initbydeptf(String dname1)throws Exception
	{
		int ch1;
		String query1=null;
		Scanner in1=new Scanner(System.in);
		ResultSet rs11;
		System.out.println("\t\tShow in:-1.Decreasing order of money inv.\n\t\t         2.Increasing order of initiation date");
		do
		{
			System.out.print("Enter choice:-");
			 ch1=in1.nextInt();
			switch(ch1)
			{
			case 1:
				query1="Select * from trans where fileno in (select fileno from trans where depfr=? and status='Initiated') order by money desc,fileno,sdate";		
				break;
			case 2:
				query1="Select * from trans where fileno in (select fileno from trans where depfr=? and status='Initiated') order by fileno,sdate";		
				break;
			default:
				System.out.println("Kindly enter a valid choice");
			}
		}while(ch1!=1 && ch1!=2);
		pstmt1=con.prepareStatement(query1);
		pstmt1.setString(1,dname1);
		rs11=pstmt1.executeQuery();
		System.out.format("%-9s %-35s %-30s %-30s %-25s %-25s %-10s %-16s %-20s %-20s","File No.","File Subject","Forwarded by","Forwarded to","Start Time","End Time","Status","Money Involved","Dept From","Dept To");
		System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		while(rs11.next())
		{
			System.out.format("%-9d %-35s %-30s %-30s %-25s %-25s %-10s %-16d %-20s %-20s",rs11.getInt(1),rs11.getString(2),rs11.getString(3),rs11.getString(4),rs11.getString(5),rs11.getString(6),rs11.getString(7),rs11.getInt(8),rs11.getString(9),rs11.getString(10));
			System.out.println();
		}
		checkf();
	}
		
		
	void currondeptf(String dname1)throws Exception
	{
		int ch1;
		String query1=null;
		Scanner in1=new Scanner(System.in);
		ResultSet rs11;
		System.out.println("\t\tShow in:-1.Decreasing order of money inv.\n\t\t         2.Increasing order of initiation date");
		do
		{
			System.out.print("Enter choice:-");
			 ch1=in1.nextInt();
			switch(ch1)
			{
			case 1:
				query1="Select * from trans where depfr=? and status='Now' order by money desc,fileno";
				break;
			case 2:
				query1="Select * from trans where depfr=? and status='Now' order by fileno";
				break;
			default:
				System.out.println("Kindly enter a valid choice");
			}
		}while(ch1!=1 && ch1!=2);
		pstmt1=con.prepareStatement(query1);
		pstmt1.setString(1,dname1);
		rs11=pstmt1.executeQuery();
		System.out.format("%-9s %-35s %-30s %-30s %-25s %-25s %-10s %-16s %-20s %-20s","File No.","File Subject","Forwarded by","Forwarded to","Start Time","End Time","Status","Money Involved","Dept From","Dept To");
		System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		while(rs11.next())
		{
			System.out.format("%-9d %-35s %-30s %-30s %-25s %-25s %-10s %-16d %-20s %-20s",rs11.getInt(1),rs11.getString(2),rs11.getString(3),rs11.getString(4),rs11.getString(5),rs11.getString(6),rs11.getString(7),rs11.getInt(8),rs11.getString(9),rs11.getString(10));
			System.out.println();
		}
		checkf();
		
	}
	
	void allfilef()throws Exception
	{
		int ch1;
		String query1=null;
		Scanner in1=new Scanner(System.in);
		ResultSet rs11;
		System.out.println("\t\tShow in:-1.Decreasing order of money inv.\n\t\t         2.Increasing order of initiation date");
		do
		{
			System.out.print("Enter choice:-");
			 ch1=in1.nextInt();
			switch(ch1)
			{
			case 1:
				query1="Select * from trans order by money desc,fileno,sdate";		
				break;
			case 2:
				query1="Select * from trans order by fileno,sdate";		
				break;
			default:
				System.out.println("Kindly enter a valid choice");
			}
		}while(ch1!=1 && ch1!=2);
		pstmt1=con.prepareStatement(query1);
		rs11=pstmt1.executeQuery();
		System.out.format("%-9s %-35s %-30s %-30s %-25s %-25s %-10s %-16s %-20s %-20s","File No.","File Subject","Forwarded by","Forwarded to","Start Time","End Time","Status","Money Involved","Dept From","Dept To");
		System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		while(rs11.next())
		{
			System.out.format("%-9d %-35s %-30s %-30s %-25s %-25s %-10s %-16d %-20s %-20s",rs11.getInt(1),rs11.getString(2),rs11.getString(3),rs11.getString(4),rs11.getString(5),rs11.getString(6),rs11.getString(7),rs11.getInt(8),rs11.getString(9),rs11.getString(10));
			System.out.println();
		}
		checkf();
	}
	
	void searchf()throws Exception
	{
		System.out.print("\n\n\n");
		System.out.println("\t\t\t\t\t\t\t\t\t\t----SEARCH----");
		ResultSet rs11,rs12;
		Scanner in1=new Scanner(System.in);
		Scanner in2=new Scanner(System.in);
		int fino,count=0,ch1;
		String q1,st1,st2,st3,st4,q2,st5;
		q1=q2=st1=st2=st3=st4=st5=null;
		
		if(etype1.equals("hod"))
		{
			System.out.print("\nEnter file no. of the relevant(initiated by you or others of your dept.) file to be searched -> ");
			fino=in1.nextInt();
			q1="Select filesub,forby,sdate,money from trans where fileno in (select fileno from trans where depfr=? and status='Initiated') and fileno=? and forto is NULL";
			pstmt1=con.prepareStatement(q1);
			pstmt1.setString(1,depname1);
			pstmt1.setInt(2,fino);
			
		}
		else if(etype1.equals("chair"))
		{
			System.out.print("\nEnter file no. of the file to be searched -> ");
			fino=in1.nextInt();
			q1="Select filesub,forby,sdate,money from trans where fileno=? and forto is NULL";
			pstmt1.setInt(1,fino);
			
		}
		else
		{
			System.out.print("\nEnter file no. of the relevant(initiated by you) file to be searched -> ");
			fino=in1.nextInt();
			q1="Select filesub,forby,sdate,money from trans where fileno in (select fileno from trans where forby=? and status='Initiated') and fileno=? and forto is NULL";
			pstmt1=con.prepareStatement(q1);
			pstmt1.setInt(2,fino);
			pstmt1.setString(1,uname1);
			
		}
		
		rs11=pstmt1.executeQuery();
		while(rs11.next())
		{
			st2=rs11.getString(2);
			q2="Select depname from dept,master where master.depno=dept.depno and username=?";
			pstmt2=con.prepareStatement(q2);
			pstmt2.setString(1,st2);
			rs12=pstmt2.executeQuery();
			while(rs12.next())
			{
				st5=rs12.getString(1);
			}
			st1=rs11.getString(1);
			st3=rs11.getString(3);
			st4=rs11.getString(4);
			count++;			

		}
		if(count==0)
		{
			System.out.println("No relevant record to be displayed");
		}
		else
		{
				
				System.out.println("File's current holder :- "+st2);
				System.out.println("Current holder's department :- "+st5);
				System.out.println("File's subject :- "+st1);
				System.out.println("Holding since :- "+st3);
				System.out.println("Money involved :- "+st4);
							
		}
						
		System.out.println("\t\t\t\t\t\t1.Search again\t\t2.Back to MAIN menu\t\t3.Exit");
		do
		{
			System.out.print("Enter choice:-");
			ch1=in2.nextInt();
			switch(ch1)
			{
			case 1:
				searchf();
				break;
			case 2:
				menuf();
				break;
			case 3:
				exitf();
				break;
			default:
				System.out.println("Kindly enter a valid choice");
			}
					
		}while(true);
	}
			

	
	
	
	
	
	public static void main(String[] args)throws Exception 
	{
		ftracker ob=new ftracker();
		
		
		try {
			System.out.println("\t\t\t\t\t\t\t\t\t\t\tWELCOME");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = (Connection)DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","system");
			ob.startf();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		 catch(Exception e)
        {
         System.out.println("Exception thrown here :" + e);
        }
        
		finally {
			try {
				//rs.close();
				pstmt1.close();
				pstmt2.close();
				pstmt3.close();
				pstmt4.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


}
