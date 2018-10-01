package com.edmondchuc.minesweeper;

public class CellFlagged implements CellState {

    @Override
    public void setStateHoverEnter(CellContext cellContext) {

    }

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
        cellContext.setState(new CellDefault());
    }
}
