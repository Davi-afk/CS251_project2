/**
 * CS 251: Data Structures and Algorithms
 * Project 2: Part 2
 *
 * TODO: Complete CustomerOrderHash.
 *
 * @author Chirayu Garg, Jordan Davis
 * @username garg104, davi1304
 * @sources
 */


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;


public class CustomerOrderHash {
    private ArrayList[] table;
    private int numOrders;
    private int tableCapacity;

    /**
     * Constructor of the class.
     * TODO: complete the default Constructor of the class
     *
     * Initilalize a new CustomerOrder array with the argument passed.
     *
     */
    public CustomerOrderHash(int capacity) {
        if (isPrime(capacity))
        {
            table = new ArrayList[capacity];
        }
        else {
            table = new ArrayList[getNextPrime(capacity)];
        }
    }


    /**
     *
     * TODO: return the CustomerOrder with the given name
     * TODO: return null if the CustomerOrder is not in the table
     *
     */
    public CustomerOrder get(String name) throws NoSuchAlgorithmException {
        CustomerOrder temp = null;
        for (int i = 0; i < table.length; i++)
        {
            if (table[i] != null)
            {
                for (int j = 0; j < table[i].toArray().length; j++)
                {
                    ArrayList temp2;
                    temp2 = table[i];
                    CustomerOrder a;
                    a = (CustomerOrder) temp2.get(j);
                    if (a.getName().equals(name)) {
                        temp = a;
                        break;
                    }

                }
            }
        }
        return temp;
    }


    /**
     *
     * TODO: put CustomerOrder c into the table
     *
     */
    public void put(CustomerOrder c) throws NoSuchAlgorithmException {
        try{
            int H = 0;
            int N = table.length;
            MessageDigest d = MessageDigest.getInstance("SHA-256");
            byte[] h = d.digest(c.getName().getBytes());
            final StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < h.length; i++) {
                final String hex = Integer.toHexString(0xff & h[i]);
                if(hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            String S = hexString.toString();
            if ((S.hashCode() % N) < 0)
            {
                H = S.hashCode() % N;
                H = H + N;
            }
            else
            {
                H = S.hashCode() % N;
            }
            boolean add = true;
            if (table[H] != null)
            {
                for (int i = 0; i < table[H].toArray().length; i++)
                {
                        ArrayList temp2;
                        temp2 = table[H];
                        CustomerOrder a;
                        a = (CustomerOrder) temp2.get(i);
                        if (a.getName().equals(c.getName())) {
                            add = false;
                            break;
                        }
                }
            }
            if (add)
            {
                if (table[H] == null) {
                    ArrayList temp = new ArrayList<CustomerOrder>();
                    temp.add(c);
                    table[H] = temp;
                    numOrders++;
                }
                else
                {
                    table[H].add(c);
                    numOrders++;
                }
            }
        } catch(NoSuchAlgorithmException e){
            System.out.println("Exception thrown: " + e);
        }

    }



    /**
     *
     * TODO: remove and return the CustomerOrder with the given name;
     * TODO: return null if CustomerOrder doesn't exist
     *
     */
    public CustomerOrder remove(String name) throws NoSuchAlgorithmException {
        CustomerOrder temp = null;
        for (int i = 0; i < table.length; i++)
        {
            if (table[i] != null)
            {
                for (int j = 0; j < table[i].toArray().length; j++)
                {
                    ArrayList temp2;
                    temp2 = table[i];
                    CustomerOrder a;
                    a = (CustomerOrder) temp2.get(j);
                    if (a.getName().equals(name)) {
                        temp = a;
                        table[i].remove(j);
                        numOrders--;
                        break;
                    }

                }
            }
        }
        return temp;
    }


    /**
     *
     * TODO: return the number of Customers in the table
     *
     */
    public int size() {
        return numOrders;
    }



    //get the next prime number p >= num
    private int getNextPrime(int num) {
        if (num == 2 || num == 3)
            return num;

        int rem = num % 6;

        switch (rem) {
            case 0:
            case 4:
                num++;
                break;
            case 2:
                num += 3;
                break;
            case 3:
                num += 2;
                break;
        }

        while (!isPrime(num)) {
            if (num % 6 == 5) {
                num += 2;
            } else {
                num += 4;
            }
        }

        return num;
    }

    //determines if a number > 3 is prime
    private boolean isPrime(int num) {
        if (num % 2 == 0) {
            return false;
        }

        int x = 3;

        for (int i = x; i < num; i += 2) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

    public ArrayList<CustomerOrder>[] getArray() {
        return this.table;
    }


}
