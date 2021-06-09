package com.pointer.kata.bowling;

/**
 * Object for ... {@Link ColorMap}.
 *
 * @author Samuel Cardonis
 * @since 2021-06-08
 */
public class FrameRegular {

  protected int scoreFrame(int[] m_arrPlays, int i) {
    return m_arrPlays[i] + m_arrPlays[i + 1];
  }

}
