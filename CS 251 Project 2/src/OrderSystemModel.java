/**
 * CS 251: Data Structures and Algorithms
 * Project 2: Part 4
 *
 * TODO: Complete OrderSystemModel.
 *
 * @author Chirayu Garg, Jordan Davis
 * @username garg104, davi1304
 * @sources
 */

import java.security.NoSuchAlgorithmException;

public class OrderSystemModel {
    private BetterCustomerOrderQueue orderList;
    private int capacityThreshold;
    private int ordersDelayed;
    private int ordersOnTime;
    private int ordersCanceled;
    private int time;
    private int totalDelayTime;

    public int getOrdersDelayed() {
        return ordersDelayed;
    }

    public int getOrdersOnTime() {
        return ordersOnTime;
    }

    public int getOrdersCanceled() {
        return ordersCanceled;
    }

    public int getTotalDelayTime() {
        return totalDelayTime;
    }

    public BetterCustomerOrderQueue getOrderList() {
        return orderList;
    }

    /**
     * Constructor of the class.
     *
     * Initialize a new OrderSystemModel and OrderSystemModel
     *
     */
    public OrderSystemModel(int capacityThreshold) {
        this.capacityThreshold = capacityThreshold;
        this.orderList = new BetterCustomerOrderQueue(this.capacityThreshold);
        this.ordersDelayed = 0;
        this.ordersOnTime = 0;
        this.ordersCanceled = 0;
        this.time = 0;
        this.totalDelayTime = 0;
    }


    /**
     *
     * TODO: Process a new CustomerOrder with a given name.
     *
     */
    public String process(String name, int orderTime, int deliveryTime) throws NoSuchAlgorithmException {
        CustomerOrder c = new CustomerOrder(name, orderTime, deliveryTime);
        if (orderList.insert(c) != -1)
        {
            return name;
        }
        else
        {
            if (c.compareTo(orderList.getMax()) == 1)
            {
                if (deliveryTime > time)
                {
                    ordersDelayed++;
                    totalDelayTime += deliveryTime - time;
                }
                else
                {
                    ordersOnTime++;
                }
                time++;
                return null;
            }
            else
            {
               String val = completeNextOrder();
               orderList.insert(c);
               return val;
            }
        }
    }

    /**
     *
     * TODO: Complete the highest priority order
     *
     */
    public String completeNextOrder() throws NoSuchAlgorithmException {
        CustomerOrder c = orderList.delMax();
        if (c != null)
        {
            if (c.getOrderDeliveryTime() > time)
            {
                ordersDelayed++;
                totalDelayTime += c.getOrderDeliveryTime() - time;
            }
            else
            {
                ordersOnTime++;
            }
            time++;
            return c.getName();
        }
        else
        {
            return null;
        }
    }

    /**
     *
     * TODO: Update the delivery time of the order for the given name
     *
     */
    public String updateOrderTime(String name, int newDeliveryTime) throws NoSuchAlgorithmException {
        CustomerOrder c = orderList.get(name);
        if (c == null)
        {
            return null;
        }
        else
        {
            if (newDeliveryTime < time)
            {

               cancelOrder(name);
               return name;
            }
            else if (orderList.getOrderList()[0] != null)
            {
                if (c.compareTo(orderList.getOrderList()[0]) == 1)
                {
                    if (newDeliveryTime > time)
                    {
                        ordersDelayed++;
                        totalDelayTime += newDeliveryTime - time;
                    }
                    else
                    {
                        ordersOnTime++;
                    }
                    time++;
                    return name;
                }
                else
                {
                    orderList.update(name, newDeliveryTime);
                    return null;
                }
            }
            else
            {
                orderList.update(name, newDeliveryTime);
                return null;
            }
        }
    }

    /**
     *
     * TODO: Cancel the order for the given name
     *
     */
    public CustomerOrder cancelOrder(String name) throws NoSuchAlgorithmException {
        CustomerOrder c = orderList.remove(name);
         if (c != null)
        {
            ordersCanceled++;
            return c;
        }
        else
            {

            return c;
        }
    }
}
