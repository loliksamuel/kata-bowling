package com.pointer.kata.bowling;
/* Company   : Pointer Software Systems LTD.
 * Project   : KataBowling
 * Package   : com.pointer.kata.bowling
 * File      : Game.java
 * create date: 06-06-2021
 * create by : Samuel Cardonis
 * update by :
 * update date:
 */

/**
 *
 */
public class Game {

  public static final int PINS = 10;
  private final int SCORE_FRAME = 0;
  private final int SCORE_ROW1 = 1;
  private final int SCORE_ROW2 = 2;
  private final int FRAMES = 13;//cause it starts from 1 to 10 and 10th frame can have 3 strikes(3 frames)
  private final int STATE_STRIKE = 1;
  private final int STATE_SPARE = 2;
  private final int STATE_REG = 3;

  private int m_playCounter = 0;
  private int[][] m_arrRolls = new int[FRAMES][3];


  public Game() {
    System.out.println("GAME START\n");
    System.out.println("#PLAY | #ROW |  #FRAME | #DOWN\n");
  }

  public void roll(int... arrOfPinsDown) {
    for (int i : arrOfPinsDown) {
      roll(i);
    }
  }

  public void roll(int pinsDown) {

    m_playCounter++;

    int rowCounter = getRowCounter();
    int frameCounter = getFrameCounter();
    System.out.println("  " + m_playCounter + "        " + rowCounter + "        " + frameCounter + "     " + pinsDown);
    m_arrRolls[frameCounter][rowCounter] = pinsDown;
    m_arrRolls[frameCounter][SCORE_FRAME] += pinsDown;
    if (rowCounter == SCORE_ROW1) {
      calcScoreSpare(frameCounter - 1);
      if (isStrike(pinsDown)) {
        m_playCounter++;
        calcScoreStrike(frameCounter - 2);
      }
    } else if (rowCounter == SCORE_ROW2) {
      calcScoreStrike(frameCounter - 1);
    }


  }

  private boolean isStrike(int pinsDown1) {
    return (pinsDown1 == PINS);
  }

  private boolean isSpare(int pinsDown1, int pinsDown2) {
    return (pinsDown1 != PINS) && pinsDown1 + pinsDown2 == PINS;
  }

  private void calcScoreSpare(int frameCounter) {
    if (frameCounter >= 1 && isSpare(m_arrRolls[frameCounter][SCORE_ROW1], m_arrRolls[frameCounter][SCORE_ROW2])) {
      int bonusSpare = m_arrRolls[frameCounter + 1][SCORE_ROW1];
      System.out.println(" adding spare " + bonusSpare + " to frame #" + frameCounter + " before="
          + m_arrRolls[frameCounter][SCORE_FRAME]);
      m_arrRolls[frameCounter][SCORE_FRAME] += bonusSpare;
    }
  }

  private void calcScoreStrike(int frameCounter) {
    if (frameCounter >= 1
        && isStrike(m_arrRolls[frameCounter][SCORE_ROW1])) {
      int bonusStrike = m_arrRolls[frameCounter + 1][SCORE_FRAME];
      if (isStrike(m_arrRolls[frameCounter + 1][SCORE_ROW1])) {
        bonusStrike = m_arrRolls[frameCounter + 1][SCORE_ROW1] + m_arrRolls[frameCounter + 2][SCORE_ROW1];
      }
      System.out.println(" adding strike " + bonusStrike + " to frame #" + frameCounter + " before="
          + m_arrRolls[frameCounter][SCORE_FRAME]);
      m_arrRolls[frameCounter][SCORE_FRAME] += bonusStrike;
    }
  }


  private int getRowCounter() {
    if (isPlayEven()) {
      return SCORE_ROW2;
    }
    return SCORE_ROW1;
  }

  private int getFrameCounter() {
    int frameCounter;
    if (isPlayEven()) {
      frameCounter = m_playCounter / 2;
    } else {
      frameCounter = m_playCounter / 2 + 1;
    }
    return frameCounter;
  }

  private boolean isPlayEven() {
    return m_playCounter % 2 == 0;
  }

  public int score() {
    int total = 0;
    for (int frm = 1; frm <= 10; frm++) {
      total += m_arrRolls[frm][SCORE_FRAME];
    }
    return total;
  }

  public void show() {
    for (int frm = 1; frm <= FRAMES - 1; frm++) {
      System.out.println(frm + ".  " + m_arrRolls[frm][SCORE_ROW1] + "  " + m_arrRolls[frm][SCORE_ROW2] + "  "
          + m_arrRolls[frm][SCORE_FRAME]);
    }
  }
}
