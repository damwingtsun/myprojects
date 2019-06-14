package ymca_phaseIV;

import javax.swing.JOptionPane;
import java.io.*;
import java.util.*;
//import java.text.*;


public class ymca_phaseIV		
	{		
		public static void main(String[] args)
		{	
			//INT MEMORY
			int selection;
			members mem= new members();
			//registers reg= new registers();
			//programs prog= new programs();
							
			//LOAD READ IN FILES AND OPEN MAIN MENU				
			mem.Begin_read_member();
			
			mem.Begin_read_class();
				
			mem.Begin_read_register();
				
			selection=mem.User_Menu();	
				
				
			//BEGIN MAIN PROG LOOP
			while(selection != 0)
			{
				if(selection == 1)
					mem.Add_Modify_Delete_Member();
					
				else if(selection == 2)
						mem.Modify_Registser();
					
				else if(selection == 3)
					mem.Report();	
					
				selection=mem.User_Menu();				
			
			}//end of main menu loop
				
			mem.Exit_YMCA_Program();
			System.exit(0);
		
		}//end of main method 
		
	}

//***************************************************************************************************************************************

class members
{
	//Attributes
	int[] memberCode = new int[100];
	String[] memberType = new String[100];
	String[] memberFirst = new String [100];
	String[] memberLast = new String[100];
	int[] memberAge = new int[100];
	String[] memberGender = new String[100];

	int memberCount = -1;


	int[] memberCodereg = new int [100];
	int[] classCode = new int[100];
	int[] classCodereg = new int[100];
	
	int regCount = -1;


	String[] className = new String[100];
	String[] classStartdate = new String[100];
	
	int classCount = -1;
			
	
	
//       		******************************* SUB METHODS BEGIN ************************************ 				  //
		
//************************ MEMBER FILE READ METHOD ****************************	
			
			 void Begin_read_member()	
			{
				String newLine; 
				try
				{
					BufferedReader member_file = new BufferedReader(new FileReader("member.dat"));
					while ((newLine = member_file.readLine()) !=null)
					{
						StringTokenizer delimiter = new StringTokenizer(newLine,"#");
						memberCount = memberCount + 1;
						memberCode[memberCount] = Integer.parseInt(delimiter.nextToken());
						memberType[memberCount] = delimiter.nextToken();
						memberFirst[memberCount] = delimiter.nextToken();
						memberLast[memberCount] = delimiter.nextToken();
						memberAge[memberCount] = Integer.parseInt(delimiter.nextToken());
						memberGender[memberCount] = delimiter.nextToken();			
					}
					member_file.close();
				}	
				catch (IOException error)
				{
					System.out.println("ERROR ON MEMBER FILE READ " + error);
				}
				
				//return memberCount;
			}
		
			//************************ CLASS FILE READ METHOD ****************************	
			 void Begin_read_class()
			{
				String newLine;
				try
				{
				
					BufferedReader class_file = new BufferedReader(new FileReader("class.dat"));
					while ((newLine = class_file.readLine()) !=null)
					{
						StringTokenizer delimiter = new StringTokenizer(newLine,"#");
						classCount = classCount + 1;
						classCode[classCount] = Integer.parseInt(delimiter.nextToken());
						className[classCount] = delimiter.nextToken();
						classStartdate[classCount] = delimiter.nextToken();
						
			
					}
					class_file.close();
				}	
				catch (IOException error)
				{
					System.out.println("ERROR ON CLASS FILE READ " + error);
				}
			
				//return classCount;
			}
			
