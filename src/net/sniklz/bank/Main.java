package net.sniklz.bank;

public class Main {

    public static final int DELAY = 10;
    public static final int STEPS = 100;
    public static final int NCOUNTS = 100;
    public static final double MAX_AMOUNT = 1000;

    public static final double INITIAL_BALANCE = 1000;

    public static void main(String[] args) {
        var bank = new BankSynchronized(NCOUNTS, INITIAL_BALANCE);
        for(int i =0; i< NCOUNTS; i++)
        {
            int fromAccount = i;
            Runnable task1 = () ->
            {
                try {
                    while (true) {
                        int toAccount = (int) (bank.size() * Math.random());

                        double amount = MAX_AMOUNT * Math.random();
                        bank.transfer(fromAccount, toAccount, amount);
                        Thread.sleep((int) (DELAY * Math.random()));
                    }
                } catch (InterruptedException e) {
                }
            };
            var t = new Thread(task1);
            t.start();
        }
    }
}