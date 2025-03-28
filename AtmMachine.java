import java.util.Scanner;

class bankingAtm{
    double balance;

    public bankingAtm(double initialBalance)
    {
        balance = (initialBalance >= 0) ? initialBalance : 0;
    }

    public void withdraw(double amount){
        if(amount>0 && amount>=balance){
            balance-=amount;
            System.out.println("Withdraw successfully remaining balance is $"+balance);
        }else if(amount>balance){
            System.out.println("insufficient balance!");
        }else{
            System.out.println("invalid withdraw amount!");
        }
    }
    public void deposit(double amount){
        if(amount > 0){
            balance+=amount;
            System.out.println("current balance is $"+balance);
        }
        else{
            System.out.println("invalid deposit amount...!");
        }
    }
    public double checkbalance(){
        return balance;
    }
}
class ATM{
    private bankingAtm account;
    private Scanner sc;
    public ATM(bankingAtm usersccount){
        this.account=usersccount;
        this.sc=new Scanner(System.in);
    }
    public void showmenu(){
        int choice;
        double amount;
        do {
            System.out.println("\n===== ATM Menu =====");
            System.out.println("1. Deposit Money");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    amount = sc.nextDouble();
                    account.deposit(amount);
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    amount = sc.nextDouble();
                    account.withdraw(amount);
                    break;
                case 3:
                    System.out.println("Current Balance: $" + account.checkbalance());
                    break;
                case 4:
                    System.out.println("Thank you for using our ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 4);

    }
}
public class AtmMachine {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter initial account balance: ");
        double initialBalance = sc.nextDouble();
        bankingAtm userAccount = new bankingAtm(initialBalance);
        ATM atm = new ATM(userAccount);
        atm.showmenu();

    }
}
