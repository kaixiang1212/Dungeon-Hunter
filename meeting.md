# Discussion 29/09 java.py group meeting: #

 
 * Abstract out passing in Point: consider 2D array
 * Potion Buff: use decorator pattern?
 * Json: hotswap object (how to implement?) Someone needs to learn json.
 * Save! Part of specs or extension?
 * Scenes w/roots
 
# Player/ Items / Decorator pattern/: #

 * Remove logic from player
 * Player -> handles pickup
 * Delegating responsibility to the item rather than the player. The item knows that it is picked up rather than the player.
 * Item calls player? Item pickup(player);
 * Item decides if it can be picked up by the player
 
#### Diagram example: ####
Condition Detection
[Player/item collision -> Player -> handles pickup]
change to: Item.pickup(player);
 
#### Design FIX: ####
 * Player should only move and everything reacts to the player. Enemies get player move and then execute action.
 * Decorate win condition check?? Decorate subsequent win conditions. Win condition check.
 





### Win condition: decorator -> dungeon state: ###

 * set it based on dungeon state nothing
 * Tests for win condition: nothing exit only reached enemies only killed.
 * Object??
 
 * Extending tile:
    * Extending switch and doors from tile.
 
Begun implemented in meeting:

 * new point system send in x,y - implemented by Ben
     
## TODO: ##


  * Point abstraction (Refactor) (think about 2D array)
  * Delete branches - Ben
  * Item.pickup from player.pickup - Gary
  * *Json (save extension?) - Richard & Ben
  * *Win Cond. Decorator - Richard
  * *Hound - Gary
  * *Switch - Kai
  * *Door - Kai 
  * ? Skeleton for Gui ? - James 
