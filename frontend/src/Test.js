/* Edamam parsing tests */

const FOOD =  {
    "food": {
      "foodId": "food_ashlcg6b4ansska87l39xb0dnupz",
      "label": "gala apple",
      "nutrients": {
        "ENERC_KCAL": 57,
        "PROCNT": 0.25,
        "FAT": 0.12,
        "CHOCDF": 13.68
      },
      "source": "Generic"
    },
    "measures": [
      {
        "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_kilogram",
        "label": "Kilogram"
      },
      {
        "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_gram",
        "label": "Gram"
      },
      {
        "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_pound",
        "label": "Pound"
      },
      {
        "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_ounce",
        "label": "Ounce"
      },
      {
        "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_serving",
        "label": "Serving"
      },
      {
        "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_cup",
        "label": "Cup"
      },
      {
        "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_unit",
        "label": "Whole"
      },
      {
        "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_liter",
        "label": "Liter"
      },
      {
        "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_milliliter",
        "label": "Milliliter"
      },
      {
        "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_cubic_inch",
        "label": "Cubic inch"
      },
      {
        "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_quart",
        "label": "Quart"
      },
      {
        "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_drop",
        "label": "Drop"
      },
      {
        "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_gallon",
        "label": "Gallon"
      },
      {
        "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_dash",
        "label": "Dash"
      },
      {
        "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_fluid_ounce",
        "label": "Fluid ounce"
      },
      {
        "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_pint",
        "label": "Pint"
      },
      {
        "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_pinch",
        "label": "Pinch"
      },
      {
        "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_teaspoon",
        "label": "Teaspoon"
      },
      {
        "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_tablespoon",
        "label": "Tablespoon"
      },
      {
        "uri": "http://www.edamam.com/ontologies/edamam.owl#Measure_dessert_spoon",
        "label": "Dessert spoon"
      }
    ]
  }
  
export const TEST_FOOD_LIST = {
"text": "gala apple",
"parsed": [],
"hints": [FOOD, FOOD, FOOD, FOOD, FOOD]
}

export const NUTRIENTS_RESPONSE = {
    "uri": "http://www.edamam.com/ontologies/edamam.owl#6cabde74-6a45-4da5-b100-f4138a587484",
    "yield": 1,
    "calories": 22,
    "glycemicIndex": 38,
    "totalWeight": 123,
    "dietLabels": [
        "LOW_FAT",
        "LOW_SODIUM",
        "..."
    ],
    "healthLabels": [
        "FAT_FREE",
        "VEGAN",
        "..."
    ],
    "cautions": [],
    "totalNutrients": {
        "ENERC_KCAL": {
            "label": "Energy",
            "quantity": 22.14,
            "unit": "kcal"
        },
        "FAT": {
            "label": "Fat",
            "quantity": 0.246,
            "unit": "g"
        },
        "...": {}
    },
    "totalDaily": {
        "ENERC_KCAL": {
            "label": "Energy",
            "quantity": 1.107,
            "unit": "%"
        },
        "FAT": {
            "label": "Fat",
            "quantity": 0.37846153846153846,
            "unit": "%"
        },
        "...": {}
    },
    "ingredients": [
        {
            "parsed": [
                {
                    "quantity": 1,
                    "measure": "whole",
                    "food": "Tomatoes, red, ripe, raw, year round average",
                    "foodId": "Food_11529",
                    "foodURI": "http://www.edamam.com/ontologies/edamam.owl#Food_11529",
                    "weight": 123,
                    "retainedWeight": 123,
                    "measureURI": "http://www.edamam.com/ontologies/edamam.owl#Measure_unit",
                    "status": "OK"
                }
            ]
        }
    ]
}