public class Queue {

    Passenger p1 = new Passenger();
    Passenger p2 = new Passenger();
    Passenger p3 = new Passenger();
    Passenger p4 = new Passenger();
    Passenger p5 = new Passenger();
    Passenger queue[] = {p1,p2,p3,p4,p5};
    int size;
    int front;
    int rear ;
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
    //show
    public void show() {
        System.out.println("Queue : ");
        for (int i = 0 ; i < size; i++) {
            System.out.print(queue[(front + i) % 5].getFullName()+" :- "+queue[(front + i) % 5].getCostPerCustomer()+ " , ");
        }
        System.out.println(" ");
    }

    //dequeue
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
