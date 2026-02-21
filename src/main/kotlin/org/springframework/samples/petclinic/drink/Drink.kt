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

import org.springframework.format.annotation.DateTimeFormat
import org.springframework.samples.petclinic.model.BaseEntity
import java.time.LocalDate
import jakarta.persistence.*
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Digits

/**
 * 飲んだお酒の記録を表すエンティティ
 */
@Entity
@Table(name = "drinks")
class Drink : BaseEntity() {
    @NotEmpty
    @Column(name = "name")
    var name: String? = null

    @Column(name = "drink_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    var drinkDate: LocalDate? = null

    @Digits(integer = 1, fraction = 0)
    @Column(name = "rating")
    var rating: Int? = null

    @Column(name= "notes")
    var notes: String? = null

    @ManyToOne
    @JoinColumn(name = "type_id")
    var type: DrinkType? = null
}
