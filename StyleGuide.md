# Java-Py Style guide

We all have good style, we all know the value of good style. This .md shold
bring value not by teaching you how to write good style, but in how to adjust
your code style so we are consistent and share your preference.

### Tabs or spaces?
Ben: [Watever, just don't use both](https://www.emacswiki.org/pics/static/TabsSpacesBoth.png). I do have a slight preference for spaces though.

The group is undecided at this point. 


## Tests

#### Test method name
Should be very descriptive, unlike method names:
`void moveCommandLeftMovesPlayerLeftOneTile() {`

#### The three-steps

1. Arrange
2. Pre-Assert
3. Act
4. Post-Assert


```
// Arrange
Dungeon testDun = new Dungeon (5);
Point testPoint = newPoint(3,4);
Point expectedPoint = newPoint(2,4);
Player testPlayer = newPlayer();
testDun.place(testPlayer, testPoint);


// preAssert
// missing code to auto-test testDun TODO
// missing code to auto-test player placement TODO

assertEquals(testDun.getPlayerLocation(), testPoint);
assertEquals (3, testPoint.x);
assertEquals (4, testPoint.y);
assertEquals (2, expectedPoint.x);
assertEquals (4, expectedPoint.y);

// Act
// code that directly causes player to move left

// Post Assert
assertEquals(testDun.getPlayerLocation(), expectedPoint);
```


#### Isolate what is being checked
Each test is an automated experiment on the correctness of the program. Reduce variability
to the one thing you are testing:


#### Use Junit asserts

the basic `assert` will only give information of the line  that failed.
Junit asserts such as `assertEquals` will also include information on the state of the
machine at time of failure.

#### Initilise everything inside the test method, not the test class.
Tests have no guarantee on their order, and cause side effects to variables that exist
outside the scope of the test method. It is okay to declare a class variable, but make
sure that using these variables in a test **always** involves initialising inside the test.

#### Consequence of a failed/passed tests

A good test will give a very precise understanding of what went wrong and why when it fails.

If the following fails on a particular use, it will tell you exactly when (on first use? last use? use after none remaining?)

```
void testSwordUseConsumesUsage() {
  // pre-amble such as setup, including...
  assertEquals(testSword.get_uses(), 5);
  

  // act and post-assert
  for(int i = 1; i <= 5; i++) {
    testSword.use();
    assertEquals(testSword.get_uses(), 5-i);
  }

  testSword.use();
  assertEquals(testSword.get_uses(), 0);
}
```
