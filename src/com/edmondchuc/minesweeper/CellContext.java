package com.edmondchuc.minesweeper;

/**
 * This is the cell context class. It models the functionality of a cell in a class-based finite state machine pattern.
 */
public class CellContext {
    /**
     * The state of the cell.
     */
    private CellState cellState;
    private boolean isBomb;

    /**
     * Constructor.
     */
    public CellContext(boolean isBomb) {
        // cell creation defaults to its original state.
        this.cellState = new CellDefault();

        this.isBomb = isBomb;
    }

    public boolean isBomb() {
        return isBomb;
    }

    /**
     * Get the current state of the cell.
     * @return CellState object.
     */
    public CellState getState() {
        return this.cellState;
    }

    /**
     * Set the state of the cell to a new state.
     * <p></p>
     * This public method should only be accessed by a class that implements CellState.
     * @see CellState
     * @param cellState CellState object.
     */
    public void setState(CellState cellState) {
        this.cellState = cellState;
    }

    /**
     * Calls the setStateHoverEnter method of a CellState object.
     * <p></p>
     * Functionality should only be implemented by CellDefault class.
     * @see CellDefault
     */
    public void setStateHoverEnter() {
        this.cellState.setStateHoverEnter(this);
    }

    /**
     * Calls the setStateHoverExit of the CellState object.
     * <p></p>
     * Functionality should only be implemented by the CellHover class.
     * @see CellHover
     */
    public void setStateHoverExit() {
        this.cellState.setStateHoverExit(this);
    }

    public void setStateLeftClick() {
        this.cellState.setStateLeftClick(this);
    }
}
