package com.gildedrose

import org.scalatest.funsuite.AnyFunSuite

class GildedRoseTest extends AnyFunSuite {

  println("\n" + "="*50)
  println("STARTING GILDED ROSE TEST SUITE")
  println("="*50 + "\n")

  // Normal items tests
  test("Normal item quality decreases by 1 before sell date") {
    println("\n[TEST START] Normal item before sell date")
    val items = Array(new Item("+5 Dexterity Vest", 10, 20))
    val app = new GildedRose(items)

    println(s"Before update: ${items(0)}")
    app.updateQuality()
    println(s"After update: ${items(0)}")

    assert(items(0).sellIn === 9)
    assert(items(0).quality === 19)
    println("[TEST PASSED] Normal item quality decreases by 1 before sell date")
  }

  test("Normal item quality decreases twice as fast after sell date") {
    println("\n[TEST START] Normal item after sell date")
    val items = Array(new Item("+5 Dexterity Vest", 0, 20))
    val app = new GildedRose(items)

    println(s"Before update: ${items(0)}")
    app.updateQuality()
    println(s"After update: ${items(0)}")

    assert(items(0).sellIn === -1)
    assert(items(0).quality === 18)
    println("[TEST PASSED] Normal item quality decreases twice as fast after sell date")
  }

  test("Normal item quality never negative") {
    println("\n[TEST START] Normal item quality never negative")
    val items = Array(new Item("+5 Dexterity Vest", 5, 0))
    val app = new GildedRose(items)

    println(s"Before update: ${items(0)}")
    app.updateQuality()
    println(s"After update: ${items(0)}")

    assert(items(0).quality === 0)
    println("[TEST PASSED] Normal item quality never negative")
  }

  // Aged Brie tests
  test("Aged Brie quality increases over time") {
    println("\n[TEST START] Aged Brie quality increases")
    val items = Array(new Item("Aged Brie", 5, 10))
    val app = new GildedRose(items)

    println(s"Before update: ${items(0)}")
    app.updateQuality()
    println(s"After update: ${items(0)}")

    assert(items(0).sellIn === 4)
    assert(items(0).quality === 11)
    println("[TEST PASSED] Aged Brie quality increases over time")
  }

  test("Aged Brie quality increases twice as fast after sell date") {
    println("\n[TEST START] Aged Brie after sell date")
    val items = Array(new Item("Aged Brie", 0, 10))
    val app = new GildedRose(items)

    println(s"Before update: ${items(0)}")
    app.updateQuality()
    println(s"After update: ${items(0)}")

    assert(items(0).sellIn === -1)
    assert(items(0).quality === 12)
    println("[TEST PASSED] Aged Brie quality increases twice as fast after sell date")
  }

  test("Aged Brie quality never exceeds 50") {
    println("\n[TEST START] Aged Brie quality cap")
    val items = Array(new Item("Aged Brie", 5, 50))
    val app = new GildedRose(items)

    println(s"Before update: ${items(0)}")
    app.updateQuality()
    println(s"After update: ${items(0)}")

    assert(items(0).quality === 50)
    println("[TEST PASSED] Aged Brie quality never exceeds 50")
  }

  // Sulfuras tests
  test("Sulfuras never changes") {
    println("\n[TEST START] Sulfuras never changes")
    val items = Array(new Item("Sulfuras, Hand of Ragnaros", 5, 80))
    val app = new GildedRose(items)

    println(s"Before update: ${items(0)}")
    app.updateQuality()
    println(s"After update: ${items(0)}")

    assert(items(0).sellIn === 5)
    assert(items(0).quality === 80)
    println("[TEST PASSED] Sulfuras never changes")
  }

  test("Sulfuras doesn't change even after sell date") {
    println("\n[TEST START] Sulfuras after sell date")
    val items = Array(new Item("Sulfuras, Hand of Ragnaros", -1, 80))
    val app = new GildedRose(items)

    println(s"Before update: ${items(0)}")
    app.updateQuality()
    println(s"After update: ${items(0)}")

    assert(items(0).sellIn === -1)
    assert(items(0).quality === 80)
    println("[TEST PASSED] Sulfuras doesn't change even after sell date")
  }

