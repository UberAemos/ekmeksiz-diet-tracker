import React, { Component } from "react";
import { Formik, Form, ErrorMessage, Field } from "formik";
//import { NUTRIENTS_RESPONSE } from "../../../Test"
import { NUTRIENT_LABELS } from "../../../Constants";
import FoodDataService from "../../../api/FoodDataService";

export default class SelectedFoodComponent extends Component {
  constructor(props) {
    super(props)

    this.addFood = this.addFood.bind(this)
  }

  addFood(values) {
    let nutrition = {}
    FoodDataService.getNutrients(values.quantity, 
      values.measure, 
      this.props.selection.food.foodId
      ).then(response => {
        for (let i = 0; i < NUTRIENT_LABELS.length; i++) {
          let label = NUTRIENT_LABELS[i]
          if (response.data.totalNutrients[label]) {
            nutrition[label] = Math.round(response.data.totalNutrients[label].quantity)
          } else {
            nutrition[label] = 0
        }
      }
      let food = {
        name: this.props.selection.food.label,
        nutrition: nutrition,
        serving: values.quantity,
        measure: values.measure,
      }

      const { onSubmit } = this.props
      onSubmit(food, values.course)
    })
  }

  render() {
    return (
      <Formik
        initialValues={{
          measure: "",
          quantity: "",
          course: ""
        }}
        onSubmit={this.addFood}
      >
        <Form className="p-2 bg-light form-signup w-50 h-100 d-flex flex-column justify-content-between align-items-center ml-2">
          <ErrorMessage
            name="food"
            component="div"
            className="alert alert-warning"
          />
          <span className="text-capitalize font-weight-bold">
            {this.props.selection.food.label}
          </span>
          <div className="d-flex flex-column">
            <span className="text-primary">How much?</span>
            <div className="mt-1 d-flex flex-row justify-content-center align-items-center">
              <Field
                className="form-control w-25"
                type="number"
                name="quantity"
                placeholder="1.0"
              />
              <span className="text-secondary mx-2">servings of</span>
              <Field name="measure" component="select" className="py-1 w-50">
                <option value="" selected disabled hidden>Choose here</option>
                {this.props.selection.measures.map((measure, index) => (
                  <option key={index} value={measure.uri}>
                    {measure.label}
                  </option>
                ))}
              </Field>
            </div>
          </div>
          <div className="d-flex flex-column align-items-center w-75">
            <span className="text-primary mb-2">To which meal?</span>
            <Field component="select" className="form-control" name="course">
              <option value="" selected disabled hidden>Choose here</option>
              <option value="breakfast">Breakfast</option>
              <option value="lunch">Lunch</option>
              <option value="dinner">Dinner</option>
              <option value="snacks">Snack</option>
            </Field>
          </div>
          <button type="submit" className="btn btn-success w-50">
            Add Food
          </button>
        </Form>
      </Formik>
    );
  }
}
