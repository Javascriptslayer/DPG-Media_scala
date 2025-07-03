                           Gilded Rose Refactoring Solution
                              🎯 Solution Overview

I've refactored the Gilded Rose inventory management system using Scala to create a maintainable, extensible solution while preserving all original functionality and adding support for "Conjured" items. The solution replaces complex conditional logic with a polymorphic design that follows SOLID principle

🧠 Key Design Decisions
1. Polymorphic Item Handling


Created an ItemUpdater trait to define update behavior

Implemented specific update logic for each item type:

NormalItem: Standard degradation

AgedBrie: Quality increases over time

Sulfuras: Immutable legendary item

BackstagePass: Special concert-ticket behavior

ConjuredItem: Degrades twice as fast (new requirement)


classDiagram
    direction LR
    
    class Item {
        <<trait>>
        +update()*
    }
    
    class GildedItem {
        -item: Item
        +update()
    }
    
    class NormalItem {
        +update()
    }
    
    class AgedBrie {
        +update()
    }
    
    class Sulfuras {
        +update()
    }
    
    class BackstagePass {
        +update()
    }
    
    class ConjuredItem {
        +update()
    }
    
    class GildedRose {
        -items: Array[Item]
        -handlers: Map[String, Item => GildedItem]
        +updateQuality()
    }
    
    GildedItem <|-- NormalItem
    GildedItem <|-- AgedBrie
    GildedItem <|-- Sulfuras
    GildedItem <|-- BackstagePass
    GildedItem <|-- ConjuredItem
    GildedItem ..> Item : Wraps
    GildedRose --> GildedItem : Creates
    GildedRose --> Item : Updates

    🚀 Benefits of This Approach
Extensibility: Add new item types in 3 simple steps:

Create new ItemUpdater implementation

Add to getUpdater matcher

Write test cases

Readability: Each item's logic is self-contained

Maintainability: Changes to one item type don't affect others

Testability: Each handler can be tested in isolation

Preserved Compatibility: Original Item class remains unchanged

1.Clone repository:

git clone https://github.com/your-username/gilded-rose-scala

2.Run the application:
sbt run
