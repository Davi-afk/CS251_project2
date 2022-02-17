/**
 * CS 251: Data Structures and Algorithms
 * Project 2: Part 3
 * <p>
 *
 *
 * @author Chirayu Garg, Jordan Davis
 * @username garg104, davi1304
 * @sources
 */

import java.security.NoSuchAlgorithmException;

public class BetterCustomerOrderQueue {
    private CustomerOrder[] orderList;
    private CustomerOrderHash table;
    private int numOrders;

    /**
     *
     * Return the CustomerOrderQueue
     *
     */
    public CustomerOrder[] getOrderList() {
        return orderList;
    }

    /**
     *
     * Return the number of orders in the queue
     *
     */
    public int getNumOrders() {
        return numOrders;
    }


    /**
     * Constructor of the class.
     * TODO: complete the default Constructor of the class
     *
     * Initialize a new CustomerOrderQueue and CustomerOrderHash
     *
     */
    public BetterCustomerOrderQueue(int capacity) {

            table = new CustomerOrderHash(capacity);
            orderList = new CustomerOrder[capacity];

    }

    /**
     * TODO: insert a new customer order.
     *
     * @return return the index at which the customer was inserted;
     * return -1 if the Customer could not be inserted
     *
     */
    public int insert(CustomerOrder c) throws NoSuchAlgorithmException {
        int y = 0;
        try{
            if (table.get(c.getName()) != null)
            {
                y = -1;
            }
            else {
                table.put(c);
            }

        } catch(NoSuchAlgorithmException e){
            System.out.println("Exception thrown: " + e);
        }
        int x = -1;
        if ( y != -1) {
            for (int i = 0; i < orderList.length; i++) {
                if (orderList[i] == null) {
                    x = i;
                    c.setPosInQueue(i);
                    orderList[i] = c;
                    break;
                }
            }
            while (true) {
                if ((x == 0) || (x == -1)) {
                    break;
                } else if (orderList[x].compareTo(orderList[(x-1)/2]) == 1) {
                    CustomerOrder temp = orderList[(x - 1) / 2];
                    orderList[(x - 1) / 2] = orderList[x];
                    orderList[(x - 1) / 2].setPosInQueue((x - 1) / 2);
                    orderList[x] = temp;
                    orderList[x].setPosInQueue(x);
                    x = (x - 1) / 2;
                } else {
                    break;
                }
            }
            if (x == 0) {
            } else if (x == -1) {
            } else {
            }
        }
        if ( (x == -1) || (y == -1))
        {
            return - 1;
        }
        else {
            numOrders++;
            return x;
        }
    }

