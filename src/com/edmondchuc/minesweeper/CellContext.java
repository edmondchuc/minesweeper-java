package com.edmondchuc.minesweeper;

import java.util.List;

/**
 * This is the cell context class. It models the functionality of a cell in a class-based finite state machine pattern.
 */
public class CellContext {
    /**
     * The state of the cell.
     */
    private CellState cellState;
    private boolean isBomb;
    private List<CellContext> neighbours;
    private int neighboursIsBomb = 0;

    /**
     * Constructor
     * @param isBomb The boolean to determine if this cell is a bomb or not
     */
    public CellContext(boolean isBomb) {
        // cell creation defaults to its original state.
        this.cellState = new CellDefault();

        this.isBomb = isBomb;
    }

    /**
     * Get the number of neighours who are bombs
     * @return The total count of neighbours that are bombs
     */
    public int getNumOfNeighboursIsBomb() {
        return neighboursIsBomb;
    }

    /**
     * Set the neighbours of this cell.
     * @param neighbours The neighbours list
     */
    public void setNeighbours(List neighbours) {
        this.neighbours = neighbours;
    }

    /**
     * Set the bomb count of neighbours
     */
    public void setBombNeighbours() {
        // find the number of neighbours who are bombs
        if(!this.isBomb) {
            for(CellContext neighbour : this.neighbours) {
                if(neighbour.isBomb()) {
                    neighboursIsBomb++;
                }
            }
        }
    }

    /**
     * Get the neighbours list
     * @return
     */
    public List<CellContext> getNeighbours() {
        return neighbours;
    }

    /**
     * Check if the cell is a bomb.
     * @return The boolean if the cell is a bomb
     */
    public boolean isBomb() {
        return isBomb;
    }

    public void setBomb() {
        this.isBomb = true;
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
     * Set the state to cell flagged
     */
    public void setStateFlagged() { this.cellState.setStateRightClick(this); }

    /**
     * Calls the setStateHoverExit of the CellState object.
     * <p></p>
     * Functionality should only be implemented by the CellHover class.
     * @see CellHover
     */
    public void setStateHoverExit() {
        this.cellState.setStateHoverExit(this);
    }

    /**
     * Set the state to left clicked
     */
    public void setStateLeftClick() {
        this.cellState.setStateLeftClick(this);
    }

    /**
     * Set the state to right clicked
     */
    public void setStateRightClick() { this.cellState.setStateRightClick(this); }

    /**
     * Reveal the neighbours
     */
    public void revealNeighbours() {
        for (CellContext neighbour : neighbours) {
            if(neighbour.getState().getClass() != CellRevealedEmpty.class && neighbour.getState().getClass() != CellRevealedBomb.class && neighbour.getState().getClass() != CellFlagged.class) {
                boolean hasBomb = false; // check for bombs in its adjacent neighbours
                for (CellContext n : neighbour.neighbours) {
                    if(n.isBomb()) {
                        hasBomb = true;
                        break;
                    }
                }
                neighbour.setState(new CellRevealedEmpty());
                if(hasBomb == false) {
                    neighbour.revealNeighbours();
                }
            }
        }
    }
}
