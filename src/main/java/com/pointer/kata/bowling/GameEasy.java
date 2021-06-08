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

public class GameEasy {

  public static final int PINS = 10;
  private int m_playCounter = 0;
  private int[] m_arrPlays = new int[21];//there can be up to 21 rolls in bowling cause  last 10th frame can have 3 rolls.


  public GameEasy() {
    System.out.println("GAME START\n");
    System.out.println("#PLAY   | #DOWN\n");
  }

  public void roll(int... arrOfPinsDown) {
    for (int i : arrOfPinsDown) {
      roll(i);
    }
  }

  public void roll(int pinsDown) {
    m_arrPlays[m_playCounter++] = pinsDown;
    System.out.println(m_playCounter + ". " + pinsDown);
  }

  private boolean isStrike(int idx) {
    return m_arrPlays[idx] == PINS;
  }

  private boolean isSpare(int idxRoll1, int idxRoll2) {
    return (m_arrPlays[idxRoll1] + m_arrPlays[idxRoll2] == PINS && m_arrPlays[idxRoll1] != PINS);
  }

  public int score() {
    int totalScore = 0;
    int i = 0;
    for (int frm = 0; frm < 10; frm++) {
      if (isStrike(i)) {
        totalScore += PINS + m_arrPlays[i + 1] + m_arrPlays[i + 2];
        i += 1;//jump to next frame, cause if you strike on 1st roll, you cannot roll again. you jump to next frame
      } else if (isSpare(i, i + 1)) {
        totalScore += PINS + m_arrPlays[i + 2];
        i += 2;//jump to next frame
      } else {//no spare no strike
        totalScore += m_arrPlays[i] + m_arrPlays[i + 1];
        i += 2;//jump to next frame
      }
    }
    return totalScore;
  }

  public void show() {
    for (int i = 1; i < 21; i++) {
      System.out.println(i + ".  " + m_arrPlays[i]);
    }
  }
}
