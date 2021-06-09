package com.pointer.kata.bowling;
/* Company   : Pointer Software Systems LTD.
 * Project   : KataBowling
 * Package   : com.pointer.kata.bowling
 * File      : Game.java
 * create date: 06-06-2021
 * create by : Samuel Cardonis
 * update by :
 * update date:
 *
 */

public class GameEasy {

  public static final int PINS = 10;
  private int m_playCounter = 0;
  private int[] m_arrPlays = new int[21];//this arr is to remember all rows for a good calculation of score. there can be up to 21 rolls in bowling if all first 9 frames are with no strike but at last 10th has 3 strikes, (frame can have 3 rolls).

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

  public int score() {
    int totalScore = 0;
    int i = 0;
    for (int frm = 0; frm < 10; frm++) {
      FrameRegular gh = getFrameInstance(i);

      if (m_arrPlays[i] == PINS) {//strike
        totalScore += gh.scoreFrame(m_arrPlays, i);
        i += 1;//jump to next frame, cause if you strike on 1st roll, you cannot roll again. you jump to next frame
      } else if ((m_arrPlays[i] + m_arrPlays[i + 1] == PINS && m_arrPlays[i] != PINS)) {//spare
        totalScore += gh.scoreFrame(m_arrPlays, i);
        i += 2;//jump to next frame
      } else {//no spare no strike
        totalScore += gh.scoreFrame(m_arrPlays, i);
        i += 2;//jump to next frame
      }

    }
    return totalScore;
  }

  private FrameRegular getFrameInstance(int i) {
    FrameRegular gh;
    if (m_arrPlays[i] == PINS) {//strike
      return new FrameStrike();
    } else if ((m_arrPlays[i] + m_arrPlays[i + 1] == PINS && m_arrPlays[i] != PINS)) {//spare
      return new FrameSpare();
    } else {//no spare no strike
      return new FrameRegular();
    }
  }

  public void show() {
    for (int i = 1; i < 21; i++) {
      System.out.println(i + ".  " + m_arrPlays[i]);
    }
  }
}
