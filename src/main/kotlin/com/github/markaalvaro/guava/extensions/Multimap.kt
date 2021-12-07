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
package com.github.markaalvaro.guava.extensions

// Aliases

/**
 * Alias for [com.google.common.collect.Multimap] to standardize naming with Kotlin collections
 * API and reduce number of imports to use this library.
 */
typealias Multimap<K, V> = com.google.common.collect.Multimap<K, V>

// Operators

/**
 * Same as [com.google.common.collect.Multimap.contains] but adds support for `contains` "in" operator syntax.
 */
operator fun <K, V> Multimap<K, V>.contains(pair: Pair<K, V>) = this.containsEntry(pair.first, pair.second)

// Extensions

/**
 * @return a [List] of [Pair]s that make up this multimap
 */
fun <K, V> Multimap<K, V>.toList() = entries().map { (key, value) -> Pair(key, value) }.toList()

/**
 * @return a mutable copy of this multimap as a [MutableListMultimap]
 */
fun <K, V> Multimap<K, V>.toMutableListMultimap() = mutableListMultimapOf(*toList().toTypedArray())

/**
 * @return an immutable copy of this multimap as a [ListMultimap]
 */
fun <K, V> Multimap<K, V>.toListMultimap() = listMultimapOf(*toList().toTypedArray())
