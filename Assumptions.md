# Assumptions

## Entity
* Strategist prediction?
  * Ben: We can start with a model that is just "next place player will be".
  * We can start with the direct player will take being a repeat of last move.
  * We probably want to have a development mode that visually shows the prediction.

* Path finding (straight line)?
  * Ben: Suggest we start with straight line. If we do it properly, implementing any changes
  * will be trivial.
* Hound behaviour
  * Ben: We can get started with a "Target square is a direct reflection"
  * Raises the question of how do we pair hounds and hunters. I suggest we just start with a
    basic interpretation of what the hound will do - something like "one hound to one hunter.
    when the hounds hunter is killed, the hound *literally* turns into a coward." That way we
    avoid getting bogged down in hound implementation.
    
         
* Conflicting moves? hunter > hound
   * Ben: I suggest we have an order of processing:
   1. Running Coward (It's in the name...)
   2. Player
   3. Strategist (is a pro-active entity)
   4. Hunter
   5. Hound
   6. Chasing Coward (same again, in the name)
    We can start with just one of each type, then we can test when they double up (two conflicting
    cowards, for example). 

* Handling boundary? (Can’t make assumptions about the sign of the player location value)
  * Ben: I think we should assume that theh world is boundless technically, but gameplay is limited to
    the nominated dungeon area.
  * This can be implemented by having a special (wall-like, an extension of wall?) entity that 
    occupies all tiles outside the chosen dungeon area.
  * Implement the barrier
  * Ben: I see above.

  * Ben: I think we should put aside the finer details of this until we have the general situation
         under control.
* Hunter and Hound
* Hunter hound association
* One hunter  > 1 hound 
* Hound only if Hunter?
* When hound closer than hunter?
* Pig in middle or kill?

## Play Mechanics

* What counts as a turn? (Sword/arrow firing)
 * Ben: I think we should *start* with user-triggered stepping.
 * Ben: 1 action, one turn: swing, drop bomb, fire arrow, etc. Pickup is free.
* Player/enemy collision
 * Ben: Start with "Player and enemy are on same tile". If swords have a reach, we can implement later.
* Stepping time? turn? 
 * Ben: Start with triggered on user-input, can modify for periodic later.

## Items
* Number of doors? (review later)
 * Ben: Can we start with three? I'll put something about this in the extras area.
* Key-door placement?
 * Ben: I liked the idea of "can only place one key or door for each color"
* inventory 
* Pits behave like walls to enemies, blocking them.
  * James: Is this a reasonable assumption to make?
  
  

## Levels
* map size?
 * Ben: Can we start with something close to the ass. spec? 20. We can make that flexible later.


## Extensions (extras)
* Treasure chest
* Dungeon unwinable?
* Can specify door/key colors.
* Can add a door color to options.
* Can place inert/decorative doors of distinct color without count limit.

## Technical Assumptions 

* Design mode? 
* Handling boundary? (Can’t make assumptions about the sign of the player location value
* Implement the barrier


* Path finding behaviour? A* algorithm (pretty good guess of best/decent path)

## Inventory

* Items that can be picked up
* keys 
* bomb 
* arrow 
* treasure 
* potion
