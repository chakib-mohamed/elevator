package com.elevator;

public class Main {

    public static void main(String[] args) {


        Elevator elevator = ElevatorImpl.instance;

        new Thread(
                () -> {while(true) elevator.run();}
        ).start();

        Thread t1 = new Thread(() -> {
            elevator.call(3, Direction.UP);
            while(elevator.getCurrentFloor() != 3 || !elevator.isDoorsOpen()){
            }
            System.out.println("Entering elevator : " + elevator.getCurrentFloor());
            elevator.selectFloor(5);
            while(elevator.getCurrentFloor() != 5 || !elevator.isDoorsOpen()){}
            System.out.println("Exit elevator : " + elevator.getCurrentFloor());
        });
        t1.start();

        new Thread(() -> {
            elevator.call(9, Direction.UP);
            while(elevator.getCurrentFloor() != 9 || !elevator.isDoorsOpen()){}
            System.out.println("Entering elevator : " + elevator.getCurrentFloor());
            elevator.selectFloor(0);
            while(elevator.getCurrentFloor() != 0 || !elevator.isDoorsOpen()){}
            System.out.println("Exit elevator : " + elevator.getCurrentFloor());
        }).start();

        new Thread(() -> {
            elevator.call(5, Direction.UP);
            while(elevator.getCurrentFloor() != 5 || !elevator.isDoorsOpen()){}
            System.out.println("Entering elevator : " + elevator.getCurrentFloor());
            elevator.selectFloor(9);
            while(elevator.getCurrentFloor() != 9 || !elevator.isDoorsOpen()){}
            System.out.println("Exit elevator : " + elevator.getCurrentFloor());
        }).start();

        new Thread(() -> {
            elevator.call(7, Direction.UP);
            while(elevator.getCurrentFloor() != 7 || !elevator.isDoorsOpen()){}
            System.out.println("Entering elevator : " + elevator.getCurrentFloor());
            elevator.selectFloor(20);
            while(elevator.getCurrentFloor() != 20 || !elevator.isDoorsOpen()){}
            System.out.println("Exit elevator : " + elevator.getCurrentFloor());
        }).start();

        new Thread(() -> {
            elevator.call(7, Direction.DOWN);
            while(elevator.getCurrentFloor() != 7 || !elevator.isDoorsOpen()){}
            System.out.println("Entering elevator : " + elevator.getCurrentFloor());
            elevator.selectFloor(4);
            while(elevator.getCurrentFloor() != 4 || !elevator.isDoorsOpen()){}
            System.out.println("Exit elevator : " + elevator.getCurrentFloor());
        }).start();

        new Thread(() -> {
            elevator.call(8, Direction.UP);
            while(elevator.getCurrentFloor() != 8 || !elevator.isDoorsOpen()){}
            System.out.println("Entering elevator : " + elevator.getCurrentFloor());
            elevator.selectFloor(15);
            while(elevator.getCurrentFloor() != 15 || !elevator.isDoorsOpen()){}
            System.out.println("Exit elevator : " + elevator.getCurrentFloor());
        }).start();

//        new Thread(() -> elevator.call(1, Direction.UP)).start();
//        new Thread(() -> elevator.call(4, Direction.UP)).start();
//
//        new Thread(() -> elevator.call(10, Direction.DOWN)).start();
//        new Thread(() -> elevator.call(15, Direction.DOWN)).start();
//
//        new Thread(() -> elevator.call(3, Direction.UP)).start();
//        new Thread(() -> elevator.call(5, Direction.UP)).start();
//        new Thread(() -> elevator.call(7, Direction.UP)).start();

    }
}
