/**
 * Copyright 2021-present Mark Alvaro. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
@file:Suppress("NAME_SHADOWING")

package com.github.markaalvaro.guava.extensions

import com.google.common.collect.ImmutableListMultimap

// Aliases

/**
 * Alias for [com.google.common.collect.ImmutableListMultimap] to standardize naming with Kotlin collections
 * API and reduce number of imports to use this library.
 */
typealias ListMultimap<K, V> = ImmutableListMultimap<K, V>

// Creation Methods

/**
 * Returns an immutable [ListMultimap] with mappings given by the passed-in [Pair]s applied.
 *
 * @param pairs the [Pair]s to add to the constructed multimap
 * @return an immutable multimap containing specified [Pair]s
 */
fun <K, V> listMultimapOf(vararg pairs: Pair<K, V>): ListMultimap<K, V> {
    val mutableMultimap = mutableListMultimapOf<K, V>()
    pairs.forEach { (key, value) -> mutableMultimap.put(key, value) }
    return ListMultimap.copyOf(mutableMultimap)
}

/**
 * Builds a new immutable [ListMultimap] by creating a [MutableListMultimap], applying any transformations
 * specified in the [builderAction] (e.g. `put("key", "value)`) and then making an immutable copy.
 *
 * @param builderAction a consumer of a [MutableListMultimap] describing what operations should be applied to
 * populate the multimap before an immutable copy is made
 * @return an immutable multimap containing the values given by applying the [builderAction] to an empty [MutableListMultimap]
 */
fun <K, V> buildListMultimap(builderAction: MutableListMultimap<K, V>.() -> Unit): ListMultimap<K, V> {
    val mutableMultimap = mutableListMultimapOf<K, V>()
    builderAction(mutableMultimap)
    return ListMultimap.copyOf(mutableMultimap)
}
