# Strategy

## The problem
* You have a Duck super class and some specific Ducks that override the display method.
* You get told to add a fly function, and you add it to the super class Duck
* Rubber ducks started to fly. (Not all ducks fly) 

```js
abstract class Duck {
    fun quack() = print("Quack")
    fun swim() = print("Swim")
    fun fly() = print("Fly") 
    abstract fun display()
}

class MallardDuck : Duck() {
    override fun display() = print("Display MallardDuck")
}

class RedHeadDuck : Duck() {
    override fun display() = print("Display RedHeadDuck")
}
...
```
## Possible solutions
* Overriding the fly method in the Rubber Duck. -> It will not scale when we add more ducks without the ability to fly.
* Having a Flyable interface that only ducks who fly implement. -> Changes on the fly method will need to be done in all classes that implement it. 

```js
interface Flyable {
    fun fly()
}

abstract class Duck {
    fun quack() = print("Quack")
    fun swim() = print("Swim")
    abstract fun display()
}

class MallardDuck : Duck(), Flyable {
    override fun fly() =  print("Fly")
    override fun display() = println("Display MallardDuck")
}

class RedHeadDuck : Duck(), Flyable {
	override fun fly() =  print("Fly")
    override fun display() = println("Display RedHeadDuck")
}

class RubberDuck : Duck() {
    override fun display() = println("Display RedHeadDuck")
}
```

## Design Principle
Identify what vary and separate it from  where state the same

## Design Principle
Program to an interface(super type) not an implementation. 

## Right Solution
Separate the behaviors from the Duck super class.
* Create behavior classes
* Reference them in the super class
* Initialize them in the sub class
* Allow to change the behavior dynamically

```js
// FlyBehavior
interface FlyBehavior {
    fun fly()
}

class FlyWithWings : FlyBehavior {
    override fun fly() = println("FlyWithWings")
}

class FlyNoWay : FlyBehavior {
    override fun fly() = println("FlyNoWay")
}

// Duck
abstract class Duck(var flyBehavior: FlyBehavior) {
	...
    fun performFly() = flyBehavior.fly()
}

class MallardDuck() : Duck(FlyWithWings()) { // Behavior is set as a property 'default value'. Duck(flyBehavior = FlyWithWings()) 
	...
}

class RedHeadDuck : Duck(FlyNoWay()) {
	...
}
```

Test

```js

fun test() {
    val mallardDuck = MallardDuck()
    mallardDuck.display()
    mallardDuck.swim()
    mallardDuck.performFly()
    mallardDuck.flyBehavior = FlyNoWay()
    mallardDuck.performFly()
}

> Display MallardDuck
> Swim
> FlyWithWings
> FlyNoWay
```

