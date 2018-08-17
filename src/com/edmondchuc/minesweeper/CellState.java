package com.edmondchuc.minesweeper;

/**
 * CellState interface should be implemented by any classes that represent a cell state.
 */
public interface CellState {
    /**
     * Set state to CellHover when mouse enters the cell through hovering.
     * @param cellContext The CellContext object wrapper for a finite state machine.
     */
    public void setStateHoverEnter(CellContext cellContext);

    /**
     * Set the state to its previous state as the mouse exits the cell through hovering.
     * @param cellContext The CellContext object wrapper for a finite state machine.
     */
    public void setStateHoverExit(CellContext cellContext);

}