			//************************ REGISTER FILE READ METHOD ****************************			
			 void Begin_read_register()
			{
				String newLine;
				try
				{
				
					BufferedReader register_file = new BufferedReader(new FileReader("register.dat"));
					while ((newLine = register_file.readLine()) !=null)
					{
						StringTokenizer delimiter = new StringTokenizer(newLine,"#");
						regCount = regCount + 1;
						memberCodereg[regCount] = Integer.parseInt(delimiter.nextToken());
						classCodereg[regCount] = Integer.parseInt(delimiter.nextToken());
					
					}
					register_file.close();
				}	
				catch (IOException error)
				{
					System.out.println("ERROR ON REGISTER FILE READ " + error);
				}
			
				//return regCount;
			}
			
			
			//************************************************ USER USER MENU METHOD DISPLAY *************************************************				
			 int User_Menu()
			{
				int selection; 
				String value,output=
						"***********  Moon Area YMCA Main Menu ***********"+"\n"+
						""+"\n"+
						"1. Add/Modify/Delete Membership Info"+"\n"+
						"2. Add Registration"+"\n"+
						"3. Report Section"+"\n"+
						""+"\n"+
						"0. Exit Program (Quit & Save)"+"\n"+
						""+"\n"+
						"Please make your selection>";		
				value=JOptionPane.showInputDialog(null, output,"Input Data",JOptionPane.QUESTION_MESSAGE);
				selection=Integer.parseInt(value);
				return selection;		
			}
			
			
			//************************ ADD/MODIFY/DELETE SUB-MENU MEMBER METHOD ****************************							
			 void Add_Modify_Delete_Member()
			{		
				System.out.println("Executing ADD/Modify/Delte");	
				
					String selection;
					String output =
									"A. Add New Member to Database"+"\n"+
							 		"M. Modify Current Member Info"+"\n"+
									"D. Delete Member from Database"+"\n"+
							 		"R. Delete Member from Register"+"\n"+
									"X. Exit"+"\n"+
									""+"\n"+
									"Please make your selection ==>";
					selection=JOptionPane.showInputDialog(null, output," ",JOptionPane.QUESTION_MESSAGE).toUpperCase();
				
				while(!selection.equals("X"))
				{
					if(selection.equals("A"))
						Add_Member();
					
					else
						if(selection.equals("M"))
						Modify_Member();
						
					else
						if(selection.equals("D"))
						Delete_Member();
					
					else
						if(selection.equals("R"))
						Delete_Register();
						
					
							
					output =
								"A. Add New Member to Database"+"\n"+
						 		"M. Modify Current Member Info"+"\n"+
								"D. Delete Member from Database"+"\n"+
								"R. Delete Member from Register"+"\n"+
								"X. Exit"+"\n"+
								""+"\n"+
								"Please make your selection ==>";
					selection=JOptionPane.showInputDialog(null, output," ",JOptionPane.QUESTION_MESSAGE).toUpperCase();
									
				}
					
				System.out.println("Executed ADD/Modify/Delte");
			    //return memberCount;//Main_Menu();
			}
			
		
			
