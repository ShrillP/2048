/**
 * Author: Shrill Patel
 * Revised: April 12, 2021
 * 
 * Description: Test class for the game board tiles.
 */

package src;

import java.awt.*;
import static org.junit.Assert.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class TestTileT {

	private TileT tile;

	@Before 
	public void setUp() {
		tile = new TileT(2, 0, 0);
		tile.setTileColour(tile.getValue());
	}

	@After
	public void tearDown() {
		tile = null;
	}

	@Test
	public void testGetValue() {
		assertTrue(tile.getValue() == 2);
	}

	@Test
	public void testGetPosition() {
		int[] expected = new int[] {0, 0};
		assertArrayEquals(tile.getPosition(), expected);
	}

	@Test
	public void testGetTileColour() {
		Color expected = new Color(238, 228, 218);
		assertTrue(expected.equals(tile.getTileColour()));
	}

	@Test (expected=IllegalArgumentException.class)
	public void testSetValueException() {
		tile.setValue(-1);
	}

	@Test
	public void testSetValue1() {
		tile.setValue(0);
		assertTrue(tile.getValue() == 0);
	}

	@Test
	public void testSetValu2() {
		tile.setValue(128);
		assertTrue(tile.getValue() == 128);
	}

	@Test (expected=IllegalArgumentException.class)
	public void testSetPositionException1() {
		tile.setPosition(new int[] {1, 2, 3});
	}

	@Test (expected=IllegalArgumentException.class)
	public void testSetPositionException2() {
		tile.setPosition(new int[] {1});
	}

	@Test
	public void testSetPosition() {
		tile.setPosition(new int[] {1, 2});
		assertArrayEquals(tile.getPosition(), new int[] {1, 2});
	}

	@Test (expected=IllegalArgumentException.class)
	public void testSetTileColourException() {
		tile.setTileColour(-1);
	}

	@Test
	public void testSetTileColour1() {
		tile.setTileColour(0);
		assertTrue(tile.getTileColour().equals(new Color(205, 193, 180)));
	}

	@Test
	public void testSetTileColour2() {
		tile.setTileColour(123);
		assertTrue(tile.getTileColour().equals(new Color(238, 228, 218)));
	}

	@Test
	public void testSetTileColour3() {
		tile.setTileColour(512);
		assertTrue(tile.getTileColour().equals(new Color(228, 193, 42)));
	}

}