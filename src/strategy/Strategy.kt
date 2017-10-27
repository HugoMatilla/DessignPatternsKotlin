package strategy

/**
 * Created by hugomatilla on 27/10/2017.
 */

fun main(args: Array<String>) {
    var line = "--- Ready --- \n"
    while (true) {
        try {
            println(line)
            doThis()
        } catch(e: Exception) {
            println("Error ${e.message}")
        }
        line = readLine() ?: ""
    }
}

fun doThis() {
    test()
    println("-------")
    val m = MallardDuck()
    m.display()
    m.swim()
    m.performQuack()
    m.performFly()
    m.flyBehavior = FlyNoWay()
    m.quackBehavior = MuteQuack()
    m.performQuack()
    m.performFly()

    println("------")

    val r = RedHeadDuck()
    r.display()
    r.swim()
    r.performFly()
    r.performQuack()
}

fun Any.getClassName() = javaClass.name.removePrefix(javaClass.`package`.name + ".")
//fun Any.println(message: String) = println("${getClassName()}  $message")

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

// QuackBehavior
interface QuackBehavior {
    fun quack()
}

class Quack : QuackBehavior {
    override fun quack() = println("Quack")
}

class Squeak : QuackBehavior {
    override fun quack() = println("Squeak")
}

class MuteQuack : QuackBehavior {
    override fun quack() = println("MuteQuack")
}

// Duck
abstract class Duck(var flyBehavior: FlyBehavior, var quackBehavior: QuackBehavior) {

    fun swim() = println("Swim")
    abstract fun display()

    fun performFly() = flyBehavior.fly()
    fun performQuack() = quackBehavior.quack()
}

class MallardDuck() : Duck(FlyWithWings(), Quack()) {
    override fun display() = println("Display MallardDuck")
}

class RedHeadDuck : Duck(FlyNoWay(), Squeak()) {
    override fun display() = println("Display RedHeadDuck")
}

fun test() {
    val mallardDuck = MallardDuck()
    mallardDuck.display()
    mallardDuck.swim()
    mallardDuck.performFly()
    mallardDuck.flyBehavior = FlyNoWay()
    mallardDuck.performFly()
}
