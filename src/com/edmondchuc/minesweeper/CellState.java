package com.edmondchuc.minesweeper;

/**
 * CellState interface should be implemented by any classes that represent a cell state.
 * TODO: Refactor CellState into CellBehaviour interfaces so that each CellState only needs to implement its required methods.
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

    /**
     * Inform the Cell that it has been left clicked and set its state based on its current state and properties.
     * @param cellContext The CellContext object wrapper for a finite state machine.
     */
    public void setStateLeftClick(CellContext cellContext);

    /**
     * Set the state to RevealedEmpty.
     * @param cellContext The CellContext object wrapper for a finite state machine.
     */
    public void setStateRevealedEmpty(CellContext cellContext);

    /**
     * Set the state to RevealedBomb
     * @param cellContext The CellContext object wrapper for a finite state machine.
     */
    public void setStateRevealedBomb(CellContext cellContext);

    public void setStateRightClick(CellContext cellContext);
}
