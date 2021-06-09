package com.pointer.kata.bowling;

import static com.pointer.kata.bowling.GameEasy.PINS;

/**
 * Object for ... {@Link ColorMap}.
 *
 * @author Samuel Cardonis
 * @since 2021-06-08
 */
public class FrameStrike extends FrameRegular {

  public static final int BONUS_STRIKE = 10;

  @Override
  protected int scoreFrame(int[] m_arrPlays, int i) {
    return BONUS_STRIKE + m_arrPlays[i + 1] + m_arrPlays[i + 2];
  }


}
