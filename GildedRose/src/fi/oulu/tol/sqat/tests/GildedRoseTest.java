package fi.oulu.tol.sqat.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import fi.oulu.tol.sqat.GildedRose;
import fi.oulu.tol.sqat.Item;

public class GildedRoseTest {

	@Test
	public void testTheTruth() {
		assertTrue(true);
	}
	
	
	@Test
	public void exampleTest() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 10, 20));
		inn.oneDay();
		
		//access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		//assert quality has decreased by one
		assertEquals("Failed quality for Dexterity Vest", 19, quality);
	}
	
	
	@Test
	public void skipLoop() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.oneDay();
	}
	
	@Test
	public void quality_decreases() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 10, 20));
		inn.setItem(new Item("+5 Dexterity Vest", 0, 20));
		inn.setItem(new Item("+5 Dexterity Vest", -1, 20));
		
		inn.setItem(new Item("Conjured Mana Cake", 3, 6));
		inn.setItem(new Item("Conjured Mana Cake", 0, 6));
		inn.setItem(new Item("Conjured Mana Cake", -1, 6));
		inn.oneDay();
		
		//access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();
		int quality1 = items.get(0).getQuality();
		int quality2 = items.get(1).getQuality();
		int quality3 = items.get(2).getQuality();
		
		int quality4 = items.get(3).getQuality();
		int quality5 = items.get(4).getQuality();
		int quality6 = items.get(5).getQuality();
		
		//assert quality has decreased by one before the expiration date
		//assert quality has decreased by two after the expiration date
		assertEquals("Failed quality for Dexterity Vest", 19, quality1);
		assertEquals("Failed quality for Dexterity Vest", 18, quality2);
		assertEquals("Failed quality for Dexterity Vest", 18, quality3);
		
		assertEquals("Failed quality for Conjured Mana Cake", 5, quality4);
		assertEquals("Failed quality for Conjured Mana Cake", 4, quality5);
		assertEquals("Failed quality for Conjured Mana Cake", 4, quality6);
	}
	
	@Test
	public void quality_doesnt_decrease_below_0() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 1, 0));
		inn.setItem(new Item("Elixir of the mongoose", 0, 0));
		inn.setItem(new Item("Conjured Mana Cake", -1, 0));
		inn.oneDay();
		
		//access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();
		int quality1 = items.get(0).getQuality();
		int quality2 = items.get(1).getQuality();
		int quality3 = items.get(2).getQuality();
		
		//assert quality does not go below 0
		assertEquals("Dexterity Vest quality1 below 0", 0, quality1);
		assertEquals("Elixir quality2 below 0", 0, quality2);
		assertEquals("Conjured Mana Cake quality3 below 0", 0, quality3);
	}
	
	@Test
	public void Aged_Brie_quality_increases() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Aged Brie", 1, 0));
		inn.setItem(new Item("Aged Brie", 0, 0));
		inn.setItem(new Item("Aged Brie", -1, 0));
		inn.oneDay();
		
		//access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();
		int quality1 = items.get(0).getQuality();
		int quality2 = items.get(1).getQuality();
		int quality3 = items.get(2).getQuality();
		
		//assert quality has increased by one before the expiration date
		//assert quality increases by two after the expiration date
		assertEquals("Failed quality for Aged Brie", 1, quality1);
		assertEquals("Failed quality for Aged Brie", 2, quality2);
		assertEquals("Failed quality for Aged Brie", 2, quality3);
	}
	
	@Test
	public void Backstage_pass_quality_changes() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 11, 20));
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 6, 20));
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 1, 20));
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20));
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", -1, 20));
		inn.oneDay();
		
		//access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();
		int quality1 = items.get(0).getQuality();
		int quality2 = items.get(1).getQuality();
		int quality3 = items.get(2).getQuality();
		int quality4 = items.get(3).getQuality();
		int quality5 = items.get(4).getQuality();
		
		//assert quality increases by 1 when there are more that 10 days
		//assert quality increases by 2 when there are 6-10 days
		//assert quality increases by 3 when there are 5 days or less
		//assert quality is 0 after the concert
		assertEquals("Backstage pass quality1 incorrect", 21, quality1);
		assertEquals("Backstage pass quality2 incorrect", 22, quality2);
		assertEquals("Backstage pass quality3 incorrect", 23, quality3);
		assertEquals("Backstage pass quality4 incorrect", 0, quality4);
		assertEquals("Backstage pass quality5 incorrect", 0, quality5);
	}
	
	@Test
	public void quality_doesnt_increase_over_50() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Aged Brie", 1, 50));
		inn.setItem(new Item("Aged Brie", 0, 50));
		inn.setItem(new Item("Aged Brie", -1, 50));
		
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 11, 50));
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 6, 50));
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 1, 50));
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 11, 49));
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 6, 49));
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 1, 49));
		inn.oneDay();
		
		//access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();
		int quality1 = items.get(0).getQuality();
		int quality2 = items.get(1).getQuality();
		int quality3 = items.get(2).getQuality();
		
		int quality4 = items.get(3).getQuality();
		int quality5 = items.get(4).getQuality();
		int quality6 = items.get(5).getQuality();
		int quality7 = items.get(6).getQuality();
		int quality8 = items.get(7).getQuality();
		int quality9 = items.get(8).getQuality();
		
		//assert quality does not go above fifty
		assertEquals("Aged Brie quality1 over 50", 50, quality1);
		assertEquals("Aged Brie quality2 over 50", 50, quality2);
		assertEquals("Aged Brie quality3 over 50", 50, quality3);
		
		assertEquals("Backstage pass quality4 over 50", 50, quality4);
		assertEquals("Backstage pass quality5 over 50", 50, quality5);
		assertEquals("Backstage pass quality6 over 50", 50, quality6);
		assertEquals("Backstage pass quality7 over 50", 50, quality7);
		assertEquals("Backstage pass quality8 over 50", 50, quality8);
		assertEquals("Backstage pass quality9 over 50", 50, quality9);
	}

	@Test
	public void Sulfuras_quality_doesnt_change() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Sulfuras, Hand of Ragnaros", 1, 80));
		inn.setItem(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
		inn.setItem(new Item("Sulfuras, Hand of Ragnaros", -1, 80));
		inn.oneDay();
		
		//access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();
		int quality1 = items.get(0).getQuality();
		int quality2 = items.get(1).getQuality();
		int quality3 = items.get(2).getQuality();
		
		//assert quality does not change
		assertEquals("Sulfuras quality1 changed", 80, quality1);
		assertEquals("Sulfuras quality2 changed", 80, quality2);
		assertEquals("Sulfuras quality3 changed", 80, quality3);
	}
	
	@Test
	public void testMainMethod() {
		System.setOut(new PrintStream(outputStreamCaptor));
		GildedRose.main(new String[0]);
		assertEquals("OMGHAI!", outputStreamCaptor.toString()
			      .trim());
	}

}