			//************************ Add New Member Info sub menu method ****************************
			 void Add_Member()
				{
					System.out.println("Executing ADD New Member");
					String output,value;
					memberCount=memberCount+1;
					
					output="Enter New Member Code ===>";
					value=JOptionPane.showInputDialog(null,output," ",JOptionPane.QUESTION_MESSAGE);
					memberCode[memberCount]=Integer.parseInt(value);
					
					output="Enter New Membership Type ===>";
					memberType[memberCount]=JOptionPane.showInputDialog(null, output," ",JOptionPane.QUESTION_MESSAGE);
					
					output="Enter First Name ===>";
					memberFirst[memberCount]=JOptionPane.showInputDialog(null, output," ",JOptionPane.QUESTION_MESSAGE);
					
					output="Enter Last Name ===>";
					memberLast[memberCount]=JOptionPane.showInputDialog(null, output," ",JOptionPane.QUESTION_MESSAGE);
					
					output="Enter Member Age ===>";
					value=JOptionPane.showInputDialog(null,output," ",JOptionPane.QUESTION_MESSAGE);
					memberAge[memberCount]=Integer.parseInt(value);
					
					output="Enter Gender ===>";
					memberGender[memberCount]=JOptionPane.showInputDialog(null, output," ",JOptionPane.QUESTION_MESSAGE).toLowerCase();
					
					System.out.println("Executed ADD New Member");
					//return memberCount;
				}
			//************************ Modify Member Info sub menu method ****************************		
			 void Modify_Member()
				{
					System.out.println("Executing Modify Member Info");
					String output,value;
					int memIDvalue,position=0,i=0,selection;
					output="Enter Member ID to Modify ==>";
					value=JOptionPane.showInputDialog(null,output," ",JOptionPane.QUESTION_MESSAGE);
					memIDvalue=Integer.parseInt(value);
					
					//find the index of the number to modify
			//		while (memberCode[i]!=memIDvalue)
			//		{		
			//			output="Unable to Locate Member ID"+"\n"+
			//					"Please Try again ==>";
			//			value=JOptionPane.showInputDialog(null,output," ",JOptionPane.QUESTION_MESSAGE);
			//			memIDvalue=Integer.parseInt(value);
			//		}
								
					
					for(i=0;i<=memberCount;++i)
					{
						if(memberCode[i]==memIDvalue) position =i;
									
					}
					//modify the proper member data
					output="1. Change Member First Name"+"\n"+
							"2. Change Member Last Name"+"\n"+
							"3. Change Membership Type"+"\n"+
							"4. Change Membership Age"+"\n"+
							"5. Change Membership Gender"+"\n"+
							"0. Exit Change Member Info"+"\n"+
							""+"\n"+
							"Enter your selection ==>";
					value=JOptionPane.showInputDialog(null,output," ",JOptionPane.QUESTION_MESSAGE);
					selection=Integer.parseInt(value);
					
					
					if(selection == 1)
						{
						//read input new first name
						output="Enter Member's New First Name ==>";
						memberFirst[position]=JOptionPane.showInputDialog(null,output," ",JOptionPane.QUESTION_MESSAGE);
						}
					else
						if(selection==2)
							{
							//read input new last name 
							output="Enter Member's New Last Name ==>";
							memberLast[position]=JOptionPane.showInputDialog(null,output," ",JOptionPane.QUESTION_MESSAGE);
							}
					else
						if(selection==3)
							{
							//read input new membership type 
							output="Enter Member's New Membership Type ==>";
							memberType[position]=JOptionPane.showInputDialog(null,output," ",JOptionPane.QUESTION_MESSAGE);
							}
					else
						if(selection==4)
							{
							//read input new age  
							output="Enter Member's New Age  ==>";
							value=JOptionPane.showInputDialog(null,output," ",JOptionPane.QUESTION_MESSAGE);
							memberAge[position]=Integer.parseInt(value);
							}
					else
						if(selection==5)
							{
							//read input new membership type 
							output="Enter Member's New Gender Type ==>";
							memberGender[position]=JOptionPane.showInputDialog(null,output," ",JOptionPane.QUESTION_MESSAGE).toLowerCase();
							}
					else
						if(selection==0)
							{
							Add_Modify_Delete_Member();
							}			
					System.out.println("Executed Modify Member Info");
				}
			
