package com.pointer.kata.bowling;
/* Company   : Pointer Software Systems LTD.
 * Project   : KataBowling
 * Package   : com.pointer.kata.bowling
 * File      : GameTest.java
 * create date: 06-06-2021
 * create by : Samuel Cardonis
 * update by :
 * update date:
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * tdd
 * demonstrates how to write test before implementation
 * the test guide the design
 * and the implementation naturally become testable
 * 10 frames
 * each frame has 2 rolls (throws)
 * score of frame is the sum of all rolls in the frame for ex. 2+3=5
 * frame can have 2 type of bonuses:
 * bonus spare:  knock down all pins in 2 rolls (for ex. 5+5)  bonus is next 1 roll
 * bonus strike: knock down all pins in 1st roll(for ex. 10)   bonus is next 2 rolls
 *
 * 10th frame can have 3 rolls to support strike on 1st roll of 10th frame
 */
public class GameTest {

  //private Game m_game;
  private GameEasy m_game;

  @BeforeEach
  void before() {
    //  m_game = new Game();
    m_game = new GameEasy();
  }

  @Test
  void roll() {
    m_game.roll(1);
    assertNotNull(m_game);
  }

  @Test
  void scoreWorstGame() {
    m_game.roll(0);
    assertEquals(0, m_game.score());
  }

  @Test
  void scoreAll1() {
    m_game.roll(1);
    assertEquals(1, m_game.score());
  }

  @Test
  void scoreFew() {
    final int rolls = 10;
    final int pinsDown = 1;
    rollSome(rolls, pinsDown);
    assertEquals(rolls * pinsDown, m_game.score());
  }

  @Test
  void scoreFew2() {
    final int rolls = 10;
    final int pinsDown = 1;
    m_game.roll(1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 4, 7, 1, 8, 0, 9, 1, 10, 10, 10);
    //2 4 6 8 10 10 8 8  10 10 10 10
    //2 4 6 8 16 17 8 8  20 30
    assertEquals(119, m_game.score());
  }

  private void rollSome(int rolls, int pinsDown) {
    for (int roll = 1; roll <= rolls; roll++) {
      m_game.roll(pinsDown);
    }
  }


  @Test
  void scoreFrame() {
    m_game.roll(5);
    m_game.roll(4);
    assertEquals(9, m_game.score());
  }

  @Test
  void spare() {
    m_game.roll(5);
    m_game.roll(5);
    assertEquals(10, m_game.score());
    m_game.roll(5);
    m_game.roll(5);
    assertEquals(25, m_game.score());
  }

  @Test
  void strike() {
    m_game.roll(10);
    assertEquals(10, m_game.score());
    m_game.roll(5);
    m_game.roll(5);
    assertEquals(30, m_game.score());
  }

  @Test
  @DisplayName("testPerfectGame all strikes = 300")
  void testPerfectGame() {
    final int rolls = 10;
    final int pinsDown = 10;
    rollSome(rolls, pinsDown);
    m_game.roll(10);//allow extra roll
    m_game.roll(10);//allow extra roll
    m_game.show();
    assertEquals(300, m_game.score());
  }

  @Test
  @DisplayName("all spares = 150")
  void spareAll() {
    final int rolls = 20;
    final int pinsDown = 5;
    rollSome(rolls, pinsDown);//15*10=150
    m_game.roll(5);
    m_game.show();
    assertEquals(150, m_game.score());//15*10=150
  }
}
