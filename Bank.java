
/*Look at the Account class Account.java and write a main method in a different class to briefly experiment with some instances of the Account class.

Using the Account class as a base class, write two derived classes called SavingsAccount and CurrentAccount. A SavingsAccount object, in addition to the attributes of an Account object, should have an interest variable and a method which adds interest to the account. A CurrentAccount object, in addition to the attributes of an Account object, should have an overdraft limit variable. Ensure that you have overridden methods of the Account class as necessary in both derived classes.
Now create a Bank class, an object of which contains an array of Account objects. Accounts in the array could be instances of the Account class, the SavingsAccount class, or the CurrentAccount class. Create some test accounts (some of each type).
Write an update method in the bank class. It iterates through each account, updating it in the following ways: Savings accounts get interest added (via the method you already wrote); CurrentAccounts get a letter sent if they are in overdraft.
The Bank class requires methods for opening and closing accounts, and for paying a dividend into each account.
Hints:
Note that the balance of an account may only be modified through the deposit(double) and withdraw(double) methods.
The Account class should not need to be modified at all.
Be sure to test what you have done after each step.*/

/**
   A class for bank accounts.
   
   This class provides the basic functionality of accounts.
   It allows deposits and withdrawals but not overdraft
   limits or interest rates.   
   @author Mayur Jain
*/

import java.util.*;

class Account
{
    protected double bal;  //The current balance
    protected int accnum;  //The account number
    
    
    public Account(int a)
    {    
    bal=0.0;
    accnum=a;
    }
    
    public void deposit(double sum)
    {
    if (sum>0)
        bal+=sum;    
    else
        System.err.println("Account.deposit(...): "
                   +"cannot deposit negative amount.");    
    }
    
    public void withdraw(double sum)
    {
    if (sum>0)
        bal-=sum;    
    else
        System.err.println("Account.withdraw(...): "
                   +"cannot withdraw negative amount.");    
    }
    
    public double getBalance()
    {
    return bal;
    }
    
    public double getAccountNumber()
    {
    return accnum;
    }
    
    public String toString()
    {
    return "Acc " + accnum + ": " + "balance = " + bal;    
    }
    
    public final void print()
    {
    //Don't override this,
    //override the toString method
    System.out.println( toString() );    
    }
    
}

class SavingsAccount extends Account{

    private double interest;

    SavingsAccount(int a){
        super(a);
    	interest=0.0;
    }  

    public void addInterest(double a){
        interest=a;
    }
    
    public void deposit(double sum)
    {
    if (sum>0)
        bal+=sum;    
    else
        System.err.println("Account.deposit(...): "
                   +"cannot deposit negative amount.");    
    }
    
    public void withdraw(double sum)
    {
    if (sum>0)
        bal-=sum;    
    else
        System.err.println("Account.withdraw(...): "
                   +"cannot withdraw negative amount.");    
    }
    
    public double getBalance()
    {
    return bal;
    }
    
    public double getAccountNumber()
    {
    return accnum;
    }
    
    public String toString()
    {
    return "Acc " + accnum + ": " + "balance = " + bal + " Interest :"+interest ;    
    }
    
}

class CurrentAccount extends Account{

    private double overdraft_lim;

    CurrentAccount(int a){
    	super(a);
    	overdraft_lim=0.0;
    }  

    public void addOverdraftLimit(double a){
        overdraft_lim=a;
    }
    public void deposit(double sum)
    {
    if (sum>0)
        bal+=sum;    
    else
        System.err.println("Account.deposit(...): "
                   +"cannot deposit negative amount.");    
    }
    
    public void withdraw(double sum)
    {
    if (sum>0 && sum>overdraft_lim)
        bal-=sum;    
    else
        System.err.println("Please Enter a valid amout greater than "+overdraft_lim+" (overdraft_lim)");    
    }
    
    public double getBalance()
    {
    return bal;
    }
    
    public double getAccountNumber()
    {
    return accnum;
    }
    
    public String toString()
    {
    return "Acc " + accnum + ": " + "balance = " + bal;    
    }
    
}
public class Bank{
	
	private static SavingsAccount arr_savAccounts[];
	private static CurrentAccount arr_currAccounts[];
	
    public static void main(String args[]){
    	Scanner sc = new Scanner(System.in);
    	arr_savAccounts=new SavingsAccount[100];
    	arr_currAccounts=new CurrentAccount[100];
    	int sav_count=0;
    	int curr_count=0;
    	int sav_acc_n=0;
    	int curr_acc_n=0;
    	while(true) {
    		System.out.println("****************************************************");
    		System.out.println("Welcome to Singhaniya Banking Services \nEnter: \n1. To Create Savings Account \n2. "
    				+ "To Create current Account \n3. To Access your account");
    		switch (sc.nextInt()) {
			case 1:
				arr_savAccounts[sav_count]=new SavingsAccount(sav_count);
				System.out.println("Saving Account opened with number : "+sav_count);
				System.out.println("Enter the Interest on this account : ");
				arr_savAccounts[sav_count].addInterest(sc.nextDouble());
				System.out.println("Thank you");
				sav_count++;
				break;
			
			case 2:
				arr_currAccounts[curr_count]=new CurrentAccount(curr_count);
				System.out.println("Current Account opened with number : "+curr_count);
				System.out.println("Enter the Over dreaft limit on this account :");
				arr_currAccounts[curr_count].addOverdraftLimit(sc.nextDouble());
				System.out.println("Thank you");
				curr_count++;
				break;
				
			case 3:
				System.out.println("What type of account do you have \nEnter : \n1. If Savings \n2. If"
						+ " Current");
				switch (sc.nextInt()) {
				case 1:
					System.out.println("Enter your account number");
					sav_acc_n=sc.nextInt();
					if(sav_acc_n<sav_count) {
						System.out.println("Enter \n1. To Deposit \n2. To Widthraw "
								+ "\n3. To View balance");
						switch (sc.nextInt()) {
						
						case 1:
							System.out.println("Enter amount to deposit");
							arr_savAccounts[sav_acc_n].deposit(sc.nextDouble());
							break;
							
						case 2:
							System.out.println("Enter amount to Widthraw");
							arr_savAccounts[sav_acc_n].withdraw(sc.nextDouble());
							break;
							
						case 3:
							arr_savAccounts[sav_acc_n].print();
							break;
							
						default:
							break;
						}
					}
					else {
						System.out.println("Invalid Account number");
					}
					break;

				case 2:
					System.out.println("Enter your account number");
					curr_acc_n=sc.nextInt();
					if(curr_acc_n<curr_count) {
						System.out.println("Enter \n1. To Deposit \n2. To Widthraw "
								+ "\n3. To View balance");
						switch (sc.nextInt()) {
						
						case 1:
							System.out.println("Enter amount to deposit");
							arr_currAccounts[curr_acc_n].deposit(sc.nextDouble());
							break;
							
						case 2:
							System.out.println("Enter amount to Widthraw");
							arr_currAccounts[curr_acc_n].withdraw(sc.nextDouble());
							break;
							
						case 3:
							arr_currAccounts[curr_acc_n].print();
							break;
							
						default:
							break;
						}
					}
					else {
						System.out.println("Invalid Account number");
					}
				default:
					break;
				}
				
			default:
				
				break;
			}
    	}
    }
}