			//************************ Delete Member Info sub menu method ****************************		
			 void Delete_Member()
				{
					System.out.println("Executing Delete Member");
					
					String output,value;
					int delvalue,position=0,i;
					output="Enter Member ID to Delete ==>";
					value=JOptionPane.showInputDialog(null,output," ",JOptionPane.QUESTION_MESSAGE);
					delvalue=Integer.parseInt(value);
					for(i=0;i<=memberCount;++i)
					{
						if(memberCode[i] == delvalue) position = i;
					}
					for(i=position;i<=memberCount-1;++i)
						{
							memberCode[i]=memberCode[i+1];
							memberType[i]=memberType[i+1];
							memberFirst[i]=memberFirst[i+1];
							memberLast[i]=memberLast[i+1];
							memberAge[i]=memberAge[i+1];
							memberGender[i]=memberGender[i+1];
						}
						
				System.out.println("Executed Delte Member");	
				memberCount=memberCount-1;
				//return memberCount;
				}
			 
			 
			//************************ Delete Member Info sub menu method ****************************		
			 void Delete_Register()
				{
					System.out.println("Executing Delete Member from Register");
					
					String output,value;
					int delvalue,position=0,i;
					output="Enter Member ID to Delete from Register ==>";
					value=JOptionPane.showInputDialog(null,output," ",JOptionPane.QUESTION_MESSAGE);
					delvalue=Integer.parseInt(value);
					for(i=0;i<=regCount;++i)
					{
						if(memberCodereg[i] == delvalue) position = i;
					}
					for(i=position;i<=regCount-1;++i)
						{
							memberCodereg[i]=memberCodereg[i+1];
							classCodereg[i]=classCodereg[i+1];
						}
						
				System.out.println("Executed Delte Member from Register");	
				regCount=regCount-1;
				//return memberCount;
				}
			
			
			//************************ Print All Member Info sub menu method ****************************
			 void Print_Member_Info()
			{
				int i;
				System.out.println("\n"+"All Member Information"+"\n"+"========================================");
				for(i=0;i<=memberCount;++i)
			
				{
					System.out.println(memberCode[i]+" "+memberType[i]+" "+memberFirst[i]+" "+memberLast[i]+" "+memberAge[i]+" "+memberGender[i]);
				}
			}
							
							
		
			
			//************************ MODIFY REGISTER INFO METHOD ****************************		
			 void Modify_Registser()
			{
				System.out.println("Executing Modify Registration");
					
				
				String selection,value;
				String output =
								"A. Add New Member Registration"+"\n"+
								"X. Exit"+"\n"+
								""+"\n"+
								"Please make your selection ==>";
								selection=JOptionPane.showInputDialog(null, output," ",JOptionPane.QUESTION_MESSAGE).toUpperCase();
			
			while(!selection.equals("X"))
			{
				regCount=regCount+1;
				output="Enter Member's ID  ==>";
				value=JOptionPane.showInputDialog(null,output," ",JOptionPane.QUESTION_MESSAGE);
				memberCodereg[regCount]=Integer.parseInt(value);
				output="Enter Class Code ==>";
				value=JOptionPane.showInputDialog(null,output," ",JOptionPane.QUESTION_MESSAGE);
				classCodereg[regCount]=Integer.parseInt(value);
				
		
				output =
							"A. Add New Member Registration"+"\n"+
							"X. Exit"+"\n"+
							""+"\n"+
							"Please make your selection ==>";
							selection=JOptionPane.showInputDialog(null, output," ",JOptionPane.QUESTION_MESSAGE).toUpperCase();
								
			}
				
			System.out.println("Executed Modify Registration");
		    //return regCount;
		}
		
			
			//************************ CREATE DISPLAY/REPORT METHOD ****************************		
			 void Report()
				{
				int searchCode,selection,i=0,j=0,k=0;
				String value;
				String output=  "       YMCA REPORT MENU       "+"\n"+
								"============================"+"\n"+
								"1. Dispaly all Members"+"\n"+
								"2. Display all Class info"+"\n"+
								"3. Display all Register info"+"\n"+
								"4. Display Specific Member Registration"+"\n"+
								"5. Display Members by Gender"+"\n"+
								"6. Display Members within Age Range"+"\n"+
								"7. Display Members NOT Registered"+"\n"+
								"8. Display All Members for Specific Class"+"\n"+
								"9. Display Each Class with All Members"+"\n"+
								"0. Exit Report Menu"+"\n"+
								"Enter your selection => ";
				
				value = JOptionPane.showInputDialog(null,output,"Input Data",JOptionPane.QUESTION_MESSAGE);
				selection = Integer.parseInt(value);
				
				
				while(selection != 0)
				{
					if(selection == 1)
						{
						System.out.println(" ");
						System.out.println("     Report 1--ALL MEMBERS INFO LIST     ");
						System.out.println("   ====================================  ");
						System.out.println("CODE"+"\t"+"TYPE"+"\t"+"FIRST"+"\t"+"LAST"+"\t"+"AGE"+"\t"+"GENDER");
							for(i=0;i<=memberCount;++i)
							{
								System.out.println(memberCode[i]+"\t"+memberType[i]+"\t"+memberFirst[i]+"\t"+
										memberLast[i]+"\t"+memberAge[i]+"\t"+memberGender[i]);
							}
						}//end of report menu 1--all members list
					else
						if(selection == 2)
						{
							System.out.println(" ");
							System.out.println("     Report 2--ALL CLASS INFO LIST    ");
							System.out.println("   =================================  ");
							System.out.println("CLASS"+"\n"+"CODE"+"\t"+"CLASS NAME"+"\t"+"\t"+"START DATE");
								for(i=0;i<=classCount;++i)
								{
									System.out.println(classCode[i]+"\t"+className[i]+"   "+"\t"+classStartdate[i]);
								}	
						
						}//end of report menu 2--all class info
					else
						if(selection == 3)
						{
							System.out.println(" ");
							System.out.println("     Report 3--ALL REGISTER INFO LIST    ");
							System.out.println("   ====================================  ");
							System.out.println("MEMBER CODE"+"\t"+"CLASS CODE");
								for(i=0;i<=regCount;++i)
								{
									System.out.println(memberCodereg[i]+"\t"+"\t"+classCodereg[i]);
								}	
						}//end of report menu 3--all register info
					
					else
						if(selection == 4)
						{	
							System.out.println(" ");
							System.out.println("     Report 4--SPECIFIC MEMBER REGISTRATION   ");
							System.out.println("   ==========================================  ");
	
							value=JOptionPane.showInputDialog(null, "Enter Member ID to Search ==>"," ",JOptionPane.QUESTION_MESSAGE);
							searchCode = Integer.parseInt(value);
							for(i=0;i<=memberCount;++i)
								{
								if(searchCode == memberCode[i])			
									{
										System.out.println(memberFirst[i]+"\t"+memberLast[i]+" is registered for the following classes:"+"\n");
										for(j=0;j<=regCount;++j)
										{
											if(memberCode[i]==memberCodereg[j])
											{
												for(k=0;k<=classCount;++k)
												{
													if(classCodereg[j]==classCode[k])
													System.out.println(classCode[k]+"\t"+className[k]+"\t"+classStartdate[k]);
												}
											}
										}
									}		
								}
						}
					//end of report menu 4--by specific member registration list
					
					else
						if(selection == 5)
						{	String searchMale="male",searchFemale="female",searchGender,output5;
							System.out.println("     Report 5--Specific Gender Member List   ");
							System.out.println("================================================"+"\n");
							output5="To list all male members"+"\n"+"enter M or F for females ====>";
							searchGender=JOptionPane.showInputDialog(null,output5," ",JOptionPane.QUESTION_MESSAGE).toUpperCase();
							if(searchGender.equals("M"))
							{
								searchGender=searchMale;
								System.out.println("Searching for all members whom are "+searchGender+"."+"\n");
								for(i=0;i<=memberCount;++i)
								{
									if(searchGender.equals(memberGender[i]))
									{
										System.out.println(memberFirst[i]+"\t"+memberLast[i]+"\t"+memberGender[i]);	
									}
								}
							}	
							else
								if(searchGender.equals("F"))
								{
									searchGender=searchFemale;
									System.out.println("Searching for all members whom are "+searchGender+"."+"\n");
									for(i=0;i<=memberCount;++i)
									{
										if(searchGender.equals(memberGender[i]))
										{
												System.out.println(memberFirst[i]+"\t"+memberLast[i]+"\t"+memberGender[i]);	
										}		
									}
								}							
						}//end of report menu 5			
					else
						if(selection == 6)
						{	int ageLow,ageHigh;
							String output6,ageInput;
							System.out.println("     Report 6--Search by Age Range    ");
							System.out.println("=======================================");
							output6="Enter LOWEST age in range ====>";
							ageInput=JOptionPane.showInputDialog(null,output6," ",JOptionPane.QUESTION_MESSAGE);
							ageLow=Integer.parseInt(ageInput);
							
							output6="Enter HIGHEST age in range ====>";
							ageInput=JOptionPane.showInputDialog(null,output6," ",JOptionPane.QUESTION_MESSAGE);
							ageHigh=Integer.parseInt(ageInput);
							
							System.out.println("Searching for all members whoes ages"+"\n"+"are between "+ageLow+" and "+ageHigh+"."+"\n");
							for(i=0;i<=memberCount;++i)
								{
									if(memberAge[i] >= ageLow && memberAge[i] <= ageHigh)
									{
										System.out.println(memberFirst[i]+"\t"+memberLast[i]+"\t"+memberAge[i]);
									}
									
								}			
						}
					//end of report menu 6		
				
					else
						if(selection == 7)
						{		
							System.out.println("     	Report 7--");
							System.out.println("     	Members NOT Registered for any Classes");
							System.out.println("==============================================="+"\n");
							for(i=0;i<=memberCount;i++)
							{							
								for(j=0;j<=regCount;j++)
									{
										if(memberCode[i] == memberCodereg[j])
										{
											i++;			
										}
									}	
							System.out.println(memberFirst[i]+"\t"+memberLast[i]+"\t"+memberCode[i]);
									
							}	
						}			
						
					//end of report menu 7					 
					
					
					else
						if(selection == 8)
						{	
							System.out.println("     Report 8--");
							System.out.println("     All Members of a Specific Class    ");
							System.out.println("=========================================="+"\n");
							String output8,classInput;
							int searchClass;
							output8="To list all members in a class"+"\n"+"enter class code ====>";
							classInput=JOptionPane.showInputDialog(null,output8," ",JOptionPane.QUESTION_MESSAGE);
							searchClass=Integer.parseInt(classInput);
							for(i=0;i<=classCount;++i)
							{
								if(searchClass == classCode[i])
								{
									System.out.println("The following members are");
									System.out.println("registered for "+className[i]+":"+"\n");
									for(j=0;j<=regCount;++j)
									{
										if(classCode[i] == classCodereg[j])
										{
											for(k=0;k<=memberCount;++k)
											{
												if(memberCodereg[j] == memberCode[k])
												{
													System.out.println(memberCode[k]+" "+memberFirst[k]+" "+memberLast[k]+" "+classStartdate[k]);	
												}
												
											}
										}
									}
								}														
							}
						}
					//end of report menu 8
							
					else
						if(selection == 9)
						{	
							System.out.println("     Report 9--    ");
							System.out.println("     Each Class with All Members Listed    ");
							System.out.println("=========================================="+"\n");
							
							//loop-- for the total amount of classes to iterate task below (8x)
							for(i=0;i<=classCount;++i)
							{
								System.out.println("\n"+classCode[i]+"\t"+className[i]+":");		
								for(j=0;j<=regCount;++j)	
								{	
									if(classCode[i] == classCodereg[j])
									{	
										for(k=0;k<=memberCount;++k)
										{	
											if(memberCodereg[j] == memberCode[k])
												{
													System.out.println(memberCode[k]+"\t" +memberFirst[k]+" "+memberLast[k]);
												}		
										}	
									}
								}
							}		
								
						}			
					//end of report menu 9					
							
				//repeat report menu
				output =
						"       YMCA REPORT MENU       "+"\n"+
						"============================"+"\n"+
						"1. Dispaly all Members"+"\n"+
						"2. Display all Class info"+"\n"+
						"3. Display all Register info"+"\n"+
						"4. Display Specific Member Registration"+"\n"+
						"5. Display Members by Gender"+"\n"+
						"6. Display Member within Age Range"+"\n"+
						"7. Display Members NOT Registered"+"\n"+
						"8. Display All Members for Specific Class"+"\n"+
						"9. Display Each Class with All Members"+"\n"+
						"0. Exit Report Menu"+"\n"+
						"Enter your selection => ";
				value = JOptionPane.showInputDialog(null,output,"Input Data",JOptionPane.QUESTION_MESSAGE);
				selection = Integer.parseInt(value);
				}//end of while loop
				
			}//end of report method
					
