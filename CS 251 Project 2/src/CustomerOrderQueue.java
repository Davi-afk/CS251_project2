/**
 * CS 251: Data Structures and Algorithms
 * Project 2: Part 1
 *
 *
 *
 * @author Chirayu Garg, Jordan Davis
 * @username garg104, davi1304
 * @sources
 */


public class CustomerOrderQueue {
    private CustomerOrder[] orderList;
    private int numOrders;

    /**
     *
     * @return return the priority queue
     *
     */
    public CustomerOrder[] getOrderList() {
        return orderList;
    }

    /**
     *
     * @return return the number of orders
     *
     */
    public int getNumOrders() {
        return this.numOrders;
    }

    /**
     * Constructor of the class.
     * TODO: complete the default Constructor of the class
     *
     * Initilalize a new CustomerOrder array with the argument passed.
     *
     */
    public CustomerOrderQueue(int capacity) {
        orderList = new CustomerOrder[capacity];
    }

    /**
     * TODO: insert a new customer order into the priority queue.
     *
     * @return return the index at which the customer was inserted
     *
     */
    public int insert(CustomerOrder c) {
        int x = -1;
        for (int i = 0; i < orderList.length; i++)
        {
            if (orderList[i] == null)
            {
                x = i;
                orderList[i] = c;
                break;
            }
        }
        while (true)
        {
            if ((x == 0) || (x == -1))
            {
                break;
            }
            else if (orderList[x].getOrderDeliveryTime() < orderList[(x-1)/2].getOrderDeliveryTime())
            {
                CustomerOrder temp = orderList[(x-1)/2];
                orderList[(x-1)/2] = orderList[x];
                orderList[x] = temp;
                x = (x-1)/2;
            }
            else
            {
                break;
            }
        }
        if (x == 0)
        {
            numOrders++;
        }
        else if (x == -1)
        {
        }
        else
        {
            numOrders++;
        }
        return x;
    }

    /**
     * TODO: remove the customer with the highest priority from the queue
     *
     * @return return the customer removed
     *
     */
    public CustomerOrder delMax() {
        CustomerOrder temp;
        if (numOrders == 0)
        {
             temp = null;
        }
        else
        {
             temp = orderList[0];
            orderList[0] = orderList[numOrders -1];
            orderList[numOrders -1] = null;
            numOrders--;
        }
        int x = 0;
        while (true) {
            if (((2 * x + 1) > (orderList.length - 1)) && ((2 * x + 2) > (orderList.length - 1))) {
                break;
            } else if (((2 * x + 2) > (orderList.length - 1))) {
                if (orderList[2 * x + 1] != null) {
                    if (orderList[x].compareTo(orderList[2 * x + 1]) == -1) {
                        CustomerOrder temp2 = orderList[x];
                        orderList[x] = orderList[2 * x + 1];
                        orderList[2 * x + 1] = temp2;
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
                if ((orderList[x].compareTo(orderList[2 * x + 1]) == -1)) {

                    CustomerOrder temp2 = orderList[x];
                    orderList[x] = orderList[2 * x + 1];
                    orderList[2 * x + 1] = temp2;
                    x = 2 * x + 1;
                } else {
                    break;
                }

            } else if (orderList[2 * x + 1] == null) {
                if ((orderList[x].compareTo(orderList[2 * x + 2]) == -1)) {
                    CustomerOrder temp2 = orderList[x];
                    orderList[x] = orderList[2 * x + 2];
                    orderList[2 * x + 2] = temp2;
                    x = 2 * x + 2;
                } else {
                    break;
                }

            } else {
                if ((orderList[x].getOrderDeliveryTime() > orderList[2 * x + 1].getOrderDeliveryTime()) || (orderList[x].getOrderDeliveryTime() > orderList[2 * x + 2].getOrderDeliveryTime())) {
                    if ((orderList[2 * x + 1].compareTo(orderList[2 * x + 2]) == -1)) {
                        CustomerOrder temp2 = orderList[x];
                        orderList[x] = orderList[2 * x + 2];
                        orderList[2 * x + 2] = temp2;
                        x = 2 * x + 2;
                    } else {
                        CustomerOrder temp2 = orderList[x];
                        orderList[x] = orderList[2 * x + 1];
                        orderList[2 * x + 1] = temp2;
                        x = 2 * x + 1;
                    }
                }
                else
                {
                    break;
                }
            }
        }
        return temp;
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
     * TODO: check if the priority queue is empty or not
     *
     * @return return true if the queue is empty; false else
     *
     */
    public boolean isEmpty() {
        boolean y = true;
        for (int i = 0; i < orderList.length; i++)
        {
            if (orderList[i] != null)
            {
                y = false;
                break;
            }
        }
        return y;
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

}
