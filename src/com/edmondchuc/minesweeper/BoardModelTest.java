package com.edmondchuc.minesweeper;

import org.junit.Assert;

public class BoardModelTest {

    @org.junit.Test
    public void isGameOver() {
        BoardModel boardModel = new BoardModel(5, GameMode.CLASSIC, GameMode.EASY);
        Assert.assertEquals(boardModel.isGameOver(), false);
    }
}