  // Backstage passes tests
  test("Backstage pass quality increases by 1 when >10 days") {
    println("\n[TEST START] Backstage pass >10 days")
    val items = Array(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20))
    val app = new GildedRose(items)

    println(s"Before update: ${items(0)}")
    app.updateQuality()
    println(s"After update: ${items(0)}")

    assert(items(0).sellIn === 14)
    assert(items(0).quality === 21)
    println("[TEST PASSED] Backstage pass quality increases by 1 when >10 days")
  }

  test("Backstage pass quality increases by 2 when <=10 days") {
    println("\n[TEST START] Backstage pass <=10 days")
    val items = Array(new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20))
    val app = new GildedRose(items)

    println(s"Before update: ${items(0)}")
    app.updateQuality()
    println(s"After update: ${items(0)}")

    assert(items(0).sellIn === 9)
    assert(items(0).quality === 22)
    println("[TEST PASSED] Backstage pass quality increases by 2 when <=10 days")
  }

  test("Backstage pass quality increases by 3 when <=5 days") {
    println("\n[TEST START] Backstage pass <=5 days")
    val items = Array(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20))
    val app = new GildedRose(items)

    println(s"Before update: ${items(0)}")
    app.updateQuality()
    println(s"After update: ${items(0)}")

    assert(items(0).sellIn === 4)
    assert(items(0).quality === 23)
    println("[TEST PASSED] Backstage pass quality increases by 3 when <=5 days")
  }

  test("Backstage pass quality drops to 0 after concert") {
    println("\n[TEST START] Backstage pass after concert")
    val items = Array(new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20))
    val app = new GildedRose(items)

    println(s"Before update: ${items(0)}")
    app.updateQuality()
    println(s"After update: ${items(0)}")

    assert(items(0).sellIn === -1)
    assert(items(0).quality === 0)
    println("[TEST PASSED] Backstage pass quality drops to 0 after concert")
  }

  test("Backstage pass quality caps at 50") {
    println("\n[TEST START] Backstage pass quality cap")
    val items = Array(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49))
    val app = new GildedRose(items)

    println(s"Before update: ${items(0)}")
    app.updateQuality()
    println(s"After update: ${items(0)}")

    assert(items(0).quality === 50)
    println("[TEST PASSED] Backstage pass quality caps at 50")
  }

  // Conjured items tests
  test("Conjured item degrades twice as fast before sell date") {
    println("\n[TEST START] Conjured item before sell date")
    val items = Array(new Item("Conjured Mana Cake", 5, 10))
    val app = new GildedRose(items)

    println(s"Before update: ${items(0)}")
    app.updateQuality()
    println(s"After update: ${items(0)}")

    assert(items(0).sellIn === 4)
    assert(items(0).quality === 8)
    println("[TEST PASSED] Conjured item degrades twice as fast before sell date")
  }

  test("Conjured item degrades twice as fast after sell date") {
    println("\n[TEST START] Conjured item after sell date")
    val items = Array(new Item("Conjured Mana Cake", 0, 10))
    val app = new GildedRose(items)

    println(s"Before update: ${items(0)}")
    app.updateQuality()
    println(s"After update: ${items(0)}")

    assert(items(0).sellIn === -1)
    assert(items(0).quality === 6)
    println("[TEST PASSED] Conjured item degrades twice as fast after sell date")
  }

  test("Conjured item quality never negative") {
    println("\n[TEST START] Conjured item quality never negative")
    val items = Array(new Item("Conjured Mana Cake", 5, 1))
    val app = new GildedRose(items)

    println(s"Before update: ${items(0)}")
    app.updateQuality()
    println(s"After update: ${items(0)}")

    assert(items(0).quality === 0)
    println("[TEST PASSED] Conjured item quality never negative")
  }

  println("\n" + "="*50)
  println("COMPLETED GILDED ROSE TEST SUITE")
  println("="*50 + "\n")
}