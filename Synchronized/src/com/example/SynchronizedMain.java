package com.example;

public class SynchronizedMain {

    public static void main(String[] args) {
        Store store = Store.getInstance();
        store.addShirt(new Shirt("1", "Polo", "Rainbow", "Large"));
        store.addShirt(new Shirt("2", "Toyota", "Black", "Large"));

        PracticeThread p1 = new PracticeThread();
        PracticeThread p2 = new PracticeThread(); 
        p1.start();
        p2.start();

        Thread t = Thread.currentThread();
        System.out.println("Thread:"+t.getName() + "," + t.getId());

        synchronized (store){
            if (store.getShirtCount() > 0 && store.authorizeCreditCard("1234", 15.00)){
               Shirt shirt = store.takeShirt();
                System.out.println("The shirt is ours");
            }
            else {
                System.out.println("No shirt for you");
            }
        }
    }
}