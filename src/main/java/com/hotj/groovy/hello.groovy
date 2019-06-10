package com.hotj.groovy

import com.hotj.java.algorithom.sort.QuickSort

println "hello r u qdy"

println "hi i m qdy"

def coll = []

coll.add("python")
coll << 'php'
coll[2] = 'java'
println coll
println coll.dump()

coll.each {
    println it
}

def z = 1..4
println z.class
println z.dump()

println z.subList(0,3).get(2)

"ITERATION".each{
 println "hello ${it}"
}

int[] q = [3, 6, 1, 2]
QuickSort.quickSort(q)
println q