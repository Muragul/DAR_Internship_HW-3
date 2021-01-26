package com.example.studentlistadvanced

import java.util.*

class CustomStack {

    private val firstQueue = LinkedList<Student>()
    private val secondQueue = LinkedList<Student>()

    fun push(x: Student) {
        if (firstQueue.size >= 5) {
            for (i in 0..3)
                secondQueue.add(firstQueue.pop())
            while (!firstQueue.isEmpty())
                firstQueue.pop()
        } else {
            while (!firstQueue.isEmpty())
                secondQueue.add(firstQueue.pop())
        }
        firstQueue.push(x)
        while (!secondQueue.isEmpty())
            firstQueue.add(secondQueue.pop())
    }

    fun pop(): Student {
        return firstQueue.pop()
    }

    fun top(): Student? {
        return firstQueue.peek()
    }

    fun empty(): Boolean {
        if (firstQueue.isEmpty() && secondQueue.isEmpty())
            return true
        return false
    }
}
