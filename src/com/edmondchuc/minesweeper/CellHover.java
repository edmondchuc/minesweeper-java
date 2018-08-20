package com.edmondchuc.minesweeper;

/**
 * This class implements the state of a cell in its hover state.
 */
public class CellHover implements CellState {
    /**
     * The previous state before the cell transitioned to the hover state.
     */
    CellState previousState;

    /**
     * Constructor.
     * @param previousState The previous CellState object.
     */
    public CellHover(CellState previousState) {
        this.previousState = previousState;
    }

    /**
     * This class does not implement functionality for this method.
     * @param cellContext The CellContext object wrapper for a finite state machine.
     */
    @Override
    public void setStateHoverEnter(CellContext cellContext) {

    }

    /**
     * Set state to the previous state before it transitioned into the hover state.
     * @param cellContext The CellContext object wrapper for a finite state machine.
     */
    @Override
    public void setStateHoverExit(CellContext cellContext) {
        cellContext.setState(previousState);
    }

    @Override
    public void setStateLeftClick(CellContext cellContext) {
        if(cellContext.isBomb()) {
            cellContext.setState(new CellRevealedBomb());
        }
        else if(!cellContext.isBomb()) {
            cellContext.setState(new CellRevealedEmpty());
            boolean hasBomb = false;
            for (CellContext neighbour: cellContext.getNeighbours()) {
                if(neighbour.isBomb()) {
                    hasBomb = true;
                    break;
                }
            }
            if(hasBomb == false) {
                cellContext.revealNeighbours();
            }
        }
        else {

        }
    }

    @Override
    public void setStateRevealedEmpty(CellContext cellContext) {

    }

    @Override
    public void setStateRevealedBomb(CellContext cellContext) {

    }


}
