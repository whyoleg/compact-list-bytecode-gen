package dev.whyoleg.testtask

//TODO: replace with bytecode implementation
@PublishedApi
internal fun newIntCompactList(initialSize: Int): CompactList<Int> = AnyCompactList(initialSize)
