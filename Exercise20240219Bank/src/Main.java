//we make the account class with the fields asked of us
class Account {
    //we initialize variables
    private String ownerName;
    private int accountNumber;
    private double balance;

    public Account(String ownerName,int accountNumber,double balance){
        this.ownerName=ownerName;
        this.accountNumber=accountNumber;
        this.balance=balance;
    }

    //we make a deposit method
    public void deposit(double amount){
        if(amount>0){
            balance+=amount;
        }else{
            System.out.println("Must enter a positive deposit amount.");
        }
    }

    //we make a withdraw method

    public void withdraw(double amount){
        if(amount>0&&amount<=balance){
            balance-=amount;
        }else{
            System.out.println("Can only withdraw an amount smaller than your deposit, and greater than zero.");
        }
    }

    //method to see balance

    public double getBalance(){
        return balance;
    }
}

//we make a bank class with some example actions

class Bank{
    public static void main(String[] args) {
        Account account1=new Account("Agent Smith",000666,600000);
        Account account2=new Account("Mr Anderson",000333,150);
        //we do some desposit and withdrawal examples
        account1.deposit(6000000.0);
        account2.withdraw(149.0);
        //we print the balance for each example account
        System.out.println("Your balance is: "+account1.getBalance());
        System.out.println("Your balance is: "+account2.getBalance());
    }
}