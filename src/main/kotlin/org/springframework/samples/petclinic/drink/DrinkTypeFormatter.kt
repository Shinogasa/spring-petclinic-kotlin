/*
 * Copyright 2002-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.drink

import org.springframework.format.Formatter
import org.springframework.stereotype.Component
import java.text.ParseException
import java.util.*

/**
 * Instructs Spring MVC on how to parse and print elements of type 'DrinkType'
 */
@Component
class DrinkTypeFormatter(val drinks: DrinkRepository) : Formatter<DrinkType> {

    override fun print(drinkType: DrinkType, locale: Locale): String
            = drinkType.name ?: ""

    override fun parse(text: String, locale: Locale): DrinkType {
        val findDrinkTypes = this.drinks.findDrinkTypes()
        return findDrinkTypes.find { it.name == text } ?:
                throw ParseException("type not found: " + text, 0)
    }
}