			 //************************ EXIT YMCA PROGRAM METHOD SAVE AND QUIT ****************************	
			 void Exit_YMCA_Program()
			{	
				{
					System.out.println("\n"+"Executing Exit_YMCA_Program");
				}
				
				//Write Member.dat
				int i;
				try
				{
					BufferedWriter member_file = new BufferedWriter(new FileWriter("member.dat"));
					for(i=0; i<= memberCount;++i)
					{
						member_file.write(memberCode[i]+"#"+memberType[i]+"#"+memberFirst[i]+"#"+memberLast[i]+"#"+memberAge[i]+"#"+memberGender[i]+"#");
						member_file.newLine();		
					}
					member_file.close();
				}
				catch(IOException error)
					{
					System.out.println("ERROR ON MEMBER FILE READ" + error);
					
					}
				
				
				//Write Register.dat
				int j;
				try
				{
					BufferedWriter register_file = new BufferedWriter(new FileWriter("register.dat"));
					for(j=0; j<= regCount;++j)
					{
						register_file.write(memberCodereg[j]+"#"+classCodereg[j]+"#");
						register_file.newLine();		
					}
					register_file.close();
				}
				catch(IOException error)
					{
					System.out.println("ERROR ON REGISTER FILE READ" + error);
					
					}
			}
			
			
			
		}
			
			
			