    /**
     * TODO: remove the customer with the highest priority from the queue
     *
     * @return return the customer removed
     *
     */
    public CustomerOrder delMax() throws NoSuchAlgorithmException {
        CustomerOrder temp;
        if (orderList[0] == null)
        {
            temp = null;
        }
        else
        {
            temp = orderList[0];
            table.remove(temp.getName());
            orderList[0] = orderList[numOrders -1];
            orderList[0].setPosInQueue(0);
            orderList[numOrders -1] = null;
            numOrders--;
        int x = 0;
        while (true) {
            if (((2 * x + 1) > (orderList.length - 1)) && ((2 * x + 2) > (orderList.length - 1))) {
                break;
            } else if (((2 * x + 2) > (orderList.length - 1))) {
                if (orderList[2 * x + 1] != null) {
                    if (orderList[x].compareTo(orderList[2 * x + 1]) == -1) {
                        CustomerOrder temp2 = orderList[x];
                        orderList[x] = orderList[2 * x + 1];
                        orderList[x].setPosInQueue(x);
                        orderList[2 * x + 1] = temp2;
                        orderList[2 * x + 1].setPosInQueue(2 * x + 1);
                        x = 2 * x + 1;
                    } else {
                        break;
                    }
                }
                else
                {
                    break;
                }
            } else if (orderList[2 * x + 1] == null && orderList[2 * x + 1] == null) {
                break;
            } else if (orderList[2 * x + 2] == null) {
                if (orderList[x].compareTo(orderList[2 * x + 1]) == -1) {

                    CustomerOrder temp2 = orderList[x];
                    orderList[x] = orderList[2 * x + 1];
                    orderList[x].setPosInQueue(x);
                    orderList[2 * x + 1] = temp2;
                    orderList[2 * x + 1].setPosInQueue(2 * x + 1);
                    x = 2 * x + 1;
                } else {
                    break;
                }

            } else if (orderList[2 * x + 1] == null) {
                if (orderList[x].compareTo(orderList[2 * x + 2]) == -1) {
                    CustomerOrder temp2 = orderList[x];
                    orderList[x] = orderList[2 * x + 2];
                    orderList[x].setPosInQueue(x);
                    orderList[2 * x + 2] = temp2;
                    orderList[2 * x + 2].setPosInQueue(2 * x + 2);
                    x = 2 * x + 2;
                } else {
                    break;
                }

            } else {
                if ((orderList[x].compareTo(orderList[2 * x + 1]) == -1) || (orderList[x].compareTo(orderList[2 * x + 2]) == -1)) {
                    int q = orderList[2 * x + 1].compareTo(orderList[2 * x + 2]);
                    if (q == -1) {
                        CustomerOrder temp2 = orderList[x];
                        orderList[x] = orderList[2 * x + 2];
                        orderList[x].setPosInQueue(x);
                        orderList[2 * x + 2] = temp2;
                        orderList[2 * x + 2].setPosInQueue(2 * x + 2);
                        x = 2 * x + 2;
                    }
                        else {
                        CustomerOrder temp2 = orderList[x];
                        orderList[x] = orderList[2 * x + 1];
                        orderList[x].setPosInQueue(x);
                        orderList[2 * x + 1] = temp2;
                        orderList[2 * x + 1].setPosInQueue(2 * x + 1);
                        x = 2 * x + 1;
                    }
                }
                else
                {
                    break;
                }
            }
        }
        }
        if (temp != null) {
            temp.setPosInQueue(-1);
        }
        return temp;
    }

    /**
     * TODO: return but do not remove the customer with the maximum priority
     *
     * @return return the customer with the maximum priority
     *
     */
    public CustomerOrder getMax() {
        return orderList[0];
    }

    /**
     * TODO: check if the priority queue is empty or not
     *
     * @return return true if the queue is empty; false else
     *
     */
    public boolean isEmpty() {
        boolean empty = true;
        for (int i = 0; i < orderList.length; i++)
        {
            if (orderList[i] != null)
            {
                empty = false;
                break;
            }
        }
        return empty;
    }

    /**
     * TODO: return the number of Customers currently in the queue
     *
     * @return return the number of Customers currently in the queue
     *
     */
    public int size() {
        return numOrders;
    }

    /**
     *
     * TODO: return the CustomerOrder with the given name
     *
     */
    public CustomerOrder get(String name) throws NoSuchAlgorithmException {
        CustomerOrder temp = table.get(name);
        return temp;
    }

