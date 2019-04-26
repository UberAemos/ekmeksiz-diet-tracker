import React, { Component } from "react";
import { Formik, Form, ErrorMessage, Field } from "formik";
import FoodDataService from "../../../api/FoodDataService";

export default class SearchFoodComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            foods : ""
        }

        this.searchFood = this.searchFood.bind(this)
    }

    searchFood(values) {
        const { onSubmit } = this.props

        FoodDataService.searchFood(values.food).then(response => 
            onSubmit(response.data))
      }

  render() {
    return (
      <Formik
        initialValues={{
          food: ""
        }}
        onSubmit={this.searchFood}
        validateOnChange={false}
        validateOnBlur={false}
        validate={this.validateSearch}
      >
        <Form className="form-signup w-50 h-25 mb-2">
          <h5 className="d-flex mb-2">Search our food database by name:</h5>
          <ErrorMessage
            name="food"
            component="div"
            className="alert alert-warning"
          />
          <div className="flex-row d-flex">
            <Field
              className="form-control mr-2"
              type="text"
              name="food"
              placeholder="Recipe name"
            />
            <button type="submit" className="btn btn-success">
              Search
            </button>
          </div>
        </Form>
      </Formik>
    );
  }
}
