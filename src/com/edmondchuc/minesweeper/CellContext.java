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

    public CellContext(boolean isBomb) {
        // cell creation defaults to its original state.
        this.cellState = new CellDefault();

        this.isBomb = isBomb;
    }

    public int getNumOfNeighboursIsBomb() {
        return neighboursIsBomb;
    }

    public void setNeighbours(List neighbours) {
        this.neighbours = neighbours;
    }

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

    public void revealNeighbours() {
        for (CellContext neighbour : neighbours) {
            if(neighbour.getState().getClass() != CellRevealedEmpty.class && neighbour.getState().getClass() != CellRevealedBomb.class) {
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
