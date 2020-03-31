package com.elevator;

public interface Elevator {

    /**
     * Make a Call to the elevator from a floor.
     * @param floor Floor for which the call is made
     * @param direction UP/DOWN
     */
    void call(int floor, Direction direction);

    /**
     * The current floor of the elevator
     * @return Current floor.
     */
    int getCurrentFloor();

    /**
     * Select the targeted floor from within the elevator
     * @param selectedFloor
     */
    void selectFloor(int selectedFloor);

    /**
     * Start the elevator. Typically process the UP and DOWN Queues successively.
     */
    void run();

    /**
     * Is the elevator's door open.
     * @return Is the elevator's door open.
     */
    boolean isDoorsOpen();
}