    /**
     *
     * TODO: remove and return the CustomerOrder with the specified name from the queue;
     * TODO: return null if the CustomerOrder isn't in the queue
     *
     */
    public CustomerOrder remove(String name) throws NoSuchAlgorithmException {
        CustomerOrder c = table.get(name);
        if ((c == null) || (orderList[c.getPosInQueue()] == null))
        {
            return null;
        }
        else
        {
            CustomerOrder temp;
            temp = orderList[c.getPosInQueue()];
            orderList[c.getPosInQueue()] = orderList[numOrders - 1];
            orderList[c.getPosInQueue()].setPosInQueue(c.getPosInQueue());
            orderList[numOrders - 1] = null;
            numOrders--;
            int x = temp.getPosInQueue();
            while (true)
            {
                if (((x - 1) / 2) >= 0) {
                    if ((orderList[(x - 1)/2] != null) && ((orderList[x] != null)))
                    {
                        if (orderList[x].compareTo(orderList[(x - 1)/2]) == 1)
                        {
                            CustomerOrder temp2 = orderList[(x - 1)/2];
                            orderList[(x - 1)/2] = orderList[x];
                            orderList[(x - 1)/2].setPosInQueue((x - 1)/2);
                            orderList[x] = temp2;
                            orderList[x].setPosInQueue(x);
                            x = (x - 1)/2;
                        }
                        else
                        {
                            break;
                        }
                    }
                    else
                    {
                        break;
                    }
                }
                else
                {
                    break;
                }
            }
            while (true) {
                if ((2 * x + 1) > (orderList.length - 1))
                {
                    break;
                }
                else if ((2 * x + 2) > (orderList.length - 1))
                {
                    if (orderList[2 * x + 1] != null)
                    {
                        if (orderList[2 * x + 1].compareTo(orderList[x]) == 1)
                        {
                            CustomerOrder temp2 = orderList[x];
                            orderList[x] = orderList[2 * x + 1];
                            orderList[x].setPosInQueue(x);
                            orderList[2 * x + 1] = temp2;
                            orderList[2 * x + 1].setPosInQueue(2 * x + 1);
                            x = 2 * x + 1;
                        }
                        else
                        {
                            break;
                        }
                    }
                    else
                    {
                        break;
                    }
                }
                else if ((orderList[2 * x + 1] != null) && (orderList[2 * x + 2] != null))
                {
                    if((orderList[2 * x + 1].compareTo(orderList[x]) == 1) || (orderList[2 * x + 2].compareTo(orderList[x]) == 1))
                    {
                        int q = orderList[2 * x + 1].compareTo(orderList[2 * x + 2]);
                        if (q == -1) {
                            CustomerOrder temp2 = orderList[x];
                            orderList[x] = orderList[2 * x + 2];
                            orderList[x].setPosInQueue(x);
                            orderList[2 * x + 2] = temp2;
                            orderList[2 * x + 2].setPosInQueue(2 * x + 2);
                            x = 2 * x + 2;
                        }
                        else {
                            CustomerOrder temp2 = orderList[x];
                            orderList[x] = orderList[2 * x + 1];
                            orderList[x].setPosInQueue(x);
                            orderList[2 * x + 1] = temp2;
                            orderList[2 * x + 1].setPosInQueue(2 * x + 1);
                            x = 2 * x + 1;
                        }
                    }
                    else
                    {
                        break;
                    }
                }
                else if (orderList[2 * x + 2] != null)
                {
                    if ((orderList[2 * x + 2].compareTo(orderList[x]) == 1))
                    {
                        CustomerOrder temp2 = orderList[x];
                        orderList[x] = orderList[2 * x + 2];
                        orderList[x].setPosInQueue(x);
                        orderList[2 * x + 2] = temp2;
                        orderList[2 * x + 2].setPosInQueue(2 * x + 2);
                        x = 2 * x + 2;
                    }
                    else
                    {
                        break;
                    }
                }
                else if (orderList[2 * x + 1] != null)
                {
                    if ((orderList[2 * x + 1].compareTo(orderList[x]) == 1))
                    {
                        CustomerOrder temp2 = orderList[x];
                        orderList[x] = orderList[2 * x + 1];
                        orderList[x].setPosInQueue(x);
                        orderList[2 * x + 1] = temp2;
                        orderList[2 * x + 1].setPosInQueue(2 * x + 1);
                        x = 2 * x + 1;
                    }
                    else
                    {
                        break;
                    }
                }
                else
                {
                    break;
                }
            }
            if (temp != null) {
                temp.setPosInQueue(-1);
            }
        return temp;
        }
    }

