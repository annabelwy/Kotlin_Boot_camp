package main.kotlin
class  RevolverDrum<T:Any>(public var volume:Int = 6) {
    private var pointer = 0
    private val elements: MutableList<T?> = MutableList(volume) { null }
    private lateinit var type: String

    fun getPointerPosition():T?{
        return when(elements[pointer]){
            null->null  // это если самое начало, и еще ничего нет
            else->elements[pointer]
        }
    }

    fun scroll() {
        pointer = (0 until volume).random()
    }


    fun add (value:T?):Boolean{
        return when (elements.isFull() ) {
            true->false
            else->{
                if (value!= null)  type = value::class.simpleName.toString()
                elements[getFreePointer()] = value
                true
            }
        }
    }



    fun addMissingElements(otherElements:MutableList<T?>):Boolean {
        if (otherElements.isEmpty() || elements.isFull()) return false
        val copy = otherElements.copy()
        for (el in copy) {
            if (add(el)) otherElements.remove(el)
            else {
                break
            }
        }
        return true
    }

    fun shoot(): Boolean {
        if (elements.isEmpty() || getPointerPosition()==null) return false
        else {
            elements[pointer]=null
            setNextPointer()
        }
        return true
    }

    private fun setNextPointer(){
        pointer =  when {
            pointer < volume-1 -> ++pointer
            else -> 0
        }
    }



    private fun getFreePointer():Int{
        val ind = elements.indexOfFirst { it == null }
        pointer = ind
        return ind
    }

    override fun toString(): String {
        val result =StringBuilder("Structure: ${this::class.simpleName}<$type>\n")
//        result.append(this::class.simpleName+"\n")
        result.append("Objects: [")
        for (i in 0 until volume) {
            result.append(elements[(pointer + i) % volume])
            if (i < volume - 1) {
                result.append(", ")
            }
            else result.append("]")
        }
        result.append("\nPointer: ${getPointerPosition()}" )
        return result.toString()
    }

    fun countElements():Int = elements.filterNotNull().count() //создает временный список, содержащий только непустые элементы, Исходный список elements остается неизменным.

    override fun equals(other: Any?): Boolean {
        if (this===other) return true
        if (other !is RevolverDrum<*>) return false  //==if (other::class != this::class) return false
//        if (other !is RevolverDrum<*>) return false
        if (other.volume!=volume) return false
        if (other.countElements()!=countElements()) return false
        else {
            for (i in 0 until volume) {
                if (other.elements[i]!=elements[i]){ // обратиться к полю листа
                    return false
                }
            }
        }
        return true
    }


    fun unloadElement():T?{
        val element = getPointerPosition()
        elements[pointer]=null
        setNextPointer()
        return element
    }



    fun unloadAll():MutableList<T?>{
        val endList = elements.copy()
        for (i in 0 until elements.size) {
            elements[i] = null
        }
        return endList
    }



    private fun MutableList<T?>.isFull(): Boolean {
        return all { it != null }
    }

    private fun  MutableList<T?>.copy (): MutableList<T?>{
        val endList: MutableList<T?> = MutableList(size) {null}
        for (i in 0 until size) {
            endList[i]=this[i]
        }
        return endList
    }
}