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

import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.InitBinder
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import jakarta.validation.Valid

/**
 * お酒記録のコントローラー
 */
@Controller
class DrinkController(val drinks: DrinkRepository) {

    val VIEWS_DRINK_CREATE_FORM = "drinks/createDrinkForm"

    @ModelAttribute("types")
    fun populateDrinkTypes(): Collection<DrinkType> {
        return this.drinks.findDrinkTypes()
    }

    @InitBinder
    fun setAllowedFields(dataBinder: WebDataBinder) {
        dataBinder.setDisallowedFields("id")
    }

    /**
     * お酒記録の新規作成フォームを表示
     */
    @GetMapping("/drinks/new")
    fun initCreationForm(model: MutableMap<String, Any>): String {
        val drink = Drink()
        model["drink"] = drink
        return VIEWS_DRINK_CREATE_FORM

    }

    /**
     * お酒記録の新規作成フォームを処理
     */
    @PostMapping("/drinks/new")
    fun processCreationForm(@Valid drink: Drink, result: BindingResult): String {
        return if (result.hasErrors()) {
            VIEWS_DRINK_CREATE_FORM
        } else {
            drinks.save(drink)
            "redirect:/drinks/${drink.id}"
        }
    }

    /**
     * お酒記録の一覧を表示
     */
    @GetMapping("/drinks")
    fun showDrinksList(model: MutableMap<String, Any>): String {
        model["drinks"] = drinks.findAll()
        return "drinks/drinksList"
    }

    /**
     * お酒記録の詳細を表示
     */
    @GetMapping("/drinks/{drinkId}")
    fun showDrink(@PathVariable("drinkId") drinkId: Int, model: MutableMap<String, Any>): String {
        model["drink"] = drinks.findById(drinkId)
        return "drinks/drinkDetails"
    }
}