    /**
     *
     * TODO: update the orderDeliveryTime of the Customer with the specified name to newTime
     *
     */
    public void update(String name, int newTime) throws NoSuchAlgorithmException {
        CustomerOrder c = table.get(name);
        c.setOrderDeliveryTime(newTime);
        int x = c.getPosInQueue();
        while (true)
        {
            if (((x - 1) / 2) >= 0) {
                if ((orderList[(x - 1)/2] != null) && ((orderList[x] != null)))
                {
                    if (orderList[x].compareTo(orderList[(x - 1)/2]) == 1)
                    {
                        CustomerOrder temp2 = orderList[(x - 1)/2];
                        orderList[(x - 1)/2] = orderList[x];
                        orderList[(x - 1)/2].setPosInQueue((x - 1)/2);
                        orderList[x] = temp2;
                        orderList[x].setPosInQueue(x);
                        x = (x - 1)/2;
                    }
                    else
                    {
                        break;
                    }
                }
                else
                {
                    break;
                }
            }
            else
            {
                break;
            }
        }
        while (true) {
            if ((2 * x + 1) > (orderList.length - 1))
            {
                break;
            }
            else if ((2 * x + 2) > (orderList.length - 1))
            {
                if (orderList[2 * x + 1] != null)
                {
                    if (orderList[2 * x + 1].compareTo(orderList[x]) == 1)
                    {
                        CustomerOrder temp2 = orderList[x];
                        orderList[x].setPosInQueue(2 * x + 1);
                        orderList[x] = orderList[2 * x + 1];
                        orderList[2 * x + 1].setPosInQueue(x);
                        orderList[2 * x + 1] = temp2;
                        x = 2 * x + 1;
                    }
                    else
                    {
                        break;
                    }
                }
                else
                {
                    break;
                }
            }
            else if ((orderList[2 * x + 1] != null) && (orderList[2 * x + 2] != null))
            {
                if((orderList[2 * x + 1].compareTo(orderList[x]) == 1) || (orderList[2 * x + 2].compareTo(orderList[x]) == 1))
                {
                    int q = orderList[2 * x + 1].compareTo(orderList[2 * x + 2]);
                    if (q == -1) {
                        CustomerOrder temp2 = orderList[x];
                        orderList[x] = orderList[2 * x + 2];
                        orderList[x].setPosInQueue(x);
                        orderList[2 * x + 2] = temp2;
                        orderList[2 * x + 2].setPosInQueue(2 * x + 2);
                        x = 2 * x + 2;
                    }
                    else {
                        CustomerOrder temp2 = orderList[x];
                        orderList[x] = orderList[2 * x + 1];
                        orderList[x].setPosInQueue(x);
                        orderList[2 * x + 1] = temp2;
                        orderList[2 * x + 1].setPosInQueue(2 * x + 1);
                        x = 2 * x + 1;
                    }
                }
                else
                {
                    break;
                }
            }
            else if (orderList[2 * x + 2] != null)
            {
                if ((orderList[2 * x + 2].compareTo(orderList[x]) == 1))
                {
                    CustomerOrder temp2 = orderList[x];
                    orderList[x] = orderList[2 * x + 2];
                    orderList[x].setPosInQueue(x);
                    orderList[2 * x + 2] = temp2;
                    orderList[2 * x + 2].setPosInQueue(2 * x + 2);
                    x = 2 * x + 2;
                }
                else
                {
                    break;
                }
            }
            else if (orderList[2 * x + 1] != null)
            {
                if ((orderList[2 * x + 1].compareTo(orderList[x]) == 1))
                {
                    CustomerOrder temp2 = orderList[x];
                    orderList[x] = orderList[2 * x + 1];
                    orderList[x].setPosInQueue(x);
                    orderList[2 * x + 1] = temp2;
                    orderList[2 * x + 1].setPosInQueue(2 * x + 1);
                    x = 2 * x + 1;
                }
                else
                {
                    break;
                }
            }
            else
            {
                break;
            }
        }
    }

}
