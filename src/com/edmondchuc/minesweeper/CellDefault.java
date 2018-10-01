package com.edmondchuc.minesweeper;

/**
 * This class represents the state of a cell in its original state.
 */
public class CellDefault implements CellState {
    /**
     * Set state to CellHover when mouse enters the cell through hovering.
     * @param cellContext The CellContext object wrapper for a finite state machine.
     */
    @Override
    public void setStateHoverEnter(CellContext cellContext) {
        cellContext.setState(new CellHover(cellContext.getState()));
    }

    /**
     * This class does not implement functionality for this method.
     * @param cellContext The CellContext object wrapper for a finite state machine.
     */
    @Override
    public void setStateHoverExit(CellContext cellContext) {

    }

    @Override
    public void setStateLeftClick(CellContext cellContext) {

    }

    @Override
    public void setStateRevealedEmpty(CellContext cellContext) {

    }

    @Override
    public void setStateRevealedBomb(CellContext cellContext) {

    }

    @Override
    public void setStateRightClick(CellContext cellContext) {

    }
}
