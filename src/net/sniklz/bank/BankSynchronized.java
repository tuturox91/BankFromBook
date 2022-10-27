package net.sniklz.bank;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankSynchronized{

    private  final double[] accounts;

    public BankSynchronized(int n, double initialBalance) {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
    }

    public synchronized double getTotalBalance() {
        double sum = 0;

        for(double a: accounts)
            sum+= a;
        return sum;
    }

    public  int size() {
        return accounts.length;
    }



    public synchronized void transfer(int from, int to, double amount) throws  InterruptedException
    {
        while (accounts[from] < amount)
            wait();
        System.out.print(Thread.currentThread());
        accounts[from] -= amount;
        System.out.printf("%10.2f from %d to %d ", amount, from, to);
        accounts[to] += amount;
        System.out.printf("Total Balance: %10.2f%n", getTotalBalance());
        notifyAll();
    }

}

