package com.elevator;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class ElevatorImpl implements Elevator {

    public final static Elevator instance = new ElevatorImpl();

    private ElevatorImpl() {
    }

    private volatile int currentFloor;
    private volatile boolean isProcessing;
    private volatile boolean isDoorsOpen;
    private Direction currentDirection = Direction.UP;

    /**
     * Contains the ascending floors to process.
     */
    private Queue<Integer> upPriorityQueue = new PriorityQueue<>(100, Comparator.naturalOrder());

    /**
     * If the elevator is currently processing ascending floors, put the next floor to be processing in the next cycle in this Queue.
     */
    private Queue<Integer> upQueue = new LinkedList<>();

    /**
     * Contains the descending floors to process.
     */
    private Queue<Integer> downPriorityQueue = new PriorityQueue<>(100, Comparator.reverseOrder());
    private Queue<Integer> downQueue = new LinkedList<>();

    @Override
    public void call(int floor, Direction direction) {

        if (currentFloor == floor) {
            OpenAndThenCloseElevator();
            return;
        }

        if (Direction.UP == direction && floor > currentFloor && !upPriorityQueue.contains(floor)) {
            upPriorityQueue.add(floor);
            System.out.println("Current UP Priority Queue :" + upPriorityQueue);
        } else if (Direction.UP == direction && floor < currentFloor) {
            // put on queue to process later
            upQueue.add(floor);
            System.out.println("Up Queue :" + upQueue);
        } else if (Direction.DOWN == direction && floor < currentFloor && !downPriorityQueue.contains(floor)) {
            downPriorityQueue.add(floor);
            System.out.println("Current Down Priority Queue :" + downPriorityQueue);
        } else if (Direction.DOWN == direction && floor > currentFloor) {
            downQueue.add(floor);
            System.out.println("Down Queue :" + downQueue);
        }

    }

    @Override
    public int getCurrentFloor() {
        return this.currentFloor;
    }

    @Override
    public void selectFloor(int selectedFloor) {
        if (selectedFloor > currentFloor && !upPriorityQueue.contains(selectedFloor)) {
            upPriorityQueue.add(selectedFloor);
            System.out.println("Current Processing UP Queue" + upPriorityQueue);
        } else if (selectedFloor < currentFloor && !downPriorityQueue.contains(selectedFloor)) {
            downPriorityQueue.add(selectedFloor);
            System.out.println("Current Processing DOWN Queue" + downPriorityQueue);
        }

    }

    @Override
    public void run() {
        if (!isProcessing) {
            this.isProcessing = true;
            if (currentDirection == Direction.UP) {
                // If the current processing queue is empty, process the next cycle
                if (upPriorityQueue.isEmpty()) {
                    upPriorityQueue.addAll(upQueue);
                    upQueue.clear();
                }
                process(upPriorityQueue);
                upPriorityQueue.addAll(upQueue);
                upQueue.clear();
                currentDirection = Direction.DOWN;

            } else if (currentDirection == Direction.DOWN) {
                if (downPriorityQueue.isEmpty()) {
                    downPriorityQueue.addAll(downQueue);
                    downQueue.clear();
                }
                process(downPriorityQueue);
                downPriorityQueue.addAll(downQueue);
                downQueue.clear();
                currentDirection = Direction.UP;
            }

            this.isProcessing = false;
        }
    }

    @Override
    public boolean isDoorsOpen() {
        return isDoorsOpen;
    }

    /**
     * Go to the floors
     */
    private void process(Queue<Integer> qToProcess) {
        System.out.println(System.lineSeparator() + "Processing Queue : " + currentDirection);
        System.out.println("Current Direction : " + currentDirection + System.lineSeparator());
        int qSize = qToProcess.size();
        for (int i = 0; i < qSize; i++) {
            if (i == 0) {
                moveToNextFloor();
            }
            currentFloor = qToProcess.poll();
            System.out.println("CurrentFloor : " + currentFloor);
            OpenAndThenCloseElevator();
            moveToNextFloor();
        }

        // MeanWhile the queue is filled
        if (!qToProcess.isEmpty()) {
            process(qToProcess);
        }

    }

    private void moveToNextFloor() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void OpenAndThenCloseElevator() {
        try {
            System.out.println("opening doors in floor: " + this.currentFloor);
            this.isDoorsOpen = true;
            Thread.sleep(3000);
            System.out.println("closing doors in floor: " + this.currentFloor +System.lineSeparator());
            this.isDoorsOpen = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
