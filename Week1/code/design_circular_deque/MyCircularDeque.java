package code.design_circular_deque;

class MyCircularDeque {
    private int[] data;
    private int count;
    private int front;
    private int rear;
    private int size;
    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        data = new int[k];
        count = 0;
        front = 0;
        rear = k-1;
        size = k;
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if(count == size){
            return false;
        }
        count++;
        data[front%size] = value;
        front = (front+1)%size;
        return true;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if(count == size){
            return false;
        }
        count++;
        data[rear%size] = value;
        rear = (rear+size-1)%size;
        return true;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if(count == 0) return false;
        count--;
        front = (front+size-1)%size;
        return true;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if(count == 0) return false;
        count--;
        rear = (rear+1)%size;
        return true;

    }

    /** Get the front item from the deque. */
    public int getFront() {
        if (count==0) return -1;
        return data[(front+size-1)%size];
    }

    /** Get the last item from the deque. */
    public int getRear() {
        if (count==0) return -1;
        return data[(rear+1)%size];
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return count==0;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return count==size;
    }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */
