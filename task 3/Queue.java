/***
 * queue class.
 */
public class Queue {

    Passenger queue[] = new Passenger[Constants.PASSENGERS_IN_QUEUE];
    int size;
    int front;
    int rear ;

    public Queue(){
        for(int i = 0; i<Constants.PASSENGERS_IN_QUEUE; i++) {
            queue[i] = new Passenger();
        }
    }
    //enQueue
    public void enQueue(String firstName, String surName, double cost) {

        if (!isFull()) {
            queue[rear].setFirstName(firstName);
            queue[rear].setSecondName(surName);
            queue[rear].setCostPerCustomer(cost);
            queue[rear].setFullName(firstName, surName);
            rear = (rear + 1) % 5;
            size = size + 1;
            show();

        } else {
            System.out.println("Sorry! Queue is full");
        }
    }

    public void show() {

        System.out.println("Queue : ");
        for (int i = 0 ; i < size; i++) {
            System.out.print(queue[(front + i) % 5].getFullName()+" :- "+queue[(front + i) % 5].getCostPerCustomer()+ " , ");
        }
        System.out.println(" ");
    }

    public Passenger deQueue() {

        Passenger passengerToCabin = queue[front];
        if (!isEmpty()) {

        } else {
            System.out.println("Queue is empty");
        }
        return passengerToCabin;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == 5;
    }

    public void updatingDequeue() {
        front = (front + 1) % 5;
        size = size - 1;
    }
}
