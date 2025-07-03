package com.gildedrose

class GildedRose(val items: Array[Item]) {
  private trait ItemUpdater {
    def update(item: Item): Unit
  }

  private object NormalItem extends ItemUpdater {
    def update(item: Item): Unit = {
      item.sellIn -= 1
      val degradeAmount = if (item.sellIn < 0) 2 else 1
      item.quality = Math.max(item.quality - degradeAmount, 0)
    }
  }

  private object AgedBrie extends ItemUpdater {
    def update(item: Item): Unit = {
      item.sellIn -= 1
      val increaseAmount = if (item.sellIn < 0) 2 else 1
      item.quality = Math.min(item.quality + increaseAmount, 50)
    }
  }

  private object Sulfuras extends ItemUpdater {
    def update(item: Item): Unit = () // No changes
  }

  private object BackstagePass extends ItemUpdater {
    def update(item: Item): Unit = {
      val oldSellIn = item.sellIn
      item.sellIn = oldSellIn - 1

      if (item.sellIn < 0) {
        item.quality = 0
      } else {
        val increase =
          if (oldSellIn <= 5) 3
          else if (oldSellIn <= 10) 2
          else 1
        item.quality = Math.min(item.quality + increase, 50)
      }
    }
  }

  private object ConjuredItem extends ItemUpdater {
    def update(item: Item): Unit = {
      item.sellIn -= 1
      val degradeAmount = if (item.sellIn < 0) 4 else 2
      item.quality = Math.max(item.quality - degradeAmount, 0)
    }
  }

  private def getUpdater(name: String): ItemUpdater = name match {
    case "Aged Brie" => AgedBrie
    case "Sulfuras, Hand of Ragnaros" => Sulfuras
    case "Backstage passes to a TAFKAL80ETC concert" => BackstagePass
    case name if name.startsWith("Conjured") => ConjuredItem
    case _ => NormalItem
  }

  def updateQuality(): Unit = {
    items.foreach { item =>
      getUpdater(item.name).update(item)
    }
  }
}